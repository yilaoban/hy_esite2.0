package com.huiyee.interact.setting.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.model.HyUser;
import com.huiyee.esite.util.ToolUtils;
import com.huiyee.interact.setting.dao.IHyUserDzDao;
import com.huiyee.interact.setting.model.HyUserDz;

@SuppressWarnings("unchecked")
public class HyUserDzDaoImpl implements IHyUserDzDao {

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private RowMapper rowMapper = new RowMapper() {

		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			HyUserDz dz = new HyUserDz();
			dz.setId(rs.getLong("id"));
			dz.setOwner(rs.getLong("owner"));
			dz.setHyuid(rs.getLong("hyuid"));
			dz.setWxuid(rs.getLong("wxuid"));
			dz.setOpenid(rs.getString("openid"));
			dz.setNickname(rs.getString("nickname"));
			dz.setSex(rs.getInt("sex"));
			dz.setHeadimgurl(rs.getString("headimgurl"));
			dz.setT1(rs.getInt("t1"));
			dz.setT2(rs.getInt("t2"));
			dz.setT3(rs.getInt("t3"));
			dz.setT4(rs.getInt("t4"));
			dz.setT5(rs.getInt("t5"));
			dz.setT6(rs.getInt("t6"));
			dz.setT7(rs.getInt("t7"));
			return dz;
		}
	};

	@Override
	public int findDzCount(long owner, String type) {
		String sql = "SELECT COUNT(id) FROM esite.es_hy_user_dz WHERE owner=?";
		if (StringUtils.isNotBlank(type)) {
			sql += " AND " + type + "=1";
		}
		Object[] param = { owner };
		return jdbcTemplate.queryForInt(sql, param);
	}

	@Override
	public List<HyUserDz> findDzList(long owner, String type, int start, int rows) {
		String sql = "SELECT * FROM esite.es_hy_user_dz WHERE owner=?";
		if (StringUtils.isNotBlank(type)) {
			sql += " AND " + type + "=1";
		}
		sql += " LIMIT ?,?";
		Object[] params = { owner, start, rows };
		return jdbcTemplate.query(sql, params, rowMapper);
	}

	@Override
	public int findDzByWxuid(long owner, String type, long wxuid) {
		String sql = "SELECT COUNT(id) FROM esite.es_hy_user_dz WHERE owner=? AND wxuid=?";
		if (StringUtils.isNotBlank(type)) {
			sql += " AND " + type + "=1";
		}
		Object[] params = { owner, wxuid };
		return jdbcTemplate.queryForInt(sql, params);
	}

	@Override
	public HyUserDz findDzByHyuid(long owner, long hyuid) {
		String sql = "SELECT * FROM esite.es_hy_user_dz WHERE owner=? AND hyuid=?";
		Object[] params = { owner, hyuid };
		List<HyUserDz> list = jdbcTemplate.query(sql, params, rowMapper);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override

	public int findDzByHyuid(long owner, String type, long hyuid) {
		String sql = "SELECT COUNT(id) FROM esite.es_hy_user_dz WHERE owner=? AND hyuid=?";
		if (StringUtils.isNotBlank(type)) {
			sql += " AND " + type + "=1";
		}
		Object[] params = { owner, hyuid };
		return jdbcTemplate.queryForInt(sql, params);
	}

	@Override
	public int addDz(HyUserDz dz) {
		String sql = "INSERT INTO esite.es_hy_user_dz(owner,hyuid,wxuid,openid,nickname,sex,headimgurl) VALUES(?,?,?,?,?,?,?)";
		Object[] params = { dz.getOwner(), dz.getHyuid(), dz.getWxuid(), dz.getOpenid(), dz.getNickname(), dz.getSex(), dz.getHeadimgurl() };
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int updateDz(long id, String type, int type_val) {
		String sql = "UPDATE esite.es_hy_user_dz SET " + type + "=? WHERE id=?";
		Object[] params = { type_val, id };
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int delDz(long id) {
		String sql = "DELETE FROM esite.es_hy_user_dz WHERE id=?";
		Object[] params = { id };
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public HyUser findUser(long id) {
		String sql = "SELECT * FROM esite.es_hy_user WHERE id=?";
		Object[] params = { id };
		List<HyUser> list = jdbcTemplate.query(sql, params, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				HyUser user = new HyUser();
				user.setId(rs.getLong("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				return user;
			}

		});
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public int updateUser(HyUser user) {
		String sql = "UPDATE esite.es_hy_user SET username=?,password=? WHERE id=?";
		Object[] params = { user.getUsername(), ToolUtils.getMD5Str(user.getPassword()), user.getId() };
		return jdbcTemplate.update(sql, params);
	}

}
