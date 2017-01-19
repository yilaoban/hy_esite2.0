<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 图文动画18B -->
<link href="/css/tuwen/twdh18B/list.css" rel="stylesheet" type="text/css">

<%@include file="/WEB-INF/card/background.jsp"%>
<div class="tw_150309_page2" class_data="hy2015050790404"> 
	<s:if test='blocks[0].display=="Y"'>
	<div class="tw_150309_page2_img block ${blocks[0].ctname}" hyblk="S" status="0" hyct="Y" hydata="${blocks[0].rid}" style="${blocks[0].hyct};" class_data="hy2015050790127"><img src="${blocks[0].img}" hyvar="img" hydesc="512*758"></div>
	</s:if>
	<s:if test='blocks[1].display=="Y"'>
	<div class="tw_150309_page2_t block ${blocks[1].ctname}" hyblk="S" status="0" hyct="Y"  hyvar="title" hydata="${blocks[1].rid}" style="${blocks[1].hyct};" class_data="hy2015050790132">
		${blocks[1].title}
	</div>
	</s:if>
	<s:if test='blocks[3].display=="Y"'>
	<div class="tw_150309_page2_s block ${blocks[3].ctname}" hyblk="S" status="0" hyct="Y"  hyvar="desc" hydata="${blocks[3].rid}" style="${blocks[3].hyct};" class_data="hy2015050790188">
		${blocks[3].desc}
	</div>
	</s:if>
</div>

<%@include file="/WEB-INF/card/cardfile.jsp"%>