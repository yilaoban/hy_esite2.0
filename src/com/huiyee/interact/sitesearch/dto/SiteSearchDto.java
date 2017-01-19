package com.huiyee.interact.sitesearch.dto;

import java.util.List;

import org.apache.solr.common.SolrDocument;

import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.model.Sitegroup;
import com.huiyee.interact.sitesearch.model.SiteSearchIndex;

public class SiteSearchDto {

	private List<SolrDocument> docs;
	private List<SiteSearchIndex> ssi_list;
	private List<Sitegroup> sg_list;
	private Pager pager;

	public List<SolrDocument> getDocs() {
		return docs;
	}

	public void setDocs(List<SolrDocument> docs) {
		this.docs = docs;
	}

	public List<SiteSearchIndex> getSsi_list() {
		return ssi_list;
	}

	public void setSsi_list(List<SiteSearchIndex> ssi_list) {
		this.ssi_list = ssi_list;
	}

	public List<Sitegroup> getSg_list() {
		return sg_list;
	}

	public void setSg_list(List<Sitegroup> sg_list) {
		this.sg_list = sg_list;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

}
