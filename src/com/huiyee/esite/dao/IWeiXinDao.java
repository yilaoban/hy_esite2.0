package com.huiyee.esite.dao;

import java.util.List;
import com.huiyee.esite.model.WeiXinPage;
import com.huiyee.esite.model.WeixinApp;
import com.huiyee.esite.model.WxOA;
import com.huiyee.esite.model.WxUser;
import com.huiyee.weixin.model.WxMp;
import com.huiyee.weixin.model.WxPageShow;

public interface IWeiXinDao
{
	public WeiXinPage findWXPage(long id);
	
	public WeiXinPage findWXPageByPageid(long pageid);
	
	public WeiXinPage findWXPageBySitegroup(long pageid);
	
	public WeiXinPage findWXPageByPP(long pageid);
	
	public WeiXinPage findWXPageByPS(long pageid);
	
	public WxMp findWxMpById( long id);
	
	public WxMp findWxMpByOwner( long owner);
	
	
	/**
	 * 默认搜索出权限最高的授权
	 * @param mpid
	 * @return
	 */
	public WxOA findWxOA(long mpid);
	
	public int saveToken(long mpid,String access_token,long expires_in);
	
	public int saveAuthToken(WxOA woa);
	
	public WxUser getWxUserById(long id);
	
	public void addWXUser(WxUser wp);
	
	public void updateWXUser(long id,WxUser wp);
	
	public long findWXUserId(String openid);
	
	public long saveWxPageShow(long pageid,long appid,String pic,String infoed);
	
	public int updatePagePic(long pageid,String pic);
	
	public int updatePagesubWx(long pageid,String status);
	
	public List<WeiXinPage> findWeiXinPageList(long pageid);
	
	public String findPagePic(long wxapageid);
	
	public int findWeixinappByOwneridTotal(long ownerid);
	
	public List<WeixinApp> findWeixinappByOwnerid(long ownerid,int start,int size);
	
	public long updateWxPageShow(long showid,String pic,String infoed);

	public String findNickNameById(long wbuid);
	
	public int updatePageAddressStatus(long pageid);
	
	public int findWeixinCount(long pageid);
	
	public WxUser findWxUserByOpenid(String openid);
	
	public WxPageShow findWxPageShowBySitegroupId(long sgid);

	public WxPageShow findWxPageShowBySiteId(long siteid);
	
	
	
}
