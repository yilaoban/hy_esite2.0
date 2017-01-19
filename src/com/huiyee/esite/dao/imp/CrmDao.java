package com.huiyee.esite.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.ICrmDao;
import com.huiyee.weixin.model.WxMediaNews;

public class CrmDao implements ICrmDao {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String findCrmKey(long id) {
		List<String> ls = jdbcTemplate.query("select url from crm.crm_wx_keyword_message where id=?", new Object[] { id }, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getString("url");
			}

		});
		if (ls != null && ls.size() > 0) {
			return ls.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public long findMpidByAccout(String accout) {
		Object[] param = { accout };
		List<Long> list = jdbcTemplate.query("SELECT id FROM esite.es_wx_mp WHERE user_name=?", param, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getLong("id");
			}

		});
		if (list.size() > 0) {
			return list.get(0);
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public WxMediaNews findNews(long id) {
		String sql = "select * from crm.crm_wx_media_news where id=?";
		Object[] param = { id };
		List<WxMediaNews> list = jdbcTemplate.query(sql, param, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				WxMediaNews wmn = new WxMediaNews();
				wmn.setId(rs.getLong("id"));
				wmn.setThumb_media_id(rs.getString("thumb_media_id"));
				wmn.setImg(rs.getString("img"));
				wmn.setTitle(rs.getString("title"));
				wmn.setAuthor(rs.getString("author"));
				wmn.setContent_source_url(rs.getString("content_source_url"));
				wmn.setDigest(rs.getString("digest"));
				wmn.setImg(rs.getString("img"));
				wmn.setContent(rs.getString("content").replaceAll("&quot;", "\""));
				return wmn;
			}

		});
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}
