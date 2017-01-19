<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 空白卡片2 -->

<%@include file="/WEB-INF/card/background.jsp"%>

<s:if test='blocks[0].display=="Y"'>
	<div class="block ${blocks[0].ctname}" hyblk="S" status="0" hyct="Y" style="${blocks[0].hyct}" hydata="${blocks[0].rid}"> 
		<a href="${blocks[0].link}" target="_blank" hyvar="link">
		
		
		
		</a> 
	</div>
</s:if>


<%@include file="/WEB-INF/card/cardfile.jsp"%>