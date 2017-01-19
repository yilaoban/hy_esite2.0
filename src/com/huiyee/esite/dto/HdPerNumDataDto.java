package com.huiyee.esite.dto;
import java.util.List;

import com.huiyee.esite.model.HdModel;
import com.huiyee.esite.model.Site;
import com.huiyee.esite.model.Sitegroup;
public class HdPerNumDataDto implements IDto{
	private int onenum;
	private String onenumpercent;
	private int twonum;
	private String twonumpercent;
	private int threenum;
	private String threenumpercent;
	private int fournum;
	private String fournumpercent;
	private int fivenum;
	private String fivenumpercent;
	private Site site;
	private List<HdModel> models;
	public int getOnenum()
	{
		return onenum;
	}
	public void setOnenum(int onenum)
	{
		this.onenum = onenum;
	}
	public String getOnenumpercent()
	{
		return onenumpercent;
	}
	public void setOnenumpercent(String onenumpercent)
	{
		this.onenumpercent = onenumpercent;
	}
	public int getTwonum()
	{
		return twonum;
	}
	public void setTwonum(int twonum)
	{
		this.twonum = twonum;
	}
	public String getTwonumpercent()
	{
		return twonumpercent;
	}
	public void setTwonumpercent(String twonumpercent)
	{
		this.twonumpercent = twonumpercent;
	}
	public int getThreenum()
	{
		return threenum;
	}
	public void setThreenum(int threenum)
	{
		this.threenum = threenum;
	}
	public String getThreenumpercent()
	{
		return threenumpercent;
	}
	public void setThreenumpercent(String threenumpercent)
	{
		this.threenumpercent = threenumpercent;
	}
	public int getFournum()
	{
		return fournum;
	}
	public void setFournum(int fournum)
	{
		this.fournum = fournum;
	}
	public String getFournumpercent()
	{
		return fournumpercent;
	}
	public void setFournumpercent(String fournumpercent)
	{
		this.fournumpercent = fournumpercent;
	}
	public int getFivenum()
	{
		return fivenum;
	}
	public void setFivenum(int fivenum)
	{
		this.fivenum = fivenum;
	}
	public String getFivenumpercent()
	{
		return fivenumpercent;
	}
	public void setFivenumpercent(String fivenumpercent)
	{
		this.fivenumpercent = fivenumpercent;
	}

	public Site getSite()
	{
		return site;
	}
	public void setSite(Site site)
	{
		this.site = site;
	}
	public List<HdModel> getModels()
	{
		return models;
	}
	public void setModels(List<HdModel> models)
	{
		this.models = models;
	}
}
