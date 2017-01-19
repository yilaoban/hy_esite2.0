<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 图文动画10B -->
<link href="/css/tuwen/twdh10B/list1.css" rel="stylesheet" type="text/css">

<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>
<div class="page_cartoon_141226_4_title block ${blocks[0].ctname}" hyblk="S" hyct="Y" status="0" style="${blocks[0].hyct}" hydata="${blocks[0].rid}" class_data="hy2015050758225"><span hyvar="title" class_data="hy2015050758225">${blocks[0].title}</span></div>
</s:if>
<s:if test='blocks[1].display=="Y"'>
<div class="page_cartoon_141226_4_con block ${blocks[1].ctname}" hyblk="S" hyct="Y" status="0" style="${blocks[1].hyct}" hydata="${blocks[1].rid}" class_data="hy2015050758959"><span hyvar="desc" class_data="hy2015050758263">${blocks[1].desc}</span></div>
</s:if>


<%@include file="/WEB-INF/card/cardfile.jsp"%>
