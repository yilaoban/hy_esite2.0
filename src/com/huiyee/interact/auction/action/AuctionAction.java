package com.huiyee.interact.auction.action;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.service.FileUploadService;
import com.huiyee.interact.auction.dto.AuctionDto;
import com.huiyee.interact.auction.mgr.IAuctionMgr;
import com.huiyee.interact.auction.model.Auction;
import com.opensymphony.xwork2.ActionContext;

public class AuctionAction extends InteractModelAction
{
	private int pageId = 1;
	private AuctionDto dto;
	private IAuctionMgr auctionMgr;
	private long auid;

	private String name;
	private String startTime;
	private String endTime;
	private String detail;
	private String startbalance;
	private String addbalance;
	private String url;
	private String addsecond;
	private File img;
	private String imgFileName;
	private String imgContentType;
	private String imgurl;
	
	private String startnote;
	private String endnote;
	private String userName = "N";
	private String userPhone = "N";
	private String userEmail = "N";
	private String userLocation = "N";
	private String logined;
	
	private long mid=10007;
	
	private String titlename;
	
	@Override
	public String execute() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		dto = (AuctionDto) auctionMgr.findAuctionList(pageId, ownerid, IPageConstants.OWNER_INTERACT_SHOW);
		return SUCCESS;
	}

	public String save() throws Exception
	{
		return SUCCESS;
	}

	public String saveSub() throws Exception
	{
		
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();

		Auction auction = new Auction();
		auction.setOwner(ownerid);
		auction.setTitle(name);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		auction.setStarttimeDate(sdf.parse(startTime));
		auction.setEndtimeDate(sdf.parse(endTime));
		auction.setProendTime(sdf.parse(endTime));
		auction.setDescription(detail);
		auction.setStartbalance(Integer.parseInt(startbalance));
		auction.setAddbalance(Integer.parseInt(addbalance));
		auction.setUrl(url);
		auction.setAddsecond(StringUtils.isEmpty(addsecond) ? 0 : Integer.parseInt(addsecond));
		auction.setStartnote(startnote);
		auction.setEndnote(endnote);
		auction.setUserName(userName);
		auction.setUserEmail(userEmail);
		auction.setUserLocation(userLocation);
		auction.setUserPhone(userPhone);
		if (img != null)
		{
			String simgfileName = FileUploadService.createFileName(imgFileName);
			String path = FileUploadService.getFilePath(IPageConstants.TYPE_INTERACT, ownerid, "10007");
			String saveResult = FileUploadService.saveFile(img, path, simgfileName);
			if (saveResult == null)
			{
				this.addFieldError("error", "上传图片出错");
				return "failed";
			}
			auction.setSimg(saveResult);
		}
		long auid = auctionMgr.saveAuction(auction,IPageConstants.OWNER_INTERACT_SHOW);
		
		return SUCCESS;
	}

	public String edit() throws Exception
	{
		dto = new AuctionDto();
		Auction auction = auctionMgr.findAuctionById(auid);
		dto.setAuction(auction);
		return SUCCESS;
	}

	public String editSub() throws Exception
	{
		if (StringUtils.isEmpty(name))
		{
			this.addFieldError("error", "标题不能为空");
		}
		if (startTime == null)
		{
			this.addFieldError("error", "开始时间不能为空");

		}
		if (endTime == null)
		{
			this.addFieldError("error", "结束时间不能为空");
		}

		if (StringUtils.isEmpty(startbalance) || Integer.parseInt(startbalance) <= 0)
		{
			this.addFieldError("error", "最低价格不能为空");
		}
		if (StringUtils.isEmpty(addbalance) || Integer.parseInt(addbalance) <= 0)
		{
			this.addFieldError("error", "增加价格不能为空");
		}

		if (this.getFieldErrors().get("error") != null)
		{
			return "failed";
		}
		else
		{
			Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
			long ownerid = account.getOwner().getId();
			Auction auction = new Auction();
			auction.setTitle(name);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			auction.setStarttimeDate(sdf.parse(startTime));
			auction.setEndtimeDate(sdf.parse(endTime));
			auction.setDescription(detail);
			auction.setStartbalance(Integer.parseInt(startbalance));
			auction.setAddbalance(Integer.parseInt(addbalance));
			auction.setUrl(url);
			auction.setAddsecond(StringUtils.isEmpty(addsecond) ? 0 : Integer.parseInt(addsecond));
			auction.setStartnote(startnote);
			auction.setEndnote(endnote);
			auction.setUserName(userName);
			auction.setUserEmail(userEmail);
			auction.setUserLocation(userLocation);
			auction.setUserPhone(userPhone);
			if (img != null)
			{
				String simgfileName = FileUploadService.createFileName(imgFileName);
				String path = FileUploadService.getFilePath(IPageConstants.TYPE_INTERACT, ownerid, "10007");
				String saveResult = FileUploadService.saveFile(img, path, simgfileName);
				if (saveResult == null)
				{
					this.addFieldError("error", "上传图片出错");
					return "failed";
				}
				auction.setSimg(saveResult);
			}
			else
			{
				auction.setSimg(imgurl);
			}

			int result = auctionMgr.updateAuction(auction, auid, ownerid);
		}
		return SUCCESS;
	}

	public String del() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int result = auctionMgr.updateStatus(auid, "D", account.getOwner().getId());
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}
	
	public String helpAuction()throws Exception{
		return SUCCESS ;
	}
	
	public String addAuction()throws Exception{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		long len = auctionMgr.addAuction(ownerid, titlename);
		out.print(len);
		out.flush();
		out.close();
		return null;
	}

	public int getPageId()
	{
		return pageId;
	}

	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	public AuctionDto getDto()
	{
		return dto;
	}

	public void setDto(AuctionDto dto)
	{
		this.dto = dto;
	}

	public void setAuctionMgr(IAuctionMgr auctionMgr)
	{
		this.auctionMgr = auctionMgr;
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

	public String getDetail()
	{
		return detail;
	}

	public void setDetail(String detail)
	{
		this.detail = detail;
	}

	public String getStartbalance()
	{
		return startbalance;
	}

	public void setStartbalance(String startbalance)
	{
		this.startbalance = startbalance;
	}

	public String getAddbalance()
	{
		return addbalance;
	}

	public void setAddbalance(String addbalance)
	{
		this.addbalance = addbalance;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getAddsecond()
	{
		return addsecond;
	}

	public void setAddsecond(String addsecond)
	{
		this.addsecond = addsecond;
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

	public IAuctionMgr getAuctionMgr()
	{
		return auctionMgr;
	}

	public long getAuid()
	{
		return auid;
	}

	public void setAuid(long auid)
	{
		this.auid = auid;
	}

	public String getImgurl()
	{
		return imgurl;
	}

	public void setImgurl(String imgurl)
	{
		this.imgurl = imgurl;
	}

	public long getMid()
	{
		return mid;
	}

	public void setMid(long mid)
	{
		this.mid = mid;
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

	public String getLogined()
	{
		return logined;
	}

	public void setLogined(String logined)
	{
		this.logined = logined;
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
