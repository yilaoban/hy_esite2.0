
package com.huiyee.interact.xc.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.model.WxUser;
import com.huiyee.esite.util.ClientUserIp;
import com.huiyee.esite.util.HttpRequestDeviceUtils;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.interact.xc.dto.XcCommentRecordDto;
import com.huiyee.interact.xc.mgr.ISigninMgr;
import com.huiyee.interact.xc.mgr.IXcMgr;
import com.huiyee.interact.xc.mgr.InteractCommentMgr;
import com.huiyee.interact.xc.model.Xc;
import com.huiyee.interact.xc.model.XcCommentRecord;
import com.opensymphony.xwork2.ActionContext;

public class CommentSubmitAction extends InteractModelAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long mid = 10014;
	private InteractCommentMgr commentMgr;
	private ISigninMgr signinMgr;
	private IXcMgr xcMgr;
	private String content;
	private long pageid;
	private long xcid;
	private String result;
	private String msg;
	private XcCommentRecordDto dto;
	private long start;
	private int size;

	public String execute() throws Exception
	{
		List<XcCommentRecord> list = commentMgr.findCommentRecordList(xcid, pageid, start, size);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.write(gson.toJson(list));
		out.flush();
		out.close();
		return null;
	}

	public String saveComment() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		VisitUser vu = (VisitUser) ServletActionContext.getRequest().getSession().getAttribute("visitUser");
		if (vu == null && HyConfig.isRun())
		{
			out.print("-11000");
			out.flush();
			out.close();
			return null;
		}

		Long sut = (Long) ServletActionContext.getRequest().getSession().getAttribute("sut");
		if (sut != null && System.currentTimeMillis() - sut < 3000)
		{
			out.print("-11001");
			out.flush();
			out.close();
			return null;
		}
		ActionContext.getContext().getSession().put("sut", System.currentTimeMillis());

		XcCommentRecord xcRecord = new XcCommentRecord();
		HttpServletRequest request = ServletActionContext.getRequest();
		String userAgent = request.getHeader("user-agent");
		String terminal = HttpRequestDeviceUtils.getMediaDevice(userAgent);
		xcRecord.setPageid(pageid);
		xcRecord.setContent(content);
		xcRecord.setXcid(xcid);
		String ip = ClientUserIp.getIpAddr(request);
		xcRecord.setIp(ip);
		xcRecord.setSource(vu.getSource());
		xcRecord.setTerminal(terminal);
		xcRecord.setUid(vu.getUid());
		xcRecord.setUtype(vu.getCd());

		Xc xc = xcMgr.getXcByIdMc(xcid);
		if (xc == null)
		{
			out.print("-10000");
			out.flush();
			out.close();
			return null;
		}
		if (xc.getNeedcomment().equalsIgnoreCase("N"))
		{
			out.print("-10001");
			out.flush();
			out.close();
			return null;
		}
		if (xc.getCommentconfig().getMaxnum() > 0)
		{
			int cnum = commentMgr.findUserCommentNum(xcRecord.getUid(), xcRecord.getUtype(), xcid);
			if (cnum >= xc.getCommentconfig().getMaxnum())
			{
				out.print("-10002");
				out.flush();
				out.close();
				return null;
			}
		}
		if (xc.getCommentconfig().getAtype().equalsIgnoreCase("Q"))
		{
			int isCheckin = signinMgr.findUser(xcid, xcRecord.getUid(), xcRecord.getUtype());
			if (isCheckin == 0)
			{
				out.print(-1);
				out.flush();
				out.close();
				return null;
			}
		}

		if (xc.getCommentconfig().getChecked().equals("N"))
		{
			xcRecord.setStatus(IPageConstants.STATUS_CMP);
		} else
		{
			xcRecord.setStatus(IPageConstants.STATUS_EDT);
		}

		commentMgr.saveComment(xcRecord);
		out.print(1);
		out.flush();
		out.close();
		return null;
	}

	public String commentlist() throws Exception
	{
		XcCommentRecordDto dto = new XcCommentRecordDto();
		Xc xc = xcMgr.getXcByIdMc(xcid);
		Gson gson = new Gson();
		if (xc.getCommentconfig() != null)
		{
			if (xc.getCommentconfig().getChecked().equalsIgnoreCase("Y"))
			{
				dto.setList(commentMgr.findCommentRecord1(xcid, start, size));
				dto.setCount(commentMgr.findCommenterTotal1(xcid));
			} else
			{
				dto.setList(commentMgr.findCommentRecord(xcid, start, size));
				dto.setCount(commentMgr.findCommenterTotal(xcid));
			}
		} else
		{
			// 搜所有评论 包括FLD的
			dto.setList(commentMgr.findCommentRecord(xcid, start, size));
			dto.setCount(commentMgr.findCommenterTotal(xcid));
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.write(gson.toJson(dto));
		out.flush();
		out.close();
		return null;
	}

	public String commentDraw() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		List<XcCommentRecord> records = new ArrayList<XcCommentRecord>();
		if (size > 0)
		{
			records = commentMgr.findCommentWinner(size);
		}
		out.write(new Gson().toJson(records));
		out.flush();
		out.close();
		return null;
	}

	public void setSigninMgr(ISigninMgr signinMgr)
	{
		this.signinMgr = signinMgr;
	}

	public void setCommentMgr(InteractCommentMgr commentMgr)
	{
		this.commentMgr = commentMgr;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public InteractCommentMgr getCommentMgr()
	{
		return commentMgr;
	}

	public String getResult()
	{
		return result;
	}

	public void setResult(String result)
	{
		this.result = result;
	}

	public XcCommentRecordDto getDto()
	{
		return dto;
	}

	public void setDto(XcCommentRecordDto dto)
	{
		this.dto = dto;
	}

	public long getPageid()
	{
		return pageid;
	}

	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	public long getXcid()
	{
		return xcid;
	}

	public void setXcid(long xcid)
	{
		this.xcid = xcid;
	}

	public long getMid()
	{
		return mid;
	}

	public void setMid(long mid)
	{
		this.mid = mid;
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	public long getStart()
	{
		return start;
	}

	public void setStart(long start)
	{
		this.start = start;
	}

	public int getSize()
	{
		return size;
	}

	public void setSize(int size)
	{
		this.size = size;
	}

	public void setXcMgr(IXcMgr xcMgr)
	{
		this.xcMgr = xcMgr;
	}
}
