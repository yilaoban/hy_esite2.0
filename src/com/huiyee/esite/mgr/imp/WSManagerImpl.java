package com.huiyee.esite.mgr.imp;

import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.huiyee.esite.mgr.IWSManager;
import com.huiyee.esite.model.Account;
import com.huiyee.tfmodel.HyWbComment;
import com.huiyee.tfmodel.HyWbLogin;
import com.huiyee.tfmodel.HyWbSrc;
import com.huiyee.tfmodel.HyWbUser;
import com.opensymphony.xwork2.ActionContext;

public class WSManagerImpl extends AbstractMgr implements IWSManager {

	@Override
	public HyWbSrc findWeiBoByWbUrl(String token, String url) {
		return this.longWS.findWeiBoByWbUrl(token, url);
	}

	@Override
	public HyWbSrc findWeiBoByWbid(String token, String wbid,boolean fast) {
		return this.longWS.findWeiBoByWbid(token, wbid, fast);
	}

	@Override
	public List<String> findAtUsers(String token, String key,int size,long wbuid) {
		StringBuilder sb = new StringBuilder("");
		List<String> result = null;
		if(!StringUtils.isEmpty(key) ){
			result = getLongWS().findFansBySchName(token, key, 5);
		}else{
			 result = getLongWS().findWbuidActiveFans(token, wbuid, 5);
		}
		return result;
	}

	@Override
	public HyWbUser findUserByid(String token, long wbuid) {
		return getLongWS().findUserByid(token, wbuid);
	}

	@Override
	public HyWbUser guanzhu(String token, long wbuid) {
		return getJustWS().guanzhu(token, wbuid);
	}

	@Override
	public String loginForLink(int cmd_type, String redirect_uri, String state,
			String appkey) {
		return getJustWS().loginForLink(cmd_type, redirect_uri, state, appkey);
	}

	@Override
	public HyWbLogin loginForWeiBo(String code, String appkey,
			String appsecret, String redirect_URI) {
		return getJustWS().loginForWeiBo(code, appkey, appsecret, redirect_URI);
	}

	@Override
	public HyWbSrc repost(String id, String content, String token,
			Integer is_comment) {
		return getJustWS().repost(id, content, token, is_comment);
	}

	@Override
	public HyWbComment wbcomment(String wbid, String token, String content) {
		return getJustWS().wbcomment(wbid, token, content);
	}

	@Override
	public HyWbSrc weibo(String token, String content) {
		return getJustWS().weibo(token, content);
	}

	@Override
	public HyWbSrc weiboByPic(String token, String content, String img) {
		return getJustWS().weiboByPic(token, content, img);
	}

}
