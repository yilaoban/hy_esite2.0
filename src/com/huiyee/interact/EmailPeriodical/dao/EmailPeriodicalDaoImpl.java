package com.huiyee.interact.EmailPeriodical.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.interact.EmailPeriodical.model.EmailPeriodicalModel;

public class EmailPeriodicalDaoImpl implements IEmailPeriodicalDao{
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public long saveEmailPeriodical(String title,String url,String status,String publish,long ownerid) {
		String sql="insert into es_interact_email_publish (ownerid,title,url,createtime,status,publish) values(?,?,?,now(),?,?) ON DUPLICATE KEY UPDATE title=?";
		return getJdbcTemplate().update(sql,new Object[]{ownerid,title,url,status,publish,title});
	}

	@Override
	public List<EmailPeriodicalModel> searchEmailPeriodical(long ownerid,
			int start, int size) {
		String sql="select id,ownerid,title,url,createtime,status,publish from es_interact_email_publish where ownerid=? and status !='DEL' order by id desc limit ?,?";
		return getJdbcTemplate().query(sql, new Object[]{ownerid,start,size},new EmailPeriodicalMapper());
	}
class EmailPeriodicalMapper implements RowMapper{
		
		@Override
		public Object mapRow(ResultSet rs, int arg) throws SQLException {
			EmailPeriodicalModel model=new EmailPeriodicalModel();
			model.setId(rs.getLong("id"));
			model.setOwnerid(rs.getLong("ownerid"));
			model.setTitle(rs.getString("title"));
			model.setUrl(rs.getString("url"));
			model.setCreatetime(rs.getTimestamp("createtime"));
			model.setStatus(rs.getString("status"));
			model.setPublish(rs.getString("publish"));
			return model;
		}
	}

	@Override
	public int searchEmailPeriodicalTotal(long ownerid) {
		String sql="select count(*) from es_interact_email_publish where ownerid=? and status !='DEL' ";
		return getJdbcTemplate().queryForInt(sql,new Object[]{ownerid});
	}

	@Override
	public int deleteEmailPeriodical(long id) {
		String sql="update es_interact_email_publish set status='DEL' where id=? ";
		return getJdbcTemplate().update(sql,new Object[]{id});
	}

	@Override
	public int updatePublishById(long id) {
		String sql="update es_interact_email_publish set publish='Y' where id=? ";
		return getJdbcTemplate().update(sql,new Object[]{id});
	}

	@Override
	public int updatePublishByOwnerid(long ownerid) {
		String sql="update es_interact_email_publish set publish='N' where ownerid=?  ";
		return getJdbcTemplate().update(sql,new Object[]{ownerid});
	}

	@Override
	public EmailPeriodicalModel findemailpublishByLpid(long id)
	{
		String sql="select * from es_interact_email_publish where id=?";
		List<EmailPeriodicalModel> list = jdbcTemplate.query(sql, new Object[]{ id }, new EmailPeriodicalMapper());
  		if (list.size() > 0)
  		{
  			return list.get(0);
  		}
  		return null;
		
	}

	@Override
	public long updateemailpublish(String title, String url,long id)
	{
		String sql = "update es_interact_email_publish set title=?,url=? where id =? ";
		return jdbcTemplate.update(sql, new Object[]{ title, url,id});
	}

}
