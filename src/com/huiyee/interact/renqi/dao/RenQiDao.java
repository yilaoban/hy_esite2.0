package com.huiyee.interact.renqi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.util.DateUtil;
import com.huiyee.interact.renqi.model.RenQi;

public class RenQiDao implements IRenQiDao
{
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public RenQi findRenQiById(long id)
	{
		List<RenQi> ls = jdbcTemplate.query("select * from es_interact_rq where id=? ", new Object[]
		{ id }, new RowMapper()
		{
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				RenQi au = new RenQi();
				au.setId(rs.getLong("id"));
				au.setOwner(rs.getLong("owner"));
				au.setLotteryid(rs.getLong("lotteryid"));
				au.setUtype(rs.getInt("utype"));
				au.setWxtitle(rs.getString("wxtitle"));
				au.setWxdesc(rs.getString("wxdesc"));
				au.setWximg(rs.getString("wximg"));
				au.setStarttimeDate(rs.getTimestamp("starttime"));
				au.setEndtimeDate(rs.getTimestamp("endtime"));
				au.setStartnote(rs.getString("startnote"));
				au.setEndnote(rs.getString("endnote"));
				au.setCnum(rs.getInt("cnum"));
				au.setMinjf(rs.getInt("minjf"));
				au.setMaxjf(rs.getInt("maxjf"));
				au.setContent(rs.getString("content"));
				au.setTitle(rs.getString("title"));
				au.setLink(rs.getString("link"));
				au.setMaxlu(rs.getInt("maxlu"));
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
	public int findTotalByOwnerId(long ownerid)
	{
		return jdbcTemplate.queryForInt("select count(*) from esite.es_interact_rq where owner=? and status!='DEL'", new Object[]
		{ ownerid });
	}

	@Override
	public List<RenQi> findRenQiListByOwner(long ownerid, int start, int size)
	{
		List<RenQi> list = jdbcTemplate.query("select * from esite.es_interact_rq where owner=?  and status!='DEL' order by id desc limit ?,?", new Object[]
		{ ownerid, start, size }, new RowMapper()
		{
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				RenQi au = new RenQi();
				au.setId(rs.getLong("id"));
				au.setOwner(rs.getLong("owner"));
				au.setTitle(rs.getString("title"));
				au.setLotteryid(rs.getLong("lotteryid"));
				au.setUtype(rs.getInt("utype"));
				au.setWxtitle(rs.getString("wxtitle"));
				au.setWxdesc(rs.getString("wxdesc"));
				au.setWximg(rs.getString("wximg"));
				au.setStarttimeDate(rs.getTimestamp("starttime"));
				au.setEndtimeDate(rs.getTimestamp("endtime"));
				au.setStartnote(rs.getString("startnote"));
				au.setEndnote(rs.getString("endnote"));
				au.setCnum(rs.getInt("cnum"));
				au.setMinjf(rs.getInt("minjf"));
				au.setMaxjf(rs.getInt("maxjf"));
				au.setContent(rs.getString("content"));
				return au;
			}
		});
		return list;
	}

	private static final String SAVE_RQ = "insert into es_interact_rq (title,owner,starttime,startnote,endtime,endnote,lotteryid,maxlu,cnum,utype,content,link,wxtitle,wxdesc,minjf,maxjf) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	@Override
	public long saveRenQi(final RenQi rq, final long ownerid)
	{
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator()
		{
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(SAVE_RQ, new String[]
				{ "id" });
				int i = 1;
				ps.setString(i++, rq.getTitle());
				ps.setLong(i++, ownerid);
				ps.setString(i++, DateUtil.getDateTimeString(rq.getStarttime()));
				ps.setString(i++, rq.getStartnote());
				ps.setString(i++, DateUtil.getDateTimeString(rq.getEndtime()));
				ps.setString(i++, rq.getEndnote());
				ps.setLong(i++, rq.getLotteryid());
				ps.setInt(i++, rq.getMaxlu());
				ps.setInt(i++, rq.getCnum());
				ps.setInt(i++, rq.getUtype());
				ps.setString(i++, rq.getContent());
				ps.setString(i++, rq.getLink());
				ps.setString(i++, rq.getWxtitle());
				ps.setString(i++, rq.getWxdesc());
				ps.setInt(i++, rq.getMinjf());
				ps.setInt(i++, rq.getMaxjf());
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	@Override
	public int updateRenQi(RenQi rq, long id, long ownerid)
	{
		return jdbcTemplate.update("update es_interact_rq set title=?,starttime=?,startnote=?,endtime=?,endnote=?,lotteryid=?,maxlu=?,cnum=?,utype=?,content=?,link=?,wxtitle=?,wxdesc=?,minjf=?,maxjf=? where id=? and owner=?", new Object[]
		{ rq.getTitle(), DateUtil.getDateTimeString(rq.getStarttime()), rq.getStartnote(), DateUtil.getDateTimeString(rq.getEndtime()), rq.getEndnote(), rq.getLotteryid(), rq.getMaxlu(), rq.getCnum(), rq.getUtype(), rq.getContent(), rq.getLink(), rq.getWxtitle(), rq.getWxdesc(),
				rq.getMinjf(), rq.getMaxjf(), id, ownerid });
	}
	
	@Override
	public int updateStatus(long id, String status, long owner)
	{
		return jdbcTemplate.update("update es_interact_rq set status=? where id=? and owner=?", new Object[]{status,id,owner});
	}
}