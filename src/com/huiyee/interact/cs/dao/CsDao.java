package com.huiyee.interact.cs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.util.DateUtil;
import com.huiyee.interact.cs.model.ChuanSan;

public class CsDao implements ICsDao
{
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<ChuanSan> findListByOwner(long ownerid, int start, int size, long omid)
	{
		List<ChuanSan> list = jdbcTemplate.query("select * from  es_interact_cs where owner=? and omid=?  order by id desc limit ?,?", new Object[]
		{ ownerid, omid, start, size }, new RowMapper()
		{
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				ChuanSan au = new ChuanSan();
				au.setId(rs.getLong("id"));
				au.setOwner(rs.getLong("owner"));
				au.setUtype(rs.getInt("utype"));
				au.setStarttimeDate(rs.getTimestamp("starttime"));
				au.setEndtimeDate(rs.getTimestamp("endtime"));
				au.setStartnote(rs.getString("startnote"));
				au.setEndnote(rs.getString("endnote"));
				au.setContent(rs.getString("content"));
				au.setTitle(rs.getString("title"));
				au.setLink(rs.getString("link"));
				return au;
			}
		});
		return list;
	}

	@Override
	public int findTotalByOwnerId(long ownerid, long omid)
	{
		return jdbcTemplate.queryForInt("select count(*) from es_interact_cs where owner=? and omid=?", new Object[]
		{ ownerid, omid });
	}

	private static final String SAVE_CHUAN_SAN = "insert into es_interact_cs (title,owner,starttime,startnote,endtime,endnote,utype,content,link,omid) values(?,?,?,?,?,?,?,?,?,?)";

	@Override
	public long save(final ChuanSan cs, final long ownerid,final long omid)
	{
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator()
		{
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(SAVE_CHUAN_SAN, new String[]
				{ "id" });
				int i = 1;
				ps.setString(i++, cs.getTitle());
				ps.setLong(i++, ownerid);
				ps.setString(i++, DateUtil.getDateTimeString(cs.getStarttime()));
				ps.setString(i++, cs.getStartnote());
				ps.setString(i++, DateUtil.getDateTimeString(cs.getEndtime()));
				ps.setString(i++, cs.getEndnote());
				ps.setInt(i++, cs.getUtype());
				ps.setString(i++, cs.getContent());
				ps.setString(i++, cs.getLink());
				ps.setLong(i++, omid);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	@Override
	public int update(ChuanSan rq, long id, long ownerid)
	{
		return jdbcTemplate.update("update es_interact_cs set title=?,starttime=?,startnote=?,endtime=?,endnote=?,utype=?,content=?,link=? where id=? and owner=?", new Object[]
		{ rq.getTitle(), DateUtil.getDateTimeString(rq.getStarttime()), rq.getStartnote(), DateUtil.getDateTimeString(rq.getEndtime()), rq.getEndnote(), rq.getUtype(), rq.getContent(), rq.getLink(), id, ownerid });
	}

	@Override
	public ChuanSan findById(long id, long ownerid)
	{
		List<ChuanSan> list = jdbcTemplate.query("select * from es_interact_cs where id=? and owner=?", new Object[]
		{ id, ownerid }, new RowMapper()
		{
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				ChuanSan cs = new ChuanSan();
				cs.setId(rs.getLong("id"));
				cs.setOwner(rs.getLong("owner"));
				cs.setUtype(rs.getInt("utype"));
				cs.setStarttimeDate(rs.getTimestamp("starttime"));
				cs.setEndtimeDate(rs.getTimestamp("endtime"));
				cs.setStartnote(rs.getString("startnote"));
				cs.setEndnote(rs.getString("endnote"));
				cs.setContent(rs.getString("content"));
				cs.setTitle(rs.getString("title"));
				cs.setLink(rs.getString("link"));
				cs.setJcontent(rs.getString("jcontent"));
				return cs;
			}
		});
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public int updateCsJcontent(long id, long owner, String jsonStr)
	{
		return jdbcTemplate.update("update es_interact_cs set jcontent=? where id=? and owner=?", new Object[]
		{ jsonStr, id, owner });
	}
}
