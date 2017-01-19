package com.huiyee.interact.lottery.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.interact.lottery.model.WxHbLotterySend;


public class WxHbSendDao implements IWxHbSendDao
{
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int findWxHbSendTotal(long lpid, Integer errcode)
	{
		Object[] params = { lpid };
		String sql = "SELECT count(*) FROM crm.crm_wx_hongbao_lottery_send WHERE lpid=?";
		if (errcode != null) {
			sql = sql + " AND errcode=? ";
			params = new Object[] { lpid, errcode };
		}
		return jdbcTemplate.queryForInt(sql, params);
	}

	@Override
	public List<WxHbLotterySend> findWxHbSendList(long lpid, Integer errcode, int start, int rows)
	{
		Object[] params = { lpid, start, rows };
		String sql = "SELECT s.*,u.nickname FROM crm.crm_wx_hongbao_lottery_send s LEFT JOIN esite.es_wx_user u ON u.openid=s.openid WHERE s.lpid=? ";
		if (errcode != null) {
			sql = sql + "AND s.errcode=? ";
			params = new Object[] { lpid, errcode, start, rows };
		}
		sql = sql + "ORDER BY s.senttime Limit ?,?";
		return jdbcTemplate.query(sql, params, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				WxHbLotterySend send = new WxHbLotterySend();
				send.setId(rs.getLong("s.id"));
				send.setLid(rs.getLong("s.lid"));
				send.setLpid(rs.getLong("s.lpid"));
				send.setLurid(rs.getLong("s.lurid"));
				send.setOpenid(rs.getString("s.openid"));
				send.setMpid(rs.getLong("s.mpid"));
				send.setSent(rs.getString("s.sent"));
				send.setCreatetime(rs.getTimestamp("s.createtime"));
				send.setSenttime(rs.getTimestamp("s.senttime"));
				send.setErrcode(rs.getInt("s.errcode"));
				send.setErrmsg(rs.getString("s.errmsg"));
				send.setNickname(rs.getString("u.nickname"));
				return send;
			}

		});
	}

	@Override
	public int addToResend(long rid)
	{
		String sql = "INSERT IGNORE INTO crm.crm_wx_hongbao_resend(type,rid,createtime) VALUES(1,?,now())";
		Object[] params = { rid };
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int updateSent(long id, String sent)
	{
		String sql = "UPDATE crm.crm_wx_hongbao_lottery_send SET sent=? WHERE id=?";
		Object[] params = { sent, id };
		return jdbcTemplate.update(sql, params);
	}
	
}
