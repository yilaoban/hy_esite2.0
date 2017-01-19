<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	$(document).ready(function(){
		$("#wideModal12").on("hidden.bs.modal", function() {
			window.location.reload();
		});
	});
	

	function quanxuanthispage(name){
		$('input:checkbox[name="'+name+'"]').each(function () {  
            $(this).attr("checked", true); 
         });
	}
	
	function fanxuan(name){
	 $('input:checkbox[name="'+name+'"]').each(function () {  
            $(this).attr("checked", !$(this).attr("checked")); 
        });
	}
	
function batchPass(checkbox,checked){
	var s = document.getElementsByName(checkbox);
	var ids="";
	for(i= 0;i<s.length;i++){
		if(s[i].checked){
			ids+=s[i].value+";";
		}
	}
	if(ids==""){
		layer.msg('您还没选择要审核的话题!',1,8);
		return;
	}
	var entity = '${entity}';
	var url = '/' + entity + '/bbs/bathPass.action';
	$.post(url,{"ids":ids,"checked":checked},function(data){
			if(data == "N"){
				layer.alert("操作失败,请重试!",0);
			}else{
				layer.alert("操作成功",1);
				window.location.reload();
			}
		});
}

function toCreateTopic(id,forumer){
	var entity = '${entity}';
	var srcString = '/' + entity + '/bbs/addTopic.action?forumid=' + id + "&forumer=" + forumer;
	var	title="创建话题";
	layer.open({
			type : 2,
			area : ['650px','550px'],
			title : [title,true],
			content: srcString
		});
	
}

function deleteBBSTopic(topicid,name){
	var entity = '${entity}';
	var url = '/' + entity + '/bbs/deleteBBSTopic.action';
	var layerid=layer.confirm('确定将['+name+']删除?',{icon: 2},function(){
		$.post(url,{"topicid":topicid,"forumid":${forumid}},function(data){
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

function batchDel(checkbox){
	var s = document.getElementsByName(checkbox);
	var ids="";
	for(i= 0;i<s.length;i++){
		if(s[i].checked){
			ids+=s[i].value+";";
		}
	}
	if(ids==""){
		layer.msg('您还没选择要删除的话题!',1,8);
		return;
	}
	var entity = '${entity}';
	var url = '/' + entity + '/bbs/batchDel.action';
	$.post(url,{"ids":ids,"forumid":${forumid}},function(data){
			if(data == "N"){
				layer.alert("操作失败,请重试!",0);
			}else{
				layer.alert("操作成功",1);
				window.location.reload();
			}
		});
}


	
function setTop(id,forumer){
	var topObj = $('#top_' + id);
	var entity = '${entity}';
	var url = '/' + entity + '/bbs/updateTopicTop.action';
	if(topObj[0].checked){
		$.post(url,{"topicid":id,"top":1,"forumer":forumer});
	}else{
		$.post(url,{"topicid":id,"top":0,"forumer":forumer});
	} 
}

function paixu(){
	var index = $('#index').val();
	var entity = '${entity}';
	var forumid = '${forumid}';
	var forumer = '${forumer}';
	window.location.href = '/' + entity + '/bbs/findTopicListSort.action?index=' + index + '&forumid=' + forumid + '&forumer=' + forumer;
}
function editBBSTopic(topicid,forumid,forumer){
		var srcString = '/${entity}/bbs/editbbsTopic.action?topicid=' + topicid +'&forumid=' + forumid + '&forumer=' + forumer;
		var	title="编辑话题";
		layer.open({
			type : 2,
			area : ['650px','550px'],
			title : [title,true],
			content: srcString
		});
}

</script>
<div class="wrap_content">
	<div class="switch_tab_div pb10">
		<span><a href="/${sessionScope.account.owner.entity}/bbs/index.action">板块管理</a><i class="gt">&gt;&gt;</i>${dto.bbsForum.title}</span>
		<p class="clearfix"></p>
	</div>
	 <div class="toolbar pb10">
	 <div class="left">
	  <select name="index" onchange="paixu()" id="index">
			<option value="0" <s:if test="index == 0">selected="selected"</s:if>>默认排序</option>
			<option value="1" <s:if test="index == 1">selected="selected"</s:if>>按评论从高到底</option>
			<option value="2" <s:if test="index == 2">selected="selected"</s:if>>按评论从低到高</option>
			<option value="3" <s:if test="index == 3">selected="selected"</s:if>>按顶从高到底</option>
			<option value="4" <s:if test="index == 4">selected="selected"</s:if>>按顶从低到高</option>
			<option value="5" <s:if test="index == 5">selected="selected"</s:if>>按踩从高到底</option>
			<option value="6" <s:if test="index == 6">selected="selected"</s:if>>按踩从低到高</option>
		</select>
	  <a href="javascript:quanxuanthispage('usercheck')" class="ml20">全选本页</a><i class='vline'>|</i><a href="javascript:fanxuan('usercheck')">反选</a>
	  </div>
	  <input type="button" class="btn btn-primary" value="批量审核通过" onclick="javascript:batchPass('usercheck','Y');"/>
	  <input type="button" class="btn btn-primary" value="批量审核不通过" onclick="javascript:batchPass('usercheck','N');"/>
	  <input type="button" class="btn btn-primary"  value="创建话题" onclick="toCreateTopic(${forumid},${forumer})"/>
	  <input type="button" class="btn btn-primary" value="批量删除" onclick="javascript:batchDel('usercheck');"/>
	</div>
  	<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
			<th></th>
			<th>话题</th>
			<th>发帖人</th>
			<!-- 
			<th>会员等级</th>
			 -->
			<th>状态</th>
			<th>创建时间</th>
			<th>评论</th>
			<th>顶</th>
			<th>踩</th>
			<th>置顶</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="dto.bbsTopicList" var="s">
			<tr align="center" >
				<td align="center">
					<input type="checkbox" name="usercheck" value="${s.id }" >
				</td>
				<td align="center">${s.TITLE }</td>
				<td align="center">${s.creater }</td>
				<!-- 
				<td align="center">${s.level_name }</td>
				 -->
				<s:if test='dto.bbsForum.topicCheck == "Y"'>
					<td align="center"><s:if test='#s.checked == "CMP"'>审核通过</s:if><s:elseif test='#s.checked == "FLD"'>审核不通过</s:elseif><s:else>待审核</s:else></td>
				</s:if>
				<s:else>
					<td align="center">审核通过</td>
				</s:else>
				<td align="center"><s:date name="CREATE_TIME" format="yyyy-MM-dd HH:mm:ss"/></td>
				<td align="center">${s.REPLY_COUNT }</td>
				<td align="center">${s.up }</td>
				<td align="center">${s.down }</td>
				<td align="center"><input type="checkbox" id="top_${s.id }" name="top" value="${s.id }" onclick="setTop(${s.id },${s.CREATER_UID })" <s:if test='#s.top == 1'>checked="checked"</s:if>/></td>
				<td align="center">
					<s:if test="#s.entityid > 0"> 
						 <a href="javascript:void(0)" onclick="layer.alert('请至内容素材处编辑',3)">编辑</a>
					</s:if>
					<s:else>
						<a href="javascript:void(0)" onclick="editBBSTopic(${s.id },${forumid},${forumer })">编辑</a>
					</s:else>
					<i class="split">|<i class="split">
					<a href="/${sessionScope.account.owner.entity}/bbs/bbsTopicDetail.action?forumid=${forumid}&topicid=${s.id}&forumer=${forumer }">查看详情</a>
					<i class="split">|<i class="split">
					<a href="javascript:void(0)" onclick="deleteBBSTopic(${s.id },'${s.TITLE }')">删除</a>
				</td>		
			</tr>
		</s:iterator>
	</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>
<div id="wideModal" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog" style="width:500px;">
    <div class="modal-content">
    </div>
  </div>
</div>
<div id="wideModal12" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog" style="width:500px;">
    <div class="modal-content" id="rr">
    </div>
  </div>
</div>
