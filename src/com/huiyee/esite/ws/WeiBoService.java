package com.huiyee.esite.ws;

import java.util.List;

import javax.jws.WebService;

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

@WebService
public interface WeiBoService
{
	/**
	 * 添加关键词搜索任务,次任务在用户添加关键词时添加
	 * @param keywords
	 * @return 0:失败;1:成功
	 */
	public int addKeyWordsJob(String keywords);
	
	/**
	 * 查找用户的活跃粉丝
	 * @param token
	 * @param wbuid
	 * @param count 返回的个数
	 * @return
	 */
	public List<String> findWbuidActiveFans(String token,long wbuid,int count);
	

	/**
	 * at好友时需要用到的,通过名称模糊查找
	 * @param token
	 * @param key
	 * @param count
	 * @return
	 */
	public List<String> findFansBySchName(String token,String key,int count);
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
	 * 回复别人的评论
	 * @param cid
	 * @param token
	 * @param content
	 * @return
	 */
	public HyWbComment replycomment(String cid,String wbid, String token, String content);


	/**
	 * 通过水军发送的评论 接受成功不代表发送成功 验证成功需要再次调用类似是否发送成功接口
	 * 
	 * @param cms
	 *            list,单个元素属性有wbuid,wbid,content,key(生成的唯一key,用来以后查询发送情况)
	 * @return 0:接受失败;1:接受成功
	 */
	public int vpscomments(List<TfVpsCommentDto> cms);

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
	 * 查看发送状态
	 * 
	 * @param type
	 *            VC-vpscommet;CM-comment;WB-weibo;RP-repost
	 * @param typekey
	 * @return 0:未发送;1:成功;2:失败
	 */
	public int findStatus(String type, String typekey);
	
	/**
	 * 通过wbuid找user,通过接口获得
	 * @param wbuid
	 * @return
	 */
			
	public HyWbUser findUserByid(String token,long wbuid);
	
	/**
	 * 加关注
	 * @param token 登陆者
	 * @param wbuid 被关注的人,一般为官微
	 * @return
	 */
	public HyWbUser guanzhu(String token,long wbuid);
	
	/**
	 * 通过wbuid找user,通过hbase获得
	 * @param wbuid
	 * @return
	 */
			
	public HyWbUserInfo findHUserByid(long wbuid);
	
	/**
	 * 通过wbuid找user,通过hbase获得
	 * 自己知道的token
	 * @param wbuid
	 * @return
	 */
			
	public HyWbUserInfo findHTUserByid(String token,long wbuid);
	
	/**
	 * 通过 昵称找user
	 * @param token
	 * @param nickname
	 * @return
	 */
    public HyWbUser findUserByNickname(String token,String nickname);
    
	
	/**
	 * 通过微博链接找到微博,同时加入监控
	 * 
	 * @param token
	 *            微博token
	 * @param url
	 *            微博链接
	 * @return 返回微博
	 */
	public HyWbSrc findWeiBoByWbUrl(String token, String url);
	
	/**
	 * 通过微博id找到微博,同时加入监控
	 * 
	 * @param token
	 *            微博token
	 * @param url
	 *            微博链接
	 * @return 返回微博
	 */
	public HyWbSrc findWeiBoByMWbid(String token, String url);
	
	
	/**
	 * 通过微博id找到微博
	 * 
	 * @param token
	 *            微博token
	 * @param url
	 *            微博链接
	 * @return 返回微博
	 */
	public HyWbSrc findWeiBoByWbid(String token, String wbid,boolean fast);
	
	
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
	 * 
	 * @param keyWordDto
	 * @return Tfpage里面object为list<hywbsrc>
	 */
	public TfPageForKeyWord findWeibosByKeyword(TfKeyWordDto keyWordDto);
	
   /**
    * 
    * @param weiBoMonitorDto
    * @return Tfpage里面object为list<hywbcomment>或者list<hywbsrc>
    */
	public TfPageForWbid findWeibosByWbid(TfWeiBoMonitorDto weiBoMonitorDto);
	
	/**
	 * 易站互动访问日志
	 * 
	 * @param joinDto
	 * @return 0:插入失败,1:插入成功
	 */
	public int addUserJoinLog(List<TfPageJoinDto> joinDto);

}
