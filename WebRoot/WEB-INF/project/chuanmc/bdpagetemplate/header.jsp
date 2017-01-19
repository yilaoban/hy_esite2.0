<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html>
<html >
<head>
<meta charset="UTF-8">
<s:if test='type=="J"'>
	<title>积分商城</title>
	<meta name="description" content="积分商城" />  
	<meta name="keywords" content="会易 易店 积分商城" />
</s:if>
<s:else>
	<title>微商城</title>
	<meta name="description" content="微商城" />  
	<meta name="keywords" content="会易 易店 微商城" />
</s:else>
<meta id="myViewport" name="viewport" content="width=320, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta content="telephone=no,email=no" name="format-detection">
<link rel="stylesheet" href="/css/modelGenaral.css">
<link rel="stylesheet" href="/css/shop/shop.css">
<%@include file="/WEB-INF/pageshow/wx_fx.jsp"%>
<link href="https://res.wx.qq.com/open/libs/weui/0.4.0/weui.css" rel="stylesheet"/>
<link href="/js/jquery-weui/css/jquery-weui.css" rel="stylesheet"/>
<script src="/js/jquery-weui/js/jquery-weui.js" type="text/javascript"></script>
<script src="/js/jquery.cookie.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function(){
		var w_width = $(window).width();
		var w_height = $(window).height();
		var viewportScale = w_width/320;
		var w_height = w_height/viewportScale;
		$("#myViewport").attr("content","width=320, initial-scale="+viewportScale+", maximum-scale="+viewportScale+", user-scalable=no");
	});
	
 </script>
</head>
<body>
