package com.huiyee.esite.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.dao.IPageDao;
import com.huiyee.esite.model.Activity;
import com.huiyee.esite.model.Feature;
import com.huiyee.esite.model.MyTempalte;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.PageAddress;
import com.huiyee.esite.model.PageRelation;
import com.huiyee.esite.model.PageTemplate;
import com.huiyee.esite.model.TemplateFeature;
import com.huiyee.esite.model.TemplateModel;
import com.huiyee.esite.model.WeiXinPage;

public class PageDaoImpl extends AbstractDao implements IPageDao
{

	private static final String FIND_PAGE_BY_SITEID = "select *  from esite.es_page  where siteid = ? and status != 'DEL' and (relationid is null and contextid is null) order by name,id desc";

	@Override
	public List<Page> findPageBySiteid(long siteid)
	{
		Object[] params =
		{ siteid };
		return getJdbcTemplate().query(FIND_PAGE_BY_SITEID, params, new PageRowmapper());
	}

	class PageRowmapper implements RowMapper
	{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			Page p = new Page();
			p.setId(rs.getLong("id"));
			p.setName(rs.getString("name"));
			p.setJspname(rs.getString("jspname"));
			p.setSiteid(rs.getLong("siteid"));
			p.setCreatetime(rs.getTimestamp("createtime"));
			p.setUpdatetime(rs.getTimestamp("updatetime"));
			p.setStatus(rs.getString("status"));
			p.setType(rs.getString("type"));
			p.setStype(rs.getString("stype"));
			p.setIsonline(rs.getString("isonline"));
			p.setWol(rs.getString("wol"));
			p.setRelationid((Long) rs.getObject("relationid"));
			p.setContextid((Long) rs.getObject("contextid"));
			p.setSubsina(rs.getString("subsina"));
			p.setSubweixin(rs.getString("subweixin"));
			p.setBg(rs.getString("bg"));
			p.setJspstyle(rs.getString("jspstyle"));
			p.setApptype(rs.getString("apptype"));
			p.setParamValue(rs.getString("paramValue"));
			return p;
		}

	}
	
	
	@Override
	public List<Page> findSinaPublishPageBySiteid(long siteid)
	{
		String sql = "select * from esite.es_page where siteid = ? and status != 'DEL'  and isonline='Y' order by id asc";
		Object[] params =
		{ siteid };
		return getJdbcTemplate().query(sql, params, new PageRowmapper());
	}
	
	@Override
	public List<Page> findQQPublishPageBySiteid(long siteid)
	{
		String sql = "select * from esite.es_page where siteid = ? and status != 'DEL' and  isonline='Y'  and  relationid is null  and  contextid is null  order by id asc";
		Object[] params =
		{ siteid };
		return getJdbcTemplate().query(sql, params, new PageRowmapper());
	}

	private static final String SAVE_PAGE = "insert into esite.es_page(name,jspname,siteid,createtime,updatetime,status,stype) values(?,?,?,now(),now(),'EDT',?)";

	@Override
	public long savePage(final String pagename,final String jspname,final long siteid,final String stype)
	{
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0)
					throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(SAVE_PAGE,
						new String[] { "id" });
				ps.setString(1, pagename);
				ps.setString(2, jspname);
				ps.setLong(3, siteid);
				ps.setString(4, stype);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
		
		
	}

	private static final String FIND_PAGE_BY_ID = "select p.*,g.entityid,g.groupName from esite.es_page p join es_site s on p.siteid = s.id join es_site_group g on s.sitegroupid = g.id where p.id = ?";

	@Override
	public Page findPageById(long pageid)
	{
		Object[] params =
		{ pageid };
		List<Page> list = getJdbcTemplate().query(FIND_PAGE_BY_ID, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				Page p = new Page();
				p.setId(rs.getLong("id"));
				p.setName(rs.getString("name"));
				p.setJspname(rs.getString("jspname"));
				p.setSiteid(rs.getLong("siteid"));
				p.setCreatetime(rs.getTimestamp("createtime"));
				p.setUpdatetime(rs.getTimestamp("updatetime"));
				p.setStatus(rs.getString("status"));
				p.setType(rs.getString("type"));
				p.setStype(rs.getString("stype"));
				p.setIsonline(rs.getString("isonline"));
				p.setWol(rs.getString("wol"));
				p.setRelationid((Long) rs.getObject("relationid"));
				p.setContextid((Long) rs.getObject("contextid"));
				p.setSubsina(rs.getString("subsina"));
				p.setSubweixin(rs.getString("subweixin"));
				p.setBg(rs.getString("bg"));
				p.setJspstyle(rs.getString("jspstyle"));
				p.setEntityid(rs.getLong("entityid"));
				p.setParamValue(rs.getString("paramvalue"));
				p.setApptype(rs.getString("apptype"));
				p.setSitename(rs.getString("g.groupName"));
				return p;
			}
		});
		if (list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}

	private static final String UPDATE_PAGE = "update esite.es_page set name = ? , jspname= ?,istemplate=?,ownertempid=? where id = ? ";

	@Override
	public int updatePage(String pagename, String jspname, long pageid,String istemplate,String ownertempid)
	{
		Object[] params =
		{ pagename, jspname, istemplate,ownertempid, pageid};
		return getJdbcTemplate().update(UPDATE_PAGE, params);
	}

	private static final String DELETE_PAGE = "update esite.es_page set status = 'DEL' where id = ?";

	@Override
	public int deletePage(long pageid)
	{
		Object[] params =
		{ pageid };
		return getJdbcTemplate().update(DELETE_PAGE, params);
	}

	@Override
	public Page findHomePageBySiteid(long siteid)
	{
		List<Page> ls = getJdbcTemplate().query("select * from esite.es_page where siteid = ? and status != 'DEL' and type='H'", new Object[]
		{ siteid }, new PageRowmapper());
		if (ls != null && ls.size() > 0)
		{
			return ls.get(0);
		}
		return null;
	}

	private static final String UPDATE_NO_HOME="update esite.es_page set type='N' where siteid = ? and type='H'";
	private static final String UPDATE_HOME="update esite.es_page set type='H' where id=?";
	@Override
	public int updateHome(long siteid,long pageid) {
		getJdbcTemplate().update(UPDATE_NO_HOME,new Object[]{siteid});
		return getJdbcTemplate().update(UPDATE_HOME,new Object[]{pageid});
	}

	private static final String FIND_ONWER_BY_PAGEID="select site.ownerid from esite.es_page page join esite.es_site site on site.id = page.siteid  where page.id = ?";
	@Override
	public long findOwnerByPageid(long pageid) {
		Object[] params={pageid};
		try {
			return getJdbcTemplate().queryForLong(FIND_ONWER_BY_PAGEID,params);
		} catch (DataAccessException e) {
			return 0;
		}
	}

	private static final String FIND_ACTIVITY_BY_PAGEID="select a.* from esite.es_page p join esite.es_site s on p.siteid = s.id join esite.es_activity a on s.id = a.siteid where p.id = ? and a.status!='DEL'";
	@Override
	public List<Activity> findActivityByPageid(long pageid) {
		Object[] params={pageid};
		return getJdbcTemplate().query(FIND_ACTIVITY_BY_PAGEID, params, new ActivityRowmapper());
	}
	
	class ActivityRowmapper implements RowMapper{
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Activity activity = new Activity();
			activity.setId(rs.getLong("id"));
			activity.setName(rs.getString("name"));
			activity.setSiteid(rs.getLong("siteid"));
			activity.setCreatetime(rs.getTimestamp("createtime"));
			activity.setStatus(rs.getString("status"));
			return activity;
		}
		
	}

	@Override
	public List<TemplateModel> findPageTemplate(long ownerid) {
		String sql="select  id,name, tempid,style from es_owner_template where ownerid=?";
		return getJdbcTemplate().query(sql, new Object[]{ownerid},new PageTemplateRowmapper());
	}
	class PageTemplateRowmapper implements RowMapper{
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			TemplateModel model = new TemplateModel();
			model.setId(rs.getLong("id"));
			model.setTempid(rs.getLong("tempid"));
			model.setName(rs.getString("name"));
			model.setStyle(rs.getString("style"));
			return model;
		}
		
	}

	private static final String FIND_TOKEN_BY_PAGEID_AND_WBUID="select token from esite.es_page p join esite.es_user_info u on p.siteid = u.siteid where p.id = ? and u.wbuid = ? limit 1";
	@Override
	public String findTokenByPageidAndWbuid(long pageid, long wbuid) {
		Object[] params={pageid,wbuid};
		try {
			return (String) getJdbcTemplate().queryForObject(FIND_TOKEN_BY_PAGEID_AND_WBUID, params, String.class);
		} catch (DataAccessException e) {
			return null;
		}
	}

	private static final String FIND_MY_TEMPLATE_BY_PAGEID="select t.cpath,t.ppath,ot.style from es_page p join es_owner_template ot on p.ownertempid = ot.id join es_template t on ot.tempid = t.id where p.id = ?";
	@Override
	public MyTempalte findMyTemplateByPageid(long pageid) {
		Object[] params={pageid};
		List<MyTempalte> list = getJdbcTemplate().query(FIND_MY_TEMPLATE_BY_PAGEID, params, new MyTemplateRowmapper());
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	class MyTemplateRowmapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			MyTempalte mt = new MyTempalte();
			mt.setCpath(rs.getString("cpath"));
			mt.setPpath(rs.getString("ppath"));
			mt.setStyle(rs.getString("style"));
			return mt;
		}
		
	}

	private static final String FIND_TEMPLATE_FEATURES_BY_TID="select f.tempid,f.featureid,f.featurename from esite.es_owner_template ot join esite.es_template_feature f on ot.tempid = f.tempid where ot.id = ? order by f.idx ";
	@Override
	public List<TemplateFeature> findTemplateFeaturesByOTid(long otid) {
		Object[] params={otid};
		return getJdbcTemplate().query(FIND_TEMPLATE_FEATURES_BY_TID,params,new TemplateFeatureRowmapper());
	}
	
	class TemplateFeatureRowmapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			TemplateFeature tf = new TemplateFeature();
			tf.setTempid(rs.getLong("tempid"));
			tf.setFid(rs.getLong("featureid"));
			tf.setFeaturename(rs.getString("featurename"));
			return tf;
		}
		
	}

	private static final String FIND_PAGE_COUNT="select count(id) from esite.es_page where siteid = ?  and (relationid is null and contextid is null) order by id desc"; 
	@Override
	public int findPageCountBySiteId(long siteid) {
		return getJdbcTemplate().queryForInt(FIND_PAGE_COUNT, new Object[]{siteid});
	}

	@Override
	public long saveNewPage(final Page page)
	{
		final String sql="insert into esite.es_page(name,jspname,jspstyle,siteid,createtime,updatetime,stype,type,relationid,contextid,bg,paramValue,apptype) values(?,?,?,?,now(),now(),?,?,?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0)
					throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(sql,
						new String[] { "id" });
				ps.setString(1, page.getName());
				ps.setString(2, page.getJspname());
				ps.setString(3, page.getJspstyle());
				ps.setLong(4, page.getSiteid());
				ps.setString(5, page.getStype());
				ps.setString(6, page.getType());
				if(page.getRelationid() == null){
					ps.setNull(7,java.sql.Types.BIGINT);
				}else{
					ps.setLong(7,page.getRelationid());
				}
				if(page.getContextid() == null){
					ps.setNull(8,java.sql.Types.BIGINT);
				}else{
					ps.setLong(8, page.getContextid());
				}
				ps.setString(9, page.getBg());
				ps.setString(10, page.getParamValue());
				ps.setString(11, page.getApptype());
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().longValue();
		return id;
		
	}
	
	@Override
	public List<Page> findPageByRelationid(long relationid)
	{
		Object[] params = { relationid };
		return getJdbcTemplate().query("select * from esite.es_page where relationid = ?", params, new PageRowmapper());
	}
	
	@Override
	public long saveNewPage(final String pagename,final String jspname,final long siteid,final String stype,final String type)
	{
		final String sql="insert into esite.es_page(name,jspname,siteid,createtime,updatetime,status,stype,type) values(?,?,?,now(),now(),'EDT',?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0)
					throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(sql,
						new String[] { "id" });
				ps.setString(1, pagename);
				ps.setString(2, jspname);
				ps.setLong(3, siteid);
				ps.setString(4, stype);
				ps.setString(5, type);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
		
	}

	@Override
	public int savePageOnlineLog(long pageid, String status)
	{
		String sql="insert into es_page_online(pageid,status,createtime) values(?,?,now())";
		return getJdbcTemplate().update(sql,new Object[]{pageid,status});
	}

	@Override
	public int updateOffline(long pageid)
	{
		String sql="update es_page set isonline='N' where id=?";
		return getJdbcTemplate().update(sql,new Object[]{pageid});
	}

	@Override
	public int updateOnline(long pageid)
	{
		String sql="update es_page set isonline='Y',wol='Y',onlinetime=now() where id=?";
		return getJdbcTemplate().update(sql,new Object[]{pageid});
	}

	@Override
	public int updatePageName(long pageid, String name)
	{
		String sql="update es_page set name=? where id=?";
		return getJdbcTemplate().update(sql,new Object[]{name,pageid});
	}

	private static final String FIND_SITE_PAGE_BY_PAGEID = "select * from es_page  where siteid = (select siteid from es_page where id = ?) and status != 'DEL' and (relationid is null and contextid is null) order by id desc";
	@Override
	public List<Page> findSitePagesByOnePageOfSite(long pageid) {
		Object[] params ={ pageid };
		return getJdbcTemplate().query(FIND_SITE_PAGE_BY_PAGEID, params, new PageRowmapper());
	}

	@Override
	public String findHtmlByPage(long pageid, String type)
	{
		Object[] params =
		{ pageid ,type};
		List<String> list = getJdbcTemplate().query("select html from esite.es_page_html where pageid = ? and type=?", params, new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				return rs.getString("html");
			}
			
		});
		if (list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}

	private static final String SAVE_PAGE_BG="update es_page set bg = ? where id = ?";
	@Override
	public int savePageBg(long pageid, String json) {
		Object[] params={json,pageid};
		return getJdbcTemplate().update(SAVE_PAGE_BG, params);
	}

	private static final String FIND_PAGE_BG="select ifnull(bg,'') from es_page where id = ?";
	@Override
	public String findPageBg(long pageid) {
		Object[] params={pageid};
		try {
			return (String) getJdbcTemplate().queryForObject(FIND_PAGE_BG,params, String.class);
		} catch (DataAccessException e) {
			return null;
		}
	}

	private static final String SAVE_PAGE_HTML="insert into es_page_html(pageid,type,html,js,css)values(?,'E',?,?,?)";
	@Override
	public int savePageHtml(long pageid, String html,String js,String css) {
		Object[] params={pageid,html,js,css};
		return getJdbcTemplate().update(SAVE_PAGE_HTML, params);
	}

	@Override
	public List<PageAddress> findAddressList(long pageid)
	{
		String sql="select *  from es_page_address where pageid=? and status!='DEL'  group by address";
		return getJdbcTemplate().query(sql, new Object[]{pageid},new PageAddressRowmapper());
	}
	
	class PageAddressRowmapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			PageAddress pa = new PageAddress();
			pa.setId(rs.getLong("id"));
			pa.setPageid(rs.getLong("pageid"));
			pa.setName(rs.getString("name"));
			pa.setSource(rs.getString("source"));
			pa.setWeixin(rs.getString("weixin"));
			pa.setAddress(rs.getString("address"));
			return pa;
		}
	}
	
	@Override
	public int findSourceExit(long pageid, String source)
	{
		String sql="select count(id) from es_page_address where pageid=? and source=? and status!='DEL'";
		return getJdbcTemplate().queryForInt(sql,new Object[]{pageid,source});
	}

	@Override
	public int savePageAddress(PageAddress pa)
	{
		String sql="insert ignore into es_page_address (pageid,name,source,createtime,weixin,address) values(?,?,?,now(),?,?)";
		return getJdbcTemplate().update(sql,new Object[]{pa.getPageid(),pa.getName(),pa.getSource(),pa.getWeixin(),pa.getAddress()});
	}


	@Override
	public int updatehiddenBlk(long relationid)
	{
		String sql="update esite.es_page_block_relation set display='N' where id=?";
		return getJdbcTemplate().update(sql, new Object[]{relationid});
	}
	
	@Override
	public int updateshowBlk(long pcid)
	{
		String sql = "update esite.es_page_block_relation set display='Y' where pcid =?";
		return getJdbcTemplate().update(sql, new Object[]{ pcid });
	}

	@Override
	public PageAddress findPageAddress(long pageid)
	{
		String sql="select * from es_page_address where pageid=? and name='Î¢²©' and status!='DEL'";
		List<PageAddress> list=getJdbcTemplate().query(sql, new Object[]{pageid},new PageAddressRowmapper());
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	@Override
	public int updateisshowMenu(long pcid,String isshow)
	{
		String sql = "update esite.es_page_card set isshow=? where id =?";
		return getJdbcTemplate().update(sql, new Object[]{isshow, pcid });
	}

	private static final String FIND_JSPSTYLE_BY_PCID="select p.jspstyle from es_page p join es_page_card pc on p.id = pc.pageid where pc.id = ?";
	@Override
	public String findJspstyleByPcid(long pcid) {
		Object[] params={pcid};
		try {
			return (String) getJdbcTemplate().queryForObject(FIND_JSPSTYLE_BY_PCID, params, String.class);
		} catch (DataAccessException e) {
			return null;
		}
	}

	@Override
	public int updatePageAddressStatus(long pageid)
	{
		String sql="update es_page_address set status='DEL' where pageid=? and name='Î¢²©'";
		return getJdbcTemplate().update(sql,new Object[]{pageid});
	}

	@Override
	public Page findPageSubweixin(long pageid)
	{
		String sql="select subweixin from es_page where id=?";
		List<Page> list=getJdbcTemplate().query(sql, new Object[]{pageid},new Page1Rowmapper());
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	} 
	class Page1Rowmapper implements RowMapper
	{
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			Page p = new Page();
			p.setSubweixin(rs.getString("subweixin"));
			return p;
		}

	}


	@Override
	public String findPageName(long pageid)
	{
		String sql="select name from es_page where id=?";
		try {
			return (String) getJdbcTemplate().queryForObject(sql, new Object[]{pageid}, String.class);
		} catch (DataAccessException e) {
			return null;
		}
	}

	@Override
	public List<WeiXinPage> findWeiXinPageList(long pageid)
	{
		String sql="select id from es_wx_page_show where pageid=?";
		return getJdbcTemplate().query(sql, new Object[]{pageid},new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1)
					throws SQLException {
				WeiXinPage page = new WeiXinPage();
				page.setId(rs.getLong("id"));
				return page;
			}
		});
	}

	@Override
	public int updateHtml(long pageid, String type, String html)
	{
		String sql = "update es_page_html set html = ? where pageid = ? and type = ?";
		return getJdbcTemplate().update(sql, new Object[]{html,pageid,type});
	}

	@Override
	public int savePageTemplate(PageTemplate pt)
	{
		String sql="insert into es_page_template (pageid,name,img,createtime) values(?,?,?,now()) on duplicate key update name=?,img=?";
		Object[] params ={pt.getPageid(),pt.getName(),pt.getImg(),pt.getName(),pt.getImg()};
		return getJdbcTemplate().update(sql,params);
	}

	@Override
	public List<Long> findPageIdBySiteid(long site)
	{
		String sql="select id  from esite.es_page  where siteid = ? and status != 'DEL' and relationid is null and contextid is null   order by id desc";
		Object[] params ={ site };
		return getJdbcTemplate().query(sql, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				return rs.getLong("id");
			}
		});
	
	}
	@Override
	public List<PageRelation> findPageRelationListBySiteid(long siteid)
	{
		String sql = "select distinct r.fapageid,r.pageid from es_site s join es_page p on s.id = p.siteid join es_block_2_page r on r.fapageid = p.id where s.id = ?";
		Object[] params = {siteid};
		return getJdbcTemplate().query(sql, params, new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				PageRelation r = new PageRelation();
				r.setFapageid(rs.getLong("fapageid"));
				r.setPageid(rs.getLong("pageid"));
				return r;
			}
			
		});
	}

	@Override
	public List<Long> findPageidByFapageid(long fapageid) {
		Object[] param = {fapageid};
		return getJdbcTemplate().query("SELECT pageid FROM esite.es_block_2_page WHERE fapageid=?",param, new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getLong("pageid");
			}
			
		});
	}

	@Override
	public List<Page> findOnlinePageByOwnerid(long ownerid, String start, String end) {
		Object[] param = {ownerid, start, end};
		String sql = "SELECT p.*,t.name as sitename FROM (SELECT s.* FROM esite.es_site s LEFT JOIN esite.es_site_group g ON g.id=s.sitegroupid WHERE g.ownerid=?) t LEFT JOIN esite.es_page p ON t.id=p.siteid WHERE p.`status`!='DEL' AND p.isonline='Y' AND p.onlinetime BETWEEN ? AND ?";
		return getJdbcTemplate().query(sql,param, new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				Page page = new Page();
				page.setId(rs.getLong("id"));
				page.setName(rs.getString("name"));
				page.setJspname(rs.getString("sitename"));
				page.setUpdatetime(rs.getTimestamp("onlinetime"));
				return page;
			}
			
		});
	}

	@Override
	public Page findPageByPageid(long pageid) {
		Object[] param = {pageid};
		String sql = "SELECT p.*,s.name as sitename FROM esite.es_page p LEFT JOIN esite.es_site s ON s.id=p.siteid WHERE p.id=?";
		List<Page> list =  getJdbcTemplate().query(sql,param, new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				Page page = new Page();
				page.setId(rs.getLong("id"));
				page.setName(rs.getString("name"));
				page.setSiteid(rs.getInt("siteid"));
				page.setJspstyle(rs.getString("jspstyle"));
				page.setStype(rs.getString("stype"));
				page.setBg(rs.getString("bg"));
				page.setJspname(rs.getString("sitename"));
				page.setUpdatetime(rs.getTimestamp("onlinetime"));
				return page;
			}
			
		});
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	private static final String FIND_PAGE_PARAM="select json from es_page_param where jspname = ?";
	@Override
	public String findPageParam(String jspname) {
		Object[] param={jspname};
		try {
			return (String) getJdbcTemplate().queryForObject(FIND_PAGE_PARAM, param, String.class);
		} catch (DataAccessException e) {
			return null;
		}
	}

	private static final String UPDATE_PAGE_PARAMS="update es_page set paramvalue = ? where id = ?";
	@Override
	public int updatePageParams(long pageid, String json) {
		Object[] params={json,pageid};
		return getJdbcTemplate().update(UPDATE_PAGE_PARAMS,params);
	}

	private static final String SAVE_PAGE_PARAM="insert into es_page_param(jspname,json) values(?,?) on duplicate key update json = ? ";
	@Override
	public int savePageParam(String jspname, String param) {
		Object[] params={jspname,param,param};
		return getJdbcTemplate().update(SAVE_PAGE_PARAM, params);
	}

	private static final String FIND_JSPNAME="select id , jspname from es_page where siteid = ? and status != 'DEL' ";
	@Override
	public JSONObject findJspname(long siteid) {
		Object[] param={siteid};
		final JSONObject jo = new JSONObject();
		getJdbcTemplate().query(FIND_JSPNAME, param, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				jo.element(rs.getString("jspname"), rs.getLong("id"));
				return jo;
			}
			
		});
		return jo;
	}

	private static final String DELETE_RELATION="delete from es_page_block_relation where id = ?";
	@Override
	public int deleteRelation(long relationid) {
		Object[] param={relationid};
		return getJdbcTemplate().update(DELETE_RELATION, param);
	}

	private static final String FIND_CBID_BY_RELATIONID="select cbid from es_page_block_relation where id = ?";
	@Override
	public long findCbidByRelationid(long relationid) {
		Object[] params={relationid};
		try {
			return getJdbcTemplate().queryForLong(FIND_CBID_BY_RELATIONID, params);
		} catch (DataAccessException e) {
			return 0;
		}
	}

	private static final String DELETE_CBID="delete from es_template_card_block where id = ? and alone='Y' ";
	@Override
	public int deleteCbid(long cbid) {
		Object[] param={cbid};
		return getJdbcTemplate().update(DELETE_CBID, param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Page> findPagesByName(long ownerid, String name, int start, int rows) {
		String sql_count = "SELECT count(1) FROM es_page p, es_site s, es_site_group g WHERE g.ownerid = ? AND g.`status` != 'DEL' AND s.sitegroupid = g.id AND s.`status` != 'DEL' AND p.siteid = s.id AND p.`status` != 'DEL' AND p.isonline = 'Y' AND p.relationid IS NULL AND p.contextid IS NULL AND p.`name` LIKE ?";
		Object[] param_count = { ownerid, "%" + name + "%" };
		final int count = getJdbcTemplate().queryForInt(sql_count, param_count);

		String sql = "SELECT p.*,s.`name` AS sitename FROM es_page p, es_site s, es_site_group g WHERE g.ownerid = ? AND g.`status` != 'DEL' AND s.sitegroupid = g.id AND s.`status` != 'DEL' AND p.siteid = s.id AND p.`status` != 'DEL' AND p.isonline = 'Y' AND p.relationid IS NULL AND p.contextid IS NULL AND p.`name` LIKE ? LIMIT ?,?";
		Object[] params = { ownerid, "%" + name + "%", start, rows };
		return getJdbcTemplate().query(sql, params, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				Page page = new Page();
				page.setId(rs.getLong("id"));
				page.setName(rs.getString("name"));
				page.setSiteid(rs.getInt("siteid"));
				page.setJspname(rs.getString("sitename"));
				page.setUpdatetime(rs.getTimestamp("onlinetime"));
				page.setEntityid(count);
				return page;
			}

		});
	}

	private static final String FIND_PAGES_BY_MBID="select m.pageid from es_mb_page m where m.mbid = ?";
	@Override
	public List<Long> findPagesByMbid(long mbid)
	{
		Object[] params={mbid};
		try
		{
			return getJdbcTemplate().queryForList(FIND_PAGES_BY_MBID, params,Long.class);
		} catch (DataAccessException e)
		{
			return null;
		}
	}

	private static final String FIND_PAGE_BY_SITEID_AND_APPTYPE = "select *  from esite.es_page  where siteid = ? and status != 'DEL' and (relationid is null and contextid is null) and apptype = ?";
	@Override
	public Page findPageBySiteidAndApptype(long siteid, String apptype)
	{
		Object[] params ={ siteid,apptype };
		List<Page> list = getJdbcTemplate().query(FIND_PAGE_BY_SITEID_AND_APPTYPE, params, new PageRowmapper());
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	private static final String FIND_FATHER_PAGEID="select pc.pageid from es_page p join es_page_block_relation r on p.relationid = r.id join es_page_card pc on r.pcid = pc.id where p.id= ?";
	@Override
	public long findFatherPageid(long childPageid)
	{
		Object[] param={childPageid};
		try
		{
			return getJdbcTemplate().queryForLong(FIND_FATHER_PAGEID,param);
		} catch (DataAccessException e)
		{
			return 0;
		}
	}

	private static final String FIND_PAGES_BY_CCID="select pageid from es_feature_contentlist where categoryid = ?";
	@Override
	public List<Long> findPagesByCcid(long ccid)
	{
		Object[] param={ccid};
		return getJdbcTemplate().queryForList(FIND_PAGES_BY_CCID, param, Long.class);
	}

	private static final String FIND_NEWS_PAGES_BY_CCID="select pageid from es_feature_newslist where categoryid = ?";
	@Override
	public List<Long> findNewsPagesByCcid(long ccid)
	{
		Object[] param={ccid};
		return getJdbcTemplate().queryForList(FIND_NEWS_PAGES_BY_CCID, param, Long.class);
	}

	private static final String FIND_PICTURE_PAGES_BY_CCID="select pageid from es_feature_piclist where categoryid = ?";
	@Override
	public List<Long> findPicturePagesByCcid(long ccid)
	{
		Object[] param={ccid};
		return getJdbcTemplate().queryForList(FIND_PICTURE_PAGES_BY_CCID, param, Long.class);
	}

	private static final String FIND_PRODUCT_PAGES_BY_CCID="select pageid from es_feature_contentlist where categoryid = ?";
	@Override
	public List<Long> findProductPagesByCcid(long ccid)
	{
		Object[] param={ccid};
		return getJdbcTemplate().queryForList(FIND_PRODUCT_PAGES_BY_CCID, param, Long.class);
	}

	private static final String FIND_VEDIO_PAGES_BY_CCID="select pageid from es_feature_contentlist where categoryid = ?";
	@Override
	public List<Long> findVedioPagesByCcid(long ccid)
	{
		Object[] param={ccid};
		return getJdbcTemplate().queryForList(FIND_VEDIO_PAGES_BY_CCID, param, Long.class);
	}
}
