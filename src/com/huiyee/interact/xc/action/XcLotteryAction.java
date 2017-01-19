
package com.huiyee.interact.xc.action;

import java.io.File;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.dto.DaohangDto;
import com.huiyee.esite.mgr.IMoveToGroupMgr;
import com.huiyee.esite.mgr.IWeiXinMgr;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.WxGroup;
import com.huiyee.esite.util.ClientUserIp;
import com.huiyee.esite.util.HttpRequestDeviceUtils;
import com.huiyee.interact.xc.dto.XcLotteryDto;
import com.huiyee.interact.xc.dto.XcSiftDto;
import com.huiyee.interact.xc.mgr.IXcLotteryMgr;
import com.huiyee.interact.xc.model.CheckinConfig;
import com.huiyee.interact.xc.model.CommentConfig;
import com.huiyee.interact.xc.model.HdEntity;
import com.huiyee.interact.xc.model.InviteConfig;
import com.huiyee.interact.xc.model.LotteryConfig;
import com.huiyee.interact.xc.model.Xc;
import com.huiyee.interact.xc.model.XcExport;
import com.huiyee.interact.xc.model.XcSendRecord;
import com.huiyee.weixin.model.WxMp;
import com.opensymphony.xwork2.ActionContext;

public class XcLotteryAction extends InteractModelAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long xcid;
	// private long id;
	private int pageId = 1;
	private XcLotteryDto dto;
	private IXcLotteryMgr xcLotteryMgr;
	private Xc xc;
	private long mid = 10014;
	private String result = "N";
	private LotteryConfig lconfig;
	private InviteConfig inviteConfig;
	private CheckinConfig checkinConfig;
	private CommentConfig commentCongig;
	private int utype;
	private int num;
	private int startnum;
	private String ids;
	private String checked;// 审核通过"Y" 不通过 "N"

	private File userfile;
	private String userfileFileName;
	private String userfileContentType;
	private int msg = -10000;

	private String nickname;
	private String topType = "A";

	private long dpmsiteid; // 大屏幕站点id
	private long xcsiteid;// 微现场站点id 对应siteid 只在新增站点使用该属性
	private long siteid;
	private long xcSiteid;// xcsite表的id

	private List<HdEntity> entitys;
	private XcSiftDto siftDto;

	// 微现场设置 仅关注人可签到
	private long msgid;// crm msgid;
	private String furl;// 失败跳转的url

	private DaohangDto dh;
	
	private long mpid;
	private IMoveToGroupMgr moveToGroupMgr;
	private IWeiXinMgr weiXinMgr;
	private List<WxGroup> wxGroupList;
	
	
	public long getMpid()
	{
		return mpid;
	}

	
	public void setMpid(long mpid)
	{
		this.mpid = mpid;
	}

	
	public void setWeiXinMgr(IWeiXinMgr weiXinMgr)
	{
		this.weiXinMgr = weiXinMgr;
	}

	@Override
	public String execute() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		dto = (XcLotteryDto) xcLotteryMgr.findXcList(ownerid, pageId);
		return SUCCESS;
	}

	/**
	 * 新增微现场 修改大屏幕 微现场站点
	 */
	public String addXcLottery() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		dto = (XcLotteryDto) xcLotteryMgr.findSiteList(ownerid);
		return SUCCESS;
	}

	public String saveXcLottery() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		long resultid = xcLotteryMgr.saveXcLottery(xc, ownerid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		if (resultid > 0)
		{
			result = "Y";
			xcLotteryMgr.saveXcSite(resultid, dpmsiteid, "D");
			xcLotteryMgr.saveXcSite(resultid, xcsiteid, "W");
		}
		out.write(result);
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 修改现场名称
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updatexcName() throws Exception
	{
		int len = xcLotteryMgr.updateXcName(xc);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		if (len > 0)
		{
			result = "Y";
		}
		out.write(result);
		out.flush();
		out.close();
		return null;

	}

	/**
	 * 修改大屏幕站点
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateBigScreenSite() throws Exception
	{
		int len = xcLotteryMgr.updateBigScreenSite(dpmsiteid, xcSiteid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		if (len > 0)
		{
			result = "Y";
		}
		out.write(result);
		out.flush();
		out.close();
		return null;

	}

	public String updateXcSite() throws Exception
	{
		int len = xcLotteryMgr.updateBigScreenSite(siteid, xcSiteid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		if (len > 0)
		{
			result = "Y";
		}
		out.write(result);
		out.flush();
		out.close();
		return null;
	}

	public String editXcLottery() throws Exception
	{
		xc = xcLotteryMgr.findXcById(xcid);
		entitys = xcLotteryMgr.findXcEntitys(xcid);
		dh = pageCompose.composeDhByXcid(xcid);
		if (xc != null)
		{
			if (xc.getInviteconfig() != null)
			{
				inviteConfig = xc.getInviteconfig();
			}
			if (xc.getCommentconfig() != null)
			{
				commentCongig = xc.getCommentconfig();
			}
			if (xc.getLotteryconfig() != null)
			{
				lconfig = xc.getLotteryconfig();
			}
			if (xc.getCheckinconfig() != null)
			{
				checkinConfig = xc.getCheckinconfig();
			}
		}
		return SUCCESS;
	}

	public String updateXcLottery() throws Exception
	{
		dh = pageCompose.composeDhByXcid(xcid);
		xc.setInviteconfig(inviteConfig);
		xc.setCheckinconfig(checkinConfig);
		xc.setCommentconfig(commentCongig);
		xc.setLotteryconfig(lconfig);
		List<XcSendRecord> list = null;
		if ("Y".equals(xc.getNeedinvite()) && userfile != null)
		{
			list = uploadSub();
			if (list.size() > 0)
			{
				HttpServletRequest request = ServletActionContext.getRequest();
				String userAgent = request.getHeader("user-agent");
				String ip = ClientUserIp.getIpAddr(request);
				int result = xcLotteryMgr.updateXcLotteryAndUpload(xcid, xc, list, ip, HttpRequestDeviceUtils.getMediaDevice(userAgent));
				msg = result;
			}
		} else
		{
			int len = xcLotteryMgr.updateXcLottery(xcid, xc);
			msg = 0;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.write(new Gson().toJson(msg));
		out.flush();
		out.close();
		return null;
	}

	public String delXcLottery() throws Exception
	{
		int len = xcLotteryMgr.delXcLottery(xcid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		if (len > 0)
		{
			result = "Y";
		}
		out.write(result);
		out.flush();
		out.close();
		return null;
	}

	public String toRestartXcLottery() throws Exception
	{
		return SUCCESS;
	}

	/**
	 * 首次 再次启动启动抽奖
	 * 
	 * @return
	 * @throws Exception
	 */
	public String restartXcLottery() throws Exception
	{
		xc = xcLotteryMgr.getXcById(xcid);
		if (xc != null)
		{
			if (xc.getLotteryconfig() != null)
			{
				lconfig = xc.getLotteryconfig();
				lconfig.setStarted(1);
				lconfig.setNum(num);
				lconfig.setStartnum(lconfig.getStartnum() + 1);
			}
		}
		Gson gson = new Gson();
		String lotteryconfig = gson.toJson(lconfig);
		int len = xcLotteryMgr.updateXcLottery(xcid, lotteryconfig);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		if (len > 0)
		{
			result = "Y";
		}
		out.write(result);
		out.flush();
		out.close();
		return null;
	}

	public String inviteDetail() throws Exception
	{
		if (siftDto == null)
		{
			siftDto = new XcSiftDto();
		}
		dto = (XcLotteryDto) xcLotteryMgr.findInviteRecordList(xcid, pageId, nickname, siftDto);
		return SUCCESS;
	}

	public String checkinDetail() throws Exception
	{
		dto = (XcLotteryDto) xcLotteryMgr.findCheckinRecordList(xcid, utype, pageId);
		return SUCCESS;
	}
	
	public String xcMoveToGroup() throws Exception{
		result = null;
		WxMp woa = weiXinMgr.updateFindWxMp(this.getOwner(), 0);
		if (woa != null) {
			mpid = woa.getId();
			wxGroupList = moveToGroupMgr.findWxGroupList(mpid);
		}
		return SUCCESS;
	}

	public String commentDetail() throws Exception
	{
		dto = (XcLotteryDto) xcLotteryMgr.findCommentRecordList(xcid, utype, pageId);
		return SUCCESS;
	}

	public String lotteryDetail() throws Exception
	{
		dto = (XcLotteryDto) xcLotteryMgr.findLotteryRecordList(xcid, utype, nickname, topType, pageId);
		return SUCCESS;
	}

	public String bathPass() throws Exception
	{
		String[] allids = ids.split(";");
		for (int i = 0; i < allids.length; i++)
		{
			long crid = Long.parseLong(allids[i]);
			xcLotteryMgr.updateCommentRecordStatus(crid, checked);
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		result = "Y";
		out.write(result);
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 需要审核的列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String auditList() throws Exception
	{
		dto = (XcLotteryDto) xcLotteryMgr.findAuditList(xcid, utype, pageId);
		return SUCCESS;
	}

	private List<XcSendRecord> uploadSub() throws Exception
	{

		List<XcSendRecord> list = new ArrayList<XcSendRecord>();
		if (userfile != null && (userfileFileName.endsWith("xls") || userfileFileName.endsWith("xlsx")))
		{
			try
			{
				Workbook workboox = WorkbookFactory.create(userfile);
				Sheet sheet = workboox.getSheetAt(0);
				int allLines = sheet.getPhysicalNumberOfRows();
				for (int i = 0; i < allLines; i++)
				{
					Row row = sheet.getRow(i);
					if (row.getLastCellNum() != 3)
					{
						result = "字段没有对应,请检查";
					} else
					{
						XcSendRecord xc = new XcSendRecord();
						row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
						row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
						row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
						String index = row.getCell(0).getRichStringCellValue().getString();
						xc.setIndex(Integer.parseInt(index));
						String username = row.getCell(1).getRichStringCellValue().getString();
						xc.setUsername(username);
						String nickname = row.getCell(2).getRichStringCellValue().getString();
						xc.setNickname(nickname);
						list.add(xc);
					}
				}
			} catch (RuntimeException e)
			{
				e.printStackTrace();
				result = "Excel读取出错";
			}
		}
		return list;
	}

	public String export() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		List<XcExport> exportlist = xcLotteryMgr.findWinner(xcid, account.getOwner().getId());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/csv;charset=GBK");
		response.setHeader("Content-Disposition", "attachment;filename=" + encodingFileName() + ".csv");
		PrintWriter out = response.getWriter();
		if (exportlist != null && exportlist.size() != 0)
		{
			out.println("第几次抽奖,嘉宾姓名,嘉宾昵称,摇多少次,中奖时间");
			for (XcExport str : exportlist)
			{
				out.println(str);
			}
		} else
		{
			out.print("无相关信息");
		}
		return null;
	}

	private String encodingFileName()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String time = sdf.format(new Date());
		String result = null;
		result = "微现场抽奖导出" + time;
		try
		{
			return new String(result.getBytes("gb2312"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e)
		{
			return time;
		}
	}
	

	public int getPageId()
	{
		return pageId;
	}

	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	public XcLotteryDto getDto()
	{
		return dto;
	}

	public void setDto(XcLotteryDto dto)
	{
		this.dto = dto;
	}

	public IXcLotteryMgr getXcLotteryMgr()
	{
		return xcLotteryMgr;
	}

	public void setXcLotteryMgr(IXcLotteryMgr xcLotteryMgr)
	{
		this.xcLotteryMgr = xcLotteryMgr;
	}

	public long getMid()
	{
		return mid;
	}

	public void setMid(long mid)
	{
		this.mid = mid;
	}

	public Xc getXc()
	{
		return xc;
	}

	public void setXc(Xc xc)
	{
		this.xc = xc;
	}

	public String getResult()
	{
		return result;
	}

	public void setResult(String result)
	{
		this.result = result;
	}

	public LotteryConfig getLconfig()
	{
		return lconfig;
	}

	public void setLconfig(LotteryConfig lconfig)
	{
		this.lconfig = lconfig;
	}

	public CheckinConfig getCheckinConfig()
	{
		return checkinConfig;
	}

	public void setCheckinConfig(CheckinConfig checkinConfig)
	{
		this.checkinConfig = checkinConfig;
	}

	public CommentConfig getCommentCongig()
	{
		return commentCongig;
	}

	public void setCommentCongig(CommentConfig commentCongig)
	{
		this.commentCongig = commentCongig;
	}

	public int getUtype()
	{
		return utype;
	}

	public void setUtype(int utype)
	{
		this.utype = utype;
	}

	public int getNum()
	{
		return num;
	}

	public void setNum(int num)
	{
		this.num = num;
	}

	public int getStartnum()
	{
		return startnum;
	}

	public void setStartnum(int startnum)
	{
		this.startnum = startnum;
	}

	public String getIds()
	{
		return ids;
	}

	public void setIds(String ids)
	{
		this.ids = ids;
	}

	public String getChecked()
	{
		return checked;
	}

	public void setChecked(String checked)
	{
		this.checked = checked;
	}

	public File getUserfile()
	{
		return userfile;
	}

	public void setUserfile(File userfile)
	{
		this.userfile = userfile;
	}

	public String getUserfileFileName()
	{
		return userfileFileName;
	}

	public void setUserfileFileName(String userfileFileName)
	{
		this.userfileFileName = userfileFileName;
	}

	public String getUserfileContentType()
	{
		return userfileContentType;
	}

	public void setUserfileContentType(String userfileContentType)
	{
		this.userfileContentType = userfileContentType;
	}

	public int getMsg()
	{
		return msg;
	}

	public void setMsg(int msg)
	{
		this.msg = msg;
	}

	public long getXcid()
	{
		return xcid;
	}

	public void setXcid(long xcid)
	{
		this.xcid = xcid;
	}

	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	public String getTopType()
	{
		return topType;
	}

	public void setTopType(String topType)
	{
		this.topType = topType;
	}

	public String getNickname()
	{
		return nickname;
	}

	public long getDpmsiteid()
	{
		return dpmsiteid;
	}

	public void setDpmsiteid(long dpmsiteid)
	{
		this.dpmsiteid = dpmsiteid;
	}

	public long getXcsiteid()
	{
		return xcsiteid;
	}

	public void setXcsiteid(long xcsiteid)
	{
		this.xcsiteid = xcsiteid;
	}

	public long getSiteid()
	{
		return siteid;
	}

	public void setSiteid(long siteid)
	{
		this.siteid = siteid;
	}

	public long getXcSiteid()
	{
		return xcSiteid;
	}

	public void setXcSiteid(long xcSiteid)
	{
		this.xcSiteid = xcSiteid;
	}

	public List<HdEntity> getEntitys()
	{
		return entitys;
	}

	public void setEntitys(List<HdEntity> entitys)
	{
		this.entitys = entitys;
	}

	public long getMsgid()
	{
		return msgid;
	}

	public void setMsgid(long msgid)
	{
		this.msgid = msgid;
	}

	public DaohangDto getDh()
	{
		return pageCompose.composeDhByXcid(xcid);
	}

	public void setDh(DaohangDto dh)
	{
		this.dh = dh;
	}

	public XcSiftDto getSiftDto()
	{
		return siftDto;
	}

	public void setSiftDto(XcSiftDto siftDto)
	{
		this.siftDto = siftDto;
	}

	public InviteConfig getInviteConfig()
	{
		return inviteConfig;
	}

	public void setInviteConfig(InviteConfig inviteConfig)
	{
		this.inviteConfig = inviteConfig;
	}

	public String getFurl()
	{
		return furl;
	}

	public void setFurl(String furl)
	{
		this.furl = furl;
	}
	
	public List<WxGroup> getWxGroupList()
	{
		return wxGroupList;
	}

	
	public void setWxGroupList(List<WxGroup> wxGroupList)
	{
		this.wxGroupList = wxGroupList;
	}

	
	public void setMoveToGroupMgr(IMoveToGroupMgr moveToGroupMgr)
	{
		this.moveToGroupMgr = moveToGroupMgr;
	}

}
