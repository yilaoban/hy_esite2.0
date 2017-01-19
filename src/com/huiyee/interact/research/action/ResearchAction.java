package com.huiyee.interact.research.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.model.Account;
import com.huiyee.interact.lottery.model.Lottery;
import com.huiyee.interact.research.dto.ResearchDto;
import com.huiyee.interact.research.mgr.IResearchManager;
import com.huiyee.interact.research.model.Pager;

public class ResearchAction extends InteractModelAction
{
	private static final long serialVersionUID = 3913889817791350944L;

	private IResearchManager researchMgr;
	private int pageId = 1;
	private long researchid;
	private long id;
	private ResearchDto dto;
	private long resultid;
	private long mid = 10006;
	private String result;
	private List<Lottery> zlotteryList;
	private List<Lottery> glotteryList;
	private List<Lottery> llotteryList;
	private List<Lottery> ylotteryList;
	private Lottery lottery;
	private String titlename;
	
	@Override
	public String execute() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		int start = (pageId - 1) * IInteractConstants.VOTE_LIMIT;
		int total = researchMgr.findResearchListTotal(ownerid,IPageConstants.OWNER_INTERACT_SHOW);
		dto = (ResearchDto) researchMgr.findResearchList(ownerid, start, IInteractConstants.VOTE_LIMIT,IPageConstants.OWNER_INTERACT_SHOW);
		Pager pager = new Pager(pageId, total, IInteractConstants.VOTE_LIMIT);
		dto.setPager(pager);
		return SUCCESS;
	}
	
	public String jumpToResearchDesign() throws Exception{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		zlotteryList = researchMgr.findLotteryByType(ownerid, "Z");
		glotteryList = researchMgr.findLotteryByType(ownerid, "G");
		llotteryList = researchMgr.findLotteryByType(ownerid, "L");
		ylotteryList = researchMgr.findLotteryByType(ownerid, "Y");
		return SUCCESS;
	} 

	public String saveResearchDesign() throws Exception{
		Account account =(Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
			if("Z".equals(dto.getType())){
				dto.getResearch().setLotteryid(dto.getZlotteryid());
			}else if ("G".equals(dto.getType())) {
				dto.getResearch().setLotteryid(dto.getGlotteryid());
			}else if("L".equals(dto.getType())){
				dto.getResearch().setLotteryid(dto.getLlotteryid());
			}else if("Y".equals(dto.getType())){
				dto.getResearch().setLotteryid(dto.getYlotteryid());
			}
			resultid = researchMgr.saveResearchDesign(ownerid,dto,IPageConstants.OWNER_INTERACT_SHOW);
			researchMgr.updateRuletypeByLotteryid(dto.getResearch().getLotteryid());
			if(resultid > 0){
				result = "Y";
			}
			return SUCCESS;
	} 
	
	public String toUpdateResearchDesign() throws Exception{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		zlotteryList = researchMgr.findLotteryByType(ownerid, "Z");
		glotteryList = researchMgr.findLotteryByType(ownerid, "G");
		llotteryList = researchMgr.findLotteryByType(ownerid, "L");
		ylotteryList = researchMgr.findLotteryByType(ownerid, "Y");
		dto = (ResearchDto) researchMgr.findResearchModelById(researchid);
		if(dto.getResearch() != null && dto.getResearch().getLotteryid() != 0){
			lottery = researchMgr.findLotteryById(dto.getResearch().getLotteryid());
			if(lottery != null && lottery.getType() != null){
				dto.setType(lottery.getType());
			}
		}
		return SUCCESS;
	}
	
	public String updateResearchDesign() throws Exception{
		Account account =(Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		if("Z".equals(dto.getType())){
			dto.getResearch().setLotteryid(dto.getZlotteryid());
		}else if ("G".equals(dto.getType())) {
			dto.getResearch().setLotteryid(dto.getGlotteryid());
		}else if("L".equals(dto.getType())){
			dto.getResearch().setLotteryid(dto.getLlotteryid());
		}else if("Y".equals(dto.getType())){
			dto.getResearch().setLotteryid(dto.getYlotteryid());
		}
		researchMgr.updateRuletypeByLotteryid(dto.getResearch().getLotteryid());
		long len = researchMgr.updateResearchDesign(ownerid,dto,researchid);
		if(len == 1){
			result="Y";
		}
		return SUCCESS;
	}
	
	public String deleteResearch()throws Exception{
		int len=researchMgr.deleteResearch(id);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.write(len);
		out.flush();
		out.close();
		return null;
	}
	
	public String addResearch()throws Exception{
		Account account =(Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		long len=researchMgr.addResearch(ownerid, titlename);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(len);
		out.flush();
		out.close();
		return null;
	}
	
	public String helpResearch()throws Exception{
		return SUCCESS;
	}
	
	public ResearchDto getDto()
	{
		return dto;
	}

	public void setDto(ResearchDto dto)
	{
		this.dto = dto;
	}

	public IResearchManager getResearchMgr()
	{
		return researchMgr;
	}

	public void setResearchMgr(IResearchManager researchMgr)
	{
		this.researchMgr = researchMgr;
	}

	public int getPageId()
	{
		return pageId;
	}

	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	public long getResultid()
	{
		return resultid;
	}

	public void setResultid(long resultid)
	{
		this.resultid = resultid;
	}

	public long getMid()
	{
		return mid;
	}

	public void setMid(long mid)
	{
		this.mid = mid;
	}

	public String getResult()
	{
		return result;
	}

	public void setResult(String result)
	{
		this.result = result;
	}

	public List<Lottery> getZlotteryList()
	{
		return zlotteryList;
	}

	public void setZlotteryList(List<Lottery> zlotteryList)
	{
		this.zlotteryList = zlotteryList;
	}

	public List<Lottery> getGlotteryList()
	{
		return glotteryList;
	}

	public void setGlotteryList(List<Lottery> glotteryList)
	{
		this.glotteryList = glotteryList;
	}

	public List<Lottery> getLlotteryList()
	{
		return llotteryList;
	}

	public void setLlotteryList(List<Lottery> llotteryList)
	{
		this.llotteryList = llotteryList;
	}

	public long getResearchid()
	{
		return researchid;
	}

	public void setResearchid(long researchid)
	{
		this.researchid = researchid;
	}

	public Lottery getLottery()
	{
		return lottery;
	}

	public void setLottery(Lottery lottery)
	{
		this.lottery = lottery;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public List<Lottery> getYlotteryList()
	{
		return ylotteryList;
	}

	public void setYlotteryList(List<Lottery> ylotteryList)
	{
		this.ylotteryList = ylotteryList;
	}

	public String getTitlename()
	{
		return titlename;
	}

	public void setTitlename(String titlename)
	{
		this.titlename = titlename;
	}
	


}
