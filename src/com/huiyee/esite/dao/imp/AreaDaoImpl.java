package com.huiyee.esite.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.dao.IAreaDao;
import com.huiyee.esite.model.AreaCity;
import com.huiyee.esite.model.AreaProvince;

public class AreaDaoImpl extends AbstractDao implements IAreaDao {

	private static final String FIND_AREA_CITY="select * from hat_city where father = ?";
	@Override
	public List<AreaCity> findAreaCity(String provinceId) {
		Object[] params={provinceId};
		return getJdbcTemplate().query(FIND_AREA_CITY, params, new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				AreaCity ac = new AreaCity();
				ac.setId(rs.getLong("id"));
				ac.setCityId(rs.getString("cityid"));
				ac.setCity(rs.getString("city"));
				ac.setFather(rs.getString("father"));
				return ac;
			}
			
		});
	}

	private static final String FIND_AREA_PROVINCE="select * from hat_province";
	@Override
	public List<AreaProvince> findAreaProvince() {
		return getJdbcTemplate().query(FIND_AREA_PROVINCE, new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				AreaProvince ap = new AreaProvince();
				ap.setId(rs.getLong("id"));
				ap.setProvinceId(rs.getString("provinceId"));
				ap.setProvince(rs.getString("province"));
				return ap;
			}
			
		});
	}
	
}
