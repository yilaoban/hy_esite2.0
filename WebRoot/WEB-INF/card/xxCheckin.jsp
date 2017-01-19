<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 线下签到 -->

<%@include file="/WEB-INF/card/background.jsp"%>


<script type="text/javascript">
$(function(){
	if("${method}"=="E"){
		return;
	}
	$.post("/${oname}/user/checking.action",function(data){
		console.log(data);
		if(data.url){
			window.location.href = data.url;
		}else if(data.pageid >0){
			window.location.href="/${oname}/user/show/"+data.pageid+"/pcn.html";
		}else{
			alert(data.hydesc);
		}
	});
	
});

</script>

<%@include file="/WEB-INF/card/cardfile.jsp"%>
