package com.huiyee.esite.ws.imp;

import java.util.List;

import com.huiyee.esite.service.IMemCacheAble;
import com.huiyee.esite.ws.WeiBoService;
import com.huiyee.tfmodel.HyWbComment;
import com.huiyee.tfmodel.HyWbLogin;
import com.huiyee.tfmodel.HyWbSrc;
import com.huiyee.tfmodel.HyWbUser;
import com.huiyee.tfmodel.HyWbUserInfo;
import com.huiyee.tfmodel.param.TfKeyWordDto;
import com.huiyee.tfmodel.param.TfPageJoinDto;
import com.huiyee.tfmodel.param.TfVpsCommentDto;
import com.huiyee.tfmodel.param.TfWeiBoMonitorDto;
import com.huiyee.tfmodel.result.TfPageForKeyWord;
import com.huiyee.tfmodel.result.TfPageForWbid;

public class WeiBoServiceImpl implements WeiBoService, IMemCacheAble
{

	private WeiBoService weiBoService;
	private int cacheTime;

	public int getCacheTime()
	{
		return cacheTime;
	}

	public void setCacheTime(int cacheTime)
	{
		this.cacheTime = cacheTime;
	}

	public WeiBoService getWeiBoService()
	{
		return weiBoService;
	}

	public void setWeiBoService(WeiBoService weiBoService)
	{
		this.weiBoService = weiBoService;
	}

	@Override
	public int findStatus(String type, String typekey) {
		return weiBoService.findStatus(type, typekey);
	}

	@Override
	public HyWbUser findUserByNickname(String token, String nickname) {
		return weiBoService.findUserByNickname(token, nickname);
	}

	@Override
	public HyWbUser findUserByid(String token, long wbuid) {
		return weiBoService.findUserByid(token, wbuid);
	}

	@Override
	public HyWbSrc findWeiBoByWbUrl(String token, String url) {
		return weiBoService.findWeiBoByWbUrl(token, url);
	}

	@Override
	public String loginForLink(int cmd_type, String redirect_uri, String state,
			String appkey) {
		return weiBoService.loginForLink(cmd_type, redirect_uri, state, appkey);
	}

	@Override
	public HyWbLogin loginForWeiBo(String code, String appkey,
			String appsecret, String redirect_URI) {
		return weiBoService.loginForWeiBo(code, appkey, appsecret, redirect_URI);
	}

	@Override
	public TfPageForKeyWord findWeibosByKeyword(TfKeyWordDto keyWordDto) {
		return weiBoService.findWeibosByKeyword(keyWordDto);
	}

	@Override
	public TfPageForWbid findWeibosByWbid(TfWeiBoMonitorDto weiBoMonitorDto) {
		return weiBoService.findWeibosByWbid(weiBoMonitorDto);
	}

	@Override
	public int vpscomments(List<TfVpsCommentDto> cms) {
		return weiBoService.vpscomments(cms);
	}

	@Override
	public HyWbUserInfo findHUserByid(long wbuid) {
		return weiBoService.findHUserByid(wbuid);
	}

	@Override
	public HyWbSrc findWeiBoByWbid(String token, String wbid, boolean fast) {
		return weiBoService.findWeiBoByWbid(token, wbid, fast);
	}

	@Override
	public HyWbComment replycomment(String cid, String wbid, String token,
			String content) {
		return weiBoService.replycomment(cid, wbid, token, content);
	}

	@Override
	public HyWbSrc repost(String id, String content, String token,
			Integer is_comment) {
		return weiBoService.repost(id, content, token, is_comment);
	}

	@Override
	public HyWbComment wbcomment(String wbid, String token, String content) {
		return weiBoService.wbcomment(wbid, token, content);
	}

	@Override
	public HyWbSrc weibo(String token, String content) {
		return weiBoService.weibo(token, content);
	}

	@Override
	public HyWbSrc weiboByPic(String token, String content, String img) {
		return weiBoService.weiboByPic(token, content, img);
	}

	@Override
	public int addKeyWordsJob(String keywords) {
		return weiBoService.addKeyWordsJob(keywords);
	}

	@Override
	public HyWbUser guanzhu(String token, long wbuid) {
		return weiBoService.guanzhu(token, wbuid);
	}

	@Override
	public int addUserJoinLog(List<TfPageJoinDto> joinDto) {
		return weiBoService.addUserJoinLog(joinDto);
	}

	@Override
	public List<String> findFansBySchName(String token, String key, int count) {
		return weiBoService.findFansBySchName(token, key, count);
	}

	@Override
	public HyWbUserInfo findHTUserByid(String token, long wbuid) {
		return weiBoService.findHTUserByid(token, wbuid);
	}

	@Override
	public List<String> findWbuidActiveFans(String token, long wbuid, int count) {
		return weiBoService.findWbuidActiveFans(token, wbuid, count);
	}

	@Override
	public HyWbSrc findWeiBoByMWbid(String token, String url) {
		return weiBoService.findWeiBoByMWbid(token, url);
	}

}
