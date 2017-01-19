<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
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
		layer.msg('您还没选择要审核的用户!',1,8);
		return;
	}
	$.post("/interact/bathPass.action",{"ids":ids,"checked":checked},function(data){
			if(data == "N"){
				layer.alert("操作失败,请重试!",0);
			}else{
				layer.alert("操作成功",1);
				window.location.reload();
			}
		});
	}
	
	function auditList(id,utype){
		$.post("/interact/auditList.action",{"xcid":id,"utype":utype});
	}
	
</script>
<div class="wrap_content left_module">
	 <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li <s:if test='utype == 1'>class="selected"</s:if>><a href="/${oname }/interact/checkdpmComment.action?xcid=${xcid }&utype=1">微信详情</a></li>
	  <li <s:if test='utype == 0'>class="selected"</s:if>><a href="/${oname }/interact/checkdpmComment.action?xcid=${xcid }&utype=0">微博详情</a></li>
	  <a href="javascript:quanxuanthispage('usercheck')" class="ml20">全选本页</a><i class='vline'>|</i><a href="javascript:fanxuan('usercheck')">反选</a>
	  <a href="/${oname }/interact/checkdpmComment.action?xcid=${xcid }&utype=${ utype}" class="button">页面刷新</a>
	  <a href="/${oname }/interact/checkauditList.action?xcid=${xcid }&utype=${utype }" class="button">查询未审核用户</a>
	  <input type="button" class="btn " value="批量审核通过" onclick="javascript:batchPass('usercheck','Y');"/>
	  <input type="button" class="btn " value="批量审核不通过" onclick="javascript:batchPass('usercheck','N');"/>
	  </ul>
	</div>
  	<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
			<th></th>
			<th>用户昵称</th>
			<th>评论内容</th>
			<th>创建日期</th>
			<th>审核</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="dto.commentRecordList" var="s">
			<tr align="center" >
				<td align="center">
					<input type="checkbox" name="usercheck" value="${s.id }" >
				</td>
				<td align="center">${s.nickname }</td>
				<td align="center">${s.content }</td>
				<td align="center"><s:date name="createtime" format="yyyy-MM-dd HH:mm:ss"/></td>
				<td align="center">
					<s:if test='#s.status =="EDT"'>待审核</s:if>
					<s:elseif test='#s.status == "CMP"'>审核通过</s:elseif>
					<s:elseif test='#s.status == "FLD"'>审核不通过</s:elseif>
				</td>		
			</tr>
		</s:iterator>
	</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>
