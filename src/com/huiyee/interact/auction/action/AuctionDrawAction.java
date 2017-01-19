package com.huiyee.interact.auction.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.dto.HdRsDto;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.HttpRequestDeviceUtils;
import com.huiyee.esite.util.ClientUserIp;
import com.huiyee.interact.auction.mgr.IAuctionMgr;
import com.huiyee.interact.auction.model.Auction;
import com.huiyee.interact.auction.service.IAuctionDrawService;
import com.opensymphony.xwork2.ActionContext;

public class AuctionDrawAction extends InteractModelAction
{
	private long wbuid;
	private long pageid;
	private long auid;
	private String source;
	private int addnum;
	private IAuctionDrawService auctionDrawService;
	private IAuctionMgr auctionMgr;
	private long mid = 10007;
	/**
	 * 
	 */
	private String username;
	private String userphone;
	private String useremail;
	private String userlocation;
	private static final long serialVersionUID = 3887688790629684079L;

	public String execute() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Auction au = auctionMgr.findAuctionById(auid);
		HdRsDto rs = pageCompose.ineractCanRun(au);
		if (rs.getStatus()==10000)
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			String ip = ClientUserIp.getIpAddr(request);
			String userA = request.getHeader("user-agent");
			String userAgent = HttpRequestDeviceUtils.getMediaDevice(userA);
			VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
			
			try {
				rs = auctionDrawService.bidOneAuction(vu, au, addnum, pageid, ip, userAgent, source);
			} catch (Exception e) {
				e.printStackTrace();
				rs.setHydesc("竞拍失败，严重问题！");
				rs.setStatus(-11000);
			}
			
		}

		Gson gson = new Gson();
		out.print(gson.toJson(rs));
		out.flush();
		out.close();
		return null;
	}
	
	
	public String winnerSub()throws Exception{
		long result=0;
		if(userphone!=null||username!=null||useremail!=null||userlocation!=null){
			VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
			 result= auctionMgr.saveWinnerUser(vu,auid,username,userphone,useremail,userlocation);
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		if(result > 0){
			out.print("Y");
		}else{
			out.print("N");			
		}
		out.flush();
		out.close();
		return null;
	}

	public long getWbuid()
	{
		return wbuid;
	}

	public void setWbuid(long wbuid)
	{
		this.wbuid = wbuid;
	}

	public String getSource()
	{
		return source;
	}

	public void setSource(String source)
	{
		this.source = source;
	}

	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	public void setAuid(long auid)
	{
		this.auid = auid;
	}

	public void setAuctionDrawService(IAuctionDrawService auctionDrawService)
	{
		this.auctionDrawService = auctionDrawService;
	}

	public int getAddnum()
	{
		return addnum;
	}

	public void setAddnum(int addnum)
	{
		this.addnum = addnum;
	}

	public long getPageid()
	{
		return pageid;
	}

	public long getAuid()
	{
		return auid;
	}

	public IAuctionDrawService getAuctionDrawService()
	{
		return auctionDrawService;
	}

	public long getMid()
	{
		return mid;
	}

	public void setMid(long mid)
	{
		this.mid = mid;
	}

	public void setAuctionMgr(IAuctionMgr auctionMgr)
	{
		this.auctionMgr = auctionMgr;
	}


	public String getUsername()
	{
		return username;
	}


	public void setUsername(String username)
	{
		this.username = username;
	}


	public String getUserphone()
	{
		return userphone;
	}


	public void setUserphone(String userphone)
	{
		this.userphone = userphone;
	}


	public String getUseremail()
	{
		return useremail;
	}


	public void setUseremail(String useremail)
	{
		this.useremail = useremail;
	}


	public String getUserlocation()
	{
		return userlocation;
	}


	public void setUserlocation(String userlocation)
	{
		this.userlocation = userlocation;
	}

}
