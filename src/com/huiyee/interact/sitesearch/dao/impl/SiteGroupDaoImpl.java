package com.huiyee.interact.sitesearch.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.huiyee.esite.model.Sitegroup;
import com.huiyee.interact.sitesearch.dao.ISiteGroupDao;

@SuppressWarnings("unchecked")
public class SiteGroupDaoImpl implements ISiteGroupDao {

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int getSiteGroupCount(long ownerid, List<String> types) {
		String sql = "SELECT COUNT(*) FROM esite.es_site_group WHERE ownerid=:ownerid AND type IN (:types) AND status!='DEL'";

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("ownerid", ownerid);
		param.put("types", types);

		return new NamedParameterJdbcTemplate(jdbcTemplate).queryForInt(sql, param);
	}

	@Override
	public List<Sitegroup> getSiteGroupList(long ownerid, List<String> types, int start, int rows) {
		String sql = "SELECT * FROM esite.es_site_group g LEFT JOIN esite.es_interact_site_search_index s ON s.sitegroupid=g.id WHERE g.ownerid=:ownerid AND g.type IN (:types) AND g.status!='DEL' ORDER BY s.starttime LIMIT :start,:rows";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ownerid", ownerid);
		params.put("types", types);
		params.put("start", start);
		params.put("rows", rows);

		return new NamedParameterJdbcTemplate(jdbcTemplate).query(sql, params, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				Sitegroup sg = new Sitegroup();
				sg.setId(rs.getLong("g.id"));
				sg.setGroupname(rs.getString("g.groupName"));
				sg.setOwnerId(rs.getLong("g.ownerid"));
				sg.setEntityid(rs.getLong("s.id"));
				return sg;
			}

		});
	}

}
