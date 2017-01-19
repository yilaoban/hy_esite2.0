<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 图文动画21 -->
<link href="/css/tuwen/donghua16/list.css" rel="stylesheet" type="text/css">

<%@include file="/WEB-INF/card/background.jsp"%>

<div class="tw_150310_page2_zt" class_data="hy2015050795392">
	<s:if test='blocks[0].display=="Y"'>
	<div class="tw_150310_page4_img block ${blocks[0].ctname}" status="0" hyblk="S" hyct="Y" hydata="${blocks[0].rid}" style="${blocks[0].hyct};background:rgba(${blocks[0].col},${blocks[0].tm});" class_data="hy2015050795434">
		<img src="${blocks[0].img}" hyvar="img" style="width:100%;height:100%;" hydesc="360*500">
	</div>
	</s:if>
	
	<s:if test='blocks[1].display=="Y"'>
	<div class="tw_150310_page4_h block ${blocks[1].ctname}" status="0" hyblk="S" hyct="Y" hydata="${blocks[1].rid}" style="${blocks[1].hyct};" class_data="hy2015050795501"><img src="${blocks[1].img}" hyvar="img" hydesc="640*612"></div>
	</s:if>
	
	<s:if test='blocks[2].display=="Y"'>
	<div class="tw_150310_page4_z block ${blocks[2].ctname}" status="0" hyblk="S" hyct="Y" hydata="${blocks[2].rid}" style="${blocks[2].hyct};" class_data="hy2015050795292"><img src="${blocks[2].img}" hyvar="img" hydesc="310*470"></div>
	</s:if>
</div>


<%@include file="/WEB-INF/card/cardfile.jsp"%>
