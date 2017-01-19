package com.huiyee.interact.lottery.action;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.model.Account;
import com.huiyee.interact.lottery.dto.LotteryDto;
import com.huiyee.interact.lottery.mgr.ILotteryMgr;
import com.opensymphony.xwork2.ActionContext;

public class LotteryYYYAction extends InteractModelAction
{
	private ILotteryMgr lotteryMgr;
	private LotteryDto dto;
	private int pageId = 1;

	// 添加抽奖的参数
	private String name;
	private String startTime;
	private String endTime;
	private String ruletype;
	private String usertype;
	private String guanzhu = "N";
	private String daren = "N";
	private String fensi = "N";
	private int fensi_num;
	private int usertotal;
	private int userdaynum;
	private int zjl;
	private String userName = "N";
	private String userNameValue;
	private String userPhone = "N";
	private String userPhoneValue;
	private String userEmail = "N";
	private String userEmailValue;
	private String userLocation = "N";
	private String userLocationValue;
	private String detail;
	private File img;
	private String imgFileName;
	private String imgContentType;
	
	// 编辑
	private long lid;
	private String imgurl;
	
	private long mid=10011;
	private String titlename;
	private String type;


	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	@Override
	public String execute() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		dto = (LotteryDto) lotteryMgr.findAllLottery(pageId, ownerid, "Y" ,IPageConstants.OWNER_INTERACT_SHOW);
		return SUCCESS;
	}

	public String del() throws Exception
	{
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
	
	public String helpGGL()throws Exception{
		return SUCCESS;
	}
	
	public String addYYY()throws Exception{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		long len = lotteryMgr.addLottery(ownerid, type, titlename);
		out.print(len);
		out.flush();
		out.close();
		return null;
	}

	public void setLotteryMgr(ILotteryMgr lotteryMgr)
	{
		this.lotteryMgr = lotteryMgr;
	}

	public LotteryDto getDto()
	{
		return dto;
	}

	public void setDto(LotteryDto dto)
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

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
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

	public String getRuletype()
	{
		return ruletype;
	}

	public void setRuletype(String ruletype)
	{
		this.ruletype = ruletype;
	}

	public String getUsertype()
	{
		return usertype;
	}

	public void setUsertype(String usertype)
	{
		this.usertype = usertype;
	}

	public String getGuanzhu()
	{
		return guanzhu;
	}

	public void setGuanzhu(String guanzhu)
	{
		this.guanzhu = guanzhu;
	}

	public String getDaren()
	{
		return daren;
	}

	public void setDaren(String daren)
	{
		this.daren = daren;
	}

	public String getFensi()
	{
		return fensi;
	}

	public void setFensi(String fensi)
	{
		this.fensi = fensi;
	}

	public int getFensi_num()
	{
		return fensi_num;
	}

	public int getUsertotal()
	{
		return usertotal;
	}

	public void setUsertotal(int usertotal)
	{
		this.usertotal = usertotal;
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

	public String getUserNameValue()
	{
		return userNameValue;
	}

	public void setUserNameValue(String userNameValue)
	{
		this.userNameValue = userNameValue;
	}

	public String getUserPhone()
	{
		return userPhone;
	}

	public void setUserPhone(String userPhone)
	{
		this.userPhone = userPhone;
	}

	public String getUserPhoneValue()
	{
		return userPhoneValue;
	}

	public void setUserPhoneValue(String userPhoneValue)
	{
		this.userPhoneValue = userPhoneValue;
	}

	public String getUserEmail()
	{
		return userEmail;
	}

	public void setUserEmail(String userEmail)
	{
		this.userEmail = userEmail;
	}

	public String getUserEmailValue()
	{
		return userEmailValue;
	}

	public void setUserEmailValue(String userEmailValue)
	{
		this.userEmailValue = userEmailValue;
	}

	public String getUserLocation()
	{
		return userLocation;
	}

	public void setUserLocation(String userLocation)
	{
		this.userLocation = userLocation;
	}

	public String getUserLocationValue()
	{
		return userLocationValue;
	}

	public void setUserLocationValue(String userLocationValue)
	{
		this.userLocationValue = userLocationValue;
	}

	public String getDetail()
	{
		return detail;
	}

	public void setDetail(String detail)
	{
		this.detail = detail;
	}

	public File getImg()
	{
		return img;
	}

	public void setImg(File img)
	{
		this.img = img;
	}

	public String getImgFileName()
	{
		return imgFileName;
	}

	public void setImgFileName(String imgFileName)
	{
		this.imgFileName = imgFileName;
	}

	public String getImgContentType()
	{
		return imgContentType;
	}

	public void setImgContentType(String imgContentType)
	{
		this.imgContentType = imgContentType;
	}

	public ILotteryMgr getLotteryMgr()
	{
		return lotteryMgr;
	}

	public long getLid()
	{
		return lid;
	}

	public void setLid(long lid)
	{
		this.lid = lid;
	}

	public String getImgurl()
	{
		return imgurl;
	}

	public void setImgurl(String imgurl)
	{
		this.imgurl = imgurl;
	}

	public void setFensi_num(String fensi_num)
	{
		if (StringUtils.isEmpty(fensi_num) || fensi_num.trim().length() == 0)
		{
			this.fensi_num = 0;
		}
		else
		{
			this.fensi_num = Integer.valueOf(fensi_num);
		}
	}

	public void setUsertotal(String usertotal)
	{
		if (StringUtils.isEmpty(usertotal) || usertotal.trim().length() == 0)
		{
			this.usertotal = 0;
		}
		else
		{
			this.usertotal = Integer.valueOf(usertotal);
		}
	}

	public void setUserdaynum(String userdaynum)
	{
		if (StringUtils.isEmpty(userdaynum) || userdaynum.trim().length() == 0)
		{
			this.userdaynum = 0;
		}
		else
		{
			this.userdaynum = Integer.valueOf(userdaynum);
		}
	}

	public void setZjl(String zjl)
	{
		if (StringUtils.isEmpty(zjl) || zjl.trim().length() == 0)
		{
			this.zjl = 0;
		}
		else
		{
			this.zjl = Integer.valueOf(zjl);
		}
	}

	public long getMid()
	{
		return mid;
	}

	public void setMid(long mid)
	{
		this.mid = mid;
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
