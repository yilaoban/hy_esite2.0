package com.huiyee.esite.dto;

import java.util.List;

import com.huiyee.esite.model.FansAnalyseRecord;
import com.huiyee.esite.model.Site;
import com.huiyee.esite.model.Sitegroup;

public class FansAnalysisDto implements IDto{

	private List<FansAnalyseRecord> fansList;
	private Site site;
	private Pager pager;
	private int vfansY;
	private int vfansN;
	private int hfansY;
	private int hfansN;

	public int getVfansN() {
		return vfansN;
	}

	public void setVfansN(int vfansN) {
		this.vfansN = vfansN;
	}

	public int getHfansY() {
		return hfansY;
	}

	public void setHfansY(int hfansY) {
		this.hfansY = hfansY;
	}

	public int getHfansN() {
		return hfansN;
	}

	public void setHfansN(int hfansN) {
		this.hfansN = hfansN;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}



	public Site getSite()
	{
		return site;
	}

	public void setSite(Site site)
	{
		this.site = site;
	}

	public int getVfansY() {
		return vfansY;
	}

	public void setVfansY(int vfansY) {
		this.vfansY = vfansY;
	}

    public List<FansAnalyseRecord> getFansList() {
        return fansList;
    }

    public void setFansList(List<FansAnalyseRecord> fansList) {
        this.fansList = fansList;
    }

}
