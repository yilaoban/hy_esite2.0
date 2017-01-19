package com.huiyee.esite.action;

import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.model.WxConfig;
import com.huiyee.esite.util.WeixinOauth;

public class WXJSSDKAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	private final String appid = "wxd141e40a0c911492";
	private final String appsecret = "a91161eb8e2aa6f41ad3e1cf7b1e0d48";
	private final String noncestr = "huiyee";
	private String url;

	@Override
	public String execute() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();

		try {
			String timestamp = System.currentTimeMillis() + "";
			String jsapi_ticket = "";

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

			out.print(new Gson().toJson(wc));
			out.flush();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	private String byteToString(byte[] bytes) {
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
