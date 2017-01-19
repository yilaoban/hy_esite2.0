package project.caidan.action;

import java.io.PrintWriter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import project.caidan.mgr.ICaiDanLotteryMgr;
import project.caidan.model.CDLottery;
import project.caidan.model.CDSet;

import com.google.gson.Gson;
import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.interact.lottery.dto.LotteryDto;
import com.huiyee.interact.lottery.dto.Usertype;
import com.huiyee.interact.lottery.mgr.ILotteryMgr;
import com.huiyee.interact.lottery.mgr.ILotteryUserDateMgr;
import com.huiyee.interact.lottery.model.Lottery;
import com.huiyee.interact.lottery.model.LotteryUserDate;


public class CaiDanLotteryAction extends InteractModelAction
{
	private static final long serialVersionUID = -3448997827151333228L;
	private String htype = "Y";//摇一摇 : Y  砸金蛋:L 抢红包：H
	private ICaiDanLotteryMgr cdLotteryMgr;
	private ILotteryMgr lotteryMgr;
	private ILotteryUserDateMgr lotteryUserDateMgr;
	private int pageId = 1;
	private LotteryDto dto;
	
	private String name;
	private String detail;
	private String startTime;
	private String endTime;
	private String startnote;
	private String endnote;
	private String usertype;
	private long gzeid;
	private String jf;
	private int usertotal;
	private int userzjtotal;
	private int userdaynum;
	private int zjl;
	
	private String userName = "N";
	private String userPhone = "N";
	private String userEmail = "N";
	private String userLocation = "N";
	
	private long lid;
	private List<CDSet> cdsetList;
	private CDLottery cdLottery;
	private String img;
	private long enid;
	private int size;
	private int drawnum;
	private int winnum;
	
	private long pageid;
	public void setLotteryMgr(ILotteryMgr lotteryMgr)
	{
		this.lotteryMgr = lotteryMgr;
	}

	public void setCdLotteryMgr(ICaiDanLotteryMgr cdLotteryMgr)
	{
		this.cdLotteryMgr = cdLotteryMgr;
	}

	public void setLotteryUserDateMgr(ILotteryUserDateMgr lotteryUserDateMgr) {
		this.lotteryUserDateMgr = lotteryUserDateMgr;
	}

	/**
	 * 列表页
	 */
	@Override
	public String execute() throws Exception
	{
		dto = (LotteryDto) cdLotteryMgr.findAllLottery(pageId, this.getOwner(), htype ,0);
		return SUCCESS;
	}

	/**
	 * 新增
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {
		if("Y".equals(htype)){
			cdsetList = cdLotteryMgr.findCdSetByType("YYY");
		}else if("L".equals(htype)){
			cdsetList = cdLotteryMgr.findCdSetByType("ZJD");
		}else if("H".equals(htype)){
			cdsetList = cdLotteryMgr.findCdSetByType("QHB");
		}
		return SUCCESS;
	}
	
	
	public String saveSub() throws Exception {
		if (StringUtils.isEmpty(name)) {
			this.addFieldError("error1", "标题不能为空");
		}
		if (startTime == null || startTime.trim().length() == 0) {
			this.addFieldError("error2", "开始时间不能为空");

		}
		if (endTime == null || endTime.trim().length() == 0) {
			this.addFieldError("error3", "结束时间不能为空");
		}
		if ("J".equals(usertype) && StringUtils.isNotEmpty(jf)) {
			Pattern pattern = Pattern.compile("[0-9]*");
			Matcher matcher = pattern.matcher(jf);
			if (!matcher.matches()) {
				this.addFieldError("error5", "积分抽奖所填积分数必须为数字!");
			}
		} else {
			jf = "0";
		}

		if (!this.getFieldErrors().isEmpty()) {
			return "failed";
		} else {
			Lottery l = new Lottery();
			l.setName(name);
			l.setStarttime(startTime);
			l.setEndtime(endTime);
			l.setStartnote(startnote);
			l.setEndnote(endnote);
			l.setUsertype(usertype);
			l.setUserzjtotal(userzjtotal);
			if ("J".equals(usertype)) {
				Usertype u = new Usertype();
				u.setJf(Integer.parseInt(jf));
				Gson g = new Gson();
				l.setAssignuser(g.toJson(u));
			}
			l.setUsertotal(usertotal);
			l.setUserdaynum(userdaynum);
			l.setZjl(zjl);
			l.setDrawnum(drawnum);
			l.setUserName(userName);
			l.setUserPhone(userPhone);
			l.setUserEmail(userEmail);
			l.setUserLocation(userLocation);
			l.setDetail(detail);
			l.setGzeid(gzeid);

			l.setOwner(this.getOwner());
			l.setType(htype);
			long lid = lotteryMgr.saveLottery(l, 0);
			
			cdLotteryMgr.saveCdLottery(lid,img,enid,this.getOwner(), htype);
		}
		return SUCCESS;
	}
	
	public String edit() throws Exception {
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		dto = (LotteryDto) lotteryMgr.findLotteryById(lid, ownerid);
		cdLottery = cdLotteryMgr.findCdLotteryById(lid);
		if("Y".equals(htype)){
			cdsetList = cdLotteryMgr.findCdSetByType("YYY");
		}else if("L".equals(htype)){
			cdsetList = cdLotteryMgr.findCdSetByType("ZJD");
		}else if("H".equals(htype)){
			cdsetList = cdLotteryMgr.findCdSetByType("QHB");
		}
		return SUCCESS;
	}
	
	public String editSub() throws Exception {
		if (StringUtils.isEmpty(name)) {
			this.addFieldError("error", "标题不能为空");
		}
		if (startTime == null || startTime.trim().length() == 0) {
			this.addFieldError("error", "开始时间不能为空");

		}
		if (endTime == null || endTime.trim().length() == 0) {
			this.addFieldError("error", "结束时间不能为空");
		}
		if ("J".equals(usertype) && StringUtils.isNotEmpty(jf)) {
			Pattern pattern = Pattern.compile("[0-9]*");
			Matcher matcher = pattern.matcher(jf);
			if (!matcher.matches()) {
				this.addFieldError("error5", "积分抽奖所填积分数必须为数字!");
			}
		} else {
			jf = "0";
		}

		if (!this.getFieldErrors().isEmpty()) {
			Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
			long ownerid = account.getOwner().getId();
			dto = (LotteryDto) lotteryMgr.findLotteryById(lid, ownerid);
			return "failed";
		} else {
			Lottery l = new Lottery();
			l.setName(name);
			l.setStarttime(startTime);
			l.setEndtime(endTime);
			l.setStartnote(startnote);
			l.setEndnote(endnote);
			l.setUsertype(usertype);
			l.setUserzjtotal(userzjtotal);
			if ("J".equals(usertype)) {
				Usertype u = new Usertype();
				u.setJf(Integer.parseInt(jf));
				Gson g = new Gson();
				l.setAssignuser(g.toJson(u));
			} else if ("X".equals(usertype)) {
				Usertype u = new Usertype();
				u.setXcid(redirectXc);
				Gson g = new Gson();
				l.setAssignuser(g.toJson(u));
			}
			l.setUsertotal(usertotal);
			l.setUserdaynum(userdaynum);
			l.setZjl(zjl);
			l.setDrawnum(drawnum);
			l.setUserName(userName);
			l.setUserPhone(userPhone);
			l.setUserEmail(userEmail);
			l.setUserLocation(userLocation);
			l.setDetail(detail);
			l.setGzeid(gzeid);
			Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
			long ownerid = account.getOwner().getId();
			l.setOwner(ownerid);
			l.setType(htype);
			int num = lotteryMgr.updateLottery(l, ownerid, lid);
			
			cdLotteryMgr.updateCdLottery(lid,img,enid);
		}
		if (redirectXc != 0) {
			// 从微现场点过来的 没有互动tool 返回按钮返回微现场
			return "fromxc";
		}
		return SUCCESS;
	}
	
	public String lotteryUp() throws Exception {
		long result = cdLotteryMgr.updatelotteryUp(lid, this.getOwner(),htype);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 上移
	 * 
	 * @return
	 * @throws Exception
	 */
	public String lotteryDown() throws Exception {
		long result = cdLotteryMgr.updatelotteryDown(lid, this.getOwner(),htype);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 前台展示抽奖活动列表
	 * @return
	 * @throws Exception
	 */
	public String findLotteryListByOwner() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		dto = (LotteryDto) cdLotteryMgr.findAllLottery(this.getOwner(),0,size);
		Gson gson = new Gson();
		out.print(gson.toJson(dto));
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 前台所有中奖记录信息显示
	 * @return
	 * @throws Exception
	 */
	public String findPrizeWinnerInfo() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		if (HyConfig.isRun()) {// 后台
			return null;	
		}else{
			VisitUser u = (VisitUser) ServletActionContext.getRequest().getSession().getAttribute("visitUser");
			dto = (LotteryDto) lotteryMgr.findPrizeWinnnerInformation(lid,u.getCd(),pageid,pageId,size);
			PrintWriter out = response.getWriter();
			Gson gson = new Gson();
			out.print(gson.toJson(dto.getLotteryPrizeList()));
			out.flush();
			out.close();
			return null;
		}
	}
	
	public void findRemainNum() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		VisitUser vu = (VisitUser) ServletActionContext.getRequest().getSession().getAttribute("visitUser");
		PrintWriter out = response.getWriter();
		long uid = vu.getUid();
		int type = vu.getCd();
		int tnum = 0;
		Lottery lo = lotteryMgr.findLotteryById(lid);
		if (vu != null && lo != null){
			LotteryUserDate lud = lotteryUserDateMgr.findUserDate(lid, uid, type);
			tnum=lo.getUserdaynum()-lud.getNum();//每天剩余的抽奖次数
		}
		out.print(tnum);
		out.flush();
		out.close();
	}
	
	public void findLottery() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Lottery lo = lotteryMgr.findLotteryById(lid);
		if(lo!=null){
			JSONObject obj=new JSONObject();
			obj.put("detail", lo.getDetail());
			out.print(obj.toString());
		}
		out.flush();
		out.close();
	
	}
	
	public int getPageId()
	{
		return pageId;
	}

	
	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	
	public LotteryDto getDto()
	{
		return dto;
	}

	
	public void setDto(LotteryDto dto)
	{
		this.dto = dto;
	}

	
	public String getHtype()
	{
		return htype;
	}

	
	public void setHtype(String htype)
	{
		this.htype = htype;
	}

	
	public String getName()
	{
		return name;
	}

	
	public void setName(String name)
	{
		this.name = name;
	}

	
	public String getDetail()
	{
		return detail;
	}

	
	public void setDetail(String detail)
	{
		this.detail = detail;
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

	
	public String getStartnote()
	{
		return startnote;
	}

	
	public void setStartnote(String startnote)
	{
		this.startnote = startnote;
	}

	
	public String getEndnote()
	{
		return endnote;
	}

	
	public void setEndnote(String endnote)
	{
		this.endnote = endnote;
	}

	
	public String getUsertype()
	{
		return usertype;
	}

	
	public void setUsertype(String usertype)
	{
		this.usertype = usertype;
	}

	
	public long getGzeid()
	{
		return gzeid;
	}

	
	public void setGzeid(long gzeid)
	{
		this.gzeid = gzeid;
	}

	
	public String getJf()
	{
		return jf;
	}

	
	public void setJf(String jf)
	{
		this.jf = jf;
	}

	
	public int getUsertotal()
	{
		return usertotal;
	}

	
	public void setUsertotal(int usertotal)
	{
		this.usertotal = usertotal;
	}

	
	public int getUserzjtotal()
	{
		return userzjtotal;
	}

	
	public void setUserzjtotal(int userzjtotal)
	{
		this.userzjtotal = userzjtotal;
	}

	
	public int getUserdaynum()
	{
		return userdaynum;
	}

	
	public void setUserdaynum(int userdaynum)
	{
		this.userdaynum = userdaynum;
	}

	
	public int getZjl()
	{
		return zjl;
	}

	
	public void setZjl(int zjl)
	{
		this.zjl = zjl;
	}

	
	public String getUserName()
	{
		return userName;
	}

	
	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	
	public String getUserPhone()
	{
		return userPhone;
	}

	
	public void setUserPhone(String userPhone)
	{
		this.userPhone = userPhone;
	}

	
	public String getUserEmail()
	{
		return userEmail;
	}

	
	public void setUserEmail(String userEmail)
	{
		this.userEmail = userEmail;
	}

	
	public String getUserLocation()
	{
		return userLocation;
	}

	
	public void setUserLocation(String userLocation)
	{
		this.userLocation = userLocation;
	}

	
	public long getLid()
	{
		return lid;
	}

	
	public void setLid(long lid)
	{
		this.lid = lid;
	}

	
	public List<CDSet> getCdsetList()
	{
		return cdsetList;
	}

	
	public void setCdsetList(List<CDSet> cdsetList)
	{
		this.cdsetList = cdsetList;
	}

	
	public String getImg()
	{
		return img;
	}

	
	public void setImg(String img)
	{
		this.img = img;
	}

	
	public long getEnid()
	{
		return enid;
	}

	
	public void setEnid(long enid)
	{
		this.enid = enid;
	}

	
	public CDLottery getCdLottery()
	{
		return cdLottery;
	}

	
	public void setCdLottery(CDLottery cdLottery)
	{
		this.cdLottery = cdLottery;
	}

	
	public int getSize()
	{
		return size;
	}

	
	public void setSize(int size)
	{
		this.size = size;
	}

	public long getPageid() {
		return pageid;
	}

	public void setPageid(long pageid) {
		this.pageid = pageid;
	}

	
	public int getDrawnum()
	{
		return drawnum;
	}

	
	public void setDrawnum(int drawnum)
	{
		this.drawnum = drawnum;
	}

	
	public int getWinnum()
	{
		return winnum;
	}

	
	public void setWinnum(int winnum)
	{
		this.winnum = winnum;
	}

	

	
	
}
