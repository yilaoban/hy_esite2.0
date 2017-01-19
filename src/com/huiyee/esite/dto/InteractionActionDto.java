package com.huiyee.esite.dto;

import java.util.List;

import com.huiyee.esite.model.Feature;
import com.huiyee.esite.model.SinaChecklistRecord;
import com.huiyee.esite.model.SinaUserApp;
import com.huiyee.esite.model.Site;
import com.huiyee.esite.model.Sitegroup;
import com.huiyee.esite.model.UploadDetail;
import com.huiyee.esite.model.ZanDetail;

public class InteractionActionDto implements IDto {
	
	private Site site;
	private List<Feature> features;
	private List<ZanDetail> zandetails;
	private List<UploadDetail> uploaddetails;
	private List<SinaUserApp> sinausers;
	private List<SinaChecklistRecord> sinashare;
	private Pager pager;



	public Site getSite()
	{
		return site;
	}

	public void setSite(Site site)
	{
		this.site = site;
	}

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

	public List<SinaUserApp> getSinausers()
	{
	    return sinausers;
	}

	public void setSinausers(List<SinaUserApp> sinausers)
	{
	    this.sinausers = sinausers;
	}

	public Pager getPager()
	{
	    return pager;
	}

	public void setPager(Pager pager)
	{
	    this.pager = pager;
	}

	public List<SinaChecklistRecord> getSinashare() {
		return sinashare;
	}

	public void setSinashare(List<SinaChecklistRecord> sinashare) {
		this.sinashare = sinashare;
	}

}
