<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
	//实例化编辑器
    var um = UE.getEditor('myEditor');
	
	function createTopic(){
		if(!um.hasContents()){
			alert('内容不能为空');
			return;
		}
		var content = um.getContent();
		$('#content').val(content);
		$('#myform').submit();
	}
	
</script>
<div align="center"> 
<s:if test="#session.visitUser.bbsUser.id >0"> 
	<form action="/user/updateTopic.action" method="post" id="myform"> 
		<input type="hidden" value="${forumid}" name="forumid">
		<input type="hidden" value="${topicid}" name="topicid">
		<input type="hidden" value="${tid}" name="tid">
		<input type="hidden" value="${postid}" name="postid">
		<input type="hidden" name="topic.content" id="content">
		<h3>编辑</h3>
		<p>标题
			<input type="text" name="topic.TITLE" id="title" value="${dto.topic.TITLE}"/>
		</p> 
		<p>
			<script type="text/plain" id="myEditor" style="width:100%;height:200px;">${dto.topic.content}</script>
		</p>
		<p><input type="button" value="提交" onclick="createTopic()"> </p>
	</form>
</s:if>
</div>