package project.caidan.action;

import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.dto.SolrDataDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.HyUser;
import com.huiyee.esite.model.OrderGoods;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.model.WxUser;
import com.huiyee.esite.solr.HyJfSolrServer;
import com.huiyee.esite.util.DateUtil;
import com.opensymphony.xwork2.ActionContext;

import project.caidan.dto.RsCdDto;
import project.caidan.mgr.ICaiDanUserMgr;

public class CaiDanUserAction extends AbstractAction {
	private static final long serialVersionUID = 6621274316552028326L;

	private ICaiDanUserMgr cdUserMgr;
	private HyJfSolrServer hyJfSolrServer;

	private int pageId = 1;
	private int rows = 10;
	private RsCdDto dto;
	private HyUser hyUser;
	private String telphone;
	private String code;

	public String execute() throws Exception {
		long owner = this.getOwner();
		int start = (pageId - 1) * rows;
		int total = cdUserMgr.findHyUserCount(owner, hyUser);
		List<HyUser> list = cdUserMgr.findHyUserList(owner, hyUser, start, rows);

		dto = new RsCdDto();
		dto.setList(list);
		dto.setPager(new Pager(pageId, total, rows));
		return SUCCESS;
	}
	
	
	public String export() throws Exception {
		long owner = this.getOwner();
		List<HyUser> list = cdUserMgr.findHyUserList(owner, hyUser);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/csv;charset=GBK");
		String fileName="用户数据"+DateUtil.getDateStringM(new Date());
		fileName=new String(fileName.getBytes("gb2312"), "ISO-8859-1");
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".csv");
		PrintWriter out = response.getWriter();
		if (list != null && list.size() != 0) {
			out.println("微信昵称,性别,区域,电话号码,关注时间,金币数");
			for (HyUser u : list) {
				WxUser wxuser=u.getWxUser();
				if(wxuser!=null){
					int sex=wxuser.getSex();
					String gender="未知";
					switch (sex)
					{
						case 0:gender="女";break;
						case 1:gender="男";break;
					}
					int less=u.getBalance_total()-u.getBalance_used();
					String rs=cvsStr(wxuser.getNickname())+","+gender+","+wxuser.getProvince()+wxuser.getCity()+","+u.getTelphone()+","+DateUtil.getDateString6(wxuser.getSubscribe_time())+","+less;
					rs=rs.replace("null", "").replace(" ", "");
					out.println(rs);
				}
			}
		} else {
			out.print("无相关信息");
		}
		out.flush();
		out.close();
		return null;
	
	}
	private static String cvsStr(String str)
	{
		if (str != null&&!"null".equalsIgnoreCase(str))
		{
			if (str.indexOf(",") != -1)
			{
				return "\"" + str + "\"";
			} else
			{
				return str;
			}
		} else
		{
			return "";
		}
	}

	public String balance() throws Exception {
		if (hyUser == null || hyUser.getId() == 0) {
			return null;
		}
		long hyuid = hyUser.getId();
		int start = (pageId - 1) * rows;
		SolrDataDto sdd = hyJfSolrServer.findUserJfDetail(hyuid, start, rows);
		int total = sdd.getTotal();
		SolrDocumentList list = sdd.getDocumentList();
		Calendar cal = Calendar.getInstance();
		for (int i = 0; i < list.size(); i++) {
			SolrDocument doc = list.get(i);
			Date created = (Date) doc.get("created");
			cal.setTime(created);
			cal.add(Calendar.HOUR_OF_DAY, -8);
			doc.setField("created", cal.getTime());
		}

		dto = new RsCdDto();
		dto.setJfList(list);
		dto.setPager(new Pager(pageId, total, rows));
		return SUCCESS;
	}

	public String order() throws Exception {
		if (hyUser == null || hyUser.getId() == 0) {
			return null;
		}
		long hyuid = hyUser.getId();
		int start = (pageId - 1) * rows;
		int total = cdUserMgr.findOrderGoodsCount(hyuid);
		List<OrderGoods> list = cdUserMgr.findOrderGoodsList(hyuid, start, rows);

		dto = new RsCdDto();
		dto.setOgList(list);
		dto.setPager(new Pager(pageId, total, rows));
		return SUCCESS;
	}

	public String findHyUser() throws Exception {
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		long wxuid = 0;
		if (vu != null && vu.getWxUser() != null) {
			wxuid = vu.getWxUser().getId();
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		HyUser result = cdUserMgr.findHyUser(this.getOwner(), wxuid);
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;

	}

	/**
	 * 绑定手机号码
	 * 
	 * @return
	 * @throws Exception
	 */
	public String bindTel() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		String sRand = (String) request.getSession().getAttribute("checkCode");
		dto = new RsCdDto();
		if (vu != null) {
			long wxuid = vu.getWxUser().getId();
			if (sRand != null) {
				if (sRand.equalsIgnoreCase(code)) {
					ActionContext.getContext().getSession().put("checkCode", null);
					int status = cdUserMgr.updateTelByWxuid(this.getOwner(), wxuid, telphone);
					dto.setStatus(status);
				} else {
					dto.setHydesc("验证码填写错误");
				}
			} else {
				dto.setHydesc("验证码失效,请重新获取");
			}
		} else {
			dto.setHydesc("请获取验证码");
		}
		Gson gson = new Gson();
		out.print(gson.toJson(dto));
		out.flush();
		out.close();
		return null;
	}

	public void setCdUserMgr(ICaiDanUserMgr cdUserMgr) {
		this.cdUserMgr = cdUserMgr;
	}

	public void setHyJfSolrServer(HyJfSolrServer hyJfSolrServer) {
		this.hyJfSolrServer = hyJfSolrServer;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public HyUser getHyUser() {
		return hyUser;
	}

	public void setHyUser(HyUser hyUser) {
		this.hyUser = hyUser;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public RsCdDto getDto() {
		return dto;
	}

	public void setDto(RsCdDto dto) {
		this.dto = dto;
	}

}
