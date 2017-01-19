package com.huiyee.esite.dto;

import java.util.List;
import com.huiyee.esite.model.MaterialCategory;
import com.huiyee.esite.model.MaterialPic;
public class MaterialDto implements IDto{
	private List<MaterialCategory> fcategoryList;
	private List<MaterialCategory> scategoryList;
	private List<MaterialPic> materiallist;
	private int total;
	private long fid;
	private long sid;
	private Pager pager;
	private int count;
	
	public int getTotal()
	{
		return total;
	}
	public void setTotal(int total)
	{
		this.total = total;
	}
	public List<MaterialPic> getMateriallist()
	{
		return materiallist;
	}
	public void setMateriallist(List<MaterialPic> materiallist)
	{
		this.materiallist = materiallist;
	}
	public List<MaterialCategory> getFcategoryList()
	{
		return fcategoryList;
	}
	public void setFcategoryList(List<MaterialCategory> fcategoryList)
	{
		this.fcategoryList = fcategoryList;
	}
	public List<MaterialCategory> getScategoryList()
	{
		return scategoryList;
	}
	public void setScategoryList(List<MaterialCategory> scategoryList)
	{
		this.scategoryList = scategoryList;
	}
	public long getFid()
	{
		return fid;
	}
	public void setFid(long fid)
	{
		this.fid = fid;
	}
	public long getSid()
	{
		return sid;
	}
	public void setSid(long sid)
	{
		this.sid = sid;
	}
	public Pager getPager() {
		return pager;
	}
	public void setPager(Pager pager) {
		this.pager = pager;
	}
	public int getCount()
	{
		return count;
	}
	public void setCount(int count)
	{
		this.count = count;
	}
}
