package com.huiyee.interact.exam.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.model.Account;
import com.huiyee.interact.lottery.model.Lottery;
import com.huiyee.interact.exam.dto.ExamDto;
import com.huiyee.interact.exam.mgr.IExamManager;
import com.huiyee.interact.exam.model.Pager;

public class ExamAction extends InteractModelAction
{
	private static final long serialVersionUID = 3913889817791350944L;

	private IExamManager examMgr;
	private int pageId = 1;
	private long examid;
	private long id;
	private ExamDto dto;
	private long resultid;
	private long mid = 10015;
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
		int total = examMgr.findExamListTotal(ownerid,IPageConstants.OWNER_INTERACT_SHOW);
		dto = (ExamDto) examMgr.findExamList(ownerid, start, IInteractConstants.VOTE_LIMIT,IPageConstants.OWNER_INTERACT_SHOW);
		Pager pager = new Pager(pageId, total, IInteractConstants.VOTE_LIMIT);
		dto.setPager(pager);
		return SUCCESS;
	}
	
	public String jumpToExamDesign() throws Exception{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		zlotteryList = examMgr.findLotteryByType(ownerid, "Z");
		glotteryList = examMgr.findLotteryByType(ownerid, "G");
		llotteryList = examMgr.findLotteryByType(ownerid, "L");
		ylotteryList = examMgr.findLotteryByType(ownerid, "Y");
		return SUCCESS;
	} 

	public String saveExamDesign() throws Exception{
		Account account =(Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
			if("Z".equals(dto.getType())){
				dto.getExam().setLotteryid(dto.getZlotteryid());
			}else if ("G".equals(dto.getType())) {
				dto.getExam().setLotteryid(dto.getGlotteryid());
			}else if("L".equals(dto.getType())){
				dto.getExam().setLotteryid(dto.getLlotteryid());
			}else if("Y".equals(dto.getType())){
				dto.getExam().setLotteryid(dto.getYlotteryid());
			}
			resultid = examMgr.saveExamDesign(ownerid,dto,IPageConstants.OWNER_INTERACT_SHOW);
			examMgr.updateRuletypeByLotteryid(dto.getExam().getLotteryid());
			if(resultid > 0){
				result = "Y";
			}
			return SUCCESS;
	} 
	
	public String toUpdateExamDesign() throws Exception{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		zlotteryList = examMgr.findLotteryByType(ownerid, "Z");
		glotteryList = examMgr.findLotteryByType(ownerid, "G");
		llotteryList = examMgr.findLotteryByType(ownerid, "L");
		ylotteryList = examMgr.findLotteryByType(ownerid, "Y");
		dto = (ExamDto) examMgr.findExamModelById(examid);
		if(dto.getExam() != null && dto.getExam().getLotteryid() != 0){
			lottery = examMgr.findLotteryById(dto.getExam().getLotteryid());
			if(lottery != null && lottery.getType() != null){
				dto.setType(lottery.getType());
			}
		}
		return SUCCESS;
	}
	
	public String updateExamDesign() throws Exception{
		Account account =(Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		if("Z".equals(dto.getType())){
			dto.getExam().setLotteryid(dto.getZlotteryid());
		}else if ("G".equals(dto.getType())) {
			dto.getExam().setLotteryid(dto.getGlotteryid());
		}else if("L".equals(dto.getType())){
			dto.getExam().setLotteryid(dto.getLlotteryid());
		}else if("Y".equals(dto.getType())){
			dto.getExam().setLotteryid(dto.getYlotteryid());
		}
		examMgr.updateRuletypeByLotteryid(dto.getExam().getLotteryid());
		long len = examMgr.updateExamDesign(ownerid,dto,examid);
		if(len == 1){
			result="Y";
		}
		return SUCCESS;
	}
	
	public String deleteExam()throws Exception{
		int len=examMgr.deleteExam(id);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.write(len);
		out.flush();
		out.close();
		return null;
	}
	
	public String addExam()throws Exception{
		Account account =(Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		long len=examMgr.addExam(ownerid, titlename);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(len);
		out.flush();
		out.close();
		return null;
	}
	
	public String helpExam()throws Exception{
		return SUCCESS;
	}
	
	public ExamDto getDto()
	{
		return dto;
	}

	public void setDto(ExamDto dto)
	{
		this.dto = dto;
	}

	public IExamManager getExamMgr()
	{
		return examMgr;
	}

	public void setExamMgr(IExamManager examMgr)
	{
		this.examMgr = examMgr;
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

	public long getExamid()
	{
		return examid;
	}

	public void setExamid(long examid)
	{
		this.examid = examid;
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
