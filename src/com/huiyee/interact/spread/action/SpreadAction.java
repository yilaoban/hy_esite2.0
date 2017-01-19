package com.huiyee.interact.spread.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.model.Account;
import com.huiyee.interact.lottery.model.Lottery;
import com.huiyee.interact.spread.dto.SpreadDto;
import com.huiyee.interact.spread.mgr.ISpreadManager;
import com.huiyee.interact.spread.model.Pager;
import com.huiyee.interact.spread.util.FileUploadService;


public class SpreadAction extends InteractModelAction{

	private static final long serialVersionUID = -5658270043390021600L;
	private int pageId = 1;
	private long spreadid;
	private long mid = 10010;
	private SpreadDto dto;
	private ISpreadManager spreadMgr;
	private String result;
	private long resultid;
	private List<Lottery> zlotteryList;
	private List<Lottery> glotteryList;
	private List<Lottery> llotteryList;
	private List<Lottery> ylotteryList;
	private File repic;
	private String repicFileName;
	private String repicContentType;
	private String repicimage;
	private Lottery lottery;
	private long id;
	
	private String titlename;
	
	@Override
	public String execute() throws Exception {
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		int start = (pageId - 1) * IInteractConstants.VOTE_LIMIT;
		int total = spreadMgr.findSpreadListTotal(ownerid,IPageConstants.OWNER_INTERACT_SHOW);
		dto = (SpreadDto) spreadMgr.findSpreadList(ownerid, start, IInteractConstants.VOTE_LIMIT,IPageConstants.OWNER_INTERACT_SHOW);
		Pager pager = new Pager(pageId, total, IInteractConstants.VOTE_LIMIT);
		dto.setPager(pager);
		return SUCCESS;
	}

	public String jumpToSpreadDesign() throws Exception {
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		zlotteryList = spreadMgr.findLotteryByType(ownerid, "Z");
		glotteryList = spreadMgr.findLotteryByType(ownerid, "G");
		llotteryList = spreadMgr.findLotteryByType(ownerid, "L");
		ylotteryList = spreadMgr.findLotteryByType(ownerid, "Y");
		return SUCCESS;
	}
	
	private String createFileName(String fileName,String type) {
		int index = fileName.lastIndexOf('.');
		// 判断上传文件名是否有扩展名
		if (index != -1) {
			return FileUploadService.getNow()+type+ fileName.substring(index);
		}
		return FileUploadService.getNow()+type;
	}
	
	public String saveSpreadDesign() throws Exception{
		Account account =(Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		/*if(repic != null){
			String rename = createFileName(repicFileName,"_m");
			String repath = FileUploadService.getFilePath(IPageConstants.TYPE_INTERACT, ownerid, mid+"");
			String resaveResult = FileUploadService.saveFile(repic,repath,rename);
			if(resaveResult == null){
				result="图片保存失败";
				return SUCCESS;
			}
			dto.getSpread().setRepic(resaveResult);
		}*/
			if(dto.getType().equals("Z")){
				dto.getSpread().setLotteryid(dto.getZlotteryid());
			}else if (dto.getType().equals("G")) {
				dto.getSpread().setLotteryid(dto.getGlotteryid());
			}else if(dto.getType().equals("L")){
				dto.getSpread().setLotteryid(dto.getLlotteryid());
			}else if(dto.getType().equals("Y")){
				dto.getSpread().setLotteryid(dto.getYlotteryid());
			}
			resultid = spreadMgr.saveSpreadDesign(ownerid,dto,IPageConstants.OWNER_INTERACT_SHOW);
			spreadMgr.updateRuletypeByLotteryid(dto.getSpread().getLotteryid());
			if(resultid > 0){
				result = "Y";
			}
			return SUCCESS;
	} 
	
	public String toUpdateSpreadDesign() throws Exception{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		zlotteryList = spreadMgr.findLotteryByType(ownerid, "Z");
		glotteryList = spreadMgr.findLotteryByType(ownerid, "G");
		llotteryList = spreadMgr.findLotteryByType(ownerid, "L");
		ylotteryList = spreadMgr.findLotteryByType(ownerid, "Y");
		dto = (SpreadDto) spreadMgr.findSpreadModelById(spreadid,ownerid);
		if(dto.getSpread() != null && dto.getSpread().getLotteryid() != 0){
			lottery = spreadMgr.findLotteryById(dto.getSpread().getLotteryid());
			if(lottery != null && lottery.getType() != null){
				dto.setType(lottery.getType());
			}
		}
		return SUCCESS;
	}
	
	public String updateResearchDesign() throws Exception{
		Account account =(Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		/*if(repic != null){
			String rename = createFileName(repicFileName,"_m");
			String repath = FileUploadService.getFilePath(IPageConstants.TYPE_INTERACT, ownerid, mid+"");
			String resaveResult = FileUploadService.saveFile(repic,repath,rename);
			if(resaveResult == null){
				result="图片保存失败";
				return SUCCESS;
			}
			dto.getSpread().setRepic(resaveResult);
		}
		if(repic == null){
			dto.getSpread().setRepic(repicimage);
		}*/
		if(dto.getType().equals("Z")){
			dto.getSpread().setLotteryid(dto.getZlotteryid());
		}else if (dto.getType().equals("G")) {
			dto.getSpread().setLotteryid(dto.getGlotteryid());
		}else if(dto.getType().equals("L")){
			dto.getSpread().setLotteryid(dto.getLlotteryid());
		}else if(dto.getType().equals("Y")){
			dto.getSpread().setLotteryid(dto.getYlotteryid());
		}
		spreadMgr.updateRuletypeByLotteryid(dto.getSpread().getLotteryid());
		long len = spreadMgr.updateSpreadDesign(ownerid,dto,spreadid);
		if(len == 1){
			result="Y";
		}
		return SUCCESS;
	}
	
	public String deleteSpread()throws Exception{
		int len=spreadMgr.deleteSpread(id);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.write(len);
		out.flush();
		out.close();
		return null;
	}
	
	public String addSpread()throws Exception{
		Account account =(Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		long len=spreadMgr.addSpread(ownerid, titlename);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(len);
		out.flush();
		out.close();
		return null;
	}
	
	public String helpSpread()throws Exception{
		return SUCCESS;
	}
	
	public Lottery getLottery()
	{
		return lottery;
	}

	public void setLottery(Lottery lottery)
	{
		this.lottery = lottery;
	}

	public long getSpreadid()
	{
		return spreadid;
	}

	public void setSpreadid(long spreadid)
	{
		this.spreadid = spreadid;
	}

	public long getResultid()
	{
		return resultid;
	}

	public void setResultid(long resultid)
	{
		this.resultid = resultid;
	}

	public String getResult()
	{
		return result;
	}

	public void setResult(String result)
	{
		this.result = result;
	}

	public int getPageId()
	{
		return pageId;
	}

	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	public SpreadDto getDto()
	{
		return dto;
	}

	public void setDto(SpreadDto dto)
	{
		this.dto = dto;
	}

	public ISpreadManager getSpreadMgr()
	{
		return spreadMgr;
	}

	public void setSpreadMgr(ISpreadManager spreadMgr)
	{
		this.spreadMgr = spreadMgr;
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

	public long getMid()
	{
		return mid;
	}

	public void setMid(long mid)
	{
		this.mid = mid;
	}

	public File getRepic()
	{
		return repic;
	}

	public void setRepic(File repic)
	{
		this.repic = repic;
	}

	public String getRepicFileName()
	{
		return repicFileName;
	}

	public void setRepicFileName(String repicFileName)
	{
		this.repicFileName = repicFileName;
	}

	public String getRepicContentType()
	{
		return repicContentType;
	}

	public void setRepicContentType(String repicContentType)
	{
		this.repicContentType = repicContentType;
	}

	public String getRepicimage()
	{
		return repicimage;
	}

	public void setRepicimage(String repicimage)
	{
		this.repicimage = repicimage;
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
