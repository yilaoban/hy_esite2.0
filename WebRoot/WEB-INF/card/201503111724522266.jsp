<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 图文动画18 -->
<link href="/css/tuwen/donghua13/list.css" rel="stylesheet" type="text/css">

<%@include file="/WEB-INF/card/background.jsp"%>
<div class="tw_150309_page2" class_data="hy2015050789255"> 
	<s:if test='blocks[0].display=="Y"'>
	<div class="tw_150309_page2_img block ${blocks[0].ctname}" hyblk="S" hyct="Y" hydata="${blocks[0].rid}" style="${blocks[0].hyct};" class_data="hy2015050789696"><img src="${blocks[0].img}" hyvar="img" hydesc="512*758"></div>
	</s:if>
	<s:if test='blocks[1].display=="Y"'>
	<div class="tw_150309_page2_t block ${blocks[1].ctname}" hyblk="S" hyct="Y" hydata="${blocks[1].rid}" style="${blocks[1].hyct};" class_data="hy2015050789375">
		<span hyvar="title" class_data="hy2015050789804">${blocks[1].title}</span>
	</div>
	</s:if>
	<s:if test='blocks[2].display=="Y"'>
	<div class="tw_150309_page2_x block ${blocks[2].ctname}" hyblk="S" hyct="Y" hydata="${blocks[2].rid}" style="${blocks[2].hyct};" class_data="hy2015050789836"><img src="${blocks[2].img}" hyvar="img" hydesc="120*726"></div>
	</s:if>
	<s:if test='blocks[3].display=="Y"'>
	<div class="tw_150309_page2_s block ${blocks[3].ctname}" hyblk="S" hyct="Y" hydata="${blocks[3].rid}" style="${blocks[3].hyct};" class_data="hy2015050789658">
		<span hyvar="desc" class_data="hy2015050789947">${blocks[3].desc}</span>
	</div>
	</s:if>
</div>

<%@include file="/WEB-INF/card/cardfile.jsp"%>
