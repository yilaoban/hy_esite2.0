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
import com.huiyee.esite.dto.NewDataDto;
import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.dto.SolrDataDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.WxUser;
import com.huiyee.esite.solr.HylogSolrServer;

public class DataShareAction extends AbstractAction {

	private static final long serialVersionUID = -2578135178579206558L;
	private IPageCompose pageCompose;
	private HylogSolrServer hylogSolrServer;

	private String atype;
	private String pname;
	private long page;
	private int pageId = 1;// 分页
	private NewDataDto dto;

	private String dtype;// today,yesterday, week, month
	private String gtype;// 按小时 HOUR, 按天 DAY
	private String starttime;
	private String endtime;

	@Override
	public String execute() throws Exception {
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		if (StringUtils.isBlank(dtype) && StringUtils.isBlank(gtype)) {
			dtype = "today";
			gtype = "HOUR";
		}

		dto = new NewDataDto();
		if ("today".equals(dtype)) {
			SolrDataDto sdd_share = hylogSolrServer.shareToday(ownerid, "s");
			SolrDataDto sdd_click = hylogSolrServer.shareToday(ownerid, "c");
			SolrDataDto sdd_subscribe = hylogSolrServer.shareToday(ownerid, "g");
			SolrDataDto sdd_interact = hylogSolrServer.shareToday(ownerid, "h");
			SolrDataDto sdd_outer = hylogSolrServer.shareToday(ownerid, "w");
			if ("HOUR".equals(gtype)) {
				toHour(dto, sdd_share, sdd_click, sdd_subscribe, sdd_interact, sdd_outer);
			} else if ("DAY".equals(gtype)) {
				toToday(dto, sdd_share, sdd_click, sdd_subscribe, sdd_interact, sdd_outer);
			}
		} else if ("yesterday".equals(dtype)) {
			SolrDataDto sdd_share = hylogSolrServer.shareYesterday(ownerid, "s");
			SolrDataDto sdd_click = hylogSolrServer.shareYesterday(ownerid, "c");
			SolrDataDto sdd_subscribe = hylogSolrServer.shareYesterday(ownerid, "g");
			SolrDataDto sdd_interact = hylogSolrServer.shareYesterday(ownerid, "h");
			SolrDataDto sdd_outer = hylogSolrServer.shareYesterday(ownerid, "w");
			if ("HOUR".equals(gtype)) {
				toHour(dto, sdd_share, sdd_click, sdd_subscribe, sdd_interact, sdd_outer);
			} else if ("DAY".equals(gtype)) {
				toYesterday(dto, sdd_share, sdd_click, sdd_subscribe, sdd_interact, sdd_outer);
			}
		} else if ("week".equals(dtype)) {
			SolrDataDto sdd_share = hylogSolrServer.shareDays(ownerid, "s", gtype, 7);
			SolrDataDto sdd_click = hylogSolrServer.shareDays(ownerid, "c", gtype, 7);
			SolrDataDto sdd_subscribe = hylogSolrServer.shareDays(ownerid, "g", gtype, 7);
			SolrDataDto sdd_interact = hylogSolrServer.shareDays(ownerid, "h", gtype, 7);
			SolrDataDto sdd_outer = hylogSolrServer.shareDays(ownerid, "w", gtype, 7);
			if ("HOUR".equals(gtype)) {
				toHour(dto, sdd_share, sdd_click, sdd_subscribe, sdd_interact, sdd_outer);
			} else if ("DAY".equals(gtype)) {
				Calendar cal = Calendar.getInstance();
				Date end = cal.getTime();
				cal.add(Calendar.DAY_OF_YEAR, -7);
				Date start = cal.getTime();
				toDays(dto, sdd_share, sdd_click, sdd_subscribe, sdd_interact, sdd_outer, start, end);
			}
		} else if ("month".equals(dtype)) {
			SolrDataDto sdd_share = hylogSolrServer.shareDays(ownerid, "s", gtype, 30);
			SolrDataDto sdd_click = hylogSolrServer.shareDays(ownerid, "c", gtype, 30);
			SolrDataDto sdd_subscribe = hylogSolrServer.shareDays(ownerid, "g", gtype, 30);
			SolrDataDto sdd_interact = hylogSolrServer.shareDays(ownerid, "h", gtype, 30);
			SolrDataDto sdd_outer = hylogSolrServer.shareDays(ownerid, "w", gtype, 30);
			if ("HOUR".equals(gtype)) {
				toHour(dto, sdd_share, sdd_click, sdd_subscribe, sdd_interact, sdd_outer);
			} else if ("DAY".equals(gtype)) {
				Calendar cal = Calendar.getInstance();
				Date end = cal.getTime();
				cal.add(Calendar.DAY_OF_YEAR, -30);
				Date start = cal.getTime();
				toDays(dto, sdd_share, sdd_click, sdd_subscribe, sdd_interact, sdd_outer, start, end);
			}
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (StringUtils.isNotBlank(starttime) && StringUtils.isNotBlank(endtime)) {
				Date start = sdf.parse(starttime);
				Date end = sdf.parse(endtime);

				Calendar cal = Calendar.getInstance();
				cal.setTime(end);
				cal.add(Calendar.DAY_OF_YEAR, 1);

				SolrDataDto sdd_share = hylogSolrServer.shareDays(ownerid, "s", 0, 0, gtype, start, cal.getTime());
				SolrDataDto sdd_click = hylogSolrServer.shareDays(ownerid, "c", 0, 0, gtype, start, cal.getTime());
				SolrDataDto sdd_subscribe = hylogSolrServer.shareDays(ownerid, "g", 0, 0, gtype, start, cal.getTime());
				SolrDataDto sdd_interact = hylogSolrServer.shareDays(ownerid, "h", 0, 0, gtype, start, cal.getTime());
				SolrDataDto sdd_outer = hylogSolrServer.shareDays(ownerid, "w", 0, 0, gtype, start, cal.getTime());
				if ("HOUR".equals(gtype)) {
					toHour(dto, sdd_share, sdd_click, sdd_subscribe, sdd_interact, sdd_outer);
				} else if ("DAY".equals(gtype)) {
					cal.setTime(start);
					cal.add(Calendar.DAY_OF_YEAR, -1);
					toDays(dto, sdd_share, sdd_click, sdd_subscribe, sdd_interact, sdd_outer, cal.getTime(), end);
				}
			}
		}

		return SUCCESS;
	}

	public String user() throws Exception {
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		if (StringUtils.isBlank(atype)) {
			atype = "f";
		}

		int rows = 10;
		int start = (pageId - 1) * rows;
		Date sDate = null;
		Date eDate = null;
		if (StringUtils.isNotBlank(starttime) && StringUtils.isNotBlank(endtime)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			sDate = sdf.parse(starttime);
			eDate = sdf.parse(endtime);
		}

		List<SolrDocument> ulist = new ArrayList<SolrDocument>();
		SolrDataDto sdd = hylogSolrServer.shareUser(ownerid, "wx", atype, sDate, eDate, start, rows);
		FacetField field = sdd.getField();
		if (field != null) {
			List<FacetField.Count> ffcList = field.getValues();
			for (FacetField.Count ffc : ffcList) {
				String ssh = ffc.getName();
				long count = ffc.getCount();

				SolrDocument doc = new SolrDocument();
				doc.addField("openid", ssh);
				doc.addField("count", count);

				if (!"f".equals(atype)) {
					SolrDataDto sdd_gc = hylogSolrServer.shareUserGroup(ownerid, "wx", atype, ssh);
					GroupCommand gc = sdd_gc.getGclist().get(0);
					int matches = gc.getMatches();
					int ngroups = gc.getNGroups();
					doc.addField("matches", matches);
					doc.addField("ngroups", ngroups);
				}

				String nickname = "匿名";
				String subscribe = "未知";
				String sex = "未知";
				String area = "";
				WxUser wxuser = pageCompose.findWxUserByOpenid(ssh);
				if (wxuser != null) {
					if (wxuser.getNickname() != null) {
						nickname = wxuser.getNickname();
					} else {
						nickname = nickname + "_" + wxuser.getId();
					}
					if (wxuser.getSex() == 1) {
						sex = "男";
					} else if (wxuser.getSex() == 2) {
						sex = "女";
					}
					if (StringUtils.isNotBlank(wxuser.getProvince())) {
						area = area + wxuser.getProvince();
					}
					if (StringUtils.isNotBlank(wxuser.getCity())) {
						area = area + wxuser.getCity();
					}
					if (StringUtils.isBlank(area)) {
						area = "未知";
					}
					if (wxuser.getSubscribe() > 0) {
						subscribe = "是";
					} else {
						subscribe = "否";
					}
				}
				doc.addField("nickname", nickname);
				doc.addField("subscribe", subscribe);
				doc.addField("sex", sex);
				doc.addField("area", area);
				ulist.add(doc);
			}
		}
		dto = new NewDataDto();
		dto.setUlist(ulist);
		return SUCCESS;
	}

	public String userDetail() throws Exception {
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		int rows = 10;
		int start = (pageId - 1) * rows;
		SolrDataDto sdd = hylogSolrServer.shareUserDetail(ownerid, "wx", atype, pname, start, rows);
		SolrDocumentList ulist = sdd.getDocumentList();
		Calendar cal = Calendar.getInstance();
		for (SolrDocument doc : ulist) {
			Date created = (Date) doc.getFieldValue("created");
			cal.setTime(created);
			cal.add(Calendar.HOUR_OF_DAY, -8);
			doc.setField("created", cal.getTime());

			String pagename = "未知页面";
			String sitename = "未知站点";
			long pageid = (Integer) doc.getFieldValue("page");
			Page p = pageCompose.findPageByPageid(pageid);
			if (p != null) {
				pagename = p.getName();
				sitename = p.getJspname();
			}
			doc.addField("pageid", pageid);
			doc.addField("pagename", pagename);
			doc.addField("sitename", sitename);

			String mid = (String) doc.getFieldValue("mid");
			String nickname = "匿名";
			String subscribe = "未知";
			WxUser wxuser = pageCompose.findWxUserByOpenid(mid);
			if (wxuser != null) {
				if (wxuser.getNickname() != null) {
					nickname = wxuser.getNickname();
				} else {
					nickname = nickname + "_" + wxuser.getId();
				}
				if (wxuser.getSubscribe() > 0) {
					subscribe = "是";
				} else {
					subscribe = "否";
				}
			}
			doc.addField("openid", mid);
			doc.addField("user", nickname);
			doc.addField("subscribe", subscribe);
			doc.setField("atype", atype);
		}

		dto = new NewDataDto();
		dto.setUlist(ulist);
		dto.setPager(new Pager(pageId, sdd.getTotal(), rows));
		return SUCCESS;
	}

	public String page() throws Exception {
		List<SolrDocument> plist = new ArrayList<SolrDocument>();
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();

		if (page > 0) {
			SolrDocument doc = facetByAtype(ownerid, page);
			plist.add(doc);
		} else {
			int rows = 10;
			int start = (pageId - 1) * rows;

			SolrDataDto sdd = hylogSolrServer.sharePage(ownerid, "wx", start, rows);
			FacetField field = sdd.getField();
			if (field != null) {
				List<FacetField.Count> ffcList = field.getValues();
				for (FacetField.Count ffc : ffcList) {
					long aoid = Long.valueOf(ffc.getName());
					// long count = ffc.getCount();
					SolrDocument doc = facetByAtype(ownerid, aoid);
					plist.add(doc);
				}
			}
		}

		dto = new NewDataDto();
		dto.setPlist(plist);
		return SUCCESS;
	}

	public String pageDetail() throws Exception {
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		int rows = 10;
		int start = (pageId - 1) * rows;
		SolrDataDto sdd = hylogSolrServer.sharePageDetail(ownerid, "wx", atype, page, start, rows);
		SolrDocumentList plist = sdd.getDocumentList();
		Calendar cal = Calendar.getInstance();
		for (SolrDocument doc : plist) {
			Date created = (Date) doc.getFieldValue("created");
			cal.setTime(created);
			cal.add(Calendar.HOUR_OF_DAY, -8);
			doc.setField("created", cal.getTime());

			String openid = (String) doc.getFieldValue("mid");
			String nickname = "匿名";
			WxUser wxuser = pageCompose.findWxUserByOpenid(openid);
			if (wxuser != null) {
				if (wxuser.getNickname() != null) {
					nickname = wxuser.getNickname();
				} else {
					nickname = nickname + "_" + wxuser.getId();
				}
			}
			doc.addField("user", nickname);

			openid = (String) doc.getFieldValue("ssh");
			nickname = "匿名";
			wxuser = pageCompose.findWxUserByOpenid(openid);
			if (wxuser != null) {
				if (wxuser.getNickname() != null) {
					nickname = wxuser.getNickname();
				} else {
					nickname = nickname + "_" + wxuser.getId();
				}
			}
			doc.addField("shareuser", nickname);
		}

		dto = new NewDataDto();
		dto.setPlist(plist);
		dto.setPager(new Pager(pageId, sdd.getTotal(), rows));
		return SUCCESS;
	}

	public String pageSearch() throws Exception {
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		int rows = 10;
		int start = (pageId - 1) * rows;
		List<Page> pages = pageCompose.findPagesByName(ownerid, pname, start, rows);
		int total = 0;
		List<SolrDocument> plist = new ArrayList<SolrDocument>();
		for (Page page : pages) {
			total = (int) page.getEntityid();
			SolrDocument doc = new SolrDocument();
			doc.addField("pageid", page.getId());
			doc.addField("pagename", page.getName());
			doc.addField("siteid", page.getSiteid());
			doc.addField("sitename", page.getJspname());
			doc.addField("onlinetime", page.getUpdatetime());
			plist.add(doc);
		}

		dto = new NewDataDto();
		dto.setPager(new Pager(pageId, total, rows));
		dto.setPlist(plist);
		return SUCCESS;
	}

	private NewDataDto toHour(NewDataDto ndd, SolrDataDto sdd_share, SolrDataDto sdd_click, SolrDataDto sdd_subscribe, SolrDataDto sdd_interact, SolrDataDto sdd_outer) {
		try {
			List<String> dates = new ArrayList<String>();
			List<Integer> share = new ArrayList<Integer>();
			List<Integer> click = new ArrayList<Integer>();
			List<Integer> subscribe = new ArrayList<Integer>();
			List<Integer> interact = new ArrayList<Integer>();
			List<Integer> outer = new ArrayList<Integer>();

			for (int i = 0; i < 24; i++) {
				dates.add("'" + i + "'");
				share.add(0);
				click.add(0);
				subscribe.add(0);
				interact.add(0);
				outer.add(0);
			}

			if (sdd_share != null) {
				FacetField field = sdd_share.getField();
				if (field != null) {
					List<FacetField.Count> ffcList = field.getValues();
					for (FacetField.Count ffc : ffcList) {
						String name = ffc.getName();
						Long count = ffc.getCount();

						String date = "'" + name + "'";
						if (dates.contains(date)) {
							int index = dates.indexOf(date);
							share.set(index, count.intValue());
						}
					}
				}
			}
			if (sdd_click != null) {
				FacetField field = sdd_click.getField();
				if (field != null) {
					List<FacetField.Count> ffcList = field.getValues();
					for (FacetField.Count ffc : ffcList) {
						String name = ffc.getName();
						Long count = ffc.getCount();

						String date = "'" + name + "'";
						if (dates.contains(date)) {
							int index = dates.indexOf(date);
							click.set(index, count.intValue());
						}
					}
				}
			}
			if (sdd_subscribe != null) {
				FacetField field = sdd_subscribe.getField();
				if (field != null) {
					List<FacetField.Count> ffcList = field.getValues();
					for (FacetField.Count ffc : ffcList) {
						String name = ffc.getName();
						Long count = ffc.getCount();

						String date = "'" + name + "'";
						if (dates.contains(date)) {
							int index = dates.indexOf(date);
							subscribe.set(index, count.intValue());
						}
					}
				}
			}
			if (sdd_interact != null) {
				FacetField field = sdd_interact.getField();
				if (field != null) {
					List<FacetField.Count> ffcList = field.getValues();
					for (FacetField.Count ffc : ffcList) {
						String name = ffc.getName();
						Long count = ffc.getCount();

						String date = "'" + name + "'";
						if (dates.contains(date)) {
							int index = dates.indexOf(date);
							interact.set(index, count.intValue());
						}
					}
				}
			}
			if (sdd_outer != null) {
				FacetField field = sdd_outer.getField();
				if (field != null) {
					List<FacetField.Count> ffcList = field.getValues();
					for (FacetField.Count ffc : ffcList) {
						String name = ffc.getName();
						Long count = ffc.getCount();

						String date = "'" + name + "'";
						if (dates.contains(date)) {
							int index = dates.indexOf(date);
							outer.set(index, count.intValue());
						}
					}
				}
			}

			ndd.setXcategory(dates);
			ndd.setShare(share);
			ndd.setClick(click);
			ndd.setSubscribe(subscribe);
			ndd.setInteract(interact);
			ndd.setOuter(outer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ndd;
	}

	private NewDataDto toToday(NewDataDto ndd, SolrDataDto sdd_share, SolrDataDto sdd_click, SolrDataDto sdd_subscribe, SolrDataDto sdd_interact, SolrDataDto sdd_outer) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
			List<String> dates = new ArrayList<String>();
			List<Integer> share = new ArrayList<Integer>();
			List<Integer> click = new ArrayList<Integer>();
			List<Integer> subscribe = new ArrayList<Integer>();
			List<Integer> interact = new ArrayList<Integer>();
			List<Integer> outer = new ArrayList<Integer>();

			Calendar calendar = Calendar.getInstance();
			String xdate = sdf.format(calendar.getTime());
			dates.add("'" + xdate + "'");
			share.add(sdd_share.getTotal());
			click.add(sdd_click.getTotal());
			subscribe.add(sdd_subscribe.getTotal());
			interact.add(sdd_interact.getTotal());
			outer.add(sdd_outer.getTotal());

			ndd.setXcategory(dates);
			ndd.setShare(share);
			ndd.setClick(click);
			ndd.setSubscribe(subscribe);
			ndd.setInteract(interact);
			ndd.setOuter(outer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ndd;
	}

	private NewDataDto toYesterday(NewDataDto ndd, SolrDataDto sdd_share, SolrDataDto sdd_click, SolrDataDto sdd_subscribe, SolrDataDto sdd_interact, SolrDataDto sdd_outer) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
			List<String> dates = new ArrayList<String>();
			List<Integer> share = new ArrayList<Integer>();
			List<Integer> click = new ArrayList<Integer>();
			List<Integer> subscribe = new ArrayList<Integer>();
			List<Integer> interact = new ArrayList<Integer>();
			List<Integer> outer = new ArrayList<Integer>();

			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_YEAR, -1);
			String xdate = sdf.format(calendar.getTime());
			dates.add("'" + xdate + "'");
			share.add(sdd_share.getTotal());
			click.add(sdd_click.getTotal());
			subscribe.add(sdd_subscribe.getTotal());
			interact.add(sdd_interact.getTotal());
			outer.add(sdd_outer.getTotal());

			ndd.setXcategory(dates);
			ndd.setShare(share);
			ndd.setClick(click);
			ndd.setSubscribe(subscribe);
			ndd.setInteract(interact);
			ndd.setOuter(outer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ndd;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private NewDataDto toDays(NewDataDto ndd, SolrDataDto sdd_share, SolrDataDto sdd_click, SolrDataDto sdd_subscribe, SolrDataDto sdd_interact, SolrDataDto sdd_outer, Date start, Date end) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd");
			List<String> dates = new ArrayList<String>();
			List<Integer> share = new ArrayList<Integer>();
			List<Integer> click = new ArrayList<Integer>();
			List<Integer> subscribe = new ArrayList<Integer>();
			List<Integer> interact = new ArrayList<Integer>();
			List<Integer> outer = new ArrayList<Integer>();

			long l = end.getTime() - start.getTime();
			int days = (int) Math.floor(l / (3600 * 24 * 1000));

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(start);
			for (int i = 0; i < days; i++) {
				calendar.add(Calendar.DAY_OF_YEAR, 1);
				String xdate = sdf2.format(calendar.getTime());

				dates.add("'" + xdate + "'");
				share.add(0);
				click.add(0);
				subscribe.add(0);
				interact.add(0);
				outer.add(0);
			}

			if (sdd_share != null) {
				List<RangeFacet> rflist = sdd_share.getRflist();
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
								share.set(index, count);
							}
						}
					}
				}
			}
			if (sdd_click != null) {
				List<RangeFacet> rflist = sdd_click.getRflist();
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
								click.set(index, count);
							}
						}
					}
				}
			}
			if (sdd_subscribe != null) {
				List<RangeFacet> rflist = sdd_subscribe.getRflist();
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
								subscribe.set(index, count);
							}
						}
					}
				}
			}
			if (sdd_interact != null) {
				List<RangeFacet> rflist = sdd_interact.getRflist();
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
								interact.set(index, count);
							}
						}
					}
				}
			}
			if (sdd_outer != null) {
				List<RangeFacet> rflist = sdd_outer.getRflist();
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
								outer.set(index, count);
							}
						}
					}
				}
			}

			ndd.setXcategory(dates);
			ndd.setShare(share);
			ndd.setClick(click);
			ndd.setSubscribe(subscribe);
			ndd.setInteract(interact);
			ndd.setOuter(outer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ndd;
	}

	private SolrDocument facetByAtype(long ownerid, long spage) {
		SolrDocument doc = new SolrDocument();
		String pagename = "未知页面";
		String sitename = "未知站点";
		long share = 0;
		long click = 0;
		long subscribe = 0;
		long interact = 0;
		long outer = 0;
		long share_g = 0;
		long click_g = 0;
		long subscribe_g = 0;
		long interact_g = 0;
		long outer_g = 0;

		try {
			Page p = pageCompose.findPageByPageid(spage);
			if (p != null) {
				pagename = p.getName();
				sitename = p.getJspname();
			}

			SolrDataDto sdd = hylogSolrServer.facetByAtype(ownerid, "wx", spage);
			FacetField field = sdd.getField();
			if (field != null) {
				List<FacetField.Count> ffcList = field.getValues();
				for (FacetField.Count ffc : ffcList) {
					long count = ffc.getCount();
					String atype = ffc.getName();
					if ("s".equals(atype)) {
						share = count;
					} else if ("c".equals(atype)) {
						click = count;
					} else if ("g".equals(atype)) {
						subscribe = count;
					} else if ("h".equals(atype)) {
						interact = count;
					} else if ("w".equals(atype)) {
						outer = count;
					}
				}
			}

			sdd = hylogSolrServer.facetByAtype(ownerid, "wx", spage, "mid");
			field = sdd.getField();
			if (field != null) {
				List<FacetField.Count> ffcList = field.getValues();
				for (FacetField.Count ffc : ffcList) {
					long count = ffc.getCount();
					String atype = ffc.getName();
					if ("s".equals(atype)) {
						share_g = count;
					} else if ("c".equals(atype)) {
						click_g = count;
					} else if ("g".equals(atype)) {
						subscribe_g = count;
					} else if ("h".equals(atype)) {
						interact_g = count;
					} else if ("w".equals(atype)) {
						outer_g = count;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		doc.addField("pageid", spage);
		doc.addField("pagename", pagename);
		doc.addField("sitename", sitename);
		doc.addField("share", share);
		doc.addField("click", click);
		doc.addField("subscribe", subscribe);
		doc.addField("interact", interact);
		doc.addField("outer", outer);
		doc.addField("share_g", share_g);
		doc.addField("click_g", click_g);
		doc.addField("subscribe_g", subscribe_g);
		doc.addField("interact_g", interact_g);
		doc.addField("outer_g", outer_g);
		return doc;
	}

	@Override
	public int getLightType() {
		return 5;
	}

	public void setPageCompose(IPageCompose pageCompose) {
		this.pageCompose = pageCompose;
	}

	public void setHylogSolrServer(HylogSolrServer hylogSolrServer) {
		this.hylogSolrServer = hylogSolrServer;
	}

	public String getAtype() {
		return atype;
	}

	public void setAtype(String atype) {
		this.atype = atype;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public long getPage() {
		return page;
	}

	public void setPage(long page) {
		this.page = page;
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

	public String getDtype() {
		return dtype;
	}

	public void setDtype(String dtype) {
		this.dtype = dtype;
	}

	public String getGtype() {
		return gtype;
	}

	public void setGtype(String gtype) {
		this.gtype = gtype;
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

}
