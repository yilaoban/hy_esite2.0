package com.huiyee.interact.cb.action;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.mgr.IContentManager;
import com.huiyee.esite.model.CbActivity;
import com.huiyee.esite.model.ContentNew;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.solr.HylogSolrServer;
import com.huiyee.interact.cb.dto.CbActivityDto;
import com.huiyee.interact.cb.mgr.ICbActivityMgr;
import com.huiyee.interact.cb.model.InteractCb;
import com.huiyee.weixin.model.WxPageShow;

public class CbActivityAction extends AbstractCbUserAction {
	private static final long serialVersionUID = -927402327123107609L;

	private HylogSolrServer hylogSolrServer;
	private ICbActivityMgr cbActivityMgr;
	private IContentManager contentManager;

	private int pageId = 1;
	private CbActivityDto dto;
	private String source;

	public String execute() throws Exception {
		VisitUser vu = (VisitUser) ServletActionContext.getRequest().getSession().getAttribute("visitUser");
		if (vu == null) {
			return null;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try {
			long owner = this.getOwner();
			long wxuid = vu.getWxuid();
			int rows = 10;
			int start = (pageId - 1) * rows;
			List<CbActivity> list = cbActivityMgr.findActivityList(this.getOwner(), start, rows);
			for (CbActivity ca : list) {
				long aid = ca.getId();
				int shared_num = hylogSolrServer.cbSharedNum(owner, aid);
				ca.setShared_num(shared_num);

				Date now = new Date();
				if (now.before(ca.getStarttime())) {
					ca.setA_status("未开始");
				} else if (now.after(ca.getEndtime())) {
					ca.setA_status("已结束");
				} else {
					boolean shared = hylogSolrServer.cbShared(owner, "t-" + wxuid + "-" + aid);
					ca.setA_status(shared ? "进行中" : "立即参与");
					ca.setActive(true);
				}
			}

			dto = new CbActivityDto();
			dto.setList(list);
			dto.setPageId(pageId);
			out.print(new Gson().toJson(dto));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
		return null;
	}

	public String activity() throws Exception {
		if (source == null || !source.matches("^t-\\d+-\\d+$")) {
			return null;
		}
		String[] kvs = source.split("-");
		long aid = Long.valueOf(kvs[2]);
		CbActivity ca = cbActivityMgr.findActivity(aid);
		if (ca == null) {
			return null;
		}
		long owner = this.getOwner();
		int type = ca.getType();
		long enid = ca.getEnid();
		WxPageShow wps = new WxPageShow();
		if (type == 0) {
			wps.setPageid(enid);
		} else if (type == 1) {
			InteractCb cb = cbActivityMgr.findCb(owner);
			if (cb == null) {
				return null;
			}
			wps.setPageid(cb.getNpageid());
			ContentNew news = contentManager.findNewById(enid, owner);
			if (news != null) {
				wps.setTitle(news.getTitle());
				wps.setPic(news.getSimgurl());
				wps.setDescription(news.getShortdesc());
			}
		}
		ca.setWps(wps);

		dto = new CbActivityDto();
		dto.setActivity(ca);

		// for ashare function
		VisitUser vu = (VisitUser) ServletActionContext.getRequest().getSession().getAttribute("visitUser");
		if (vu != null) {
			if (ca.getType() == 0) {
				vu.setSource(source, ca.getEnid());
			} else if (ca.getType() == 1) {
				vu.setSource(source, wps.getPageid());
			}
		}
		return SUCCESS;
	}

	public void setHylogSolrServer(HylogSolrServer hylogSolrServer) {
		this.hylogSolrServer = hylogSolrServer;
	}

	public void setCbActivityMgr(ICbActivityMgr cbActivityMgr) {
		this.cbActivityMgr = cbActivityMgr;
	}

	public void setContentManager(IContentManager contentManager) {
		this.contentManager = contentManager;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public CbActivityDto getDto() {
		return dto;
	}

	public void setDto(CbActivityDto dto) {
		this.dto = dto;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
