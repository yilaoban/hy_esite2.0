<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="/css/shop/ydyx.css" rel="stylesheet" type="text/css">
<link href="/css/shop/wuxiao.css" rel="stylesheet" type="text/css">
<div class="top"><a href="javascript:window.history.back()" class="left"><img src="/images/shop/back.png" />&nbsp;返回</a>订单过期<a href="#" class="right"></a></div>
<div style="width:100%;height:44px;"></div>
<div class="main0415" style="height:90vh;background:#FFF;">
   <img src="/images/shop/pic_2.png" class="pic1">
   <img src="/images/shop/pic_1.png" class="pic2">
   <s:if test='type=="J"'>
   		<a href="/${oname }/user/payJfHome.action">返回首页</a>
   </s:if>
   <s:else>
	   <a href="/${oname }/user/payHome.action">返回首页</a>
   </s:else>
</div>
<div class="bottom">
<s:if test='type=="J"'>
	<p><a href="/${oname}/user/payJfHome.action" class="shouye">首页</a><a href="/${oname }/user/payJfProductList.action" class="fenlei">分类</a><a href="/${oname}/user/showJfShoppingCart.action" class="car">购物车</a><a href="/${oname}/user/showJfOrderList.action" class="mine2">我的</a></p>
</s:if>
<s:else>
	<p><a href="/${oname}/user/payHome.action" class="shouye">首页</a><a href="/${oname }/user/payProductList.action" class="fenlei">分类</a><a href="/${oname}/user/showShoppingCart.action" class="car">购物车</a><a href="/${oname}/user/showOrderList.action" class="mine2">我的</a></p>
</s:else>
</div>