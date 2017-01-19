package com.huiyee.interact.checkin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.huiyee.interact.checkin.model.CheckinData;
import com.huiyee.interact.checkin.model.CheckinRecord;

public class CheckinRecordDao implements ICheckinRecordDao
{
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void addCheckIn(long wbuid, long ownerwbuid, int addnum, int daynum, long pageid, String ip, String terminal, String source,int utype)
	{
		jdbcTemplate.update("insert into es_interact_checkin_record (wbuid,ownerwbuid,addbalance,daynum,pageid,ip,terminal,source,createtime,type) values (?,?,?,?,?,?,?,?,now(),?)", new Object[]
		{ wbuid, ownerwbuid, addnum, daynum, pageid, ip, terminal, source ,utype});
	}

	@Override
	public CheckinRecord findLastCheckIn(long wbuid, long ownerwbuid,int utype)
	{
		List<CheckinRecord> ls = jdbcTemplate.query("select * from es_interact_checkin_record where ownerwbuid=? and wbuid=? and type=? order by id desc limit 1", new Object[]
		{ ownerwbuid, wbuid,utype }, new RowMapper()
		{
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CheckinRecord cr = new CheckinRecord();
				cr.setId(rs.getLong("id"));
				cr.setAddbalance(rs.getInt("addbalance"));
				cr.setOwnerwbuid(rs.getLong("ownerwbuid"));
				cr.setCreatetime(rs.getTimestamp("createtime"));
				cr.setIp(rs.getString("ip"));
				cr.setPageid(rs.getLong("pageid"));
				cr.setSource(rs.getString("source"));
				cr.setTerminal(rs.getString("terminal"));
				cr.setWbuid(rs.getLong("wbuid"));
				cr.setDaynum(rs.getInt("daynum"));
				return cr;
			}
		});
		if (ls != null && ls.size() > 0)
		{
			return ls.get(0);
		}

		return null;
	}

	@Override
	public int findCheckinDataTotal(long ownerwbuid,int utype)
	{
		return jdbcTemplate.queryForInt("select count(*) from es_interact_checkin_record where ownerwbuid=? and type = ?", new Object[]
		{ ownerwbuid ,utype});
	}

	@Override
	public List<CheckinData> findCheckinData(long ownerwbuid,int utype, int start, int size)
	{
		String sql = "";
		if(utype == 0){
			sql = "select count(*) total, max(record.createtime) createtime,user.wbuid wbuid,user.nickname nickName from es_interact_checkin_record record  left outer join es_sina_user user on record.wbuid=user.wbuid where record.ownerwbuid=? and record.type = ? group by record.wbuid limit ?,?";
		}else if(utype == 1){
			sql = "select count(*) total, max(record.createtime) createtime,user.id wbuid,user.nickname nickName from es_interact_checkin_record record left outer join es_wx_user user on record.wbuid = user.id where record.ownerwbuid=? and record.type = ? group by record.wbuid limit ?,?";
		}
		List<CheckinData> list = jdbcTemplate.query(sql,new Object[]{ ownerwbuid,utype,   start, size }, new RowMapper(){
					@Override
					public Object mapRow(ResultSet rs, int arg1) throws SQLException
					{
						CheckinData cd = new CheckinData();
						cd.setTotal(rs.getInt("total"));
						cd.setCreatetime(rs.getTimestamp("createtime"));
						cd.setWbuid(rs.getLong("wbuid"));
						cd.setNickName(rs.getString("nickName"));
						return cd;
					}
				});
		return list;
	}

	@Override
	public CheckinRecord findLastCheckInByHyuid(long hyuid)
	{
		List<CheckinRecord> ls = jdbcTemplate.query("select * from es_interact_checkin_record where hyuid=? order by id desc limit 1", new Object[]
		{ hyuid}, new RowMapper()
		{
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CheckinRecord cr = new CheckinRecord();
				cr.setId(rs.getLong("id"));
				cr.setHyuid(rs.getLong("hyuid"));
				cr.setAddbalance(rs.getInt("addbalance"));
				cr.setDaynum(rs.getInt("daynum"));
				cr.setCreatetime(rs.getTimestamp("createtime"));
				cr.setIp(rs.getString("ip"));
				cr.setSource(rs.getString("source"));
				cr.setTerminal(rs.getString("terminal"));
				return cr;
			}
		});
		if (ls != null && ls.size() > 0)
		{
			return ls.get(0);
		}

		return null;
	}

	@Override
	public void addCheckIn(long hyuid, int addnum, int daynum, String ip, String terminal, String source)
	{
		String sql = "insert into es_interact_checkin_record (hyuid,addbalance,daynum,ip,terminal,source,createtime) values (?,?,?,?,?,?,now())";
		jdbcTemplate.update(sql, new Object[]{ hyuid, addnum, daynum, ip, terminal, source});
	}
}
