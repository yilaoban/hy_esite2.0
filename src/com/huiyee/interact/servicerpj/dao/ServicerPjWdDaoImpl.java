package com.huiyee.interact.servicerpj.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.interact.servicerpj.model.ServicerPjWd;

public class ServicerPjWdDaoImpl implements IServicerPjWdDao {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<ServicerPjWd> findServicerPjwdList(long owner,int start,int size) {
		String sql = "select * from es_yu_yue_servicer_pj_wd where owner = ? and del_tag != 'Y' order by id desc limit ?,?";
		Object[] params = {owner,start,size};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				ServicerPjWd pjwd = new ServicerPjWd();
				pjwd.setId(rs.getLong("id"));
				pjwd.setName(rs.getString("name"));
				pjwd.setOwner(rs.getLong("owner"));
				return pjwd;
			}
			
		});
	}

	@Override
	public int findTotalServicerPjwd(long owner) {
		String sql = "select count(id) from es_yu_yue_servicer_pj_wd where owner = ? and del_tag != 'Y'";
		Object[] params = {owner};
		return jdbcTemplate.queryForInt(sql, params);
	}

	@Override
	public int savepjwd(long owner, String name) {
		String sql = "insert into es_yu_yue_servicer_pj_wd(owner,name) values(?,?)";
		Object[] params = {owner,name};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public ServicerPjWd findPjWdById(long id) {
		String sql = "select * from es_yu_yue_servicer_pj_wd where id = ? and del_tag != 'Y'";
		Object[] params = {id};
		List<ServicerPjWd> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				ServicerPjWd pjwd = new ServicerPjWd();
				pjwd.setId(rs.getLong("id"));
				pjwd.setName(rs.getString("name"));
				pjwd.setOwner(rs.getLong("owner"));
				return pjwd;
			}
		});
		if(list.size() >0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int updatePjWdById(String name, long id) {
		String sql = "update es_yu_yue_servicer_pj_wd set name = ? where id = ?";
		Object[] params = {name,id};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int delPjWdById(long id) {
		String sql = "update es_yu_yue_servicer_pj_wd set del_tag = 'Y' where id= ?";
		Object[] params = {id};
		return jdbcTemplate.update(sql, params);
	}
	
}
