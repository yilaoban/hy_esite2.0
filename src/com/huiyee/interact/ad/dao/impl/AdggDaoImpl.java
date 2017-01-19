package com.huiyee.interact.ad.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.interact.ad.dao.IAdggDao;
import com.huiyee.interact.ad.model.Adgg;


public class AdggDaoImpl implements IAdggDao
{
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int findTotalAdGGByOwner(long owner)
	{
		String sql = "select count(id) from es_ad_gg where owner = ?";
		Object[] params = {owner};
		return jdbcTemplate.queryForInt(sql, params);
	}

	@Override
	public List<Adgg> findAdGGListByOwner(long owner, int start, int size)
	{
		String sql = "select * from es_ad_gg where owner = ? order by id desc limit ?,?";
		Object[] params = {owner,start,size};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				Adgg adgg = new Adgg();
				adgg.setId(rs.getLong("id"));
				adgg.setTitle(rs.getString("title"));
				adgg.setHydesc(rs.getString("hydesc"));
				adgg.setContent(rs.getString("content"));
				adgg.setOwner(rs.getLong("owner"));
				adgg.setImg(rs.getString("img"));
				adgg.setUrl(rs.getString("url"));
				adgg.setStarttime(rs.getTimestamp("starttime"));
				adgg.setEndtime(rs.getTimestamp("endtime"));
				return adgg;
			}
			
			
		});
	}
	
	
	@Override
	public List<Adgg> findAdGGListByOwnerOrderByStart(long owner, int start, int size)
	{
		String sql = "select * from es_ad_gg where owner = ? order by starttime desc limit ?,?";
		Object[] params = {owner,start,size};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				Adgg adgg = new Adgg();
				adgg.setId(rs.getLong("id"));
				adgg.setTitle(rs.getString("title"));
				adgg.setHydesc(rs.getString("hydesc"));
				adgg.setContent(rs.getString("content"));
				adgg.setOwner(rs.getLong("owner"));
				adgg.setImg(rs.getString("img"));
				adgg.setUrl(rs.getString("url"));
				adgg.setStarttime(rs.getTimestamp("starttime"));
				adgg.setEndtime(rs.getTimestamp("endtime"));
				return adgg;
			}
			
			
		});
	}

	@Override
	public int saveGG(Adgg adgg)
	{
		String sql = "insert into es_ad_gg(title,hydesc,content,owner,img,url,starttime,endtime) values(?,?,?,?,?,?,?,?)";
		Object[] params = {adgg.getTitle(),adgg.getHydesc(),adgg.getContent(),adgg.getOwner(),adgg.getImg(),adgg.getUrl(),adgg.getStarttime(),adgg.getEndtime()};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public Adgg findadGGById(long ggid)
	{
		String sql = "select * from es_ad_gg where id = ?";
		Object[] params = {ggid};
		List<Adgg> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				Adgg adgg = new Adgg();
				adgg.setId(rs.getLong("id"));
				adgg.setTitle(rs.getString("title"));
				adgg.setHydesc(rs.getString("hydesc"));
				adgg.setContent(rs.getString("content"));
				adgg.setOwner(rs.getLong("owner"));
				adgg.setImg(rs.getString("img"));
				adgg.setUrl(rs.getString("url"));
				adgg.setStarttime(rs.getTimestamp("starttime"));
				adgg.setEndtime(rs.getTimestamp("endtime"));
				return adgg;
			}
			
		});
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int updateGG(Adgg adgg)
	{
		String sql = "update es_ad_gg set title=?,hydesc=?,content=?,img=?,url=?,starttime=?,endtime=? where id = ? and owner=?";
		Object[] params = {adgg.getTitle(),adgg.getHydesc(),adgg.getContent(),adgg.getImg(),adgg.getUrl(),adgg.getStarttime(),adgg.getEndtime(),adgg.getId(),adgg.getOwner()};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int delGGById(long ggid, long owner)
	{
		String sql = "delete from es_ad_gg where id = ? and owner = ?";
		Object[] params = {ggid,owner};
		return jdbcTemplate.update(sql, params);
	}
	
	@Override
	public List<Adgg> findAdGGListByOwner(long owner)
	{
		String sql = "select * from es_ad_gg where owner = ? order by id desc";
		Object[] params = {owner};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				Adgg adgg = new Adgg();
				adgg.setId(rs.getLong("id"));
				adgg.setTitle(rs.getString("title"));
				adgg.setHydesc(rs.getString("hydesc"));
				adgg.setContent(rs.getString("content"));
				adgg.setOwner(rs.getLong("owner"));
				adgg.setImg(rs.getString("img"));
				adgg.setUrl(rs.getString("url"));
				adgg.setStarttime(rs.getTimestamp("starttime"));
				adgg.setEndtime(rs.getTimestamp("endtime"));
				return adgg;
			}
			
			
		});
	}

}
