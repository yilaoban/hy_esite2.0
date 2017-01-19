package com.huiyee.interact.cb.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.model.CbActivity;
import com.huiyee.interact.cb.dao.ICbActivityDao;
import com.huiyee.interact.cb.model.InteractCb;
import com.huiyee.interact.cb.model.WxHbCheck;

@SuppressWarnings("unchecked")
public class CbActivityDaoImpl implements ICbActivityDao {

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private RowMapper rowMapper = new RowMapper() {

		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			CbActivity ca = new CbActivity();
			ca.setId(rs.getLong("id"));
			ca.setTitle(rs.getString("title"));
			ca.setImg(rs.getString("img"));
			ca.setContent(rs.getString("content"));
			ca.setZhuanfa(rs.getInt("zhuanfa"));
			ca.setZhuanfajf(rs.getInt("zhuanfajf"));
			ca.setClick(rs.getInt("click"));
			ca.setClickjf(rs.getInt("clickjf"));
			ca.setGuanzhu(rs.getInt("guanzhu"));
			ca.setGuanzhujf(rs.getInt("guanzhujf"));
			ca.setGuanzhudays(rs.getInt("guanzhudays"));
			ca.setWaibu(rs.getInt("waibu"));
			ca.setWaibujf(rs.getInt("waibujf"));
			ca.setHudong(rs.getInt("hudong"));
			ca.setHudongjf(rs.getInt("hudongjf"));
			ca.setStarttime(rs.getTimestamp("starttime"));
			ca.setEndtime(rs.getTimestamp("endtime"));
			ca.setStatus(rs.getInt("status"));
			ca.setType(rs.getInt("type"));
			ca.setEnid(rs.getLong("enid"));
			return ca;
		}
	};

	@Override
	public List<CbActivity> findActivityList(long owner, int start, int rows) {
		String sql = "SELECT * FROM esite.es_interact_cb_activity WHERE owner=? AND del_tag!='Y' AND status=1 ORDER BY createtime DESC LIMIT ?,?";
		Object[] args = { owner, start, rows };
		return jdbcTemplate.query(sql, args, rowMapper);
	}

	@Override
	public CbActivity findActivity(long id) {
		String sql = "SELECT * FROM esite.es_interact_cb_activity WHERE id=?";
		Object[] args = { id };
		List<CbActivity> list = jdbcTemplate.query(sql, args, rowMapper);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public InteractCb findCb(long owner) {
		String sql = "SELECT * FROM es_interact_cb WHERE owner=?";
		Object[] params = { owner };
		List<InteractCb> list = jdbcTemplate.query(sql, params, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				InteractCb cb = new InteractCb();
				cb.setId(rs.getLong("id"));
				cb.setTitle(rs.getString("title"));
				cb.setOwner(rs.getLong("owner"));
				cb.setAptid(rs.getLong("aptid"));
				cb.setSiteid(rs.getLong("siteid"));
				cb.setSitegroupid(rs.getLong("sitegroupid"));
				cb.setCreatetime(rs.getTimestamp("createtime"));
				cb.setWishing(rs.getString("wishing"));
				cb.setAct_name(rs.getString("act_name"));
				cb.setRemark(rs.getString("remark"));
				cb.setSpageid(rs.getLong("spageid"));
				cb.setNpageid(rs.getLong("npageid"));
				cb.setRpageid(rs.getLong("rpageid"));
				cb.setYpageid(rs.getLong("ypageid"));
				cb.setPizhun(rs.getInt("pizhun"));
				return cb;
			}

		});
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public WxHbCheck findCbHbCheck(long mpid, long aid) {
		String sql = "SELECT * FROM crm.crm_wx_hongbao_check WHERE mpid=? AND type=2 AND enid=?";
		Object[] args = { mpid, aid };
		List<WxHbCheck> list = jdbcTemplate.query(sql, args, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				WxHbCheck c = new WxHbCheck();
				c.setId(rs.getLong("id"));
				c.setMpid(rs.getLong("mpid"));
				c.setTotal(rs.getInt("total"));
				c.setUsed(rs.getInt("used"));
				c.setStatus(rs.getInt("status"));
				c.setUpdatestatus(rs.getInt("updatestatus"));
				c.setType(rs.getInt("type"));
				c.setEnid(rs.getLong("enid"));
				return c;
			}

		});
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
