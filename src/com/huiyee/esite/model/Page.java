package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONObject;

public class Page implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String name;
	private String jspname;
	private String jspstyle;
	private long siteid;
	private Date createtime;
	private Date updatetime;
	private String status;
	private String type;
	private String stype;
	private String isonline;
	private String wol;
	private Long relationid;
	private Long contextid;
	private String bctype;// 用于判断成功、失败
	private String subsina;
	private String subweixin;

	private List<PageCard> pageCards;
	private long pageid;
	private String bg;
	private String pic;
	private String title;
	private long entityid;

	private long pv;
	private long uv;

	private List<WeiXinPage> list;

	private String paramValue;

	// NOR-普通;SHQ-申请;
	// CBS-合伙人申请;CBR-任务详情;CBN-新闻详情;CBY-邀请伙伴详情
	// ADD-广告投放页面;
	// WSD-微商城设置店主页;WSH-微商城首页;WSU-微商城个人中心页;KYZ-卡券验证;KYS-卡券验证成功;KYF-卡券验证失败;WSL-微商城列表页
	// OCS-线下签到成功页;OCF-线下签到页;OCZ-收营员页面;OCD-生成动态二维码的页面;OCJ-消费获得积分成功的页面
	// YYS-预约成功页;YYX-预约详情页;YYD-预约设置店主页;YYQ-店主确定预约页面;YYZ-预约者确认页面;FWL-服务列表；FZL-服务者列表；FWX-服务详情；FZX-服务者详情
	// ZSJ-站内搜索结果页;
	// PJT-服务评价提交页;PJX-评价详情页;PJR-店主回复评价页;PJS-服务人员列表页;PJC-评价列表页
	// RMX-我的会员卡；RMY-我的储值；RMJ-我的积分；RMK-我的卡券；RMC-信息填写成功的页面；RMZ-我的账单
	private String apptype;
	private String sitename;

	public static String APPTYPE_ADD = "ADD";

	public String getSitename() {
		return sitename;
	}

	public void setSitename(String sitename) {
		this.sitename = sitename;
	}

	public String getBctype() {
		return bctype;
	}

	public void setBctype(String bctype) {
		this.bctype = bctype;
	}

	public long getPageid() {
		return pageid;
	}

	public void setPageid(long pageid) {
		this.pageid = pageid;
	}

	public List<PageCard> getPageCards() {
		return pageCards;
	}

	public void setPageCards(List<PageCard> pageCards) {
		this.pageCards = pageCards;
	}

	public Long getRelationid() {
		return relationid;
	}

	public void setRelationid(Long relationid) {
		this.relationid = relationid;
	}

	public Long getContextid() {
		return contextid;
	}

	public void setContextid(Long contextid) {
		this.contextid = contextid;
	}

	public String getIsonline() {
		return isonline;
	}

	public void setIsonline(String isonline) {
		this.isonline = isonline;
	}

	public String getWol() {
		return wol;
	}

	public void setWol(String wol) {
		this.wol = wol;
	}

	public String getStype() {
		return stype;
	}

	public void setStype(String stype) {
		this.stype = stype;
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

	public String getJspname() {
		return jspname;
	}

	public void setJspname(String jspname) {
		this.jspname = jspname;
	}

	public long getSiteid() {
		return siteid;
	}

	public void setSiteid(long siteid) {
		this.siteid = siteid;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSubsina() {
		return subsina;
	}

	public void setSubsina(String subsina) {
		this.subsina = subsina;
	}

	public String getSubweixin() {
		return subweixin;
	}

	public void setSubweixin(String subweixin) {
		this.subweixin = subweixin;
	}

	public String getBg() {
		return bg;
	}

	public void setBg(String bg) {
		this.bg = bg;
	}

	public JSONObject getBgJson() {
		if (!StringUtils.isEmpty(bg)) {
			return JSONObject.fromObject(bg);
		}
		return null;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getJspstyle() {
		return jspstyle;
	}

	public void setJspstyle(String jspstyle) {
		this.jspstyle = jspstyle;
	}

	public List<WeiXinPage> getList() {
		return list;
	}

	public void setList(List<WeiXinPage> list) {
		this.list = list;
	}

	public long getEntityid() {
		return entityid;
	}

	public void setEntityid(long entityid) {
		this.entityid = entityid;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public JSONObject getParam() {
		if (paramValue != null) {
			return JSONObject.fromObject(paramValue);
		}
		return null;
	}

	public long getPv() {
		return pv;
	}

	public void setPv(long pv) {
		this.pv = pv;
	}

	public long getUv() {
		return uv;
	}

	public void setUv(long uv) {
		this.uv = uv;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getApptype() {
		return apptype;
	}

	public void setApptype(String apptype) {
		this.apptype = apptype;
	}

}
