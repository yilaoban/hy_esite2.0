<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns:wb="http://open.weibo.com/wb">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>${title}</title>
		<meta name="description" content="${description}" />  
		<meta name="keywords" content="${keywords}" />
		<script type="text/javascript" src="/js/jquery-1.8.2.min.js"></script>
		<!--[if IE]>
		<script type="text/javascript" src="/js/jquery.placeholder-1.0.js"></script>
		<![endif]-->
		<script type="text/javascript" src="/js/functions.js"></script>
        <style type="text/css">
        body {background:url(/images/login_bg.png) repeat 0 0;width:100%;height:100%;margin:0;padding:0;}
        #loginbg {position:relative;background:url(/images/big_bg.png) no-repeat;margin:0 auto;margin-top:80px;width:1000px;height:419px;}
        #loginbox {position:absolute;top:70px;right:54px;padding:30px;font-size:11px;}
        .inputdiv {padding-bottom:20px;}
        input[type="text"],input[type="password"] {border:1px solid #e6e5e3;line-height:20px;width:165px;height:20px;padding:5px 10px;border-radius:2px;font-family:Tahoma,Helvetica,"Microsoft Yahei","微软雅黑",Arial;}
        input[type="submit"] {border:none;background:url(/images/login_button.png) no-repeat;height:32px;width:170px;}
        </style>
		
		<script type="text/javascript">
       document.onkeydown=function(event){ 
			e = event ? event :(window.event ? window.event : null); 
			if(e.keyCode==13){ 
				checkMsg(document.getElementById("loginForm"));
			} 
		} 
	 
		function checkMsg(form){
		var ownerName = document.getElementById("ownerloginName");
		if(ownerName.value.length == 0){
			document.getElementById("errrSpan").innerHTML="会员名未填！";
			ownerName.focus();
			return;
		}
		else{
			document.getElementById("errrSpan").innerHTML="";
		}
		
		var accountName = document.getElementById("accountloginName");
		if(accountName.value.length == 0){
			document.getElementById("errrSpan").innerHTML="账号未填！";
			accountName.focus();
			return;
		}
		else{
			document.getElementById("errrSpan").innerHTML="";
		}
		var password = document.getElementById("loginpassword");
		if(password.value.length == 0){
			document.getElementById("errrSpan").innerHTML="密码未填！";
			password.focus();
			return;
		}
		else{
			document.getElementById("errrSpan").innerHTML="";
		}
		form.submit();
	} 
		</script>
		
    </head>
    <body>
    	<div id="loginbg">
	        <div id="loginbox">            
	            <form action="/sign.action" method="post" id="loginForm" class="form-vertical">
	            <div class="inputdiv">
					<input type="text" placeholder="会员名" name="ownerName" value="${ownerName }" id="ownerloginName" />
				</div>
				<div class="inputdiv">
					<input type="text" placeholder="账号"  name="accountName"  value="${accountName}" id="accountloginName"/>
				</div>
				<div class="inputdiv">
					<input type="password" placeholder="密码"  name="password" id="loginpassword"/>
				</div>
				<div class="inputdiv">
					<a href="#">忘记密码</a>
				</div>
				<div class="inputdiv" style="text-align:center;">
					<input type="submit" value="" class="btn btn-success btn-large" /><span id="errrSpan" style="color:red"><strong style="color:red"><s:fielderror theme="" fieldName="error"/></strong> </span>
				</div>
				<div class="inputdiv">
					想加入@marketing营销？<a href="#">立即注册</a>
				</div>
	            </form>
	        </div>
    	</div>
    </body>
</html>