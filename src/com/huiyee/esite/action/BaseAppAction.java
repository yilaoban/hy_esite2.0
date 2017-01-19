
package com.huiyee.esite.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.huiyee.bdmap.utils.BaiDuMapApp;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dto.BaseAppDto;
import com.huiyee.esite.dto.SiteGroupDto;
import com.huiyee.esite.mgr.IBaseAppMgr;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.util.HttpTookit;
import com.huiyee.interact.cb.model.InteractCb;

/**
 * 微应用
 * 
 * @author ldw
 * 
 */
public class BaseAppAction extends AbstractAction
{

	private IBaseAppMgr baseAppMgr;
	private BaseAppDto dto;

	public void setBaseAppMgr(IBaseAppMgr baseAppMgr)
	{
		this.baseAppMgr = baseAppMgr;
	}

	public BaseAppDto getDto()
	{
		return dto;
	}

	public void setDto(BaseAppDto dto)
	{
		this.dto = dto;
	}

	@Override
	public String execute() throws Exception
	{
		ServletActionContext.getRequest().getSession().setAttribute("positionId", IPageConstants.POSITIONID_5);
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		dto = (BaseAppDto) baseAppMgr.findBaseApp(account);
		lightType = 1;
		return SUCCESS;
	}
	
	public String appShop() throws Exception
	{
		ServletActionContext.getRequest().getSession().setAttribute("positionId", IPageConstants.POSITIONID_5);
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		dto = (BaseAppDto) baseAppMgr.findBaseApp(account);
		lightType = 2;
		return SUCCESS;
	}

	public String toolLeft() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		dto = (BaseAppDto) baseAppMgr.findBaseApp(account);

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		JSONArray ja = new JSONArray();
		// 社区
		JSONObject bbs = new JSONObject();
		bbs.put("type", "B");
		ja.add(bbs);

		// 传播
		List<InteractCb> cblist = dto.getCbList();
		if (cblist != null && cblist.size() > 0)
		{
			for (InteractCb interactCb : cblist)
			{
				JSONObject cb = new JSONObject();
				cb.put("type", "C");
				cb.put("entity", interactCb.getId());
				cb.put("name", interactCb.getTitle());
				ja.add(cb);
			}
		}
		out.print(ja.toString());
		out.flush();
		out.close();
		return null;

	}
	
}
