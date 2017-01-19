<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>

<script type="text/javascript">
		
function hdCallBack(data,successAlert){
	if(data.status!=1){
		$.alert(data.hydesc,"");
	}else if(successAlert=="Y"){
		$.alert(data.hydesc,"");
	}
	
	if(data.url!=null&&data.url!=""){
		window.location.href=data.url;
	}else if(data.id!=0){
		window.location.href="/${oname}/user/show/"+data.id+".html";
	}
}
</script>