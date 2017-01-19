
package com.huiyee.interact.xc.dto;

import java.util.List;
import java.util.Map;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.model.Site;
import com.huiyee.esite.model.Sitegroup;
import com.huiyee.interact.xc.model.XcInfo;
import com.huiyee.weixin.model.WxPageShow;

public class XcSiteDto implements IDto
{

	private List<Site> list;
	private XcInfo xc;
	private Sitegroup sg;
	private Map<Long, WxPageShow> wps;

	public List<Site> getList()
	{
		return list;
	}

	public void setList(List<Site> list)
	{
		this.list = list;
	}

	public XcInfo getXc()
	{
		return xc;
	}

	public void setXc(XcInfo xc)
	{
		this.xc = xc;
	}

	public Sitegroup getSg()
	{
		return sg;
	}

	public void setSg(Sitegroup sg)
	{
		this.sg = sg;
	}

	public Map<Long, WxPageShow> getWps()
	{
		return wps;
	}

	public void setWps(Map<Long, WxPageShow> wps)
	{
		this.wps = wps;
	}

}
