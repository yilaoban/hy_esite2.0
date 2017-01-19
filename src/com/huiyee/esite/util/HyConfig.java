package com.huiyee.esite.util;

public class HyConfig {

	private static String memcachedServer;
	private static String imgDomain;// 图片域名
	private static String unifyloginurl;// 统一登录链接

	// ↓↓↓↓↓↓↓↓↓↓请在这里配置您支付宝的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	/*public static String alipartner = "2088911397711670";
	// 收款支付宝账号
	public static String aliseller_email = "415168305@qq.com";
	// 商户的私钥
	public static String alikey = "5adlvrpjhm9yue2nbt3feprhtuhvzzke";*/
	
	public static String alipartner;
	// 收款支付宝账号
	public static String aliseller_email;
	// 商户的私钥
	public static String alikey;

	private static String cloudDomain;
	private static String yiboDomain;
	private static String yiyouDomain;
	private static String rootPath;
	private static boolean isRun;
	private static String clearUrl;
	private static String allowClearIp;
	private static String yuming;
	private static String pageyuming;
	private static String hypageyuming;
	private static String showPagePath;
	private static String root;
	private static String tmp;
	private static String mcrmDomain;
	private static String crmDomain;

	private static String appid = "wxd141e40a0c911492";
	private static String appsecret = "a91161eb8e2aa6f41ad3e1cf7b1e0d48";
	private static String mchid = "1235398802";
	private static String key = "G7LMTCREVJSADID0PBOCSR04YP6KFW74";
	private static long adminowner=1140;
	
	
	
	public static String getMcrmDomain()
	{
		return mcrmDomain;
	}


	
	public void setMcrmDomain(String mcrmDomain)
	{
		HyConfig.mcrmDomain = mcrmDomain;
	}
	
	public static String getCrmDomain()
	{
		return crmDomain;
	}


	
	public void setCrmDomain(String crmDomain)
	{
		HyConfig.crmDomain = crmDomain;
	}


	public static long getAdminowner()
	{
		return adminowner;
	}

	
	public void setAdminowner(long adminowner)
	{
		HyConfig.adminowner = adminowner;
	}

	public static String getCloudDomain() {
		return cloudDomain;
	}

	public void setCloudDomain(String cloudDomain) {
		HyConfig.cloudDomain = cloudDomain;
	}

	public static String getYiboDomain() {
		return yiboDomain;
	}

	public void setYiboDomain(String yiboDomain) {
		HyConfig.yiboDomain = yiboDomain;
	}

	public static String getYiyouDomain() {
		return yiyouDomain;
	}

	public void setYiyouDomain(String yiyouDomain) {
		HyConfig.yiyouDomain = yiyouDomain;
	}

	public static String getUnifyloginurl() {
		return unifyloginurl;
	}

	public void setUnifyloginurl(String unifyloginurl) {
		HyConfig.unifyloginurl = unifyloginurl;
	}

	public static String getImgDomain() {
		return imgDomain;
	}

	public void setImgDomain(String imgDomain) {
		HyConfig.imgDomain = imgDomain;
	}

	public static String getRootPath() {
		return rootPath;
	}

	public void setRootPath(String rootPath) {
		HyConfig.rootPath = rootPath;
	}

	public static boolean isRun() {
		return isRun;
	}

	public void setRun(boolean isRun) {
		HyConfig.isRun = isRun;
	}

	public static String getMemcachedServer() {
		return memcachedServer;
	}

	public void setMemcachedServer(String memcachedServer) {
		HyConfig.memcachedServer = memcachedServer;
	}

	public static String[] getClearUrl() {
		return clearUrl.split(",");
	}

	public void setClearUrl(String clearUrl) {
		HyConfig.clearUrl = clearUrl;
	}

	public static String getAllowClearIp() {
		return allowClearIp;
	}

	public void setAllowClearIp(String allowClearIp) {
		HyConfig.allowClearIp = allowClearIp;
	}

	public static String getYuming() {
		return yuming;
	}

	public void setYuming(String yuming) {
		HyConfig.yuming = yuming;
	}

	public static String getPageyuming(long owner) {
		String rs = pageyuming;
		return rs;
	}

	public static String getPageyuming1() {
		String rs = pageyuming;

		return rs;
	}

	public void setPageyuming(String pageyuming) {
		HyConfig.pageyuming = pageyuming;
	}

	public static String getHypageyuming() {
		return hypageyuming;
	}

	public void setHypageyuming(String hypageyuming) {
		HyConfig.hypageyuming = hypageyuming;
	}

	public static String getShowPagePath() {
		return showPagePath;
	}

	public void setShowPagePath(String showPagePath) {
		HyConfig.showPagePath = showPagePath;
	}

	/**
	 * WEB-ROOT 路径
	 * 
	 * @return
	 */
	public static String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		HyConfig.root = root;
	}

	public static String getTmp() {
		return tmp;
	}

	public void setTmp(String tmp) {
		HyConfig.tmp = tmp;
	}

	public static void setAlipartner(String alipartner) {
		HyConfig.alipartner = alipartner;
	}

	public static void setAliseller_email(String aliseller_email) {
		HyConfig.aliseller_email = aliseller_email;
	}

	public static void setAlikey(String alikey) {
		HyConfig.alikey = alikey;
	}

	public static String getPageyuming() {
		return pageyuming;
	}

	public static String getAlipartner() {
		return alipartner;
	}

	public static String getAliseller_email() {
		return aliseller_email;
	}

	public static String getAlikey() {
		return alikey;
	}

	public static String getAppid() {
		return appid;
	}

	
	public static void setAppid(String appid)
	{
		HyConfig.appid = appid;
	}

	public static String getAppsecret() {
		return appsecret;
	}

	
	public static void setAppsecret(String appsecret)
	{
		HyConfig.appsecret = appsecret;
	}

	public static String getMchid() {
		return mchid;
	}

	
	public static void setMchid(String mchid)
	{
		HyConfig.mchid = mchid;
	}

	public static String getKey() {
		return key;
	}

	
	public static void setKey(String key)
	{
		HyConfig.key = key;
	}
}
