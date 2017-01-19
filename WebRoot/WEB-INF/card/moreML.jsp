<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<link rel="stylesheet" href="/css/hudong/moreML/list.css" type="text/css"></link>

<!-- 多目录 -->

<%@include file="/WEB-INF/card/background.jsp"%>

<div class="more block status" status="0" hyct="Y" style="${blocks[0].hyct}"  hydata="${blocks[0].rid}">
	<s:if test='blocks[0].list.size > 0'>
	<ul>
		<s:iterator value="blocks[0].list" var="c">
			<li>${c.name}${blocks[0].pageid }</li>
		</s:iterator>
	</ul>
	</s:if>
	<s:else>
		<ul>
			<li>目录1</li>
			<li>目录2</li>
			<li>目录3</li>
			<li>目录4</li>
			<li>目录5</li>
		</ul>
	</s:else>
</div>

<%@include file="/WEB-INF/card/cardfile.jsp"%>
