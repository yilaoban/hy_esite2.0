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
import com.huiyee.esite.fdao.INewsListDao;
import com.huiyee.esite.model.CategoryTree;
import com.huiyee.esite.model.ContentNew;
import com.huiyee.esite.model.FeatureNews;
import com.huiyee.esite.model.FeatureNewslist;
import com.huiyee.esite.model.PageBlockRelation;

public class NewsListDaoImpl extends AbstractDao implements INewsListDao {
	private static final String ADD_NEWS_LIST = "insert into es_feature_newslist (pageid,name) values(?,now())";

	public long addNewsList(final long pageid) {
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

	private static final String FIND_NEWS_LIST_BY_FID = "select * from es_feature_newslist  f  join es_feature_newslist_news relation on  f.id=relation.nlid  join es_content_news news on relation.nid=news.id where f.id=? and relation.status!='DEL' and news.status!='DEL' order by relation.idx asc ";

	public List<FeatureNews> findNewsListByFid(long fid) {
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
				fn.setNews(cn);
				fn.setId(rs.getLong("relation.id"));
				fn.setIdx(rs.getInt("relation.idx"));
				fn.setNid(rs.getLong("relation.nid"));
				fn.setNlid(rs.getLong("relation.nlid"));
				fn.setStatus(rs.getString("relation.status"));
				return fn;
			}
		});
	}

	private static final String ADD_RELATION = "insert into es_feature_newslist_news (nlid,nid,idx,status) values (?,?,?,'CMP') on duplicate key update status ='CMP',idx=? ";

	public void addReation(FeatureNews fn) {
		getJdbcTemplate().update(ADD_RELATION, new Object[] { fn.getNlid(), fn.getNid(), fn.getIdx(), fn.getIdx() });
	}

	private static final String DELETE_ALL_RELATION = "update es_feature_newslist_news set status='DEL' where nlid= ? ";

	public void deleteAllRelation(long fid) {
		getJdbcTemplate().update(DELETE_ALL_RELATION, new Object[] { fid });
	}

	private static final String FIND_IDX_BY_FID = "select max(idx) from es_feature_newslist_news where nlid = ? ";

	public int findIdxByfid(long fid) {
		return getJdbcTemplate().queryForInt(FIND_IDX_BY_FID, new Object[] { fid });
	}
	
	private static final String FIND_NEWS_BY_OWNER = "select * from es_content_news where ownerid= ? ";
	@Override
	public List<ContentNew> findNewsByOwner(long owner) {
		return getJdbcTemplate().query(FIND_NEWS_BY_OWNER, new Object[] { owner }, new MyRowMapper());
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
			cn.setCreatetime(rs.getTimestamp("createtime"));
			cn.setUpdatetime(rs.getTimestamp("updatetime"));
			return cn;
		}
	}

	@Override
	public List<Long> findFeatureNewListByFid(long fid) {
		String sql ="select nid from esite.es_feature_newslist_news where nlid = ? and status != 'DEL'";
		Object[] params={fid};
		try {
			return getJdbcTemplate().queryForList(sql,params, Long.class);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public int saveFeatureNewslistNews(long fid, long nid, long idx) {
		String sql = "insert into es_feature_newslist_news (nlid,nid,idx,status) values (?,?,?,'CMP') on duplicate key update status ='CMP',idx=? ";
		return getJdbcTemplate().update(ADD_RELATION, new Object[] { fid, nid, idx, idx });
	}
	
	private static final String FIND_PAGE_BLOCK_RELATION_BY_BLOCKID="select r.*,b.cid,pf.fid from es_page_block_relation r join es_template_card_block b on r.cbid = b.id join es_page_feature pf on r.pfid = pf.id  where r.id = ?";
	@Override
	public PageBlockRelation findPageBlockRelationByRelationid(long relationid) {
		Object[] params={relationid};
		List<PageBlockRelation> list = getJdbcTemplate().query(FIND_PAGE_BLOCK_RELATION_BY_BLOCKID, params, new RowMapper(){
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				PageBlockRelation pbr = new PageBlockRelation();
				pbr.setId(rs.getLong("id"));
				pbr.setCbid(rs.getLong("cbid"));
				pbr.setPcid(rs.getLong("pcid"));
				pbr.setJson(rs.getString("json"));
				pbr.setCid(rs.getLong("cid"));
				pbr.setPfid(rs.getLong("pfid"));
				pbr.setFid(rs.getLong("fid"));
				return pbr;
			}
		});
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public int updatePageBlockRelationByRelationid(long relationid, String json) {
		String sql = "update es_page_block_relation r set r.json = ? where r.id = ?";
		Object[] params = { json,relationid};
		return getJdbcTemplate().update(sql, params);
	}

	@Override
	public ContentNew findNewsByNid(long id) {
		String sql = "select * from es_content_news where id = ?";
		Object[] params = { id};
		List<ContentNew> list = getJdbcTemplate().query(sql, params, new MyRowMapper());
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	private static final String FIND_TREE_BY_TYPE = "select * from es_content_category where type=? and status !='DEL' and (ownerid =? or ownerid is null) order by id asc";
	@Override
	public List<CategoryTree> findTreeByType(String type,long owner) {
		return getJdbcTemplate().query(FIND_TREE_BY_TYPE, new Object[] { type,owner }, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				CategoryTree t = new CategoryTree();
				t.setId(rs.getLong("id"));
				t.setPId(rs.getLong("fartherCatId"));
				t.setName(rs.getString("name"));
				return t;
			}
		});
	}
	
	@Override
	public void updateFeatureNnewslistXpidByFid(long fid,long xqpageid)
	{
		String sql = "update es_feature_newslist set xqpageid = ? where id = ?";
		Object[] parms = {xqpageid,fid};
		getJdbcTemplate().update(sql, parms);
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
	
	private static final String UPDATE_CONTENT_CATEGORY_PAGEID="update es_content_category set pageid = ? where id = ?";
	@Override
	public int updateContentCategoryPageid(long cateid, long pageid)
	{
		Object[] param={pageid,cateid};
		return getJdbcTemplate().update(UPDATE_CONTENT_CATEGORY_PAGEID, param);
	}
}
