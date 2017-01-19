package com.huiyee.interact.checkin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.interact.checkin.model.Checkin;
import com.huiyee.interact.checkin.model.CheckinBalance;
import com.huiyee.interact.checkin.model.CheckinRecord;

public class CheckinDao implements ICheckinDao
{
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Checkin findCheckRule(long ownerwbuid,int utype)
	{
		List<Checkin> ls = jdbcTemplate.query("select * from es_interact_checkin where ownerwbuid=? and utype=?", new Object[]
		{ ownerwbuid ,utype}, new RowMapper()
		{
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				Checkin ck = new Checkin();
				ck.setId(rs.getLong("id"));
				ck.setAddaddnum(rs.getInt("addaddnum"));
				ck.setAddnum(rs.getInt("addnum"));
				ck.setMaxday(rs.getInt("maxday"));
				ck.setOwnerwbuid(rs.getLong("ownerwbuid"));
				ck.setUtype(rs.getInt("utype"));
				return ck;
			}
		});
		if (ls != null && ls.size() > 0)
		{
			return ls.get(0);
		}

		return null;
	}

	@Override
	public List<Checkin> findCheckRuleList(long ownerid,int start,int size)
	{
		String sql = "select distinct token.cid,token.nickname, checkin.* from es_sina_token token left outer join es_interact_checkin checkin on checkin.ownerwbuid=token.cid where ownerid=? and token.cid is not null limit ?,?";
		return jdbcTemplate.query(sql, new Object[]{ ownerid,start,size }, new MyRowMapper());
	}

	@Override
	public int findCheckRuleTotal(long ownerid){
		String sql = "select count(distinct token.cid) from es_sina_token token left outer join es_interact_checkin checkin on checkin.ownerwbuid=token.cid where ownerid=? and  token.cid is not null";
		Object[] params={ownerid};
		return jdbcTemplate.queryForInt(sql,params);
	}
	
	@Override
	public List<Checkin> findWxCheckRuleList(long ownerid,int start,int size)
	{
		String sql = "select * from es_wx_open_auth app left outer join es_interact_checkin checkin on checkin.ownerwbuid=app.id where app.ownerid=? limit ?,? ";
		return jdbcTemplate.query(sql, new Object[]
		{ ownerid,start,size }, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				Checkin ck = new Checkin();
				ck.setId(rs.getLong("checkin.id"));
				ck.setAddaddnum(rs.getInt("checkin.addaddnum"));
				ck.setAddnum(rs.getInt("checkin.addnum"));
				ck.setMaxday(rs.getInt("checkin.maxday"));
				ck.setOwnerwbuid(rs.getLong("app.id"));
				ck.setNickName(rs.getString("app.nick_name"));
				ck.setUtype(rs.getInt("checkin.utype"));
				return ck;
			}
			
		});
	}
	
	public int findWxCheckRuleTotal(long ownerid){
		String sql = "select count(*) from es_wx_open_auth app left outer join es_interact_checkin checkin on checkin.ownerwbuid=app.id where app.ownerid=? ";
		Object[] params={ownerid};
		return jdbcTemplate.queryForInt(sql,params);
	}
	
	class MyRowMapper implements RowMapper
	{
		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException
		{
			Checkin ck = new Checkin();
			ck.setId(rs.getLong("checkin.id"));
			ck.setAddaddnum(rs.getInt("checkin.addaddnum"));
			ck.setAddnum(rs.getInt("checkin.addnum"));
			ck.setMaxday(rs.getInt("checkin.maxday"));
			ck.setOwnerwbuid(rs.getLong("token.cid"));
			ck.setNickName(rs.getString("token.nickname"));
			ck.setUtype(rs.getInt("checkin.utype"));
			return ck;
		}
	}

	@Override
	public int updateCheckin(Checkin in)
	{
		String sql = "insert into es_interact_checkin (ownerwbuid,utype,addnum,addaddnum,maxday) values(?,?,?,?,?) on duplicate key update addnum=?,addaddnum=?,maxday=?";
		return jdbcTemplate.update(sql, new Object[]
		{ in.getOwnerwbuid(), in.getUtype(),in.getAddnum(), in.getAddaddnum(), in.getMaxday(), in.getAddnum(), in.getAddaddnum(), in.getMaxday() });
	}

	@Override
	public int delCheckin(long ownerwbuid,int utype)
	{
		return jdbcTemplate.update("delete from es_interact_checkin where ownerwbuid=? and utype =?", new Object[]
		{ ownerwbuid,utype });
	}

	@Override
	public CheckinBalance findBalance(long ownerwbuid, long wbuid, int utype) {
		String sql = "select * from es_owner_wbuid_balance where ownerwbuid = ? and wbuid = ? and utype = ?";
		List<CheckinBalance> list = jdbcTemplate.query(sql, new Object[]{ownerwbuid,wbuid,utype}, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CheckinBalance cb = new CheckinBalance();
				cb.setId(rs.getLong("id"));
				cb.setTotal(rs.getInt("total"));
				cb.setUsed(rs.getInt("used"));
				cb.setOwnerwbuid(rs.getLong("ownerwbuid"));
				cb.setWbuid(rs.getLong("wbuid"));
				cb.setPreused(rs.getInt("preused"));
				cb.setUtype(rs.getInt("utype"));
				return cb;
			}
			
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int findCheckinBalanceCount(long ownerwbuid, long wbuid, int utype)
	{
		String sql = "select sum(addbalance) from es_interact_checkin_record where wbuid =? and ownerwbuid= ? and type = ?";
		return jdbcTemplate.queryForInt(sql, new Object[]{wbuid,ownerwbuid,utype});
	}

	@Override
	public List<CheckinRecord> findCheckinBalance(long ownerwbuid, long wbuid, int utype)
	{
		String sql = "select * from es_interact_checkin_record r where wbuid =? and ownerwbuid= ? and type = ? order by r.id desc limit 5";
		return jdbcTemplate.query(sql, new Object[]{wbuid,ownerwbuid,utype}, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CheckinRecord r = new CheckinRecord();
				r.setCreatetime(rs.getTimestamp("createtime"));
				r.setAddbalance(rs.getInt("addbalance"));
				return r;
			}
			
		});
	}

	@Override
	public List<CheckinRecord> findCheckinRecordList(long owner,int start,int size)
	{
		String sql = "select r.*,hu.nickname huname,wx.nickname wxname from es_interact_checkin_record r left join es_hy_user hu on r.hyuid = hu.id left join es_wx_user wx on hu.wxuid = wx.id where hu.owner = ? order by r.createtime desc limit ?,?";
		Object[] params = {owner,start,size};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CheckinRecord r = new CheckinRecord();
				r.setCreatetime(rs.getTimestamp("r.createtime"));
				r.setAddbalance(rs.getInt("r.addbalance"));
				r.setDaynum(rs.getInt("r.daynum"));
				r.setIp(rs.getString("r.ip"));
				r.setTerminal(rs.getString("r.terminal"));
				r.setSource(rs.getString("r.source"));
				if(StringUtils.isNotBlank(rs.getString("huname"))){
					r.setNickname(rs.getString("huname"));
				}else{
					r.setNickname(rs.getString("wxname"));
				}
				return r;
			}
			
		});
	}

	@Override
	public int findCheckinRecordListTotal(long owner)
	{
		String sql = "select count(r.id) from es_interact_checkin_record r left join es_hy_user hu on r.hyuid = hu.id left join es_wx_user wx on hu.wxuid = wx.id where hu.owner = ?";
		Object[] params = {owner};
		return jdbcTemplate.queryForInt(sql,params);
	}

	@Override
	public Checkin findCheckRuleByOwner(long owner)
	{
		List<Checkin> ls = jdbcTemplate.query("select * from es_interact_checkin where owner=? ", new Object[]
		{ owner}, new RowMapper()
		{
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				Checkin ck = new Checkin();
				ck.setId(rs.getLong("id"));
				ck.setOwner(rs.getLong("owner"));
				ck.setAddaddnum(rs.getInt("addaddnum"));
				ck.setAddnum(rs.getInt("addnum"));
				ck.setMaxday(rs.getInt("maxday"));
				return ck;
			}
		});
		if (ls != null && ls.size() > 0)
		{
			return ls.get(0);
		}

		return null;
	}
	
}
