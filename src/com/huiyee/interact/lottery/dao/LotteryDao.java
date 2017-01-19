package com.huiyee.interact.lottery.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.model.SinaUser;
import com.huiyee.esite.model.WxUser;
import com.huiyee.interact.lottery.model.Lottery;
import com.huiyee.interact.lottery.model.LotteryUserRecord;
import com.huiyee.interact.lottery.model.LotteryUserSub;
import com.huiyee.interact.lottery.model.LotteryWinner;

public class LotteryDao implements IlotteryDao
{

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int findLotteryTotal(long owner, String type, long omid)
	{
		return jdbcTemplate.queryForInt("select count(id) from es_interact_lottery where owner=? and status!='D' and type=? and omid=?", new Object[]
		{ owner, type, omid });
	}

	@Override
	public List<Lottery> findAllLottery(int start, int size, long owner, String type, long omid)
	{
		return jdbcTemplate.query("select * from es_interact_lottery where owner=? and status!='D'  and type=? and omid=? order by id desc limit ?,?", new Object[]
		{ owner, type, omid, start, size }, new LotteryRowMapper());
	}

	class LotteryRowMapper implements RowMapper
	{
		public Object mapRow(ResultSet rs, int arg1) throws SQLException
		{
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
			l.setUserzjtotal(rs.getInt("userzjtotal"));
			l.setUserdaynum(rs.getInt("userdaynum"));
			l.setStartnote(rs.getString("startnote"));
			l.setEndnote(rs.getString("endnote"));
			l.setGzeid(rs.getLong("gzeid"));
			l.setDrawnum(rs.getInt("drawnum"));
			l.setWinnum(rs.getInt("winnum"));
			return l;
		}
	}

	@Override
	public Lottery findLotteryById(long id)
	{
		List<Lottery> ls = jdbcTemplate.query("select * from es_interact_lottery where id=?", new Object[]
		{ id }, new LotteryRowMapper());
		if (ls != null && ls.size() > 0)
		{
			return ls.get(0);
		}
		return null;
	}

	@Override
	public long saveLottery(final Lottery l, final long omid)
	{
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator()
		{
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0
						.prepareStatement(
								"insert into esite.es_interact_lottery (name,starttime,endtime,owner,type,zjl,detail,endimg,username,usernamevalue,userphone,userphonevalue,useremail,useremailvalue,userlocation,userlocationvalue,usertype,assignuser,usertotal,userdaynum,startnote,endnote,omid,userzjtotal,gzeid,drawnum,winnum) value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
								new String[]
								{ "id" });
				int i = 1;
				ps.setString(i++, l.getName());
				
				ps.setTimestamp(i++, l.getStarttime()==null?null:new Timestamp(l.getStarttime().getTime()));
				ps.setTimestamp(i++, l.getEndtime()==null?null:new Timestamp(l.getEndtime().getTime()));
				ps.setLong(i++, l.getOwner());
				ps.setString(i++, l.getType());
				ps.setInt(i++, l.getZjl());
				ps.setString(i++, l.getDetail());
				ps.setString(i++, l.getEndimg());
				ps.setString(i++, l.getUserName());
				ps.setString(i++, l.getUserNameValue());
				ps.setString(i++, l.getUserPhone());
				ps.setString(i++, l.getUserPhoneValue());
				ps.setString(i++, l.getUserEmail());
				ps.setString(i++, l.getUserEmailValue());
				ps.setString(i++, l.getUserLocation());
				ps.setString(i++, l.getUserLocationValue());
				ps.setString(i++, l.getUsertype());
				ps.setString(i++, l.getAssignuser());
				ps.setInt(i++, l.getUsertotal());
				ps.setInt(i++, l.getUserdaynum());
				ps.setString(i++, l.getStartnote());
				ps.setString(i++, l.getEndnote());
				ps.setLong(i++, omid);
				ps.setInt(i++, l.getUserzjtotal());
				ps.setLong(i++, l.getGzeid());
				ps.setInt(i++, l.getDrawnum());
				ps.setInt(i++, l.getWinnum());
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().longValue();
		return id;
	}

	@Override
	public int updateLottery(Lottery l, long ownerid, long lid)
	{
		int i = jdbcTemplate
				.update(
						"update esite.es_interact_lottery  set name=?,starttime=?,endtime=?,type=?,zjl=?,detail=?,endimg=?,username=?,usernamevalue=?,userphone=?,userphonevalue=?,useremail=?,useremailvalue=?,userlocation=?,userlocationvalue=?,usertype=?,assignuser=?,usertotal=?,userdaynum=?,startnote=?,endnote=?,userzjtotal=?,gzeid=?,drawnum=?,winnum=? where id=? and owner=?",
						new Object[]
						{ l.getName(), new Timestamp(l.getStarttime().getTime()), new Timestamp(l.getEndtime().getTime()), l.getType(), l.getZjl(), l.getDetail(), l.getEndimg(), l.getUserName(), l.getUserNameValue(), l.getUserPhone(), l.getUserPhoneValue(), l.getUserEmail(), l.getUserEmailValue(),
								l.getUserLocation(), l.getUserLocationValue(), l.getUsertype(), l.getAssignuser(), l.getUsertotal(), l.getUserdaynum(), l.getStartnote(), l.getEndnote(),l.getUserzjtotal(),l.getGzeid(),l.getDrawnum(),l.getWinnum(),lid, ownerid });
		return i;
	}

	@Override
	public int updateStatus(long lid, String status, long owner)
	{
		int i = jdbcTemplate.update("update esite.es_interact_lottery set status=? where id= ? and owner=?", new Object[]
		{ status, lid, owner });
		return i;
	}

	private static final String SAVE_LOTTERY_WINNER_USER = "replace into esite.es_interact_lottery_user_sub(lurid,username,userphone,useremail,userlocation,createtime) values(?,?,?,?,?,now())";

	@Override
	public long saveLotteryWinnerUser(LotteryUserSub l)
	{
		Object[] params =
		{ l.getLurid(), l.getUsername(), l.getUserphone(), l.getUseremail(), l.getUserlocation() };
		return jdbcTemplate.update(SAVE_LOTTERY_WINNER_USER, params);
	}

	@Override
	public List<Lottery> findLotteryByOwner(long ownerid)
	{
		return jdbcTemplate.query("select * from es_interact_lottery where owner=? and status!='D'", new Object[]
		{ ownerid }, new RowMapper()
		{
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				Lottery l = new Lottery();
				l.setName(rs.getString("name"));
				l.setId(rs.getLong("id"));
				return l;
			}
		});
	}

	@Override
	public void updateLotteryGuanLian(long lotteryid)
	{
		jdbcTemplate.update("update es_interact_lottery set type='G' where id=?", new Object[]
		{ lotteryid });
	}

	private static final String FIND_LOTTERY_WINNER = "select r.id,r.lid,r.lpid,r.wbuid,r.type,p.name from es_interact_lottery_user_record r join es_interact_lottery_prize p on r.lpid =p.id where r.lid = ? and r.id > ? order by r.id asc";
	@Override
	public List<LotteryWinner> findLotteryWinner(long lid,long start,int size) {
		Object[] params={lid,start};
		return jdbcTemplate.query(FIND_LOTTERY_WINNER, params, new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				LotteryWinner w = new LotteryWinner();
				w.setId(rs.getLong("id"));
				w.setUid(rs.getLong("wbuid"));
				w.setUtype(rs.getInt("type"));
				w.setLid(rs.getLong("lid"));
				w.setLpid(rs.getLong("lpid"));
				w.setPrizeName(rs.getString("name"));
				return w;
			}
			
		});
	}
	
	private static final String FIND_WX_USER = "select * from es_wx_user where id = ?";
	@Override
	public WxUser findWxUser(long wxuid) {
		Object[] params={wxuid};
		List<WxUser> list = jdbcTemplate.query(FIND_WX_USER,params,new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				WxUser u = new WxUser();
				u.setNickname(rs.getString("nickname"));
				u.setHeadimgurl(rs.getString("headimgurl"));
				return u;
			}
			
		});
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	private static final String FIND_SINA_USER = "select * from es_sina_user where wbuid = ?";
	@Override
	public SinaUser findSinaUser(long wbuid) {
		Object[] params={wbuid};
		List<SinaUser> list = jdbcTemplate.query(FIND_SINA_USER,params,new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				SinaUser u = new SinaUser();
				u.setNickname(rs.getString("nickname"));
				u.setImageurl(rs.getString("url"));
				return u;
			}
			
		});
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public List<LotteryUserRecord> findUserRecord(long lid, long entity, int type)
	{
		List<LotteryUserRecord> list=jdbcTemplate.query("select * from es_interact_lottery_user_record r where r.lid=? and wbuid=? and type=?", new Object[]{lid,entity,type}, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				LotteryUserRecord lur=new LotteryUserRecord();
				lur.setId(rs.getLong("id"));
				lur.setStatus(rs.getInt("status"));
				lur.setLpid(rs.getLong("lpid"));
				return lur;
			}
		});
		return list;
	}

	@Override
	public long addLottery(final long ownerid, final String type,final String title)
	{
		final String sql="insert into es_interact_lottery(name,owner,type) values(?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator()
		{
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(sql,new String[]{ "id" });
				int i = 1;
				ps.setString(i++, title);
				ps.setLong(i++, ownerid);
				ps.setString(i++, type);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}
	
	@Override
	public List<LotteryUserRecord> findRecordByUser(long lid, long entity, int type)
	{
		String sql="select * from es_interact_lottery_user_record r join es_interact_lottery_prize p on r.lpid=p.id where r.lpid is not null and r.lid=? and r.wbuid=? and r.type=?";
		return jdbcTemplate.query(sql, new Object[]{lid,entity,type}, new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				LotteryUserRecord lur=new LotteryUserRecord();
				lur.setLpName(rs.getString("p.name"));
				Date d=rs.getTimestamp("r.createtime");
				if(d!=null){
					lur.setCreatetime(d.toGMTString());
				}
				lur.setPrice(rs.getInt("p.price"));
				return lur;
			}
		});
	}

}
