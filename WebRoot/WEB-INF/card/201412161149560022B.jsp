<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 图文动画12B -->
<link href="/css/tuwen/twdh12B/list.css" rel="stylesheet" type="text/css">

<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>
<div class="donghua_141216_telbtn01 block ${blocks[0].ctname}" hyblk="S" hyct="Y" status="0" style="${blocks[0].hyct}" hydata="${blocks[0].rid}" class_data="hy2015050747580">
	<img src="${blocks[0].img}" style="width:100%;height:100%;" hyvar="img" hydesc="700*170"> 
</div>
</s:if>

<%@include file="/WEB-INF/card/cardfile.jsp"%>