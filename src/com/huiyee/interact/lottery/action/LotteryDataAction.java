package com.huiyee.interact.lottery.action;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.google.gson.Gson;
import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.solr.HylogSolrServer;
import com.huiyee.interact.lottery.dto.LRSDto;
import com.huiyee.interact.lottery.dto.LotteryDataDto;
import com.huiyee.interact.lottery.mgr.ILotteryPrizeMgr;
import com.huiyee.interact.lottery.mgr.ILotteryUserMgr;
import com.huiyee.interact.lottery.mgr.ILotteryUserRecordMgr;
import com.huiyee.interact.lottery.mgr.IWxHbSendMgr;
import com.huiyee.interact.lottery.model.LotteryWinnerDetail;
import com.opensymphony.xwork2.ActionContext;

public class LotteryDataAction extends InteractModelAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nickName;
	private long lid;
	private String type;
	private long wbuid;
	private long lpid;
	private int pageId = 1;
	private LotteryDataDto dto;
	private ILotteryUserMgr lotteryUserMgr;
	private ILotteryPrizeMgr lotteryPrizeMgr;
	private ILotteryUserRecordMgr lotteryUserRecordMgr;
	private IWxHbSendMgr wxHbSendMgr;
	private String lpname;
	private int media;
	private long mid;
	private Integer errcode;
	private List<Long> rids;
	private long maxRecordid;
	private String processstatus;
	private HylogSolrServer hylogSolrServer;
	private int gz_i;
	private List<List<Object>> areaList;
	
	public void setHylogSolrServer(HylogSolrServer hylogSolrServer)
	{
		this.hylogSolrServer = hylogSolrServer;
	}

	/**
	 * wb数据
	 */
	@Override
	public String execute() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		dto = (LotteryDataDto) lotteryUserMgr.findLotteryData(pageId, nickName, lid, ownerid, 0);
		media = 0;
		setMidByType(type);
		return SUCCESS;
	}

	public String wxList() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		dto = (LotteryDataDto) lotteryUserMgr.findLotteryData(pageId, nickName, lid, ownerid, media);
		setMidByType(type);
		return SUCCESS;
	}
	
	public String lotteryMoveToGroup() throws Exception{
		if(type != null){
			areaList = hylogSolrServer.findHudongUserArea("wx", "h", type.toLowerCase(), lid, gz_i);
//			areaList = hylogSolrServer.findHudongUserArea("wx", "h", type.toLowerCase(), 325, gz_i);
		}
		return SUCCESS;
	}

	public String userDetail() throws Exception
	{
		dto = (LotteryDataDto) lotteryUserRecordMgr.findRecord(lid, wbuid, pageId, type,media);
		return SUCCESS;
	}

	public String winList() throws Exception
	{
		dto = (LotteryDataDto) lotteryPrizeMgr.findLotteryPrize(lid, pageId);
		return SUCCESS;
	}

	public String showWinDetail() throws Exception
	{
		dto = (LotteryDataDto) lotteryUserRecordMgr.findRecordDetail(lpid, nickName, pageId);
		return SUCCESS;
	}
	
	public String showLotteryWinner() throws Exception
	{
		dto = (LotteryDataDto) lotteryUserRecordMgr.findLotteryUserRecord(lpid, processstatus, pageId);
		return SUCCESS;
	}
	
	public String exportWinner() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		List<LotteryWinnerDetail> exportlist = lotteryUserRecordMgr.exportWb(lpid);
		List<LotteryWinnerDetail> exportlist2 = lotteryUserRecordMgr.exportWx(lpid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/csv;charset=GBK");
		response.setHeader("Content-Disposition", "attachment;filename=" + encodingFileName(lpname) + ".csv");
		PrintWriter out = response.getWriter();
		if (exportlist != null && exportlist.size() != 0)
		{
			out.println("wbuid,微博昵称,中奖时间,姓名,手机,邮箱,地址");
			for (LotteryWinnerDetail str : exportlist)
			{
				out.println(str.toString());
			}
		}
		if (exportlist2 != null && exportlist2.size() != 0)
		{
			out.println("微信ID,微信昵称,中奖时间,姓名,手机,邮箱,地址");
			for (LotteryWinnerDetail str : exportlist2)
			{
				out.println(str.toString());
			}
		}
		else
		{
			out.print("无相关信息");
		}
		return null;
	}

	public String lotterySend() throws Exception{
		dto = (LotteryDataDto)wxHbSendMgr.findWxHbSendList(lpid,errcode,pageId);
		return SUCCESS;
	}
	
	public String resendCoupon() throws Exception {
		int result = 0;
		for (Long rid : rids) {
			wxHbSendMgr.addToResend(rid);
			result += wxHbSendMgr.updateSent(rid, "R");
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		return null;
	}
	
	public String lotteryRecordShow() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		LRSDto dto=(LRSDto)lotteryUserRecordMgr.findRecord(lid, maxRecordid);
		out.print(new Gson().toJson(dto));
		out.flush();
		out.close();
		return null;
	}
	
	public String dateClean() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		int rs = 0;
		if (account != null && lid > 0)
		{
			rs = lotteryUserRecordMgr.updateLotteryClean(lid,account.getOwner().getId());
		}
		out.print(rs);
		out.flush();
		out.close();
		return null;
	}
	
	private String encodingFileName(String name)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String time = sdf.format(new Date());
		String result = name + time;
		try
		{
			return new String(result.getBytes("gb2312"), "ISO-8859-1");
		}
		catch (UnsupportedEncodingException e)
		{
			return time;
		}
	}
	
	private void setMidByType(String type)
	{
		if ("Z".equals(type))
		{
			mid = 10003;
		}
		else if ("L".equals(type))
		{
			mid = 10004;
		}
		else if ("G".equals(type))
		{
			mid = 10005;
		}
	}
	
	public String getNickName()
	{
		return nickName;
	}

	public void setNickName(String nickName)
	{
		if(nickName!=null){
			this.nickName = nickName.trim();
		}
		this.nickName = nickName;
	}

	public int getPageId()
	{
		return pageId;
	}

	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	public LotteryDataDto getDto()
	{
		return dto;
	}

	public void setDto(LotteryDataDto dto)
	{
		this.dto = dto;
	}

	public void setLotteryUserMgr(ILotteryUserMgr lotteryUserMgr)
	{
		this.lotteryUserMgr = lotteryUserMgr;
	}

	public long getLid()
	{
		return lid;
	}

	public void setLid(long lid)
	{
		this.lid = lid;
	}

	public void setLotteryPrizeMgr(ILotteryPrizeMgr lotteryPrizeMgr)
	{
		this.lotteryPrizeMgr = lotteryPrizeMgr;
	}

	public void setLotteryUserRecordMgr(ILotteryUserRecordMgr lotteryUserRecordMgr)
	{
		this.lotteryUserRecordMgr = lotteryUserRecordMgr;
	}

	public long getWbuid()
	{
		return wbuid;
	}

	public void setWbuid(long wbuid)
	{
		this.wbuid = wbuid;
	}

	public long getLpid()
	{
		return lpid;
	}

	public void setLpid(long lpid)
	{
		this.lpid = lpid;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getLpname()
	{
		return lpname;
	}

	public void setLpname(String lpname)
	{
		this.lpname = lpname;
	}

	public int getMedia()
	{
		return media;
	}

	public void setMedia(int media)
	{
		this.media = media;
	}

	public long getMid()
	{
		return mid;
	}

	public void setMid(long mid)
	{
		this.mid = mid;
	}

	
	public void setWxHbSendMgr(IWxHbSendMgr wxHbSendMgr)
	{
		this.wxHbSendMgr = wxHbSendMgr;
	}

	
	public Integer getErrcode()
	{
		return errcode;
	}

	
	public void setErrcode(Integer errcode)
	{
		this.errcode = errcode;
	}

	
	public List<Long> getRids()
	{
		return rids;
	}

	
	public void setRids(List<Long> rids)
	{
		this.rids = rids;
	}

	
	public void setMaxRecordid(long maxRecordid)
	{
		this.maxRecordid = maxRecordid;
	}

	
	public String getProcessstatus()
	{
		return processstatus;
	}

	
	public void setProcessstatus(String processstatus)
	{
		this.processstatus = processstatus;
	}
	
	public int getGz_i()
	{
		return gz_i;
	}

	
	public void setGz_i(int gz_i)
	{
		this.gz_i = gz_i;
	}

	
	public List<List<Object>> getAreaList()
	{
		return areaList;
	}

	
	public void setAreaList(List<List<Object>> areaList)
	{
		this.areaList = areaList;
	}

}
