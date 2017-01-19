package com.huiyee.esite.dto;

import com.huiyee.esite.model.HourAnalyse;
import java.util.List;
public class HourReportDto implements IDto {
    private List<Integer>  vnum;//访问数
    private List<Integer>  hnum;//互动数
    private List<HourAnalyse> list;
    private List<String> hour;
    private String name;
    public List<Integer> getVnum() {
        return vnum;
    }
    public void setVnum(List<Integer> vnum) {
        this.vnum = vnum;
    }
    public List<Integer> getHnum() {
        return hnum;
    }
    public void setHnum(List<Integer> hnum) {
        this.hnum = hnum;
    }
    public List<HourAnalyse> getList() {
        return list;
    }
  
    public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public void setList(List<HourAnalyse> list) {
        this.list = list;
    }
	public List<String> getHour()
	{
		return hour;
	}
	public void setHour(List<String> hour)
	{
		this.hour = hour;
	}
}
