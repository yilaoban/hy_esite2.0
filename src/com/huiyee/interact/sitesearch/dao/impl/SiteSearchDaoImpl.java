package com.huiyee.interact.sitesearch.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.interact.sitesearch.dao.ISiteSearchDao;
import com.huiyee.interact.sitesearch.model.SiteSearch;

@SuppressWarnings("unchecked")
public class SiteSearchDaoImpl implements ISiteSearchDao {

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public SiteSearch getSiteSearchByOwner(long ownerid) {
		String sql = "SELECT * FROM esite.es_interact_site_search s LEFT JOIN esite.es_page p ON p.id=s.pageid WHERE s.ownerid=?";
		Object[] param = { ownerid };
		List<SiteSearch> list = jdbcTemplate.query(sql, param, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				SiteSearch ss = new SiteSearch();
				ss.setId(rs.getLong("s.id"));
				ss.setOwnerid(rs.getLong("s.ownerid"));
				ss.setPageid(rs.getLong("s.pageid"));
				ss.setJspname(rs.getString("p.jspname"));
				return ss;
			}

		});
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public int addSiteSearch(SiteSearch ss) {
		String sql = "INSERT IGNORE INTO esite.es_interact_site_search(ownerid,pageid) VALUES(?,?)";
		Object[] params = { ss.getOwnerid(), ss.getPageid() };
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int updateSiteSearch(SiteSearch ss) {
		String sql = "UPDATE esite.es_interact_site_search SET pageid=? WHERE ownerid=?";
		Object[] params = { ss.getPageid(), ss.getOwnerid() };
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int deleteSiteSearch(long ownerid) {
		String sql = "DELETE FROM esite.es_interact_site_search WHERE ownerid=?";
		Object[] params = { ownerid };
		return jdbcTemplate.update(sql, params);
	}

}
