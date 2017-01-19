package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.huiyee.weixin.model.WxPageShow;


public class Sitegroup implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1287141078474197549L;
	private long id;
	private String groupname;
	private long ownerId;
	private List<Site> site;
	private String type;
	private String stype;
	private Date updatetime;
	private int vpernum;//站点访问人数
	private int vistinum;//站点访问次数
	private int pagenum;
	private Date createtime;
	private String status;
	private String istemplate;
	private long entityid;
	private long loginpage;
	private long registpage;
	private long pv;
	private long uv;
	
	private WxPageShow wps;
	
	
	public WxPageShow getWps()
	{
		return wps;
	}

	
	public void setWps(WxPageShow wps)
	{
		this.wps = wps;
	}

	public long getLoginpage()
	{
		return loginpage;
	}
	
	public void setLoginpage(long loginpage)
	{
		this.loginpage = loginpage;
	}
	
	public long getRegistpage()
	{
		return registpage;
	}
	
	public void setRegistpage(long registpage)
	{
		this.registpage = registpage;
	}
	public Date getCreatetime()
	{
		return createtime;
	}
	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}
	public List<Site> getSite() {
		return site;
	}
	public void setSite(List<Site> site) {
		this.site = site;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
    public Date getUpdatetime() {
        return updatetime;
    }
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
	public int getVpernum()
	{
		return vpernum;
	}
	public void setVpernum(int vpernum)
	{
		this.vpernum = vpernum;
	}
	public int getVistinum()
	{
		return vistinum;
	}
	public void setVistinum(int vistinum)
	{
		this.vistinum = vistinum;
	}
	public int getPagenum()
	{
		return pagenum;
	}
	public void setPagenum(int pagenum)
	{
		this.pagenum = pagenum;
	}
	public String getStype()
	{
		return stype;
	}
	public void setStype(String stype)
	{
		this.stype = stype;
	}
	public String getIstemplate()
	{
		return istemplate;
	}
	public void setIstemplate(String istemplate)
	{
		this.istemplate = istemplate;
	}
	public long getEntityid()
	{
		return entityid;
	}
	public void setEntityid(long entityid)
	{
		this.entityid = entityid;
	}
	public long getPv()
	{
		return pv;
	}
	public void setPv(long pv)
	{
		this.pv = pv;
	}
	public long getUv()
	{
		return uv;
	}
	public void setUv(long uv)
	{
		this.uv = uv;
	}
}
