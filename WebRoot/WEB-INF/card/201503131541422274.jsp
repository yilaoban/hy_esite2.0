<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 首页23 -->
<link href="/css/shouye/23/index.css" rel="stylesheet" type="text/css" />

<%@include file="/WEB-INF/card/background.jsp"%>
<div class="index_box" class_data="hy2015050796330">
<s:if test='blocks[0].display=="Y"'>
  <div class="sy_logo block ${blocks[0].ctname}" status="0" hyct="Y" style="${blocks[0].hyct};" hyblk="S" hydata="${blocks[0].rid}" class_data="hy2015050796806"><img hyvar="img" hydesc="480*83" src="${blocks[0].img}"/></div>
</s:if>
  <div class="sy_menu" class_data="hy2015050796808">
  <s:if test='blocks[1].display=="Y"'>
	<div hyblk="C" class="block clearfix ${blocks[1].ctname}" status="0" hyct="Y" style="${blocks[1].hyct};" hydata="${blocks[1].rid}" class_data="hy2015050796265">
		<s:iterator status='st' value='blocks[1].list' var='item'>
		<div class="sy_menu${st.count }" class_data="hy2015050796420">
			<a href="${item.link}" target="_blank" hyvar="link"><img hyvar="img" hydesc="175*201" src="${item.img}"/></a>
		</div>
		</s:iterator>
	</div>
	</s:if>
	<s:if test='blocks[2].display=="Y"'>
	<div hyblk="C" class="block clearfix ${blocks[2].ctname}" status="0" hyct="Y" style="${blocks[2].hyct};" hydata="${blocks[2].rid}">
	<s:iterator status='st' value='blocks[2].list' var='item'>
		  <div class="sy_k${st.count }" class_data="hy2015050796555"><img hyvar="img" hydesc="114*132" src="${item.img}"/></div>
	</s:iterator>
	</div>
	</s:if>
  </div>
</div>

<%@include file="/WEB-INF/card/cardfile.jsp"%>
