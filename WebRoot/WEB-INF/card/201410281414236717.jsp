<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 图文4 -->
<link href="/css/tuwen/4/list.css" rel="stylesheet" type="text/css" />

<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>
<div class="tuwen1028_1 block" hyblk="S" status="0" style="${blocks[0].hyct}" hydata="${blocks[0].rid}" class_data="hy2015050715877">
	<span class="roundspan" class_data="hy2015050715352">
		<img src="${blocks[0].img}" style="width:100%;height:100%;" hyvar="img" hydesc="270*270"/>
	</span>
</div>
</s:if>

<s:if test='blocks[1].display=="Y"'>
<div class="tuwen1028_1_box block" hyblk="S" status="0" style="${blocks[1].hyct}" hydata="${blocks[1].rid}" class_data="hy2015050715172">
	<header>
		<h3 hyvar="title" class_data="hy2015050715685">${blocks[1].title}</h3>
		<p hyvar="time" class_data="hy2015050715111">${blocks[1].time}</p>
	</header>
	<div class="tuwen1028_1_line"></div>
	<div class="tuwen1028_1_wenzi" hyvar="desc" class_data="hy2015050715765">
		${blocks[1].desc}
	</div>
</div>
</s:if>


<%@include file="/WEB-INF/card/cardfile.jsp"%>
