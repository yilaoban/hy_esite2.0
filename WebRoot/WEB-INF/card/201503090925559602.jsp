<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 首页17 -->
<link href="/css/shouye/17/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/shouye/17/swipe.js" ></script>
<script type="text/javascript" src="/js/shouye/17/zepto.js" ></script>

<%@include file="/WEB-INF/card/background.jsp"%>

<div style="-webkit-transform:translate3d(0,0,0);" class_data="hy2015050775129">
<s:if test='blocks[0].display=="Y"'>
  <div id="banner_box${pcid}" class="box_swipe block" hyblk="C" hydata="${blocks[0].rid}" class_data="hy2015050775849">
    <ul class_data="hy2015050775433">
		<s:iterator status='st' value='blocks[0].list' var='item'>
		<li class_data="hy2015050775291"><img  src="${item.img}" alt="${st.count }" style="width:100%;" hyvar="img" hydesc="640*300" /></li>
		</s:iterator>
    </ul>
</div>
</s:if>
</div>
<script type="text/javascript">
	$(function(){
		new Swipe(document.getElementById('banner_box${pcid}'), {
			speed:500,
			auto:3000
		});
	});
</script>
<s:if test='blocks[1].display=="Y"'>
<div class="mainmenu_20150227_1 block" hyblk="C" hydata="${blocks[1].rid}" class_data="hy2015050775264">
	<dl class="mainmenu_20150227_1_box" class_data="hy2015050775418">
	<s:iterator status='st' value='blocks[1].list' var='item'>
		<dd class_data="hy2015050775818">
			<a href="${item.link}" target="_blank" hyvar="link">
				<img src="${item.img}" hyvar="img" hydesc="400*300"/>
				<p class_data="hy2015050775786">
					<span class="zxf_addspan1" hyvar="title" class_data="hy2015050775875">${item.title}</span>
					<span class="zxf_addspan2" hyvar="desc" class_data="hy2015050775282">${item.desc}</span>
				</p>
			</a>
		</dd>
	</s:iterator>
	</dl>
</div>
</s:if>
<%@include file="/WEB-INF/card/cardfile.jsp"%>