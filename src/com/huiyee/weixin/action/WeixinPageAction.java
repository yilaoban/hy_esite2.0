package com.huiyee.weixin.action;

import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.mgr.IWeiXinMgr;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.weixin.mgr.IWxPageShowMgr;
import com.huiyee.weixin.model.WxConfig;
import com.huiyee.weixin.model.WxMp;
import com.huiyee.weixin.model.WxPageShow;
import com.huiyee.weixin.utils.HttpsUtil;

import net.sf.json.JSONObject;

public class WeixinPageAction extends AbstractAction {

	private static final long serialVersionUID = -980182699174186671L;
	private static Map<String, String> ticketCache = new HashMap<String, String>();
	private static Map<String, Long> expiresCache = new HashMap<String, Long>();
	private static Map<Long, WxPageShow> wpsCache = new HashMap<Long, WxPageShow>();

	private IWeiXinMgr weiXinMgr;
	private IWxPageShowMgr wxPageShowMgr;

	private long mpid;
	private long pageid;
	private String url;

	public String jssdk() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try {
			VisitUser vu = (VisitUser) ServletActionContext.getRequest().getSession().getAttribute("visitUser");
			if (mpid == 0 && vu != null && vu.getWxUser() != null) {
				mpid = vu.getWxUser().getMpid();
			}
			WxMp mp = weiXinMgr.updateFindWxMp(this.getOwner(), mpid);
			if (url == null || mp == null) {
				return null;
			}

			String appid = mp.getAppid();
			String noncestr = "huiyee";
			String timestamp = System.currentTimeMillis() / 1000 + "";
			String jsapi_ticket = getJSTicket(mp);

			Map<String, String> map = new HashMap<String, String>();
			map.put("noncestr", noncestr);
			map.put("jsapi_ticket", jsapi_ticket);
			map.put("timestamp", timestamp);
			map.put("url", url);

			String[] arr = new String[] { "noncestr", "jsapi_ticket", "timestamp", "url" };
			Arrays.sort(arr);

			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < arr.length; i++) {
				String key = arr[i];
				sb.append(key).append("=").append(map.get(key));
				if (i < arr.length - 1) {
					sb.append("&");
				}
			}

			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] digest = md.digest(sb.toString().getBytes());
			String signature = byteToString(digest);

			WxConfig wc = new WxConfig();
			wc.setAppid(appid);
			wc.setNoncestr(noncestr);
			wc.setTimestamp(timestamp);
			wc.setSignature(signature);
			wc.setMap(map);
			wc.setWps(getWxPageShow(pageid));

			out.print(new Gson().toJson(wc));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}

	private String getJSTicket(WxMp mp) throws Exception {
		String appid = mp.getAppid();
		Long expires = expiresCache.get(appid);
		if (expires == null || expires < System.currentTimeMillis()) {
			String access_token = mp.getAu_access_token();
			if (mp.getAppsecret() != null) {
				access_token = mp.getAccess_token();
			}
			if (access_token != null) {
				String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + access_token + "&type=jsapi";
				JSONObject obj = HttpsUtil.httpsRequest(url, "GET");
				if (obj.containsKey("errcode")) {
					int errcode = obj.getInt("errcode");
					if (errcode != 0) {
						System.out.println("error: getJsTicket-" + obj.toString());
						return null;
					}
				}

				String ticket = obj.getString("ticket");
				long expires_in = System.currentTimeMillis() + (obj.getLong("expires_in") - 200) * 1000;

				ticketCache.put(appid, ticket);
				expiresCache.put(appid, expires_in);
			}
		}
		return ticketCache.get(appid);
	}

	private WxPageShow getWxPageShow(long pageid) {
		WxPageShow wp = wpsCache.get(pageid);
		long ctime = System.currentTimeMillis();
		if (wp == null || wp.getCachetime() < ctime - 100 * 1000) {
			wp = wxPageShowMgr.getWxPageShow(pageid);
			if (wp != null) {
				wp.setCachetime(ctime);
				wpsCache.put(pageid, wp);
			}
		}
		return wp;
	}

	private static String byteToString(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		for (byte b : bytes) {
			String str = Integer.toHexString(b & 0xFF);
			if (str.length() == 1) {
				sb.append("0");
			}
			sb.append(str);
		}
		return sb.toString();
	}

	public void setWeiXinMgr(IWeiXinMgr weiXinMgr) {
		this.weiXinMgr = weiXinMgr;
	}

	public void setWxPageShowMgr(IWxPageShowMgr wxPageShowMgr) {
		this.wxPageShowMgr = wxPageShowMgr;
	}

	public long getMpid() {
		return mpid;
	}

	public void setMpid(long mpid) {
		this.mpid = mpid;
	}

	public long getPageid() {
		return pageid;
	}

	public void setPageid(long pageid) {
		this.pageid = pageid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
