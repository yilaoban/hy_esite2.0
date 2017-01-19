<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<input id='cardid' type='hidden' value='${pcid}' />
<!-- 预约添加店主页 -->
<%@include file="/WEB-INF/card/background.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		if("${method}"=="E"){
			return;
		}
		$.post("/${oname}/user/yuyueAddDZ.action", function(data) {
			if (data) {
				if (data.errcode == 0) {
					alert("成功");
				} else {
					alert(data.errmsg);
				}
			} else {
				alert("失败");
			}
		});
	});

	
</script>