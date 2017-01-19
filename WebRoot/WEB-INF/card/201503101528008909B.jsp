<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 图文动画15B -->
<link href="/css/tuwen/twdh15B/list.css" rel="stylesheet" type="text/css">


<%@include file="/WEB-INF/card/background.jsp"%>

<s:if test='blocks[0].display=="Y"'>
<div class="tw_150304_page1_img block ${blocks[0].ctname}" hyblk="S" hyct="Y" hydata="${blocks[0].rid}" status="0" style="${blocks[0].hyct};"><img src="${blocks[0].img}" style="width:100%; height:100%" hyvar="img" hydesc="150*150"></div>
</s:if>
<s:if test='blocks[1].display=="Y"'>
<div class="zxf_addspan1 block ${blocks[1].ctname}" hyblk="S" hyct="Y" hydata="${blocks[1].rid}" status="0" style="${blocks[1].hyct};"><span hyvar="title" class_data="hy2015050785180">${blocks[1].title}</span></div>
</s:if>
<s:if test='blocks[2].display=="Y"'>
<div class="zxf_addspan2 block ${blocks[2].ctname}" hyblk="S" hyct="Y" hydata="${blocks[2].rid}" status="0" style="${blocks[2].hyct};"><span hyvar="desc" class_data="hy2015050785909">${blocks[2].desc}</span></div>
</s:if>


<%@include file="/WEB-INF/card/cardfile.jsp"%>
