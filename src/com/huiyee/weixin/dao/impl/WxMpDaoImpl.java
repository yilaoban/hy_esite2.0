package com.huiyee.weixin.dao.impl;

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

import com.huiyee.weixin.dao.IWxMpDao;
import com.huiyee.weixin.model.WxMp;

@SuppressWarnings("unchecked")
public class WxMpDaoImpl implements IWxMpDao {

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public long saveWxMp(final String appid) {
		Object[] params = { appid };
		String sql = "SELECT id FROM esite.es_wx_mp WHERE appid=?";
		List<Long> list = jdbcTemplate.query(sql, params, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getLong("id");
			}

		});
		if (list.size() > 0) {
			return list.get(0);
		}

		KeyHolder keyholder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement ps = conn.prepareStatement("INSERT INTO esite.es_wx_mp(appid) VALUES(?)", new String[] { "id" });
				ps.setString(1, appid);
				return ps;
			}

		}, keyholder);
		return keyholder.getKey().longValue();
	}

	@Override
	public int saveWxMp(WxMp mp) {
		Object[] params = { mp.getNick_name(), mp.getHead_img(), mp.getService_type_info(), mp.getVerify_type_info(), mp.getUser_name(), mp.getAlias(), mp.getQrcode_url(), mp.getId() };
		String sql = "UPDATE esite.es_wx_mp SET nick_name=?,head_img=?,service_type_info=?,verify_type_info=?,user_name=?,alias=?,qrcode_url=? WHERE id=?";
		return jdbcTemplate.update(sql, params);
	}

}
