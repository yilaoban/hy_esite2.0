package com.huiyee.esite.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.dao.IAccountHideDao;

public class AccountHideDaoImpl  extends AbstractDao implements IAccountHideDao{

	@Override
	public List<Long> findHidIds(long ownerid, long accountid, String type) {
		return getJdbcTemplate().query("select entityid from es_account_hide where ownerid=? and accountid=? and type=?", new Object[]{ownerid,accountid,type},new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getLong("entityid");
			}
		});
	}
}
