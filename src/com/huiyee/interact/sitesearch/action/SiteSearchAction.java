package com.huiyee.interact.sitesearch.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.constants.IPrivilegeConstants;
import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.Sitegroup;
import com.huiyee.esite.service.Permission;
import com.huiyee.interact.sitesearch.dto.SiteSearchDto;
import com.huiyee.interact.sitesearch.mgr.ISiteGroupMgr;
import com.huiyee.interact.sitesearch.mgr.ISiteSearchIndexMgr;
import com.huiyee.interact.sitesearch.mgr.ISiteSearchMgr;
import com.huiyee.interact.sitesearch.model.SiteSearch;
import com.huiyee.interact.sitesearch.model.SiteSearchIndex;
import com.huiyee.interact.sitesearch.solr.SitesearchSolrServer;
import com.opensymphony.xwork2.ActionContext;

import net.sf.json.JSONObject;

public class SiteSearchAction extends AbstractAction {

	private static final long serialVersionUID = -5384356609674099053L;
	private SitesearchSolrServer sitesearchSolrServer;
	private ISiteSearchMgr siteSearchMgr;
	private ISiteSearchIndexMgr siteSearchIndexMgr;
	private ISiteGroupMgr siteGroupMgr;

	private int pageId = 1;
	private SiteSearchDto dto;
	private SiteSearch ss;
	private SiteSearchIndex ssi;
	private String wd;
	private long catid;
	private String url;

	public String resultPage() throws Exception {
		SiteSearch ss = siteSearchMgr.getSiteSearchByOwner(this.getOwner());
		if (ss == null || ss.getPageid() == 0) {
			return null;
		}
		ActionContext.getContext().getSession().put("wd", wd);
		url = "/" + this.getOname() + "/user/show/" + ss.getPageid() + "/ssh.html";
		return SUCCESS;
	}

	public String result() throws Exception {
		if (StringUtils.isBlank(wd)) {
			wd = (String) ActionContext.getContext().getSession().get("wd");
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try {
			SiteSearch ss = siteSearchMgr.getSiteSearchByOwner(this.getOwner());
			if (ss == null) {
				return null;
			}

			if (StringUtils.isNotBlank(wd)) {
				List<Long> sgidList = siteSearchIndexMgr.getSitegroupidList(this.getOwner(), 1);
				int rows = 10;
				int start = (pageId - 1) * rows;
				QueryResponse qr = sitesearchSolrServer.search(wd, this.getOwner(), sgidList, catid, start, rows);
				if (qr != null) {
					Map<String, Map<String, List<String>>> highlighting = qr.getHighlighting();
					SolrDocumentList docs = qr.getResults();
					for (SolrDocument doc : docs) {
						String id = (String) doc.getFieldValue("id");
						Map<String, List<String>> map = highlighting.get(id);
						for (Map.Entry<String, List<String>> entry : map.entrySet()) {
							String key = entry.getKey();
							List<String> value = entry.getValue();
							doc.setField(key, value);
						}
					}

					Long numFound = docs.getNumFound();
					JSONObject json = new JSONObject();
					json.put("wd", wd);
					json.put("docs", docs);
					json.put("currentPage", pageId);
					json.put("totalPages", numFound / rows + 1);
					out.print(json);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}

	@Override
	@Permission(module = IPrivilegeConstants.MODULE_APP_Õ¾ÄÚËÑË÷, privilege = IPrivilegeConstants.PERMISSION_R)
	public String execute() throws Exception {
		this.setLightType(1);

		SiteSearch ss = siteSearchMgr.getSiteSearchByOwner(this.getOwner());
		if (ss == null) {
			return ERROR;
		}
		if (ss.getPageid() > 0 && StringUtils.isNotBlank(ss.getJspname())) {
			url = "/WEB-INF/pageshow/" + ss.getJspname();
		} else {
			url = "/WEB-INF/pageshow/search_result.jsp";
		}

		if (StringUtils.isNotBlank(wd)) {
			List<Long> sgidList = siteSearchIndexMgr.getSitegroupidList(this.getOwner(), 1);
			int rows = 10;
			int start = (pageId - 1) * rows;
			QueryResponse response = sitesearchSolrServer.search(wd, this.getOwner(), sgidList, catid, start, rows);
			if (response != null) {
				Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
				SolrDocumentList docs = response.getResults();
				for (SolrDocument doc : docs) {
					String id = (String) doc.getFieldValue("id");
					Map<String, List<String>> map = highlighting.get(id);
					for (Map.Entry<String, List<String>> entry : map.entrySet()) {
						String key = entry.getKey();
						List<String> value = entry.getValue();
						doc.setField(key, value);
					}
				}

				Long numFound = docs.getNumFound();
				dto = new SiteSearchDto();
				dto.setDocs(docs);
				dto.setPager(new Pager(pageId, numFound.intValue(), rows));
			}
		}

		return SUCCESS;
	}

	public String open() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try {
			SiteSearch ss = new SiteSearch();
			ss.setOwnerid(this.getOwner());
			int res = siteSearchMgr.addSiteSearch(ss);
			out.print(res);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}

	@Permission(module = IPrivilegeConstants.MODULE_APP_Õ¾ÄÚËÑË÷, privilege = IPrivilegeConstants.PERMISSION_R)
	public String config() throws Exception {
		this.setLightType(2);

		long ownerid = this.getOwner();
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		List<Sitegroup> sg_list = pageCompose.findPageTofs(account, "Q");
		ss = siteSearchMgr.getSiteSearchByOwner(ownerid);
		if (ss == null) {
			return ERROR;
		}

		int rows = 10;
		int start = (pageId - 1) * rows;
		int total = siteSearchIndexMgr.getSiteSearchIndexCount(ownerid);
		List<SiteSearchIndex> list = siteSearchIndexMgr.getSiteSearchIndexList(ownerid, start, rows);

		dto = new SiteSearchDto();
		dto.setSg_list(sg_list);
		dto.setSsi_list(list);
		dto.setPager(new Pager(pageId, total, rows));
		return SUCCESS;
	}

	public String savePage() throws Exception {
		if (ss == null) {
			return null;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try {
			ss.setOwnerid(this.getOwner());
			int res = siteSearchMgr.updateSiteSearch(ss);
			out.print(res);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}

	public String index() throws Exception {
		long ownerid = this.getOwner();
		List<String> types = new ArrayList<String>();
		types.add("G");
		types.add("S");
		types.add("F");
		types.add("Y");

		int rows = 10;
		int start = (pageId - 1) * rows;
		int total = siteGroupMgr.getSiteGroupCount(ownerid, types);
		List<Sitegroup> sg_list = siteGroupMgr.getSiteGroupList(ownerid, types, start, rows);

		dto = new SiteSearchDto();
		dto.setSg_list(sg_list);
		dto.setPager(new Pager(pageId, total, rows));

		return SUCCESS;
	}

	public String addIndex() throws Exception {
		if (ssi == null) {
			return null;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try {
			ssi.setOwnerid(this.getOwner());
			ssi.setOname(this.getOname());
			ssi.setStatus(1);
			int res = siteSearchIndexMgr.addSiteSearchIndex(ssi);
			out.print(res);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}

	public String updateIndex() throws Exception {
		if (ssi == null) {
			return null;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try {
			int res = siteSearchIndexMgr.updateStarttime(ssi.getId());
			out.print(res);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}

	public String deleteIndex() throws Exception {
		if (ssi == null) {
			return null;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try {
			int res = siteSearchIndexMgr.deleteSiteSearchIndex(ssi.getId());
			out.print(res);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}

	public String updateStatus() throws Exception {
		if (ssi == null) {
			return null;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try {
			int res = siteSearchIndexMgr.updateStatus(ssi.getId(), ssi.getStatus());
			out.print(res);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}

	public void setSitesearchSolrServer(SitesearchSolrServer sitesearchSolrServer) {
		this.sitesearchSolrServer = sitesearchSolrServer;
	}

	public void setSiteSearchMgr(ISiteSearchMgr siteSearchMgr) {
		this.siteSearchMgr = siteSearchMgr;
	}

	public void setSiteSearchIndexMgr(ISiteSearchIndexMgr siteSearchIndexMgr) {
		this.siteSearchIndexMgr = siteSearchIndexMgr;
	}

	public void setSiteGroupMgr(ISiteGroupMgr siteGroupMgr) {
		this.siteGroupMgr = siteGroupMgr;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public SiteSearchDto getDto() {
		return dto;
	}

	public void setDto(SiteSearchDto dto) {
		this.dto = dto;
	}

	public SiteSearch getSs() {
		return ss;
	}

	public void setSs(SiteSearch ss) {
		this.ss = ss;
	}

	public SiteSearchIndex getSsi() {
		return ssi;
	}

	public void setSsi(SiteSearchIndex ssi) {
		this.ssi = ssi;
	}

	public String getWd() {
		return wd;
	}

	public void setWd(String wd) {
		this.wd = wd;
	}

	public long getCatid() {
		return catid;
	}

	public void setCatid(long catid) {
		this.catid = catid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
