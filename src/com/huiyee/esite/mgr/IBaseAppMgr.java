package com.huiyee.esite.mgr;

import com.huiyee.esite.dto.BaseAppDto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.model.Account;


public interface IBaseAppMgr
{

	public IDto findBaseApp(Account account);

}
