<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 图文动画19B -->
<link href="/css/tuwen/twdh19B/list.css" rel="stylesheet" type="text/css">

<%@include file="/WEB-INF/card/background.jsp"%>

<s:if test='blocks[0].display=="Y"'>
<div class="tw_150309_page3_logo block ${blocks[0].ctname}" hyblk="S" status="0" style="${blocks[0].hyct};" hydata="${blocks[0].rid}" class_data="hy2015050792282"><img src="${blocks[0].img}" hyvar="img" hydesc="215*110"></div>
</s:if>
<s:if test='blocks[1].display=="Y"'>
<div class="tw_150309_page3_zt block ${blocks[1].ctname}" hyblk="S" status="0" style="${blocks[1].hyct};" hydata="${blocks[1].rid}" class_data="hy2015050792257"><img src="${blocks[1].img}" hyvar="img" hydesc="595*390"></div>
</s:if>
<%@include file="/WEB-INF/card/cardfile.jsp"%>