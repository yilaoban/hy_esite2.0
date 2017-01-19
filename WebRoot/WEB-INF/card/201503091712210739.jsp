<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 首页21 -->
<link href="/css/shouye/21/index.css" rel="stylesheet" type="text/css" />

<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>
<div class="mainmenu_20150228_3 block" hyblk="C" hydata="${blocks[0].rid}" class_data="hy2015050779916">
<s:iterator status='st' value='blocks[0].list' var='item'>
	<ul class_data="hy2015050779910">
		<li style="background-color:rgba(${item.col},${item.tm});" class_data="hy2015050779140">
			<a href="${item.link}" target="_blank" hyvar="link">
				<b><img src="${item.img}" hyvar="img" hydesc="256*256"/></b>
				<span style="color:#505359;" hyvar="title" class_data="hy2015050779413">${item.title}</span>
			</a>
		</li> 
	</ul>
</s:iterator>
</div>
</s:if>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
