package com.huiyee.interact.offcheck.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.interact.offcheck.model.OffCheckDzWay;

public class OffCheckDzWayDao extends AbstractDao implements IOffCheckDzWayDao
{
	@Override
	public OffCheckDzWay findDzWay(long id)
	{
		List<OffCheckDzWay> list = getJdbcTemplate().query("select * from es_off_check_dz_way where id=?", new Object[]
				{ id }, new RowMapper()
				{

					public Object mapRow(ResultSet rs, int arg1) throws SQLException
					{
						OffCheckDzWay sc = new OffCheckDzWay();
						sc.setId(rs.getLong("id"));
						sc.setCreatetime(rs.getTimestamp("createtime"));
						sc.setCsid(rs.getLong("csid"));
						sc.setCwxuid(rs.getLong("cwxuid"));
						sc.setEndtime(rs.getTimestamp("endtime"));
						sc.setJf(rs.getInt("jf"));
						sc.setRmb(rs.getInt("rmb"));
						sc.setStatus(rs.getInt("status"));
						sc.setStime(rs.getTime("stime"));
						sc.setSwxuid(rs.getLong("swxuid"));
						return sc;
					}
				});
				return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public void updateDzWay(long id, long wxuid, int jf)
	{
		getJdbcTemplate().update("update es_off_check_dz_way set swxuid=?,jf=?,status=1,stime=now() where id=?",new Object[]{wxuid,jf,id});
	}

	@Override
	public void addDzWay(long wxuid,long csid, int rmb)
	{
		Object[] params ={ wxuid,csid,rmb,new Date(System.currentTimeMillis()+10*60*1000)};
		String sql = "INSERT INTO es_off_check_dz_way (cwxuid,csid,rmb,endtime,createtime) VALUES(?,?,?,?,now())";
		getJdbcTemplate().update(sql, params);		
	}

	@Override
	public long findDzWayId(long wxuid, long csid , int rmb)
	{
		try
		{
			return getJdbcTemplate().queryForLong("select id from es_off_check_dz_way where cwxuid=? and csid=? and rmb=? and status=0 and endtime >now()",new Object[]{wxuid,csid,rmb});
		} catch (Exception e)
		{
			return 0;
		}
	}

}
