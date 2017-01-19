
package com.huiyee.interact.xc.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.dto.DaohangDto;
import com.huiyee.esite.util.HttpRequestDeviceUtils;
import com.huiyee.interact.xc.mgr.IXcMgr;
import com.huiyee.interact.xc.mgr.XcMgr;
import com.huiyee.interact.xc.model.InviteConfig;
import com.huiyee.interact.xc.model.Xc;
import com.huiyee.interact.xc.model.XcSendRecord;

/**
 * 微现场与会人员
 * 
 * @author ldw
 * 
 */
public class XcPersonAction extends AbstractAction
{

	private IXcMgr xcMgr;
	private long xcid;
	private InviteConfig inviteConfig;
	private DaohangDto dh;
	private Xc xc;

	private String result;
	private File userfile;
	private String userfileFileName;
	private String userfileContentType;

	@Override
	public String execute() throws Exception
	{
		xc = xcMgr.findXcById(xcid);
		if("N".equals(xc.getInviteconfig().getAtype())||"N".equals(xc.getNeedinvite())){
			return "INVITE";
		}else{
			return "SEND";
		}
	}

	public long getXcid()
	{
		return xcid;
	}

	public void setXcid(long xcid)
	{
		this.xcid = xcid;
	}

	public InviteConfig getInviteConfig()
	{
		return inviteConfig;
	}

	public void setInviteConfig(InviteConfig inviteConfig)
	{
		this.inviteConfig = inviteConfig;
	}

	public DaohangDto getDh()
	{
		return dh;
	}

	public void setDh(DaohangDto dh)
	{
		this.dh = dh;
	}

	public void setXcMgr(IXcMgr xcMgr)
	{
		this.xcMgr = xcMgr;
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

}
