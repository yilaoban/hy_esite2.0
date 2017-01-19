package com.huiyee.esite.dto;

import java.util.List;
import com.huiyee.esite.model.Feature;
import com.huiyee.esite.model.Site;
import com.huiyee.esite.model.UploadDetail;
import com.huiyee.esite.model.ZanDetail;

public class LoadPageInteractionDto implements IDto {
	
	private List<Feature> features;
	private List<ZanDetail> zandetails;
	private List<UploadDetail> uploaddetails;
	private Site site;
	
	public List<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(List<Feature> features) {
		this.features = features;
	}

	public List<ZanDetail> getZandetails() {
		return zandetails;
	}

	public void setZandetails(List<ZanDetail> zandetails) {
		this.zandetails = zandetails;
	}

	public List<UploadDetail> getUploaddetails() {
		return uploaddetails;
	}

	public void setUploaddetails(List<UploadDetail> uploaddetails) {
		this.uploaddetails = uploaddetails;
	}

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

}
