<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head lang="en">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="format-detection" content="telephone=no">
	<meta name="format-detection" content="email=no">
	<meta content="width=320, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" name="viewport" />	
    <link rel="stylesheet" type="text/css" href="/css/cashier/reset.css" />
	<link rel="stylesheet" type="text/css" href="/css/cashier/index.css" />
    <link rel="stylesheet" type="text/css" media="screen,print" href="/js/jqprint/print.css" />
	<script src="/js/jquery-1.11.1.min.js" type="text/javascript"></script>
    <script src="/js/jqprint/jquery.jqprint-0.3.js"></script>
    <script src="/js/jqprint/jquery-migrate-1.1.0.js"></script>
     <script>
     function print(){
    	 $("#print>div").jqprint();
     }
     
     $(document).ready(function(){
	     var rmb = parseFloat('${hydesc}');
	     $('#xfje').html(rmb/100);
     });
     
     function back(){
    	 window.location.href = "/${oname}/user/scan.action";
     }
     </script>
</head>
<body>
	<div class="pcs_img_bg">
		<div class="pcs_white">
		<div class="pcs_main">
			<img src="/images/cashier/finish.png" class="pcs_finish_img">
			<div class="pcs_print">
				<p>会员名:${dto.wxUser.nickname}</p>
				<p>会员卡余额：${dto.rmb/100}元</p>
				<p>消费金额：<span id="xfje"></span>元</p>
				<p>实际消费金额：<span class="jine">${dto.uw.rmb/100}</span>元</p>
			</div>
			<div class="pcs_input_bg">
				<input type="button" value="打 印" onclick="print()" style="cursor:pointer">
			</div>
			<div class="pcs_input_bg">
				<input type="button" value="返 回" style="cursor:pointer" onclick="back()">
			</div>
		</div>
		</div>
	</div>
	<div id="print" style="display:none;">${dto.vm}</div>
</body>
</html>
