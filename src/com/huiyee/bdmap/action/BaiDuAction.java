package com.huiyee.bdmap.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.bdmap.dto.BDAddress;
import com.huiyee.bdmap.utils.BaiDuMapApp;
import com.huiyee.bdmap.utils.BaiDuMapUtil;
import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.util.HttpTookit;

public class BaiDuAction extends AbstractAction
{

	/**
	 * 百度地图 通过关键字搜索地址列表.测试github2,111
	 * 百度地图 通过关键字搜索地址列表,,,ppp
	 */
	private static final long serialVersionUID = 3887688790629684079L;
	private String q = "";
	private int limit;

	public void setLimit(int limit)
	{
		this.limit = limit;
	}

	private String x;
	private String y;
	private String lc;
	private String tags;
	private String region = "";
	private String refurl = "";

	public String execute() throws Exception
	{
		return "success0";
	}

	public String asub() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		String rs = HttpTookit.doGet("http://api.map.baidu.com/geosearch/v3/nearby", "geotable_id=" + BaiDuMapApp.geotable_id + "&location=" + x + "," + y + "&radius=30000&ak="
				+ BaiDuMapApp.ak + "&q=" + tags + "&sortby=distance:1", "utf-8", false);
		JSONObject js = JSONObject.fromObject(rs);
		out.print(js);
		out.flush();
		out.close();
		return null;
	}

	public String asub2() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		String lt = "";
		if (limit > 0)
		{
			lt = "&page_size=" + limit;
		}
		String rs = HttpTookit.doGet("http://api.map.baidu.com/geosearch/v3/nearby", "geotable_id=" + BaiDuMapApp.geotable_id + "&location=" + x + "," + y + "&radius=30000&ak="
				+ BaiDuMapApp.ak + "&tags=" + tags + "&sortby=distance:1" + lt, "utf-8", false);
		JSONObject js = JSONObject.fromObject(rs);
		out.print(js);
		out.flush();
		out.close();
		return null;
	}

	public String ak() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();

		out.print(HttpTookit.doGet("http://api.map.baidu.com/place/search", "q=" + q + "&region=" + region + "&output=json&ak=" + BaiDuMapApp.ak, "utf-8", false));
		out.flush();
		out.close();
		return null;
	}

	public String dh() throws Exception
	{
		JSONObject js = JSONObject.fromObject(lc);
		double a = 0;
		double b = 0;
		try
		{
			JSONArray ja = js.getJSONArray("location");
			a = ja.getDouble(0);
			b = ja.getDouble(1);
		} catch (Exception e)
		{
			JSONObject jo = js.getJSONObject("location");
			a = jo.getDouble("lng");
			b = jo.getDouble("lat");
		}

		String rs = HttpTookit.doGet("http://mapi.map.qq.com/translate/", "type=3&points=" + x + "," + y + ";" + a + "," + b + "&output=jsonp", "utf-8", false);
		JSONObject js2 = JSONObject.fromObject(rs);
		JSONObject point1 = js2.getJSONObject("detail").getJSONArray("points").getJSONObject(0);
		JSONObject point2 = js2.getJSONObject("detail").getJSONArray("points").getJSONObject(1);
		// refurl="http://api.map.baidu.com/direction?origin=latlng:"+y+","+x+"|name:我的位置&destination=latlng:"+ja.getDouble(1)+","+ja.getDouble(0)+"|name:"+js.getString("tags")+"&mode=walking&region="+js.getString("province")+"&output=html&src=会易科技|测试&coord_type=bd09ll&vt=map";

		// refurl="http://map.qq.com/m/walk/routeplan/type=walk&sword=myLocation&spointx="+point1.get("lng")+"&spointy="+point1.get("lat")+"&eword=target&epointx="+point2.get("lng")+"&epointy="+point2.get("lat")+"&cond=0&noback=";
		refurl = "http://m.amap.com/?from=" + point1.get("lat") + "," + point1.get("lng") + "(from)&to=" + point2.get("lat") + "," + point2.get("lng") + "(to)";
		return SUCCESS;
	}

	public String address() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();

		BDAddress address = BaiDuMapUtil.address(x, y);
		out.print(new Gson().toJson(address));
		out.flush();
		out.close();
		return null;
	}

	public void setQ(String q)
	{
		this.q = q;
	}

	public String getQ()
	{
		return q;
	}

	public void setX(String x)
	{
		this.x = x;
	}

	public void setY(String y)
	{
		this.y = y;
	}

	public void setTags(String tags)
	{
		this.tags = tags;
	}

	public String getRegion()
	{
		return region;
	}

	public void setRegion(String region)
	{
		this.region = region;
	}

	public String getRefurl()
	{
		return refurl;
	}

	public void setLc(String lc)
	{
		this.lc = lc;
	}

	public String getX()
	{
		return x;
	}

}
