<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 微商城列表 -->
<link href="/css/liebiao/14/list.css" rel="stylesheet" type="text/css" />
<style>
.nav16413 ul li{display:inline-block;height:18px;line-height:18px;font-size:14px;}
.nav16413 ul li em{display:inline-block;border-right:1px solid #D6D7DC;padding:0 20px 0 17px;height:18px;margin-top:11px;}
.main16413 ul li a.join{position:absolute;bottom:10px;right:4%;width:26px;height:26px;z-index:10;}
.main16413 ul li a.join img{width:26px;height:26px;}
</style>
<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>
<div class="nav16413" <s:if test='method != "E"'>style="position:fixed;"</s:if>>
	<ul>
		<s:if test="blocks[0].list.size >0">
		<s:iterator status='st' value='blocks[0].list' var='item'>
	    	<li class="loadproduct" ccid="${item.id }" pageid=${item.pageid }><em>${item.name}</em></li>
	    </s:iterator>
		</s:if>
		<s:else>
			<li class="loadproduct active"><em>剪发</em></li>
			<li><em>染发</em></li>
			<li><em>烫发</em></li>
		</s:else>
	</ul>
</div>
</s:if>
<div style="width:100%;height:40px;"></div>
<div class="main16413 block clearfix ${blocks[0].ctname}" status="0" hyct="Y" hydata="${blocks[0].rid}">
	<ul class="liebiao_content">
		<s:if test='blocks[0].list.size == 0'>
        <li>
        <a href="#" class="join"><img src="/images/liebiao/14/icon3.png" ></a>
        <a href="#">        
        <img src="/images/liebiao/14/pic1.png" class="pic">
        <span class="one move">美发造型</span><br>
        <span class="two">原价1018元的美发套餐，现仅售680元。请于消费前三小时...</span><br>
        <span class="three"><span>￥680元</span><i>￥10000元</i></span>
        </a>
        </li>
        <li>
        <a href="#" class="join"><img src="/images/liebiao/14/icon3.png" ></a>
        <a href="#">        
        <img src="/images/liebiao/14/pic1.png" class="pic">
        <span class="one move">美发造型</span><br>
        <span class="two">原价1018元的美发套餐，现仅售680元。请于消费前三小时...</span><br>
        <span class="three"><span>￥680元</span><i>￥10000元</i></span>
        </a>
        </li>
        <li>
        <a href="#" class="join"><img src="/images/liebiao/14/icon3.png" ></a>
        <a href="#">        
        <img src="/images/liebiao/14/pic1.png" class="pic">
        <span class="one move">美发造型</span><br>
        <span class="two">原价1018元的美发套餐，现仅售680元。请于消费前三小时...</span><br>
        <span class="three"><span>￥680元</span><i>￥10000元</i></span>
        </a>
        </li>
        <li>
        <a href="#" class="join"><img src="/images/liebiao/14/icon3.png" ></a>
        <a href="#">        
        <img src="/images/liebiao/14/pic1.png" class="pic">
        <span class="one move">美发造型</span><br>
        <span class="two">原价1018元的美发套餐，现仅售680元。请于消费前三小时...</span><br>
        <span class="three"><span>￥680元</span><i>￥10000元</i></span>
        </a>
        </li>
        </s:if>
	</ul>
</div>
<s:if test='method != "E"'>
<div class="bottom">
	<p>
		<a href="/${oname}/user/payHome.action" class="shouye">首页</a>
		<a href="/${oname}/user/payProductList.action" class="fenlei2">分类</a>
		<a href="/${oname}/user/showShoppingCart.action" class="car">购物车</a>
		<a href="/${oname}/user/showOrderList.action" class="mine">我的</a>
	</p>
</div>
</s:if>
<script>
var start = 1;
$(document).ready(function(){
	$.post("/${oname}/user/showCartNum.action",function(data){
	});
	
	$(".loadproduct").click(function(){
		var ccid = $(this).attr("ccid");
		$(".liebiao_content").empty();
		start = 1;
		load(ccid);
	});
	var id = '${blocks[0].list[0].id}';
	if(id>0){
		load(id);
	}
	
});
function load(ccid){
	$(".nav16413 .active").removeClass("active");
	$("[ccid='"+ccid+"']").addClass("active");
	var sizecount= 6;
	$.post("/${oname}/user/loadcontent.action",{"ccid":ccid,"pageId":start,"size":sizecount},function(data){
		$.each(data.product,function(index,value){
			var linkurl;
			if(value.linkurl != ""){
				linkurl = value.linkurl;
			}else{
				var pageid = '${blocks[0].pageid}';
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
			var goumai = "";
			if((value.total - value.used) > 0){
				goumai='<a onclick="addShoppingCart('+value.id+')" class="join"><img src="/images/liebiao/14/icon3.png" ></a>';
			}else{
				goumai='<a href="javascript:void(0)" class="join"><img src="/images/liebiao/14/icon4.png" ></a>';
			}
			var viphtml = '<img src="/images/scIndex/pic0522.png" class="pic0522">';
			var html = '<li>';
				html += goumai+'<a href="'+linkurl+'">'; 
				html += '<div class="i-0524">';           
				if(value.vip>0){
					html += viphtml;
				}
		        html += '<img src="'+imgurl+'" class="pic">';
		        html += '</div>';     
		        html +='<span class="one move">'+value.name+'</span>';
		        html +='<span class="two">'+tag+'</span>';
		        html +='<span class="three"><span>￥'+value.salesprice+'</span><i>￥'+value.price+'</i></span>';
        	html +='</a>';
        	html +='</li>';
			$(".liebiao_content").append(html);
		});
		if(data.product.length >= sizecount){
			start++;
			$(".jzgd").show();
		}else{
			$(".jzgd").hide();
		}
	});
}


function addShoppingCart(productid){
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

</script>	
<%@include file="/WEB-INF/card/cardfile.jsp"%>
