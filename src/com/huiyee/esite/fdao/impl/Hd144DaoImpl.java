package com.huiyee.esite.fdao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.fdao.IHd144Dao;
import com.huiyee.esite.model.Feature118InteractApt;
import com.huiyee.esite.model.RenQiRecord;
import com.huiyee.interact.renqi.model.RenQi;

public class Hd144DaoImpl extends AbstractDao implements IHd144Dao
{

	private static final String SAVE_FEATRUE_INTERACT = "insert into esite.es_feature_rq (pageid,createtime) values(?,now())";
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
	
	private String FIND_RQID_BY_FID = "select rqid from es_feature_rq where id = ? ";
	@Override
	public int findRqidByFid(long fid)
	{
		Object[] params = {fid};
		return getJdbcTemplate().queryForInt(FIND_RQID_BY_FID, params);
	}
	
	class RenQiRowmapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			RenQi rq = new RenQi();
			rq.setTitle(rs.getString("title"));
			rq.setId(rs.getLong("id"));
			rq.setOwner(rs.getLong("owner"));
			rq.setStartnote(rs.getString("startnote"));
			rq.setEndnote(rs.getString("endnote"));
			rq.setLotteryid(rs.getLong("lotteryid"));
			rq.setMaxlu(rs.getInt("maxlu"));
			rq.setCnum(rs.getInt("cnum"));
			rq.setUtype(rs.getInt("utype"));
			rq.setContent(rs.getString("content"));
			rq.setLink(rs.getString("link"));
			rq.setWxtitle(rs.getString("wxtitle"));
			rq.setWximg(rs.getString("wximg"));
			rq.setWxdesc(rs.getString("wxdesc"));
			rq.setMinjf(rs.getInt("minjf"));
			rq.setMaxjf(rs.getInt("maxjf"));
			rq.setStarttimeDate(rs.getTimestamp("starttime"));
			rq.setEndtimeDate(rs.getTimestamp("endtime"));
			return rq;
		}

	}
	
	private String FIND_RENQI_BY_OWNER = "select * from es_interact_rq where owner = ?";
	@Override
	public List<RenQi> findRenQiByOwner(long ownerid) {
		Object[] params = {ownerid};
		return getJdbcTemplate().query(FIND_RENQI_BY_OWNER, params,new RenQiRowmapper());
	}
	
	private String UPDATE_FEATURE_INTERACT_RQ = "update es_feature_rq set rqid = ? where id = ?";
	@Override
	public int updateFeatureIneractRq(long rqid, long fid)
	{
		Object[] params = {rqid,fid};
		return  getJdbcTemplate().update(UPDATE_FEATURE_INTERACT_RQ, params);
	}
	
	private static final String FIND_REN_QI_BY_PAGEID="select t1.* from es_interact_rq t1 join es_feature_rq t2 on t1.id = t2.rqid where t2.id = ?";
	@Override
	public RenQi findRenQiByFId(long fid) {
		Object[] params = {fid};
		List<RenQi> list = getJdbcTemplate().query(FIND_REN_QI_BY_PAGEID, params,new RenQiRowmapper());
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	private static final String FIND_REN_QI_RECORD="select r.*,u.nickname dname from es_interact_rq_duid_draw r left outer join es_wx_user u on r.duid = u.id where r.rqid = ? and r.fuid = ? and r.utype=? order by r.id desc limit ?";
	@Override
	public List<RenQiRecord> findRenQiRecord(long rqid,long duid,int type,int size) {
		Object[] params={rqid,duid,type,size};
		return getJdbcTemplate().query(FIND_REN_QI_RECORD, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				RenQiRecord r = new RenQiRecord();
				r.setId(rs.getLong("id"));
				r.setRqid(rs.getLong("rqid"));
				r.setDuid(rs.getLong("duid"));
				r.setFuid(rs.getLong("fuid"));
				r.setUtype(rs.getInt("utype"));
				r.setAddjf(rs.getInt("addjf"));
				r.setIp(rs.getString("ip"));
				r.setTerminal(rs.getString("terminal"));
				r.setSource(rs.getString("source"));
				r.setCreatetime(rs.getTimestamp("createtime"));
				r.setDname(rs.getString("dname"));
				return r;
			}
				
		});
	}
	
	private static final String FIND_IS_JOIN_BY_VISIT="select count(id) from es_interact_rq_duid_draw where rqid = ? and fuid = ? and duid = ?";
	@Override
	public int findIsJoinByVisit(long rqid,long fuid, long duid) {
		Object[] params={rqid,fuid,duid};
		return getJdbcTemplate().queryForInt(FIND_IS_JOIN_BY_VISIT,params);
	}
	
	private static final String FIND_IS_SEND_BY_VISIT="select count(id) from es_interact_rq_fuid_jf where rqid = ? and fuid = ?";
	@Override
	public int findIsSendByVisit(long rqid, long fuid) {
		Object[] params={rqid,fuid};
		return getJdbcTemplate().queryForInt(FIND_IS_SEND_BY_VISIT,params);
	}
	
	private static final String FIND_TOATL_BY_FUID="select totalnum from es_interact_rq_fuid_jf where rqid = ? and fuid = ?";
	@Override
	public int findTotalByFuid(long rqid, long fuid) {
		Object[] params={rqid,fuid};
		try {
			return getJdbcTemplate().queryForInt(FIND_TOATL_BY_FUID,params);
		} catch (DataAccessException e) {
			return 0;
		}
	}
}
