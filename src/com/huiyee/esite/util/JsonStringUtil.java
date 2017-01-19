package com.huiyee.esite.util;


import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JsonStringUtil
{
	private static Log logger = LogFactory.getLog(JsonStringUtil.class);

	public static Object String2Obj(String str, Class pojoCalss)
	{
		if (str != null)
		{
			try
			{
				JSONObject jo = JSONObject.fromObject(str);
				return JSONObject.toBean(jo, pojoCalss);
			}
			catch (RuntimeException e)
			{
				logger.warn(str + "×ª»»¶ÔÏóÊ§°Ü");
			}
		}
		return null;
	}
	
	public static List String2List(String str, Class pojoCalss)
	{
		if (str != null)
		{
			try
			{
				JSONArray jarr = JSONArray.fromObject(str);
				return (List)jarr.toCollection(jarr,pojoCalss);
			}
			catch (RuntimeException e)
			{
				logger.warn(str + "×ª»»listÊ§°Ü");
			}
		}
		return null;
	}

	public static String Obj2String(Object obj)
	{
		if (obj != null)
		{
			try
			{
				if (obj instanceof java.util.List)
				{
					JSONArray ja=JSONArray.fromObject(obj);
					return ja.toString();
				}
				else
				{
					JSONObject jo = JSONObject.fromObject(obj);
					return jo.toString();
				}
			}
			catch (RuntimeException e)
			{
				logger.warn(obj + "×ª»»×Ö·û´®Ê§°Ü");
			}
		}
		return null;
	}
}
