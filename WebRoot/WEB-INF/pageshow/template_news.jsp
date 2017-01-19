<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
		<title>${dto.contentNew.title}</title>
		<meta name="description" content="${dto.contentNew.shortdesc}" />  
		<meta name="keywords" content="${page.bgJson.keywords}" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">  
		<meta name="apple-mobile-web-app-status-bar-style" content="black">  
		<meta content="telephone=no,email=no" name="format-detection">
		<link href="/css/modelGenaral.css" rel="stylesheet" type="text/css" />
		<link href="/css/modelGlobal.css" rel="stylesheet" type="text/css" />
		<link href="/js/easyDialog/easydialog.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="/js/jquery-1.8.2.min.js"></script>
	</head>
	<body style="background:${page.bgJson.background}">
		<jsp:include page="/WEB-INF/card/${dto.tc.path}" flush="true"></jsp:include>	
	</body>
	<%@include file="/WEB-INF/pageshow/page_visit_time.jsp" %>
</html>
