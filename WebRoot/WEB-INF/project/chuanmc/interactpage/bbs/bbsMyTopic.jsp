<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
</script>
<div align="center"> 
	<a href="/user/bbsUserInfo.action?forumid=${forumid}">个人资料</a>
	<a href="/user/bbsMyTopic.action?forumid=${forumid}">我的主题</a>
	<a href="/user/bbsMyReply.action?forumid=${forumid}">我的回复</a>
	<table>
		<thead>
			<tr>	 	 	
				<th>标 题 </th>
				<th>版块</th>
				<th>最后发表人</th>
				<th>发布时间</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="dto.topicList" var="t">
				<tr>
					<th><a href="/user/topicPostInfo.action?forumid=${forumid}&topicid=${t.id}">${t.TITLE }</a></th>
					<th>${t.forumname }</th>
					<th>${t.replyer}</th>
					<th><s:date name="#t.CREATE_TIME" format="yyyy-MM-dd HH:mm:ss"/></th>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</div>