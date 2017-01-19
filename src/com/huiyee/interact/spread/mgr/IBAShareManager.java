package com.huiyee.interact.spread.mgr;

import com.huiyee.esite.model.VisitUser;

public interface IBAShareManager
{
	public int share(VisitUser user, long pageid, String content, String pic);
}
