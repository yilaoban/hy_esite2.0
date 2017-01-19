package com.huiyee.esite.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.IOwnerBalanceSetDao;
import com.huiyee.esite.model.OwnerBalanceSet;

public class OwnerBalanceSetDao implements IOwnerBalanceSetDao
{
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public OwnerBalanceSet findRuleById(long id)
	{
		List<OwnerBalanceSet> ls = jdbcTemplate.query("select * from es_hy_user_balance_set where owner=?", new Object[]
		{ id }, new RowMapper()
		{
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				OwnerBalanceSet obs = new OwnerBalanceSet();
				obs.setId(rs.getLong("id"));
				obs.setUpnum(rs.getInt("upnum"));
				obs.setOwner(rs.getLong("owner"));
				obs.setComnum(rs.getInt("comnum"));
				obs.setDownum(rs.getInt("downum"));
				obs.setNewnum(rs.getInt("newnum"));
				obs.setSgznum(rs.getInt("sgznum"));
				obs.setTopnum(rs.getInt("topnum"));
				obs.setTopicnum(rs.getInt("topicnum"));
				obs.setSharenum(rs.getInt("sharenum"));
				obs.setSsharenum(rs.getInt("ssharenum"));
				obs.setOcnum(rs.getInt("ocnum"));
				obs.setYynum(rs.getInt("yynum"));
				obs.setYypjnum(rs.getInt("yypjnum"));
				obs.setOcxfnum(rs.getInt("ocxfnum"));
				return obs;
			}
		});
		if (ls.size() > 0)
		{
			return ls.get(0);
		}
		return null;
	}
}
