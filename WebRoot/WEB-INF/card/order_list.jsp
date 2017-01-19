<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script src="/js/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/qrcode.js"></script>
<link rel="stylesheet" href="/css/shop/shop.css">
<div class="page-head">
	<h4>我的订单</h4>
	<a href="/${oname}/user/payHome.action" class="back"><</a>
	<a href="/${oname}/user/showOrderList.action" class="right-link">全部订单</a>
</div>
<div class="order-cate clearfix">
	<ul>
		<li class="dfk <s:if test='status == "DFK"'>active</s:if>"><a href="/${oname}/user/showOrderList.action?status=DFK"><img src="/images/shop/dfk.png" /><span>待付款</span></a></li>
		<li class="dfh <s:if test='status == "DFH"'>active</s:if>"><a href="/${oname}/user/showOrderList.action?status=DFH"><img src="/images/shop/dfh.png" /><span>待发货</span></a></li>
		<li class="dsk <s:if test='status == "DSH"'>active</s:if>"><a href="/${oname}/user/showOrderList.action?status=DSH"><img src="/images/shop/dsk.png" /><span>待收货</span></a></li>
	</ul>
</div>

<div class="order-list">
<s:if test="dto.payOrderList.size() > 0">
	<s:iterator value="dto.payOrderList" var="d">
		<div class="order">
		<table>
		 	<tr>
			 	<td width="60">
			 		<s:if test='#d.status == "DFK"'>
						<a href="javascript:void(0);" onclick="payOrder(${d.id },${d.payrecordid })">
				 	</s:if>
				 	<s:else>
			 			<a href="javascript:void(0);" onclick="orderShow(${d.id },${d.payrecordid })">
			 		</s:else>
			 		<img src="${imgDomain }${d.simgurl}" height="60" width="60"/>
			 		</a>
			 	</td>
			 	<td width="120">
			 		<s:if test='#d.status == "DFK"'>
						<a href="javascript:void(0);" onclick="payOrder(${d.id },${d.payrecordid })">
				 	</s:if>
				 	<s:else>
			 			<a href="javascript:void(0);" onclick="orderShow(${d.id },${d.payrecordid })">
			 		</s:else>
			 		${d.name }
			 		</a>
			 	</td>
			 	<td width="30" style="color:#979797;">
			 		x ${d.num }
			 	</td>
			 	<td width="50">
				 	￥${d.price/100 }<br>
				 	<s:if test='#d.status == "DFK"'>
						<a href="javascript:void(0);" onclick="payOrder(${d.id },${d.payrecordid })" class="pay-link">去付款</a>
				 	</s:if>
			 	</td>
			 	<td>
			 		<s:if test='#d.status == "DFK"'>
			 			<a href="javascript:void(0);" onclick="delPayOrder(${d.id })" class="pay-link">删除</a>
			 		</s:if>
			 		<s:if test='#d.subtype == "K"'>
			 			<s:if test='#d.status != "CMP" && #d.status != "DFK"'>
				 			<a href="javascript:void(0);" onclick="showOrderUrl(${d.id })">查看</a>
			 			</s:if>
			 			<s:elseif test='#d.status == "CMP" '>
				 			已验证
				 		</s:elseif>
			 		</s:if>
			 	</td>
			</tr>
		</table>
		</div>
	</s:iterator>
	<%-- <%@include file="/WEB-INF/bdpage/pageBar.jsp" %> --%>
</s:if>
<s:else>
	<p align="center" style="padding:20px 0 50px;">
		<img src="/images/shop/noorder.png" style="width:60%;"/>
		<a href="#"><img src="/images/shop/guang.png" style="width:40%;margin-top:20px;"/></a>
	</p>
</s:else>
</div>
 
 
<script type="text/javascript">
 		function payOrder(payOrderid,payrecordid){
 			var str = '['+payOrderid+']';
 			var redirectUrl = '/${oname}/user/showOrderList.action';
		window.location.href = "/${oname}/user/payOrder.action?str=" + str +"&redirectUrl=" + redirectUrl;
  	}
  	
  	function orderShow(payOrderid,payrecordid){
  		var str = '['+payOrderid+']';
		window.location.href = "/${oname}/user/orderShow.action?str=" + str +"&payrecordid="+payrecordid;
  	}
  	
  	function showOrderUrl(poid){
  		$.post("/${oname}/user/showOrderUrl.action",{"poid":poid},function(data){
  			if(data.status == 1){
  				$('#myModal').modal('show');
  				qrcode.makeCode(data.url);
  			}else{
  				$.alert(data.hydesc,"");
  			}
  		});
  	
  	}
  	
  	function delPayOrder(payOrderid){
  		bootbox.confirm('确定删除该订单?',function(result){
  			if(result){
	  			$.post("/${oname}/user/delPayOrder.action",{"payOrderid":payOrderid},function(data){
 					if(data> 0){
 						window.location.reload();
 					}else{
 						$.alert("删除该订单失败","");
 					}
 				});
  			}
		});
  	}
</script>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
         </div>
         <div class="modal-body">
            	<div id="qrcode"></div>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
<script type="text/javascript">
var qrcode = new QRCode(document.getElementById("qrcode"), {
	width : 200,
	height : 200
});
</script>