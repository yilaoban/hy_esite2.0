package com.huiyee.esite.dto;

import java.util.List;

public class TerminalDataDto implements IDto{

	private List<List<Object>>  terminaldata;
	private List<ReportTerminalAnalyse> list;
	private String name;
	public List<ReportTerminalAnalyse> getList()
	{
		return list;
	}
	public void setList(List<ReportTerminalAnalyse> list)
	{
		this.list = list;
	}
	public List<List<Object>> getTerminaldata()
	{
		return terminaldata;
	}
	public void setTerminaldata(List<List<Object>> terminaldata)
	{
		this.terminaldata = terminaldata;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}

}
