package com.huiyee.interact.spread.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.dto.HdRsDto;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.spread.mgr.ISpreadManager;
import com.huiyee.interact.spread.model.SpreadModel;
import com.huiyee.interact.spread.util.HttpRequestDeviceUtils;
import com.huiyee.esite.util.ClientUserIp;

public class SpreadSubmitAction  extends InteractModelAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7682088271654052113L;
	private long spreadid;
	private long wbuid;
	private long cid;
	private long pageid;
	private String source;
	private String content;
	private String pic;
	private long wbid;
	private long mid = 10010;
	private long oid;
	private String nicknames;
	private int type;
	private long featureid=IInteractConstants.INTERACT_SPREAD;
	
	private ISpreadManager spreadManager;
	
	public long getSpreadid() {
		return spreadid;
	}

	public void setSpreadid(long spreadid) {
		this.spreadid = spreadid;
	}

	public long getWbuid() {
		return wbuid;
	}

	public void setWbuid(long wbuid) {
		this.wbuid = wbuid;
	}

	public long getCid() {
		return cid;
	}

	public void setCid(long cid) {
		this.cid = cid;
	}

	@Override
	public String execute() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		SpreadModel spread=spreadManager.findSpreadByIdAndPageId(spreadid,pageid);
		HdRsDto rs = pageCompose.ineractCanRun(spread);
		if (rs.getStatus()==10000) {
			HttpServletRequest request=ServletActionContext.getRequest();
			String ip = ClientUserIp.getIpAddr(request);
			String userAgent = request.getHeader("user-agent");
			VisitUser u = (VisitUser) ServletActionContext.getRequest().getSession().getAttribute("visitUser");
			try {
				rs = spreadManager.saveSpreadRecord(pageid, spreadid, u,content,pic,wbid,ip, HttpRequestDeviceUtils.getMediaDevice(userAgent), source,oid,nicknames,spread);
			} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(-11000);
			rs.setHydesc("´«²¥Ê§°Ü£¬ÑÏÖØ´íÎó£¡");
			}

		} 
		out.print(new Gson().toJson(rs));
		out.flush();
		out.close();
		return null;
	}

	public String pageSub() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		//pageCompose.updateWXShareJoined();
		out.print("Y");
		out.flush();
		out.close();
		return null;
	}
	
	
	public void setSpreadManager(ISpreadManager spreadManager) {
		this.spreadManager = spreadManager;
	}

	public long getPageid() {
		return pageid;
	}

	public void setPageid(long pageid) {
		this.pageid = pageid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public long getWbid() {
		return wbid;
	}

	public void setWbid(long wbid) {
		this.wbid = wbid;
	}

	public long getMid()
	{
		return mid;
	}

	public void setMid(long mid)
	{
		this.mid = mid;
	}

	public long getOid() {
		return oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getNicknames() {
		return nicknames;
	}

	public void setNicknames(String nicknames) {
		this.nicknames = nicknames;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
