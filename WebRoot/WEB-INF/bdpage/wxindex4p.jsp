<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="/css/liebiao/14/list.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function payMoney() {
		reSendCountdown();
		wxPay();//微信支付
	}
	
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
					"payorderids":"${str}",
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
									window.location.href = "/${oname}/user/wxpayCallBack.action?out_trade_no=" + data.out_trade_no + "&redirectUrl=${redirectUrl}&str=${str}" ;
								}
							});
						} else {
							alert("支付失败:" + data.return_msg + "-" + data.err_code_des);
						}
					} else {
						alert("支付失败:" + data.return_msg);
					}
				},
				error : function(err) {
					alert(err);
				}
			});
		}else{
			alertMsg("购买成功");
			setInterval(function(){
				window.location.href = "/${oname}/user/callBack.action?out_trade_no=${payrecordid }&redirectUrl=${redirectUrl}&str=${str}" ;
			},2000);
		}

	}
	var reSendCountdown = function() {
		var count = 5;//禁用时间
		$('#paymoney').attr("disabled", true);
		var resend = setInterval(function() {
			count--;
			if (count == 0) {
				clearInterval(resend);//清除计时
				$('#paymoney').attr("disabled", false);
			}
		}, 1000);
	}
	function alertMsg(msg) {
		easyDialog.open({
			container : {
				content : msg
			},
			autoClose : 2000
		});
	}
	
</script>
	<s:if test='dto.product.type != "J"'>
	<div class="page-head">
		<h4>确认支付</h4>
	</div>
	<div class="chooseAddr">
		<s:iterator value="dtoList" status="st" var="d">
				<p>
					订单编号：${d.payOrderid }
				</p>
				<p>
					商品：${d.product.name }
				</p>
				<p>
					数量：${d.quantity }
				</p>
				<p>
					单价：￥${d.product.salesprice }
				</p>
		</s:iterator>
		<p style="margin-top:10px;text-align:right;font-size:16px;">
			共计<span style="color:#FF6634;">￥${totalPrice/100 }</span>
		</p>
			<input size="30" name="out_trade_no" value="${payrecordid }" id="out_trade_no" type="hidden" />
			<input size="30" name="subject" value="${subject}"  type="hidden" />
			<input size="30" name="total_fee" value="${totalPrice}" type="hidden" />
			<p style="margin-top:10px;">
				付款方式:
			</p>
					<div id="weixin">
					<input type="radio" name="paytype" id="radio-choice-v-2a" value="0" checked="checked">
					<label for="radio-choice-v-2a" id="wx" class="ui-radio-on">微信支付</label>
					</div>
			<p style="margin-top:10px;">
				<input type="button" value="去支付" onclick="payMoney()" id="paymoney"  class="btn btn-primary btn-lg btn-block" />
			</p>
	</div>
	</s:if>
	<s:else>
		<div class="page-head">
			<h4>确认支付</h4>
			<a href="${redirectUrl}" class="back"><</a>
		</div>
		<div class="chooseAddr" style="font-size:16px;text-align:center;">
				成功购买${dto.product.name }${dto.quantity }件<br>
					消耗了${dto.totalPrice}积分
		</div>
		<div class="wdy_bottom2_fixedmenu">
			<ul>
				<li class="active"><a href="/${oname}/user/payJfHome.action"><span class="home"></span>首页</a></li>
				<li class=""><a href="/${oname }/user/payJfUser.action"><span class="grcenter"></span>个人中心</a></li>
				<li class=""><a href="/${oname}/user/showJfOrderList.action"><span class="myorder"></span>我的订单</a></li>
			</ul>
		</div>
	</s:else>
