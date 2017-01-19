<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<meta name="viewport" content="width=320, initial-scale=1, maximum-scale=1, user-scalable=no">
<link href="https://res.wx.qq.com/open/libs/weui/0.4.0/weui.css" rel="stylesheet" />
<link href="/js/jquery-weui/css/jquery-weui.css" rel="stylesheet" />
<script src="/js/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="/js/jquery-weui/js/jquery-weui.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var kv = '${kv}';
		if (kv == '') {
			return;
		}
		window.location.href = "/${oname}/user/ownerPayBanlance.action?id=" + kv +"&type=H";
	});
</script>