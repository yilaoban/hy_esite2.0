<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 积分商城列表 -->
<link href="/css/liebiao/14/list.css" rel="stylesheet" type="text/css" />
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
	<div class="weui-infinite-scroll" style="display:none">
      <div class="infinite-preloader"></div>
     	正在加载
    </div>
</div>
<s:if test='method != "E"'>
<div class="bottom">
<p>
	<a href="/${oname}/user/payJfHome.action" class="shouye">首页</a>
	<a href="/${oname}/user/payJfProductList.action" class="fenlei2">分类</a>
	<a href="/${oname}/user/showJfShoppingCart.action" class="car">购物车</a>
	<a href="/${oname}/user/showJfOrderList.action" class="mine">我的</a>
</p>
</div>
</s:if>
<script>
var loading = false;
var start = 1;
var id = '${blocks[0].list[0].id}';
$(document.body).infinite().on("infinite", function() {
  if(loading) return;
  loading = true;
  load(id);
});
$(document).ready(function(){
	$(".loadproduct").click(function(){
		var ccid = $(this).attr("ccid");
		$(".liebiao_content").empty();
		start = 1;
		loading = false;
		id=ccid;
		load(ccid);
	});
	if(id>0){
		load(id);
	}
	
});
function load(ccid){
	$(".weui-infinite-scroll").show();
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
		        html +='<img src="'+imgurl+'" class="pic">';
		        html += '</div>'; 
		        html +='<span class="one move">'+value.name+'</span>';
		        html +='<span class="two">'+tag+'</span>';
		        html +='<span class="three"><span>积分值:'+value.salesprice+'</span></span>';
	        	html +='</a>';
	        	html +='</li>';
			$(".liebiao_content").append(html);
		});
		if(data.product.length >= sizecount){
			start++;
		}
		$(".weui-infinite-scroll").hide();
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
