
package project.caidan.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.response.FieldStatsInfo;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.dto.SolrDataDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.WxUser;
import com.huiyee.esite.solr.HyJfSolrServer;
import com.huiyee.esite.util.DateUtil;
import com.huiyee.esite.util.ToolUtils;
import com.opensymphony.xwork2.ActionContext;

import project.caidan.dto.CdReportSift;
import project.caidan.dto.ReportDto;
import project.caidan.mgr.ICaiDanReportMgr;
import project.caidan.mgr.ICouponMgr;
import project.caidan.model.CDWayCatalog;
import project.caidan.model.CdWkqRmb;
import project.caidan.model.Task;

/**
 * 渠道报告
 * 
 * @author ldw
 * 
 */
public class CaiDanReportAction extends AbstractAction {

	private HyJfSolrServer hyJfSolrServer;
	private ICaiDanReportMgr caiDanReportMgr;
	private ICouponMgr couponMgr;

	private String starttime;
	private String endtime;
	private int status;
	private int pageId = 1;
	private ReportDto dto;
	private long wayid;
	private long wxuid;
	private CdReportSift sift;

	public String wayIndex() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		dto = (ReportDto) caiDanReportMgr.findWayReport(account.getOwner().getId(), pageId, starttime, endtime);
		setLightType(1);
		return SUCCESS;
	}

	public String cpzIndex() throws Exception {
		dto = (ReportDto) caiDanReportMgr.findCpzs(wxuid, wayid, pageId, starttime, endtime);
		return SUCCESS;
	}

	public String wayMoneyIndex() throws Exception {
		setLightType(2);
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		sift = sift == null ? new CdReportSift() : sift;
		dto = (ReportDto) caiDanReportMgr.findChannelPerformance(account.getOwner().getId(), pageId, sift);
		List<CDWayCatalog> catalogs = couponMgr.findWayCatalogs(account);
		dto.setCatalogs(catalogs);
		return SUCCESS;
	}

	public String export() throws Exception {
		long owner = this.getOwner();
		List<CdWkqRmb> list = caiDanReportMgr.findExport(owner, sift);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/csv;charset=GBK");
		String fileName = "渠道业绩" + DateUtil.getDateStringM(new Date());
		fileName = new String(fileName.getBytes("gb2312"), "ISO-8859-1");
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".csv");
		PrintWriter out = response.getWriter();
		if (list != null && list.size() != 0) {
			out.println("兑换时间,渠道名称,省级渠道经理,县级管理员,彩票站,微信昵称,兑换券,兑换金额,提成金额,县级提成金额");
			for (CdWkqRmb r : list) {
				try {
					List<String> gl = new ArrayList<String>();
					gl.add(DateUtil.getDateTimeString(r.getCreatetime()));
					gl.add(r.getWay().getName());
					gl.add(r.getSj().getNickname());
					gl.add(r.getDl().getName());
					gl.add(r.getCpz().getName());
					gl.add(r.getWxuser().getNickname());
					gl.add(r.getRmb().getProduct().getName());
					gl.add(String.valueOf((float) r.getRmb().getRmb() / 100));
					gl.add(String.valueOf((float) r.getRmb().getZdrmb() / 100));
					gl.add(String.valueOf((float) r.getRmb().getXjrmb() / 100));
					String rs = ToolUtils.listToString(gl, ",");
					out.println(rs);
				} catch (Exception e) {
					System.out.println("export error");
				}
			}
		} else {
			out.print("无相关信息");
		}
		out.flush();
		out.close();
		return null;

	}

	public String jfIndex() throws Exception {
		long owner = this.getOwner();
		String type = "JF";
		String cday = getCday(starttime, endtime);
		int rows = 10;
		int start = (pageId - 1) * rows;
		SolrDataDto sdd_add = hyJfSolrServer.findAddByCday(owner, type, cday, start, rows);
		SolrDataDto sdd_sub = hyJfSolrServer.findSubByCday(owner, type, cday, start, rows);

		SolrDocumentList docList = new SolrDocumentList();
		int total = 0;
		if (status == 1) {
			docList = sdd_add.getDocumentList();
			total = sdd_add.getTotal();
		} else if (status == 0) {
			SolrDataDto sdd = hyJfSolrServer.findByCday(owner, type, cday, start, rows);
			docList = sdd.getDocumentList();
			total = sdd.getTotal();
		} else if (status == -1) {
			docList = sdd_sub.getDocumentList();
			total = sdd_sub.getTotal();
		}
		docList = supplementDoc(docList);

		dto = new ReportDto();
		dto.setDocList(docList);
		dto.setPager(new Pager(pageId, total, rows));
		// 总增长
		FieldStatsInfo fsi = sdd_add.getFsi();
		if (fsi != null) {
			dto.setSum_add((Double) fsi.getSum());
		}
		// 总减少
		fsi = sdd_sub.getFsi();
		if (fsi != null) {
			dto.setSum_sub((Double) fsi.getSum());
		}

		setLightType(3);
		return SUCCESS;
	}

	public String jfExport() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/csv;charset=GBK");
		String fileName = "金币明细" + starttime + "-" + endtime;
		fileName = new String(fileName.getBytes("gb2312"), "ISO-8859-1");
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".csv");
		PrintWriter out = response.getWriter();
		out.println("昵称,性别,时间,途径,详细	,金币");

		long owner = this.getOwner();
		String type = "JF";
		String cday = getCday(starttime, endtime);

		int rows = 10;
		int start = 0;
		int size = 1;
		while (size > 0) {
			SolrDocumentList docList = new SolrDocumentList();
			if (status == 1) {
				SolrDataDto sdd_add = hyJfSolrServer.findAddByCday(owner, type, cday, start, rows);
				docList = sdd_add.getDocumentList();
			} else if (status == 0) {
				SolrDataDto sdd = hyJfSolrServer.findByCday(owner, type, cday, start, rows);
				docList = sdd.getDocumentList();
			} else if (status == -1) {
				SolrDataDto sdd_sub = hyJfSolrServer.findSubByCday(owner, type, cday, start, rows);
				docList = sdd_sub.getDocumentList();
			}
			docList = supplementDoc(docList);
			for (SolrDocument doc : docList) {
				List<String> gl = new ArrayList<String>();
				gl.add((String) doc.get("nickname"));
				gl.add((String) doc.get("sex"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				gl.add(sdf.format((Date) doc.get("created")));
				gl.add((String) doc.get("desc"));
				gl.add((String) doc.get("detail"));
				gl.add((Integer) doc.get("score") + "");
				String rs = ToolUtils.listToString(gl, ",");
				out.println(rs);
			}

			start += docList.size();
			size = docList.size();
		}

		out.flush();
		out.close();
		return null;
	}

	private SolrDocumentList supplementDoc(SolrDocumentList docList) throws Exception {
		Calendar cal = Calendar.getInstance();
		for (int i = 0; i < docList.size(); i++) {
			SolrDocument doc = docList.get(i);
			// 用户
			long hyuid = (Long) doc.get("hyuid");
			String nickname = "匿名";
			String gender = "未知";
			WxUser wxuser = caiDanReportMgr.findWxUserByHyuid(hyuid);
			if (wxuser != null) {
				if (wxuser.getNickname() != null) {
					nickname = wxuser.getNickname();
				} else {
					nickname = nickname + "_" + wxuser.getId();
				}
				int sex = wxuser.getSex();
				if (sex == 1) {
					gender = "男";
				} else if (sex == 2) {
					gender = "女";
				}
			}
			doc.setField("nickname", nickname);
			doc.setField("sex", gender);
			// 时间
			Date created = (Date) doc.get("created");
			cal.setTime(created);
			cal.add(Calendar.HOUR_OF_DAY, -8);
			doc.setField("created", cal.getTime());
			// 途径
			String tp = (String) doc.get("type");
			long enid = (Integer) doc.get("enid");
			if ("TAK".equals(tp)) {
				Task task = caiDanReportMgr.findTask(enid);
				if (task != null) {
					doc.setField("detail", task.getTitle());
				}
			} else if ("HUD".equals(tp)) {

			} else if ("JFS".equals(tp)) {

			} else if ("CDD".equals(tp)) {

			}
		}
		return docList;
	}

	private static String getCday(String startTime, String endTime) {
		String sday = "*";
		String eday = "*";
		if (StringUtils.isNotBlank(startTime)) {
			sday = startTime.replaceAll("-", "");
		}
		if (StringUtils.isNotBlank(endTime)) {
			eday = endTime.replaceAll("-", "");
		}

		if (sday.equals(eday)) {
			return sday;
		} else {
			return "[ " + sday + " TO " + eday + " ]";
		}
	}

	public void setHyJfSolrServer(HyJfSolrServer hyJfSolrServer) {
		this.hyJfSolrServer = hyJfSolrServer;
	}

	public void setCaiDanReportMgr(ICaiDanReportMgr caiDanReportMgr) {
		this.caiDanReportMgr = caiDanReportMgr;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public ReportDto getDto() {
		return dto;
	}

	public void setDto(ReportDto dto) {
		this.dto = dto;
	}

	public long getWayid() {
		return wayid;
	}

	public void setWayid(long wayid) {
		this.wayid = wayid;
	}

	public long getWxuid() {
		return wxuid;
	}

	public void setWxuid(long wxuid) {
		this.wxuid = wxuid;
	}

	public CdReportSift getSift() {
		return sift;
	}

	public void setSift(CdReportSift sift) {
		this.sift = sift;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setCouponMgr(ICouponMgr couponMgr) {
		this.couponMgr = couponMgr;
	}

}
