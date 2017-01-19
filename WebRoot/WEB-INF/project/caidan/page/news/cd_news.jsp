<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
function delcat(id,name){
	var layerid=layer.confirm('确定将['+name+']删除吗?',function(){
		$.post("/${oname}/cd-news/del.action",{inajax:1,contentid:id},function(data){
			if(data==1){
				layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
					window.parent.location.reload();
				}); 
			}else{
				layer.msg('删除失败！请重试……', {icon: 5, time: 2000});
			}
		});
	});
}

function moveUp(id,name){
	var layerid=layer.confirm('确定将['+name+']上移吗?',{title:"上移"},function(){
		$.post("/${oname}/cd-news/moveUp.action",{inajax:1,contentid:id},function(data){
		if(data==0){
			layer.msg('上移失败！请重试……', {icon: 5, time: 2000});
		}else{
			layer.msg('上移成功！', {icon: 6, time: 1500}, function(){
					window.location.reload();
			}); 
		}
		});
	});
}

function moveDown(id,name){
	var layerid=layer.confirm('确定将['+name+']下移吗?',{title:"下移"},function(){
		$.post("/${oname}/cd-news/moveDown.action",{inajax:1,contentid:id},function(data){
		if(data==0){
			layer.msg('下移失败！请重试……', {icon: 5, time: 2000});
		}else{
			layer.msg('下移成功！', {icon: 6, time: 1500}, function(){
					window.location.reload();
			}); 
		}
		});
	});
}
</script>
<div class="wrap_content">
	<div class="toolbar pb10">
		<input type="button" value="新增" class="btn btn-primary" onclick="javascript:window.location='/${oname}/cd-news/add.action'"/>
	</div>
	<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
		<tr>
			<th width="25%">标题</th>
			<th width="10%">更新时间</th>
			<th width="40%">操作</th>
		</tr>
		<s:iterator value="dto.list" var="l">
		<tr>
			<td>${l.news.title}</td>
			<td><s:date name="#l.news.updatetime" format="yyyy-MM-dd"/></td>
			<td>
				<a href="/${oname }/cd-news/edit.action?contentid=${l.news.id }">编辑</a><i class="split">|</i>
				<a href="javascript:moveUp(${l.news.id },'${l.news.title }')">上移</a><i class="split">|</i>
				<a href="javascript:moveDown(${l.news.id },'${l.news.title }')">下移</a><i class="split">|</i>
				<a href="javascript:delcat(${l.news.id },'${l.news.title }')">删除</span></a>
			</td>
		</tr>
		</s:iterator>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>