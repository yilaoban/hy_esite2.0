package com.huiyee.weixin.model;

import net.sf.json.JSONObject;

public class WxMp {

	private long id;
	private long owner;
	private String appid;
	private String nick_name;
	private String head_img;
	private int service_type_info;
	private int verify_type_info;
	private String user_name;
	private String alias;
	private String qrcode_url;
	private String token;//公众号平台填写的token。当有值时是默认的形式的调用接口形式
	private String appsecret;//公众号平台生成的秘钥。当有值时是默认的形式的调用接口形式
	private String access_token;//默认的access_token
	private long expires_in;//默认的过期时间
    /*
     * 红包证书的参数
     */
	private String mch_id;
	private String cert_path;
	private String key;
	/*
	 * 开放平台的参数
	 */
    private String third_appid;
    private long third_expires_in;
    private String third_access_token;//开放平台第三方的access_token
    /*
     *授权后获得的参数 
     */
    private String au_access_token;//授权后获得的access_token
	private String au_refresh_token;//授权后获得的刷新token
	private long au_expires_in;//openauth的过期时间
	
	public boolean isWork()
	{
		if(access_token!=null&&expires_in>System.currentTimeMillis())
		{
			return true;
		}
		if(au_access_token!=null&&au_expires_in>System.currentTimeMillis()&&third_access_token!=null&&third_expires_in>System.currentTimeMillis())
		{
			return true;
		}
		return false;
	}
	
	public long getThird_expires_in()
	{
		return third_expires_in;
	}
	
	public void setThird_expires_in(long third_expires_in)
	{
		this.third_expires_in = third_expires_in;
	}

	public long getOwner()
	{
		return owner;
	}

	
	public void setOwner(long owner)
	{
		this.owner = owner;
	}

	public long getExpires_in()
	{
		return expires_in;
	}
	
	public void setExpires_in(long expires_in)
	{
		this.expires_in = expires_in;
	}
	public String getAu_access_token()
	{
		return au_access_token;
	}

	
	public String getAu_refresh_token()
	{
		return au_refresh_token;
	}

	
	public long getAu_expires_in()
	{
		return au_expires_in;
	}

	
	public void setAu_access_token(String au_access_token)
	{
		this.au_access_token = au_access_token;
	}

	
	public void setAu_refresh_token(String au_refresh_token)
	{
		this.au_refresh_token = au_refresh_token;
	}

	public void setAu_expires_in(long au_expires_in)
	{
		this.au_expires_in = au_expires_in;
	}
	

	public String getThird_access_token()
	{
		return third_access_token;
	}
	
	public void setThird_access_token(String third_access_token)
	{
		this.third_access_token = third_access_token;
	}


	public String getAppsecret()
	{
		return appsecret;
	}
	
	public void setAppsecret(String appsecret)
	{
		this.appsecret = appsecret;
	}
	
	public String getToken()
	{
		return token;
	}



	
	public void setToken(String token)
	{
		this.token = token;
	}


	public String getThird_appid()
	{
		return third_appid;
	}

	
	public void setThird_appid(String third_appid)
	{
		this.third_appid = third_appid;
	}


	public String getAccess_token()
	{
		return access_token;
	}

	

	
	public void setAccess_token(String access_token)
	{
		this.access_token = access_token;
	}

	
	public WxMp() {
		super();
	}

	public WxMp(JSONObject obj) {
		super();
		try {
			JSONObject authorizer_info = obj.getJSONObject("authorizer_info");
			JSONObject service_type = authorizer_info.getJSONObject("service_type_info");
			JSONObject verify_type = authorizer_info.getJSONObject("verify_type_info");

			if (authorizer_info.containsKey("nick_name")) {
				this.nick_name = authorizer_info.getString("nick_name");
			}
			if (authorizer_info.containsKey("head_img")) {
				this.head_img = authorizer_info.getString("head_img");
			}
			this.service_type_info = service_type.getInt("id");
			this.verify_type_info = verify_type.getInt("id");
			if (authorizer_info.containsKey("user_name")) {
				this.user_name = authorizer_info.getString("user_name");
			}
			if (authorizer_info.containsKey("alias")) {
				this.alias = authorizer_info.getString("alias");
			}
			if (authorizer_info.containsKey("qrcode_url")) {
				this.qrcode_url = authorizer_info.getString("qrcode_url");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getHead_img() {
		return head_img;
	}

	public void setHead_img(String head_img) {
		this.head_img = head_img;
	}

	public int getService_type_info() {
		return service_type_info;
	}

	public void setService_type_info(int service_type_info) {
		this.service_type_info = service_type_info;
	}

	public int getVerify_type_info() {
		return verify_type_info;
	}

	public void setVerify_type_info(int verify_type_info) {
		this.verify_type_info = verify_type_info;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getQrcode_url() {
		return qrcode_url;
	}

	public void setQrcode_url(String qrcode_url) {
		this.qrcode_url = qrcode_url;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getCert_path() {
		return cert_path;
	}

	public void setCert_path(String cert_path) {
		this.cert_path = cert_path;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
