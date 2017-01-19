<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 图文动画9B -->
<link href="/css/tuwen/twdh9B/list.css" rel="stylesheet" type="text/css">
<%@include file="/WEB-INF/card/background.jsp"%>

<div class="page_cartoon_141223_3" class_data="hy2015050756361">
	<s:if test='blocks[0].display=="Y"'>
	<div class="page_cartoon_141223_3_ttle block ${blocks[0].ctname}" hyblk="S" status="0" hyct="Y" style="${blocks[0].hyct}" hydata="${blocks[0].rid}" class_data="hy2015050756607"><span hyvar="title" class_data="hy2015050756550">${blocks[0].title}</span></div>
	</s:if>
	<s:if test='blocks[1].display=="Y"'>
	<div class="page_cartoon_141223_3_con block ${blocks[1].ctname}" hyblk="S" status="0" hyct="Y" style="${blocks[1].hyct}" hydata="${blocks[1].rid}" class_data="hy2015050756518"><span hyvar="desc" class_data="hy2015050756756">${blocks[1].desc}</span></div>
	</s:if>
</div>


<%@include file="/WEB-INF/card/cardfile.jsp"%>
