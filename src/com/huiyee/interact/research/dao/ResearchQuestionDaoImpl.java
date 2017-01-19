package com.huiyee.interact.research.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.interact.research.dto.ResearchQuestionDataDto;
import com.huiyee.interact.research.model.OptionIdx;
import com.huiyee.interact.research.model.ResearchQuestionModel;
import com.huiyee.interact.research.model.ResearchQuestionOption;
import com.huiyee.interact.research.model.ResearchQuestionOptionVO;
import com.huiyee.interact.research.model.ResearchRecordAnswer;

public class ResearchQuestionDaoImpl implements IResearchQuestionDao
{
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<ResearchQuestionModel> findResearchQuestionList(long searchid,int start,int size)
	{
		String sql="select id,searchid,type,title,pic,idx from es_interact_research_question where searchid=? and status!='DEL' order by idx limit ?,?";
		return getJdbcTemplate().query(sql, new Object[]{searchid,start,size},new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				ResearchQuestionModel model = new ResearchQuestionModel();
				model.setId(rs.getLong("id"));
				model.setSearchid(rs.getLong("searchid"));
				model.setType(rs.getString("type"));
				model.setTitle(rs.getString("title"));
				model.setPic(rs.getString("pic"));
				model.setIdx(rs.getInt("idx"));
				return model;
			}
			
			
		});
	}
	
	class ResearchQuestionMapper implements RowMapper {
		@Override
		public Object mapRow(ResultSet rs, int arg) throws SQLException {
			ResearchQuestionModel model = new ResearchQuestionModel();
			model.setId(rs.getLong("id"));
			model.setSearchid(rs.getLong("searchid"));
			model.setType(rs.getString("type"));
			model.setTitle(rs.getString("title"));
			model.setPic(rs.getString("pic"));
			return model;
		}
	}
	class ResearchQuestionDataMapper implements RowMapper {
		@Override
		public Object mapRow(ResultSet rs, int arg) throws SQLException {
			ResearchQuestionDataDto model = new ResearchQuestionDataDto();
			model.setId(rs.getLong("id"));
			model.setSearchid(rs.getLong("searchid"));
			model.setType(rs.getString("type"));
			model.setTitle(rs.getString("title"));
			model.setPic(rs.getString("pic"));
			return model;
		}
	}


	@Override
	public long saveResearchQuestion(final long searchid, final String type, final String title, final String pic,final String isreq)
	{
		final String sql="insert into es_interact_research_question (searchid,type,title,idx,pic,isreq,createtime) values(?,?,?,?,?,?,now())";
		final int idx=findMaxIndx(searchid);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0)
					throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(sql,
						new String[] { "id" });
				ps.setLong(1, searchid);
				ps.setString(2, type);
				ps.setString(3, title);
				ps.setInt(4, idx+1);
				ps.setString(5, pic);
				ps.setString(6, isreq);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}
	
	@Override
	public List<ResearchQuestionDataDto> findResearchQuestionListOrderByIdex(long searchid)
	{
		String sql="select id,searchid,type,title,pic from es_interact_research_question where searchid=? and status ='EDT' order by idx,id ";
		return getJdbcTemplate().query(sql, new Object[]{searchid},new ResearchQuestionDataMapper());
	}
	private static final String FIND_OPTION_ORDER_BY_COUNT="select * from esite.es_interact_research_question_option where questionid = ? and status != 'DEL' order by count desc";
	@Override
	public List<ResearchQuestionOption> findQuestionOptionsByQuestionid(long questionid){
		Object[] params={questionid};
		return getJdbcTemplate().query(FIND_OPTION_ORDER_BY_COUNT, params, new QusetionOptionRowmapper());
	}
	class QusetionOptionRowmapper implements RowMapper{
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			ResearchQuestionOption o = new ResearchQuestionOption();
			o.setId(rs.getLong("id"));
			o.setQuestionid(rs.getLong("questionid"));
			o.setContent(rs.getString("content"));
			o.setPic(rs.getString("pic"));
			o.setIdx(rs.getInt("idx"));
			o.setCount(rs.getInt("count"));
			return o;
		}
	}

	@Override
	public int delResearchQuestion(long id)
	{
		String sql="update es_interact_research_question set status='DEL',idx=0 where id=?";
		Map map=findIndx(id);
		int result= getJdbcTemplate().update(sql,new Object[]{id});
		deleteIndx((Integer)map.get("idx"), (Long)map.get("searchid"));
		return result;
	}

	@Override
	public int updateResearchQuestion(long id,  String title, String pic,String isreq)
	{
		String sql="update es_interact_research_question set title=?,pic=?,isreq=? where id=?";
		return getJdbcTemplate().update(sql,new Object[]{title,pic,isreq,id});
	}

	@Override
	public List<ResearchQuestionModel> findOneResearchQuestion(long id)
	{
		String sql="select a.searchid searchid,a.type type,a.title title,a.pic pic,b.id id,b.content content,b.pic img from es_interact_research_question a join es_interact_research_question_option b on a.id=b.questionid  where b.questionid=? and a.status!='DEL' and b.status!='DEL'";
		List<ResearchQuestionModel> list=getJdbcTemplate().query(sql, new Object[]{id},new ResearchQuestionMapper1());
		return list;
	}
	class ResearchQuestionMapper1 implements RowMapper {
		@Override
		public Object mapRow(ResultSet rs, int arg) throws SQLException {
			ResearchQuestionModel model = new ResearchQuestionModel();
			model.setId(rs.getLong("id"));
			model.setSearchid(rs.getLong("searchid"));
			model.setType(rs.getString("type"));
			model.setTitle(rs.getString("title"));
			model.setPic(rs.getString("pic"));
			model.setContent(rs.getString("content"));
			model.setPhoto(rs.getString("img"));
			return model;
		}
	}

	@Override
	public int saveQuestionContent(String content,String pic, long questionid)
	{
		String sql="insert into es_interact_research_question_option (questionid,content,pic,createtime) values(?,?,?,now())";
		return getJdbcTemplate().update(sql,new Object[]{questionid,content,pic});
	}

	@Override
	public int delResearchQuestionOption(long questionid)
	{
		String sql="update es_interact_research_question_option set status='DEL' where questionid=?";
		
		return getJdbcTemplate().update(sql,new Object[]{questionid});
	}

	@Override
	public int updateResearchQuestionOption(long questionid, String content, String pic)
	{
		String sql="update es_interact_research_question_option set content=?,pic=? where id=? ";
		return getJdbcTemplate().update(sql,new Object[]{content,pic,questionid});
	}

	@Override
	public ResearchQuestionModel findTypeIds(long id)
	{
		String sql="select  id, searchid, type, title,pic,isreq from es_interact_research_question where id=? and status!='DEL'";
		List<ResearchQuestionModel> list= getJdbcTemplate().query(sql, new Object[]{id},new ResearchQuestionMapper2());
		if(list!=null&&list.size()>0){
			ResearchQuestionModel rqmodel=list.get(0);
			return rqmodel;
		}
		return null;
	}
	class ResearchQuestionMapper2 implements RowMapper {
		@Override
		public Object mapRow(ResultSet rs, int arg) throws SQLException {
			ResearchQuestionModel model = new ResearchQuestionModel();
			model.setId(rs.getLong("id"));
			model.setSearchid(rs.getLong("searchid"));
			model.setType(rs.getString("type"));
			model.setTitle(rs.getString("title"));
			model.setPic(rs.getString("pic"));
			model.setIsreq(rs.getString("isreq"));
			return model;
		}
	}

	
	@Override
	public int findResearchOptionTotalByQuestionid(long questionid)
	{
		String sql = "select sum(count) from es_interact_research_question_option where questionid=? and status!='DEL'";
		return getJdbcTemplate().queryForInt(sql, new Object[]
		{ questionid });
	}

	@Override
	public int findResearchQuestionTotal(long id)
	{
		String sql="select count(*) from es_interact_research_question where searchid=? and status!='DEL'";
		return getJdbcTemplate().queryForInt(sql,new Object[]{id});
	}

	@Override
	public int findMaxIndx(long id)
	{
		String sql="select Max(idx) from es_interact_research_question where searchid=?";
		return getJdbcTemplate().queryForInt(sql,new Object[]{id});
	}

	@Override
	public Map findIndx(long id)
	{
		String sql="select searchid,idx from es_interact_research_question where id=?";
		try{
			return getJdbcTemplate().queryForMap(sql,new Object[]{id});
		}catch (DataAccessException e) {
			return null;
		}
	}

	@Override
	public int deleteIndx(int idx,long searchid )
	{
		String sql="update es_interact_research_question set idx=idx-1 where idx>? and searchid=?";
		return getJdbcTemplate().update(sql,new Object[]{idx,searchid});
	}

	@Override
	public int updateDownResearchQuestion(long id)
	{
		String sql="update es_interact_research_question set idx = idx + 1 where id =?";
		String sql1="update es_interact_research_question set idx = idx -1 where searchid=? and idx= ? +1 ";
		Map map=findIndx(id);
		getJdbcTemplate().update(sql1,new Object[]{(Long)map.get("searchid"),(Integer)map.get("idx")});
		int result=getJdbcTemplate().update(sql,new Object[]{ id });
		return result;
	}

	@Override
	public int updateUpResearchQuestion(long id)
	{
		String sql="update es_interact_research_question set idx = idx + 1 where searchid=? and idx = ?-1";
		String sql1="update es_interact_research_question set idx = idx  - 1 where id=?";
		Map map=findIndx(id);
		getJdbcTemplate().update(sql,new Object[]{(Long)map.get("searchid"),(Integer)map.get("idx")});
		int result=getJdbcTemplate().update(sql1,new Object[]{id});
		return result;
	}

	@Override
	public int deleteResearchOptions(long id)
	{
		String sql="update es_interact_research_question_option set status='DEL' where id=?";
		return getJdbcTemplate().update(sql,new Object[]{id});
	}

	@Override
	public List<ResearchQuestionOption> findoptionbyqid(long questionid)
	{
		String sql="select * from es_interact_research_question_option  where questionid=?";
		List<ResearchQuestionOption> list=getJdbcTemplate().query(sql, new Object[]{questionid},new ResearchQuestionMapper3());
		return list;
	}
	class ResearchQuestionMapper3 implements RowMapper {
		@Override
		public Object mapRow(ResultSet rs, int arg) throws SQLException {
			ResearchQuestionOption model = new ResearchQuestionOption();
			model.setId(rs.getLong("id"));
			model.setQuestionid(rs.getLong("questionid"));
			model.setContent(rs.getString("content"));
			model.setTarget(rs.getLong("target"));
			return model;
		}
	}


	@Override
	public List<ResearchQuestionModel> findquestionbyqid(long questionid,long searchid,int idx)
	{
		String sql="select * from es_interact_research_question  where searchid=? and idx > ? and status = 'EDT'";
		List<ResearchQuestionModel> list=getJdbcTemplate().query(sql, new Object[]{searchid,idx},new ResearchQuestionMapper2());
		return list;
	}

	@Override
	public int updateinTarget(long id, long target)
	{
		String sql;
		if("-1".equals(target)){
			sql="update es_interact_research_question_option set target=null where id=?";
		}else{
			sql="update es_interact_research_question_option set target=? where id=?";
		}
		return getJdbcTemplate().update(sql,new Object[]{target,id});
	}

	@Override
	public ResearchQuestionOptionVO findselisTar(long id)
	{
		String sql="select a.title from es_interact_research_question a join es_interact_research_question_option b on a.id=b.target  where b.id=?";
		List<ResearchQuestionOptionVO> list=getJdbcTemplate().query(sql, new Object[]{id},new ResearchQuestionMapper4());
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	class ResearchQuestionMapper4 implements RowMapper {
		@Override
		public Object mapRow(ResultSet rs, int arg) throws SQLException {
			ResearchQuestionOptionVO model = new ResearchQuestionOptionVO();
			model.setTitle(rs.getString("title"));
			return model;
		}
	}


	@Override
	public List<ResearchRecordAnswer> findAnswerByQuestionid(long questionid)
	{
		String sql="select * from es_feature_interact_research_record_answer  where questionid=?";
		List<ResearchRecordAnswer> list=getJdbcTemplate().query(sql, new Object[]{questionid},new ResearchQuestionAnswerMapper());
		return list;
	}
	class ResearchQuestionAnswerMapper implements RowMapper {
		@Override
		public Object mapRow(ResultSet rs, int arg) throws SQLException {
			ResearchRecordAnswer model = new ResearchRecordAnswer();
			model.setId(rs.getLong("id"));
			model.setOptionid(rs.getLong("optionid"));
			model.setQuestionid(rs.getLong("questionid"));
			model.setRecordid(rs.getLong("recordid"));
			model.setCreatetime(rs.getDate("createtime"));
			model.setIdx(rs.getInt("idx"));
			return model;
		}
	}


	@Override
	public int countByQuestionid(long questionid)
	{
		String sql="select count(distinct recordid) from es_feature_interact_research_record_answer where questionid=?";
		return getJdbcTemplate().queryForInt(sql,new Object[]{questionid});
	}

	@Override
	public List<OptionIdx> countIdx(long questionid)
	{
		String sql="select idx,count(id) count from es_feature_interact_research_record_answer where optionid=? group by idx";
		return getJdbcTemplate().query(sql,new Object[]{questionid},new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				OptionIdx i = new OptionIdx();
				i.setIdx(rs.getInt("idx"));
				i.setCount(rs.getInt("count"));
				return i;
			}
			
		});
	}

	@Override
	public int findResearchOptionCountsByQuestionid(long questionid)
	{
		String sql = "select count(id) from es_interact_research_question_option where questionid=? and status!='DEL'";
		return getJdbcTemplate().queryForInt(sql, new Object[]
		{ questionid });
	}

}
