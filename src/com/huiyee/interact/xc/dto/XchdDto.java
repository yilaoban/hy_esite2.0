
package com.huiyee.interact.xc.dto;

import java.util.List;

import com.huiyee.esite.model.Sitegroup;

public class XchdDto implements IDto
{

	private List<Sitegroup> list;

	public List<Sitegroup> getList()
	{
		return list;
	}

	public void setList(List<Sitegroup> list)
	{
		this.list = list;
	}

}
