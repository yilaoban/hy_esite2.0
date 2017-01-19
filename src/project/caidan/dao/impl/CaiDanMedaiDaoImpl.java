package project.caidan.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import project.caidan.dao.ICaiDanMedaiDao;


public class CaiDanMedaiDaoImpl implements ICaiDanMedaiDao
{
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public long findEWMPageidByType(String type)
	{
		String sql = "select enid from caidan.cd_set where type = ?";
		Object[] params = {type};
		List<Long> list = jdbcTemplate.query(sql, params, new RowMapper()
		{
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				return rs.getLong("enid");
			}
		});
		if (list.size() > 0)
		{
			return list.get(0);
		}
		return 0;
	}
	
	
	
}
