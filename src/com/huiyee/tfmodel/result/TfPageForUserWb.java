package com.huiyee.tfmodel.result;

import java.io.Serializable;
import java.util.List;

public class TfPageForUserWb implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String startrow;
	private String endrow;
	private List<TfWeiBoCommnet>  wbSrcs; // it contains both HyWbSrc and HyWbComment

	public String getStartrow()
	{
		return startrow;
	}
	public void setStartrow(String startrow)
	{
		this.startrow = startrow;
	}
	public String getEndrow()
	{
		return endrow;
	}
	public void setEndrow(String endrow)
	{
		this.endrow = endrow;
	}
	public List<TfWeiBoCommnet> getWbSrcs()
	{
		return wbSrcs;
	}
	public void setWbSrcs(List<TfWeiBoCommnet> wbSrcs)
	{
		this.wbSrcs = wbSrcs;
	}
}
