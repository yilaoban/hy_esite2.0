
package com.huiyee.interact.servicerpj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.util.EmojiFilter;
import com.huiyee.interact.servicerpj.model.ServicerPj;
import com.huiyee.interact.servicerpj.model.ServicerPjWd;

@SuppressWarnings("unchecked")
public class YuyueServicerPjDaoImpl extends AbstractDao implements IYuyueServicePjDao {

	private RowMapper rowMapper = new RowMapper() {

		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			ServicerPj pj = new ServicerPj();
			pj.setId(rs.getLong("id"));
			pj.setSerid(rs.getLong("srid"));
			pj.setWxuid(rs.getLong("wxuid"));
			pj.setContent(rs.getString("content"));
			pj.setDzcontent(rs.getString("dzcontent"));
			pj.setLevel(rs.getInt("level"));
			pj.setCreatetime(rs.getTimestamp("createtime"));
			pj.setType(rs.getInt("type"));
			pj.setEnid(rs.getLong("enid"));
			return pj;
		}

	};

	@Override
	public ServicerPj findServicerPj(int type, long enid) {
		String sql = "SELECT * FROM esite.es_yu_yue_servicer_pj WHERE type=? AND enid=?";
		Object[] param = { type, enid };
		List<ServicerPj> list = getJdbcTemplate().query(sql, param, rowMapper);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public int findServicerPjTotal(long owner, long srid) {
		String sql = "SELECT COUNT(p.id) FROM esite.es_yu_yue_servicer_pj p,esite.es_yu_yue_servicer s WHERE s.id=p.srid AND s.owner=?";
		List<Object> param = new ArrayList<Object>();
		param.add(owner);
		if (srid > 0) {
			sql += " AND p.srid=?";
			param.add(srid);
		}
		return getJdbcTemplate().queryForInt(sql, param.toArray());
	}

	@Override
	public List<ServicerPj> findServicerPjList(long owner, long srid, int start, int rows) {
		List<Object> list = new ArrayList<Object>();
		String sql = "SELECT p.* FROM esite.es_yu_yue_servicer_pj p,esite.es_yu_yue_servicer s WHERE s.id=p.srid AND s.owner=?";
		list.add(owner);
		if (srid > 0) {
			sql += " AND p.srid=?";
			list.add(srid);
		}
		sql += " ORDER BY p.id DESC LIMIT ?,?";
		list.add(start);
		list.add(rows);
		return getJdbcTemplate().query(sql, list.toArray(), rowMapper);
	}

	@Override
	public List<ServicerPjWd> findServicerPjWdList(long owner) {
		String sql = "SELECT * FROM esite.es_yu_yue_servicer_pj_wd WHERE owner=? AND del_tag='N'";
		Object[] param = { owner };
		return getJdbcTemplate().query(sql, param, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				ServicerPjWd wd = new ServicerPjWd();
				wd.setId(rs.getLong("id"));
				wd.setOwner(rs.getLong("owner"));
				wd.setName(rs.getString("name"));
				return wd;
			}

		});
	}

	@Override
	public List<ServicerPjWd> findServicerPjWdListByPjid(long pjid) {
		String sql = "SELECT * FROM esite.es_yu_yue_servicer_pj_wdl l LEFT JOIN esite.es_yu_yue_servicer_pj_wd w ON w.id=l.wdid WHERE l.pjid=?";
		Object[] param = { pjid };
		return getJdbcTemplate().query(sql, param, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				ServicerPjWd wd = new ServicerPjWd();
				wd.setId(rs.getLong("w.id"));
				wd.setOwner(rs.getLong("w.owner"));
				wd.setName(rs.getString("w.name"));
				wd.setLevel(rs.getInt("l.level"));
				return wd;
			}

		});
	}

	@Override
	public long addServicerPj(final ServicerPj pj) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
				String sql = "INSERT INTO esite.es_yu_yue_servicer_pj(srid,wxuid,content,createtime,type,enid) VALUES(?,?,?,now(),?,?)";
				PreparedStatement ps = arg0.prepareStatement(sql, new String[] { "id" });
				ps.setLong(1, pj.getSerid());
				ps.setLong(2, pj.getWxuid());
				ps.setString(3, EmojiFilter.getCutString(pj.getContent()));
				ps.setInt(4, pj.getType());
				ps.setLong(5, pj.getEnid());
				return ps;
			}

		}, keyHolder);
		return keyHolder.getKey().longValue();
	}

	@Override
	public int[] addServicerPjWdl(final long pjid, final List<ServicerPjWd> wdList) {
		String sql = "INSERT IGNORE INTO esite.es_yu_yue_servicer_pj_wdl(pjid,wdid,level) VALUES(?,?,?)";
		return getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int index) throws SQLException {
				ServicerPjWd wd = wdList.get(index);
				ps.setLong(1, pjid);
				ps.setLong(2, wd.getId());
				ps.setInt(3, wd.getLevel());
			}

			@Override
			public int getBatchSize() {
				return wdList.size();
			}
		});
	}

	@Override
	public int[] updateServicerPt(final long serid, final List<ServicerPjWd> wdList) {
		String sql = "INSERT INTO esite.es_yu_yue_servicer_pt(serid,wdid,pjtotal,leveltotal) VALUES(?,?,1,?) ON DUPLICATE KEY UPDATE pjtotal=pjtotal+1,leveltotal=leveltotal+?";
		return getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int index) throws SQLException {
				ServicerPjWd wd = wdList.get(index);
				ps.setLong(1, serid);
				ps.setLong(2, wd.getId());
				ps.setInt(3, wd.getLevel());
				ps.setInt(4, wd.getLevel());
			}

			@Override
			public int getBatchSize() {
				return wdList.size();
			}
		});
	}

	@Override
	public int updateDzcontent(long id, String dzcontent) {
		String sql = "UPDATE esite.es_yu_yue_servicer_pj SET dzcontent=? WHERE id=?";
		Object[] param = { EmojiFilter.getCutString(dzcontent), id };
		return getJdbcTemplate().update(sql, param);
	}
}
