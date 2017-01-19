<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 图文动画6B -->
<link rel="stylesheet" href="/css/tuwen/twdh6B/list.css" type="text/css"></link>
<%@include file="/WEB-INF/card/background.jsp"%>

<div class="page_cartoon_141225_2" class_data="hy2015050767610">

	<s:if test='blocks[0].display=="Y"'>
	<div class="page_cartoon_141225_2_title block ${blocks[0].ctname}" hyblk="S" hyct="Y" status="0" style="${blocks[0].hyct}" hydata="${blocks[0].rid}" class_data="hy2015050767661"><span hyvar="title" class_data="hy2015050767602">${blocks[0].title}</span></div>
	</s:if>
	<s:if test='blocks[1].display=="Y"'>
	<div class="page_cartoon_141225_2_box_jie block ${blocks[1].ctname}" hyblk="S" hyct="Y" status="0" style="${blocks[1].hyct};background:rgba(${blocks[1].col},${blocks[1].tm});" hydata="${blocks[1].rid}" class_data="hy2015050767130"><span hyvar="name" class_data="hy2015050767512">${blocks[1].name}</span></div>
	</s:if>
	<s:if test='blocks[2].display=="Y"'>
	<div class="page_cartoon_141225_2_box_dao block ${blocks[2].ctname}" hyblk="S" hyct="Y" status="0" style="${blocks[2].hyct};background:rgba(${blocks[2].col},${blocks[2].tm});" hydata="${blocks[2].rid}" class_data="hy2015050767793"><span hyvar="name" class_data="hy2015050767439">${blocks[2].name}</span></div>
	</s:if>
	<s:if test='blocks[3].display=="Y"'>
	<div class="page_cartoon_141225_2_box_zuobiao block ${blocks[3].ctname}" hyblk="S" hyct="Y" status="0" style="${blocks[3].hyct}" hydata="${blocks[3].rid}" class_data="hy2015050767341"><img src="${blocks[3].img}" hyvar="img" hydesc="140*140"></div>
	</s:if>
	<s:if test='blocks[4].display=="Y"'>
	<div class="page_cartoon_141225_2_box_dz block ${blocks[4].ctname}" hyblk="S" hyct="Y" status="0" style="${blocks[4].hyct};background:rgba(${blocks[4].col},${blocks[4].tm});" hydata="${blocks[4].rid}" class_data="hy2015050767427"><span hyvar="desc" class_data="hy2015050767473">${blocks[4].desc}</span></div>
	</s:if>
	<s:if test='blocks[5].display=="Y"'>
	<div class="page_cartoon_141225_2_time block ${blocks[5].ctname}" hyblk="S" hyct="Y" status="0" style="${blocks[5].hyct}" hydata="${blocks[5].rid}" class_data="hy2015050767196">
		<span hyvar="time" class_data="hy2015050767398">${blocks[5].time}</span>
	</div>
	</s:if>
</div>

<%@include file="/WEB-INF/card/cardfile.jsp"%>