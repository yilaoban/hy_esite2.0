<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="/css/shop/ydyx.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	function wxPay() {
		var total_fee = $("input[name='total_fee']").val();
		if(total_fee > 0){
			$.ajax({
				url : "/${oname}/user/wxpayPrePay.action",
				type : "post",
				data : {
					"body" : $("input[name='subject']").val(),
					"out_trade_no" : $("input[name='out_trade_no']").val(),
					"total_fee" : total_fee,
				},
				success : function(data) {
					if (data.return_code == "SUCCESS") {
						if (data.result_code == "SUCCESS") {
							wx.chooseWXPay({
								timestamp : data.timeStamp, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
								nonceStr : data.nonceStr, // 支付签名随机串，不长于 32 位
								package : data.package, // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
								signType : data.signType, // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
								paySign : data.paySign, // 支付签名
								success : function(res) { // 支付成功后的回调函数
									window.location.href = "/${oname}/user/wxpayCallBack.action?out_trade_no=" + data.out_trade_no;
								}
							});
						} else {
							$.alert("支付失败:" + data.return_msg + "-" + data.err_code_des);
						}
					} else {
						$.alert("支付失败:" + data.return_msg);
					}
				},
				error : function(err) {
					$.alert(err);
				}
			});
		}else{
			$.alert("购买成功");
			setInterval(function(){
				window.location.href = "/${oname}/user/callBack.action?out_trade_no=${payrecordid }" ;
			},2000);
		}

	}
	
	function toPay(){
		var paytype = $('#paytype').val();
		if(paytype == 1){
			wxPay();
		}else if(paytype == 2){
			window.location.href = "/${oname}/user/ownerPayBanlance.action?id=${payOrder.id}";
		}
	}
	
</script>

<div class="top">
	<a href="javascript:window.history.back()" class="left"><img src="/images/shop/back.png" />&nbsp;返回</a>确认支付　
	<a href="#" class="right"></a>
</div>
<div style="width:100%;height:44px;"></div>
<input size="30" name="out_trade_no" value="${payOrder.id}" id="out_trade_no" type="hidden" />
<input size="30" name="subject" value="${payOrder.subject}"  type="hidden" />
<input size="30" name="total_fee" value="${payOrder.realprice}" type="hidden" />

<s:if test='payOrder.status == "DFK" '>
	<div class="main">
        <ul class="one">
             <li>
             	 <p class="seventhn"><em>共计:￥${payOrder.realprice/100 }</em></p>
             </li>
        </ul>
	</div>
	<div class="way">
	<form>
	    <ul>
	         <li class="pay"><img src="/images/shop/weixin.png">微信支付<b class="choi"></b></li>
	         <li class="pay"><img src="/images/shop/weixin.png">会员卡支付<b class="border"></b></li>
	         <input type="hidden" name="paytype" value="1" id="paytype">
	    </ul>
	</form>
	</div>
	<div class="bottom2">
		<a href="javascript:void(0);" onclick="toPay()" class="zengjia">确认支付<span>￥${payOrder.realprice/100 }</span></a>
	</div>
</s:if>
<s:else>
	<div class="main">
        <ul class="one">
             <li>
             	 <p class="title">该订单已经支付过了</p>
             </li>
        </ul>
	</div>
</s:else>

<script type="text/javascript">
	$(document).ready(function(){
		$(".pay b").eq(0).click(function(){
			var bclass=$(this).attr("class");
			if(bclass=="border"){
				$(this).removeClass("border").addClass("choi");
				$('#paytype').val(1);
				$(".pay b").eq(1).removeClass("choi").addClass("border");
			}
		});
		
		$(".pay b").eq(1).click(function(){
			var bclass=$(this).attr("class");
			if(bclass=="border"){
				$(this).removeClass("border").addClass("choi");
				$('#paytype').val(2);
				$(".pay b").eq(0).removeClass("choi").addClass("border");
			}
		});
	});
</script>

