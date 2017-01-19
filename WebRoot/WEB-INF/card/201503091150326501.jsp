<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 首页18 -->
<link href="/css/shouye/18/index.css" rel="stylesheet" type="text/css" />

<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>
<div class="mainmenu_20150206_2 block" hyblk="C" hydata="${blocks[0].rid}" class_data="hy2015050776311">
<s:iterator status='st' value='blocks[0].list' var='item'>
	<a href="${item.link}" target="_blank" hyvar="link">
		<div class="mainmenu_20150206_2_btn" style="background-color: rgba(${item.col},${item.tm});" class_data="hy2015050776344">
			<p class_data="hy2015050776239"><img src="${item.img}" hyvar="img" hydesc="236*236"/></p>
			<p class="mainmenu_20150206_2_ttle" style="color: #fff;" hyvar="title" class_data="hy2015050776186">${item.title}</p>
		</div>
	</a>
</s:iterator>
</div>
</s:if>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
