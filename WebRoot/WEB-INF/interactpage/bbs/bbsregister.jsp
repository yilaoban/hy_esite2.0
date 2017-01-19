<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/js/easyDialog/easydialog.min.js"></script>
		<link href="/js/easyDialog/easydialog.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function bbsregister() {
	var forumid = '${forumid}';
	var telphone = $('#telphone').val().trim();
	var code = $('#code').val().trim();
	var password = $('#password').val().trim();
	var nickname = $('#nickname').val().trim();
	var img = '${sessionScope.visitUser.wxUser.headimgurl}';
	if(telphone=="" || password=="" || nickname=="" || code == "") {
		alertMsg("请填写完整的信息！");
		return;
	}
	if(!checkStyle()){
		return;
	}
	if(!checkPassword()){
		return;
	}
	$.post("/${oname}/user/bbs/findBBSUserByName.action",{"username":telphone},function(data){
		if(data > 0){
			alertMsg("该手机号已注册，去登录");
		 	setInterval(function(){
				window.location.href = "/${oname}/user/bbs/toLogin.action";
			},2000);
			return;
		}else{
			$("#error").html("");
			$.post("/${oname}/user/bbs/doRegister.action",{"username":telphone,"password":password,"code":code,"bbsUser.telphone":telphone,"bbsUser.nickname":nickname,"bbsUser.img":img},function(data){
				if(data == "Y"){
					alertMsg("注册成功！");
					var pageid = '${sessionScope.visitUser.pageid }';
					var source = '${sessionScope.visitUser.source }';
					var oname = '${oname}';
					var url = "/" + oname + "/user/show/" + pageid+ "/" + source + ".html";
					window.location.href = url;
				}else if(data == "N"){
					alertMsg("注册失败！");
				}else{
					alert(data);
				}
			});
		}
	});
}

function checkPassword(){
	var password = $('#password').val().trim();
	var password1 = $('#password1').val().trim();
	if(password != password1){
		$("#error").html("密码输入不一致，请重新输入").css("color", "RED");
		return false;
	}else{
		$("#error").html("");
		return true;
	}
}

function checkStyle(){
	var re = /^[0-9_a-zA-Z]{6,20}$/;
	if(!(re.test($("#password").val()))){
		$("#error").html("长度在6~18之间，只能包含字母、数字和下划线").css("color", "RED");
		return false;
	}else{
		$("#error").html("");
		return true;
	}
}
function alertMsg(msg){
			easyDialog.open({
				  container : {
				    content : msg
				  },
				  autoClose : 2000
				});
		}
$(function(){
    var getCodeBtn = $('#get_code');//获取验证码按钮
    //获取短信验证码的方法
    var sendPhoneCode = function(){
	   var telphone = $('#telphone').val();//获取手机号码
    	if(telphone == ""){
    		alertMsg("请输入手机号码");
    		return;
    	}
    	var oname = '${oname}';
    	$.post("/${oname}/user/bbs/findBBSUserByName.action",{"username":telphone},function(data){
			if(data > 0){
				alertMsg("该手机号已注册，去登录");
			 	setInterval(function(){
					window.location.href = "/${oname}/user/bbs/toLogin.action";
				},2000);
				return;
			}else{
				$.post("/${oname}/user/bbs/getCode.action",{"bbsUser.telphone":telphone},function(data){
					if(data == "Y"){
						alertMsg("发送成功，请稍后");
			    		reSendCountdown();
		    		}else{
		    			alertMsg("获取验证码失败");
		    		}
				});
			}
    	});
    }
     
    //60s后重新发送手机验证码
    var reSendCountdown = function(){
	        var count = 60;//禁用时间为60秒
	        getCodeBtn.attr("disabled", true);
	        var resend = setInterval(function(){
	        count--;
	        if (count > 0){
	            $('#showtime').html(count+"秒后点击重新发送").css("color", "RED");
	        }else {
		        clearInterval(resend);//清除计时
		        getCodeBtn.attr("disabled", false);
		        $('#showtime').html("如果没有收到短信，请点击重新发送！").css("color", "RED");
	        }
	    }, 1000);
    }
    //点击按钮触发获取短信验证码事件
    getCodeBtn.click(sendPhoneCode);
});

function nextStep(){
	var telphone = $('#telphone').val().trim();
	var validCode = $('#code').val().trim();
	if( telphone=="") {
		alertMsg("请填写手机号码");
		return;
	}
	if(validCode ==""){
		alertMsg("请输入验证码！");
		return;
	}
	$('.step2').show();$('.step1').hide();
}

</script>
</head>
<body>
  <div align="center">
	<form>
		<h1>用户注册</h1>
		<div class="step1">
			<p>
		    	手机号码：<input type="text" name="bbsUser.telphone" id="telphone" placeholder="手机号(手机号就是登陆用户名)" onkeyup="this.value = this.value.replace(/\D/g,'');">
	    	</p>
	    	<p>
	    		<input class="btn btn-lg btn-primary btn-block" type="button" value="获取验证码"  id="get_code" />
	    	</p>
	    	<p>
	    	  	验证码:<input type="text" id="code" placeholder="请输入验证码" >
	    	</p>
	    	<p>
	    	<span id="showtime"></span>
	    	</p>
		    <input class="btn btn-lg btn-primary btn-block" type="button" value="下一步"  onclick="nextStep()"/>
		</div>
		<div style="display: none" class="step2"> 
			<input class="btn btn-lg btn-primary btn-block" type="button" value="上一步"  onclick="$('.step1').show();$('.step2').hide()"/>
			<p>
	    		昵称:<input type="text" id="nickname" placeholder="请输入昵称" value="${sessionScope.visitUser.wxUser.nickname}">
	    	</p>
	    	<p>
	       		密码：<input type="password" name="bbsUser.password" id="password" placeholder="密码" onblur="checkStyle()">
	       	</p>
	       	<p>
	       		确认密码：<input type="password" id="password1" placeholder="再次输入密码" onblur="checkPassword()">
	       	</p>
           	<span id="error"></span>
           	<p> 
	        	<input type="button" value="注册" onclick="bbsregister()"/>
       		</p>
		</div>
	</form>
  </div>
</body>
</html>