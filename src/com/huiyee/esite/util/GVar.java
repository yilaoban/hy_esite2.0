package com.huiyee.esite.util;

public class GVar {
	private String var;   //hyvar = ""
	private String desc;
	private String cnt;
	private String display;
	
	public GVar(String var, String cnt,String desc,String display) {
		super();
		this.var = var;
		this.cnt = cnt;
		this.desc = desc;
		this.display = display;
	}

	public String getVar() {
		return var;
	}

	public String getDesc() {
		return desc;
	}

	public String getCnt() {
		return cnt.replaceAll("\n", "").replaceAll("\t", "");
	}

	public String getDisplay() {
		return display;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setCnt(String cnt) {
		this.cnt = cnt;
	}

	public void setDisplay(String display) {
		this.display = display;
	}
	
	public String getType() {
		
		return GProcessHtml.getType(this.getVar());
		
	}

	public String toKJson() {
		
		String x = "{HYtypeHY:HY"+this.getType() + "HY,HYmappingHY:HY"+getDisplay()+"HY,HYvalueHY:"+JSONObject.quote(this.getCnt())+",HYdescHY:HY"+getDesc()+"HY}";
		
		return x.replaceAll("HY", "\"");
		
	}
	
	public String toCJson() {
		
		String x = "HY"+this.getVar() + "HY:"+JSONObject.quote(getCnt());
		
		return x.replaceAll("HY", "\"");
		
	}

}
