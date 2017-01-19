package com.huiyee.esite.dto;

import com.huiyee.esite.model.Video;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class Feature101Dto implements IDto,Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Video> list;
    private List<Video> flist;
    private ArrayList<Long> vids;
    private ArrayList<Long> oldvids;
    private String vlvids;
    private long fid;
    private List<Integer> ididxs;
    public List<Video> getList() {
        return list;
    }
    public void setList(List<Video> list) {
        this.list = list;
    }
    public List<Video> getFlist() {
        return flist;
    }
    public void setFlist(List<Video> flist) {
        this.flist = flist;
    }
    public ArrayList<Long> getVids() {
        return vids;
    }
    public void setVids(ArrayList<Long> vids) {
        this.vids = vids;
    }
    public long getFid() {
        return fid;
    }
    public void setFid(long fid) {
        this.fid = fid;
    }
    public String getVlvids() {
        return vlvids;
    }
    public void setVlvids(String vlvids) {
        this.vlvids = vlvids;
    }
    public ArrayList<Long> getOldvids() {
        return oldvids;
    }
    public void setOldvids(ArrayList<Long> oldvids) {
        this.oldvids = oldvids;
    }
    public List<Integer> getIdidxs() {
        return ididxs;
    }
    public void setIdidxs(List<Integer> ididxs) {
        this.ididxs = ididxs;
    }
}
