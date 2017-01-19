package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;

public class FansAnalyse implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = -2636981987790797277L;
    private long sitegroupid;
	private int vfansnum;
	private int vunfansnum;
	private int dfansnum;
	private int dunfansnum;
	private Date createtime;
    public long getSitegroupid() {
        return sitegroupid;
    }
    public void setSitegroupid(long sitegroupid) {
        this.sitegroupid = sitegroupid;
    }
    public int getVfansnum() {
        return vfansnum;
    }
    public void setVfansnum(int vfansnum) {
        this.vfansnum = vfansnum;
    }
    public int getVunfansnum() {
        return vunfansnum;
    }
    public void setVunfansnum(int vunfansnum) {
        this.vunfansnum = vunfansnum;
    }
    public int getDfansnum() {
        return dfansnum;
    }
    public void setDfansnum(int dfansnum) {
        this.dfansnum = dfansnum;
    }
    public int getDunfansnum() {
        return dunfansnum;
    }
    public void setDunfansnum(int dunfansnum) {
        this.dunfansnum = dunfansnum;
    }
	public Date getCreatetime()
	{
		return createtime;
	}
	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}
}
