<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!-- 积分商城订单 -->
<script src="/js/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/qrcode.js"></script>
<link rel="stylesheet" href="/css/shop/shop.css">
<script type="text/javascript" src="/js/easyDialog/easydialog.min.js"></script>
<link href="/js/easyDialog/easydialog.css" rel="stylesheet" type="text/css" />
<%@include file="/WEB-INF/card/background.jsp"%>
<div class="page-head">
	<h4>我的订单</h4>
	<a href="/${oname}/user/payJfHome.action" class="back"><</a>
	<a href="/${oname}/user/showJfOrderList.action" class="right-link">全部订单</a>
</div>
<div class="order-cate clearfix">
	<ul>
		<li class="dfk <s:if test='status == "DFK"'>active</s:if>"><a href="/${oname}/user/showJfOrderList.action?status=DFK"><img src="/images/shop/dfk.png" /><span>待付款</span></a></li>
		<li class="dfh <s:if test='status == "DFH"'>active</s:if>"><a href="/${oname}/user/showJfOrderList.action?status=DFH"><img src="/images/shop/dfh.png" /><span>待发货</span></a></li>
		<li class="dsk <s:if test='status == "DSH"'>active</s:if>"><a href="/${oname}/user/showJfOrderList.action?status=DSH"><img src="/images/shop/dsk.png" /><span>待收货</span></a></li>
	</ul>
</div>

<div class="order-list">
<s:if test="dto.payOrderList.size() > 0">
	<s:iterator value="dto.payOrderList" var="d">
		<div class="order">
		<table>
		 	<tr>
			 	<td width="60">
			 		<a href="javascript:void(0);" onclick="orderShow(${d.id },${d.payrecordid })">
			 		<img src="${imgDomain }${d.simgurl}" height="60" width="60"/>
			 		</a>
			 	</td>
			 	<td width="120">
			 		<a href="javascript:void(0);" onclick="orderShow(${d.id },${d.payrecordid })">
			 		${d.name }
			 		</a>
			 	</td>
			 	<td width="30" style="color:#979797;">
			 		x ${d.num }
			 	</td>
			 	<td width="50">
			 		${d.price}积分
			 	</td>
			 	<td>
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
  	function orderShow(payOrderid,payrecordid){
  		var str = '['+payOrderid+']';
		window.location.href = "/${oname}/user/orderShow.action?str=" + str +"&payrecordid="+payrecordid;
  	}
  	
  	function showOrderUrl(poid){
  		$.post("/${oname}/user/showOrderUrl.action",{"poid":poid},function(data){
  			if(data.status == 1){
  				qrcode.makeCode(data.url);
				easyDialog.open({
				   container : 'easyDialogWrapper'
				});
                $("#qrcode img").css("margin","0 auto");
  			}else{
  				alertMsg(data.hydesc);
  			}
  		});
  	
  	}
  	
</script>
<div class="easyDialog_wrapper" id="easyDialogWrapper" >
  <div class="easyDialog_content">
    <h4 class="easyDialog_title" id="easyDialogTitle">
      <a href="javascript:void(0)" title="关闭窗口" class="close_btn" id="closeBtn" onclick="easyDialog.close();">&times;</a>
    </h4>
    <div class="easyDialog_text">
       <div id="qrcode" style="text-align:center;"></div>
    </div>	
  </div>
</div>
<script type="text/javascript">
var qrcode = new QRCode(document.getElementById("qrcode"), {
	width : 200,
	height : 200
});
</script>
<%@include file="/WEB-INF/card/cardfile.jsp"%>