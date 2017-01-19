package com.huiyee.interact.xc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.model.SinaUser;
import com.huiyee.esite.model.WxUser;
import com.huiyee.interact.xc.model.CheckinRecord;
import com.huiyee.interact.xc.model.XcCheckinRecord;
import com.huiyee.interact.xc.model.XcSendRecord;

public class SigninDao implements ISigninDao
{

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int findUser(long xcid,long uid,int utype)
	{
		String sql="select count(id) from es_interact_xc_checkin_record where xcid=? and uid =? and utype =? and status=1";
		return jdbcTemplate.queryForInt(sql, new Object[]{ xcid,uid,utype});
	}
	
	@Override
	public long findCheckinRecordId(long xcid,long uid,int utype)
	{
		String sql="select id from es_interact_xc_checkin_record where xcid=? and uid =? and utype =?";
		List<Long> list=jdbcTemplate.query(sql, new Object[]{ xcid,uid,utype},new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				return rs.getLong("id");
			}
		});
		return list.size()>0?list.get(0):0;
	}
	class CheckinRecordRowMapper implements RowMapper
	{
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			CheckinRecord cr = new CheckinRecord();
			cr.setId(rs.getLong("id"));
			cr.setPageid(rs.getLong("pageid"));
			cr.setXcid(rs.getLong("xcid"));
			cr.setUid(rs.getLong("uid"));
			cr.setUtype(rs.getInt("utype"));
			cr.setIp(rs.getString("ip"));
			cr.setTerminal(rs.getString("terminal"));
			cr.setSource(rs.getString("source"));
			return cr;
		}
	}
	
	@Override
	public int saveUser(long uid,int type,long pageid,String source,long xcid,String ip,String terminal,int status)
	{
		String sql = "insert into es_interact_xc_checkin_record (uid,pageid,utype,source,xcid,ip,terminal,createtime,status) values(?,?,?,?,?,?,?,now(),?) on duplicate key update status=?";
		return jdbcTemplate.update(sql, new Object[]{ uid,pageid,type,source,xcid,ip,terminal,status,status});
	}

	private static final String FIND_XC_CHECKIN_RECORD="select * from (select * from es_interact_xc_checkin_record where xcid = ? and status=1 order by id desc limit ?) t order by t.id asc";
	private static final String FIND_XC_CHECKIN_RECORD1="select * from es_interact_xc_checkin_record where xcid = ? and id > ? and status=1 order by id asc limit ?";
	@Override
	public List<XcCheckinRecord> findXcCheckinRecord(long xcid,long start, int size) {
		if(start==0){
			Object[] params={xcid,size};
			return jdbcTemplate.query(FIND_XC_CHECKIN_RECORD, params, new RowMapper(){
				@Override
				public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
					XcCheckinRecord cr = new XcCheckinRecord();
					cr.setId(rs.getLong("id"));
					cr.setPageid(rs.getLong("pageid"));
					cr.setXcid(rs.getLong("xcid"));
					cr.setUid(rs.getLong("uid"));
					cr.setUtype(rs.getInt("utype"));
					cr.setIp(rs.getString("ip"));
					cr.setTerminal(rs.getString("terminal"));
					cr.setSource(rs.getString("source"));
					return cr;
				}
			});
		}
		if(start>0){
			Object[] params={xcid,start,size};
			return jdbcTemplate.query(FIND_XC_CHECKIN_RECORD1, params, new RowMapper(){
				@Override
				public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
					XcCheckinRecord cr = new XcCheckinRecord();
					cr.setId(rs.getLong("id"));
					cr.setPageid(rs.getLong("pageid"));
					cr.setXcid(rs.getLong("xcid"));
					cr.setUid(rs.getLong("uid"));
					cr.setUtype(rs.getInt("utype"));
					cr.setIp(rs.getString("ip"));
					cr.setTerminal(rs.getString("terminal"));
					cr.setSource(rs.getString("source"));
					return cr;
				}
			});
		}
		return null;
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
	public int findisInvite(long xcid, long uid, int type)
	{
		String sql="select count(id) from es_interact_xc_invite_record where xcid=? and uid =? and utype =? and cg='Y'";
		return jdbcTemplate.queryForInt(sql, new Object[]{ xcid,uid,type});
	}

	@Override
	public int updateInvite(long uid, int type, long xcid)
	{
		String sql = "update es_interact_xc_invite_record set checked='Y' where xcid=? and uid=? and utype=?";
		return jdbcTemplate.update(sql, new Object[]{ xcid,uid,type});
	}
	
	@Override
	public int updateSd(long uid, int type, long xcid)
	{
		String sql = "update es_interact_xc_sd_record set checked='Y' where xcid=? and uid=? and utype=?";
		return jdbcTemplate.update(sql, new Object[]{ xcid,uid,type});
	}

	@Override
	public int findSignerTotal(long xcid)
	{
		String sql="select count(*) from es_interact_xc_checkin_record where xcid=? and status=1";
		return jdbcTemplate.queryForInt(sql,new Object[]{xcid});
	}

	@Override
	public XcSendRecord findsdRecord(long uid, int utype, long xcid)
	{
		String sql="select * from es_interact_xc_sd_record where uid=? and utype=? and xcid=?";
		List<XcSendRecord> list = jdbcTemplate.query(sql,new Object[]{uid,utype,xcid},new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				XcSendRecord u = new XcSendRecord();
				u.setUsername(rs.getString("username"));
				return u;
			}
			
		});
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
