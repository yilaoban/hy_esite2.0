
package com.huiyee.interact.cb.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.interact.cb.dao.IInteractCbDao;
import com.huiyee.interact.cb.model.HbConfig;
import com.huiyee.interact.cb.model.InteractCb;

public class InteractCbDaoImpl extends AbstractDao implements IInteractCbDao
{

	@Override
	public InteractCb findCbById(long id)
	{
		List<InteractCb> list = getJdbcTemplate().query("select * from es_interact_cb where id=? ", new Object[]
		{ id}, new CbRowmapper());
		return list.size() > 0 ? list.get(0) : null;
	}

	class CbRowmapper implements RowMapper
	{

		public Object mapRow(ResultSet rs, int arg1) throws SQLException
		{
			InteractCb cb = new InteractCb();
			cb.setId(rs.getLong("id"));
			cb.setTitle(rs.getString("title"));
			cb.setOwner(rs.getLong("owner"));
			cb.setAptid(rs.getLong("aptid"));
			cb.setSiteid(rs.getLong("siteid"));
			cb.setSitegroupid(rs.getLong("sitegroupid"));
			cb.setSpageid(rs.getLong("spageid"));
			cb.setCreatetime(rs.getTimestamp("createtime"));
			cb.setSenderAccept(rs.getInt("pizhun"));
			return cb;
		}
	}
	
	private static final String SAVE_INTERACT_CB = "insert into es_interact_cb (id,title,owner,aptid,siteid,sitegroupid,createtime) values(?,?,?,?,?,?,now())";
	@Override
	public long saveInteractCb(final InteractCb cb)
	{
		int rs=getJdbcTemplate().update(SAVE_INTERACT_CB, new Object[]{cb.getOwner(),cb.getTitle(),cb.getOwner(),cb.getAptid(),cb.getSiteid(),cb.getSitegroupid()});
		return rs;
	}
	
	@Override
	public HbConfig findCbhbConfig(long cbid)
	{
		List<HbConfig> list=getJdbcTemplate().query("select * from es_interact_cb where id=? ", new Object[]{cbid}, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				HbConfig h=new HbConfig();
				h.setWishing(rs.getString("wishing"));
				h.setRemark(rs.getString("remark"));
				h.setAct_name(rs.getString("act_name"));
				return h;
			}
		});
		return list.size()>0?list.get(0):null;
	}
	
	@Override
	public int updateHbConfig(long cbid, HbConfig hbc)
	{
		return getJdbcTemplate().update("update es_interact_cb set wishing=?,act_name=?,remark=? where id=?", new Object[]{hbc.getWishing(),hbc.getAct_name(),hbc.getRemark(),cbid});
	}
	
	@Override
	public void updateApptype(long cbid, long id)
	{
		getJdbcTemplate().update("update es_interact_cb set spageid=? where id=?", new Object[]{id,cbid});
	}
	
	
	@Override
	public long findCbStatusPageid(long pageid)
	{
		List<Long> list=getJdbcTemplate().query("select id from es_page where apptype='STS' and siteid=(select siteid from es_page where id=?) limit 1", new Object[]{pageid}, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				return rs.getLong("id");
			}
		});
		return list.size()>0?list.get(0):0;
	}
	
	@Override
	public int updateAptAcpt(long cbid, int status)
	{
		return getJdbcTemplate().update("update es_interact_cb set pizhun=? where id=?", new Object[]{status,cbid});
	}
	
	@Override
	public InteractCb findCbByOwner(long owner)
	{
		List<InteractCb> list = getJdbcTemplate().query("select * from es_interact_cb where owner=? ", new Object[]
		{ owner}, new CbRowmapper());
		return list.size() > 0 ? list.get(0) : null;
	}
	
	@Override
	public int updateUsedSiteGroup(long owner, Map<String, Long> map)
	{
		return getJdbcTemplate().update("update es_interact_cb set spageid=?,npageid=?,rpageid=?,ypageid=? where owner=?", new Object[]{map.get("CBS"),map.get("CBN"),map.get("CBR"),map.get("CBY"),owner});
	}
}
