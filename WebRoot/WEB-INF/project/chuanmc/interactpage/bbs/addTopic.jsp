<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
	//实例化编辑器
    var um = UE.getEditor('myEditor');
	
	function createTopic(){
		var title = $('#title').val().trim();
		if(title == ""){
			alert('标题不能为空');
			return;
		}
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
	<form action="/user/saveTopic.action" method="post" id="myform"> 
		<input type="hidden" value="${forumid}" name="topic.FORUM_ID">
		<input type="hidden" value="${sessionScope.visitUser.bbsUser.id}" name="topic.CREATER_UID">
		<input type="hidden" name="topicText.content" id="content">
		<h3>发表新话题</h3>
		<p>标题
			<input type="text" name="topic.TITLE" id="title"/>
			<select name="topic.TYPE_ID">
				<s:iterator value="dto.topicTypeList" var="t">
				  <option value="${t.id}">${t.type_name}</option>
				</s:iterator>  
			</select>
		</p> 
		<p>
			<script type="text/plain" id="myEditor" style="width:100%;height:200px;"></script>
		</p>
		<!-- 
		<p>插入图片或者附件:<input type="file" name="pic"> </p>${msg}
		 -->
		<p><input type="button" value="提交" onclick="createTopic()"> </p>
	</form>
	</s:if>
</div>