<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- 微商城详情-->
<link rel="stylesheet" href="/css/hudong/chpxq1/index.css" type="text/css"></link>
<style>
.content p.two a img{width:27px;}
.content p.one{color:#323232;padding:5px 0;line-height:20px;font-size:14px;}
.detail .nav em{width:110px;display:inline-block;text-align:center;color:#353535;font-size:14px;height:31px;color:#353535;}
.red{background:url(/images/hudong/chpxq1/line.png) no-repeat bottom center;background-size:80px 2px;}
</style>
<script type="text/javascript">
	function addShoppingCart(){
	    var productid = '${dto.product.id}';
		$.post("/${oname}/user/addShoppingCart.action",{"productid":productid},function(data){
			if(data > 0 ){
				$.toast("加入成功");
			}else if(data == 0){
				$.toast("加入失败", "forbidden");
			}else if(data == -1){
				$.toast("库存不足", "forbidden");
			}else if(data == -2){
				$.toast("您已经达到限购上限", "forbidden");
			}
		});
 	}
	
	function buyProduct(productid){
		$.post("/${oname}/user/buyProduct.action",{"productid":productid},function(data){
			if(data.id > 0 ){
				window.location.href = "/${oname}/user/showOrder.action?id=" + data.id + "&type=" + data.type;
			}else if(data.id == 0){
				$.toast("购买失败", "forbidden");
			}else if(data.id == -1){
				$.toast("库存不足", "forbidden");
			}else if(data.id == -2){
				$.toast("您已经达到限购上限", "forbidden");
			}
		});
	}
</script>
<div style="top:0px;left:0px;right:0;bottom:0;width: 100%;height: 100%;position:absolute;z-index:-999;background:#F6F5F7;background-size:100%,100%;"> </div>

<s:if test='dto.product != null'>
	<div class="box">
	    <div id="demo01" class="flexslider">
            <ul class="slides">
                <li><div class="img"><img src="${imgDomain }${dto.product.bimgurl}" alt="" /></div></li>
            </ul>
	    </div>
	    <div class="content">
	    <p class="one">${dto.product.tag}</p>
	    <p class="two" id="mydiv">
	    	<s:if test="dto.product.plevelMap.keySet().contains(#session.visitUser.hyUser.levelid)">
	    		<s:iterator value="dto.product.plevelMap" id="map">
	    			<s:if test="value.levelid == #session.visitUser.hyUser.levelid">
		    			<i><s:property value='value.name'/>会员价:<fmt:formatNumber type="number" value="${value.price/100}" minFractionDigits="2"/>元</i>
	    			</s:if>
	    		</s:iterator>
	    	</s:if>
	    	<s:else>
		    	<i>￥${dto.product.salesprice }元</i><em>￥${dto.product.price }元</em>
	    	</s:else>
	    	<s:if test="dto.product.total - dto.product.used > 0 && dto.buyProduct">
		    	<a href="javascript:void(0);" onclick="buyProduct(${dto.product.id})" class="buy">立即购买</a>
		    	<a onclick="addShoppingCart()" class="join"><img src="/images/hudong/chpxq1/icon7.png"></a>
		    </s:if>
		    <s:else>
		    	<s:if test="!dto.buyProduct">
		    		<a href="javascript:void(0)" class="buy2">每人限购<i>${dto.product.personlimit }</i>份</a>
		    	</s:if>
		    	<s:else>
			    	<a href="javascript:void(0)" class="buy2">已售完</a>
		    	</s:else>
		    </s:else>
	    </p>
	    </div>
	</div>
	<div class="detail">
	    <div class="nav"><em id="shangpin" class="red"><i>商品详情</i></em><em id="pinglun"><i class="wu">评论详情</i></em></div>
	    
	    <div class="shang">${dto.product.detail}</div>
	    <input type="hidden" id="oname" value="${oname }">
	    <ul class="ping" id="loadMore" page="1">
	    	<p class="sorry">抱歉，暂无评论！</p>
	    </ul>
	</div>
</s:if>
<s:else>
	<div class="box">
	    <div id="demo01" class="flexslider">
	            <ul class="slides">
	                <li><div class="img"><img src="/images/hudong/chpxq1/pic2.png" alt="" /></div></li>
	            </ul>
	    </div>
	    <div class="content">
	    <p class="one">【染发套餐】原价1018元，现价680元！请于消费前3小时进行预约，节假日通用，店内提供免费无线WiFi！</p>
	    <p class="two" id="mydiv"><i>￥680元</i><em>￥1018元</em><a href="#" class="buy">立即购买</a><a href="#"  class="join"><img src="/images/hudong/chpxq1/icon7.png"></a></p>
	    </div>
	</div>
	<div class="detail">
	    <div class="nav"><em id="shangpin" class="red"><i>商品详情</i></em><em id="pinglun"><i class="wu">评论详情</i></em></div>
	    
	    <ul class="shang">
	        <li><img src="/images/hudong/chpxq1/pic2.png"></li>
	        <li><img src="/images/hudong/chpxq1/pic3.png"></li>
	        <li><img src="/images/hudong/chpxq1/pic4.png"></li>
	    </ul>
	    <input type="hidden" id="oname" value="${oname }">
	    <ul class="ping">
	            <li><p class="one"><img src="/images/hudong/chpxq1/pic6.png">木槿花</p>
	                <p class="two">服务周到，小哥手艺很好，发型做完整个人的气质都有提升了，下次还来哦..</p>
	                <p class="three">2016-03-26</p>
	            </li>
	            
	    </ul>
	</div>
</s:else>
<s:if test='method != "E"'>
<div class="bottom16413">
	<a href="/${oname}/user/payHome.action" class="shouye">首页</a>
		<a href="/${oname}/user/payProductList.action" class="fenlei2">分类</a>
		<a href="/${oname}/user/showShoppingCart.action" class="car">购物车</a>
		<a href="/${oname}/user/showOrderList.action" class="mine">我的</a>
</div>
</s:if>
<script>
$(".detail ul").hide();
$(".nav em#pinglun").click(function(){
	$(this).siblings().removeClass("red");
	$(this).addClass("red")
	$(".shang").hide();
	$(".ping").show();
})
$(".nav em#shangpin").click(function(){
	$(this).siblings().removeClass("red");
	$(this).addClass("red")
	$(".ping").hide();
	$(".shang").show();
	loadComments();
})

function loadComments() {
	var topicid = $("#topicid").val();
	var forumid = $("#forumid").val();
	var source = "0-" + forumid + "-" + topicid;
	$.post("/" + $("#oname").val() + "/user/bbs/topic/showComments-" + source + ".action", {
		"pageId" : page
	}, function(data) {
		var comments = data.comments;
		if (comments.length != 0) {
			$(".sorry").hide();
			if(comments.length == 10){
				$("#loadMore").attr("page", page + 1);
			}
		}
		var comment_html =  '<li>'+
							'<p class="one"><img src="{{creater_img}}">{{creater}}</p>'+
			                '<p class="two">{{{content}}}</p>'+
			                '<p class="three">{{CREATE_TIME}}</p>'+
			            '</li>';
		for (var i = 0; i < comments.length; i++) {
			comments[i].CREATE_TIME = new Date(comments[i].CREATE_TIME).Format("yyyy-MM-dd hh:mm:ss");
			var rendered = Mustache.render(comment_html, comments[i]);
			$(".ping").append(rendered);
		}
	});
}
</script>
<%@include file="/WEB-INF/card/cardfile.jsp"%>

