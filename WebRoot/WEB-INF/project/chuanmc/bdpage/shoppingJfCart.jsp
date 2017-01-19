<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="/css/shop/myorders2.css" rel="stylesheet" type="text/css">
<link href="/css/shop/shopping cart.css" rel="stylesheet" type="text/css">
	<script type="text/javascript">
	  	function updateQuantity(type,shopCartid){
	  		var quantity = parseInt($('#quantity_' + shopCartid).html());
	  		var remain = parseInt($('#remian_' + shopCartid).val());
		  	if(type == "A"){
	  			if(remain > quantity){
		  			quantity = quantity + 1;
	  			}
	  		}else if(type == "S"){
	  			if(quantity > 1){
		  			quantity = quantity - 1;
	  			}else if(quantity == 1){
	  				$('#del_' + productid).click();
	  			}
	  		}
		  	if(quantity > 0){
		  		$.post("/${oname}/user/updateQuantity.action",{"shopCartid":shopCartid,"quantity":quantity},function(data){
		  			if(data > 0){
		  				$('#quantity_'+ shopCartid).html(quantity);
		  				checkMoney();
		  			}else{
		  				$.alert("更新数量失败");
		  			}
		  		});
		  	}
	  	}
	  	
	  	function checkMoney(){
	  		var  num=0;
	  		var  price=0.0;
	  		$('input:checkbox[name="id"]:checked').each(function(){
	  			var pid=$(this).val();
	  			var thisNum=$("#quantity_"+pid).html();
	  			var thisSaleprice=$(this).next("input[name='salesprice']").val();
				num=num+parseInt(thisNum);
				price=price+parseFloat(thisSaleprice)*thisNum;
	  		});
	  		$(".bottom em:first").text(num)
	  		$(".bottom em:last").text(price)
	  		
	  	}
	  	
	  	function delShopCartProduct(shopCartid,name){
	  		var url = '/${oname}/user/delShopCartProduct.action';
	  		$.confirm('确定将['+name+']删除?',"",function(){
				$.post(url,{"shopCartid":shopCartid},function(data){
					if(data > 0 ){
						window.parent.location.reload();
					}else{
						alertMsg("删除失败,请重试!");
					}
				});
			});
	  	}
	  	
	  	
  		function showDingdan(){
  			var ox=$('input:checkbox[name="id"]:checked');
  			if(ox.length > 0){
	  			$('#shopform').submit();
  			}else{
				$.alert('请选择要购买的商品!');
  			}
	  	}
  		
  		
  		
  		
  		
  	
  		$(document).ready(function(){
  			$(".main ul li b").click(function(){
  				var bclass=$(this).attr("class");
  				if(bclass=="border"){
  					$(this).removeClass("border").addClass("choi");
  					$(this).children("input:checkbox").prop("checked",true);
  				}else{
  					$(this).removeClass("choi").addClass("border");
  	  				$(this).children("input:checkbox").prop("checked",false);
  				}
  				checkMoney();
  			});
  			
  			$(".bottom i").click(function(){
  				var iclass=$(this).children("b").eq(0).attr("class");
  				if(iclass=="border"){
  					$(this).children("b").removeClass("border").addClass("choice");
  					$('.main ul li b.border').click();
  				}else{
  					$(this).children("b").removeClass("choice").addClass("border");
  					$('.main ul li b.choi').click();
  				}
  			});
  			$(".bottom i").click();
  		});
	  	
  		
	</script>
	<div class="top">
		<a href="javascript:window.history.back()" class="left"><img src="/images/shop/back.png" />&nbsp;返回</a>购物车　
		<a href="#" class="right"></a>
	</div>
	<div style="width:100%;height:44px;"></div>
	<s:if test="dto.shopCartList.size()>0">
<div class="main">
	<form action="/${oname}/user/showOrder.action?type=J" method="post" id="shopform">
  	<ul>
  		<s:iterator value="dto.shopCartList" status="st" var="p">
  			<li id="${p.product.id }">
             <em class="firm">
             <b class="border">
             	<input type="checkbox" name="id" value="${p.id }">
             	<input type="hidden" name="salesprice" value="${p.product.salesjifen }">
             </b>
             <s:if test="#p.payApt != null">
	  			 <img src="${imgDomain }${p.payApt.img}" class="pic">
			</s:if>
			<s:else>
				<img src="${imgDomain }${p.product.simgurl}" class="pic">
			</s:else>
             <img src="/images/shop/del.png" class="del" onclick="delShopCartProduct(${p.id},'${p.product.name}')" id="del_${p.id }">
             <p class="ninth">${p.product.name }</p><br>
             <p class="tenth"><i>${p.product.salesjifen }积分</i><span>${p.product.jifen }积分</span></p><br>
             <p class="num"><i class="jian" onclick="updateQuantity('S',${p.id})"><img src="/images/shop/jian.png"></i><span id="quantity_${p.id}">${p.num}</span><i class="jia" onclick="updateQuantity('A',${p.id})"><img src="/images/shop/jia.png"></i></p>
             <input type="hidden" id="remian_${p.id}" value="${p.product.total - p.product.used}">
             </em>
             </li>
  		</s:iterator>
	</ul>
	</form>
</div>
<div class="bottom">
	<i><b class="border"></b>全选</i>
	<a href="javascript:void(0);" onclick="showDingdan()">去结算(<em>0</em>)</a><span>合计: <em>0.00</em>积分</span>
</div>

	</s:if>
	<s:else>
		<div class="main" style="height:90vh;background:#FFF;margin-bottom:0;">
	        <ul class="one">
	             <li class="zanwu">
	             <p class="third"><img src="/images/shop/icon2.png"></p>
	             <p class="fourth">购物车空空如也</p>
	             <p class="fifth">去挑几件喜欢的宝贝吧~</p>
	             <p class="sixth"><a href="/${oname}/user/payJfHome.action">去逛逛</a></p>
	             </li>
	        </ul>
	    </div>
	</s:else>
<div class="bottom2">
	<p><a href="/${oname}/user/payJfHome.action" class="shouye">首页</a><a href="/${oname }/user/payJfProductList.action" class="fenlei">分类</a><a href="#" class="car2">购物车</a><a href="/${oname}/user/showJfOrderList.action" class="mine">我的</a>
	</p>
</div>