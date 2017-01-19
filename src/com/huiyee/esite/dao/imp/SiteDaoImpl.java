
package com.huiyee.esite.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.dao.ISiteDao;
import com.huiyee.esite.dto.SitePage;
import com.huiyee.esite.model.Module;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.Site;
import com.huiyee.esite.model.Sitegroup;
import com.huiyee.weixin.model.WxPageShow;

public class SiteDaoImpl extends AbstractDao implements ISiteDao
{

	class SiteRowmapper implements RowMapper
	{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			Site site = new Site();
			site.setId(rs.getLong("id"));
			site.setName(rs.getString("name"));
			site.setOwnerId(rs.getLong("ownerid"));
			site.setType(rs.getString("type"));
			site.setCreatetime(rs.getTimestamp("createtime"));
			site.setUpdatetime(rs.getTimestamp("updatetime"));
			site.setOnlinetime(rs.getTimestamp("onlinetime"));
			site.setStatus(rs.getString("status"));
			site.setUpWhole(rs.getString("upwhole"));
			site.setSitegroupid(rs.getLong("sitegroupid"));
			return site;
		}

	}

	private static final String FIND_PAGE_TOTAL = "select count(id) from esite.es_page where siteid = ? and status != 'DEL'";

	@Override
	public int findPageTotalBySite(long siteId)
	{
		Object[] params =
		{ siteId };
		return getJdbcTemplate().queryForInt(FIND_PAGE_TOTAL, params);
	}

	private static final String FIND_WB_NICKNAME = "select nickname from esite.es_sina_token where siteid = ? and type='Z'";

	@Override
	public String findWbNickName(long siteId)
	{
		Object[] params =
		{ siteId };
		try
		{
			return (String) getJdbcTemplate().queryForObject(FIND_WB_NICKNAME, params, String.class);
		} catch (DataAccessException e)
		{
			return null;
		}
	}

	private static final String FIND_MODULES = "select * from esite.es_module_model order by id desc";

	@Override
	public List<Module> findModules()
	{
		return getJdbcTemplate().query(FIND_MODULES, new ModuleRowmapper());
	}

	class ModuleRowmapper implements RowMapper
	{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			Module m = new Module();
			m.setId(rs.getLong("id"));
			m.setName(rs.getString("name"));
			m.setIsown(rs.getString("isown"));
			return m;
		}

	}

	@Override
	public long saveSite(final long ownerid, final String name, final String type, final long groupid)
	{
		final StringBuffer buffer = new StringBuffer();
		if (groupid > 0)
		{
			buffer.append("insert into esite.es_site(ownerid,name,type,createtime,updatetime,status,sitegroupid)values(?,?,?,now(),now(),'EDT',?)");
		} else
		{
			buffer.append("insert into esite.es_site(ownerid,name,type,createtime,updatetime,status)values(?,?,?,now(),now(),'EDT')");
		}
		final String sql = buffer.toString();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator()
		{

			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(sql, new String[]
				{ "id" });
				ps.setLong(1, ownerid);
				ps.setString(2, name);
				ps.setString(3, type);
				if (groupid > 0)
				{
					ps.setLong(4, groupid);
				}
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	private static final String DELETE_MODULE_SITE = "delete from esite.es_module_site where siteid = ?";

	@Override
	public int deleteModuleSite(long siteid)
	{
		Object[] params =
		{ siteid };
		return getJdbcTemplate().update(DELETE_MODULE_SITE, params);
	}

	private static final String SAVE_MODULE_SITE = "insert into esite.es_module_site(siteid,mmid) values (?,?)";

	@Override
	public int saveModuleSite(long mmid, long siteid)
	{
		Object[] params =
		{ siteid, mmid };
		return getJdbcTemplate().update(SAVE_MODULE_SITE, params);
	}

	private static final String FIND_SITE_BY_ID = "select * from esite.es_site where id = ?";

	@Override
	public Site findSiteByID(long siteid)
	{
		Object[] params =
		{ siteid };
		List<Site> list = getJdbcTemplate().query(FIND_SITE_BY_ID, params, new SiteRowmapper());
		if (list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}

	private static final String FIND_SITE_MODULE = "select mmid from esite.es_module_site where siteid = ?";

	@Override
	public List<Long> findSiteModule(long siteid)
	{
		Object[] params =
		{ siteid };
		try
		{
			return getJdbcTemplate().queryForList(FIND_SITE_MODULE, params, Long.class);
		} catch (Exception e)
		{
			return null;
		}
	}

	private static final String UPDATE_SITE = "update esite.es_site_group set groupName = ?  where id = ?";

	@Override
	public int updateSite(String name, long sitegroupid)
	{
		Object[] params =
		{ name, sitegroupid };
		return getJdbcTemplate().update(UPDATE_SITE, params);
	}

	private static final String DELETE_SITE = "update esite.es_site_group set status= 'DEL' where id = ?";

	@Override
	public int deleteSite(long sitegroupid)
	{
		Object[] params =
		{ sitegroupid };
		return getJdbcTemplate().update(DELETE_SITE, params);
	}

	private static final String FIND_SITE_GROUP_BY_OWNER = "select * from es_site_group where ownerid=? and status!='DEL' and stype=? order by createtime desc limit ?,?";

	@Override
	public List<Sitegroup> findSitegroupByOwner(long owner, String type, int start, int size)
	{
		Object[] params =
		{ owner, type, start, size };
		return getJdbcTemplate().query(FIND_SITE_GROUP_BY_OWNER, params, new RowMapper()
		{

			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				Sitegroup sg = new Sitegroup();
				sg.setId(rs.getLong("id"));
				sg.setGroupname(rs.getString("groupName"));
				sg.setOwnerId(rs.getLong("ownerid"));
				sg.setType(rs.getString("type"));
				sg.setCreatetime(rs.getTimestamp("createtime"));
				sg.setIstemplate(rs.getString("istemplate"));
				sg.setEntityid(rs.getLong("entityid"));
				sg.setUpdatetime(rs.getTimestamp("updatetime"));
				return sg;
			}
		});
	}
	
	@Override
	public List<WxPageShow> sitegroupListLink(long sitegroupid)
	{
		Object[] params ={sitegroupid};
		String sql="select * from es_wx_page_show where sitegroupid=? limit 1";
		List<WxPageShow> wxps  =getJdbcTemplate().query(sql, params ,new ListLinkRowmapper());
		return wxps;
	}
	
	class ListLinkRowmapper implements RowMapper
	{
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			WxPageShow  wxpg = new WxPageShow();
			wxpg.setId(rs.getLong("id"));
			wxpg.setSitegroupid(rs.getLong("sitegroupid"));
			wxpg.setPageid(rs.getLong("pageid"));
			wxpg.setOwnerid(rs.getLong("ownerid"));
			wxpg.setPic(rs.getString("pic"));
			wxpg.setInfoed(rs.getString("infoed"));
			wxpg.setTitle(rs.getString("title"));
			wxpg.setDescription(rs.getString("description"));
			wxpg.setUpdateseconds(rs.getInt("updateseconds"));
			return wxpg;
		}
	}
	
	@Override
	public Sitegroup sitegroupListbyId(long pgid)
	{
		Object[] params ={pgid};
		String sql="select g.* from es_site_group g join es_site s on g.id=s.sitegroupid where s.id=?";
		List<Sitegroup> list = getJdbcTemplate().query(sql, params, new SitegroupRowmapper());
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	private static final String FIND_SITE_GROUP_LIST_BY_OWNER = "select g.* from esite.es_site_group g  join es_site s on g.id=s.sitegroupid join es_sina_token t on t.siteid=s.id where g.status!='DEL'  and t.cid is not null and g.ownerid = ? group by g.id order by g.id desc";

	@Override
	public List<Sitegroup> findSitegroupListByOwner(long owner)
	{
		Object[] params =
		{ owner };
		return getJdbcTemplate().query(FIND_SITE_GROUP_LIST_BY_OWNER, params, new SitegroupRowmapper());
	}

	@Override
	public int findPageCountBySiteId(long siteid)
	{
		String sql = "select count(*) from es_page where siteid=? and status!='DEL' and (relationid is null and contextid is null)";
		return getJdbcTemplate().queryForInt(sql, new Object[]
		{ siteid });
	}

	private static final String FIND_SITE_LIST_BY_OWNER = "select s.* from es_site s left join es_sina_token t on t.siteid=s.id where t.cid is not null and t.type='Z' and s.ownerid = ?  order by s.id desc";

	@Override
	public List<Site> findSiteListByOwner(long owner)
	{
		Object[] params =
		{ owner };
		return getJdbcTemplate().query(FIND_SITE_LIST_BY_OWNER, params, new SiteRowmapper());
	}

	private static final String FIND_SITE_GROUP_ALL = "select * from esite.es_site_group where status!='DEL' order by hourprocesstime limit ?";

	@Override
	public List<Sitegroup> findSitegroupAll(int size)
	{
		return getJdbcTemplate().query(FIND_SITE_GROUP_ALL, new Object[]
		{ size }, new SitegroupRowmapper());
	}

	@Override
	public void updateSiteAction(long siteid, String type)
	{
		Object[] params =
		{ siteid, type };
		getJdbcTemplate().update("insert into esite.es_site_action (siteid,visitTotal,type) values (?,1,?) on duplicate key update visitTotal=visitTotal+1", params);
	}

	class SitegroupRowmapper implements RowMapper
	{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			Sitegroup sg = new Sitegroup();
			sg.setId(rs.getLong("id"));
			sg.setGroupname(rs.getString("groupname"));
			sg.setOwnerId(rs.getLong("ownerid"));
			sg.setType(rs.getString("type"));
			sg.setStype(rs.getString("stype"));
			return sg;
		}
	}

	class SitegroupNewRowmapper implements RowMapper
	{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			Sitegroup sg = new Sitegroup();
			sg.setId(rs.getLong("id"));
			sg.setGroupname(rs.getString("groupName"));
			sg.setOwnerId(rs.getLong("ownerid"));
			sg.setType(rs.getString("type"));
			sg.setCreatetime(rs.getTimestamp("createtime"));
			sg.setIstemplate(rs.getString("istemplate"));
			return sg;
		}
	}

	private static final String FIND_SITE_GROUP_COUNT_BY_OWNER = "select count(id) from es_site_group where status!='DEL' and ownerid = ? and stype=?";

	@Override
	public int findSitegroupCountByOwner(long owner, String type)
	{
		return getJdbcTemplate().queryForInt(FIND_SITE_GROUP_COUNT_BY_OWNER, new Object[]
		{ owner, type });
	}

	private static final String FIND_SITE_BY_GROUPID = "select * from esite.es_site where sitegroupid = ? and type = ? and status != 'DEL'";

	@Override
	public List<Site> findSiteByGroupId(long groupid, String type)
	{
		Object[] params =
		{ groupid, type };
		return getJdbcTemplate().query(FIND_SITE_BY_GROUPID, params, new SiteRowmapper());
	}

	private static final String FIND_SITE_LIST_ALL = "select * from esite.es_site where type = ? and ownerid=? and status != 'DEL'";

	@Override
	public List<Site> findSiteListAll(String type, long owner)
	{
		Object[] params =
		{ type, owner };
		return getJdbcTemplate().query(FIND_SITE_LIST_ALL, params, new SiteRowmapper());
	}

	private static final String ADD_SITE_GROUP = "insert into esite.es_site(name,ownerid,type,createtime,updatetime)values(?,?,?,now(),now())";

	@Override
	public int addSitegroup(String name, long ownerid, String type)
	{
		Object[] params =
		{ name, ownerid, type };
		return getJdbcTemplate().update(ADD_SITE_GROUP, params);
	}

	private static final String FIND_SITES = "select s.*,g.groupName gname from esite.es_site s left join esite.es_site_group g on g.id=s.sitegroupid where s.type = ? and s.ownerid=? and s.status != 'DEL' order by s.id desc limit ?,? ";

	@Override
	public List<Site> findSites(long ownerid, String type, int start, int size)
	{
		Object[] params =
		{ type, ownerid, start, size };
		return getJdbcTemplate().query(FIND_SITES, params, new SiteListRowmapper());
	}

	private static final String FIND_SITES_COUNT_BY_OWNER = "select count(id) from esite.es_site where type = ? and ownerid=? and status != 'DEL'";

	@Override
	public int findSiteCountByOwner(long owner, String type)
	{
		return getJdbcTemplate().queryForInt(FIND_SITES_COUNT_BY_OWNER, new Object[]
		{ type, owner });
	}

	@Override
	public int findPageWolCountByOwner(long owner)
	{
		String sql = "select count(p.id) from es_page p left join es_site s on p.siteid=s.id where s.ownerid=? and p.wol='Y'";
		return getJdbcTemplate().queryForInt(sql, new Object[]
		{ owner });
	}

	class SiteListRowmapper implements RowMapper
	{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			Site site = new Site();
			site.setId(rs.getLong("id"));
			site.setName(rs.getString("name"));
			site.setOwnerId(rs.getLong("ownerid"));
			site.setType(rs.getString("type"));
			site.setCreatetime(rs.getTimestamp("createtime"));
			site.setUpdatetime(rs.getTimestamp("updatetime"));
			site.setOnlinetime(rs.getTimestamp("onlinetime"));
			site.setStatus(rs.getString("status"));
			site.setGroupName(rs.getString("gname"));
			return site;
		}

	}

	private static final String FIND_SITEID_BY_PAGEID = "select s.id from esite.es_site s join esite.es_page p on p.siteid = s.id where p.id = ?";

	@Override
	public long findSiteidByPageid(long pageid)
	{
		Object[] params =
		{ pageid };
		try
		{
			return getJdbcTemplate().queryForLong(FIND_SITEID_BY_PAGEID, params);
		} catch (DataAccessException e)
		{
			return 0;
		}
	}

	private static final String FIND_SITENAMES = "select s.name,s.createtime,s.id from esite.es_site s where sitegroupid = ? and s.status != 'DEL'";

	@Override
	public List<Site> findSiteNames(long id)
	{
		Object[] params =
		{ id };
		return getJdbcTemplate().query(FIND_SITENAMES, params, new SiteNamemapper());
	}

	class SiteNamemapper implements RowMapper
	{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			Site site = new Site();
			site.setName(rs.getString("name"));
			site.setCreatetime(rs.getTimestamp("createtime"));
			site.setId(rs.getLong("id"));
			return site;
		}
	}

	private static final String DELETE_SITE_GROUP = "update esite.es_site_group set status= 'DEL' where id = ?";

	@Override
	public int deleteSiteGroup(long siteid)
	{
		Object[] params =
		{ siteid };
		return getJdbcTemplate().update(DELETE_SITE_GROUP, params);
	}

	private static final String UPDATE_SITE_GROUP = "update esite.es_site_group set groupName = ? where id = ?";

	@Override
	public int updateSiteGroup(String name, long siteid)
	{
		Object[] params =
		{ name, siteid };
		return getJdbcTemplate().update(UPDATE_SITE_GROUP, params);
	}

	/*
	 * 后台查询数据库返回集合列表 先查询到站点，然后根据站点查询页面
	 */
	private static final String FIND_SITE_BY_SITEGROUPID = "select id, name,type from es_site where sitegroupid=?  and status != 'DEL'";

	@Override
	public List<Site> findSiteBySiteGroupId(long sitegroupid)
	{
		Object[] params =
		{ sitegroupid };
		List<Site> list = getJdbcTemplate().query(FIND_SITE_BY_SITEGROUPID, params, new PageConfigMapper());
		return list;
	}

	class PageConfigMapper implements RowMapper
	{

		@Override
		public Object mapRow(ResultSet rs, int arg) throws SQLException
		{
			Site site = new Site();
			site.setId(rs.getLong("id"));
			site.setName(rs.getString("name"));
			site.setType(rs.getString("type"));
			return site;
		}
	}

	private static final String FIND_SITEID_BY_SITEGROUPID = "select * from es_site where sitegroupid=?  and status != 'DEL'";

	@Override
	public List<Site> findSiteIdBySiteGroupId(long sitegroupid)
	{
		Object[] params =
		{ sitegroupid };
		return getJdbcTemplate().query(FIND_SITEID_BY_SITEGROUPID, params, new SiteRowmapper());
	}

	private static final String UPDATE_SITE_GROUP_PROCESS_TIME = "update esite.es_site_group set hourprocesstime=now() where id = ?";

	@Override
	public int updateSiteGroupProcessTime(long siteid)
	{
		Object[] params =
		{ siteid };
		return getJdbcTemplate().update(UPDATE_SITE_GROUP_PROCESS_TIME, params);
	}

	private static final String FIND_SITE_GROUP_BY_ID = "select g.* ,c.updatetime  from es_site_group g join es_site s on g.id=s.sitegroupid join es_page p on p.siteid=s.id  join es_page_cache c on c.pageid=p.id  where g.ownerid = ? and g.id=? order by c.updatetime desc";

	@Override
	public Sitegroup findSitegroupByOwner(long owner, long siteid)
	{
		Object[] params =
		{ owner, siteid };
		List<Sitegroup> list = getJdbcTemplate().query(FIND_SITE_GROUP_BY_ID, params, new SitegroupNewRowmapper());
		if (list != null && list.size() > 0)
			return list.get(0);
		return null;
	}

	private static final String FIND_SITE_BY_OWNER = "select s.* from esite.es_site s join es_sina_token t on t.siteid=s.id where s.status!='DEL'  and t.cid is not null and s.ownerid = ? group by s.id order by s.id desc ";

	@Override
	public List<Site> findSiteByOwner(long owner)
	{
		Object[] params =
		{ owner };
		return getJdbcTemplate().query(FIND_SITE_BY_OWNER, params, new SiteRowmapper());
	}

	@Override
	public List<Sitegroup> findSitegroupByOwner(long owner)
	{
		String sql = "select * from es_site_group where ownerid=? and status!='DEL' order by id desc ";
		return getJdbcTemplate().query(sql, new Object[]
		{ owner }, new RowMapper()
		{

			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				Sitegroup sg = new Sitegroup();
				sg.setId(rs.getLong("id"));
				sg.setGroupname(rs.getString("groupName"));
				sg.setOwnerId(rs.getLong("ownerid"));
				sg.setType(rs.getString("type"));
				return sg;
			}
		});
	}

	@Override
	public Sitegroup findSitegroupByPageid(long pageid)
	{
		String sql = "select sg.* from es_site_group sg,es_site s,es_page p where sg.id=s.sitegroupid and s.id=p.siteid and p.id=?";
		List<Sitegroup> ls = getJdbcTemplate().query(sql, new Object[]
		{ pageid }, new RowMapper()
		{

			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				Sitegroup sg = new Sitegroup();
				sg.setId(rs.getLong("id"));
				sg.setGroupname(rs.getString("groupName"));
				sg.setOwnerId(rs.getLong("ownerid"));
				sg.setType(rs.getString("type"));
				sg.setLoginpage(rs.getLong("loginpage"));
				sg.setRegistpage(rs.getLong("registpage"));
				sg.setStype(rs.getString("stype"));
				return sg;
			}
		});
		if (ls != null && ls.size() > 0)
		{
			return ls.get(0);
		}
		return null;
	}

	@Override
	public List<Site> findSiteList(long owner)
	{
		String sql = "select * from es_site s join es_site_group g on s.sitegroupid=g.id where s.ownerid=? and s.status!='DEL' and g.status!='DEL' order by s.id desc ";
		return getJdbcTemplate().query(sql, new Object[]
		{ owner }, new RowMapper()
		{

			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				Site sg = new Site();
				sg.setId(rs.getLong("id"));
				sg.setName(rs.getString("name"));
				sg.setOwnerId(rs.getLong("ownerid"));
				sg.setType(rs.getString("type"));
				sg.setGroupName(rs.getString("g.groupName"));
				sg.setGroupStype(rs.getString("g.stype"));
				sg.setSitegroupid(rs.getLong("s.sitegroupid"));
				return sg;
			}
		});
	}

	private static final String FIND_SITE_PAGE_BY_OWNER = "select s.id siteid,p.id pageid,s.name sitename,p.name pagename,s.type,(select count(id) from es_page p1 where p1.siteid=s.id and p1.wol='Y' ) as pnum from es_page p left join es_site s on p.siteid=s.id  where s.ownerid=? and wol='Y' order by s.id desc,p.id desc";

	@Override
	public List<SitePage> findSitePageByOwner(long owner)
	{
		Object[] params =
		{ owner };
		return getJdbcTemplate().query(FIND_SITE_PAGE_BY_OWNER, params, new SitePagemapper());
	}

	class SitePagemapper implements RowMapper
	{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			SitePage sitePage = new SitePage();
			sitePage.setPageid(rs.getLong("pageid"));
			sitePage.setSiteid(rs.getLong("siteid"));
			sitePage.setPagename(rs.getString("pagename"));
			sitePage.setSitename(rs.getString("sitename"));
			sitePage.setPnum(rs.getInt("pnum"));
			sitePage.setStype(rs.getString("type"));
			return sitePage;
		}
	}

	private static final String FIND_PAGE_BY_OWNER = "select p.id,p.name,s.name sitename from es_page p left join es_site s on p.siteid=s.id where s.ownerid=? and wol='Y' order by p.id desc limit ?,?";

	@Override
	public List<SitePage> findPageByOwner(long owner, int start, int size)
	{
		Object[] params =
		{ owner, start, size };
		return getJdbcTemplate().query(FIND_PAGE_BY_OWNER, params, new Pagemapper1());
	}

	class Pagemapper1 implements RowMapper
	{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			SitePage sitePage = new SitePage();
			sitePage.setPageid(rs.getLong("id"));
			sitePage.setPagename(rs.getString("name"));
			sitePage.setSitename(rs.getString("sitename"));
			return sitePage;
		}
	}

	private static final String FIND_PAGE_BY_SITEID = "select p.id,p.name from es_page p where p.siteid=? and wol='Y'";

	@Override
	public List<SitePage> findPageBySiteId(long siteid)
	{
		Object[] params =
		{ siteid };
		return getJdbcTemplate().query(FIND_PAGE_BY_SITEID, params, new Pagemapper());
	}

	private static final String FIND_PAGE_BY_ID = "select p.id,p.name,s.ownerid from es_page p left join es_site s on p.siteid=s.id where p.id=?";

	@Override
	public SitePage findPageById(long pageid)
	{
		Object[] params =
		{ pageid };
		List<SitePage> list = getJdbcTemplate().query(FIND_PAGE_BY_ID, params, new PageModelmapper());
		if (list != null && list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}

	class PageModelmapper implements RowMapper
	{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			SitePage sitePage = new SitePage();
			sitePage.setPageid(rs.getLong("id"));
			sitePage.setPagename(rs.getString("name"));
			sitePage.setOwnerid(rs.getLong("ownerid"));
			return sitePage;
		}
	}

	class Pagemapper implements RowMapper
	{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			SitePage sitePage = new SitePage();
			sitePage.setPageid(rs.getLong("id"));
			sitePage.setPagename(rs.getString("name"));
			return sitePage;
		}
	}

	@Override
	public long findXcIdBySiteid(long siteid)
	{
		List<Long> ls = getJdbcTemplate().query("select xcid from es_interact_xc_site where siteid=?", new Object[]
		{ siteid }, new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				return rs.getLong("xcid");
			}

		});
		if (ls != null && ls.size() > 0)
		{
			return ls.get(0);
		}
		return 0;
	}

	@Override
	public long saveSiteGroup(final String sitegroupname, final String type, final long ownerid,final String stype)
	{
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator()
		{

			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement("insert into es_site_group (groupName,ownerid,type,createtime,stype,updatetime) values(?,?,?,now(),?,now())", new String[]
				{ "id" });
				int i = 1;
				ps.setString(i++, sitegroupname);
				ps.setLong(i++, ownerid);
				ps.setString(i++, type);
				ps.setString(i++, stype);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;

	}

	@Override
	public List<Site> findCopySiteByOwner(long groupid)
	{
		String sql = "select site.* from esite.es_site site join  es_site_group grp on grp.id=site.sitegroupid where   site.status!='DEL' and grp.id=?";
		List<Site> list = getJdbcTemplate().query(sql, new Object[]
		{ groupid }, new RowMapper()
		{

			// String sql="select site.* from esite.es_site site join (select *
			// from esite.es_site_group where id=426 ) grp on
			// grp.id=site.sitegroupid where site.status!='DEL'";
			// List<Site> list=getJdbcTemplate().query(sql,new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				Site site = new Site();
				site.setId(rs.getLong("id"));
				site.setName(rs.getString("name"));
				site.setOwnerId(rs.getLong("ownerid"));
				site.setType(rs.getString("type"));
				site.setCreatetime(rs.getTimestamp("createtime"));
				site.setUpdatetime(rs.getTimestamp("updatetime"));
				site.setOnlinetime(rs.getTimestamp("onlinetime"));
				site.setStatus(rs.getString("status"));
				return site;
			}
		});
		return list;
	}

	@Override
	public int updateSiteGroupIsTemplateSetY(long sitegroupid)
	{
		String sql = "update es_site_group set istemplate='Y' where id=?";
		return getJdbcTemplate().update(sql, new Object[]
		{ sitegroupid });
	}

	@Override
	public List<Page> findPageListBySiteId(long siteid)
	{
		String sql = "select p.id,p.name,p.apptype from es_page p where p.siteid=? and (p.relationid is null and p.contextid is null) and p.status !='DEL' and p.isonline='Y' order by p.createtime desc";
		Object[] params =
		{ siteid };
		return getJdbcTemplate().query(sql, params, new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				Page p = new Page();
				p.setId(rs.getLong("p.id"));
				p.setName(rs.getString("p.name"));
				p.setApptype(rs.getString("p.apptype"));
				return p;
			}

		});
	}

	@Override
	public int updateSiteGroupIsTemplateSetN(long sitegroupid)
	{
		String sql = "update es_site_group set istemplate='N' where id=?";
		return getJdbcTemplate().update(sql, new Object[]
		{ sitegroupid });
	}

	@Override
	public void updateSiteGroupEntity(long sitegroupid, long entity)
	{
		getJdbcTemplate().update("update es_site_group set entityid=? where id=?", new Object[]
		{ entity, sitegroupid });
	}

	@Override
	public List<Sitegroup> findAllTempSiteGroup(String type)
	{
		Object[] params =
		{ type };
		return getJdbcTemplate().query("select * from es_site_group where status!='DEL' and istemplate='Y' and type = ?", params, new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				Sitegroup s = new Sitegroup();
				s.setId(rs.getLong("id"));
				s.setGroupname(rs.getString("groupName"));
				s.setType(rs.getString("type"));
				s.setEntityid(rs.getLong("entityid"));
				return s;
			}
		});
	}

	@Override
	public long findSiteidByXcandType(long xcid, String type)
	{
		List<Long> list = getJdbcTemplate().query("select s.id from es_site s join es_site_group g on s.sitegroupid=g.id where g.entityid=? and g.type='W' and s.type=?",
				new Object[]
				{ xcid, type }, new RowMapper()
				{

					@Override
					public Object mapRow(ResultSet rs, int arg1) throws SQLException
					{
						return rs.getLong("id");
					}
				});
		return list.size() > 0 ? list.get(0) : 0;
	}

	@Override
	public long findSiteidByXcandPageId(long xcid, Long pageId)
	{
		List<Long> list = getJdbcTemplate().query(
				"select s.id from es_site s join es_site_group g on s.sitegroupid=g.id where g.entityid=? and g.type='W' and s.type=(select stype from es_page p where p.id=?)",
				new Object[]
				{ xcid, pageId }, new RowMapper()
				{

					@Override
					public Object mapRow(ResultSet rs, int arg1) throws SQLException
					{
						return rs.getLong("id");
					}
				});
		/**
		 * 如果pageId对于原站点为PC站点 则直接返回微现场的PC站点ID
		 * 如果pageId对于原站点为移动站点 则直接返回微现场的第三个移动站点
		 */
		if(list.size()==1){
			return list.get(0);
		}
		if(list.size()==3){
			return list.get(2);
		}
		return 0;
	}

	private static final String UPDATE_SITE_IS_WHOLE = "update es_site set upwhole = 'Y' where id= ?";

	@Override
	public int updateSiteIsWhole(long siteid)
	{
		Object[] param =
		{ siteid };
		return getJdbcTemplate().update(UPDATE_SITE_IS_WHOLE, param);
	}

	@Override
	public int updateNamebypageid(long pageid, String name)
	{
		String sql = "update es_page set name=? where id=?";
		return getJdbcTemplate().update(sql, new Object[]
		{ name, pageid });
	}

	@Override
	public Sitegroup findSiteGroupById(long sitegroupid)
	{
		List<Sitegroup> list = getJdbcTemplate().query("select * from es_site_group where id=?", new Object[]
		{ sitegroupid }, new SitegroupRowmapper());
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public Map<String, Long> findLogRegById(long sitegroupid)
	{
		return getJdbcTemplate().queryForMap("select loginpage,registpage from es_site_group where id=?", new Object[]
		{ sitegroupid });
	}

	@Override
	public void updateSglr(long sitegroupid, Long nl, Long nr)
	{
		getJdbcTemplate().update("update es_site_group set loginpage=?,registpage=?  where id=?", new Object[]
		{ nl, nr, sitegroupid });
	}

	@Override
	public void updateSiteName(String newName, long id)
	{
		getJdbcTemplate().update("update es_site set name=? where id=?", new Object[]
		{ newName, id });
	}

	private static final String FIND_SAME_NAME_COUNT = "select count(id) from es_site_group where ownerid = ? and groupName = ? and type=? and status != 'DEL'";

	@Override
	public int findSameNameCount(long ownerid, String sitename, String type)
	{
		Object[] params =
		{ ownerid, sitename, type };
		return getJdbcTemplate().queryForInt(FIND_SAME_NAME_COUNT, params);
	}

	@Override
	public List<Page> findOnlinePageListBySiteId(long siteid)
	{
		String sql = "select p.id,p.name from es_page p where p.siteid=? and p.status !='DEL' and p.isonline = 'Y' order by p.createtime desc";
		Object[] params =
		{ siteid };
		return getJdbcTemplate().query(sql, params, new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				Page p = new Page();
				p.setId(rs.getLong("p.id"));
				p.setName(rs.getString("p.name"));
				return p;
			}
		});
	}

	@Override
	public String findOnameBySg(long groupid)
	{
		List<String> list = getJdbcTemplate().query("select * from es_owner o join es_site_group g on o.id=g.ownerid where g.id=?", new Object[]
		{ groupid }, new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				return rs.getString("o.entity");
			}
		});
		return list.size() > 0 ? list.get(0) : "";
	}

	@Override
	public int findSitegroupCountByGrp(long groupid, String copyType)
	{
		return getJdbcTemplate().queryForInt("select count(*) from es_site_group where id=? and type=?", new Object[]
		{ groupid, copyType });
	}

	private static final String FIND_CB_COUNT = "select count(distinct pageid) from es_interact_cb_activity_matter where owner = ? and sgtype = ?";

	@Override
	public int findCbCount(long ownerid, String type)
	{
		Object[] params =
		{ ownerid, type };
		return getJdbcTemplate().queryForInt(FIND_CB_COUNT, params);
	}

	private static final String FIND_PUB_COUNT = "select count(id) from es_site_group where ownerid = ? and status = 'CMP' and stype = ?";

	@Override
	public int findPubCount(long ownerid, String type)
	{
		Object[] param =
		{ ownerid, type };
		return getJdbcTemplate().queryForInt(FIND_PUB_COUNT, param);
	}

	private static final String UPDATE_SITEGROUP_TIME = "update es_site_group set updatetime = now() where id = ?";

	@Override
	public int updateSiteGroupTime(long sgid)
	{
		Object[] param =
		{ sgid };
		return getJdbcTemplate().update(UPDATE_SITEGROUP_TIME, param);
	}

	@Override
	public long saveSameSite(final long ownerid,final long sitegroupid,final long oldid)
	{
		final String sql="insert into es_site (ownerid,sitegroupid,name,type,createtime,updatetime,status) select ?,?,name,type,now(),now(),'EDT' from es_site where id=?";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator()
		{

			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(sql, new String[]
				{ "id" });
				ps.setLong(1, ownerid);
				ps.setLong(2, sitegroupid);
				ps.setLong(3, oldid);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}
	
	@Override
	public long saveSameSiteGroup(final long ownerid,final String sitename,final long groupid)
	{
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator()
		{

			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement("insert into es_site_group (groupName,ownerid,type,createtime,stype,updatetime) select ?,?,type,now(),stype,now() from es_site_group where id=?", new String[]
				{ "id" });
				int i = 1;
				ps.setString(i++, sitename);
				ps.setLong(i++, ownerid);
				ps.setLong(i++, groupid);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;

	}
	
	@Override
	public List<Sitegroup> findXcTemplate()
	{
		return getJdbcTemplate().query("select * from es_site_group where istemplate='Y' and type='W' and stype='X'", new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				Sitegroup sg = new Sitegroup();
				sg.setId(rs.getLong("id"));
				sg.setGroupname(rs.getString("groupName"));
				sg.setOwnerId(rs.getLong("ownerid"));
				sg.setType(rs.getString("type"));
				sg.setCreatetime(rs.getTimestamp("createtime"));
				sg.setIstemplate(rs.getString("istemplate"));
				sg.setEntityid(rs.getLong("entityid"));
				sg.setUpdatetime(rs.getTimestamp("updatetime"));
				return sg;
			}
		});
	}
	
	@Override
	public long findSiteGroupbyXcid(long xcid)
	{
		return getJdbcTemplate().queryForLong("select id from es_site_group where entityid=? and type='W'", new Object[]{xcid});
	}
	
	@Override
	public String findGroupTypeByMbid(long mbid)
	{
		List<String> list=getJdbcTemplate().query("select g.type from es_site_group g join es_site s on g.id=s.sitegroupid join es_page p on s.id=p.siteid join es_mb_page m on m.pageid=p.id where m.mbid=? limit 1", new Object[]{mbid}, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				return rs.getString("g.type");
			}
		});
		return list.size()>0?list.get(0):"N";
	}
	
	@Override
	public Site findSiteWithGrpById(long siteid)
	{
		Object[] params =
		{ siteid };
		List<Site> list = getJdbcTemplate().query("select * from es_site_group g join es_site s on g.id=s.sitegroupid where s.id=? ", params, new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				Site site = new Site();
				site.setId(rs.getLong("s.id"));
				site.setName(rs.getString("s.name"));
				site.setOwnerId(rs.getLong("s.ownerid"));
				site.setType(rs.getString("s.type"));
				site.setCreatetime(rs.getTimestamp("s.createtime"));
				site.setUpdatetime(rs.getTimestamp("s.updatetime"));
				site.setOnlinetime(rs.getTimestamp("s.onlinetime"));
				site.setStatus(rs.getString("s.status"));
				site.setUpWhole(rs.getString("s.upwhole"));
				site.setSitegroupid(rs.getLong("s.sitegroupid"));
				site.setGroupName(rs.getString("g.groupName"));
				site.setGroupStype(rs.getString("g.stype"));
				return site;
			}
		});
		if (list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public long findxcidBysitegroup(long sitegroupid)
	{
		return getJdbcTemplate().queryForLong("select entityid from es_site_group where id=? and type='W'", new Object[]{sitegroupid});
	}
	
	@Override
	public Sitegroup findSiteGroup(long groupid)
	{
		Object[] params =
		{ groupid };
		List<Sitegroup> list = getJdbcTemplate().query("select * from es_site_group  where id=? ", params, new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				Sitegroup sg=new Sitegroup();
				sg.setId(rs.getLong("id"));
				sg.setGroupname(rs.getString("groupName"));
				sg.setOwnerId(rs.getLong("ownerid"));
				sg.setType(rs.getString("type"));
				sg.setStatus(rs.getString("status"));
				sg.setEntityid(rs.getLong("entityid"));
				sg.setCreatetime(rs.getTimestamp("createtime"));
				sg.setIstemplate(rs.getString("istemplate"));
				sg.setLoginpage(rs.getLong("loginpage"));
				sg.setRegistpage(rs.getLong("registpage"));
				sg.setStype(rs.getString("stype"));
				sg.setUpdatetime(rs.getTimestamp("updatetime"));
				return sg;
			}
		});
		if (list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Site> findSiteListByOwnerid(long owner)
	{
		String sql = "select * from es_site s where s.ownerid=? and s.status!='DEL' order by s.id desc ";
		return getJdbcTemplate().query(sql, new Object[]
		{ owner }, new RowMapper()
		{

			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				Site sg = new Site();
				sg.setId(rs.getLong("id"));
				sg.setName(rs.getString("name"));
				sg.setOwnerId(rs.getLong("ownerid"));
				sg.setType(rs.getString("type"));
				return sg;
			}
		});
	}
	
	@Override
	public List<Sitegroup> findGroupByType(long owner, String grouptype, int start, int size)
	{
		Object[] params =
		{ owner, grouptype, start, size };
		return getJdbcTemplate().query("select * from es_site_group where ownerid=? and status!='DEL' and type=? order by createtime desc limit ?,?", params, new RowMapper()
		{

			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				Sitegroup sg = new Sitegroup();
				sg.setId(rs.getLong("id"));
				sg.setGroupname(rs.getString("groupName"));
				sg.setOwnerId(rs.getLong("ownerid"));
				sg.setType(rs.getString("type"));
				sg.setCreatetime(rs.getTimestamp("createtime"));
				sg.setIstemplate(rs.getString("istemplate"));
				sg.setEntityid(rs.getLong("entityid"));
				sg.setUpdatetime(rs.getTimestamp("updatetime"));
				return sg;
			}
		});
	}
	
	@Override
	public int findGroupTotalByType(long owner, String grouptype)
	{
		return getJdbcTemplate().queryForInt("select count(*) from es_site_group where ownerid=? and type=? and status != 'DEL'", new Object[]{owner,grouptype});
	}
	
	@Override
	public List<Sitegroup> findGroupByType(long owner, String grouptype)
	{
		Object[] params ={ owner, grouptype };
		return getJdbcTemplate().query("select * from es_site_group where ownerid=? and status!='DEL' and type=? order by createtime ", params, new RowMapper()
		{

			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				Sitegroup sg = new Sitegroup();
				sg.setId(rs.getLong("id"));
				sg.setGroupname(rs.getString("groupName"));
				sg.setOwnerId(rs.getLong("ownerid"));
				sg.setType(rs.getString("type"));
				sg.setCreatetime(rs.getTimestamp("createtime"));
				sg.setIstemplate(rs.getString("istemplate"));
				sg.setEntityid(rs.getLong("entityid"));
				sg.setUpdatetime(rs.getTimestamp("updatetime"));
				return sg;
			}
		});
	}
}

