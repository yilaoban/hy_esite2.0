<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 图文7 -->
<link href="/css/tuwen/7/list.css" rel="stylesheet" type="text/css" />

<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>
<div hyblk="S" status="0" style="${blocks[0].hyct}" class="tw72015050718100 block" hydata="${blocks[0].rid}" class_data="hy2015050718100">
	<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,0,0" width="100%" height="100%" id="FLVPlayer" class_data="hy2015050718457">
		<param name="movie" value="FLVPlayer_Progressive.swf" />
		<param name="wmode" value="transparent">
		<param name="salign" value="lt" />
		<param name="quality" value="high" />
		<param name="scale" value="noscale" />
		<param name="FlashVars" value="&MM_ComponentVersion=1&skinName=Clear_Skin_1&streamName=/images/tuwen/7/cafe_townsend_home&autoPlay=false&autoRewind=false" />
		<embed hyvar="video" src="${blocks[0].video}" flashvars="&MM_ComponentVersion=1&skinName=Clear_Skin_1&streamName=/images/tuwen/7/cafe_townsend_home&autoPlay=false&autoRewind=false" quality="high" scale="noscale" width="100%" height="100%" name="FLVPlayer" salign="LT" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer"  wmode="transparent"/>  
	</object>
</div>
</s:if>
	
<s:if test='blocks[1].display=="Y"'>
<div class="tuwen1028_4 block" hyblk="S" status="0" style="${blocks[1].hyct}" hydata="${blocks[1].rid}" class_data="hy2015050718513">
	<div class="tuwen1028_4_wenzi" class_data="hy2015050718641">
		<header>
		<h3 hyvar="title" class_data="hy2015050718233">${blocks[1].title}</h3>
		<span hyvar="time" class_data="hy2015050718107">${blocks[1].time}</span> 
		</header>
		<span hyvar="desc" class_data="hy2015050718822">${blocks[1].desc}</span>
	</div>
</div>
</s:if>


<%@include file="/WEB-INF/card/cardfile.jsp"%>
