
package com.huiyee.esite.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.constants.IPrivilegeConstants;
import com.huiyee.esite.dto.MarketingEbDto;
import com.huiyee.esite.mgr.IBBSManager;
import com.huiyee.esite.mgr.IMarketingEbMgr;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.MarketingSet;
import com.huiyee.esite.model.Sitegroup;
import com.huiyee.esite.service.Permission;
import com.huiyee.esite.util.HyConfig;

/**
 * 微电商
 * 
 * @author ldw
 * 
 */
public class MarketingEbAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private IMarketingEbMgr marketingEbMgr;
	private IBBSManager bbsMgr;
	private MarketingEbDto dto;
	private long ccid;
	private int pageId = 1;
	private String subtype = "W";
	private long pid;
	private String code;
	private String status;
	private List<Sitegroup> list;
	private int tool = 1;
	private MarketingSet marketingSet;

	@Permission(module=IPrivilegeConstants.MODULE_APP_商城,privilege=IPrivilegeConstants.PERMISSION_R)
	public String productList() throws Exception {
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		if (ccid > 0 || StringUtils.isNotEmpty(subtype)) {
			dto = (MarketingEbDto) marketingEbMgr.findProductList(ccid, account.getOwner().getId(), pageId, subtype);
			List<Long> topic = bbsMgr.findEntityByType(account.getOwner().getId(), "T");
			dto.setTopicList(topic);
		}
		try {
			subtype = dto.getCurrent().getSubtype();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setLightType(2);
		return SUCCESS;
	}
	
	public String findLevelbyOwner() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		dto = (MarketingEbDto) marketingEbMgr.findLevelbyOwner(account.getOwner().getId(),pid);
		return SUCCESS;
	}

	public String orderList() throws Exception {
		return SUCCESS;
	}

	public String productCode() throws Exception {

		dto = (MarketingEbDto) marketingEbMgr.findProductCodeList(pid, pageId, code, status);
		return SUCCESS;

	}

	public String productCodeUpload() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Access-Control-Allow-Origin", HyConfig.getPageyuming(this.getOwner()));
		response.addHeader("Access-Control-Allow-Methods", "HEAD,GET,POST,PUT,DELETE,OPTIONS");
		response.addHeader("Access-Control-Allow-Headers", "Content-Type,Origin,Accept");
		response.addHeader("Access-Control-Max-Age", "120");
		PrintWriter out = response.getWriter();
		HttpServletRequest request = ServletActionContext.getRequest();
		List<String> codes = IOUtils.readLines(request.getInputStream());
		if (codes != null && codes.size() > 0) {
			List<String> insertList = new ArrayList<String>();
			for (String str : codes) {
				if (!insertList.contains(str)) {
					insertList.add(str.trim());
				}
			}
			marketingEbMgr.saveProductCode(pid, insertList);
			out.print("{\"status\":1}");
		} else {
			out.print("{\"status\":2}");
		}
		out.flush();
		out.close();
		return null;
	}

	public String jifenList() throws Exception {
		return SUCCESS;
	}

	@Permission(module=IPrivilegeConstants.MODULE_APP_商城,privilege=IPrivilegeConstants.PERMISSION_R)
	public String marketingHomePage() throws Exception {
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		if ("W".equals(subtype)) {
			list = pageCompose.findPageTofs(account, IPageConstants.SITE_GROUP_S);
		} else if ("J".equals(subtype)) {
			list = pageCompose.findPageTofs(account, IPageConstants.SITE_GROUP_F);
		}
		marketingSet = marketingEbMgr.findSetting(account, subtype);
		if ("J".equals(subtype)) {
			return "jifen";
		}
		this.setLightType(4);
		return SUCCESS;
	}

	public void setMarketingEbMgr(IMarketingEbMgr marketingEbMgr) {
		this.marketingEbMgr = marketingEbMgr;
	}

	public void setBbsMgr(IBBSManager bbsMgr) {
		this.bbsMgr = bbsMgr;
	}

	public MarketingEbDto getDto() {
		return dto;
	}

	public void setDto(MarketingEbDto dto) {
		this.dto = dto;
	}

	public long getCcid() {
		return ccid;
	}

	public void setCcid(long ccid) {
		this.ccid = ccid;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public String getSubtype() {
		if (subtype != null) {
			return subtype.toUpperCase();
		}
		return subtype;
	}

	public void setSubtype(String subtype) {
		if (subtype != null) {
			this.subtype = subtype.toUpperCase();
		}
		this.subtype = subtype;
	}

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getTool() {
		return tool;
	}

	public List<Sitegroup> getList() {
		return list;
	}

	public void setList(List<Sitegroup> list) {
		this.list = list;
	}

	public MarketingSet getMarketingSet() {
		return marketingSet;
	}

	public void setMarketingSet(MarketingSet marketingSet) {
		this.marketingSet = marketingSet;
	}

}
