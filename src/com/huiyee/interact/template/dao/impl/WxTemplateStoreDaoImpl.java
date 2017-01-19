package com.huiyee.interact.template.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.interact.template.dao.IWxTemplateStoreDao;
import com.huiyee.interact.template.model.WxTemplateStore;

@SuppressWarnings("unchecked")
public class WxTemplateStoreDaoImpl implements IWxTemplateStoreDao {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int getStoreCount() {
		String sql = "SELECT COUNT(*) FROM crm.crm_wx_template_store";
		return jdbcTemplate.queryForInt(sql);
	}

	@Override
	public List<WxTemplateStore> getStoreList(int start, int rows) {
		String sql = "SELECT * FROM crm.crm_wx_template_store LIMIT ?,?";
		Object[] param = { start, rows };
		return jdbcTemplate.query(sql, param, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int index) throws SQLException {
				WxTemplateStore wts = new WxTemplateStore();
				wts.setId(rs.getLong("id"));
				wts.setCode(rs.getString("code"));
				wts.setTitle(rs.getString("title"));
				wts.setFirst_class(rs.getString("first_class"));
				wts.setSecond_class(rs.getString("second_class"));
				wts.setContent(rs.getString("content"));
				wts.setExample(rs.getString("example"));
				return wts;
			}

		});
	}

	@Override
	public WxTemplateStore getStore(long id) {
		String sql = "SELECT * FROM crm.crm_wx_template_store WHERE id=?";
		Object[] param = { id };
		List<WxTemplateStore> list = jdbcTemplate.query(sql, param, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int index) throws SQLException {
				WxTemplateStore wts = new WxTemplateStore();
				wts.setId(rs.getLong("id"));
				wts.setCode(rs.getString("code"));
				wts.setTitle(rs.getString("title"));
				wts.setFirst_class(rs.getString("first_class"));
				wts.setSecond_class(rs.getString("second_class"));
				wts.setContent(rs.getString("content"));
				wts.setExample(rs.getString("example"));
				return wts;
			}

		});
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
