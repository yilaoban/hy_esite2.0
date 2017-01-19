package com.huiyee.esite.solr;
public class SolrTest {
	public static void main(String[] args)
	{
		SolrServerMgr mgr=new SolrServerMgr();
		mgr.init();
		mgr.getPageData(21);
	}
}
