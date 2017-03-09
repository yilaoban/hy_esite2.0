package com.huiyee.interact.lottery.action;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.google.gson.Gson;
import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.dto.HdRsDto;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.HttpRequestDeviceUtils;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.esite.util.YinPiaoHttp;
import com.huiyee.esite.util.ClientUserIp;
import com.huiyee.interact.lottery.dto.LotteryJoin;
import com.huiyee.interact.lottery.mgr.ILotteryMgr;
import com.huiyee.interact.lottery.model.Lottery;
import com.huiyee.interact.lottery.model.LotteryUserRecord;
import com.huiyee.interact.lottery.service.ILotteryDrawService;
import com.opensymphony.xwork2.ActionContext;

public class LotteryDrawAction extends InteractModelAction
{
	private long wbuid;
	private long lid;
	private String source;
	private long pageid;
	private long relationid;
	/**
	 * 
	 */
	private static final long serialVersionUID = 3887688790629684079L;

	private ILotteryDrawService lotteryDrawService;
	private ILotteryMgr lotteryMgr;
	private String st;
	
	public void setSt(String st)
	{
		this.st = st;
	}

	public String execute() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Lottery lo = lotteryMgr.findLotteryById(lid);
		HdRsDto rs = pageCompose.ineractCanRun(lo);
		if (rs.getStatus()==10000)
		{
			lo.setFeatureid(findFeidByLtype(lo.getType()));
			String ip = ClientUserIp.getIpAddr(request);
			String userA = request.getHeader("user-agent");
			String userAgent = HttpRequestDeviceUtils.getMediaDevice(userA);
			VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
			if (vu != null && lo != null)
			{
				try {
					String	url = HyConfig.getPageyuming(this.getOwner()) + this.getOnameUrl();
					rs = lotteryDrawService.drawOneLottery(vu, lo, pageid, ip, userAgent, vu.getSource(),relationid,url,true);
				} catch (Exception e) {
					e.printStackTrace();
					rs.setStatus(-11000);
					rs.setHydesc("�齱ʧ�ܣ��������⣡");
				}
			}
			pageCompose.canRunLog(lo.getId(), lo.getType().toLowerCase(), request);
		}
		Gson gson = new Gson();
		out.print(gson.toJson(rs));
		out.flush();
		out.close();
		return null;
	}

	public String getJoinDetail() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		LotteryJoin lj = null;
		if (vu != null && lid != 0)
		{
			lj = lotteryMgr.findLotteryJoinDetail(lid, vu,source);
		}
		Gson gson = new Gson();
		out.print(gson.toJson(lj));
		out.flush();
		out.close();
		return null;
	}
	/**
	 * ���ʣ��齱������ͬʱ�����ж��Ƿ���Գ齱
	 * @return
	 * @throws Exception
	 */
	public String remainDraw() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Lottery lo = lotteryMgr.findLotteryById(lid);
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		HdRsDto rs = null;
		if (vu != null && lo != null)
		{
			rs = lotteryDrawService.drawOneLottery(vu, lo, pageid, null, null, vu.getSource(),relationid,null,false);
			Gson gson = new Gson();
			out.print(gson.toJson(rs));
			out.flush();
			out.close();
			return null;
		}else
		{
			rs=new HdRsDto();
			rs.setStatus(-11000);
			rs.setHydesc("�û����߳齱�����ڣ�");
			Gson gson = new Gson();
			out.print(gson.toJson(rs));
			out.flush();
			out.close();
			return null;
		}
	}
	
	
	/**
	 * �н���¼
	 * @return
	 * @throws Exception
	 */
	public String userWinList() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		List<LotteryUserRecord> record = null;
		if (vu != null && lid != 0)
		{
			record = lotteryMgr.findRecordByUser(lid, vu);
		}
		Gson gson = new Gson();
		out.print(gson.toJson(record));
		out.flush();
		out.close();
		return null;
	}
	/**
	 * ������
	 * @return
	 * @throws Exception
	 */
	public String kj() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(YinPiaoHttp.kj(st));
		out.flush();
		out.close();
		return null;
	}

	/**
	 * ����type����ȡfeatureid
	 * 
	 * @param type
	 * @return
	 */
	private long findFeidByLtype(String type)
	{
		if (type.equals("Z"))
		{
			return IInteractConstants.INTERACT_ZLOTTERY;
		}
		else if (type.equals("G"))
		{
			return IInteractConstants.INTERACT_GLOTTERY;
		}
		else
		{
			return IInteractConstants.INTERACT_LLOTTERY;
		}
	}

	public void setLotteryDrawService(ILotteryDrawService lotteryDrawService)
	{
		this.lotteryDrawService = lotteryDrawService;
	}

	public long getWbuid()
	{
		return wbuid;
	}

	public void setWbuid(long wbuid)
	{
		this.wbuid = wbuid;
	}

	public long getLid()
	{
		return lid;
	}

	public void setLid(long lid)
	{
		this.lid = lid;
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

	public void setLotteryMgr(ILotteryMgr lotteryMgr)
	{
		this.lotteryMgr = lotteryMgr;
	}

	public void setRelationid(long relationid)
	{
		this.relationid = relationid;
	}
}
