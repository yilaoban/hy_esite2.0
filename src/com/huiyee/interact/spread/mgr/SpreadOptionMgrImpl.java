package com.huiyee.interact.spread.mgr;

import java.util.List;

import com.huiyee.esite.util.Base62Util;
import com.huiyee.esite.util.ToolUtils;
import com.huiyee.interact.spread.dao.ISpreadOptionDao;
import com.huiyee.interact.spread.dto.IDto;
import com.huiyee.interact.spread.dto.SpreadOptionDto;
import com.huiyee.interact.spread.model.SpreadOption;

public class SpreadOptionMgrImpl implements ISpreadOptionMgr
{

	private ISpreadOptionDao spreadOptionDao;

	public ISpreadOptionDao getSpreadOptionDao()
	{
		return spreadOptionDao;
	}

	public void setSpreadOptionDao(ISpreadOptionDao spreadOptionDao)
	{
		this.spreadOptionDao = spreadOptionDao;
	}

	@Override
	public int deleteSpreadOption(long id)
	{
		return spreadOptionDao.deleteSpreadOption(id);
	}

	@Override
	public IDto findSpreadOption(long spreadid,int start,int size)
	{
		SpreadOptionDto dto=new SpreadOptionDto();
		dto.setList(spreadOptionDao.findSpreadOption(spreadid,start,size));
		dto.setSm(spreadOptionDao.fingSpreadType(spreadid));
		dto.setSp(spreadOptionDao.findOneSpreadWbid(spreadid));
		return dto;
	}
	
	@Override
	public int findSpreadOptionTotal(long spreadid)
	{
		return spreadOptionDao.findSpreadOptionTotal(spreadid);
	}

	@Override
	public int saveSpreadOption(SpreadOption sp)
	{
		int result=0;
		long spreadid=sp.getSpreadid();
		String wbid=sp.getWbid();
		String title=sp.getTitle();
		String content=sp.getContent();
		if(sp.getPic()==null){
			String img=sp.getImg();
			result=spreadOptionDao.saveSpreadOption(spreadid, wbid, title,content, img);
		}else{
			String pic=sp.getPic();
			result= spreadOptionDao.saveSpreadOption(spreadid, wbid,title,content, pic);
		}
		if(result>0){
			result=1;
		}
		return result;
	}

	@Override
	public int updateSpreadOption(SpreadOption sp)
	{
		int result=0;
		long id=sp.getSpreadid();
		String title=sp.getTitle();
		String content=sp.getContent();
		if(sp.getPic()==null){
			String img=sp.getImg();
			result=spreadOptionDao.updateSpreadOption(title,content, img, id);
		}else{
			String pic=sp.getPic();
			result=spreadOptionDao.updateSpreadOption(title,content, pic, id);
		}
		if(result>0){
			result=1;
		}
		return result;
	}

	@Override
	public SpreadOption findOneSpreadOption(long id)
	{
		return spreadOptionDao.findOneSpreadOption(id);
	}

	@Override
	public int updateSpreadWbid(SpreadOption sp,long id)
	{
		String url=sp.getWbid();
		if(url.startsWith("http")||url.contains("weibo.com")){
			String str= url.substring(url.lastIndexOf("/") + 1, url.contains("?") ? url.indexOf("?") : url.length());
			String mid=Base62Util.UrlToMid(str);
			if(ToolUtils.isNumeric(mid)){
				return spreadOptionDao.updateSpreadWbid(mid,url, id);
			}
		}
		return 0;
	}

	@Override
	public SpreadOption findOneSpreadWbid(long id)
	{
		return spreadOptionDao.findOneSpreadWbid(id);
	}

	@Override
	public int saveSpreadWbid(SpreadOption sp, long spreadid)
	{
		String url=sp.getWbid();
		if(url.startsWith("http")||url.contains("weibo.com")){
			String str= url.substring(url.lastIndexOf("/") + 1, url.contains("?") ? url.indexOf("?") : url.length());
			String mid=Base62Util.UrlToMid(str);
			if(ToolUtils.isNumeric(mid)){
				return spreadOptionDao.saveSpreadWbid(mid,url, spreadid);
			}
		}
		return 0;
	}
}
