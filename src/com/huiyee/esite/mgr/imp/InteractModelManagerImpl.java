package com.huiyee.esite.mgr.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dao.IAccountHideDao;
import com.huiyee.esite.dao.IInteractModelDao;
import com.huiyee.esite.mgr.IInteractModelManager;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.InteractModel;
import com.huiyee.esite.model.OwnerContentType;
import com.opensymphony.xwork2.ActionContext;

public class InteractModelManagerImpl implements IInteractModelManager
{

	private IInteractModelDao interactModelDao;
	private IAccountHideDao accountHideDao;

	public void setInteractModelDao(IInteractModelDao interactModelDao)
	{
		this.interactModelDao = interactModelDao;
	}

	@Override
	public List<InteractModel> findAllInteractModel(long owner)
	{
		List<InteractModel> list=interactModelDao.findAllInteractModel(owner);
		return updateInteractForAccount(list);
	}

	@Override
	public List<InteractModel> findInteractModel(long owner)
	{
		List<InteractModel> list=interactModelDao.findInteractModel(owner);
		return updateInteractForAccount(list);
	}

	@Override
	public List<InteractModel> findInteractModel()
	{
		return interactModelDao.findInteractModel();
	}

	private List<InteractModel> updateInteractForAccount(List<InteractModel> list){
		List<InteractModel> result=new ArrayList<InteractModel>();
		result.addAll(list);
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		List<Long> ids=accountHideDao.findHidIds(account.getOwner().getId(), account.getId(), IPageConstants.ACCOUNT_HIDE_INTERACT);
		if(ids.size()>0&&list.size()>0){
			for (InteractModel t : list) {
				if(ids.contains(t.getOmid())){
					result.remove(t);
				}
			}
		}
		return result;
	}
	
	@Override
	public String addOwnerInteract(String title, long mid, long owner)
	{
		Map<Long, String> map = getMap();
		if (map.get(mid) != null)
		{
			int total = interactModelDao.findTotalByMid(mid, owner);
			if (total > 0)
			{
				return "[" + map.get(mid) + "]模块只能创建一个";
			}
		}
		int total=interactModelDao.findCountByTitle(title,owner);
		if(total>0){
			return "互动名称["+title+"]已存在!";
		}
		int i = interactModelDao.addOwnerInteract(title, mid, owner);
		return i > 0 ? "Y" : "N";
	}
	
	private Map<Long, String> getMap()
	{
		Map<Long, String> map = new HashMap<Long, String>();
		map.put(10008L, "签到");
		map.put(10009L, "积分");
		map.put(10012L, "集人气");
		map.put(10014L, "微现场");
		return map;
	}

	@Override
	public int deleteOwnerInteract(long lid, long ownerid)
	{
		return interactModelDao.deleteOwnerInteract(lid, ownerid);
	}

	@Override
	public int updateOwnerInteract(long lid, String title, String status, long ownerid)
	{
		return interactModelDao.updateOwnerInteract(lid, title, status, ownerid);
	}
	
	@Override
	public InteractModel findInteractModelById(long omid)
	{
		return interactModelDao.findInteractModelById(omid);
	}

	public void setAccountHideDao(IAccountHideDao accountHideDao) {
		this.accountHideDao = accountHideDao;
	}
	
	@Override
	public int updateOwnerInteractStatus(long lid, long owner, String status) {
		return interactModelDao.updateOwnerInteractStatus(lid,owner,status);
	}
	
	@Override
	public int updateOwnerInteractTitle(long lid, long owner, String title) {
		int i=interactModelDao.findCountByTitle(title,owner);
		if(i>0){
			return 2;
		}
		return interactModelDao.updateOwnerInteractTitle(lid,owner,title);
	}
}
