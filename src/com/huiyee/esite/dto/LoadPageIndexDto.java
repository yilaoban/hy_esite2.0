package com.huiyee.esite.dto;
import java.util.List;
import com.huiyee.esite.model.Module;
import com.huiyee.esite.model.SinaApp;
import com.huiyee.esite.model.Site;
public class LoadPageIndexDto implements IDto{

	private List<Site> siteList;
	private List<Module> modules;
    private String result;
    private List<SinaApp> apps;
    private Site site;
    private List<Long> moduleArr;
	
	public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<SinaApp> getApps() {
        return apps;
    }

    public void setApps(List<SinaApp> apps) {
        this.apps = apps;
    }

    public List<Site> getSiteList() {
		return siteList;
	}

	public void setSiteList(List<Site> siteList) {
		this.siteList = siteList;
	}

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public List<Long> getModuleArr() {
        return moduleArr;
    }

    public void setModuleArr(List<Long> moduleArr) {
        this.moduleArr = moduleArr;
    }
}
