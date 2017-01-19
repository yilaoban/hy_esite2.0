<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 图文动画2B -->
<link href="/css/tuwen/twdh2B/list.css" rel="stylesheet" type="text/css">
<%@include file="/WEB-INF/card/background.jsp"%>
<div class="dh_141217_box_1" class_data="hy2015050749488">

	<s:if test='blocks[0].display=="Y"'>
	<div class="donghua_141217_telbtn01 block ${blocks[0].ctname}" hyblk="S" hyct="Y" status="0" style="${blocks[0].hyct};background:rgba(${blocks[0].col},${blocks[0].tm});" hydata="${blocks[0].rid}" class_data="hy2015050749148">
		<span hyvar="desc" class_data="hy2015050749346">${blocks[0].desc}</span>
	</div>
 	</s:if>

</div>


<%@include file="/WEB-INF/card/cardfile.jsp"%>
