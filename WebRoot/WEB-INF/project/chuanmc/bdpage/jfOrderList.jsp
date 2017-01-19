<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="/css/shop/myorders2.css" rel="stylesheet" type="text/css">
<link href="/css/shop/ydyx.css" rel="stylesheet" type="text/css">
<div class="top">
	<a href="/${oname}/user/payJfHome.action" class="left"><img src="/images/shop/back.png" />&nbsp;返回</a>我的订单　
	<a href="#" class="right"></a>
</div>

<div class="nav">
        <ul>
        <li <s:if test="status == null">class="one red"</s:if><s:else>class="one"</s:else>><a href="/${oname}/user/showJfOrderList.action" class="mine">全部</a></li>
        <li <s:if test='status == "DFK"'>class="two red"</s:if><s:else>class="two"</s:else>><a href="/${oname}/user/showJfOrderList.action?status=DFK" class="mine"><s:if test='status != "DFK" && dto.dfkCount > 0'><i>${dto.dfkCount }</i></s:if>待付款</a></li>
        <li class="two"><a class="mine" href="/${oname}/user/showJfKQOrderList.action"><s:if test='dto.kqCount > 0'><i>${dto.kqCount }</i></s:if>我的卡券</a></li>
        <li class="last"><a class="mine" href="/${oname}/user/myAddress.action?type=J">我的地址</a></li>
        </ul>
</div>

<div style="width:100%;height:84px;"></div>
<s:if test="dto.payOrderList.size() > 0">
<div class="main" id="wrapper">
	<ul id="cont">
		<s:iterator value="dto.payOrderList" var="d">
		<li id="order_list${d.id }">
            <ul class="list">
			<s:if test="#d.goods.size>1">
              		<s:iterator value="#d.goods" var="g">
                    	<li>
                    		<s:if test="#g.payApt != null">
								<a href="/${oname}/user/showPayOrder.action?payOrderid=${d.id }"><img src="${imgDomain }${g.payApt.img}"></a>
							</s:if>
							<s:else>
		                    	<a href="/${oname}/user/showPayOrder.action?payOrderid=${d.id }"><img src="${imgDomain }${g.productsimg}"></a>
							</s:else>
                    	</li>
                    </s:iterator>
			</s:if>
			<s:else>
               	<li>
               		<s:if test="#d.goods[0].payApt != null">
						<a href="/${oname}/user/showPayOrder.action?payOrderid=${d.id }"><img src="${imgDomain }${d.goods[0].payApt.img}"></a>
					</s:if>
					<s:else>
	                  	<a href="/${oname}/user/showPayOrder.action?payOrderid=${d.id }"><img src="${imgDomain }${d.goods[0].productsimg}"></a>
					</s:else>
               	</li>
				<div class="pname">
					${d.goods[0].productname }
				</div>
			</s:else>
			</ul>
              <p class="total"><span>${d.createtimeStr}</span>共<i>${d.goodscount}</i>件商品&nbsp;&nbsp;实付款：<i>${d.realprice}积分</i></p>
              <p class="second">
              	<s:if test='#d.status == "DFK"'>
              		<a href="/${oname}/user/payOrder.action?payOrderid=${d.id }" class="use">去支付</a>
		 			<a href="javascript:void(0);" onclick="cancelPayOrder(${d.id })" class="use">取消订单</a>
              	</s:if>
              	<s:if test='#d.status == "YQX"'>
				 		<a href="#" class="used">已取消</a>
				</s:if>
		 		<s:if test='#d.status == "DQR" || #d.status == "DFH"'>
		 			<a href="javascript:void(0);" class="used">已付款</a>
		 		</s:if>
		 		<s:if test='#d.status == "END"'>
		 			<a href="javascript:void(0);" class="used">已过期</a>
		 		</s:if>
		 		<a href="javascript:void(0);" onclick="delPayOrder(${d.id })" class="use">删除订单</a>
		 		<s:if test="#d.expressid != null">
		 			<a href="javascript:void(0);" onclick="showexpress('${d.expressid}')" class="use">查看物流单号</a>
		 		</s:if>
              </p>
        </li>
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
             <p class="sixth"><a href="/${oname}/user/payJfHome.action">去逛逛</a></p>
             </li>
        </ul>
</div>
</s:else>
<div class="bottom">
<p><a href="/${oname}/user/payJfHome.action" class="shouye">首页</a><a href="/${oname }/user/payJfProductList.action" class="fenlei">分类</a><a href="/${oname}/user/showJfShoppingCart.action" class="car">购物车</a><a href="/${oname}/user/showJfOrderList.action" class="mine2">我的</a></p>
</div>
 
<script type="text/javascript">
	function showexpress(expressid){
		$.modal({
          title: "物流单号",
          text: expressid,
          buttons: [
            { text: "取消", className: "default"},
          ]
        });
	}

  	function delPayOrder(payOrderid){
  		$.confirm('确定删除该订单?',"",function(result){
  			$.post("/${oname}/user/delPayOrder.action",{"payOrderid":payOrderid},function(data){
					if(data> 0){
						$.toast("删除成功",function(){
 							$("#order_list"+payOrderid).remove();
 						});
					}else{
						$.toast("删除失败",function(){
 							window.location.reload();
 						});
					}
				});
		});
  	}
  	
  	function cancelPayOrder(payOrderid){
  		$.confirm("确定取消订单吗","",function(){
  			$.post("/${oname}/user/cancelPayOrder.action",{"payOrderid":payOrderid},function(data){
 					if(data> 0){
 						$.toast("取消订单成功",function(){
 							window.location.reload();
 						});
 					}else{
 						$.toast("取消该订单失败",function(){
 							window.location.reload();
 						});
 					}
 				});
  		});
  	}
</script>
<script>
        var generatedCount = 2;
        function load1() {
             	$.post("/${oname}/user/ajaxShowJfOrderList.action",{"pageId":generatedCount,"status":'${status}'},function(data){
		    		if(data){
		    			if(data.payOrderList){
							$.each( data.payOrderList, function(i, n){
								var html = '<li>';
								html += '<ul class="list">';
								
								$.each(n.goods,function(i2,n2){
									html += '<li>';
									if(n2.payApt != null){
										html += '<a href="/${oname}/user/showPayOrder.action?payOrderid='+n.id+'"><img src="${imgDomain }'+n2.payApt.img+'"></a>';
									}else{
										html += '<a href="/${oname}/user/showPayOrder.action?payOrderid='+n.id+'"><img src="${imgDomain }'+n2.productsimg+'"></a>';
									}
									html += '</li>';
									if(n.goods.length == 1){
										html += '<div class="pname">'+n2.productname+'</div>';
									}
								});
								
              					html += '</ul>';
              					html += '<p class="total"><span>'+n.createtimeStr+'</span>共<i>'+n.goodscount+'</i>件商品&nbsp;&nbsp;实付款：<i>'+n.realprice+'积分</i></p>';
              					html += '<p class="second">';
              					if(n.status == "DFK"){
	              					html += '<a href="/${oname}/user/payOrder.action?payOrderid='+n.id+'" class="use">去支付</a>';
	              					html += '<a href="javascript:void(0);" onclick="cancelPayOrder('+n.id+')" class="use">取消订单</a>';
              					}
              					if(n.status == "YQX"){
	              					html += '<a href="#" class="used">已取消</a>';
              					}
              					if(n.status == "CMP"){
	              					
              					}
              					if(n.status == "DQR" || n.status == "DFH"){
	              					html += '<a href="javascript:void(0);" class="used">已付款</a>';
              					}
              					if(n.status == "END"){
              						html += '<a href="javascript:void(0);" class="used">已过期</a>';
              					}
              					html += '<a href="javascript:void(0);" onclick="delPayOrder('+n.id+')" class="use">删除订单</a>';
              					if(n.expressid){
	              					html += '<a href="javascript:void(0);" onclick="showexpress(\''+n.expressid+'\')" class="use">物流单号</a>';
              					}
              					html += '</li>';
              					html += '</p>';
								$("#cont").append(html);
							});
		                	generatedCount++;
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