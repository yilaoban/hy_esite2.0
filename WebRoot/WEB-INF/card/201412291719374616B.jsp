<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 图文动画5B -->
<link rel="stylesheet" href="/css/tuwen/twdh5B/list.css" type="text/css"></link>
<%@include file="/WEB-INF/card/background.jsp"%>

<div class="page_cartoon_141225_1" class_data="hy2015050765565">

	<s:if test='blocks[0].display=="Y"'>
	<div class="page_cartoon_141225_1_left block ${blocks[0].ctname}" hyblk="S" hyct="Y" status="0" style="${blocks[0].hyct}" hydata="${blocks[0].rid}" class_data="hy2015050765989"><span hyvar="title" class_data="hy2015050765715">${blocks[0].title}</span></div>
	</s:if>
	<s:if test='blocks[1].display=="Y"'>
	<div class="page_cartoon_141225_1_xian block ${blocks[1].ctname}" hyblk="S" hyct="Y" status="0" style="${blocks[1].hyct}" hydata="${blocks[1].rid}" class_data="hy2015050765916"><img src="/images/tuwen/donghua5/xian_141225.png"></div>
	</s:if>
	<s:if test='blocks[2].display=="Y"'>
	<div class="page_cartoon_141225_1_right block ${blocks[2].ctname}" hyblk="S" hyct="Y" status="0" style="${blocks[2].hyct}" hydata="${blocks[2].rid}" class_data="hy2015050765209"><span hyvar="desc" class_data="hy2015050765388">${blocks[2].desc}</span></div>
	</s:if>

</div>

<%@include file="/WEB-INF/card/cardfile.jsp"%>
