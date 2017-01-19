package com.huiyee.esite.fmgr.imp;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.huiyee.esite.dao.IPageDao;
import com.huiyee.esite.dto.Feature149Dto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.fdao.IHd149Dao;
import com.huiyee.esite.mgr.IUserTagMgr;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.ContentCategory;
import com.huiyee.esite.model.HD149Model;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.UserPkvTag;
import com.huiyee.interact.bbs.model.BBSContent;

public class Feature149ManagerImpl extends AbstractFeatureManager
{
	private IHd149Dao hd149Dao;
	private IPageDao pageDao;
	private IUserTagMgr userTagMgr;

	@Override
	public long add(long pageid, long featureid, String featurename)
	{
		long fid = hd149Dao.saveFid(pageid);
		return getPageFeatureDao().addPageFeature(pageid, featureid, fid, featurename, "N");
	}

	@Override
	public IDto config(long fid)
	{
		Feature149Dto dto = new Feature149Dto();
		dto.setFid(fid);
		HD149Model m=hd149Dao.findFid(fid);
		ContentCategory current = new ContentCategory();
		current.setId(m.getCategoryid());
		current.setType(m.getType());
		dto.setCurrent(current);
		dto.setPageid(m.getTopage());
		List<BBSContent> list=new ArrayList<BBSContent>();
		if ("T".equals(current.getType()))
		{
			list=hd149Dao.findBBSProduct(current.getId(),getTypeCode(current.getType()));
			try
			{
				if(list.size()>0){
					for (BBSContent bbsContent : list)
					{
						UserPkvTag tags=userTagMgr.findContentTag(m.getCategoryid(), bbsContent.getProduct().getId());
						bbsContent.setTags(tags);
					}
				}
			} catch (Exception e)
			{
				System.out.println("find product tag error ccid="+m.getCategoryid());
			}
		}
		else if ("P".equals(current.getType()))
		{
			list=hd149Dao.findBBSPicture(current.getId(),getTypeCode(current.getType()));
			try
			{
				if(list.size()>0){
					for (BBSContent bbsContent : list)
					{
						UserPkvTag tags=userTagMgr.findContentTag(m.getCategoryid(), bbsContent.getPicture().getId());
						bbsContent.setTags(tags);
					}
				}
			} catch (Exception e)
			{
				System.out.println("find picture tag error ccid="+m.getCategoryid());
			}
		}
		else if ("V".equals(current.getType()))
		{
			list=hd149Dao.findBBSVideo(current.getId(),getTypeCode(current.getType()));
			try
			{
				if(list.size()>0){
					for (BBSContent bbsContent : list)
					{
						UserPkvTag tags=userTagMgr.findContentTag(m.getCategoryid(), bbsContent.getVideo().getId());
						bbsContent.setTags(tags);
					}
				}
			} catch (Exception e)
			{
				System.out.println("find video tag error ccid="+m.getCategoryid());
			}
		}
		else if ("N".equals(current.getType()))
		{
			list=hd149Dao.findBBSNews(current.getId(),getTypeCode(current.getType()));
			try
			{
				if(list.size()>0){
					for (BBSContent bbsContent : list)
					{
						UserPkvTag tags=userTagMgr.findContentTag(m.getCategoryid(), bbsContent.getNews().getId());
						bbsContent.setTags(tags);
					}
				}
			} catch (Exception e)
			{
				System.out.println("find news tag error ccid="+m.getCategoryid());
			}
			
		}else if ("F".equals(current.getType()))
		{
			//自定义表单目前没有涉及的论坛.
			list=hd149Dao.findBBSForm(current.getId(),getTypeCode(current.getType()));
		}
		
		dto.setList(list);
		return dto;
	}

	@Override
	public IDto config(long fid, Account account)
	{
		Feature149Dto dto = new Feature149Dto();
		dto.setFid(fid);
		HD149Model m=hd149Dao.findFid(fid);
		ContentCategory current = new ContentCategory();
		current.setId(m.getCategoryid());
		current.setType(m.getType());
		dto.setCurrent(current);
		Page p = pageDao.findPageById(m.getPageid());
		dto.setPagelist(pageDao.findPageBySiteid(p.getSiteid()));
		dto.setPageid(m.getTopage());

		List<ContentCategory> list=hd149Dao.findCategory(account.getOwner().getId());
		JSONArray ja=new JSONArray();
		JSONArray ja1=new JSONArray();
		JSONArray ja2=new JSONArray();
		JSONArray ja3=new JSONArray();
		JSONArray ja4=new JSONArray();
		for (ContentCategory contentCategory : list)
		{
			JSONObject jo=new JSONObject();
			jo.put("id", contentCategory.getId());
			jo.put("name", contentCategory.getName());
			jo.put("pId", contentCategory.getFartherCatId());
			if(contentCategory.getId()==current.getId()){
				jo.put("checked", true);
			}
			if("T".equals(contentCategory.getType())){
				ja.add(jo);
			}else if("P".equals(contentCategory.getType())){
				ja1.add(jo);
			}else if("V".equals(contentCategory.getType())){
				ja2.add(jo);
			}else if("N".equals(contentCategory.getType())){
				ja3.add(jo);
			}else if("F".equals(contentCategory.getType())){
				ja4.add(jo);
			}
		}
		dto.setJson(ja.toString());
		dto.setJson1(ja1.toString());
		dto.setJson2(ja2.toString());
		dto.setJson3(ja3.toString());
		dto.setJson4(ja4.toString());
		return dto;
	}

	@Override
	public String configSub(long featureid, IDto dto, Account account)
	{
		try
		{
			Feature149Dto featuredto = (Feature149Dto) dto;
			if (featuredto.getCurrent() != null && featuredto.getCurrent().getId() != 0)
			{
				long catid = featuredto.getCurrent().getId();
				String type = hd149Dao.findContentType(catid);
				hd149Dao.saveFid(featuredto.getFid(), catid, type,featuredto.getPageid());
				hd149Dao.updateContentCategoryPageid(catid, featuredto.getPageid());//记录该目录的最近绑定在哪个page上
				return "Y";
			}
			else
			{
				return "没有配置目录.";
			}

		}
		catch (RuntimeException e)
		{
			e.printStackTrace();
			return "配置失败!";
		}
	}

	public void setHd149Dao(IHd149Dao hd149Dao)
	{
		this.hd149Dao = hd149Dao;
	}
	
	public void setPageDao(IPageDao pageDao)
	{
		this.pageDao = pageDao;
	}

	private int getTypeCode(String type)
	{
		// 0-图文;1-产品;2-新闻;3-视频
		if ("T".equals(type))
		{
			return 1;
		}
		else if ("P".equals(type))
		{
			return 0;
		}
		else if ("V".equals(type))
		{
			return 3;
		}
		else if ("N".equals(type))
		{
			return 2;
		}
		else
		{
			return 0;
		}
	}

	
	public void setUserTagMgr(IUserTagMgr userTagMgr)
	{
		this.userTagMgr = userTagMgr;
	}
}
