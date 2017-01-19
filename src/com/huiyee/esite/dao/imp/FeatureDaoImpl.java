package com.huiyee.esite.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.dao.IFeatureDao;
import com.huiyee.esite.dto.HdType;
import com.huiyee.esite.dto.QueryDto;
import com.huiyee.esite.model.Feature;
import com.huiyee.esite.model.PageFeature;
import com.huiyee.esite.model.SinaChecklistRecord;
import com.huiyee.esite.model.SinaUser;
import com.huiyee.esite.model.SinaUserApp;
import com.huiyee.esite.model.UploadDetail;
import com.huiyee.esite.model.ZanDetail;
import com.huiyee.interact.cb.model.InteractCb;
import com.huiyee.interact.renqi.model.RenQi;

public class FeatureDaoImpl extends AbstractDao implements IFeatureDao {

	private static final String FIND_FEATURE_BY_PAGEID = "select pf.id pfid, f.id,f.name,pf.name fname,f.type,pf.createtime,pf.status,pf.fid ,pf.idx from esite.es_page_feature pf join esite.es_feature f on pf.featureid = f.id where pf.pageid = ? order by pf.idx ";

	@Override
	public List<Feature> findFeatureByPageId(long pageid) {
		Object[] params = { pageid };
		return getJdbcTemplate().query(FIND_FEATURE_BY_PAGEID, params, new FeatureRowmapper());
	}

	class FeatureRowmapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Feature f = new Feature();
			f.setId(rs.getLong("id"));
			f.setName(rs.getString("name"));
			f.setFname(rs.getString("fname"));
			f.setType(rs.getString("type"));
			f.setCreatetime(rs.getTimestamp("createtime"));
			f.setStatus(rs.getString("status"));
			f.setFid(rs.getLong("fid"));
			f.setIdx(rs.getInt("idx"));
			f.setPfid(rs.getLong("pfid"));
			return f;
		}

	}
	
	private static final String FIND_NEEDUSER_FEATURE_BY_PAGEID = "select featureid,fid,idx from es_page_feature where pageid = ? and needuser ='Y' order by idx ";

	@Override
	public List<Feature> findNeedUserFeatureByPageId(long pageid) {
		Object[] params = { pageid };
		return getJdbcTemplate().query(FIND_NEEDUSER_FEATURE_BY_PAGEID, params, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				Feature f = new Feature();
				f.setId(rs.getLong("featureid"));
				f.setFid(rs.getLong("fid"));
				f.setIdx(rs.getInt("idx"));
				return f;
			}
			
		});
	}

	private static final String FIND_FEATURE_BY_SITEID = "select f.id,f.name from esite.es_page p join esite.es_module_site s on p.siteid = s.siteid join esite.es_feature f on s.mmid = f.mmid where p.id = ? and f.type='S'";

	@Override
	public List<Feature> findModuleFeaturesByPageid(long pageid) {
		Object[] params = { pageid };
		return getJdbcTemplate().query(FIND_FEATURE_BY_SITEID, params, new FeatureRowmapper1());
	}

	class FeatureRowmapper1 implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Feature f = new Feature();
			f.setId(rs.getLong("id"));
			f.setName(rs.getString("name"));
			return f;
		}

	}

	private static final String FIND_FEATURE_BY_SITE_GROUPID = "select count(fpz.uid) count,max(fpz.createtime) updatetime from es_site_group sg join es_site s on sg.id = s.sitegroupid join es_page p on s.id = p.siteid join es_feature_prolist_list fpl on p.id = fpl.pageid join es_feature_prolist fp on fpl.id = fp.prolist_listid join es_feature_prolist_product fpp on fp.id=fpp.prolistid join es_feature_product_zan fpz on fpp.id = fpz.zanproid where sg.id = ?";

	@Override
	public Map<String, Object> findZanBySitegroupid(long sitegroupid) {
		Object[] params = { sitegroupid };
		return getJdbcTemplate().queryForMap(FIND_FEATURE_BY_SITE_GROUPID, params);
	}

	private static final String FIND_FEATURE_BY_SITE_ID = "select count(fpz.uid) count,max(fpz.createtime) updatetime from es_site s  join es_page p on s.id = p.siteid join es_feature_prolist_list fpl on p.id = fpl.pageid join es_feature_prolist fp on fpl.id = fp.prolist_listid join es_feature_prolist_product fpp on fp.id=fpp.prolistid join es_feature_product_zan fpz on fpp.id = fpz.zanproid where s.id = ?";

	@Override
	public Map<String, Object> findZanBySiteId(long siteid) {
		Object[] params = { siteid };
		return getJdbcTemplate().queryForMap(FIND_FEATURE_BY_SITE_ID, params);
	}

	private static final String FIND_DYNAMIC_FEATURE = "select * from esite.es_feature where type='D'";

	@Override
	public List<Feature> findDynameicFeature() {
		return getJdbcTemplate().query(FIND_DYNAMIC_FEATURE, new FeatureRowmapper1());
	}

	private static final String FIND_DYNAMIC_FEATURE_SITEGROUPID = "select distinct f.name,f.id from esite.es_site_group sg join esite.es_site s on sg.id = s.sitegroupid join esite.es_module_site ms on s.id = ms.siteid join esite.es_module_model mm on mm.id = ms.mmid join esite.es_feature f on f.mmid = mm.id where sg.id = ? and f.type='D'";

	@Override
	public List<Feature> findDynameicFeature(long sitegroupid) {
		Object[] params = { sitegroupid };
		return getJdbcTemplate().query(FIND_DYNAMIC_FEATURE_SITEGROUPID, params, new FeatureRowmapper1());
	}

	private static final String FIND_UPLOAD_BY_SITE_GROUPID = "select count(fuur.id) count,max(fuur.uploadtime) updatetime from es_site_group sg join es_site s on sg.id = s.sitegroupid join es_page p on s.id = p.siteid join es_feature_user_upload fuu on fuu.pageid = p.id join es_feature_user_upload_record fuur on fuur.uploadid = fuu.id where sg.id = ?";

	@Override
	public Map<String, Object> findUploadBySitegroupid(long sitegroupid) {
		Object[] params = { sitegroupid };
		return getJdbcTemplate().queryForMap(FIND_UPLOAD_BY_SITE_GROUPID, params);
	}

	private static final String FIND_UPLOAD_BY_SITEID = "select count(fuur.id) count,max(fuur.uploadtime) updatetime from es_site s join es_page p on s.id = p.siteid join es_feature_user_upload fuu on fuu.pageid = p.id join es_feature_user_upload_record fuur on fuur.uploadid = fuu.id where s.id = ?";

	@Override
	public Map<String, Object> findUploadBySiteId(long siteid) {
		Object[] params = { siteid };
		return getJdbcTemplate().queryForMap(FIND_UPLOAD_BY_SITEID, params);
	}

	private static final String FIND_ZAN_DETAIL_BY_SITE_GROUPID = "select fpz.uid,user.nickname,fpp.productid,product.name,product.linkurl,fpz.createtime from es_site_group sg join es_site s on sg.id = s.sitegroupid join es_page p on s.id = p.siteid join es_feature_prolist_list fpl on p.id = fpl.pageid join es_feature_prolist fp on fpl.id = fp.prolist_listid join es_feature_prolist_product fpp on fp.id=fpp.prolistid join es_feature_product_zan fpz on fpp.id = fpz.zanproid join es_user_info user on fpz.uid = user.id join es_content_product product on product.id =fpp.productid where sg.id = ?";

	@Override
	public List<ZanDetail> findZanDetailBySitegroupid(long sitegroupid) {
		Object[] params = { sitegroupid };
		return getJdbcTemplate().query(FIND_ZAN_DETAIL_BY_SITE_GROUPID, params, new ZanDetailRowmapper());
	}

	private static final String FIND_ZAN_DETAIL_BY_SITEID = "select fpz.uid,user.nickname,fpp.productid,product.name,product.linkurl,fpz.createtime from es_site s join es_page p on s.id = p.siteid join es_feature_prolist_list fpl on p.id = fpl.pageid join es_feature_prolist fp on fpl.id = fp.prolist_listid join es_feature_prolist_product fpp on fp.id=fpp.prolistid join es_feature_product_zan fpz on fpp.id = fpz.zanproid join es_user_info user on fpz.uid = user.id join es_content_product product on product.id =fpp.productid where s.id = ?";

	@Override
	public List<ZanDetail> findZanDetailBySiteId(long siteid) {
		Object[] params = { siteid };
		return getJdbcTemplate().query(FIND_ZAN_DETAIL_BY_SITEID, params, new ZanDetailRowmapper());
	}

	class ZanDetailRowmapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			ZanDetail zd = new ZanDetail();
			zd.setUid(rs.getLong("uid"));
			zd.setNickname(rs.getString("nickname"));
			zd.setProductid(rs.getLong("productid"));
			zd.setProductname(rs.getString("name"));
			zd.setProductlinkurl(rs.getString("linkurl"));
			zd.setCreatetime(rs.getTimestamp("createtime"));
			return zd;
		}

	}

	private static final String FIND_UPLOAD_DETAIL_BY_SITE_GROUPID = "select fuur.uid,user.nickname,fuur.smallimg,fuur.bigimg,fuur.uploadtime createtime,fuur.content from es_site_group sg join es_site s on sg.id = s.sitegroupid join es_page p on s.id = p.siteid join es_feature_user_upload fuu on fuu.pageid = p.id join es_feature_user_upload_record fuur on fuur.uploadid = fuu.id join es_user_info user on fuur.uid = user.id where sg.id = ?";

	@Override
	public List<UploadDetail> findUploadDetailBySitegroupid(long sitegroupid) {
		Object[] params = { sitegroupid };
		return getJdbcTemplate().query(FIND_UPLOAD_DETAIL_BY_SITE_GROUPID, params, new UploadDetailRowmapper());
	}

	private static final String FIND_UPLOAD_DETAIL_BY_SITEID = "select fuur.uid,user.nickname,fuur.smallimg,fuur.bigimg,fuur.uploadtime createtime,fuur.content from es_site s join es_page p on s.id = p.siteid join es_feature_user_upload fuu on fuu.pageid = p.id join es_feature_user_upload_record fuur on fuur.uploadid = fuu.id join es_user_info user on fuur.uid = user.id where s.id = ?";

	@Override
	public List<UploadDetail> findUploadDetailBySiteId(long siteid) {
		Object[] params = { siteid };
		return getJdbcTemplate().query(FIND_UPLOAD_DETAIL_BY_SITEID, params, new UploadDetailRowmapper());
	}

	class UploadDetailRowmapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			UploadDetail ud = new UploadDetail();
			ud.setUid(rs.getLong("uid"));
			ud.setNickname(rs.getString("nickname"));
			ud.setSimg(rs.getString("smallimg"));
			ud.setBimg(rs.getString("bigimg"));
			ud.setContent(rs.getString("content"));
			ud.setCreatetime(rs.getTimestamp("createtime"));
			return ud;
		}

	}

	private static final String FIND_ZAN_TOTAL = "select id ,zantotal from esite.es_feature_prolist_product where id = ?";

	@Override
	public Map<String, Object> findZanTotal(long pfid) {
		Object[] params = { pfid };
		return getJdbcTemplate().queryForMap(FIND_ZAN_TOTAL, params);
	}

	private static final String FIND_FEATURES = "select pf.id pfid, f.id,f.name,pf.name fname,f.type,pf.createtime,pf.status,pf.fid ,pf.idx from esite.es_page_feature pf join esite.es_feature f on pf.featureid = f.id left join esite.es_page p on p.id=pf.pageid  where p.siteid = ? order by pf.idx ";

	@Override
	public List<Feature> findFeaturesBySiteId(long siteid) {
		Object[] params = { siteid };
		return getJdbcTemplate().query(FIND_FEATURES, params, new FeatureRowmapper());
	}

	private static final String FIND_SINA_ACCREDIT_BY_SITEGROUPID = "select count(sg.id) count,max(app.updatetime) updatetime from es_site_group sg join es_site s on sg.id = s.sitegroupid join es_sina_token token on token.siteid=s.id join es_feature_sina_user_app app on app.appid=token.appid   where sg.id=?";

	@Override
	public Map<String, Object> findSinaAccreditBySitegroupid(long sitegroupid) {
		Object[] params = { sitegroupid };
		return getJdbcTemplate().queryForMap(FIND_SINA_ACCREDIT_BY_SITEGROUPID, params);
	}

	private static final String FIND_SINA_SHARE_BY_SITEGROUPID = "select count(record.id) count,max(share.lastupdatetime) updatetime from  es_site_group sg join es_site s on sg.id=s.sitegroupid join es_page p on s.id = p.siteid join es_feature_sina_share share on share.pageid=p.id join es_feature_sina_share_record record on record.shareid=share.id where sg.id = ?";

	@Override
	public Map<String, Object> findSinaShareBySitegroupid(long sitegroupid) {
		Object[] params = { sitegroupid };
		return getJdbcTemplate().queryForMap(FIND_SINA_SHARE_BY_SITEGROUPID, params);
	}

	@Override
	public List<SinaUserApp> findSinaUsersDetail(long sitegroupid, QueryDto siftDto, int start, int size) {
		List<Object> list = new ArrayList<Object>();
		String sql = "select user.*,app.*   from es_site_group sg join es_site s on sg.id = s.sitegroupid join es_sina_token token on token.siteid=s.id join es_feature_sina_user_app app on app.appid=token.appid  join es_feature_sina_user user on user.wbuid=app.wbuid where sg.id=?";
		list.add(sitegroupid);
		if (!StringUtils.isEmpty(siftDto.getStarttime())) {
			sql += " and app.createtime >= ? ";
			list.add(siftDto.getStarttime());
		}
		if (!StringUtils.isEmpty(siftDto.getEndtime())) {
			sql += " and app.createtime <= ? ";
			list.add(siftDto.getEndtime());
		}
		if (!"-1".equals(siftDto.getTerminal())) {
			sql += " and app.terminal= ? ";
			list.add(siftDto.getTerminal());
		}
		sql += "  limit ?,? ";
		list.add(start);
		list.add(size);
		return getJdbcTemplate().query(sql, list.toArray(), new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				SinaUserApp user = new SinaUserApp();
				user.setNickname(rs.getString("user.nickname"));
				user.setCreatetime(rs.getTimestamp("app.createtime"));
				user.setIp(rs.getString("app.ip"));
				user.setTerminal(rs.getString("app.terminal"));
				return user;
			}
		});
	}

	public int findSinaUsersDetailTotal(long sitegroupid, QueryDto siftDto) {
		List<Object> list = new ArrayList<Object>();
		String sql = "select count(app.id)   from es_site_group sg join es_site s on sg.id = s.sitegroupid join es_sina_token token on token.siteid=s.id join es_feature_sina_user_app app on app.appid=token.appid  join es_feature_sina_user user on user.wbuid=app.wbuid where sg.id=?";
		list.add(sitegroupid);
		if (!StringUtils.isEmpty(siftDto.getStarttime())) {
			sql += " and app.createtime >= ? ";
			list.add(siftDto.getStarttime());
		}
		if (!StringUtils.isEmpty(siftDto.getEndtime())) {
			sql += " and app.createtime <= ? ";
			list.add(siftDto.getEndtime());
		}
		if (!"-1".equals(siftDto.getTerminal())) {
			sql += " and app.terminal= ? ";
			list.add(siftDto.getTerminal());
		}
		return getJdbcTemplate().queryForInt(sql, list.toArray());
	}

	public List<SinaChecklistRecord> findSinaShare(long sitegroupid, QueryDto siftDto, int start, int size) {
		List<Object> list = new ArrayList<Object>();
		String sql = "select record.*,user.*  from es_site_group  sg join es_site site on site.sitegroupid=sg.id join es_page page on page.siteid=site.id join es_feature_sina_share share on  share.pageid=page.id join es_feature_sina_share_record record on record.shareid=share.id left outer join es_feature_sina_user user on user.wbuid=record.wbuid where sg.id= ?";
		list.add(sitegroupid);
		if (!StringUtils.isEmpty(siftDto.getStarttime())) {
			sql += " and record.createtime >= ? ";
			list.add(siftDto.getStarttime());
		}
		if (!StringUtils.isEmpty(siftDto.getEndtime())) {
			sql += " and record.createtime <= ? ";
			list.add(siftDto.getEndtime());
		}
		if (!"ALL".equals(siftDto.getWbstatus())) {
			sql += " and record.status = ? ";
			list.add(siftDto.getWbstatus());
		}
		if (!"-1".equals(siftDto.getTerminal())) {
			sql += " and record.terminal = ? ";
			list.add(siftDto.getTerminal());
		}
		if (!"-1".equals(siftDto.getOrderType())) {
			sql += " order by "+siftDto.getOrderType()+" desc";
		}else{
			sql += " order by record.id desc";
		}
		sql += " limit ?,?";
		list.add(start);
		list.add(size);
		return getJdbcTemplate().query(sql, list.toArray(), new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				SinaChecklistRecord scr = new SinaChecklistRecord();
				SinaUser user = new SinaUser();
				user.setWbuid(rs.getLong("user.wbuid"));
				user.setNickname(rs.getString("user.nickname"));
				user.setImageurl(rs.getString("user.imageurl"));
				scr.setUser(user);
				scr.setContent(rs.getString("record.content"));
				scr.setImgPath(rs.getString("record.imgPath"));
				scr.setCreatetime(rs.getTimestamp("record.createtime"));
				scr.setAttitudesCount(rs.getInt("record.attitudesCount"));
				scr.setCommentsCount(rs.getInt("record.commentsCount"));
				scr.setRepostsCount(rs.getInt("record.repostsCount"));
				return scr;
			}
		});
	}

	@Override
	public int findSinaShareTotal(long sitegroupid, QueryDto siftDto) {
		List<Object> list = new ArrayList<Object>();
		String sql = "select count(*)  from es_site_group  sg join es_site site on site.sitegroupid=sg.id join es_page page on page.siteid=site.id join es_feature_sina_share_list fid on  fid.pageid=page.id join es_feature_sina_share_record record on record.shareid=fid.shareid left outer join es_feature_sina_user user on user.wbuid=record.wbuid where sg.id= ? ";
		list.add(sitegroupid);
		if (!StringUtils.isEmpty(siftDto.getStarttime())) {
			sql += " and record.createtime >= ? ";
			list.add(siftDto.getStarttime());
		}
		if (!StringUtils.isEmpty(siftDto.getEndtime())) {
			sql += " and record.createtime <= ? ";
			list.add(siftDto.getEndtime());
		}
		if (!"ALL".equals(siftDto.getWbstatus())) {
			sql += " and record.status = ? ";
			list.add(siftDto.getWbstatus());
		}
		if (!"-1".equals(siftDto.getTerminal())) {
			sql += " and record.terminal = ? ";
			list.add(siftDto.getTerminal());
		}
		return getJdbcTemplate().queryForInt(sql, list.toArray());
	}

	private static final String FIND_FEATURES_BY_MODULESID="select * from esite.es_feature where mmid = ? and type='S'";
	@Override
	public List<Feature> findFeaturesByModuleid(long mouleid) {
		Object[] params = { mouleid };
		return getJdbcTemplate().query(FIND_FEATURES_BY_MODULESID, params, new FeatureRowmapper1());
	}
	private static final String FIND_FEATURES_BY_ID="select * from esite.es_feature where id=?";
	@Override
	public Feature findFeaturesId(long id) {
		Object[] params = { id };
		List<Feature> list= getJdbcTemplate().query(FIND_FEATURES_BY_ID, params, new FeatureRowmapper1());
		if(list!=null&&list.size()>0)
			return list.get(0);
		return null;
	}

	 @Override
	    public HdType findHdType(long id) {
	        String sql="select act,hdid,name,lasthdtime from esite.es_hd_type where id=?";
	        Object[] params={id};
	        List<HdType> list= getJdbcTemplate().query(sql, params,new RowMapper(){
	            @Override
	            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
	                HdType type=new HdType();
	                type.setAct(rs.getLong("act"));
	                type.setHdid(rs.getLong("hdid"));
	                type.setName(rs.getString("name"));
	                type.setCreatetime(rs.getTimestamp("lasthdtime"));
	                return type;
	            }
	            
	        });
	        if(list!=null&&list.size()>0){
	            return list.get(0);
	        }
	        return null;
	    }
	
	private static final String FIND_FEATURE_BY_MODULE_AND_OWNER="select f.* from es_feature f join es_owner_feature of on f.id = of.featureid where f.mmid= ? and of.ownerid = ?";
	@Override
	public List<Feature> findFeaturesByModuleidAndOwnerid(long mouleid,
			long ownerid) {
		Object[] params = { mouleid,ownerid};
		return getJdbcTemplate().query(FIND_FEATURE_BY_MODULE_AND_OWNER, params, new FeatureRowmapper1());
	}
	 
	@Override
	public List<PageFeature> findPageFeature(long ownerid,int start,int size)
	{
		String sql="select pf.id id,pf.fid fid,pf.featureid featureid,pf.pageid pageid,pf.name name,f.name name2 from es_page_feature pf join es_owner_feature o on pf.featureid=o.featureid join es_feature f on o.featureid=f.id where o.ownerid=? order by pf.id limit ?,?";
		return getJdbcTemplate().query(sql, new Object[]{ownerid,start,size},new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				PageFeature pf=new PageFeature();
				pf.setFid(rs.getLong("fid"));
				pf.setId(rs.getLong("id"));
				pf.setFeatureid(rs.getLong("featureid"));
				pf.setPageid(rs.getLong("pageid"));
				pf.setName1(rs.getString("name"));
				pf.setName2(rs.getString("name2"));
				return pf;
			}
		});
	}

	@Override
	public int findPageFeatureFid(long ownerid)
	{
		String sql="select count(pf.fid) from  es_page_feature pf  join es_owner_feature o on pf.featureid=o.featureid  where o.ownerid=? ";
		return getJdbcTemplate().queryForInt(sql,new Object[]{ownerid});
	}
	
	@Override
	public Feature findPageFeatureByFid(long fid)
	{
		List<Feature> list=getJdbcTemplate().query("select * from es_page_feature where fid=?", new Object[]{fid}, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				Feature f=new Feature();
				f.setId(rs.getLong("id"));
				f.setIdx(rs.getInt("idx"));
				return f;
			}
		});
		return list.size()>0?list.get(0):null;
	}
	
	private static final String SAVE_PAGE_FEATURE="insert into es_page_feature (pageid,featureid,fid,createtime,updatetime,idx) values(?,?,?,now(),now(),?)";
	@Override
	public long savePageFeature(final long pageid,final long fid,final int idx,final int featureid)
	{
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator()
		{
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(SAVE_PAGE_FEATURE, new String[]
				{ "id" });
				int i = 1;
				ps.setLong(i++, pageid);
				ps.setInt(i++, featureid);
				ps.setLong(i++, fid);
				ps.setLong(i++, idx);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}
	
	@Override
	public long saveSimpleRq(final RenQi rq,final long owner)
	{
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator()
		{
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement("insert into es_interact_rq (title,owner,maxjf,starttime,endtime) values(?,?,0,now(),now())", new String[]
				{ "id" });
				int i = 1;
				ps.setString(i++, rq.getTitle());
				ps.setLong(i++, owner);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}
	
	@Override
	public long findRqFidByPage(long pageId)
	{
		List<Long> list=getJdbcTemplate().query("select id from es_feature_rq where pageid=?", new Object[]{pageId},new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				return rs.getLong("id");
			}
		});
		return list.size()==1?list.get(0):0;
	}
	
	@Override
	public long saveRQFid(final long pageId,final long rqid)
	{
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator()
		{
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement("insert into es_feature_rq (pageid,rqid,createtime) values (?,?,now())", new String[]
				{ "id" });
				int i = 1;
				ps.setLong(i++, pageId);
				ps.setLong(i++, rqid);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}
	
	@Override
	public int findMaxIdxByPageId(long pageid)
	{
		List<Integer> list=getJdbcTemplate().query("select max(idx) idx from es_page_feature where pageid=?",new Object[]{pageid}, new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				return rs.getInt("idx");
			}
		});
		return list.size()>0?list.get(0):0;
	}
	
	@Override
	public long findEntityId(long id, long fid)
	{
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	private static final String SAVE_XC_FID="insert into es_feature_interact_xc (pageid,xcid,createtime) values(?,?,now())";
	@Override
	public long saveXcFid(final long pageId,final long xcid)
	{
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator()
		{
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(SAVE_XC_FID, new String[]
				{ "id" });
				int i = 1;
				ps.setLong(i++, pageId);
				ps.setLong(i++, xcid);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}
	
	private static final String SAVE_VOTE_FID="insert into es_feature_interact_vote (pageid,voteid,createtime) values(?,?,now())";
	@Override
	public long saveVotefid(final long pageid,final long entityId)
	{
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator()
		{
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(SAVE_XC_FID, new String[]
				{ "id" });
				int i = 1;
				ps.setLong(i++, entityId);
				ps.setLong(i++, entityId);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}
	
	private static final String SAVE_LOTTERY_FID = "insert into es_feature_interact_lottery (pageid,lotteryid,type,createtime) values(?,?,?,now())";

	@Override
	public long saveLotteryFid(final long pageid,final long entityId,final String type)
	{
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator()
		{
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(SAVE_LOTTERY_FID, new String[]
				{ "id" });
				int i = 1;
				ps.setLong(i++, pageid);
				ps.setLong(i++, pageid);
				ps.setString(i++, type);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}
	
	@Override
	public Feature findPageFeatureById(long pfid)
	{
		String sql = "select * from es_page_feature where id=?";
		List<Feature> list = getJdbcTemplate().query(sql, new Object[]
		{ pfid }, new RowMapper()
		{
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				Feature f = new Feature();
				f.setId(rs.getLong("featureid"));
				f.setFid(rs.getLong("fid"));
				f.setIdx(rs.getInt("idx"));
				return f;
			}
		});
		return list.size()>0?list.get(0):null;
	}
}
