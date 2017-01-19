package com.huiyee.esite.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.dao.IContentVideoDao;
import com.huiyee.esite.model.ContentVideo;

public class ContentVideoDaoImpl extends AbstractDao implements IContentVideoDao {
	private static final String SAVE_VIDEO = "insert into es_content_video (ownerid,catid,title,picurl,plinkurl,videourl,html,status,createtime,updatetime,idx,fatherid) values(?,?,?,?,?,?,?,?,now(),now(),?,?)";

	public long saveVideo(final ContentVideo video) {
		final int idx=findMaxIndx(video.getCatid());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(SAVE_VIDEO, new String[] { "id" });
				int i = 1;
				ps.setLong(i++, video.getOwnerid());
				ps.setLong(i++, video.getCatid());
				ps.setString(i++, video.getTitle());
				ps.setString(i++, video.getPicurl());
				ps.setString(i++, video.getLinkurl());
				ps.setString(i++, video.getVideourl());
				ps.setString(i++, video.getHtml());
				ps.setString(i++, video.getStatus());
				ps.setInt(i++, idx+1);
				ps.setLong(i++, video.getFatherid());
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	public List<ContentVideo> findVideoByCcid(long ccid,long owner, int start, int size, String name) {
		List<Object> list=new ArrayList<Object>();
		String sql="select * from es_content_video v join es_content_category c on v.catid=c.id where v.catid=? and v.ownerid=? and v.status!='DEL' ";
		list.add(ccid);
		list.add(owner);
		if(StringUtils.isNotEmpty(name)){
			sql+=" and v.title like ? ";
			list.add("%"+name+"%");
		}
		sql+=" order by v.idx desc limit ?,?";
		list.add(start);
		list.add(size);
		
		return getJdbcTemplate().query(sql, list.toArray(), new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				ContentVideo v = new ContentVideo();
				v.setId(rs.getLong("v.id"));
				v.setOwnerid(rs.getLong("v.ownerid"));
				v.setCatid(rs.getLong("v.catid"));
				v.setTitle(rs.getString("v.title"));
				v.setPicurl(rs.getString("v.picurl"));
				v.setVideourl(rs.getString("v.videourl"));
				v.setHtml(rs.getString("v.html"));
				v.setLinkurl(rs.getString("v.plinkurl"));
				v.setStatus(rs.getString("v.status"));
				v.setCreatetime(rs.getTimestamp("v.createtime"));
				v.setUpdatetime(rs.getTimestamp("v.updatetime"));
				v.setCatname(rs.getString("c.name"));
				v.setIdx(rs.getInt("v.idx"));
				return v;
			}
		});
	}

	private static final String FIND_VIDEO_BY_ID = "select * from es_content_video where id=? and ownerid=? ";

	public ContentVideo findVideoById(long contentId, long owner) {
		List<ContentVideo> list = getJdbcTemplate().query(FIND_VIDEO_BY_ID, new Object[] { contentId, owner }, new MyRowMapper());
		if (list.size() == 1) {
			return list.get(0);
		}
		return null;
	}
	

	public ContentVideo findVideoById(long contentId) {
		String sql = "select * from es_content_video where id=? ";
		List<ContentVideo> list = getJdbcTemplate().query(sql, new Object[] { contentId}, new MyRowMapper());
		if (list.size() == 1) {
			return list.get(0);
		}
		return null;
	}
	
	public ContentVideo findNextVideoById(ContentVideo video) {
		String sql = "select * from es_content_video where catid = ? and status != 'DEL' and idx < ? order by idx desc";
		List<ContentVideo> list = getJdbcTemplate().query(sql, new Object[] { video.getCatid(),video.getIdx()}, new MyRowMapper());
		if (list.size() >= 1) {
			return list.get(0);
		}
		return null;
	}
	
	public ContentVideo findBeforeVideoById(ContentVideo video) {
		String sql = "select * from es_content_video where catid = ? and status != 'DEL' and idx > ? order by idx desc";
		List<ContentVideo> list = getJdbcTemplate().query(sql, new Object[] { video.getCatid(),video.getIdx()}, new MyRowMapper());
		if (list.size() >= 1) {
			return list.get(list.size()-1);
		}
		return null;
	}

	class MyRowMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			ContentVideo v = new ContentVideo();
			v.setId(rs.getLong("id"));
			v.setOwnerid(rs.getLong("ownerid"));
			v.setCatid(rs.getLong("catid"));
			v.setTitle(rs.getString("title"));
			v.setPicurl(rs.getString("picurl"));
			v.setVideourl(rs.getString("videourl"));
			v.setHtml(rs.getString("html"));
			v.setLinkurl(rs.getString("plinkurl"));
			v.setStatus(rs.getString("status"));
			v.setCreatetime(rs.getTimestamp("createtime"));
			v.setUpdatetime(rs.getTimestamp("updatetime"));
			v.setFatherid(rs.getLong("fatherid"));
			v.setIdx(rs.getInt("idx"));
			return v;
		}
	}

	private static final String UPDATE_VIDEO = "update es_content_video set title=?,picurl=?,plinkurl=?,videourl=?,html=?,status=?,updatetime=now() where id=? and ownerid=? ";

	public int updateVideo(ContentVideo video) {
		return getJdbcTemplate().update(UPDATE_VIDEO,
				new Object[] { video.getTitle(), video.getPicurl(),video.getLinkurl(), video.getVideourl(), video.getHtml(), video.getStatus(), video.getId(), video.getOwnerid() });
	}
	
	private static final String UPDATE_VIDEO_BY_FATHERID = "update es_content_video set title=?,picurl=?,plinkurl=?,videourl=?,html=?,status=?,updatetime=now() where fatherid=? and status!='DEL' and ownerid=? ";

	public void updateVideoByFatherid(long fatherid,ContentVideo video) {
		getJdbcTemplate().update(UPDATE_VIDEO_BY_FATHERID,
				new Object[] { video.getTitle(), video.getPicurl(),video.getLinkurl(), video.getVideourl(), video.getHtml(), video.getStatus(), fatherid, video.getOwnerid() });
	}

	private static final String UPDATE_VIDEO_STATUS = " update es_content_video set status=?,updatetime=now() where id=? and ownerid=? ";

	public int updateVideo(long contentId, String status, long owner) {
		return getJdbcTemplate().update(UPDATE_VIDEO_STATUS, new Object[] { status, contentId, owner });
	}

	public int findVideoTotal(long ccid,long ownerid,String name) {
		List<Object> list=new ArrayList<Object>();
		String sql="select count(id) from es_content_video where catid=? and ownerid=? and status!='DEL'";
		list.add(ccid);
		list.add(ownerid);
		if(StringUtils.isNotEmpty(name)){
			sql+=" and title like ? ";
			list.add("%"+name+"%");
		}
		return getJdbcTemplate().queryForInt(sql,list.toArray());
	}

	@Override
	public int deleteIndx(int idx, long catid)
	{
		String sql="update es_content_video set idx=idx-1 where idx>? and catid=?";
		return getJdbcTemplate().update(sql,new Object[]{idx,catid});
	}

	@Override
	public Map findIndx(long id)
	{
		String sql="select catid,idx from es_content_video where id=?";
		try{
			return getJdbcTemplate().queryForMap(sql,new Object[]{id});
		}catch (DataAccessException e) {
			return null;
		}
	}

	@Override
	public int findMaxIndx(long catid)
	{
		String sql="select Max(idx) from es_content_video where catid=?";
		return getJdbcTemplate().queryForInt(sql,new Object[]{catid});
	}

	@Override
	public int updateVideoIdx(ContentVideo video)
	{
		return getJdbcTemplate().update("update es_content_video set idx=? where id=?", new Object[]{video.getIdx(),video.getId()});
	}
	
	@Override
	public void updateVideoPost(long entityid, long topicid) {
		getJdbcTemplate().update("update esite.es_content_video set fatie='Y',topicid=? where id=?", new Object[]{topicid,entityid});
	}
}
