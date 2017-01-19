package com.huiyee.esite.fdao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.fdao.ISinaShareListRecordDao;
import com.huiyee.esite.model.SinaChecklistRecord;
import com.huiyee.esite.model.SinaUser;

public class SinaShareListRecordDaoImpl extends AbstractDao implements ISinaShareListRecordDao {

	private static final String FIND_RECORD_BY_SHAREID = "select * from es_feature_sina_share_record where shareid= ? order by id desc ";

	public List<SinaChecklistRecord> findRecordByShareId(long shareid) {
		return getJdbcTemplate().query(FIND_RECORD_BY_SHAREID, new Object[] { shareid }, new MyRowMapper());
	}

	class MyRowMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			SinaChecklistRecord record = new SinaChecklistRecord();
			record.setId(rs.getLong("id"));
			record.setShareid(rs.getLong("shareid"));
			record.setWbuid(rs.getLong("wbuid"));
			record.setWbid(rs.getLong("wbid"));
			record.setContent(rs.getString("content"));
			record.setImgPath(rs.getString("imgPath"));
			record.setCreatetime(rs.getTimestamp("createtime"));
			record.setRepostsCount(rs.getInt("repostsCount"));
			record.setCommentsCount(rs.getInt("commentsCount"));
			record.setAttitudesCount(rs.getInt("attitudesCount"));
			record.setStatus(rs.getString("status"));
			return record;
		}
	}

	private static final String UPDATE_STATUS_BY_ID = "update  es_feature_sina_share_record set status=? where id=? ";

	public void updateStatusbyid(SinaChecklistRecord r) {
		getJdbcTemplate().update(UPDATE_STATUS_BY_ID, new Object[] { r.getStatus(), r.getId() });
	}

	private static final String FIND_CMP_RECORD_BY_PAGEID = "select * from es_feature_sina_share_check_list relation join es_feature_sina_share_record record on relation.shareid=record.shareid join es_feature_sina_user user on record.wbuid=user.wbuid where relation.pageid=? and record.status='CMP'";

	public List<SinaChecklistRecord> findCMPRecordByPageId(long pageid) {
		return getJdbcTemplate().query(FIND_CMP_RECORD_BY_PAGEID, new Object[] { pageid }, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				SinaChecklistRecord scr = new SinaChecklistRecord();
				SinaUser user = new SinaUser();
				user.setWbuid(rs.getLong("user.wbuid"));
				user.setNickname(rs.getString("user.nickname"));
				user.setImageurl(rs.getString("user.imageurl"));
				scr.setUser(user);
				scr.setContent(rs.getString("record.content"));
				scr.setImgPath(rs.getString("record.imgPath"));
				scr.setCreatetime(rs.getTimestamp("record.createtime"));
				scr.setAttitudesCount(rs.getInt("record.attitudesCount"));
				scr.setCommentsCount(rs.getInt("record.commentsCount"));
				scr.setRepostsCount(rs.getInt("record.repostsCount"));
				return scr;
			}
		});
	}
	
	private static final String FIND_CMP_RECORD_BY_SHAREID = "select * from es_feature_sina_share_check_list relation join es_feature_sina_share_record record on relation.shareid=record.shareid join es_feature_sina_user user on record.wbuid=user.wbuid where relation.shareid=? and record.status='CMP'";
	@Override
	public List<SinaChecklistRecord> findCMPRecordByShareId(long id) {
		return getJdbcTemplate().query(FIND_CMP_RECORD_BY_SHAREID, new Object[] { id }, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				SinaChecklistRecord scr = new SinaChecklistRecord();
				SinaUser user = new SinaUser();
				user.setWbuid(rs.getLong("user.wbuid"));
				user.setNickname(rs.getString("user.nickname"));
				user.setImageurl(rs.getString("user.imageurl"));
				scr.setUser(user);
				scr.setContent(rs.getString("record.content"));
				scr.setImgPath(rs.getString("record.imgPath"));
				scr.setCreatetime(rs.getTimestamp("record.createtime"));
				scr.setAttitudesCount(rs.getInt("record.attitudesCount"));
				scr.setCommentsCount(rs.getInt("record.commentsCount"));
				scr.setRepostsCount(rs.getInt("record.repostsCount"));
				scr.setMimgPath(rs.getString("record.mimgPath"));
				scr.setSimgPath(rs.getString("record.simgPath"));
				return scr;
			}
		});
	}

	private static final String FIND_NO_CHECK_RECORD_BY_SHATEID = "select * from  es_feature_sina_share_record record join es_feature_sina_user user on record.wbuid=user.wbuid where shareid=? order by record.id desc limit ?";

	@Override
	public List<SinaChecklistRecord> findNoCheckRecordByShareid(long id,int size) {
		return getJdbcTemplate().query(FIND_NO_CHECK_RECORD_BY_SHATEID, new Object[] { id,size }, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				SinaChecklistRecord scr = new SinaChecklistRecord();
				SinaUser user = new SinaUser();
				user.setWbuid(rs.getLong("user.wbuid"));
				user.setNickname(rs.getString("user.nickname"));
				user.setImageurl(rs.getString("user.imageurl"));
				scr.setUser(user);
				scr.setContent(rs.getString("record.content"));
				scr.setImgPath(rs.getString("record.imgPath"));
				scr.setCreatetime(rs.getTimestamp("record.createtime"));
				scr.setAttitudesCount(rs.getInt("record.attitudesCount"));
				scr.setCommentsCount(rs.getInt("record.commentsCount"));
				scr.setRepostsCount(rs.getInt("record.repostsCount"));
				scr.setMimgPath(rs.getString("record.mimgPath"));
				scr.setSimgPath(rs.getString("record.simgPath"));
				return scr;
			}
		});
	}
}
