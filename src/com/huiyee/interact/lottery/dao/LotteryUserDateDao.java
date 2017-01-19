package com.huiyee.interact.lottery.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.huiyee.interact.lottery.model.LotteryUserDate;

public class LotteryUserDateDao implements ILotteryUserDateDao
{
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void addUserDate(long lid, long wbuid,int type)
	{
		jdbcTemplate.update("insert into es_interact_lottery_user_date (wbuid,type,lid,hyday,num) values (?,?,?,now(),1) on duplicate key update num=num+1", new Object[]{wbuid,type,lid});
	}

	@Override
	public LotteryUserDate findUserDate(long lid, long wbuid,int type)
	{
		List<LotteryUserDate> ls = jdbcTemplate.query("select * from es_interact_lottery_user_date where lid=? and wbuid=? and type=? and hyday=DATE_FORMAT(NOW(),'%Y-%m-%d')", new Object[]
		{ lid, wbuid ,type}, new RowMapper()
		{
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				LotteryUserDate l = new LotteryUserDate();
				l.setId(rs.getLong("id"));
				l.setHyday(rs.getDate("hyday"));
				l.setLid(rs.getLong("lid"));
				l.setNum(rs.getInt("num"));
				l.setWbuid(rs.getLong("wbuid"));
				return l;
			}
		});
		if (ls != null && ls.size() > 0)
		{
			return ls.get(0);
		}

		return null;
	}
}
