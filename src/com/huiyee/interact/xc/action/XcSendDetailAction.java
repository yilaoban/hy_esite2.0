package com.huiyee.interact.xc.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.dto.DaohangDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.ClientUserIp;
import com.huiyee.esite.util.HttpRequestDeviceUtils;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.interact.xc.dto.XcSendRecordDto;
import com.huiyee.interact.xc.dto.XcSiftDto;
import com.huiyee.interact.xc.mgr.IXcMgr;
import com.huiyee.interact.xc.model.Xc;
import com.huiyee.interact.xc.model.XcSendRecord;
import com.opensymphony.xwork2.ActionContext;

public class XcSendDetailAction extends InteractModelAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long xcid;
	private IXcMgr xcMgr;
	private XcSendRecordDto dto;
	private int pageId = 1;
	private String username;

	private String content;
	private File userfile;
	private String userfileFileName;
	private String userfileContentType;
	private String result;
	private int hypage;

	private XcSiftDto siftDto;
	private long recordId;
	private String nickname;
	private DaohangDto dh;
	@Override
	public String execute() throws Exception
	{
		if (siftDto == null)
		{
			siftDto = new XcSiftDto();
		}
		dto = (XcSendRecordDto) xcMgr.findSendDetail(xcid, username, pageId, siftDto);
		return SUCCESS;
	}

	public String uploadIndex() throws Exception
	{
		return SUCCESS;
	}

	public String uploadSub() throws Exception
	{
		if (userfile == null && StringUtils.isEmpty(content))
		{
			result = "操作完成,没有新增嘉宾";
		}
		else
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
						}
						else
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
				}
				catch (RuntimeException e)
				{
					e.printStackTrace();
					result = "Excel读取出错";
				}
			}

			HttpServletRequest request = ServletActionContext.getRequest();
			String userAgent = request.getHeader("user-agent");
			String ip = ClientUserIp.getIpAddr(request);
			result = xcMgr.addSendRecordSub(xcid, list, content, ip, HttpRequestDeviceUtils.getMediaDevice(userAgent));
			Pattern pattern = Pattern.compile("[0-9]+");
			Matcher matcher = pattern.matcher(result);
			if (matcher.matches())
			{
				result = "Y";
			}
		}
		return SUCCESS;
	}

	public String checkTeyue() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();

		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if (vu == null && HyConfig.isRun())
		{
			out.print("-11000");
			out.flush();
			out.close();
			return null;
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		String userAgent = request.getHeader("user-agent");
		String ip = ClientUserIp.getIpAddr(request);
		Xc xc = xcMgr.getXcByIdMc(xcid);
		if (xc == null)
		{
			out.print("-10000");
			out.flush();
			out.close();
			return null;
		}
		if (xc.getNeedinvite().equalsIgnoreCase("N"))
		{
			out.print("-10001");
			out.flush();
			out.close();
			return null;
		}
		int rs = xcMgr.updateTeyueCheck(vu, hypage, xcid, HttpRequestDeviceUtils.getMediaDevice(userAgent), ip,xc);
		out.print(rs);
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 确认接受邀请函
	 * @return
	 * @throws Exception
	 */
	public String acceptInvite() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();

		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if (vu == null)
		{
			out.print("-11000");
			out.flush();
			out.close();
			return null;
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		String userAgent = request.getHeader("user-agent");
		String ip = ClientUserIp.getIpAddr(request);
		Xc xc = xcMgr.getXcByIdMc(xcid);
		if (xc == null)
		{
			out.print("-10000");
			out.flush();
			out.close();
			return null;
		}
		if (xc.getNeedinvite().equalsIgnoreCase("N"))
		{
			out.print("-10001");
			out.flush();
			out.close();
			return null;
		}
		int rs = xcMgr.updateAcceptInvite(vu, xcid);
		out.print(rs);
		out.flush();
		out.close();
		return null;
	}

	public String updateNickname() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		int result = 3;
		if (nickname != null && nickname.trim().length() > 0)
		{
			result = xcMgr.updateNickname(recordId, nickname,account.getOwner().getId());
		}
		else
		{
			result = 2;
		}

		out.print(result);
		out.flush();
		out.close();
		return null;
	}

	public String deleteSdRecord() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		int result = xcMgr.deleteSdRecord(recordId,account.getOwner().getId());
		out.print(result);
		out.flush();
		out.close();
		return null;
	}

	public void setXcMgr(IXcMgr xcMgr)
	{
		this.xcMgr = xcMgr;
	}

	public XcSendRecordDto getDto()
	{
		return dto;
	}

	public void setDto(XcSendRecordDto dto)
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

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
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

	public String getResult()
	{
		return result;
	}

	public void setResult(String result)
	{
		this.result = result;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public long getXcid()
	{
		return xcid;
	}

	public void setXcid(long xcid)
	{
		this.xcid = xcid;
	}

	public int getHypage()
	{
		return hypage;
	}

	public void setHypage(int hypage)
	{
		this.hypage = hypage;
	}

	public XcSiftDto getSiftDto()
	{
		return siftDto;
	}

	public void setSiftDto(XcSiftDto siftDto)
	{
		this.siftDto = siftDto;
	}

	public long getRecordId()
	{
		return recordId;
	}

	public void setRecordId(long recordId)
	{
		this.recordId = recordId;
	}

	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}
	public String getSitetype(){
		return "现场配置";
	}

	
	public DaohangDto getDh()
	{
		return pageCompose.composeDhByXcid(xcid);
	}

	
	public void setDh(DaohangDto dh)
	{
		this.dh = dh;
	}
}
