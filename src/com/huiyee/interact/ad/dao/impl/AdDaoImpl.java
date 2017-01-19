package com.huiyee.interact.ad.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.model.Page;
import com.huiyee.interact.ad.dao.IAdDao;
import com.huiyee.interact.ad.model.Ad;


public class AdDaoImpl extends AbstractDao implements IAdDao
{

	@Override
	public Ad findAdByOwner(long id)
	{
		List<Ad> list=getJdbcTemplate().query("select * from es_ad where owner=?", new Object[]{id}, new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				Ad ad=new Ad();
				ad.setId(rs.getLong("id"));
				ad.setOwner(rs.getLong("owner"));
				return ad;
			}
		});
		return list.size()>0?list.get(0):null;
	}
	
	@Override
	public int saveAd(long id)
	{
		return getJdbcTemplate().update("insert into es_ad (owner) values (?)", new Object[]{id});
	}
	
	@Override
	public Page findPageByApptypeAndGroupId(long gid, String apptype)
	{
		List<Page> list=getJdbcTemplate().query("select * from es_site s join es_page p on s.id=p.siteid where s.sitegroupid=? and p.apptype=?", new Object[]{gid,apptype}, new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				Page p=new Page();
				p.setId(rs.getLong("p.id"));
				return p;
			}
		});
		return list.size()>0?list.get(0):null;
	}
}
