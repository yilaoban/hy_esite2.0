<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div align="center">
	<form action="" method="post" id="myReplyform"> 
		<input type="hidden" value="${topicid}" name="topic.id">
		<input type="hidden" value="${forumid}" name="topic.FORUM_ID">
		<input type="hidden" value="${forumer}" name="topic.CREATER_UID">
		<input type="hidden" name="topic.content" id="content1">
		<p>标题
			<input type="text" name="topic.TITLE" id="title" placeholder="此标题可以不填"/>
		</p> 
		<p>
			<s:if test="indexCount == 0">
				<script type="text/plain" id="myEditor1" style="width:470px;height:200px;">回复楼主:</script>
			</s:if>
			<s:else>
				<script type="text/plain" id="myEditor1" style="width:470px;height:200px;">回复第${indexCount}楼:</script>
			</s:else>
		</p>
		<p><input type="button" value="提交" onclick="saveReplyInfo()"> </p>
	</form>
</div>
<script type="text/javascript">
	//实例化编辑器
    var um1 = UE.getEditor('myEditor1');
	um1.addListener('ready', function(editor) {
		$(".edui-btn-toolbar").css("white-space","normal");
	});
	
	function saveReplyInfo(){
		if(!um1.hasContents()){
			alert('内容不能为空');
			return;
		}
		var content = um1.getContent();
		$('#content1').val(content);
		$.ajax({
			cache: true,
			type: "POST",
			url:'/${oname}/bbs/saveBBSReply.action',
			data:$('#myReplyform').serialize(),// 你的formid
			async: false,
			error: function(request) {
				layer.alert("Connection error",0);
			},
			success: function(data) {
				if(data > 0){
					layer.msg('保存成功！请稍等……', {icon: 6, time: 1500}, function(){
						window.parent.location.reload();
					}); 
				}else{
					layer.alert("操作失败!",0);
				}
			}
		});
		
	}
	

	
</script>