<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 图文动画11B -->
<link href="/css/tuwen/twdh11B/list.css" rel="stylesheet" type="text/css">

<%@include file="/WEB-INF/card/background.jsp"%>

<s:if test='blocks[0].display=="Y"'>
	<div class="page_cartoon_141223_5_title block ${blocks[0].ctname}" hyblk="S" hyct="Y" status="0" style="${blocks[0].hyct}" hydata="${blocks[0].rid}" class_data="hy2015050760774"><span hyvar="title" class_data="hy2015050760369">${blocks[0].title}</span></div>
</s:if>
<s:if test='blocks[1].display=="Y"'>
	<div class="page_cartoon_141223_5_con block ${blocks[1].ctname}" hyblk="S" hyct="Y" status="0" style="${blocks[1].hyct};background:rgba(${blocks[1].col},${blocks[1].tm});" hydata="${blocks[1].rid}" class_data="hy2015050760996"><span hyvar="desc" class_data="hy2015050760592">${blocks[1].desc}</span></div>
</s:if>

<%@include file="/WEB-INF/card/cardfile.jsp"%>