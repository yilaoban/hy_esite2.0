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
import com.huiyee.esite.fdao.IVideoDao;
import com.huiyee.esite.model.FeatureVideoList;
import com.huiyee.esite.model.Video;

public class VideoDaoImpl extends AbstractDao implements IVideoDao {

	private static final String FIND_VIDEO_BY_ID="select vv.id vlvid,vv.idx, cv.id, cv.html,cv.picurl,cv.title,cv.videourl from esite.es_feature_videolist_video vv join esite.es_content_video cv on vv.vid=cv.id where vv.status!='DEL' and vv.vlid = ? order by vv.idx,cv.id asc";
	@Override
	public List<Video> findVideoById(long id) {
		Object[] params={id};
		List<Video> list = getJdbcTemplate().query(FIND_VIDEO_BY_ID, params, new VideoRowmapper());
		return list;
	}
	
	class VideoRowmapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		    Video v = new Video();
		    v.setId(rs.getLong("id"));
		    v.setVlvid(rs.getLong("vlvid"));
		    v.setIdx(rs.getInt("idx"));
		    v.setHtml(rs.getString("html"));
		    v.setPicurl(rs.getString("picurl"));
		    v.setTitle(rs.getString("title"));
		    v.setVideourl(rs.getString("videourl"));
			return v;
		}
	}
	class VideoRowmapper1 implements RowMapper{

        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            Video v = new Video();
            v.setId(rs.getLong("id"));
            v.setHtml(rs.getString("html"));
            v.setPicurl(rs.getString("picurl"));
            v.setTitle(rs.getString("title"));
            v.setVideourl(rs.getString("videourl"));
            return v;
        }
    }
	
	   private static final String FIND_VIDEO_BY_OWNER="select id, html,picurl,title,videourl from esite.es_content_video  where ownerid = ? and status!='DEL' order by id desc";
	    @Override
	public List<Video> findVideoByOwner(long owner) {
	        Object[] params={owner};
	        List<Video> list = getJdbcTemplate().query(FIND_VIDEO_BY_OWNER, params, new VideoRowmapper1());
	        return list;
	}

//	private static final String UPDATE_NOTICE="update esite.es_feature_notice set title = ?,content = ? ,imgurl = ?,CountdownTime = ?,CountdownWeekday = ? where id = ?";
//	@Override
//	public int updateVideo(Video video) {
//		Object[] params = {
//				notice.getTitle(),
//				notice.getContent(),
//				notice.getImgurl(),
//				notice.getHour() + ":" + notice.getMinute() + ":"
//						+ notice.getSecond(), notice.getCountdownWeekday(),
//				notice.getId() };
//		return getJdbcTemplate().update(UPDATE_NOTICE, params);
//	}
	private static final String SAVE_FETRUE_VIDEO = "insert into esite.es_feature_videolist (pageid) values(?)";

    public long saveFetureVideo(final FeatureVideoList video) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
                PreparedStatement ps = arg0.prepareStatement(SAVE_FETRUE_VIDEO, new String[] { "id" });
                int i = 1;
                ps.setLong(i++, video.getPageid());
                return ps;
            }
        }, keyHolder);
        long id = keyHolder.getKey().intValue();
        return id;
    }
    private static final String SAVE_FETRUE_VIDEO_LIST = "insert into esite.es_feature_videolist_video (vlid,vid,idx) values(?,?,?) on duplicate key update status='NOR',idx=?";

    public int saveFetureVideoListVideo(final long lvid,final long vid,int idx) {
        Object[] params ={ lvid, vid,idx,idx};
        return getJdbcTemplate().update(SAVE_FETRUE_VIDEO_LIST, params);
    }

//    @Override
//    public int findFetureVideoListVideo(long lvid, long vid) {
//        Object[] args={lvid,vid};
//        String sql = "select count(id) esite.es_feature_videolist_video where lvid=? and vid=?";
//        return getJdbcTemplate().queryForInt(sql,args);
//    }
    
    
    @Override
    public int deleteFeatureVideoList(String ids) {
        String sql="update esite.es_feature_videolist_video set status='DEL' where id in ("+ids+")";
        return getJdbcTemplate().update(sql);
    }

}
