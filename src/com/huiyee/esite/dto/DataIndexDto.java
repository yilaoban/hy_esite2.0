package com.huiyee.esite.dto;

import com.huiyee.esite.model.AreaAnalysis;
import com.huiyee.esite.model.FansLevelAnalyse;
import java.util.List;
public class DataIndexDto implements IDto {
	private List<ReportViewAllDto> viewall;
    private List<FansLevelAnalyse> levellist;
    private List<AreaAnalysis> arealist;
    private int totalvisitnum;//总访问数
    private int visitnum;//非匿名访问数
    private int nvisitnum;//匿名访问数
    private int todayadd;//每天新增数
    private int totalhdnum;//互动总数
    private String groupname;
    private long sgid;
    private int joinnum;
    private int sucjoinnum;
    private int outnum;
    public List<FansLevelAnalyse> getLevellist() {
        return levellist;
    }
    public void setLevellist(List<FansLevelAnalyse> levellist) {
        this.levellist = levellist;
    }
    public List<AreaAnalysis> getArealist() {
        return arealist;
    }
    public void setArealist(List<AreaAnalysis> arealist) {
        this.arealist = arealist;
    }
    public int getTotalvisitnum() {
        return totalvisitnum;
    }
    public void setTotalvisitnum(int totalvisitnum) {
        this.totalvisitnum = totalvisitnum;
    }
    public int getVisitnum() {
        return visitnum;
    }
    public void setVisitnum(int visitnum) {
        this.visitnum = visitnum;
    }
    public int getNvisitnum() {
        return nvisitnum;
    }
    public void setNvisitnum(int nvisitnum) {
        this.nvisitnum = nvisitnum;
    }
    public int getTodayadd() {
        return todayadd;
    }
    public void setTodayadd(int todayadd) {
        this.todayadd = todayadd;
    }
    public int getTotalhdnum() {
        return totalhdnum;
    }
    public void setTotalhdnum(int totalhdnum) {
        this.totalhdnum = totalhdnum;
    }
    public String getGroupname() {
        return groupname;
    }
    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }
    public long getSgid() {
        return sgid;
    }
    public void setSgid(long sgid) {
        this.sgid = sgid;
    }
    public int getJoinnum() {
        return joinnum;
    }
    public void setJoinnum(int joinnum) {
        this.joinnum = joinnum;
    }
    public int getOutnum() {
        return outnum;
    }
    public void setOutnum(int outnum) {
        this.outnum = outnum;
    }
    public int getSucjoinnum() {
        return sucjoinnum;
    }
    public void setSucjoinnum(int sucjoinnum) {
        this.sucjoinnum = sucjoinnum;
    }
	public List<ReportViewAllDto> getViewall()
	{
		return viewall;
	}
	public void setViewall(List<ReportViewAllDto> viewall)
	{
		this.viewall = viewall;
	}
}
