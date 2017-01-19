package com.huiyee.esite.dto;

public class HdRsDto {
	/**
	 * $$$$公共状态$$$$
	 * -10000：互动的id不存在；
	 * -10001：提交过于频繁；
	 * -10002：互动的时间未开始，页面提示直接用hydesc提示；
	 * -10003：互动已经结束，页面提示直接用hydesc提示
	 * -10004：互动用户不存在；
	 * -10005：互动总次数达到上限；
	 * -10006：互动每天的次数达到上限；
	 * -11000：互动失败,严重问题；
	 *  10000：中间状态，页面不需要处理；
	 *  1：互动成功；
	 * $$$$会易表单提交$$$$
	 * -1：验证码不正确
	 * $$$$会易竞拍$$$$
	 * -1：积分不够；
	 * -2:竞拍价格低；
	 * -3:竞拍时间结束；
	 * $$$$会易抽奖$$$$
	 * -4：没在微现场签到；
	 * -5：不是微信关注者；
	 * -7：积分不够抽奖；
	 * -8：指定用户条件不存在；
	 * -9：用户中奖达到上限；
	 *  0：未中奖；
	 *  1：中了积分；
	 *  2：中了优惠劵；
	 *  3：中了实物；
	 *  4：微信红包；
	 *  5：红包兑换码；
	 *  6: 自提商品；
	 *  7:不认识的奖品类型;
	 *  8:获取成功用户的剩余抽奖次数
	 *  $$$$会易投票$$$$
	 *  -1:没有选择选项；
	 *  -2:每人每天对于一个选择只有一次机会；
	 */
	private int status;
	private String hydesc;
	/**
	 * 一般为互动成功失败后，需要跳转的pageid；
	 */
	private long id;
	/**
	 * 成功或者失败，这个优先级高！
	 */
	private String url;
	/**
	 * 跳转的url地址.
	 */
	private String furl;
	
	private int tnum;//今天剩余次数
	private int num;//总共剩余次数
	
	private int shownum;//用来前台显示的剩余次数
	
	public int getShownum()
	{
		return shownum;
	}

	
	public void setShownum(int shownum)
	{
		this.shownum = shownum;
	}

	public int getTnum()
	{
		return tnum;
	}
	
	public int getNum()
	{
		return num;
	}
	
	public void setTnum(int tnum)
	{
		this.tnum = tnum;
	}
	
	public void setNum(int num)
	{
		this.num = num;
	}

	public String getFurl()
	{
		return furl;
	}
	
	public void setFurl(String furl)
	{
		this.furl = furl;
	}

	public String getUrl()
	{
		return url;
	}
	
	public void setUrl(String url)
	{
		this.url = url;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getHydesc() {
		return hydesc;
	}

	public void setHydesc(String hydesc) {
		this.hydesc = hydesc;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


}
