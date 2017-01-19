package com.huiyee.esite.dao.imp;

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

import com.huiyee.esite.dao.IMoveToGroupDao;
import com.huiyee.esite.model.WxGroup;


public class MoveToGroupDaoImpl implements IMoveToGroupDao
{
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int saveWxGroupJob(long mpid, String name, String jobrule,String type)
	{
		String sql = "insert into crm.crm_wx_group_job(mpid,type,jobrule,name,createtime) values(?,?,?,?,now())";
		return jdbcTemplate.update(sql, new Object[] { mpid,type,jobrule, "按条件移动到" + name });
	}

	@Override
	public List<WxGroup> findWxGroupList(long mpid)
	{
		String sql = "select * from crm.crm_wx_group where mpid = ? and status != 'DEL' order by createtime desc";
		Object[] params = {mpid};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				WxGroup wg = new WxGroup();
				wg.setId(rs.getLong("id"));
				wg.setName(rs.getString("name"));
				return wg;
			}
			
		});
	}
	
	@Override
	public long saveCrmWxGroup(final long mpid, final String name) {
		final String sql = "insert into crm.crm_wx_group (mpid,name,createtime) values(?,?,now())";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(sql, new String[] { "id" });
				ps.setLong(1, mpid);
				ps.setString(2, name);
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().longValue();
	}
	
}
