<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 摇一摇2 -->

<script src="/js/hudong/yaoyiyao2/shake.js"></script>
<link rel="stylesheet" href="/css/hudong/yaoyiyao2/yyy.css" />
<%@include file="/WEB-INF/card/background.jsp"%>
<div id="main" class="block" status="0" style="${blocks[0].hyct }" hydata="${blocks[0].rid}">
	<div id="hand" class="hand hand-animate"><img src="/images/hudong/yaoyiyao/hand.png" /></div>
	<div id="result" class=""></div>
</div>
<s:if test='method == "E"'>
	<div class="result2 block ${blocks[1].ctname}" status="0" style="${blocks[1].hyct }" hydata="${blocks[1].rid}">恭喜你中了60积分!</div>
</s:if>

<%@include file="/WEB-INF/card/yyyfile.jsp"%>
<%@include file="/WEB-INF/card/cardfile.jsp"%>