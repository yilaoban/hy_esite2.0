<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 图文动画11 -->
<link href="/css/tuwen/page5/list.css" rel="stylesheet" type="text/css">
<%@include file="/WEB-INF/card/background.jsp"%>

<div class="page_cartoon_141223_5" class_data="hy2015050759223">

	
	<s:if test='blocks[0].display=="Y"'>
	<div class="page_cartoon_141223_5_title block ${blocks[0].ctname}" hyblk="S" hyct="Y" style="${blocks[0].hyct}" hydata="${blocks[0].rid}" class_data="hy2015050759141"><span hyvar="title" class_data="hy2015050759375">${blocks[0].title}</span></div>
	</s:if>
	<s:if test='blocks[1].display=="Y"'>
	<div class="page_cartoon_141223_5_con block ${blocks[1].ctname}" hyblk="S" hyct="Y" style="${blocks[1].hyct};background:rgba(${blocks[1].col},${blocks[1].tm});" hydata="${blocks[1].rid}" class_data="hy2015050759348"><span hyvar="desc" class_data="hy2015050759862">${blocks[1].desc}</span></div>
	</s:if>

</div>


<%@include file="/WEB-INF/card/cardfile.jsp"%>
