<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 首页1 -->
<link href="/css/shouye/16/index.css" rel="stylesheet" type="text/css" />

<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>
<div class="mainmenu_20150206_1 block" status="0" style="${blocks[0].hyct};" hyblk="C" hydata="${blocks[0].rid}" class_data="hy2015050774250">
<s:iterator status='st' value='blocks[0].list' var='item'>

	<a href="${item.link}" target="_blank" hyvar="link">
		<div class="mainmenu_20150206_1_btn" style="background-color: rgba(${item.col},${item.tm});" class_data="hy2015050774198">
			<p class_data="hy2015050774179"><img src="${item.img}" hyvar="img" hydesc="236*236"/></p>
			<p class="mainmenu_20150206_1_ttle" style="color: #fff;" hyvar="title" class_data="hy2015050774602">${item.title}</p>
		</div>
	</a>

</s:iterator>
</div>
</s:if>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
