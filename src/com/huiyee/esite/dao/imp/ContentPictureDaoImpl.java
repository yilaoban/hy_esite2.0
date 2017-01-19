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
import com.huiyee.esite.dao.IContentPictureDao;
import com.huiyee.esite.dao.imp.ContentProductDaoImpl.ContentProductRowMapper;
import com.huiyee.esite.model.ContentPicture;
import com.huiyee.esite.model.ContentProduct;

public class ContentPictureDaoImpl extends AbstractDao implements IContentPictureDao {
	private static final String SAVE_PICTURE = " insert into es_content_pic (ownerid,catid,title,imgurl,linkurl,status,createtime,updatetime,idx,tag,fatherid) values (?,?,?,?,?,?,now(),now(),?,?,?)";

	public long savePicture(final ContentPicture picture) {
		final int idx=findMaxIndx(picture.getCatid());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(SAVE_PICTURE, new String[] { "id" });
				int i = 1;
				ps.setLong(i++, picture.getOwnerid());
				ps.setLong(i++, picture.getCatid());
				ps.setString(i++, picture.getTitle());
				ps.setString(i++, picture.getImgurl());
				ps.setString(i++, picture.getLinkurl());
				ps.setString(i++, picture.getStatus());
				ps.setInt(i++, idx+1);
				ps.setString(i++, picture.getTag());
				ps.setLong(i++, picture.getFatherid());
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	public List<ContentPicture> findPictureByCcid(long ccid,long owner, int start, int size, String name) {
		List<Object> list=new ArrayList<Object>();
		String sql="select * from es_content_pic p join es_content_category c on p.catid=c.id where catid=? and p.ownerid=? and p.status!='DEL' ";
		list.add(ccid);
		list.add(owner);
		if(StringUtils.isNotEmpty(name)){
			sql+=" and p.title like ? ";
			list.add("%"+name+"%");
		}
		sql+=" order by p.idx desc limit ?,?";
		list.add(start);
		list.add(size);
		
		return getJdbcTemplate().query(sql, list.toArray(), new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				ContentPicture p = new ContentPicture();
				p.setId(rs.getLong("p.id"));
				p.setOwnerid(rs.getLong("p.ownerid"));
				p.setCatid(rs.getLong("p.catid"));
				p.setCatname(rs.getString("c.name"));
				p.setTitle(rs.getString("p.title"));
				p.setImgurl(rs.getString("p.imgurl"));
				p.setStatus(rs.getString("p.status"));
				p.setCreatetime(rs.getTimestamp("p.createtime"));
				p.setUpdatetime(rs.getTimestamp("p.updatetime"));
				p.setIdx(rs.getInt("p.idx"));
				return p;
			}
		});
	}

	private static final String FIND_PICTURE_BY_ID = "select * from es_content_pic where id=? and ownerid=? ";

	public ContentPicture findPictureById(long contentId, long id) {
		List<ContentPicture> list = getJdbcTemplate().query(FIND_PICTURE_BY_ID, new Object[] { contentId, id }, new MyRowMapper());
		if (list.size() == 1) {
			return list.get(0);
		}
		return null;
	}
	

	public ContentPicture findPictureById(long contentId) {
		String sql = "select * from es_content_pic where id=? ";
		List<ContentPicture> list = getJdbcTemplate().query(sql, new Object[] { contentId }, new MyRowMapper());
		if (list.size() == 1) {
			return list.get(0);
		}
		return null;
	}

	class MyRowMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			ContentPicture p = new ContentPicture();
			p.setId(rs.getLong("id"));
			p.setOwnerid(rs.getLong("ownerid"));
			p.setCatid(rs.getLong("catid"));
			p.setTitle(rs.getString("title"));
			p.setImgurl(rs.getString("imgurl"));
			p.setLinkurl(rs.getString("linkurl"));
			p.setStatus(rs.getString("status"));
			p.setTag(rs.getString("tag"));
			p.setCreatetime(rs.getTimestamp("createtime"));
			p.setUpdatetime(rs.getTimestamp("updatetime"));
			p.setIdx(rs.getInt("idx"));
			p.setFatherid(rs.getLong("fatherid"));
			return p;
		}
	}

	private static final String UPDATE_PICTURE = "update es_content_pic set title=?,imgurl=?,linkurl=?,status=?,tag=? where ownerid=? and id= ? ";

	public int updatePicture(ContentPicture picture) {
		return getJdbcTemplate().update(UPDATE_PICTURE, new Object[] { picture.getTitle(), picture.getImgurl(),picture.getLinkurl(), picture.getStatus(),picture.getTag(), picture.getOwnerid(), picture.getId() });
	}
	
	private static final String UPDATE_PICTURE_BY_FATHERID = "update es_content_pic set title=?,imgurl=?,linkurl=?,status=?,tag=? where ownerid=? and fatherid= ?  and status!='DEL'";
	@Override
	public void updatePictureByFatherid(long fatherid, ContentPicture picture) {
		getJdbcTemplate().update(UPDATE_PICTURE_BY_FATHERID, new Object[] { picture.getTitle(), picture.getImgurl(),picture.getLinkurl(), picture.getStatus(),picture.getTag(), picture.getOwnerid(), fatherid });
	}

	private static final String UPDATE_PICTURE_STATUS = "update es_content_pic set status=? where id= ? and ownerid= ? ";

	public int updatePicture(long contentId, long owner, String contentDel) {
		return getJdbcTemplate().update(UPDATE_PICTURE_STATUS, new Object[] { contentDel, contentId, owner });
	}

	public int findPictureTotal(long ccid,long owner, String name) {
		List<Object> list=new ArrayList<Object>();
		String sql="select count(*) from es_content_pic where catid =? and ownerid=? and status!='DEL' ";
		list.add(ccid);
		list.add(owner);
		if(StringUtils.isNotEmpty(name)){
			sql+=" and title like ? ";
			list.add("%"+name+"%");
		}
		return getJdbcTemplate().queryForInt(sql,list.toArray());
	}

	@Override
	public int deleteIndx(int idx, long catid)
	{
		String sql="update es_content_pic set idx=idx-1 where idx>? and catid=?";
		return getJdbcTemplate().update(sql,new Object[]{idx,catid});
	}

	@Override
	public Map findIndx(long id)
	{
		String sql="select catid,idx from es_content_pic where id=?";
		try{
			return getJdbcTemplate().queryForMap(sql,new Object[]{id});
		}catch (DataAccessException e) {
			return null;
		}
	}

	@Override
	public int findMaxIndx(long catid)
	{
		String sql="select Max(idx) from es_content_pic where catid=?";
		return getJdbcTemplate().queryForInt(sql,new Object[]{catid});
	}

	@Override
	public int updatePicIdx(ContentPicture cp)
	{
		return getJdbcTemplate().update("update es_content_pic set idx=? where id=?", new Object[]{cp.getIdx(),cp.getId()});
	}

	@Override
	public List<ContentPicture> findPictureByCcid(long ccid,long ownerid) {
		String sql = "select n.*,c.name categoryname  from es_content_pic n join es_content_category c on n.catid=c.id where n.catid=? and n.ownerid=? and n.status='CMP' order by n.idx desc, n.id desc";
		return getJdbcTemplate().query(sql, new Object[] { ccid,ownerid}, new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				ContentPicture p = new ContentPicture();
				p.setId(rs.getLong("id"));
				p.setOwnerid(rs.getLong("ownerid"));
				p.setCatid(rs.getLong("catid"));
				p.setTitle(rs.getString("title"));
				p.setImgurl(rs.getString("imgurl"));
				p.setLinkurl(rs.getString("linkurl"));
				p.setStatus(rs.getString("status"));
				p.setTag(rs.getString("tag"));
				p.setCreatetime(rs.getTimestamp("createtime"));
				p.setUpdatetime(rs.getTimestamp("updatetime"));
				p.setCatname(rs.getString("categoryname"));
				p.setStatus(rs.getString("status"));
				p.setFatie(rs.getString("fatie"));
				p.setTopicid(rs.getLong("topicid"));
				return p;
			}
		});
	}
	
	@Override
	public void updatePicturePost(long entityid, long topicid) {
		getJdbcTemplate().update("update esite.es_content_pic set fatie='Y',topicid=? where id=?", new Object[]{topicid,entityid});
	}
}
