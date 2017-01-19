<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
	<form action="" method="post" id="myform" class="formview jNice">
		<input type="hidden" name="topicText.content" id="content">
		<input type="hidden" value="${forumid}" name="topic.FORUM_ID"> 
		<input type="hidden" value="${forumer}" name="topic.CREATER_UID">
		<dl>
			<dt>标题</dt>
			<dd>
				<input type="text" class="text-medium" name="topic.TITLE" id="title"/>
			</dd>
		</dl> 
		<dl>
			<dt>内容</dt>
			<dd>
			<script type="text/plain" id="myEditor" style="width:400px;height:200px;"></script>
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
    var um = UE.getEditor('myEditor');
	um.addListener('ready', function(editor) {
		$(".edui-btn-toolbar").css("white-space","normal");
	});
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
		$.ajax({
			cache: true,
			type: "POST",
			url:'/${oname}/bbs/saveTopic.action',
			data:$('#myform').serialize(),// 你的formid
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