<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="/js/qrcode.js"></script>

<link href="/css/shop/myorders2.css" rel="stylesheet" type="text/css">
<link href="/css/shop/ydyx.css" rel="stylesheet" type="text/css">
<div class="top">
		<a href="javascript:void(0);" onclick="window.history.back()" class="left">
		<img src="/images/shop/back.png" />&nbsp;返回</a>我的卡券　
	<a href="#" class="right"></a>
</div>
<div class="nav">
        <ul>
        <s:if test='type == "W"'>
	        <li class="one"><a class="mine" href="/${oname}/user/showOrderList.action">全部</a></li>
	        <li class="two"><a class="mine" href="/${oname}/user/showOrderList.action?status=DFK"><s:if test='dto.dfkCount > 0'><i>${dto.dfkCount }</i></s:if>待付款</a></li>
	        <li class="two red"><a class="mine" href="/${oname}/user/showKqOrderList.action">我的卡券</a></li>
	        <li class="last"><a class="mine" href="/${oname}/user/myAddress.action?type=W">我的地址</a></li>
        </s:if>
        <s:else>
        	<li class="one"><a class="mine" href="/${oname}/user/showJfOrderList.action">全部</a></li>
	        <li class="two"><a class="mine" href="/${oname}/user/showJfOrderList.action?status=DFK"><s:if test='dto.dfkCount > 0'><i>${dto.dfkCount }</i></s:if>待付款</a></li>
	        <li class="two red"><a class="mine" href="/${oname}/user/showJfKQOrderList.action">我的卡券</a></li>
	        <li class="last"><a class="mine" href="/${oname}/user/myAddress.action?type=J">我的地址</a></li>
        </s:else>
        </ul>
</div>
<div style="width:100%;height:84px;"></div>

<s:if test="dto.payOrderList.size() > 0">
<div class="main">
	<ul id="cont">
	<s:iterator value="dto.payOrderList" var="d">
		<s:iterator value="#d.goods" var="g">
	<li>
		<ul class="list">
				<li>
	            	<s:if test="#g.payApt != null">
						<a href="javascript:void(0);"><img src="${imgDomain }${g.payApt.img}"></a>
					</s:if>
					<s:else>
	                  	<a href="javascript:void(0);"><img src="${imgDomain }${g.productsimg}"></a>
					</s:else>
               	</li>
				<div class="pname">
					${g.productname }
				</div>
				<s:if test='type == 1'>
					<p class="total"><span>${d.createtimeStr}</span>实付款：<i>￥${g.price/100}</i></p>
				 </s:if>
				 <s:else>
					<p class="total"><span>${d.createtimeStr}</span>实付款：<i>${g.price}积分</i></p>
				 </s:else>
				<p class="second">
	             	<s:if test='#g.used != "Y"'>
		             	<a href="javascript:void(0);" onclick="showOrderUrl(${g.id })" class="use" id="qsy${g.id}">去使用</a>
		 			</s:if>
		 			<s:elseif test='#g.used == "Y" '>
			 			<a href="javascript:void(0);" class="used">已使用</a>
			 		</s:elseif>
	             </p>
		</ul>
	</li>
		</s:iterator>
	</s:iterator>
	</ul>
</div>
</s:if>
<s:else>
	<div class="main" style="height:80vh;background:#FFF;margin-bottom:0;">
        <ul class="one">
             <li class="zanwu">
             <p class="third"><img src="/images/shop/icon.png"></p>
             <p class="fourth">暂无订单记录</p>
             <p class="fifth">您的购物记录会出现在这里</p>
             <s:if test='type == "W"'>
             	<p class="sixth"><a href="/${oname}/user/payHome.action">去逛逛</a></p>
             </s:if>
             <s:else>
             	 <p class="sixth"><a href="/${oname}/user/payJfHome.action">去逛逛</a></p>
             </s:else>
             </li>
        </ul>
	</div>
</s:else>
 <div class="bottom">
	<s:if test='type == "W"'>
		<p><a href="/${oname}/user/payHome.action" class="shouye">首页</a><a href="/${oname }/user/payProductList.action" class="fenlei">分类</a><a href="/${oname}/user/showShoppingCart.action" class="car">购物车</a><a href="/${oname}/user/showOrderList.action" class="mine2">我的</a></p>
	</s:if>
	<s:else>
		<p><a href="/${oname}/user/payJfHome.action" class="shouye">首页</a><a href="/${oname }/user/payJfProductList.action" class="fenlei">分类</a><a href="/${oname}/user/showJfShoppingCart.action" class="car">购物车</a><a href="/${oname}/user/showJfOrderList.action" class="mine2">我的</a></p>
	</s:else>
</div>
<script type="text/javascript">
var type = "${type}";

  	function showOrderUrl(poid){
  				$.showLoading();
  		$.post("/${oname}/user/showOrderUrl.action",{"poid":poid},function(data){
  			if(data.status == 1){
  				qrcode.makeCode(data.url);
		  		setTimeout(function() {
		          $.hideLoading();
	  				erweima(poid);
		        }, 1500);
               	$("#qrcode img").css("margin","0 auto");
  			}else{
  				$.alert(data.hydesc);
  			}
  		});
  	
  	}
  	var run = false;
  	function erweima(gid){
  		  $.modal({
	          title: "扫描二维码",
	          text: $('#qrcode').html(),
	          buttons: [
	            { text: "取消", className: "default",onClick: function(){ run = false }},
	          ]
	        });
  		  run = true;
  		  check(gid);
  	}
  	
  	function check(gid){
  		if(!run) return;
  		$.post("/${oname}/user/checkused.action",{"gid":gid},function(data){
  			if(data == "Y"){
  				run = false;
  				$.closeModal();
 				$("#qsy"+gid).unbind("click").removeClass("use").addClass("used").text("已使用");
  				$.toast("扫描成功！")
  			}else{
	  			setTimeout("check("+gid+")",1000);
  			}
  		});
  	}
  	
  	function delPayOrder(payOrderid){
  		$.confirm('确定删除该订单?',"",function(result){
  			$.post("/${oname}/user/delPayOrder.action",{"payOrderid":payOrderid},function(data){
					if(data> 0){
						$.alert("删除订单成功","",function(){
 							window.location.reload();
 						});
					}else{
						$.alert("删除该订单失败");
					}
				});
		});
  	}
  	
  	function cancelPayOrder(payOrderid){
  		$.confirm("确定取消订单吗","",function(){
  			$.post("/${oname}/user/cancelPayOrder.action",{"payOrderid":payOrderid},function(data){
 					if(data> 0){
 						$.alert("取消订单成功","",function(){
 							window.location.reload();
 						});
 					}else{
 						$.alert("删除该订单失败");
 					}
 				});
  		});
  	}
        var generatedCount = 2;
        var url;
        if(type == "W"){
			url="/${oname}/user/ajaxShowKqOrderList.action";
		}else{
			url="/${oname}/user/ajaxShowJfKQOrderList.action";
		}
        
        function load1() {
             	$.post(url,{"pageId":generatedCount},function(data){
		    		if(data){
		    			if(data.payOrderList){
							$.each( data.payOrderList, function(i, n){
								$.each(n.goods,function(i2,n2){
								var html = '<li>';
										html += '<ul class="list"><li><a href="javascript:void(0);">';
									if(n2.payApt != null){
										html += '<img src="${imgDomain}'+n2.payApt.img+'"></a></li>';
									}else{
										html += '<img src="${imgDomain }'+n2.productsimg+'"></a></li>';
									}
									html += '<div class="pname">'+n2.productname+'</div>';
									if(n2.type == 1){
										html += '<p class="total"><span>'+n.createtimeStr+'</span>实付款：<i>￥'+n2.price/100+'</i></p>';
									}else{
										html += '<p class="total"><span>'+n.createtimeStr+'</span>实付款：<i>'+n2.price+'积分</i></p>';
									}
									html += '<p class="second">';
									if(n2.used == "Y"){
										html += '<a href="javascript:void(0);" onclick="showOrderUrl('+n2.id+')" class="use">去使用</a>';
									}else{
										html += '<a href="javascript:void(0);" class="used">已使用</a>';
									}
									html += '</p></ul></li>';
									$("#cont").append(html);
								});
							});
		                	++generatedCount;
		    			}
		    		}
			   });
               
        }
        
		var loading = false;
        $(document.body).infinite().on("infinite", function() {
          if(loading) return;
          loading = true;
          setTimeout(function() {
           load1();
            loading = false;
          }, 1000);
        });
  	
</script>

<div id="qrcode" style="display: none;" ></div>


<script type="text/javascript">
var qrcode = new QRCode(document.getElementById("qrcode"), {
	width : 200,
	height : 200
});
</script>