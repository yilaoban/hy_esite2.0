package com.huiyee.interact.xc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.interact.xc.dto.XcSiftDto;
import com.huiyee.interact.xc.model.XcSendRecord;

public class XcSendRecordDaoImpl implements IXcSendRecordDao
{
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int findTotal(long id, String username, XcSiftDto siftDto)
	{
		List<Object> params = new ArrayList<Object>();
		String sql = "select count(*) from es_interact_xc_sd_record where xcid=?";
		params.add(id);
		if (username != null && username.trim().length() > 0)
		{
			sql += " and username like ?";
			params.add("%" + username + "%");
		}
		if (!"A".equals(siftDto.getIsInvited()))
		{
			sql += " and invited=? ";
			params.add(siftDto.getIsInvited());
		}
		if (!"A".equals(siftDto.getIsChecked()))
		{
			sql += " and checked=? ";
			params.add(siftDto.getIsChecked());
		}
		return jdbcTemplate.queryForInt(sql, params.toArray());
	}

	@Override
	public List<XcSendRecord> findList(long id, String username, XcSiftDto siftDto, int start, int size)
	{
		List<Object> params = new ArrayList<Object>();
		String sql = "select * from es_interact_xc_sd_record where xcid=?";
		params.add(id);
		if (username != null && username.trim().length() > 0)
		{
			sql += " and username like ?";
			params.add("%" + username + "%");
		}
		if (!"A".equals(siftDto.getIsInvited()))
		{
			sql += " and invited=? ";
			params.add(siftDto.getIsInvited());
		}
		if (!"A".equals(siftDto.getIsChecked()))
		{
			sql += " and checked=? ";
			params.add(siftDto.getIsChecked());
		}
		sql += " order by id desc limit ?,?";
		params.add(start);
		params.add(size);
		return jdbcTemplate.query(sql, params.toArray(), new myRowMapper());
	}

	class myRowMapper implements RowMapper
	{
		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException
		{
			XcSendRecord record = new XcSendRecord();
			record.setId(rs.getLong("id"));
			record.setXcid(rs.getLong("xcid"));
			record.setUid(rs.getLong("uid"));
			record.setUtype(rs.getInt("utype"));
			record.setUsername(rs.getString("username"));
			record.setNickname(rs.getString("nickname"));
			record.setIndex(rs.getInt("idx"));
			record.setCreatetime(rs.getTimestamp("createtime"));
			record.setIp(rs.getString("ip"));
			record.setTerminal(rs.getString("terminal"));
			record.setSource(rs.getString("source"));
			record.setPageid(rs.getLong("pageid"));
			record.setInvited(rs.getString("invited"));
			record.setChecked(rs.getString("checked"));
			return record;
		}
	}

	@Override
	public int findExist(long id, String username, String nickname)
	{
		return jdbcTemplate.queryForInt("select count(*) from es_interact_xc_sd_record where xcid=? and username=? and nickname=?", new Object[]
		{ id, username, nickname });
	}

	private static final String ADD_SEND_RECORD = "insert into es_interact_xc_sd_record (xcid,username,nickname,idx,createtime,ip,terminal) values(?,?,?,?,now(),?,?)";

	@Override
	public int addSendRecordSub(final List<XcSendRecord> needInsert, final long id, final String ip, final String terminal)
	{
		int[] arr = jdbcTemplate.batchUpdate(ADD_SEND_RECORD, new BatchPreparedStatementSetter()
		{
			@Override
			public int getBatchSize()
			{
				return needInsert.size();
			}

			@Override
			public void setValues(PreparedStatement ps, int index) throws SQLException
			{
				XcSendRecord xr = needInsert.get(index);
				int i = 1;
				ps.setLong(i++, id);
				ps.setString(i++, xr.getUsername());
				ps.setString(i++, xr.getNickname());
				ps.setInt(i++, xr.getIndex());
				ps.setString(i++, ip);
				ps.setString(i++, terminal);

			}
		});
		return arr.length;
	}

	@Override
	public XcSendRecord findRecordByNickname(String nickname, long xcid)
	{
		List<XcSendRecord> list = jdbcTemplate.query("select * from es_interact_xc_sd_record where xcid=? and nickname=?", new Object[]
		{ xcid, nickname }, new myRowMapper());
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public void updateSendRecordInvited(long id, long uid, int type, String source, int hypage)
	{
		jdbcTemplate.update("update es_interact_xc_sd_record set uid=?,utype=?,source=?,pageid=?,invited='Y' where id=?", new Object[]
		{ uid, type, source, hypage, id });
	}

	@Override
	public void saveInviteRecord(long xcid, long uid, int type, String source, int hypage, String terminal, String ip,String cg)
	{
		jdbcTemplate.update("insert  into es_interact_xc_invite_record (pageid,xcid,uid,utype,createtime,ip,terminal,source,cg) values (?,?,?,?,now(),?,?,?,?) on duplicate key update cg=?", new Object[]
		{ hypage, xcid, uid, type, ip, terminal, source,cg,cg });
	}

	@Override
	public XcSendRecord findRecordById(long recordId, long owner)
	{
		List<XcSendRecord> list = jdbcTemplate.query("select record.* from es_interact_xc_sd_record record join es_interact_xc xc on xc.id=record.xcid where record.id=? and xc.owner=?", new Object[]
		{ recordId, owner }, new myRowMapper());
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public int updateNickname(long recordId, String nickname)
	{
		return jdbcTemplate.update("update es_interact_xc_sd_record set nickname=? where id=?", new Object[]
		{ nickname, recordId });
	}

	@Override
	public int deleteSdRecord(long recordId)
	{
		return jdbcTemplate.update("delete from  es_interact_xc_sd_record where id=?", new Object[]
		{ recordId });
	}
	
	@Override
	public List<String> findNames(long id, long uid, int utype)
	{
		List<List<String>> list=jdbcTemplate.query("select * from es_interact_xc_sd_record where xcid=? and uid=? and utype=?", new Object[]{id,uid,utype}, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				List<String> list=new ArrayList<String>();
				list.add(rs.getString("username"));
				list.add(rs.getString("nickname"));
				return list;
			}
		});
		return list.size()>0?list.get(0):null;
	}

	@Override
	public XcSendRecord getSdRecord(long xcid, long uid, int utype)
	{
		Object[] param = { xcid, uid, utype };
		List<XcSendRecord> list = jdbcTemplate.query("SELECT * FROM esite.es_interact_xc_sd_record WHERE xcid=? AND uid=? AND utype=?", param, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				XcSendRecord xsr = new XcSendRecord();
				xsr.setUsername(rs.getString("username"));
				return xsr;
			}
		});

		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public void updateXcClean(long xcid)
	{
		String[] sql={
			"update es_interact_xc_sd_record set invited='N',checked='N',status='N' where xcid="+xcid,	
			"delete from es_interact_xc_invite_record where xcid="+xcid,
			"delete from es_interact_xc_checkin_record where xcid="+xcid,
			"delete from es_interact_xc_cj_record where xcid="+xcid,
			"delete from es_interact_xc_comment_record where xcid="+xcid,
		};
		int[] rs=jdbcTemplate.batchUpdate(sql);
	}

	@Override
	public void updateXcCleanWithOutInvite(long xcid)
	{
		String[] sql={
				"update es_interact_xc_sd_record set checked='N' where xcid="+xcid,	
				"delete from es_interact_xc_checkin_record where xcid="+xcid,
				"delete from es_interact_xc_cj_record where xcid="+xcid,
				"delete from es_interact_xc_comment_record where xcid="+xcid,
			};
			int[] rs=jdbcTemplate.batchUpdate(sql);
	}
		 
	
}
