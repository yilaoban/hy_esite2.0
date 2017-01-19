package com.huiyee.esite.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.dao.ISiteSourceDao;
import com.huiyee.esite.model.OwnerSource;
import com.huiyee.esite.model.Page4Source;
import com.huiyee.esite.model.PageSource;
import com.huiyee.esite.model.SiteSource;
import com.huiyee.esite.model.SiteSourceVm;

public class SiteSourceDaoImpl extends AbstractDao implements ISiteSourceDao
{

	@Override
	public int saveSiteSource(long siteid, long owner, SiteSource s)
	{
		return getJdbcTemplate().update("insert into esite.es_site_source (ownerid,siteid,title,path,json,vjson,createtime,type) values(?,?,?,?,?,?,now(),?)", new Object[]{owner,siteid,s.getTitle(),s.getPath(),s.getJson(),s.getVjson(),s.getType()});
	}

	private static final String FIND_SITE_SOURCE_BY_OWNERID="select t1.*,t2.pageid from (select s.* from es_site_source s where s.ownerid = ? and s.siteid = ? ) t1 left outer join (select s.id,p.pageid from es_page_source p join es_site_source s on p.sourceid = s.id where p.pageid = ?) t2 on t1.id = t2.id order by t1.id desc";
	@Override
	public List<SiteSource> findSiteSourceByOwnerid(long ownerid,long siteid,long pageid)
	{
		Object[] param={ownerid,siteid,pageid};
		return getJdbcTemplate().query(FIND_SITE_SOURCE_BY_OWNERID, param, new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				SiteSource ss = new SiteSource();
				ss.setId(rs.getLong("id"));
				ss.setOwnerid(rs.getLong("ownerid"));
				ss.setTitle(rs.getString("title"));
				ss.setPath(rs.getString("path"));
				ss.setJson(rs.getString("json"));
				ss.setVjson(rs.getString("vjson"));
				ss.setSiteid(rs.getLong("siteid"));
				ss.setCreatetime(rs.getTimestamp("createtime"));
				ss.setType(rs.getString("type"));
				ss.setPid(rs.getLong("pageid"));
				return ss;
			}
			
		});
	}
	
	private static final String FIND_SITE_SOURCE_BY_PAGEID="select s.*,p.pageid from es_page_source p left outer join es_site_source s on p.sourceid = s.id where p.pageid = ? order by s.id desc;";
	@Override
	public List<SiteSource> findSiteSourceByPageid(long pageid)
	{
		Object[] param={pageid};
		return getJdbcTemplate().query(FIND_SITE_SOURCE_BY_OWNERID, param, new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				SiteSource ss = new SiteSource();
				ss.setId(rs.getLong("id"));
				ss.setOwnerid(rs.getLong("ownerid"));
				ss.setTitle(rs.getString("title"));
				ss.setPath(rs.getString("path"));
				ss.setJson(rs.getString("json"));
				ss.setVjson(rs.getString("vjson"));
				ss.setSiteid(rs.getLong("siteid"));
				ss.setCreatetime(rs.getTimestamp("createtime"));
				ss.setType(rs.getString("type"));
				ss.setPid(rs.getLong("pageid"));
				return ss;
			}
			
		});
	}
	
	private static final String UPDATE_PAGE_SOURCE="insert into es_page_source(pageid,sourceid,vjson) values (?,?,?)";
	@Override
	public int updatePageSource(long pageid, SiteSource ss)
	{
		Object[] param={pageid,ss.getId(),ss.getVjson()};
		return getJdbcTemplate().update(UPDATE_PAGE_SOURCE, param);
	}
	
	private static final String DELETE_PAGE_SOURCE="delete from es_page_source where pageid = ?";
	@Override
	public int deletePageSource(long pageid)
	{
		Object[] param={pageid};
		return getJdbcTemplate().update(DELETE_PAGE_SOURCE, param);
	}
	
	private static final String FIND_PAGE_SOURCE_BY_PAGEID_InCardAndPage ="select html from es_page_source where pageid = ? and html is not null order by id desc";
	@Override
	public List<String> findPageSourceByPageidInCardAndPage(long pageid)
	{
		Object[] param={pageid};
		try
		{
			return getJdbcTemplate().queryForList(FIND_PAGE_SOURCE_BY_PAGEID_InCardAndPage, param, String.class);
		} catch (DataAccessException e)
		{
			return null;
		}
	}
	
	private static final String FIND_PAGE_SOURCE_BY_PAGEID_IN_CARD="select p.html from es_page_source p join es_site_source s on p.sourceid = s.id where p.pageid = ? and s.type=? and p.html is not null order by p.id desc";
	@Override
	public List<String> findPageSourceByPageidInCardOrPage(long pageid,String type)
	{
		Object[] param={pageid,type};
		try
		{
			return getJdbcTemplate().queryForList(FIND_PAGE_SOURCE_BY_PAGEID_IN_CARD, param, String.class);
		} catch (DataAccessException e)
		{
			return null;
		}
	}
	
	private static final String FIND_PAGE_SOURCE_LIST_BY_PAGEID="select p.*,s.id sid,s.title,s.path from es_page_source p join es_site_source s on p.sourceid = s.id where p.pageid = ?";
	@Override
	public List<PageSource> findPageSourceListByPageid(long pageid)
	{
		Object[] param={pageid};
		return getJdbcTemplate().query(FIND_PAGE_SOURCE_LIST_BY_PAGEID, param, new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				PageSource ps = new PageSource();
				ps.setId(rs.getLong("id"));
				ps.setVjson(rs.getString("vjson"));
				ps.setLeft(rs.getString("pleft"));
				ps.setRight(rs.getString("pright"));
				ps.setTop(rs.getString("ptop"));
				ps.setBottom(rs.getString("pbottom"));
				SiteSource ss = new SiteSource();
				ss.setId(rs.getLong("sid"));
				ss.setPath(rs.getString("path"));
				ss.setTitle(rs.getString("title"));
				ps.setSource(ss);
				return ps;
			}
			
		});
	}
	
	private static final String FIND_PAGE_SOURCE_BY_ID="select p.id pid,p.ptop,p.pleft,p.pright,p.pbottom,p.vjson pvjson, s.* from es_page_source p join es_site_source s on p.sourceid = s.id where p.id = ?";
	@Override
	public PageSource findPageSourceById(long psid)
	{
		Object[] param={psid};
		List<PageSource> list = getJdbcTemplate().query(FIND_PAGE_SOURCE_BY_ID,param, new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				PageSource ps = new PageSource();
				ps.setId(rs.getLong("pid"));
				ps.setTop(rs.getString("ptop"));
				ps.setLeft(rs.getString("pleft"));
				ps.setVjson(rs.getString("pvjson"));
				ps.setRight(rs.getString("pright"));
				ps.setBottom(rs.getString("pbottom"));
				SiteSource ss = new SiteSource();
				ss.setId(rs.getLong("id"));
				ss.setPath(rs.getString("path"));
				ss.setJson(rs.getString("json"));
				ss.setVjson(rs.getString("vjson"));
				ss.setType(rs.getString("type"));
				ps.setSource(ss);
				return ps;
			}
			
		});
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	private static final String SAVE_PAGE_SOURCE_EDIT="update es_page_source set vjson = ? ,html = ?,ptop = ?, pleft = ?,pright=?,pbottom=? where id = ?";
	@Override
	public int savePageSourceEdit(long psid, String vjson, String html, String top, String left, String right, String bottom)
	{
		Object[] param={vjson,html,top,left,right,bottom,psid};
		return getJdbcTemplate().update(SAVE_PAGE_SOURCE_EDIT,param);
	}
	
	private static final String FIND_SITE_SOURCE_BY_ID="select * from es_site_source where id = ?";
	@Override
	public SiteSource findSiteSourceById(long sourceid)
	{
		Object[] param={sourceid};
		List<SiteSource> list = getJdbcTemplate().query(FIND_SITE_SOURCE_BY_ID, param, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				SiteSource ss = new SiteSource();
				ss.setId(rs.getLong("id"));
				ss.setOwnerid(rs.getLong("ownerid"));
				ss.setTitle(rs.getString("title"));
				ss.setPath(rs.getString("path"));
				ss.setJson(rs.getString("json"));
				ss.setVjson(rs.getString("vjson"));
				ss.setSiteid(rs.getLong("siteid"));
				ss.setType(rs.getString("type"));
				ss.setCreatetime(rs.getTimestamp("createtime"));
				return ss;
			}
		});
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	private static final String SAVE_COPY_PAGE_SOURCE="insert into es_page_source(pageid,sourceid,vjson,html,ptop,pleft) (select ?,sourceid,vjson,html,ptop,pleft from es_page_source where pageid = ?);";
	@Override
	public int saveCopyPageSource(long pageid, long newpageid)
	{
		Object[] param ={newpageid, pageid};
		return getJdbcTemplate().update(SAVE_COPY_PAGE_SOURCE,param);
	}
	
	@Override
	public List<SiteSource> findSiteSource(long siteid)
	{
		return getJdbcTemplate().query("select * from es_site_source where siteid=?", new Object[]{siteid}, new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				SiteSource ss = new SiteSource();
				ss.setId(rs.getLong("id"));
				ss.setOwnerid(rs.getLong("ownerid"));
				ss.setTitle(rs.getString("title"));
				ss.setPath(rs.getString("path"));
				ss.setJson(rs.getString("json"));
				ss.setVjson(rs.getString("vjson"));
				ss.setSiteid(rs.getLong("siteid"));
				ss.setType(rs.getString("type"));
				ss.setCreatetime(rs.getTimestamp("createtime"));
				return ss;
			}
		});
	}
	
	@Override
	public int updateSiteSource(long siteid, long owner, SiteSource s)
	{
		return getJdbcTemplate().update("update esite.es_site_source set title=?,path=?,json=?,vjson=?,type=? where id=?", new Object[]{s.getTitle(),s.getPath(),s.getJson(),s.getVjson(),s.getType(),s.getId()});
	}

	private static final String FIND_SOURCE_PAGE="select count(distinct pageid) from es_page_source where sourceid = ?";
	@Override
	public int findSourcePage(long sourceid)
	{
		return getJdbcTemplate().queryForInt(FIND_SOURCE_PAGE, new Object[]{sourceid});
	}

	private static final String DEL_SITE_SOURCE="delete from es_site_source where id = ?";
	@Override
	public int delSiteSource(long sourceid)
	{
		return getJdbcTemplate().update(DEL_SITE_SOURCE, new Object[]{sourceid});
	}
	
	private static final String DEL_OWNER_SOURCE="update es_owner_source set del_tag='Y' where ownerid=? and id=?";
	@Override
	public int delOwnerSource(long owner, long osid)
	{
		return getJdbcTemplate().update(DEL_OWNER_SOURCE, new Object[]{owner,osid});
	}

	private static final String FIND_OWNER_SOURCE="select * from es_owner_source os join es_source_vm vm on os.vmid = vm.id where os.ownerid = ? and del_tag!='Y'";
	@Override
	public List<OwnerSource> findOwnerSource(long ownerid)
	{
		Object[] param={ownerid};
		return getJdbcTemplate().query(FIND_OWNER_SOURCE, param, new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				OwnerSource os = new OwnerSource();
				os.setId(rs.getLong("os.id"));
				os.setOwnerid(rs.getLong("ownerid"));
				os.setTitle(rs.getString("title"));
				os.setCreatetime(rs.getTimestamp("createtime"));
				os.setLevel(rs.getInt("os.level"));
				os.setJson(rs.getString("json"));
				SiteSourceVm vm = new SiteSourceVm();
				vm.setId(rs.getLong("vm.id"));
				vm.setPath(rs.getString("vm.path"));
				vm.setJson(rs.getString("vm.json"));
				vm.setLevel(rs.getInt("vm.level"));
				vm.setTitle(rs.getString("vm.title"));
				vm.setType(rs.getString("vm.type"));
				vm.setImg(rs.getString("vm.img"));
				os.setVm(vm);
				return os;
			}
			
		});
	}

	private static final String FIND_SITE_SOURCE_VM="select * from es_source_vm where type =? order by id desc";
	@Override
	public List<SiteSourceVm> findSiteSourceVm(String type)
	{
		Object[] param={type};
		return getJdbcTemplate().query(FIND_SITE_SOURCE_VM, param, new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				SiteSourceVm vm = new SiteSourceVm();
				vm.setId(rs.getLong("id"));
				vm.setPath(rs.getString("path"));
				vm.setJson(rs.getString("json"));
				vm.setLevel(rs.getInt("level"));
				vm.setTitle(rs.getString("title"));
				vm.setType(rs.getString("type"));
				vm.setImg(rs.getString("img"));
				return vm;
			}
			
		});
	}

	private static final String FIND_SITE_SOURCE_VM_BY_ID="select * from es_source_vm where id = ?";
	@Override
	public SiteSourceVm findSiteSourceVmById(long id)
	{
		Object[] param={id};
		List<SiteSourceVm> list = getJdbcTemplate().query(FIND_SITE_SOURCE_VM_BY_ID, param, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				SiteSourceVm vm = new SiteSourceVm();
				vm.setId(rs.getLong("id"));
				vm.setPath(rs.getString("path"));
				vm.setJson(rs.getString("json"));
				vm.setLevel(rs.getInt("level"));
				vm.setTitle(rs.getString("title"));
				vm.setType(rs.getString("type"));
				vm.setImg(rs.getString("img"));
				return vm;
			}
		});
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	private static final String SAVE_OWNER_SITE_SOURCE="insert into es_owner_source(ownerid,vmid,title,html,createtime,level,json) values (?,?,?,?,now(),?,?)";
	@Override
	public int saveOwnerSiteSource(long ownerid, long vmid, String title, String html,int level,String json)
	{
		Object[] params={ownerid,vmid,title,html,level,json};
		return getJdbcTemplate().update(SAVE_OWNER_SITE_SOURCE, params);
	}

	private static final String FIND_OWNER_SOURCE_BY_ID="select * from es_owner_source os join es_source_vm vm on os.vmid = vm.id where os.id = ?";
	@Override
	public OwnerSource findOwnerSourceById(long id)
	{
		Object[] param={id};
		List<OwnerSource> list = getJdbcTemplate().query(FIND_OWNER_SOURCE_BY_ID, param, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				OwnerSource os = new OwnerSource();
				os.setId(rs.getLong("os.id"));
				os.setOwnerid(rs.getLong("ownerid"));
				os.setTitle(rs.getString("title"));
				os.setCreatetime(rs.getTimestamp("createtime"));
				os.setLevel(rs.getInt("os.level"));
				os.setJson(rs.getString("json"));
				os.setStyle(rs.getString("style"));
				os.setHtml(rs.getString("html"));
				SiteSourceVm vm = new SiteSourceVm();
				vm.setId(rs.getLong("vm.id"));
				vm.setPath(rs.getString("vm.path"));
				vm.setJson(rs.getString("vm.json"));
				vm.setLevel(rs.getInt("vm.level"));
				vm.setTitle(rs.getString("vm.title"));
				vm.setType(rs.getString("vm.type"));
				vm.setImg(rs.getString("vm.img"));
				os.setVm(vm);
				return os;
			}
		});
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	private static final String UPDATE_OWNER_SITE_SOURCE="update es_owner_source set json = ? ,html = ? where id = ?";
	@Override
	public int updateOwnerSiteSource(long id, String json, String html)
	{
		Object[] params={json,html,id};
		return getJdbcTemplate().update(UPDATE_OWNER_SITE_SOURCE, params);
	}

	private static final String FIND_PAGE_4_SOURCE_BY_PAGEID="select * from es_owner_source_4_page where pageid = ? and type = ?";
	@Override
	public Page4Source findPage4SourceByPageid(long pageid,String type)
	{
		Object[] param={pageid,type};
		List<Page4Source> list = getJdbcTemplate().query(FIND_PAGE_4_SOURCE_BY_PAGEID, param, new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				Page4Source ps = new Page4Source();
				ps.setId(rs.getLong("id"));
				ps.setOsid(rs.getLong("osid"));
				ps.setPageid(rs.getLong("pageid"));
				ps.setType(rs.getString("type"));
				ps.setCreatetime(rs.getTimestamp("createtime"));
				return ps;
			}
			
		});
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	private static final String SAVE_PAGE_4_SOURCE="insert into es_owner_source_4_page(osid,pageid,type,createtime) value (?,?,?,now()) on duplicate key update osid = ?";
	@Override
	public int savePage4Source(long pageid, long osid,String type)
	{
		Object[] params={osid,pageid,type,osid};
		return getJdbcTemplate().update(SAVE_PAGE_4_SOURCE, params);
	}

	private static final String FIND_PAGE_SOUERCE_BY_PAGEID_IN_EDIT="select os.html from es_owner_source_4_page sp join es_owner_source os on sp.osid = os.id where sp.pageid = ? and os.del_tag !='Y'";
	@Override
	public List<String> findPageSourceByPageidInEdit(long pageid)
	{
		Object[] params={pageid};
		try
		{
			return getJdbcTemplate().queryForList(FIND_PAGE_SOUERCE_BY_PAGEID_IN_EDIT, params, String.class);
		} catch (DataAccessException e)
		{
			return null;
		}
	}

	private static final String FIND_PAGE_SOUERCE_BY_PAGEID_IN_SHOW="select os.html from es_owner_source_4_page sp join es_owner_source os on sp.osid = os.id join es_source_vm vm on os.vmid = vm.id where sp.pageid = ? and vm.level = ? and os.del_tag !='Y'";
	@Override
	public List<String> findPageSourceByPageidInShow(long pageid, int level)
	{
		Object[] params={pageid,level};
		try
		{
			return getJdbcTemplate().queryForList(FIND_PAGE_SOUERCE_BY_PAGEID_IN_SHOW, params, String.class);
		} catch (DataAccessException e)
		{
			return null;
		}
	}

	private static final String DELETE_PAGE_4_SOURCE="delete from es_owner_source_4_page where pageid = ? and type = ?";
	@Override
	public int deletePage4Source(long pageid, String type)
	{
		Object[] param={pageid,type};
		return getJdbcTemplate().update(DELETE_PAGE_4_SOURCE, param);
	}
	
	private static final String FIND_COUNT_BY_OSID="select count(id) from es_owner_source_4_page where osid=?";
	@Override
	public int findCountByOsid(long osid)
	{
		return getJdbcTemplate().queryForInt(FIND_COUNT_BY_OSID, new Object[]{osid});
	}
	
	@Override
	public int updateOwnerSourceStyle(long id, long owner, String style,String html)
	{
		return getJdbcTemplate().update("update es_owner_source set style=? ,html = ? where id=? and ownerid=?", new Object[]{style,html,id,owner});
	}
}
