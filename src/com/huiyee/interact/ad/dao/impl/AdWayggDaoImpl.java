
package com.huiyee.interact.ad.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.interact.ad.dao.IAdWayggDao;
import com.huiyee.interact.ad.model.AdWaygg;
import com.huiyee.interact.ad.model.Adgg;

public class AdWayggDaoImpl implements IAdWayggDao
{

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Adgg> findAdggByWayid(long wayid)
	{
		Date date = new Date();
		String sql = "select * from es_ad_gg gg join es_ad_way_gg wg on gg.id = wg.ggid join es_ad_way w on wg.wayid = w.id where wg.wayid = ? and (gg.starttime is null or gg.starttime <= ?) and (gg.endtime is null or gg.endtime >= ?)";
		Object[] params =
		{ wayid, date, date };
		return jdbcTemplate.query(sql, params, new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				Adgg adgg = new Adgg();
				adgg.setId(rs.getLong("gg.id"));
				adgg.setTitle(rs.getString("gg.title"));
				adgg.setHydesc(rs.getString("gg.hydesc"));
				adgg.setContent(rs.getString("gg.content"));
				adgg.setOwner(rs.getLong("gg.owner"));
				adgg.setImg(rs.getString("gg.img"));
				adgg.setUrl(rs.getString("gg.url"));
				adgg.setStarttime(rs.getTimestamp("gg.starttime"));
				adgg.setEndtime(rs.getTimestamp("gg.endtime"));
				adgg.setFsurl(rs.getString("w.fsurl"));
				adgg.setWurl(rs.getString("w.url"));
				return adgg;
			}
		});
	}

	@Override
	public List<AdWaygg> findAdWayggByWayid(long wayid)
	{
		return jdbcTemplate.query("select * from es_ad_way_gg wg join es_ad_gg g on wg.ggid=g.id where wayid=?", new Object[]
		{ wayid }, new RowMapper()
		{

			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				AdWaygg wg = new AdWaygg();
				wg.setGgid(rs.getLong("wg.ggid"));
				wg.setWayid(rs.getLong("wg.wayid"));

				Adgg adgg = new Adgg();
				adgg.setId(rs.getLong("g.id"));
				adgg.setTitle(rs.getString("g.title"));
				adgg.setHydesc(rs.getString("g.hydesc"));
				adgg.setContent(rs.getString("g.content"));
				adgg.setOwner(rs.getLong("g.owner"));
				adgg.setImg(rs.getString("g.img"));
				adgg.setUrl(rs.getString("g.url"));
				adgg.setStarttime(rs.getTimestamp("g.starttime"));
				adgg.setEndtime(rs.getTimestamp("g.endtime"));
				wg.setGg(adgg);

				return wg;
			}
		});
	}

	@Override
	public int saveWaygg(final long wayid, final String[] ggid)
	{
		int[] rs = jdbcTemplate.batchUpdate("insert into es_ad_way_gg (ggid,wayid) values(?,?)", new BatchPreparedStatementSetter()
		{

			@Override
			public void setValues(PreparedStatement ps, int idx) throws SQLException
			{
				ps.setString(1, ggid[idx]);
				ps.setLong(2, wayid);

			}

			@Override
			public int getBatchSize()
			{
				return ggid.length;
			}
		});
		int i = 0;
		for (int j : rs)
		{
			if (j == 1)
			{
				i++;
			}
		}
		return i;
	}

	@Override
	public void updateWayggClean(long wayid)
	{
		jdbcTemplate.update("delete from es_ad_way_gg where wayid=?", new Object[]
		{ wayid });
	}

}
