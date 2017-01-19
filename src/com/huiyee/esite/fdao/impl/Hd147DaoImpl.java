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
import com.huiyee.esite.fdao.IHd147Dao;
import com.huiyee.esite.model.ContentCategory;
import com.huiyee.esite.model.ContentNew;
import com.huiyee.esite.model.FeatureNews;
import com.huiyee.esite.model.FeatureNewslist;

public class Hd147DaoImpl extends AbstractDao implements IHd147Dao
{
	private static final String ADD_NEWS_LIST = "insert into es_feature_newslist (pageid,name) values(?,now())";
	@Override
	public long addNewsList(final long pageid)
	{
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(ADD_NEWS_LIST, new String[] { "id" });
				ps.setLong(1, pageid);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}
	
	
	@Override
	public List<ContentCategory> findContentCategoryListByOwner(long ownerid)
	{
		String sql = "select * from es_content_category cc where cc.ownerid= ? and cc.type = 'N' and cc.status != 'DEL'";
		Object[] params = {ownerid};
		return getJdbcTemplate().query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				ContentCategory cc= new ContentCategory();
				cc.setId(rs.getLong("cc.id"));
				cc.setName(rs.getString("cc.name"));
				return cc;
			}
		});
	}


	@Override
	public FeatureNewslist findCatidByFid(long fid)
	{
		String sql = "select * from es_feature_newslist where id = ?";
		Object[] params = {fid};
		List<FeatureNewslist> list  =getJdbcTemplate().query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				FeatureNewslist l = new FeatureNewslist();
				l.setPageid(rs.getLong("pageid"));
				l.setCategoryid(rs.getLong("categoryid"));
				l.setXpid(rs.getLong("xqpageid"));
				return l;
			}
			
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}


	private static final String FIND_NEWS_LIST_BY_FID = "select * from es_feature_newslist f join es_content_news news on f.categoryid = news.catid where f.id=? and news.status='CMP'";
	@Override
	public List<FeatureNews> findNewsListByFid(long fid)
	{
		return getJdbcTemplate().query(FIND_NEWS_LIST_BY_FID, new Object[] { fid }, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				FeatureNews fn = new FeatureNews();
				ContentNew cn = new ContentNew();
				cn.setId(rs.getLong("news.id"));
				cn.setOwnerid(rs.getLong("news.ownerid"));
				cn.setCatid(rs.getLong("news.catid"));
				cn.setTitle(rs.getString("news.title"));
				cn.setContent(rs.getString("news.content"));
				cn.setStatus(rs.getString("news.status"));
				cn.setCreatetime(rs.getTimestamp("news.createtime"));
				cn.setUpdatetime(rs.getTimestamp("news.updatetime"));
				cn.setBimgurl(rs.getString("news.bimgurl"));
				cn.setSimgurl(rs.getString("news.simgurl"));
				cn.setShortdesc(rs.getString("news.shortdesc"));
				fn.setNews(cn);
				return fn;
			}
		});
	}


	private static final String DELETE_ALL_RELATION = "update es_feature_newslist_news set status='DEL' where nlid= ? ";
	@Override
	public void deleteAllRelation(long fid)
	{
		getJdbcTemplate().update(DELETE_ALL_RELATION, new Object[] { fid });
	}


	@Override
	public List<ContentNew> findNewListByCatid(long catid)
	{
		String sql = "select * from es_content_category cc join es_content_news n on cc.id = n.catid where cc.id = ? and n.status = 'CMP'";
		Object[] params = {catid};
		return getJdbcTemplate().query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
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
				cn.setIslink(rs.getString("n.islink"));
				return cn;
			}
			
		});
	}

	@Override
	public void updateFeatureNnewslistCatidByFid(long fid, long catid,long xqpageid)
	{
		String sql = "update es_feature_newslist set categoryid = ? , xqpageid = ? where id = ?";
		Object[] parms = {catid,xqpageid,fid};
		getJdbcTemplate().update(sql, parms);
	}


	private static final String FIND_IDX_BY_FID = "select max(idx) from es_feature_newslist_news where nlid = ? and status != 'DEL' ";
	@Override
	public int findIdxByfid(long fid)
	{
		return getJdbcTemplate().queryForInt(FIND_IDX_BY_FID, new Object[] { fid });
	}

	private static final String ADD_RELATION = "insert into es_feature_newslist_news(nlid,nid,idx,status) values (?,?,?,'CMP') on duplicate key update status ='CMP',idx=? ";
	@Override
	public void addReation(long fid, long nid, int idx)
	{
		Object[] parms = {fid,nid,idx,idx};
		getJdbcTemplate().update(ADD_RELATION, parms);
		
	}

}
