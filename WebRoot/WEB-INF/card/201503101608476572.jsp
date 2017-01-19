<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 图文动画17 -->
<link href="/css/tuwen/donghua12/list.css" rel="stylesheet" type="text/css">

<%@include file="/WEB-INF/card/background.jsp"%>
	<s:if test='blocks[0].display=="Y"'>
	<div class="tw_150306_page1_center block ${blocks[0].ctname}" status="0" hyblk="S" hyct="Y" hydata="${blocks[0].rid}" style="${blocks[0].hyct};" class_data="hy2015050788499"><img src="${blocks[0].img}" hyvar="img" hydesc="468*468"></div>
	</s:if>
	<s:if test='blocks[1].display=="Y"'>
	<div class="tw_150306_page1_title block ${blocks[1].ctname}" status="0" hyblk="S" hyct="Y" hydata="${blocks[1].rid}" style="${blocks[1].hyct};" class_data="hy2015050788784"><span hyvar="title" class_data="hy2015050788819">${blocks[1].title}</span></div>
	</s:if>
	<s:if test='blocks[2].display=="Y"'>
	<div class="tw_150306_page1_desc block ${blocks[2].ctname}" status="0" hyblk="S" hyct="Y" hydata="${blocks[2].rid}" style="${blocks[2].hyct};" class_data="hy2015050788977"><span hyvar="desc" class_data="hy2015050788467">${blocks[2].desc}</span></div>
	</s:if>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
