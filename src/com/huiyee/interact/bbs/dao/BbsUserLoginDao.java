package com.huiyee.interact.bbs.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dto.BbsUserLoginTime;
import com.huiyee.interact.bbs.model.BBSUserOnline;

public class BbsUserLoginDao implements IBbsUserLoginDao
{
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void updateLoginTime(BbsUserLoginTime bbsUserLoginTime)
	{
		String sql = "";
		long userid = bbsUserLoginTime.getUid();
		long online_latest = bbsUserLoginTime.getTime();//最后登录时长
		BBSUserOnline on = findBBSUserOnlineByUserid(userid);
		if(on != null){
			sql = "update hybbs.bbs_user_online set online_latest = ?,online_total=? where user_id =?";
			long online_total = online_latest + on.getOnline_total();
			Object[] params = {online_latest,online_total,userid};
			jdbcTemplate.update(sql, params);
		}
		if(on == null){
			sql = "insert into hybbs.bbs_user_online(user_id,online_latest,online_total) values(?,?,?)";
			Object[] params = {userid,online_latest,online_latest};
			jdbcTemplate.update(sql, params);
		}
	}
	
	public BBSUserOnline findBBSUserOnlineByUserid(long userid){
		String sql = "select * from hybbs.bbs_user_online where user_id = ?";
		Object[] params = {userid};
		List<BBSUserOnline> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				BBSUserOnline on = new BBSUserOnline();
				on.setUser_id(rs.getLong("user_id"));
				on.setOnline_latest(rs.getLong("online_latest"));
				on.setOnline_total(rs.getLong("online_total"));
				return on;
			}
			
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public void updateBBSLoginLog(long logid)
	{
		String sql = "update hybbs.bbs_login_log set logout_time = now() where id = ?";
		Object[] params = {logid};
		jdbcTemplate.update(sql,params);
	}
}
