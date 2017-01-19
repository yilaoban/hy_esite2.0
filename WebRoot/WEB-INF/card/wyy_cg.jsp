<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 预约成功 -->
<link href="/css/wyy/index.css" rel="stylesheet" type="text/css" />
<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>
<div class="yuyue_160229 block" status="0" hydata="${blocks[0].rid}" style="${blocks[0].hyct};">
	<img src="/images/wyy/cg_03.png"/>
	<div class="yuyue_160229_button">
		<a href="#" hyvar="link">
			确定
		</a>
	</div>
 </div>
 </s:if>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
