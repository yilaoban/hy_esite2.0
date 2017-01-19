<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 首页22 -->
<link href="/css/shouye/22/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/shouye/22/swipe.js" ></script>
<script type="text/javascript" src="/js/shouye/22/zepto.js" ></script>

<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>
<div style="-webkit-transform:translate3d(0,0,0);" class_data="hy2015050780894">
	<div id="banner_box${pcid}" class="box_swipe block" hyblk="C" hydata="${blocks[0].rid}" class_data="hy2015050780621">
		<ul class_data="hy2015050780647">
		<s:iterator status='st' value='blocks[0].list' var='item'>
			<li class_data="hy2015050780378"><img  src="${item.img}" alt="${st.count }" style="width:100%;" hyvar="img" hydesc="640*340" /></li>
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
<div class="mainmenu_20150228_4" class_data="hy2015050780731">
	<ul hyblk="C" class="block clearfix" hydata="${blocks[1].rid}" class_data="hy2015050780726">
		<s:iterator status='st' value='blocks[1].list' var='item'>
		<li class_data="hy2015050780404">
			<a href="${item.link}" target="_blank" hyvar="link">
				<div class="ChannelIcon" class_data="hy2015050780355"> <img src="${item.img}" hyvar="img" hydesc="256*180"/> </div>
				<div class="ChannelName" hyvar="title" style="color:#575757;" class_data="hy2015050780919">${item.title}</div>
			</a>
		</li>
		</s:iterator>
</ul>
</div>
</s:if>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
