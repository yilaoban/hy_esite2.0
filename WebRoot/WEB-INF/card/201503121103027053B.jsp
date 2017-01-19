<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 图文动画20B -->
<link href="/css/tuwen/twdh20B/list.css" rel="stylesheet" type="text/css">

<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>
<div class="tw_150310_page3_zt block ${blocks[0].ctname}" hyblk="S" status="0" hyct="Y" hydata="${blocks[0].rid}" style="${blocks[0].hyct};" class_data="hy2015050794988"><img hyvar="img" hydesc="640*1008" src="${blocks[0].img}"> </div>
</s:if>
<s:if test='blocks[1].display=="Y"'>
<div class="tw_150310_page3_zi block ${blocks[1].ctname}" hyblk="S" status="0" hyct="Y" hydata="${blocks[1].rid}" style="${blocks[1].hyct};" class_data="hy2015050794628"><span hyvar="desc" class_data="hy2015050794294">${blocks[1].desc}</span></div>
</s:if>


<%@include file="/WEB-INF/card/cardfile.jsp"%>
