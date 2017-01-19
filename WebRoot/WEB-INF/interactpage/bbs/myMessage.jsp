<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
	function myReply(receiver,messageid){
		var content = prompt("回复内容","");
		content = content.trim();
		if(content == "" || content == null){
			alert("内容不能为空");
			return; 
		}
		if(content!="" && content != null){
			$.post("/user/saveMessagereply.action",{"content":content,"receiver":receiver,"messageid":messageid},function(data){
				if(data > 0){
					alert(发送消息成功);					
				}
			});
		}
	}
</script>
<s:if test="#session.visitUser.bbsUser.id >0"> 
<div align="center"> 
	<s:iterator value="dto.messagList" var="m">
		<table>
			<thead>
				<tr>
					<th>sender</th>
					<th>内容</th>
					<th>状态</th>
					<th>操作</th>
			</thead>
			<tbody>
				<tr>
					<th>${m.sendername}</th>
					<th>${m.content}</th>
					<th><s:if test="#m.is_read == 0">未读</s:if><s:else>已读</s:else></th>
					<th><a href="javascript:void(0)" onclick="myReply(${m.sender},${m.id})">回复</a></th>
				</tr>
			</tbody>
		</table>
	</s:iterator>	
</div>
</s:if>