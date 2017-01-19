
package com.huiyee.interact.lottery.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.huiyee.interact.lottery.model.LotteryUser;

public class LotteryUserDao implements ILotteryUserDao
{

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public LotteryUser findLotteryUser(long wbuid, long lid, int type)
	{
		List<LotteryUser> ls = jdbcTemplate.query("select * from es_interact_lottery_user where wbuid=? and lid=? and type=?", new Object[]
		{ wbuid, lid, type }, new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				LotteryUser lu = new LotteryUser();
				lu.setId(rs.getLong("id"));
				lu.setLid(rs.getLong("lid"));
				lu.setNickName(rs.getString("nickName"));
				lu.setTotalnum(rs.getInt("totalnum"));
				lu.setUsednum(rs.getInt("usednum"));
				lu.setWbuid(rs.getLong("wbuid"));
				return lu;

			}
		});
		if (ls != null && ls.size() > 0)
		{
			return ls.get(0);
		}

		return null;
	}

	@Override
	public void addLotteryUser(long wbuid, long lid, int total, int used, int type)
	{
		jdbcTemplate.update("insert into es_interact_lottery_user (wbuid,lid,totalnum,usednum,type) values (?,?,?,?,?)", new Object[]
		{ wbuid, lid, total, used, type });
	}
	
	@Override
	public void updateLotteryUser(long wbuid, long lid, int total, int type)
	{
		jdbcTemplate.update("update es_interact_lottery_user set totalnum=? where lid=? and wbuid=? and type=?", new Object[]
				{ total, lid,wbuid, type });
	}
	
	@Override
	public void updateLotteryUsed(long wbuid, long lid, int type)
	{
		jdbcTemplate.update("update es_interact_lottery_user  set usednum=usednum+1 where wbuid=? and lid=? and type=? and totalnum>usednum", new Object[]
		{ wbuid, lid, type });

	}

	@Override
	public List<LotteryUser> findAllLotteryUser(String nickName, int start, int size, long lid, long ownerid)
	{
		List<Object> list = new ArrayList<Object>();
		String sql = "select *,u.username,wb.nickname nickname1 from es_interact_lottery_user l left outer join es_sina_user wb on l.wbuid=wb.wbuid left join es_hy_user u on l.wbuid = u.wbuid and u.owner=?  where l.lid=? and l.type=0";
		list.add(ownerid);
		list.add(lid);
		if (!StringUtils.isEmpty(nickName) && nickName.trim().length() > 0)
		{
			sql += " and u.nickname like ? ";
			list.add("%" + nickName + "%");
		}
		sql += " limit ?,?";
		list.add(start);
		list.add(size);
		List<LotteryUser> result = jdbcTemplate.query(sql, list.toArray(), new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				LotteryUser lu = new LotteryUser();
				lu.setId(rs.getLong("l.id"));
				lu.setLid(rs.getLong("l.lid"));
				lu.setNickName(rs.getString("nickname1"));
				lu.setTotalnum(rs.getInt("l.totalnum"));
				lu.setUsednum(rs.getInt("l.usednum"));
				lu.setWbuid(rs.getLong("l.wbuid"));
				lu.setUsername(rs.getString("u.username"));
				return lu;
			}
		});
		return result;
	}

	@Override
	public int findAllLotteryUserTotal(String nickName, long lid, long ownerid)
	{
		List<Object> list = new ArrayList<Object>();
		String sql = "select count(l.id) from es_interact_lottery_user l left outer join es_sina_user user on l.wbuid=user.wbuid left join es_hy_user u on l.wbuid = u.wbuid and u.owner=?  where l.lid=? and l.type=0";
		list.add(ownerid);
		list.add(lid);
		if (!StringUtils.isEmpty(nickName) && nickName.trim().length() > 0)
		{
			sql += " and user.nickname like ? ";
			list.add("%" + nickName + "%");
		}
		return jdbcTemplate.queryForInt(sql, list.toArray());
	}



	@Override
	public List<LotteryUser> findAllWxUser(String nickName, int start, int size, long lid, long ownerid, int type)
	{
		String sql = "";
		if (type == 1)
		{
			sql = "select *,u.username,wx.nickname nickname1 from es_interact_lottery_user r left outer join es_wx_user wx on wx.id=r.wbuid left join es_hy_user u on r.wbuid = u.wxuid and u.owner=? where r.lid=? and r.type=1 ";
		} else if (type == 2)
		{
			sql = "select *,u.username,wb.nickname nickname1,wx.nickname wxnickname from es_interact_lottery_user r left join es_hy_user u on r.wbuid = u.id and u.owner=? left join es_sina_user wb on u.wbuid = wb.wbuid left join es_wx_user wx on u.wxuid = wx.id where r.lid=? and r.type=2 ";
		} else if (type == -1)
		{
			sql = "select *,u.username,'' nickname1,'' wxnickname from es_interact_lottery_user r left join es_hy_user u on r.wbuid = u.wxuid and u.owner=?  where lid=? and type=-1 ";
		}
		List<Object> list = new ArrayList<Object>();
		list.add(ownerid);
		list.add(lid);
		if (!StringUtils.isEmpty(nickName) && nickName.trim().length() > 0)
		{
			sql += " and u.nickname like ? ";
			list.add("%" + nickName + "%");
		}
		sql += " limit ?,?";
		list.add(start);
		list.add(size);
		List<LotteryUser> result = jdbcTemplate.query(sql, list.toArray(), new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				LotteryUser lu = new LotteryUser();
				lu.setId(rs.getLong("r.id"));
				lu.setLid(rs.getLong("r.lid"));
				lu.setTotalnum(rs.getInt("r.totalnum"));
				lu.setUsednum(rs.getInt("r.usednum"));
				lu.setWbuid(rs.getLong("r.wbuid"));
				int type = rs.getInt("r.type");
				if (type != -1)
				{
					lu.setNickName(rs.getString("nickname1"));
				}
				if (type == 2)
				{
					lu.setWxnickname(rs.getString("wxnickname"));
				}
				lu.setUsername(rs.getString("u.username"));
				return lu;
			}
		});
		return result;
	}

	@Override
	public int findAllWxUserTotal(String nickName, long lid, long ownerid, int type)
	{
		String sql = "";
		if (type == 1)
		{
			sql = "select count(r.id) from es_interact_lottery_user r left outer join es_wx_user wx on wx.id=r.wbuid left join es_hy_user u on r.wbuid = u.wxuid and u.owner=? where r.lid=? and r.type=1 ";
		} else if (type == 2)
		{
			sql = "select count(r.id) from es_interact_lottery_user r left join es_hy_user u on r.wbuid = u.id  and u.owner=? left join es_sina_user wb on u.wbuid = wb.wbuid left join es_wx_user wx on u.wxuid = wx.id where r.lid=? and r.type=2 ";
		} else if (type == -1)
		{
			sql = "select count(r.id) from es_interact_lottery_user r left join es_hy_user u on r.wbuid = u.wxuid  and u.owner=? where lid=? and type=-1 ";
		}
		List<Object> list = new ArrayList<Object>();
		list.add(ownerid);
		list.add(lid);
		if (!StringUtils.isEmpty(nickName) && nickName.trim().length() > 0)
		{
			sql += " and user.nickname like ? ";
			list.add("%" + nickName + "%");
		}
		return jdbcTemplate.queryForInt(sql, list.toArray());
	}

	@Override
	public String findWbNickName(long wbuid)
	{
		List<String> list = jdbcTemplate.query("select nickname from es_sina_user where wbuid=? ", new Object[]
		{ wbuid }, new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				return rs.getString("nickname");
			}
		});
		if (list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}

	@Override
	public String findWxNickName(long wbuid)
	{
		List<String> list = jdbcTemplate.query("select nickname from es_wx_user where id=? ", new Object[]
		{ wbuid }, new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				return rs.getString("nickname");
			}
		});
		if (list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}

	@Override
	public Map<String, String> findWxUserInfo(long uid)
	{
		return jdbcTemplate.queryForMap("select * from es_wx_user where id=?", new Object[]
		{ uid });
	}

	@Override
	public Map<String, String> findHyUserInfo(long uid)
	{
		return jdbcTemplate.queryForMap("select * from es_hy_user where id=?", new Object[]
		{ uid });
	}



}
