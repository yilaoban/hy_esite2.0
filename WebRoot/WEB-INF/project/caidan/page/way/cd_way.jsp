<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
function delWay(id,name){
	var layerid=layer.confirm('确定删除渠道['+name+']吗!',function(){
		$.post("/${oname}/cd-way/deleteWay.action",{inajax:1,wayid:id},function(data){
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
</script>
<div class="wrap_content">
	<div class="toolbar pb10">
		<input type="button" value="新增渠道" class="btn btn-primary" onclick="javascript:window.location='/${oname}/cd-way/addWay.action?caid=${caid }'"/>
		<a href="/${oname }/cd-way/index.action" class="return" title="返回"></a>
	</div>
	<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
		<tr>
			<th>渠道编号</th>
			<th>渠道名称</th>
			<th>渠道分类</th>
			<th>渠道经理</th>
			<th>联系人</th>
			<th>联系电话</th>
			<th>联系邮件</th>
			<th>地址</th>
			<th>简介</th>
			<th>操作</th>
		</tr>
		<s:iterator value="dto.ways" var="c" status="st">
		<tr>
			<td>${c.idStr }</td>
			<td>${c.name }</td>
			<td>${c.catalog.name }</td>
			<td>${c.account.username }</td>
			<td>${c.realname }</td>
			<td>${c.telphone }</td>
			<td>${c.email }</td>
			<td>${c.address }</td>
			<td>${c.hydesc }</td>
			<td>
				<a href="editWay.action?wayid=${c.id }">编辑</a><i class="split">|</i>
				<a href="javascript:delWay(${c.id },'${c.name }')">删除</a>
			</td>
		</tr>
		</s:iterator>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>