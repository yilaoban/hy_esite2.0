package com.huiyee.esite.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.dao.ISinaUserAppDao;

public class SinaUserAppDaoImpl extends AbstractDao implements ISinaUserAppDao {

	@Override
	public List<String> findNewToken()
	{
		return getJdbcTemplate().query("select token from esite.es_feature_sina_user_app where tokenendtime>now() limit 1000",  new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				return rs.getString("token");
			}
			
		});
	}



}
