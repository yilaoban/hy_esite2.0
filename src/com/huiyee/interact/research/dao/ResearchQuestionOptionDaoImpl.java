package com.huiyee.interact.research.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.interact.research.model.ResearchQuestionModel;
import com.huiyee.interact.research.model.ResearchVO;

public class ResearchQuestionOptionDaoImpl implements IResearchQuestionOptionDao
{
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<ResearchVO> findAllSurveyresult(long recordid)
	{
		List<ResearchVO> list = new ArrayList<ResearchVO>();
		list = jdbcTemplate.query("select *,group_concat(qo.content)  content  FROM  es_interact_research_question rq left join es_feature_interact_research_record_answer rra   on rra.questionid=rq.id  left join es_interact_research_question_option qo on rra.optionid=qo.id join es_feature_interact_research_record rr on rra.recordid=rr.id where rr.id=? group by rq.id", new Object[]
		{ recordid }, new MyRowMapper());
		return list;
	}
	

	class MyRowMapper implements RowMapper
	{
		public Object mapRow(ResultSet rs, int arg1) throws SQLException
		{
			ResearchVO revo =new ResearchVO();
			revo.setTitle(rs.getString("rq.title"));
			revo.setId(rs.getLong("rra.questionid"));
			revo.setType(rs.getString("rq.type"));
			revo.setAnswer(rs.getString("rra.answer"));
			revo.setContent(rs.getString(".content"));
			return revo;
		}
	}
	

	
	class MyRowMapper2 implements RowMapper
	{
		public Object mapRow(ResultSet rs, int arg1) throws SQLException
		{
			ResearchVO revo =new ResearchVO();
			revo.setContent(rs.getString("qo.content"));
			return revo;
		}
	}

	@Override
	public String setContentbyord(long questionid, long record)
	{
		List<ResearchVO> list = new ArrayList<ResearchVO>();
		 StringBuffer s = new StringBuffer();
		System.out.println();
		String sql="select * FROM   es_feature_interact_research_record_answer rra  left join es_interact_research_question_option qo on rra.optionid=qo.id where rra.questionid =? and rra.recordid=? order by rra.idx,rra.id";
		list = jdbcTemplate.query(sql,new Object[]{questionid,record},new MyRowMapper2());
		for(int i=0;i<list.size();i++){
			s.append((i+1)+"."+list.get(i).getContent());
		}
		return s.toString();
	}

}
