<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 首页8 -->
<link rel="stylesheet" href="/css/shouye/14_n/index.css" type="text/css"></link>


<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>
<div class="mainmenu_20150205_1 block" style="${blocks[0].hyct};" status="0" hyblk="C" hydata="${blocks[0].rid}" class_data="hy2015050772576">
<ul class_data="hy2015050772568">
	<s:iterator status='st' value='blocks[0].list' var='item'>
	<li class_data="hy2015050772715">
		<div class="menubtn_20150205_1" style="background-color:rgba(${item.col},${item.tm});" class_data="hy2015050772230"> 
			<a href="${item.link}" target="_blank" hyvar="link">
				<div class_data="hy2015050772135"><img src="${item.img}" hyvar="img" hydesc="256*256"></div>
				<div class="menutitle_20150205_1" hyvar="title" class_data="hy2015050772410">${item.title}</div>
			</a> 
		</div>
	</li>
	</s:iterator>
</ul>
</div>
</s:if>
<%@include file="/WEB-INF/card/cardfile.jsp"%>