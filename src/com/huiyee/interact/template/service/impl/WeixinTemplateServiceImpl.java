package com.huiyee.interact.template.service.impl;

import com.huiyee.interact.template.service.IWeixinTemplateService;
import com.huiyee.weixin.model.message.ErrMsg;
import com.huiyee.weixin.utils.HttpsUtil;

import net.sf.json.JSONObject;

public class WeixinTemplateServiceImpl implements IWeixinTemplateService {

	@Override
	public ErrMsg setIndustry(String access_token, long industry_id1, long industry_id2) {
		try {
			if (access_token == null || industry_id1 == 0 || industry_id2 == 0) {
				return null;
			}

			String url = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=" + access_token;
			JSONObject json = new JSONObject();
			json.put("industry_id1", industry_id1);
			json.put("industry_id2", industry_id2);
			JSONObject obj = HttpsUtil.httpsRequest(url, "POST", json.toString());

			ErrMsg errMsg = new ErrMsg(obj);
			return errMsg;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public JSONObject getIndustry(String access_token) {
		try {
			if (access_token == null) {
				return null;
			}

			String url = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=" + access_token;
			JSONObject obj = HttpsUtil.httpsRequest(url, "GET");

			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ErrMsg addTemplate(String access_token, String template_id_short) {
		try {
			if (access_token == null || template_id_short == null) {
				return null;
			}

			String url = "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=" + access_token;
			JSONObject json = new JSONObject();
			json.put("template_id_short", template_id_short);
			JSONObject obj = HttpsUtil.httpsRequest(url, "POST", json.toString());

			ErrMsg errMsg = new ErrMsg(obj);
			return errMsg;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public JSONObject getTemplate(String access_token) {
		try {
			if (access_token == null) {
				return null;
			}

			String url = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=" + access_token;
			JSONObject obj = HttpsUtil.httpsRequest(url, "GET");

			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ErrMsg delTemplate(String access_token, String template_id) {
		try {
			if (access_token == null || template_id == null) {
				return null;
			}

			String url = "https://api.weixin.qq.com/cgi-bin/template/del_private_template?access_token=" + access_token;
			JSONObject json = new JSONObject();
			json.put("template_id", template_id);
			JSONObject obj = HttpsUtil.httpsRequest(url, "POST", json.toString());

			ErrMsg errMsg = new ErrMsg(obj);
			return errMsg;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ErrMsg send(String access_token, String json) {
		try {
			if (access_token == null || json == null) {
				return null;
			}

			String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + access_token;
			JSONObject obj = HttpsUtil.httpsRequest(url, "POST", json);

			ErrMsg errMsg = new ErrMsg(obj);
			return errMsg;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
