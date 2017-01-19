
package com.huiyee.interact.lottery.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.util.DateUtil;
import com.huiyee.interact.lottery.model.LotteryUserRecord;
import com.huiyee.interact.lottery.model.LotteryUserShow;
import com.huiyee.interact.lottery.model.LotteryUserSub;
import com.huiyee.interact.lottery.model.LotteryWinnerDetail;

public class LotteryUserRecordDao implements ILotteryUserRecordDao
{

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<LotteryUserRecord> findRecord(long lid, long wbuid, String type, int media, int start, int size)
	{
		String sql = "select * from es_interact_lottery_user_record r left outer join es_interact_lottery_prize p on p.id=r.lpid where r.lid=? and r.wbuid=? and r.type=?  ";
		if ("Y".equalsIgnoreCase(type))
		{
			sql += " and r.status!=0 ";
		}
		sql += " limit ?,? ";
		return jdbcTemplate.query(sql, new Object[]
		{ lid, wbuid, media, start, size }, new MyRowMapper());
	}

	@Override
	public int findRecordTotal(long lid, long wbuid, String type, int media)
	{
		String sql = "select count(r.id) from es_interact_lottery_user_record r  where r.lid=? and r.wbuid=?  and r.type=?";
		if ("Y".equalsIgnoreCase(type))
		{
			sql += " and r.status!=0 ";
		}
		return jdbcTemplate.queryForInt(sql, new Object[]
		{ lid, wbuid, media });
	}

	class MyRowMapper implements RowMapper
	{

		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException
		{
			LotteryUserRecord l = new LotteryUserRecord();
			l.setId(rs.getLong("r.id"));
			l.setCreatetime(DateUtil.getDateTimeString(rs.getTimestamp("r.createtime")));
			l.setLpid(rs.getLong("p.id"));
			l.setLpName(rs.getString("p.name"));
			l.setStatus(rs.getInt("status"));
			l.setIp(rs.getString("r.ip"));
			l.setTerminal(rs.getString("r.terminal"));
			l.setSource(rs.getString("r.source"));
			return l;
		}
	}

	@Override
	public List<LotteryWinnerDetail> findRecordDetail(long lpid, String nickName, int start, int size)
	{
		List<Object> list = new ArrayList<Object>();
		String sql = "select * from es_interact_lottery_prize prize join es_interact_lottery_user_record record  on prize.id=record.lpid  left outer join es_interact_lottery_user_sub sub on sub.lurid=record.id where prize.id=? ";
		list.add(lpid);
		sql += " limit ?,?";
		list.add(start);
		list.add(size);
		return jdbcTemplate.query(sql, list.toArray(), new RowMapper()
		{

			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				LotteryWinnerDetail l = new LotteryWinnerDetail();
				LotteryUserRecord record = new LotteryUserRecord();
				LotteryUserSub sub = new LotteryUserSub();

				record.setCreatetime(DateUtil.getDateTimeString(rs.getTimestamp("record.createtime")));
				record.setWbuid(rs.getLong("record.wbuid"));
				record.setStatus(rs.getInt("record.status"));
				record.setLpcid(rs.getLong("record.lpcid"));
				// record.setNickName(rs.getString("user.nickName"));
				sub.setUsername(rs.getString("sub.username"));
				sub.setUserphone(rs.getString("sub.userphone"));
				sub.setUseremail(rs.getString("sub.useremail"));
				sub.setUserlocation(rs.getString("sub.userlocation"));
				l.setType(rs.getInt("record.type"));
				l.setRecord(record);
				l.setSub(sub);
				return l;
			}
		});
	}

	@Override
	public int findRecordDetailTotal(long lpid, String nickName)
	{
		List<Object> list = new ArrayList<Object>();
		String sql = "select count(*) from es_interact_lottery_prize prize join es_interact_lottery_user_record record   on prize.id=record.lpid left outer join es_interact_lottery_user_sub sub on sub.lurid=record.id where prize.id=? and record.status!=0";
		list.add(lpid);
		return jdbcTemplate.queryForInt(sql, list.toArray());
	}

	@Override
	public long addLotteryCmp(final String nickname,final Long wbuid, final Long lid, final Long lpid, final Long lpcid, final Long pageid, final String ip, final String terminal, final String source,
			final int status, final int type)
	{
		KeyHolder keyholder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator()
		{
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				if (lpcid != null && lpid != null)
				{
					PreparedStatement ps = arg0
							.prepareStatement(
									"insert into es_interact_lottery_user_record (wbuid,lid,lpid,lpcid,pageid,ip,terminal,source,status,createtime,type,nickname) values (?,?,?,?,?,?,?,?,?,now(),?,?)",
									new String[]
									{ "id" });
					ps.setLong(1, wbuid);
					ps.setLong(2, lid);
					ps.setLong(3, lpid);
					ps.setLong(4, lpcid);
					ps.setLong(5, pageid);
					ps.setString(6, ip);
					ps.setString(7, terminal);
					ps.setString(8, source);
					ps.setInt(9, status);
					ps.setInt(10, type);
					ps.setString(11, nickname);
					return ps;
				} else if (lpcid != null && lpid == null)
				{
					PreparedStatement ps = arg0.prepareStatement(
							"insert into es_interact_lottery_user_record (wbuid,lid,lpcid,pageid,ip,terminal,source,status,createtime,type,nickname) values (?,?,?,?,?,?,?,?,now(),?,?)",
							new String[]
							{ "id" });
					ps.setLong(1, wbuid);
					ps.setLong(2, lid);
					ps.setLong(3, lpcid);
					ps.setLong(4, pageid);
					ps.setString(5, ip);
					ps.setString(6, terminal);
					ps.setString(7, source);
					ps.setInt(8, status);
					ps.setInt(9, type);
					ps.setString(10, nickname);
					return ps;
				} else if (lpcid == null && lpid != null)
				{
					PreparedStatement ps = arg0.prepareStatement(
							"insert into es_interact_lottery_user_record (wbuid,lid,lpid,pageid,ip,terminal,source,status,createtime,type,nickname) values (?,?,?,?,?,?,?,?,now(),?,?)",
							new String[]
							{ "id" });
					ps.setLong(1, wbuid);
					ps.setLong(2, lid);
					ps.setLong(3, lpid);
					ps.setLong(4, pageid);
					ps.setString(5, ip);
					ps.setString(6, terminal);
					ps.setString(7, source);
					ps.setInt(8, status);
					ps.setInt(9, type);
					ps.setString(10, nickname);
					return ps;
				} else
				{
					PreparedStatement ps = arg0.prepareStatement(
							"insert into es_interact_lottery_user_record (wbuid,lid,pageid,ip,terminal,source,status,createtime,type,nickname) values (?,?,?,?,?,?,?,now(),?,?)", new String[]
							{ "id" });
					ps.setLong(1, wbuid);
					ps.setLong(2, lid);
					ps.setLong(3, pageid);
					ps.setString(4, ip);
					ps.setString(5, terminal);
					ps.setString(6, source);
					ps.setInt(7, status);
					ps.setInt(8, type);
					ps.setString(9, nickname);
					return ps;
				}
			}
		}, keyholder);
		return keyholder.getKey().longValue();
	}

	@Override
	public List<LotteryWinnerDetail> findRecordDetail(long lpid)
	{
		String sql = "select * from es_interact_lottery_prize prize join es_interact_lottery_user_record record  on prize.id=record.lpid join es_sina_user user on record.wbuid=user.wbuid left outer join es_interact_lottery_user_sub sub on sub.lurid=record.id where prize.id=? and record.status!=0";
		return jdbcTemplate.query(sql, new Object[]
		{ lpid }, new RowMapper()
		{
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				LotteryWinnerDetail l = new LotteryWinnerDetail();
				LotteryUserRecord record = new LotteryUserRecord();
				LotteryUserSub sub = new LotteryUserSub();

				record.setCreatetime(DateUtil.getDateTimeString(rs.getTimestamp("record.createtime")));
				record.setWbuid(rs.getLong("user.wbuid"));
				record.setNickName(rs.getString("user.nickName"));
				sub.setUsername(rs.getString("sub.username"));
				sub.setUserphone(rs.getString("sub.userphone"));
				sub.setUseremail(rs.getString("sub.useremail"));
				sub.setUserlocation(rs.getString("sub.userlocation"));
				l.setRecord(record);
				l.setSub(sub);
				return l;
			}
		});
	}

	@Override
	public List<LotteryWinnerDetail> findWxRecordDetail(long lpid)
	{
		String sql = "select * from es_interact_lottery_prize prize join es_interact_lottery_user_record record  on prize.id=record.lpid join es_wx_user user on record.wbuid=user.id left outer join es_interact_lottery_user_sub sub on sub.lurid=record.id where prize.id=? and record.status!=0";
		return jdbcTemplate.query(sql, new Object[]
		{ lpid }, new RowMapper()
		{

			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				LotteryWinnerDetail l = new LotteryWinnerDetail();
				LotteryUserRecord record = new LotteryUserRecord();
				LotteryUserSub sub = new LotteryUserSub();

				record.setCreatetime(DateUtil.getDateTimeString(rs.getTimestamp("record.createtime")));
				record.setWbuid(rs.getLong("user.id"));
				record.setNickName(rs.getString("user.nickName"));
				sub.setUsername(rs.getString("sub.username"));
				sub.setUserphone(rs.getString("sub.userphone"));
				sub.setUseremail(rs.getString("sub.useremail"));
				sub.setUserlocation(rs.getString("sub.userlocation"));
				l.setRecord(record);
				l.setSub(sub);
				return l;
			}
		});
	}

	@Override
	public List<LotteryUserRecord> findUserJoin(long lid, long fuid, int utype)
	{
		String sql = "select * from es_interact_lottery_user_record r left outer join es_interact_lottery_prize p on p.id=r.lpid where r.lid=? and r.wbuid=? and r.type=? order by r.status desc";
		return jdbcTemplate.query(sql, new Object[]
		{ lid, fuid, utype }, new MyRowMapper());
	}

	@Override
	public List<LotteryUserShow> findRecordToScreent(long lid, long lpid, long start, long end)
	{
		String sql = "select * from es_interact_lottery_user_record r join es_interact_lottery_prize p on r.lpid=p.id where r.lid=? and lpid=?  and r.id>?  and r.id<=?";
		return jdbcTemplate.query(sql, new Object[]
		{ lid, lpid, start, end }, new RowMapper()
		{

			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				LotteryUserShow lus = new LotteryUserShow();
				lus.setUid(rs.getLong("r.wbuid"));
				lus.setUtype(rs.getInt("r.type"));
				lus.setLpid(rs.getLong("p.id"));
				lus.setPrice(rs.getInt("p.price"));
				return lus;
			}
		});
	}

	@Override
	public long findMaxRecordid(long lid)
	{
		try
		{
			return jdbcTemplate.queryForLong("select max(id) from es_interact_lottery_user_record where lid=? ", new Object[]
			{ lid });
		} catch (DataAccessException e)
		{
			return 0;
		}
	}
	
	@Override
	public int findLotteryTotal(long lid)
	{
		return jdbcTemplate.queryForInt("select count(id) from es_interact_lottery_user_record where lid=?", new Object[]{lid});
	}
	
	@Override
	public int findWinTotal(long lid)
	{
		return jdbcTemplate.queryForInt("select count(id) from es_interact_lottery_user_record where lid=? and status!=0 ", new Object[]{lid});
	}
	
	@Override
	public int updateLotteryClean(long lid)
	{
		String[] sql={
				"delete s from  es_interact_lottery_user_record  r left outer join es_interact_lottery_user_sub s on s.lurid=r.id where r.lid="+lid,	
				"delete from es_interact_lottery_user_record where lid="+lid,
				"delete from es_interact_lottery_user_date where lid="+lid,
				"delete from es_interact_lottery_user where lid="+lid,
				"delete from es_interact_lottery_user_chance_record where lid="+lid,
				"update es_interact_lottery_prize set used=0 where lid="+lid,
				"delete from es_interact_lottery_lose where lid="+lid
			};
			int[] rs=jdbcTemplate.batchUpdate(sql);
			int sum=0;
			for (int i : rs)
			{
				sum=sum+i;
			}
			return sum;
	}

	@Override
	public List<LotteryWinnerDetail> findLotteryUserRecord(long lpid, String processstatus, int start, int size)
	{
		List<Object> list = new ArrayList<Object>();
		String sql = "select * from es_interact_lottery_prize prize join es_interact_lottery_user_record record  on prize.id=record.lpid where prize.id=? ";
		list.add(lpid);
		if(StringUtils.isNotBlank(processstatus)){
			sql += " and processstatus = ?";
			list.add(processstatus);
		}
		sql += " limit ?,?";
		list.add(start);
		list.add(size);
		return jdbcTemplate.query(sql, list.toArray(), new RowMapper()
		{
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				LotteryWinnerDetail l = new LotteryWinnerDetail();
				LotteryUserRecord record = new LotteryUserRecord();
				record.setCreatetime(DateUtil.getDateTimeString(rs.getTimestamp("record.createtime")));
				record.setWbuid(rs.getLong("record.wbuid"));
				record.setStatus(rs.getInt("record.status"));
				record.setLpcid(rs.getLong("record.lpcid"));
				record.setProcessstatus(rs.getString("record.processstatus"));
				record.setProcesstime(rs.getTimestamp("record.processtime"));
				l.setType(rs.getInt("record.type"));
				l.setRecord(record);
				return l;
			}
		});
	}

	@Override
	public int findLotteryUserRecordTotal(long lpid, String processstatus)
	{
		List<Object> list = new ArrayList<Object>();
		String sql = "select count(*) from es_interact_lottery_prize prize join es_interact_lottery_user_record record  on prize.id=record.lpid where prize.id=? ";
		list.add(lpid);
		if(StringUtils.isNotBlank(processstatus)){
			sql += " and processstatus = ?";
			list.add(processstatus);
		}
		return jdbcTemplate.queryForInt(sql,list.toArray());
	}

	@Override
	public int findUserZjTotal(long lid, long uid, int type)
	{
		return jdbcTemplate.queryForInt("select count(id) from es_interact_lottery_user_record where lid=? and status!=0 and wbuid=? and type=?", new Object[]{lid,uid,type});
	}
}
