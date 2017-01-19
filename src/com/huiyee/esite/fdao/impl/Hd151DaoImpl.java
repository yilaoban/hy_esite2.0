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
import com.huiyee.esite.fdao.IHd151Dao;
import com.huiyee.esite.model.BBSForum;
import com.huiyee.esite.model.HD151Model;
import com.huiyee.esite.model.PageBlockRelation;

public class Hd151DaoImpl extends AbstractDao implements IHd151Dao {

	private static final String SAVE_FEATRUE_INTERACT = "insert into esite.es_feature_interact_community (pageid,createtime) values(?,now())";
	@Override
	public long saveFeatureInteract(final long pageid) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
                PreparedStatement ps = arg0.prepareStatement(SAVE_FEATRUE_INTERACT, new String[] { "id" });
                int i = 1;
                ps.setLong(i++, pageid);
                return ps;
            }
        }, keyHolder);
        long id = keyHolder.getKey().intValue();
        return id;
	}
	@Override
	public List<BBSForum> findForumListByOwnerid(long ownerid, long accid) {
		String sql = "select * from hybbs.bbs_category c join hybbs.bbs_forum f on c.id = f.cateid join hybbs.bbs_forum_account a on a.forumid = f.id where c.owner = ? and a.accid = ? and f.status != 'DEL' and c.name = 'ÂÛÌ³' order by f.createtime";
		Object[] params = {ownerid,accid};
		return getJdbcTemplate().query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				BBSForum f = new BBSForum();
				f.setId(rs.getLong("f.id"));
				f.setCateid(rs.getLong("f.cateid"));
				f.setTitle(rs.getString("f.title"));
				f.setCreatetime(rs.getTimestamp("f.createtime"));
				f.setTopicnum(rs.getInt("f.topicnum"));
				f.setCatname(rs.getString("c.name"));
				return f;
			}
			
		});
	}
	@Override
	public HD151Model findForumidListByFid(long fid) {
		String sql = "select c.* from esite.es_feature_interact_community c where id = ?"; 
		Object[] params = {fid};
		List<HD151Model> list = getJdbcTemplate().query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				HD151Model m = new HD151Model();
				m.setForumid(rs.getString("c.forumid"));
				m.setId(rs.getLong("c.id"));
				m.setPageid(rs.getLong("c.pageid"));
				return m;
			}
			
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	@Override
	public int updateFeatureIneractCommunity(String forumid, long fid) {
		String sql = "update esite.es_feature_interact_community c set c.forumid = ? where c.id = ? ";
		Object[] params = {forumid,fid};
		return getJdbcTemplate().update(sql, params);
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
	public BBSForum findBBSForumById(long id) {
		String sql = "select f.* from hybbs.bbs_forum f where f.id = ? and f.status != 'DEL'";
		Object[] params = {id};
		List<BBSForum> list = getJdbcTemplate().query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				BBSForum f = new BBSForum();
				f.setId(rs.getLong("f.id"));
				f.setCateid(rs.getLong("f.cateid"));
				f.setLastpostid(rs.getLong("f.lastpostid"));
				f.setLastposter(rs.getLong("f.lastposter"));
				f.setTitle(rs.getString("f.title"));
				f.setHydesc(rs.getString("f.hydesc"));
				f.setHyrule(rs.getString("f.hyrule"));
				f.setRank(rs.getInt("rank"));
				f.setTopicnum(rs.getInt("f.topicnum"));
				f.setPostnum(rs.getInt("f.postnum"));
				f.setPosttodaynum(rs.getInt("f.posttodaynum"));
				f.setCreatetime(rs.getTimestamp("f.createtime"));
				f.setVisittype(rs.getInt("f.visittype"));
				f.setForumer(rs.getLong("f.forumer"));
				f.setForumname(rs.getString("f.forumname"));
				f.setTopicCheck(rs.getString("f.topicCheck"));
				f.setCommentCheck(rs.getString("f.commentCheck"));
				f.setLoginpageid(rs.getLong("f.loginpageid"));
				f.setRegisterpageid(rs.getLong("f.registerpageid"));
				return f;
			}
			
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
}
