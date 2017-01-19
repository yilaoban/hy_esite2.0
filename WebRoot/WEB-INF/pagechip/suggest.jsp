<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>${title}</title>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">  
		<meta name="apple-mobile-web-app-status-bar-style" content="black"> 
		<style type="text/css">
			body {background:#FFE1D6;}
			div {margin:0 auto;width:60%;margin-top:40%;text-align:center;}
			p {font-size:22px;color:#333;}
			a {font-size:18px;margin-top:10px;text-decoration:none;display:inline-block;padding:10px 20px;background:#428BCA;border-radius:8px;color:#fff;}
		</style> 
	</head>
	<body>
	<%request.getSession().setAttribute("preview","Y");%>
		<div>
	 		<p>当前访问的链接为后台预览页面，发布之后此页面不再显示。</p>
	 		<a href="/${oname}/user/preview.action?pageid=${pageid}">继续浏览</a>
	 	</div>
	</body>
</html>
	
