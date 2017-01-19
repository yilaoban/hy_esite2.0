<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<meta name="viewport" content="width=320, initial-scale=1, maximum-scale=1, user-scalable=no">
<link href="https://res.wx.qq.com/open/libs/weui/0.4.0/weui.css" rel="stylesheet" />
<link href="/js/jquery-weui/css/jquery-weui.css" rel="stylesheet" />
<%@include file="/WEB-INF/pageshow/wx_fx.jsp"%>
<script src="/js/jquery-weui/js/jquery-weui.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		setInterval("closeWindow()", 1000);
	});

	function closeWindow() {
		wx.closeWindow();
	}

	
</script>