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
import com.huiyee.esite.fdao.IHd118Dao;
import com.huiyee.esite.model.Feature118InteractApt;
import com.huiyee.esite.model.Module;
import com.huiyee.esite.model.PageBlockRelation;
import com.huiyee.interact.appointment.model.AppointmentModel;
import com.huiyee.interact.appointment.model.OrderMappingModel;

public class Hd118DaoImpl extends AbstractDao implements IHd118Dao{

	private static final String SAVE_FEATRUE_INTERACT = "insert into esite.es_feature_interact_apt (pageid,createtime) values(?,now())";
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
	
	class Feature118Rowmapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Feature118InteractApt apt = new Feature118InteractApt();
			apt.setId(rs.getLong("id"));
			apt.setTitle(rs.getString("title"));
			apt.setContent(rs.getString("content"));
			return apt;
		}

	}
	
	private static final String FIND_APPOINTMENTS_BY_OWNER = "select a.id,a.title,a.content from esite.es_interact_apt a  where a.ownerid = ? and a.status != 'DEL' and a.omid=1 order by a.id desc";
	@Override
	public List<Feature118InteractApt> findApponitmentsByOwner(long ownerid) {
		Object[] params = { ownerid };
		return getJdbcTemplate().query(FIND_APPOINTMENTS_BY_OWNER, params, new Feature118Rowmapper());
	}
	
	private static final String UPDATE_FEATURE_INTERACT_APT = "update esite.es_feature_interact_apt a set a.aptid = ? where a.id = ? ";
	@Override
	public int updateFeatureIneractApt(long aptid, long fid) {
		Object[] params = { aptid,fid};
		return getJdbcTemplate().update(UPDATE_FEATURE_INTERACT_APT, params);
	}
	
	private static final String UPDATE_FEATURE_INTERACT_APT_NAME = "update esite.es_feature_interact_apt a set a.name = ? where a.id = ?";
	@Override
	public int updateupdateFeatureIneractAptName(long fid, String name) {
		Object[] params = { name,fid};
		return getJdbcTemplate().update(UPDATE_FEATURE_INTERACT_APT_NAME, params);
	}
	
	private static final String FIND_CONTENT_BY_ID = "select s.id,s.title,s.content from esite.es_feature_interact_apt a where a.id = ?";
	@Override
	public Feature118InteractApt findFeature118InteractAptById(long fid) {
		Object[] params = { fid };
		List<Feature118InteractApt> list = getJdbcTemplate().query(FIND_CONTENT_BY_ID, params, new Feature118Rowmapper());
		if(list !=null && list.size()>0){
	 		return list.get(0);
	 	}
		return null;
	}
	
	private static final String FIND_APTID_BY_FID = "select a.* from esite.es_feature_interact_apt a where id = ? ";
	@Override
	public Module findAptidByFid(long fid) {
		List<Module> list = getJdbcTemplate().query(FIND_APTID_BY_FID, new Object[] { fid }, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				Module m = new Module();
				m.setId(rs.getLong("aptid"));
				m.setName(rs.getString("name"));
				m.setPageid(rs.getLong("pageid"));
				return m;
			}
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	class FeatureInteractAptMappingRowmapper implements RowMapper {
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			OrderMappingModel apt = new OrderMappingModel();
			apt.setAptid(rs.getLong("aptid"));
			apt.setName(rs.getString("name"));
			apt.setMapping(rs.getString("mapping"));
			apt.setColtype(rs.getString("coltype"));
			apt.setStype(rs.getString("stype"));
			apt.setDefaultvalue(rs.getString("defaultvalue"));
			apt.setIsshow(rs.getString("isshow"));
			apt.setRow(rs.getInt("row"));
			apt.setTag(rs.getString("tag"));
			apt.setReq(rs.getString("req"));
			return apt;
		}

	}
	
	private static final String FIND_INTERACT_APT_MAPPING_BY_FID = "select s.* from esite.es_feature_interact_apt a join esite.es_interact_apt_mapping s on a.aptid= s.aptid where a.id = ? and s.isshow = 'Y' order by s.row";
	@Override
	public List<OrderMappingModel> findInteractAptMappingByFId(long fid) {
		Object[] params = { fid };
		return getJdbcTemplate().query(FIND_INTERACT_APT_MAPPING_BY_FID, params,new FeatureInteractAptMappingRowmapper());
	}
	
	private static final String FIND_INTERACT_APT_MAPPING_BY_ID = "select s.* from esite.es_interact_apt a join esite.es_interact_apt_mapping s on a.id= s.aptid where a.id = ? and s.isshow = 'Y' order by s.row";
	@Override
	public List<OrderMappingModel> findInteractAptMappingById(long fid) {
		Object[] params = { fid };
		return getJdbcTemplate().query(FIND_INTERACT_APT_MAPPING_BY_ID, params,new FeatureInteractAptMappingRowmapper());
	}
	
	class FeatureRowmapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			AppointmentModel apt = new AppointmentModel();
			apt.setId(rs.getLong("id"));
			apt.setOwnerid(rs.getLong("ownerid"));
			apt.setTitle(rs.getString("title"));
			apt.setContent(rs.getString("content"));
			apt.setStarttimeDate(rs.getTimestamp("starttime"));
			apt.setEndtimeDate(rs.getTimestamp("endtime"));
			apt.setTotallimit(rs.getInt("totallimit"));
			apt.setStatus(rs.getString("status"));
			apt.setIslottery(rs.getString("islottery"));
			apt.setLotteryid(rs.getLong("lotteryid"));
			apt.setLotterychance(rs.getInt("lotterychance"));
			apt.setBalance(rs.getInt("balance"));
			apt.setMaxlotterychance(rs.getInt("maxlotterychance"));
			apt.setStartnote(rs.getString("startnote"));
			apt.setEndnote(rs.getString("endnote"));
			return apt;
		}

	}
	
	private static final String FIND_INTERACT_BY_ID = "select s.*,a.aptid from esite.es_feature_interact_apt a join esite.es_interact_apt s on a.aptid= s.id where a.id = ?";
	@Override
	public AppointmentModel findFeatureInteractAptById(long fid) {
		Object[] params = { fid };
		List<AppointmentModel> list = getJdbcTemplate().query(FIND_INTERACT_BY_ID, params, new FeatureRowmapper());
		if(list !=null && list.size()>0){
	 		return list.get(0);
	 	}
		return null;
	}
	
	@Override
	public AppointmentModel findFeatureInteractAptByAptid(long aptid) {
		String sql = "select s.*,a.aptid from esite.es_feature_interact_apt a join esite.es_interact_apt s on a.aptid= s.id where s.id = ?";
		Object[] params = { aptid };
		List<AppointmentModel> list = getJdbcTemplate().query(sql, params, new FeatureRowmapper());
		if(list !=null && list.size()>0){
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

}
