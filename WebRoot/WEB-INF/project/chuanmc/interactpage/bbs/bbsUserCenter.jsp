<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
</script>
<s:if test="#session.visitUser.bbsUser.id >0"> 
<div align="center"> 
	<a href="/user/bbsUserInfo.action?forumid=${forumid}">个人资料</a>
	<a href="/user/bbsMyTopic.action?forumid=${forumid}">我的主题</a>
	<a href="/user/bbsMyReply.action?forumid=${forumid}">我的回复</a>
	<table>
		<tr>
			<td>
				${dto.bbsUser.username }(数字ID：${dto.bbsUser.id })
				<a href="/user/bbsUserInfo.action?forumid=${forumid}">编辑资料</a><br/>
				发帖：${dto.bbsUser.topicnum }
				回帖：${dto.bbsUser.replynum }
			</td>
			<td>
				<table>
					<tr>
						<td>
								会员头衔：${dto.bbsUser.level_name }
								注册时间：<s:date name="dto.bbsUser.createtime" format="yyyy-MM-dd HH:mm:ss"/>
						</td>
					</tr>
				</table>
				
			</td>
		</tr>
	</table>
</div>
</s:if>