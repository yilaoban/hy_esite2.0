package com.huiyee.interact.lottery.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.interact.lottery.dto.LotteryDto;
import com.huiyee.interact.lottery.mgr.ILotteryMgr;
import com.huiyee.interact.lottery.model.LotteryWinner;

public class LotteryWinnerUserAction extends InteractModelAction {
	
	private ILotteryMgr lotteryMgr;
	private LotteryDto dto;
	private String[] lpid;
	private long lid;
	private long start = 0;
	private int size =10;
	private long xcid;
	
	
	public long getXcid()
	{
		return xcid;
	}

	public void setXcid(long xcid)
	{
		this.xcid = xcid;
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public String execute() throws Exception{
		long result =0;
		if(dto!=null&&dto.getLotteryUser()!=null){
			 result=lotteryMgr.saveLotteryWinnerUser(dto.getLotteryUser());
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

	public String winnerlist() throws Exception{
		List<LotteryWinner> list = lotteryMgr.findLotteryWinner(lid, lpid, start, size,xcid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(list));
		out.flush();
		out.close();
		return null;
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

	public long getLid() {
		return lid;
	}

	public void setLid(long lid) {
		this.lid = lid;
	}

	public String[] getLpid() {
		return lpid;
	}

	public void setLpid(String[] lpid) {
		this.lpid = lpid;
	}

}
