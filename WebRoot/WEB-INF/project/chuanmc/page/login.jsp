<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>${hyTitle}</title>
		<meta name="description" content="${hyDescription}" />  
		<meta name="keywords" content="${hyKeywords}" />
		<script type="text/javascript" src="/js/jquery-1.8.2.min.js"></script>
		<!--[if IE]>
		<script type="text/javascript" src="/js/jquery.placeholder-1.0.js"></script>
		<![endif]-->
		<script type="text/javascript" src="/js/functions.js"></script>
        <style type="text/css">
        body {background:#F2F2F2;margin:0;padding:0;}
        a {text-decoration:none;color:#858585;}
        h2 {margin:0;font-size:16px;}
        .main-wrap {width:1000px;margin:0 auto;}
        #hd {height:80px;background:#FFFFFF;border-top:5px solid #3BAEFD;}
		#logo {float:left; margin-top:25px;}    
		#link {float: right; margin-top: 35px;font-size: 16px;text-align: right;color:#858585;}    
		#banner {}
		#bottom {margin-top:20px;}
        #foot {margin-top:20px;background:#fff;border-top:1px solid #EEEEEE;text-align:center;padding:15px 0;font-size:14px;}
        #loginbox {  position: absolute;top: 50px;left:50%;margin-left:150px;font-size:14px;color:#888;background:#fff;border-radius:4px;}
        .loginform {padding:15px 30px 30px;border-bottom:1px solid #D3D5D9;}
        .thirdpart {padding:10px 30px;}
        .inputdiv {border-color:#EAEAED;border-style:solid;border-width:1px 1px 0 1px;padding-left:40px;}
        input[type="text"],input[type="password"] {border:none;line-height:40px;height:40px;outline:none;font-size:16px;width:200px;background:none;}
        a.login, a.register {border:none;padding:10px 0;background:#0096FF;border-radius:4px;width:100%;display:inline-block;color:#fff;font-weight:700;text-align:center;font-size:16px;}
        a.register {background:#CBCBCB;}
        h2 span {border-left:3px solid #3BAEFD;padding-left:10px;}
        .block-content3 ul {padding:0 20px;}
        .block-content3 ul li {padding:5px 0;white-space:nowrap;text-overflow:ellipsis;overflow:hidden;}
		.block-content3 ul li a {color:#90a4ae;font-size:14px;}
		.slick .frame {position:relative;}
		.slick p.title {white-space:nowrap;text-overflow:ellipsis;overflow:hidden;text-align:center;font-size:14px;}
		.slick .qrcode {position:absolute;top:178px;left:0;right:0;height:108px;opacity:0.8;}
		.slick .qrcode p {margin:0;padding:10px 0;background:#fff;}
		.slick .qrcode img {width:50%;margin-left:25%;}
		.slider .slick-slide {margin:0 !important;}
        </style>
    </head>
    <body>
	    <div id="hd">
	    	<div class="main-wrap">
				<div id="logo"><img src="/project/chuanmc/img/logo.png" style="width:250px;"></div>
		    </div>
		</div>
		<div id="banner">
			<div class="main-wrap" style="position:relative;">
				<div class="slider">
					<div class="frame" style="height:402px;background:url(/project/chuanmc/img/DMCbanner1.png) center center;"></div>
				</div>
			        <div id="loginbox">      
			        	<div class="loginform">
			        		<h3 style="margin:10px 0;">请登录</h3>
				            <s:form method="post" action="sign" id="myform">
				            <div class="inputdiv" style="background:url(/project/chuanmc/img/mmb.png) no-repeat 12px 10px;">
								<input type="text" placeholder="会员名" name="ownername" value="${ownerName }" id="ownerloginName" />
							</div>
							<div class="inputdiv" style="background:url(/project/chuanmc/img/id.png) no-repeat 12px 14px;">
								<input type="text" placeholder="用户名"  name="accountname"  value="${accountName}" id="accountloginName"/>
							</div>
							<div class="inputdiv" style="background:url(/project/chuanmc/img/pwd.png) no-repeat 14px 10px;border-bottom:1px solid #eaeaed;">
								<input type="password" placeholder="密码"  name="password" id="loginpassword"/>
							</div>
							   <span style="color:red;" id="errrSpan">${msg }</span>
							<div class="inputdiv" style="padding:0;border:none;margin-top:15px;">
								<a class="login" href="javascript:void(0);" onclick="checkMsg()"/>登录</a>
							</div>
				            </s:form>
			            </div>
			        </div>
			        </div>
		</div>
		<script>
		function checkMsg(){
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
			$("#myform").submit();
		} 
		</script>
    </body>
</html>
