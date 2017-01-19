package com.huiyee.tfmodel;

import net.sf.json.JSONObject;

public class HyWbSource implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
    private String url;               //来源连接
    private String relationShip;      
    private String name;              //来源文案名称
    
    public HyWbSource() {
    	super();
    	
    }
    
	public HyWbSource(String jsonstr) {
		JSONObject json = JSONObject.fromObject(jsonstr);
		this.url = HyJsonUtils.pasString(json, "url");
		this.relationShip = HyJsonUtils.pasString(json, "relationship");
		this.name = HyJsonUtils.pasString(json, "name");
	}

	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getRelationship() {
		return relationShip;
	}


	public void setRelationship(String relationShip) {
		this.relationShip = relationShip;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

    
	@Override
	public String toString() {
		return "Source [url=" + url + ", relationShip=" + relationShip
				+ ", name=" + name + "]";
	}

}
