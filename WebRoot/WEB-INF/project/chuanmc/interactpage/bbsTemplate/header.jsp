<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
		<script type="text/javascript" src="/js/ueditor/ueditor.config.js"></script>
		<script type="text/javascript" src="/js/ueditor/ueditor.all.min.js"></script>
		<link rel="stylesheet" href="/js/ueditor/themes/default/css/ueditor.min.css" />
		<script type="text/javascript" charset="utf-8" src="/js/ueditor/lang/zh-cn/zh-cn.js"></script>
    	<script type="text/javascript" charset="utf-8" src="/js/ueditor/lang/en/en.js"></script>
	<script type="text/javascript">
		function bbsexit(){
			var forumid = '${forumid}'; 
			$.post("/user/bbsexit.action",function(data){
				if(data == "Y"){
					window.location.href="/user/bbsforum.action?forumid=" + forumid;
				}else {
					alert('退出失败');
				}
			});
		}
		
		function login(){
			var forumid = '${forumid}';
			var username = $('#username').val().trim();
			var password = $('#password').val().trim();
			$.post("/user/bbslogin.action",{"username":username,"password":password},function(data){
				if(data > 0){
					window.location.href="/user/bbsforum.action?forumid=" + forumid;
				}else {
					alert('用户名或密码输入有误！');
				}
			});
		}
		
		
			function hello(){
				$.post("/user/findMessage.action",function(data){
				if(data > 0){
					alert("你有未读的消息啦");	
				}
				});
			}
			
			$(document).ready(function() {
				var t2 = window.setInterval("hello()",30000);
			});
 	</script>
</head>
<body >
	<div align="center">
		<s:if test="#session.visitUser.bbsUser.id >0"> 
			欢迎您：${sessionScope.visitUser.bbsUser.username}
			【级别：${sessionScope.visitUser.bbsUser.level_name}】
			<a href="/user/bbsUserCenter.action?forumid=${forumid}">【用户中心】</a>
			<a href="javascript:void(0);" onclick="bbsexit()">【退出】</a>
			<a href="/user/bbsforum.action?forumid=${forumid}">【首页】</a>
			<a href="/user/messageCenter.action?forumid=${forumid}">【消息中心】</a>
		</s:if>
		<s:else>
			<form>
				<input type="text" name="username" id="username"/>
				<input type="password" name="password" id="password"/>
				<input type="button" value="登录" onclick="login()"/> 
				<a href="/user/bbsregister.action?forumid=${forumid}">注册</a>
			</form>
		</s:else>
		
	</div>