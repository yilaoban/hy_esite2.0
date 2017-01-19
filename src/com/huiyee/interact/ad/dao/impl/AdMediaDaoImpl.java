package com.huiyee.interact.ad.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.Site;
import com.huiyee.interact.ad.dao.IAdMediaDao;
import com.huiyee.interact.ad.model.AdMedia;
import com.huiyee.interact.ad.model.AdWay;
import com.huiyee.interact.ad.model.Adwd;



public class AdMediaDaoImpl implements IAdMediaDao
{
	private JdbcTemplate jdbcTemplate;
	

	@Override
	public List<AdMedia> findMediaList(long owner, String media)
	{
		return jdbcTemplate.query("select * from es_ad_media where owner=? and name like ? order by id desc", new Object[]{owner,"%"+media+"%"}, new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				AdMedia media=new AdMedia();
				media.setId(rs.getLong("id"));
				media.setName(rs.getString("name"));
				media.setOwner(rs.getLong("owner"));
				media.setCreatetime(rs.getTimestamp("createtime"));
				media.setHydesc(rs.getString("hydesc"));
				return media;
			}
		});
	}
	
	@Override
	public AdMedia findMediaByName(String media, long owner)
	{
		List<AdMedia> list=jdbcTemplate.query("select * from es_ad_media where owner=? and name =? ", new Object[]{owner,media}, new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				AdMedia media=new AdMedia();
				media.setId(rs.getLong("id"));
				media.setName(rs.getString("name"));
				media.setOwner(rs.getLong("owner"));
				media.setCreatetime(rs.getTimestamp("createtime"));
				media.setHydesc(rs.getString("hydesc"));
				return media;
			}
		});
		return list.size()>0?list.get(0):null;
	}
	
	@Override
	public long saveMedia(final String media,final long owner)
	{
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator()
		{
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement("insert into es_ad_media (name,createtime,owner)values(?,now(),?)", new String[]
				{ "id" });
				ps.setString(1, media);
				ps.setLong(2, owner);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}


	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int findTotalAdMedia(long owner)
	{
		String sql = "select count(id) from es_ad_media where owner = ?";
		Object[] params = {owner};
		return jdbcTemplate.queryForInt(sql,params);
	}

	@Override
	public List<AdMedia> findAdMediaListByOwner(long owner, int start, int size)
	{
		String sql = "select * from es_ad_media m  where m.owner = ? order by m.id desc limit ?,?";
		Object[] params = {owner,start,size};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				AdMedia m = new AdMedia();
				m.setId(rs.getLong("m.id"));
				m.setName(rs.getString("m.name"));
				m.setHydesc(rs.getString("m.hydesc"));
				m.setCreatetime(rs.getTimestamp("m.createtime"));
				m.setOwner(rs.getLong("m.owner"));
				m.setArea(rs.getString("m.area"));
				return m;
			}
			
		});
	}
	
	@Override
	public List<AdMedia> findAdMediaListByOwner(long owner){
		String sql = "select * from es_ad_media m  where m.owner = ? order by m.id desc";
		Object[] params = {owner};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				AdMedia m = new AdMedia();
				m.setId(rs.getLong("m.id"));
				m.setName(rs.getString("m.name"));
				m.setHydesc(rs.getString("m.hydesc"));
				m.setCreatetime(rs.getTimestamp("m.createtime"));
				m.setOwner(rs.getLong("m.owner"));
				m.setArea(rs.getString("m.area"));
				return m;
			}
			
		});
	}
	

	@Override
	public int savedMedia(AdMedia media)
	{
		if(media.getHztime() != null){
			String sql = "insert into es_ad_media(name,hydesc,createtime,owner,area) values(?,?,?,?,?)";
			Object[] params = {media.getName(),media.getHydesc(),media.getCreatetime(),media.getOwner(),media.getArea()};
			return jdbcTemplate.update(sql, params);
		}else{
			String sql = "insert into es_ad_media(name,hydesc,createtime,owner,area) values(?,?,now(),?,?)";
			Object[] params = {media.getName(),media.getHydesc(),media.getOwner(),media.getArea()};
			return jdbcTemplate.update(sql, params);
		}
	}

	@Override
	public AdMedia findAdMediaById(long mid)
	{
		String sql = "select * from es_ad_media m  where m.id = ?";
		Object[] params = {mid};
		List<AdMedia> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				AdMedia m = new AdMedia();
				m.setId(rs.getLong("m.id"));
				m.setName(rs.getString("m.name"));
				m.setHydesc(rs.getString("m.hydesc"));
				m.setCreatetime(rs.getTimestamp("m.createtime"));
				m.setOwner(rs.getLong("m.owner"));
				return m;
			}
			
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int updateMedia(AdMedia media)
	{
		if(media.getHztime() != null){
			String sql = "update es_ad_media set name=?,hydesc=?,area=?,createtime=? where owner=? and id = ?";
			Object[] params = {media.getName(),media.getHydesc(),media.getArea(),media.getCreatetime(),media.getOwner(),media.getId()};
			return jdbcTemplate.update(sql, params);
		}else{
			String sql = "update es_ad_media set name=?,hydesc=?,area=? where owner=? and id = ?";
			Object[] params = {media.getName(),media.getHydesc(),media.getArea(),media.getOwner(),media.getId()};
			return jdbcTemplate.update(sql, params);
		}
	}

	@Override
	public int delMediaById(long mid, long owner)
	{
		String sql = "delete from es_ad_media where owner=? and id = ?";
		Object[] params = {owner,mid};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int findTotalMediaWay(long mid,String qwd, String wd)
	{
		List<Object> param = new ArrayList<Object>();
		String sql = "select count(w.id) from es_ad_way w join es_ad_media m on w.mid = m.id join es_ad_wd wd on (w.wdid = wd.id and wd.type = 'BBN') join es_ad_wd p on (w.qwdid = p.id and p.type = 'QHO') where w.mid = ?";
		param.add(mid);
		if (StringUtils.isNotEmpty(qwd))
		{
			sql += " and p.name like ?";
			param.add("%"+qwd+"%");
		}
		if (StringUtils.isNotEmpty(wd))
		{
			sql += " and wd.name like ?";
			param.add("%"+wd+"%");
		}
		return jdbcTemplate.queryForInt(sql, param.toArray());
	}

	@Override
	public List<AdWay> findMediaWayList(long mid,String qwd, String wd, int start, int size)
	{
		List<Object> param = new ArrayList<Object>();
		String sql = "select * from es_ad_way w join es_ad_media m on w.mid = m.id join es_ad_wd wd on (w.wdid = wd.id and wd.type = 'BBN') join es_ad_wd p on (w.qwdid = p.id and p.type = 'QHO') where w.mid = ? ";
		param.add(mid);
		if (StringUtils.isNotEmpty(qwd))
		{
			sql += " and p.name like ?";
			param.add("%"+qwd+"%");
		}
		if (StringUtils.isNotEmpty(wd))
		{
			sql += " and wd.name like ?";
			param.add("%"+wd+"%");
		}
		sql += "order by w.id desc limit ?,?"; 
		param.add(start);param.add(size);
		return jdbcTemplate.query(sql, param.toArray(), new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				AdWay way = new AdWay();
				AdMedia media = new AdMedia();
				media.setName(rs.getString("m.name"));
				media.setId(rs.getLong("m.id"));
				Adwd wd = new Adwd();
				wd.setId(rs.getLong("wd.id"));
				wd.setName(rs.getString("wd.name"));
				Adwd qwd = new Adwd();
				qwd.setName(rs.getString("p.name"));
				qwd.setId(rs.getLong("p.id"));
				way.setQwd(qwd);
				way.setWd(wd);
				way.setMedia(media);
				way.setId(rs.getLong("w.id"));
				way.setQwdid(rs.getLong("w.qwdid"));
				way.setPageid(rs.getLong("w.pageid"));
				way.setType(rs.getString("w.type"));
				way.setNum(rs.getInt("w.num"));
				return way;
			}
			
		});
	}

	@Override
	public int delWayById(long wayid, long owner)
	{
		String sql = "delete from es_ad_way where owner=? and id = ?";
		Object[] params = {owner,wayid};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public AdWay findAdWayById(long wayid, long owner)
	{
		String sql = "select * from es_ad_way w join es_ad_media m on w.mid=m.id join es_ad_wd d on w.wdid=d.id join es_ad_wd a on w.qwdid=a.id where  w.owner=? and w.id=?";
		Object[] params = {owner,wayid};
		List<AdWay> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				AdWay way = new AdWay();
				way.setId(rs.getLong("id"));
				way.setMid(rs.getLong("w.mid"));
				way.setWdid(rs.getLong("w.wdid"));
				way.setQwdid(rs.getLong("w.qwdid"));
				way.setPageid(rs.getLong("w.pageid"));
				way.setUrl(rs.getString("w.url"));
				way.setFsurl(rs.getString("w.fsurl"));
				way.setType(rs.getString("w.type"));
				way.setNum(rs.getInt("w.num"));
				
				AdMedia media=new AdMedia();
				media.setId(rs.getLong("m.id"));
				media.setName(rs.getString("m.name"));
				media.setOwner(rs.getLong("m.owner"));
				media.setCreatetime(rs.getTimestamp("m.createtime"));
				media.setHydesc(rs.getString("m.hydesc"));
				way.setMedia(media);
				
				Adwd wd = new Adwd();
				wd.setId(rs.getLong("d.id"));
				wd.setName(rs.getString("d.name"));
				wd.setOwner(rs.getLong("d.owner"));
				wd.setCreatetime(rs.getTimestamp("d.createtime"));
				way.setWd(wd);
				
			
				Adwd qwd = new Adwd();
				qwd.setId(rs.getLong("a.id"));
				qwd.setName(rs.getString("a.name"));
				qwd.setOwner(rs.getLong("a.owner"));
				qwd.setCreatetime(rs.getTimestamp("a.createtime"));
				way.setQwd(qwd);
				return way;
			}
			
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public long findgroupid(long pageid)
	{
		String sql ="select * from es_site s join es_page p on s.id=p.siteid where p.id=?";
		List<Site> list=jdbcTemplate.query(sql, new Object[]{pageid}, new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				Site s=new Site();
				s.setSitegroupid(rs.getLong("s.sitegroupid"));
				return s;
			}
		});
		if(list != null && list.size()>0){
			return list.get(0).getSitegroupid();
		}
		return 0;
	}

	@Override
	public String findgroupnameBygroupId(long groupid)
	{
		String sql="select groupName from es_site_group where id=?";
		Object[] params ={ groupid };
		try
		{
			return (String) jdbcTemplate.queryForObject(sql, params, String.class);
		} catch (DataAccessException e)
		{
			return null;
		}
	}
	
	
}
