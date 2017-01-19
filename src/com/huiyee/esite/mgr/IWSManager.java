package com.huiyee.esite.mgr;

import java.util.List;

import com.huiyee.tfmodel.HyWbComment;
import com.huiyee.tfmodel.HyWbLogin;
import com.huiyee.tfmodel.HyWbSrc;
import com.huiyee.tfmodel.HyWbUser;

public interface IWSManager {
	
	/**
	 * 通过微博链接找到微博
	 * 
	 * @param token
	 *            微博token
	 * @param url
	 *            微博链接
	 * @return 返回微博
	 */
	public HyWbSrc findWeiBoByWbUrl(String token,
			String url);

	/**
	 * 通过wbid查找id
	 * @param token
	 * @param wbid
	 * @return
	 */
	public HyWbSrc findWeiBoByWbid(String token, String wbid,boolean fast);
	
	/**
	 * AT 相关联系人
	 * @param token
	 * @param key
	 * @return
	 */
	public List<String> findAtUsers(String token,String key,int size,long wbuid);
	
	/**
	 * 
	 * @param token
	 *            发送微博者的token
	 * @param content
	 *            发送微博的内容
	 * @return 返回wbid
	 */
	public HyWbSrc weibo(String token, String content);

	/**
	 * 
	 * @param token
	 *            发送者的token
	 * @param content
	 *            发送微博的内容
	 * @param img
	 *            img的url地址
	 * @return 返回wbid
	 */
	public HyWbSrc weiboByPic(String token, String content, String img);
	
	/**
	 * 转发一条微博
	 * 
	 * @param id
	 *            要转发的微博ID
	 * @param content
	 *            添加的转发文本，必须做URLencode，内容不超过140个汉字，不填则默认为“转发微博”
	 * @param is_comment
	 *            是否在转发的同时发表评论，0：否、1：评论给当前微博、2：评论给原微博、3：都评论，默认为0
	 * @return 返回wbid
	 */
	public HyWbSrc repost(String id, String content, String token, Integer is_comment);
	
	/**
	 * 
	 * @param wbid
	 *            评论的微博id
	 * @param token
	 *            评论者的token
	 * @param content
	 *            评论的内容
	 * @return 0:失败;1:成功
	 */
	public HyWbComment wbcomment(String wbid, String token, String content);
	
	/**
	 * 加关注
	 * @param token 登陆者
	 * @param wbuid 被关注的人,一般为官微
	 * @return
	 */
	public HyWbUser guanzhu(String token,long wbuid);
	
	/**
	 * 
	 * @param cmd_type
	 *            1.代表登陆授权
	 * @param redirect_uri
	 *            回调地址
	 * @param state
	 *            传入的参数,最后会传给回调函数
	 * @param appkey 申请的appid
	 * @return
	 */

	public String loginForLink(int cmd_type, String redirect_uri, String state,String appkey);
	
	/**
	 * 
	 * @param code
	 *            sina传过来的code用来换取token值
	 * @param appkey 申请的appid
	 * @param appsecret 申请的app的密钥
	 * @redirect_URI 返回地址,可以不填写
	 * @return 返回LoginWeiBoDto对象,里面包含token信息,失效时间(毫秒数),wbuid
	 */

	public HyWbLogin loginForWeiBo(String code,String appkey,String appsecret,String redirect_URI);
	
	/**
	 * 通过wbuid找user,通过接口获得
	 * @param wbuid
	 * @return
	 */
			
	public HyWbUser findUserByid(String token,long wbuid);
}
