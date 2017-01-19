<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!DOCTYPE html>
<html>
<head lang="en">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="format-detection" content="telephone=no">
	<meta name="format-detection" content="email=no">
	<meta content="width=320, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" name="viewport" />	
	<link href="https://res.wx.qq.com/open/libs/weui/0.4.0/weui.css" rel="stylesheet" />
	<link href="/js/jquery-weui/css/jquery-weui.css" rel="stylesheet" />
	<script src="/js/jquery-1.11.1.min.js" type="text/javascript"></script>
	<script src="/js/jquery-weui/js/jquery-weui.js" type="text/javascript"></script>
	 <link rel="stylesheet" type="text/css" href="/css/cashier/reset.css" />
	<link rel="stylesheet" type="text/css" href="/css/cashier/index.css" />
	<script type="text/javascript">
	function login() {
	  var username = $('#username').val().trim();
	  var password = $('#password').val().trim();
	  var url = '/${oname}/user/cashierdologin.action';
	  $.post(url,{"username":username,"password":password},function(data){
	   if(data == 0){
		   $.alert("用户名或密码输入有误！","");
	   }else if(data == -1){
		   $.alert("您没有收营员权限！","");
	   }else {
	    window.location.href="/${oname}/user/scan.action";
	   }
	  });
	}
	
	$(function(){ 
		document.onkeydown = function(event){
		  switch(event.keyCode) {
		    case 13:
		      if (!window.buttonIsFocused) login();
		      break; // enter 键
		  }
		};
	})
</script>
</head>
<body>
	<div class="pcs_img_bg">
		<div class="pcs_main">
			<img src="/images/cashier/login_img.png" class="pcs_login_img">
			<div class="pcs_input_bg pcs_input_bg1">
				<input type="text" placeholder="点此输入账号" id="username">
			</div>
			<div class="pcs_input_bg pcs_input_bg2">
				<input type="password" placeholder="点此输入密码" id="password">
			</div>
			<div class="pcs_input_bg">
				<input type="button" value="登 录" onclick="login()" onfocus="window.buttonIsFocused=true;" onblur="window.buttonIsFocused=false;">
			</div>
		</div>
	</div>
</body>
</html>
