package com.huiyee.esite.dto;

import java.util.List;

import com.huiyee.esite.model.Feature;
import com.huiyee.esite.model.Page;

public class FeatureActionDto implements IDto{

	private Page page;
	private List<Feature> features;
	private String sitename;

	public List<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(List<Feature> features) {
		this.features = features;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

    public String getSitename() {
        return sitename;
    }

    public void setSitename(String sitename) {
        this.sitename = sitename;
    }


}
