package com.huiyee.esite.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.compose.IFeatureCompose;
import com.huiyee.esite.dto.DynamicActionDto;
import com.huiyee.esite.model.SinaChecklistRecord;
import com.huiyee.esite.util.ClientUserIp;
import com.huiyee.esite.util.DateUtil;
import com.huiyee.esite.util.HttpRequestDeviceUtils;

public class DynamicAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5661689678700624876L;
	private IFeatureCompose featureCompose;
	private long pageid;
	private long featureid;
	private long fid;
	private DynamicActionDto dto;
	private Long uid;
	private String keywords;// @联想关键字
	private long shareid;
	private String source;//来源
	private long category = 1;//表情category
	private String email;

	public long getFeatureid() {
		return featureid;
	}

	public void setFeatureid(long featureid) {
		this.featureid = featureid;
	}

	public long getFid() {
		return fid;
	}

	public void setFid(long fid) {
		this.fid = fid;
	}

	public void setFeatureCompose(IFeatureCompose featureCompose) {
		this.featureCompose = featureCompose;
	}

	// 赞
	public String zan() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ip = ClientUserIp.getIpAddr(request);
        dto.setIp(ip);
		String userA = request.getHeader("user-agent");
		String userAgent = HttpRequestDeviceUtils.getMediaDevice(userA);
		dto.setTerminal(userAgent);//终端
		dto.setPageid(pageid);
		dto.setSource(source);
		Long uid = (Long) ServletActionContext.getRequest().getSession().getAttribute("uid");
		String result = featureCompose.dynamicAction(featureid, uid != null ? uid : 0, dto);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		return null;
	}

	// 查赞
	public String selectZan() throws Exception {
		String[] p = ServletActionContext.getRequest().getParameterValues("pfid[]");
		dto = (DynamicActionDto) pageCompose.findZanTotal(p);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(dto));
		out.flush();
		out.close();
		return null;
	}

	// 显示用户上传页面
	public String upload() throws Exception {
		Long id = (Long) ServletActionContext.getRequest().getSession().getAttribute("uid");
		uid = (id != null) ? id : 0;
		return SUCCESS;
	}

	// @好友
	public String selectFans() throws Exception {
		if (uid == null) {
			System.out.println("uid is null");
			return null;
		}
		List<String> result = pageCompose.findAtUsers(uid, pageid, keywords);
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

	// 滚动微博
	public String selectWeibo() throws Exception {
		List<SinaChecklistRecord> list = pageCompose.findSinaShareRecord(shareid, fid);
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

	// 参与人数
	public String weiboTotal() throws Exception {
		int total = pageCompose.findSinaShareTotal(shareid);
		long time = new Date().getTime() - DateUtil.getDateTime("2014-04-04 12:00:00").getTime();
		int min = (int) (time / (1000 * 60 * 12));
		if (min * 5 >= 5000) {
			total = total + 5000;
		} else {
			total = total + min * 5;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("html/text;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(total));
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 获取表情信息
	 * @return
	 * @throws Exception
	 */
	public String getFace() throws Exception{
		dto = (DynamicActionDto) pageCompose.composeFace(category);
		Gson gson = new Gson();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter pw;
		try {
			pw = response.getWriter();
			pw.print(gson.toJson(dto));				
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// 邮件预定
    public String emailContract() throws Exception {
        String result="N";
        if(email==null||"".equals(email)){
            result="E";
        }else{
            String checkEmail = "^([a-z0-9A-Z]+[-|\\.|_]?)*[a-z0-9A-Z]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regexemail = Pattern.compile(checkEmail);
            Matcher matcheremail = regexemail.matcher(email.trim());
            boolean isMatchedEmail = matcheremail.matches();
            if (!isMatchedEmail) {
                result="F";
            }else{
                HttpServletRequest request = ServletActionContext.getRequest();
                String ip = ClientUserIp.getIpAddr(request);
                DynamicActionDto dto=new DynamicActionDto();
                dto.setIp(ip);
                String userA = request.getHeader("user-agent");
                String userAgent = HttpRequestDeviceUtils.getMediaDevice(userA);
                dto.setTerminal(userAgent);//终端
                dto.setPageid(pageid);
                dto.setSource(source);
                dto.setEmail(email);
                dto.setContractid(fid);
                result = featureCompose.dynamicAction(121, uid != null ? uid : 0, dto);
            }
        }
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        out.print(result);
        out.flush();
        out.close();
        return null;
    }
	
	public String sinaReturn() throws Exception{
		return SUCCESS;
	}

	public long getPageid() {
		return pageid;
	}

	public void setPageid(long pageid) {
		this.pageid = pageid;
	}

	public DynamicActionDto getDto() {
		return dto;
	}

	public void setDto(DynamicActionDto dto) {
		this.dto = dto;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public long getShareid() {
		return shareid;
	}

	public void setShareid(long shareid) {
		this.shareid = shareid;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public long getCategory() {
		return category;
	}

	public void setCategory(long category) {
		this.category = category;
	}

    public void setEmail(String email) {
        this.email = email;
    }

}
