package com.huiyee.interact.xc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.google.gson.Gson;
import com.huiyee.esite.service.IMemCacheAble;
import com.huiyee.interact.xc.model.CheckinConfig;
import com.huiyee.interact.xc.model.CommentConfig;
import com.huiyee.interact.xc.model.InviteConfig;
import com.huiyee.interact.xc.model.LotteryConfig;
import com.huiyee.interact.xc.model.Xc;

public class XcMcDao implements IXcMcDao,IMemCacheAble
{
	private int cacheTime;
	private JdbcTemplate jdbcTemplate;
	Gson gson = new Gson();

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Xc getXcByIdMc(long xcid)
	{
		Object[] param =
		{ xcid };
		List<Xc> list = jdbcTemplate.query("SELECT * FROM esite.es_interact_xc WHERE id=?", param, new RowMapper()
		{
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
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

		if (list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}

	public int getCacheTime() {
		return cacheTime;
	}

	public void setCacheTime(int cacheTime) {
		this.cacheTime = cacheTime;
	}
}
