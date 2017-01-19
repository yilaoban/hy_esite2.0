package com.huiyee.esite.dao.imp;

import org.springframework.jdbc.core.JdbcTemplate;
import com.huiyee.esite.dao.IOwnerBalanceDao;

public class OwnerBalanceDao implements IOwnerBalanceDao
{
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void addLessBalance(long hyuid, int balance)
	{
		jdbcTemplate.update("insert into es_hy_user_balance (hyuid,used,lastusetime) values (?,?,now()) on duplicate key update used=used+?,lastusetime=now()", new Object[]
		{ hyuid, balance, balance });
	}

	@Override
	public void addMoreBalance(long hyuid, int balance)
	{
		jdbcTemplate.update("insert into es_hy_user_balance (hyuid,total,lastaddtime) values (?,?,now()) on duplicate key update total=total+?,lastaddtime=now()", new Object[]
		{ hyuid, balance, balance });
	}
	
	@Override
	public void addLessRmbBalance(long hyuid, int balance)
	{
		jdbcTemplate.update("insert into es_hy_user_balance (hyuid,rmbused,rmblastusetime) values (?,?,now()) on duplicate key update rmbused=rmbused+?,rmblastusetime=now()", new Object[]
		{ hyuid, balance, balance });
	}

	@Override
	public void addMoreRmbBalance(long hyuid, int balance)
	{
		jdbcTemplate.update("insert into es_hy_user_balance (hyuid,rmbtotal,rmblastaddtime) values (?,?,now()) on duplicate key update rmbtotal=rmbtotal+?,rmblastaddtime=now()", new Object[]
		{ hyuid, balance, balance });
	}
	
	@Override
	public int findJFByUser(long hyuid)
	{
		try
		{
			return jdbcTemplate.queryForInt("select total-used from es_hy_user_balance where hyuid=?", new Object[]
			{ hyuid });
		}
		catch (Exception e)
		{
		}
		return 0;
	}

	@Override
	public void updatePreUsedByUser(long hyuid, int preused)
	{
		jdbcTemplate.update("update es_hy_user_balance set used=used+? where hyuid=?", new Object[]
		{ preused, hyuid });
	}

	@Override
	public int findRmbByUser(long uid)
	{
		try
		{
			return jdbcTemplate.queryForInt("select rmbtotal-rmbused from es_hy_user_balance where hyuid=?", new Object[]
			{ uid });
		}
		catch (Exception e)
		{
		}
		return 0;
	}

	@Override
	public int findTotalRmbByUser(long uid) {
		try
		{
			return jdbcTemplate.queryForInt("select rmbtotal from es_hy_user_balance where hyuid=?", new Object[]
			{ uid });
		}
		catch (Exception e)
		{
		}
		return 0;
	}

	@Override
	public int findRmbusedByUser(long uid) {
		try
		{
			return jdbcTemplate.queryForInt("select rmbused from es_hy_user_balance where hyuid=?", new Object[]
			{ uid });
		}
		catch (Exception e)
		{
		}
		return 0;
	}
}
