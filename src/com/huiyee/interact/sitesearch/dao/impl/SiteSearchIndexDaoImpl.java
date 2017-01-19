package com.huiyee.interact.sitesearch.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.interact.sitesearch.dao.ISiteSearchIndexDao;
import com.huiyee.interact.sitesearch.model.SiteSearchIndex;

@SuppressWarnings("unchecked")
public class SiteSearchIndexDaoImpl implements ISiteSearchIndexDao {

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int getSiteSearchIndexCount(long ownerid) {
		String sql = "SELECT COUNT(*) FROM esite.es_interact_site_search_index WHERE ownerid=?";
		Object[] param = { ownerid };
		return jdbcTemplate.queryForInt(sql, param);
	}

	@Override
	public List<SiteSearchIndex> getSiteSearchIndexList(long ownerid, int start, int rows) {
		String sql = "SELECT * FROM esite.es_interact_site_search_index s LEFT JOIN esite.es_site_group g ON g.id=s.sitegroupid WHERE s.ownerid=? ORDER BY s.starttime ASC LIMIT ?,?";
		Object[] params = { ownerid, start, rows };
		return jdbcTemplate.query(sql, params, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				SiteSearchIndex csj = new SiteSearchIndex();
				csj.setId(rs.getLong("s.id"));
				csj.setOwnerid(rs.getLong("s.ownerid"));
				csj.setSitegroupid(rs.getLong("s.sitegroupid"));
				csj.setOname(rs.getString("s.oname"));
				csj.setStatus(rs.getInt("s.status"));
				csj.setUpdatetime(rs.getTimestamp("s.updatetime"));
				csj.setStarttime(rs.getTimestamp("s.starttime"));
				csj.setInterval(rs.getInt("s.interval"));
				csj.setGroupName(rs.getString("g.groupName"));
				return csj;
			}

		});
	}

	@Override
	public List<Long> getSitegroupidList(long ownerid, int status) {
		String sql = "SELECT * FROM esite.es_interact_site_search_index  WHERE ownerid=? AND status=?";
		Object[] params = { ownerid, status };
		return jdbcTemplate.query(sql, params, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getLong("sitegroupid");
			}

		});
	}

	@Override
	public int addSiteSearchIndex(SiteSearchIndex ssi) {
		String sql = "INSERT INTO esite.es_interact_site_search_index(ownerid,sitegroupid,oname,status,starttime) VALUES(?,?,?,?,now())";
		Object[] params = { ssi.getOwnerid(), ssi.getSitegroupid(), ssi.getOname(), ssi.getStatus() };
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int updateStarttime(long id) {
		String sql = "UPDATE esite.es_interact_site_search_index SET starttime=now() WHERE id=?";
		Object[] params = { id };
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int updateStatus(long id, int status) {
		String sql = "UPDATE esite.es_interact_site_search_index SET status=? WHERE id=?";
		Object[] params = { status, id };
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int deleteSiteSearchIndex(long id) {
		String sql = "DELETE FROM esite.es_interact_site_search_index WHERE id=?";
		Object[] params = { id };
		return jdbcTemplate.update(sql, params);
	}

}
