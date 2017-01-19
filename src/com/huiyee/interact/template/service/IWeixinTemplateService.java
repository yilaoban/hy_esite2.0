package com.huiyee.interact.template.service;

import com.huiyee.weixin.model.message.ErrMsg;

import net.sf.json.JSONObject;

public interface IWeixinTemplateService {

	// 设置所属行业
	public ErrMsg setIndustry(String access_token, long industry_id1, long industry_id2);

	// 获取设置的行业信息
	public JSONObject getIndustry(String access_token);

	// 获得模板ID
	public ErrMsg addTemplate(String access_token, String template_id_short);

	// 获取模板列表
	public JSONObject getTemplate(String access_token);

	// 删除模板
	public ErrMsg delTemplate(String access_token, String template_id);

	// 发送模板消息
	public ErrMsg send(String access_token, String json);

}
