
package com.huiyee.bdmap.utils;

import net.sf.json.JSONObject;

import com.huiyee.bdmap.dto.BDAddress;
import com.huiyee.esite.util.HttpTookit;

public class BaiDuMapUtil
{

	public static BDAddress address(String lat, String lng) throws Exception
	{

		String rs = HttpTookit.doGet("http://api.map.baidu.com/geocoder/v2/", "ak=" + BaiDuMapApp.ak + "&callback=renderReverse&location=" + lat + "," + lng
				+ "&output=json&pois=1", "utf-8", false);
		int h = rs.indexOf("{");
		int e = rs.lastIndexOf("}");
		if (h > 0 && e > 0)
		{
			rs = rs.substring(h, e + 1);
		}
		JSONObject obj = JSONObject.fromObject(rs);
		if (obj != null)
		{
			return new BDAddress(obj);
		}
		return null;
	}
}
