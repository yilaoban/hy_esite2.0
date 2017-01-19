package com.huiyee.esite.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.RangeFacet;
import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.dto.NewDataDto;
import com.huiyee.esite.dto.SolrDataDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.solr.HylogSolrServer;

public class DataIndexAction extends AbstractAction {

	private static final long serialVersionUID = -2578135178579206558L;

	private HylogSolrServer hylogSolrServer;

	private int pageId = 1;// 分页
	private String starttime;
	private String endtime;
	private String mid;

	private NewDataDto dto;
	private String mtype;
	private String dtype;// today,yesterday, week, month
	private String gtype;// 按小时 HOUR, 按天 DAY

	@Override
	public String execute() throws Exception {
		if (StringUtils.isBlank(mtype)) {
			mtype = "wx";
		}
		return SUCCESS;
	}

	public String data() throws Exception {
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();

		dto = new NewDataDto();
		// 访问数据
		if ("today".equals(dtype)) {
			SolrDataDto sdd_pv = hylogSolrServer.pvToday(ownerid, mtype);
			SolrDataDto sdd_uv = hylogSolrServer.uvToday(ownerid, mtype);
			if ("HOUR".equals(gtype)) {
				toHour(dto, sdd_pv, sdd_uv);
			} else if ("DAY".equals(gtype)) {
				toToday(dto, sdd_pv, sdd_uv);
			}
		} else if ("yesterday".equals(dtype)) {
			SolrDataDto sdd_pv = hylogSolrServer.pvYesterday(ownerid, mtype);
			SolrDataDto sdd_uv = hylogSolrServer.uvYesterday(ownerid, mtype);
			if ("HOUR".equals(gtype)) {
				toHour(dto, sdd_pv, sdd_uv);
			} else if ("DAY".equals(gtype)) {
				toYesterday(dto, sdd_pv, sdd_uv);
			}
		} else if ("week".equals(dtype)) {
			SolrDataDto sdd_pv = hylogSolrServer.pvDays(ownerid, mtype, gtype, 7);
			SolrDataDto sdd_uv = hylogSolrServer.uvDays(ownerid, mtype, gtype, 7);
			if ("HOUR".equals(gtype)) {
				toHour(dto, sdd_pv, sdd_uv);
			} else if ("DAY".equals(gtype)) {
				Calendar cal = Calendar.getInstance();
				// cal.add(Calendar.DAY_OF_YEAR, -1);
				Date end = cal.getTime();
				cal.add(Calendar.DAY_OF_YEAR, -7);
				Date start = cal.getTime();
				toDays(dto, sdd_pv, sdd_uv, start, end);
			}
		} else if ("month".equals(dtype)) {
			SolrDataDto sdd_pv = hylogSolrServer.pvDays(ownerid, mtype, gtype, 30);
			SolrDataDto sdd_uv = hylogSolrServer.uvDays(ownerid, mtype, gtype, 30);
			if ("HOUR".equals(gtype)) {
				toHour(dto, sdd_pv, sdd_uv);
			} else if ("DAY".equals(gtype)) {
				Calendar cal = Calendar.getInstance();
				// cal.add(Calendar.DAY_OF_YEAR, -1);
				Date end = cal.getTime();
				cal.add(Calendar.DAY_OF_YEAR, -30);
				Date start = cal.getTime();
				toDays(dto, sdd_pv, sdd_uv, start, end);
			}
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (StringUtils.isNotBlank(starttime) && StringUtils.isNotBlank(endtime)) {
				Date start = sdf.parse(starttime);
				Date end = sdf.parse(endtime);

				Calendar cal = Calendar.getInstance();
				cal.setTime(end);
				cal.add(Calendar.DAY_OF_YEAR, 1);

				SolrDataDto sdd_pv = hylogSolrServer.pvDays(ownerid, mtype, 0, 0, gtype, start, cal.getTime());
				SolrDataDto sdd_uv = hylogSolrServer.uvDays(ownerid, mtype, 0, 0, gtype, start, cal.getTime());
				if ("HOUR".equals(gtype)) {
					toHour(dto, sdd_pv, sdd_uv);
				} else if ("DAY".equals(gtype)) {
					cal.setTime(start);
					cal.add(Calendar.DAY_OF_YEAR, -1);
					toDays(dto, sdd_pv, sdd_uv, cal.getTime(), end);
				}
			}
		}

		// 区域分布
		SolrDataDto sdd_area = hylogSolrServer.dataArea(ownerid, mtype, 0, 0);
		toArea(dto, sdd_area);
		// 终端分布
		SolrDataDto sdd_terminal = hylogSolrServer.dataTerminal(ownerid, mtype, 0, 0);
		toTerminal(dto, sdd_terminal);

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

	private NewDataDto toToday(NewDataDto ndd, SolrDataDto sdd_pv, SolrDataDto sdd_uv) {
		if (sdd_pv == null || sdd_uv == null) {
			return null;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
			List<String> dates = new ArrayList<String>();
			List<Integer> pvs = new ArrayList<Integer>();
			List<Integer> uvs = new ArrayList<Integer>();

			Calendar calendar = Calendar.getInstance();
			String xdate = sdf.format(calendar.getTime());
			dates.add("'" + xdate + "'");
			pvs.add(sdd_pv.getTotal());

			FacetField field = sdd_uv.getField();
			if (field != null) {
				int total = 0;
				List<FacetField.Count> ffcList = field.getValues();
				for (FacetField.Count ffc : ffcList) {
					total += ffc.getCount();
				}
				uvs.add(total);
			}

			ndd.setPv(pvs);
			ndd.setUv(uvs);
			ndd.setXcategory(dates);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ndd;
	}

	private NewDataDto toYesterday(NewDataDto ndd, SolrDataDto sdd_pv, SolrDataDto sdd_uv) {
		if (sdd_pv == null || sdd_uv == null) {
			return null;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
			List<String> dates = new ArrayList<String>();
			List<Integer> pvs = new ArrayList<Integer>();
			List<Integer> uvs = new ArrayList<Integer>();

			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_YEAR, -1);
			String xdate = sdf.format(calendar.getTime());
			dates.add("'" + xdate + "'");
			pvs.add(sdd_pv.getTotal());

			FacetField field = sdd_uv.getField();
			if (field != null) {
				int total = 0;
				List<FacetField.Count> ffcList = field.getValues();
				for (FacetField.Count ffc : ffcList) {
					total += ffc.getCount();
				}
				uvs.add(total);
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
		return 1;
	}

	public void setHylogSolrServer(HylogSolrServer hylogSolrServer) {
		this.hylogSolrServer = hylogSolrServer;
	}

	public NewDataDto getDto() {
		return dto;
	}

	public void setDto(NewDataDto dto) {
		this.dto = dto;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public String getMtype() {
		return mtype;
	}

	public void setMtype(String mtype) {
		this.mtype = mtype;
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

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

}
