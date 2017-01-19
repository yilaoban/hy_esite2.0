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
import com.huiyee.esite.fdao.IHd145Dao;
import com.huiyee.esite.model.CsFuidDraw;
import com.huiyee.interact.cs.model.Cs;

public class Hd145DaoImpl extends AbstractDao implements IHd145Dao {
	private static final String SAVE_FEATRUE_INTERACT = "insert into esite.es_feature_cs (pageid,createtime) values(?,now())";
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
	
	private String FIND_CSID_BY_FID = "select csid from es_feature_cs where id = ? ";
	@Override
	public int findCsidByFid(long fid) {
		Object[] params = {fid};
		return getJdbcTemplate().queryForInt(FIND_CSID_BY_FID, params);
	}
	
	class CsRowmapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Cs cs = new Cs();
			cs.setId(rs.getLong("id"));
			cs.setTitle(rs.getString("title"));
			cs.setOwner(rs.getLong("owner"));
			cs.setStartnote(rs.getString("startnote"));
			cs.setEndnote(rs.getString("endnote"));
			cs.setUtype(rs.getInt("utype"));
			cs.setContent(rs.getString("content"));
			cs.setLink(rs.getString("link"));
			cs.setJcontent(rs.getString("jcontent"));
			cs.setStarttimeDate(rs.getTimestamp("starttime"));
			cs.setEndtimeDate(rs.getTimestamp("endtime"));
			return cs;
		}

	}
	
	private String FIND_CS_BY_OWNER = "select * from es_interact_cs where owner = ?";
	@Override
	public List<Cs> findCsByOwner(long ownerid) {
		Object[] params = {ownerid};
		return getJdbcTemplate().query(FIND_CS_BY_OWNER, params,new CsRowmapper());
	}
	
	private String UPDATE_FEATURE_INTERACT_CS = "update es_feature_cs set csid = ? where id = ?";
	@Override
	public int updateFeatureIneractCs(long csid, long fid)
	{
		Object[] params = {csid,fid};
		return  getJdbcTemplate().update(UPDATE_FEATURE_INTERACT_CS, params);
	}
	
	private String FIND_CS_BY_FID = "select cs.* from es_feature_cs f join es_interact_cs cs on f.csid = cs.id where f.id = ?";
	@Override
	public Cs findCsByFid(long fid)
	{
		Object[] params = {fid};
		List<Cs> list = getJdbcTemplate().query(FIND_CS_BY_FID, params,new CsRowmapper());
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	private String FIND_CS_FUID_BYID = "select * from es_interact_cs_fuid_draw where id = ?";
	@Override
	public CsFuidDraw findCsFuidDrawByid(long id)
	{
		Object[] params = {id};
		List<CsFuidDraw> list = getJdbcTemplate().query(FIND_CS_FUID_BYID, params,new CsFuidDrawRowmapper());
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	class CsFuidDrawRowmapper implements RowMapper {
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			CsFuidDraw cs = new CsFuidDraw();
			cs.setId(rs.getLong("id"));
			cs.setCsid(rs.getLong("csid"));
			cs.setFuid(rs.getLong("fuid"));
			cs.setUtype(rs.getInt("utype"));
			cs.setJcontent(rs.getString("jcontent"));
			return cs;
		}

	}
	
}
