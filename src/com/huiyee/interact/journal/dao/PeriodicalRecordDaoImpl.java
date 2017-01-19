package com.huiyee.interact.journal.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.interact.journal.model.JournalContent;
import com.huiyee.interact.journal.model.JournalShareRecord;

public class PeriodicalRecordDaoImpl implements IPeriodicalRecordDao{
	private JdbcTemplate jdbcTemplate;
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<JournalContent> findJournalContentByJ(int start, int size,long jid)
	{
		String sql = "select * from es_interact_journal_content where jid=? limit ?,?";
		return jdbcTemplate.query(sql, new Object[]{jid,start,size},new MyRowMapper());
	}
	
	@Override
	public int findPeriodicalRecordTotal(long jid)
	{
		return jdbcTemplate.queryForInt("select count(*) from es_interact_journal_content where jid=?", new Object[]{ jid });
	}

	class MyRowMapper implements RowMapper
	{
		public Object mapRow(ResultSet rs, int arg1) throws SQLException
		{
			JournalContent jc = new JournalContent();
			jc.setId(rs.getLong("id"));
			jc.setJid(rs.getLong("jid"));
			jc.setTitle(rs.getString("title"));
			jc.setCount(rs.getInt("count"));
			jc.setLastsharetime(rs.getDate("lastsharetime"));
			return jc;
		}
	}
	


	@Override
	public List<JournalShareRecord> findJournalSR(int start, int size, long contentid)
	{
		String sql = "select * from es_feature_interact_journal_share_record jsr join es_sina_user su on jsr.wbuid=su.wbuid where jsr.contentid=? limit ?,?";
		return jdbcTemplate.query(sql, new Object[]{contentid,start,size},new MyRowMapper2());
	}

	@Override
	public int findJournalSRTotal(long contentid)
	{
		return jdbcTemplate.queryForInt("select count(*) from es_feature_interact_journal_share_record jsr join es_sina_user su on jsr.wbuid=su.wbuid where jsr.contentid=?", new Object[]{ contentid });
	}
	
	class MyRowMapper2 implements RowMapper
	{
		public Object mapRow(ResultSet rs, int arg1) throws SQLException
		{
			JournalShareRecord jsr = new JournalShareRecord();
			jsr.setId(rs.getLong("jsr.id"));
			jsr.setWbid(rs.getLong("jsr.wbid"));
			jsr.setCreatetime(rs.getDate("jsr.createtime"));
			jsr.setIp(rs.getString("jsr.ip"));
			jsr.setTerminal(rs.getString("jsr.terminal"));
			jsr.setSource(rs.getString("jsr.source"));
			jsr.setNickname(rs.getString("su.nickname"));
			return jsr;
		}
	}



	@Override
	public List<JournalShareRecord> searchnickname(int start, int size, String nickname, long contentid)
	{
		if(nickname == null){
			nickname="";
		}
		String sql = "select * from es_feature_interact_journal_share_record jsr join es_sina_user su on jsr.wbuid=su.wbuid where jsr.contentid=? and su.nickname like '%"+nickname+"%' limit ?,?";
		return jdbcTemplate.query(sql, new Object[]{contentid,start,size},new MyRowMapper2());
	}

	@Override
	public int searchnicknameTotal(String nickname, long contentid)
	{
		if(nickname == null){
			nickname="";
		}
		return jdbcTemplate.queryForInt("select count(*) from es_feature_interact_journal_share_record jsr join es_sina_user su on jsr.wbuid=su.wbuid where jsr.contentid=? and su.nickname like '%"+nickname+"%' ", new Object[]{ contentid });
	}

	@Override
	public List<JournalShareRecord> searchtimeorts(int start, int size, String begintime, String endtime, String terminal, String source, long contentid)
	{
		if(terminal.equals("不限") || terminal == null){
			terminal="";
		}
		if(source.equals("不限") || source == null){
			source="";
		}
		if(begintime ==null || (begintime.equals(""))){
			begintime ="0000-00-00 00:00:00";
		}
		if(endtime ==null || (endtime.equals(""))){
			endtime=df.format(new Date());
		}
		String sql = "select * from es_feature_interact_journal_share_record jsr join es_sina_user su on jsr.wbuid=su.wbuid where jsr.contentid=? and (?<jsr.createtime and ?>jsr.createtime) and jsr.terminal like '%"+terminal+"%' and jsr.source like '%"+source+"%' limit ?,?";
		return jdbcTemplate.query(sql, new Object[]{contentid,begintime,endtime,start,size},new MyRowMapper2());
	}

	@Override
	public int searchtimeortsTotal(String begintime, String endtime, String terminal, String source, long contentid)
	{
		if(terminal.equals("不限") || terminal == null){
			terminal="";
		}
		if(source.equals("不限") || source == null){
			source="";
		}
		if(begintime ==null || (begintime.equals(""))){
			begintime ="0000-00-00 00:00:00";
		}
		if(endtime ==null || (endtime.equals(""))){
			endtime=df.format(new Date());
		}
		return jdbcTemplate.queryForInt("select count(*) from es_feature_interact_journal_share_record jsr join es_sina_user su on jsr.wbuid=su.wbuid where jsr.contentid=? and (?<jsr.createtime and ?>jsr.createtime) and jsr.terminal like '%"+terminal+"%' and jsr.source like '%"+source+"%'", new Object[]{ contentid ,begintime,endtime});
	}

	@Override
	public List<JournalShareRecord> bindsource()
	{
		return jdbcTemplate.query("select source,terminal from es_feature_interact_journal_share_record ",new MyRowMapper3());
	}
	
	class MyRowMapper3 implements RowMapper
	{
		public Object mapRow(ResultSet rs, int arg1) throws SQLException
		{
			JournalShareRecord jsr = new JournalShareRecord();
			jsr.setSource(rs.getString("source"));
			jsr.setTerminal(rs.getString("terminal"));
			return jsr;
		}
	}

}
