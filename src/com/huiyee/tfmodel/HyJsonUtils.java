package com.huiyee.tfmodel;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class HyJsonUtils
{
	public static String pasString(JSONObject json, String key)
	{
		try
		{
			String str = json.getString(key);
			return str;
		}
		catch (Exception e)
		{
		}

		return null;
	}
	
	public static int pasInt(JSONObject json, String key)
	{
		try
		{
			int str = json.getInt(key);
			return str;
		}
		catch (Exception e)
		{
		}

		return 0;
	}
	public static long pasLong(JSONObject json, String key)
	{
		try
		{
			long str = json.getLong(key);
			return str;
		}
		catch (Exception e)
		{
		}

		return 0;
	}
	
	public static JSONObject pasJsonobj(JSONObject json, String key)
	{
		try
		{
			JSONObject str = json.getJSONObject(key);
			return str;
		}
		catch (Exception e)
		{
		}
		return null;
	}
	public static boolean pasBoolean(JSONObject json, String key)
	{
		try
		{
			boolean str = json.getBoolean(key);
			return str;
		}
		catch (Exception e)
		{
		}
		return false;
	}
	public static double pasDouble(JSONObject json, String key)
	{
		try
		{
			double str = json.getDouble(key);
			return str;
		}
		catch (Exception e)
		{
		}
		return 0;
	}
	public static JSONArray pasArray(JSONObject json, String key)
	{
		try
		{
			JSONArray str = json.getJSONArray(key);
			return str;
		}
		catch (Exception e)
		{
		}
		return null;
	}
}
