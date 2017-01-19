package com.huiyee.esite.solr;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.Group;
import org.apache.solr.client.solrj.response.GroupCommand;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.GroupParams;

import com.huiyee.esite.dto.SolrDataDto;

public class HylogSolrServer {

	private CloudSolrServer cloudSolrServer;

	private String zkHost;
	private String defaultCollection;
	private int zkClientTimeout = 20000;
	private int zkConnectTimeout = 1000;

	public CloudSolrServer getCloudSolrServer(final String zkHost) {
		if (cloudSolrServer == null) {
			init();
		}

		return cloudSolrServer;
	}

	public void init() {
		try {
			cloudSolrServer = new CloudSolrServer(zkHost);
			cloudSolrServer.setDefaultCollection(defaultCollection);
		} catch (Exception e) {
			System.out.println("something wrong when creating solr server with " + zkHost);
			e.printStackTrace();
		}
	}

	/**
	 * 根据条件获取区域的facet，人数uni gz_i :-1 时代表全部 adesc: t:投票 d:调研 f:表单 l:砸金蛋 y:摇一摇
	 * z:转盘
	 */
	public List<List<Object>> findHudongUserArea(String mtype, String atype, String adesc, long aoid, int gz_i) {
		List<List<Object>> area = new ArrayList<List<Object>>();
		try {
			SolrQuery query = new SolrQuery();
			query.setQuery("*");
			query.addFilterQuery("mtype:" + mtype);
			query.addFilterQuery("atype:" + atype);
			query.addFilterQuery("adesc:" + adesc);
			query.addFilterQuery("aoid:" + aoid);
			if (gz_i != -1) {
				query.addFilterQuery("gz_i:" + gz_i);
			}
			query.setRows(0);
			query.addFacetField("area");
			query.setFacetMinCount(1);
			query.setFacetMissing(false);

			QueryResponse response = cloudSolrServer.query(query, SolrRequest.METHOD.POST);
			FacetField ff = response.getFacetField("area");
			List<FacetField.Count> ffcList = ff.getValues();
			for (FacetField.Count ffc : ffcList) {
				String name = ffc.getName();
				long count = ffc.getCount();
				if (count > 1) {
					query = new SolrQuery();
					query.setQuery("*");
					query.addFilterQuery("mtype:" + mtype);
					query.addFilterQuery("atype:" + atype);
					query.addFilterQuery("adesc:" + adesc);
					query.addFilterQuery("aoid:" + aoid);
					query.addFilterQuery("area:" + name);
					if (gz_i != -1) {
						query.addFilterQuery("gz_i:" + gz_i);
					}
					query.setRows(0);
					query.setParam(GroupParams.GROUP, true);
					query.setParam(GroupParams.GROUP_FIELD, "wx_l");
					query.add("group.ngroups", "true");

					response = cloudSolrServer.query(query, SolrRequest.METHOD.POST);
					count = response.getGroupResponse().getValues().get(0).getNGroups();
				}
				List<Object> list = new ArrayList<Object>();
				list.add("'" + name + "'");
				list.add(count);
				area.add(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return area;
	}

	/**
	 * 根据条件获取list wxuid gz_i :-1 代表全部 area :null 代表全部 adesc: t:投票 d:调研 f:表单
	 * l:砸金蛋 y:摇一摇 z:转盘
	 */
	public List<Long> findHudongUser(String mtype, String atype, String adesc, long aoid, int gz_i, String area) {
		return null;
	}

	public SolrDataDto top10Page(long owner, int start, int rows) throws Exception {
		SolrDataDto dto = new SolrDataDto();
		try {
			SolrQuery query = new SolrQuery();
			query.setQuery("*");
			query.addFilterQuery("owner:" + owner);
			query.addFilterQuery("atype:c");
			query.setRows(0);
			query.addFacetField("page");
			query.add("facet.offset", start + "");
			query.setFacetLimit(rows);
			query.setFacetMinCount(1);
			query.setFacetMissing(false);

			QueryResponse response = cloudSolrServer.query(query, SolrRequest.METHOD.POST);
			dto.setTotal((int) response.getResults().getNumFound());
			dto.setField(response.getFacetField("page"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	public SolrDataDto pvUv(long owner, long site, long page) {
		SolrDataDto dto = new SolrDataDto();
		try {
			SolrQuery query = new SolrQuery();
			query.setQuery("*");
			query.addFilterQuery("owner:" + owner);
			query.addFilterQuery("atype:c");
			if (site > 0) {
				query.addFilterQuery("site:" + site);
			}
			if (page > 0) {
				query.addFilterQuery("page:" + page);
			}

			query.setRows(0);

			query.setParam(GroupParams.GROUP, true);
			query.setParam(GroupParams.GROUP_FIELD, "cookie");
			query.add("group.ngroups", "true");

			QueryResponse response = cloudSolrServer.query(query, SolrRequest.METHOD.POST);
			dto.setGclist(response.getGroupResponse().getValues());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	public SolrDataDto pvToday(long owner, String mtype) throws Exception {
		return facetByHour(owner, mtype, "c", false, 0);
	}

	public SolrDataDto pvYesterday(long owner, String mtype) throws Exception {
		return facetByHour(owner, mtype, "c", false, 1);
	}

	public SolrDataDto pvDays(long owner, String mtype, String gtype, int days) throws Exception {
		if ("HOUR".equals(gtype)) {
			return facetByHour(owner, mtype, "c", false, days);
		} else {
			return facetByDay(owner, mtype, "c", false, days);
		}
	}

	public SolrDataDto pvDays(long owner, String mtype, long site, long page, String gtype, Date start, Date end) throws Exception {
		if ("HOUR".equals(gtype)) {
			return facetByHour(owner, mtype, "c", false, site, page, start, end);
		} else {
			return facetByDay(owner, mtype, "c", false, site, page, start, end);
		}
	}

	public SolrDataDto uvToday(long owner, String mtype) throws Exception {
		return groupByHour(owner, mtype, "c", 0);
	}

	public SolrDataDto uvYesterday(long owner, String mtype) throws Exception {
		return groupByHour(owner, mtype, "c", 1);
	}

	public SolrDataDto uvDays(long owner, String mtype, String gtype, int days) throws Exception {
		if ("HOUR".equals(gtype)) {
			return groupByHour(owner, mtype, "c", days);
		} else {
			return groupByDay(owner, mtype, "c", days);
		}
	}

	public SolrDataDto uvDays(long owner, String mtype, long site, long page, String gtype, Date start, Date end) throws Exception {
		if ("HOUR".equals(gtype)) {
			return groupByHour(owner, mtype, "c", site, page, start, end);
		} else {
			return groupByDay(owner, mtype, "c", site, page, start, end);
		}
	}

	public SolrDataDto shareToday(long owner, String atype) throws Exception {
		return facetByHour(owner, "wx", atype, true, 0);
	}

	public SolrDataDto shareYesterday(long owner, String atype) throws Exception {
		return facetByHour(owner, "wx", atype, true, 1);
	}

	public SolrDataDto shareDays(long owner, String atype, String gtype, int days) throws Exception {
		if ("HOUR".equals(gtype)) {
			return facetByHour(owner, "wx", atype, true, days);
		} else {
			return facetByDay(owner, "wx", atype, true, days);
		}
	}

	public SolrDataDto shareDays(long owner, String atype, long site, long page, String gtype, Date start, Date end) throws Exception {
		if ("HOUR".equals(gtype)) {
			return facetByHour(owner, "wx", atype, true, site, page, start, end);
		} else {
			return facetByDay(owner, "wx", atype, true, site, page, start, end);
		}
	}

	private SolrDataDto facetByDay(long owner, String mtype, String atype, boolean isShare, int days) throws Exception {
		SolrDataDto dto = new SolrDataDto();
		try {
			SolrQuery query = new SolrQuery();
			query.setQuery("*");
			query.addFilterQuery("owner:" + owner);
			query.addFilterQuery("mtype:" + mtype);
			query.addFilterQuery("atype:" + atype);
			if (isShare && "c".equals(atype)) {
				query.addFilterQuery("ssh:*");
			}
			query.setRows(0);
			query.setFacetMinCount(1);
			query.setFacetMissing(false);

			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.HOUR_OF_DAY, 8);
			Date end = cal.getTime();

			removeTime(cal);
			cal.add(Calendar.DAY_OF_YEAR, -days);
			cal.add(Calendar.HOUR_OF_DAY, 8);
			Date start = cal.getTime();
			query.addDateRangeFacet("created", start, end, "+1DAY");

			QueryResponse response = cloudSolrServer.query(query, SolrRequest.METHOD.POST);
			dto.setTotal((int) response.getResults().getNumFound());
			dto.setDocumentList(response.getResults());
			dto.setRflist(response.getFacetRanges());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	private SolrDataDto facetByDay(long owner, String mtype, String atype, boolean isShare, long site, long page, Date start, Date end) throws Exception {
		SolrDataDto dto = new SolrDataDto();
		try {
			SolrQuery query = new SolrQuery();
			query.setQuery("*");
			query.addFilterQuery("owner:" + owner);
			query.addFilterQuery("mtype:" + mtype);
			query.addFilterQuery("atype:" + atype);
			if (isShare && "c".equals(atype)) {
				query.addFilterQuery("ssh:*");
			}
			if (site > 0) {
				query.addFilterQuery("site:" + site);
			}
			if (page > 0) {
				query.addFilterQuery("page:" + page);
			}
			query.setRows(0);
			query.setFacetMinCount(1);
			query.setFacetMissing(false);
			query.addDateRangeFacet("created", add8Hour(start), add8Hour(end), "+1DAY");

			QueryResponse response = cloudSolrServer.query(query, SolrRequest.METHOD.POST);
			dto.setTotal((int) response.getResults().getNumFound());
			dto.setDocumentList(response.getResults());
			dto.setRflist(response.getFacetRanges());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	private SolrDataDto facetByHour(long owner, String mtype, String atype, boolean isShare, int days) throws Exception {
		SolrDataDto dto = new SolrDataDto();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.000'Z'");
			Calendar cal = Calendar.getInstance();
			removeTime(cal);
			if (days != 1) {
				cal.add(Calendar.DAY_OF_YEAR, 1);
			}
			String end = sdf.format(cal.getTime());

			if (days > 0) {
				cal.add(Calendar.DAY_OF_YEAR, -days);
			} else {
				cal.add(Calendar.DAY_OF_YEAR, -1);
			}
			String start = sdf.format(cal.getTime());

			SolrQuery query = new SolrQuery();
			query.setQuery("*");
			query.addFilterQuery("owner:" + owner);
			query.addFilterQuery("mtype:" + mtype);
			query.addFilterQuery("atype:" + atype);
			if (isShare && "c".equals(atype)) {
				query.addFilterQuery("ssh:*");
			}
			query.addFilterQuery("created:[" + start + " TO " + end + "]");
			query.setRows(0);
			query.addFacetField("hour");
			query.setFacetMinCount(1);
			query.setFacetMissing(false);

			QueryResponse response = cloudSolrServer.query(query, SolrRequest.METHOD.POST);
			dto.setTotal((int) response.getResults().getNumFound());
			dto.setField(response.getFacetField("hour"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	private SolrDataDto facetByHour(long owner, String mtype, String atype, boolean isShare, long site, long page, Date starttime, Date endtime) throws Exception {
		SolrDataDto dto = new SolrDataDto();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.000'Z'");
			String start = sdf.format(starttime);
			String end = sdf.format(endtime);

			SolrQuery query = new SolrQuery();
			query.setQuery("*");
			query.addFilterQuery("owner:" + owner);
			query.addFilterQuery("mtype:" + mtype);
			query.addFilterQuery("atype:" + atype);
			if (isShare && "c".equals(atype)) {
				query.addFilterQuery("ssh:*");
			}
			if (site > 0) {
				query.addFilterQuery("site:" + site);
			}
			if (page > 0) {
				query.addFilterQuery("page:" + page);
			}
			query.addFilterQuery("created:[" + start + " TO " + end + "]");
			query.setRows(0);
			query.addFacetField("hour");
			query.setFacetMinCount(1);
			query.setFacetMissing(false);

			QueryResponse response = cloudSolrServer.query(query, SolrRequest.METHOD.POST);
			dto.setTotal((int) response.getResults().getNumFound());
			dto.setField(response.getFacetField("hour"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	private SolrDataDto groupByDay(long owner, String mtype, String atype, int days) throws Exception {
		SolrDataDto dto = new SolrDataDto();
		try {
			SolrQuery query = new SolrQuery();
			query.setQuery("*");
			query.addFilterQuery("owner:" + owner);
			query.addFilterQuery("mtype:" + mtype);
			query.addFilterQuery("atype:" + atype);
			query.setRows(0);
			query.setFacetMinCount(1);
			query.setFacetMissing(false);

			query.setParam(GroupParams.GROUP, true);
			query.setParam(GroupParams.GROUP_FACET, true);
			query.setParam(GroupParams.GROUP_FIELD, "cookie");
			query.setParam(GroupParams.GROUP_LIMIT, "1");

			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.HOUR_OF_DAY, 8);
			Date end = cal.getTime();

			removeTime(cal);
			cal.add(Calendar.DAY_OF_YEAR, -days);
			cal.add(Calendar.HOUR_OF_DAY, 8);
			Date start = cal.getTime();
			query.addDateRangeFacet("created", start, end, "+1DAY");

			QueryResponse response = cloudSolrServer.query(query, SolrRequest.METHOD.POST);
			dto.setDocumentList(response.getResults());
			dto.setRflist(response.getFacetRanges());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	private SolrDataDto groupByDay(long owner, String mtype, String atype, long site, long page, Date start, Date end) throws Exception {
		SolrDataDto dto = new SolrDataDto();
		try {
			SolrQuery query = new SolrQuery();
			query.setQuery("*");
			query.addFilterQuery("owner:" + owner);
			query.addFilterQuery("mtype:" + mtype);
			query.addFilterQuery("atype:" + atype);
			if (site > 0) {
				query.addFilterQuery("site:" + site);
			}
			if (page > 0) {
				query.addFilterQuery("page:" + page);
			}
			query.setRows(0);
			query.setFacetMinCount(1);
			query.setFacetMissing(false);

			query.setParam(GroupParams.GROUP, true);
			query.setParam(GroupParams.GROUP_FACET, true);
			query.setParam(GroupParams.GROUP_FIELD, "cookie");
			query.setParam(GroupParams.GROUP_LIMIT, "1");

			query.addDateRangeFacet("created", add8Hour(start), add8Hour(end), "+1DAY");

			QueryResponse response = cloudSolrServer.query(query, SolrRequest.METHOD.POST);
			dto.setDocumentList(response.getResults());
			dto.setRflist(response.getFacetRanges());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	private SolrDataDto groupByHour(long owner, String mtype, String atype, int days) throws Exception {
		SolrDataDto dto = new SolrDataDto();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.000'Z'");
			Calendar cal = Calendar.getInstance();
			removeTime(cal);
			if (days != 1) {
				cal.add(Calendar.DAY_OF_YEAR, 1);
			}
			String end = sdf.format(cal.getTime());

			if (days > 0) {
				cal.add(Calendar.DAY_OF_YEAR, -days);
			} else {
				cal.add(Calendar.DAY_OF_YEAR, -1);
			}
			String start = sdf.format(cal.getTime());

			SolrQuery query = new SolrQuery();
			query.setQuery("*");
			query.addFilterQuery("owner:" + owner);
			query.addFilterQuery("mtype:" + mtype);
			query.addFilterQuery("atype:" + atype);
			query.addFilterQuery("created:[" + start + " TO " + end + "]");
			query.setRows(0);
			query.addFacetField("hour");
			query.setFacetMinCount(1);
			query.setFacetMissing(false);

			query.setParam(GroupParams.GROUP, true);
			query.setParam(GroupParams.GROUP_FACET, true);
			query.setParam(GroupParams.GROUP_FIELD, "cookie");
			query.setParam(GroupParams.GROUP_LIMIT, "1");

			QueryResponse response = cloudSolrServer.query(query, SolrRequest.METHOD.POST);
			dto.setField(response.getFacetField("hour"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	private SolrDataDto groupByHour(long owner, String mtype, String atype, long site, long page, Date starttime, Date endtime) throws Exception {
		SolrDataDto dto = new SolrDataDto();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.000'Z'");
			String start = sdf.format(starttime);
			String end = sdf.format(endtime);

			SolrQuery query = new SolrQuery();
			query.setQuery("*");
			query.addFilterQuery("owner:" + owner);
			query.addFilterQuery("mtype:" + mtype);
			query.addFilterQuery("atype:" + atype);
			if (site > 0) {
				query.addFilterQuery("site:" + site);
			}
			if (page > 0) {
				query.addFilterQuery("page:" + page);
			}
			query.addFilterQuery("created:[" + start + " TO " + end + "]");
			query.setRows(0);
			query.addFacetField("hour");
			query.setFacetMinCount(1);
			query.setFacetMissing(false);

			query.setParam(GroupParams.GROUP, true);
			query.setParam(GroupParams.GROUP_FACET, true);
			query.setParam(GroupParams.GROUP_FIELD, "cookie");
			query.setParam(GroupParams.GROUP_LIMIT, "1");

			QueryResponse response = cloudSolrServer.query(query, SolrRequest.METHOD.POST);
			dto.setField(response.getFacetField("hour"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	private Calendar removeTime(Calendar cal) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(sdf.format(cal.getTime()));
		cal.setTime(date);
		return cal;
	}

	private Date add8Hour(Date date) throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, 8);
		return cal.getTime();
	}

	public SolrDataDto dataArea(long owner, String mtype, long site, long page) throws Exception {
		SolrDataDto dto = new SolrDataDto();
		try {
			SolrQuery query = new SolrQuery();
			query.setQuery("*");
			query.addFilterQuery("owner:" + owner);
			query.addFilterQuery("mtype:" + mtype);
			query.addFilterQuery("atype:c");
			if (site > 0) {
				query.addFilterQuery("site:" + site);
			}
			if (page > 0) {
				query.addFilterQuery("page:" + page);
			}
			query.setRows(0);
			query.setFacetMinCount(1);
			query.setFacetMissing(false);
			query.addFacetField("area");

			QueryResponse response = cloudSolrServer.query(query, SolrRequest.METHOD.POST);
			dto.setTotal((int) response.getResults().getNumFound());
			dto.setField(response.getFacetField("area"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	public SolrDataDto dataTerminal(long owner, String mtype, long site, long page) throws Exception {
		SolrDataDto dto = new SolrDataDto();
		try {
			SolrQuery query = new SolrQuery();
			query.setQuery("*");
			query.addFilterQuery("owner:" + owner);
			query.addFilterQuery("mtype:" + mtype);
			query.addFilterQuery("atype:c");
			if (site > 0) {
				query.addFilterQuery("site:" + site);
			}
			if (page > 0) {
				query.addFilterQuery("page:" + page);
			}
			query.setRows(0);
			query.setFacetMinCount(1);
			query.setFacetMissing(false);
			query.addFacetField("agent");

			QueryResponse response = cloudSolrServer.query(query, SolrRequest.METHOD.POST);
			dto.setTotal((int) response.getResults().getNumFound());
			dto.setField(response.getFacetField("agent"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	public SolrDataDto dataUser(long owner, String mtype, long site, long page, int start, int rows, Date sDate, Date eDate) throws Exception {
		SolrDataDto dto = new SolrDataDto();
		try {
			SolrQuery query = new SolrQuery();
			query.setQuery("*");
			query.addFilterQuery("owner:" + owner);
			query.addFilterQuery("mtype:" + mtype);
			query.addFilterQuery("atype:c");
			if (site > 0) {
				query.addFilterQuery("site:" + site);
			}
			if (page > 0) {
				query.addFilterQuery("page:" + page);
			}
			if (sDate != null && eDate != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.000'Z'");
				String starttime = sdf.format(sDate);
				String endtime = sdf.format(eDate);
				query.addFilterQuery("created:[" + starttime + " TO " + endtime + "]");
			}
			query.setStart(start);
			query.setRows(rows);
			query.setSort("created", ORDER.desc);

			if ("wx".equals(mtype)) {
				query.setParam(GroupParams.GROUP, true);
				query.setParam(GroupParams.GROUP_FIELD, "mid");
				query.add("group.ngroups", "true");
				query.setParam(GroupParams.GROUP_SORT, "created desc");

				QueryResponse response = cloudSolrServer.query(query, SolrRequest.METHOD.POST);
				List<GroupCommand> gclist = response.getGroupResponse().getValues();

				if (gclist != null && gclist.size() > 0) {

					GroupCommand gc = gclist.get(0);
					int ngroups = gc.getNGroups();
					List<Group> glist = gc.getValues();

					SolrDocumentList docs = new SolrDocumentList();
					if (glist != null) {
						for (Group g : glist) {
							SolrDocumentList doc = g.getResult();
							docs.addAll(doc);
						}
					}

					dto.setTotal(ngroups);
					dto.setDocumentList(docs);
				}

				return dto;
			}

			QueryResponse response = cloudSolrServer.query(query, SolrRequest.METHOD.POST);
			dto.setTotal((int) response.getResults().getNumFound());
			dto.setDocumentList(response.getResults());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	public SolrDataDto dataUserDetail(long owner, String mtype, String mid, long site, long page, int start, int rows) throws Exception {
		SolrDataDto dto = new SolrDataDto();
		try {
			SolrQuery query = new SolrQuery();
			query.setQuery("*");
			query.addFilterQuery("owner:" + owner);
			query.addFilterQuery("atype:c");
			query.addFilterQuery("mtype:" + mtype);
			query.addFilterQuery("mid:" + mid);
			if (site > 0) {
				query.addFilterQuery("site:" + site);
			}
			if (page > 0) {
				query.addFilterQuery("page:" + page);
			}

			query.setStart(start);
			query.setRows(rows);
			query.setSort("created", ORDER.desc);

			QueryResponse response = cloudSolrServer.query(query, SolrRequest.METHOD.POST);
			dto.setTotal((int) response.getResults().getNumFound());
			dto.setDocumentList(response.getResults());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	public SolrDataDto shareUser(long owner, String mtype, String atype, Date sDate, Date eDate, int start, int rows) throws Exception {
		SolrDataDto dto = new SolrDataDto();
		try {
			String ff = "ssh";
			SolrQuery query = new SolrQuery();
			query.setQuery("*");
			query.addFilterQuery("owner:" + owner);
			query.addFilterQuery("mtype:" + mtype);
			query.addFilterQuery("ssh:*");
			if ("f".equals(atype)) {
				query.addFilterQuery("atype:s");
				ff = "mid";
			} else {
				query.addFilterQuery("atype:" + atype);
			}
			if (sDate != null && eDate != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.000'Z'");
				String starttime = sdf.format(sDate);
				String endtime = sdf.format(eDate);
				query.addFilterQuery("created:[" + starttime + " TO " + endtime + "]");
			}
			query.setRows(0);
			query.addFacetField(ff);
			query.add("facet.offset", start + "");
			query.setFacetLimit(rows);
			query.setFacetMinCount(1);
			query.setFacetMissing(false);

			QueryResponse response = cloudSolrServer.query(query, SolrRequest.METHOD.POST);
			dto.setTotal((int) response.getResults().getNumFound());
			dto.setField(response.getFacetField(ff));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	public SolrDataDto shareUserGroup(long owner, String mtype, String atype, String ssh) throws Exception {
		SolrDataDto dto = new SolrDataDto();
		try {
			SolrQuery query = new SolrQuery();
			query.setQuery("*");
			query.addFilterQuery("owner:" + owner);
			query.addFilterQuery("mtype:" + mtype);
			query.addFilterQuery("atype:" + atype);
			query.addFilterQuery("ssh:" + ssh);
			query.setRows(0);

			query.setParam(GroupParams.GROUP, true);
			query.setParam(GroupParams.GROUP_FIELD, "mid");
			query.setParam("group.ngroups", true);

			QueryResponse response = cloudSolrServer.query(query, SolrRequest.METHOD.POST);
			dto.setGclist(response.getGroupResponse().getValues());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	public SolrDataDto shareUserDetail(long owner, String mtype, String atype, String openid, int start, int rows) throws Exception {
		SolrDataDto dto = new SolrDataDto();
		try {
			SolrQuery query = new SolrQuery();
			query.setQuery("*");
			query.addFilterQuery("owner:" + owner);
			query.addFilterQuery("mtype:" + mtype);
			if ("f".equals(atype)) {
				query.addFilterQuery("atype:s");
				query.addFilterQuery("mid:" + openid);
			} else {
				query.addFilterQuery("atype:" + atype);
				query.addFilterQuery("ssh:" + openid);
			}
			query.setSort("created", ORDER.desc);
			query.setStart(start);
			query.setRows(rows);

			QueryResponse response = cloudSolrServer.query(query, SolrRequest.METHOD.POST);
			dto.setDocumentList(response.getResults());
			dto.setTotal((int) response.getResults().getNumFound());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	public SolrDataDto sharePage(long owner, String mtype, int start, int rows) throws Exception {
		SolrDataDto dto = new SolrDataDto();
		try {
			SolrQuery query = new SolrQuery();
			query.setQuery("*");
			query.addFilterQuery("owner:" + owner);
			query.addFilterQuery("mtype:" + mtype);
			query.addFilterQuery("atype:s");
			query.addFilterQuery("ssh:*");
			query.setRows(0);
			query.addFacetField("aoid");
			query.add("facet.offset", start + "");
			query.setFacetLimit(rows);
			query.setFacetMinCount(1);
			query.setFacetMissing(false);

			QueryResponse response = cloudSolrServer.query(query, SolrRequest.METHOD.POST);
			dto.setTotal((int) response.getResults().getNumFound());
			dto.setField(response.getFacetField("aoid"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	public SolrDataDto sharePageDetail(long owner, String mtype, String atype, long spage, int start, int rows) throws Exception {
		SolrDataDto dto = new SolrDataDto();
		try {
			SolrQuery query = new SolrQuery();
			query.setQuery("*");
			query.addFilterQuery("owner:" + owner);
			query.addFilterQuery("mtype:" + mtype);
			query.addFilterQuery("atype:" + atype);
			query.addFilterQuery("spage:" + spage);
			query.addFilterQuery("ssh:*");
			query.setSort("created", ORDER.desc);
			query.setStart(start);
			query.setRows(rows);

			QueryResponse response = cloudSolrServer.query(query, SolrRequest.METHOD.POST);
			dto.setDocumentList(response.getResults());
			dto.setTotal((int) response.getResults().getNumFound());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	public SolrDataDto facetByAtype(long owner, String mtype, long spage) throws Exception {
		SolrDataDto dto = new SolrDataDto();
		try {
			SolrQuery query = new SolrQuery();
			query.setQuery("*");
			query.addFilterQuery("owner:" + owner);
			query.addFilterQuery("mtype:" + mtype);
			query.addFilterQuery("spage:" + spage);
			query.addFilterQuery("ssh:*");
			query.setRows(0);
			query.addFacetField("atype");
			query.setFacetMinCount(1);
			query.setFacetMissing(false);

			QueryResponse response = cloudSolrServer.query(query, SolrRequest.METHOD.POST);
			dto.setField(response.getFacetField("atype"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	public SolrDataDto facetByAtype(long owner, String mtype, long spage, String group_filed) throws Exception {
		SolrDataDto dto = new SolrDataDto();
		try {
			SolrQuery query = new SolrQuery();
			query.setQuery("*");
			query.addFilterQuery("owner:" + owner);
			query.addFilterQuery("mtype:" + mtype);
			query.addFilterQuery("spage:" + spage);
			query.addFilterQuery("ssh:*");
			query.setRows(0);
			query.addFacetField("atype");
			query.setFacetMinCount(1);
			query.setFacetMissing(false);

			query.setParam(GroupParams.GROUP, true);
			query.setParam(GroupParams.GROUP_FACET, true);
			query.setParam(GroupParams.GROUP_FIELD, group_filed);

			QueryResponse response = cloudSolrServer.query(query, SolrRequest.METHOD.POST);
			dto.setField(response.getFacetField("atype"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	public boolean cbShared(long owner, String source) {
		try {
			SolrQuery query = new SolrQuery();
			query.setQuery("*");
			query.addFilterQuery("owner:" + owner);
			query.addFilterQuery("mtype:wx");
			query.addFilterQuery("atype:s");
			query.addFilterQuery("source:" + source);

			query.setRows(0);

			QueryResponse response = cloudSolrServer.query(query, SolrRequest.METHOD.POST);
			long numFound = response.getResults().getNumFound();
			return numFound > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public int cbSharedNum(long owner, long a_l) {
		try {
			SolrQuery query = new SolrQuery();
			query.setQuery("*");
			query.addFilterQuery("owner:" + owner);
			query.addFilterQuery("mtype:wx");
			query.addFilterQuery("atype:s");
			query.addFilterQuery("a_l:" + a_l);
			query.addFilterQuery("t_s:*");

			query.setRows(0);
			query.setParam(GroupParams.GROUP, true);
			query.setParam(GroupParams.GROUP_FIELD, "t_s");
			query.add("group.ngroups", "true");

			QueryResponse response = cloudSolrServer.query(query, SolrRequest.METHOD.POST);
			return response.getGroupResponse().getValues().get(0).getNGroups();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int cbSenderEffectCount(long owner, String mtype, long page, String starttime, String endtime) {
		try {
			SolrQuery query = new SolrQuery();
			query.setQuery("*");
			query.addFilterQuery("owner:" + owner);
			query.addFilterQuery("mtype:" + mtype);
			query.addFilterQuery("page:" + page);
			query.addFilterQuery("t_s:*");
			if (starttime != null && endtime != null) {
				query.addFilterQuery("created:[" + starttime + " TO " + endtime + "]");
			}
			query.setRows(0);

			query.setParam(GroupParams.GROUP, true);
			query.setParam(GroupParams.GROUP_FIELD, "t_s");
			query.add("group.ngroups", "true");

			QueryResponse response = cloudSolrServer.query(query, SolrRequest.METHOD.POST);
			return response.getGroupResponse().getValues().get(0).getNGroups();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public SolrDocumentList cbSenderEffect(long owner, String mtype, long page, String atype, String starttime, String endtime, int start, int rows) {
		SolrDocumentList docs = new SolrDocumentList();
		try {
			SolrQuery query = new SolrQuery();
			query.setQuery("*");
			query.addFilterQuery("owner:" + owner);
			query.addFilterQuery("mtype:" + mtype);
			query.addFilterQuery("page:" + page);
			query.addFilterQuery("atype:" + atype);
			query.addFilterQuery("t_s:*");
			if (starttime != null && endtime != null) {
				query.addFilterQuery("created:[" + starttime + " TO " + endtime + "]");
			}
			query.setRows(0);
			query.addFacetField("t_s");
			query.add("facet.offset", start + "");
			query.setFacetLimit(rows);
			query.setFacetMissing(false);
			query.setFacetMinCount(1);

			QueryResponse response = cloudSolrServer.query(query, SolrRequest.METHOD.POST);
			FacetField ff = response.getFacetField("t_s");
			List<FacetField.Count> ffcList = ff.getValues();
			for (FacetField.Count ffc : ffcList) {
				String t_s = ffc.getName();

				SolrDocument doc = cbSenderEffect(owner, mtype, page, t_s, starttime, endtime);
				docs.add(doc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return docs;
	}

	public SolrDocument cbSenderEffect(long owner, String mtype, long page, String t_s, String starttime, String endtime) {
		SolrDocument doc = new SolrDocument();
		doc.addField("t_s", t_s);
		doc.addField("s", 0);
		doc.addField("c", 0);
		doc.addField("g", 0);
		doc.addField("h", 0);
		doc.addField("w", 0);
		try {
			SolrQuery query = new SolrQuery();
			query.setQuery("*");
			query.addFilterQuery("owner:" + owner);
			query.addFilterQuery("mtype:" + mtype);
			query.addFilterQuery("page:" + page);
			query.addFilterQuery("t_s:" + t_s);
			if (starttime != null && endtime != null) {
				query.addFilterQuery("created:[" + starttime + " TO " + endtime + "]");
			}

			query.setRows(0);
			query.addFacetField("atype");
			query.setFacetMinCount(1);
			query.setFacetMissing(false);

			QueryResponse response = cloudSolrServer.query(query, SolrRequest.METHOD.POST);
			FacetField ff = response.getFacetField("atype");
			List<FacetField.Count> ffcList = ff.getValues();
			for (FacetField.Count ffc : ffcList) {
				String atype = ffc.getName();
				long count = ffc.getCount();
				doc.setField(atype, count);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doc;
	}

	public long readNumber(String type, String id) {
		try {
			SolrQuery query = new SolrQuery();
			query.setQuery("*");
			query.addFilterQuery("atype:c");
			query.addFilterQuery("k_s:" + type);
			query.addFilterQuery("v_s:" + id);
			query.setRows(0);

			QueryResponse response = cloudSolrServer.query(query, SolrRequest.METHOD.POST);
			return response.getResults().getNumFound();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public String getZkHost() {
		return zkHost;
	}

	public String getDefaultCollection() {
		return defaultCollection;
	}

	public int getZkClientTimeout() {
		return zkClientTimeout;
	}

	public int getZkConnectTimeout() {
		return zkConnectTimeout;
	}

	public void setZkHost(String zkHost) {
		this.zkHost = zkHost;
	}

	public void setDefaultCollection(String defaultCollection) {
		this.defaultCollection = defaultCollection;
	}

	public void setZkClientTimeout(int zkClientTimeout) {
		this.zkClientTimeout = zkClientTimeout;
	}

	public void setZkConnectTimeout(int zkConnectTimeout) {
		this.zkConnectTimeout = zkConnectTimeout;
	}

}
