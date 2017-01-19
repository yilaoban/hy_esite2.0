<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>

<%@include file="/WEB-INF/card/background.jsp"%>

<s:if test='blocks[0].display=="Y"'>
	<div class="block ${blocks[0].ctname}" hyblk="S" status="0" style="${blocks[0].hyct};" hydata="${blocks[0].rid}" class_data="hy20150507110462">
		<div hyvar="content" class_data="hy20150507110389">${blocks[0].content}</div>
	</div>
</s:if>

<%@include file="/WEB-INF/card/cardfile.jsp"%>
