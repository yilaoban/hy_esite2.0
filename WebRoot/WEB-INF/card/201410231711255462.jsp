<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>

<link href="/css/tuwen/2/list.css" rel="stylesheet" type="text/css">
<%@include file="/WEB-INF/card/background.jsp"%>
<div class="tuwen_1023_2" class_data="hy2015050710816">
<div class="tuwen_1023_2_content" class_data="hy2015050710397">
<s:if test='blocks[0].display=="Y"'>
	<div class="tuwen_1023_2_biaoti block"  hyblk="S" hydata="${blocks[0].rid}" class_data="hy2015050710145">
		<p hyvar="title" class_data="hy2015050710645">${blocks[0].title}</p>
		<span hyvar="time" class_data="hy2015050710483">${blocks[0].time}</span>
		<span class="${pcid}"></span>
</div>
</s:if>
<s:if test='blocks[1].display=="Y"'>
	<div class="c-content block" hyblk="S" hydata="${blocks[1].rid}" class_data="hy2015050710712">
		<center>
			<img src="${blocks[1].img}" style="max-width: 100%;height: auto;width: auto\9;border:none;" hyvar="img" hydesc="720*400">
		</center>
		<span hyvar="desc" class_data="hy2015050710353">${blocks[1].desc}</span>
</div>
</s:if>
	<div style="clear:both"></div>
</div>
<s:if test='blocks[2].display=="Y"'>
<div class="tuwen_1023_2_bottom block" hyblk="S" hydata="${blocks[2].rid}" class_data="hy2015050710240">
<span hyvar="footer" class_data="hy2015050710963">${blocks[2].footer}</span>
</div>
</s:if>
</div>

<%@include file="/WEB-INF/card/cardfile.jsp"%>
