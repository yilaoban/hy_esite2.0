<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 图文动画19 -->
<link href="/css/tuwen/donghua14/list.css" rel="stylesheet" type="text/css">

<%@include file="/WEB-INF/card/background.jsp"%>

<s:if test='blocks[0].display=="Y"'>
<div class="tw_150309_page3_logo block ${blocks[0].ctname}" hyblk="S" hyct="Y" hydata="${blocks[0].rid}" style="${blocks[0].hyct};" class_data="hy2015050791763"><img src="${blocks[0].img}" hyvar="img" hydesc="215*110"></div>
</s:if>
<s:if test='blocks[1].display=="Y"'>
<div class="tw_150309_page3_zt block ${blocks[1].ctname}" hyblk="S" hyct="Y" hydata="${blocks[1].rid}" style="${blocks[1].hyct};" class_data="hy2015050791547"><img src="${blocks[1].img}" hyvar="img" hydesc="595*390"></div>
</s:if>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
