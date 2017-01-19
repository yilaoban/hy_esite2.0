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
import com.huiyee.esite.fdao.IInteractSpreadDao;
import com.huiyee.esite.model.InteractSpread;
import com.huiyee.esite.model.PageBlockRelation;
import com.huiyee.esite.model.SpreadOption;
import com.huiyee.interact.spread.model.SpreadModel;

public class InteractSpreadDaoImpl extends AbstractDao implements IInteractSpreadDao
{

	private static final String SAVE_PAGEID = "insert into es_feature_interact_spread (pageid,createtime) values(?,now())";

	@Override
	public long savePageId(final long pageid)
	{
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator()
		{
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(SAVE_PAGEID, new String[]
				{ "id" });
				ps.setLong(1, pageid);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	@Override
	public InteractSpread findInteractSpread(long fid)
	{
		List<InteractSpread> list = getJdbcTemplate().query("select * from es_feature_interact_spread where id=?", new Object[]
		{ fid }, new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				InteractSpread is = new InteractSpread();
				is.setFid(rs.getLong("id"));
				is.setSpreadid(rs.getLong("spreadid"));
				is.setCreatetime(rs.getTimestamp("createtime"));
				return is;
			}
		});
		if (list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<InteractSpread> findInteractSpreadList(long id)
	{
		List<InteractSpread> list = getJdbcTemplate().query("select * from es_interact_spread where ownerid=? and status != 'DEL' order by id desc", new Object[]
		{ id }, new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				InteractSpread is = new InteractSpread();
				is.setId(rs.getLong("id"));
				is.setTitle(rs.getString("title"));
				is.setType(rs.getString("type"));
				is.setOmid(rs.getLong("omid"));
				return is;
			}
		});
		return list;
	}

	@Override
	public int updateSpreadid(long spreadid, long fid)
	{
		return getJdbcTemplate().update("update es_feature_interact_spread set spreadid=? where id=?", new Object[]
		{ spreadid, fid });
	}

	private static final String FIND_SPREAD_MODEL_BY_FID="select sp.* from esite.es_interact_spread sp join es_feature_interact_spread fsp on sp.id = fsp.spreadid where fsp.id = ?";
	@Override
	public SpreadModel findSpreadModelByFId(long fid) {
		Object[] params = { fid};
		List<SpreadModel> list = getJdbcTemplate().query(FIND_SPREAD_MODEL_BY_FID, params, new FeatureRowmapper());
		if(list != null && list.size()>0){
	 		return list.get(0);
	 	}
		return null;
	}
	
	class FeatureRowmapper implements RowMapper {
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			SpreadModel iv = new SpreadModel();
			iv.setId(rs.getLong("id"));
			iv.setOwnerid(rs.getLong("ownerid"));
			iv.setTitle(rs.getString("title"));
			iv.setContent(rs.getString("content"));
			iv.setStarttimeDate(rs.getTimestamp("starttime"));
			iv.setEndtimeDate(rs.getTimestamp("endtime"));
			iv.setSharelimit(rs.getInt("sharelimit"));
			iv.setBalance(rs.getInt("balance"));
			iv.setIslottery(rs.getString("islottery"));
			iv.setLotteryid(rs.getLong("lotteryid"));
			iv.setLotterychance(rs.getInt("lotterychance"));
			iv.setRepic(rs.getString("repic"));
			iv.setType(rs.getString("type"));
			iv.setStatus(rs.getString("status"));
			iv.setMaxlotterychance(rs.getInt("maxlotterychance"));
			iv.setStartnote(rs.getString("startnote"));
			iv.setEndnote(rs.getString("endnote"));
			return iv;
		}
	}

	private static final String FIND_OPTIONS_BY_SPREADID="select * from esite.es_interact_spread_option where spreadid = ? and status != 'DEL'";
	@Override
	public List<SpreadOption> findOptionsBySpreadid(long spreadid) {
		Object[] param={spreadid};
		return getJdbcTemplate().query(FIND_OPTIONS_BY_SPREADID, param, new SpreadOptionRowmapper());
	}
	
	class SpreadOptionRowmapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			SpreadOption o = new SpreadOption();
			o.setId(rs.getLong("id"));
			o.setSpreadid(rs.getLong("spreadid"));
			o.setWbid(rs.getString("wbid"));
			o.setTitle(rs.getString("title"));
			o.setContent(rs.getString("content"));
			o.setPic(rs.getString("pic"));
			o.setCreatetime(rs.getTimestamp("createtime"));
			return o;
		}
		
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
