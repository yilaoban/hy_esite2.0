
package com.huiyee.interact.setting.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.interact.setting.dao.IHyUserWayDao;
import com.huiyee.interact.setting.model.UWay;

public class HyUserWayDao implements IHyUserWayDao
{

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public UWay findWay(long chyuid)
	{
		List<UWay> list = jdbcTemplate.query("select id,endtime from es_hy_user_way where chyuid=? and endtime>? and status=0", new Object[]
				{ chyuid ,System.currentTimeMillis()}, new RowMapper()
				{

					public Object mapRow(ResultSet rs, int arg1) throws SQLException
					{
						UWay sc = new UWay();
						sc.setId(rs.getLong("id"));
						sc.setEndtime(rs.getLong("endtime"));
						return sc;
					}
				});
				return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public void addway(long chyuid, long owner)
	{
		Object[] params ={ owner,chyuid,System.currentTimeMillis()+10*60*1000};
		String sql = "INSERT INTO es_hy_user_way (owner,chyuid,endtime,createtime) VALUES(?,?,?,now())";
		jdbcTemplate.update(sql, params);	
	}

	@Override
	public void updateway(long id, int rmb, int jf,long shyuid)
	{
		jdbcTemplate.update("update es_hy_user_way set shyuid=?,rmb=?,jf=?,status=1,stime=now() where id=?",new Object[]{shyuid,rmb,jf,id});

	}

	@Override
	public UWay findWayById(long id, long endtime,int status) {
		String sql = "select id,chyuid,rmb from es_hy_user_way where id=?  and status=? and endtime =?";
		Object[] params = {id,status,endtime};
		List<UWay> list = jdbcTemplate.query(sql,params,new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				UWay uw = new UWay();
				uw.setId(rs.getLong("id"));
				uw.setChyuid(rs.getLong("chyuid"));
				uw.setRmb(rs.getInt("rmb"));
				return uw;
			}
			
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public UWay findUWayById(long id) {
		String sql = "select id,chyuid,rmb,status from es_hy_user_way where id=?";
		Object[] params = {id};
		List<UWay> list = jdbcTemplate.query(sql,params,new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				UWay uw = new UWay();
				uw.setId(rs.getLong("id"));
				uw.setChyuid(rs.getLong("chyuid"));
				uw.setRmb(rs.getInt("rmb"));
				uw.setStatus(rs.getInt("status"));
				return uw;
			}
			
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
