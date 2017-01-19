<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
	//实例化编辑器
    var um = UE.getEditor('myEditor');
	
	function saveReplyInfo(){
		if(!um.hasContents()){
			alert('内容不能为空');
			return;
		}
		var content = um.getContent();
		$('#content').val(content);
		$('#myform').submit();
	}
	
	$(document).ready(function() {
		var indexCount = '${indexCount}';
		var content = '回复第' +　indexCount + "楼";
		if(indexCount != 0){
			alert("请填写回复内容");
			um.setContent(content, true);
		}
		var useContent = '${useContent}';
		if(useContent != null && useContent != ""){
			useContent = "<em>" + useContent + "</em>";
			alert("请填写回复内容");
			um.setContent(useContent, true);
		}
	});
	
</script>
<div align="center">
	<s:if test="#session.visitUser.bbsUser.id >0">  
	<form action="/user/saveReplyInfo.action" method="post" id="myform"> 
		<input type="hidden" value="${topicid}" name="topic.id">
		<input type="hidden" value="${forumid}" name="topic.FORUM_ID">
		<input type="hidden" value="${typeid}" name="topic.TYPE_ID">
		<input type="hidden" value="${sessionScope.visitUser.bbsUser.id}" name="topic.CREATER_UID">
		<input type="hidden" name="topic.content" id="content">
		<h3>回复主题</h3>
		<p>标题
			<input type="text" name="topic.TITLE" id="title"/>
		</p> 
		<p>
			<script type="text/plain" id="myEditor" style="width:100%;height:200px;"></script>
		</p>
		<p><input type="button" value="提交" onclick="saveReplyInfo()"> </p>
	</form>
	</s:if>
</div>