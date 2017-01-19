<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 大转盘4 -->
<script type="text/javascript" src="/js/hudong/dazhuanpan1/awardRotate.js"></script>
<link rel="stylesheet" href="/css/hudong/dazhuanpan1/demo.css" type="text/css"></link>

<%@include file="/WEB-INF/card/background.jsp"%>

<s:if test='blocks[0].display=="Y"'>
<div class="wdy_top block ${blocks[0].ctname}" status="0" hyct="Y" style="${blocks[0].hyct}" hyblk="S" hydata="${blocks[0].rid}" class_data="hy20150507111858">
	<img src="${blocks[0].img}" style="width:100%;height:100%;" hyvar="img" hydesc="640*350"/>
</div>
</s:if>
<s:if test='blocks[1].display=="Y"'>
<div class="turntable-bg block ${blocks[1].ctname}" status="0" hyct="Y" style="${blocks[1].hyct}" hydata="${blocks[1].rid}">
	<s:if test='blocks[1].obj.id > 0'> 
		<div class="pointer"><img src="/images/hudong/dazhuanpan2/dzp_simg.png" alt="pointer" width=100% height=100% /></div>
		<div class="rotate" ><img id="rotate" src="/images/hudong/dazhuanpan2/dzp_bimg.png" alt="turntable" width=100% height=100% /></div>
		<s:set name="obj" value="blocks[1].obj" />
	</s:if>
	<s:else>
		<img src="/images/hudong/dazhuanpan2/dzp_03.png" width="auto\9" height="auto" style="max-width: 100%;" hyvar="img" hydesc="320*210"/>
	</s:else>
</div>
</s:if>
<%@include file="/WEB-INF/card/dzpfileFprize.jsp"%>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
