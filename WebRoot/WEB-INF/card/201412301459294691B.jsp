<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 图文动画7B -->
<link rel="stylesheet" href="/css/tuwen/twdh7B/list.css" type="text/css"></link>
<%@include file="/WEB-INF/card/background.jsp"%>

<div class="page_cartoon_141225_3" class_data="hy2015050769661">

	<s:if test='blocks[0].display=="Y"'>
	<div class="page_cartoon_141225_3_desc block ${blocks[0].ctname}" hyblk="S" status="0" hyct="Y" style="${blocks[0].hyct};background:rgba(${blocks[0].col},${blocks[0].tm});" hydata="${blocks[0].rid}" class_data="hy2015050769700"><span hyvar="desc" class_data="hy2015050769840">${blocks[0].desc}</span></div>
	</s:if>
	<s:if test='blocks[1].display=="Y"'>
	<div class="page_cartoon_141225_3_title block ${blocks[1].ctname}" hyblk="S" status="0" hyct="Y" style="${blocks[1].hyct};background:rgba(${blocks[1].col},${blocks[1].tm});" hydata="${blocks[1].rid}" class_data="hy2015050769746"><span hyvar="title" class_data="hy2015050769930">${blocks[1].title}</span></div>
	</s:if>
</div>

<%@include file="/WEB-INF/card/cardfile.jsp"%>