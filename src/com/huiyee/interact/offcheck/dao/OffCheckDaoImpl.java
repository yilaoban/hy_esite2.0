
package com.huiyee.interact.offcheck.dao;

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
import com.huiyee.interact.offcheck.model.OffCheck;

public class OffCheckDaoImpl extends AbstractDao implements IOffCheckDao
{

	@Override
	public OffCheck findStoreCrmByOwner(long owner)
	{
		List<OffCheck> list = getJdbcTemplate().query("select * from es_off_check where owner=?", new Object[]
		{ owner }, new RowMapper()
		{

			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				OffCheck sc = new OffCheck();
				sc.setId(rs.getLong("id"));
				sc.setAptid(rs.getLong("aptid"));
				sc.setOwner(rs.getLong("owner"));
				return sc;
			}
		});
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public long saveScrm(final OffCheck crm)
	{
		final String sql = "insert into es_off_check(owner,aptid) values(?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator()
		{

			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(sql, new String[]
				{ "id" });
				int i = 1;
				ps.setLong(i++, crm.getOwner());
				ps.setLong(i++, crm.getAptid());
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	@Override
	public int findUserAptRecord(long owner, long wxuid)
	{
		return getJdbcTemplate().queryForInt("select count(r.id) from esite.es_feature_interact_apt_record r,esite.es_off_check c where r.entityid=? and r.aptid=c.aptid and r.type=1 and c.owner=?", new Object[]
				{ wxuid,owner });
	}
}
