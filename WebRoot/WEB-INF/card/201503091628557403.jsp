<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 首页20 -->
<link href="/css/shouye/20/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/shouye/20/swipe.js" ></script>
<script type="text/javascript" src="/js/shouye/20/zepto.js" ></script>

<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>
<div style="-webkit-transform:translate3d(0,0,0);" hyblk="C" class="block" hydata="${blocks[0].rid}" class_data="hy2015050778280">
	<div id="banner_box${pcid}" class="box_swipe" class_data="hy2015050778513">
		<ul class_data="hy2015050778436">
		<s:iterator status='st' value='blocks[0].list' var='item'>
			<li class_data="hy2015050778691"><img  src="${item.img}" alt="${st.count }" style="width:100%;" hyvar="img" hydesc="640*340" /></li>
		</s:iterator>
		</ul>
	</div>
</div>
</s:if>
<script type="text/javascript">
	$(function(){
		new Swipe(document.getElementById('banner_box${pcid}'), {
			speed:500,
			auto:3000
		});
	});
</script>
<s:if test='blocks[1].display=="Y"'>
<div class="mainmenu_20150228_2 block" hyblk="C" hydata="${blocks[1].rid}" class_data="hy2015050778260">
	<ul class_data="hy2015050778991">
	<s:iterator status='st' value='blocks[1].list' var='item'>
		<li class_data="hy2015050778345">
			<a href="${item.link}" target="_blank" hyvar="link">
				<img src="${item.img}" alt="" hyvar="img" hydesc="160*120">
				<span style="background:url(/images/shouye/20/lmbg.png) no-repeat center top;background-size:100% auto;color:#fff; " hyvar="title" class_data="hy2015050778940">${item.title}</span>
			</a>
		</li>
	</s:iterator>
	</ul>
</div>
</s:if>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
