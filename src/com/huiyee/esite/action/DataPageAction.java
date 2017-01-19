package com.huiyee.esite.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.GroupCommand;
import org.apache.solr.client.solrj.response.RangeFacet;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.compose.IPageCompose;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dto.NewDataDto;
import com.huiyee.esite.dto.PageConfigDto;
import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.dto.SolrDataDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.Site;
import com.huiyee.esite.model.WxUser;
import com.huiyee.esite.solr.HylogSolrServer;

public class DataPageAction extends AbstractAction {

	private static final long serialVersionUID = -2578135178579206558L;

	private IPageCompose pageCompose;
	private HylogSolrServer hylogSolrServer;

	private List<Page> plist;
	private NewDataDto dto;

	private int pageId = 1;// 分页
	private String starttime;
	private String endtime;

	private long siteid;
	private long pageid;
	private String ptype;// 页面类型 site, top10
	private String mtype; // wx, wb, dl
	private String gtype;// 按小时 HOUR, 按天 DAY

	@Override
	public String execute() throws Exception {
		if (StringUtils.isBlank(ptype)) {
			ptype = "top10";
		}
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();

		SimpleDateFormat sdf_start = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		SimpleDateFormat sdf_end = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		Calendar cal = Calendar.getInstance();
		if ("today".equals(ptype)) {
			String start = sdf_start.format(cal.getTime());
			String end = sdf_end.format(cal.getTime());
			plist = pageCompose.findOnlinePageByOwnerid(ownerid, start, end);
		} else if ("week".equals(ptype)) {
			String end = sdf_end.format(cal.getTime());
			cal.add(Calendar.DAY_OF_YEAR, -7);
			String start = sdf_start.format(cal.getTime());
			plist = pageCompose.findOnlinePageByOwnerid(ownerid, start, end);
		} else if ("site".equals(ptype)) {
			PageConfigDto dto = (PageConfigDto) pageCompose.composePageconfigNew(siteid, account);
			Site site = dto.getSite();
			if (site != null) {
				plist = site.getPages();
				for (Page page : plist) {
					page.setJspname(site.getName());
					SolrDataDto sdd = hylogSolrServer.pvUv(ownerid, 0, page.getId());
					List<GroupCommand> gclist = sdd.getGclist();
					if (gclist != null && gclist.size() > 0) {
						GroupCommand gc = gclist.get(0);
						page.setPv(gc.getMatches());
						page.setUv(gc.getNGroups());
					}
				}
			}
		} else if ("top10".equals(ptype)) {
			plist = new ArrayList<Page>();
			int rows = 10;
			int start = (pageId - 1) * 10;
			SolrDataDto sdd = hylogSolrServer.top10Page(ownerid, start, rows);
			FacetField field = sdd.getField();
			if (field != null) {
				List<FacetField.Count> ffcList = field.getValues();
				for (FacetField.Count ffc : ffcList) {
					String name = ffc.getName();
					// long count = ffc.getCount();

					long pageid = Long.valueOf(name);
					Page page = pageCompose.findPageByPageid(pageid);
					if (page != null) {
						SolrDataDto sdd1 = hylogSolrServer.pvUv(ownerid, 0, pageid);
						List<GroupCommand> gclist = sdd1.getGclist();
						if (gclist != null && gclist.size() > 0) {
							GroupCommand gc = gclist.get(0);
							page.setPv(gc.getMatches());
							page.setUv(gc.getNGroups());
							plist.add(page);
						}
					}
				}
			}
		}
		return SUCCESS;
	}

	public String data() throws Exception {
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();

		// 访问数据
		Date start = null;
		Date end = null;
		Calendar cal = Calendar.getInstance();
		if ("today".equals(ptype)) {
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.add(Calendar.DAY_OF_YEAR, 1);
			end = cal.getTime();

			cal.add(Calendar.DAY_OF_YEAR, -1);
			start = cal.getTime();
		} else if ("week".equals(ptype)) {
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.add(Calendar.DAY_OF_YEAR, 1);
			end = cal.getTime();

			cal.add(Calendar.DAY_OF_YEAR, -7);
			start = cal.getTime();
		} else if ("top10".equals(ptype)) {
			Page page = pageCompose.findPageByPageid(pageid);
			if (page != null) {
				Date online = page.getUpdatetime();
				if (online != null) {
					cal.setTime(online);
				}
			}
			if (StringUtils.isNotBlank(starttime) && StringUtils.isNotBlank(endtime)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				start = sdf.parse(starttime);
				cal.setTime(sdf.parse(endtime));
				cal.add(Calendar.DAY_OF_YEAR, 1);
				end = cal.getTime();
			} else {
				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				start = cal.getTime();

				cal.add(Calendar.DAY_OF_YEAR, 7);
				end = cal.getTime();
			}
		}

		dto = new NewDataDto();
		SolrDataDto sdd_pv = hylogSolrServer.pvDays(ownerid, mtype, 0, pageid, gtype, start, end);
		SolrDataDto sdd_uv = hylogSolrServer.uvDays(ownerid, mtype, 0, pageid, gtype, start, end);
		if ("HOUR".equals(gtype)) {
			toHour(dto, sdd_pv, sdd_uv);
		} else if ("DAY".equals(gtype)) {
			toDays(dto, sdd_pv, sdd_uv, start, end);
		}

		// 区域分布
		SolrDataDto sdd_area = hylogSolrServer.dataArea(ownerid, mtype, 0, pageid);
		toArea(dto, sdd_area);
		// 终端分布
		SolrDataDto sdd_terminal = hylogSolrServer.dataTerminal(ownerid, mtype, 0, pageid);
		toTerminal(dto, sdd_terminal);

		return SUCCESS;
	}

	public String user() throws Exception {
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();

		int start = (pageId - 1) * IPageConstants.CONTENT_MOUNTS;
		int rows = IPageConstants.CONTENT_MOUNTS;
		Date sDate = null;
		Date eDate = null;
		if (StringUtils.isNotBlank(starttime) && StringUtils.isNotBlank(endtime)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			sDate = sdf.parse(starttime);
			eDate = sdf.parse(endtime);
		}

		SolrDataDto sdd = hylogSolrServer.dataUser(ownerid, mtype, 0, pageid, start, rows, sDate, eDate);
		int total = sdd.getTotal();
		SolrDocumentList list = sdd.getDocumentList();

		dto = new NewDataDto();
		Pager pager = new Pager(pageId, total, rows);
		dto.setPager(pager);

		List<SolrDocument> ulist = new ArrayList<SolrDocument>();
		for (int i = 0; i < list.size(); i++) {
			SolrDocument doc = list.get(i);

			Date created = (Date) doc.get("created");
			Calendar cal = Calendar.getInstance();
			cal.setTime(created);
			cal.add(Calendar.HOUR_OF_DAY, -8);
			doc.addField("visittime", cal.getTime());

			if ("wx".equals(mtype)) {
				String mid = (String) doc.get("mid");
				String nickname = "匿名";
				String gender = "未知";
				WxUser wxuser = pageCompose.findWxUserByOpenid(mid);
				if (wxuser != null) {
					if (wxuser.getNickname() != null) {
						nickname = wxuser.getNickname();
					} else {
						nickname = nickname + "_" + wxuser.getId();
					}
					int sex = wxuser.getSex();
					if (sex == 1) {
						gender = "男";
					} else if (sex == 2) {
						gender = "女";
					}
				}
				doc.addField("nickname", nickname);
				doc.addField("sex", gender);
			}

			ulist.add(doc);
		}
		dto.setUlist(ulist);

		return SUCCESS;
	}

	private NewDataDto toHour(NewDataDto ndd, SolrDataDto sdd_pv, SolrDataDto sdd_uv) {
		if (sdd_pv == null || sdd_uv == null) {
			return null;
		}
		try {
			List<String> dates = new ArrayList<String>();
			List<Integer> pvs = new ArrayList<Integer>();
			List<Integer> uvs = new ArrayList<Integer>();

			for (int i = 0; i < 24; i++) {
				dates.add("'" + i + "'");
				pvs.add(0);
				uvs.add(0);
			}

			FacetField field = sdd_pv.getField();
			if (field != null) {
				List<FacetField.Count> ffcList = field.getValues();
				for (FacetField.Count ffc : ffcList) {
					String name = ffc.getName();
					Long count = ffc.getCount();

					String date = "'" + name + "'";
					if (dates.contains(date)) {
						int index = dates.indexOf(date);
						pvs.set(index, count.intValue());
					}
				}
			}

			field = sdd_uv.getField();
			if (field != null) {
				List<FacetField.Count> ffcList = field.getValues();
				for (FacetField.Count ffc : ffcList) {
					String name = ffc.getName();
					Long count = ffc.getCount();

					String date = "'" + name + "'";
					if (dates.contains(date)) {
						int index = dates.indexOf(date);
						uvs.set(index, count.intValue());
					}
				}
			}

			ndd.setPv(pvs);
			ndd.setUv(uvs);
			ndd.setXcategory(dates);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ndd;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private NewDataDto toDays(NewDataDto ndd, SolrDataDto sdd_pv, SolrDataDto sdd_uv, Date start, Date end) {
		if (sdd_pv == null || sdd_uv == null) {
			return null;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd");
			List<String> dates = new ArrayList<String>();
			List<Integer> pvs = new ArrayList<Integer>();
			List<Integer> uvs = new ArrayList<Integer>();

			long l = end.getTime() - start.getTime();
			int days = (int) Math.floor(l / (3600 * 24 * 1000));

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(start);
			calendar.add(Calendar.DAY_OF_YEAR, -1);
			for (int i = 0; i < days; i++) {
				calendar.add(Calendar.DAY_OF_YEAR, 1);
				String xdate = sdf2.format(calendar.getTime());

				dates.add("'" + xdate + "'");
				pvs.add(0);
				uvs.add(0);
			}

			List<RangeFacet> rflist = sdd_pv.getRflist();
			if (rflist != null) {
				for (RangeFacet rf : rflist) {
					List<RangeFacet.Count> rfcList = rf.getCounts();
					for (RangeFacet.Count rfc : rfcList) {
						String value = rfc.getValue();
						int idot = value.indexOf(".");
						if (idot > 0) {
							value = value.substring(0, idot);
						}
						int count = rfc.getCount();

						Date dt = sdf.parse(value);
						String date = "'" + sdf2.format(dt) + "'";

						if (dates.contains(date)) {
							int index = dates.indexOf(date);
							pvs.set(index, count);
						}
					}
				}
			}

			rflist = sdd_uv.getRflist();
			if (rflist != null) {
				for (RangeFacet rf : rflist) {
					List<RangeFacet.Count> rfcList = rf.getCounts();
					for (RangeFacet.Count rfc : rfcList) {
						String value = rfc.getValue();
						int count = rfc.getCount();

						Date dt = sdf.parse(value);
						String date = "'" + sdf2.format(dt) + "'";

						if (dates.contains(date)) {
							int index = dates.indexOf(date);
							uvs.set(index, count);
						}
					}
				}
			}

			ndd.setPv(pvs);
			ndd.setUv(uvs);
			ndd.setXcategory(dates);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ndd;
	}

	private NewDataDto toArea(NewDataDto ndd, SolrDataDto sdd) {
		try {
			List<List<Object>> area = new ArrayList<List<Object>>();

			FacetField field = sdd.getField();
			if (field != null) {
				List<FacetField.Count> ffcList = field.getValues();
				for (FacetField.Count ffc : ffcList) {
					String name = ffc.getName();
					Long count = ffc.getCount();

					List<Object> list = new ArrayList<Object>();
					list.add("'" + name + "'");
					list.add(count);

					area.add(list);
				}
			}

			ndd.setArea(area);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ndd;
	}

	private NewDataDto toTerminal(NewDataDto ndd, SolrDataDto sdd) {
		try {
			List<List<Object>> terminal = new ArrayList<List<Object>>();

			FacetField field = sdd.getField();
			if (field != null) {
				List<FacetField.Count> ffcList = field.getValues();
				for (FacetField.Count ffc : ffcList) {
					String name = ffc.getName();
					Long count = ffc.getCount();

					List<Object> list = new ArrayList<Object>();
					list.add("'" + name + "'");
					list.add(count);

					terminal.add(list);
				}
			}

			ndd.setTerminal(terminal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ndd;
	}

	@Override
	public int getLightType() {
		return 2;
	}

	public void setPageCompose(IPageCompose pageCompose) {
		this.pageCompose = pageCompose;
	}

	public void setHylogSolrServer(HylogSolrServer hylogSolrServer) {
		this.hylogSolrServer = hylogSolrServer;
	}

	public List<Page> getPlist() {
		return plist;
	}

	public void setPlist(List<Page> plist) {
		this.plist = plist;
	}

	public NewDataDto getDto() {
		return dto;
	}

	public void setDto(NewDataDto dto) {
		this.dto = dto;
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

	public long getPageid() {
		return pageid;
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

	public long getSiteid() {
		return siteid;
	}

	public void setSiteid(long siteid) {
		this.siteid = siteid;
	}

	public void setPageid(long pageid) {
		this.pageid = pageid;
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

}
