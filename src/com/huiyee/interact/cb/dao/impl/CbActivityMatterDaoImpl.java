package com.huiyee.interact.cb.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.interact.cb.dao.ICbActivityMatterDao;
import com.huiyee.interact.cb.model.CbActivityMatter;

public class CbActivityMatterDaoImpl extends AbstractDao implements ICbActivityMatterDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<CbActivityMatter> findPageidByAid(long aid) {
		String sql = "SELECT * FROM	esite.es_interact_cb_activity_matter WHERE del_tag='N' AND aid=? LIMIT 0,1";
		Object[] params = { aid };
		return getJdbcTemplate().query(sql, params, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				CbActivityMatter cam = new CbActivityMatter();
				cam.setId(rs.getLong("id"));
				cam.setCbid(rs.getLong("cbid"));
				cam.setAid(rs.getLong("aid"));
				cam.setPageid(rs.getLong("pageid"));
				cam.setWxshareid(rs.getLong("wxshareid"));
				cam.setUpdatetime(rs.getTimestamp("updatetime"));
				cam.setCreatetime(rs.getTimestamp("createtime"));
				cam.setDel_tag(rs.getString("del_tag"));
				return cam;
			}

		});
	}
	
	
	@Override
	public List<CbActivityMatter> findPageByAid(long aid)
	{
		String sql = "SELECT * FROM	esite.es_interact_cb_activity_matter m join es_page p on m.pageid=p.id WHERE del_tag='N' AND aid=?";
		Object[] params = { aid };
		return getJdbcTemplate().query(sql, params, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				CbActivityMatter cam = new CbActivityMatter();
				cam.setId(rs.getLong("m.id"));
				cam.setCbid(rs.getLong("m.cbid"));
				cam.setAid(rs.getLong("m.aid"));
				cam.setPageid(rs.getLong("m.pageid"));
				cam.setWxshareid(rs.getLong("m.wxshareid"));
				cam.setUpdatetime(rs.getTimestamp("m.updatetime"));
				cam.setCreatetime(rs.getTimestamp("m.createtime"));
				cam.setDel_tag(rs.getString("m.del_tag"));
				cam.setPageName(rs.getString("name"));
				return cam;
			}

		});
	}
	
	@Override
	public List<CbActivityMatter> findWxPageShowByAid(long cbaid)
	{
		String sql = "SELECT * FROM	esite.es_interact_cb_activity_matter m join es_wx_page_show p on m.wxshareid=p.id WHERE del_tag='N' AND aid=?";
		Object[] params = { cbaid };
		return getJdbcTemplate().query(sql, params, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				CbActivityMatter cam = new CbActivityMatter();
				cam.setId(rs.getLong("m.id"));
				cam.setCbid(rs.getLong("m.cbid"));
				cam.setAid(rs.getLong("m.aid"));
				cam.setPageid(rs.getLong("m.pageid"));
				cam.setWxshareid(rs.getLong("m.wxshareid"));
				cam.setUpdatetime(rs.getTimestamp("m.updatetime"));
				cam.setCreatetime(rs.getTimestamp("m.createtime"));
				cam.setDel_tag(rs.getString("m.del_tag"));
				cam.setPageName(rs.getString("p.title"));
				return cam;
			}

		});
	}

}
