<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 首页11 -->
<link href="/css/shouye/13/list.css" rel="stylesheet" type="text/css" />
<%@include file="/WEB-INF/card/background.jsp"%>
<div id="shouye_1022_6" class_data="hy2015050723756">
<s:if test='blocks[0].display=="Y"'>
	<div class="telnav block" hyblk="C" hydata="${blocks[0].rid}" class_data="hy2015050723611">
	<s:iterator status='st' value='blocks[0].list' var='item'> 
		<a href="${item.link}" target="_blank" hyvar="link">
			<div class="telbtn" class_data="hy2015050723292">
				<div class="pic" style="border-top:6px;border-color:rgba(${item.col},${item.tm}); border-top-style:solid;" class_data="hy2015050723930"><img src="${item.img}" hyvar="img" hydesc="500*332"></div>
				<div class="t_content" style="background:rgba(${item.col},${item.tm});" class_data="hy2015050723576">
					<div class="t_t" hyvar="title" class_data="hy2015050723274">${item.title}</div>
				</div>
			</div>
		</a> 
	</s:iterator>
</div>
</s:if>
</div>


<%@include file="/WEB-INF/card/cardfile.jsp"%>
