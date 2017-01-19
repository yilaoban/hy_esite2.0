<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
	<form action="" method="post" id="myform1" class="formview jNice">
		<input type="hidden" name="topicText.content" id="content1">
		<input type="hidden" value="${forumid}" name="topic.FORUM_ID"> 
		<input type="hidden" value="${forumer}" name="topic.CREATER_UID">
		<input type="hidden" value="${topicid}" name="topic.id"> 
		<dl>
			<dt>标题</dt>
			<dd>
				<input type="text" name="topic.TITLE" class="text-medium" id="title1" value="${dto.topic.TITLE}"/>
			</dd>
		</dl> 
		<dl>
			<dt>内容</dt>
			<dd>
				<script type="text/plain" id="myEditor1" style="width:400px;height:200px;">${dto.topic.content}</script>
			</dd>
		</dl> 
		<dl>
			<dt></dt>
			<dd>
				  <button type="button" class="btn btn-primary" onclick="createTopic()" >保存</button>
				  <button type="button" class="btn btn-default" onclick="closeFrame()">关闭</button>
			</dd>
		</dl> 
	</form>
<script type="text/javascript">
	//实例化编辑器
    var um1 = UE.getEditor('myEditor1');
	um1.addListener('ready', function(editor) {
		$(".edui-btn-toolbar").css("white-space","normal");
	});
	function createTopic(){
		var title = $('#title1').val().trim();
		if(title == ""){
			alert('标题不能为空');
			return;
		}
		if(!um1.hasContents()){
			alert('内容不能为空');
			return;
		}
		var content = um1.getContent();
		$('#content1').val(content);
		$.ajax({
			cache: true,
			type: "POST",
			url:'/${oname}/bbs/updateTopic.action',
			data:$('#myform1').serialize(),// 你的formid
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