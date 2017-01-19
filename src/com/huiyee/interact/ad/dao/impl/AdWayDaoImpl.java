
package com.huiyee.interact.ad.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.interact.ad.dao.IAdWayDao;
import com.huiyee.interact.ad.model.AdMedia;
import com.huiyee.interact.ad.model.AdWay;
import com.huiyee.interact.ad.model.Adwd;

public class AdWayDaoImpl extends AbstractDao implements IAdWayDao
{

	@Override
	public long saveAdWay(final long mediaid,final long wdid, final long qwdid, final long pageid, final String url, final String fsurl, final long owner,final String type,final int num)
	{
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
				String sql = "insert ignore into es_ad_way (owner,mid,qwdid,wdid,pageid,url,fsurl,type,num) values(?,?,?,?,?,?,?,?,?)";
				PreparedStatement ps = arg0.prepareStatement(sql, new String[] { "id" });
				ps.setLong(1, owner);
				ps.setLong(2, mediaid);
				ps.setLong(3, qwdid);
				ps.setLong(4, wdid);
				ps.setLong(5, pageid);
				ps.setString(6, url);
				ps.setString(7, fsurl);
				ps.setString(8, type);
				ps.setInt(9, num);
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().longValue();
	}
	
	private static final String UPDATE_ADWAY = "update esite.es_ad_way set owner=?,qwdid=?,wdid=?,mid=?,pageid=?,url=?,fsurl=?,type=?,num=? where id=?";
	@Override
	public int updateAdWay(long wayid,long mediaid, long wdid, long qwdid, long pageid, String url, String fsurl, long owner,String type,int num)
	{
		Object[] params={owner,qwdid,wdid,mediaid,pageid,url,fsurl,type,num,wayid};
		return getJdbcTemplate().update(UPDATE_ADWAY,params);
		
	}
	
	private static final String UPDATE_MEDIAWAY = "update esite.es_ad_way set owner=?,qwdid=?,wdid=?,pageid=?,url=?,fsurl=? where id=?";
	@Override
	public int updateMediaWay(long wayid, long wdid, long qwdid, long pageid, String url, String fsurl, long owner)
	{
		Object[] params={owner,qwdid,wdid,pageid,url,fsurl,wayid};
		return getJdbcTemplate().update(UPDATE_MEDIAWAY,params);
		
	}
	
	@Override
	public AdWay findWayByid(long wayid)
	{
		List<AdWay> list=getJdbcTemplate().query("select * from es_ad_way where id=?", new Object[]{wayid}, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				AdWay way=new AdWay();
				way.setWdid(rs.getLong("wdid"));
				way.setQwdid(rs.getLong("qwdid"));
				way.setMid(rs.getLong("mid"));
				way.setUrl(rs.getString("url"));
				way.setFsurl(rs.getString("fsurl"));
				return way;
			}
		});
		return list.size()>0?list.get(0):null;
	}
	

	@Override
	public int findWayTotal(long owner, String qwd, String media, String wd)
	{
		List<Object> param = new ArrayList<Object>();
		String sql = "select count(*) from es_ad_way w join es_ad_media m on w.mid=m.id join es_ad_wd d on w.wdid=d.id join es_ad_wd a on w.qwdid=a.id where  w.owner=? ";
		param.add(owner);
		if (StringUtils.isNotEmpty(qwd))
		{
			sql += " and (a.name like ?  and a.type='QHO')";
			param.add("%"+qwd+"%");
		}
		if (StringUtils.isNotEmpty(media))
		{
			sql += " and m.name like ?";
			param.add("%"+media+"%");
		}
		if (StringUtils.isNotEmpty(wd))
		{
			sql += " and (d.name like ? and d.type='BBN')";
			param.add("%"+wd+"%");
		}
		return getJdbcTemplate().queryForInt(sql, param.toArray());
	}

	@Override
	public List<AdWay> findWayList(long owner, String qwd, String media, String wd, int start, int size)
	{
		List<Object> param = new ArrayList<Object>();
		String sql = "select * from es_ad_way w join es_ad_media m on w.mid=m.id join es_ad_wd d on w.wdid=d.id join es_ad_wd a on w.qwdid=a.id where  w.owner=? ";
		param.add(owner);
		if (StringUtils.isNotEmpty(qwd))
		{
			sql += " and (a.name like ?  and a.type='QHO')";
			param.add("%"+qwd+"%");
		}
		if (StringUtils.isNotEmpty(media))
		{
			sql += " and m.name like ?";
			param.add("%"+media+"%");
		}
		if (StringUtils.isNotEmpty(wd))
		{
			sql += " and (d.name like ? and d.type='BBN')";
			param.add("%"+wd+"%");
		}
		sql += " order by w.id desc limit ?,?";
		param.add(start);
		param.add(size);
		return getJdbcTemplate().query(sql, param.toArray(), new RowMapper()
		{

			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				AdWay way = new AdWay();
				way.setId(rs.getLong("w.id"));
				way.setMid(rs.getLong("w.mid"));
				way.setWdid(rs.getLong("w.wdid"));
				way.setQwdid(rs.getLong("w.qwdid"));
				way.setPageid(rs.getLong("w.pageid"));
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
	}
}
