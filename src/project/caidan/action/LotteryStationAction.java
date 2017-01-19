
package project.caidan.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import project.caidan.mgr.ICdRmbRecordMgr;
import project.caidan.mgr.ICouponMgr;
import project.caidan.model.CDWkqRecord;
import project.caidan.model.CdRmbRecord;

import com.google.gson.Gson;
import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.model.VisitUser;

public class LotteryStationAction extends AbstractAction
{

	private ICdRmbRecordMgr rmbRecordMgr;
	private ICouponMgr couponMgr;
	private List<CdRmbRecord> zhangben;
	private int pageId = 1;
	private int size = 15;

	/**
	 * ’À±æ
	 * 
	 * @return
	 * @throws Exception
	 */
	public String zhangben() throws Exception
	{
		VisitUser vu = (VisitUser) ServletActionContext.getRequest().getSession().getAttribute("visitUser");
		long wxuid = 0;
		List<CdRmbRecord> list = new ArrayList<CdRmbRecord>();
		if (vu != null && vu.getCd() == 1)
		{
			wxuid = vu.getWxuid();
			list = rmbRecordMgr.findListForPage(wxuid, pageId, size);
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(list));
		out.flush();
		out.close();
		return null;
	}

	public String duihuan() throws Exception
	{
		VisitUser vu = (VisitUser) ServletActionContext.getRequest().getSession().getAttribute("visitUser");
		long wxuid = 0;
		List<CDWkqRecord> list = new ArrayList<CDWkqRecord>();
		if (vu != null && vu.getCd() == 1)
		{
			wxuid = vu.getWxuid();
			list = couponMgr.findWkqRecord(wxuid, this.getOwner(), pageId, size);
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(list));
		out.flush();
		out.close();
		return null;

	}

	public List<CdRmbRecord> getZhangben()
	{
		return zhangben;
	}

	public void setZhangben(List<CdRmbRecord> zhangben)
	{
		this.zhangben = zhangben;
	}

	public int getPageId()
	{
		return pageId;
	}

	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	public int getSize()
	{
		return size;
	}

	public void setSize(int size)
	{
		this.size = size;
	}

	public void setRmbRecordMgr(ICdRmbRecordMgr rmbRecordMgr)
	{
		this.rmbRecordMgr = rmbRecordMgr;
	}

	public void setCouponMgr(ICouponMgr couponMgr)
	{
		this.couponMgr = couponMgr;
	}

}
