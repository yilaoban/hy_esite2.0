package com.huiyee.interact.lottery.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.model.CrmWxHongbaoCheck;
import com.huiyee.esite.model.WxHbCheckLog;


public class WxHongbaoDaoImpl implements IWxHongbaoDao
{
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public long saveCrmWxHongbaoCheck(final int type,final long lid,final long mpid)
	{
		final String sql = "insert into crm.crm_wx_hongbao_check(type,enid,status,updatestatus,mpid) values(?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator()
		{
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(sql, new String[]
				{ "id" });
				ps.setInt(1, type);
				ps.setLong(2, lid);
				ps.setInt(3, 0);
				ps.setInt(4, 0);
				ps.setLong(5, mpid);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().longValue();
		return id;
	}

	@Override
	public int saveCrmWxHongbaoCheckLog(int total, long ckid, long account, String accountname, String reason, String ip)
	{
		String sql = "insert into crm.crm_wx_hongbao_check_log(ckid,total,account,accountname,reason,ip,createtime,type) values(?,?,?,?,?,?,now(),0)";
		Object[] params = {ckid,total,account,accountname,reason,ip};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public CrmWxHongbaoCheck findCrmWxHongbaoCheckByLid(long lid,int type)
	{
		String sql = "select * from crm.crm_wx_hongbao_check c join crm.crm_wx_hongbao_check_log l on c.id = l.ckid where c.enid = ? and c.type = ? order by l.createtime desc";
		Object[] params = {lid,type};
		List<CrmWxHongbaoCheck> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CrmWxHongbaoCheck c = new CrmWxHongbaoCheck();
				c.setId(rs.getLong("c.id"));
				c.setMpid(rs.getLong("c.mpid"));
				c.setTotal(rs.getInt("l.total"));
				c.setReason(rs.getString("l.reason"));
				c.setStatus(rs.getInt("c.status"));
				return c;
			}
			
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int updateCrmWxHongbaoCheck(long ckid, int type, long lid,long mpid)
	{
		String sql = "update crm.crm_wx_hongbao_check c set c.status=0,c.updatestatus=0,type=?,enid=?,mpid=? where c.id= ?";
		Object[] params = {type,lid,mpid,ckid};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public CrmWxHongbaoCheck findWxHongbaoCheckByLid(long lid)
	{
		String sql = "select * from crm.crm_wx_hongbao_check  where enid = ? and type = 1 ";
		Object[] params = {lid};
		List<CrmWxHongbaoCheck> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CrmWxHongbaoCheck c = new CrmWxHongbaoCheck();
				c.setId(rs.getLong("id"));
				c.setMpid(rs.getLong("mpid")); 
				c.setTotal(rs.getInt("total"));
				c.setUsed(rs.getInt("used"));
				c.setStatus(rs.getInt("status"));
				return c;
			}
			
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public CrmWxHongbaoCheck findCheckByTypeAndEnid(long enid,int type)
	{
		String sql = "select * from crm.crm_wx_hongbao_check  where enid = ? and type = ? ";
		Object[] params = {enid,type};
		List<CrmWxHongbaoCheck> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CrmWxHongbaoCheck c = new CrmWxHongbaoCheck();
				c.setId(rs.getLong("id"));
				c.setMpid(rs.getLong("mpid")); 
				c.setTotal(rs.getInt("total"));
				c.setUsed(rs.getInt("used"));
				c.setStatus(rs.getInt("status"));
				return c;
			}
			
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public void saveWxHongbaoLotterySend(long lid,long lpid, long lurid, String openid, long mpid)
	{
		String sql = "insert into crm.crm_wx_hongbao_lottery_send (lid,lpid,lurid,openid,mpid,createtime) values(?,?,?,?,?,now())";
		Object[] params = {lid,lpid,lurid,openid,mpid};
		jdbcTemplate.update(sql, params);
	}

	@Override
	public List<WxHbCheckLog> findWxHbCheckLogListBylid(long lid, int start, int rows)
	{
		String sql = "SELECT l.* FROM crm.crm_wx_hongbao_check_log l LEFT JOIN crm.crm_wx_hongbao_check c on c.id=l.ckid WHERE c.type=1 AND c.enid=? ORDER BY l.createtime LIMIT ?,?";
		Object[] params = { lid, start, rows };
		return jdbcTemplate.query(sql, params, hbCheckLogRowMapper);
	}

	private RowMapper hbCheckLogRowMapper = new RowMapper() {

		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			WxHbCheckLog log = new WxHbCheckLog();
			log.setId(rs.getLong("id"));
			log.setCkid(rs.getLong("ckid"));
			log.setTotal(rs.getInt("total"));
			log.setAccount(rs.getLong("account"));
			log.setAccountname(rs.getString("accountname"));
			log.setReason(rs.getString("reason"));
			log.setIp(rs.getString("ip"));
			log.setCreatetime(rs.getTimestamp("createtime"));
			log.setType(rs.getInt("type"));
			return log;
		}

	};

	@Override
	public void saveSysKeywords(long lid,long mpid,String openid, String keywords, int hbgz, long lpid, long rid)
	{
		jdbcTemplate.update("insert ignore into crm.crm_system_keywords (lid,mpid,openid,keywords,hbgz,enid,recordid,type) values(?,?,?,?,?,?,?,1)", new Object[]{lid,mpid,openid,keywords,hbgz,lpid,rid});		
	}
	
	@Override
	public void updateCbActivityUsed(long enid, int type, int used)
	{
		jdbcTemplate.update("update crm.crm_wx_hongbao_check set used=used+? where enid=? and type=?", new Object[]{used,enid,type});
	}
}
