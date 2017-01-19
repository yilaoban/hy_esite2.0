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
import com.huiyee.esite.fdao.IHd123Dao;
import com.huiyee.esite.model.Module;
import com.huiyee.esite.model.PageBlockRelation;
import com.huiyee.interact.vote.model.InteractVote;
import com.huiyee.interact.vote.model.VoteOption;

public class Hd123DaoImpl extends AbstractDao implements IHd123Dao{

	private static final String SAVE_FEATRUE_INTERACT = "insert into esite.es_feature_interact_vote (pageid,createtime) values(?,now())";
	@Override
	public long saveFeatureInteractVote(final long pageid) {
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
	
	class FeatureVoteRowmapper implements RowMapper {
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			InteractVote iv = new InteractVote();
			iv.setId(rs.getLong("id"));
			iv.setTitle(rs.getString("title"));
			iv.setContent(rs.getString("content"));
//			iv.setType(rs.getString("type"));
			return iv;
		}
	}
	private static final String FIND_INTERACT_VOTE_BY_OWNER = "select a.id,a.title,a.content,a.omid from esite.es_interact_vote a where a.ownerid = ? and a.status != 'DEL' and a.omid=1 order by a.id desc";
	@Override
	public List<InteractVote> findInteractVoteByOwner(long ownerid) {
		Object[] params = { ownerid };
		return getJdbcTemplate().query(FIND_INTERACT_VOTE_BY_OWNER, params, new FeatureVoteRowmapper());
	}
	
	private static final String FIND_VOTEID_BY_FID = "select a.* from esite.es_feature_interact_vote a where id = ? ";
	@Override
	public Module findVoteidByFid(long fid) {
		List<Module> list = getJdbcTemplate().query(FIND_VOTEID_BY_FID, new Object[] { fid }, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				Module m = new Module();
				m.setId(rs.getLong("a.voteid"));
				m.setStart(rs.getInt("a.start"));
				m.setEnd(rs.getInt("a.end"));
				m.setPageid(rs.getLong("a.pageid"));
				return m;
			}
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	private static final String UPDATE_FEATURE_INTERACT_VOTE = "update esite.es_feature_interact_vote a set a.voteid = ?,a.start=?,a.end=? where a.id = ? ";
	@Override
	public int updateFeatureIneractVote(long voteid, long fid,int start,int end) {
		Object[] params = { voteid,start,end,fid};
		return getJdbcTemplate().update(UPDATE_FEATURE_INTERACT_VOTE, params);
	}
	
	class FeatureRowmapper implements RowMapper {
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			InteractVote iv = new InteractVote();
			iv.setId(rs.getLong("id"));
			iv.setTitle(rs.getString("title"));
			iv.setContent(rs.getString("content"));
			iv.setStarttime(rs.getTimestamp("starttime"));
			iv.setEndtime(rs.getTimestamp("endtime"));
			iv.setIslottery(rs.getString("islottery"));
			iv.setLotteryid(rs.getLong("lotteryid"));
			iv.setLotterychance(rs.getInt("lotterychance"));
			iv.setBalance(rs.getInt("balance"));
			iv.setMaxlotterychance(rs.getInt("maxlotterychance"));
			iv.setDaylimit(rs.getInt("daylimit"));
			iv.setStartnote(rs.getString("startnote"));
			iv.setEndnote(rs.getString("endnote"));
			iv.setTotallimit(rs.getInt("totallimit"));
			iv.setLotterytype(rs.getString("lotterytype"));
			iv.setOmid(rs.getInt("omid"));
			return iv;
		}
	}
	
	private static final String FIND_INTERACT_VOTE_BY_ID = "select s.* from esite.es_feature_interact_vote a join esite.es_interact_vote s on a.voteid= s.id where a.id = ?";
	@Override
	public InteractVote findFeatureInteractVoteById(long fid) {
		Object[] params = { fid };
		List<InteractVote> list = getJdbcTemplate().query(FIND_INTERACT_VOTE_BY_ID, params, new FeatureRowmapper());
		if(list !=null && list.size()>0){
	 		return list.get(0);
	 	}
		return null;
	}
	
	class FeatureVoteOptionRowmapper implements RowMapper {
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			VoteOption iv = new VoteOption();
			iv.setId(rs.getLong("id"));
			iv.setVoteid(rs.getLong("voteid"));
			iv.setVediourl(rs.getString("vediourl"));
			iv.setDescription(rs.getString("description"));
			iv.setContent(rs.getString("content"));
			iv.setCount(rs.getInt("count"));
			iv.setPic(rs.getString("pic"));
			iv.setIdx(rs.getInt("idx"));
			iv.setTags(rs.getString("tags"));
			iv.setLinked(rs.getString("linked"));
			iv.setLinkurl(rs.getString("linkurl"));
			return iv;
		}
	}
	
	@Override
	public List<VoteOption> findFeatureInteractVoteOptionById(long fid,int start, int end) {
		String sql = "select s.* from esite.es_feature_interact_vote a join esite.es_interact_vote_option s on a.voteid= s.voteid where a.id = ? and s.status != 'DEL' ";
		List<Object> param = new ArrayList<Object>();
		param.add(fid);
		if(start > 0){
			sql += " and s.idx >= ?";
			param.add(start);
		}
		if(end > 0){
			sql += " and s.idx <= ?";
			param.add(end);
		}
		sql += " order by s.idx ";
		return getJdbcTemplate().query(sql, param.toArray(), new FeatureVoteOptionRowmapper());
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
	public int findTotalOptionById(long id) {
		String sql = "select count(id) from es_interact_vote_option where voteid = ? and status != 'DEL'";
		Object[] params = { id};
		return getJdbcTemplate().queryForInt(sql, params);
	}
}
