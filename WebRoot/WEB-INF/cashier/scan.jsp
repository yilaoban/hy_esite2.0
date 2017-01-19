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
	<script type="text/javascript" src="/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript">
	function exit(){
		$.post("/${oname}/user/cashierexit.action",function(data){
			if(data == "Y"){
				window.location.href = "/${oname}/user/cashierlogin.action";
			}
		});
	}
	
	$(document).ready(function(){
		$('#scan').click();$('#scan').focus();
		 $('#scan').bind('keypress',function(event){
            if(event.keyCode == "13")    
            {
				window.location.href = $('#scan').val();
            }
        });

	});

	function fun(obj){
		obj.value = "";
		obj.type = "password";
	}
	
	function show(){
		var type = $('#scan').attr("type");
		if(type == "password"){
			$('#scan').attr("type","text");
		}else{
			$('#scan').attr("type","password");
		}
	}
</script>
</head>
<body>
	<div class="pcs_img_bg">
		<ul class="pcs_tab">
			<li>
				<a href="javascript:void(0)" onclick="exit()" style="color: white;display: block;">退出登录</a>
			</li>
			<li>
				<a href="/${oname}/user/userBill.action" style="color: white;display: block;">进入账单</a>
			</li>
		</ul>
		<div class="pcs_white">
		<div class="pcs_main h90">
			<div class="h50">
				<img src="/images/cashier/scan_big.png" class="pcs_scan_big">
			</div>
			<div class="pcs_input">
				<input type="text" id="scan" placeholder="请输入链接" onclick="fun(this)" onblur="$('#scan').focus();">
			</div>
			<div class="pcs_dscp">
				将扫描枪对准手机二维码，系统会自动识别会员身份！
			</div>
		</div>
		</div>
	</div>
</body>
</html>

