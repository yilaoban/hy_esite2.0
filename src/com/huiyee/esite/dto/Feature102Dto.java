package com.huiyee.esite.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.huiyee.esite.model.CategoryTree;
import com.huiyee.esite.model.ContentProduct;
import com.huiyee.esite.model.Prolist;
import com.huiyee.esite.model.ProlistProduct;

public class Feature102Dto implements IDto ,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Prolist> prolist;
	private List<List<ProlistProduct>> product;
	// 两层的product与prolist直接用Dto传难以时间所以用String来传,格式
	// prolistid-productid,prolistid2-productid2,
	private List<String> relationStr;
	private long fid;
	
	private long relationid;
	private List<CategoryTree> categoryTreeList;
	private long ccid;
	
	private String type;
	private long pageid;
	
	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public long getPageid()
	{
		return pageid;
	}

	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	public long getCcid()
	{
		return ccid;
	}

	public void setCcid(long ccid)
	{
		this.ccid = ccid;
	}

	public List<CategoryTree> getCategoryTreeList()
	{
		return categoryTreeList;
	}

	public void setCategoryTreeList(List<CategoryTree> categoryTreeList)
	{
		this.categoryTreeList = categoryTreeList;
	}

	public List<Prolist> getProlist() {
		return prolist;
	}

	public void setProlist(List<Prolist> prolist) {
		this.prolist = prolist;
	}

	public List<List<ProlistProduct>> getProduct() {
		return product;
	}

	public void setProduct(List<List<ProlistProduct>> product) {
		this.product = product;
	}

	public List<String> getRelationStr() {
		return relationStr;
	}

	public void setRelationStr(List<String> relationStr) {
		this.relationStr = relationStr;
	}

	public long getFid() {
		return fid;
	}

	public void setFid(long fid) {
		this.fid = fid;
	}

	public long getRelationid()
	{
		return relationid;
	}

	public void setRelationid(long relationid)
	{
		this.relationid = relationid;
	}

}
