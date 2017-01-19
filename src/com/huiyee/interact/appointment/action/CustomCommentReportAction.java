
package com.huiyee.interact.appointment.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.bdmap.dto.BDAddress;
import com.huiyee.bdmap.utils.BaiDuMapUtil;
import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.dto.HdRsDto;
import com.huiyee.esite.model.AppointmentRecord;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.HttpRequestDeviceUtils;
import com.huiyee.interact.appointment.mgr.IInteractAptManager;
import com.huiyee.interact.appointment.model.AppointmentModel;
import com.huiyee.esite.util.ClientUserIp;
import com.opensymphony.xwork2.ActionContext;

public class CustomCommentReportAction extends AbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7147696704553523952L;
	private String source;
	private AppointmentRecord aptRecord;
	private String lat;
	private String lng;
	private String result;
	private long cid;
	private long relationid;
	private long aptid;
	private int start;
	private int size;
	private int cansee;// 0-能看到所有人的申请,1-只能看到自己的
	private IInteractAptManager interactAptManager;

	public void setLat(String lat)
	{
		this.lat = lat;
	}

	public void setLng(String lng)
	{
		this.lng = lng;
	}

	public void setInteractAptManager(IInteractAptManager interactAptManager)
	{
		this.interactAptManager = interactAptManager;
	}

	public int getStart()
	{
		return start;
	}

	public void setStart(int start)
	{
		this.start = start;
	}

	public int getSize()
	{
		return size;
	}

	public void setSize(int size)
	{
		this.size = size;
	}

	public long getAptid()
	{
		return aptid;
	}

	public void setAptid(long aptid)
	{
		this.aptid = aptid;
	}

	public long getPageid()
	{
		return pageid;
	}

	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	private long pageid;

	@Override
	public String execute() throws Exception
	{
		// 验证是否能继续操作
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		AppointmentModel apt = interactAptManager.findAppointById(aptRecord.getAptid());
		HdRsDto rs = pageCompose.ineractCanRun(apt);
		if (rs.getStatus() == 10000)
		{
			String userAgent = request.getHeader("user-agent");
			String ip = ClientUserIp.getIpAddr(request);
			VisitUser visit = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
			try
			{
				if (apt.getCoded() >0)
				{
					String code = aptRecord.getCode();
					int rsd = 0;
					if (code != null)
					{
						rsd = interactAptManager.findCode(code, aptRecord.getAptid(),apt.getCoded());
					}
					if (rsd == 0)
					{
						rs.setStatus(-1);
						rs.setHydesc("提交失败，请检查验证码！");
						pageCompose.canRunLog(apt.getId(), "f", request);
						out.print(new Gson().toJson(rs));
						out.flush();
						out.close();
						return null;
					}else
					{
						interactAptManager.updateCode(code, aptRecord.getAptid());
					}
				}
				if (lat != null && lng != null)
				{
					BDAddress bda = BaiDuMapUtil.address(lat, lng);
					if (bda != null)
					{
						rs = interactAptManager.saveCustomCommentRepotrXY(visit, aptRecord, ip, HttpRequestDeviceUtils.getMediaDevice(userAgent), source, relationid, apt, bda);
					} else
					{
						rs = interactAptManager.saveCustomCommentRepotr(visit, aptRecord, ip, HttpRequestDeviceUtils.getMediaDevice(userAgent), source, relationid, apt);
					}
				} else
				{
					rs = interactAptManager.saveCustomCommentRepotr(visit, aptRecord, ip, HttpRequestDeviceUtils.getMediaDevice(userAgent), source, relationid, apt);
				}
			} catch (Exception e)
			{
				e.printStackTrace();
				rs.setStatus(-11000);
				rs.setHydesc("提交失败，严重问题！");
			}

			pageCompose.canRunLog(apt.getId(), "f", request);
		}
		out.print(new Gson().toJson(rs));
		out.flush();
		out.close();
		return null;
	}

	public String record() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		VisitUser visit = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		List<AppointmentRecord> list = interactAptManager.findAptRecord(aptid, pageid, start, size, cansee > 0 ? visit : null);
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(list));
		out.flush();
		out.close();
		return null;
	}

	public String getSource()
	{
		return source;
	}

	public void setSource(String source)
	{
		this.source = source;
	}

	public String getResult()
	{
		return result;
	}

	public void setResult(String result)
	{
		this.result = result;
	}

	public void setAptRecord(AppointmentRecord aptRecord)
	{
		this.aptRecord = aptRecord;
	}

	public AppointmentRecord getAptRecord()
	{
		return aptRecord;
	}

	public long getCid()
	{
		return cid;
	}

	public void setCid(long cid)
	{
		this.cid = cid;
	}

	public long getRelationid()
	{
		return relationid;
	}

	public void setRelationid(long relationid)
	{
		this.relationid = relationid;
	}

	public int getCansee()
	{
		return cansee;
	}

	public void setCansee(int cansee)
	{
		this.cansee = cansee;
	}
}
