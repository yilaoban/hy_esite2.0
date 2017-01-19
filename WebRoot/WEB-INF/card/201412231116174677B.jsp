<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 图文动画3B -->
<link href="/css/tuwen/twdh3B/list.css" rel="stylesheet" type="text/css">

<%@include file="/WEB-INF/card/background.jsp"%>

<s:if test='blocks[0].display=="Y"'>
<div class="page1_141223_top1 block ${blocks[0].ctname}" hyblk="S" hyct="Y" status="0" style="${blocks[0].hyct}" hydata="${blocks[0].rid}" class_data="hy2015050752506"><span hyvar="title" class_data="hy2015050752180">${blocks[0].title}</span></div>
</s:if>
<s:if test='blocks[1].display=="Y"'>
<div class="page1_141223_top2 block ${blocks[1].ctname}" hyblk="S" hyct="Y" status="0" style="${blocks[1].hyct}" hydata="${blocks[1].rid}" class_data="hy2015050752294"><span hyvar="desc" class_data="hy2015050752214">${blocks[1].desc}</span></div>
</s:if>


<%@include file="/WEB-INF/card/cardfile.jsp"%>
