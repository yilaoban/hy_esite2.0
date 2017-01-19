package com.huiyee.esite.constants;

public interface IInteractConstants
{
	public static final int INTERACT_APT = 118; // 申请featureid
	public static final int INTERACT_WEIXINSHARE = 100; // 微信分享
	public static final int INTERACT_VOTE = 123;// 投票featureid
	public static final int INTERACT_RESEARCH = 124;// 调研featureid
	public static final int INTERACT_SPREAD = 134;// 口碑营销featureid
	public static final int INTERACT_ZLOTTERY = 136;// 大转盘featureid
	public static final int INTERACT_GLOTTERY = 138;// 刮刮乐featureid
	public static final int INTERACT_LLOTTERY = 125;// 老虎机featureid
	public static final int INTERACT_YLOTTERY = 143;// 摇一摇featureid
	public static final int INTERACT_AUCTION = 126;// 竞拍featureid
	public static final int INTERACT_RQ = 144;// 人气featureid
	public static final int INTERACT_CS = 145;// 散播featureid
	public static final int INTERACT_XC = 146;// 现场featureid
	public static final int INTERACT_EXAM = 153;// 调研featureid
	/** 申请 */
	public static final int APT_LIMIT = 10;
	public static final String APT_DESC = "申请获得积分";
	public static final int APT_RECORD_LIMIT = 10;
	/**auction*/
	public static int AUCTION_LIST_LIMIT = 20;
	public static int AUCTION_RECORD_LIMIT = 20;
	/**checkin*/
	public static int CHECK_DATA_LIMIT = 20;
	public static int CHECK_LIMIT = 10;
	/**emailPeriodical*/
	public static final int EMAIL_LIMIT = 10;
	/**journal*/
	public static final int VOTE_LIMIT = 25;
	public static final int TYPE_INTERACT = 4;
	public static final int JOURNAL_LIMIT = 10;
	public static final int JOURNALRECORD_LIMIT = 10;
	public static final String JOURNAL_DESC = "微期刊转发获得积分";
	/**lottery*/
	public static int LOTTERY_LIST_LIMIT = 20;
	public static int LOTTERY_USER_LIMIT = 20;
	public static int LOTTERY_USER_RECORD_LIMIT = 20;
	public static int LOTTERYPRIZE_LIST_LIMIT = 10;
	/**spread*/
	public static final int SPREADOPTION_LIMIT = 10;
	public static final int SPREADRECORD_LIMIT = 10;
	public static final String SPREAD_TYPE_SRE = "SRE";//内容随机直发
	public static final String SPREAD_TYPE_FWD = "FWD";//选择内容直发
	public static final String SPREAD_TYPE_FAC = "FAC";//转发并评论
	/** vote*/
	public static final String VOTE_DESC = "投票获得积分";
	public static final String RESEARCH_DESC = "调研获得积分";
	public static final int INTERACT_WXSHARE  = 100;//微信分享是针对页面的,当featureid为此时,hid就是pageid
	
	public static final int INTERACT_RENQI_LIMIT=20;
	public static final int INTERACT_RENQI_DATA_LIMIT=30;
	public static final int INTERACT_RENQI_DETAIL_LIMIT=10;
	public static final int INTERACT_CS_LIMIT=20;
	public static final int INTERACT_CS_DATA_LIMIT=20;
	/** 现场抽奖*/
	public static final int INTERACT_XC_LIMIT=20;
	/** comment*/
	public static final int INTERACT_COMMENT_LIMIT=10;
	
	public static final int INTERACT_XC_RECORD_LIMIT=30;
	
	public static final int INTERACT_BBS_LIMIT=10;
	
	public static final int INTERACT_MODEL_ZHUANPAN=10003;
	public static final int INTERACT_MODEL_ZJD=10004;
	
	public static final int INTERACT_CB_ACTIVITY=10;
	
	public static final int INTERACT_RECORD=10;
	
	public static final int JF_LIMIT =10;
	
	public static final String GZ_EVENT_XC="X";
	public static final String GZ_EVENT_CHECKIN="C";
	
	public static final int INTERACT_SERVICER_LIMIT=20;
	public static final int INTERACT_SERVICER_PJ_LIMIT=20;
	
	public static final int INTERACT_AD_WAY_GG=9;
}
