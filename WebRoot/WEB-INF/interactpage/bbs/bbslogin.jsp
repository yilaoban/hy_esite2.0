<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
function bbslogin() {
		var forumid = '${forumid}';
		var username = $('#username').val().trim();
		var password = $('#password').val().trim();
		var oname = '${oname}';
		var url = '/' + oname + '/user/bbs/login.action';
		$.post(url,{"username":username,"password":password},function(data){
			if(data == 0){
				alert('用户名或密码输入有误！');
			}else {
				var pageid = '${sessionScope.visitUser.pageid }';
				var source = '${sessionScope.visitUser.source }';
				if(source==''){
				window.location.href = '/'+ oname +'/user/show/'+pageid+'.html';
				}else{
				window.location.href = '/'+ oname +'/user/show/'+pageid+ '/' + source + '.html';
				}
			}
		});
}
</script>
</head>
<body>
  <div align="center">
	<form>
		<h1>用户登录</h1>
		<p>
			用户名：<input type="text" name="bbsUser.username" id="username" >
		</p>
		<p>	
			密码：<input type="password" name="bbsUser.password" id="password" >
		</p>
       	<p> 
	        <input type="button" value="登录" onclick="bbslogin()"/>
	        <a href="/${oname}/user/bbs/register.action?forumid=${forumid}">去注册</a>
       	</p>
	</form>
  </div>
</body>
</html>