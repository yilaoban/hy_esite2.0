package com.huiyee.esite.solr;

import org.apache.solr.client.solrj.SolrQuery;

public class SimpleUserSearch {
	
	public static int NOT_REQUIRED = -100000;
	
	private int minFans = NOT_REQUIRED;
	private int maxFans= NOT_REQUIRED;
	
	private int vtypes = NOT_REQUIRED;
	
	private long starttime = 0L;
	private long endtime = 0L;
	private int province = NOT_REQUIRED;
	private int city = NOT_REQUIRED;
	
	private String sex = null;//"m","f"
	private String wtype=null;//"F","C"
	
	public SimpleUserSearch() {
		super();
	}

	public int getMinFans() {
		return minFans;
	}

	public int getMaxFans() {
		return maxFans;
	}

	public long getStarttime() {
		return starttime;
	}

	public long getEndtime() {
		return endtime;
	}

	public int getProvince() {
		return province;
	}

	public int getCity() {
		return city;
	}

	public String getSex() {
		return sex;
	}

	public void setMinFans(int minFans) {
		this.minFans = minFans;
	}

	public void setMaxFans(int maxFans) {
		this.maxFans = maxFans;
	}

	public void setStarttime(long starttime) {
		this.starttime = starttime;
	}

	public void setEndtime(long endtime) {
		this.endtime = endtime;
	}

	public void setProvince(int province) {
		this.province = province;
	}

	public void setCity(int city) {
		this.city = city;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
	public void constructQuery(SolrQuery query) {
		if (this.minFans != NOT_REQUIRED || this.maxFans != NOT_REQUIRED) {
			 String start = "*";
			 String end  = "*";
			 if (this.minFans != NOT_REQUIRED) {
				 start = minFans +"";
			 }
			 
			 if (this.maxFans != NOT_REQUIRED) {
				 end = maxFans +"";
			 }
			 
			 query.addFilterQuery("fans:[" + start + " TO " + end + "]");
		}
		
		if (this.province != NOT_REQUIRED) {
			query.addFilterQuery("prov:" + this.province);
		}
		
		if (this.city != NOT_REQUIRED) {
			query.addFilterQuery("city:" + this.city);
		}
		
		if (sex != null && sex.equals("") == false) {
			query.addFilterQuery("sex:" + this.sex);
		}
		if(wtype!=null && wtype.equals("") == false){
			query.addFilterQuery("wtype:" + this.wtype);
		}
		
		if (starttime != 0L || endtime != 0L) {
			String start="*";
			String end = "*";
			if (starttime != 0L) {
				start = starttime + "";
			}
			
			if (endtime != 0L) {
				end = endtime + ""	;
			}
			
			query.addFilterQuery("wbcreated:[" + start + " TO " + end + "]");
		}
		
		if (this.vtypes != NOT_REQUIRED) {
			/**
			 * 如果就是查普通： addFilterQuery("vtype:[-1 TO 0}");
			 * 如果查普通和达人： addFilterQuery("vtype:[-1 TO 0} || [200  TO * ]")
			 */
			/*String str="";
			1000 str1="[-1 TO 0}";
			100 str2="[200  TO * ]";
			10 str3="[0 TO 1}";
			1 str4="[1 TO 200}";
			query.addFilterQuery("vtype:"+str1+" || "+str2+" || "+str3);
			(int)(n/10000) = 9
			(int)(n/1000)%10 = 8
			(int)(n/100)%10 = 9
			(int)(n/10)%10 = 8
			*/
			String str = "";
			String putong = "[-1 TO 0}";
			String daren = "[200  TO * ]";
			String geren = "[0 TO 1}";
			String qiye = "[1 TO 200}";
			if((int)(this.vtypes /1000) >= 1){
				str += putong+" OR ";
			}
			if((int)(this.vtypes /100)%10 >= 1){
				str += daren+" OR ";
			}
			if((int)(this.vtypes /10)%10 >= 1){
				str += geren+" OR ";
			}
			if(this.vtypes % 10 >= 1){
				str += qiye+" OR ";
			}
			str = str.substring(0,str.length()-3);
			query.addFilterQuery("vtype: (" + str + " )");	 
		}
			
		return;
	}

	public String getWtype() {
		return wtype;
	}

	public void setWtype(String wtype) {
		this.wtype = wtype;
	}

	public int getVtypes() {
		return vtypes;
	}

	public void setVtypes(int vtypes) {
		this.vtypes = vtypes;
	}

}
