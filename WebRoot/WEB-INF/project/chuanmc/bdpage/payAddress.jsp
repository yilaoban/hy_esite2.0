<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="/css/shop/ydyx.css" rel="stylesheet" type="text/css">
<div class="top"><a href="javascript:void(0)" onclick="window.history.back()" class="left"><img src="/images/shop/back.png" />&nbsp;返回</a>地址簿　<a href="#" class="right"></a></div>
<div style="width:100%;height:44px;"></div>
<div class="main">
        <ul class="one">
        <s:iterator value="dto.payAddressList" var="ua">
            <li>
            <a href="javascript:usead(${ua.id})">
             <s:if test='#ua.id==aid'><b class="choice"></b></s:if>
             <p class="seventhn"><em>${ua.name }</em>${ ua.telphone}</p>
             <p class="eighthn"><i <s:if test='#ua.isdefault=="Y"'> class="moren" </s:if>>[默认]</i>${ua.province }${ua.city }${ua.address }</p>
             </a>
            </li>
        </s:iterator>
        	<li>
        		<p class="dizhi"><a href="/${oname}/user/addPayAddress.action"><em>+</em>新增收货地址</a></p>
        	</li>
        </ul>
</div>
<script type="text/javascript">
$(".moren").show();

function usead(aid){
	var param = $.cookie('showOrder_cookie');
	window.location.href="/${oname}/user/showOrder.action?aid="+aid+"&type=${type}"+"&"+param;
}
</script>
