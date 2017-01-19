package com.huiyee.esite.fdao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.fdao.IHd126Dao;
import com.huiyee.esite.model.Module;
import com.huiyee.esite.model.PageBlockRelation;
import com.huiyee.interact.auction.model.Auction;

public class Hd126DaoImpl extends AbstractDao implements IHd126Dao {

	private static final String SAVE_FEATRUE_INTERACT = "insert into esite.es_feature_interact_auction (pageid,createtime) values(?,now())";
	@Override
	public long saveFeatureInteractAuction(final long pageid) {
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
	
	private static final String FIND_AUCTIONID_BY_FID = "select a.auctionid from esite.es_feature_interact_auction a where id = ? ";
	@Override
	public Module findAuctionidByFid(long fid) {
		List<Module> list = getJdbcTemplate().query(FIND_AUCTIONID_BY_FID, new Object[] { fid }, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				Module m = new Module();
				m.setId(rs.getLong("auctionid"));
				return m;
			}
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	class FeatureAuctionRowmapper implements RowMapper {
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Auction iv = new Auction();
			iv.setId(rs.getLong("id"));
			iv.setTitle(rs.getString("title"));
			iv.setOmid(rs.getLong("omid"));
			return iv;
		}
	}
	
	private static final String FIND_INTERACT_VOTE_BY_OWNER = "select a.id,a.title,a.omid from esite.es_interact_auction a where a.owner = ? and a.status != 'D' order by a.id desc";
	@Override
	public List<Auction> findInteractAuctionByOwner(long ownerid) {
		Object[] params = { ownerid };
		return getJdbcTemplate().query(FIND_INTERACT_VOTE_BY_OWNER, params, new FeatureAuctionRowmapper());
	}
	
	private static final String UPDATE_FEATURE_INTERACT_AUCTION = "update esite.es_feature_interact_auction a set a.auctionid = ? where a.id = ? ";
	@Override
	public int updateFeatureIneractAuction(long auctionid, long fid) {
		Object[] params = { auctionid,fid};
		return getJdbcTemplate().update(UPDATE_FEATURE_INTERACT_AUCTION, params);
	}
	

	class FeatureRowmapper implements RowMapper {
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Auction auction = new Auction();
			auction.setId(rs.getLong("id"));
			auction.setTitle(rs.getString("title"));
			auction.setStarttimeDate(rs.getTimestamp("starttime"));
			auction.setEndtimeDate(rs.getTimestamp("endtime"));
			auction.setProendTime(rs.getTimestamp("proendTime"));
			auction.setStartbalance(rs.getInt("startbalance"));
			auction.setAddbalance(rs.getInt("addbalance"));
			auction.setSimg(rs.getString("simg"));
			auction.setUrl(rs.getString("url"));
			auction.setDescription(rs.getString("description"));
			auction.setAddsecond(rs.getInt("addsecond"));
			auction.setStatus(rs.getString("status"));
			
			auction.setOwner(rs.getLong("owner"));
			auction.setUrid(rs.getLong("urid"));
			auction.setLastsecond(rs.getInt("lastsecond"));
			auction.setCurrentmax(rs.getInt("currentmax"));
			auction.setStartnote(rs.getString("startnote"));
			auction.setEndnote(rs.getString("endnote"));
			auction.setUserEmail(rs.getString("useremail"));
			auction.setUserLocation(rs.getString("userLocation"));
			auction.setUserPhone(rs.getString("userPhone"));
			auction.setUserName(rs.getString("username"));
			return auction;
			
		}
	}
	
	private static final String FIND_INTERACT_VOTE_BY_ID = "select s.* from esite.es_feature_interact_auction a join esite.es_interact_auction s on a.auctionid= s.id where a.id = ?";
	@Override
	public Auction findFeatureInteractAuctionById(long fid) {
		Object[] params = { fid };
		List<Auction> list = getJdbcTemplate().query(FIND_INTERACT_VOTE_BY_ID, params, new FeatureRowmapper());
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
	
	@Override
	public List<Long> findAuctionWinner(long id)
	{
		List<List<Long>> result=getJdbcTemplate().query("select id,wbuid,type from es_interact_auction_user_record where auid=? order by id desc limit 1",new Object[]{id},new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				List<Long> list=new ArrayList<Long>();
				list.add(rs.getLong("id"));
				list.add(rs.getLong("wbuid"));
				list.add(rs.getLong("type"));
				return list;
			}
		});
		if(result.size()==1){
			return result.get(0);
		}
		return null;
	}
	
	@Override
	public int findAuctionSub(long aurid)
	{
		return getJdbcTemplate().queryForInt("select count(*) from esite.es_interact_auction_user_sub where aurid=?",new Object[]{aurid});
	}
}
