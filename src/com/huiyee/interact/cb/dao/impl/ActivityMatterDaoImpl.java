package com.huiyee.interact.cb.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.model.CbActivity;
import com.huiyee.esite.util.DateUtil;
import com.huiyee.interact.cb.dao.IActivityMatterDao;
import com.huiyee.weixin.model.WxPageShow;


public class ActivityMatterDaoImpl implements IActivityMatterDao
{
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int findTotalActivityMatterList(long cbid,long aid)
	{
		String sql = "select count(m.id) from es_interact_cb_activity_matter m join es_page p on m.pageid = p.id  where m.cbid = ? and m.aid = ? and p.status != 'DEL' and m.del_tag != 'Y'";
		Object[] params = {cbid,aid};
		return jdbcTemplate.queryForInt(sql, params);
	}

	@Override
	public List<WxPageShow> findActivityMatterList(long cbid,long aid, int start, int size)
	{
		String sql = "select * from es_interact_cb_activity_matter m join es_page p on m.pageid = p.id where m.cbid = ? and m.aid = ? and m.del_tag != 'Y' and p.status != 'DEL' limit ?,?";
		Object[] params = {cbid,aid,start,size};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				WxPageShow s = new WxPageShow();
				/*s.setId(rs.getLong("s.id"));
				s.setTitle(rs.getString("s.title"));
				s.setDescription(rs.getString("s.description"));
				s.setPic(rs.getString("s.pic"));
				s.setPageid(rs.getLong("s.pageid"));*/
				s.setKv(rs.getString("m.kv"));
				s.setWxshareid(rs.getLong("m.wxshareid"));
				s.setPageid(rs.getLong("m.pageid"));
				s.setId(rs.getLong("m.id"));
				return s;
			}
			
		});
	}

	@Override
	public WxPageShow findWxPageShowById(long id,long aid)
	{
		String sql = "select * from es_wx_page_show s join es_interact_cb_activity_matter m on m.pageid = s.pageid where s.id = ? and m.aid = ? and m.del_tag != 'Y'";
		Object[] params = {id,aid};
		List<WxPageShow> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				WxPageShow s = new WxPageShow();
				s.setId(rs.getLong("s.id"));
				s.setTitle(rs.getString("s.title"));
				s.setDescription(rs.getString("s.description"));
				s.setPic(rs.getString("s.pic"));
				s.setPageid(rs.getLong("s.pageid"));
				s.setKv(rs.getString("m.kv"));
				return s;
			}
			
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<CbActivity> findCbActivityList(long owner)
	{
		String sql = "select * from es_interact_cb_activity where owner = ? and del_tag != 'Y' and status = 1";
		Object[] params = {owner};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CbActivity a = new CbActivity();
				a.setId(rs.getLong("id"));
				a.setTitle(rs.getString("title"));
				a.setStarttime(rs.getTimestamp("starttime"));
				a.setEndtime(rs.getTimestamp("endtime"));
				a.setContent(rs.getString("content"));
				a.setZhuanfa(rs.getInt("zhuanfa"));
				a.setClick(rs.getInt("click"));
				a.setGuanzhu(rs.getInt("guanzhu"));
				a.setGuanzhudays(rs.getInt("guanzhudays"));
				a.setWaibu(rs.getInt("waibu"));
				a.setHudong(rs.getInt("hudong"));
				return a;
			}
			
		});
	}
	
	@Override
	public List<Long> findCbAptRecord(long cbid, long wxuid)
	{
		return jdbcTemplate.query("select r.id from es_interact_cb c join es_feature_interact_apt_record r on c.aptid=r.aptid where c.id=? and entityid=? and type=1", new Object[]{cbid,wxuid}, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				return rs.getLong("r.id");
			}
		});
	}

	@Override
	public WxPageShow findActivityMatterDetailById(long id, long aid)
	{
		String sql = "select * from es_interact_cb_activity_matter m where m.id = ? and m.aid = ? and del_tag != 'Y'";
		Object[] params = {id,aid};
		List<WxPageShow> list =jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				WxPageShow s = new WxPageShow();
				s.setKv(rs.getString("m.kv"));
				s.setWxshareid(rs.getLong("m.wxshareid"));
				s.setPageid(rs.getLong("m.pageid"));
				s.setId(rs.getLong("m.id"));
				return s;
			}
			
		});
		if(list !=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
}
