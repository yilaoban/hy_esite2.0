package com.huiyee.esite.dto;
import java.util.List;

import com.huiyee.esite.model.HdModel;
import com.huiyee.esite.model.Site;
import com.huiyee.esite.model.Sitegroup;
public class SiteDataDto implements IDto {
	private List<Integer> hvisitnum;
	private List<Integer> nhvisitnum;
	private List<Integer> nhvisitadd;
	private List<String> datelist;
	private List<Sitegroup> grouplist;
	private List<Site> sitelist;
	private String hdterminaldata;
	private String hdareadata;
	private String hdtypedata;
	private List<VisitNum> hdnumlist;
	private List<HdModel> hdlist;
	private List<ReportArea> arealist ;
	private List<ReportTerminalAnalyse> terminallist;
	private int hnum1;
	private int hnum2;
	private int hnum3;
	private int hnum4;
	private int hnum5;

	public List<Integer> getHvisitnum()
	{
		return hvisitnum;
	}

	public void setHvisitnum(List<Integer> hvisitnum)
	{
		this.hvisitnum = hvisitnum;
	}

	public List<Integer> getNhvisitnum()
	{
		return nhvisitnum;
	}

	public void setNhvisitnum(List<Integer> nhvisitnum)
	{
		this.nhvisitnum = nhvisitnum;
	}

	public List<Integer> getNhvisitadd()
	{
		return nhvisitadd;
	}

	public void setNhvisitadd(List<Integer> nhvisitadd)
	{
		this.nhvisitadd = nhvisitadd;
	}

	public List<String> getDatelist()
	{
		return datelist;
	}

	public void setDatelist(List<String> datelist)
	{
		this.datelist = datelist;
	}

	public List<Sitegroup> getGrouplist()
	{
		return grouplist;
	}

	public void setGrouplist(List<Sitegroup> grouplist)
	{
		this.grouplist = grouplist;
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

	public String getHdtypedata()
	{
		return hdtypedata;
	}

	public void setHdtypedata(String hdtypedata)
	{
		this.hdtypedata = hdtypedata;
	}

	public List<VisitNum> getHdnumlist()
	{
		return hdnumlist;
	}

	public void setHdnumlist(List<VisitNum> hdnumlist)
	{
		this.hdnumlist = hdnumlist;
	}

	public List<HdModel> getHdlist()
	{
		return hdlist;
	}

	public void setHdlist(List<HdModel> hdlist)
	{
		this.hdlist = hdlist;
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

	public List<Site> getSitelist()
	{
		return sitelist;
	}

	public void setSitelist(List<Site> sitelist)
	{
		this.sitelist = sitelist;
	}
}
