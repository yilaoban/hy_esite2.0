<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
	        <li class="two"><a class="mine" href="/${oname}/user/showOrderList.action?status=DFK">待付款</a></li>
	        <li class="two "><a class="mine" href="/${oname}/user/showKqOrderList.action">我的卡券</a></li>
	        <li class="last red"><a class="mine" href="/${oname}/user/myAddress.action">我的地址</a></li>
        </s:if>
        <s:else>
        	<li class="one"><a class="mine" href="/${oname}/user/showJfOrderList.action">全部</a></li>
	        <li class="two"><a class="mine" href="/${oname}/user/showJfOrderList.action?status=DFK">待付款</a></li>
	        <li class="two "><a class="mine" href="/${oname}/user/showJfKQOrderList.action">我的卡券</a></li>
	        <li class="last red"><a class="mine" href="/${oname}/user/myAddress.action">我的地址</a></li>
        </s:else>
        </ul>
</div>
<div style="width:100%;height:84px;"></div>
<div class="main">
        <ul class="one">
             <s:iterator value="dto.payAddressList" var="ua">
	            <li class="my_address" id="${ua.id}">
	             <p class="seventhn"><em>${ua.name }</em>${ ua.telphone}</p>
	             <p class="eighthn"><i <s:if test='#ua.isdefault=="Y"'> class="moren" </s:if>>[默认]</i>${ua.province }${ua.city }${ua.address }</p>
	            </li>
	        </s:iterator>
	        <li>
        		<p class="dizhi"><a href="/${oname}/user/addPayAddress.action?type=${type}"><em>+</em>新增地址</a></p>
        	</li>
        </ul>
        
</div>
 <div class="bottom">
	<s:if test='type == "W"'>
		<p><a href="/${oname}/user/payHome.action" class="shouye">首页</a><a href="/${oname }/user/payProductList.action" class="fenlei">分类</a><a href="/${oname}/user/showShoppingCart.action" class="car">购物车</a><a href="/${oname}/user/showOrderList.action" class="mine2">我的</a></p>
	</s:if>
	<s:else>
		<p><a href="/${oname}/user/payJfHome.action" class="shouye">首页</a><a href="/${oname }/user/payJfProductList.action" class="fenlei">分类</a><a href="/${oname}/user/showJfShoppingCart.action" class="car">购物车</a><a href="/${oname}/user/showJfOrderList.action" class="mine2">我的</a></p>
	</s:else>
</div>
<script type="text/javascript">
$(".moren").show();

function delPayAddress(aid){
	$.confirm("确定删除该收获地址吗","",function(){
 		$.post("/${oname}/user/delPayAddress.action",{"aid":aid},function(data){
 			if(data > 0){
 				$.alert("删除收货地址成功","",function(){
					$("#"+aid).remove();
 				});
 			}else{
 				$.alert("删除收货地址失败！");
 			}
 		});
	});
 	}
 	
$(".my_address").on("click", function() {
	var id = $(this).attr("id");
    $.actions({
      actions: [{
        text: "编辑",
        onClick: function() {
        	window.location.href="/${oname }/user/editPayAddress.action?aid="+id+"&type=${type }";
        }
      },{
        text: "删除",
        onClick: function() {
        	delPayAddress(id);
        }
      }]
    });
  });

$(document).ready(function(){
	var param = '${type}';
	$.cookie('showOrder_cookie', param);
});
</script>
