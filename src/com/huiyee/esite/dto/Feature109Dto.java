package com.huiyee.esite.dto;

import com.huiyee.esite.model.CategoryTree;
import com.huiyee.esite.model.Picture;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class Feature109Dto implements IDto,Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2406446040121987569L;
	private List<Picture> list;
    private List<Picture> flist;
    private ArrayList<Long> pids;
    private ArrayList<Long> oldpids;
    private String vlpids;
    private long fid;
    private List<Integer> ididxs;
    
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
	public long getRelationid()
	{
		return relationid;
	}
	public void setRelationid(long relationid)
	{
		this.relationid = relationid;
	}
	public List<CategoryTree> getCategoryTreeList()
	{
		return categoryTreeList;
	}
	public void setCategoryTreeList(List<CategoryTree> categoryTreeList)
	{
		this.categoryTreeList = categoryTreeList;
	}
	public long getCcid()
	{
		return ccid;
	}
	public void setCcid(long ccid)
	{
		this.ccid = ccid;
	}
	public List<Picture> getList() {
        return list;
    }
    public void setList(List<Picture> list) {
        this.list = list;
    }
    public List<Picture> getFlist() {
        return flist;
    }
    public void setFlist(List<Picture> flist) {
        this.flist = flist;
    }
    public ArrayList<Long> getPids() {
        return pids;
    }
    public void setPids(ArrayList<Long> pids) {
        this.pids = pids;
    }
    public ArrayList<Long> getOldpids() {
        return oldpids;
    }
    public void setOldpids(ArrayList<Long> oldpids) {
        this.oldpids = oldpids;
    }
    public String getVlpids() {
        return vlpids;
    }
    public void setVlpids(String vlpids) {
        this.vlpids = vlpids;
    }
    public long getFid() {
        return fid;
    }
    public void setFid(long fid) {
        this.fid = fid;
    }
    public List<Integer> getIdidxs() {
        return ididxs;
    }
    public void setIdidxs(List<Integer> ididxs) {
        this.ididxs = ididxs;
    }
}
