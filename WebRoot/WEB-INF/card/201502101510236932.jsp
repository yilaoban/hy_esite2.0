<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 首页13 -->
<link rel="stylesheet" href="/css/shouye/13_n/style.css" type="text/css"></link>
<script type="text/javascript" src="/js/shouye/12_n/zepto.js"></script>
<script type="text/javascript" src="/js/shouye/12_n/swipe.js"></script>
<script type="text/javascript" src="/js/shouye/12_n/jquery.flexslider.js"></script>
<script type="text/javascript">
var $ = jQuery.noConflict();
$(window).load(function() {
	$('.icons_nav_20150204').flexslider({
	animation: "slide",
	directionNav: true, 
	animationLoop: false,
	controlNav: false, 
	slideshow: false,
	animationDuration: 300
	});
	$('.panels_slider').flexslider({
	animation: "slide",
	directionNav: false,
	controlNav: true, 
	animationLoop: false,
	slideToStart: 1,
	animationDuration: 300, 
	slideshow: false
	});
});
</script>
<%@include file="/WEB-INF/card/background.jsp"%>

<s:if test='blocks[0].display=="Y"'>
<div style="-webkit-transform:translate3d(0,0,0);" class_data="hy2015050771861">
	<div id="banner_box${pcid}" class="box_swipe block" hyblk="C" hydata="${blocks[0].rid}" class_data="hy2015050771680">
	    <ul class_data="hy2015050771851">
		<s:iterator status='st' value='blocks[0].list' var='item'>
	      <li class_data="hy2015050771478"><img src="${item.img}" alt="${st.count }" hyvar="img" hydesc="640*1136"/></li>
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
<div class="bottom_nav_20150204" class_data="hy2015050771600">
	<div class="icons_nav_20150204" class_data="hy2015050771429">
		<div class="paginated_20150204 block"  hyblk="C" hydata="${blocks[1].rid}" class_data="hy2015050771751">
			<ul class="slides" class_data="hy2015050771407">
			<s:iterator status='st' value='blocks[1].list' var='item'>
				<li class_data="hy2015050771504">  
					<a href="${item.link1}" target="_blank" hyvar="link1">
						<img class="shouye_20150204_2_img" src="${item.img1}" hyvar="img1" hydesc="108*108"/>
						<span hyvar="title1" class_data="hy2015050771591">${item.title1}</span>
					</a> 
					<a href="${item.link2}" target="_blank" hyvar="link2">
						<img class="shouye_20150204_2_img" src="${item.img2}" hyvar="img2" hydesc="108*108"/>
						<span hyvar="title2" class_data="hy2015050771133">${item.title2}</span>
					</a> 
					<a href="${item.link3}" target="_blank" hyvar="link3">
						<img class="shouye_20150204_2_img" src="${item.img3}" hyvar="img3" hydesc="108*108"/>
						<span hyvar="title3" class_data="hy2015050771951">${item.title3}</span>
					</a> 
					<a href="${item.link4}" target="_blank" hyvar="link4">
						<img class="shouye_20150204_2_img" src="${item.img4}" hyvar="img4" hydesc="108*108"/>
						<span hyvar="title4" class_data="hy2015050771391">${item.title4}</span>
					</a> 
				</li>
			</s:iterator>
			</ul>
		</div>
	</div>
</div>
</s:if>
<%@include file="/WEB-INF/card/cardfile.jsp"%>