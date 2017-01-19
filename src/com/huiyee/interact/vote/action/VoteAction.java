package com.huiyee.interact.vote.action;

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
import com.huiyee.interact.vote.dto.VoteDto;
import com.huiyee.interact.vote.dto.VoteOptionDto;
import com.huiyee.interact.vote.mgr.IVoteMgr;
import com.huiyee.interact.vote.model.InteractVote;
import com.huiyee.interact.vote.model.Pager;
import com.huiyee.interact.vote.model.VoteOption;
import com.huiyee.interact.vote.model.VoteOptionModel;
import com.huiyee.interact.vote.util.FileUploadService;

public class VoteAction extends InteractModelAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3887688790629684079L;

	private IVoteMgr voteMgr;
	private long id;
	private long voteid;
	private String type;
	private VoteDto dto;
	private VoteOptionDto vdto;
	private int pageId=1;
	private List<String> content;
	private List<File> img;
	private List<String> imgFileName;
	private List<String> imgContentType;
	private File pic;
	private File repic;
	private String picFileName;
	private String picContentType;
	private String picimage;
	private String repicFileName;
	private String repicContentType;
	private VoteOptionModel vom;
	private InteractVote vmm;
	private long resultid;
	private long mid=10002;
	private String result;
	private List<VoteOption> os;
	private String repicimage;
	private List<Lottery> zlotteryList;
	private List<Lottery> glotteryList;
	private List<Lottery> llotteryList;
	private List<Lottery> ylotteryList;
	private List<Lottery> lotteryList;
	private Lottery lottery;
	
	private String titlename;
	
	public List<File> getImg() {
		return img;
	}
	public void setImg(List<File> img) {
		this.img = img;
	}
	public List<String> getImgFileName() {
		return imgFileName;
	}
	public void setImgFileName(List<String> imgFileName) {
		this.imgFileName = imgFileName;
	}
	public File getPic() {
		return pic;
	}
	public void setPic(File pic) {
		this.pic = pic;
	}
	public String getPicFileName() {
		return picFileName;
	}
	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}
	public VoteDto getDto() {
		return dto;
	}
	public void setDto(VoteDto dto) {
		this.dto = dto;
	}
	public int getPageId() {
		return pageId;
	}
	public void setPageId(int pageId) {
		this.pageId = pageId;
	}
	
	public String execute()throws Exception{
		Account account=(Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid=account.getOwner().getId();
		dto=new VoteDto();
		int start=(pageId-1)*IInteractConstants.VOTE_LIMIT;
		int total=voteMgr.findVoteListTotal(ownerid,IPageConstants.OWNER_INTERACT_SHOW);
		dto.setList(voteMgr.findVoteList(ownerid, start, IInteractConstants.VOTE_LIMIT,IPageConstants.OWNER_INTERACT_SHOW));
		Pager pager=new Pager(pageId,total,IInteractConstants.VOTE_LIMIT);
		dto.setPager(pager);
		return SUCCESS;
	}
	
	public String jumpToVoteDesign() throws Exception{
		Account account=(Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid=account.getOwner().getId();
		zlotteryList = voteMgr.findLotteryByType(ownerid, "Z");
		glotteryList = voteMgr.findLotteryByType(ownerid, "G");
		llotteryList = voteMgr.findLotteryByType(ownerid, "L");
		ylotteryList = voteMgr.findLotteryByType(ownerid, "Y");
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

	
	public String saveVoteDesign() throws Exception{
		Account account =(Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		if("Z".equals(dto.getType())){
			dto.setLotteryid(dto.getZlotteryid());
		}else if ("G".equals(dto.getType())) {
			dto.setLotteryid(dto.getGlotteryid());
		}else if("L".equals(dto.getType())){
			dto.setLotteryid(dto.getLlotteryid());
		}else if("Y".equals(dto.getType())){
			dto.setLotteryid(dto.getYlotteryid());
		}
		resultid = voteMgr.saveVoteDesign(ownerid,dto,IPageConstants.OWNER_INTERACT_SHOW);
		voteMgr.updateRuletypeByLotteryid(dto.getLotteryid());
		if(resultid > 0){
			result = "Y";
		}
		return SUCCESS;
	} 
	
	
	public String toUpdateVoteDesign() throws Exception{
		Account account=(Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid=account.getOwner().getId();
		zlotteryList = voteMgr.findLotteryByType(ownerid, "Z");
		glotteryList = voteMgr.findLotteryByType(ownerid, "G");
		llotteryList = voteMgr.findLotteryByType(ownerid, "L");
		ylotteryList = voteMgr.findLotteryByType(ownerid, "Y");
		dto = (VoteDto) voteMgr.findVoteManageModelById(voteid,ownerid);
		if(dto.getVote() !=null && dto.getVote().getLotteryid() != 0){
			lottery = voteMgr.findLotteryById(dto.getVote().getLotteryid());
			if(lottery !=null && lottery.getType() != null){
				dto.setType(lottery.getType());
			}
		}
		return SUCCESS;
	}
	
	public String updateVoteDesign() throws Exception{
		Account account =(Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		if("Z".equals(dto.getType())){
			dto.setLotteryid(dto.getZlotteryid());
		}else if ("G".equals(dto.getType())) {
			dto.setLotteryid(dto.getGlotteryid());
		}else if("L".equals(dto.getType())){
			dto.setLotteryid(dto.getLlotteryid());
		}else if("Y".equals(dto.getType())){
			dto.setLotteryid(dto.getYlotteryid());
		}
		voteMgr.updateRuletypeByLotteryid(dto.getLotteryid());
		long len = voteMgr.updateVoteDesign(ownerid,dto,voteid);
		if(len == 1){
			result="Y";
		}
		if(redirectXc!=0){
			return "toxc";
		}
		return SUCCESS;
	}
	
	public String deleteVote()throws Exception{
		int len=voteMgr.deleteVote(id);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.write(len);
		out.flush();
		out.close();
		return null;
	}
	
	public String helpVote()throws Exception{
		return SUCCESS;
	}
	
	public String addVote()throws Exception{
		Account account =(Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		long len=voteMgr.addVote(ownerid, titlename);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(len);
		out.flush();
		out.close();
		return null;
	}
	
	public IVoteMgr getVoteMgr() {
		return voteMgr;
	}
	public void setVoteMgr(IVoteMgr voteMgr) {
		this.voteMgr = voteMgr;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public long getMid() {
		return mid;
	}
	public void setMid(long mid) {
		this.mid = mid;
	}
	public File getRepic() {
		return repic;
	}
	public void setRepic(File repic) {
		this.repic = repic;
	}
	public String getRepicFileName() {
		return repicFileName;
	}
	public void setRepicFileName(String repicFileName) {
		this.repicFileName = repicFileName;
	}
	public String getRepicContentType() {
		return repicContentType;
	}
	public void setRepicContentType(String repicContentType) {
		this.repicContentType = repicContentType;
	}
	public long getResultid() {
		return resultid;
	}
	public void setResultid(long resultid) {
		this.resultid = resultid;
	}
	public long getVoteid() {
		return voteid;
	}
	public void setVoteid(long voteid) {
		this.voteid = voteid;
	}
	public VoteOptionDto getVdto() {
		return vdto;
	}
	public void setVdto(VoteOptionDto vdto) {
		this.vdto = vdto;
	}
	public VoteOptionModel getVom() {
		return vom;
	}
	public void setVom(VoteOptionModel vom) {
		this.vom = vom;
	}
	public List<String> getImgContentType() {
		return imgContentType;
	}
	public void setImgContentType(List<String> imgContentType) {
		this.imgContentType = imgContentType;
	}
	public List<String> getContent() {
		return content;
	}
	public void setContent(List<String> content) {
		this.content = content;
	}
	public void setOs(List<VoteOption> os) {
		this.os = os;
	}
	public List<Lottery> getZlotteryList() {
		return zlotteryList;
	}
	public void setZlotteryList(List<Lottery> zlotteryList) {
		this.zlotteryList = zlotteryList;
	}
	public List<Lottery> getGlotteryList() {
		return glotteryList;
	}
	public void setGlotteryList(List<Lottery> glotteryList) {
		this.glotteryList = glotteryList;
	}
	public List<Lottery> getLlotteryList() {
		return llotteryList;
	}
	public void setLlotteryList(List<Lottery> llotteryList) {
		this.llotteryList = llotteryList;
	}
	public List<Lottery> getLotteryList() {
		return lotteryList;
	}
	public void setLotteryList(List<Lottery> lotteryList) {
		this.lotteryList = lotteryList;
	}
	public String getPicimage() {
		return picimage;
	}
	public void setPicimage(String picimage) {
		this.picimage = picimage;
	}
	public String getRepicimage() {
		return repicimage;
	}
	public void setRepicimage(String repicimage) {
		this.repicimage = repicimage;
	}
	public Lottery getLottery() {
		return lottery;
	}
	public void setLottery(Lottery lottery) {
		this.lottery = lottery;
	}
	public String getPicContentType() {
		return picContentType;
	}
	public void setPicContentType(String picContentType) {
		this.picContentType = picContentType;
	}
	
	public InteractVote getVmm()
	{
		return vmm;
	}
	
	public void setVmm(InteractVote vmm)
	{
		this.vmm = vmm;
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
