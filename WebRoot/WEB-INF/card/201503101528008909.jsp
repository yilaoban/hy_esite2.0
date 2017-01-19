<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 图文动画15 -->
<link href="/css/tuwen/donghua10/list.css" rel="stylesheet" type="text/css">


<%@include file="/WEB-INF/card/background.jsp"%>

<div class="tw_150304_page1" class_data="hy2015050784263">
<s:if test='blocks[0].display=="Y"'>
<div class="tw_150304_page1_img block ${blocks[0].ctname}" hyblk="S" hyct="Y" hydata="${blocks[0].rid}" style="${blocks[0].hyct};" class_data="hy2015050784511"><img src="${blocks[0].img}" hyvar="img" hydesc="150*150"></div>
</s:if>
<div class="tw_150304_page1_title" class_data="hy2015050784844">
	<s:if test='blocks[1].display=="Y"'>
	<div class="zxf_addspan1 block ${blocks[1].ctname}" hyblk="S" hyct="Y" hydata="${blocks[1].rid}" style="${blocks[1].hyct};" class_data="hy2015050784741"><span hyvar="title" class_data="hy2015050784803">${blocks[1].title}</span></div>
	</s:if>
	<s:if test='blocks[2].display=="Y"'>
	<div class="zxf_addspan2 block ${blocks[2].ctname}" hyblk="S" hyct="Y" hydata="${blocks[2].rid}" style="${blocks[2].hyct};" class_data="hy2015050784923"><span hyvar="desc" class_data="hy2015050784307">${blocks[2].desc}</span></div>
	</s:if>
</div>

</div>

<%@include file="/WEB-INF/card/cardfile.jsp"%>
