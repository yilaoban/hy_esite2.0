package com.huiyee.weixin.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.weixin.dao.IWxAppDao;
import com.huiyee.weixin.model.WxApp;

public class WxAppDaoImpl implements IWxAppDao {

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@SuppressWarnings("unchecked")
	@Override
	public WxApp getWxapp(String appid) {
		Object[] param = { appid };
		List<WxApp> list = jdbcTemplate.query("SELECT * FROM esite.es_wx_app WHERE appid=?", param, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				WxApp wa = new WxApp();
				wa.setId(rs.getLong("id"));
				wa.setAppid(rs.getString("appid"));
				wa.setAppsecret(rs.getString("appsecret"));
				wa.setAccess_token(rs.getString("access_token"));
				wa.setExpires(rs.getLong("expires"));
				return wa;
			}

		});
		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxApp> getWxapps() {
		return jdbcTemplate.query("SELECT * FROM esite.es_wx_app", new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				WxApp wa = new WxApp();
				wa.setId(rs.getLong("id"));
				wa.setAppid(rs.getString("appid"));
				wa.setAppsecret(rs.getString("appsecret"));
				wa.setAccess_token(rs.getString("access_token"));
				wa.setExpires(rs.getLong("expires"));
				return wa;
			}

		});
	}

	@Override
	public WxApp getWxappById(long id)
	{
		Object[] param = { id };
		List<WxApp> list = jdbcTemplate.query("SELECT * FROM esite.es_wx_app WHERE id=?", param, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				WxApp wa = new WxApp();
				wa.setId(rs.getLong("id"));
				wa.setAppid(rs.getString("appid"));
				wa.setAppsecret(rs.getString("appsecret"));
				wa.setAccess_token(rs.getString("access_token"));
				wa.setExpires(rs.getLong("expires"));
				return wa;
			}

		});
		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

}
