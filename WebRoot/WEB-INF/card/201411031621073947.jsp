<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 图文6 -->

<link href="/css/tuwen/6/list.css" rel="stylesheet" type="text/css" />
<link href="/css/tuwen/6/General.css" rel="stylesheet" type="text/css" />
<%@include file="/WEB-INF/card/background.jsp"%>
<div class="tuwen1028_3" class_data="hy2015050717703">
<s:if test='blocks[0].display=="Y"'>
  <div hyblk="S" class="block" hydata="${blocks[0].rid}" class_data="hy2015050717489">
<img src="${blocks[0].img}" hyvar="img" hydesc="640*510"/>
</div>
</s:if>
<s:if test='blocks[1].display=="Y"'>
  <div class="tuwen1028_3_wenzi block" hyblk="S" hydata="${blocks[1].rid}" class_data="hy2015050717792">
    <header>
      <h3 hyvar="title" class_data="hy2015050717902">${blocks[1].title}</h3>
      <span hyvar="time" class_data="hy2015050717408">${blocks[1].time}</span> 
	 </header>
    <span hyvar="desc" class_data="hy2015050717818">${blocks[1].desc}</span>
</div>
</s:if>
</div>


<%@include file="/WEB-INF/card/cardfile.jsp"%>
