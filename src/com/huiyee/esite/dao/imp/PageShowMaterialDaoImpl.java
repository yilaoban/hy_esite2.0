package com.huiyee.esite.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.IPageShowMaterialDao;
import com.huiyee.interact.cb.model.CbActivityMatter;
import com.huiyee.weixin.model.WxPageShow;


public class PageShowMaterialDaoImpl implements IPageShowMaterialDao
{
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int findTotalWxPageShowByAid(long cbid,long aid)
	{
		String sql = "select count(m.id) from esite.es_interact_cb_activity_matter m join es_page p on m.pageid = p.id where m.aid = ? and m.del_tag != 'Y' and m.cbid=? and p.status != 'DEL'";
		Object[] params = {aid,cbid};
		return jdbcTemplate.queryForInt(sql,params);
	}

	@Override
	public List<CbActivityMatter> findWxPageShowListByAid(long cbid,long aid, int start, int size)
	{
		String sql = "select * from esite.es_interact_cb_activity_matter m join es_page p on m.pageid = p.id where m.aid = ? and m.del_tag != 'Y' and m.cbid=? and p.status != 'DEL' order by m.createtime limit ?,?";
		Object[] params = {aid,cbid,start,size};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				CbActivityMatter cam = new CbActivityMatter();
				cam.setId(rs.getLong("id"));
				cam.setCbid(rs.getLong("cbid"));
				cam.setAid(rs.getLong("aid"));
				cam.setPageid(rs.getLong("pageid"));
				cam.setWxshareid(rs.getLong("wxshareid"));
				cam.setUpdatetime(rs.getTimestamp("updatetime"));
				cam.setCreatetime(rs.getTimestamp("createtime"));
				cam.setDel_tag(rs.getString("del_tag"));
				cam.setKv(rs.getString("kv"));
				return cam;
			}
			
		});
	}
	
	@Override
	public int findTotalWxPageShowByOwnerid(long ownerid,String title)
	{
		String sql = "select count(s.id) from esite.es_wx_page_show s join es_page p on s.pageid = p.id where s.ownerid = ? and p.status != 'DEL'";
		if(StringUtils.isNotBlank(title)){
			sql += " and s.title like '%"+title+"%'";
		}
		Object[] params = {ownerid};
		return jdbcTemplate.queryForInt(sql, params);
	}

	@Override
	public List<WxPageShow> findPageShowMaterialByOwnerid(long ownerid,String title,int start, int size)
	{
		String sql = "select * from esite.es_wx_page_show s join es_page p on s.pageid = p.id where s.ownerid = ? and p.status != 'DEL'";
		if(StringUtils.isNotBlank(title)){
			sql += " and s.title like '%"+title+"%'";
		}
		sql += " order by s.createtime desc limit ?,?";
		Object[] params = {ownerid,start,size};
		return jdbcTemplate.query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				WxPageShow wx = new WxPageShow();
				wx.setId(rs.getLong("s.id"));
				wx.setPageid(rs.getLong("s.pageid"));
				wx.setOwnerid(rs.getLong("s.ownerid"));
				wx.setPic(rs.getString("s.pic"));
				wx.setInfoed(rs.getString("s.infoed"));
				wx.setTitle(rs.getString("s.title"));
				wx.setDescription(rs.getString("s.description"));
				wx.setUpdateseconds(rs.getInt("s.updateseconds"));
				return wx;
			}
			
		});
	}

	@Override
	public int updatePageShowActioned(long id, String actioned)
	{
		String sql = "update esite.es_wx_page_show set actioned = ? where id = ?";
		Object[] params = {actioned,id};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int saveCbActivityMatter(long cbid, long aid, long pageid, long id,long ownerid,String stype)
	{
		String sql = "insert into esite.es_interact_cb_activity_matter(cbid,aid,pageid,wxshareid,updatetime,createtime,owner,sgtype) values(?,?,?,?,now(),now(),?,?)";
		Object[] params = {cbid,aid,pageid,id,ownerid,stype};
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int updateInteractCbActivityMatterById(long amid)
	{
		String sql = "update esite.es_interact_cb_activity_matter set del_tag = 'Y' where id = ?";
		Object[] params = {amid};
		return jdbcTemplate.update(sql, params);
	}
	
	@Override
	public int saveNewsMatter(long cbid, long aid, long pageid, long ownerid, String stype, String kv)
	{
		String sql = "insert into esite.es_interact_cb_activity_matter(cbid,aid,pageid,updatetime,createtime,owner,sgtype,kv) values(?,?,?,now(),now(),?,?,?)";
		Object[] params = {cbid,aid,pageid,ownerid,stype,kv};
		return jdbcTemplate.update(sql, params);
	}
	
}
