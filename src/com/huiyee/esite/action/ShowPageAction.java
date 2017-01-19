package com.huiyee.esite.action;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.util.ClientUserIp;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

public class ShowPageAction extends AbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5037307729765883699L;
	private long appid = 1;// 申请的app在本地数据库的id
	private long cid;
	private String viewer;
	private long pageid;
	// 暂时不用的属性
	private String url;
	private String key="SNA";
	private long sub_appkey;
	private String tokenString;
	private String ifmID;
	private String iniframe;
	private long v;
	private int hyoauth;
	private long hyuid;
	private static Map<String, String> wbCahe = new HashMap<String, String>();
	
	private String signed_request;

	public void setCid(long cid)
	{
		this.cid = cid;
	}

	@Override
	public String execute() throws Exception{
		if(appid > 3){
			return "forward";
		}
		System.out.println(signed_request);
		HttpServletRequest request = ServletActionContext.getRequest();
		String ip = ClientUserIp.getIpAddr(request);
		String agent = request.getHeader("User-Agent");
		if (agent.contains("Commons-HttpClient")){
			//新浪虫子;不需要记录
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter();
			out.print(-1);
			out.flush();
			out.close();
			return null;
		}
		long siteid = this.pageCompose.findPageIdForPageShow(cid, appid);
		if(siteid == 0){
			return "forward";
		}
		hyuid = ShowPageAction.getUid(viewer, cid);
		try {
			v = Long.parseLong(viewer);//访问用户的wbuid
		} catch (RuntimeException e) {
			v = 0;
		}
		if (hyuid == 0){
			if (v > 0){
				//首次访问
				hyuid = pageCompose.updateUidByViewer(v, siteid);//记录信息，
				//pageCompose.insertVisitLog(siteid, hyuid, ip,agent,key);//访问记录
				ShowPageAction.addWbCahe(viewer+","+cid, hyuid+","+new Date().getTime());//缓存记录
				request.getSession().setAttribute("uid", hyuid);//暂作保留，不建议使用session内的uid
			}else{
				//匿名访问,暂时照原来处理
				Object h = ActionContext.getContext().getSession().get("h");
				if (h == null){
					ActionContext.getContext().getSession().put("h", "h");
					//pageCompose.insertVisitLogAnonymous(siteid, ip,agent,key);
				}
			}
		}
		pageid = pageCompose.findHomePageBySiteid(siteid).getId();
		if( hyuid > 0){
		  hyoauth = pageCompose.composeCheckToken(hyuid, siteid);//检查是否授权
		}
		System.out.println("showpageAction:hyuid:"+hyuid+"----hyoauth:"+hyoauth+"---v:"+v);
		return Action.SUCCESS;
	}

	public static synchronized boolean addWbCahe(String key,String value) {
		String str = wbCahe.get(key);
		if (str == null) {
			wbCahe.put(key, value);
			return true;
		}
		return false;
	}
	
	public static synchronized long getUid(String viewer,long cid){
		String value = wbCahe.get(viewer+","+cid);
		long uid = 0;
		try {
			uid = (value != null)?Long.parseLong(value.split(",")[0]):0;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return uid;
	}

	public static synchronized void removeWbCahe(String key) {
		wbCahe.remove(key);
	}
	
	public long getCid()
	{
		return 1;
	}

	public long getPageid()
	{
		return pageid;
	}

	public void setAppid(long appid)
	{
		this.appid = appid;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public void setKey(String key)
	{
		this.key = key;
	}

	public void setSub_appkey(long sub_appkey)
	{
		this.sub_appkey = sub_appkey;
	}

	public void setTokenString(String tokenString)
	{
		this.tokenString = tokenString;
	}

	public void setIfmID(String ifmID)
	{
		this.ifmID = ifmID;
	}

	public void setIniframe(String iniframe)
	{
		this.iniframe = iniframe;
	}

	public String getViewer() {
		return viewer;
	}

	public void setViewer(String viewer) {
		this.viewer = viewer;
	}

	public long getV()
	{
		return v;
	}

	public int getHyoauth()
	{
		return hyoauth;
	}

	public long getHyuid()
	{
		return hyuid;
	}

	public static Map<String, String> getWbCahe() {
		return wbCahe;
	}

	public String getSigned_request() {
		return signed_request;
	}

	public void setSigned_request(String signed_request) {
		this.signed_request = signed_request;
	}
}
