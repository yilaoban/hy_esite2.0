package com.huiyee.interact.leader.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.interact.leader.dto.LeaderDto;
import com.huiyee.interact.leader.model.Leader;
import com.huiyee.interact.leader.model.LeaderLog;

@SuppressWarnings("unchecked")
public class LeaderDaoImpl implements ILeaderDao {
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Leader> findLeaderListByOwnerid(long ownerid, String status, Date startTime, Date endTime, int start, int size) {
		String sql = "select * from crm.crm_leader l join esite.es_hy_user u on l.hyuid = u.id left join esite.es_wx_user wx on u.wxuid = wx.id where u.owner = ? ";
		List<Object> list = new ArrayList<Object>();
		list.add(ownerid);
		if (!"ALL".equals(status)) {
			sql += " and l.status = ? ";
			list.add(status);
		}
		if (startTime != null) {
			sql += " and lasttime > ? ";
			list.add(startTime);
		}
		if (endTime != null) {
			sql += " and lasttime < ? ";
			list.add(endTime);
		}
		sql += "order by l.lasttime desc limit ?,?";
		list.add(start);
		list.add(size);
		return jdbcTemplate.query(sql, list.toArray(), new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				Leader l = new Leader();
				l.setId(rs.getLong("l.id"));
				l.setHyuid(rs.getLong("l.hyuid"));
				if(StringUtils.isNotBlank(rs.getString("l.nickname"))){
					l.setNickname(rs.getString("l.nickname"));
				}else{
					l.setNickname(rs.getString("wx.nickname"));
				}
				if(StringUtils.isNotBlank(rs.getString("l.email"))){
					l.setEmail(rs.getString("l.email"));
				}else{
					l.setEmail(rs.getString("u.email"));
				}
				if(StringUtils.isNotBlank(rs.getString("l.telphone"))){
					l.setTelphone(rs.getString("l.telphone"));
				}else{
					l.setTelphone(rs.getString("u.telphone"));
				}
				l.setTel(rs.getString("l.tel"));
				l.setCompany(rs.getString("l.company"));
				l.setJoinnum(rs.getInt("l.joinnum"));
				l.setFirsttime(rs.getTimestamp("l.firsttime"));
				l.setLasttime(rs.getTimestamp("l.lasttime"));
				l.setStatus(rs.getString("l.status"));
				l.setKehued(rs.getString("l.kehued"));
				l.setUsername(rs.getString("l.username"));
				if(rs.getInt("l.gender") != 0){
					l.setGender(rs.getInt("l.gender"));
				}else{
					l.setGender(rs.getInt("wx.sex"));
				}
				l.setArea(rs.getString("l.area"));
				return l;
			}
		});
	}

	@Override
	public int findTotalLeaderByOwnerid(long ownerid, String status, Date startTime, Date endTime) {
		String sql = "select count(l.id) from crm.crm_leader l join esite.es_hy_user u on l.hyuid = u.id where u.owner = ?";
		List<Object> list = new ArrayList<Object>();
		list.add(ownerid);
		if (!"ALL".equals(status)) {
			sql += " and l.status = ? ";
			list.add(status);
		}
		if (startTime != null) {
			sql += " and lasttime > ? ";
			list.add(startTime);
		}
		if (endTime != null) {
			sql += " and lasttime < ? ";
			list.add(endTime);
		}
		return jdbcTemplate.queryForInt(sql, list.toArray());
	}

	@Override
	public Leader findLeaderById(long id) {
		String sql = "select * from crm.crm_leader l join esite.es_hy_user u on l.hyuid = u.id left join esite.es_wx_user wx on u.wxuid = wx.id  where l.id = ?";
		Object[] parms = { id };
		List<Leader> list = jdbcTemplate.query(sql, parms, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				Leader l = new Leader();
				l.setId(rs.getLong("l.id"));
				l.setHyuid(rs.getLong("l.hyuid"));
				if(StringUtils.isNotBlank(rs.getString("l.nickname"))){
					l.setNickname(rs.getString("l.nickname"));
				}else{
					l.setNickname(rs.getString("wx.nickname"));
				}
				if(StringUtils.isNotBlank(rs.getString("l.email"))){
					l.setEmail(rs.getString("l.email"));
				}else{
					l.setEmail(rs.getString("u.email"));
				}
				if(StringUtils.isNotBlank(rs.getString("l.telphone"))){
					l.setTelphone(rs.getString("l.telphone"));
				}else{
					l.setTelphone(rs.getString("u.telphone"));
				}
				l.setTel(rs.getString("l.tel"));
				l.setCompany(rs.getString("l.company"));
				l.setJoinnum(rs.getInt("l.joinnum"));
				l.setFirsttime(rs.getTimestamp("l.firsttime"));
				l.setLasttime(rs.getTimestamp("l.lasttime"));
				l.setStatus(rs.getString("l.status"));
				l.setKehued(rs.getString("l.kehued"));
				l.setUsername(rs.getString("l.username"));
				if(rs.getInt("l.gender") != 0){
					l.setGender(rs.getInt("l.gender"));
				}else{
					l.setGender(rs.getInt("wx.sex"));
				}
				l.setArea(rs.getString("l.area"));
				return l;
			}
		});
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public int updateLeader(LeaderDto dto) {
		String sql = "update crm.crm_leader set nickname=?,email=?,telphone=?,tel=?,company=?,status=?,kehued=?,username=?,gender=?,area=? where id = ?";
		Object[] params = { dto.getLeader().getNickname(), dto.getLeader().getEmail(), dto.getLeader().getTelphone(), dto.getLeader().getTel(), dto.getLeader().getCompany(),
				dto.getLeader().getStatus(), dto.getLeader().getKehued(), dto.getLeader().getUsername(), dto.getLeader().getGender(), dto.getLeader().getArea(), dto.getLeader().getId() };
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int findTotalLeaderLogByHyuid(long hyuid) {
		String sql = "select count(id) from crm.crm_leader_join_log where hyuid = ?";
		Object[] params = { hyuid };
		return jdbcTemplate.queryForInt(sql, params);
	}

	@Override
	public List<LeaderLog> findLeaderLogListByHyuid(long hyuid, int start, int size) {
		String sql = "select * from crm.crm_leader_join_log where hyuid = ? limit ?,?";
		Object[] params = { hyuid, start, size };
		return jdbcTemplate.query(sql, params, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				LeaderLog ll = new LeaderLog();
				ll.setId(rs.getLong("id"));
				ll.setHyuid(rs.getLong("hyuid"));
				ll.setCreatetime(rs.getTimestamp("createtime"));
				ll.setHydesc(rs.getString("hydesc"));
				ll.setSubtype(rs.getString("subtype"));
				ll.setType(rs.getString("type"));
				ll.setEnid(rs.getLong("enid"));
				return ll;
			}
		});
	}

	@Override
	public int delLeader(long id) {
		String sql = "delete from crm.crm_leader where id = ?";
		Object[] params = { id };
		return jdbcTemplate.update(sql, params);
	}

}
