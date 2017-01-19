package com.huiyee.interact.yuyue.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.util.DateUtil;
import com.huiyee.interact.yuyue.model.YuYue;
import com.huiyee.interact.yuyue.model.YuYueCatalog;
import com.huiyee.interact.yuyue.model.YuYueRecord;
import com.huiyee.interact.yuyue.model.YuYueSS;
import com.huiyee.interact.yuyue.model.YuYueSSTime;
import com.huiyee.interact.yuyue.model.YuYueService;
import com.huiyee.interact.yuyue.model.YuYueServicer;
import com.huiyee.interact.yuyue.model.YuYueTag;


public class YuYueDaoImpl implements IYuYueDao
{
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public YuYue findYuYueByOwner(long owner)
	{
		String sql = "select * from es_yu_yue where owner = ?";
		Object[] params = {owner};
		List<YuYue> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				YuYue yy = new YuYue();
				yy.setId(rs.getLong("id"));
				yy.setAptid(rs.getLong("aptid"));
				yy.setOwner(rs.getLong("owner"));
				return yy;
			}
			
		});
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public long saveYuYue(final YuYue yueyue)
	{
		final String sql = "insert into es_yu_yue(owner,aptid) values(?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator()
		{

			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(sql, new String[]
				{ "id" });
				int i = 1;
				ps.setLong(i++, yueyue.getOwner());
				ps.setLong(i++, yueyue.getAptid());
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	@Override
	public int findTotalYuYueCatalog(long owner)
	{
		String sql = "select count(c.id) from es_yu_yue_catalog c join es_yu_yue y on c.yyid = y.id where y.owner = ?";
		Object[] params = {owner};
		return jdbcTemplate.queryForInt(sql,params);
	}

	@Override
	public List<YuYueCatalog> findYuYueCatalog(long owner, int start, int size)
	{
		String sql = "select * from es_yu_yue_catalog c join es_yu_yue y on c.yyid = y.id where y.owner = ? order by c.idx desc limit ?,?";
		Object[] params = {owner,start,size};
		return jdbcTemplate.query(sql, params, new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				YuYueCatalog c = new YuYueCatalog();
				c.setId(rs.getLong("c.id"));
				c.setName(rs.getString("c.name"));
				c.setHydesc(rs.getString("c.hydesc"));
				c.setHyldesc(rs.getString("c.hyldesc"));
				c.setYyid(rs.getLong("c.yyid"));
				c.setImg(rs.getString("c.img"));
				c.setSpageid(rs.getLong("c.spageid"));
				c.setXpageid(rs.getLong("c.xpageid"));
				c.setIdx(rs.getInt("c.idx"));
				c.setType(rs.getString("c.type"));
				c.setYzpageid(rs.getLong("c.yzpageid"));
				c.setDzpageid(rs.getLong("c.dzpageid"));
				c.setFwxpageid(rs.getLong("c.fwxpageid"));
				c.setFwzxpageid(rs.getLong("c.fwzxpageid"));
				c.setFwpageid(rs.getLong("c.fwpageid"));
				c.setFwzpageid(rs.getLong("c.fwzpageid"));
				return c;
			}
			
		});
	}
	
	
	@Override
	public List<YuYueCatalog> findYuYueCatalog(long owner)
	{
		String sql = "select * from es_yu_yue_catalog c join es_yu_yue y on c.yyid = y.id where y.owner = ? order by c.idx desc";
		Object[] params = {owner};
		return jdbcTemplate.query(sql, params, new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				YuYueCatalog c = new YuYueCatalog();
				c.setId(rs.getLong("c.id"));
				c.setName(rs.getString("c.name"));
				c.setHydesc(rs.getString("c.hydesc"));
				c.setHyldesc(rs.getString("c.hyldesc"));
				c.setYyid(rs.getLong("c.yyid"));
				c.setImg(rs.getString("c.img"));
				c.setSpageid(rs.getLong("c.spageid"));
				c.setXpageid(rs.getLong("c.xpageid"));
				c.setIdx(rs.getInt("c.idx"));
				c.setType(rs.getString("c.type"));
				c.setYzpageid(rs.getLong("c.yzpageid"));
				c.setDzpageid(rs.getLong("c.dzpageid"));
				c.setFwxpageid(rs.getLong("c.fwxpageid"));
				c.setFwzxpageid(rs.getLong("c.fwzxpageid"));
				c.setFwpageid(rs.getLong("c.fwpageid"));
				c.setFwzpageid(rs.getLong("c.fwzpageid"));
				return c;
			}
			
		});
	}

	@Override
	public long saveYuYueCatalog(final YuYueCatalog yuYueCatalog)
	{
		final String sql = "insert into es_yu_yue_catalog(name,hydesc,hyldesc,yyid,img,spageid,xpageid,dzpageid,yzpageid,fwxpageid,fwzxpageid,fwpageid,fwzpageid,idx) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator()
		{

			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(sql, new String[]
				{ "id" });
				int i = 1;
				ps.setString(i++, yuYueCatalog.getName());
				ps.setString(i++, yuYueCatalog.getHydesc());
				ps.setString(i++, yuYueCatalog.getHyldesc());
				ps.setLong(i++, yuYueCatalog.getYyid());
				ps.setString(i++, yuYueCatalog.getImg());
				ps.setLong(i++, yuYueCatalog.getSpageid());
				ps.setLong(i++, yuYueCatalog.getXpageid());
				ps.setLong(i++, yuYueCatalog.getDzpageid());
				ps.setLong(i++, yuYueCatalog.getYzpageid());
				ps.setLong(i++, yuYueCatalog.getFwxpageid());
				ps.setLong(i++, yuYueCatalog.getFwzxpageid());
				ps.setLong(i++, yuYueCatalog.getFwpageid());
				ps.setLong(i++, yuYueCatalog.getFwzpageid());
				ps.setInt(i++, yuYueCatalog.getIdx());
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	@Override
	public long delYuYueCatalog(long catid,long yyid)
	{
		return jdbcTemplate.update("delete from es_yu_yue_catalog where id=? and yyid= ?", new Object[]{ catid,yyid});
	}

	@Override
	public YuYueCatalog findYuYueCatalogById(long catid, long owner)
	{
		String sql = "select * from es_yu_yue_catalog c join es_yu_yue y on c.yyid = y.id where c.id = ? and y.owner = ?";
		Object[] params = {catid,owner};
		List<YuYueCatalog> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				YuYueCatalog c = new YuYueCatalog();
				c.setId(rs.getLong("c.id"));
				c.setName(rs.getString("c.name"));
				c.setHydesc(rs.getString("c.hydesc"));
				c.setHyldesc(rs.getString("c.hyldesc"));
				c.setYyid(rs.getLong("c.yyid"));
				c.setImg(rs.getString("c.img"));
				c.setSpageid(rs.getLong("c.spageid"));
				c.setXpageid(rs.getLong("c.xpageid"));
				c.setIdx(rs.getInt("c.idx"));
				c.setYzpageid(rs.getLong("c.yzpageid"));
				c.setDzpageid(rs.getLong("c.dzpageid"));
				c.setFwxpageid(rs.getLong("c.fwxpageid"));
				c.setFwzxpageid(rs.getLong("c.fwzxpageid"));
				c.setFwpageid(rs.getLong("c.fwpageid"));
				c.setFwzpageid(rs.getLong("c.fwzpageid"));
				return c;
			}
			
		});
		if(list!= null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int updateYuYueCatalog(YuYueCatalog yuYueCatalog)
	{
		String sql = "update es_yu_yue_catalog set name=?,hydesc=?,hyldesc=?,yyid=?,img=?,spageid=?,xpageid=?,dzpageid=?,yzpageid=?,fwxpageid=?,fwzxpageid=?,fwpageid=?,fwzpageid=? where id = ?";
		Object[] params = {yuYueCatalog.getName(),yuYueCatalog.getHydesc(),yuYueCatalog.getHyldesc(),yuYueCatalog.getYyid(),yuYueCatalog.getImg(),yuYueCatalog.getSpageid(),yuYueCatalog.getXpageid(),yuYueCatalog.getDzpageid(),yuYueCatalog.getYzpageid(),yuYueCatalog.getFwxpageid(),yuYueCatalog.getFwzxpageid(),yuYueCatalog.getFwpageid(),yuYueCatalog.getFwzpageid(),yuYueCatalog.getId()};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int findTotalYuYueServiceByCatid(long catid,long yyid)
	{
		String sql = "select count(id) from es_yu_yue_service where caid = ? and yyid = ?";
		Object[] params = {catid,yyid};
		return jdbcTemplate.queryForInt(sql, params);
	}

	@Override
	public List<YuYueService> findYuYueServiceListByCatid(long catid, long yyid, int start, int size)
	{
		String sql = "select * from es_yu_yue_service where caid = ? and yyid = ? order by idx desc limit ?,?";
		Object[] params = {catid,yyid,start,size};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				YuYueService s = new YuYueService();
				s.setId(rs.getLong("id"));
				s.setName(rs.getString("name"));
				s.setHydesc(rs.getString("hydesc"));
				s.setHyldesc(rs.getString("hyldesc"));
				s.setYyid(rs.getLong("yyid"));
				s.setCaid(rs.getLong("caid"));
				s.setImg(rs.getString("img"));
				s.setSimg(rs.getString("simg"));
				return s;
			}
			
		});
	}
	@Override
	public List<YuYueService> findYuYueServiceListByCatid(long catid,long yyid){
		String sql = "select * from es_yu_yue_service where caid = ? and yyid = ? order by idx desc";
		Object[] params = {catid,yyid};
		List<YuYueService> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				YuYueService s = new YuYueService();
				s.setId(rs.getLong("id"));
				s.setName(rs.getString("name"));
				s.setHydesc(rs.getString("hydesc"));
				s.setHyldesc(rs.getString("hyldesc"));
				s.setYyid(rs.getLong("yyid"));
				s.setCaid(rs.getLong("caid"));
				s.setImg(rs.getString("img"));
				s.setSimg(rs.getString("simg"));
				return s;
			}
			
		});
		if(list != null && list.size()>0){
			return list;
		}
		return null;
	}

	@Override
	public long saveYuYueService(final YuYueService yuYueService)
	{
		final String sql = "insert into es_yu_yue_service(name,hydesc,hyldesc,yyid,caid,idx,img,simg) values(?,?,?,?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator()
		{

			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(sql, new String[]
				{ "id" });
				int i = 1;
				ps.setString(i++, yuYueService.getName());
				ps.setString(i++, yuYueService.getHydesc());
				ps.setString(i++, yuYueService.getHyldesc());
				ps.setLong(i++, yuYueService.getYyid());
				ps.setLong(i++, yuYueService.getCaid());
				ps.setInt(i++, yuYueService.getIdx());
				ps.setString(i++, yuYueService.getImg());
				ps.setString(i++, yuYueService.getSimg());
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	@Override
	public YuYueService findYuYueServiceById(long yyid, long serviceid)
	{
		String sql = "select * from es_yu_yue_service where yyid=? and id = ?";
		Object[] params = {yyid,serviceid};
		List<YuYueService> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				YuYueService s = new YuYueService();
				s.setId(rs.getLong("id"));
				s.setName(rs.getString("name"));
				s.setHydesc(rs.getString("hydesc"));
				s.setHyldesc(rs.getString("hyldesc"));
				s.setCaid(rs.getLong("caid"));
				s.setYyid(rs.getLong("yyid"));
				s.setIdx(rs.getInt("idx"));
				s.setImg(rs.getString("img"));
				s.setSimg(rs.getString("simg"));
				return s;
			}
			
		});
		if(list != null && list.size()> 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int updateYuYueService(YuYueService yuYueService)
	{
		String sql = "update es_yu_yue_service set name=?,hydesc=?,hyldesc=?,yyid=?,caid=?,img=?,simg=? where id = ?";
		Object[] params = {yuYueService.getName(),yuYueService.getHydesc(),yuYueService.getHyldesc(),yuYueService.getYyid(),yuYueService.getCaid(),yuYueService.getImg(),yuYueService.getSimg(),yuYueService.getId()};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public long delYuYueService(long serviceid, long yyid)
	{
		String sql = "delete from es_yu_yue_service where id = ? and yyid = ?";
		return jdbcTemplate.update(sql, new Object[]{ serviceid,yyid});
	}

	@Override
	public int findTotalYuYueServicerByServiceid(long serviceid)
	{
		String sql = "select count(ss.id) from es_yu_yue_ss ss join es_yu_yue_servicer s on ss.serid = s.id where ss.sid = ?";
		Object[] params = {serviceid};
		return jdbcTemplate.queryForInt(sql, params);
	}

	@Override
	public List<YuYueServicer> findYuYueServicerListByServiceid(long serviceid, int start, int size)
	{
		String sql = "select * from es_yu_yue_ss ss join es_yu_yue_servicer s on ss.serid = s.id where ss.sid = ? limit ?,?";
		Object[] params = {serviceid,start,size};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				YuYueServicer servicer = new YuYueServicer();
				servicer.setId(rs.getLong("s.id"));
				servicer.setSsid(rs.getLong("ss.id"));
				servicer.setHydesc(rs.getString("s.hydesc"));
				servicer.setImg(rs.getString("s.img"));
				servicer.setSimg(rs.getString("s.simg"));
				servicer.setHyldesc(rs.getString("s.hyldesc"));
				servicer.setName(rs.getString("s.name"));
				servicer.setCaid(rs.getLong("s.caid"));
				servicer.setYyid(rs.getLong("s.yyid"));
				return servicer;
			}
		});
	}
	
	@Override
	public List<YuYueService> findYuYueServiceListBySerid(long serid,int start,int size){
		String sql = "select * from es_yu_yue_ss ss join es_yu_yue_service s on ss.sid = s.id where ss.serid = ? limit ?,?";
		Object[] params = {serid,start,size};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				YuYueService s = new YuYueService();
				s.setId(rs.getLong("s.id"));
				s.setName(rs.getString("s.name"));
				s.setHydesc(rs.getString("s.hydesc"));
				s.setHyldesc(rs.getString("s.hyldesc"));
				s.setYyid(rs.getLong("s.yyid"));
				s.setCaid(rs.getLong("s.caid"));
				s.setImg(rs.getString("s.img"));
				s.setSimg(rs.getString("s.simg"));
				return s;
			}
		});
	}

	@Override
	public long saveYuYueServicer(final YuYueServicer yuYueServicer)
	{
		final String sql = "insert into es_yu_yue_servicer(name,hydesc,hyldesc,yyid,caid,img,simg,idx,type) values(?,?,?,?,?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator()
		{

			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(sql, new String[]
				{ "id" });
				int i = 1;
				ps.setString(i++, yuYueServicer.getName());
				ps.setString(i++, yuYueServicer.getHydesc());
				ps.setString(i++, yuYueServicer.getHyldesc());
				ps.setLong(i++, yuYueServicer.getYyid());
				ps.setLong(i++, yuYueServicer.getCaid());
				ps.setString(i++, yuYueServicer.getImg());
				ps.setString(i++, yuYueServicer.getSimg());
				ps.setInt(i++, yuYueServicer.getIdx());
				ps.setInt(i++, yuYueServicer.getType());
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	@Override
	public long saveYuYueSS(final long serid,final long sid)
	{
		final String sql = "insert ignore into es_yu_yue_ss(serid,sid) values(?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator()
		{

			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(sql, new String[]
				{ "id" });
				int i = 1;
				ps.setLong(i++, serid);
				ps.setLong(i++, sid);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
		
	}

	@Override
	public YuYueServicer findYuYueServicerById(long serid, long yyid, long catid)
	{
		String sql = "select * from es_yu_yue_servicer where id = ? and yyid = ? and caid = ?";
		Object[] params = {serid,yyid,catid};
		List<YuYueServicer> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				YuYueServicer ser = new YuYueServicer();
				ser.setId(rs.getLong("id"));
				ser.setHydesc(rs.getString("hydesc"));
				ser.setHyldesc(rs.getString("hyldesc"));
				ser.setImg(rs.getString("img"));
				ser.setSimg(rs.getString("simg"));
				ser.setCaid(rs.getLong("caid"));
				ser.setName(rs.getString("name"));
				ser.setYyid(rs.getLong("yyid"));
				return ser;
			}
			
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public YuYueServicer findYuYueServicerById(long serid)
	{
		String sql = "select * from es_yu_yue_servicer where id = ? ";
		Object[] params = {serid};
		List<YuYueServicer> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				YuYueServicer ser = new YuYueServicer();
				ser.setId(rs.getLong("id"));
				ser.setHydesc(rs.getString("hydesc"));
				ser.setHyldesc(rs.getString("hyldesc"));
				ser.setImg(rs.getString("img"));
				ser.setSimg(rs.getString("simg"));
				ser.setCaid(rs.getLong("caid"));
				ser.setName(rs.getString("name"));
				ser.setYyid(rs.getLong("yyid"));
				ser.setIdx(rs.getInt("idx"));
				ser.setTop(rs.getInt("top"));
				return ser;
			}
			
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int updateYuYueServicer(YuYueServicer yuYueServicer)
	{
		String sql = "update es_yu_yue_servicer set name=?,hydesc=?,hyldesc=?,yyid=?,caid=?,img=?,simg=? where id = ?";
		Object[] params = {yuYueServicer.getName(),yuYueServicer.getHydesc(),yuYueServicer.getHyldesc(),yuYueServicer.getYyid(),yuYueServicer.getCaid(),yuYueServicer.getImg(),yuYueServicer.getSimg(),yuYueServicer.getId()};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public long delYuYueServicer(long serid, long yyid)
	{
		String sql = "delete from es_yu_yue_servicer where id = ? and yyid = ?";
		return jdbcTemplate.update(sql, new Object[]{ serid,yyid});
	}

	@Override
	public long delYuYueSS(long serid, long sid)
	{
		String sql = "delete from es_yu_yue_ss where sid = ? and serid = ?";
		return jdbcTemplate.update(sql, new Object[]{ sid,serid});
	}

	@Override
	public int findTotalYuYueSSTimeBySsid(long ssid, long owner)
	{
		String sql = "select count(id) from es_yu_yue_ss_time where ssid = ? and owner = ?";
		Object[] params = {ssid,owner};
		return jdbcTemplate.queryForInt(sql, params);
	}
	
	/*@Override
	public int findTotalYuYueSSTimeByCatid(long catid,long owner){
		String sql = "select count(st.id) from es_yu_yue_ss_time st join es_yu_yue_ss ss on st.ssid = ss.id join es_yu_yue_servicer s on ss.serid = s.id where s.caid = ? and st.owner = ?";
		Object[] params = {catid,owner};
		return jdbcTemplate.queryForInt(sql, params);
	}*/
	
	@Override
	public List<YuYueSSTime> findYuYueSSTimeBySsid(long ssid, long owner, int start, int size)
	{
		String sql = "select * from es_yu_yue_ss_time where ssid = ? and owner = ?";
		Object[] params = {ssid,owner};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				YuYueSSTime t = new YuYueSSTime();
				t.setId(rs.getLong("id"));
				t.setDateday(rs.getInt("dateday"));
//				t.setWeeknum(rs.getInt("weeknum"));
				t.setWeekday(rs.getInt("weekday"));
				t.setTotal(rs.getInt("total"));
				t.setSsid(rs.getLong("ssid"));
				t.setOwner(rs.getLong("owner"));
				t.setShoure(rs.getInt("shoure"));
				t.setSminute(rs.getInt("sminute"));
				t.setEhoure(rs.getInt("ehoure"));
				t.setEminute(rs.getInt("eminute"));
				return t;
			}
			
		});
	}
	
	@Override
	public List<YuYueSSTime> findYuYueSSTimeByCatid(long catid,long owner,int weekday){
		String sql = "";
		List<Object> list = new ArrayList<Object>();
		list.add(catid);list.add(owner);list.add(weekday);
		if(weekday > 0){
			sql = "select * from es_yu_yue_ss_time st join es_yu_yue_ss ss on st.ssid = ss.id join es_yu_yue_servicer sr on ss.serid = sr.id join es_yu_yue_service s on ss.sid = s.id where s.caid = ? and st.owner = ? and st.weekday = ? and st.type = 0";
		}else{
			Date date = new Date();
			String dateday = DateUtil.getDate5String(date);
			list.add(Long.parseLong(dateday));
			sql = "select * from es_yu_yue_ss_time st join es_yu_yue_ss ss on st.ssid = ss.id join es_yu_yue_servicer sr on ss.serid = sr.id join es_yu_yue_service s on ss.sid = s.id where s.caid = ? and st.owner = ? and st.weekday = ? and st.dateday >= ? group by st.dateday order by st.dateday ";
		}
		return jdbcTemplate.query(sql, list.toArray(), new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				YuYueSSTime t = new YuYueSSTime();
				t.setId(rs.getLong("st.id"));
				t.setDateday(rs.getInt("st.dateday"));
//				t.setWeeknum(rs.getInt("st.weeknum"));
				t.setWeekday(rs.getInt("st.weekday"));
				t.setTotal(rs.getInt("st.total"));
				t.setSsid(rs.getLong("st.ssid"));
				t.setOwner(rs.getLong("st.owner"));
				t.setShoure(rs.getInt("st.shoure"));
				t.setSminute(rs.getInt("st.sminute"));
				t.setEhoure(rs.getInt("st.ehoure"));
				t.setEminute(rs.getInt("st.eminute"));
				t.setSername(rs.getString("sr.name"));
				t.setServicename(rs.getString("s.name"));
				return t;
			}
		});
	}
	
	@Override
	public List<YuYueSSTime> findYuYueSSTimeByDateday(long catid,long owner,int dateday){
		String sql = "select * from es_yu_yue_ss_time st join es_yu_yue_ss ss on st.ssid = ss.id join es_yu_yue_servicer sr on ss.serid = sr.id join es_yu_yue_service s on ss.sid = s.id where s.caid = ? and st.owner = ? and st.dateday = ? and st.type = 1";
		Object[] params = {catid,owner,dateday};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				YuYueSSTime t = new YuYueSSTime();
				t.setId(rs.getLong("st.id"));
				t.setDateday(rs.getInt("st.dateday"));
//				t.setWeeknum(rs.getInt("st.weeknum"));
				t.setWeekday(rs.getInt("st.weekday"));
				t.setTotal(rs.getInt("st.total"));
				t.setSsid(rs.getLong("st.ssid"));
				t.setOwner(rs.getLong("st.owner"));
				t.setShoure(rs.getInt("st.shoure"));
				t.setSminute(rs.getInt("st.sminute"));
				t.setEhoure(rs.getInt("st.ehoure"));
				t.setEminute(rs.getInt("st.eminute"));
				t.setSername(rs.getString("sr.name"));
				t.setServicename(rs.getString("s.name"));
				return t;
			}
		});
		
	}
	
	
	@Override
	public YuYueSS findYuYueSSBySsid(long ssid)
	{
		String sql = "select * from es_yu_yue_ss ss left join es_yu_yue_servicer sr on ss.serid = sr.id where ss.id = ?";
		Object[] params = {ssid};
		List<YuYueSS> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				YuYueSS ss = new YuYueSS();
				ss.setId(rs.getLong("ss.id"));
				ss.setCaid(rs.getLong("sr.caid"));
				ss.setSerid(rs.getLong("ss.serid"));
				ss.setSid(rs.getLong("ss.sid"));
				return ss;
			}
			
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int saveYuYueSSTime(YuYueSSTime yuYueSSTime, long owner)
	{
		String sql = "insert into es_yu_yue_ss_time(dateday,weekday,total,ssid,owner,shoure,sminute,ehoure,eminute,type,starttime,endtime) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {yuYueSSTime.getDateday(),yuYueSSTime.getWeekday(),yuYueSSTime.getTotal(),yuYueSSTime.getSsid(),owner,yuYueSSTime.getShoure(),yuYueSSTime.getSminute(),yuYueSSTime.getEhoure(),yuYueSSTime.getEminute(),yuYueSSTime.getType(),yuYueSSTime.getStarttime(),yuYueSSTime.getEndtime()};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public YuYueSSTime findYuYueSSTimeById(long id, long ssid)
	{
		String sql = "select * from es_yu_yue_ss_time where ssid = ? and id = ?";
		Object[] params = {ssid,id};
		List<YuYueSSTime> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				YuYueSSTime t = new YuYueSSTime();
				t.setId(rs.getLong("id"));
				t.setDateday(rs.getInt("dateday"));
//				t.setWeeknum(rs.getInt("weeknum"));
				t.setWeekday(rs.getInt("weekday"));
				t.setTotal(rs.getInt("total"));
				t.setSsid(rs.getLong("ssid"));
				t.setOwner(rs.getLong("owner"));
				t.setShoure(rs.getInt("shoure"));
				t.setSminute(rs.getInt("sminute"));
				t.setEhoure(rs.getInt("ehoure"));
				t.setEminute(rs.getInt("eminute"));
				t.setStarttime(rs.getTimestamp("starttime"));
				t.setEndtime(rs.getTimestamp("endtime"));
				return t;
			}
			
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int updateYuYueSSTime(YuYueSSTime yuYueSSTime)
	{
		String sql = "update es_yu_yue_ss_time set dateday=?,weekday=?,total=?,ssid=?,owner=?,shoure=?,sminute=?,ehoure=?,eminute=?,starttime=?,endtime=?,type=? where id = ?";
		Object[] params = {yuYueSSTime.getDateday(),yuYueSSTime.getWeekday(),yuYueSSTime.getTotal(),yuYueSSTime.getSsid(),yuYueSSTime.getOwner(),yuYueSSTime.getShoure(),yuYueSSTime.getSminute(),yuYueSSTime.getEhoure(),yuYueSSTime.getEminute(),yuYueSSTime.getStarttime(),yuYueSSTime.getEndtime(),yuYueSSTime.getType(),yuYueSSTime.getId()};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int delYuYueSSTime(long id, long ssid)
	{
		String sql = "delete from es_yu_yue_ss_time where id = ? and ssid = ?";
		Object[] params = {id,ssid};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public List<YuYueTag> findYuYueTagList(long yyid,int start,int size)
	{
		String sql = "select * from es_yu_yue_tag where yyid = ? order by id desc limit ?,?";
		Object[] params = {yyid,start,size};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				YuYueTag tag = new YuYueTag();
				tag.setId(rs.getLong("id"));
				tag.setName(rs.getString("name"));
				tag.setYyid(rs.getLong("yyid"));
				return tag;
			}
			
		});
	}

	@Override
	public long saveYuYueTag(final YuYueTag yuYueTag)
	{

		final String sql = "insert into es_yu_yue_tag(name,yyid) values(?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator()
		{
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(sql, new String[]
				{ "id" });
				ps.setString(1, yuYueTag.getName());
				ps.setLong(2, yuYueTag.getYyid());
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	@Override
	public int findTotalYuYueTag(long yyid)
	{
		String sql = "select count(id) from es_yu_yue_tag where yyid = ?";
		Object[] params = {yyid};
		return jdbcTemplate.queryForInt(sql, params);
	}

	@Override
	public long delYuYueTag(long yyid, long id)
	{
		String sql = "delete from es_yu_yue_tag where id = ? and yyid = ?";
		Object[] params = {id,yyid};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public List<YuYueTag> findYuYueTagListBySerid(long serid)
	{
		String sql = "select * from es_yu_yue_servicer_tag st join es_yu_yue_tag t on st.tid = t.id where st.serid = ? order by st.id asc";
		Object[] params = {serid};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				YuYueTag tag = new YuYueTag();
				tag.setId(rs.getLong("id"));
				tag.setName(rs.getString("name"));
				tag.setYyid(rs.getLong("yyid"));
				return tag;
			}
			
		});
	}

	@Override
	public long saveYuYueServicerTag(long serid, long tagid)
	{
		String sql = "insert ignore into es_yu_yue_servicer_tag(serid,tid) values(?,?)";
		Object[] params = {serid,tagid};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int findTotalYuYueRecord(long owner,String status,Date startTime,Date endTime,Date yytime)
	{
		String sql = "select count(r.id) from es_yu_yue_record r join es_wx_user u on r.wxuid = u.id join es_yu_yue_ss_time st on r.stid = st.id where st.owner = ?";
		List<Object> list = new ArrayList<Object>();
		list.add(owner);
		if (!"ALL".equals(status)) {
			sql += " and r.status = ? ";
			list.add(status);
		}else{
			sql += " and r.status != 'DEL' ";
		}
		if (startTime != null) {
			sql += " and r.yytime > ? ";
			list.add(startTime);
		}
		if (endTime != null) {
			sql += " and r.yytime < ? ";
			list.add(endTime);
		}
		if(yytime != null){
			sql += " and date(r.yytime) = date(?)";
			list.add(yytime);
		}
		return jdbcTemplate.queryForInt(sql, list.toArray());
	}
	
	@Override
	public int findTotalYuYueRecord(long owner,String status){
		String sql = "select count(r.id) from es_yu_yue_record r join es_wx_user u on r.wxuid = u.id join es_yu_yue_ss_time st on r.stid = st.id where st.owner = ? and r.status != 'DEL' and date(r.yytime) < date(now())";
		Object[] params = {owner};
		return jdbcTemplate.queryForInt(sql, params);
	}

	@Override
	public List<YuYueRecord> findYuYueRecordList(long owner,String status,int start,int size){
		String sql = "select * from es_yu_yue_record r join es_wx_user u on r.wxuid = u.id join es_yu_yue_ss_time st on r.stid = st.id where st.owner = ? and r.status != 'DEL' and date(r.yytime) < date(now()) limit ?,?";
		Object[] params = {owner,start,size};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				YuYueRecord r = new YuYueRecord();
				r.setId(rs.getLong("r.id"));
				r.setWxuid(rs.getLong("r.wxuid"));
				r.setNickname(rs.getString("u.nickname"));
				r.setCreatetime(rs.getTimestamp("r.createtime"));
				r.setIp(rs.getString("r.ip"));
				r.setShoure(rs.getInt("st.shoure"));
				r.setSminute(rs.getInt("st.sminute"));
				r.setEhoure(rs.getInt("st.ehoure"));
				r.setEminute(rs.getInt("st.eminute"));
				r.setSername(rs.getString("r.sername"));
				r.setServicename(rs.getString("r.servicename"));
				r.setYytime(rs.getTimestamp("r.yytime"));
				r.setStatus(rs.getString("r.status"));
				r.setCatname(rs.getString("r.catname"));
				r.setHydesc(rs.getString("r.hydesc"));
				return r;
			}
			
			
		});
	}
	
	@Override
	public List<YuYueRecord> findYuYueRecordList(long owner,String status,Date startTime,Date endTime,Date yytime, int start, int size)
	{
		String sql = "select * from es_yu_yue_record r join es_wx_user u on r.wxuid = u.id join es_yu_yue_ss_time st on r.stid = st.id where st.owner = ? ";
		List<Object> list = new ArrayList<Object>();
		list.add(owner);
		if (!"ALL".equals(status)) {
			sql += " and r.status = ? ";
			list.add(status);
		}else{
			sql += " and r.status != 'DEL' ";
		}
		if (startTime != null) {
			sql += " and r.yytime > ? ";
			list.add(startTime);
		}
		if (endTime != null) {
			sql += " and r.yytime < ? ";
			list.add(endTime);
		}
		if(yytime != null){
			sql += " and date(r.yytime) = date(?)";
			list.add(yytime);
		}
		sql += " order by r.yytime desc limit ?,?";
		list.add(start);list.add(size);
		return jdbcTemplate.query(sql, list.toArray(), new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				YuYueRecord r = new YuYueRecord();
				r.setId(rs.getLong("r.id"));
				r.setWxuid(rs.getLong("r.wxuid"));
				r.setNickname(rs.getString("u.nickname"));
				r.setCreatetime(rs.getTimestamp("r.createtime"));
				r.setShoure(rs.getInt("st.shoure"));
				r.setSminute(rs.getInt("st.sminute"));
				r.setEhoure(rs.getInt("st.ehoure"));
				r.setEminute(rs.getInt("st.eminute"));
				r.setIp(rs.getString("r.ip"));
				r.setSername(rs.getString("r.sername"));
				r.setServicename(rs.getString("r.servicename"));
				r.setYytime(rs.getTimestamp("r.yytime"));
				r.setStatus(rs.getString("r.status"));
				r.setCatname(rs.getString("r.catname"));
				r.setHydesc(rs.getString("r.hydesc"));
				return r;
			}
			
			
		});
	}
	
	@Override
	public List<YuYueRecord> findYuYueRecordList(long owner,String status,Date yytime){
		String sql = "select r.*,u.nickname from es_yu_yue_record r join es_wx_user u on r.wxuid = u.id join es_yu_yue_ss_time st on r.stid = st.id where st.owner = ? ";
		List<Object> list = new ArrayList<Object>();
		list.add(owner);
		if (!"ALL".equals(status)) {
			sql += " and r.status = ? ";
			list.add(status);
		}else{
			sql += " and r.status != 'DEL' ";
		}
		if(yytime != null){
			sql += " and date(r.yytime) = date(?)";
			list.add(yytime);
		}
		sql += " order by r.yytime ";
		return jdbcTemplate.query(sql, list.toArray(), new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				YuYueRecord r = new YuYueRecord();
				r.setId(rs.getLong("r.id"));
				r.setWxuid(rs.getLong("r.wxuid"));
				r.setNickname(rs.getString("u.nickname"));
				r.setCreatetime(rs.getTimestamp("r.createtime"));
				r.setIp(rs.getString("r.ip"));
				r.setSername(rs.getString("r.sername"));
				r.setServicename(rs.getString("r.servicename"));
				r.setYytime(rs.getTimestamp("r.yytime"));
				r.setStatus(rs.getString("r.status"));
				r.setHydesc(rs.getString("r.hydesc"));
				r.setTag1(rs.getString("r.tag1"));
				r.setTag2(rs.getString("r.tag2"));
				return r;
			}
			
			
		});
	}
	
	@Override
	public int findTotalYuYueServicerByCatid(long catid, long yyid)
	{
		String sql = "select count(id) from es_yu_yue_servicer where caid = ? and yyid = ? and type != 1";
		Object[] params = {catid,yyid};
		return jdbcTemplate.queryForInt(sql, params);
	}

	@Override
	public List<YuYueServicer> findYuYueServicerListByCatid(long catid, long yyid, int start, int size)
	{
		String sql = "select * from es_yu_yue_servicer where caid = ? and yyid = ? and type != 1 order by top desc,idx desc limit ?,?";
		Object[] params = {catid,yyid,start,size};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				YuYueServicer s = new YuYueServicer();
				s.setId(rs.getLong("id"));
				s.setName(rs.getString("name"));
				s.setHydesc(rs.getString("hydesc"));
				s.setHyldesc(rs.getString("hyldesc"));
				s.setYyid(rs.getLong("yyid"));
				s.setCaid(rs.getLong("caid"));
				s.setImg(rs.getString("img"));
				s.setSimg(rs.getString("simg"));
				s.setTop(rs.getInt("top"));
				return s;
			}
			
		});
	}
	
	@Override
	public List<YuYueServicer> findYuYueServicerListByCatid(long catid, long yyid){
		String sql = "select * from es_yu_yue_servicer where caid = ? and yyid = ? and type != 1 order by top desc,idx desc";
		Object[] params = {catid,yyid};
		List<YuYueServicer> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				YuYueServicer s = new YuYueServicer();
				s.setId(rs.getLong("id"));
				s.setName(rs.getString("name"));
				s.setHydesc(rs.getString("hydesc"));
				s.setHyldesc(rs.getString("hyldesc"));
				s.setYyid(rs.getLong("yyid"));
				s.setCaid(rs.getLong("caid"));
				s.setImg(rs.getString("img"));
				s.setSimg(rs.getString("simg"));
				return s;
			}
			
		});
		if(list != null && list.size()>0){
			return list;
		}
		return null;
	}

	@Override
	public int findTotalYuYueServiceBySerid(long serid)
	{
		String sql = "select count(ss.id) from es_yu_yue_ss ss join es_yu_yue_service s on ss.sid = s.id where ss.serid = ?";
		Object[] params = {serid};
		return jdbcTemplate.queryForInt(sql, params);
	}

	@Override
	public int yuyueTotalCatelogRecord(long owner, long catid,int day,String status,Date startTime,Date endTime)
	{
		String sql = "";
		if(day > 0){
			sql = "select count(r.id) from es_yu_yue_record r join es_wx_user u on r.wxuid = u.id join es_yu_yue_ss_time st on r.stid = st.id where st.owner = ? and r.catid = ? and date(r.createtime) = curdate()";
		}else{
			sql = "select count(r.id) from es_yu_yue_record r join es_wx_user u on r.wxuid = u.id join es_yu_yue_ss_time st on r.stid = st.id where st.owner = ? and r.catid = ?";
		}
		List<Object> list = new ArrayList<Object>();
		list.add(owner);list.add(catid);
		if (!"ALL".equals(status)) {
			sql += " and r.status = ? ";
			list.add(status);
		}else{
			sql += " and r.status != 'DEL' ";
		}
		if (startTime != null) {
			sql += " and r.yytime > ? ";
			list.add(startTime);
		}
		if (endTime != null) {
			sql += " and r.yytime < ? ";
			list.add(endTime);
		}
		
		return jdbcTemplate.queryForInt(sql, list.toArray());
	}

	@Override
	public List<YuYueRecord> yuyueCatelogRecord(long owner, long catid,int day,String status,Date startTime,Date endTime, int start, int size)
	{	
		String sql = "";
		if(day > 0){
			sql = "select * from es_yu_yue_record r join es_wx_user u on r.wxuid = u.id join es_yu_yue_ss_time st on r.stid = st.id where st.owner = ? and r.catid = ? and date(r.createtime) = curdate() ";
		}else{
			sql = "select * from es_yu_yue_record r join es_wx_user u on r.wxuid = u.id join es_yu_yue_ss_time st on r.stid = st.id where st.owner = ? and r.catid = ? ";
		}
		List<Object> list = new ArrayList<Object>();
		list.add(owner);list.add(catid);
		if (!"ALL".equals(status)) {
			sql += " and r.status = ? ";
			list.add(status);
		}else{
			sql += " and r.status != 'DEL' ";
		}
		if (startTime != null) {
			sql += " and r.yytime > ? ";
			list.add(startTime);
		}
		if (endTime != null) {
			sql += " and r.yytime < ? ";
			list.add(endTime);
		}
		sql += " order by r.yytime desc limit ?,?";
		list.add(start);list.add(size);
		return jdbcTemplate.query(sql, list.toArray(), new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				YuYueRecord r = new YuYueRecord();
				r.setId(rs.getLong("r.id"));
				r.setWxuid(rs.getLong("r.wxuid"));
				r.setNickname(rs.getString("u.nickname"));
				r.setCreatetime(rs.getTimestamp("r.createtime"));
				r.setIp(rs.getString("r.ip"));
				r.setShoure(rs.getInt("st.shoure"));
				r.setSminute(rs.getInt("st.sminute"));
				r.setEhoure(rs.getInt("st.ehoure"));
				r.setEminute(rs.getInt("st.eminute"));
				r.setSername(rs.getString("r.sername"));
				r.setServicename(rs.getString("r.servicename"));
				r.setYytime(rs.getTimestamp("r.yytime"));
				r.setStatus(rs.getString("r.status"));
				r.setCatname(rs.getString("r.catname"));
				r.setHydesc(rs.getString("r.hydesc"));
				return r;
			}
			
			
		});
	}
	
	@Override
	public YuYueRecord findYuYueRecordById(long owner,long recordid){
		String sql = "select r.id,u.nickname,u.openid,yc.yzpageid from es_yu_yue_record r join es_wx_user u on r.wxuid = u.id join es_yu_yue_ss_time st on r.stid = st.id join es_yu_yue_catalog yc on yc.id = r.catid where st.owner = ? and r.id = ? ";
		Object[] params = {owner,recordid};
		List<YuYueRecord> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				YuYueRecord r = new YuYueRecord();
				r.setId(rs.getLong("r.id"));
				r.setNickname(rs.getString("u.nickname"));
				r.setOpenid(rs.getString("u.openid"));
				r.setYzpageid(rs.getLong("yc.yzpageid"));
				return r;
			}
		});
		if(list != null && list.size()> 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public long findYuYueTag(YuYueTag yuYueTag)
	{
		String sql = "select id from es_yu_yue_tag where name = ? and yyid = ?";
		Object[] params = {yuYueTag.getName(),yuYueTag.getYyid()};
		List<Long> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				return rs.getLong("id");
			}
			
		});
		if(list!= null && list.size()>0){
			return list.get(0);
		}
		return 0;
	}

	@Override
	public int delYuYueServicerTag(long serid)
	{
		String sql = "delete from es_yu_yue_servicer_tag where serid = ?";
		Object[] params = {serid};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public long findYuYueSS(long serviceid, long serid)
	{
		String sql = "select id from es_yu_yue_ss where sid = ? and serid = ?";
		Object[] params = {serviceid,serid};
		List<Long> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				return rs.getLong("id");
			}
			
		});
		if(list!= null && list.size()>0){
			return list.get(0);
		}
		return 0;
	}

	@Override
	public List<YuYueSSTime> findYuYueSSTimeByDateday(long owner, int dateday, long ssid)
	{
		String sql = "select * from es_yu_yue_ss_time st where st.owner = ? and st.dateday = ? and st.ssid = ? and st.type = 1";
		Object[] params = {owner,dateday,ssid};
		
		List<YuYueSSTime> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				YuYueSSTime t = new YuYueSSTime();
				t.setId(rs.getLong("st.id"));
				t.setDateday(rs.getInt("st.dateday"));
				t.setWeekday(rs.getInt("st.weekday"));
				t.setTotal(rs.getInt("st.total"));
				t.setSsid(rs.getLong("st.ssid"));
				t.setOwner(rs.getLong("st.owner"));
				t.setShoure(rs.getInt("st.shoure"));
				t.setSminute(rs.getInt("st.sminute"));
				t.setEhoure(rs.getInt("st.ehoure"));
				t.setEminute(rs.getInt("st.eminute"));
				return t;
			}
		});
		if(list != null && list.size()>0){
			return list;
		}
		return null;
	}

	@Override
	public List<YuYueSSTime> findYuYueSSTimeByWeekday(long owner, int weekday, long ssid)
	{
		String sql = "select * from es_yu_yue_ss_time st where st.owner = ? and st.weekday = ? and st.ssid = ? and st.type = 0";
		Object[] params = {owner,weekday,ssid};
		List<YuYueSSTime> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				YuYueSSTime t = new YuYueSSTime();
				t.setId(rs.getLong("st.id"));
				t.setDateday(rs.getInt("st.dateday"));
				t.setWeekday(rs.getInt("st.weekday"));
				t.setTotal(rs.getInt("st.total"));
				t.setSsid(rs.getLong("st.ssid"));
				t.setOwner(rs.getLong("st.owner"));
				t.setShoure(rs.getInt("st.shoure"));
				t.setSminute(rs.getInt("st.sminute"));
				t.setEhoure(rs.getInt("st.ehoure"));
				t.setEminute(rs.getInt("st.eminute"));
				return t;
			}
		});
		if(list.size()>0){
			return list;
		}
		return null;
	}

	@Override
	public long findYuYueServicerByCatidAndServicerid(long catid, long serviceid)
	{
		String sql = "select serid from es_yu_yue_ss where sid = ?";
		Object[] params = {serviceid};
		List<Long> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				return rs.getLong("serid");
			}
			
		});
		if(list!= null && list.size()>0){
			return list.get(0);
		}
		return 0;
	}

	@Override
	public long findYuYueServiceBySerid(long serid)
	{
		String sql = "select sid from es_yu_yue_ss where serid = ?";
		Object[] params = {serid};
		List<Long> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				return rs.getLong("sid");
			}
			
		});
		if(list!= null && list.size()>0){
			return list.get(0);
		}
		return 0;
	}
	
	@Override
	public int findCatalogMaxIdx(long yyid)
	{
		try
		{
			return jdbcTemplate.queryForInt("select max(idx) from es_yu_yue_catalog where yyid=?", new Object[]{yyid});
		} catch (DataAccessException e)
		{
			return 0;
		}
	}
	
	@Override
	public void updateCataLogIdxForDel(long yyid, int idx)
	{
		jdbcTemplate.update("update es_yu_yue_catalog set idx=idx-1 where yyid=? and idx>?", new Object[]{yyid,idx});
	}
	
	@Override
	public YuYueCatalog findFrontCatalog(long catid)
	{
		List<YuYueCatalog> list=jdbcTemplate.query("select * from es_yu_yue_catalog c where idx>(select idx from es_yu_yue_catalog where id=?) and yyid=(select yyid from es_yu_yue_catalog where id=?) order by idx asc limit 1", new Object[]{catid,catid}, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				YuYueCatalog c = new YuYueCatalog();
				c.setId(rs.getLong("c.id"));
				c.setName(rs.getString("c.name"));
				c.setHydesc(rs.getString("c.hydesc"));
				c.setHyldesc(rs.getString("c.hyldesc"));
				c.setYyid(rs.getLong("c.yyid"));
				c.setImg(rs.getString("c.img"));
				c.setSpageid(rs.getLong("c.spageid"));
				c.setXpageid(rs.getLong("c.xpageid"));
				c.setIdx(rs.getInt("c.idx"));
				return c;
			}
		});
		return list.size()>0?list.get(0):null;
	}
	
	@Override
	public YuYueCatalog findNextCatalog(long catid)
	{
		List<YuYueCatalog> list=jdbcTemplate.query("select * from es_yu_yue_catalog c where idx<(select idx from es_yu_yue_catalog where id=?) and yyid=(select yyid from es_yu_yue_catalog where id=?) order by idx desc limit 1", new Object[]{catid,catid}, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				YuYueCatalog c = new YuYueCatalog();
				c.setId(rs.getLong("c.id"));
				c.setName(rs.getString("c.name"));
				c.setHydesc(rs.getString("c.hydesc"));
				c.setHyldesc(rs.getString("c.hyldesc"));
				c.setYyid(rs.getLong("c.yyid"));
				c.setImg(rs.getString("c.img"));
				c.setSpageid(rs.getLong("c.spageid"));
				c.setXpageid(rs.getLong("c.xpageid"));
				c.setIdx(rs.getInt("c.idx"));
				return c;
			}
		});
		return list.size()>0?list.get(0):null;
	}
	
	@Override
	public int updateCatelogIdx(long id, int idx)
	{
		return jdbcTemplate.update("update es_yu_yue_catalog set idx=? where id=?", new Object[]{idx,id});
	}
	
	@Override
	public YuYueServicer findFrontServicer(int current,int top,long caid,long yyid)
	{
		String sql = "select * from es_yu_yue_servicer where idx>? and caid=? and top=? and yyid=? order by idx asc limit 1";
		Object[] params = {current,caid,top,yyid};
		List<YuYueServicer> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				YuYueServicer ser = new YuYueServicer();
				ser.setId(rs.getLong("id"));
				ser.setHydesc(rs.getString("hydesc"));
				ser.setHyldesc(rs.getString("hyldesc"));
				ser.setImg(rs.getString("img"));
				ser.setSimg(rs.getString("simg"));
				ser.setCaid(rs.getLong("caid"));
				ser.setName(rs.getString("name"));
				ser.setYyid(rs.getLong("yyid"));
				ser.setIdx(rs.getInt("idx"));
				return ser;
			}
			
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	@Override
	public YuYueServicer findNextServicer(int idx,int top,long caid,long yyid)
	{
		String sql = "select * from es_yu_yue_servicer where idx<? and caid=? and top=? and yyid=? order by idx desc limit 1";
		Object[] params = {idx,caid,top,yyid};
		List<YuYueServicer> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				YuYueServicer ser = new YuYueServicer();
				ser.setId(rs.getLong("id"));
				ser.setHydesc(rs.getString("hydesc"));
				ser.setHyldesc(rs.getString("hyldesc"));
				ser.setImg(rs.getString("img"));
				ser.setSimg(rs.getString("simg"));
				ser.setCaid(rs.getLong("caid"));
				ser.setName(rs.getString("name"));
				ser.setYyid(rs.getLong("yyid"));
				ser.setIdx(rs.getInt("idx"));
				return ser;
			}
			
		});
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	@Override
	public int findServicerMaxIdx(long catid)
	{
		try
		{
			return jdbcTemplate.queryForInt("select max(idx) from es_yu_yue_servicer where caid=?", new Object[]{catid});
		} catch (DataAccessException e)
		{
			return 0;
		}
	}
	@Override
	public int updateServicerIdx(long serid, int idx)
	{
		return jdbcTemplate.update("update es_yu_yue_servicer set idx=? where id=?", new Object[]{idx,serid});
	}
	@Override
	public void updateServicerIdxForDel(long catid, int idx)
	{
		jdbcTemplate.update("update es_yu_yue_servicer set idx=idx-1 where caid=? and idx>?", new Object[]{catid,idx});		
	}
	
	@Override
	public YuYueService findFrontService(long seid,long caid,long yyid)
	{
		String sql = "select * from es_yu_yue_service where idx>(select idx from es_yu_yue_service where id=?) and caid=? and yyid=? order by idx asc limit 1";
		Object[] params = {seid,caid,yyid};
		List<YuYueService> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				YuYueService s = new YuYueService();
				s.setId(rs.getLong("id"));
				s.setIdx(rs.getInt("idx"));
				return s;
			}
			
		});
		return list.size()>0?list.get(0):null;
	}
	
	
	@Override
	public YuYueService findNextService(long seid,long caid,long yyid)
	{
		String sql = "select * from es_yu_yue_service where idx<(select idx from es_yu_yue_service where id=?) and caid=? and yyid=? order by idx desc limit 1";
		Object[] params = {seid,caid,yyid};
		List<YuYueService> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				YuYueService s = new YuYueService();
				s.setId(rs.getLong("id"));
				s.setIdx(rs.getInt("idx"));
				return s;
			}
			
		});
		return list.size()>0?list.get(0):null;
	}
	@Override
	public int findServiceMaxIdx(long catid,long yyid)
	{
		return jdbcTemplate.queryForInt("select max(idx) from es_yu_yue_service where caid=? and yyid=?", new Object[]{catid,yyid});
	}
	@Override
	public int updateServiceIdx(long seid, int idx,long yyid)
	{
		return jdbcTemplate.update("update es_yu_yue_service set idx=? where id=? and yyid=?", new Object[]{idx,seid,yyid});
	}
	@Override
	public void updateServiceIdxForDel(long catid, int idx,long yyid)
	{
		jdbcTemplate.update("update es_yu_yue_service set idx=idx-1 where idx>? and  caid=? and yyid=?", new Object[]{idx,catid,yyid});		
	}

	@Override
	public int findTotalYuYueSSTimeByTime(long catid,long owner){
		String sql = "select count(st.id) from es_yu_yue_ss_time st join es_yu_yue_ss ss on st.ssid = ss.id join es_yu_yue_servicer sr on ss.serid = sr.id join es_yu_yue_service s on ss.sid = s.id where s.caid = ? and st.owner = ? and st.type = 2";
		Object[] params = {catid,owner};
		return jdbcTemplate.queryForInt(sql, params);
	}
	
	@Override
	public List<YuYueSSTime> findYuYueSSTimeByTime(long catid, long owner,int start,int size)
	{
		String sql = "select * from es_yu_yue_ss_time st join es_yu_yue_ss ss on st.ssid = ss.id join es_yu_yue_servicer sr on ss.serid = sr.id join es_yu_yue_service s on ss.sid = s.id where s.caid = ? and st.owner = ? and st.type = 2 limit ?,?";
		Object[] params = {catid,owner,start,size};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				YuYueSSTime t = new YuYueSSTime();
				t.setId(rs.getLong("st.id"));
				t.setDateday(rs.getInt("st.dateday"));
				t.setWeekday(rs.getInt("st.weekday"));
				t.setTotal(rs.getInt("st.total"));
				t.setSsid(rs.getLong("st.ssid"));
				t.setOwner(rs.getLong("st.owner"));
				t.setShoure(rs.getInt("st.shoure"));
				t.setSminute(rs.getInt("st.sminute"));
				t.setEhoure(rs.getInt("st.ehoure"));
				t.setEminute(rs.getInt("st.eminute"));
				t.setSername(rs.getString("sr.name"));
				t.setServicename(rs.getString("s.name"));
				t.setStarttime(rs.getTimestamp("st.starttime"));
				t.setEndtime(rs.getTimestamp("st.endtime"));
				return t;
			}
		});
	}

	@Override
	public int findTotalYuYueNumByServiceid(long serviceid)
	{
		String sql = "select count(r.id) from es_yu_yue_record r join es_yu_yue_ss_time st on r.stid = st.id join es_yu_yue_ss ss on st.ssid = ss.id join es_yu_yue_service s on ss.sid = s.id where ss.sid = ?";
		Object[] params = {serviceid};
		return jdbcTemplate.queryForInt(sql, params);
	}

	@Override
	public long updateYuYueCatalogType(long catid, String type)
	{
		String sql = "update es_yu_yue_catalog set type = ? where id = ? ";
		Object[] params = {type,catid};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public List<YuYueSSTime> findYuYueSSTimeByTimeToTime(long owner, Date yytime, long ssid)
	{
		String sql = "select * from es_yu_yue_ss_time st where (st.starttime is null or st.starttime <= ?) and (st.endtime is null or st.endtime >= ?) and  st.owner = ? and st.type = 2 and st.ssid = ? ";
		Object[] params = {yytime,yytime,owner,ssid};
		List<YuYueSSTime> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				YuYueSSTime t = new YuYueSSTime();
				t.setId(rs.getLong("st.id"));
				t.setDateday(rs.getInt("st.dateday"));
				t.setWeekday(rs.getInt("st.weekday"));
				t.setTotal(rs.getInt("st.total"));
				t.setSsid(rs.getLong("st.ssid"));
				t.setOwner(rs.getLong("st.owner"));
				t.setShoure(rs.getInt("st.shoure"));
				t.setSminute(rs.getInt("st.sminute"));
				t.setEhoure(rs.getInt("st.ehoure"));
				t.setEminute(rs.getInt("st.eminute"));
				t.setStarttime(rs.getTimestamp("st.starttime"));
				t.setEndtime(rs.getTimestamp("st.endtime"));
				return t;
			}
		});
		if(list.size()>0){
			return list;
		}
		return null;
	}

	@Override
	public int findYuYueSSTimeUsedBySSid(long ssid,long owner)
	{
		String sql = "select sum(used) from es_yu_yue_ss_time_used where ssid = ? and owner = ?";
		Object[] params = {ssid,owner};
		return jdbcTemplate.queryForInt(sql, params);
	}

	@Override
	public int updateYYRecord(long recordid, Date yytime)
	{
		String sql = "update es_yu_yue_record set yytime = ?,status='CMP' where id = ?";
		Object[] params = {yytime,recordid};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int delYYRecord(long recordid)
	{
		String sql = "update es_yu_yue_record set status='DEL' where id = ?";
		Object[] params = {recordid};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public long findPageIdBySiteid(long siteid, String type)
	{
		String sql = "select p.id from es_page p where p.siteid=? and (p.relationid is null and p.contextid is null) and p.status !='DEL' and p.apptype = ?";
		Object[] params = {siteid,type};
		List<Long> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				return rs.getLong("p.id");
			}
			
		});
		if(list!= null && list.size()>0){
			return list.get(0);
		}
		return 0;
	}

	@Override
	public int updateServiceTop(long serid, long yyid, int top) {
		return jdbcTemplate.update("update es_yu_yue_servicer set top=? where id=? and yyid=?", new Object[]{top,serid,yyid});
	}

	@Override
	public long findServicerIdByCatid(long catid, long yyid)
	{
		String sql = "select s.id from es_yu_yue_servicer s where s.caid=? and s.yyid = ? and s.type = 1";
		Object[] params = {catid,yyid};
		List<Long> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				return rs.getLong("s.id");
			}
			
		});
		if(list!= null && list.size()>0){
			return list.get(0);
		}
		return 0;
	}

	@Override
	public List<YuYueServicer> findYuYueServicerListById(long catid, long serviceid)
	{
		String sql = "select * from es_yu_yue_servicer s join es_yu_yue_ss ss on ss.serid = s.id where ss.sid = ? and s.caid = ?";
		Object[] params = {serviceid,catid};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				YuYueServicer ser = new YuYueServicer();
				ser.setId(rs.getLong("s.id"));
				ser.setName(rs.getString("s.name"));
				return ser;
			}
		});
	}

	@Override
	public List<YuYueService> findServiceListBySerid(long catid, long serid)
	{
		String sql = "select * from es_yu_yue_service s join es_yu_yue_ss ss on ss.sid = s.id where ss.serid = ? and s.caid = ?";
		Object[] params = {serid,catid};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				YuYueService ser = new YuYueService();
				ser.setId(rs.getLong("s.id"));
				ser.setName(rs.getString("s.name"));
				return ser;
			}
		});
	}

	@Override
	public List<YuYueRecord> findyyRecord(long owner, String status,int start,int size)
	{
		String sql = "select r.yytime from es_yu_yue_record r join es_yu_yue_ss_time st on r.stid = st.id where st.owner = ?";
		List<Object> list = new ArrayList<Object>();
		list.add(owner);
		if (!"ALL".equals(status)) {
			sql += " and r.status = ? ";
			list.add(status);
		}else{
			sql += " and r.status != 'DEL' ";
		}
		sql += " group by date(r.yytime) order by r.yytime desc limit ?,?";
		list.add(start);list.add(size);
		return jdbcTemplate.query(sql, list.toArray(), new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				YuYueRecord r = new YuYueRecord();
				r.setYytime(rs.getTimestamp("r.yytime"));
				return r;
			}
			
		});
	}

	@Override
	public YuYueRecord findYuYueRecordById(long id)
	{
		String sql = "select sername,servicename,yytime,status,hydesc,tag1,tag2 from es_yu_yue_record where id = ? ";
		Object[] params = {id};
		List<YuYueRecord> list = jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				YuYueRecord r = new YuYueRecord();
				r.setSername(rs.getString("sername"));
				r.setServicename(rs.getString("servicename"));
				r.setYytime(rs.getTimestamp("yytime"));
				r.setStatus(rs.getString("status"));
				r.setHydesc(rs.getString("hydesc"));
				r.setTag1(rs.getString("tag1"));
				r.setTag2(rs.getString("tag2"));
				return r;
			}
			
		}); 
		if(list !=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
