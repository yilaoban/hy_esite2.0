package com.huiyee.weixin.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.model.Page;
import com.huiyee.weixin.dao.IWxPageShowDao;
import com.huiyee.weixin.model.WxPageShow;

@SuppressWarnings("unchecked")
public class WxPageShowDaoImpl implements IWxPageShowDao {

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public WxPageShow getWxPageShow(long pageid) {
		Object[] params = { pageid };
		List<WxPageShow> list = jdbcTemplate.query("SELECT s.* FROM esite.es_wx_page_show s WHERE s.pageid=? ORDER BY s.id DESC", params, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				WxPageShow wps = new WxPageShow();
				wps.setId(rs.getLong("id"));
				wps.setSitegroupid(rs.getLong("sitegroupid"));
				wps.setPageid(rs.getLong("pageid"));
				wps.setInfoed(rs.getString("infoed"));
				wps.setPic(rs.getString("pic"));
				wps.setTitle(rs.getString("title"));
				wps.setDescription(rs.getString("description"));
				wps.setUpdateseconds(rs.getInt("updateseconds") / 86400);
				wps.setSpageid(rs.getLong("spageid"));
				return wps;
			}
		});
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public WxPageShow getWxPageShowBySite(long pageid) {
		Object[] params = { pageid };
		String sql = "SELECT s.* FROM esite.es_wx_page_show s,esite.es_page p,esite.es_site t WHERE s.sitegroupid=t.sitegroupid and p.siteid=t.id and p.id=? ORDER BY s.id DESC";
		List<WxPageShow> list = jdbcTemplate.query(sql, params, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				WxPageShow wps = new WxPageShow();
				wps.setId(rs.getLong("id"));
				wps.setSitegroupid(rs.getLong("sitegroupid"));
				wps.setPageid(rs.getLong("pageid"));
				wps.setInfoed(rs.getString("infoed"));
				wps.setPic(rs.getString("pic"));
				wps.setTitle(rs.getString("title"));
				wps.setDescription(rs.getString("description"));
				wps.setUpdateseconds(rs.getInt("updateseconds") / 86400);
				return wps;
			}
		});
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public WxPageShow getWxPageShowByPP(long pageid) {
		Object[] params = { pageid };
		List<WxPageShow> list = jdbcTemplate.query("SELECT s.* FROM esite.es_wx_page_show s,esite.es_wx_pp p WHERE p.pageid=? and s.id=p.wxpid", params, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				WxPageShow wps = new WxPageShow();
				wps.setId(rs.getLong("id"));
				wps.setSitegroupid(rs.getLong("sitegroupid"));
				wps.setPageid(rs.getLong("pageid"));
				wps.setInfoed(rs.getString("infoed"));
				wps.setPic(rs.getString("pic"));
				wps.setTitle(rs.getString("title"));
				wps.setDescription(rs.getString("description"));
				wps.setUpdateseconds(rs.getInt("updateseconds") / 86400);
				wps.setSpageid(rs.getLong("spageid"));
				return wps;
			}
		});
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public WxPageShow getWxPageShowByPS(long pageid) {
		Object[] params = { pageid };
		List<WxPageShow> list = jdbcTemplate.query("SELECT s.* FROM esite.es_wx_page_show s,esite.es_wx_ps ps,esite.es_page p WHERE p.id=? and s.id=ps.wxpid and p.siteid=ps.siteid", params,
				new RowMapper() {
					@Override
					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
						WxPageShow wps = new WxPageShow();
						wps.setId(rs.getLong("id"));
						wps.setSitegroupid(rs.getLong("sitegroupid"));
						wps.setPageid(rs.getLong("pageid"));
						wps.setInfoed(rs.getString("infoed"));
						wps.setPic(rs.getString("pic"));
						wps.setTitle(rs.getString("title"));
						wps.setDescription(rs.getString("description"));
						wps.setUpdateseconds(rs.getInt("updateseconds") / 86400);
						wps.setSpageid(rs.getLong("spageid"));
						return wps;
					}
				});
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public WxPageShow saveWxPageShow(WxPageShow wps) {
		Object[] params = { wps.getPageid(), wps.getOwnerid(), wps.getSitegroupid(), wps.getInfoed(), wps.getPic(), wps.getTitle(), wps.getDescription(), wps.getUpdateseconds(), wps.getSpageid(),
				wps.getMpid(), wps.getOwnerid(), wps.getSitegroupid(), wps.getInfoed(), wps.getPic(), wps.getTitle(), wps.getDescription(), wps.getUpdateseconds(), wps.getSpageid(), wps.getMpid() };
		String sql = "INSERT INTO esite.es_wx_page_show(pageid,ownerid,sitegroupid,infoed,pic,title,description,createtime,updateseconds,spageid,mpid) VALUES(?,?,?,?,?,?,?,now(),?,?,?) ON DUPLICATE KEY UPDATE ownerid=?,sitegroupid=?,infoed=?,pic=?,title=?,description=?,updateseconds=?,spageid=?,mpid=?";
		jdbcTemplate.update(sql, params);

		Object[] param = { wps.getPageid() };
		List<WxPageShow> list = jdbcTemplate.query("SELECT * FROM esite.es_wx_page_show WHERE pageid=?", param, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				WxPageShow wps = new WxPageShow();
				wps.setId(rs.getLong("id"));
				wps.setPageid(rs.getLong("pageid"));
				wps.setSitegroupid(rs.getLong("sitegroupid"));
				wps.setInfoed(rs.getString("infoed"));
				wps.setPic(rs.getString("pic"));
				wps.setTitle(rs.getString("title"));
				wps.setDescription(rs.getString("description"));
				wps.setUpdateseconds(rs.getInt("updateseconds") / 86400);
				wps.setSpageid(rs.getLong("spageid"));
				wps.setMpid(rs.getLong("mpid"));
				return wps;
			}
		});
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<WxPageShow> findWxPageListByGroupid(long sitegroupid) {
		String sql = "select * from esite.es_wx_page_show where sitegroupid = ?";
		Object[] params = { sitegroupid };
		return jdbcTemplate.query(sql, params, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				WxPageShow wps = new WxPageShow();
				wps.setId(rs.getLong("id"));
				wps.setSitegroupid(rs.getLong("sitegroupid"));
				wps.setPageid(rs.getLong("pageid"));
				wps.setInfoed(rs.getString("infoed"));
				wps.setPic(rs.getString("pic"));
				wps.setTitle(rs.getString("title"));
				wps.setDescription(rs.getString("description"));
				wps.setUpdateseconds(rs.getInt("updateseconds") / (60 * 60 * 24));
				wps.setSpageid(rs.getLong("spageid"));
				wps.setMpid(rs.getLong("mpid"));
				return wps;
			}

		});
	}

	@Override
	public int saveWxpp(long wxpid, long pageid) {
		String sql = "insert into esite.es_wx_pp(wxpid,pageid,createtime) values(?,?,now()) ON DUPLICATE KEY update wxpid=?,createtime=now()";
		Object[] params = { wxpid, pageid, wxpid };
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public WxPageShow findWxPageShowById(long id) {
		String sql = "select * from esite.es_wx_page_show where id = ?";
		Object[] params = { id };
		List<WxPageShow> list = jdbcTemplate.query(sql, params, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				WxPageShow wps = new WxPageShow();
				wps.setId(rs.getLong("id"));
				wps.setSitegroupid(rs.getLong("sitegroupid"));
				wps.setPageid(rs.getLong("pageid"));
				wps.setInfoed(rs.getString("infoed"));
				wps.setPic(rs.getString("pic"));
				wps.setTitle(rs.getString("title"));
				wps.setDescription(rs.getString("description"));
				wps.setUpdateseconds(rs.getInt("updateseconds") / 86400);
				wps.setSpageid(rs.getLong("spageid"));
				wps.setMpid(rs.getLong("mpid"));
				return wps;
			}

		});
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Long> findWxPPById(long id) {
		String sql = "select pageid from esite.es_wx_pp where wxpid = ?";
		Object[] params = { id };
		return jdbcTemplate.query(sql, params, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getLong("pageid");
			}

		});
	}

	@Override
	public int updateWxPageShow(WxPageShow wps) {
		String sql = "update esite.es_wx_page_show set pageid=?,ownerid=?,sitegroupid=?,infoed=?,pic=?,title=?,description=?,updateseconds=?,spageid=?,mpid=? where id = ?";
		Object[] params = { wps.getPageid(), wps.getOwnerid(), wps.getSitegroupid(), wps.getInfoed(), wps.getPic(), wps.getTitle(), wps.getDescription(), wps.getUpdateseconds(), wps.getSpageid(),
				wps.getMpid(), wps.getId() };
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int deleteWxppByWxpid(long wxpid) {
		String sql = "delete from es_wx_pp where wxpid = ?";
		Object[] params = { wxpid };
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public List<Page> findPublishPageBySiteid(long siteid) {
		String sql = "select p.* from esite.es_page p left join esite.es_wx_page_show s on p.id = s.pageid where p.siteid = ? and p.status != 'DEL' and  p.isonline='Y'  and  p.relationid is null  and  p.contextid is null and s.id is null order by p.id asc";
		Object[] params = { siteid };
		return jdbcTemplate.query(sql, params, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				Page p = new Page();
				p.setId(rs.getLong("id"));
				p.setName(rs.getString("name"));
				p.setJspname(rs.getString("jspname"));
				p.setSiteid(rs.getLong("siteid"));
				p.setCreatetime(rs.getTimestamp("createtime"));
				p.setUpdatetime(rs.getTimestamp("updatetime"));
				p.setStatus(rs.getString("status"));
				p.setType(rs.getString("type"));
				p.setStype(rs.getString("stype"));
				p.setIsonline(rs.getString("isonline"));
				p.setWol(rs.getString("wol"));
				p.setRelationid((Long) rs.getObject("relationid"));
				p.setContextid((Long) rs.getObject("contextid"));
				p.setSubsina(rs.getString("subsina"));
				p.setSubweixin(rs.getString("subweixin"));
				p.setBg(rs.getString("bg"));
				p.setJspstyle(rs.getString("jspstyle"));
				p.setApptype(rs.getString("apptype"));
				p.setParamValue(rs.getString("paramValue"));
				return p;
			}

		});
	}

	private static final String SAVE_WX_PS = "insert ignore into es_wx_ps(wxpid,siteid,createtime)values(?,?,now())";

	@Override
	public int saveWxPs(long wxpid, long siteid) {
		Object[] params = { wxpid, siteid };
		return jdbcTemplate.update(SAVE_WX_PS, params);
	}

	private static final String DELETE_WX_PP = "delete from es_wx_pp where wxpid = ?";

	@Override
	public int deleteWxpp(long wxpid) {
		Object[] param = { wxpid };
		return jdbcTemplate.update(DELETE_WX_PP, param);
	}

	@Override
	public List<WxPageShow> findWxPageListBySiteid(long siteid) {
		String sql = "select p.* from es_wx_page_show p join es_wx_ps ps on p.id = ps.wxpid where ps.siteid = ?";
		Object[] params = { siteid };
		return jdbcTemplate.query(sql, params, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				WxPageShow wps = new WxPageShow();
				wps.setId(rs.getLong("id"));
				wps.setSitegroupid(rs.getLong("sitegroupid"));
				wps.setPageid(rs.getLong("pageid"));
				wps.setInfoed(rs.getString("infoed"));
				wps.setPic(rs.getString("pic"));
				wps.setTitle(rs.getString("title"));
				wps.setDescription(rs.getString("description"));
				wps.setUpdateseconds(rs.getInt("updateseconds") / (60 * 60 * 24));
				wps.setSpageid(rs.getLong("spageid"));
				wps.setMpid(rs.getLong("mpid"));
				return wps;
			}

		});
	}

	private static final String UPDATE_SITEGROUP_CMP = "update es_site_group set status = 'CMP' where id = ?";

	@Override
	public int updateSitegroupCMP(long sitegroupid) {
		Object[] param = { sitegroupid };
		return jdbcTemplate.update(UPDATE_SITEGROUP_CMP, param);
	}

	@Override
	public int deleteWps(long shareid) {
		return jdbcTemplate.update("delete from es_wx_page_show where id=? ", new Object[] { shareid });
	}

	@Override
	public int deleteWxps(long shareid) {
		return jdbcTemplate.update("delete from es_wx_ps where wxpid=? ", new Object[] { shareid });
	}
}
