<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 首页19 -->
<link href="/css/shouye/19/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/shouye/19/swipe.js" ></script>
<script type="text/javascript" src="/js/shouye/19/zepto.js" ></script>

<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>
<div style="-webkit-transform:translate3d(0,0,0);" class_data="hy2015050777135">
	<div id="banner_box${pcid}" class="box_swipe" class_data="hy2015050777675">
		<ul hyblk="C" class="block" hydata="${blocks[0].rid}" class_data="hy2015050777432">
		<s:iterator status='st' value='blocks[0].list' var='item'>
			<li class_data="hy2015050777631"><img  src="${item.img}" alt="${st.count }" style="width:100%;" hyvar="img" hydesc="640*300" /></li>
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
<div class="mainmenu_20150228_1 block" hyblk="C" hydata="${blocks[1].rid}" class_data="hy2015050777978">
	<ul class="mainmenu_20150228_1_box" class_data="hy2015050777846">
	<s:iterator status='st' value='blocks[1].list' var='item'>
		<li class_data="hy2015050777635">
			<a href="${item.link}" target="_blank" hyvar="link">
				<em style="background-color: rgba(${item.col},${item.tm});border-bottom-left-radius: 4px;border-top-left-radius: 4px;"></em>
				<p class_data="hy2015050777403"><span class="zxf_addspan1" hyvar="title" class_data="hy2015050777751">${item.title}</span><span class="zxf_addspan2" hyvar="desc" class_data="hy2015050777169">${item.desc}</span></p>
				<b></b>
			</a>
		</li>	
	</s:iterator>
	</ul>
</div>
</s:if>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
