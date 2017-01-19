<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 图文1 -->
<link href="/css/tuwen/1/list.css" rel="stylesheet" type="text/css" />

<%@include file="/WEB-INF/card/background.jsp"%><s:if test='blocks[0].display=="Y"'>
	<div class="wdy_top2 block" hyblk="S" status="0" style="position:absolute;top:0;width:320px;${blocks[0].hyct}" hydata="${blocks[0].rid}" class_data="hy201505070532">
		<img src="${blocks[0].img}" style="width: 100%;" hyvar="img" hydesc="640*280"/>
	</div>
</s:if>

<s:if test='blocks[1].display=="Y"'>
<div class="mainshowtop block" hyblk="S" status="0" style="position:absolute;width:300px;top:150px;left:10px;${blocks[1].hyct}" hydata="${blocks[1].rid}" class_data="hy201505070452">
    <a class="weimob-list-item1 add_qq_more" href="${blocks[1].link}" hyvar="link">
    <div class="weimob-list-item-line1" style="padding-left:20px;padding-right:20px;" class_data="hy201505070171">
      <div class="weimob-list-item-title m_text" hyvar="title" class_data="hy201505070829">${blocks[1].title}</div>
      <div class="weimob-list-item-fulltitle m_date" hyvar="time" class_data="hy201505070463">${blocks[1].time}</div>
      <div class="weimob-list-item-img" class_data="hy201505070207"><img src="${blocks[1].img}" class="m_pic" hyvar="img" hydesc="720*400"></div>
      <div class="weimob-list-item-summary m_txt" hyvar="desc" class_data="hy201505070391">${blocks[1].desc}</div>
    </div>
	<div style="clear:both"></div>
    </a>
</div>
</s:if>

<%@include file="/WEB-INF/card/cardfile.jsp"%>