<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 获得积分 -->
<link rel="stylesheet" type="text/css" href="/css/shouye/jf/index.css" />
<%@include file="/WEB-INF/card/background.jsp"%>
<div class="jf_text">获得积分</div>
<div class="jf_value"></div>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		var balance = $("#hy_kv").val();
		if (balance) {
			$(".jf_value").html(balance);
		}
	});

	
</script>