<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 图文动画4 -->
<link rel="stylesheet" href="/css/tuwen/donghua4/list.css" type="text/css"></link>
<%@include file="/WEB-INF/card/background.jsp"%>

<div class="page_cartoon_141224_1" class_data="hy2015050762881">

	<s:if test='blocks[0].display=="Y"'>
	<div class="page_cartoon_141224_1_tu block ${blocks[0].ctname}" hyblk="S" hyct="Y" style="${blocks[0].hyct}" hydata="${blocks[0].rid}" class_data="hy2015050762724"><img src="${blocks[0].img}" hyvar="img" hydesc="640*430"></div>
	</s:if>
	<div class="page_cattoon_141224_1_box" class_data="hy2015050762584">
	<s:if test='blocks[1].display=="Y"'>
	<div class="page_cattoon_141224_1_box1 block ${blocks[1].ctname}" hyblk="S" hyct="Y" style="${blocks[1].hyct}" hydata="${blocks[1].rid}" class_data="hy2015050762463"><img src="/images/tuwen/donghua4/page6_dh.png"></div>
	</s:if>
	<s:if test='blocks[2].display=="Y"'>
	<div class="page_cattoon_141224_1_box2 block ${blocks[2].ctname}" hyblk="S" hyct="Y" style="${blocks[2].hyct}" hydata="${blocks[2].rid}" class_data="hy2015050762542"><img src="/images/tuwen/donghua4/page6_xian.png"></div>
	</s:if>
	<s:if test='blocks[3].display=="Y"'>
	<div class="page_cattoon_141224_1_box3 block ${blocks[3].ctname}" hyblk="S" hyct="Y" style="${blocks[3].hyct}" hydata="${blocks[3].rid}" class_data="hy2015050762148"><span hyvar="title" class_data="hy2015050762326">${blocks[3].title}</span></div>
	</s:if>
	</div>
	<s:if test='blocks[4].display=="Y"'>
	<div class="page_cattoon_141224_1_desc block ${blocks[4].ctname}" hyblk="S" hyct="Y" style="${blocks[4].hyct}" hydata="${blocks[4].rid}" class_data="hy2015050762582">
		<span hyvar="desc" class_data="hy2015050762356">${blocks[4].desc}</span>
	</div>
	</s:if>
</div>

<%@include file="/WEB-INF/card/cardfile.jsp"%>
