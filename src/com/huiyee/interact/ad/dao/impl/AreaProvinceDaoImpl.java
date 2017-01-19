
package com.huiyee.interact.ad.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.model.AreaProvince;
import com.huiyee.interact.ad.dao.IAreaProvinceDao;

public class AreaProvinceDaoImpl extends AbstractDao implements IAreaProvinceDao
{

	@Override
	public List<AreaProvince> findList()
	{
		return getJdbcTemplate().query("select * from es_area_province", new RowMapper()
		{

			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				AreaProvince ap = new AreaProvince();
				ap.setId(rs.getLong("id"));
				ap.setProvince(rs.getString("province"));
				return ap;
			}
		});
	}

	@Override
	public List<AreaProvince> findList(String area)
	{
		return getJdbcTemplate().query("select * from es_area_province where province like ?", new Object[]
		{ "%" + area + "%" }, new RowMapper()
		{

			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				AreaProvince ap = new AreaProvince();
				ap.setId(rs.getLong("id"));
				ap.setProvince(rs.getString("province"));
				return ap;
			}
		});
	}

	@Override
	public AreaProvince findAreaByName(String area)
	{
		List<AreaProvince> list = getJdbcTemplate().query("select * from es_area_province where province = ?", new Object[]
		{ area }, new RowMapper()
		{

			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				AreaProvince ap = new AreaProvince();
				ap.setId(rs.getLong("id"));
				ap.setProvince(rs.getString("province"));
				return ap;
			}
		});
		return list.size() > 0 ? list.get(0) : null;
	}
}
