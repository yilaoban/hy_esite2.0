package com.huiyee.interact.setting.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.google.gson.Gson;
import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.constants.IPrivilegeConstants;
import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.service.Permission;
import com.huiyee.interact.setting.dto.SettingDto;
import com.huiyee.interact.setting.mgr.IHyUserPrintMgr;
import com.huiyee.interact.setting.mgr.IHyUserXfDescMgr;
import com.huiyee.interact.setting.model.HyUserPrint;
import com.huiyee.interact.setting.model.HyUserXfDesc;

public class SettingUserAction extends AbstractAction {
	private static final long serialVersionUID = 4294757669436867905L;

	private IHyUserPrintMgr hyUserPrintMgr;
	private IHyUserXfDescMgr hyUserXfDescMgr;
	private VelocityEngine velocityEngine;

	private int pageId = 1;
	private SettingDto dto;
	private List<String> vmList;
	private String vm;
	private HyUserPrint print;
	private HyUserXfDesc xd;
	private long xfid;
	private long levelid;
	private int zk;
	
	@Override
	public int getLightType() {
		return 2;
	}

	@Permission(module = IPrivilegeConstants.MODULE_APP_微积分, privilege = IPrivilegeConstants.PERMISSION_R)
	public String print() throws Exception {
		print = hyUserPrintMgr.findPrint(this.getOwner());
		if (print != null) {
			Map<String, Object> map = exampleMap();
			map.put("logo", print.getLogo());
			map.put("url", print.getUrl());

			vm = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "/WEB-INF/velocity/print/" + print.getVm() + ".vm", "UTF-8", map);
		}
		return SUCCESS;
	}

	public String printVm() throws Exception {
		vmList = new ArrayList<String>();

		Map<String, Object> map = exampleMap();
		map.put("showTitle", true);

		String[] vms = { "58_1.vm", "58_2.vm", "80_1.vm" };
		for (String vm : vms) {
			vmList.add(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "/WEB-INF/velocity/print/" + vm, "UTF-8", map));
		}

		return SUCCESS;
	}

	public String addPrint() throws Exception {
		if (print == null) {
			return null;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try {
			print.setOwner(this.getOwner());
			int res = hyUserPrintMgr.addPrint(print);
			out.print(res);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}

	public String updatePrint() throws Exception {
		if (print == null) {
			return null;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try {
			String url = print.getUrl();
			if (StringUtils.isNotBlank(url) && !url.startsWith("http")) {
				print.setUrl("http://" + url);
			}
			int res = hyUserPrintMgr.updatePrint(print);
			out.print(res);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}

	@Permission(module = IPrivilegeConstants.MODULE_APP_微积分, privilege = IPrivilegeConstants.PERMISSION_R)
	public String xfDesc() throws Exception {
		long owner = this.getOwner();
		int rows = 10;
		int start = (pageId - 1) * rows;
		int total = hyUserXfDescMgr.findXfDescCount(owner);
		List<HyUserXfDesc> list = hyUserXfDescMgr.findXfDescList(owner, start, rows);

		dto = new SettingDto();
		dto.setXdList(list);
		dto.setPager(new Pager(pageId, total, rows));

		return SUCCESS;
	}

	/**
	 * 消费备注折扣
	 * @return
	 * @throws Exception
	 */
	public String xfzk() throws Exception{
		dto = hyUserXfDescMgr.findXfZkListByXfid(this.getOwner(),xfid);
		return SUCCESS;
	}
	
	public String savezk() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		int res = hyUserXfDescMgr.savezk(levelid, xfid,zk,this.getOwner());
		out.print(gson.toJson(res));
		out.flush();
		out.close();
		return null;
	}
	
	public String addXfDesc() throws Exception {
		if (xd == null) {
			return null;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try {
			xd.setOwner(this.getOwner());
			int res = hyUserXfDescMgr.addXfDesc(xd);
			out.print(res);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}

	public String updXfDesc() throws Exception {
		if (xd == null) {
			return null;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try {
			xd.setOwner(this.getOwner());
			int res = hyUserXfDescMgr.updateXfDesc(xd);
			out.print(res);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}

	public String delXfDesc() throws Exception {
		if (xd == null) {
			return null;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try {
			int res = hyUserXfDescMgr.deleteXfDesc(xd.getId());
			out.print(res);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}

	private static Map<String, Object> exampleMap() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> x = new HashMap<String, Object>();
		x.put("name", "商品1");
		x.put("num", 1);
		x.put("price", "99.00");
		x.put("subtotal", "99.00");
		list.add(x);
		x = new HashMap<String, Object>();
		x.put("name", "商品2");
		x.put("num", 1);
		x.put("price", "99.00");
		x.put("subtotal", "99.00");
		list.add(x);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("No", "No.88888888");
		map.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		map.put("logo", "/images/logo.png");
		map.put("url", "http://www.huiyee.com");
		map.put("list", list);
		map.put("total", "198.00");
		map.put("receive", "200.00");
		map.put("change", "2.00");

		return map;
	}

	public String findXfDescList() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("json/application;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		List<HyUserXfDesc> list = hyUserXfDescMgr.findXfDescList(this.getOwner());
		Gson gson = new Gson();
		out.print(gson.toJson(list));
		out.flush();
		out.close();
		return null;
	}

	public void setHyUserPrintMgr(IHyUserPrintMgr hyUserPrintMgr) {
		this.hyUserPrintMgr = hyUserPrintMgr;
	}

	public void setHyUserXfDescMgr(IHyUserXfDescMgr hyUserXfDescMgr) {
		this.hyUserXfDescMgr = hyUserXfDescMgr;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public SettingDto getDto() {
		return dto;
	}

	public void setDto(SettingDto dto) {
		this.dto = dto;
	}

	public List<String> getVmList() {
		return vmList;
	}

	public void setVmList(List<String> vmList) {
		this.vmList = vmList;
	}

	public String getVm() {
		return vm;
	}

	public void setVm(String vm) {
		this.vm = vm;
	}

	public HyUserPrint getPrint() {
		return print;
	}

	public void setPrint(HyUserPrint print) {
		this.print = print;
	}

	public HyUserXfDesc getXd() {
		return xd;
	}

	public void setXd(HyUserXfDesc xd) {
		this.xd = xd;
	}

	public long getXfid() {
		return xfid;
	}

	public void setXfid(long xfid) {
		this.xfid = xfid;
	}

	public long getLevelid() {
		return levelid;
	}

	public void setLevelid(long levelid) {
		this.levelid = levelid;
	}

	public int getZk() {
		return zk;
	}

	public void setZk(int zk) {
		this.zk = zk;
	}

}
