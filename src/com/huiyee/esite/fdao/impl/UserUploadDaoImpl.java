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
import com.huiyee.esite.fdao.IUserUploadDao;
import com.huiyee.esite.model.UserUpload;

public class UserUploadDaoImpl extends AbstractDao implements IUserUploadDao {

	private static final String ADD_FEATURE_USER_UPLOAD="insert into esite.es_feature_user_upload(pageid,name) value(?,now())";
	@Override
	public long addFeatureUserUpload(final long pageid) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(ADD_FEATURE_USER_UPLOAD, new String[] { "id" });
				ps.setLong(1, pageid);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}
	
	private static final String FIND_USER_UPLOAD="select * from esite.es_feature_user_upload where id = ?";
	@Override
	public UserUpload findUserUpload(long fid) {
		Object[] params={fid};
		List<UserUpload> list = getJdbcTemplate().query(FIND_USER_UPLOAD, params, new UserUploadRowmapper());
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	class UserUploadRowmapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserUpload uu = new UserUpload();
			uu.setId(rs.getLong("id"));
			uu.setPageid(rs.getLong("pageid"));
			uu.setContent(rs.getString("content"));
			uu.setName(rs.getString("name"));
			uu.setMheight(rs.getInt("mheight"));
			uu.setMwidth(rs.getInt("mwidth"));
			uu.setSheight(rs.getInt("sheight"));
			uu.setSwidth(rs.getInt("swidth"));
			return uu;
		}
		
	}

	private static final String UPDATE_FEATURE_USER_UPLOAD="update esite.es_feature_user_upload set content = ? ,mwidth =? , mheight =? ,swidth=?, sheight=? where id = ?";
	@Override
	public int updateFeatureUserUpload(UserUpload userUpload) {
		Object[] params = { userUpload.getContent(), userUpload.getMwidth(),
				userUpload.getMheight(), userUpload.getSwidth(),
				userUpload.getSheight(), userUpload.getId() };
		return getJdbcTemplate().update(UPDATE_FEATURE_USER_UPLOAD, params);
	}

}
