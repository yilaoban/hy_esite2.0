package com.huiyee.esite.dto;

import com.huiyee.esite.model.Site;
import com.huiyee.esite.model.Sitegroup;
import java.util.List;
public class DataCenterDto implements IDto {
	private List<Sitegroup> grouplist;
	private List<Integer> pernumlist;
	private List<Integer> numlist;
	private List<VisitNum> vistinumlist;
	private List<VisitNum> hdnumlist;
	private String hdterminaldata;
	private String hdareadata;
	private Sitegroup allsitegroup;
	private String gnames;
	private List<ReportArea> arealist ;
	private List<ReportTerminalAnalyse> terminallist;
	private int hnum1;
	private int hnum2;
	private int hnum3;
	private int hnum4;
	private int hnum5;
	private int vnum1;
	private int vnum2;
	private int vnum3;
	private int vnum4;
	private int vnum5;
	private List<Site> siteList;
	
	public List<Site> getSiteList()
	{
		return siteList;
	}
	public void setSiteList(List<Site> siteList)
	{
		this.siteList = siteList;
	}
	public List<Sitegroup> getGrouplist()
	{
		return grouplist;
	}
	public void setGrouplist(List<Sitegroup> grouplist)
	{
		this.grouplist = grouplist;
	}
	public List<VisitNum> getVistinumlist()
	{
		return vistinumlist;
	}
	public void setVistinumlist(List<VisitNum> vistinumlist)
	{
		this.vistinumlist = vistinumlist;
	}
	public List<VisitNum> getHdnumlist()
	{
		return hdnumlist;
	}
	public void setHdnumlist(List<VisitNum> hdnumlist)
	{
		this.hdnumlist = hdnumlist;
	}
	public String getHdterminaldata()
	{
		return hdterminaldata;
	}
	public void setHdterminaldata(String hdterminaldata)
	{
		this.hdterminaldata = hdterminaldata;
	}
	public String getHdareadata()
	{
		return hdareadata;
	}
	public void setHdareadata(String hdareadata)
	{
		this.hdareadata = hdareadata;
	}
	public Sitegroup getAllsitegroup()
	{
		return allsitegroup;
	}
	public void setAllsitegroup(Sitegroup allsitegroup)
	{
		this.allsitegroup = allsitegroup;
	}
	public List<Integer> getPernumlist()
	{
		return pernumlist;
	}
	public void setPernumlist(List<Integer> pernumlist)
	{
		this.pernumlist = pernumlist;
	}
	public List<Integer> getNumlist()
	{
		return numlist;
	}
	public void setNumlist(List<Integer> numlist)
	{
		this.numlist = numlist;
	}
	public String getGnames()
	{
		return gnames;
	}
	public void setGnames(String gnames)
	{
		this.gnames = gnames;
	}
	public List<ReportArea> getArealist()
	{
		return arealist;
	}
	public void setArealist(List<ReportArea> arealist)
	{
		this.arealist = arealist;
	}
	public List<ReportTerminalAnalyse> getTerminallist()
	{
		return terminallist;
	}
	public void setTerminallist(List<ReportTerminalAnalyse> terminallist)
	{
		this.terminallist = terminallist;
	}
	public int getHnum1()
	{
		return hnum1;
	}
	public void setHnum1(int hnum1)
	{
		this.hnum1 = hnum1;
	}
	public int getHnum2()
	{
		return hnum2;
	}
	public void setHnum2(int hnum2)
	{
		this.hnum2 = hnum2;
	}
	public int getHnum3()
	{
		return hnum3;
	}
	public void setHnum3(int hnum3)
	{
		this.hnum3 = hnum3;
	}
	public int getHnum4()
	{
		return hnum4;
	}
	public void setHnum4(int hnum4)
	{
		this.hnum4 = hnum4;
	}
	public int getHnum5()
	{
		return hnum5;
	}
	public void setHnum5(int hnum5)
	{
		this.hnum5 = hnum5;
	}
	public int getVnum1()
	{
		return vnum1;
	}
	public void setVnum1(int vnum1)
	{
		this.vnum1 = vnum1;
	}
	public int getVnum2()
	{
		return vnum2;
	}
	public void setVnum2(int vnum2)
	{
		this.vnum2 = vnum2;
	}
	public int getVnum3()
	{
		return vnum3;
	}
	public void setVnum3(int vnum3)
	{
		this.vnum3 = vnum3;
	}
	public int getVnum4()
	{
		return vnum4;
	}
	public void setVnum4(int vnum4)
	{
		this.vnum4 = vnum4;
	}
	public int getVnum5()
	{
		return vnum5;
	}
	public void setVnum5(int vnum5)
	{
		this.vnum5 = vnum5;
	}
}
