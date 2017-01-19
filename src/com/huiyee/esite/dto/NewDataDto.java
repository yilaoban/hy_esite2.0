package com.huiyee.esite.dto;

import java.util.List;

import org.apache.solr.common.SolrDocument;

import com.huiyee.esite.model.Sitegroup;

public class NewDataDto implements IDto {

	private List<Sitegroup> list;

	private List<String> xcategory;

	private List<Integer> uv;
	private List<Integer> pv;

	private List<Integer> share;
	private List<Integer> click;
	private List<Integer> subscribe;
	private List<Integer> interact;
	private List<Integer> outer;

	private List<List<Object>> area;
	private List<List<Object>> terminal;
	private List<List<Object>> loyalty;

	private Pager pager;
	private List<SolrDocument> ulist;
	private List<SolrDocument> plist;

	public List<Sitegroup> getList() {
		return list;
	}

	public void setList(List<Sitegroup> list) {
		this.list = list;
	}

	public List<Integer> getUv() {
		return uv;
	}

	public void setUv(List<Integer> uv) {
		this.uv = uv;
	}

	public List<Integer> getPv() {
		return pv;
	}

	public void setPv(List<Integer> pv) {
		this.pv = pv;
	}

	public List<Integer> getShare() {
		return share;
	}

	public void setShare(List<Integer> share) {
		this.share = share;
	}

	public List<Integer> getClick() {
		return click;
	}

	public void setClick(List<Integer> click) {
		this.click = click;
	}

	public List<Integer> getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(List<Integer> subscribe) {
		this.subscribe = subscribe;
	}

	public List<Integer> getInteract() {
		return interact;
	}

	public void setInteract(List<Integer> interact) {
		this.interact = interact;
	}

	public List<Integer> getOuter() {
		return outer;
	}

	public void setOuter(List<Integer> outer) {
		this.outer = outer;
	}

	public List<String> getXcategory() {
		return xcategory;
	}

	public void setXcategory(List<String> xcategory) {
		this.xcategory = xcategory;
	}

	public List<List<Object>> getArea() {
		return area;
	}

	public void setArea(List<List<Object>> area) {
		this.area = area;
	}

	public List<List<Object>> getTerminal() {
		return terminal;
	}

	public void setTerminal(List<List<Object>> terminal) {
		this.terminal = terminal;
	}

	public List<List<Object>> getLoyalty() {
		return loyalty;
	}

	public void setLoyalty(List<List<Object>> loyalty) {
		this.loyalty = loyalty;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public List<SolrDocument> getUlist() {
		return ulist;
	}

	public void setUlist(List<SolrDocument> ulist) {
		this.ulist = ulist;
	}

	public List<SolrDocument> getPlist() {
		return plist;
	}

	public void setPlist(List<SolrDocument> plist) {
		this.plist = plist;
	}

}
