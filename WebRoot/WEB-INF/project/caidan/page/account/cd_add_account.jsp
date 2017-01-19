<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
$(document).ready(function(){
	$("#saveBtn").click(function(){
		var username=$("#username").val();
		var password1=$("#password1").val();
		var password2=$("#password2").val();
		var phone=$("#phone").val();
		if(username==""){
			$("#username").next("span").text("帐号不能为空!");
			return;
		}else{
			$("#username").next("span").text("*");
		}
		if(password1==""){
			$("#password1").next("span").text("密码不能为空!");
			return;
		}else{
			$("#password1").next("span").text("*");
		}
		if(password2==""){
			$("#password2").next("span").text("确认密码不能为空!");
			return;
		}else{
			$("#password2").next("span").text("*");
		}
		if(password1!==password2){
			$("#password2").next("span").text("确认密码与密码不一致!");
			return;
		}else{
			$("#password2").next("span").text("*");
		}
		if(phone!=""){
			if (isNaN(phone) || phone.length != 11) {
				$("#phone").next("span").text("手机号格式不对");
				return;
			}
		}else{
			$("#phone").next("span").text("");
		}
		$("#form1").submit();
	});
});
</script>
<div class="wrap_content left_module">
	<form action="/${oname }/cd-account/saveAccount.action" id="form1" class="formview jNice" method="post" id="form1">
	<input type="hidden" name="type" value="${type }">
	<div class="toolbar pb10">
		<a href="javascript:window.history.back()" class="return" title="返回"></a>
	</div>
	<div>
		<dl>
		 	<dt>用户名</dt>
			<dd>
				<input type="text" name="act.username" id="username"/><span style="color:red">*</span>
			</dd>
		</dl>
		<dl>
		 	<dt>密码</dt>
			<dd>
				<input type="password" name="password1" id="password1"/><span style="color:red">*</span>
			</dd>
		</dl>
		<dl>
		 	<dt>确认密码</dt>
			<dd>
				<input type="password" name="password2" id="password2"/><span style="color:red">*</span>
			</dd>
		</dl>
		<dl>
		 	<dt>姓名</dt>
			<dd>
				<input type="text" name="act.fullname" id="fullname"/>
			</dd>
		</dl>
		<dl>
		 	<dt>手机号</dt>
			<dd>
				<input type="text" name="act.phone" id="phone"/><span style="color:red"></span>
			</dd>
		</dl>
		<dl>
		 	<dt>邮箱</dt>
			<dd>
				<input type="text" name="act.email" id="email"/><span style="color:red"></span>
			</dd>
		</dl>
		<dl>
		 	<dt>地址</dt>
			<dd>
				<input type="text" name="act.title" id="title"/><span style="color:red"></span>
			</dd>
		</dl>
		<dl>
		 	<dt></dt>
			<dd>
				<span style="color:red"><s:actionerror/></span>
			</dd>
		</dl>
	</div>
	<dl>
		<dt></dt>
		<dd>
			<button type="button" class="btn btn-primary" id="saveBtn">保存</button>
		</dd>
	</dl>
	</form>
</div>
