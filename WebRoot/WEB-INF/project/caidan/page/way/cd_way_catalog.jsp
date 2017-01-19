<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
function delcat(id,name){
	var layerid=layer.confirm('删除渠道分类['+name+']会同时删除此分类下所有渠道!',function(){
		$.post("/${oname}/cd-way/deleteCatalog.action",{inajax:1,caid:id},function(data){
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
		<input type="button" value="新增分类" class="btn btn-primary" onclick="javascript:window.location='/${oname}/cd-way/addCatalog.action'"/>
	</div>
	<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
		<tr>
			<th width="25%">标题</th>
			<th width="10%">创建时间</th>
			<th width="40%">操作</th>
		</tr>
		<s:iterator value="dto.catalogs" var="c" status="st">
		<tr>
			<td>${c.name }</td>
			<td><s:date name="#c.createtime" format="yyyy-MM-dd HH:mm:ss"/></td>
			<td>
				<a href="wayIndex.action?caid=${c.id }">管理</a><i class="split">|</i>
				<a href="editCatalog.action?caid=${c.id }">编辑</a><i class="split">|</i>
				<a href="javascript:delcat(${c.id },'${c.name }')">删除</a>
			</td>
		</tr>
		</s:iterator>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>