package com.huiyee.interact.xc.mgr;

import java.util.List;

import com.google.gson.Gson;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.Site;
import com.huiyee.interact.xc.dao.IXcBigScreenDao;
import com.huiyee.interact.xc.dao.IXcDao;
import com.huiyee.interact.xc.dto.IDto;
import com.huiyee.interact.xc.dto.XcBigScreenDto;
import com.huiyee.interact.xc.model.CheckinConfig;
import com.huiyee.interact.xc.model.CommentConfig;
import com.huiyee.interact.xc.model.InviteConfig;
import com.huiyee.interact.xc.model.LotteryConfig;
import com.huiyee.interact.xc.model.Xc;
import com.huiyee.interact.xc.model.XcBigScreen;

public class XcBigScreenMgr implements IXcBigScreenMgr
{
	private IXcBigScreenDao xcBigScreenDao;
	private IXcDao xcDao;
	
	public void setXcBigScreenDao(IXcBigScreenDao xcBigScreenDao)
	{
		this.xcBigScreenDao = xcBigScreenDao;
	}

	@Override
	public IDto findXcBigScreenList(long xcid)
	{
		XcBigScreenDto dto = new XcBigScreenDto();
		dto.setXcid(xcid);
		Xc xc = xcDao.getXcById(xcid);
		if(xc != null){
			if (xc.getLotteryconfig() != null)
			{
				LotteryConfig lconfig = xc.getLotteryconfig();
				dto.setLconfig(lconfig);
			}
		}
		long siteid=xcBigScreenDao.findSiteIdByXc(xcid);
		dto.setSiteid(siteid);
		List<XcBigScreen> bigScreenList = xcBigScreenDao.findXcBigScreenList(siteid);
		if(bigScreenList != null){
			dto.setSize(bigScreenList.size());
		}
		dto.setXcBigScreenList(bigScreenList);
		return dto;
	}

	@Override
	public IDto findPageList(long siteid)
	{
		XcBigScreenDto dto = new XcBigScreenDto();
		List<Page> pageList = xcBigScreenDao.findPageList(siteid);
		dto.setPageList(pageList);
		return dto;
	}

	@Override
	public long saveXcBigScreen(long xcid, String name, long pageid,String imgurl)
	{
		return xcBigScreenDao.saveXcBigScreen(xcid, name, pageid,imgurl);
	}

	@Override
	public IDto findSiteList(long ownerid)
	{
		XcBigScreenDto dto = new XcBigScreenDto();
		List<Site> siteList = xcBigScreenDao.findSiteList(ownerid);
		dto.setSiteList(siteList);
		return dto;
	}

	@Override
	public int delXcBigScreen(long id)
	{
		return xcBigScreenDao.delXcBigScreen(id);
	}

	@Override
	public int updateImgurlByDpmid(long id, String imgurl)
	{
		return xcBigScreenDao.updateImgurlByDpmid(id,imgurl);
	}

	public void setXcDao(IXcDao xcDao)
	{
		this.xcDao = xcDao;
	}

}
