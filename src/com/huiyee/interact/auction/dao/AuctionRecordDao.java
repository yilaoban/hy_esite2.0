package com.huiyee.interact.auction.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import com.huiyee.interact.auction.model.AuctionUserRecord;

import com.huiyee.interact.auction.model.AuctionRecord;

public class AuctionRecordDao implements IAuctionRecordDao
{
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<AuctionRecord> findAuctionJoin(long auid, String nickName, int start, int size,final int type)
	{
		List<Object> list = new ArrayList<Object>();
		String sql = "select record.wbuid,count(record.id) total,max(record.bidnum) bidnum,ip from es_interact_auction_user_record record where auid=? and type=? group by record.wbuid";
		list.add(auid);
		list.add(type);
		if (!StringUtils.isEmpty(nickName) && nickName.trim().length() > 0)
		{
			sql += " having user.nickname like ? ";
			list.add("%" + nickName + "%");
		}
		sql += " limit ?,?";
		list.add(start);
		list.add(size);
		return jdbcTemplate.query(sql, list.toArray(), new RowMapper()
		{
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				AuctionRecord a = new AuctionRecord();
				a.setWbuid(rs.getLong("record.wbuid"));
				a.setBidnum(rs.getInt("bidnum"));
				a.setTotal(rs.getInt("total"));
				a.setIp(rs.getString("ip"));
				a.setType(type);
				return a;
			}
		});
	}

	@Override
	public int findAuctionJoinTotal(long auid, int type)
	{
		List<Object> list = new ArrayList<Object>();
		String sql = "select count(DISTINCT record.wbuid) from es_interact_auction_user_record record where auid=? and type=?";
		list.add(auid);
		list.add(type);
		return jdbcTemplate.queryForInt(sql, list.toArray());
	}

	@Override
	public long addAuctionRecord(final long ownerwbuid,final long wbuid, final int utype, final long auid, final long pageid, final int bidnum, final String ip, final String terminal, final String source)
	{
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator()
		{
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement("insert into esite.es_interact_auction_user_record (wbuid,type,auid,pageid,bidnum,createtime,ip,terminal,source,ownerwbuid) value(?,?,?,?,?,now(),?,?,?,?)", new String[]
				{ "id" });
				int i = 1;
				ps.setLong(i++, wbuid);
				ps.setInt(i++, utype);
				ps.setLong(i++, auid);
				ps.setLong(i++, pageid);
				ps.setInt(i++, bidnum);
				ps.setString(i++, ip);
				ps.setString(i++, terminal);
				ps.setString(i++, source);
				ps.setLong(i++, ownerwbuid);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().longValue();
		return id;
	}

	@Override
	public AuctionUserRecord findRecord(long auid, long wbuid, int utype)
	{
		List<AuctionUserRecord> ls = jdbcTemplate.query("select * from es_interact_auction_user_record where auid=? and wbuid=? and type=?", new Object[]
		{ auid, wbuid, utype }, new RowMapper()
		{
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				AuctionUserRecord au = new AuctionUserRecord();
				au.setId(rs.getLong("id"));
				au.setAuid(rs.getLong("auid"));
				au.setBidnum(rs.getInt("bidnum"));
				au.setCreatetime(rs.getTimestamp("createtime"));
				au.setIp(rs.getString("ip"));
				au.setPageid(rs.getLong("pageid"));
				au.setSource(rs.getString("source"));
				au.setTerminal(rs.getString("terminal"));
				au.setWbuid(rs.getLong("wbuid"));
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
	public List<AuctionRecord> findJoinDetail(long auid, long wbuid)
	{
		List<AuctionRecord> ls = jdbcTemplate.query("select * from es_interact_auction_user_record where auid=? and wbuid=?", new Object[]
		{ auid, wbuid }, new RowMapper()
		{
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				AuctionRecord au = new AuctionRecord();
				au.setBidnum(rs.getInt("bidnum"));
				au.setCreatetime(rs.getTimestamp("createtime"));
				au.setWbuid(rs.getLong("wbuid"));
				return au;
			}
		});
		return ls;
	}

	@Override
	public AuctionRecord findAuctionWinner(long auid)
	{
		List<AuctionRecord> ls = jdbcTemplate
				.query(
						"select record.wbuid,user.nickname,count(record.id) total,max(record.bidnum) bidnum from es_interact_auction_user_record record join es_interact_auction auction on auction.id=record.auid join es_sina_user user on record.wbuid=user.wbuid  where record.auid=? and auction.status='C' group by record.wbuid order by bidnum desc limit 1 ",
						new Object[]
						{ auid }, new RowMapper()
						{
							@Override
							public Object mapRow(ResultSet rs, int arg1) throws SQLException
							{
								AuctionRecord au = new AuctionRecord();
								au.setBidnum(rs.getInt("bidnum"));
								au.setWbuid(rs.getLong("record.wbuid"));
								au.setNickName(rs.getString("user.nickName"));
								au.setTotal(rs.getInt("total"));
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
	public List<AuctionRecord> findAuctionPssed(long auid, int utype)
	{
		List<AuctionRecord> ls = jdbcTemplate.query("select * from es_interact_auction_user_record where auid=? and type=? and pssed=0", new Object[]
		{ auid, utype }, new RowMapper()
		{
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				AuctionRecord au = new AuctionRecord();
				au.setId(rs.getLong("id"));
				au.setBidnum(rs.getInt("bidnum"));
				au.setCreatetime(rs.getTimestamp("createtime"));
				au.setWbuid(rs.getLong("wbuid"));
				au.setType(rs.getInt("type"));
				au.setOwnerwbuid(rs.getLong("ownerwbuid"));
				return au;
			}
		});
		return ls;
	}

	@Override
	public void updatePssed(long id)
	{
		jdbcTemplate.update("update es_interact_auction_user_record set pssed=1 where id=?", new Object[]
		{ id });
	}
}
