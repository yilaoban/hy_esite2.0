package com.huiyee.interact.setting.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.interact.setting.dao.IHyUserXfDescDao;
import com.huiyee.interact.setting.model.HyUserLevel;
import com.huiyee.interact.setting.model.HyUserXfDesc;
import com.huiyee.interact.setting.model.HyUserXfZk;

@SuppressWarnings("unchecked")
public class HyUserXfDescDaoImpl implements IHyUserXfDescDao {

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int findXfDescCount(long owner) {
		String sql = "SELECT COUNT(*) FROM esite.es_hy_user_xf_desc WHERE owner=?";
		Object[] args = { owner };
		return jdbcTemplate.queryForInt(sql, args);
	}

	@Override
	public List<HyUserXfDesc> findXfDescList(long owner, int start, int rows) {
		String sql = "SELECT * FROM esite.es_hy_user_xf_desc WHERE owner=? LIMIT ?,?";
		Object[] args = { owner, start, rows };
		return jdbcTemplate.query(sql, args, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				HyUserXfDesc x = new HyUserXfDesc();
				x.setId(rs.getLong("id"));
				x.setOwner(rs.getLong("owner"));
				x.setName(rs.getString("name"));
				return x;
			}

		});
	}
	
	@Override
	public List<HyUserXfDesc> findXfDescList(long owner) {
		String sql = "SELECT * FROM esite.es_hy_user_xf_desc WHERE owner=? ";
		Object[] args = { owner};
		return jdbcTemplate.query(sql, args, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				HyUserXfDesc x = new HyUserXfDesc();
				x.setId(rs.getLong("id"));
				x.setOwner(rs.getLong("owner"));
				x.setName(rs.getString("name"));
				return x;
			}

		});
	}

	@Override
	public int addXfDesc(HyUserXfDesc xd) {
		String sql = "INSERT IGNORE INTO esite.es_hy_user_xf_desc(owner,name) VALUES(?,?)";
		Object[] args = { xd.getOwner(), xd.getName() };
		return jdbcTemplate.update(sql, args);
	}

	@Override
	public int updateXfDesc(HyUserXfDesc xd) {
		String sql = "SELECT COUNT(*) FROM esite.es_hy_user_xf_desc WHERE owner=? AND name=?";
		Object[] args = { xd.getOwner(), xd.getName() };
		int count = jdbcTemplate.queryForInt(sql, args);
		if (count > 0) {
			return 0;
		}
		sql = "UPDATE esite.es_hy_user_xf_desc SET name=? WHERE id=?";
		args = new Object[] { xd.getName(), xd.getId() };
		return jdbcTemplate.update(sql, args);
	}

	@Override
	public int deleteXfDesc(long id) {
		String sql = "DELETE FROM esite.es_hy_user_xf_desc WHERE id=?";
		Object[] args = { id };
		return jdbcTemplate.update(sql, args);
	}

	@Override
	public HyUserXfZk findXfZkListByXfid(long owner, long levelid,long xfid) {
		String sql = "select * from es_hy_user_xf_zk zk where zk.owner= ? and zk.xfid = ? and zk.levelid = ?";
		Object[] params = {owner,xfid,levelid};
		List<HyUserXfZk> list = jdbcTemplate.query(sql, params, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				HyUserXfZk zk = new HyUserXfZk();
				zk.setId(rs.getLong("zk.id"));
				zk.setXfid(rs.getLong("zk.xfid"));
				zk.setLevelid(rs.getLong("zk.levelid"));
				zk.setZk(rs.getInt("zk.zk"));
				zk.setOwner(rs.getLong("zk.owner"));
				return zk;
			}
		});
		if(list.size() >0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<HyUserLevel> findHyUserLevel(long owner) {
		String sql = "select * from es_hy_user_level where owner = ?";
		Object[] params = {owner};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				HyUserLevel level = new HyUserLevel();
				level.setId(rs.getLong("id"));
				level.setName(rs.getString("name"));
				level.setImg(rs.getString("img"));
				level.setHydesc(rs.getString("hydesc"));
				level.setOwner(rs.getLong("owner"));
				level.setDannum(rs.getInt("dannum"));
				level.setTotalnum(rs.getInt("totalnum"));
				level.setUsednum(rs.getInt("usednum"));
				return level;
			}
			
		});
	}

	@Override
	public int savezk(long levelid, long xfid, int zk, long owner) {
		String sql = "insert into es_hy_user_xf_zk(xfid,levelid,zk,owner) values(?,?,?,?) on duplicate key update zk = ?";
		Object[] params = {xfid,levelid,zk,owner,zk};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public HyUserXfDesc findXfdescByXfid(long xfid) {
		String sql = "select * from es_hy_user_xf_desc where id = ?";
		Object[] params = {xfid};
		List<HyUserXfDesc> list = jdbcTemplate.query(sql, params, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				HyUserXfDesc xf = new HyUserXfDesc();
				xf.setId(rs.getLong("id"));
				xf.setName(rs.getString("name"));
				xf.setOwner(rs.getLong("owner"));
				return xf;
			}
		});
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

}
