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
import com.huiyee.esite.fdao.IUserUploadRecordDao;
import com.huiyee.esite.model.UserUploadRecord;

public class UserUploadRecordDaoImpl extends AbstractDao implements IUserUploadRecordDao {
	private static final String FIND_UPLOAD_RECORD_BY_UPLOADID = "select * from es_feature_user_upload_record record join es_user_info user on record.uid=user.id where uploadid=? and status = ? order by record.id desc  ";

	public List<UserUploadRecord> findUploadRecor(long uploadid,String status) {
		return getJdbcTemplate().query(FIND_UPLOAD_RECORD_BY_UPLOADID, new Object[] { uploadid ,status}, new MyRowMapper());
	}
	
	private static final String FIND_ALL_UPLOAD_RECORD = "select * from es_feature_user_upload_record record join es_user_info user on record.uid=user.id where uploadid=? order by record.id desc  ";
	public List<UserUploadRecord> findAllUploadRecor(long uploadid) {
		return getJdbcTemplate().query(FIND_ALL_UPLOAD_RECORD, new Object[] { uploadid}, new MyRowMapper());
	}

	class MyRowMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			UserUploadRecord record = new UserUploadRecord();
			record.setId(rs.getLong("record.id"));
			record.setUid(rs.getLong("record.uid"));
			record.setNickName(rs.getString("nickname"));
			record.setUploadid(rs.getLong("uploadid"));
			record.setSmallimg(rs.getString("smallimg"));
			record.setMidimg(rs.getString("midimg"));
			record.setBigimg(rs.getString("bigimg"));
			record.setContent(rs.getString("content"));
			record.setUploadtime(rs.getTimestamp("uploadtime"));
			record.setPasstime(rs.getTimestamp("passtime"));
			record.setStatus(rs.getString("status"));
			return record;
		}
	}

	private static final String UPDATE_STATUS_BY_ID = "update es_feature_user_upload_record set status = ? where id=? ";

	public void updateStatusbyid(UserUploadRecord r) {
		getJdbcTemplate().update(UPDATE_STATUS_BY_ID, new Object[] { r.getStatus(), r.getId() });
	}

	private static final String ADD_USER_UPLOAD_RECORD="insert into esite.es_feature_user_upload_record(uid,uploadid,smallimg,midimg,bigimg,content,uploadtime,status,ip,terminal,source,pageid) values (?,?,?,?,?,?,now(),'EDT',?,?,?,?)";
	@Override
	public long addUserUploadRecord(final UserUploadRecord record,final String ip,final String terminal,final String source,final long pageid) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(ADD_USER_UPLOAD_RECORD, new String[] { "id" });
				ps.setLong(1, record.getUid());
				ps.setLong(2, record.getUploadid());
				ps.setString(3, record.getSmallimg());
				ps.setString(4, record.getMidimg());
				ps.setString(5, record.getBigimg());
				ps.setString(6, record.getContent());
				ps.setString(7, ip);
				ps.setString(8, terminal);
				ps.setString(9, source);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}
}
