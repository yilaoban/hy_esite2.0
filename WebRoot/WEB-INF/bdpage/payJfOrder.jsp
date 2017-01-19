<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="/css/shop/ydyx.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	function payMoney(){
		$.post("/${oname}/user/payjf.action",{"payOrderid":"${payOrder.id}"},function(data){
			if(data.status == -1){
				$.alert(data.hydesc);
			}else if(data.status == 1){
				$('#main1').hide();$('#main2').show();
			}
		});
	}
	
</script>
<div class="top">
	<a href="javascript:window.history.back()" class="left"><img src="/images/shop/back.png" />&nbsp;返回</a>确认支付　
	<a href="#" class="right"></a>
</div>
<div style="width:100%;height:44px;"></div>
		<div id="main1" class="main" <s:if test='payOrder.status != "DFK"'>style="display: none;"</s:if>>
	        <ul class="one">
	             <li>
	             	 <p class="seventhn"><em>共计:${payOrder.realprice}积分</em></p>
	             </li>
	        </ul>
			<div class="bottom2">
				<a href="javascript:void(0);" onclick="payMoney()" class="zengjia">确认支付<span>${payOrder.realprice}积分</span></a>
			</div>
		</div>
	
	<div id="main2" class="main" <s:if test='payOrder.status == "DFK"'>style="display: none;"</s:if>>
        <div class="weui_msg">
	      <div class="weui_icon_area"><i class="weui_icon_success weui_icon_msg"></i></div>
	      <div class="weui_text_area">
	        <h2 class="weui_msg_title">支付成功</h2>
	        <p class="weui_msg_desc">恭喜您，已成功支付！</p>
	      </div>
	      <div class="weui_opr_area">
	        <p class="weui_btn_area">
	          <a href="/${oname}/user/showJfOrderList.action" class="weui_btn weui_btn_primary">确定</a>
	        </p>
	      </div>
	    </div>
	</div>
