package com.huiyee.interact.xc.action;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dto.DaohangDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.service.FileUploadService;
import com.huiyee.esite.util.HttpTookit;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.interact.xc.dto.XcBigScreenDto;
import com.huiyee.interact.xc.mgr.IXcBigScreenMgr;
import com.opensymphony.xwork2.ActionContext;

public class XcBigScreenAction extends InteractModelAction {
	
	private static final long serialVersionUID = 1313783719188801865L;
	private long dpmid;
	private long xcid;
	private XcBigScreenDto dto;
	private IXcBigScreenMgr xcBigScreenMgr;
	private String name;
	private long pageid;
	private long mid = 10014;
	private long siteid;
	private File img;
	private String imgFileName;
	private String imgContentType;
	private String imgurl;
	private DaohangDto dh;
	
	public String editXcBigScreen() throws Exception {
		dto = (XcBigScreenDto) xcBigScreenMgr.findXcBigScreenList(xcid);
		dh=pageCompose.composeDhByXcid(xcid);
		try{
			for(String url:HyConfig.getClearUrl()){
			String result = HttpTookit.doGet(url.replace("cc.action", "xcSwitchAction.action"), "xcid="+xcid, "utf-8", true);
			pageid = Long.valueOf(result.trim());
			dto.setPageid(pageid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String addXcBigScreen() throws Exception {
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		dto = (XcBigScreenDto) xcBigScreenMgr.findSiteList(ownerid);
		return SUCCESS;
	}

	/**
	 * ÐÂÔö´óÆÁÄ»
	 * @return
	 * @throws Exception
	 */
	public String saveXcBigScreen() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		String imgfilePath = FileUploadService.getFilePath(IPageConstants.TYPE_INTERACT, account.getOwner().getId(), IPageConstants.CONTENT_PICTURE_FILEFATH);
		// Ð¡Í¼
		if (img != null) {
			String simgfileName = FileUploadService.createFileName(imgFileName);
			imgurl = FileUploadService.saveFile(img, imgfilePath, simgfileName);
		}
		
		long result = xcBigScreenMgr.saveXcBigScreen(xcid,name,pageid,imgurl);
		return SUCCESS;
	}
	
	/**
	 * ÇÐ»»ÆÁÄ»
	 * @return
	 * @throws Exception
	 */
	public String switchBigScreen() throws Exception {
		String result="";
		for(String url:HyConfig.getClearUrl()){
			result= HttpTookit.doGet(url.replace("cc.action", "xcCacheAction.action"), "pageid="+pageid+"&xcid="+xcid, "utf-8", true); 
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		return null;
	}
	
	public String findPageList() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setContentType("text/html; charset=utf-8");
		dto = (XcBigScreenDto) xcBigScreenMgr.findPageList(siteid);
		JSONArray ja = JSONArray.fromObject(dto.getPageList());
		PrintWriter pw = response.getWriter();
		pw.print(ja.toString());
		pw.flush();
		pw.close();
		return null;
	}
	
	public String delXcBigScreen() throws Exception{
		int len = xcBigScreenMgr.delXcBigScreen(dpmid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(len);
		out.flush();
		out.close();
		return null;
	}
	
	public String ajaxUpLoad() throws Exception{
		int len = xcBigScreenMgr.updateImgurlByDpmid(pageid,imgurl);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(len);
		out.flush();
		out.close();
		return null;
	} 
	
	public long getXcid()
	{
		return xcid;
	}

	public void setXcid(long xcid)
	{
		this.xcid = xcid;
	}

	public XcBigScreenDto getDto()
	{
		return dto;
	}

	public void setDto(XcBigScreenDto dto)
	{
		this.dto = dto;
	}

	public IXcBigScreenMgr getXcBigScreenMgr()
	{
		return xcBigScreenMgr;
	}

	public void setXcBigScreenMgr(IXcBigScreenMgr xcBigScreenMgr)
	{
		this.xcBigScreenMgr = xcBigScreenMgr;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public long getPageid()
	{
		return pageid;
	}

	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	public long getMid()
	{
		return mid;
	}

	public void setMid(long mid)
	{
		this.mid = mid;
	}

	public long getSiteid()
	{
		return siteid;
	}

	public void setSiteid(long siteid)
	{
		this.siteid = siteid;
	}

	public long getDpmid()
	{
		return dpmid;
	}

	public void setDpmid(long dpmid)
	{
		this.dpmid = dpmid;
	}

	public File getImg()
	{
		return img;
	}

	public void setImg(File img)
	{
		this.img = img;
	}

	public String getImgFileName()
	{
		return imgFileName;
	}

	public void setImgFileName(String imgFileName)
	{
		this.imgFileName = imgFileName;
	}

	public String getImgContentType()
	{
		return imgContentType;
	}

	public void setImgContentType(String imgContentType)
	{
		this.imgContentType = imgContentType;
	}

	public String getImgurl()
	{
		return imgurl;
	}

	public void setImgurl(String imgurl)
	{
		this.imgurl = imgurl;
	}
	
	public String getSitetype(){
		return "´óÆÁÄ»ÅäÖÃ";
	}
	
	public DaohangDto getDh()
	{
		return dh;
	}

	
	public void setDh(DaohangDto dh)
	{
		this.dh = dh;
	}
	
}
