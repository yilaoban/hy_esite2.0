<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<link rel="stylesheet" href="/css/shouye/12_n/index.css" type="text/css"></link>
<!-- 首页7 -->
<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>
<div class="mainmenu_20150204_1 block" hyblk="C" status="0" style="${blocks[0].hyct};" hydata="${blocks[0].rid}" class_data="hy2015050770198">
	<div class="mainmenu_20150204_1_content" class_data="hy2015050770645">
		<s:iterator status='st' value='blocks[0].list' var='item'> 
			<a href="${item.link}" target="_blank" hyvar="link">
				<p class="mainmenu_20150204_1_ico" class_data="hy2015050770437"><img src="${item.img}" hyvar="img" hydesc="236*236"/></p>
				<p class="mainmenu_20150204_1_title" hyvar="title" class_data="hy2015050770957">${item.title}</p>
			</a> 
		</s:iterator>
	</div>
</div>
</s:if>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
