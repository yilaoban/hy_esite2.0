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
import com.huiyee.esite.fdao.IHd160Dao;
import com.huiyee.esite.model.ContentCategory;
import com.huiyee.esite.model.HD154Model;
import com.huiyee.esite.model.PageBlockRelation;

public class Hd160DaoImpl extends AbstractDao implements IHd160Dao {

	private static final String SAVE_FEATRUE_INTERACT = "insert into esite.es_feature_interact_content (pageid,createtime) values(?,now())";
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
	public HD154Model findForumidListByFid(long fid) {
		String sql = "select c.* from esite.es_feature_interact_content c where id = ?"; 
		Object[] params = {fid};
		List<HD154Model> list = getJdbcTemplate().query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				HD154Model m = new HD154Model();
				m.setIds(rs.getString("c.ids"));
				m.setId(rs.getLong("c.id"));
				m.setPageid(rs.getLong("c.pageid"));
				m.setType(rs.getString("type"));
				m.setTopage(rs.getLong("topage"));
				return m;
			}
			
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
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
	public List<ContentCategory> findCategory(long id)
	{
		return getJdbcTemplate().query("select * from esite.es_content_category c where c.ownerid=? and c.status!='DEL' and c.subtype='J'", new Object[]
		{ id }, new RowMapper()
		{
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				ContentCategory c = new ContentCategory();
				c.setId(rs.getLong("c.id"));
				c.setType(rs.getString("c.type"));
				c.setName(rs.getString("c.name"));
				c.setFartherCatId(rs.getLong("c.fartherCatId"));
				return c;
			}
		});
	}
	@Override
	public int saveFid(long fid, String ids, String type, long topage)
	{
		return getJdbcTemplate().update("update es_feature_interact_content set ids=?, type=?, topage=? where id=?", 
				new Object[]{ ids, type, topage, fid });
	}
	
	private static final String UPDATE_CONTENT_CATEGORY_PAGEID="update es_content_category set pageid = ? where id = ?";
	@Override
	public int updateContentCategoryPageid(long cateid, long pageid)
	{
		Object[] param={pageid,cateid};
		return getJdbcTemplate().update(UPDATE_CONTENT_CATEGORY_PAGEID, param);
	}
	
}
