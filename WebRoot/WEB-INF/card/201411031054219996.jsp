<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 图文5 -->

<link href="/css/tuwen/5/list.css" rel="stylesheet" type="text/css" />
<link href="/css/tuwen/5/General.css" rel="stylesheet" type="text/css" />

<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>
	<header><div hyblk="S" class="block" hydata="${blocks[0].rid}" class_data="hy2015050716792">
<img src="${blocks[0].img}" hyvar="img" hydesc="640*510"/><span hyvar="title" class_data="hy2015050716245">${blocks[0].title}</span>
</div></header>
</s:if>
<s:if test='blocks[1].display=="Y"'>
	<div class="tuwen1028_2_wenzi block" hyblk="S" hydata="${blocks[1].rid}" class_data="hy2015050716343">
		<h2 class_data="hy2015050716282"><span  hyvar="title" class_data="hy2015050716203">${blocks[1].title}</span>
		<p hyvar="time" class_data="hy2015050716530">${blocks[1].time}</p>
		</h2>
		<div class="tuwen1028_2_wenzi_1" hyvar="desc" class_data="hy2015050716249">${blocks[1].desc}</div>
</div>
</s:if>
</div>


<%@include file="/WEB-INF/card/cardfile.jsp"%>
