
package com.huiyee.interact.cb.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.zookeeper.data.Stat;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;

import com.google.gson.Gson;
import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.interact.cb.dao.ICbJiDaoImpl;
import com.huiyee.interact.cb.model.CbImpel;
import com.huiyee.interact.cb.model.SenderImpel;

public class CbJiDaoImpl extends AbstractDao implements ICbJiDaoImpl
{

	@Override
	public int findJiliTotal(long cbaid, String name, String starttime, String endtime, String status)
	{
		List<Object> list = new ArrayList<Object>();
		String sql = "select count(*)  from  es_interact_cb_activity_jl_record g join es_interact_cb_sender s on g.sender=s.id join  es_feature_interact_apt_record  r on s.recordid=r.id  where g.aid=? ";
		list.add(cbaid);
		if (starttime != null && starttime.trim().length() > 0)
		{
			sql += " and g.dataday>=? ";
			list.add(starttime);
		}
		if (endtime != null && endtime.trim().length() > 0)
		{
			sql += " and g.dataday<=? ";
			list.add(endtime);
		}
		if (name != null && name.trim().length() > 0)
		{
			sql += " and r.name like ? ";
			list.add("%" + name + "%");
		}
		if (status != null && status.trim().length() > 0)
		{
			sql += " and g.status = ? ";
			list.add(status);
		}
		/*
		 * System.out.println(sql); System.out.println(new Gson().toJson(list));
		 */
		return getJdbcTemplate().queryForInt(sql, list.toArray());
	}

	@Override
	public List<SenderImpel> findSenderImpel(long cbid, String name, String starttime, String endtime, int start, int size, String stauts)
	{
		List<Object> list = new ArrayList<Object>();
		String sql = "select *  from  es_interact_cb_activity_jl_record g join es_interact_cb_sender s on g.sender=s.id join  es_feature_interact_apt_record  r on s.recordid=r.id where g.aid=? ";
		list.add(cbid);
		if (starttime != null && starttime.trim().length() > 0)
		{
			sql += " and g.createtime>=? ";
			list.add(starttime);
		}
		if (endtime != null && endtime.trim().length() > 0)
		{
			sql += " and g.createtime<=? ";
			list.add(endtime);
		}
		if (name != null && name.trim().length() > 0)
		{
			sql += " and r.name like ? ";
			list.add("%" + name + "%");
		}
		if (stauts != null && stauts.trim().length() > 0)
		{
			sql += " and g.status = ? ";
			list.add(stauts);
		}
		sql += "  order by g.id asc limit ?,?";
		list.add(start);
		list.add(size);
		/*
		 * System.out.println(sql); System.out.println(new Gson().toJson(list));
		 */
		return getJdbcTemplate().query(sql, list.toArray(), new RowMapper()
		{

			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				SenderImpel s = new SenderImpel();
				s.setId(rs.getLong("id"));
				s.setCbid(rs.getLong("cbid"));
				s.setCbaid(rs.getLong("aid"));
				s.setTotal(rs.getInt("total"));
				s.setCreatetime(rs.getTimestamp("createtime"));
				s.setDataday(rs.getDate("dataday"));
				s.setZhuanfa(rs.getInt("zhuanfa"));
				s.setZhuanfanum(rs.getInt("zhuanfanum"));
				s.setGuanzhu(rs.getInt("guanzhu"));
				s.setGuanzhunum(rs.getInt("guanzhunum"));
				s.setHudong(rs.getInt("hudong"));
				s.setHudongnum(rs.getInt("hudongnum"));
				s.setWaibu(rs.getInt("waibu"));
				s.setWaibunum(rs.getInt("waibunum"));
				s.setClick(rs.getInt("click"));
				s.setClicknum(rs.getInt("clicknum"));
				s.setStatus(rs.getInt("g.status"));
				s.setName(rs.getString("r.name"));
				return s;
			}
		});
	}

	@Override
	public int findSenderJiTotal(long sender, String starttime, String endtime)
	{
		List<Object> list = new ArrayList<Object>();
		String sql = "select count(*) from es_interact_cb_jl_record where sender=? ";
		list.add(sender);
		if (starttime != null && starttime.trim().length() > 0)
		{
			sql += " and createtime>=? ";
			list.add(starttime);
		}
		if (endtime != null && endtime.trim().length() > 0)
		{
			sql += " and createtime<=? ";
			list.add(endtime);
		}
		return getJdbcTemplate().queryForInt(sql, list.toArray());
	}

	@Override
	public List<CbImpel> findSenderImpelDetail(long senderid, String starttime, String endtime, int i, int cbImpelLimit)
	{
		List<Object> list = new ArrayList<Object>();
		String sql = "select * from es_interact_cb_jl_record where sender=? ";
		list.add(senderid);
		if (starttime != null && starttime.trim().length() > 0)
		{
			sql += " and createtime>=? ";
			list.add(starttime);
		}
		if (endtime != null && endtime.trim().length() > 0)
		{
			sql += " and createtime<=? ";
			list.add(endtime);
		}
		return getJdbcTemplate().query(sql, list.toArray(), new CbImpelRowMapper());
	}

	@Override
	public SenderImpel findJiRecordById(int impelId)
	{
		List<SenderImpel> list = getJdbcTemplate().query("select * from es_interact_cb_activity_jl_record where id=?", new Object[]
		{ impelId }, new RowMapper()
		{

			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				SenderImpel s = new SenderImpel();
				s.setId(rs.getLong("id"));
				s.setCbid(rs.getLong("cbid"));
				s.setCbaid(rs.getLong("aid"));
				s.setTotal(rs.getInt("total"));
				s.setCreatetime(rs.getTimestamp("createtime"));
				s.setSender(rs.getLong("sender"));
				return s;
			}
		});
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public void updatejISub(int impelId, int subPrice)
	{
		getJdbcTemplate().update("update es_interact_cb_activity_jl_record set status=1,total=? where id=?", new Object[]
		{ subPrice, impelId });
	}

	class CbImpelRowMapper implements RowMapper
	{

		public Object mapRow(ResultSet rs, int arg1) throws SQLException
		{
			CbImpel impel = new CbImpel();
			impel.setId(rs.getLong("id"));
			impel.setHongbao(rs.getInt("num"));
			impel.setCreatetime(rs.getTimestamp("createtime"));
			impel.setStarttime(rs.getTimestamp("starttime"));
			impel.setEndtime(rs.getTimestamp("endtime"));
			return impel;
		}
	}
}
