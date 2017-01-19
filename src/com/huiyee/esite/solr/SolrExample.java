package com.huiyee.esite.solr;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.RangeFacet;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

public class SolrExample {
	
	
    private static CloudSolrServer cloudSolrServer;  
    
    private static String zkHost="a1.huiyee.com:2181/solr";
    
    private static  String  defaultCollection = "hbasecola";  
    private static int  zkClientTimeout = 2000;  
    private static  int zkConnectTimeout = 1000;  
    
    private  static synchronized CloudSolrServer getCloudSolrServer(final String zkHost) {  
        if(cloudSolrServer == null) {  
            try {  
                cloudSolrServer = new CloudSolrServer(zkHost);  
            }catch(Exception e) {  
                System.out.println("something wrong");  
                e.printStackTrace();  
            }
        }  
          
        return cloudSolrServer;  
    } 
    
    public static void search(SolrServer solrServer, String q) {        
        
        SolrQuery solrQuery = new SolrQuery(q);

        
       solrQuery.set("q", q);
        
        try {
        	
        	
        String key = "测试数据";
        	
        //  solrQuery.addFilterQuery("prov:11","city:1");
        solrQuery.addFilterQuery("skwd:" + key);
        
        solrQuery.setRows(0);
        solrQuery.addFilterQuery("fans:[1000 TO 20000]");
        System.out.println(solrQuery.toString());
        
        }catch (Exception x) {
        	System.out.println("HEHHEH");
        	x.printStackTrace();
        	return;
        }
       
       // solrQuery.set("start", 0);
        //solrQuery.set("rows", 10);
      
   
      
    
  
        try {  
        	
        	QueryResponse response = solrServer.query(solrQuery, SolrRequest.METHOD.POST);
            SolrDocumentList docs = response.getResults();  
  
            System.out.println("文档个数�?" + docs.getNumFound());  
            System.out.println("查询时间�?" + response.getQTime());  
  
            for (SolrDocument doc : docs) {  
                Long uage = (Long) doc.getFieldValue("uage");  
                String sex = (String) doc.getFieldValue("sex");  
                System.out.println("uage: " + uage);  
                System.out.println("sex: " + sex);  
                System.out.println();  
            }  
        } catch (SolrServerException e) {  
            e.printStackTrace();  
        } catch(Exception e) {  
            System.out.println("Unknowned Exception!!!!");  
            e.printStackTrace();  
        }  
    }  
    
    
    public static void main(String[] args) throws Exception {          
              
        CloudSolrServer cloudSolrServer = getCloudSolrServer(zkHost);         
        System.out.println("The Cloud SolrServer Instance has benn created!");  
          
        cloudSolrServer.setDefaultCollection(defaultCollection);  
        cloudSolrServer.setZkClientTimeout(zkClientTimeout);  
        cloudSolrServer.setZkConnectTimeout(zkConnectTimeout);    
          
        
        
       
        
        
      search(cloudSolrServer, "*");  
          /*
       
        wbStat("3735614698134393", 1406123410000L);
          
        Thread.sleep(100000);
        wbStat("3735614698134393", 1406123410000L);
         // release the resource   
        cloudSolrServer.shutdown();  
        */
   
    }  
    
    private static void wbStat(String webid, long wbcreated) {
 
        
        SolrQuery query = new SolrQuery();

        constructQuery(query, webid, wbcreated);
        
        System.out.println(query.toString());
        
        
        try {  
        	
        	QueryResponse response = cloudSolrServer.query(query, SolrRequest.METHOD.POST);
            SolrDocumentList docs = response.getResults();  
  
            System.out.println("文档个数�?" + docs.getNumFound());  
            System.out.println("查询时间�?" + response.getQTime()); 
           
            List x = response.getFacetFields();
            
            for (int i=0; i<x.size(); i++) {
            	FacetField y = (FacetField)x.get(i);
            	System.out.println(y.getName());
            	
            	List<FacetField.Count> cs = y.getValues();
            	for (FacetField.Count a:cs) {
            		System.out.println(a.getName() + " "  + a.getCount());
            		
            	}
            	
            }
            
           List<RangeFacet> rf = response.getFacetRanges();
           for (RangeFacet r:rf) {
        	   List<RangeFacet.Count> rx = r.getCounts();
        	   for(RangeFacet.Count ra:rx) {
        		   System.out.println(ra.getValue() + " " + ra.getCount());
        	   }
        	   
        	   
           }
           
           Map<String, Integer>fq = response.getFacetQuery();
           Iterator it = fq.keySet().iterator();
           while (it.hasNext()) {
        	   String key = (String)it.next();
        	   Integer value = fq.get(key);
        	   System.out.println(key + " " + value);
           }
            
        }catch (Exception x) {
        	x.printStackTrace();
        }
    	
    }
    
    private static long TWO_WEEK = 14 * 24 * 60 * 60 * 1000;
    private static String fansQuery = "facet.query=fans:[* TO 500}&facet.query=fans:[500 TO 1000}&facet.query=fans:[1000 TO 2000}&facet.query=fans:[2000 TO 5000}&facet.query=fans:[5000 TO 10000}&facet.query=fans:[10000 TO *]";
    
    private static void constructQuery(SolrQuery query, String wid, long created) {
    	query.setQuery("*");
    	query.addFilterQuery("mwbid:"+wid);
    	query.setRows(0);
    	query.setFacetMinCount(1);
    	query.setFacetMissing(false);
    	
    	query.addFacetField("hour", "prov", "vtype", "sex");
    	long end = created + TWO_WEEK;
    	query.addNumericRangeFacet("wbcreated", created, end, TWO_WEEK);
    	
    	query.addFacetQuery("{!key=A}fans:[* TO 500}");
    	query.addFacetQuery("fans:[500 TO 1000}");
    	query.addFacetQuery("fans:[1000 TO 3000}");
    	query.addFacetQuery("fans:[3000 TO 5000}");
    	query.addFacetQuery("fans:[5000 TO 10000}");
    	query.addFacetQuery("fans:[10000 TO *]");
    	
    	
    	return;
    }
    	
    	
}
