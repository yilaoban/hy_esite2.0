package com.huiyee.weixin.model.message;

import net.sf.json.JSONObject;

public class ErrMsg {

	private int errcode;
	private String errmsg;
	private String msgid;
	private String template_id;

	public ErrMsg() {
		super();
	}

	public ErrMsg(JSONObject json) {
		super();
		if (json.containsKey("errcode")) {
			errcode = json.getInt("errcode");
		}
		if (json.containsKey("errmsg")) {
			errmsg = json.getString("errmsg");
		}
		if (json.containsKey("msgid")) {
			msgid = json.getString("msgid");
		}
		if (json.containsKey("template_id")) {
			template_id = json.getString("template_id");
		}
	}

	public int getErrcode() {
		return errcode;
	}

	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public String getMsgid() {
		return msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

}
