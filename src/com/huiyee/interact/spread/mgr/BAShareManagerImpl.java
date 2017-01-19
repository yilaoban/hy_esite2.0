package com.huiyee.interact.spread.mgr;

import org.apache.commons.lang.StringUtils;

import com.huiyee.esite.mgr.imp.AbstractMgr;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.spread.dao.ISpreadDao;

public class BAShareManagerImpl extends AbstractMgr implements IBAShareManager
{
	private ISpreadDao spreadDao;

	@Override
	public int share(VisitUser user, long pageid, String content, String pic)
	{
		String token = spreadDao.findTokenByPageidAndWbuid(user.getWbuid(), pageid);
		if (token == null)
		{
			return -2;
		}
		if (StringUtils.isNotEmpty(pic))
		{
			try
			{
				this.getJustWS().weiboByPic(token, content, pic);
			}
			catch (Exception e)
			{
				return -3;// 新浪接口繁忙
			}
		}
		else
		{
			try
			{
				this.getJustWS().weibo(token, content);
			}
			catch (RuntimeException e)
			{
				return -3;// 新浪接口繁忙
			}
		}

		return 1;
	}

	public void setSpreadDao(ISpreadDao spreadDao)
	{
		this.spreadDao = spreadDao;
	}

	public ISpreadDao getSpreadDao()
	{
		return spreadDao;
	}

}
