package com.huiyee.interact.sitesearch.mgr;

import java.util.List;

import com.huiyee.esite.model.Sitegroup;

public interface ISiteGroupMgr {

	public int getSiteGroupCount(long ownerid, List<String> types);

	public List<Sitegroup> getSiteGroupList(long ownerid, List<String> types, int start, int rows);

}
