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
import com.huiyee.esite.fdao.IHd122Dao;
import com.huiyee.interact.EmailPeriodical.model.EmailPeriodicalModel;

public class Hd122DaoImpl extends AbstractDao implements IHd122Dao{

	private static final String SAVE_FEATRUE_INTERACT = "insert into esite.es_feature_email_publish (pageid,createtime) values(?,now())";
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
	
	class Feature122Rowmapper implements RowMapper {
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			EmailPeriodicalModel ep = new EmailPeriodicalModel();
			ep.setId(rs.getLong("id"));
			ep.setTitle(rs.getString("title"));
			ep.setUrl(rs.getString("url"));
			ep.setPublish(rs.getString("publish"));
			ep.setName(rs.getString("name"));
			return ep;
		}
	}
	
	private static final String FIND_EMAILPERIODICAL_BY_OWNER = "select a.id,a.title,a.url,a.publish,s.name from esite.es_feature_publish_list s right join esite.es_interact_email_publish a on s.pid = a.id and s.fid = ? where a.ownerid = ? and a.status != 'DEL'";
	@Override
	public List<EmailPeriodicalModel> findEmailPeriodicalByOwner(long ownerid,long fid) {
		Object[] params = { fid,ownerid};
		return getJdbcTemplate().query(FIND_EMAILPERIODICAL_BY_OWNER, params, new Feature122Rowmapper());
	}
	
	private static final String DELETE_FEATURE_PUBLISH = "delete from esite.es_feature_publish_list where fid = ?";
	@Override
	public int deleteFeaturePublish(long fid) {
		Object[] params = {fid};
		return getJdbcTemplate().update(DELETE_FEATURE_PUBLISH, params);
	}
	
	private static final String SAVE_FEATURE_PUBLISH = "insert into esite.es_feature_publish_list(fid,pid,name,idx,createtime) values(?,?,?,?,now())";
	@Override
	public int saveFeaturePublish(long fid, long pid, String name, long idx) {
		Object[] params={fid,pid,name,idx};
		return getJdbcTemplate().update(SAVE_FEATURE_PUBLISH,params);
	}
	
	private static final String FIND_FEATURE_PUBLISH_BY_FID = "select pid from esite.es_feature_publish_list where fid = ?";
	@Override
	public List<Long> findFeaturePublishByFid(long fid) {
		Object[] params={fid};
		try {
			return getJdbcTemplate().queryForList(FIND_FEATURE_PUBLISH_BY_FID,params, Long.class);
		} catch (Exception e) {
			return null;
		}
	}
	
	class Feature122ShowRowmapper implements RowMapper {
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			EmailPeriodicalModel ep = new EmailPeriodicalModel();
			ep.setId(rs.getLong("id"));
			ep.setTitle(rs.getString("title"));
			ep.setUrl(rs.getString("url"));
			ep.setName(rs.getString("name"));
			return ep;
		}
	}
	
	private static final String FIND_URL_BY_FID = "select a.id,a.title,a.url,s.name from esite.es_feature_publish_list s join esite.es_interact_email_publish a on s.pid = a.id where s.fid = ? order by s.idx";
	@Override
	public List<EmailPeriodicalModel> findUrlByFid(long fid) {
		Object[] params = { fid };
		return getJdbcTemplate().query(FIND_URL_BY_FID, params, new Feature122ShowRowmapper());
	}
	
	private static final String FIND_EMAILPERIODICAL_MODEL_BY_FID = "select a.id,a.title,a.url,s.name from esite.es_feature_publish_list s join esite.es_interact_email_publish a on s.pid = a.id where s.fid = ? and a.publish = 'Y' ";
	@Override
	public EmailPeriodicalModel findEmailPeriodicalModelByFid(long fid) {
		Object[] params = { fid };
		List<EmailPeriodicalModel> list = getJdbcTemplate().query(FIND_EMAILPERIODICAL_MODEL_BY_FID, params, new Feature122ShowRowmapper());
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	private static final String FIND_EMAILPERIODICAL_BY_FID = "select a.id,a.title,a.url,s.name from esite.es_feature_publish_list s join esite.es_interact_email_publish a on s.pid = a.id where a.id = ? ";
	@Override
	public EmailPeriodicalModel findEmailPeriodicalByFid(long pid) {
		Object[] params = { pid };
		List<EmailPeriodicalModel> list = getJdbcTemplate().query(FIND_EMAILPERIODICAL_BY_FID, params, new Feature122ShowRowmapper());
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
}
