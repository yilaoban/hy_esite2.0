
package com.huiyee.esite.mgr.imp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dao.IFeatureDao;
import com.huiyee.esite.dao.IPageDao;
import com.huiyee.esite.dao.IPageFeatureDao;
import com.huiyee.esite.dao.ISiteDao;
import com.huiyee.esite.dao.ITemplateDao;
import com.huiyee.esite.dto.Feature118Dto;
import com.huiyee.esite.dto.HdType;
import com.huiyee.esite.dto.QueryDto;
import com.huiyee.esite.mgr.IFeatureManager;
import com.huiyee.esite.mgr.IPageManager;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.Feature;
import com.huiyee.esite.model.Owner;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.PageBlockRelation;
import com.huiyee.esite.model.PageFeature;
import com.huiyee.esite.model.SinaChecklistRecord;
import com.huiyee.esite.model.SinaUserApp;
import com.huiyee.esite.model.Site;
import com.huiyee.esite.model.Sitegroup;
import com.huiyee.esite.model.UploadDetail;
import com.huiyee.esite.model.ZanDetail;
import com.huiyee.esite.util.DateUtil;
import com.huiyee.interact.appointment.dao.IInteractAptDao;
import com.huiyee.interact.cb.dao.IInteractCbDao;
import com.huiyee.interact.cb.model.InteractCb;
import com.huiyee.interact.offcheck.dao.IOffCheckDao;
import com.huiyee.interact.offcheck.model.OffCheck;
import com.huiyee.interact.renqi.model.RenQi;
import com.huiyee.interact.xc.dao.IXcLotteryDao;
import com.huiyee.interact.xc.model.Xc;

public class FeatureManagerImpl implements IFeatureManager
{

	private IFeatureDao featureDao;
	private ISiteDao siteDao;
	private IPageDao pageDao;
	private IXcLotteryDao xcLotteryDao;
	private ITemplateDao templateDao;
	private IPageManager pageManager;
	private IInteractAptDao interactAptDao;
	private IPageFeatureDao pageFeatureDao;
	private Map<Long, com.huiyee.esite.fmgr.IFeatureManager> managers;
	private IInteractCbDao interactCbDao;
	private IOffCheckDao offCheckDao;

	
	public void setOffCheckDao(IOffCheckDao offCheckDao)
	{
		this.offCheckDao = offCheckDao;
	}

	public void setInteractCbDao(IInteractCbDao interactCbDao)
	{
		this.interactCbDao = interactCbDao;
	}

	public void setManagers(Map<Long, com.huiyee.esite.fmgr.IFeatureManager> managers)
	{
		this.managers = managers;
	}

	public void setPageFeatureDao(IPageFeatureDao pageFeatureDao)
	{
		this.pageFeatureDao = pageFeatureDao;
	}

	public void setInteractAptDao(IInteractAptDao interactAptDao)
	{
		this.interactAptDao = interactAptDao;
	}

	public void setPageManager(IPageManager pageManager)
	{
		this.pageManager = pageManager;
	}

	public void setFeatureDao(IFeatureDao featureDao)
	{
		this.featureDao = featureDao;
	}

	@Override
	public List<Feature> findFeatureByPageId(long pageid)
	{
		return featureDao.findFeatureByPageId(pageid);
	}

	@Override
	public List<Feature> findNeedUserFeatureByPageId(long pageid)
	{
		return featureDao.findNeedUserFeatureByPageId(pageid);
	}

	@Override
	public List<Feature> findFeatureBySitegroupid(long sitegroupid)
	{
		List<Feature> list = featureDao.findDynameicFeature(sitegroupid);
		for (Feature feature : list)
		{
			Map<String, Object> map = new HashMap<String, Object>();
			if (feature.getId() == IPageConstants.FEATURE_ZAM)
			{
				map = featureDao.findZanBySitegroupid(sitegroupid);
			}
			if (feature.getId() == IPageConstants.FEATURE_UPLOAD)
			{
				map = featureDao.findUploadBySitegroupid(sitegroupid);
			}
			if (feature.getId() == IPageConstants.SINA_ACCREDIT)
			{
				// 新浪授权
				map = featureDao.findSinaAccreditBySitegroupid(sitegroupid);
			}
			if (feature.getId() == IPageConstants.USER_SINA_SHARE)
			{
				// 用户新浪分享
				map = featureDao.findSinaShareBySitegroupid(sitegroupid);
			}
			if (map.size() > 0)
			{
				feature.setInteractionCount((Long) map.get("count"));
				feature.setInteractionDate((Date) map.get("updatetime"));
			}
		}
		return list;
	}

	@Override
	public List<Feature> findFeatureBySiteId(long siteid)
	{
		List<Feature> list = featureDao.findDynameicFeature();
		for (Feature feature : list)
		{
			Map<String, Object> map = new HashMap<String, Object>();
			if (feature.getId() == IPageConstants.FEATURE_ZAM)
			{
				map = featureDao.findZanBySiteId(siteid);
			}
			if (feature.getId() == IPageConstants.FEATURE_UPLOAD)
			{
				map = featureDao.findUploadBySiteId(siteid);
			}
			if (map.size() > 0)
			{
				feature.setInteractionCount((Long) map.get("count"));
				feature.setInteractionDate((Date) map.get("updatetime"));
			}
		}
		return list;
	}

	@Override
	public List<ZanDetail> findZanDetailBySitegroupid(long sitegroupid)
	{
		return featureDao.findZanDetailBySitegroupid(sitegroupid);
	}

	@Override
	public List<ZanDetail> findZanDetailBySiteId(long siteid)
	{
		return featureDao.findZanDetailBySitegroupid(siteid);
	}

	@Override
	public List<UploadDetail> findUploadDetailBySitegroupid(long sitegroupid)
	{
		return featureDao.findUploadDetailBySitegroupid(sitegroupid);
	}

	@Override
	public List<UploadDetail> findUploadDetailBySiteId(long siteid)
	{
		return featureDao.findUploadDetailBySitegroupid(siteid);
	}

	@Override
	public Map<Long, Integer> findZanTotal(String[] pfid)
	{
		Map<Long, Integer> map = new HashMap<Long, Integer>();
		for (String pf : pfid)
		{
			Map<String, Object> rs = featureDao.findZanTotal(Long.parseLong(pf));
			map.put((Long) rs.get("id"), (Integer) rs.get("zantotal"));
		}
		return map;
	}

	@Override
	public List<SinaUserApp> findSinaUsersDetail(long sitegroupid, QueryDto siftDto)
	{
		return featureDao.findSinaUsersDetail(sitegroupid, siftDto, (siftDto.getPageId() - 1) * IPageConstants.SINA_USER_DETAIL_LIMIT, IPageConstants.SINA_USER_DETAIL_LIMIT);
	}

	@Override
	public int findSinaUsersDetailTotal(long sitegroupid, QueryDto siftDto)
	{
		return featureDao.findSinaUsersDetailTotal(sitegroupid, siftDto);
	}

	@Override
	public List<Feature> findFeaturesBySiteId(long siteid)
	{
		return featureDao.findFeaturesBySiteId(siteid);
	}

	@Override
	public List<SinaChecklistRecord> findSinaShare(long sitegroupid, QueryDto siftDto)
	{
		return featureDao.findSinaShare(sitegroupid, siftDto, (siftDto.getPageId() - 1) * IPageConstants.SINA_SHARE_LIMIT, IPageConstants.SINA_SHARE_LIMIT);
	}

	@Override
	public int findSinaShareTotal(long sitegroupid, QueryDto siftDto)
	{
		return featureDao.findSinaShareTotal(sitegroupid, siftDto);
	}

	@Override
	public Feature findFeaturesId(long id)
	{
		return featureDao.findFeaturesId(id);
	}

	@Override
	public HdType findHdType(long id)
	{
		return featureDao.findHdType(id);
	}

	@Override
	public List<PageFeature> findPageFeature(long ownerid, int start, int size)
	{
		return featureDao.findPageFeature(ownerid, start, size);
	}

	@Override
	public int findPageFeatureFid(long ownerid)
	{
		return featureDao.findPageFeatureFid(ownerid);
	}

	@Override
	public long savePageFeature(long pageid, long fid, int idx, int featureid)
	{
		if (idx == 0)
		{
			idx = featureDao.findMaxIdxByPageId(pageid) + 1;
		}
		return featureDao.savePageFeature(pageid, fid, idx, featureid);
	}

	@Override
	public long saveSimpleRq(RenQi rq, long owner)
	{
		return featureDao.saveSimpleRq(rq, owner);
	}

	@Override
	public long findRqFidByPage(long l)
	{
		return featureDao.findRqFidByPage(l);
	}

	@Override
	public long saveRQFid(long pageId, long rqid)
	{
		return featureDao.saveRQFid(pageId, rqid);
	}

	@Override
	public long addSiteCopy(Owner owner, String sitename, String copyType, long groupid)
	{
		long ownerid = owner.getId();
		String oname = owner.getEntity();
		String oldoname = siteDao.findOnameBySg(groupid);

		int count = siteDao.findSameNameCount(ownerid, sitename, copyType);
		if (count > 0)
		{
			return -1;
		}
		long sitegroupid = siteDao.saveSameSiteGroup(ownerid,sitename,groupid);
		
		Map<Long, Long> siteMap = new HashMap<Long, Long>();
		// key:oldsiteid value:newsiteid
		List<Site> siteArr = siteDao.findCopySiteByOwner(groupid);// 查找需要复制的site
		for (Site site : siteArr)
		{
			long ns=siteDao.saveSameSite(ownerid,sitegroupid,site.getId());
			siteMap.put(site.getId(), ns);
		}
		
		// value:feature_新实例id
		Map<Long, Long> map = new HashMap<Long, Long>();
		for (Site site : siteArr)
		{
			long targetSite = siteMap.get(site.getId());
			map.putAll(siteCopy(targetSite, site.getId(), oname, oldoname));
		}
		/**
		 * 登录页失败页
		 */
		upSglr(map, groupid, sitegroupid);
		
		/**
		 * 微现场需要不同site之间的链接change
		 */
		for (Long key : map.keySet())
		{
			changelink(map.get(key), map, oname, oldoname);
		}
		return sitegroupid;
	}
	
	@Override
	public long addXcSite(Owner owner, String sitename, String copyType, long groupid)
	{
		long ownerid=owner.getId();
		long newgroupid=addSiteCopy(owner, sitename, copyType, groupid);
		
		//1.创建现场实例
		long newXc = createEntity(IInteractConstants.INTERACT_XC, sitename, ownerid);
		siteDao.updateSiteGroupEntity(newgroupid, newXc);
		
		Sitegroup sg=siteDao.findSiteGroup(groupid);
		if(sg.getEntityid()>0){
			
			//2.如果被复制现场有申请Id
			Xc xc=xcLotteryDao.findXcById(sg.getEntityid());
			if(xc.getAptid()>0){
				long newApt=createEntity(IInteractConstants.INTERACT_APT, sitename, ownerid);
				xcLotteryDao.updateXcWithApt(newXc,newApt);
				
				List<Long> pageList=new ArrayList<Long>();
				List<Site> siteArr = siteDao.findCopySiteByOwner(newgroupid);
				for (Site site : siteArr)
				{
					List<Long> pageArr = pageDao.findPageIdBySiteid(site.getId());
					if(pageArr!=null)
						pageList.addAll(pageArr);
				}
				
				Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
				for (long np : pageList)
				{
					List<Long> pfList = pageFeatureDao.findPfidByPageId(np, IInteractConstants.INTERACT_APT);
					if (pfList == null || pfList.size() == 0)
					{
						continue;
					}
					for (Long pfid : pfList)
					{
						Feature f = featureDao.findPageFeatureById(pfid);
						Feature118Dto dto = new Feature118Dto();
						dto.setAptid(newApt);
						dto.setFid(f.getFid());
						managers.get((long) IInteractConstants.INTERACT_APT).configSub(IInteractConstants.INTERACT_APT, dto, account);
					}
				}
				
			}
		}
		return newgroupid;
	}
	
	@Override
	public long addOffCheckSite(Owner owner, String sitename, String copyType, long groupid)
	{
		long ownerid=owner.getId();
		long newgroupid=addSiteCopy(owner, sitename, copyType, groupid);
		OffCheck ofc=offCheckDao.findStoreCrmByOwner(ownerid);
		if(ofc!=null&&ofc.getAptid()!=0){
			
			List<Long> pageList=new ArrayList<Long>();
			List<Site> siteArr = siteDao.findCopySiteByOwner(newgroupid);
			for (Site site : siteArr)
			{
				List<Long> pageArr = pageDao.findPageIdBySiteid(site.getId());
				if(pageArr!=null)
					pageList.addAll(pageArr);
			}
			
			Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
			for (long np : pageList)
			{
				List<Long> pfList = pageFeatureDao.findPfidByPageId(np, IInteractConstants.INTERACT_APT);
				if (pfList == null || pfList.size() == 0)
				{
					continue;
				}
				for (Long pfid : pfList)
				{
					Feature f = featureDao.findPageFeatureById(pfid);
					Feature118Dto dto = new Feature118Dto();
					dto.setAptid(ofc.getAptid());
					dto.setFid(f.getFid());
					managers.get((long) IInteractConstants.INTERACT_APT).configSub(IInteractConstants.INTERACT_APT, dto, account);
				}
			}
			
		}
		return newgroupid;
	}

	@Override
	public long addNormalSitegroup(Owner owner, String sitename, String copyType, long groupid)
	{
		long ownerid = owner.getId();
		int count = siteDao.findSameNameCount(ownerid, sitename, copyType);
		if (count > 0)
		{
			return -1;
		}
		long sitegroupid = siteDao.saveSiteGroup(sitename, copyType, ownerid,IPageConstants.SITEGROUP_TYPE_WEBSITE);
		long pcSite = siteDao.saveSite(ownerid, sitename, IPageConstants.SITE_TYPE_PHONE, sitegroupid);
		return pcSite;
	}

	private void changelink(long pageid, Map<Long, Long> map, String oname, String oldoname)
	{
		List<PageBlockRelation> list = templateDao.findPageBlockRelationByPageId(pageid);
		for (PageBlockRelation pbr : list)
		{
			String regex = "/(\\w+|\\$\\{oname\\}{1})/user/show/[0-9]+(/(\\w|-)+)*.html";
			String json = pbr.getJson();
			Pattern pattern = Pattern.compile(regex);
			Matcher match = pattern.matcher(json);
			while (match.find())
			{
				String oldStr = match.group();
				Pattern pattern2 = Pattern.compile("/show/[0-9]+");
				Matcher match2 = pattern2.matcher(oldStr);
				if (match2.find())
				{
					long p = Long.parseLong(match2.group().replace("/show/", ""));
					if (map.get(p) != null)
					{
						long rs = map.get(p);
						String newStr = oldStr.replace(oldoname, oname).replace("/show/" + p, "/show/" + rs);
						json = json.replace(oldStr, newStr);
					}
				}
			}
			templateDao.updatePageBlockRelation(pbr.getId(), json);

		}
	}
	
	public static void main(String[] args)
	{
		String oldoname="yizhan";
		String oname="hy";
		String regex = "/(\\w+|\\$\\{oname\\}{1})/user/show/[0-9]+(/(\\w|-)+)*.html";
		String json = "/${oname}/user/show/38838/pcn/kv.html";
		Pattern pattern = Pattern.compile(regex);
		Matcher match = pattern.matcher(json);
		while (match.find())
		{
			String oldStr = match.group();
			Pattern pattern2 = Pattern.compile("/show/[0-9]+");
			Matcher match2 = pattern2.matcher(oldStr);
			System.out.println(oldStr);
			if (match2.find())
			{
				long p = Long.parseLong(match2.group().replace("/show/", ""));
					long rs = 2;
					String newStr = oldStr.replace(oldoname, oname).replace("/show/" + p, "/show/" + rs);
					json = json.replace(oldStr, newStr);
					System.out.println(newStr);
					System.out.println("oldoname=="+oldoname+":oname=="+oname);
			}
		}

	
	}

	private long createEntity(long featureid, String sitename, long ownerid)
	{
		if (featureid == IInteractConstants.INTERACT_XC)
		{
			Xc xc = new Xc();
			xc.setTitle("微现场" + DateUtil.getDateString(new Date()));
			return xcLotteryDao.saveXcLottery(xc, ownerid);// 创建现场ID
		} else if (featureid == IInteractConstants.INTERACT_RQ)
		{
			RenQi rq = new RenQi();
			rq.setTitle("集人气" + DateUtil.getDateString(new Date()));
			return featureDao.saveSimpleRq(rq, ownerid);
		}else if (featureid == IInteractConstants.INTERACT_APT)
		{
			String aptname = "申请-" + DateUtil.getDateString(new Date());
			return interactAptDao.saveSimpleApt(ownerid, aptname);
		}
		return 0;
	}

	public void setSiteDao(ISiteDao siteDao)
	{
		this.siteDao = siteDao;
	}

	public void setPageDao(IPageDao pageDao)
	{
		this.pageDao = pageDao;
	}

	public void setXcLotteryDao(IXcLotteryDao xcLotteryDao)
	{
		this.xcLotteryDao = xcLotteryDao;
	}

	public void setTemplateDao(ITemplateDao templateDao)
	{
		this.templateDao = templateDao;
	}

	@Override
	public long addCbSite(Owner owner, String sitename, String copyType, long groupid)
	{

		long ownerid = owner.getId();

		int count = siteDao.findSameNameCount(ownerid, sitename ,copyType);
		if (count > 0)
		{
			return -1;
		}
		long sitegroupid = siteDao.saveSameSiteGroup(ownerid, sitename, groupid);
		long newSiteId = siteDao.saveSite(ownerid, sitename, IPageConstants.SITE_TYPE_PHONE, sitegroupid);

		InteractCb cb = interactCbDao.findCbById(ownerid);
		long cbid = 0;
		long aptid = 0;
		if (cb == null)
		{
			aptid = createEntity(IInteractConstants.INTERACT_APT, sitename, ownerid);
			interactAptDao.addName(aptid, "", "f1", "S", "T", "", "N", 0, "N", "N");
			interactAptDao.addName(aptid, "", "f2", "S", "S", "", "N", 0, "N", "N");
			interactAptDao.addName(aptid, "", "f3", "S", "R", "", "N", 0, "N", "N");
			interactAptDao.addName(aptid, "", "f4", "S", "C", "", "N", 0, "N", "N");
			interactAptDao.addName(aptid, "", "f5", "S", "A", "", "N", 0, "N", "N");

			cb = new InteractCb();
			cb.setAptid(aptid);
			cb.setTitle("传播" + DateUtil.getDateString(new Date()));
			cb.setOwner(ownerid);
			cb.setSiteid(newSiteId);
			cb.setSitegroupid(sitegroupid);
			cbid = interactCbDao.saveInteractCb(cb);
		} else
		{
			cbid = cb.getId();
			aptid = cb.getAptid();
		}

		siteDao.updateSiteGroupEntity(sitegroupid, cbid);

		List<Site> siteArr = siteDao.findCopySiteByOwner(groupid);// 查找需要复制的site
		String oname = owner.getEntity();
		String oldoname = siteDao.findOnameBySg(groupid);
		Map<Long, Long> map = new HashMap<Long, Long>();
		for (Site site : siteArr)
		{
			map = siteCopy(newSiteId, site.getId(), oname, oldoname);
		}

		for (long oldpage : map.keySet())
		{
			Page p = pageDao.findPageById(map.get(oldpage));
			if ("SHQ".equals(p.getApptype()))
			{
				List<Long> pfList = pageFeatureDao.findPfidByPageId(map.get(oldpage), IInteractConstants.INTERACT_APT);
				if (pfList == null || pfList.size() == 0)
				{
					continue;
				}
				for (Long pfid : pfList)
				{
					Feature f = featureDao.findPageFeatureById(pfid);
					Feature118Dto dto = new Feature118Dto();
					dto.setAptid(aptid);
					dto.setFid(f.getFid());
					Account account = new Account();
					account.setOwner(owner);
					managers.get((long) IInteractConstants.INTERACT_APT).configSub(IInteractConstants.INTERACT_APT, dto, account);
				}
			} else if ("STS".equals(p.getApptype()))
			{
				interactCbDao.updateApptype(cbid, p.getId());
			}
		}
		upSglr(map, groupid, sitegroupid);
		return sitegroupid;
	}

	private void upSglr(Map<Long, Long> map, long oldsitegroupid, long newsitegroupid)
	{
		Map<String, Long> old = siteDao.findLogRegById(oldsitegroupid);
		if (old.size() == 2)
		{
			Long nl = map.get(old.get("loginpage"));
			Long nr = map.get(old.get("registpage"));
			siteDao.updateSglr(newsitegroupid, nl, nr);
		}
	}

	private Map<Long, Long> siteCopy(long newSite, long oldSite, String newOname, String oldOname)
	{

		List<Long[]> list = new ArrayList<Long[]>();// String[0]:新站点ID //
		// String[1]:需复制的pageId
		List<Long> pageList = pageDao.findPageIdBySiteid(oldSite);
		Collections.sort(pageList);
		for (Long pageId : pageList)
		{
			Long[] arr =
			{ newSite, pageId };
			list.add(arr);
		}
		Map<Long, Long> map = new HashMap<Long, Long>();
		for (Long[] arr : list)
		{
			Page page = pageManager.findPageById(arr[1]);
			long newPageId = pageManager.addcopyPage(arr[0], page);
			map.put(arr[1], newPageId);
		}

		for (Long key : map.keySet())
		{
			changelink(map.get(key), map, newOname, oldOname);
		}

		return map;

	}

	@Override
	public long saveUseMb(Owner owner, String type, long mbid, String name, long groupid,String stype)
	{
		long siteid = 0;
		long ownerid = owner.getId();
		String oname = owner.getEntity();
		String oldoname = oname;
		String grouptype="N";//默认sitegroup是普通站点
		if ("A".equalsIgnoreCase(type) || mbid == 0)
		{
			// 新建站点
			if(mbid!=0){
				grouptype=siteDao.findGroupTypeByMbid(mbid);
			}
			int count = siteDao.findSameNameCount(ownerid, name, grouptype);
			if (count > 0)
			{
				return -1;
			}
			long sitegroupid=0;
			sitegroupid = siteDao.saveSiteGroup(name, grouptype, ownerid ,stype);
			siteid = siteDao.saveSite(ownerid, name, IPageConstants.SITE_TYPE_PHONE, sitegroupid);
		} else
		{
			// 选择已有站点
			List<Site> sites = siteDao.findSiteByGroupId(groupid, IPageConstants.SITE_TYPE_PHONE);
			if (sites.size() == 1)
			{
				siteid = sites.get(0).getId();
				oldoname = siteDao.findOnameBySg(groupid);
			} else
			{
				return -2;
			}
		}
		if (mbid > 0)
		{
			List<Long[]> list = new ArrayList<Long[]>();// String[0]:新站点ID
														// String[1]:需复制的pageId
			List<Long> pageList = pageDao.findPagesByMbid(mbid);
			Collections.sort(pageList);
			for (Long pageId : pageList)
			{
				Long[] arr =
				{ siteid, pageId };
				list.add(arr);
			}
			Map<Long, Long> map = new HashMap<Long, Long>();
			for (Long[] arr : list)
			{
				Page page = pageManager.findPageById(arr[1]);
				long newPageId = pageManager.addcopyPage(arr[0], page);
				map.put(arr[1], newPageId);
			}

			for (Long key : map.keySet())
			{
				changelink(map.get(key), map, oname, oldoname);
			}
		}
		return siteid;
	}

}
