
package com.huiyee.interact.servicerpj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.interact.servicerpj.model.ServicerPjWd;
import com.huiyee.interact.yuyue.model.YuYueServicer;

/**
 * 预约服务相关
 * 
 * @author ldw
 * 
 */
@SuppressWarnings("unchecked")
public class YuyueServicerDaoImpl extends AbstractDao implements IYuyueServicerDao {

	@Override
	public List<YuYueServicer> findServicers(long owner, int start, int size) {
		return getJdbcTemplate().query("select * from es_yu_yue_servicer where owner=? and type=2 order by otop desc, oidx desc limit ?,?", new Object[] { owner, start, size }, new RowMapper() {

			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				YuYueServicer servicer = new YuYueServicer();
				servicer.setId(rs.getLong("id"));
				servicer.setHydesc(rs.getString("hydesc"));
				servicer.setImg(rs.getString("img"));
				servicer.setSimg(rs.getString("simg"));
				servicer.setHyldesc(rs.getString("hyldesc"));
				servicer.setName(rs.getString("name"));
				servicer.setTop(rs.getInt("otop"));
				servicer.setLeveltotal(rs.getInt("leveltotal"));
				servicer.setPjtotal(rs.getInt("pjtotal"));
				return servicer;
			}
		});
	}

	@Override
	public List<YuYueServicer> findServicers(long owner) {
		return getJdbcTemplate().query("select * from es_yu_yue_servicer where owner=? and type=2 order by otop desc, oidx desc", new Object[] { owner }, new RowMapper() {

			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				YuYueServicer servicer = new YuYueServicer();
				servicer.setId(rs.getLong("id"));
				servicer.setHydesc(rs.getString("hydesc"));
				servicer.setImg(rs.getString("img"));
				servicer.setSimg(rs.getString("simg"));
				servicer.setHyldesc(rs.getString("hyldesc"));
				servicer.setName(rs.getString("name"));
				servicer.setTop(rs.getInt("otop"));
				return servicer;
			}
		});
	}

	@Override
	public YuYueServicer findServicerByid(long id) {
		List<YuYueServicer> list = getJdbcTemplate().query("select * from es_yu_yue_servicer where id=?", new Object[] { id }, new RowMapper() {

			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				YuYueServicer servicer = new YuYueServicer();
				servicer.setId(rs.getLong("id"));
				servicer.setName(rs.getString("name"));
				servicer.setHydesc(rs.getString("hydesc"));
				servicer.setHyldesc(rs.getString("hyldesc"));
				servicer.setYyid(rs.getLong("yyid"));
				servicer.setCaid(rs.getLong("caid"));
				servicer.setImg(rs.getString("img"));
				servicer.setSimg(rs.getString("simg"));
				servicer.setOtop(rs.getInt("otop"));
				servicer.setOidx(rs.getInt("oidx"));
				return servicer;
			}
		});
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public int findServicerMaxIdx(long owner) {
		try {
			return getJdbcTemplate().queryForInt("select max(oidx) from es_yu_yue_servicer where owner=? and type=2", new Object[] { owner });
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public int findServicerTotalByOwner(long owner) {
		return getJdbcTemplate().queryForInt("select count(id) from es_yu_yue_servicer where owner=?", new Object[] { owner });
	}

	@Override
	public long saveYuYueServicer(final YuYueServicer yuYueServicer) {
		final String sql = "insert into es_yu_yue_servicer(name,hydesc,hyldesc,img,simg,oidx,type,owner) values(?,?,?,?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(sql, new String[] { "id" });
				int i = 1;
				ps.setString(i++, yuYueServicer.getName());
				ps.setString(i++, yuYueServicer.getHydesc());
				ps.setString(i++, yuYueServicer.getHyldesc());
				ps.setString(i++, yuYueServicer.getImg());
				ps.setString(i++, yuYueServicer.getSimg());
				ps.setInt(i++, yuYueServicer.getOidx());
				ps.setInt(i++, yuYueServicer.getType());
				ps.setLong(i++, yuYueServicer.getOwner());
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	@Override
	public List<YuYueServicer> findServicerByCaid(long yyid, long caid, long type) {
		return getJdbcTemplate().query("select * from es_yu_yue_servicer where yyid=? and caid=? and type=?  order by top desc,idx desc", new Object[] { yyid, caid, type }, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				YuYueServicer servicer = new YuYueServicer();
				servicer.setId(rs.getLong("id"));
				return servicer;
			}
		});
	}

	@Override
	public int updateServicerToPj(final List<YuYueServicer> list) {
		int rs[] = getJdbcTemplate().batchUpdate("update es_yu_yue_servicer set oidx=?,owner=?,type=2 where id=?", new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int idx) throws SQLException {
				int i = 1;
				YuYueServicer y = list.get(idx);
				ps.setLong(i++, y.getOidx());
				ps.setLong(i++, y.getOwner());
				ps.setLong(i++, y.getId());
			}

			@Override
			public int getBatchSize() {
				return list.size();
			}
		});
		int result = 0;
		for (int i : rs) {
			if (i == 1)
				result++;
		}
		return result;
	}

	@Override
	public int delServicer(long owner, long id) {
		return getJdbcTemplate().update("delete from  es_yu_yue_servicer where owner=? and id=?", new Object[] { owner, id });
	}

	@Override
	public int updateServicerBack(long owner, long id) {
		return getJdbcTemplate().update("update es_yu_yue_servicer set type=0 where owner=? and id=?", new Object[] { owner, id });
	}

	@Override
	public YuYueServicer findFrontServicer(long owner, int oidx, int otop) {
		List<YuYueServicer> list = getJdbcTemplate().query("select * from es_yu_yue_servicer where owner=? and type=2 and otop=? and oidx>? order by oidx asc limit 1",
				new Object[] { owner, otop, oidx }, new RowMapper() {

					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
						YuYueServicer y = new YuYueServicer();
						y.setId(rs.getLong("id"));
						y.setOidx(rs.getInt("oidx"));
						y.setOtop(rs.getInt("otop"));
						return y;
					}
				});
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public YuYueServicer findNextServicer(long owner, int oidx, int otop) {
		List<YuYueServicer> list = getJdbcTemplate().query("select * from es_yu_yue_servicer where owner=? and otop=? and type=2 and oidx<? order by oidx desc limit 1",
				new Object[] { owner, otop, oidx }, new RowMapper() {

					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
						YuYueServicer y = new YuYueServicer();
						y.setId(rs.getLong("id"));
						y.setOidx(rs.getInt("oidx"));
						y.setOtop(rs.getInt("otop"));
						return y;
					}
				});
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public int updateServicerOidx(YuYueServicer servicer) {
		return getJdbcTemplate().update("update es_yu_yue_servicer set oidx=? where id=?", new Object[] { servicer.getOidx(), servicer.getId() });
	}

	@Override
	public int updateServicerTop(long owner, long serid, int top) {
		return getJdbcTemplate().update("update es_yu_yue_servicer set otop=? where id=? and owner=?", new Object[] { top, serid, owner });
	}

	@Override
	public int updateYuYueServicer(YuYueServicer yuYueServicer) {
		String sql = "update es_yu_yue_servicer set name=?,hydesc=?,hyldesc=?,img=?,simg=? where id = ?";
		Object[] params = { yuYueServicer.getName(), yuYueServicer.getHydesc(), yuYueServicer.getHyldesc(), yuYueServicer.getImg(), yuYueServicer.getSimg(), yuYueServicer.getId() };
		return getJdbcTemplate().update(sql, params);
	}

	@Override
	public int updateServicerPage(long owner, int source, Map<String, Long> map) {
		return getJdbcTemplate().update(
				"insert into esite.es_yu_yue_servicer_pj_page (owner,type,pjtid,pjxid,pjrid,pjsid,pjcid) values(?,?,?,?,?,?,?) on duplicate key update pjtid=?,pjxid=?,pjrid=?,pjsid=?,pjcid=?",
				new Object[] { owner, source, map.get("pjt"), map.get("pjx"), map.get("pjr"), map.get("pjs"), map.get("pjc"), map.get("pjt"), map.get("pjx"), map.get("pjr"), map.get("pjs"),
						map.get("pjc") });
	}

	@Override
	public List<ServicerPjWd> findServicerPjWdListBySerid(long serid) {
		String sql = "SELECT * FROM esite.es_yu_yue_servicer_pt p LEFT JOIN esite.es_yu_yue_servicer_pj_wd w ON w.id=p.wdid WHERE serid=?";
		Object[] params = { serid };
		return getJdbcTemplate().query(sql, params, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				ServicerPjWd wd = new ServicerPjWd();
				wd.setId(rs.getLong("w.id"));
				wd.setName(rs.getString("w.name"));
				int pjtotal = rs.getInt("p.pjtotal");
				int leveltotal = rs.getInt("p.leveltotal");
				if (pjtotal > 0) {
					wd.setLevel(leveltotal / pjtotal);
				}
				return wd;
			}

		});
	}
}
