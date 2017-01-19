
package com.huiyee.esite.dto;

import java.util.List;

import com.huiyee.interact.cb.model.InteractCb;

public class BaseAppDto implements IDto
{

	private int formTotal;
	private int cbTotal;
	private List<InteractCb> cbList;

	public int getFormTotal()
	{
		return formTotal;
	}

	public void setFormTotal(int formTotal)
	{
		this.formTotal = formTotal;
	}

	public int getCbTotal()
	{
		return cbTotal;
	}

	public void setCbTotal(int cbTotal)
	{
		this.cbTotal = cbTotal;
	}

	public List<InteractCb> getCbList()
	{
		return cbList;
	}

	public void setCbList(List<InteractCb> cbList)
	{
		this.cbList = cbList;
	}

}
