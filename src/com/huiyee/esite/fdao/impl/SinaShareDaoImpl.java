package com.huiyee.esite.fdao.impl;

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
import com.huiyee.esite.fdao.ISinaShareDao;
import com.huiyee.esite.model.SinaShare;

public class SinaShareDaoImpl extends AbstractDao implements ISinaShareDao {

	private static final String ADD_FEATURE_SINA_SHARE="insert into esite.es_feature_sina_share(pageid,name) values (?,now())";
	@Override
	public long addFeatureSinaShare(final long pageid) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(ADD_FEATURE_SINA_SHARE, new String[] { "id" });
				ps.setLong(1, pageid);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}
	
	private static final String FIND_SINA_SHARE_BY_ID="select * from esite.es_feature_sina_share where id = ?";
	@Override
	public SinaShare findFeatureSinaShareById(long id) {
		Object[] params={ id };
		List<SinaShare> list = getJdbcTemplate().query(FIND_SINA_SHARE_BY_ID,params,new SinaShareRowmapper());
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	class SinaShareRowmapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			SinaShare ss = new SinaShare();
			ss.setId(rs.getLong("id"));
			ss.setPageid(rs.getLong("pageid"));
			ss.setName(rs.getString("name"));
			ss.setStatus(rs.getString("status"));
			ss.setLastupdatetime(rs.getTimestamp("lastupdatetime"));
			ss.setSharecount(rs.getInt("sharecount"));
			ss.setSoauthurl(rs.getString("soauthurl"));
			ss.setCoauthurl(rs.getString("coauthurl"));
			return ss;
		}
		
	}

	private static final String UPDATE_SINA_SHARE="update esite.es_feature_sina_share set name = ?,soauthurl = ?,coauthurl = ? where id = ?";
	@Override
	public int updateFeatureSinaShare(SinaShare share) {
		Object[] params={share.getName(),share.getSoauthurl(),share.getCoauthurl(),share.getId()};
		return getJdbcTemplate().update(UPDATE_SINA_SHARE, params);
	}
	
	 private static final String FIND_SINA_SHARE_BY_PAGEID = "select * from es_feature_sina_share where pageid=? order by id desc ";

	 public List<SinaShare> findSinaShareListByPageId(long pageid)
	    {
		return getJdbcTemplate().query(FIND_SINA_SHARE_BY_PAGEID, new Object[] { pageid }, new SinaShareRowmapper());
	    }

}
