<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 图文动画16B -->
<link href="/css/tuwen/twdh16B/list.css" rel="stylesheet" type="text/css">

<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>
<div class="zxf_addspan1 block ${blocks[0].ctname}" hyblk="S" hyct="Y" hydata="${blocks[0].rid}" status="0" style="${blocks[0].hyct};" class_data="hy2015050787670"><span hyvar="title" class_data="hy2015050787507">${blocks[0].title}</span></div>
</s:if>
<s:if test='blocks[1].display=="Y"'>
<div class="tw_150305_img_z block ${blocks[1].ctname}" hyblk="S" hyct="Y" hydata="${blocks[1].rid}" status="0" style="${blocks[1].hyct};" class_data="hy2015050787540"><img src="${blocks[1].img}" style="width: 100%;height:100%" hyvar="img" hydesc="300*200"></div>
</s:if>
<s:if test='blocks[2].display=="Y"'>
<div class="zxf_addspan2 block ${blocks[2].ctname}" hyblk="S" hyct="Y" hydata="${blocks[2].rid}" status="0" style="${blocks[2].hyct};" class_data="hy2015050787854">
	<span hyvar="desc" class_data="hy2015050787152">${blocks[2].desc}</span>
</div>
</s:if>

<%@include file="/WEB-INF/card/cardfile.jsp"%>
