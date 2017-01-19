package com.huiyee.interact.lottery.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.mgr.IWeiXinMgr;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.CrmWxHongbaoCheck;
import com.huiyee.esite.model.WxHbCheckLog;
import com.huiyee.esite.util.ClientUserIp;
import com.huiyee.interact.lottery.dto.LotteryDto;
import com.huiyee.interact.lottery.dto.Usertype;
import com.huiyee.interact.lottery.mgr.ILotteryMgr;
import com.huiyee.interact.lottery.mgr.ILotteryPrizeMgr;
import com.huiyee.interact.lottery.mgr.ISinaGroupMgr;
import com.huiyee.interact.lottery.mgr.IWxHongbaoMgr;
import com.huiyee.interact.lottery.model.Lottery;
import com.huiyee.interact.lottery.model.SinaGroup;
import com.huiyee.weixin.model.WxMp;
import com.opensymphony.xwork2.ActionContext;

public class LotteryZhuanPanAction extends InteractModelAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ILotteryMgr lotteryMgr;
	private ISinaGroupMgr sinaGroupMgr;
	private IWeiXinMgr weiXinMgr;
	private IWxHongbaoMgr wxHongbaoMgr;
	private ILotteryPrizeMgr lotteryPrizeMgr;

	private LotteryDto dto;
	private int pageId = 1;

	private String htype; // 用来区分抽奖类型
	// 添加抽奖的参数
	private String name;
	private String detail;
	private String startTime;
	private String endTime;

	private String usertype;
	private String jf;
	private String wbgroup = "N";
	private long wbgroupid;
	// private String wxgzuser = "N";
	private int wxg = 0;
	private int usertotal;
	private int userzjtotal;
	private int userdaynum;
	private int zjl;
	private String sharetype;
	private String sharecontent;
	private File sharepic;
	private String sharepicFileName;
	private String sharepicContentType;
	private String sharepicimage;
	// private String sharewxtype;

	private String userName = "N";
	private String userPhone = "N";
	private String userEmail = "N";
	private String userLocation = "N";
	private String wbgroupname;
	private String logined;
	private String startnote;
	private String endnote;
	// 编辑
	private long lid;
	private String imgurl;
	private CrmWxHongbaoCheck hongbao;
	private int total;
	private String result;
	private String reason;
	private long ckid;
	private String idStr;
	private List<WxHbCheckLog> logs;
	private long mid = 10003;
	private List<SinaGroup> sinaGroupList;
	private long gzeid;
	
	private int drawnum;
	private int winnum;
	
	@Override
	public String execute() throws Exception {
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		dto = (LotteryDto) lotteryMgr.findAllLottery(pageId, ownerid, "Z", IPageConstants.OWNER_INTERACT_SHOW);
		return SUCCESS;
	}

	public String save() throws Exception {
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
		if ("S".equals(usertype)) {
			if ("N".equals(wbgroup) && 0 == wxg) {
				this.addFieldError("error4", "抽奖资格未设置");
			} else if ("Y".equals(wbgroup) && wbgroupid == 0) {
				this.addFieldError("error4", "抽奖资格易博祖未选择");
			}
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

			Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
			long ownerid = account.getOwner().getId();
			l.setOwner(ownerid);
			l.setType(htype);
			long lid = lotteryMgr.saveLottery(l, IPageConstants.OWNER_INTERACT_SHOW);
		}
		return SUCCESS;
	}

	public String edit() throws Exception {
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		dto = (LotteryDto) lotteryMgr.findLotteryById(lid, ownerid);
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
		if ("S".equals(usertype)) {
			if ("N".equals(wbgroup) && 0 == wxg) {
				this.addFieldError("error4", "抽奖资格未设置");
			} else if ("Y".equals(wbgroup) && wbgroupid == 0) {
				this.addFieldError("error4", "抽奖资格易博祖未选择");
			}
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
		}
		if (redirectXc != 0) {
			// 从微现场点过来的 没有互动tool 返回按钮返回微现场
			return "fromxc";
		}
		return SUCCESS;
	}

	public String showwbgroup() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		long ownerid = account.getOwner().getId();
		sinaGroupList = sinaGroupMgr.findWbGroupListByOwner(ownerid);
		return SUCCESS;
	}

	public String del() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int result = lotteryMgr.updateStatus(lid, "D", account.getOwner().getId());
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 审核
	 */
	public String checkLottery() throws Exception {
		hongbao = wxHongbaoMgr.findCrmWxHongbaoCheckByLid(lid, 1);
		if (hongbao != null) {
			total = hongbao.getTotal();
		} else {
			total = lotteryPrizeMgr.findLotteryTotalPrizeById(lid);
		}
		return SUCCESS;
	}

	/**
	 * 提交审核
	 * 
	 * @return
	 * @throws Exception
	 */
	public String checkLotterySub() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ip = ClientUserIp.getIpAddr(request);
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long accountid = account.getId();
		String accountname = account.getUsername();
		WxMp woa = weiXinMgr.updateFindWxMp(this.getOwner(), 0);
		if (woa != null)
		{
		    long mpid = woa.getId();
			int type = 1;
			int res = wxHongbaoMgr.saveHongbaoCheck(lid, total, reason, accountid, type, accountname, ip, ckid, mpid);
			if (res == 1)
			{
				result = "Y";
			}
		} else
		{
			result = "N";
		}
		return SUCCESS;
	}

	public String showLog() throws Exception {
		int rows = 10;
		int start = (pageId - 1) * rows;
		logs = wxHongbaoMgr.findWxHbCheckLogListBylid(lid, start, rows);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(logs));
		out.flush();
		out.close();
		return null;
	}

	public String helpDZP() throws Exception {
		result = "Y";
		return SUCCESS;
	}

	public void setLotteryMgr(ILotteryMgr lotteryMgr) {
		this.lotteryMgr = lotteryMgr;
	}

	public LotteryDto getDto() {
		return dto;
	}

	public void setDto(LotteryDto dto) {
		this.dto = dto;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public int getUsertotal() {
		return usertotal;
	}

	public void setUsertotal(int usertotal) {
		this.usertotal = usertotal;
	}

	public int getUserdaynum() {
		return userdaynum;
	}

	public void setUserdaynum(int userdaynum) {
		this.userdaynum = userdaynum;
	}

	public int getZjl() {
		return zjl;
	}

	public void setZjl(int zjl) {
		this.zjl = zjl;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserLocation() {
		return userLocation;
	}

	public void setUserLocation(String userLocation) {
		this.userLocation = userLocation;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public ILotteryMgr getLotteryMgr() {
		return lotteryMgr;
	}

	public long getLid() {
		return lid;
	}

	public void setLid(long lid) {
		this.lid = lid;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public void setUsertotal(String usertotal) {
		if (StringUtils.isEmpty(usertotal) || usertotal.trim().length() == 0) {
			this.usertotal = 0;
		} else {
			this.usertotal = Integer.valueOf(usertotal);
		}
	}

	public void setUserdaynum(String userdaynum) {
		if (StringUtils.isEmpty(userdaynum) || userdaynum.trim().length() == 0) {
			this.userdaynum = 0;
		} else {
			this.userdaynum = Integer.valueOf(userdaynum);
		}
	}

	public void setZjl(String zjl) {
		if (StringUtils.isEmpty(zjl) || zjl.trim().length() == 0) {
			this.zjl = 0;
		} else {
			this.zjl = Integer.valueOf(zjl);
		}
	}

	public String getHtype() {
		return htype;
	}

	public void setHtype(String htype) {
		this.htype = htype;
	}

	public String getWbgroup() {
		return wbgroup;
	}

	public void setWbgroup(String wbgroup) {
		this.wbgroup = wbgroup;
	}

	public long getWbgroupid() {
		return wbgroupid;
	}

	public void setWbgroupid(long wbgroupid) {
		this.wbgroupid = wbgroupid;
	}

	public int getWxg() {
		return wxg;
	}

	public void setWxg(int wxg) {
		this.wxg = wxg;
	}

	public String getSharetype() {
		return sharetype;
	}

	public void setSharetype(String sharetype) {
		this.sharetype = sharetype;
	}

	public String getSharecontent() {
		return sharecontent;
	}

	public void setSharecontent(String sharecontent) {
		this.sharecontent = sharecontent;
	}

	public File getSharepic() {
		return sharepic;
	}

	public void setSharepic(File sharepic) {
		this.sharepic = sharepic;
	}

	public String getSharepicFileName() {
		return sharepicFileName;
	}

	public void setSharepicFileName(String sharepicFileName) {
		this.sharepicFileName = sharepicFileName;
	}

	public String getSharepicContentType() {
		return sharepicContentType;
	}

	public void setSharepicContentType(String sharepicContentType) {
		this.sharepicContentType = sharepicContentType;
	}

	public String getSharepicimage() {
		return sharepicimage;
	}

	public void setSharepicimage(String sharepicimage) {
		this.sharepicimage = sharepicimage;
	}

	public String getStartnote() {
		return startnote;
	}

	public void setStartnote(String startnote) {
		this.startnote = startnote;
	}

	public String getEndnote() {
		return endnote;
	}

	public void setEndnote(String endnote) {
		this.endnote = endnote;
	}

	public long getMid() {
		return mid;
	}

	public void setMid(long mid) {
		this.mid = mid;
	}

	public String getLogined() {
		return logined;
	}

	public void setLogined(String logined) {
		this.logined = logined;
	}

	public List<SinaGroup> getSinaGroupList() {
		return sinaGroupList;
	}

	public void setSinaGroupList(List<SinaGroup> sinaGroupList) {
		this.sinaGroupList = sinaGroupList;
	}

	public String getWbgroupname() {
		return wbgroupname;
	}

	public void setWbgroupname(String wbgroupname) {
		this.wbgroupname = wbgroupname;
	}

	public ISinaGroupMgr getSinaGroupMgr() {
		return sinaGroupMgr;
	}

	public void setSinaGroupMgr(ISinaGroupMgr sinaGroupMgr) {
		this.sinaGroupMgr = sinaGroupMgr;
	}

	public String getJf() {
		return jf;
	}

	public void setJf(String jf) {
		this.jf = jf;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public void setWeiXinMgr(IWeiXinMgr weiXinMgr)
	{
		this.weiXinMgr = weiXinMgr;
	}

	public void setWxHongbaoMgr(IWxHongbaoMgr wxHongbaoMgr) {
		this.wxHongbaoMgr = wxHongbaoMgr;
	}

	public CrmWxHongbaoCheck getHongbao() {
		return hongbao;
	}

	public void setHongbao(CrmWxHongbaoCheck hongbao) {
		this.hongbao = hongbao;
	}

	public long getCkid() {
		return ckid;
	}

	public void setCkid(long ckid) {
		this.ckid = ckid;
	}

	public void setLotteryPrizeMgr(ILotteryPrizeMgr lotteryPrizeMgr) {
		this.lotteryPrizeMgr = lotteryPrizeMgr;
	}

	public List<WxHbCheckLog> getLogs() {
		return logs;
	}

	public void setLogs(List<WxHbCheckLog> logs) {
		this.logs = logs;
	}
	
	public String getIdStr()
	{
		return idStr;
	}

	
	public void setIdStr(String idStr)
	{
		this.idStr = idStr;
	}

	
	public int getUserzjtotal()
	{
		return userzjtotal;
	}

	
	public void setUserzjtotal(int userzjtotal)
	{
		this.userzjtotal = userzjtotal;
	}

	
	public long getGzeid()
	{
		return gzeid;
	}

	
	public void setGzeid(long gzeid)
	{
		this.gzeid = gzeid;
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
