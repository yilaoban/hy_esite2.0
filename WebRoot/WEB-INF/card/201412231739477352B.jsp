<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 图文动画8B -->
<link href="/css/tuwen/twdh8B/list.css" rel="stylesheet" type="text/css">

<%@include file="/WEB-INF/card/background.jsp"%>

<div class="page_cartoon_141223_2" class_data="hy2015050754665">
<s:if test='blocks[0].display=="Y"'>
	<div class="page_cartoon_141223_2_con block ${blocks[0].ctname}" hyblk="S" hyct="Y" status="0" style="${blocks[0].hyct};background:rgba(${blocks[0].col},${blocks[0].tm});" hydata="${blocks[0].rid}" class_data="hy2015050754622"><span hyvar="desc" class_data="hy2015050754794">${blocks[0].desc}</span></div>
</s:if>
</div>


<%@include file="/WEB-INF/card/cardfile.jsp"%>