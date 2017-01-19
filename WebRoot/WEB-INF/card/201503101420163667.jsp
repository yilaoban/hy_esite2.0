<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 图文动画13 -->
<link href="/css/tuwen/donghua8/list.css" rel="stylesheet" type="text/css">

<%@include file="/WEB-INF/card/background.jsp"%>

	<s:if test='blocks[0].display=="Y"'>
	<div hyblk="S" hyct="Y" class="tw_150303_page1_img block ${blocks[0].ctname}" status="0" style="${blocks[0].hyct}; background:rgba(${blocks[0].col},${blocks[0].tm})" hydata="${blocks[0].rid}" class_data="hy2015050781642"><img src="${blocks[0].img}" hyvar="img" hydesc="150*150"></div>
	</s:if>
	<s:if test='blocks[1].display=="Y"'>
	<div class="tw_150303_page1_title block ${blocks[1].ctname}" hyblk="S" hyct="Y" hydata="${blocks[1].rid}" status="0" style="${blocks[1].hyct}; color:#fdea02;" class_data="hy2015050781591"><span hyvar="title" class_data="hy2015050781121">${blocks[1].title}</span></div>
	</s:if>
	<s:if test='blocks[2].display=="Y"'>
	<div class="tw_150303_page1_xian block ${blocks[2].ctname}" hyblk="S" hyct="Y" hydata="${blocks[2].rid}" status="0" style="${blocks[2].hyct};" class_data="hy2015050781444"><div class="vline"></div></div>
	</s:if>
	<s:if test='blocks[3].display=="Y"'>
	<div class="tw_150303_page1_title2 block ${blocks[3].ctname}" hyblk="S" hyct="Y" hydata="${blocks[3].rid}" status="0" style="${blocks[3].hyct};color:#FFF;" class_data="hy2015050781843"><span hyvar="title" class_data="hy2015050781489">${blocks[3].title}</span></div>
	</s:if>
	<s:if test='blocks[4].display=="Y"'>
	<div class="tw_150303_page1_desc block ${blocks[4].ctname}" hyblk="S" hyct="Y" hydata="${blocks[4].rid}" status="0" style="${blocks[4].hyct}; color:#FFF;" class_data="hy2015050781727">
		<span hyvar="desc" class_data="hy2015050781291">${blocks[4].desc}</span>
	</div>
	</s:if>

<%@include file="/WEB-INF/card/cardfile.jsp"%>