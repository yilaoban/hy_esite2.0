<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 微现场首页 -->
<link href="/css/wxc.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	var timer1;
	function ping1(){
		timer1 = window.setInterval("found()",2000);
	}
	function found(){
		$.post("/${oname}/user/xcSwitchAction.action",{"xcid":$("#hy_entity").val()},function(data){
			if(data > 0 && data != ${pageid}){
				window.location.href="/${oname}/user/show/"+data+"/pcn.html";
			}
		});
	}  
	if('${method}' != "E"){
		ping1();
	}
	
	function adjust(obj){  
	   var w  = document.body.clientWidth;  
	   var h  = document.body.clientHeight;  
	   $("body").css("zoom",h/643*100+"%");
	}  
	window.onload=function(){  
	  window.onresize = adjust;  
	  adjust();  
	}  
	</script>
<s:if test='method=="E"'>
	<div style="position:absolute;top:0;left:0;right:0;bottom:0;background:${page.bgJson.background};background-size:100% 100%;"></div>
</s:if>	<s:if test='blocks[0].display=="Y"'>
	<div style="position:absolute;left:100px;top:10px;width:149px;height:70px;${blocks[0].hyct};" hyblk="S" class="block ${blocks[0].ctname}" status="0" hyct="Y" hydata="${blocks[0].rid}">
		<img src="${blocks[0].img}" style="width:100%;height:100%;" hyvar="img" hydesc="217*72"/>
	</div>
	</s:if>
	<s:if test='blocks[1].display=="Y"'>
	<div style="position:absolute;width:700px;text-align:center;left:290px;top:150px;${blocks[1].hyct};" hyblk="S" class="block ${blocks[1].ctname}" status="0" hyct="Y" hydata="${blocks[1].rid}">
		<img src="${blocks[1].img}" style="width:100%;height:100%;" hyvar="img" hydesc="320*568"/>
	</div>
	</s:if>

	<%@include file="/WEB-INF/card/cardfile.jsp"%>