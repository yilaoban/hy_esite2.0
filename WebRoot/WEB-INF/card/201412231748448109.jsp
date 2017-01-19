<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 图文动画10 -->
<link href="/css/tuwen/page4/list1.css" rel="stylesheet" type="text/css">
<%@include file="/WEB-INF/card/background.jsp"%>

<div class="page_cartoon_141226_4" class_data="hy2015050757541">

	<s:if test='blocks[0].display=="Y"'>
	<div class="page_cartoon_141226_4_title block ${blocks[0].ctname}" hyblk="S" hyct="Y" style="${blocks[0].hyct}" hydata="${blocks[0].rid}" class_data="hy2015050757403"><span hyvar="title" class_data="hy2015050757778">${blocks[0].title}</span></div>
	</s:if>
	<s:if test='blocks[1].display=="Y"'>
	<div class="page_cartoon_141226_4_con block ${blocks[1].ctname}" hyblk="S" hyct="Y" style="${blocks[1].hyct}" hydata="${blocks[1].rid}" class_data="hy2015050757399"><span hyvar="desc" class_data="hy2015050757320">${blocks[1].desc}</span></div>
	</s:if>
</div>


<%@include file="/WEB-INF/card/cardfile.jsp"%>
