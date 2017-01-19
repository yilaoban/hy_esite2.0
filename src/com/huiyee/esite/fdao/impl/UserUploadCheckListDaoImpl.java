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
import com.huiyee.esite.fdao.IUserUploadCheckListDao;
import com.huiyee.esite.model.UserUpload;
import com.huiyee.esite.model.UserUploadCheckList;

public class UserUploadCheckListDaoImpl extends AbstractDao implements IUserUploadCheckListDao {
	private static final String SAVE_CHECK_List = "insert into es_feature_user_upload_check_list (pageid,createtime,status) values (?,now(),'CMP')";

	public long saveCheckList(final long pageid) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(SAVE_CHECK_List, new String[] { "id" });
				int i = 1;
				ps.setLong(i++, pageid);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	private static final String FIND_UPLOAD_BY_FID = "select * from es_feature_user_upload_check_list where id =? ";

	public UserUploadCheckList findUploadId(long fid) {
		List<UserUploadCheckList> list = getJdbcTemplate().query(FIND_UPLOAD_BY_FID, new Object[] { fid }, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				UserUploadCheckList up = new UserUploadCheckList();
				up.setId(rs.getLong("id"));
				up.setPageid(rs.getLong("pageid"));
				up.setUploadid(rs.getLong("uploadid"));
				up.setCreatetime(rs.getTimestamp("createtime"));
				up.setStatus(rs.getString("status"));
				return up;
			}
		});
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	private static final String FIND_USER_UPLOAD_LIST_BY_PAGEID = " select * from es_feature_user_upload where pageid=? ";

	public List<UserUpload> findUserUploadListByPageid(long pageid) {
		return getJdbcTemplate().query(FIND_USER_UPLOAD_LIST_BY_PAGEID, new Object[] { pageid }, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				UserUpload up = new UserUpload();
				up.setId(rs.getLong("id"));
				up.setName(rs.getString("name"));
				up.setPageid(rs.getLong("pageid"));
				up.setContent(rs.getString("content"));
				return up;
			}
		});
	}

	private static final String UPDATE_UPLOADID = "update es_feature_user_upload_check_list set  uploadid=? where id= ? ";

	public void updateUploadid(long fid, long uploadid) {
		getJdbcTemplate().update(UPDATE_UPLOADID, new Object[] { uploadid, fid });
	}
}
