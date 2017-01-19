<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>E+ 数字营销平台</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="数字营销">
<meta http-equiv="description" content="会易科技">
</head>
<body>
	<%
		String rf = "/yizhan/user/show.action?pageid=2297";
		try
		{
			String ua = request.getHeader("user-agent").toLowerCase();
			if (request.getServerName().equals("huiyee.com") || request.getServerName().equals("www.huiyee.com"))
			{
				if (ua.contains("windows") || ua.contains("macintosh"))
				{
					rf = "/yizhan/user/show.action?pageid=2297";
				} else
				{
					rf = "/yizhan/user/show.action?pageid=2388";
				}
			} else if (request.getServerName().equals("m.huiyee.com"))
			{
				rf = "/yizhan/user/show.action?pageid=2388";
			}
			   else if (request.getServerName().equals("www.ucpchina.com"))
			{
				rf = "/hengtong/user/show.action?pageid=2084";
			}
                              else if (request.getServerName().contains("cpssc.org"))
			{
				rf = "/nn/user/show.action?pageid=29907";
			}
			 else if (request.getServerName().equals("www.mocaidan.com"))
			{
				rf = "/cadavisa/user/show.action?pageid=41416";
			}
			else if (request.getServerName().equals("m.langshanym.com"))
			{
				rf = "/langshan/user/show.action?pageid=30264";
			}
			RequestDispatcher rd = request.getRequestDispatcher(rf);
			rd.forward(request, response);

		} catch (Exception e)
		{
		}
	%>
</body>
</html>
