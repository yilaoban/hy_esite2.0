<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 图文动画14B -->
<link href="/css/tuwen/twdh14B/list.css" rel="stylesheet" type="text/css">

<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>
	<div class="tw_150303_page2_left1 block ${blocks[0].ctname}" hyblk="S" hyct="Y" hydata="${blocks[0].rid}" status="0" style="${blocks[0].hyct};" class_data="hy2015050783618"><img src="${blocks[0].img}" style="width:100%;height:100%;" hyvar="img" hydesc="330*780"></div>
</s:if>
<s:if test='blocks[1].display=="Y"'>
	<div class="tw_150303_page2_left2 block ${blocks[1].ctname}" hyblk="S" hyct="Y" hydata="${blocks[1].rid}" status="0" style="${blocks[1].hyct};" class_data="hy2015050783303"><img src="${blocks[1].img}" style="width:100%;height:100%;" hyvar="img" hydesc="330*550"></div>   
</s:if>
<s:if test='blocks[2].display=="Y"'>
	<div class="tw_150303_page2_right1 block ${blocks[2].ctname}" hyblk="S" hyct="Y" hydata="${blocks[2].rid}" status="0" style="${blocks[2].hyct};" class_data="hy2015050783203"><img src="${blocks[2].img}" style="width:100%;height:100%;" hyvar="img" hydesc="500*474"></div>
</s:if>
<s:if test='blocks[3].display=="Y"'>
	<div class="tw_150303_page2_right3 block ${blocks[3].ctname}" hyblk="S" hyct="Y" hydata="${blocks[3].rid}" status="0" style="${blocks[3].hyct};" class_data="hy2015050783228"><img src="${blocks[3].img}" style="width:100%;height:100%;" hyvar="img" hydesc="300*99"></div>
</s:if>
<s:if test='blocks[4].display=="Y"'>
	<div class="tw_150303_page2_right2 block ${blocks[4].ctname}" hyblk="S" hyct="Y" hydata="${blocks[4].rid}" status="0" style="${blocks[4].hyct};" class_data="hy2015050783491"><img src="${blocks[4].img}" style="width:100%;height:100%;" hyvar="img" hydesc="340*320"></div>
</s:if>

<%@include file="/WEB-INF/card/cardfile.jsp"%>
