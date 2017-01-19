package com.huiyee.interact.setting.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.interact.setting.dao.IHyUserLevelDao;
import com.huiyee.interact.setting.model.HyUserLevel;

public class HyUserLevelDaoImpl implements IHyUserLevelDao {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<HyUserLevel> findHyUserLevelByOwner(long owner) {
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
	public int saveUserLevel(HyUserLevel level,long owner,int hylevel) {
		String sql = "";
		List<Object> list = new ArrayList<Object>();
		list.add(level.getName());
		list.add(level.getImg());
		list.add(level.getHydesc());
		if(hylevel == 1){
			sql = "insert into es_hy_user_level(name,img,hydesc,dannum,owner) values(?,?,?,?,?)";
			list.add(level.getDannum());
		}else if(hylevel == 2){
			sql = "insert into es_hy_user_level(name,img,hydesc,totalnum,owner) values(?,?,?,?,?)";
			list.add(level.getTotalnum());
		}else if(hylevel == 3){
			sql = "insert into es_hy_user_level(name,img,hydesc,usednum,owner) values(?,?,?,?,?)";
			list.add(level.getUsednum());
		}else if(hylevel == 4){
			sql = "insert into es_hy_user_level(name,img,hydesc,owner) values(?,?,?,?)";
		}
		list.add(owner);
		return jdbcTemplate.update(sql, list.toArray());
	}

	@Override
	public HyUserLevel findHyUserLevelById(long id, long owner) {
		String sql = "select * from es_hy_user_level where id = ? and owner = ?";
		Object[] params = {id,owner};
		List<HyUserLevel> list = jdbcTemplate.query(sql, params, new RowMapper(){
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
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int updateUserLevelById(HyUserLevel level,int hylevel) {
		String sql = "";
		List<Object> list = new ArrayList<Object>();
		list.add(level.getName());
		list.add(level.getImg());
		list.add(level.getHydesc());
		if(hylevel == 1){
			sql = "update es_hy_user_level set name=?,img=?,hydesc=?,dannum=? where id = ?";
			list.add(level.getDannum());
		}else if(hylevel == 2){
			sql = "update es_hy_user_level set name=?,img=?,hydesc=?,totalnum=? where id = ?";
			list.add(level.getTotalnum());
		}else if(hylevel == 3){
			sql = "update es_hy_user_level set name=?,img=?,hydesc=?,usednum=? where id = ?";
			list.add(level.getUsednum());
		}else if(hylevel == 4){
			sql = "update es_hy_user_level set name=?,img=?,hydesc=? where id = ?";
		}
		list.add(level.getId());
		return jdbcTemplate.update(sql, list.toArray());
	}

	@Override
	public int delUserLevel(long id, long owner) {
		String sql = "delete from es_hy_user_level where id = ? and owner = ?";
		Object[] params = {id,owner};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int updateHyUserByHyuid(long hyuid, long levelid) {
		String sql = "update es_hy_user set levelid = ? where id = ?";
		Object[] params = {levelid,hyuid};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public HyUserLevel findHyUserLevelByOwnerAndRMB(long owner, int rmb,int hylevel,int totalRmb,int rmbused) {
		String sql = "select * from es_hy_user_level where owner = ? ";
		List<Object> params = new ArrayList<Object>();
		params.add(owner);
		if(hylevel == 1){
			sql += " and dannum <= ? order by dannum desc";
			params.add(rmb);
		}else if(hylevel == 2){
			sql += " and totalnum <= ? order by totalnum desc";
			params.add(totalRmb);
		}else if(hylevel == 3){
			sql += " and usednum <= ? order by usednum desc";
			params.add(rmbused);
		}
		List<HyUserLevel> list = jdbcTemplate.query(sql, params.toArray(), new RowMapper(){
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
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	
}
