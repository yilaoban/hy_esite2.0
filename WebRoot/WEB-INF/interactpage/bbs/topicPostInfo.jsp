<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
	$(document).ready(function() {
		var forumid = '${forumid}';
		var topicid = '${topicid}';
		$.post("/user/addViewCount.action",{"topicid":topicid});
	});

	function myFunction(){
		var userid = '${sessionScope.visitUser.bbsUser.id}';
		var topicid = '${topicid}';
		var forumid = '${forumid}';
		if(userid > 0){
			var reason = prompt("请输入举报理由","");
			reason = reason.trim();
			if(reason == "" || reason == null){
				alert("内容不能为空");
				return; 
			}
			if(reason!="" && reason != null){
				$.post("/user/savetopicReport.action",{"topicid":topicid,"forumid":forumid,"reason":reason});
			}
		}else{
			window.location.href="user/topicPostInfo.action?forumid=" + forumid + "&topicid=" + topicid;
		}
	}
	
	function myCall(receiver){
		var userid = '${sessionScope.visitUser.bbsUser.id}';
		var topicid = '${topicid}';
		var forumid = '${forumid}';
		if(userid > 0){
			var content = prompt("打个招呼","");
			content = content.trim();
			if(content == "" || content == null){
				alert("内容不能为空");
				return; 
			}
			if(content!="" && content != null){
				$.post("/user/saveMessage.action",{"content":content,"receiver":receiver});
			}
		}else {
			window.location.href="user/topicPostInfo.action?forumid=" + forumid + "&topicid=" + topicid;
		}
	}
	
	function useContent(index){
		var useContent = $('#content_' + index).html();
		var topicid = '${topicid}';
		var forumid = '${forumid}';
		var typeid = '${dto.topic.TYPE_ID}';
		window.location.href = "/user/replyAuthor.action?topicid=" + topicid + "&forumid=" + forumid + "&typeid=" + typeid + "&useContent=" + useContent;
	}
	
</script>

<div align="center">
	<h3>主题：${dto.topic.TITLE}</h3>
<s:iterator value="dto.topicList" var="t" status="st">
	<table>
		<tr>
			<td>
				${t.creater}<br/>
				级别：${t.level_name }<br/>
				UID：${t.CREATER_UID} 
				<s:if test="#t.CREATER_UID != #session.visitUser.bbsUser.id">
					<a href="javascript:void(0)" onclick="myCall(${t.CREATER_UID})">打招呼</a>  
				</s:if><br/>
				注册时间：<s:date name="#t.createtime" format="yyyy-MM-dd"/><br/>
				在线时长:${ t.minute}分钟<br/>
				主题：${t.topicnum}<br/>
				回复：${ t.replynum}
			</td>
			<td>
				<table>
					<thead>
						<tr>
							<td>
								${t.INDEX_COUNT }#发表于：<s:date name="#t.CREATE_TIME" format="yyyy-MM-dd HH:mm:ss"/>
									<a href="/user/topicPostInfo.action?topicid=${topicid }&forumid=${forumid}">显示全部</a>
									<a href="/user/personalTopicPost.action?topicid=${topicid }&forumid=${forumid}&userid=${t.CREATER_UID}">只看该作者</a>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>
								<span id="content_${st.count}">	${t.content} </span>
							</td>
						</tr>
					</tbody>
					<s:if test="#session.visitUser.bbsUser.id >0"> 
					<tfoot>
						<tr >
							<td >
								<s:if test="#t.CREATER_UID == #session.visitUser.bbsUser.id">
									<a href="/user/editContent.action?topicid=${topicid}&forumid=${forumid}&tid=${t.id}&postid=${t.postid }">编辑</a>
								</s:if>
								<a href="javascript:void(0)" onclick="useContent(${st.count })">引用</a> 
								<a href="/user/replyAuthor.action?topicid=${topicid}&forumid=${forumid}&typeid=${dto.topic.TYPE_ID}&indexCount=${t.INDEX_COUNT}">回复</a>
							</td>
							<td align="right">
								<a href="javascript:void(0)" onclick="myFunction()">举报</a> 
							</td>
							
						</tr>
					</tfoot>
					</s:if>
				</table>
			</td>
		</tr>
	</table>
</s:iterator>
	<s:if test="#session.visitUser.bbsUser.id >0"> 
		<a href="/user/replyAuthor.action?topicid=${topicid}&forumid=${forumid}&typeid=${dto.topic.TYPE_ID }">回复</a>
	</s:if>
</div>
 
