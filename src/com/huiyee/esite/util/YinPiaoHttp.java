
package com.huiyee.esite.util;

import java.io.IOException;
import java.net.URLDecoder;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * 
 */
public class YinPiaoHttp
{

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String doGet(String url, String queryStr,String atoken)
	{
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
		HttpGet httpGet = null;
		try
		{
			if (StringUtils.isNotEmpty(queryStr))
			{
				url=url + "?" +queryStr;
			} 
			httpGet = new HttpGet((url).trim().replaceAll(" ", "%20"));
			 httpGet.setConfig(RequestConfig.DEFAULT);
	            if (StringUtils.isNotEmpty(atoken))
	                httpGet.setHeader("atoken", URLDecoder.decode(atoken, "UTF-8"));
			HttpResponse httpResponse = closeableHttpClient.execute(httpGet);
			HttpEntity entity = httpResponse.getEntity();
			if (entity != null)
			{
				return EntityUtils.toString(entity,"utf-8");
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				httpGet.releaseConnection();
				closeableHttpClient.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * post
	 * 
	 * @param url
	 * @param json
	 * @return
	 */

	public static JSONObject doPost(String url, JSONObject json,String atoken)
	{
		HttpClient client = HttpClientBuilder.create().build();
		  HttpPost post = new HttpPost((url).trim().replaceAll(" ", "%20"));
		JSONObject response = null;
		try
		{
			StringEntity httpEntity = new StringEntity(json.toString(), "UTF-8");
            httpEntity.setChunked(true);
            post.setEntity(httpEntity);
            if (StringUtils.isNotEmpty(atoken))
                post.setHeader("atoken", URLDecoder.decode(atoken, "UTF-8"));
            post.addHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
			HttpResponse res = client.execute(post);
			if (res.getEntity()!=null)
			{
				String result = EntityUtils.toString(res.getEntity());// 
				response = JSONObject.fromObject(result);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		 finally
			{
				try
				{
					post.releaseConnection();
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		return response;
	}
	/**
	 * post2璇锋眰
	 * 
	 * @param url
	 * @param json
	 * @return
	 */

	public static JSONObject doPost(String url, String json,String atoken)
	{
		HttpClient client = HttpClientBuilder.create().build();
		  HttpPost post = new HttpPost((url).trim().replaceAll(" ", "%20"));
		JSONObject response = null;
		try
		{
			StringEntity httpEntity = new StringEntity(json, "UTF-8");
            httpEntity.setChunked(true);
            post.setEntity(httpEntity);
            if (StringUtils.isNotEmpty(atoken))
                post.setHeader("atoken", URLDecoder.decode(atoken, "UTF-8"));
            post.addHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
			HttpResponse res = client.execute(post);
			if (res.getEntity()!=null)
			{
				String result = EntityUtils.toString(res.getEntity());// 
				response = JSONObject.fromObject(result);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		 finally
			{
				try
				{
					post.releaseConnection();
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		return response;
	}
	public static String kj(String str){
		JSONObject jo=new JSONObject();
	    jo.put("TerminalIds", str);
	    JSONObject s=YinPiaoHttp.doPost("http://36.110.5.131:9080/ips/queryHeartRateKJ.do?_t=json", jo,null);
	    return s.toString();
	}
	   public static void main(String[] args) throws Exception {
			JSONObject jo=new JSONObject();
		    jo.put("TerminalIds", "b3623000000346,b3660000000109,868568023375329,99000711604543,b3660000000108");
		    JSONObject s=YinPiaoHttp.doPost("http://36.110.5.131:9080/ips/queryHeartRateKJ.do?_t=json", jo,null);
		    System.out.println(s);
//	   
			//String	sr = YinPiaoHttp.doGet("https://api.yinpiao.com" + "/api/v1/vip/28682",null,"ZONdSDKGwf%2B7MiGkmxPakzccJTqVqiIResEACiEV0Gs5BCdmTrAxO1hn3Xi%2B5rTQ9TN0iMpasjyDwrfWlplWQiCsaeQsAczc");
//			String sr = YinPiaoHttp.doGet("http://sso.yinpiao.com/LogOut",
//					"ticket=BNBd%2B0hnv7hkZvyyexBCXv61AHwLVqbhB76zz%2FXjT0de%2FRzitmMN5kJTXP3f1qGAiMjZqiZaYEGIVfGlj3oQvOi0HeV%2FHJrC%2F1LSRnubYFQ%3D",null);
//			System.out.println(sr);   
				
//		    String	sr2 = YinPiaoHttp.doGet("https://api.yinpiao.com/api/v2/tradeCount/count",null,null);
//			System.out.println(sr2);  
		   
//		   JSONObject jo=new JSONObject();
//	       jo.put("userId", 48671);
//	       jo.put("activityName", "aoyuntouzhu");
//	       jo.put("step", "1");
//	       jo.put("times", "1");
//	       jo.put("awardType", "2");
//	       jo.put("amount", "100");
//	       JSONObject s=YinPiaoHttp.doPost(HyConfig.getLcDomain()+"/api/v1/activity/addActivityAchievements", jo,null);
	   }

}
