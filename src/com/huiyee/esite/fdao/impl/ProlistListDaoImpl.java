package com.huiyee.esite.fdao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.fdao.IProlistListDao;
import com.huiyee.esite.util.DateUtil;

public class ProlistListDaoImpl extends AbstractDao implements IProlistListDao {
	private static final String FIND_OWNER_BY_FID = "select site.ownerid  from es_feature_prolist_list fpl join es_page page on fpl.pageid=page.id  join es_site site on site.id=page.siteid where fpl.id=?";

	public long findOwnerByFid(long fid) {
		return getJdbcTemplate().queryForLong(FIND_OWNER_BY_FID, new Object[] { fid });
	}

	private static final String ADD_PROLIST_LIST = "insert into es_feature_prolist_list (pageid,name) value (?,now())";

	public long addProlistlist(final long pageid) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(ADD_PROLIST_LIST, new String[] { "id" });
				int i = 1;
				ps.setLong(i++, pageid);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}
}
