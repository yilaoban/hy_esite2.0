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
import com.huiyee.esite.fdao.IHd125Dao;
import com.huiyee.esite.model.Module;
import com.huiyee.esite.model.PageBlockRelation;
import com.huiyee.interact.lottery.model.Lottery;
import com.huiyee.interact.lottery.model.LotteryPrize;

public class Hd125DaoImpl extends AbstractDao implements IHd125Dao{

	private static final String SAVE_FEATRUE_INTERACT = "insert into esite.es_feature_interact_lottery (pageid,type,createtime) values(?,?,now())";
	@Override
	public long saveFeatureInteractLottery(final long pageid,final String type) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
                PreparedStatement ps = arg0.prepareStatement(SAVE_FEATRUE_INTERACT, new String[] { "id" });
                int i = 1;
                ps.setLong(i++, pageid);
                ps.setString(i++, type);
                return ps;
            }
        }, keyHolder);
        long id = keyHolder.getKey().intValue();
        return id;
	}
	
	private static final String FIND_LOTTERYID_BY_FID = "select a.* from esite.es_feature_interact_lottery a where id = ? ";
	@Override
	public Module findLotteryidByFid(long fid) {
		List<Module> list = getJdbcTemplate().query(FIND_LOTTERYID_BY_FID, new Object[] { fid }, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				Module m = new Module();
				m.setId(rs.getLong("lotteryid"));
				m.setType(rs.getString("type"));
				m.setPageid(rs.getLong("pageid"));
				return m;
			}
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	class FeatureLotteryRowmapper implements RowMapper {
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Lottery iv = new Lottery();
			iv.setId(rs.getLong("id"));
			iv.setName(rs.getString("name"));
			return iv;
		}
	}
	
	private static final String FIND_INTERACT_LOTTERY_BY_OWNER = "select a.id,a.name,a.omid from esite.es_interact_lottery a where a.owner = ? and a.type = ? and a.status != 'D' and a.omid=1 order by a.id desc";
	@Override
	public List<Lottery> findInteractLotteryByOwner(long ownerid,String type) {
		Object[] params = { ownerid,type };
		return getJdbcTemplate().query(FIND_INTERACT_LOTTERY_BY_OWNER, params, new FeatureLotteryRowmapper());
	}
	
	private static final String UPDATE_FEATURE_INTERACT_LOTTERY = "update esite.es_feature_interact_lottery a set a.lotteryid = ? where a.id = ? ";
	@Override
	public int updateFeatureIneractLottery(long lotteryid, long fid) {
		Object[] params = { lotteryid,fid};
		return getJdbcTemplate().update(UPDATE_FEATURE_INTERACT_LOTTERY, params);
	}
	
	class FeatureRowmapper implements RowMapper {
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Lottery l = new Lottery();
			l.setId(rs.getLong("id"));
			l.setName(rs.getString("name"));
			l.setStarttimeDate(rs.getTimestamp("starttime"));
			l.setEndtimeDate(rs.getTimestamp("endtime"));
			l.setOwner(rs.getLong("owner"));
			l.setStatus(rs.getString("status"));
			l.setType(rs.getString("type"));
			l.setZjl(rs.getInt("zjl"));
			l.setDetail(rs.getString("detail"));
			l.setEndimg(rs.getString("endimg"));
			l.setUserName(rs.getString("userName"));
			l.setUserNameValue(rs.getString("userNameValue"));
			l.setUserPhone(rs.getString("userPhone"));
			l.setUserPhoneValue(rs.getString("userPhoneValue"));
			l.setUserEmail(rs.getString("userEmail"));
			l.setUserEmailValue(rs.getString("userEmailValue"));
			l.setUserLocation(rs.getString("userLocation"));
			l.setUserLocationValue(rs.getString("userLocationValue"));
			l.setRuletype(rs.getString("ruletype"));
			l.setUsertype(rs.getString("usertype"));
			l.setAssignuser(rs.getString("assignuser"));
			l.setUsertotal(rs.getInt("usertotal"));
			l.setUserdaynum(rs.getInt("userdaynum"));
			l.setStartnote(rs.getString("startnote"));
			l.setEndnote(rs.getString("endnote"));
			return l;
		}
	}
	
	private static final String FIND_INTERACT_LOTTERY_BY_ID = "select s.* from esite.es_feature_interact_lottery a join esite.es_interact_lottery s on a.lotteryid= s.id where a.id = ? and s.status != 'D'";
	@Override
	public Lottery findFeatureInteractLotteryById(long fid) {
		Object[] params = { fid };
		List<Lottery> list = getJdbcTemplate().query(FIND_INTERACT_LOTTERY_BY_ID, params, new FeatureRowmapper());
		if(list !=null && list.size()>0){
	 		return list.get(0);
	 	}
		return null;
	}
	
	private static final String FIND_LOTTERY_TYPE_BY_FID = "select a.type from esite.es_feature_interact_lottery a where id = ? ";
	@Override
	public Module findLotteryTypeByFid(long fid) {
		List<Module> list = getJdbcTemplate().query(FIND_LOTTERY_TYPE_BY_FID, new Object[] { fid }, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				Module m = new Module();
				m.setType(rs.getString("type"));
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

	private static final String FIND_LOTTERY_PRICE_BY_LOTTERYID = "select * from es_interact_lottery_prize pr where lid=? and status!='D' order by positionid asc";
	@Override
	public List<LotteryPrize> findLotteryPriceByLotteryid(long lid) {
		Object[] params = {lid};
		return getJdbcTemplate().query(FIND_LOTTERY_PRICE_BY_LOTTERYID, params, new LotteryPrizeRowMapper());
	}

	class LotteryPrizeRowMapper implements RowMapper
	{
		public Object mapRow(ResultSet rs, int arg1) throws SQLException
		{
			LotteryPrize pr = new LotteryPrize();
			pr.setId(rs.getLong("id"));
			pr.setLid(rs.getLong("lid"));
			pr.setName(rs.getString("name"));
			pr.setImg(rs.getString("img"));
			pr.setTotal(rs.getInt("total"));
			pr.setUsed(rs.getInt("used"));
			pr.setPrice(rs.getInt("price"));
			pr.setStatus(rs.getString("status"));
			pr.setType(rs.getString("type"));
			pr.setHydefault(rs.getString("hydefault"));
			pr.setLocation(rs.getString("location"));
			pr.setPositionid(rs.getInt("positionid"));
			pr.setStyle(rs.getString("style"));
			return pr;
		}
	}
}
