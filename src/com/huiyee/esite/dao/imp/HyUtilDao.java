package com.huiyee.esite.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.IHyUtilDao;
import com.huiyee.esite.model.OwnerSetting;
import com.huiyee.esite.service.IMemCacheAble;

public class HyUtilDao implements IHyUtilDao, IMemCacheAble
{
	private JdbcTemplate jdbcTemplate;
	private int cacheTime;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public long findOwnerByName(String oname)
	{
		List<Long> ls = jdbcTemplate.query("select id from esite.es_owner where entity=?", new Object[]
		{ oname }, new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				return rs.getLong("id");
			}

		});
		if (ls != null && ls.size() > 0)
		{
			return ls.get(0);
		}
		return 0;
	}

	public int getCacheTime()
	{
		return cacheTime;
	}

	public void setCacheTime(int cacheTime)
	{
		this.cacheTime = cacheTime;
	}

	@Override
	public List<OwnerSetting> findOwnerSetting()
	{
		return jdbcTemplate.query("select * from esite.es_owner_setting ", new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				OwnerSetting os=new OwnerSetting();
				os.setOdomain(rs.getString("odomain"));
				os.setOwnerid(rs.getLong("ownerid"));
				os.setWxappid(rs.getString("wxappid"));
				os.setWxsecret(rs.getString("wxsecret"));
				return os;
			}

		});

	}

}
