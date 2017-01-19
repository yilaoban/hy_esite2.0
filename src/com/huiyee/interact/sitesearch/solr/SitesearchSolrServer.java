package com.huiyee.interact.sitesearch.solr;

import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;

import com.huiyee.esite.util.HyConfig;

public class SitesearchSolrServer {

	private CloudSolrServer cloudSolrServer;
	private String zkHost ;
	private String defaultCollection ;
	
	public void setZkHost(String zkHost)
	{
		this.zkHost = zkHost;
	}

	
	public void setDefaultCollection(String defaultCollection)
	{
		this.defaultCollection = defaultCollection;
	}

	public void setCloudSolrServer(CloudSolrServer cloudSolrServer) {
		this.cloudSolrServer = cloudSolrServer;
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

	public QueryResponse search(String text, long owner, List<Long> sites, long catid, int start, int rows) {
		if (sites == null || sites.size() == 0) {
			return null;
		}
		StringBuffer site = new StringBuffer();
		if (sites.size() > 1) {
			site.append("(");
		}
		for (int i = 0; i < sites.size(); i++) {
			site.append(sites.get(i));
			if (i < sites.size() - 1) {
				site.append(" or ");
			}
		}
		if (sites.size() > 1) {
			site.append(")");
		}
		try {
			SolrQuery query = new SolrQuery();
			query.setRequestHandler("/browse");
			query.setQuery(text);
			query.addFilterQuery("owner:" + owner);
			query.addFilterQuery("site:" + site.toString());
			if (catid > 0) {
				query.addFilterQuery("catid_l:" + catid);
			}

			query.setStart(start);
			query.setRows(rows);

			QueryResponse response = cloudSolrServer.query(query, SolrRequest.METHOD.POST);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
