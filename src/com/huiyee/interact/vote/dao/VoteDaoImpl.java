package com.huiyee.interact.vote.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.model.BlockContext;
import com.huiyee.esite.service.IMemCacheAble;
import com.huiyee.interact.lottery.model.Lottery;
import com.huiyee.interact.vote.dto.VoteDto;
import com.huiyee.interact.vote.dto.VoteRecordQueryDto;
import com.huiyee.interact.vote.model.InteractVote;
import com.huiyee.interact.vote.model.VoteOption;
import com.huiyee.interact.vote.model.VoteRecord;

public class VoteDaoImpl extends AbstractDao implements IVoteDao,IMemCacheAble {
	private int cacheTime;
	
	public int getCacheTime()
	{
		return cacheTime;
	}
	
	public void setCacheTime(int cacheTime)
	{
		this.cacheTime = cacheTime;
	}


	@Override
	public List<InteractVote> findVoteList(long ownerid, int start, int size, long omid) {
		String sql = "select id,ownerid,title,starttime,endtime from es_interact_vote where ownerid=? and omid=? and status !='DEL' order by id desc limit ?,?";
		return getJdbcTemplate().query(sql,
				new Object[] { ownerid, omid, start, size }, new VoteMapper());
	}
	class VoteMapper implements RowMapper {
		@Override
		public Object mapRow(ResultSet rs, int arg) throws SQLException {
			InteractVote model = new InteractVote();
			model.setId(rs.getLong("id"));
			model.setOwnerid(rs.getLong("ownerid"));
			model.setTitle(rs.getString("title"));
			model.setStarttimeDate(rs.getTimestamp("starttime"));
			model.setEndtimeDate(rs.getTimestamp("endtime"));
//			model.setType(rs.getString("type"));
			return model;
		}
	}

	@Override
	public int findVoteListTotal(long ownerid, long omid) {
		String sql = "select count(*) from es_interact_vote where ownerid=? and omid=? and status !='DEL'";
		return getJdbcTemplate().queryForInt(sql, new Object[] { ownerid, omid });
	}


	public List<VoteRecord> findVoteRecordList(long voteid,
			VoteRecordQueryDto queryDto, int start, int size,int type,long owner) {
		String sql="";
		if(type==0){
			sql="select r.*,u.username,v.content,wb.nickname nickname1,'' wxnickname from es_feature_interact_vote_record r join es_feature_interact_vote_record_option o on o.recordid=r.id join es_interact_vote_option v on v.id=o.optionid left join es_sina_user wb on wb.wbuid=r.wbuid left join es_hy_user u on r.wbuid = u.wbuid and u.owner=? where r.voteid=? and r.type=0 ";
		}else if(type==1){
			sql="select r.*,u.username,v.content,wx.nickname nickname1,'' wxnickname from es_feature_interact_vote_record r join es_feature_interact_vote_record_option o on o.recordid=r.id join es_interact_vote_option v on v.id=o.optionid left join es_wx_user wx on wx.id=r.wbuid left join es_hy_user u on r.wbuid = u.wxuid and u.owner=? where r.voteid=? and r.type=1 ";
		}else if(type == 2){
			sql="select r.*,u.username,v.content,wb.nickname nickname1,wx.nickname wxnickname from es_feature_interact_vote_record r join es_feature_interact_vote_record_option o on o.recordid=r.id join es_interact_vote_option v on v.id=o.optionid left join es_hy_user u on r.wbuid = u.id and u.owner=? left join es_sina_user wb on u.wbuid = wb.wbuid left join es_wx_user wx on u.wxuid = wx.id where r.voteid=? and r.type=2 ";
		}else if(type == -1){
			sql="select r.*,u.username,v.content,'' nickname1,'' wxnickname from es_feature_interact_vote_record r join es_feature_interact_vote_record_option o on o.recordid=r.id join es_interact_vote_option v on v.id=o.optionid left join es_hy_user u on r.wbuid = u.wxuid and u.owner=? where r.voteid=? and r.type=-1 ";
		}
		
	       StringBuffer buffer=new StringBuffer();
	       buffer.append(sql);
	       List<Object> list=new ArrayList<Object>();
	       list.add(owner);
	       list.add(voteid);
	       if(queryDto!=null){
	           if(queryDto.getStartTime()!=null&&!"".equals(queryDto.getStartTime().trim())){
	               buffer.append(" and r.createtime>=?");
	               list.add(queryDto.getStartTime());
	           }
	           if(queryDto.getEndTime()!=null&&!"".equals(queryDto.getEndTime().trim())){
	               buffer.append(" and r.createtime<=?");
	               list.add(queryDto.getEndTime());  
	           }
	           if((type==0||type==1)&&StringUtils.isNotEmpty(queryDto.getNickname())){
	        	   buffer.append(" and u.nickname like ?");
	               list.add("%"+queryDto.getNickname()+"%");  
	           }
	       }
	       buffer.append(" order by r.id desc limit ?,?");
	       list.add(start);
	       list.add(size);
	       return getJdbcTemplate().query(buffer.toString(),list.toArray(),new VoteRecordMapper());
	}
	
	public int findVoteRecordCountByNickname(long voteid,String nickname,int type) {
			if(type==0){
				String sql="select count(r.id) from es_feature_interact_vote_record r join es_feature_interact_vote_record_option o on o.recordid=r.id join es_interact_vote_option v on v.id=o.optionid left join es_sina_user u on u.wbuid=r.wbuid where r.voteid=? and u.nickname like '%"+nickname+"%'";
				return getJdbcTemplate().queryForInt(sql,new Object[]{voteid});
			}else{
				String sql="select count(r.id) from es_feature_interact_vote_record r join es_feature_interact_vote_record_option o on o.recordid=r.id join es_interact_vote_option v on v.id=o.optionid left join es_wx_user u on u.id=r.wbuid where r.voteid=? and u.nickname like '%"+nickname+"%'";
				return getJdbcTemplate().queryForInt(sql,new Object[]{voteid});
			}
	}


	private static final String SAVE_VOTE_DESIGN = "insert into esite.es_interact_vote(ownerid,title,content,starttime,endtime,islottery,lotteryid,lotterychance,balance,daylimit,maxlotterychance,startnote,endnote,totallimit,omid,createtime) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now())";
	@Override
	public long saveVoteDesign(final long ownerid,final VoteDto dto,final long omid) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0)
					throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(SAVE_VOTE_DESIGN,
						new String[] { "id" });
				int i = 1;
				ps.setLong(i++, ownerid);
				ps.setString(i++, dto.getTitle());
				ps.setString(i++, dto.getContent());
				if(dto.getStarttime() != null ){
					ps.setTimestamp(i++, new Timestamp(dto.getStarttime().getTime()));
				}else{
					ps.setTimestamp(i++, null);
				}
				if(dto.getEndtime() !=null){
					ps.setTimestamp(i++, new Timestamp(dto.getEndtime().getTime()));
				}else{
					ps.setTimestamp(i++, null);
				}
				ps.setString(i++, dto.getIslottery());
				ps.setLong(i++, dto.getLotteryid());
				ps.setInt(i++, dto.getLotterychance());
				ps.setInt(i++, dto.getBalance());
				ps.setInt(i++, dto.getDaylimit());
				ps.setInt(i++,dto.getMaxlotterychance());
				ps.setString(i++, dto.getStartnote());
				ps.setString(i++, dto.getEndnote());
				ps.setInt(i++,dto.getTotallimit());
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
			InteractVote iv = new InteractVote();
			iv.setId(rs.getLong("id"));
			iv.setTitle(rs.getString("title"));
			iv.setContent(rs.getString("content"));
			iv.setStarttimeDate(rs.getTimestamp("starttime"));
			iv.setEndtimeDate(rs.getTimestamp("endtime"));
			iv.setIslottery(rs.getString("islottery"));
			iv.setLotteryid(rs.getLong("lotteryid"));
			iv.setLotterychance(rs.getInt("lotterychance"));
			iv.setBalance(rs.getInt("balance"));
			iv.setMaxlotterychance(rs.getInt("maxlotterychance"));
			iv.setDaylimit(rs.getInt("daylimit"));
			iv.setStartnote(rs.getString("startnote"));
			iv.setEndnote(rs.getString("endnote"));
			iv.setTotallimit(rs.getInt("totallimit"));
			iv.setOmid(rs.getLong("omid"));
			return iv;
		}
	}
	public int findVoteRecordTotal(long voteid, VoteRecordQueryDto queryDto,int type,long owner) {
		String sql="";
		if(type==0){
			sql="select count(r.id) from es_feature_interact_vote_record r join es_feature_interact_vote_record_option o on o.recordid=r.id join es_interact_vote_option v on v.id=o.optionid left join es_sina_user wb on wb.wbuid=r.wbuid left join es_hy_user u on r.wbuid = u.wbuid and u.owner=? where r.voteid=? and r.type=0 ";
		}else if(type==1){
			sql="select count(r.id) from es_feature_interact_vote_record r join es_feature_interact_vote_record_option o on o.recordid=r.id join es_interact_vote_option v on v.id=o.optionid left join es_wx_user wx on wx.id=r.wbuid left join es_hy_user u on r.wbuid = u.wxuid and u.owner=? where r.voteid=? and r.type=1 ";
		}else if(type == 2){
			sql="select count(r.id) from es_feature_interact_vote_record r join es_feature_interact_vote_record_option o on o.recordid=r.id join es_interact_vote_option v on v.id=o.optionid left join es_hy_user u on r.wbuid = u.id and u.owner=? left join es_sina_user wb on u.wbuid = wb.wbuid left join es_wx_user wx on u.wxuid = wx.id where r.voteid=? and r.type=2 ";
		}else if(type == -1){
			sql="select count(r.id) from es_feature_interact_vote_record r join es_feature_interact_vote_record_option o on o.recordid=r.id join es_interact_vote_option v on v.id=o.optionid left join es_hy_user u on r.wbuid = u.wxuid and u.owner=? where r.voteid=? and r.type=-1 ";
		}
//		String sql = "select count(r.id) from es_feature_interact_vote_record r join es_feature_interact_vote_record_option o on o.recordid=r.id join es_interact_vote_option v on v.id=o.optionid where r.voteid=? and r.type=?";
		StringBuffer buffer = new StringBuffer();
		buffer.append(sql);
		List<Object> list = new ArrayList<Object>();
		list.add(owner);
		list.add(voteid);
		if (queryDto != null) {
			if (queryDto.getStartTime() != null
					&& !"".equals(queryDto.getStartTime().trim())) {
				buffer.append(" and r.createtime>=?");
				list.add(queryDto.getStartTime());
			}
			if (queryDto.getEndTime() != null
					&& !"".equals(queryDto.getEndTime().trim())) {
				buffer.append(" and r.createtime<=?");
				list.add(queryDto.getEndTime());
			}
			  if((type==0||type==1)&&StringUtils.isNotEmpty(queryDto.getNickname())){
	        	   buffer.append(" and u.nickname like ?");
	               list.add("%"+queryDto.getNickname()+"%");  
	           }
		}
		return getJdbcTemplate().queryForInt(buffer.toString(), list.toArray());
	}
	private static final String FIND_VOTE_MANAGER_MODEL_BY_ID = "select * from esite.es_interact_vote where id = ? and status !='DEL'";
	public InteractVote findVoteManageModelById(long voteid) {
		Object[] params = { voteid };
		List<InteractVote> list = getJdbcTemplate().query(FIND_VOTE_MANAGER_MODEL_BY_ID, params, new FeatureRowmapper());
		if(list !=null && list.size()>0){
	 		return list.get(0);
	 	}
		return null;
	}
	
	private static final String UPDATE_VOTE_DESIGN = "update esite.es_interact_vote set title=?,content=?,starttime=?,endtime=?,islottery=?,lotteryid=?,lotterychance=?,balance=?,maxlotterychance=?,totallimit=?,daylimit=?,startnote=?,endnote=? where id=?";
	@Override
	public long updateVoteDesign(long ownerid, VoteDto dto, long voteid) {
		Object[] params={dto.getTitle(),dto.getContent(),dto.getStarttime(),dto.getEndtime(),dto.getIslottery(),dto.getLotteryid(),dto.getLotterychance(),dto.getBalance(),dto.getMaxlotterychance(),dto.getTotallimit(),dto.getDaylimit(),dto.getStartnote(),dto.getEndnote(),voteid};
		return getJdbcTemplate().update(UPDATE_VOTE_DESIGN,params);
	}
   @Override
   public List<VoteOption> findVoteOptionList(long voteid) {
       String sql="select count,content from es_interact_vote_option where voteid=? and status!='DEL'  order by count desc";
       return getJdbcTemplate().query(sql, new Object[]{voteid},new VoteOptionMapper());
   }
   @Override
   public int findVoteOptionTotalByVoteid(long voteid) {
       String sql="select sum(count) from es_interact_vote_option where voteid=? and status!='DEL'";
       return getJdbcTemplate().queryForInt(sql,new Object[]{voteid});
   }
   class VoteOptionMapper implements RowMapper{
       @Override
       public Object mapRow(ResultSet rs, int arg) throws SQLException {
           VoteOption option=new VoteOption();
           option.setContent(rs.getString("content"));
           option.setCount(rs.getInt("count"));
           return option;
       }
   }
   class VoteRecordMapper implements RowMapper{
       @Override
       public Object mapRow(ResultSet rs, int arg) throws SQLException {
           VoteRecord record=new VoteRecord();
           record.setId(rs.getLong("id"));
           record.setContent(rs.getString("content"));
           record.setCreatetime(rs.getTimestamp("createtime"));
           record.setIsshare(rs.getString("isshare"));
           record.setIp(rs.getString("ip"));
           int type=rs.getInt("r.type");
           record.setType(type);
           record.setUsername(rs.getString("u.username"));
           if(type==0||type==1||type==2){
        	   record.setNickname(rs.getString("nickname1"));
           }
           if(type == 2){
        	   record.setWxnickname(rs.getString("wxnickname"));
           }
           return record;
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
   
   private static final String FIND_LOTTERY_BY_TYPE = "select es.id,es.name,es.type from esite.es_interact_lottery es where es.owner = ? and type = ? and status !='D' order by es.id desc";
	@Override
	public List<Lottery> findLotteryByType(long ownerid, String type) {
		Object[] params = { ownerid,type };
		return getJdbcTemplate().query(FIND_LOTTERY_BY_TYPE, params, new LotteryMapper());
	}

	@Override
	public int saveVoteContent(long voteid, String content, String description,String img,String vediourl,String tags,String linked,String linkurl) {
		final int idx = findMaxIndx(voteid);
		String sql="insert into es_interact_vote_option (voteid,content,description,pic,vediourl,createtime,idx,tags,linked,linkurl) values(?,?,?,?,?,now(),?,?,?,?)";
		return getJdbcTemplate().update(sql,new Object[]{voteid,content,description,img,vediourl,idx+1,tags,linked,linkurl});
	}

	public int findMaxIndx(long voteid)
	{
		String sql = "select Max(idx) from es_interact_vote_option where voteid=?";
		return getJdbcTemplate().queryForInt(sql, new Object[]{ voteid });
	}
	
	@Override
	public int savaVoteType(long voteid, String type) {
		String sql="update es_interact_vote set type=? where id=?";
		return getJdbcTemplate().update(sql,new Object[]{type,voteid});
	}

	@Override
	public List<VoteOption> searchVoteOptionList(long id,int start,int size) {
		String sql="select * from es_interact_vote_option where voteid=? and status!='DEL' order by idx limit ?,?";
		List<VoteOption> list=getJdbcTemplate().query(sql, new Object[]{id,start,size},new VoteContentMapper());
		if(list!=null&&list.size()>0){
			return list;
		}
		return null;
	}
	class VoteContentMapper implements RowMapper {
		@Override
		public Object mapRow(ResultSet rs, int arg) throws SQLException {
			VoteOption model = new VoteOption();
			model.setId(rs.getLong("id"));
			model.setContent(rs.getString("content"));
			model.setVoteid(rs.getLong("voteid"));
			model.setImg(rs.getString("pic"));
			model.setIdx(rs.getInt("idx"));
			model.setDescription(rs.getString("description"));
			model.setLinked(rs.getString("linked"));
			model.setLinkurl(rs.getString("linkurl"));
			return model;
		}
	}
	
	@Override
	public int findVoteOptionTotal(long id){
		String sql="select count(*) from es_interact_vote_option where voteid=? and status != 'DEL'";
		return getJdbcTemplate().queryForInt(sql,new Object[]{id});
	}

	@Override
	public int updateVoteContent(long id, String content, String img) {
		String sql="update es_interact_vote_option set content=?,pic=? where id=?";
		return getJdbcTemplate().update(sql,new Object[]{content,img,id});
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

	private static final String SAVE_VOTE_RECORD="insert into esite.es_feature_interact_vote_record(wbuid,voteid,ip,terminal,source,createtime,pageid,type) values (?,?,?,?,?,now(),?,?)";
	@Override
	public long saveVoteRecord(final long wbuid,final int type,final long voteid,final String ip,
			final String terminal,final String source,final long pageid) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0)
					throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(SAVE_VOTE_RECORD,
						new String[] { "id" });
				int i = 1;
				ps.setLong(i++, wbuid);
				ps.setLong(i++, voteid);
				ps.setString(i++, ip);
				ps.setString(i++, terminal);
				ps.setString(i++, source);
				ps.setLong(i++, pageid);
				ps.setInt(i++, type);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().longValue();
		return id;
	}

	private static final String SAVE_VOTE_OPTION_RECORD="insert into esite.es_feature_interact_vote_record_option(recordid,optionid,createtime,hyday,uid,utype)values(?,?,now(),now(),?,?)";
	@Override
	public int saveVoteOptionRecord(long id, long optionid,long uid,int type) {
		Object[] params={id,optionid,uid,type};
		return getJdbcTemplate().update(SAVE_VOTE_OPTION_RECORD, params);
	}

	private static final String ADD_OPTION_COUNT="update esite.es_interact_vote_option set count = count+1 where id = ?";
	@Override
	public int addOptionCount(long optionid){
		Object[] params={optionid};
		return getJdbcTemplate().update(ADD_OPTION_COUNT, params);
	}

	@Override
	public int delVoteOption(long id)
	{
		String sql="update es_interact_vote_option set status='DEL' where id=?";
		return getJdbcTemplate().update(sql,new Object[]{id});
	}

	@Override
	public int updateVoteOption(long id,String content,String img,String vediourl,String tags,String description,String linked,String linkurl)
	{
		String sql="update es_interact_vote_option set content=?,pic=?,vediourl=?,tags=?,description=?,linked=?,linkurl = ? where id=?";
		return getJdbcTemplate().update(sql,new Object[]{content,img,vediourl,tags,description,linked,linkurl,id});
	}

	@Override
	public VoteOption findOneOption(long id)
	{
		String sql="select * from es_interact_vote_option where id=? and status!='DEL'";
		List<VoteOption> list=getJdbcTemplate().query(sql, new Object[]{id},new VoteOptionContentMapper());
		if(list!=null&&list.size()>0){
			VoteOption vo=list.get(0);
			return vo;
		}
		return null;
	}
	class VoteOptionContentMapper implements RowMapper {
		@Override
		public Object mapRow(ResultSet rs, int arg) throws SQLException {
			VoteOption model = new VoteOption();
			model.setId(rs.getLong("id"));
			model.setVoteid(rs.getLong("voteid"));
			model.setContent(rs.getString("content"));
			model.setTags(rs.getString("tags"));
			model.setImg(rs.getString("pic"));
			model.setVediourl(rs.getString("vediourl"));
			model.setDescription(rs.getString("description"));
			model.setLinked(rs.getString("linked"));
			model.setLinkurl(rs.getString("linkurl"));
			return model;
		}
	}

	private static final String ADD_LOTTERY_CHANCE="insert into esite.es_interact_lottery_user(wbuid,lid,totalnum)values(?,?,?) on duplicate key update totalnum = totalnum + ? ";
	@Override
	public int addLotteryChance(long wbuid, long lid, int chance) {
		Object[] params={wbuid,lid,chance,chance};
		return getJdbcTemplate().update(ADD_LOTTERY_CHANCE,params);
	}

	@Override
	public int deleteVote(long id)
	{
		String sql="update es_interact_vote set status='DEL' where id=?";
		return getJdbcTemplate().update(sql,new Object[]{id});
	}



	class BlockContextRowmapper implements RowMapper{
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			BlockContext bc = new BlockContext();
			bc.setId(rs.getLong("id"));
			bc.setBlockid(rs.getLong("blockid"));
			bc.setContext(rs.getString("context"));
			bc.setType(rs.getString("type"));
			return bc;
		}
	}

	
	@Override
	public int findVoteJoinTotal(long voteid, long entityid, int type)
	{
		return getJdbcTemplate().queryForInt("select count(*) from esite.es_feature_interact_vote_record where wbuid=? and type=? and voteid=?", new Object[]{entityid,type,voteid});
	}

	private static final String UPDATE_RULETYPE_BY_LOTTERYID = "update es_interact_lottery set ruletype = 'G' where id = ?";
	@Override
	public int updateRuletypeByLotteryid(long lotteryid) {
		Object[] params={lotteryid};
		return getJdbcTemplate().update(UPDATE_RULETYPE_BY_LOTTERYID,params);
	}

	@Override
	public long addVote(final long ownerid, final String title)
	{
		final String sql="insert into es_interact_vote(ownerid,title,createtime,starttime,endtime) values(?,?,now(),now(),DATE_ADD(now(),INTERVAL 2 MONTH))";
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
	
	@Override
	public List<VoteOption> findVoteOptionListByVoteid(long voteid)
	{
	       String sql="select o.* from es_interact_vote_option o where o.voteid=? and o.status!='DEL' ";
	       return getJdbcTemplate().query(sql, new Object[]{voteid},new RowMapper(){
	    	   @Override
	    	public Object mapRow(ResultSet rs, int arg1) throws SQLException
	    	{
	    		VoteOption option=new VoteOption();
	    		option.setId(rs.getLong("id"));
	    		option.setVoteid(rs.getLong("voteid"));
	    		option.setPic(rs.getString("pic"));
	    		option.setVediourl(rs.getString("vediourl"));
	    		option.setCreatetime(rs.getTimestamp("createtime"));
	    		option.setContent(rs.getString("content"));
	            option.setCount(rs.getInt("count"));
	            option.setDescription(rs.getString("description"));
	            option.setLinked(rs.getString("linked"));
	            option.setLinkurl(rs.getString("linkurl"));
	    		return option;
	    	}
	       });
	 }
	
	public List<VoteOption> findVoteOptionCountPC(long pageid)
	{
	       String sql="select o.* from es_interact_vote_option o join es_feature_interact_vote v on v.voteid=o.voteid where v.pageid=? and o.status!='DEL' ";
	       return getJdbcTemplate().query(sql, new Object[]{pageid},new RowMapper(){
	    	   @Override
	    	public Object mapRow(ResultSet rs, int arg1) throws SQLException
	    	{
	    		VoteOption option=new VoteOption();
	    		option.setId(rs.getLong("id"));
	    		option.setVoteid(rs.getLong("voteid"));
	    		option.setPic(rs.getString("pic"));
	    		option.setVediourl(rs.getString("vediourl"));
	    		option.setCreatetime(rs.getTimestamp("createtime"));
	    		option.setContent(rs.getString("content"));
	            option.setCount(rs.getInt("count"));
	    		return option;
	    	}
	       });
	 }


	@Override
	public int findUserDaySub(long uid, int type, long optionsid)
	{
		return getJdbcTemplate().queryForInt("select count(id) from esite.es_feature_interact_vote_record_option where uid=? and utype=? and optionid=? and hyday=DATE_FORMAT(NOW(),'%Y-%m-%d')", new Object[]{uid,type,optionsid});
	}


	@Override
	public int updateContnetIdx(long optionid, long voteid, int oldIdx, int newIdx) {
		getJdbcTemplate().update("update es_interact_vote_option set idx=? where idx=? and voteid=?", new Object[]{ oldIdx, newIdx, voteid });
   		return getJdbcTemplate().update("update es_interact_vote_option set idx=? where id=?", new Object[]{ newIdx, optionid });
	}

	@Override
	public Map findIndx(long id) {
		String sql="select voteid,idx from es_interact_vote_option where id=?";
		try{
			return getJdbcTemplate().queryForMap(sql,new Object[]{id});
		}catch (DataAccessException e) {
			return null;
		}
	}

	@Override
	public int deleteIndx(int idx, long voteid) {
		String sql="update es_interact_vote_option set idx=idx-1 where idx>? and voteid=?";
		return getJdbcTemplate().update(sql,new Object[]{idx,voteid});
	}

}
