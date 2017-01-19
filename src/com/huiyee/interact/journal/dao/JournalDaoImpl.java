package com.huiyee.interact.journal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.interact.journal.dto.JournalDto;
import com.huiyee.interact.journal.model.JournalContent;
import com.huiyee.interact.journal.model.JournalModel;

public class JournalDaoImpl implements IJournalDao
{

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate(){
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	@Override
	public List<JournalModel> findJournalList(long ownerid, int start, int size)
	{
		String sql="select id,title,isshare,balance,createtime from es_interact_journal where ownerid=? and status!='DEL' order by createtime desc limit ?,?";
		return getJdbcTemplate().query(sql, new Object[]{ownerid,start,size},new JournalMapper());
	}
	class JournalMapper implements RowMapper{
		@Override
		public Object mapRow(ResultSet rs, int arg) throws SQLException {
			JournalModel model=new JournalModel();
			model.setId(rs.getLong("id"));
			model.setTitle(rs.getString("title"));
			model.setIsshare(rs.getString("isshare"));
			model.setBalance(rs.getInt("balance"));
			model.setCreatetime(rs.getTimestamp("createtime"));
			return model;
		}
	}

	@Override
	public int findJournalTotal(long ownerid)
	{
		String sql="select count(*) from es_interact_journal where ownerid=?";
		return getJdbcTemplate().queryForInt(sql,new Object[]{ownerid});
	}

	private static final String FIND_JOURNAL_MODEL_BY_ID="select * from esite.es_interact_journal where id = ? and ownerid = ?";
	@Override
	public JournalModel findJournalModelById(long id, long ownerid) {
		Object[] params={id,ownerid};
		List<JournalModel> list = getJdbcTemplate().query(FIND_JOURNAL_MODEL_BY_ID, params, new JournalMapper());
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	private static final String FIND_JOURNAL_CONTENT_BY_JID="select * from esite.es_interact_journal_content where jid = ? and status !='DEL' order by id desc";
	@Override
	public List<JournalContent> findJournalContentByJid(long jid) {
		Object[] params={jid};
		return getJdbcTemplate().query(FIND_JOURNAL_CONTENT_BY_JID, params, new JournalContentRowmapper());
	}
	
	class JournalContentRowmapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			JournalContent jc = new JournalContent();
			jc.setId(rs.getLong("id"));
			jc.setJid(rs.getLong("jid"));
			jc.setTitle(rs.getString("title"));
			jc.setContent(rs.getString("content"));
			jc.setBimg(rs.getString("bimg"));
			jc.setSimg(rs.getString("simg"));
			jc.setUrl(rs.getString("url"));
			jc.setTag(rs.getString("tag"));
			jc.setSharecontent(rs.getString("sharecontent"));
			jc.setSharepic(rs.getString("sharepic"));
			jc.setCreatetime(rs.getTimestamp("createtime"));
			jc.setUpdatetime(rs.getTimestamp("updatetime"));
			jc.setStatus(rs.getString("status"));
			jc.setCount(rs.getInt("count"));
			jc.setLastsharetime(rs.getTimestamp("lastsharetime"));
			return jc;
		}
		
	}

	private static final String FIND_JOURNAL_CONTENT_LIST = "select * from esite.es_interact_journal_content a where jid = ? and status!='DEL' order by createtime desc limit ?,?";
	@Override
	public List<JournalContent> findJournalContentList(long jid,int start, int size) {
		Object[] params = { jid,start,size };
		return getJdbcTemplate().query(FIND_JOURNAL_CONTENT_LIST, params, new JournalContentMapper());
	}

	class JournalContentMapper implements RowMapper{
		@Override
		public Object mapRow(ResultSet rs, int arg) throws SQLException {
			JournalContent jc = new JournalContent();
			jc.setId(rs.getLong("id"));
			jc.setJid(rs.getLong("jid"));
			jc.setTitle(rs.getString("title"));
			jc.setContent(rs.getString("content"));
			jc.setBimg(rs.getString("bimg"));
			jc.setSimg(rs.getString("simg"));
			jc.setUrl(rs.getString("url"));
			jc.setTag(rs.getString("tag"));
			jc.setSharecontent(rs.getString("sharecontent"));
			jc.setSharepic(rs.getString("sharepic"));
			jc.setCreatetime(rs.getTimestamp("createtime"));
			jc.setUpdatetime(rs.getTimestamp("updatetime"));
			return jc;
		}
	}

	@Override
	public int findJournalContentTotal(long jid) {
		String sql="select count(*) from esite.es_interact_journal_content where jid=? and status!='DEL'";
		return getJdbcTemplate().queryForInt(sql,new Object[]{jid});
	}

	private static final String SAVE_JOURNAL_CONTENT = "insert into esite.es_interact_journal_content(jid,title,content,bimg,simg,url,tag,createtime) values(?,?,?,?,?,?,?,now())";
	@Override
	public long saveJournalContent(final long jid,final JournalDto dto) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0)
					throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(SAVE_JOURNAL_CONTENT,
						new String[] { "id" });
				int i = 1;
				ps.setLong(i++, jid);
				ps.setString(i++, dto.getJournal().getTitle());
				ps.setString(i++, dto.getJournal().getContent());
				ps.setString(i++,dto.getJournal().getBimg());
				ps.setString(i++, dto.getJournal().getSimg());
				ps.setString(i++, dto.getJournal().getUrl());
				ps.setString(i++, dto.getJournal().getTag());
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	private static final String FIND_JOURNAL_CONTENT_BY_ID = "select * from esite.es_interact_journal_content where id = ? ";
	@Override
	public JournalContent findJournalContentById(long id) {
		Object[] params = { id};
		List<JournalContent> list = getJdbcTemplate().query(FIND_JOURNAL_CONTENT_BY_ID, params, new JournalContentMapper());
		if(list != null && list.size()>0){
	 		return list.get(0);
	 	}
		return null;
	}

	private static final String UPDATE_JOURNAL_CONTENT = "update esite.es_interact_journal_content set title=?,content=?,bimg=?,simg=?,url=?,tag=? where id = ?";
	@Override
	public long updateJournalContent(long id, JournalDto dto) {
		Object[] params = {dto.getJournal().getTitle(),dto.getJournal().getContent(),dto.getJournal().getBimg(),dto.getJournal().getSimg(),dto.getJournal().getUrl(),dto.getJournal().getTag(),id};
		return getJdbcTemplate().update(UPDATE_JOURNAL_CONTENT,params);
	}

	private static final String UPDATE_JOURNAL_SHARECONTENT = "update esite.es_interact_journal_content set sharecontent=?,sharepic=? where id = ?";
	@Override
	public long updateJournalShareContent(long id, JournalDto dto) {
		Object[] params = {dto.getJournal().getSharecontent(),dto.getJournal().getSharepic(),id};
		return getJdbcTemplate().update(UPDATE_JOURNAL_SHARECONTENT,params);
	}
	@Override
	public int deleteJournal(long id)
	{
		String sql="update es_interact_journal set status='DEL' where id=?";
		return getJdbcTemplate().update(sql, new Object[]{id});
	}

	@Override
	public JournalModel findOneJournal(long id)
	{
		String sql="select id, title,isshare,balance from es_interact_journal where id=?";
		List<JournalModel> list= getJdbcTemplate().query(sql, new Object[]{id},new JournalMapper1());
		if(list.size()>0){
			JournalModel jm=list.get(0);
			return jm;
		}
		return null;
	}
	class JournalMapper1 implements RowMapper{
		@Override
		public Object mapRow(ResultSet rs, int arg) throws SQLException {
			JournalModel model=new JournalModel();
			model.setId(rs.getLong("id"));
			model.setTitle(rs.getString("title"));
			model.setIsshare(rs.getString("isshare"));
			model.setBalance(rs.getInt("balance"));
			return model;
		}
	}

	@Override
	public int saveJournal(long ownerid, String title, String isshare, int balance)
	{
		String sql="insert into es_interact_journal (ownerid,title,isshare,balance,createtime) values(?,?,?,?,now())";
		return getJdbcTemplate().update(sql,new Object[]{ownerid,title,isshare,balance});
	}

	@Override
	public int updateJournal(long id,String title, String isshare, int balance)
	{
		String sql="update es_interact_journal set title=?,isshare=?,balance=? where id=?";
		return getJdbcTemplate().update(sql,new Object[]{title,isshare,balance,id});
	}

	private static final String SAVE_JOURNAL_RECORD="insert into esite.es_feature_interact_journal_share_record(pageid,contentid,wbuid,wbid,sharecontent,sharepic,createtime,ip,terminal,source)values(?,?,?,?,?,?,now(),?,?,?)";
	@Override
	public int saveJournalRecord(long pageid, long wbuid, long contentid,
			String wbid, String content, String pic, String ip,
			String terminal, String source) {
		Object[] params={pageid,contentid,wbuid,wbid,content,pic,ip,terminal,source};
		return getJdbcTemplate().update(SAVE_JOURNAL_RECORD,params);
	}
	
	private static final String UPDATE_CONTENT_COUNT="update esite.es_interact_journal_content set count = count + 1 ,lastsharetime = now() where id = ?";
	@Override
	public int updateContentCount(long contentid){
		Object[] param={contentid};
		return getJdbcTemplate().update(UPDATE_CONTENT_COUNT,param);
	}

	@Override
	public int deleteJournalContent(long id) {
		String sql="update esite.es_interact_journal_content set status='DEL' where id=?";
		return getJdbcTemplate().update(sql,new Object[]{id});
	}

	private static final String FIND_BALANCE_BY_CONETNTID="select balance from esite.es_interact_journal where id = (select jid from esite.es_interact_journal_content where id =?)";
	@Override
	public int findBalanceByContentId(long contentid) {
		Object[] params={contentid};
		return getJdbcTemplate().queryForInt(FIND_BALANCE_BY_CONETNTID, params);
	}

}
