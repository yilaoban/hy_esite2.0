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
import com.huiyee.esite.fdao.IHd124Dao;
import com.huiyee.esite.model.Module;
import com.huiyee.esite.model.PageBlockRelation;
import com.huiyee.interact.research.model.ResearchModel;
import com.huiyee.interact.research.model.ResearchQuestionModel;
import com.huiyee.interact.research.model.ResearchQuestionOption;

public class Hd124DaoImpl extends AbstractDao implements IHd124Dao{

	private static final String SAVE_FEATRUE_INTERACT = "insert into esite.es_feature_interact_research (pageid,createtime) values(?,now())";
	@Override
	public long saveFeatureInteractResearch(final long pageid){
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
			ResearchModel rm = new ResearchModel();
			rm.setId(rs.getLong("id"));
			rm.setTitle(rs.getString("title"));
			rm.setContent(rs.getString("content"));
			return rm;
		}
	}
	
	private static final String FIND_INTERACT_RESEARCH_BY_OWNER = "select a.id,a.title,a.content,a.omid from esite.es_interact_research a where a.ownerid = ? and a.status != 'DEL' and a.omid=1 order by a.id desc";
	@Override
	public List<ResearchModel> findInteractResearchByOwner(long ownerid) {
		Object[] params = { ownerid };
		return getJdbcTemplate().query(FIND_INTERACT_RESEARCH_BY_OWNER, params, new FeatureVoteRowmapper());
	}
	
	private static final String FIND_RESEARCHID_BY_FID = "select a.* from esite.es_feature_interact_research a where id = ? ";
	@Override
	public Module findResearchidByFid(long fid){
		List<Module> list = getJdbcTemplate().query(FIND_RESEARCHID_BY_FID, new Object[] { fid }, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				Module m = new Module();
				m.setId(rs.getLong("a.researchid"));
				m.setPageid(rs.getLong("a.pageid"));
				return m;
			}
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	private static final String UPDATE_FEATURE_INTERACT_RESEARCH = "update esite.es_feature_interact_research a set a.researchid = ? where a.id = ? ";
	@Override
	public int updateFeatureIneractResearch(long researchid, long fid){
		Object[] params = { researchid,fid};
		return getJdbcTemplate().update(UPDATE_FEATURE_INTERACT_RESEARCH, params);
	}
	
	
	class FeatureRowmapper implements RowMapper {
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			ResearchModel iv = new ResearchModel();
			iv.setId(rs.getLong("id"));
			iv.setTitle(rs.getString("title"));
			iv.setContent(rs.getString("content"));
			iv.setStarttimeDate(rs.getTimestamp("starttime"));
			iv.setEndtimeDate(rs.getTimestamp("endtime"));
			iv.setTotallimit(rs.getInt("totallimit"));
			iv.setIslottery(rs.getString("islottery"));
			iv.setLotterychance(rs.getInt("lotterychance"));
			iv.setLotteryid(rs.getLong("lotteryid"));
			iv.setBalance(rs.getInt("balance"));
			iv.setMaxlotterychance(rs.getInt("maxlotterychance"));
			iv.setStartnote(rs.getString("startnote"));
			iv.setEndnote(rs.getString("endnote"));
			return iv;
		}
	}
	
	private static final String FIND_INTERACT_RESEARCH_BY_ID = "select s.* from esite.es_feature_interact_research a join esite.es_interact_research s on a.researchid= s.id where a.id = ?";
	@Override
	public ResearchModel findFeatureInteractResearchById(long fid){
		Object[] params = { fid };
		List<ResearchModel> list = getJdbcTemplate().query(FIND_INTERACT_RESEARCH_BY_ID, params, new FeatureRowmapper());
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
	
	
	private static final String FIND_QUESTION_BY_RESEARCHID="select * from esite.es_interact_research_question where searchid = ? and status != 'DEL' order by idx";
	@Override
	public List<ResearchQuestionModel> findQuestionsByResearchid(long researchid){
		return getJdbcTemplate().query(FIND_QUESTION_BY_RESEARCHID, new Object[]{researchid}, new QuestionRowmapper());
	}
	
	class QuestionRowmapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			ResearchQuestionModel q = new ResearchQuestionModel();
			q.setId(rs.getLong("id"));
			q.setSearchid(rs.getLong("searchid"));
			q.setType(rs.getString("type"));
			q.setTitle(rs.getString("title"));
			q.setPic(rs.getString("pic"));
			q.setIdx(rs.getInt("idx"));
			q.setCreatetime(rs.getTimestamp("createtime"));
			q.setStatus(rs.getString("status"));
			q.setIsreq(rs.getString("isreq"));
			return q;
		}
		
	}
	
	private static final String FIND_OPTION_BY_QUESTIONID="select * from esite.es_interact_research_question_option where questionid = ? and status != 'DEL'";
	@Override
	public List<ResearchQuestionOption> findOptionsByQuestionid(long questionid){
		Object[] params={questionid};
		return getJdbcTemplate().query(FIND_OPTION_BY_QUESTIONID, params, new OptionsRowmapper());
	}
	
	class OptionsRowmapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			ResearchQuestionOption o = new ResearchQuestionOption();
			o.setId(rs.getLong("id"));
			o.setQuestionid(rs.getLong("questionid"));
			o.setContent(rs.getString("content"));
			o.setPic(rs.getString("pic"));
			o.setIdx(rs.getInt("idx"));
			o.setCreatetime(rs.getTimestamp("createtime"));
			o.setStatus(rs.getString("status"));
			o.setTarget(rs.getLong("target"));
			return o;
		}
		
	}

}
