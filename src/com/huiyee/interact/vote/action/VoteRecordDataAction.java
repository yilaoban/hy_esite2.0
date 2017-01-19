
package com.huiyee.interact.vote.action;

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
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.solr.HylogSolrServer;
import com.huiyee.interact.lottery.model.LotteryWinnerDetail;
import com.huiyee.interact.vote.dto.VoteOptionDto;
import com.huiyee.interact.vote.dto.VoteRecordDto;
import com.huiyee.interact.vote.dto.VoteRecordQueryDto;
import com.huiyee.interact.vote.mgr.IVoteMgr;
import com.huiyee.interact.vote.model.VoteOption;
import com.opensymphony.xwork2.ActionContext;

public class VoteRecordDataAction extends InteractModelAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1211680738140330967L;
	private IVoteMgr voteMgr;
	private VoteRecordDto dto;
	private int pageId = 1;
	private VoteRecordQueryDto queryDto;
	private long voteid;
	private long espageid;
	private int type = -1;// -1=投票结果 0=微博数据 1=微信数据 2=其他
	private VoteOptionDto optionDto;
	private String vtitle;
	private String nickname;
	private long mid = 10002;
	private HylogSolrServer hylogSolrServer;
	private int gz_i;// 1粉丝 0 非粉丝 -1全部
	private List<List<Object>> areaList;

	public int getPageId()
	{
		return pageId;
	}

	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	public String execute() throws Exception
	{
		/*
		 * if (type == -1) { type = 0; }
		 */
		dto = (VoteRecordDto) voteMgr.findVoteRecordData(voteid, queryDto, pageId, type, this.getOwner());
		return SUCCESS;
	}

	public String getVoteOptionData() throws Exception
	{
		optionDto = (VoteOptionDto) voteMgr.findVoteData(voteid);
		return SUCCESS;
	}

	public String exportVoteOptionData() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/csv;charset=GBK");
		response.setHeader("Content-Disposition", "attachment;filename=" + encodingFileName("投票数据") + ".csv");
		PrintWriter out = response.getWriter();
		optionDto = (VoteOptionDto) voteMgr.findVoteData(voteid);
		if (optionDto.getList() != null && optionDto.getList().size() > 0)
		{

			out.println("候选项 ,投票人数,占比");
			for (VoteOption opt : optionDto.getList())
			{
				String str = cvsStr(opt.getContent()) + "," + cvsStr(opt.getCount() + "") + "," + cvsStr(opt.getPercent());
				out.println(str.toString());
			}

		} else
		{
			out.print("无相关信息");
		}
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
		} catch (UnsupportedEncodingException e)
		{
			return time;
		}
	}

	private String cvsStr(String str)
	{
		if (str != null)
		{
			if (str.indexOf(",") != -1)
			{
				return "\"" + str + "\"";
			} else
			{
				return str;
			}
		} else
		{
			return "";
		}
	}

	public String getVoteOptionCount() throws Exception
	{
		VisitUser visit = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		if (visit == null)
		{
			out.print(new Gson().toJson("-1"));
			out.flush();
			out.close();
			return null;
		}
		List<VoteOption> list = voteMgr.findVoteOptionCount(voteid, visit);
		if (list.size() > 0)
		{
			out.print(new Gson().toJson(list));
		} else
		{
			out.print(new Gson().toJson("-1"));
		}
		out.flush();
		out.close();
		return null;
	}

	public String getVoteOptionCountPC() throws Exception
	{
		List<VoteOption> list = voteMgr.findVoteOptionCountPC(espageid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		if (list.size() > 0)
		{
			out.print(new Gson().toJson(list));
		} else
		{
			out.print(new Gson().toJson("-1"));
		}
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
		if (account != null && voteid > 0)
		{
			rs = voteMgr.updateVoteClean(voteid, account.getOwner().getId());
		}
		out.print(rs);
		out.flush();
		out.close();
		return null;
	}

	public String voteMoveToGroup() throws Exception
	{
		areaList = hylogSolrServer.findHudongUserArea("wx", "h", "t", voteid, gz_i);
		return SUCCESS;
	}

	public void setVoteMgr(IVoteMgr voteMgr)
	{
		this.voteMgr = voteMgr;
	}

	public VoteRecordDto getDto()
	{
		return dto;
	}

	public long getVoteid()
	{
		return voteid;
	}

	public void setVoteid(long voteid)
	{
		this.voteid = voteid;
	}

	public IVoteMgr getVoteMgr()
	{
		return voteMgr;
	}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public VoteOptionDto getOptionDto()
	{
		return optionDto;
	}

	public VoteRecordQueryDto getQueryDto()
	{
		return queryDto;
	}

	public void setQueryDto(VoteRecordQueryDto queryDto)
	{
		this.queryDto = queryDto;
	}

	public String getVtitle()
	{
		return vtitle;
	}

	public void setVtitle(String vtitle)
	{
		this.vtitle = vtitle;
	}

	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	public long getMid()
	{
		return mid;
	}

	public void setMid(long mid)
	{
		this.mid = mid;
	}

	public long getEspageid()
	{
		return espageid;
	}

	public void setEspageid(long espageid)
	{
		this.espageid = espageid;
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

	public void setHylogSolrServer(HylogSolrServer hylogSolrServer)
	{
		this.hylogSolrServer = hylogSolrServer;
	}

}
