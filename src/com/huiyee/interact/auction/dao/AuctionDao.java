package com.huiyee.interact.auction.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.interact.auction.model.Auction;
import com.huiyee.interact.auction.model.AuctionRecord;

public class AuctionDao implements IAuctionDao
{
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int findAuctionTotal(long ownerid, long omid)
	{
		return jdbcTemplate.queryForInt("select count(id) from es_interact_auction where owner=? and omid=? and status!='D'", new Object[]
		{ ownerid, omid });
	}

	@Override
	public List<Auction> findAuctionList(long ownerid, int start, int size, long omid)
	{
		String sql = "select * from es_interact_auction where owner=? and status!='D' and omid=? order by id desc limit ?,? ";
		List<Object> param = new ArrayList<Object>();
		param.add(ownerid);
		param.add(omid);
		param.add(start);
		param.add(size);
		return jdbcTemplate.query(sql, param.toArray(), new MyRowMapper());
	}

	class MyRowMapper implements RowMapper
	{
		public Object mapRow(ResultSet rs, int arg1) throws SQLException
		{
			Auction auction = new Auction();
			auction.setId(rs.getLong("id"));
			auction.setTitle(rs.getString("title"));
			auction.setStarttimeDate(rs.getTimestamp("starttime"));
			auction.setEndtimeDate(rs.getTimestamp("endtime"));
			auction.setProendTime(rs.getTimestamp("proendTime"));
			auction.setStartbalance(rs.getInt("startbalance"));
			auction.setAddbalance(rs.getInt("addbalance"));
			auction.setSimg(rs.getString("simg"));
			auction.setUrl(rs.getString("url"));
			auction.setDescription(rs.getString("description"));
			auction.setAddsecond(rs.getInt("addsecond"));
			auction.setStatus(rs.getString("status"));
			return auction;
		}
	}

	@Override
	public long saveAuction(final Auction auction,final long omid)
	{
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator()
		{
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement("insert into esite.es_interact_auction (title,starttime,endtime,proendtime,owner,startbalance,addbalance,simg,url,description,addsecond,startnote,endnote,username,userphone,useremail,userlocation,logined,omid) value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new String[]
				{ "id" });
				int i = 1;
				ps.setString(i++, auction.getTitle());
				ps.setTimestamp(i++, new Timestamp(auction.getStarttime().getTime()));
				ps.setTimestamp(i++, new Timestamp(auction.getEndtime().getTime()));
				ps.setTimestamp(i++, new Timestamp(auction.getProendTime().getTime()));
				ps.setLong(i++, auction.getOwner());
				ps.setInt(i++, auction.getStartbalance());
				ps.setInt(i++, auction.getAddbalance());
				ps.setString(i++, auction.getSimg());
				ps.setString(i++, auction.getUrl());
				ps.setString(i++, auction.getDescription());
				ps.setInt(i++, auction.getAddsecond());
				ps.setString(i++, auction.getStartnote());
				ps.setString(i++, auction.getEndnote());
				ps.setString(i++, auction.getUserName());
				ps.setString(i++, auction.getUserPhone());
				ps.setString(i++, auction.getUserEmail());
				ps.setString(i++, auction.getUserLocation());
				ps.setLong(i++, omid);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().longValue();
		return id;
	}

	@Override
	public Auction findAuctionById(long id)
	{
		List<Auction> ls = jdbcTemplate.query("select * from es_interact_auction where id=? ", new Object[]
		{ id }, new RowMapper()
		{
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				Auction au = new Auction();
				au.setId(rs.getLong("id"));
				au.setTitle(rs.getString("title"));
				au.setStarttimeDate(rs.getTimestamp("starttime"));
				au.setEndtimeDate(rs.getTimestamp("endtime"));
				au.setOwner(rs.getLong("owner"));
				au.setStatus(rs.getString("status"));
				au.setAddbalance(rs.getInt("addbalance"));
				au.setAddsecond(rs.getInt("addsecond"));
				au.setDescription(rs.getString("description"));
				au.setProendTime(rs.getTimestamp("proendTime"));
				au.setSimg(rs.getString("simg"));
				au.setStartbalance(rs.getInt("startbalance"));
				au.setUrid(rs.getLong("urid"));
				au.setUrl(rs.getString("url"));
				au.setLastsecond(rs.getInt("lastsecond"));
				au.setCurrentmax(rs.getInt("currentmax"));
				au.setStartnote(rs.getString("startnote"));
				au.setEndnote(rs.getString("endnote"));
				au.setUserEmail(rs.getString("useremail"));
				au.setUserLocation(rs.getString("userlocation"));
				au.setUserPhone(rs.getString("userphone"));
				au.setUserName(rs.getString("username"));
				return au;
			}
		});
		if (ls != null && ls.size() > 0)
		{
			return ls.get(0);
		}

		return null;
	}

	@Override
	public void updateProEndtime(long id, long urid, int addnum,Date proendtime)
	{
		jdbcTemplate.update("update es_interact_auction set proendtime=?,urid=?,currentmax=? where id=?", new Object[]
		{ proendtime, urid, addnum,id });
	}

	@Override
	public int updateAuction(Auction auction, long auid, long owner)
	{
		String sql = "update es_interact_auction set title=?,starttime=?,endtime=?,startbalance=?,addbalance=?,simg=?,url=?,description=?,addsecond=?,startnote=?,endnote=?,username=?,userphone=?,useremail=?,userlocation=? where id=? and owner=?";
		return jdbcTemplate.update(sql, new Object[]
		{ auction.getTitle(), auction.getStarttime(), auction.getEndtime(), auction.getStartbalance(), auction.getAddbalance(), auction.getSimg(), auction.getUrl(), auction.getDescription(), auction.getAddsecond(),auction.getStartnote(),auction.getEndnote(),auction.getUserName(),auction.getUserPhone(),auction.getUserEmail(),auction.getUserLocation(), auid, owner });
	}

	@Override
	public int updateStatus(long auid, String status, long owner)
	{
		String sql = "update es_interact_auction set status=? where id=? and owner=?";
		return jdbcTemplate.update(sql, new Object[]
		{ status, auid, owner });
	}

	@Override
	public void updateUrid(long id, long urid,int addnum)
	{
		jdbcTemplate.update("update es_interact_auction set urid=?,currentmax=? where id=?", new Object[]
		{ urid, addnum,id });
	}
	
	@Override
	public long saveWinnerUser(final long aurid,final String username,final String userphone,final String useremail,final String userlocation)
	{
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator()
		{
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement("insert into esite.es_interact_auction_user_sub (aurid,username,userphone,useremail,userlocation,createtime) value(?,?,?,?,?,now())", new String[]
				{ "id" });
				int i = 1;
				ps.setLong(i++, aurid);
				ps.setString(i++, username);
				ps.setString(i++, userphone);
				ps.setString(i++, useremail);
				ps.setString(i++, userlocation);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().longValue();
		return id;
	}
	
	@Override
	public AuctionRecord findWinerRecord(long auid)
	{
		List<AuctionRecord> list=jdbcTemplate.query("select * from es_interact_auction_user_record where auid=? order by id desc limit 1", new Object[]{auid}, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				AuctionRecord r=new AuctionRecord();
				r.setId(rs.getLong("id"));
				r.setWbuid(rs.getLong("wbuid"));
				r.setType(rs.getInt("type"));
				return r;
			}
		});
		if(list.size()==1){
			return list.get(0);
		}
		return null;
	}

	@Override
	public long addAuction(final long ownerid, final String title)
	{
		final String sql="insert into es_interact_auction(owner,title) values(?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator()
		{
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(sql, new String[]
				{ "id" });
				int i = 1;
				ps.setLong(i++, ownerid);
				ps.setString(i++, title);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().longValue();
		return id;
	}
}
