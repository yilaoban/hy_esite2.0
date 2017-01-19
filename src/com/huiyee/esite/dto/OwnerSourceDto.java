
package com.huiyee.esite.dto;

import java.util.List;

import net.sf.json.JSONObject;

import com.huiyee.esite.model.OwnerSource;
import com.huiyee.esite.model.Page4Source;
import com.huiyee.esite.model.PageSource;
import com.huiyee.esite.model.SiteSourceVm;

public class OwnerSourceDto implements IDto
{

	private OwnerSource os;
	private List<OwnerSource> list;
	private List<SiteSourceVm> vmList;
	private Page4Source source;
	private JSONObject style;

	public Page4Source getSource()
	{
		return source;
	}

	public void setSource(Page4Source source)
	{
		this.source = source;
	}

	public OwnerSource getOs()
	{
		return os;
	}

	public void setOs(OwnerSource os)
	{
		this.os = os;
	}

	public List<SiteSourceVm> getVmList()
	{
		return vmList;
	}

	public void setVmList(List<SiteSourceVm> vmList)
	{
		this.vmList = vmList;
	}

	public List<OwnerSource> getList()
	{
		return list;
	}

	public void setList(List<OwnerSource> list)
	{
		this.list = list;
	}

	public JSONObject getStyle()
	{
		return style;
	}

	public void setStyle(JSONObject style)
	{
		this.style = style;
	}
}
