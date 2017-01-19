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
import com.huiyee.esite.fdao.IHd149Dao;
import com.huiyee.esite.model.ContentCategory;
import com.huiyee.esite.model.ContentFormRecord;
import com.huiyee.esite.model.ContentNew;
import com.huiyee.esite.model.ContentPicture;
import com.huiyee.esite.model.ContentProduct;
import com.huiyee.esite.model.ContentVideo;
import com.huiyee.esite.model.HD149Model;
import com.huiyee.interact.bbs.model.BBSContent;

import net.sf.json.JSONObject;

public class Hd149DaoImpl extends AbstractDao implements IHd149Dao
{

	private static final String SAVE_FETRUE_PICLIST = "insert into esite.es_feature_contentlist (pageid) values(?)";

	@Override
	public long saveFid(final long pageid)
	{
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator()
		{
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(SAVE_FETRUE_PICLIST, new String[]
				{ "id" });
				int i = 1;
				ps.setLong(i++, pageid);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	@Override
	public List<ContentCategory> findCategory(long id)
	{
		return getJdbcTemplate().query("select * from esite.es_content_category c join esite.es_content_type t on c.typeid=t.id where c.ownerid=? and c.status!='DEL'", new Object[]
		{ id }, new RowMapper()
		{
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				ContentCategory c = new ContentCategory();
				c.setId(rs.getLong("c.id"));
				c.setType(rs.getString("t.type"));
				c.setName(rs.getString("c.name"));
				c.setFartherCatId(rs.getLong("c.fartherCatId"));
				return c;
			}
		});
	}

	@Override
	public String findContentType(long catid)
	{
		List<String> list = getJdbcTemplate().query("select t.* from es_content_category c join es_content_type t on c.typeid=t.id where c.id=?", new Object[]
		{ catid }, new RowMapper()
		{
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				return rs.getString("type");
			}
		});
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public int saveFid(long fid, long catid, String type, long topage)
	{
		return getJdbcTemplate().update("update esite.es_feature_contentlist set categoryid=?, type=?, topage=? where id=?", new Object[]
		{ catid, type, topage, fid });
	}

	@Override
	public List<BBSContent> findBBSNews(long catid, int entype)
	{
		return getJdbcTemplate().query("select n.*,t.*,c.name catname,c.bread from esite.es_content_news n join es_content_category c on n.catid = c.id left outer join hybbs.bbs_topic t on (n.id=t.entityid and entype=?) where catid=? and n.status='CMP' order by n.top desc, n.idx desc", new Object[]
		{ entype, catid }, new RowMapper()
		{
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				BBSContent content = new BBSContent();
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
				cn.setShortdesc(rs.getString("n.shortdesc"));
				cn.setCreatetime(rs.getTimestamp("n.createtime"));
				cn.setUpdatetime(rs.getTimestamp("n.updatetime"));
				cn.setIslink(rs.getString("n.islink"));
				cn.setCatname(rs.getString("catname"));
				String bread = rs.getString("bread");
				if(bread != null){
					cn.setBread(JSONObject.fromObject(bread));
				}
				content.setNews(cn);
				content.setForumid(rs.getLong("t.FORUM_ID"));
				content.setTopicid(rs.getLong("t.id"));
				cn.setStartTime(rs.getTimestamp("startTime"));
				cn.setEndTime(rs.getTimestamp("endTime"));
				cn.setAuthor(rs.getString("author"));
				cn.setSource(rs.getString("source"));
				cn.setPublishtime(rs.getTimestamp("publishtime"));
				return content;
			}
		});
	}

	@Override
	public List<BBSContent> findBBSPicture(long catid, int entype)
	{
		return getJdbcTemplate().query("select n.*,t.* from esite.es_content_pic n left outer join hybbs.bbs_topic t on (n.id=t.entityid and entype=?) where catid=? and n.status='CMP'  order by n.idx desc", new Object[]
		{ entype, catid }, new RowMapper()
		{
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				BBSContent content = new BBSContent();
				ContentPicture p = new ContentPicture();
				p.setId(rs.getLong("n.id"));
				p.setOwnerid(rs.getLong("n.ownerid"));
				p.setCatid(rs.getLong("n.catid"));
				p.setTitle(rs.getString("n.title"));
				p.setImgurl(rs.getString("n.imgurl"));
				p.setLinkurl(rs.getString("n.linkurl"));
				p.setStatus(rs.getString("n.status"));
				p.setTag(rs.getString("n.tag"));
				p.setCreatetime(rs.getTimestamp("n.createtime"));
				p.setUpdatetime(rs.getTimestamp("n.updatetime"));
				content.setPicture(p);

				content.setForumid(rs.getLong("t.FORUM_ID"));
				content.setTopicid(rs.getLong("t.id"));
				return content;
			}
		});
	}

	@Override
	public List<BBSContent> findBBSProduct(long catid, int entype)
	{
		return getJdbcTemplate().query("select n.*,t.* from esite.es_content_product n left outer join hybbs.bbs_topic t on (n.id=t.entityid and entype=?) where catid=? and n.status='CMP'  order by n.idx desc ", new Object[]
		{ entype, catid }, new RowMapper()
		{
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				BBSContent content = new BBSContent();
				ContentProduct p = new ContentProduct();
				p.setId(rs.getLong("n.id"));
				p.setOwner(rs.getLong("n.ownerid"));
				p.setCatid(rs.getLong("n.catid"));
				p.setName(rs.getString("n.name"));
				p.setSimgurl(rs.getString("n.simgurl"));
				p.setBimgurl(rs.getString("n.bimgurl"));
				p.setLinkurl(rs.getString("n.linkurl"));
				p.setPrice(rs.getDouble("n.price"));
				p.setDetail(rs.getString("n.detail"));
				p.setStatus(rs.getString("n.status"));
				p.setCreatetime(rs.getTimestamp("n.createtime"));
				p.setUpdatetime(rs.getDate("n.updatetime"));
				p.setTag(rs.getString("n.tag"));
				p.setF1(rs.getString("n.f1"));
				p.setF2(rs.getString("n.f2"));
				p.setF3(rs.getString("n.f3"));
				p.setF4(rs.getString("n.f4"));
				p.setF5(rs.getString("n.f5"));

				content.setProduct(p);
				content.setForumid(rs.getLong("t.FORUM_ID"));
				content.setTopicid(rs.getLong("t.id"));
				return content;
			}
		});
	}

	@Override
	public List<BBSContent> findBBSVideo(long catid, int entype)
	{
		return getJdbcTemplate().query("select n.*,t.* from esite.es_content_video n left outer join hybbs.bbs_topic t on (n.id=t.entityid and entype=?) where catid=? and n.status='CMP'  order by n.idx desc", new Object[]
		{ entype, catid }, new RowMapper()
		{
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				BBSContent content = new BBSContent();
				ContentVideo v = new ContentVideo();
				v.setId(rs.getLong("n.id"));
				v.setOwnerid(rs.getLong("n.ownerid"));
				v.setCatid(rs.getLong("n.catid"));
				v.setTitle(rs.getString("n.title"));
				v.setPicurl(rs.getString("n.picurl"));
				v.setVideourl(rs.getString("n.videourl"));
				v.setHtml(rs.getString("n.html"));
				v.setLinkurl(rs.getString("n.plinkurl"));
				v.setStatus(rs.getString("n.status"));
				v.setCreatetime(rs.getTimestamp("n.createtime"));
				v.setUpdatetime(rs.getTimestamp("n.updatetime"));

				content.setVideo(v);
				content.setForumid(rs.getLong("t.FORUM_ID"));
				content.setTopicid(rs.getLong("t.id"));
				return content;
			}
		});
	}
	
	@Override
	public List<BBSContent> findBBSForm(long catid, int typeCode) {
		return getJdbcTemplate().query("select * from esite.es_content_form_record r join es_content_form f on r.formid=f.id where f.catid=?", new Object[]
		{ catid }, new RowMapper()
		{
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				BBSContent c=new BBSContent();
				ContentFormRecord r = new ContentFormRecord();
				r.setId(rs.getLong("id"));
				r.setFormid(rs.getLong("formid"));
				r.setTitle(rs.getString("title"));
				r.setDetail(rs.getString("detail"));
				r.setF1(rs.getString("f1"));
				r.setF2(rs.getString("f2"));
				r.setF3(rs.getString("f3"));
				r.setF4(rs.getString("f4"));
				r.setF5(rs.getString("f5"));
				r.setF6(rs.getString("f6"));
				r.setF7(rs.getString("f7"));
				r.setF8(rs.getString("f8"));
				r.setF9(rs.getString("f9"));
				r.setF10(rs.getString("f10"));
				r.setF11(rs.getString("f11"));
				r.setF12(rs.getString("f12"));
				r.setF13(rs.getString("f13"));
				r.setF14(rs.getString("f14"));
				r.setF15(rs.getString("f15"));
				r.setF16(rs.getString("f16"));
				r.setF17(rs.getString("f17"));
				r.setF18(rs.getString("f18"));
				r.setF19(rs.getString("f19"));
				r.setF20(rs.getString("f20"));
				c.setFormRecord(r);
				return c;
			}
		});
	}

	@Override
	public HD149Model findFid(long fid)
	{
		List<HD149Model> list=getJdbcTemplate().query("select * from es_feature_contentlist where id=?",new Object[]{fid}, new RowMapper(){@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException
		{
			HD149Model m=new HD149Model();
			m.setId(rs.getLong("id"));
			m.setPageid(rs.getLong("pageid"));
			m.setCategoryid(rs.getLong("categoryid"));
			m.setType(rs.getString("type"));
			m.setTopage(rs.getLong("topage"));
			return m;
		}});
		return list.size()>0?list.get(0):null;
	}

	private static final String UPDATE_CONTENT_CATEGORY_PAGEID="update es_content_category set pageid = ? where id = ?";
	@Override
	public int updateContentCategoryPageid(long cateid, long pageid)
	{
		Object[] param={pageid,cateid};
		return getJdbcTemplate().update(UPDATE_CONTENT_CATEGORY_PAGEID, param);
	}
}
