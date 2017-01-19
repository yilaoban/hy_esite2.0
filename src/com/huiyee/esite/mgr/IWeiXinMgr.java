package com.huiyee.esite.mgr;

import java.util.List;

import com.huiyee.esite.model.WeiXinPage;
import com.huiyee.esite.model.WxUser;
import com.huiyee.weixin.model.WxMp;
import com.huiyee.weixin.model.WxPageShow;

public interface IWeiXinMgr
{
	public WxMp updateFindWxMp(long owner,long id);
	
	public WeiXinPage findWXPage(long id);
	
	public WeiXinPage findWXPageByPageid(long pageid,long owner);
	
	public WxUser addWXUser(WxUser wp);
	
	/**
	 * °ó¶¨Î¢ÐÅ
	 * @param pageid
	 * @param ownerid
	 * @param name
	 * @param appid
	 * @param appsecret
	 * @param pic
	 * @param infoed
	 * @return
	 */
//	public long savePublic2qq(long pageid,long ownerid, String name, String appid,
//			String appsecret, String pic,String infoed);
	
	public List<WeiXinPage> findWeiXinPageList(long pageid);
	
	public String findPagePic(long wxapageid);	
	
	public int findWeixinCount(long pageid);
	
	public WxUser findWxUserByOpenid(String openid);
	
	 public WxUser getWxUserById(long id);
	 
	 public WxPageShow findWxPageShowBySitegroupId(long sgid);

	public WxPageShow findWxPageShowBySiteId(long id);
}
