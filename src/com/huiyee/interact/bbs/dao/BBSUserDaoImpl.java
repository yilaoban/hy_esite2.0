package com.huiyee.interact.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.model.BBSForum;
import com.huiyee.esite.model.Page;
import com.huiyee.interact.bbs.model.BBSUser;
import com.huiyee.interact.bbs.model.BBSUserActiveLevel;
import com.huiyee.interact.bbs.model.BBSUserOnline;

public class BBSUserDaoImpl implements IBBSUserDao
{
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int updateBBSUserbyId(long id)
	{
		String sql = "update hybbs.bbs_user set loginnum = loginnum + 1 where id = ?";
		Object[] params =
		{ id };
		return jdbcTemplate.update(sql, params);
	}

	class MyRowMapper implements RowMapper
	{
		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException
		{
			BBSUser su = new BBSUser();
			su.setId(rs.getLong("su.id"));
			su.setOwner(rs.getLong("su.owner"));
			su.setHyuserid(rs.getLong("su.hyuserid"));
			su.setLevel_name(rs.getString("l.level_name"));
			return su;
		}
	}

	@Override
	public BBSUser findBBSUserByHyuid(long hyuid)
	{
		String sql = "select * from hybbs.bbs_user su  join hybbs.bbs_user_active_level l on su.level_id = l.id where su.hyuserid=? ";
		Object[] params =
		{ hyuid };
		List<BBSUser> list = jdbcTemplate.query(sql, params, new MyRowMapper());
		if (list != null && list.size() > 0)
		{
			BBSUser bbsUser = list.get(0);
			return bbsUser;
		}
		return null;
	}

	@Override
	public long saveBBSUser(final BBSUser bbsUser, final String ip)
	{
		final String sql = "insert into hybbs.bbs_user(createtime,createip,hyuserid,owner) values(now(),?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator()
		{
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(sql, new String[]
				{ "id" });
				ps.setString(1, ip);
				ps.setLong(2, bbsUser.getHyuserid());
				ps.setLong(3, bbsUser.getOwner());
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().longValue();
		return id;
	}

	@Override
	public int saveBBSUserOnline(long id)
	{
		String sql = "insert ignore into hybbs.bbs_user_online(user_id,online_latest,online_total) values(?,0,0)";
		Object[] params =
		{ id };
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public long saveBBSLoginLog(final long userid, final String ip)
	{
		final String sql = "insert into hybbs.bbs_login_log(user_id,login_time,ip) values(?,now(),?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator()
		{
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(sql, new String[]
				{ "id" });
				ps.setLong(1, userid);
				ps.setString(2, ip);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().longValue();
		return id;
	}

	@Override
	public BBSUserActiveLevel findBBSUseActiveLevel(long userid)
	{
		String sql = "select * from hybbs.bbs_user u join hybbs.bbs_user_active_level l on u.level_id = l.id where u.id = ?";
		Object[] params =
		{ userid };
		List<BBSUserActiveLevel> list = jdbcTemplate.query(sql, params, new RowMapper()
		{
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				BBSUserActiveLevel l = new BBSUserActiveLevel();
				l.setId(rs.getInt("l.id"));
				l.setLevel_name(rs.getString("l.level_name"));
				l.setRequired_hour(rs.getLong("l.required_hour"));
				return l;
			}

		});
		if (list != null && list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}

	@Override
	public BBSUserOnline findBBSUserOnlineById(long userid)
	{
		String sql = "select * from hybbs.bbs_user_online where user_id = ?";
		Object[] params =
		{ userid };
		List<BBSUserOnline> list = jdbcTemplate.query(sql, params, new RowMapper()
		{
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				BBSUserOnline on = new BBSUserOnline();
				on.setOnline_latest(rs.getLong("online_latest"));
				on.setOnline_total(rs.getLong("online_total"));
				return on;
			}
		});
		if (list != null && list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}

	@Override
	public int updateBBSUserLevlebyId(long id)
	{
		String sql = "update hybbs.bbs_user set level_id = level_id+1 where id = ?";
		Object[] params =
		{ id };
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public Page findJspnameByForumid(long forumid) {
		String sql = "select p.* from hybbs.bbs_forum f join esite.es_page p on p.id = f.loginpageid where f.id = ? ";
		Object[] params = { forumid };
		List<Page> list =  jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				Page f = new Page();
				f.setId(rs.getLong("p.id"));
				f.setName(rs.getString("p.name"));
				f.setJspname(rs.getString("p.jspname"));
				return f;
			}
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public Page findRegJspnameByForumid(long forumid) {
		String sql = "select p.* from hybbs.bbs_forum f join esite.es_page p on p.id = f.registerpageid where f.id = ? ";
		Object[] params = { forumid };
		List<Page> list =  jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				Page f = new Page();
				f.setId(rs.getLong("p.id"));
				f.setName(rs.getString("p.name"));
				f.setJspname(rs.getString("p.jspname"));
				return f;
			}
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int updateOperateNum(String column, long id) {
		if (StringUtils.isBlank(column)) {
			return 0;
		}
		String sql = "UPDATE hybbs.bbs_user SET " + column + "=" + column + "+1 WHERE id = ?";
		Object[] params = { id };
		return jdbcTemplate.update(sql, params);
	}


}
