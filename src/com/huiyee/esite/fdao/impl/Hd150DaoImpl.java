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
import com.huiyee.esite.fdao.IHd150Dao;
import com.huiyee.esite.model.ContentCategory;
import com.huiyee.esite.model.HD150Model;

public class Hd150DaoImpl extends AbstractDao implements IHd150Dao{

	private static final String SAVE_FEATRUE_INTERACT = "insert into esite.es_feature_content_catlist (pageid,createtime) values(?,now())";
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
	public HD150Model findModelByFid(long fid) {
		List<HD150Model> list=getJdbcTemplate().query("select * from esite.es_feature_content_catlist where id=?",new Object[]{fid}, new RowMapper(){@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				HD150Model m=new HD150Model();
				m.setId(rs.getLong("id"));
				m.setPageid(rs.getLong("pageid"));
				m.setCategoryid(rs.getLong("categoryid"));
				m.setType(rs.getString("type"));
				m.setTopage(rs.getLong("topage"));
				return m;
			}});
			return list.size()>0?list.get(0):null;
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
				c.setFartherCatId(rs.getLong("fartherCatId"));
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
		return getJdbcTemplate().update("update esite.es_feature_content_catlist set categoryid=?, type=?, topage=? where id=?", new Object[]
		{ catid, type, topage, fid });
	}

	@Override
	public List<ContentCategory> findCategoryByCateid(long catid) {
		String sql = "select * from esite.es_content_category c where c.fartherCatId = ? and c.status!='DEL'";
		Object[] params = {catid};
		return getJdbcTemplate().query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				ContentCategory c = new ContentCategory();
				c.setId(rs.getLong("c.id"));
				c.setOwnerid(rs.getLong("c.ownerid"));
				c.setType(rs.getString("c.type"));
				c.setName(rs.getString("c.name"));
				c.setPic(rs.getString("c.pic"));
				c.setContent(rs.getString("c.content"));
				c.setFartherCatId(rs.getLong("c.fartherCatId"));
				c.setTypeid(rs.getLong("typeid"));
				return c;
			}
			
		});
	}
}
