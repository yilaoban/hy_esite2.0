package com.huiyee.esite.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
import com.huiyee.esite.dao.IContentNewDao;
import com.huiyee.esite.model.ContentNew;
import com.huiyee.esite.model.ContentPicture;

import net.sf.json.JSONObject;

public class ContentNewDaoImpl extends AbstractDao implements IContentNewDao {
	private static final String SAVE_NEW = "insert into es_content_news (ownerid,catid,title,simgurl,bimgurl,linkurl,content,shortdesc,status,createtime,updatetime,idx,islink,startTime,endTime,author,source,publishtime,initialZan,initialRead,fatherid) values(?,?,?,?,?,?,?,?,?,now(),now(),?,?,?,?,?,?,?,?,?,?)";

	public long saveNew(final ContentNew cn) {
		final int idx=findMaxIndx(cn.getCatid());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(SAVE_NEW, new String[] { "id" });
				int i = 1;
				ps.setLong(i++, cn.getOwnerid());
				ps.setLong(i++, cn.getCatid());
				ps.setString(i++, cn.getTitle());
				ps.setString(i++, cn.getSimgurl());
				ps.setString(i++, cn.getBimgurl());
				ps.setString(i++, cn.getLinkurl());
				ps.setString(i++, cn.getContent());
				ps.setString(i++, cn.getShortdesc());
				ps.setString(i++, cn.getStatus());
				ps.setInt(i++, idx+1);
				ps.setString(i++, cn.getIslink());
				if(cn.getStartTime() != null){
					ps.setTimestamp(i++, new Timestamp(cn.getStartTime().getTime()));
				}else{
					ps.setTimestamp(i++, null);
				}
				if(cn.getEndTime() != null){
					ps.setTimestamp(i++, new Timestamp(cn.getEndTime().getTime()));
				}else{
					ps.setTimestamp(i++, null);
				}
				ps.setString(i++, cn.getAuthor());
				ps.setString(i++, cn.getSource());
				if(cn.getPublishtime() != null){
					ps.setTimestamp(i++, new Timestamp(cn.getPublishtime().getTime()));
				}else{
					ps.setTimestamp(i++, null);
				}
				ps.setInt(i++, cn.getInitialZan());
				ps.setInt(i++, cn.getInitialRead());
				ps.setLong(i++, cn.getFatherid());
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}
	
	@Override
	public long savecdNews(final ContentNew cn)
	{
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(SAVE_NEW, new String[] { "id" });
				int i = 1;
				ps.setLong(i++, cn.getOwnerid());
				ps.setLong(i++, cn.getCatid());
				ps.setString(i++, cn.getTitle());
				ps.setString(i++, cn.getSimgurl());
				ps.setString(i++, cn.getBimgurl());
				ps.setString(i++, cn.getLinkurl());
				ps.setString(i++, cn.getContent());
				ps.setString(i++, cn.getShortdesc());
				ps.setString(i++, cn.getStatus());
				ps.setInt(i++, 0);
				ps.setString(i++, cn.getIslink());
				if(cn.getStartTime() != null){
					ps.setTimestamp(i++, new Timestamp(cn.getStartTime().getTime()));
				}else{
					ps.setTimestamp(i++, null);
				}
				if(cn.getEndTime() != null){
					ps.setTimestamp(i++, new Timestamp(cn.getEndTime().getTime()));
				}else{
					ps.setTimestamp(i++, null);
				}
				ps.setString(i++, cn.getAuthor());
				ps.setString(i++, cn.getSource());
				if(cn.getPublishtime() != null){
					ps.setTimestamp(i++, new Timestamp(cn.getPublishtime().getTime()));
				}else{
					ps.setTimestamp(i++, null);
				}
				ps.setInt(i++, cn.getInitialZan());
				ps.setInt(i++, cn.getInitialRead());
				ps.setLong(i++, cn.getFatherid());
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	public List<ContentNew> findNewByCcid(long ccid,long ownerid, int start, int size, String name) {
		List<Object> list=new ArrayList<Object>();
		String sql="select * from es_content_news n join es_content_category c on n.catid=c.id where n.catid=? and n.ownerid=? and n.status!='DEL' ";
		list.add(ccid);
		list.add(ownerid);
		if(StringUtils.isNotEmpty(name)){
			sql+=" and n.title like ? ";
			list.add("%"+name+"%");
		}
		sql+=" order by n.top desc,n.idx desc limit ?,? ";
		list.add(start);
		list.add(size);
		return getJdbcTemplate().query(sql, list.toArray(), new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				ContentNew cn = new ContentNew();
				cn.setId(rs.getLong("n.id"));
				cn.setOwnerid(rs.getLong("n.ownerid"));
				cn.setCatid(rs.getLong("n.catid"));
				cn.setTitle(rs.getString("n.title"));
				cn.setContent(rs.getString("n.content"));
				cn.setShortdesc(rs.getString("n.shortdesc"));
				cn.setStatus(rs.getString("n.status"));
				cn.setBimgurl(rs.getString("n.bimgurl"));
				cn.setSimgurl(rs.getString("n.simgurl"));
				cn.setLinkurl(rs.getString("n.linkurl"));
				cn.setCreatetime(rs.getTimestamp("n.createtime"));
				cn.setUpdatetime(rs.getTimestamp("n.updatetime"));
				cn.setCatname(rs.getString("c.name"));
				cn.setIslink(rs.getString("n.islink"));
				cn.setIdx(rs.getInt("n.idx"));
				cn.setTop(rs.getInt("n.top"));
				return cn;
			}
		});
	}
	
	
	public List<ContentNew> findNewsByCcid(long ccid,long ownerid, int start, int size, String status) {
		List<Object> list=new ArrayList<Object>();
		String sql="select * from es_content_news n join es_content_category c on n.catid=c.id where n.catid=? and n.ownerid=? and  n.status=? ";
		list.add(ccid);
		list.add(ownerid);
		list.add(status);
		sql+=" order by n.top desc,n.idx desc limit ?,? ";
		list.add(start);
		list.add(size);
		return getJdbcTemplate().query(sql, list.toArray(), new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				ContentNew cn = new ContentNew();
				cn.setId(rs.getLong("n.id"));
				cn.setOwnerid(rs.getLong("n.ownerid"));
				cn.setCatid(rs.getLong("n.catid"));
				cn.setTitle(rs.getString("n.title"));
				cn.setContent(rs.getString("n.content"));
				cn.setStatus(rs.getString("n.status"));
				cn.setBimgurl(rs.getString("n.bimgurl"));
				cn.setSimgurl(rs.getString("n.simgurl"));
				cn.setLinkurl(rs.getString("n.linkurl"));
				cn.setCreatetime(rs.getTimestamp("n.createtime"));
				cn.setUpdatetime(rs.getTimestamp("n.updatetime"));
				cn.setCatname(rs.getString("c.name"));
				cn.setIslink(rs.getString("n.islink"));
				cn.setIdx(rs.getInt("n.idx"));
				cn.setTop(rs.getInt("n.top"));
				return cn;
			}
		});
	}

	private static final String FIND_NEW_BY_ID = "select * from es_content_news where id=? and ownerid= ? ";

	public ContentNew findNewById(long contentId, long owner) {
		List<ContentNew> list = new ArrayList<ContentNew>();
		list = getJdbcTemplate().query(FIND_NEW_BY_ID, new Object[] { contentId, owner }, new MyRowMapper());
		if (list.size() == 1) {
			return list.get(0);
		}
		return null;
	}

	class MyRowMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			ContentNew cn = new ContentNew();
			cn.setId(rs.getLong("id"));
			cn.setOwnerid(rs.getLong("ownerid"));
			cn.setCatid(rs.getLong("catid"));
			cn.setTitle(rs.getString("title"));
			cn.setContent(rs.getString("content"));
			cn.setStatus(rs.getString("status"));
			cn.setBimgurl(rs.getString("bimgurl"));
			cn.setSimgurl(rs.getString("simgurl"));
			cn.setLinkurl(rs.getString("linkurl"));
			cn.setShortdesc(rs.getString("shortdesc"));
			cn.setCreatetime(rs.getTimestamp("createtime"));
			cn.setUpdatetime(rs.getTimestamp("updatetime"));
			cn.setIslink(rs.getString("islink"));
			cn.setStartTime(rs.getTimestamp("startTime"));
			cn.setEndTime(rs.getTimestamp("endTime"));
			cn.setAuthor(rs.getString("author"));
			cn.setSource(rs.getString("source"));
			cn.setPublishtime(rs.getTimestamp("publishtime"));
			cn.setInitialZan(rs.getInt("initialZan"));
			cn.setInitialRead(rs.getInt("initialRead"));
			return cn;
		}
	}

	private static final String UPDATE_NEW = "update es_content_news set title=?,content=?,shortdesc=?,simgurl=?,bimgurl=?,linkurl=?,status=?,islink=?,updatetime=now(),startTime=?,endTime=?,author =?,source =?,publishtime=?,initialRead=?,initialZan=? where id=? and ownerid= ? ";

	public int updateNew(ContentNew cn) {
		return getJdbcTemplate().update(UPDATE_NEW, new Object[] { cn.getTitle(), cn.getContent(),cn.getShortdesc(),cn.getSimgurl(),cn.getBimgurl(),cn.getLinkurl(), cn.getStatus(),cn.getIslink() ,cn.getStartTime(),cn.getEndTime(),cn.getAuthor(),cn.getSource(),cn.getPublishtime(),cn.getInitialRead(),cn.getInitialZan(),cn.getId(), cn.getOwnerid() });
	}
	
	private static final String UPDATE_NEW_BY_FATHERID = "update es_content_news set title=?,content=?,shortdesc=?,simgurl=?,bimgurl=?,linkurl=?,status=?,islink=?,updatetime=now(),startTime=?,endTime=?,author =?,source =?,publishtime=?,initialRead=?,initialZan=? where fatherid=? and ownerid= ?  and status!='DEL'";
	@Override
	public void updateNewsByFatherid(long fatherid, ContentNew cn) {
		getJdbcTemplate().update(UPDATE_NEW_BY_FATHERID, new Object[] { cn.getTitle(), cn.getContent(),cn.getShortdesc(),cn.getSimgurl(),cn.getBimgurl(),cn.getLinkurl(), cn.getStatus(),cn.getIslink() ,cn.getStartTime(),cn.getEndTime(),cn.getAuthor(),cn.getSource(),cn.getPublishtime(),cn.getInitialRead(),cn.getInitialZan(),fatherid, cn.getOwnerid() });
	}

	private static final String UPDATE_NEW_STATUS = "update es_content_news set status=? where id=? and ownerid= ?";

	public int updateNew(long contentId, long owner, String status) {
		return getJdbcTemplate().update(UPDATE_NEW_STATUS, new Object[] { status, contentId, owner });
	}

	private static final String FIND_NEWS_TOTAL = "";

	public int findNewsTotal(long ccid,long owner,String name) {
		List<Object> list=new ArrayList<Object>();
		String sql="select count(*) from es_content_news where catid =? and ownerid=? and status!='DEL'";
		list.add(ccid);
		list.add(owner);
		if(StringUtils.isNotEmpty(name)){
			sql+=" and title like ? ";
			list.add("%"+name+"%");
		}
		return getJdbcTemplate().queryForInt(sql,list.toArray());
	}

	private static final String FIND_NEWS_BY_OWNER = "select * from es_content_news where ownerid= ? ";

	public List<ContentNew> findNewsByOwner(long owner) {
		return getJdbcTemplate().query(FIND_NEWS_BY_OWNER, new Object[] { owner }, new MyRowMapper());

	}
	
	public List<ContentNew> findNewByCcid(long ccid,long ownerid) {
		String sql = "select n.*,c.name categoryname  from es_content_news n join es_content_category c on n.catid=c.id where n.catid=? and n.ownerid=? and n.status='CMP' order by n.top desc,n.idx desc ";
		return getJdbcTemplate().query(sql, new Object[] { ccid,ownerid }, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				ContentNew cn = new ContentNew();
				cn.setId(rs.getLong("id"));
				cn.setOwnerid(rs.getLong("ownerid"));
				cn.setCatid(rs.getLong("catid"));
				cn.setTitle(rs.getString("title"));
				cn.setContent(rs.getString("content"));
				cn.setStatus(rs.getString("status"));
				cn.setBimgurl(rs.getString("bimgurl"));
				cn.setSimgurl(rs.getString("simgurl"));
				cn.setLinkurl(rs.getString("linkurl"));
				cn.setCreatetime(rs.getTimestamp("createtime"));
				cn.setUpdatetime(rs.getTimestamp("updatetime"));
				cn.setCatname(rs.getString("categoryname"));
				cn.setShortdesc(rs.getString("shortdesc"));
				cn.setIslink(rs.getString("islink"));
				cn.setLinkurl(rs.getString("linkurl"));
				cn.setFatie(rs.getString("fatie"));
				cn.setTopicid(rs.getLong("topicid"));
				cn.setStartTime(rs.getTimestamp("startTime"));
				cn.setEndTime(rs.getTimestamp("endTime"));
				return cn;
			}
		});
	}

	@Override
	public int deleteIndx(int idx, long catid)
	{
		String sql="update es_content_news set idx=idx-1 where idx>? and catid=?";
		return getJdbcTemplate().update(sql,new Object[]{idx,catid});
	}

	@Override
	public Map findIndx(long id)
	{
		String sql="select catid,idx from es_content_news where id=?";
		try{
			return getJdbcTemplate().queryForMap(sql,new Object[]{id});
		}catch (DataAccessException e) {
			return null;
		}
	}

	@Override
	public int findMaxIndx(long catid)
	{
		String sql="select Max(idx) from es_content_news where catid=?";
		return getJdbcTemplate().queryForInt(sql,new Object[]{catid});
	}

	@Override
	public int updateNewsIdx(ContentNew news)
	{
		return getJdbcTemplate().update("update es_content_news set idx=? where id=?", new Object[]{news.getIdx(),news.getId()});
	}

	private static final String FIND_NEWS_BY_ID = "select n.*, c.name catname,c.bread from es_content_news n join es_content_category c on n.catid = c.id where n.id=?";
	@Override
	public ContentNew findNewsById(long contentId) {
		List<ContentNew> list = new ArrayList<ContentNew>();
		list = getJdbcTemplate().query(FIND_NEWS_BY_ID, new Object[] { contentId }, new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				ContentNew cn = new ContentNew();
				cn.setId(rs.getLong("id"));
				cn.setOwnerid(rs.getLong("ownerid"));
				cn.setCatid(rs.getLong("catid"));
				cn.setTitle(rs.getString("title"));
				cn.setContent(rs.getString("content"));
				cn.setStatus(rs.getString("status"));
				cn.setBimgurl(rs.getString("bimgurl"));
				cn.setSimgurl(rs.getString("simgurl"));
				cn.setLinkurl(rs.getString("linkurl"));
				cn.setIdx(rs.getInt("idx"));
				cn.setShortdesc(rs.getString("shortdesc"));
				cn.setCreatetime(rs.getTimestamp("createtime"));
				cn.setUpdatetime(rs.getTimestamp("updatetime"));
				cn.setIslink(rs.getString("islink"));
				cn.setCatname(rs.getString("catname"));
				String bread = rs.getString("bread");
				if(bread != null){
					cn.setBread(JSONObject.fromObject(bread));
				}
				cn.setStartTime(rs.getTimestamp("startTime"));
				cn.setEndTime(rs.getTimestamp("endTime"));
				cn.setAuthor(rs.getString("author"));
				cn.setPublishtime(rs.getTimestamp("publishtime"));
				cn.setSource(rs.getString("source"));
				cn.setFatie(rs.getString("fatie"));
				cn.setInitialZan(rs.getInt("initialZan"));
				cn.setInitialRead(rs.getInt("initialRead"));
				cn.setFatherid(rs.getLong("n.fatherid"));
				return cn;
			}
		});
		if (list.size() == 1) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public void updateNewsPost(long entityid, long topicid) {
		getJdbcTemplate().update("update esite.es_content_news set fatie='Y',topicid=? where id=?", new Object[]{topicid,entityid});
	}
	
	private static final String FIND_BEFORE_NEWS="select * from es_content_news where catid = ? and status != 'DEL' and idx > ? order by idx desc";
	@Override
	public ContentNew findBeforeNews(ContentNew news) {
		List<ContentNew> list = getJdbcTemplate().query(FIND_BEFORE_NEWS, new Object[] { news.getCatid(),news.getIdx()}, new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				ContentNew cn = new ContentNew();
				cn.setId(rs.getLong("id"));
				cn.setOwnerid(rs.getLong("ownerid"));
				cn.setCatid(rs.getLong("catid"));
				cn.setTitle(rs.getString("title"));
				cn.setShortdesc(rs.getString("shortdesc"));
				cn.setCreatetime(rs.getTimestamp("createtime"));
				cn.setIdx(rs.getInt("idx"));
				return cn;
			}
		});
		if (list.size() >= 1) {
			return list.get(list.size()-1);
		}
		return null;
	}

	private static final String FIND_NEXT_NEWS="select * from es_content_news where catid = ? and status != 'DEL' and idx < ? order by idx desc";
	@Override
	public ContentNew findNextNews(ContentNew news) {
		List<ContentNew> list = getJdbcTemplate().query(FIND_NEXT_NEWS, new Object[] { news.getCatid(),news.getIdx()}, new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				ContentNew cn = new ContentNew();
				cn.setId(rs.getLong("id"));
				cn.setOwnerid(rs.getLong("ownerid"));
				cn.setCatid(rs.getLong("catid"));
				cn.setTitle(rs.getString("title"));
				cn.setShortdesc(rs.getString("shortdesc"));
				cn.setCreatetime(rs.getTimestamp("createtime"));
				cn.setIdx(rs.getInt("idx"));
				return cn;
			}
		});
		if (list.size() >= 1) {
			return list.get(0);
		}
		return null;
	}
	
	
	
	@Override
	public int updateContentToTop(long contentid, long owner, int topType)
	{
		return getJdbcTemplate().update("update es_content_news set top=? where id=? and ownerid=?", new Object[]{topType,contentid,owner});
	}

	private static final String FIND_BEFORE_PICTURE="select * from es_content_pic where catid = ? and status != 'DEL' and idx > ? order by idx desc";
	@Override
	public ContentPicture findBeforePicture(ContentPicture pic)
	{
		List<ContentPicture> list = getJdbcTemplate().query(FIND_BEFORE_PICTURE, new Object[] { pic.getCatid(),pic.getIdx()}, new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				ContentPicture cn = new ContentPicture();
				cn.setId(rs.getLong("id"));
				cn.setOwnerid(rs.getLong("ownerid"));
				cn.setCatid(rs.getLong("catid"));
				cn.setTitle(rs.getString("title"));
				cn.setTag(rs.getString("tag"));
				cn.setCreatetime(rs.getTimestamp("createtime"));
				cn.setIdx(rs.getInt("idx"));
				return cn;
			}
		});
		if (list.size() >= 1) {
			return list.get(list.size()-1);
		}
		return null;
	}

	private static final String FIND_NEXT_PICTURE="select * from es_content_pic where catid = ? and status != 'DEL' and idx < ? order by idx desc";
	@Override
	public ContentPicture findNextPicture(ContentPicture pic)
	{
		List<ContentPicture> list = getJdbcTemplate().query(FIND_NEXT_PICTURE, new Object[] { pic.getCatid(),pic.getIdx()}, new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				ContentPicture cn = new ContentPicture();
				cn.setId(rs.getLong("id"));
				cn.setOwnerid(rs.getLong("ownerid"));
				cn.setCatid(rs.getLong("catid"));
				cn.setTitle(rs.getString("title"));
				cn.setTag(rs.getString("tag"));
				cn.setCreatetime(rs.getTimestamp("createtime"));
				cn.setIdx(rs.getInt("idx"));
				return cn;
			}
		});
		if (list.size() >= 1) {
			return list.get(0);
		}
		return null;
	}
}
