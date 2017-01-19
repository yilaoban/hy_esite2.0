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
import com.huiyee.esite.fdao.IUserUploadRecommentListDao;
import com.huiyee.esite.model.FeatureUploadRecommentList;
import com.huiyee.esite.model.UserUpload;
import com.huiyee.esite.model.UserUploadRecord;

public class UserUploadRecommentListDaoImpl extends AbstractDao implements IUserUploadRecommentListDao {
    private static final String SAVE_FETRUE_USER_UPLOAD_RECOMMENT_LIST = "insert into esite.es_feature_user_upload_recommendlist (pageid) values(?)";

    public long saveFetureUserUploadRecommentList(final FeatureUploadRecommentList recommentList) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
                PreparedStatement ps = arg0.prepareStatement(SAVE_FETRUE_USER_UPLOAD_RECOMMENT_LIST,
                        new String[] { "id" });
                int i = 1;
                ps.setLong(i++, recommentList.getPageid());
                return ps;
            }
        }, keyHolder);
        long id = keyHolder.getKey().intValue();
        return id;
    }

    private static final String FIND_UPLOAD_LIST_RECORD = "select recordid from esite.es_feature_user_upload_list_record where listid=? and status!='DEL' ";

    @Override
    public List<Long> findRecordids(long fid) {
        Object[] args = { fid };
        List<Long> list = getJdbcTemplate().queryForList(FIND_UPLOAD_LIST_RECORD, args, Long.class);
        return list;
    }

    private static final String FIND_USER_UPLOAD_LIST_CHECKED = "select * from esite.es_feature_user_upload ";

    @Override
    public List<UserUpload> findUserUploadList() {
        List<UserUpload> list = getJdbcTemplate().query(FIND_USER_UPLOAD_LIST_CHECKED, new UserUploadCheckRowmapper());
        return list;
    }

    private static final String FIND_USER_UPLOAD_BY_FID = "select u.* from esite.es_feature_user_upload_recommendlist r join esite.es_feature_user_upload u on r.uploadid=u.id where r.id=?";

    @Override
    public UserUpload findUserUploadListByFid(long fid) {
        Object[] args = { fid };
        List<UserUpload> list = getJdbcTemplate().query(FIND_USER_UPLOAD_BY_FID, args, new UserUploadCheckRowmapper());
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    private static final String FIND_FEATURE_UPLOAD_RECOMMENT = "select * from esite.es_feature_user_upload_recommendlist where id=?";

    @Override
    public FeatureUploadRecommentList findUploadRecommentList(long fid) {
        List<FeatureUploadRecommentList> list = getJdbcTemplate().query(FIND_FEATURE_UPLOAD_RECOMMENT,
                new FeatureUploadRecommentRowmapper());
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    private static final String UPDATE_FEATURE_USER_UPLOAD_RECOMMENT = "update esite.es_feature_user_upload_recommendlist set uploadid=? where id = ?";

    @Override
    public int updateFeatureUserUploadRecomment(long fid, long uploadid) {
        if(uploadid<=0){
            Object[] params = { null, fid };
            return getJdbcTemplate().update(UPDATE_FEATURE_USER_UPLOAD_RECOMMENT, params);
        }else{
            Object[] params = { uploadid, fid };
            return getJdbcTemplate().update(UPDATE_FEATURE_USER_UPLOAD_RECOMMENT, params);
        }
        
    }

    @Override
    public int deleteUpLoadListRecord(String ids, long fid) {
        String sql = "update esite.es_feature_user_upload_list_record set status='DEL' where recordid in (" + ids
                + ") and listid=" + fid;
        return getJdbcTemplate().update(sql);
    }

    class UserUploadCheckRowmapper implements RowMapper {

        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserUpload uu = new UserUpload();
            ;
            uu.setId(rs.getLong("id"));
            uu.setPageid(rs.getLong("pageid"));
            uu.setContent(rs.getString("content"));
            return uu;
        }

    }

    private static final String FIND_UPLOAD_RECORD_CHECKED = "select r.content,r.smallimg,r.id,(select idx from esite.es_feature_user_upload_list_record lr where lr.recordid=r.id and lr.status!='DEL' and lr.listid=? ) as idx from esite.es_feature_user_upload_record r where r.uploadid=? and r.status ='CMP' order by r.id asc";

    public List<UserUploadRecord> findUploadRecordCheckRecomment(long fid, long uploadid) {
        return getJdbcTemplate().query(FIND_UPLOAD_RECORD_CHECKED, new Object[] { fid, uploadid },
                new CommentListRowMapper());
    }
    private static final String FIND_UPLOAD_RECORD_CHECKED_BY_UID = "select u.content, u.smallimg,u.midimg,u.bigimg,u.id from esite.es_feature_user_upload_list_record r join esite.es_feature_user_upload_record u on u.id=r.recordid where r.listid=? and r.status!='DEL' and u.status ='CMP' order by r.idx,u.id asc";

    public List<UserUploadRecord> findUploadRecordByFid(long fid) {
        return getJdbcTemplate().query(FIND_UPLOAD_RECORD_CHECKED_BY_UID, new Object[] { fid},
                new CommentListUidRowMapper());
    }

    class CommentListRowMapper implements RowMapper {
        public Object mapRow(ResultSet rs, int arg1) throws SQLException {
            UserUploadRecord record = new UserUploadRecord();
            record.setId(rs.getLong("id"));
            record.setSmallimg(rs.getString("smallimg"));
            record.setContent(rs.getString("content"));
            record.setIdx(rs.getString("idx"));
            return record;
        }
    }
    class CommentListUidRowMapper implements RowMapper {
        public Object mapRow(ResultSet rs, int arg1) throws SQLException {
            UserUploadRecord record = new UserUploadRecord();
            record.setId(rs.getLong("id"));
            record.setSmallimg(rs.getString("smallimg"));
            record.setBigimg(rs.getString("bigimg"));
            record.setMidimg(rs.getString("midimg"));
            record.setContent(rs.getString("content"));
            return record;
        }
    }

    class FeatureUploadRecommentRowmapper implements RowMapper {

        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserUpload uu = new UserUpload();
            uu.setId(rs.getLong("id"));
            uu.setPageid(rs.getLong("pageid"));
            uu.setName(rs.getString("name"));
            return uu;
        }
    }

    private static final String SAVE_FETRUE_USER_UPLOAD_LIST_RECORD = "insert into esite.es_feature_user_upload_list_record (listid,recordid,createtime,idx) values(?,?,now(),?) on duplicate key update status='NOR' ,idx=? ";

    public int saveFeatureUserUploadlistRecord(final long listid, final long recordid,final int idx) {
        Object[] params = { listid, recordid,idx,idx};
        return getJdbcTemplate().update(SAVE_FETRUE_USER_UPLOAD_LIST_RECORD, params);
    }

}
