package com.huiyee.esite.solr;

import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.GroupParams;

import com.huiyee.esite.dto.SolrDataDto;

public class HyJfSolrServer {

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

	public SolrDataDto findByCday(long owner, String type, String cday, int start, int rows) throws Exception {

		SolrDataDto dto = new SolrDataDto();
		try {
			SolrQuery query = new SolrQuery();
			query.setQuery("*");
			query.addFilterQuery("owner:" + owner);
			if ("RMB".equals(type)) {
				query.addFilterQuery("type:RMB");
			} else {
				query.addFilterQuery("!type:RMB");
				query.addFilterQuery("!type:CHR");
			}
			query.addFilterQuery("cday:" + cday);

			query.setSort("ctime", ORDER.desc);
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

	public SolrDataDto findAddByCday(long owner, String type, String cday, int start, int rows) throws Exception {
		SolrDataDto dto = new SolrDataDto();
		try {
			SolrQuery query = new SolrQuery();
			query.setQuery("*");
			query.addFilterQuery("owner:" + owner);
			if ("RMB".equals(type)) {
				query.addFilterQuery("type:RMB");
			} else {
				query.addFilterQuery("!type:RMB");
				query.addFilterQuery("!type:CHR");
			}
			query.addFilterQuery("score:[0 TO *]");
			query.addFilterQuery("cday:" + cday);

			query.setSort("ctime", ORDER.desc);
			query.setStart(start);
			query.setRows(rows);
			query.add("stats", "true");
			query.add("stats.field", "score");

			QueryResponse response = cloudSolrServer.query(query, SolrRequest.METHOD.POST);
			dto.setDocumentList(response.getResults());
			dto.setTotal((int) response.getResults().getNumFound());
			dto.setFsi(response.getFieldStatsInfo().get("score"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	public SolrDataDto findSubByCday(long owner, String type, String cday, int start, int rows) throws Exception {
		SolrDataDto dto = new SolrDataDto();
		try {
			SolrQuery query = new SolrQuery();
			query.setQuery("*");
			query.addFilterQuery("owner:" + owner);
			if ("RMB".equals(type)) {
				query.addFilterQuery("type:RMB");
			} else {
				query.addFilterQuery("!type:RMB");
				query.addFilterQuery("!type:CHR");
			}
			query.addFilterQuery("score:[* TO 0]");
			query.addFilterQuery("cday:" + cday);

			query.setSort("ctime", ORDER.desc);
			query.setStart(start);
			query.setRows(rows);
			query.add("stats", "true");
			query.add("stats.field", "score");

			QueryResponse response = cloudSolrServer.query(query, SolrRequest.METHOD.POST);
			dto.setDocumentList(response.getResults());
			dto.setTotal((int) response.getResults().getNumFound());
			dto.setFsi(response.getFieldStatsInfo().get("score"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	public SolrDataDto findUserJfDetail(long hyuid, int start, int rows) throws Exception {
		SolrDataDto dto = new SolrDataDto();
		try {
			SolrQuery query = new SolrQuery();
			query.setQuery("*");
			query.addFilterQuery("hyuid:" + hyuid);
			query.addFilterQuery("!type:RMB");
			query.addFilterQuery("!type:CHR");
			query.setSort("ctime", ORDER.desc);
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

	public SolrDataDto findUserRmbDetail(long hyuid, String solrtype, int start, int rows) throws Exception {
		SolrDataDto dto = new SolrDataDto();
		try {
			SolrQuery query = new SolrQuery();
			query.setQuery("*");
			query.addFilterQuery("hyuid:" + hyuid);
			if (StringUtils.isNotEmpty(solrtype)) {
				query.addFilterQuery("type:" + solrtype);
			}
			query.setSort("ctime", ORDER.desc);
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

	public SolrDataDto userSolrDetail(long hyuid, String solrtype, int start, int rows) throws Exception {
		SolrDataDto dto = new SolrDataDto();
		try {
			SolrQuery query = new SolrQuery();
			query.setQuery("*");
			query.addFilterQuery("hyuid:" + hyuid);
			if (StringUtils.isNotEmpty(solrtype)) {
				query.addFilterQuery("type:" + solrtype);
			}

			query.setSort("ctime", ORDER.desc);
			query.setStart(start);
			query.setRows(rows);
			query.setParam(GroupParams.GROUP, true);
			query.setParam(GroupParams.GROUP_FIELD, "cday");
			query.add("group.ngroups", "true");

			QueryResponse response = cloudSolrServer.query(query, SolrRequest.METHOD.POST);
			dto.setGclist(response.getGroupResponse().getValues());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	public SolrDataDto userSolrDetail(long hyuid, String solrtype, int cday) throws Exception {
		SolrDataDto dto = new SolrDataDto();
		try {
			SolrQuery query = new SolrQuery();
			query.setQuery("*");
			query.addFilterQuery("hyuid:" + hyuid);
			if (StringUtils.isNotEmpty(solrtype)) {
				query.addFilterQuery("type:" + solrtype);
			}
			query.addFilterQuery("cday:" + cday);
			query.setSort("ctime", ORDER.desc);

			QueryResponse response = cloudSolrServer.query(query, SolrRequest.METHOD.POST);
			dto.setDocumentList(response.getResults());
			dto.setTotal((int) response.getResults().getNumFound());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	public SolrDataDto findUserBill(long owner) throws Exception {
		SolrDataDto dto = new SolrDataDto();
		try {
			SolrQuery query = new SolrQuery();
			query.setQuery("*");
			query.addFilterQuery("owner:" + owner);
			query.addFilterQuery("stype:" + "SMQ");
			query.addFilterQuery("type:" + "RMB");
			query.setSort("ctime", ORDER.desc);

			QueryResponse response = cloudSolrServer.query(query, SolrRequest.METHOD.POST);
			dto.setDocumentList(response.getResults());
			dto.setTotal((int) response.getResults().getNumFound());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	public SolrDataDto findUserJfAddDetail(long hyuid, int start, int rows) throws Exception {
		SolrDataDto dto = new SolrDataDto();
		try {
			SolrQuery query = new SolrQuery();
			query.setQuery("*");
			query.addFilterQuery("hyuid:" + hyuid);
			query.addFilterQuery("!type:RMB");
			query.addFilterQuery("!type:CHR");
			query.addFilterQuery("score:[0 TO *]");
			query.setSort("ctime", ORDER.desc);
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

	public SolrDataDto findUserJfSubDetail(long hyuid, int start, int rows) throws Exception {
		SolrDataDto dto = new SolrDataDto();
		try {
			SolrQuery query = new SolrQuery();
			query.setQuery("*");
			query.addFilterQuery("hyuid:" + hyuid);
			query.addFilterQuery("!type:RMB");
			query.addFilterQuery("!type:CHR");
			query.addFilterQuery("score:[* TO 0]");
			query.setSort("ctime", ORDER.desc);
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

	public SolrDataDto getRecordBySolr(long hyuid, String solrtype, String solrstype, int start, int rows) {
		SolrDataDto dto = new SolrDataDto();
		try {
			if (StringUtils.isEmpty(solrtype) && StringUtils.isNotEmpty(solrstype)) {
				dto.setDocumentList(new SolrDocumentList());
			} else {
				SolrQuery query = new SolrQuery();
				query.setQuery("*");
				query.addFilterQuery("hyuid:" + hyuid);
				if (StringUtils.isNotEmpty(solrtype)) {
					query.addFilterQuery("type:" + solrtype);
				}
				if (StringUtils.isNotEmpty(solrstype)) {
					query.addFilterQuery("stype:" + solrstype);
				}
				query.setSort("ctime", ORDER.desc);
				query.setStart(start);
				query.setRows(rows);

				QueryResponse response = cloudSolrServer.query(query, SolrRequest.METHOD.POST);
				dto.setDocumentList(response.getResults());
				dto.setTotal((int) response.getResults().getNumFound());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

}
