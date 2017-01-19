package com.huiyee.interact.template.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.interact.template.dao.IWxTemplateIndustryDao;
import com.huiyee.interact.template.model.WxTemplateIndustry;

@SuppressWarnings("unchecked")
public class WxTemplateIndustryDaoImpl implements IWxTemplateIndustryDao {

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<WxTemplateIndustry> getFirst_class() {
		String sql = "SELECT DISTINCT first_class FROM crm.crm_wx_template_industry";
		return jdbcTemplate.query(sql, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int index) throws SQLException {
				WxTemplateIndustry wi = new WxTemplateIndustry();
				wi.setFirst_class(rs.getString("first_class"));
				return wi;
			}

		});
	}

	@Override
	public List<WxTemplateIndustry> getSecond_class(String first_class) {
		String sql = "SELECT * FROM crm.crm_wx_template_industry WHERE first_class=?";
		Object[] param = { first_class };
		return jdbcTemplate.query(sql, param, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int index) throws SQLException {
				WxTemplateIndustry wi = new WxTemplateIndustry();
				wi.setId(rs.getLong("id"));
				wi.setFirst_class(rs.getString("first_class"));
				wi.setSecond_class(rs.getString("second_class"));
				return wi;
			}

		});
	}

}
