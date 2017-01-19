<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
	function addTopic(){
		var userid = '${sessionScope.visitUser.bbsUser.id}';
		var forumid = '${forumid}';
		if(userid > 0){
			window.location.href= "/user/addTopic.action?forumid=" + forumid;
		}else{
			window.location.href= "/user/bbsforum.action?forumid=" + forumid;
		}
	}
	
</script>
	<s:if test="#session.visitUser.bbsUser.id >0"> 
		<input type="button" value="发帖" onclick="addTopic()">
	</s:if>
	<table align="center">
		<thead>
			<tr>
				<th>标题</th>
				<th>作者</th>
				<th>回复/人气</th>
				<th>最后发表</th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="dto.topicList" var="t"> 
			<tr>
				<th><a href="/user/topicPostInfo.action?forumid=${forumid}&topicid=${t.id}">${t.TITLE }</a></th>
				<th>${t.creater } <br/> <s:date name="#t.CREATE_TIME" format="yyyy-MM-dd HH:mm:ss"/></th>
				<th>${t.REPLY_COUNT}/${t.VIEW_COUNT }</th>
				<th><s:date name="#t.LAST_TIME" format="yyyy-MM-dd HH:mm:ss"/> <br/> by:${t.replyer}</th>
			</tr>
		</s:iterator>	
		</tbody>
	</table>