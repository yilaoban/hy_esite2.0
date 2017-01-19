package com.huiyee.interact.xc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.google.gson.Gson;
import com.huiyee.interact.xc.model.CheckinConfig;
import com.huiyee.interact.xc.model.CommentConfig;
import com.huiyee.interact.xc.model.InviteConfig;
import com.huiyee.interact.xc.model.LotteryConfig;
import com.huiyee.interact.xc.model.Xc;
import com.huiyee.interact.xc.model.XcRecord;

public class XcDao implements IXcDao {

	private JdbcTemplate jdbcTemplate;
	Gson gson = new Gson();

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Xc getXcById(long xcid) {
		Object[] param = { xcid };
		List<Xc> list = jdbcTemplate.query("SELECT * FROM esite.es_interact_xc WHERE id=?", param, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				Xc xc = new Xc();
				xc.setId(rs.getLong("id"));
				xc.setTitle(rs.getString("title"));
				xc.setOwner(rs.getLong("owner"));
				xc.setCreatetime(rs.getTimestamp("createtime"));
				xc.setCheckinconfig(gson.fromJson(rs.getString("checkinconfig"), CheckinConfig.class));
				xc.setCommentconfig(gson.fromJson(rs.getString("commentconfig"), CommentConfig.class));
				xc.setInviteconfig(gson.fromJson(rs.getString("inviteconfig"), InviteConfig.class));
				xc.setLotteryconfig(gson.fromJson(rs.getString("lotteryconfig"), LotteryConfig.class));
				xc.setNeedcheckin(rs.getString("needcheckin"));
				xc.setNeedcomment(rs.getString("needcomment"));
				xc.setNeedinvite(rs.getString("needinvite"));
				xc.setNeedlottery(rs.getString("needlottery"));
				return xc;
			}
		});

		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void saveRecords(final List<XcRecord> records) {
		jdbcTemplate.batchUpdate(
				"INSERT IGNORE INTO esite.es_interact_xc_cj_record(xcid,startnum,uid,utype,joinnum,top,createtime,ip,terminal,source,pageid) VALUES(?,?,?,?,?,?,?,?,?,?,?) ",
				new BatchPreparedStatementSetter() {

					@Override
					public int getBatchSize() {
						return records.size();
					}

					@Override
					public void setValues(PreparedStatement ps, int index) throws SQLException {
						XcRecord xcr = records.get(index);
						ps.setLong(1, xcr.getXcid());
						ps.setInt(2, xcr.getStartnum());
						ps.setLong(3, xcr.getUid());
						ps.setInt(4, xcr.getUtype());
						ps.setInt(5, xcr.getJoinnum());
						ps.setString(6, xcr.getTop());
						ps.setTimestamp(7, new Timestamp(xcr.getCreatetime().getTime()));
						ps.setString(8, xcr.getIp());
						ps.setString(9, xcr.getTerminal());
						ps.setString(10, xcr.getSource());
						ps.setLong(11, xcr.getPageid());
					}

				});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> getUids(long xcid, String atype) {

		Object[] param = { xcid };
		return jdbcTemplate.query("SELECT DISTINCT uid FROM esite.es_interact_xc_checkin_record WHERE xcid=? and status=1", param, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getLong("uid");
			}
		});
	}

	@Override
	public int getYCount(long xcid, long uid, int utype) {
		Object[] params = { xcid, uid, utype };
		return jdbcTemplate.queryForInt("SELECT COUNT(1) FROM esite.es_interact_xc_cj_record WHERE xcid=? AND uid=? AND utype=? AND top='Y'", params);
	}

	@Override
	public void updateLConfig(long xcid) {
		Xc xc = getXcById(xcid);
		if (xc != null) {
			LotteryConfig lc = xc.getLotteryconfig();
			if (lc != null) {
				lc.setStarted(2);
				Object[] params = { gson.toJson(lc), xcid };
				jdbcTemplate.update("UPDATE esite.es_interact_xc SET lotteryconfig=? WHERE id=?", params);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<XcRecord> getLastResult(long xcid, int startnum) {
		Object[] params = { xcid, startnum };
		return jdbcTemplate.query("SELECT * FROM esite.es_interact_xc_cj_record WHERE xcid=? AND startnum=? AND top='Y'", params, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				XcRecord xcr = new XcRecord();
				xcr.setUid(rs.getLong("uid"));
				xcr.setUtype(rs.getInt("utype"));
				xcr.setJoinnum(rs.getInt("joinnum"));
				return xcr;
			}
		});
	}
	
	
	@Override
	public int updateAcceptInvite(long uid, int type, long xcid)
	{
		return jdbcTemplate.update("update esite.es_interact_xc_invite_record set status='Y' where xcid=? and uid=? and utype=?", new Object[]{xcid,uid,type});
	}
	
	@Override
	public void updateSdAcceptInvite(long uid, int type, long xcid)
	{
		jdbcTemplate.update("update esite.es_interact_xc_sd_record set status='Y' where xcid=? and uid=? and utype=?", new Object[]{xcid,uid,type});
	}
	
	@Override
	public int findCheckedNum(long xcid)
	{
		return jdbcTemplate.queryForInt("select count(*) from es_interact_xc_checkin_record where xcid=? and status=1", new Object[]{xcid});
	}
	
	@Override
	public int findInviteNum(long xcid)
	{
		return jdbcTemplate.queryForInt("select count(*) from es_interact_xc_invite_record where xcid=? and cg='Y'", new Object[]{xcid});
	}

	
	@Override
	public int updateInviteConfig(long xcid, String needInvite, InviteConfig inviteConfig)
	{
		return jdbcTemplate.update("update es_interact_xc set inviteconfig=?,needinvite=? where id=?", new Object[]{new Gson().toJson(inviteConfig),needInvite,xcid});
	}
}
