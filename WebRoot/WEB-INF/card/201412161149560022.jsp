<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 图文动画12 -->
<link href="/css/tuwen/kafei/list.css" rel="stylesheet" type="text/css">
<%@include file="/WEB-INF/card/background.jsp"%>
<div class="dh_141216_box_1" class_data="hy2015050746402">

	<s:if test='blocks[0].display=="Y"'>
	<div class="donghua_141216_telbtn01 block ${blocks[0].ctname}" hyblk="S" hyct="Y" style="${blocks[0].hyct}" hydata="${blocks[0].rid}" class_data="hy2015050746953">
		<img src="${blocks[0].img}" hyvar="img" hydesc="700*170"> 
	</div>
	</s:if>

</div>


<%@include file="/WEB-INF/card/cardfile.jsp"%>
