package com.huiyee.interact.xc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.Site;
import com.huiyee.interact.xc.model.Xc;
import com.huiyee.interact.xc.model.XcBigScreen;

public class XcBigScreenDao implements IXcBigScreenDao {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<XcBigScreen> findXcBigScreenList(long siteid) {	
		String sql ="select * from es_page p where p.siteid = ? and isonline= 'Y' and p.status!='DEL'";
		Object[] params = {siteid};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				XcBigScreen bs = new XcBigScreen();
				bs.setId(rs.getLong("p.id"));
				bs.setPageid(rs.getLong("p.id"));
				bs.setPageName(rs.getString("p.name"));
				bs.setImgurl(rs.getString("p.img"));
				return bs;
			}
			
		});
	}

	@Override
	public List<Page> findPageList(long siteid)
	{
		String sql = "select p.id,p.name pageName from es_page p where p.siteid = ? and p.isonline ='Y' ";
		Object[] params = {siteid};
		return jdbcTemplate.query(sql, params, new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				Page p = new Page();
				p.setId(rs.getLong("p.id"));
				p.setName(rs.getString("pageName"));
				return p;
			}
			
		});
	}

	@Override
	public long saveXcBigScreen(final long xcid, final String name,final long pageid,final String imgurl)
	{
		final String sql = "insert into es_interact_xc_dpm(name,pageid,xcid,imgurl) values(?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0)
					throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(sql,
						new String[] { "id" });
				ps.setString(1, name);
				ps.setLong(2, pageid);
				ps.setLong(3, xcid);
				ps.setString(4, imgurl);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	@Override
	public List<Site> findSiteList(long ownerid)
	{
		String sql = "select id,name from es_site where ownerid = ? and status != 'DEL'";
		Object[] params = {ownerid};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				Site s = new Site();
				s.setId(rs.getLong("id"));
				s.setName(rs.getString("name"));
				return s;
			}
		});
	}

	@Override
	public int delXcBigScreen(long id)
	{
		String sql = "delete from es_interact_xc_dpm where id = ?";
		Object[] params = {id};
		return jdbcTemplate.update(sql,params);
	}

	@Override
	public int updateImgurlByDpmid(long id, String imgurl)
	{
		String sql = "update es_page set img = ? where id = ?";
		Object[] params = {imgurl,id};
		return jdbcTemplate.update(sql, params);
	}
	
	@Override
	public long findSiteIdByXc(long xcid)
	{
		String sql="select a.id from es_site a join es_site_group b on a.sitegroupid=b.id where b.type='W' and b.entityid=? and a.type='C'";
		List<Long> list=jdbcTemplate.query(sql, new Object[]{xcid}, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				return rs.getLong("id");
			}
		});
		return list.size()==1?list.get(0):0;
	}
}
