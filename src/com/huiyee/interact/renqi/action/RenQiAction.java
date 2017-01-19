package com.huiyee.interact.renqi.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.model.Account;
import com.huiyee.interact.renqi.dto.RqDto;
import com.huiyee.interact.renqi.mgr.IRenQiMgr;
import com.huiyee.interact.renqi.model.RenQi;
import com.opensymphony.xwork2.ActionContext;

public class RenQiAction extends InteractModelAction
{
	private IRenQiMgr renQiMgr;
	private RqDto dto;
	private int pageId = 1;
	private long mid=10012;
	private long id;
	private String title;
	private String startTime;
	private String endTime;
	private String startNote;
	private String endNote;
	private long lid;
	private String maxlu;
	private String cnum;
	private int utype;
	private String content;
	private String link;
	private String wxtitle;
	private String wxdesc;
	private String minjf;
	private String maxjf;
	private RenQi renqi;

	@Override
	public String execute() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		dto = (RqDto) renQiMgr.findOwnerRenqi(ownerid, pageId);
		return SUCCESS;
	}

	public String add() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		dto = (RqDto) renQiMgr.addPre(ownerid);
		return SUCCESS;
	}

	public String addSub() throws Exception
	{
		addCheck();
		if (this.getFieldErrors().size() > 0)
		{
			return "fail";
		}
		else
		{
			RenQi rq = new RenQi();
			Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
			long ownerid = account.getOwner().getId();
			rq.setTitle(title);
			SimpleDateFormat dateFormatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			rq.setStarttime(startTime);
			rq.setEndtime(startTime);
			rq.setStartnote(startNote);
			rq.setEndnote(endNote);
			rq.setLotteryid(lid);
			rq.setLink(link);
			rq.setMaxlu(StringUtils.isNotEmpty(maxlu)?Integer.parseInt(maxlu):0);
			rq.setCnum(StringUtils.isNotEmpty(cnum)?Integer.parseInt(cnum):0);
			rq.setUtype(utype);
			rq.setContent(content);
			rq.setLink(link);
			rq.setWxtitle(wxtitle);
			rq.setWxdesc(wxdesc);
			rq.setMinjf(StringUtils.isNotEmpty(minjf)?Integer.parseInt(minjf):0);
			rq.setMaxjf(StringUtils.isNotEmpty(maxjf)?Integer.parseInt(maxjf):0);
			long id = renQiMgr.saveRenQi(rq, ownerid);
			return SUCCESS;
		}
	}

	public String edit() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		dto = (RqDto) renQiMgr.editPre(ownerid, id);
		return SUCCESS;
	}

	public String editSub() throws Exception
	{
		addCheck();
		if (this.getFieldErrors().size() > 0)
		{
			this.addFieldError("error", "123123");
			return "fail";
		}
		else
		{
			RenQi rq = new RenQi();
			Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
			long ownerid = account.getOwner().getId();
			rq.setTitle(title);
			SimpleDateFormat dateFormatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			rq.setStarttime(startTime);
			rq.setEndtime(endTime);
			rq.setStartnote(startNote);
			rq.setEndnote(endNote);
			rq.setLotteryid(lid);
			rq.setLink(link);
			rq.setMaxlu(StringUtils.isNotEmpty(maxlu)?Integer.parseInt(maxlu):0);
			rq.setCnum(StringUtils.isNotEmpty(cnum)?Integer.parseInt(cnum):0);
			rq.setUtype(utype);
			rq.setContent(content);
			rq.setLink(link);
			rq.setWxtitle(wxtitle);
			rq.setWxdesc(wxdesc);
			rq.setMinjf(StringUtils.isNotEmpty(minjf)?Integer.parseInt(minjf):0);
			rq.setMaxjf(StringUtils.isNotEmpty(maxjf)?Integer.parseInt(maxjf):0);
			int result = renQiMgr.updateRenQi(rq, id, ownerid);
			return SUCCESS;
		}
	}
	
	public String del() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int result = renQiMgr.updateStatus(id, "DEL", account.getOwner().getId());
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}

	private void addCheck()
	{
		if (StringUtils.isEmpty(title))
		{
			this.addFieldError("err1", "标题不能为空");
		}
		if (StringUtils.isEmpty(startTime))
		{
			this.addFieldError("err2", "开始时间不能为空");
		}
		if (StringUtils.isEmpty(endTime))
		{
			this.addFieldError("err3", "结束时间不能为空");
		}
		if (lid == 0)
		{
			this.addFieldError("err4", "抽奖不能为空");
		}
	}

	public IRenQiMgr getRenQiMgr()
	{
		return renQiMgr;
	}

	public void setRenQiMgr(IRenQiMgr renQiMgr)
	{
		this.renQiMgr = renQiMgr;
	}

	public RqDto getDto()
	{
		return dto;
	}

	public void setDto(RqDto dto)
	{
		this.dto = dto;
	}

	public int getPageId()
	{
		return pageId;
	}

	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getStartTime()
	{
		return startTime;
	}

	public void setStartTime(String startTime)
	{
		this.startTime = startTime;
	}

	public String getEndTime()
	{
		return endTime;
	}

	public void setEndTime(String endTime)
	{
		this.endTime = endTime;
	}

	public String getStartNote()
	{
		return startNote;
	}

	public void setStartNote(String startNote)
	{
		this.startNote = startNote;
	}

	public String getEndNote()
	{
		return endNote;
	}

	public void setEndNote(String endNote)
	{
		this.endNote = endNote;
	}

	public long getLid()
	{
		return lid;
	}

	public void setLid(long lid)
	{
		this.lid = lid;
	}

	public String getMaxlu()
	{
		return maxlu;
	}

	public String getCnum()
	{
		return cnum;
	}

	public int getUtype()
	{
		return utype;
	}

	public void setUtype(int utype)
	{
		this.utype = utype;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getLink()
	{
		return link;
	}

	public void setLink(String link)
	{
		this.link = link;
	}

	public String getWxtitle()
	{
		return wxtitle;
	}

	public void setWxtitle(String wxtitle)
	{
		this.wxtitle = wxtitle;
	}

	public String getWxdesc()
	{
		return wxdesc;
	}

	public void setWxdesc(String wxdesc)
	{
		this.wxdesc = wxdesc;
	}

	public String getMinjf()
	{
		return minjf;
	}

	public void setMinjf(String minjf)
	{
		this.minjf = minjf;
	}

	public String getMaxjf()
	{
		return maxjf;
	}

	public void setMaxjf(String maxjf)
	{
		this.maxjf = maxjf;
	}

	public void setMaxlu(String maxlu)
	{
		this.maxlu = maxlu;
	}

	public void setCnum(String cnum)
	{
		this.cnum = cnum;
	}

	public RenQi getRenqi()
	{
		return renqi;
	}

	public void setRenqi(RenQi renqi)
	{
		this.renqi = renqi;
	}

	public long getMid()
	{
		return mid;
	}

	public void setMid(long mid)
	{
		this.mid = mid;
	}

}