<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
  <div align="center">
	  <a href="/user/bbsUserInfo.action?forumid=${forumid}">个人资料</a>
	  <a href="/user/bbsMyTopic.action?forumid=${forumid}">我的主题</a>
	  <a href="/user/bbsMyReply.action?forumid=${forumid}">我的回复</a>
	<form>
		<h1>个人信息</h1>
		<p>
			用户名：<input type="text" name="dto.bbsUser.username" id="username" value="${dto.bbsUser.username }">
		</p>
		<p> 	
			E-mail：<input type="text" id="email" name="dto.bbsUser.email" value="${dto.bbsUser.email }">
		</p>
       	<span id="error"></span>
	</form>
  </div>
</body>
</html>