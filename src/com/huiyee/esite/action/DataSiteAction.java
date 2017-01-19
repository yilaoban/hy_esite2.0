package com.huiyee.esite.action;

import java.util.List;

import org.apache.solr.client.solrj.response.GroupCommand;
import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.compose.IPageCompose;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dto.NewDataDto;
import com.huiyee.esite.dto.SiteGroupDto;
import com.huiyee.esite.dto.SolrDataDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.Site;
import com.huiyee.esite.model.Sitegroup;
import com.huiyee.esite.solr.HylogSolrServer;

public class DataSiteAction extends AbstractAction {

	private static final long serialVersionUID = -2578135178579206558L;

	private IPageCompose pageCompose;
	private HylogSolrServer hylogSolrServer;

	private int pageId = 1;// 分页
	private NewDataDto dto;

	private String starttime;
	private String endtime;

	private long siteid;
	private String ptype;// 页面类型 today, week, top10, 2week
	private String mtype; // wx, wb, dl
	private String gtype;// 按小时 HOUR, 按天 DAY
	private String source;
	private int checked;


	@Override
	public String execute() throws Exception {
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		SiteGroupDto sgd = (SiteGroupDto) pageCompose.composeSiteGroupList(account, pageId, IPageConstants.SITEGROUP_TYPE_EVENT);
		dto = new NewDataDto();
		dto.setPager(sgd.getPager());
		dto.setList(sgd.getList());

		List<Sitegroup> list = dto.getList();
		if (list != null) {
			for (Sitegroup sitegroup : list) {
				long siteid = 0;
				List<Site> slist = sitegroup.getSite();
				if (slist != null && slist.size() > 0) {
					if ("W".equals(sitegroup.getType())) {
						for (Site s : slist) {
							if ("P".equals(s.getType())) {
								siteid = s.getId();
							}
						}
					} else {
						siteid = slist.get(0).getId();
					}
				}
				sitegroup.setId(siteid);

				SolrDataDto sdd = hylogSolrServer.pvUv(ownerid, siteid, 0);
				List<GroupCommand> gclist = sdd.getGclist();
				if (gclist != null && gclist.size() > 0) {
					GroupCommand gc = gclist.get(0);
					sitegroup.setPv(gc.getMatches());
					sitegroup.setUv(gc.getNGroups());
				}
			}
		}
		return SUCCESS;
	}

	public String data() throws Exception {
		checked = 1;
		return SUCCESS;
	}

	public String user() throws Exception {
		checked = 2;
		return SUCCESS;
	}

	@Override
	public int getLightType() {
		return 3;
	}

	public void setPageCompose(IPageCompose pageCompose) {
		this.pageCompose = pageCompose;
	}

	public void setHylogSolrServer(HylogSolrServer hylogSolrServer) {
		this.hylogSolrServer = hylogSolrServer;
	}

	public String getPtype() {
		return ptype;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public NewDataDto getDto() {
		return dto;
	}

	public void setDto(NewDataDto dto) {
		this.dto = dto;
	}

	public long getSiteid() {
		return siteid;
	}

	public void setSiteid(long siteid) {
		this.siteid = siteid;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getMtype() {
		return mtype;
	}

	public void setMtype(String mtype) {
		this.mtype = mtype;
	}

	public String getGtype() {
		return gtype;
	}

	public void setGtype(String gtype) {
		this.gtype = gtype;
	}

	public void setPtype(String ptype) {
		this.ptype = ptype;
	}

	public int getChecked() {
		return checked;
	}

	public void setChecked(int checked) {
		this.checked = checked;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
