package com.huiyee.esite.dto;

import java.util.List;

public class PageDto implements IDto {
	private int addvnum;
	private int addhnum;
	private int totalvnum;
	private int totalhnum;
	private String pagename;
	private long pageid;
	private List<List<Object>> data;
	private SitePage sitePage;
	private int fansnum1;
	private int fansnum2;
	private int fansnum3;
	private int fansnum4;
	private int fansnum5;
	private int fansnum6;
	private int total;
	private List<HdType> typelist;
	private String sitename;
	public int getAddvnum()
	{
		return addvnum;
	}
	public void setAddvnum(int addvnum)
	{
		this.addvnum = addvnum;
	}
	public int getAddhnum()
	{
		return addhnum;
	}
	public void setAddhnum(int addhnum)
	{
		this.addhnum = addhnum;
	}
	public int getTotalvnum()
	{
		return totalvnum;
	}
	public void setTotalvnum(int totalvnum)
	{
		this.totalvnum = totalvnum;
	}
	public int getTotalhnum()
	{
		return totalhnum;
	}
	public void setTotalhnum(int totalhnum)
	{
		this.totalhnum = totalhnum;
	}
	public String getPagename()
	{
		return pagename;
	}
	public void setPagename(String pagename)
	{
		this.pagename = pagename;
	}
	public long getPageid()
	{
		return pageid;
	}
	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}
	public List<List<Object>> getData()
	{
		return data;
	}
	public void setData(List<List<Object>> data)
	{
		this.data = data;
	}
	public SitePage getSitePage()
	{
		return sitePage;
	}
	public void setSitePage(SitePage sitePage)
	{
		this.sitePage = sitePage;
	}
	public int getFansnum1()
	{
		return fansnum1;
	}
	public void setFansnum1(int fansnum1)
	{
		this.fansnum1 = fansnum1;
	}
	public int getFansnum2()
	{
		return fansnum2;
	}
	public void setFansnum2(int fansnum2)
	{
		this.fansnum2 = fansnum2;
	}
	public int getFansnum3()
	{
		return fansnum3;
	}
	public void setFansnum3(int fansnum3)
	{
		this.fansnum3 = fansnum3;
	}
	public int getFansnum4()
	{
		return fansnum4;
	}
	public void setFansnum4(int fansnum4)
	{
		this.fansnum4 = fansnum4;
	}
	public int getFansnum5()
	{
		return fansnum5;
	}
	public void setFansnum5(int fansnum5)
	{
		this.fansnum5 = fansnum5;
	}
	public int getFansnum6()
	{
		return fansnum6;
	}
	public void setFansnum6(int fansnum6)
	{
		this.fansnum6 = fansnum6;
	}
	public int getTotal()
	{
		return total;
	}
	public void setTotal(int total)
	{
		this.total = total;
	}
	public List<HdType> getTypelist()
	{
		return typelist;
	}
	public void setTypelist(List<HdType> typelist)
	{
		this.typelist = typelist;
	}
	public String getSitename()
	{
		return sitename;
	}
	public void setSitename(String sitename)
	{
		this.sitename = sitename;
	}
	 
}
