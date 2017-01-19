package com.huiyee.esite.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.IMarketActivityDao;
import com.huiyee.esite.model.CbActivity;

@SuppressWarnings("unchecked")
public class MarketActivityDaoImpl implements IMarketActivityDao {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int findTotalCbActivityByCbid(long owner) {
		String sql = "select count(id) from esite.es_interact_cb_activity where owner = ? and del_tag != 'Y'";
		Object[] params = { owner };
		return jdbcTemplate.queryForInt(sql, params);
	}

	@Override
	public List<CbActivity> findCbActivityListByCbid(long owner, int start, int size) {
		String sql = "select * from esite.es_interact_cb_activity where owner = ? and del_tag != 'Y' order by createtime desc limit ?,?";
		Object[] params = { owner, start, size };
		return jdbcTemplate.query(sql, params, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				CbActivity ca = new CbActivity();
				ca.setId(rs.getLong("id"));
				ca.setOwner(rs.getLong("owner"));
				ca.setTitle(rs.getString("title"));
				ca.setImg(rs.getString("img"));
				ca.setContent(rs.getString("content"));
				ca.setUpdatetime(rs.getTimestamp("updatetime"));
				ca.setCreatetime(rs.getTimestamp("createtime"));
				ca.setStarttime(rs.getTimestamp("starttime"));
				ca.setEndtime(rs.getTimestamp("endtime"));
				ca.setStatus(rs.getInt("status"));
				ca.setType(rs.getInt("type"));
				ca.setEnid(rs.getLong("enid"));
				return ca;
			}

		});
	}

	@Override
	public int saveMarketActivity(CbActivity cbActivity) {
		String sql = "insert into esite.es_interact_cb_activity(owner,title,img,content,updatetime,createtime,zhuanfa,click,guanzhu,waibu,hudong,guanzhudays,starttime,endtime,zhuanfajf,clickjf,guanzhujf,waibujf,hudongjf,type,enid) values(?,?,?,?,now(),now(),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = { cbActivity.getOwner(), cbActivity.getTitle(), cbActivity.getImg(), cbActivity.getContent(), cbActivity.getZhuanfa(), cbActivity.getClick(), cbActivity.getGuanzhu(),
				cbActivity.getWaibu(), cbActivity.getHudong(), cbActivity.getGuanzhudays(), cbActivity.getStarttime(), cbActivity.getEndtime(), cbActivity.getZhuanfajf(), cbActivity.getClickjf(),
				cbActivity.getGuanzhujf(), cbActivity.getWaibujf(), cbActivity.getHudongjf(), cbActivity.getType(), cbActivity.getEnid() };
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int delMarketActivity(long aid) {
		String sql = "update esite.es_interact_cb_activity set del_tag = 'Y' where id = ?";
		Object[] params = { aid };
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public CbActivity findCbActivityByAid(long aid) {
		String sql = "select * from esite.es_interact_cb_activity where id = ?";
		Object[] params = { aid };
		List<CbActivity> list = jdbcTemplate.query(sql, params, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				CbActivity ca = new CbActivity();
				ca.setId(rs.getLong("id"));
				ca.setOwner(rs.getLong("owner"));
				ca.setTitle(rs.getString("title"));
				ca.setImg(rs.getString("img"));
				ca.setContent(rs.getString("content"));
				ca.setUpdatetime(rs.getTimestamp("updatetime"));
				ca.setCreatetime(rs.getTimestamp("createtime"));
				ca.setZhuanfa(rs.getInt("zhuanfa"));
				ca.setClick(rs.getInt("click"));
				ca.setGuanzhu(rs.getInt("guanzhu"));
				ca.setGuanzhudays(rs.getInt("guanzhudays"));
				ca.setWaibu(rs.getInt("waibu"));
				ca.setHudong(rs.getInt("hudong"));
				ca.setStarttime(rs.getTimestamp("starttime"));
				ca.setEndtime(rs.getTimestamp("endtime"));
				ca.setZhuanfajf(rs.getInt("zhuanfajf"));
				ca.setClickjf(rs.getInt("clickjf"));
				ca.setGuanzhujf(rs.getInt("guanzhujf"));
				ca.setWaibujf(rs.getInt("waibujf"));
				ca.setHudongjf(rs.getInt("hudongjf"));
				ca.setType(rs.getInt("type"));
				ca.setEnid(rs.getLong("enid"));
				return ca;
			}

		});
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public int updateMarketActivity(CbActivity cbActivity) {
		String sql = "update esite.es_interact_cb_activity set title = ?,img = ?,content = ?,updatetime = now(),zhuanfa=?,click=?,guanzhu=?,waibu=?,hudong=?,starttime=?,endtime=?,guanzhudays=?,zhuanfajf=?,clickjf=?,guanzhujf=?,waibujf=?,hudongjf=?,enid=? where id = ?";
		Object[] params = { cbActivity.getTitle(), cbActivity.getImg(), cbActivity.getContent(), cbActivity.getZhuanfa(), cbActivity.getClick(), cbActivity.getGuanzhu(), cbActivity.getWaibu(),
				cbActivity.getHudong(), cbActivity.getStarttime(), cbActivity.getEndtime(), cbActivity.getGuanzhudays(), cbActivity.getZhuanfajf(), cbActivity.getClickjf(), cbActivity.getGuanzhujf(),
				cbActivity.getWaibujf(), cbActivity.getHudongjf(), cbActivity.getEnid(), cbActivity.getId() };
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int updateMarketActivityStatus(long aid, int status) {
		String sql = "update esite.es_interact_cb_activity set status = ? where id = ?";
		Object[] params = { status, aid };
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public String findM_title(long owner, int type, long enid) {
		if (type == 0) {
			String sql = "select title from esite.es_wx_page_show where ownerid=? and pageid=?";
			List<String> list = jdbcTemplate.queryForList(sql, new Object[] { owner, enid }, String.class);
			if (list.size() > 0) {
				return list.get(0);
			}
		} else if (type == 1) {
			String sql = "select title from esite.es_content_news where id=?";
			List<String> list = jdbcTemplate.queryForList(sql, new Object[] { enid }, String.class);
			if (list.size() > 0) {
				return list.get(0);
			}
		}
		return null;
	}

	@Override
	public long findNpageid(long owner) {
		String sql = "select npageid from esite.es_interact_cb where id=?";
		return jdbcTemplate.queryForLong(sql, new Object[] { owner });
	}

}
