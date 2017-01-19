package com.huiyee.esite.dto;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FieldStatsInfo;
import org.apache.solr.client.solrj.response.GroupCommand;
import org.apache.solr.client.solrj.response.RangeFacet;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

@SuppressWarnings("rawtypes")
public class SolrDataDto implements IDto {
	private List<FacetField> fflist;

	private List<RangeFacet> rflist;
	private List<GroupCommand> gclist;
	private FacetField field;
	private Map<String, Integer> fq;
	private FieldStatsInfo fsi;
	private int total;
	private SolrDocumentList documentList;
	private SolrDocument document;

	public List<FacetField> getFflist() {
		return fflist;
	}

	public void setFflist(List<FacetField> fflist) {
		this.fflist = fflist;
	}

	public List<RangeFacet> getRflist() {
		return rflist;
	}

	public void setRflist(List<RangeFacet> rflist) {
		this.rflist = rflist;
	}

	public FacetField getField() {
		return field;
	}

	public void setField(FacetField field) {
		this.field = field;
	}

	public Map<String, Integer> getFq() {
		return fq;
	}

	public void setFq(Map<String, Integer> fq) {
		this.fq = fq;
	}

	public FieldStatsInfo getFsi() {
		return fsi;
	}

	public void setFsi(FieldStatsInfo fsi) {
		this.fsi = fsi;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<GroupCommand> getGclist() {
		return gclist;
	}

	public void setGclist(List<GroupCommand> gclist) {
		this.gclist = gclist;
	}

	public SolrDocumentList getDocumentList() {
		return documentList;
	}

	public void setDocumentList(SolrDocumentList documentList) {
		this.documentList = documentList;
	}

	public SolrDocument getDocument() {
		return document;
	}

	public void setDocument(SolrDocument document) {
		this.document = document;
	}
}
