<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>

<!-- 卡券验证 -->

<%@include file="/WEB-INF/card/background.jsp"%>

<script type="text/javascript">
$(document).ready(function(){
	if("${method}"=="E"){
		return;
	}
	$.post("/${oname}/user/wkq_asub.action",function(data){
    	if(data != null){
    		window.location.href=data.url;
    	}else{
    		alert("data is null?");
    	}
    });
});
</script>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
