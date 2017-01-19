
package project.caidan.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import project.caidan.dto.CaiDanRmbDto;
import project.caidan.mgr.ICaiDanRmbMgr;
import project.caidan.model.CDRmbGet;

import com.google.gson.Gson;
import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.model.HyUser;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.model.WxUser;
import com.huiyee.esite.util.DateUtil;
import com.huiyee.esite.util.ToolUtils;

public class CaiDanRmbAction extends AbstractAction
{

	private static final long serialVersionUID = -7754712002472744824L;
	private ICaiDanRmbMgr cdRmbMgr;
	private CaiDanRmbDto dto;
	private int pageId = 1;
	private CDRmbGet rmbGet;
	private float rmb;
	private int size = 20;
	private String starttime;
	private String endtime;

	public void setCdRmbMgr(ICaiDanRmbMgr cdRmbMgr)
	{
		this.cdRmbMgr = cdRmbMgr;
	}

	@Override
	public String execute() throws Exception
	{
		dto = (CaiDanRmbDto) cdRmbMgr.findRmbGetList(pageId,starttime,endtime);
		return SUCCESS;
	}
	
	
	public String export() throws Exception {
		long owner = this.getOwner();
		List<CDRmbGet> list = cdRmbMgr.findRmbGetList(starttime,endtime);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/csv;charset=GBK");
		String fileName="提现数据"+DateUtil.getDateStringM(new Date());
		fileName=new String(fileName.getBytes("gb2312"), "ISO-8859-1");
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".csv");
		PrintWriter out = response.getWriter();
		if (list != null && list.size() != 0) {
			out.println("昵称,金钱,交易流水号,创建时间,说明,状态");
			for (CDRmbGet g : list) {
					List<String> gl=new ArrayList<String>();
					gl.add(g.getNickname());
					float r=(float)g.getRmb()/100;
					gl.add(r+"元");
					gl.add(g.getMch_billno());
					gl.add(DateUtil.getDateTimeString(g.getCreatetime()));
					gl.add(g.getErrreason());
					String status=g.getStatus();
					if("NTP".equalsIgnoreCase(status)){
						gl.add("审核通过待发送");
					}else if("CMP".equalsIgnoreCase(status)){
						gl.add("已经发放完毕");
					}else if("SNN".equalsIgnoreCase(status)){
						gl.add("审核不通过");
					}
					String rs=ToolUtils.listToString(gl, ",");
					out.println(rs);
			}
		} else {
			out.print("无相关信息");
		}
		out.flush();
		out.close();
		return null;
	
	}

	/**
	 * 提取现金审核通过
	 * @return
	 * @throws Exception
	 */
	public String tiquCheck() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int result = cdRmbMgr.updateRmbGetById(rmbGet);
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 提取现金审核不通过
	 * @return
	 * @throws Exception
	 */
	public String tiquCheckFail() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int	result = cdRmbMgr.updateRmbGetStatusById(rmbGet);
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	} 

	public String tixian() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int rs = 0;
		VisitUser vu = (VisitUser) ServletActionContext.getRequest().getSession().getAttribute("visitUser");
		long wxuid = 0;
		if (vu != null && vu.getCd() == 1 && rmb > 0 && isNumber(rmb + ""))
		{
			if(rmb<100){
				rs=100;
			}else if(rmb>200){
				rs=200;
			}else{
				wxuid = vu.getWxuid();
				rs = cdRmbMgr.addRmbGet(wxuid, rmb);
			}
		}
		Gson gson = new Gson();
		out.print(gson.toJson(rs));
		out.flush();
		out.close();
		return null;

	}

	public String tixianRecord() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		List<CDRmbGet> list = new ArrayList<CDRmbGet>();
		VisitUser vu = (VisitUser) ServletActionContext.getRequest().getSession().getAttribute("visitUser");
		long wxuid = 0;
		if (vu != null && vu.getCd() == 1)
		{
			wxuid = vu.getWxuid();
			list = cdRmbMgr.findRmbGetList(wxuid, pageId, size);
		}
		Gson gson = new Gson();
		out.print(gson.toJson(list));
		out.flush();
		out.close();
		return null;

	}

	public static boolean isNumber(String str)
	{
		java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后一位的数字的正则表达式
		java.util.regex.Matcher match = pattern.matcher(str);
		if (match.matches() == false)
		{
			return false;
		} else
		{
			return true;
		}
	}

	public CaiDanRmbDto getDto()
	{
		return dto;
	}

	public void setDto(CaiDanRmbDto dto)
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

	public CDRmbGet getRmbGet()
	{
		return rmbGet;
	}

	public void setRmbGet(CDRmbGet rmbGet)
	{
		this.rmbGet = rmbGet;
	}

	public float getRmb()
	{
		return rmb;
	}

	public void setRmb(float rmb)
	{
		this.rmb = rmb;
	}

	public int getSize()
	{
		return size;
	}

	public void setSize(int size)
	{
		this.size = size;
	}

	
	public String getStarttime()
	{
		return starttime;
	}

	
	public void setStarttime(String starttime)
	{
		this.starttime = starttime;
	}

	
	public String getEndtime()
	{
		return endtime;
	}

	
	public void setEndtime(String endtime)
	{
		this.endtime = endtime;
	}

}
