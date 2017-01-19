<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 积分商城首页 -->
<link href="/css/scIndex/index.css" rel="stylesheet" type="text/css" />
<script src="/js/hudong/chpxq1/slider.js" type="text/javascript"></script>
<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[1].display=="Y"'>
<div id="demo01${pcid}" class="flexslider block" status="0" hydata="${blocks[1].rid}" style="${blocks[1].hyct};">
	<ul class="slides">
		<s:iterator status='st' value='blocks[1].list' var='item'>
	    	<li><div class="img"><img src="${item.img}" style="width:100%;" /></div></li>
	    </s:iterator>
	</ul>
</div>
</s:if>
<div class="more">
<%-- 
        <a href="/${oname}/user/payJfProductList.action">查看所有商品<img src="/images/scIndex/right2.png"></a>
        --%>
</div>
<s:if test='blocks[2].display=="Y"'>
<div class="main block" status="0" hydata="${blocks[2].rid}" style="${blocks[2].hyct};">
	<ul id="mainul">
	<s:if test='blocks[2].list.size == 0'>
		<li>
			<a href="#">
			<img src="/images/scIndex/pic11.png">
			<p>680积分<i>1010积分</i></p>
			<span>滋养保湿套系</span>
			</a>
		</li>
		<li><a href="#">
		     <img src="/images/scIndex/pic11.png">
		     <p>680积分<i>1010积分</i></p>
		     <span>滋养保湿套系</span>
		</a></li>
		<li><a href="#">
		     <img src="/images/scIndex/pic11.png">
		     <p>680积分<i>1010积分</i></p>
		     <span>滋养保湿套系</span>
		</a></li>
		<li><a href="#">
		     <img src="/images/scIndex/pic11.png">
		     <p>680积分<i>1010积分</i></p>
		     <span>滋养保湿套系</span>
		</a></li>
		</s:if>
	</ul>

</div>
</s:if>
<s:if test='method != "E"'>
<div class="bottom2">
	<p>
		<a href="/${oname}/user/payJfHome.action" class="shouye2">首页</a>
		<a href="/${oname}/user/payJfProductList.action" class="fenlei">分类</a>
		<a href="/${oname}/user/showJfShoppingCart.action" class="car">购物车</a>
		<a href="/${oname}/user/showJfOrderList.action" class="mine">我的</a>
	</p>
</div>
</s:if>
<script type="text/javascript">
$(document).ready(function(){
	var ccid = '${blocks[2].list[0].id}';
	if(ccid > 0){
		$.post("/${oname}/user/loadcontent.action",{"ccid":ccid,"pageId":1,"size":6},function(data){
			$.each(data.product,function(index,value){
				var linkurl;
				if(value.linkurl != ""){
					linkurl = value.linkurl;
				}else{
					var pageid = '${blocks[2].pageid}';
					linkurl = "/${oname}/user/show/"+pageid+"/pcn/ctt-hy-"+value.id+".html";
				}
				var imgurl = "";
				if(value.simgurl){
					imgurl = '${imgDomain}'+value.simgurl;
				}
				var tag = "";
				if(value.tag){
					tag = value.tag;
				}
				var viphtml = '<img src="/images/scIndex/pic0522.png" class="pic0522">';
				var html ='<li>';
				if(value.vip>0){
					html += viphtml;
				}
					html += '<a href="'+linkurl+'">';
					html +=  '<img src="'+imgurl+'">';
					html +=  '<p>积分:'+value.salesprice+'<i>积分:'+value.price+'</i></p>';
					html +=  '<span>'+value.name+'</span>';
					html += '</a>';
					html += '</li>';
				$("#mainul").append(html);
			});
		});
	}
});

$(function(){

	$('#demo01${pcid}').flexslider({
		animation: "slide",
		direction:"horizontal",
		easing:"swing"
	});

});
</script>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
