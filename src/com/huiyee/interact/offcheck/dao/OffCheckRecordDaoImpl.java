
package com.huiyee.interact.offcheck.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;

import net.sf.json.JSONObject;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.interact.offcheck.model.OffCheckLog;
import com.huiyee.interact.offcheck.model.OffCheckRecord;
import com.huiyee.interact.offcheck.model.OffCheckSource;

public class OffCheckRecordDaoImpl extends AbstractDao implements IOffCheckRecordDao
{
	@Override
	public int findRecordTotal(long owner, JSONObject sift)
	{
		List<Object> list = new ArrayList<Object>();
		String sql = "select count(*) from es_off_check_record r join es_off_check_source s on r.sid=s.id where s.owner=? ";
		list.add(owner);
		if (sift.has("sourceid"))
		{
			int sid = Integer.parseInt((String) sift.getJSONArray("sourceid").get(0));
			if (sid != 0)
			{
				sql += " and s.id=? ";
				list.add(sid);
			}

		}
		if(sift.has("starttime")){
			String starttime=(String) sift.getJSONArray("starttime").get(0);
			if(StringUtils.isNotEmpty(starttime)){
				sql +=" and lastvisttime>=? ";
				list.add(starttime);
			}
		}
		if(sift.has("endtime")){
			String endtime=(String) sift.getJSONArray("endtime").get(0);
			if(StringUtils.isNotEmpty(endtime)){
				sql +=" and lastvisttime<=? ";
				list.add(endtime);	
			}
		}
		return getJdbcTemplate().queryForInt(sql, list.toArray());
	}

	@Override
	public List<OffCheckRecord> findRecordList(long owner, JSONObject sift, int start, int size)
	{
		List<Object> list = new ArrayList<Object>();
		String sql = "select * from es_off_check_record r join es_off_check_source s on r.sid=s.id where s.owner=? ";
		list.add(owner);
		if (sift.has("sourceid"))
		{
			int sid = Integer.parseInt((String) sift.getJSONArray("sourceid").get(0));
			if (sid != 0)
			{
				sql += " and s.id=? ";
				list.add(sid);
			}
		}
		if(sift.has("starttime")){
			String starttime=(String) sift.getJSONArray("starttime").get(0);
			if(StringUtils.isNotEmpty(starttime)){
				sql +=" and lastvisttime>=? ";
				list.add(starttime);
			}
		}
		if(sift.has("endtime")){
			String endtime=(String) sift.getJSONArray("endtime").get(0);
			if(StringUtils.isNotEmpty(endtime)){
				sql +=" and lastvisttime<=? ";
				list.add(endtime);	
			}
		}
		sql += " order by lastvisttime desc limit ?,?";
		list.add(start);	list.add(size);
		return getJdbcTemplate().query(sql, list.toArray(), new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				OffCheckRecord r = new OffCheckRecord();

				OffCheckSource os = new OffCheckSource();
				os.setId(rs.getLong("s.id"));
				os.setOwner(rs.getLong("s.owner"));
				os.setOcid(rs.getLong("s.ocid"));
				os.setFpageid(rs.getLong("s.fpageid"));
				os.setSpageid(rs.getLong("s.spageid"));
				os.setFensi(rs.getString("s.fensi"));
				os.setName(rs.getString("s.name"));
				os.setCreatetime(rs.getTimestamp("s.createtime"));
				os.setGzeid(rs.getLong("s.gzeid"));
				r.setSource(os);
				r.setId(rs.getLong("r.id"));
				r.setLastip(rs.getString("lastip"));
				r.setLastarea(rs.getString("lastarea"));
				r.setLastvisttime(rs.getTimestamp("lastvisttime"));
				r.setSid(rs.getLong("sid"));
				r.setVistnum(rs.getInt("vistnum"));
				r.setWxuid(rs.getLong("wxuid"));

				return r;
			}
		});
	}

	@Override
	public List<OffCheckLog> findLogs(long owner, long sourceid, long wxuid, int start, int size)
	{
		return getJdbcTemplate().query("select * from es_off_check_log where wxuid=? and sid=? order by id desc limit ?,?", new Object[]
		{ wxuid, sourceid, start, size }, new RowMapper()
		{

			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				OffCheckLog l = new OffCheckLog();
				l.setIp(rs.getString("ip"));
				l.setCreatetime(rs.getTimestamp("createtime"));
				return l;
			}
		});
	}
	
	
	@Override
	public int findLogTotal(long owner, JSONObject sift)
	{
		List<Object> list=new ArrayList<Object>();
		String sql="select count(*) from es_off_check_log l join es_off_check_source s on l.sid=s.id where s.owner=? ";
		list.add(owner);
		if (sift.has("sourceid"))
		{
			int sid = Integer.parseInt((String) sift.getJSONArray("sourceid").get(0));
			if (sid != 0)
			{
				sql += " and s.id=? ";
				list.add(sid);
			}
		}
		if(sift.has("starttime")){
			String starttime=(String) sift.getJSONArray("starttime").get(0);
			if(StringUtils.isNotEmpty(starttime)){
				sql +=" and l.createtime>=? ";
				list.add(starttime);
			}
		}
		if(sift.has("endtime")){
			String endtime=(String) sift.getJSONArray("endtime").get(0);
			if(StringUtils.isNotEmpty(endtime)){
				sql +=" and l.createtime<=? ";
				list.add(endtime);	
			}
		}
		return getJdbcTemplate().queryForInt(sql, list.toArray());
	}
	
	@Override
	public List<OffCheckLog> findLogs(long owner, int start, int size, JSONObject sift)
	{
		List<Object> list=new ArrayList<Object>();
		String sql="select * from es_off_check_log l join es_off_check_source s on l.sid=s.id where s.owner=? ";
		list.add(owner);
		if (sift.has("sourceid"))
		{
			int sid = Integer.parseInt((String) sift.getJSONArray("sourceid").get(0));
			if (sid != 0)
			{
				sql += " and s.id=? ";
				list.add(sid);
			}
		}
		if(sift.has("starttime")){
			String starttime=(String) sift.getJSONArray("starttime").get(0);
			if(StringUtils.isNotEmpty(starttime)){
				sql +=" and l.createtime>=? ";
				list.add(starttime);
			}
		}
		if(sift.has("endtime")){
			String endtime=(String) sift.getJSONArray("endtime").get(0);
			if(StringUtils.isNotEmpty(endtime)){
				sql +=" and l.createtime<=? ";
				list.add(endtime);	
			}
		}
		sql +=" order by l.id desc limit ?,?";
		list.add(start);
		list.add(size);
		return getJdbcTemplate().query(sql, list.toArray(),new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				OffCheckLog l = new OffCheckLog();
				l.setIp(rs.getString("ip"));
				l.setCreatetime(rs.getTimestamp("createtime"));
				l.setWxuid(rs.getLong("l.wxuid"));
				l.setArea(rs.getString("l.area"));
				OffCheckSource os = new OffCheckSource();
				os.setId(rs.getLong("s.id"));
				os.setOwner(rs.getLong("s.owner"));
				os.setOcid(rs.getLong("s.ocid"));
				os.setFpageid(rs.getLong("s.fpageid"));
				os.setSpageid(rs.getLong("s.spageid"));
				os.setFensi(rs.getString("s.fensi"));
				os.setName(rs.getString("s.name"));
				os.setCreatetime(rs.getTimestamp("s.createtime"));
				os.setGzeid(rs.getLong("s.gzeid"));
				l.setSource(os);
				
				return l;
			}
		});
	}

	@Override
	public int findLogTotal(long owner, long sourceid, long wxuid)
	{
		return getJdbcTemplate().queryForInt("select count(*) from es_off_check_log where wxuid=? and sid=?", new Object[]
		{ wxuid, sourceid });
	}

	@Override
	public void addRecord(long wxuid, String ip, long sid,String area,int num)
	{
		Object[] params =
		{ wxuid, ip, sid, area,num,ip,area };
		String sql = "INSERT INTO es_off_check_record (wxuid,lastip,sid,vistnum,lastvisttime,lastarea) VALUES(?,?,?,1,now(),?) ON DUPLICATE KEY UPDATE vistnum=vistnum+?,lastvisttime=now(),lastip=?,lastarea=?";
		getJdbcTemplate().update(sql, params);
	}

	@Override
	public void addRecordLog(long wxuid, String ip, long sid,String area)
	{
		Object[] params =
		{ wxuid, ip, sid ,area};
		String sql = "INSERT INTO es_off_check_log (wxuid,ip,sid,createtime,area) VALUES(?,?,?,now(),?)";
		getJdbcTemplate().update(sql, params);
	}

	@Override
	public long findLogCurDate(long wxuid, long sourceid)
	{
		try
		{
			return getJdbcTemplate().queryForLong("select id from es_off_check_log where date(createtime) = curdate() and wxuid=? and sid=?",new Object[]{wxuid,sourceid});
		} catch (Exception e)
		{
			return 0;
		}
	}

	@Override
	public void updateLogCurDate(long id, String area, String ip)
	{
		getJdbcTemplate().update("update es_off_check_log set ip=?,area=?,createtime=now() where id=?",new Object[]{ip,area,id});
	}
}
