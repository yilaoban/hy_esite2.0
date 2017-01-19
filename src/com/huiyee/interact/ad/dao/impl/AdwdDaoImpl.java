
package com.huiyee.interact.ad.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.interact.ad.dao.IAdwdDao;
import com.huiyee.interact.ad.model.Adwd;

public class AdwdDaoImpl extends AbstractDao implements IAdwdDao
{

	@Override
	public List<Adwd> findListByOwner(long owner, String wd, String type)
	{
		return getJdbcTemplate().query("select * from es_ad_wd where owner=? and name like ? and type=? order by id desc", new Object[]
		{ owner, "%" + wd + "%", type }, new RowMapper()
		{

			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				Adwd wd = new Adwd();
				wd.setId(rs.getLong("id"));
				wd.setName(rs.getString("name"));
				wd.setOwner(rs.getLong("owner"));
				wd.setCreatetime(rs.getTimestamp("createtime"));
				return wd;
			}
		});
	}

	@Override
	public Adwd findWdByName(long owner, String name, String type)
	{
		List<Adwd> list = getJdbcTemplate().query("select * from es_ad_wd where owner=? and name = ? and type=?", new Object[]
		{ owner, name, type }, new RowMapper()
		{

			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				Adwd wd = new Adwd();
				wd.setId(rs.getLong("id"));
				wd.setName(rs.getString("name"));
				wd.setOwner(rs.getLong("owner"));
				wd.setCreatetime(rs.getTimestamp("createtime"));
				return wd;
			}
		});
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public long saveWd(final String name, final long owner, final String type)
	{
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator()
		{

			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement("insert into es_ad_wd (name,createtime,owner,type)values(?,now(),?,?)", new String[]
				{ "id" });
				ps.setString(1, name);
				ps.setLong(2, owner);
				ps.setString(3, type);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}
}
