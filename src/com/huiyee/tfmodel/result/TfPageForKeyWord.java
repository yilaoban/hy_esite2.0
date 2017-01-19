package com.huiyee.tfmodel.result;

import java.util.List;

import com.huiyee.tfmodel.HyWbSrc;

public class TfPageForKeyWord implements java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String startrow;
	private String endrow;
	private List<HyWbSrc> wbSrcs;

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
	public List<HyWbSrc> getWbSrcs()
	{
		return wbSrcs;
	}
	public void setWbSrcs(List<HyWbSrc> wbSrcs)
	{
		this.wbSrcs = wbSrcs;
	}
}
