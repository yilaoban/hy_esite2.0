<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 图文2 -->
<link href="/css/tuwen/8/list.css" rel="stylesheet" type="text/css" />

<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>
<div hyblk="S" status="0" style="${blocks[0].hyct}" class="tuwen1029_2 block" hydata="${blocks[0].rid}">
	<img src="${blocks[0].img}" style="width:100%;height:100%;" hyvar="img" hydesc="640*510"/>
</div>
</s:if>

<s:if test='blocks[1].display=="Y"'>
<div class="tuwen1029_1_wenzi block" hyblk="S" status="0" style="${blocks[1].hyct}" hydata="${blocks[1].rid}" class_data="hy2015050719195">
	<header>
		<h3 hyvar="title" class_data="hy2015050719641">${blocks[1].title}</h3>
		<div class="tuwen1029_1_riqi" hyvar="time" class_data="hy2015050719842">${blocks[1].time}</div> 
	</header>
	<div class="tuwen_z8" hyvar="desc" class_data="hy2015050719289">${blocks[1].desc}</div>
</div>
</s:if>

<%@include file="/WEB-INF/card/cardfile.jsp"%>
