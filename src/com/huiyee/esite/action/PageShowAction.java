package com.huiyee.esite.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.compose.IFeatureCompose;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.model.MyTempalte;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.util.HttpRequestDeviceUtils;
import com.huiyee.esite.util.ToolUtils;

public class PageShowAction extends AbstractAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6677318762427944074L;
	private IFeatureCompose featureCompose;
	private long pageid;
	private long featureid;
	private long fid;
	private Map<String, Object> result;
	private String jspname;
	private String url;
	private String type = "N";
	private String userAgent;
	private long v;
	private int hyoauth;
	private long hyuid;
	private String style;

	public long getFeatureid() {
		return featureid;
	}

	public void setFeatureid(long featureid) {
		this.featureid = featureid;
	}

	public long getFid() {
		return fid;
	}

	public void setFid(long fid) {
		this.fid = fid;
	}
	
	//查看
	public String show() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String userA = request.getHeader("user-agent");
		userAgent = HttpRequestDeviceUtils.getMediaDevice(userA);
		url = ToolUtils.encodeURLString(ServletActionContext.getRequest().getRequestURI()+"?"+ServletActionContext.getRequest().getQueryString());
		type = (String) ServletActionContext.getRequest().getSession().getAttribute("type");//这里的type是用作新浪授权，授权成功为S，默认N
		ServletActionContext.getRequest().getSession().removeAttribute("type");
		result = pageCompose.show(pageid);
		Page page = (Page)result.get("page");
		jspname = page.getJspname();			
		System.out.println("pageShowAction:hyuid:"+hyuid+"----hyoauth:"+hyoauth+"---wbuid:"+v);
		return SUCCESS;
	}
	
	//预览
	public String preview() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String userA = request.getHeader("user-agent");
		userAgent = HttpRequestDeviceUtils.getMediaDevice(userA);
		url = ServletActionContext.getRequest().getRequestURI()+"?"+ServletActionContext.getRequest().getQueryString();
		result = featureCompose.show(pageid,"C",null);
		Page page = (Page)result.get("page");
		jspname = page.getJspname();			
		return SUCCESS;
	}

	public long getPageid() {
		return pageid;
	}

	public void setPageid(long pageid) {
		this.pageid = pageid;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public String getJspname() {
		return jspname;
	}

	public void setFeatureCompose(IFeatureCompose featureCompose) {
		this.featureCompose = featureCompose;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public int getHyoauth()
	{
		return hyoauth;
	}

	public void setHyoauth(int hyoauth)
	{
		this.hyoauth = hyoauth;
	}

	public long getV()
	{
		return v;
	}

	public void setV(long v)
	{
		this.v = v;
	}

	public long getHyuid()
	{
		return hyuid;
	}

	public void setHyuid(long hyuid)
	{
		this.hyuid = hyuid;
	}

	public String getStyle() {
		return style;
	}

}
