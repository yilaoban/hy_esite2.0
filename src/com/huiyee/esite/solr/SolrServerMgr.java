package com.huiyee.esite.solr;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dto.PageDto;
import com.huiyee.esite.dto.SolrDataDto;
import com.huiyee.esite.util.DateUtil;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.RangeFacet;
import org.apache.solr.common.SolrDocumentList;
public class SolrServerMgr {
	
	private static Log logger = LogFactory.getLog(SolrServerMgr.class);
    private  CloudSolrServer cloudSolrServer;  
  
    
    private String zkHost="a1.huiyee.com:2181/solr";
    
    private String  defaultCollection = "hbasewlog";  
    private int  zkClientTimeout = 20000;  
    private int zkConnectTimeout = 1000;  
    
    public CloudSolrServer  getCloudSolrServer(final String zkHost) {  
        if(cloudSolrServer == null) {  
           init();
        }  
          
        return cloudSolrServer;  
    } 
    
    public void init() {
    	try {  
            cloudSolrServer = new CloudSolrServer(zkHost);  
            cloudSolrServer.setDefaultCollection(defaultCollection);  
           // cloudSolrServer.setZkClientTimeout(zkClientTimeout);  
           // cloudSolrServer.setZkConnectTimeout(zkConnectTimeout);
        }catch(Exception e) {  
            System.out.println("something wrong when creating solr server with " + zkHost);  
            e.printStackTrace();  
        }
    }
    public SolrDataDto getOwnerHdData(long owner) {
        SolrDataDto dto=new SolrDataDto();
		SolrQuery query = new SolrQuery();
		ownerHdDataQuery(query, owner);
		try {
			QueryResponse response = cloudSolrServer.query(query,
					SolrRequest.METHOD.POST);

			List<RangeFacet> rf = response.getFacetRanges();
			dto.setRflist(rf);
			

		} catch (Exception x) {
			x.printStackTrace();
		}
		return dto;
	}
    public SolrDataDto getPageHdData(long pageid) {
        SolrDataDto dto=new SolrDataDto();
		SolrQuery query = new SolrQuery();
		pageHdDataQuery(query, pageid);
		try {
			QueryResponse response = cloudSolrServer.query(query,
					SolrRequest.METHOD.POST);
			List<RangeFacet> rf = response.getFacetRanges();
			dto.setRflist(rf);
		} catch (Exception x) {
			x.printStackTrace();
		}
		return dto;
	}
    public SolrDataDto getPageVisitData(long pageid) {
        SolrDataDto dto=new SolrDataDto();
		SolrQuery query = new SolrQuery();
		pageVisitDataQuery(query, pageid);
		try {
			QueryResponse response = cloudSolrServer.query(query,
					SolrRequest.METHOD.POST);

			List<RangeFacet> rf = response.getFacetRanges();
			dto.setRflist(rf);
		} catch (Exception x) {
			x.printStackTrace();
		}
		return dto;
	}
    public SolrDataDto getPageVisitArea(long pageid,String src) {
        SolrDataDto dto=new SolrDataDto();
		SolrQuery query = new SolrQuery();
		pageVisitAreaQuery(query, pageid, src);
		try {
			QueryResponse response = cloudSolrServer.query(query,
					SolrRequest.METHOD.POST);
            dto.setTotal((int) response.getResults().getNumFound());
			FacetField field = response.getFacetField("area");
			dto.setField(field);
		} catch (Exception x) {
			x.printStackTrace();
		}
		return dto;
	}
    public SolrDataDto getPageVisitGender(long pageid,String src) {
        SolrDataDto dto=new SolrDataDto();
		SolrQuery query = new SolrQuery();
		pageVisitGenderQuery(query, pageid, src);
		try {
			QueryResponse response = cloudSolrServer.query(query,
					SolrRequest.METHOD.POST);

			FacetField field = response.getFacetField("wsex");
			dto.setField(field);
			dto.setTotal((int) response.getResults().getNumFound());
		} catch (Exception x) {
			x.printStackTrace();
		}
		return dto;
	}
    public SolrDataDto getPageVisitFans(long pageid) {
        SolrDataDto dto=new SolrDataDto();
		SolrQuery query = new SolrQuery();
		pageVisitFansQuery(query, pageid);
		try {
			QueryResponse response = cloudSolrServer.query(query,
					SolrRequest.METHOD.POST);
			Map<String, Integer> fq = response.getFacetQuery();
			dto.setFq(fq);
			dto.setTotal((int) response.getResults().getNumFound());
		} catch (Exception x) {
			x.printStackTrace();
		}
		return dto;
	}
    public SolrDataDto getPageHdArea(long pageid,String src) {
        SolrDataDto dto=new SolrDataDto();
		SolrQuery query = new SolrQuery();
		pageHdAreaDataQuery(query, pageid, src);
		try {
			QueryResponse response = cloudSolrServer.query(query,
					SolrRequest.METHOD.POST);

			FacetField field = response.getFacetField("area");
			dto.setField(field);
			dto.setTotal((int) response.getResults().getNumFound());
			
		} catch (Exception x) {
			x.printStackTrace();
		}
		return dto;
	}
    public SolrDataDto getPageHdGender(long pageid,String src) {
        SolrDataDto dto=new SolrDataDto();
		SolrQuery query = new SolrQuery();
		pageHdGenderDataQuery(query, pageid, src);
		try {
			QueryResponse response = cloudSolrServer.query(query,
					SolrRequest.METHOD.POST);
			FacetField field = response.getFacetField("wsex");
			dto.setField(field);
			dto.setTotal((int) response.getResults().getNumFound());
			
		} catch (Exception x) {
			x.printStackTrace();
		}
		return dto;
	}
    public SolrDataDto getPageHdFans(long pageid) {
        SolrDataDto dto=new SolrDataDto();
		SolrQuery query = new SolrQuery();
		pageHdFansQuery(query, pageid);
		try {
			QueryResponse response = cloudSolrServer.query(query,
					SolrRequest.METHOD.POST);
			Map<String, Integer> fq = response.getFacetQuery();
			dto.setFq(fq);
			dto.setTotal((int) response.getResults().getNumFound());
			
		} catch (Exception x) {
			x.printStackTrace();
		}
		return dto;
	}
    public SolrDataDto getPageHdType(long pageid, String src) {
        SolrDataDto dto=new SolrDataDto();
		SolrQuery query = new SolrQuery();
		pageHdTypeDataQuery(query, pageid, src);
		try {
			QueryResponse response = cloudSolrServer.query(query,
					SolrRequest.METHOD.POST);
			FacetField field = response.getFacetField("act");
			dto.setField(field);
			dto.setTotal((int) response.getResults().getNumFound());
			
		} catch (Exception x) {
			x.printStackTrace();
		}
		return dto;
	}
    public SolrDataDto getPageHdList(long pageid,int start,int size,String src) {
        SolrDataDto dto=new SolrDataDto();
		SolrQuery query = new SolrQuery();
		pageHdListDataQuery(query, pageid,start,size,src);
		try {
			QueryResponse response = cloudSolrServer.query(query,
					SolrRequest.METHOD.POST);
			dto.setTotal((int) response.getResults().getNumFound());
			SolrDocumentList list=response.getResults();
			dto.setDocumentList(list);
			
		} catch (Exception x) {
			x.printStackTrace();
		}
		return dto;
	}
    public SolrDataDto getPageHdByAct(long pageid,long act) {
        SolrDataDto dto=new SolrDataDto();
		SolrQuery query = new SolrQuery();
		pageHdDataByTypeQuery(query, pageid,act);
		try {
			QueryResponse response = cloudSolrServer.query(query,
					SolrRequest.METHOD.POST);
			dto.setTotal((int) response.getResults().getNumFound());
			SolrDocumentList list=response.getResults();
			if(list!=null&&list.size()>0){
				dto.setDocument(list.get(0));
			}
		} catch (Exception x) {
			x.printStackTrace();
		}
		return dto;
	}
    public SolrDataDto getPageVisitList(long pageid,int start,int size,String src) {
        SolrDataDto dto=new SolrDataDto();
		SolrQuery query = new SolrQuery();
		pageVisitListDataQuery(query, pageid,start,size,src);
		try {
			QueryResponse response = cloudSolrServer.query(query,
					SolrRequest.METHOD.POST);
			dto.setTotal((int) response.getResults().getNumFound());
			SolrDocumentList list=response.getResults();
			dto.setDocumentList(list);
			
		} catch (Exception x) {
			x.printStackTrace();
		}
		return dto;
	}
    public PageDto getPageData(long pageid) {
    	PageDto dto=new PageDto();
		SolrQuery visitquery = new SolrQuery();
		SolrQuery hdquery = new SolrQuery();
		SolrQuery addvquery = new SolrQuery();
		SolrQuery addhdquery = new SolrQuery();
		pageVisitDataQuery(visitquery,pageid,0);
		pageHdDataQuery(hdquery,pageid,0);
		pageVisitDataQuery(addvquery,pageid,-1);
		pageHdDataQuery(addhdquery,pageid,-1);
		try {
			QueryResponse visitresponse = cloudSolrServer.query(visitquery,
					SolrRequest.METHOD.POST);
			QueryResponse hdresponse = cloudSolrServer.query(hdquery,
					SolrRequest.METHOD.POST);
			QueryResponse vaddresponse = cloudSolrServer.query(addvquery,
					SolrRequest.METHOD.POST);
			QueryResponse hdaddresponse = cloudSolrServer.query(addhdquery,
					SolrRequest.METHOD.POST);
			int vtotal=(int)visitresponse.getResults().getNumFound();
			dto.setTotalvnum(vtotal);
			int htotal=(int)hdresponse.getResults().getNumFound();
			dto.setTotalhnum(htotal);
			List<RangeFacet> rfv = vaddresponse.getFacetRanges();
			for (RangeFacet r : rfv) {
				List<RangeFacet.Count> rx = r.getCounts();
				for (RangeFacet.Count ra : rx) {
					int addvnum=ra.getCount();
					dto.setAddvnum(addvnum);
					break;
				}

			}
			List<RangeFacet> rfh = hdaddresponse.getFacetRanges();
			for (RangeFacet r : rfh) {
				List<RangeFacet.Count> rx = r.getCounts();
				for (RangeFacet.Count ra : rx) {
					int addhnum=ra.getCount();
					dto.setAddhnum(addhnum);
					break;
				}

			}
		} catch (Exception x) {
			x.printStackTrace();
		}
		return dto;
	}
    public SolrDataDto getOwnerWbVisitData(long owner) {
        SolrDataDto dto=new SolrDataDto();
		SolrQuery query = new SolrQuery();
		ownerWbVisitDataQuery(query, owner);
		try {
			QueryResponse response = cloudSolrServer.query(query,
					SolrRequest.METHOD.POST);
//			for (int i = 0; i < x.size(); i++) {
//				FacetField y = (FacetField) x.get(i);
//				System.out.println(y.getName());
//
//				List<FacetField.Count> cs = y.getValues();
//				for (FacetField.Count a : cs) {
//					System.out.println(a.getName() + " " + a.getCount());
//
//				}
//
//			}

			// this is the range for date
			List<RangeFacet> rf = response.getFacetRanges();
			dto.setRflist(rf);
			// this is for followers
//			Map<String, Integer> fq = response.getFacetQuery();
//			Iterator it = fq.keySet().iterator();
//			while (it.hasNext()) {
//				String key = (String) it.next();
//				Integer value = fq.get(key);
//				System.out.println(key + " " + value);
//			}
			

		} catch (Exception x) {
			x.printStackTrace();
		}
		return dto;
	}
    public SolrDataDto getOwnerWxVisitData(long owner) {
        SolrDataDto dto=new SolrDataDto();
		SolrQuery query = new SolrQuery();
		ownerWxVisitDataQuery(query, owner);
		try {
			QueryResponse response = cloudSolrServer.query(query,SolrRequest.METHOD.POST);
			List<RangeFacet> rf = response.getFacetRanges();
			dto.setRflist(rf);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

    private void ownerWbVisitDataQuery(SolrQuery query,long owner) {
    	query.setQuery("*");
    	query.addFilterQuery("ow:"+owner);
    	query.addFilterQuery("type:v");
    	query.addFilterQuery("nm:n");
    	query.addFilterQuery("src:s");
    	query.setRows(0);
    	query.setFacetMinCount(1);
    	query.setFacetMissing(false);
    	String theday=DateUtil.getDateString(new Date())+" 00:00:00";
    	long end=DateUtil.getDateTime(theday).getTime();
    	long start = end - IPageConstants.ONE_MONTH;
    	query.addNumericRangeFacet("created", start, end, IPageConstants.ONE_DAY);
    	return;
    }
    private void ownerWxVisitDataQuery(SolrQuery query,long owner) {
    	query.setQuery("*");
    	query.addFilterQuery("ow:"+owner);
    	query.addFilterQuery("type:v");
    	query.addFilterQuery("nm:n");
    	query.addFilterQuery("src:q");
    	query.setRows(0);
    	query.setFacetMinCount(1);
    	query.setFacetMissing(false);
    	String theday=DateUtil.getDateString(new Date())+" 00:00:00";
    	long end=DateUtil.getDateTime(theday).getTime();
    	long start = end - IPageConstants.ONE_MONTH;
    	query.addNumericRangeFacet("created", start, end, IPageConstants.ONE_DAY);
    	return;
    }
    private  void ownerHdDataQuery(SolrQuery query,long owner) {
    	query.setQuery("*");
    	query.addFilterQuery("ow:"+owner);
    	query.addFilterQuery("type:j");
    	query.setRows(0);
    	query.setFacetMinCount(1);
    	query.setFacetMissing(false);
    	String theday=DateUtil.getDateString(new Date())+" 00:00:00";
    	long end=DateUtil.getDateTime(theday).getTime();
    	long start = end - IPageConstants.ONE_MONTH;
    	query.addNumericRangeFacet("created", start, end, IPageConstants.ONE_DAY);
    	return;
    }
    private void pageVisitDataQuery(SolrQuery query,long pageid) {
    	query.setQuery("*");
    	query.addFilterQuery("pgid:"+pageid);
    	query.addFilterQuery("type:v");
    	query.addFilterQuery("nm:n");
    	query.setRows(0);
    	query.setFacetMinCount(1);
    	query.setFacetMissing(false);
    	String theday=DateUtil.getDateString(new Date())+" 00:00:00";
    	long end=DateUtil.getDateTime(theday).getTime();
    	long start = end - IPageConstants.ONE_MONTH;
    	query.addNumericRangeFacet("created", start, end, IPageConstants.ONE_DAY);
    	
    	return;
    }
    private void pageVisitAreaQuery(SolrQuery query,long pageid,String src) {
    	query.setQuery("*");
    	query.addFilterQuery("pgid:"+pageid);
    	query.addFilterQuery("type:v");
    	query.addFilterQuery("src:"+src);
    	query.addFilterQuery("nm:n");
    	query.setRows(0);
    	query.setFacetMinCount(1);
    	query.setFacetMissing(false);
    	query.addFacetField("area");
    	return;
    }
    private void pageVisitGenderQuery(SolrQuery query,long pageid,String src) {
    	query.setQuery("*");
    	query.addFilterQuery("pgid:"+pageid);
    	query.addFilterQuery("type:v");
    	query.addFilterQuery("src:"+src);
    	query.addFilterQuery("nm:n");
    	query.setRows(0);
    	query.setFacetMinCount(1);
    	query.setFacetMissing(false);
    	query.addFacetField("wsex");
    	return;
    }
    private void pageVisitFansQuery(SolrQuery query, long pageid)
	{
		query.setQuery("*");
		query.addFilterQuery("pgid:" + pageid);
		query.addFilterQuery("type:v");
		query.addFilterQuery("nm:n");
		query.setRows(0);
		query.setFacetMinCount(1);
		query.setFacetMissing(false);
		query.addFacetQuery("{!key=A}wfans:[* TO 500}");
		query.addFacetQuery("{!key=B}wfans:[500 TO 1000}");
		query.addFacetQuery("{!key=C}wfans:[1000 TO 3000}");
		query.addFacetQuery("{!key=D}wfans:[3000 TO 5000}");
		query.addFacetQuery("{!key=E}wfans:[5000 TO 10000}");
		query.addFacetQuery("{!key=F}wfans:[10000 TO *]");
		return;
	}
    private void pageHdFansQuery(SolrQuery query, long pageid)
	{
		query.setQuery("*");
		query.addFilterQuery("pgid:" + pageid);
		query.addFilterQuery("type:j");
		query.setRows(0);
		query.setFacetMinCount(1);
		query.setFacetMissing(false);
		query.addFacetQuery("{!key=A}wfans:[* TO 500}");
		query.addFacetQuery("{!key=B}wfans:[500 TO 1000}");
		query.addFacetQuery("{!key=C}wfans:[1000 TO 3000}");
		query.addFacetQuery("{!key=D}wfans:[3000 TO 5000}");
		query.addFacetQuery("{!key=E}wfans:[5000 TO 10000}");
		query.addFacetQuery("{!key=F}wfans:[10000 TO *]");
		return;
	}
    private void pageHdDataQuery(SolrQuery query,long pageid) {
    	query.setQuery("*");
    	query.addFilterQuery("pgid:"+pageid);
    	query.addFilterQuery("type:j");
    	query.setRows(0);
    	query.setFacetMinCount(1);
    	query.setFacetMissing(false);
    	String theday=DateUtil.getDateString(new Date())+" 00:00:00";
    	long end=DateUtil.getDateTime(theday).getTime();
    	long start = end - IPageConstants.ONE_MONTH;
    	query.addNumericRangeFacet("created", start, end, IPageConstants.ONE_DAY);
    	return;
    }
    private void pageHdAreaDataQuery(SolrQuery query,long pageid,String src) {
    	query.setQuery("*");
    	query.addFilterQuery("pgid:"+pageid);
    	query.addFilterQuery("type:j");
    	query.addFilterQuery("src:"+src);
    	query.setRows(0);
    	query.setFacetMinCount(1);
    	query.setFacetMissing(false);
    	query.addFacetField("area");
    	return;
    }
    private void pageHdGenderDataQuery(SolrQuery query,long pageid,String src) {
    	query.setQuery("*");
    	query.addFilterQuery("pgid:"+pageid);
    	query.addFilterQuery("type:j");
    	query.addFilterQuery("src:"+src);
    	query.setRows(0);
    	query.setFacetMinCount(1);
    	query.setFacetMissing(false);
    	query.addFacetField("wsex");
    	return;
    }
    /**
     * 
     * @param query
     * @param pageid
     * @param offset 从当前往前推几天的数据
     */
    private  void pageHdDataQuery(SolrQuery query,long pageid,int offset) {
    	query.setQuery("*");
    	query.addFilterQuery("pgid:"+pageid);
    	query.addFilterQuery("type:j");
    	query.setRows(0);
    	query.setFacetMinCount(1);
    	query.setFacetMissing(false);
    	if(offset>0){
    		String theday=DateUtil.getDateString(new Date())+" 00:00:00";
        	long end=DateUtil.getDateTime(theday).getTime();
        	long start = end - offset*IPageConstants.ONE_DAY;
        	query.addNumericRangeFacet("created", start, end, IPageConstants.ONE_DAY);
    	}else if(offset==-1){
    		String theday=DateUtil.getDateString(DateUtil.addDays(new Date(), 1))+" 00:00:00";
        	long end=DateUtil.getDateTime(theday).getTime();
        	long start = end - IPageConstants.ONE_DAY;
        	query.addNumericRangeFacet("created", start, end, IPageConstants.ONE_DAY);
    	}
    	return;
    }
    /**
     * 
     * @param query
     * @param pageid
     * @param offset 从当前往前推几天的数据 -1表示取当天的数据
     */
    private  void pageVisitDataQuery(SolrQuery query,long pageid,int offset) {
    	query.setQuery("*");
    	query.addFilterQuery("pgid:"+pageid);
    	query.addFilterQuery("type:v");
    	query.addFilterQuery("nm:n");
    	query.setRows(0);
    	query.setFacetMinCount(1);
    	query.setFacetMissing(false);
    	if(offset>0){
    		String theday=DateUtil.getDateString(new Date())+" 00:00:00";
        	long end=DateUtil.getDateTime(theday).getTime();
        	long start = end - offset*IPageConstants.ONE_DAY;
        	query.addNumericRangeFacet("created", start, end, IPageConstants.ONE_DAY);
    	}else if(offset==-1){
    		String theday=DateUtil.getDateString(DateUtil.addDays(new Date(), 1))+" 00:00:00";
        	long end=DateUtil.getDateTime(theday).getTime();
        	long start = end - IPageConstants.ONE_DAY;
        	query.addNumericRangeFacet("created", start, end, IPageConstants.ONE_DAY);
    	}
    	return;
    }
    private void pageHdTypeDataQuery(SolrQuery query,long pageid,String src) {
    	query.setQuery("*");
    	query.addFilterQuery("pgid:"+pageid);
    	query.addFilterQuery("type:j");
    	query.addFilterQuery("src:"+src);
    	query.setRows(0);
    	query.setSort("created",ORDER.desc);
    	query.setFacetMinCount(1);
    	query.setFacetMissing(false);
        query.addFacetField("act");
    	return;
    }
    private void pageHdListDataQuery(SolrQuery query,long pageid,int start,int size,String src) {
    	query.setQuery("*");
    	query.addFilterQuery("pgid:"+pageid);
    	query.addFilterQuery("type:j");
    	query.addFilterQuery("src:"+src);
    	query.setSort("created",ORDER.desc);
    	query.setFacetMinCount(1);
    	query.setFacetMissing(false);
    	query.setStart(start);
        query.setRows(size);
    	return;
    }
    private void pageHdDataByTypeQuery(SolrQuery query,long pageid,long act) {
    	query.setQuery("*");
    	query.addFilterQuery("pgid:"+pageid);
    	query.addFilterQuery("type:j");
    	query.addFilterQuery("act:"+act);
    	query.setSort("created",ORDER.desc);
    	query.setRows(1);
    	query.setFacetMinCount(1);
    	query.setFacetMissing(false);
    	return;
    }
    private void pageVisitListDataQuery(SolrQuery query,long pageid,int start,int size,String src) {
    	query.setQuery("*");
    	query.addFilterQuery("pgid:"+pageid);
    	query.addFilterQuery("type:v");
    	query.addFilterQuery("src:"+src);
    	query.addFilterQuery("nm:n");
    	query.setSort("created",ORDER.desc);
    	query.setFacetMinCount(1);
    	query.setFacetMissing(false);
        //query.addFacetField("wbuid");
    	query.setStart(start);
    	query.setRows(size);
    	return;
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
