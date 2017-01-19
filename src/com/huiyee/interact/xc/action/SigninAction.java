
package com.huiyee.interact.xc.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.mgr.IGzEventMgr;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.model.WxGzEvent;
import com.huiyee.esite.model.WxUser;
import com.huiyee.esite.util.ClientUserIp;
import com.huiyee.esite.util.HttpRequestDeviceUtils;
import com.huiyee.interact.xc.dto.SignRsDto;
import com.huiyee.interact.xc.dto.SigninDto;
import com.huiyee.interact.xc.mgr.ISigninMgr;
import com.huiyee.interact.xc.mgr.IXcMgr;
import com.huiyee.interact.xc.model.Xc;

public class SigninAction extends InteractModelAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ISigninMgr signinMgr;
	private IGzEventMgr gzEventMgr;
	private IXcMgr xcMgr;
	private String result;
	private long xcid;
	private long start = 0;
	private int size = 10;
	private long uid;
	private int type;

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public void setSigninMgr(ISigninMgr signinMgr)
	{
		this.signinMgr = signinMgr;
	}

	public String updateCheckin() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		VisitUser vu = (VisitUser) ServletActionContext.getRequest().getSession().getAttribute("visitUser");
		WxUser wu = vu.getWxUser();
		if (vu == null || vu.getCd() != 1|| wu == null)
		{
			out.print("-11000");
			out.flush();
			out.close();
			return null;
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		String ip = ClientUserIp.getIpAddr(request);
		String terminal = HttpRequestDeviceUtils.getMediaDevice(request.getHeader("user-agent"));
		if (vu == null)
		{
			return null;
		}

		uid = vu.getUid();
		type = vu.getCd();
		Xc xc = xcMgr.getXcByIdMc(xcid);
		String url=null;
		long msgid=0;
		if(xc.getCheckinconfig()!=null&&xc.getCheckinconfig().getGzid()>0){
			WxGzEvent g=gzEventMgr.findGzEvent(xc.getCheckinconfig().getGzid());
			if(g!=null){
				url=g.getLink();
			   msgid=g.getMsg().getId();
			}
		}
		if (xc == null || xc.getNeedcheckin() == null)
		{
			out.print("-10000");
			out.flush();
			out.close();
			return null;
		}
		if (xc.getNeedcheckin().equalsIgnoreCase("N"))
		{
			out.print("-10001");
			out.flush();
			out.close();
			return null;
		}
		int status = 1;
		if (xc.getCheckinconfig().getAtype().equalsIgnoreCase("Y"))
		{
			int len = signinMgr.findisInvite(xcid, uid, type);
			if (len == 0)
			{
				status = 0;
			}
		}
		if (xc.getCheckinconfig().getAtype().equalsIgnoreCase("G"))
		{
			if (wu.getSubscribe() == 0)
			{
				// 不是关注者
				status = -1;
			}
		}
		// status 1:签到成功 0:限定邀请人签到失败 -1:限定关注人签到失败
		int rs =0;//1:插入成功 2:已有成功签到记录
        if(status==1){
			rs= signinMgr.saveUser(uid, type, vu.getPageid(), vu.getSource(), xcid, ip, terminal);
		}else{
			rs= signinMgr.saveUserFail(uid, type, vu.getPageid(), vu, xcid, ip, terminal,status,msgid);
		}
		SignRsDto dto = new SignRsDto();
		if(rs==1){
			dto.setStatus(status);
			String str="";
			switch (status)
			{
				case -1:str="签到失败!仅关注人可签到.";	break;
				case 0:str="签到失败!仅邀请人可签到.";	break;
				case 1:str="签到成功!";break;
			}
			dto.setHydesc(str);
			dto.setUrl(url);
			
		}else if(rs==2){
			dto.setStatus(1);
			dto.setHydesc("签到成功!");
		}else{
			dto.setStatus(-2);
			dto.setHydesc("签到失败!插入数据异常.");
		}
		out.print(new Gson().toJson(dto));
		out.flush();
		out.close();
		return null;
	}

	public String signinlist() throws Exception
	{
		SigninDto dto = new SigninDto();
		dto.setList(signinMgr.findXcCheckinRecord(xcid, start, size));
		dto.setCount(signinMgr.findSignerTotal(xcid));
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.write(gson.toJson(dto));
		out.flush();
		out.close();
		return null;
	}

	public String getResult()
	{
		return result;
	}

	public void setResult(String result)
	{
		this.result = result;
	}

	public long getXcid()
	{
		return xcid;
	}

	public void setXcid(long xcid)
	{
		this.xcid = xcid;
	}

	public int getSize()
	{
		return size;
	}

	public void setSize(int size)
	{
		this.size = size;
	}

	public long getStart()
	{
		return start;
	}

	public void setStart(long start)
	{
		this.start = start;
	}

	public long getUid()
	{
		return uid;
	}

	public void setUid(long uid)
	{
		this.uid = uid;
	}

	public void setXcMgr(IXcMgr xcMgr)
	{
		this.xcMgr = xcMgr;
	}

	
	public void setGzEventMgr(IGzEventMgr gzEventMgr)
	{
		this.gzEventMgr = gzEventMgr;
	}



}
