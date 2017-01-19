package com.huiyee.interact.spread.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.interact.lottery.model.Lottery;
import com.huiyee.interact.spread.dto.SpreadDto;
import com.huiyee.interact.spread.model.SpreadModel;
import com.huiyee.interact.spread.model.SpreadOption;

public class SpreadDaoImpl implements ISpreadDao {

	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate(){
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int findSpreadListTotal(long ownerid, long omid) {
		String sql="select count(*) from esite.es_interact_spread where ownerid=? and omid=? and status !='DEL'";
		return getJdbcTemplate().queryForInt(sql,new Object[]{ownerid,omid});
	}

	@Override
	public List<SpreadModel> findSpreadList(long ownerid, int start, int size, long omid) {
		String sql="select id,ownerid,title,starttime,endtime,type from esite.es_interact_spread where ownerid=? and omid=? and status !='DEL' order by id desc limit ?,?";
		return getJdbcTemplate().query(sql, new Object[]{ownerid,omid,start,size},new SpreadMapper());
	}

	class SpreadMapper implements RowMapper{
		@Override
		public Object mapRow(ResultSet rs, int arg) throws SQLException {
			SpreadModel model=new SpreadModel();
			model.setId(rs.getLong("id"));
			model.setOwnerid(rs.getLong("ownerid"));
			model.setTitle(rs.getString("title"));
			model.setStarttimeDate(rs.getTimestamp("starttime"));
			model.setEndtimeDate(rs.getTimestamp("endtime"));
			model.setType(rs.getString("type"));
			return model;
		}
	}

	class LotteryMapper implements RowMapper{
	       @Override
	       public Object mapRow(ResultSet rs, int arg) throws SQLException {
	    	   Lottery lottery = new Lottery();
	    	   lottery.setId(rs.getLong("id"));
	    	   lottery.setName(rs.getString("name"));
	    	   lottery.setType(rs.getString("type"));
	           return lottery;
	       }
	   }
	
	private static final String FIND_LOTTERY_BY_TYPE = "select es.id,es.name,es.type from esite.es_interact_lottery es where es.owner = ? and type = ? and status !='D'";
	@Override
	public List<Lottery> findLotteryByType(long ownerid, String type) {
		Object[] params = { ownerid,type };
		return getJdbcTemplate().query(FIND_LOTTERY_BY_TYPE, params, new LotteryMapper());
	}

	private static final String SAVE_SPREAD_DESIGN = "insert into esite.es_interact_spread(ownerid,title,content,starttime,endtime,sharelimit,balance,islottery,lotteryid,lotterychance,repic,type,createtime,maxlotterychance,everyonelimit,startnote,endnote,logined,omid) values(?,?,?,?,?,?,?,?,?,?,?,?,now(),?,?,?,?,?,?)";
	@Override
	public long saveSpreadDesign(final long ownerid, final SpreadDto dto, final long omid) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0)
					throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(SAVE_SPREAD_DESIGN,
						new String[] { "id" });
				int i = 1;
				ps.setLong(i++, ownerid);
				ps.setString(i++, dto.getSpread().getTitle());
				ps.setString(i++, dto.getSpread().getContent());
				if(dto.getSpread().getStarttime() != null ){
					ps.setTimestamp(i++, new Timestamp(dto.getSpread().getStarttime().getTime()));
				}else{
					ps.setTimestamp(i++, null);
				}
				if(dto.getSpread().getEndtime() !=null){
					ps.setTimestamp(i++, new Timestamp(dto.getSpread().getEndtime().getTime()));
				}else{
					ps.setTimestamp(i++, null);
				}
				ps.setInt(i++,dto.getSpread().getSharelimit());
				ps.setInt(i++, dto.getSpread().getBalance());
				ps.setString(i++, dto.getSpread().getIslottery());
				ps.setLong(i++, dto.getSpread().getLotteryid());
				ps.setInt(i++, dto.getSpread().getLotterychance());
				ps.setString(i++, dto.getSpread().getRepic());
				ps.setString(i++, dto.getSpread().getType());
				ps.setInt(i++, dto.getSpread().getMaxlotterychance());
				ps.setInt(i++, dto.getSpread().getTotallimit());
				ps.setString(i++, dto.getSpread().getStartnote());
				ps.setString(i++, dto.getSpread().getEndnote());
				ps.setLong(i++, omid);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	class FeatureRowmapper implements RowMapper {
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			SpreadModel iv = new SpreadModel();
			iv.setId(rs.getLong("id"));
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
			iv.setMaxlotterychance(rs.getInt("maxlotterychance"));
			iv.setTotallimit(rs.getInt("everyonelimit"));
			iv.setStartnote(rs.getString("startnote"));
			iv.setEndnote(rs.getString("endnote"));
			return iv;
		}
	}
	
	private static final String FIND_SPREAD_MODEL_BY_ID = "select * from esite.es_interact_spread where id = ? and ownerid = ? and status !='DEL'";
	@Override
	public SpreadModel findSpreadModelById(long spreadid,long ownerid) {
		Object[] params = { spreadid ,ownerid};
		List<SpreadModel> list = getJdbcTemplate().query(FIND_SPREAD_MODEL_BY_ID, params, new FeatureRowmapper());
		if(list != null && list.size()>0){
	 		return list.get(0);
	 	}
		return null;
	}

	private static final String FIND_LTTERY_BY_ID = "select es.id,es.name,es.type from esite.es_interact_lottery es where id = ?";
	@Override
	public Lottery findLotteryById(long lotteryid) {
		Object[] params = { lotteryid };
		List<Lottery> list = getJdbcTemplate().query(FIND_LTTERY_BY_ID, params, new LotteryMapper());
		if(list !=null && list.size()>0){
			return list.get(0);
		}
		return null; 
	}

	private static final String UPDATE_SPREAD_DESIGN= "update esite.es_interact_spread set title=?,content=?,starttime=?,endtime=?,sharelimit=?,balance=?,islottery=?,lotteryid=?,lotterychance=?,repic=?,type=?,createtime = now(),maxlotterychance=?,everyonelimit=?,startnote=?,endnote=? where id=?";
	@Override
	public long updateSpreadDesign(long ownerid, SpreadDto dto, long spreadid) {
		Object[] params = {dto.getSpread().getTitle(),dto.getSpread().getContent(),dto.getSpread().getStarttime(),dto.getSpread().getEndtime(),dto.getSpread().getSharelimit(),dto.getSpread().getBalance(),dto.getSpread().getIslottery(),dto.getSpread().getLotteryid(),dto.getSpread().getLotterychance(),dto.getSpread().getRepic(),dto.getSpread().getType(),dto.getSpread().getMaxlotterychance(),dto.getSpread().getTotallimit(),dto.getSpread().getStartnote(),dto.getSpread().getEndnote(),spreadid };
		return getJdbcTemplate().update(UPDATE_SPREAD_DESIGN,params);
	}

	@Override
	public int deleteSpread(long id)
	{
		String sql="update esite.es_interact_spread set status='DEL' where id=?";
		return getJdbcTemplate().update(sql,new Object[]{id});
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
			o.setContent(rs.getString("content"));
			o.setPic(rs.getString("pic"));
			o.setCreatetime(rs.getTimestamp("createtime"));
			o.setTitle(rs.getString("title"));
			return o;
		}
		
	}

	private static final String FIND_TOKEN_BY_PAGEID="select token from esite.es_page p join esite.es_user_info u on p.siteid = u.siteid where u.wbuid = ? and p.id = ? and tokenendtime > now()";
	@Override
	public String findTokenByPageidAndWbuid(long wbuid, long pageid) {
		Object[] params={wbuid,pageid};
		try {
			return (String) getJdbcTemplate().queryForObject(FIND_TOKEN_BY_PAGEID, params, String.class);
		} catch (DataAccessException e) {
			return null;
		}
	}

	private static final String SAVE_SPREAD_RECORD="insert into esite.es_feature_interact_spread_record(pageid,wbuid,spreadid,content,pic,fartherwbid,ip,terminal,source,oid,atnicknames,createtime,type)values(?,?,?,?,?,?,?,?,?,?,?,now(),0)";
	@Override
	public int saveSpreadRecord(long pageid, long wbuid, long spreadid,
			String content, String pic, String fartherwbid, String ip,
			String terminal, String source,long oid,String nicknames) {
		Object[] params={pageid,wbuid,spreadid,content,pic,fartherwbid,ip,terminal,source,oid,nicknames};
		return getJdbcTemplate().update(SAVE_SPREAD_RECORD, params);
	}

	private static final String SAVE_WXSPREAD_RECORD="insert into esite.es_feature_interact_spread_record(pageid,spreadid,content,ip,terminal,source,oid,wbuid,createtime,type,atnicknames)values(?,?,?,?,?,?,?,?,now(),1,?)";
	@Override
	public int saveWxSpreadRecord(long pageid,long spreadid,String content,String ip,String terminal,String source,long oid,long wxuid,String nicknames){
		Object[] params={pageid,spreadid,content,ip,terminal,source,oid,wxuid,nicknames};
		return getJdbcTemplate().update(SAVE_WXSPREAD_RECORD, params);
	}
	
	private static final String FIND_SPREAD_RECORD_BY_WBUID="select count(id) from es_feature_interact_spread_record where spreadid = ? and wbuid = ? and type= 0";
	@Override
	public int findSpreadRecordCountByWbuid(long spreadid, long wbuid) {
		Object[] params={spreadid,wbuid};
		return getJdbcTemplate().queryForInt(FIND_SPREAD_RECORD_BY_WBUID, params);
	}
	
	private static final String FIND_SPREAD_RECORD_BY_WXUID="select count(id) from es_feature_interact_spread_record where spreadid = ? and wbuid = ? and type= 1";
	@Override
	public int findSpreadRecordCountByWxuid(long spreadid, long wxuid) {
		Object[] params={spreadid,wxuid};
		return getJdbcTemplate().queryForInt(FIND_SPREAD_RECORD_BY_WXUID, params);
	}

	private static final String FIND_SPREAD_OPTION_BY_OID="select * from es_interact_spread_option where id = ?";
	@Override
	public SpreadOption findSpreadOptionByOid(long oid) {
		Object[] params={oid};
		List<SpreadOption> list =getJdbcTemplate().query(FIND_SPREAD_OPTION_BY_OID, params, new SpreadOptionRowmapper());
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	private static final String UPDATE_RULETYPE_BY_LOTTERYID = "update es_interact_lottery set ruletype = 'G' where id = ?";
	@Override
	public int updateRuletypeByLotteryid(long lotteryid) {
		Object[] params={lotteryid};
		return getJdbcTemplate().update(UPDATE_RULETYPE_BY_LOTTERYID,params);
	}

	private static final String FIND_SPREAD_OPTIONS_BY_SPREADID = "select * from es_interact_spread_option so join es_interact_spread s on s.id = so.spreadid where s.id = ? and so.status != 'DEL'";
	@Override
	public List<SpreadOption> findSpreadOptionsBySpreadid(long spreadid) {
		Object[] params={spreadid};
		return getJdbcTemplate().query(FIND_SPREAD_OPTIONS_BY_SPREADID, params, new SpreadOptionRowmapper());
	}

	@Override
	public long addSpread(final long ownerid, final String title)
	{
		final String sql="insert into es_interact_spread(ownerid,title,createtime) values(?,?,now())";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0)
					throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(sql,
						new String[] { "id" });
				int i = 1;
				ps.setLong(i++, ownerid);
				ps.setString(i++, title);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}
}
