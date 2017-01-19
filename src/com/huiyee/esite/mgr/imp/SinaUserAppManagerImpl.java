package com.huiyee.esite.mgr.imp;

import java.util.List;

import com.huiyee.esite.dao.ISinaUserAppDao;
import com.huiyee.esite.mgr.ISinaUserAppManager;

public class SinaUserAppManagerImpl implements ISinaUserAppManager
{
	private ISinaUserAppDao sinaUserAppDao;
	private List<String> tokens;

	public void setSinaUserAppDao(ISinaUserAppDao sinaUserAppDao)
	{
		this.sinaUserAppDao = sinaUserAppDao;
	}

	@Override
	public String findNewToken()
	{
		if (tokens == null || tokens.size() == 0)
		{
			tokens = sinaUserAppDao.findNewToken();
		}
		if (tokens != null && tokens.size() > 0)
		{
			return tokens.remove(0);
		}
		return null;

	}

}
