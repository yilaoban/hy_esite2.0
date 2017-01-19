<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	function addUser(){
		var username = $('#username').val().trim();
		var password = $('#password').val().trim();
		var nickname = $('#nickname').val().trim();
		var telphone = $('#telphone').val().trim();
		var email = $('#email').val().trim();
		if(username == ""){
			$("#username_").html("用户名不能为空").css("color", "RED");
			return;	
		}
		if(password == ""){
			$("#password_").html("密码不能为空").css("color", "RED");
			return;
		}
		$.post("/${oname}/bbs/addUser.action",{"username":username,"password":password,"bbsUser.nickname":nickname,"bbsUser.telphone":telphone,"bbsUser.email":email},function(data){
			if(data == 1){
				window.parent.location.reload();
			}else if(data == -1){
				alert("账户名密码重复");
			}
		});
		
	}
</script>
	<div class="formview jNice">
		<dl>
			<dt>用户名：</dt>
			<dd><input type="text" class="text-medium" name="username" id="username"/><span id="username_" class="must">*</span></dd>
		</dl>
		<dl>
			<dt>密码：</dt>
			<dd><input type="password" class="text-medium" name="password" id="password"/><span id="password_" class="must">*</span></dd>
		</dl>
		<dl>
			<dt>昵称：</dt>
			<dd><input type="text" class="text-medium" name="bbsUser.nickname" id="nickname"/></dd>
		</dl>
		<dl>
			<dt>电话：</dt>
			<dd><input type="text" class="text-medium" name="bbsUser.telphone" id="telphone" onkeyup="this.value = this.value.replace(/\D/g,'');"/></dd>
		</dl>
		<dl>
			<dt>邮箱：</dt>
			<dd><input type="text" class="text-medium" name="bbsUser.email" id="email"/></dd>
		</dl>
		<dl>
			<dt></dt>
			<dd>
				  <button type="button" class="btn btn-primary" onclick="addUser()">保存</button>
				  <button type="button" class="btn btn-default" onclick="closeFrame()">关闭</button>
			</dd>
		</dl>
	</div>
