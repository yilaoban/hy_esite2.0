package com.huiyee.interact.exam.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.interact.exam.model.ExamQuestionModel;
import com.huiyee.interact.exam.model.ExamQuestionOption;
import com.huiyee.interact.exam.model.ExamVO;

public class ExamQuestionOptionDaoImpl implements IExamQuestionOptionDao
{
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<ExamVO> findAllSurveyresult(long recordid)
	{
		List<ExamVO> list = new ArrayList<ExamVO>();
		list = jdbcTemplate.query("select *,group_concat(qo.content)  content  FROM  es_interact_exam_question rq left join es_feature_interact_exam_record_answer rra   on rra.questionid=rq.id  left join es_interact_exam_question_option qo on rra.optionid=qo.id join es_feature_interact_exam_record rr on rra.recordid=rr.id where rr.id=? group by rq.id", new Object[]
		{ recordid }, new MyRowMapper());
		return list;
	}
	

	class MyRowMapper implements RowMapper
	{
		public Object mapRow(ResultSet rs, int arg1) throws SQLException
		{
			ExamVO revo =new ExamVO();
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
			ExamVO revo =new ExamVO();
			revo.setContent(rs.getString("qo.content"));
			return revo;
		}
	}

	@Override
	public String setContentbyord(long questionid, long record)
	{
		List<ExamVO> list = new ArrayList<ExamVO>();
		 StringBuffer s = new StringBuffer();
		System.out.println();
		String sql="select * FROM   es_feature_interact_exam_record_answer rra  left join es_interact_exam_question_option qo on rra.optionid=qo.id where rra.questionid =? and rra.recordid=? order by rra.idx,rra.id";
		list = jdbcTemplate.query(sql,new Object[]{questionid,record},new MyRowMapper2());
		for(int i=0;i<list.size();i++){
			s.append((i+1)+"."+list.get(i).getContent());
		}
		return s.toString();
	}
	
	@Override
	public ExamQuestionOption findOptionById(long optionid)
	{
		List<ExamQuestionOption> list=jdbcTemplate.query("select * from es_interact_exam_question_option where id=?", new Object[]{optionid}, new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				ExamQuestionOption o = new ExamQuestionOption();
				o.setId(rs.getLong("id"));
				o.setQuestionid(rs.getLong("questionid"));
				o.setContent(rs.getString("content"));
				o.setPic(rs.getString("pic"));
				o.setIdx(rs.getInt("idx"));
				o.setCreatetime(rs.getTimestamp("createtime"));
				o.setStatus(rs.getString("status"));
				o.setTarget(rs.getLong("target"));
				o.setScore(rs.getInt("score"));
				return o;
			}
		});
		return list.size()>0?list.get(0):new ExamQuestionOption();
	}

}
