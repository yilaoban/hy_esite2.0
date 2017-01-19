<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 首页15 -->
<link rel="stylesheet" href="/css/shouye/15_n/index.css" type="text/css"></link>
<script type="text/javascript" src="/js/shouye/15_n/swipe.js" ></script>
<script type="text/javascript" src="/js/shouye/15_n/zepto.js" ></script>
<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>
<div id="banner_box${pcid}" class="box_swipe block" hyblk="C" hydata="${blocks[0].rid}" class_data="hy2015050773752">
	<ul class_data="hy2015050773474">
	<s:iterator status='st' value='blocks[0].list' var='item'>
		<li class_data="hy2015050773235"><a href="${item.link}" target="_blank" hyvar="link"><img src="${item.img}" alt="${st.count }" hyvar="img" hydesc="650*400"/></a></li>
	</s:iterator>
	</ul>
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
		
		
<div style="clear:both"></div>	
<s:if test='blocks[1].display=="Y"'>	
<div class="mainmenu_20150205_2 block" hyblk="C" hydata="${blocks[1].rid}" class_data="hy2015050773261">
	<ul class_data="hy2015050773166">
	<s:iterator status='st' value='blocks[1].list' var='item'>
		<li class_data="hy2015050773726">
			<a style="background:rgba(${item.col},${item.tm});" href="${item.link}" target="_blank" hyvar="link">
				<b></b>
				<img src="${item.img}" hyvar="img" hydesc="200*120"/>
				<p hyvar="title" style="color: #505359;" class_data="hy2015050773713">${item.title}</p>
			</a>
		</li>
	</s:iterator>
	</ul>
</div>
</s:if>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
