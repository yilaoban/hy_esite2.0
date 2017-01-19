
package com.huiyee.esite.mgr.imp;

import java.util.List;

import com.huiyee.esite.dao.IBaseAppDao;
import com.huiyee.esite.dto.BBSAccount;
import com.huiyee.esite.dto.BaseAppDto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.mgr.IBaseAppMgr;
import com.huiyee.esite.model.Account;
import com.huiyee.interact.cb.model.InteractCb;

public class BaseAppMgrImpl implements IBaseAppMgr
{

	private IBaseAppDao baseAppDao;

	public void setBaseAppDao(IBaseAppDao baseAppDao)
	{
		this.baseAppDao = baseAppDao;
	}

	@Override
	public IDto findBaseApp(Account account)
	{
		BaseAppDto dto = new BaseAppDto();
		return dto;
	}
}
