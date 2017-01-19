<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html >
   <head lang="en">
	    <meta charset="UTF-8">
	    <meta content="width=320, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" name="viewport" />
		<title>充值页面</title>
		<link href="/css/mycards/reset.css" rel="stylesheet" type="text/css" />
		<link href="/css/mycards/index_chongzhi.css" rel="stylesheet" type="text/css" />
		<%@include file="/WEB-INF/pageshow/wx_fx.jsp"%>
		<link href="https://res.wx.qq.com/open/libs/weui/0.4.0/weui.css" rel="stylesheet"/>
		<link href="/js/jquery-weui/css/jquery-weui.css" rel="stylesheet"/>
		<script src="/js/jquery-weui/js/jquery-weui.js" type="text/javascript"></script>
		<script type="text/javascript">
			function wxPay() {
				var total_fee = $("input[name='total_fee']").val();
					$.ajax({
						url : "/${oname}/user/wxBalancePayPrePay.action",
						type : "post",
						data : {
							"body" : $("input[name='subject']").val(),
							"out_trade_no" : $("input[name='out_trade_no']").val(),
							"total_fee" : total_fee,
							"attach":$('#attach').val(),
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
												$.showLoading("正在充值中...");
												var payid = data.out_trade_no;
												setInterval(function(){
											  		$.post("/${oname}/user/findBalancePayById.action",{"payid":payid},function(data){
											  			if(data.status == 1){
											  				$.hideLoading();
											  				$.toast("操作成功");
													  		setTimeout(function() {
													  			window.location.href = "/${oname}/user/payBalanceCallBack.action?price=${rule.rmb}";
													        }, 1500);
											  			}
											  		});
												},2000);
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
				}
		</script>
		</head>
		<body>
		<div class="zhifuje">
			<form>
			<input size="30" name="out_trade_no" value="${payid}" id="out_trade_no" type="hidden" />
			<input size="30" name="subject" value="充值"  type="hidden" />
			<input size="30" name="total_fee" value="${rule.rmb}" type="hidden" />
			<input type="hidden" id="attach" value="${ruleid}">
			<h1>支付金额(元)</h1>
			<span>${rule.rmb/100 }</span>
		    <h2>到账金额<i><b>${(rule.rmb + rule.getrmb)/100 }</b>元</i></h2>
		    <em><input type="button" value="立即充值" onclick="wxPay()" style="display: block; width: 100%;height: 100%;"></em>
		    </form>
		</div>
		</body>
		</html>