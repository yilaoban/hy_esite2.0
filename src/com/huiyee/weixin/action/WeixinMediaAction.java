package com.huiyee.weixin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.mgr.IWeiXinMgr;
import com.huiyee.esite.service.FileUploadService;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.weixin.model.WxMp;
import com.huiyee.weixin.utils.HttpsUtil;

public class WeixinMediaAction extends AbstractAction {

	private static final long serialVersionUID = -5617727137609998776L;
	private IWeiXinMgr weiXinMgr;

	private long mpid;
	private String media_id;

	@Override
	public String execute() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try {
			WxMp mp = weiXinMgr.updateFindWxMp(this.getOwner(), 0);
			String access_token = mp.getAu_access_token();
			if (mp.getAppsecret() != null) {
				access_token = mp.getAccess_token();
			}
			if (access_token == null || media_id == null) {
				out.print("{\"status\":-1,\"errmsg\":\"参数有误\"}");
				return null;
			}
			String filePath = FileUploadService.getFilePath(IPageConstants.TYPE_WX_PIC, this.getOwner());// 路径
			String fileName = media_id + ".jpg";

			String url = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=" + access_token + "&media_id=" + media_id;
			boolean res = HttpsUtil.httpsGetFile(url, filePath, fileName);

			if (res) {
				String picUrl = filePath + "/" + fileName;
				out.print("{\"status\":1,\"picUrl\":\"" + picUrl + "\"}");
			} else {
				out.print("{\"status\":-2,\"errmsg\":\"上传失败\"}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}

	public void setWeiXinMgr(IWeiXinMgr weiXinMgr) {
		this.weiXinMgr = weiXinMgr;
	}

	public long getMpid() {
		return mpid;
	}

	public void setMpid(long mpid) {
		this.mpid = mpid;
	}

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

}
