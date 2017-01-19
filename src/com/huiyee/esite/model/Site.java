package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Site implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8637341009511303870L;
	private long id;
	private String name;
	private long ownerId;
	private String type;
	private Date createtime;
	private Date updatetime;
	private Date onlinetime;
	private String status;
	private int pageTotal;// page数
	private String wbnickname;
	private SinaToken sinaToken;
	private String groupName;

	private List<Page> pages;
	private long homepageId;
	private int activityCount;
	private List<Long> modules;

	private int vpernum;// 站点访问人数
	private int vistinum;// 站点访问次数
	private String upWhole;

	private long sitegroupid;
	private String groupStype;

	public long getSitegroupid() {
		return sitegroupid;
	}

	public void setSitegroupid(long sitegroupid) {
		this.sitegroupid = sitegroupid;
	}

	public int getActivityCount() {
		return activityCount;
	}

	public void setActivityCount(int activityCount) {
		this.activityCount = activityCount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public Date getOnlinetime() {
		return onlinetime;
	}

	public void setOnlinetime(Date onlinetime) {
		this.onlinetime = onlinetime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}

	public String getWbnickname() {
		return wbnickname;
	}

	public void setWbnickname(String wbnickname) {
		this.wbnickname = wbnickname;
	}

	public SinaToken getSinaToken() {
		return sinaToken;
	}

	public void setSinaToken(SinaToken sinaToken) {
		this.sinaToken = sinaToken;
	}

	public List<Page> getPages() {
		return pages;
	}

	public void setPages(List<Page> pages) {
		this.pages = pages;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public long getHomepageId() {
		return homepageId;
	}

	public void setHomepageId(long homepageId) {
		this.homepageId = homepageId;
	}

	public List<Long> getModules() {
		return modules;
	}

	public void setModules(List<Long> modules) {
		this.modules = modules;
	}

	public int getVpernum() {
		return vpernum;
	}

	public void setVpernum(int vpernum) {
		this.vpernum = vpernum;
	}

	public int getVistinum() {
		return vistinum;
	}

	public void setVistinum(int vistinum) {
		this.vistinum = vistinum;
	}

	public String getUpWhole() {
		return upWhole;
	}

	public void setUpWhole(String upWhole) {
		this.upWhole = upWhole;
	}

	public String getGroupStype() {
		return groupStype;
	}

	public void setGroupStype(String groupStype) {
		this.groupStype = groupStype;
	}

	public String getOffCheckPage() {// 线下签到功能页
		if (pages != null) {
			long fpageid = 0;
			long spageid = 0;
			long dzpageid = 0;
			long dtpageid = 0;
			long jfpageid = 0;
			for (Page page : pages) {
				if ("OCF".equals(page.getApptype())) {
					fpageid = page.getId();
				} else if ("OCS".equals(page.getApptype())) {
					spageid = page.getId();
				} else if ("OCZ".equals(page.getApptype())) {
					dzpageid = page.getId();
				} else if ("OCD".equals(page.getApptype())) {
					dtpageid = page.getId();
				} else if ("OCJ".equals(page.getApptype())) {
					jfpageid = page.getId();
				}
			}
			if (fpageid > 0) {
				return fpageid + "-" + spageid + "-" + dzpageid + "-" + dtpageid + "-" + jfpageid;
			}
		}
		return null;
	}

	public String getPayPage() {// 微商城/积分商城 功能页
		if (pages != null) {
			long dzpage = 0;
			long homepage = 0;
			long userpage = 0;
			long yzpage = 0;
			long spage = 0;
			long fpage = 0;
			long lpage = 0;
			for (Page page : pages) {
				if ("WSD".equals(page.getApptype())) {
					dzpage = page.getId();
				} else if ("WSH".equals(page.getApptype())) {
					homepage = page.getId();
				} else if ("WSU".equals(page.getApptype())) {
					userpage = page.getId();
				} else if ("KYZ".equals(page.getApptype())) {
					yzpage = page.getId();
				} else if ("KYS".equals(page.getApptype())) {
					spage = page.getId();
				} else if ("KYF".equals(page.getApptype())) {
					fpage = page.getId();
				} else if ("WSL".equals(page.getApptype())) {
					lpage = page.getId();
				}
			}
			if (homepage > 0) {
				return dzpage + "-" + homepage + "-" + userpage + "-" + yzpage + "-" + spage + "-" + fpage + "-" + lpage;
			}
		}
		return null;
	}

}
