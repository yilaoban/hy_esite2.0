package com.huiyee.interact.research.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.interact.lottery.model.Lottery;
import com.huiyee.interact.research.dto.ResearchDto;
import com.huiyee.interact.research.model.ResearchModel;
import com.huiyee.interact.research.model.ResearchQuestionModel;
import com.huiyee.interact.research.model.ResearchQuestionOption;
import com.huiyee.interact.research.model.ResearchRecord;
public class ResearchDaoImpl implements IResearchDao{
	
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate(){
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}

	
	@Override
	public int findResearchListTotal(long ownerid, long omid){
		String sql="select count(*) from esite.es_interact_research where ownerid=? and omid=? and status !='DEL'";
		return getJdbcTemplate().queryForInt(sql,new Object[]{ownerid, omid});
	}

	@Override
	public List<ResearchModel> findVoteList(long ownerid, int start, int size, long omid){
		String sql="select id,ownerid,title,starttime,endtime from esite.es_interact_research where ownerid=? and omid=? and status !='DEL' order by id desc limit ?,?";
		return getJdbcTemplate().query(sql, new Object[]{ownerid,omid,start,size},new ResearchMapper());
	}
	
	class ResearchMapper implements RowMapper{
		@Override
		public Object mapRow(ResultSet rs, int arg) throws SQLException {
			ResearchModel model=new ResearchModel();
			model.setId(rs.getLong("id"));
			model.setOwnerid(rs.getLong("ownerid"));
			model.setTitle(rs.getString("title"));
			model.setStarttimeDate(rs.getTimestamp("starttime"));
			model.setEndtimeDate(rs.getTimestamp("endtime"));
			return model;
		}
	}

	class LotteryMapper implements RowMapper{
	       @Override
	       public Object mapRow(ResultSet rs, int arg) throws SQLException {
	    	   Lottery lottery = new Lottery();
	    	   lottery.setId(rs.getLong("id"));
	    	   lottery.setName(rs.getString("name"));
	    	   lottery.setType(rs.getString("type"));
	           return lottery;
	       }
	   }
	
	private static final String FIND_LOTTERY_BY_TYPE = "select es.id,es.name,es.type from esite.es_interact_lottery es where es.owner = ? and type = ? and status !='D' order by es.id desc";
	@Override
	public List<Lottery> findLotteryByType(long ownerid, String type){
		Object[] params = { ownerid,type };
		return getJdbcTemplate().query(FIND_LOTTERY_BY_TYPE, params, new LotteryMapper());
	}

	private static final String SAVE_RESEARCH_DESIGN = "insert into esite.es_interact_research(ownerid,title,content,starttime,endtime,totallimit,daylimit,islottery,lotterychance"
			+ ",lotteryid,balance,startnote,endnote,maxlotterychance,omid,createtime) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now())";
	@Override
	public long saveResearchDesign(final long ownerid, final ResearchDto dto,final long omid){
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0)
					throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(SAVE_RESEARCH_DESIGN,
						new String[] { "id" });
				int i = 1;
				ps.setLong(i++, ownerid);
				ps.setString(i++, dto.getResearch().getTitle());
				ps.setString(i++, dto.getResearch().getContent());
				if(dto.getResearch().getStarttime() != null ){
					ps.setTimestamp(i++, new Timestamp(dto.getResearch().getStarttime().getTime()));
				}else{
					ps.setTimestamp(i++, null);
				}
				if(dto.getResearch().getEndtime() !=null){
					ps.setTimestamp(i++, new Timestamp(dto.getResearch().getEndtime().getTime()));
				}else{
					ps.setTimestamp(i++, null);
				}
				ps.setInt(i++, dto.getResearch().getTotallimit());
				ps.setInt(i++, dto.getResearch().getDaylimit());
				ps.setString(i++, dto.getResearch().getIslottery());
				ps.setInt(i++, dto.getResearch().getLotterychance());
				ps.setLong(i++, dto.getResearch().getLotteryid());
				ps.setInt(i++, dto.getResearch().getBalance());
				ps.setString(i++, dto.getResearch().getStartnote());
				ps.setString(i++, dto.getResearch().getEndnote());
				ps.setInt(i++, dto.getResearch().getMaxlotterychance());
				ps.setLong(i++, omid);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	private static final String FIND_RESEARCH_MODEL_BY_ID = "select * from esite.es_interact_research where id = ? and status !='DEL'";
	@Override
	public ResearchModel findResearchModelById(long researchid){
		Object[] params = { researchid };
		List<ResearchModel> list = getJdbcTemplate().query(FIND_RESEARCH_MODEL_BY_ID, params, new FeatureRowmapper());
		if(list != null && list.size()>0){
	 		return list.get(0);
	 	}
		return null;
	}
	
	class FeatureRowmapper implements RowMapper {
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			ResearchModel iv = new ResearchModel();
			iv.setId(rs.getLong("id"));
			iv.setTitle(rs.getString("title"));
			iv.setContent(rs.getString("content"));
			iv.setStarttimeDate(rs.getTimestamp("starttime"));
			iv.setEndtimeDate(rs.getTimestamp("endtime"));
			iv.setTotallimit(rs.getInt("totallimit"));
			iv.setDaylimit(rs.getInt("daylimit"));
			iv.setIslottery(rs.getString("islottery"));
			iv.setLotterychance(rs.getInt("lotterychance"));
			iv.setLotteryid(rs.getLong("lotteryid"));
			iv.setBalance(rs.getInt("balance"));
			iv.setMaxlotterychance(rs.getInt("maxlotterychance"));
			iv.setStartnote(rs.getString("startnote"));
			iv.setEndnote(rs.getString("endnote"));
			iv.setOwnerid(rs.getLong("ownerid"));
			iv.setOmid(rs.getLong("omid"));
			return iv;
		}
		
	}

	private static final String FIND_LTTERY_BY_ID = "select es.id,es.name,es.type from esite.es_interact_lottery es where id = ?";
	@Override
	public Lottery findLotteryById(long lotteryid){
		Object[] params = { lotteryid };
		List<Lottery> list = getJdbcTemplate().query(FIND_LTTERY_BY_ID, params, new LotteryMapper());
		if(list !=null && list.size()>0){
			return list.get(0);
		}
		return null; 
	}

	public List<ResearchRecord> findResearchRecordList(long searchid, int start, int size, long owner) {
		List<Object> list=new ArrayList<Object>();
		list.add(owner);
		list.add(searchid);
		String sql = "select r.*,u.username,wb.nickname nickname1 from es_feature_interact_research_record r left join es_sina_user wb on r.wbuid = wb.wbuid left join es_hy_user u on r.wbuid = u.wbuid and u.owner=? where r.searchid=? and r.type=0 ";
		sql+=" order by r.id desc limit ?,? ";
		list.add(start);
		list.add(size);
		return getJdbcTemplate().query(sql, list.toArray(),
				new ResearRecordMapper());
	}
		
	public int findResearchRecordTotal(long searchid,long owner) {
		List<Object> list=new ArrayList<Object>();
		list.add(owner);
		list.add(searchid);
		String sql = "select count(r.id) from es_feature_interact_research_record r left join es_sina_user wb on r.wbuid = wb.wbuid left join es_hy_user u on r.wbuid = u.wbuid and u.owner=? where r.searchid=? and r.type=0 ";
		return getJdbcTemplate().queryForInt(sql,list.toArray());
	}
	
    class ResearRecordMapper implements RowMapper{
	       @Override
	       public Object mapRow(ResultSet rs, int arg) throws SQLException {
	           ResearchRecord record=new ResearchRecord();
	           record.setId(rs.getLong("id"));
	           record.setCreatetime(rs.getTimestamp("createtime"));
	           record.setIsshare(rs.getString("isshare"));
	           record.setWbuid(rs.getLong("wbuid"));
	           record.setSearchid(rs.getLong("searchid"));
	           record.setIp(rs.getString("ip"));
	           record.setNickname(rs.getString("nickname1"));
	           record.setUsername(rs.getString("u.username"));
	           return record;
	       }
	   }
    
    private static final String UPDATE_RESEARCH_DESIGN = "update esite.es_interact_research set title=?,content=?,starttime=?,endtime=?,totallimit=?"
    		+ ",daylimit=?,islottery=?,lotterychance=?,lotteryid=?,balance=?,createtime = now(),maxlotterychance=?,startnote=?,endnote=? where id=?";
	@Override
	public long updateResearchDesign(long ownerid, ResearchDto dto, long researchid){
		ResearchModel r = dto.getResearch();
		Object[] params={r.getTitle(),r.getContent(),r.getStarttime(),r.getEndtime(),r.getTotallimit(),
				r.getDaylimit(),r.getIslottery(),r.getLotterychance(),r.getLotteryid(),r.getBalance(),r.getMaxlotterychance(),
				r.getStartnote(),r.getEndnote(),researchid};
		return getJdbcTemplate().update(UPDATE_RESEARCH_DESIGN,params);
	}

	private static final String FIND_QUESTION_BY_RESEARCHID="select * from esite.es_interact_research_question where searchid = ? and status != 'DEL'";
	@Override
	public List<ResearchQuestionModel> findQuestionsByResearchid(long researchid){
		return getJdbcTemplate().query(FIND_QUESTION_BY_RESEARCHID, new Object[]{researchid}, new QuestionRowmapper());
	}
	
	class QuestionRowmapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			ResearchQuestionModel q = new ResearchQuestionModel();
			q.setId(rs.getLong("id"));
			q.setSearchid(rs.getLong("searchid"));
			q.setType(rs.getString("type"));
			q.setTitle(rs.getString("title"));
			q.setPic(rs.getString("pic"));
			q.setIdx(rs.getInt("idx"));
			q.setCreatetime(rs.getTimestamp("createtime"));
			q.setStatus(rs.getString("status"));
			return q;
		}
		
	}

	private static final String FIND_OPTION_BY_QUESTIONID="select * from esite.es_interact_research_question_option where questionid = ? and status != 'DEL'";
	@Override
	public List<ResearchQuestionOption> findOptionsByQuestionid(long questionid){
		Object[] params={questionid};
		return getJdbcTemplate().query(FIND_OPTION_BY_QUESTIONID, params, new OptionsRowmapper());
	}
	
	class OptionsRowmapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			ResearchQuestionOption o = new ResearchQuestionOption();
			o.setId(rs.getLong("id"));
			o.setQuestionid(rs.getLong("questionid"));
			o.setContent(rs.getString("content"));
			o.setPic(rs.getString("pic"));
			o.setIdx(rs.getInt("idx"));
			o.setCreatetime(rs.getTimestamp("createtime"));
			o.setStatus(rs.getString("status"));
			return o;
		}
		
	}

	private static final String SAVE_RESEARCH_RECORD="insert into esite.es_feature_interact_research_record(pageid,wbuid,searchid,createtime,ip,terminal,source,type)values(?,?,?,now(),?,?,?,?)";
	@Override
	public long saveResearchReocrd(final long pageid, final long wbuid, final long researchid, final String ip, final String terminal, final String source,final int type){
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0)
					throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(SAVE_RESEARCH_RECORD,
						new String[] { "id" });
				int i = 1;
				ps.setLong(i++, pageid);
				ps.setLong(i++, wbuid);
				ps.setLong(i++, researchid);
				ps.setString(i++, ip);
				ps.setString(i++, terminal);
				ps.setString(i++, source);
				ps.setInt(i++, type);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().longValue();
		return id;
	}

	private static final String SAVE_ANSWER_XZ="insert into esite.es_feature_interact_research_record_answer(recordid,questionid,answer,createtime)values(?,?,?,now())";
	@Override
	public int saveResearchAnswerTK(long recordid, long questionid, String answer){
		Object[] params={recordid,questionid,answer};
		return getJdbcTemplate().update(SAVE_ANSWER_XZ, params);
	}

	private static final String SAVE_ANSWER_TK="insert into esite.es_feature_interact_research_record_answer(recordid,questionid,optionid,createtime)values(?,?,?,now())";
	@Override
	public int saveResearchAnswerXZ(long recordid, long questionid, long option){
		Object[] params={recordid,questionid,option};
		return getJdbcTemplate().update(SAVE_ANSWER_TK, params);
	}

	private static final String FIND_RESEARCH_COUNT="select count(id) from esite.es_feature_interact_research_record where wbuid = ? and type=0 and searchid = ?";
	@Override
	public int findResearchCount(long wbuid, long researchid) {
		Object[] params={wbuid,researchid};
		return getJdbcTemplate().queryForInt(FIND_RESEARCH_COUNT, params);
	}
	
	private static final String FIND_RESEARCH_COUNT_BY_WXUID = "select count(id) from esite.es_feature_interact_research_record where wbuid = ? and type=1 and searchid = ?";
	@Override
	public int findResearchCountByWxuid(long wxuid,long researchid){
		Object[] params={wxuid,researchid};
		return getJdbcTemplate().queryForInt(FIND_RESEARCH_COUNT_BY_WXUID, params); 
	}
	
	private static final String ADD_LOTTERY_CHANCE="insert into esite.es_interact_lottery_user(wbuid,lid,totalnum)values(?,?,?) on duplicate key update totalnum = totalnum + ? ";
	@Override
	public int addLotteryChance(long wbuid, long lid, int chance) {
		Object[] params={wbuid,lid,chance,chance};
		return getJdbcTemplate().update(ADD_LOTTERY_CHANCE,params);
	}

	@Override
	public int deleteResearch(long id)
	{
		String sql="update es_interact_research set status='DEL' where id=?";
		return getJdbcTemplate().update(sql,new Object[]{id});
	}
	
	



	private static final String  UPDATE_RESEARCH_QUESTION_OPTION = "update es_interact_research_question_option set count = count + 1 where id = ?";
	@Override
	public int updateResearchQuestionOption(long option) {
		return getJdbcTemplate().update(UPDATE_RESEARCH_QUESTION_OPTION,new Object[]{option});
	}

	@Override
	public List<ResearchRecord> findWxResearchRecordList(long searchid,int start, int size, String source,long owner)
	{
		List<Object> list=new ArrayList<Object>();
		String sql="select r.*,u.username,wx.nickname nickname1 from es_feature_interact_research_record r left join es_wx_user wx on r.wbuid = wx.id left join es_hy_user u on r.wbuid = u.wxuid and u.owner=? where r.searchid=? and r.type=1 ";
		list.add(owner);
		list.add(searchid);
		if(StringUtils.isNotEmpty(source)){
			sql+=" and r.source =? ";
			list.add(source);
		}
		sql+=" order by r.id desc limit ?,? ";
		list.add(start);
		list.add(size);
		return getJdbcTemplate().query(sql,list.toArray(),new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				ResearchRecord record = new ResearchRecord();
				record.setId(rs.getLong("id"));
		        record.setCreatetime(rs.getTimestamp("createtime"));
		        record.setIsshare(rs.getString("isshare"));
		        record.setWbuid(rs.getLong("wbuid"));
		        record.setSearchid(rs.getLong("searchid"));
		        record.setIp(rs.getString("ip"));
		        record.setSource(rs.getString("source"));
		        record.setUsername(rs.getString("u.username"));
		        record.setNickname(rs.getString("nickname1"));
		        return record;
			}
		});
	}
	
	@Override
	public List<ResearchRecord> findWxSourceByResearchRecord(long searchid){
		String sql="select distinct r.source from es_feature_interact_research_record r left join es_wx_user u on u.id=r.wbuid where r.searchid=? and r.type=1 and r.source is not null";
		return getJdbcTemplate().query(sql, new Object[]{searchid},new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				ResearchRecord record = new ResearchRecord();
		        record.setSource(rs.getString("source"));
		        return record;
			}
		});
	}
	
	class ResearchRecordRowmapper implements RowMapper{
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			ResearchRecord record = new ResearchRecord();
			record.setId(rs.getLong("id"));
	        record.setCreatetime(rs.getTimestamp("createtime"));
	        record.setIsshare(rs.getString("isshare"));
	        record.setWbuid(rs.getLong("wbuid"));
	        record.setSearchid(rs.getLong("searchid"));
	        record.setIp(rs.getString("ip"));
	        record.setSource(rs.getString("source"));
	        record.setNickname(rs.getString("nickname"));
	        record.setOpenid(rs.getString("openid"));
	        return record;
		}
	}

	@Override
	public int findWxResearchRecordListTotal(long searchid,String source,long owner)
	{
		List<Object> list=new ArrayList<Object>();
		String sql="select count(r.id) from es_feature_interact_research_record r left join es_wx_user wx on r.wbuid = wx.id left join es_hy_user u on r.wbuid = u.wxuid and u.owner=?  where r.searchid=? and r.type=1";
		list.add(owner);
		list.add(searchid);
		if(StringUtils.isNotEmpty(source)){
			sql+=" and r.source =? ";
			list.add(source);
		}
		return getJdbcTemplate().queryForInt(sql,list.toArray());
	}
	
	private static final String UPDATE_RULETYPE_BY_LOTTERYID = "update es_interact_lottery set ruletype = 'G' where id = ?";
	@Override
	public int updateRuletypeByLotteryid(long lotteryid) {
		Object[] params={lotteryid};
		return getJdbcTemplate().update(UPDATE_RULETYPE_BY_LOTTERYID,params);
	}
	@Override
	public int findNiResearchRecordTotal(long searchid,int type,long owner)
	{
		String sql = "";
		if(type == 2){
			sql = "select count(r.id) from es_feature_interact_research_record r left join es_hy_user u on r.wbuid = u.id and u.owner=? left join es_sina_user wb on u.wbuid = wb.wbuid left join es_wx_user wx on u.wxuid = wx.id where r.searchid=? and r.type=2 "; 
		}else if(type == -1){
		   sql = "select count(r.id) from es_feature_interact_research_record r left join es_hy_user u on r.wbuid = u.wxuid  and u.owner=? where r.searchid=? and r.type=-1";
		}
		return getJdbcTemplate().queryForInt(sql, new Object[]{owner,searchid});
	}
	
	@Override
	public List<ResearchRecord> findNiResearchRecordList(long searchid,int type, int start, int voteLimit, long owner)
	{
		String sql = "";
		if(type == 2){
			sql = "select r.*,u.username,wb.nickname nickname1,wx.nickname wxnickname from es_feature_interact_research_record r left join es_hy_user u on r.wbuid = u.id and u.owner=? left join es_sina_user wb on u.wbuid = wb.wbuid left join es_wx_user wx on u.wxuid = wx.id where r.searchid=? and r.type=2 order by r.id desc limit ?,? "; 
		}else if(type == -1){
		   sql = "select r.*,u.username,'' nickname1,'' wxnickname from es_feature_interact_research_record r left join es_hy_user u on r.wbuid = u.wxuid and u.owner=? where r.searchid=? and r.type=-1 order by r.id desc limit ?,? ";
		}
		return getJdbcTemplate().query(sql, new Object[]{owner,searchid,start,voteLimit},new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				ResearchRecord record = new ResearchRecord();
				record.setId(rs.getLong("id"));
		        record.setCreatetime(rs.getTimestamp("createtime"));
		        record.setIsshare(rs.getString("isshare"));
		        record.setWbuid(rs.getLong("wbuid"));
		        record.setSearchid(rs.getLong("searchid"));
		        record.setIp(rs.getString("ip"));
		        record.setSource(rs.getString("source"));
		        record.setNickname(rs.getString("nickname1"));
		        int type = rs.getInt("r.type");
		        if(type == 2){
		        	record.setWxnickname(rs.getString("wxnickname"));
		        }
		        record.setUsername(rs.getString("u.username"));
		        return record;
	        }
		});
	}

	@Override
	public long addResearch(final long ownerid, final String title)
	{
		final String sql="insert into es_interact_research(ownerid,title,createtime) values(?,?,now())";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0)
					throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(sql,
						new String[] { "id" });
				int i = 1;
				ps.setLong(i++, ownerid);
				ps.setString(i++, title);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	@Override
	public int saveResearchAnswerPx(long recordid, long questionid,long optionid, int index)
	{
		String sql="insert into es_feature_interact_research_record_answer(recordid,questionid,optionid,idx,createtime)values(?,?,?,?,now())";
		Object[] params={recordid,questionid,optionid,index};
		return getJdbcTemplate().update(sql,params);
	}

	@Override
	public String findResearchType(long questionid)
	{
		String sql="select type from esite.es_interact_research_question where id=?";
		List<String> list=getJdbcTemplate().query(sql, new Object[]{questionid}, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				return rs.getString("type");
			}
		});
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	
	@Override
	public int updateResearchClean(long searchid)
	{
		String[] sql={
				"update  es_interact_research_question_option  set count=0  where  questionid in (select id from es_interact_research_question  where searchid="+searchid+")",	
				"delete o from  es_feature_interact_research_record  r left outer join es_feature_interact_research_record_answer o on o.recordid=r.id where r.searchid="+searchid,
				"delete from es_feature_interact_research_record where searchid="+searchid,
				"delete from es_interact_user_data where featureid=124 and hdid="+searchid,
			};
		int[] rs=getJdbcTemplate().batchUpdate(sql);
		int sum=0;
		for (int i : rs)
		{
			sum=sum+i;
		}
		return sum;
	}
}
