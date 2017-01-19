<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 图文动画14 -->
<link href="/css/tuwen/donghua9/list.css" rel="stylesheet" type="text/css">

<%@include file="/WEB-INF/card/background.jsp"%>
<div class="tw_150303_page2" class_data="hy2015050782744">
	<div class="tw_150303_page2_left" class_data="hy2015050782974">
		<s:if test='blocks[0].display=="Y"'>
		<div class="tw_150303_page2_left1 block ${blocks[0].ctname}" hyblk="S" hyct="Y" hydata="${blocks[0].rid}" style="${blocks[0].hyct};" class_data="hy2015050782275"><img src="${blocks[0].img}" hyvar="img" hydesc="330*780"></div>
		</s:if>
		<s:if test='blocks[1].display=="Y"'>
		<div class="tw_150303_page2_left2 block ${blocks[1].ctname}" hyblk="S" hyct="Y" hydata="${blocks[1].rid}" style="${blocks[1].hyct};" class_data="hy2015050782169"><img src="${blocks[1].img}" hyvar="img" hydesc="330*550"></div>   
		</s:if>
	</div>
	<div class="tw_150303_page2_right" class_data="hy2015050782520">
		<s:if test='blocks[2].display=="Y"'>
	   <div class="tw_150303_page2_right1 block ${blocks[2].ctname}" hyblk="S" hyct="Y" hydata="${blocks[2].rid}" style="${blocks[2].hyct};" class_data="hy2015050782529"><img src="${blocks[2].img}" hyvar="img" hydesc="500*474"></div>
	   </s:if>
	   <s:if test='blocks[3].display=="Y"'>
	   <div class="tw_150303_page2_right3 block ${blocks[3].ctname}" hyblk="S" hyct="Y" hydata="${blocks[3].rid}" style="${blocks[3].hyct};" class_data="hy2015050782679"><img src="${blocks[3].img}" hyvar="img" hydesc="300*99"></div>
	   </s:if>
	   <s:if test='blocks[4].display=="Y"'>
	   <div class="tw_150303_page2_right2 block ${blocks[4].ctname}" hyblk="S" hyct="Y" hydata="${blocks[4].rid}" style="${blocks[4].hyct};" class_data="hy2015050782302"><img src="${blocks[4].img}" hyvar="img" hydesc="340*320"></div>
		</s:if>
	</div>
</div>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
