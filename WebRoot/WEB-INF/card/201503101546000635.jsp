<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 图文动画16 -->
<link href="/css/tuwen/donghua11/list.css" rel="stylesheet" type="text/css">

<%@include file="/WEB-INF/card/background.jsp"%>
<div class="tw_150305_page1" class_data="hy2015050786373"> 
	<s:if test='blocks[0].display=="Y"'>
	<div class="zxf_addspan1 block ${blocks[0].ctname}" hyblk="S" hyct="Y" hydata="${blocks[0].rid}" style="${blocks[0].hyct};" class_data="hy2015050786861"><span hyvar="title" class_data="hy2015050786281">${blocks[0].title}</span></div>
	</s:if>
	<s:if test='blocks[1].display=="Y"'>
	<div class="tw_150305_img_z block ${blocks[1].ctname}" hyblk="S" hyct="Y" hydata="${blocks[1].rid}" style="${blocks[1].hyct};" class_data="hy2015050786539"><img src="${blocks[1].img}" hyvar="img" hydesc="300*200"></div>
	</s:if>
	<s:if test='blocks[2].display=="Y"'>
	<div class="zxf_addspan2 block ${blocks[2].ctname}" hyblk="S" hyct="Y" hydata="${blocks[2].rid}" style="${blocks[2].hyct};" class_data="hy2015050786191">
		<span hyvar="desc" class_data="hy2015050786162">${blocks[2].desc}</span>
	</div>
	</s:if>
</div>

<%@include file="/WEB-INF/card/cardfile.jsp"%>
