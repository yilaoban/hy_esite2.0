<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 图文动画1 -->
<link href="/css/tuwen/9/list.css" rel="stylesheet" type="text/css" />

<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>
<div class="tuwendh_141118_box_1_telnav block ${blocks[0].ctname}" hyblk="S" hyct="Y" style="${blocks[0].hyct}" hydata="${blocks[0].rid}" class_data="hy2015050737338"> 

	<a href="${blocks[0].link}" target="_blank" hyvar="link">
		<div class="tuwendh_141118_box_1_telbtn01" style="background:rgba(${blocks[0].col},${blocks[0].tm});" class_data="hy2015050737229">
			<p hyvar="title" class_data="hy2015050737456">${blocks[0].title}</p>
			<span class="zxf_span2" hyvar="desc" class_data="hy2015050737859">${blocks[0].desc}</span>
		</div>
	</a> 
</div>
</s:if>


<%@include file="/WEB-INF/card/cardfile.jsp"%>