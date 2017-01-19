<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="/js/easyDialog/easydialog.min.js"></script>
<link href="/js/easyDialog/easydialog.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	$(document).ready(function(){
		$("#wideModal").on("hidden.bs.modal", function() {
			window.location.reload();
		});
	});
</script>
<div class="wrap_content">
	<div class="switch_tab_div pb10">
		<span><a href="/hy/bbs/index.action">板块管理</a><i class="gt">&gt;&gt;</i><a href="/${sessionScope.account.owner.entity}/bbs/topicIndex.action?forumid=${forumid}&forumer=${forumer}" >${dto.topic.forumname}</a><i class="gt">&gt;&gt;</i>${dto.topic.TITLE}</span>
		<span style="float:right"><a href="/${sessionScope.account.owner.entity}/bbs/topicIndex.action?forumid=${forumid}&forumer=${forumer}" class="return">返回</a></span>
		<p class="clearfix"></p>
	</div>
	<table width="100%" class="tb_special" border="1" cellspacing="1" cellpadding="1">
<s:iterator value="dto.bbsTopicList" var="t" status="st">
		<tr>
			<td width="30%">
				<p>
					<div class="com_img">
						<s:if test="#t.img != null">
						<img src="${t.img }" height="67" width="67">
						</s:if>
						<s:else>
							<img src="http://img.huiyee.com/mr.jpg" height="67" width="67">		
						</s:else>
					</div></p>
				<p class="name">${t.creater}<s:if test="#t.CREATER_UID == forumer">(版块管理员)</s:if></p>
				<p>UID：${t.CREATER_UID}</p>
				<!-- 
				<p>级别：${t.level_name }</p>
				 -->
				<p>注册时间：<s:date name="#t.createtime" format="yyyy-MM-dd"/></p>
				<%-- <p><s:if test="#t.CREATER_UID != #session.visitUser.bbsUser.id">
					<a href="javascript:void(0)" onclick="myCall(${t.CREATER_UID})">打招呼</a>  
				</s:if></p> --%>
			</td>
			<td>
				<div class="postinfo">
								<s:if test="#st.first"><p class="title">话题：${dto.topic.TITLE}</p></s:if>
								<p class="posttime"><s:if test="#st.first">楼主</s:if><s:else>${t.INDEX_COUNT}#</s:else> 发表于：<s:date name="#t.CREATE_TIME" format="yyyy-MM-dd HH:mm:ss"/>
								<s:if test='dto.commentCheck == "Y"'>
									<s:if test="!#st.first">
										<s:if test='#t.checked == "CMP"'>审核通过</s:if><s:elseif test='#t.checked == "FLD"'>审核不通过</s:elseif><s:else>待审核</s:else>
										<a style="float: right" class="btn btn-sm btn-primary" href="javaScript:void(0);" onclick="commentCheck(${t.postid},'CMP')">审核通过</a>
										<a style="float: right" class="btn btn-sm btn-primary" href="javaScript:void(0);" onclick="commentCheck(${t.postid},'FLD')">审核不通过</a>
									</s:if>
								</s:if>
								</p>
							<div class="content">
							<s:if test="#st.first">
								<s:if test="dto.topic.entityid > 0">
									<s:if test="dto.topic.entype == 2">
										<s:if test='dto.topic.islink == "Y"'>
											<span id="content_${st.count}">	${t.linkurl} </span>
										</s:if>
										<s:else>
											<span id="content_${st.count}">	${t.content} </span>
										</s:else>
									</s:if>
									<s:else>
										<img src="${imgDomain}${t.content}" height="67" width="67"/>
									</s:else>
								</s:if>
								<s:else>
									<span id="content_${st.count}">	${t.content} </span>
								</s:else>
								<p>
								<s:iterator value="dto.topic.pic_list" var="l">
									<img src="${imgDomain}${l}" height="67" width="67">
								</s:iterator>
								</p>
							</s:if>
							<s:else>
								<span id="content_${st.count}">	${t.content} </span>
							</s:else>
							</div>
							<div class="operation">
							<s:if test="#t.CREATER_UID != forumer">
								<a class="btn btn-sm btn-primary" href="javaScript:void(0);" onclick="bbsReplyTopic(${topicid},${forumid},${forumer},${t.INDEX_COUNT})">回复</a>
							</s:if>
							<s:if test="!#st.first">
								<a class="btn btn-sm btn-primary" href="javaScript:void(0);" onclick="delReplyTopic(${topicid},${t.postid})">删除</a>
							</s:if>
							<s:if test="#st.first"><a class="btn btn-sm btn-primary" href="javascript:void(0);" onclick="bbsUp(${topicid},${forumer})">顶(<span id="upamount">${dto.topic.up }</span>)</a></s:if>
							<s:if test="#st.first"><a class="btn btn-sm btn-primary" href="javascript:void(0);" onclick="bbsDown(${topicid},${forumer})">踩(<span id="downamount">${dto.topic.down }</span>)</a></s:if>
							</div>
							</div>
			</td>
		</tr>
</s:iterator>
	</table>

<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
		<form action="/${sessionScope.account.owner.entity}/bbs/saveBBSReply.action" method="post" id="myReplyform1"> 
		<input type="hidden" name="topic.content" id="content">
		<input type="hidden" value="${topicid}" name="topic.id">
		<input type="hidden" value="${forumid}" name="topic.FORUM_ID"> 
		<input type="hidden" value="${forumer}" name="topic.CREATER_UID">
		<p>
			<script type="text/plain" id="myEditor" style="width:100%;height:200px;"></script>
		</p>
	</form>
		<button type="button" class="btn btn-primary" onclick="replyTopic()" >快速回复</button>
</div>
 

<script type="text/javascript">
	 var um = UE.getEditor('myEditor');
	um.addListener('ready', function(editor) {
		$(".edui-btn-toolbar").css("white-space","normal");
	});
	
	function bbsReplyTopic(topicid,forumid,forumer,indexCount){
		var entity = '${entity}';
		var srcString = '/' + entity + '/bbs/bbsReplyTopic.action?forumid=' + forumid + "&forumer=" + forumer + "&topicid=" + topicid + "&indexCount=" + indexCount;
		var	title="回复主题";
		layer.open({
				type : 2,
				area : ['550px','500px'],
				title : [title,true],
				content: srcString
			});
		
	}
	
	function bbsUp(topicid,forumer){
		var entity = '${entity}';
		var url = '/' + entity + '/bbs/bbsUp.action';
		$.post(url,{"topicid":topicid,"forumer":forumer},function(data){
			if(data == "Y"){
				var amount = $('#upamount').html();
				amount = amount - 0 + 1;
				$('#upamount').html(amount);
			}else{
				alertMsg("已经对该话题顶过了");
			}
		});
	}
	
	function alertMsg(msg){
			easyDialog.open({
				  container : {
				    content : msg
				  },
				  autoClose : 2000
				});
		}
	
	function bbsDown(topicid,forumer){
		var entity = '${entity}';
		var url = '/' + entity + '/bbs/bbsDown.action';
		$.post(url,{"topicid":topicid,"forumer":forumer},function(data){
		if(data == "Y"){
				var amount = $('#downamount').html();
				amount = amount - 0 + 1;
				$('#downamount').html(amount);
			}else{
				alertMsg("已经对该话题踩过了");
			}
		});
	}
	
	function replyTopic(){
		if(!um.hasContents()){
			alert('内容不能为空');
			return;
		}
		var content = um.getContent();
		$('#content').val(content);
		$('#myReplyform1').submit();
	}
	
	function delReplyTopic(topicid,postid){
		var entity = '${entity}';
		var url = '/' + entity + '/bbs/delReplyTopic.action'
		var layerid=layer.confirm('确定将该条评论删除?',{icon: 2},function(){
			$.post(url,{"topicid":topicid,"postid":postid},function(data){
				if(data == "Y"){
					layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
							window.parent.location.reload();
						});
				}else{
					layer.alert("删除失败,请重试!",0);
				}
			});
		});
	}
	
function commentCheck(postid,checked){
	var url = '/${entity}/bbs/commentCheck.action';
	$.post(url,{"postid":postid,"checked":checked},function(data){
			if(data == "N"){
				layer.alert("操作失败,请重试!",0);
			}else{
				window.location.reload();
			}
		});
}
</script>