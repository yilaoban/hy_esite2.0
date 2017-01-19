package com.huiyee.interact.lottery.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.interact.lottery.dto.LotteryDto;
import com.huiyee.interact.lottery.mgr.ILotteryMgr;

public class LotteryInformationAction extends InteractModelAction {

	private ILotteryMgr lotteryMgr;
	private LotteryDto dto;
	private long lid;
	private int type;
	private long pageid;
	
	@Override
	public String execute() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		if (HyConfig.isRun()) {// ºóÌ¨
			return null;	
		}else{
			VisitUser u = (VisitUser) ServletActionContext.getRequest().getSession().getAttribute("visitUser");
			long wbuid = u.getUid();
			type=u.getCd();
			dto = (LotteryDto) lotteryMgr.findPrizeWinnnerInformation(lid, wbuid,type,pageid);
			PrintWriter out = response.getWriter();
			Gson gson = new Gson();
			out.print(gson.toJson(dto.getLotteryPrizeList()));
			out.flush();
			out.close();
			return null;
		}
	}
	
	public ILotteryMgr getLotteryMgr()
	{
		return lotteryMgr;
	}
	public void setLotteryMgr(ILotteryMgr lotteryMgr)
	{
		this.lotteryMgr = lotteryMgr;
	}
	public LotteryDto getDto()
	{
		return dto;
	}
	public void setDto(LotteryDto dto)
	{
		this.dto = dto;
	}

	public long getLid()
	{
		return lid;
	}

	public void setLid(long lid)
	{
		this.lid = lid;
	}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public long getPageid()
	{
		return pageid;
	}

	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}
	
}
