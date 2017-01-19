<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="/css/scIndex/reset.css" rel="stylesheet" type="text/css" />
<link href="/css/scIndex/index_zhifu.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	document.title='会员卡支付';
	
	function balancePay(){
		var total_fee = parseInt('${dto.realprice}');//支付价格
		var rmb = parseInt('${dto.rmb}');
		var type = '${type}';
		if(rmb < total_fee){
			$.alert("会员卡余额不足");
		}else{
			$.post("/${oname}/user/payBalance.action",{"id":'${id}',"type":'${type}'},function(data){
				if(data > 0){
					$.toast("支付成功");
					if(type == "S"){
						setTimeout('window.location.href = "/${oname}/user/payHome.action"',2000);
					}else{
						setTimeout('window.location.href = "${pageDomain}/${oname}/user/wxshowp/uidt/${id}.html"',2000);
					}
				}else if(data == 0){
					$.toast("该订单已付款");
				}
			})
		}
	}
	
</script>

<div class="box0511">
     <div class="top05112">
     	<p><img src="${sessionScope.visitUser.wxUser.headimgurl}" alt=""></p>
     </div>
	 <div class="middle05112">
		<p>消费金额：<i>${dto.realprice/100 }</i>元</p>
		<p>账户余额：<i>${dto.rmb/100}</i>元<a href="/${oname}/user/wxshow/${dto.pagedto.rmyid}/wxn/${id}-hy-${type}.html">充值</a></p>
	</div>
	<div class="bottom0511">
		<p class="one"><a href="javascript:void(0);" onclick="balancePay()">立即支付</a></p>
	</div>
</div>


