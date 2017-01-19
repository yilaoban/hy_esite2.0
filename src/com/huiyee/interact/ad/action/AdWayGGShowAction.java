package com.huiyee.interact.ad.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.interact.ad.mgr.IAdWagGGMgr;
import com.huiyee.interact.ad.model.AdWay;
import com.huiyee.interact.ad.model.Adgg;


public class AdWayGGShowAction extends AbstractAction
{
	/**
	 * ǰ̨չʾ
	 */
	private static final long serialVersionUID = 4404604302028426384L;
	private IAdWagGGMgr adWagGGMgr;
	private Adgg adgg;
	
	public void setAdWagGGMgr(IAdWagGGMgr adWagGGMgr)
	{
		this.adWagGGMgr = adWagGGMgr;
	}

	@Override
	public String execute() throws Exception
	{
		if(HyConfig.isRun()){
			return null;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		VisitUser vu = (VisitUser) ServletActionContext.getRequest().getSession().getAttribute("visitUser");
		AdWay way = adWagGGMgr.findAdggByWayid(vu);
		out.print(gson.toJson(way));
		out.flush();
		out.close();
		return null;
	}

	
	public Adgg getAdgg()
	{
		return adgg;
	}

	
	public void setAdgg(Adgg adgg)
	{
		this.adgg = adgg;
	}

}
