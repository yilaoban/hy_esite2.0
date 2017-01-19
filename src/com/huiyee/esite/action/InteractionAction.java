package com.huiyee.esite.action;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.compose.IFeatureCompose;
import com.huiyee.esite.dto.ExportDto;
import com.huiyee.esite.dto.InteractionActionDto;
import com.huiyee.esite.dto.QueryDto;
import com.huiyee.esite.model.Account;
import com.opensymphony.xwork2.ActionContext;

public class InteractionAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7315143127306779385L;
	private InteractionActionDto dto;
	private long sitegroupid;
	private long featureid;
	private ExportDto exportDto;
	private QueryDto siftDto;
	private IFeatureCompose featureCompose;

	public void setFeatureCompose(IFeatureCompose featureCompose) {
		this.featureCompose = featureCompose;
	}

	@Override
	public String execute() throws Exception {
		dto = (InteractionActionDto) pageCompose.composeInteraction(sitegroupid);
		return SUCCESS;
	}

	public String details() throws Exception {
		dto = (InteractionActionDto) pageCompose.composeInteractionDetail(featureid, sitegroupid, siftDto);
		return SUCCESS;
	}

	public String updateHandler() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		String result = pageCompose.updateHandler(featureid, sitegroupid, account.getOwner().getId());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}

	public String export() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		List<String> exportlist = featureCompose.export(featureid, sitegroupid, account.getOwner().getId(),exportDto);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/csv;charset=GBK");
		response.setHeader("Content-Disposition", "attachment;filename=" + encodingFileName(featureid) + ".csv");
		PrintWriter out = response.getWriter();
		if (exportlist != null && exportlist.size() != 0) {
			for (String str : exportlist) {
				out.println(str);
			}
		} else {
			out.print("无相关信息");
		}
		return null;
	}

	private String encodingFileName(long featureid) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String time = sdf.format(new Date());
		String result = null;
		if (featureid == 113) {
			result = "用户新浪分享" + time;
		} else if (featureid == 111) {
			result = "新浪授权" + time;
		} else if(featureid==142){
			result = "米其林数据" + time;
		}else{
			result = "未知功能";
		}
		try {
			return new String(result.getBytes("gb2312"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			return time;
		}
	}

	public void setSitegroupid(long sitegroupid) {
		this.sitegroupid = sitegroupid;
	}

	public long getSitegroupid() {
		return sitegroupid;
	}

	public InteractionActionDto getDto() {
		return dto;
	}

	public void setDto(InteractionActionDto dto) {
		this.dto = dto;
	}

	public long getFeatureid() {
		return featureid;
	}

	public void setFeatureid(long featureid) {
		this.featureid = featureid;
	}

	public int getPagePosition() {
		return 2;
	}

	public QueryDto getSiftDto() {
		return siftDto;
	}

	public void setSiftDto(QueryDto siftDto) {
		this.siftDto = siftDto;
	}

	public ExportDto getExportDto()
	{
		return exportDto;
	}

	public void setExportDto(ExportDto exportDto)
	{
		this.exportDto = exportDto;
	}

}
