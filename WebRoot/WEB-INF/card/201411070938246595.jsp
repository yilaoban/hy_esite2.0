<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 首页9 -->
<link href="/css/shouye/14/list.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/shouye/14/swipe.js" ></script>
<script type="text/javascript" src="/js/shouye/14/zepto.js" ></script>

<div class="shouye_1105_1" class_data="hy2015050726667">
<%@include file="/WEB-INF/card/background.jsp"%>
	<div style="-webkit-transform:translate3d(0,0,0);" class_data="hy2015050726459">
<s:if test='blocks[0].display=="Y"'>
		<div id="banner_box${pcid}" class="box_swipe block" hyblk="C"  hydata="${blocks[0].rid}" class_data="hy2015050726269">
			<ul class_data="hy2015050726202">
			<s:iterator status='st' value='blocks[0].list' var='item'>
				<li class_data="hy2015050726125"><a href="${item.link}" target="_blank" hyvar="link"><img  hyvar="img" hydesc="650*365" src="${item.img}" alt="1" style="width:100%;" /></a></li>
			</s:iterator>
			</ul>
</div>
</s:if>
	</div>
	<script type="text/javascript">
	$(function(){
	new Swipe(document.getElementById('banner_box${pcid}'), {
	speed:500,
	auto:3000,
	callback: function(){
	var lis = $(this.element).next("ol").children();
	lis.removeClass("on").eq(this.index).addClass("on");
	}
	});
	});
	</script>
	<div class="shouye_1105_1_arrowbox" class_data="hy2015050726406">
<s:if test='blocks[1].display=="Y"'>
		<div class="shouye_1105_1_arowbg block" hyblk="C" hydata="${blocks[1].rid}" class_data="hy2015050726238">
			<div class="maskshow"></div>
			<s:iterator status='st' value='blocks[1].list' var='item'>
				<div class="icon0${st.count }" class_data="hy2015050726300"> 
					<a href="${item.link}" target="_blank" hyvar="link"> 
						<span class="arrow0${st.count }" style="background:rgba(${item.col},${item.tm});"></span>
						<div class="contbox0${st.count }" class_data="hy2015050726600"> 
							<img src="${item.img}" width="40" height="40" hyvar="img" hydesc="40*40"> 
							<p hyvar="title" class_data="hy2015050726983">${item.title}</p> 
						</div>
					</a> 
				</div>
			</s:iterator>
</div>
</s:if>
	</div>
</div>


<%@include file="/WEB-INF/card/cardfile.jsp"%>
