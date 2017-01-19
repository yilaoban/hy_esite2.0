package com.huiyee.esite.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class EmojiFilter
{

	/**
	* 检测是否有emoji字符
	* @param source
	* @return 一旦含有就抛出
	*/
	public static boolean containsEmoji(String source)
	{
		if (StringUtils.isBlank(source))
		{
			return false;
		}

		int len = source.length();

		for (int i = 0; i < len; i++)
		{
			char codePoint = source.charAt(i);

			if (isEmojiCharacter(codePoint))
			{
				return true;
			}
		}

		return false;
	}

	private static boolean isEmojiCharacter(char codePoint)
	{
		return ((codePoint >= 0xE001) && (codePoint <= 0xE05A)) || ((codePoint >= 0xE101) && (codePoint <= 0xE15A)) || ((codePoint >= 0xE201) && (codePoint <= 0xE253))
				|| ((codePoint >= 0xE301) && (codePoint <= 0xE34D)) || ((codePoint >= 0xE401) && (codePoint <= 0xE44C)) || ((codePoint >= 0xE501) && (codePoint <= 0xE537));
	}

	/**
	* 替换emoji成","
	* @param source
	* @return
	*/
	public static String filterEmoji(String source)
	{

		if (!containsEmoji(source))
		{
			return source;// 如果不包含，直接返回
		}
		// 到这里铁定包含
		StringBuilder buf = null;

		int len = source.length();

		for (int i = 0; i < len; i++)
		{
			char codePoint = source.charAt(i);

			if (isEmojiCharacter(codePoint))
			{
				buf.append(",");

			} else
			{
				if (buf == null)
				{
					buf = new StringBuilder(source.length());
				}

				buf.append(codePoint);

			}
		}
		return buf.toString();

	}

	public static void main(String[] args)
	{
		String abc = "花梦 ";
		System.out.println(filterEmoji(abc));
	}
	
	public static String getCutString(String abc)
	{
		if (abc == null)
		{
			return null;
		}
		else
		{
			StringBuilder sb = new StringBuilder();
			int len = abc.length();
			for (int i = 0; i < len; i++)
			{
				char c = abc.charAt(i);
				if (canUse(c))
				{
					sb.append(c);
				}
			}
			return sb.toString();
		}
	}

	private static boolean canUse(char c)
	{
		String str = c + "";
		boolean result = false;
		Pattern p1 = Pattern.compile("[a-zA-Z0-9\u4e00-\u9fa5 \\pP‘’“”]+");
		Matcher m1 = p1.matcher(str);
		if (m1.matches())
		{
			result = true;
		}
		return result;
	}
}
