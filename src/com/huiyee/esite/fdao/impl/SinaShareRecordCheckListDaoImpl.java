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
import com.huiyee.esite.fdao.ISinaShareRecordCheckListDao;
import com.huiyee.esite.model.SinaChecklistRecord;
import com.huiyee.esite.model.SinaShareRecord;
import com.huiyee.esite.model.SinaShareRecordCategory;
import com.huiyee.esite.model.SinaUser;

public class SinaShareRecordCheckListDaoImpl extends AbstractDao implements
		ISinaShareRecordCheckListDao {

	private static final String ADD_CHECK_LIST="insert into esite.es_feature_sina_share_record_check_list(pageid,createtime) values(?,now())";
	@Override
	public long saveSinaShareReocrdCheckList(final long pageid) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(ADD_CHECK_LIST, new String[] { "id" });
				ps.setLong(1, pageid);
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}
	
	private static final String FIND_SINA_CHECK_LIST_RECORD="select * from esite.es_feature_sina_share_record_check_list cl join esite.es_feature_sina_share s on cl.pageid = s.pageid join esite.es_feature_sina_share_record r on s.id = r.shareid join esite.es_feature_sina_user u on u.wbuid = r.wbuid left outer join esite.es_feature_sina_share_record_checklist_list l on l.recordid = r.id where cl.id = ? and r.status='CMP' and l.categoryid is null";
	@Override
	public List<SinaChecklistRecord> findSinaCheckListRecordByFid(long fid) {
		Object[] params={fid};
		return getJdbcTemplate().query(FIND_SINA_CHECK_LIST_RECORD, params, new SinaChecklistRecordRowmapper());
	}
	
	class SinaChecklistRecordRowmapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			SinaChecklistRecord scr = new SinaChecklistRecord();
			SinaUser user = new SinaUser();
			user.setWbuid(rs.getLong("u.wbuid"));
			user.setNickname(rs.getString("u.nickname"));
			user.setImageurl(rs.getString("u.imageurl"));
			scr.setUser(user);
			scr.setId(rs.getLong("r.id"));
			scr.setWbid(rs.getLong("r.wbid"));
			scr.setContent(rs.getString("r.content"));
			scr.setImgPath(rs.getString("r.imgPath"));
			scr.setCreatetime(rs.getTimestamp("r.createtime"));
			scr.setAttitudesCount(rs.getInt("r.attitudesCount"));
			scr.setCommentsCount(rs.getInt("r.commentsCount"));
			scr.setRepostsCount(rs.getInt("r.repostsCount"));
			scr.setMimgPath(rs.getString("r.mimgPath"));
			scr.setSimgPath(rs.getString("r.simgPath"));
			scr.setCategoryid(rs.getLong("l.categoryid"));
			scr.setIdx(rs.getInt("l.idx"));
			return scr;
		}

	}

	private static final String FIND_SINA_SHARE_RECORD_CATEGORY="select * from esite.es_feature_sina_share_record_category where category_listid = ? order by idx";
	@Override
	public List<SinaShareRecordCategory> findSinaShareRecordCategoryByFid(
			long fid) {
		Object[] params={fid};
		return getJdbcTemplate().query(FIND_SINA_SHARE_RECORD_CATEGORY, params, new SinaShareRecordCategoryRowmapper());
	}
	
	class SinaShareRecordCategoryRowmapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			SinaShareRecordCategory c = new SinaShareRecordCategory();
			c.setId(rs.getLong("id"));
			c.setName(rs.getString("name"));
			c.setListid(rs.getLong("category_listid"));
			c.setIdx(rs.getInt("idx"));
			c.setCreatetime(rs.getTimestamp("createtime"));
			return c;
		}
		
	}

	private static final String FIND_SINA_SHARE_RECORD_CATEGORY_RECORD="select * from esite.es_feature_sina_share_record_checklist_list l join esite.es_feature_sina_share_record r on l.recordid = r.id join esite.es_feature_sina_user u on r.wbuid = u.wbuid where l.categoryid = ?";
	@Override
	public List<SinaChecklistRecord> findSinaChecklistRecordByCategoryId(
			long cid) {
		Object[] params={cid};
		return getJdbcTemplate().query(FIND_SINA_SHARE_RECORD_CATEGORY_RECORD, params, new SinaChecklistRecordRowmapper());
	}
	
	private static final String SAVE_SINA_SHARE_RECORD_CHECK_LIST="insert into esite.es_feature_sina_share_record_checklist_list(recordid,categoryid,idx,createtime) values(?,?,?,now())";
	@Override
	public int saveSinaShareRecordCheckList(long recordid, long categoryid,
			int idx) {
		Object[] params={recordid,categoryid,idx};
		return getJdbcTemplate().update(SAVE_SINA_SHARE_RECORD_CHECK_LIST,params);
	}
	
	private static final String DELETE_SINA_SHARE_RECORD_CHECK_LIST="delete from esite.es_feature_sina_share_record_checklist_list where categoryid in (select c.id from esite.es_feature_sina_share_record_category c join esite.es_feature_sina_share_record_check_list l on c.category_listid = l.id where l.id = ?)";
	@Override
	public int deleteSinaShareRecordChekcList(long fid) {
		Object[] params={fid};
		return getJdbcTemplate().update(DELETE_SINA_SHARE_RECORD_CHECK_LIST, params);
	}

}
