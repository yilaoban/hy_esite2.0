<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	function delYuYueTag(id,name){
		var layerid=layer.confirm('确定将标签['+name+']删除吗?',{title:"确认删除"},function(){
			$.post("/${oname}/interact/delYuYueTag.action",{inajax:1,tagid:id},function(data){
			if(data==0){
				layer.msg('删除失败！请重试……', {icon: 5, time: 2000});
			}else{
				layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
						window.location.reload();
				}); 
			}
			});
		});
}
</script>
<div class="wrap_content">
		<form action="/${oname }/interact/saveYuYueTag.action" method="post">
			<input type="text" name="yuYueTag.name">
			<input type="submit" value="新增标签">
		</form>
	<table width="100%" class="tb_normal" border="1" cellspacing="1" cellpadding="1">
		<tr>
			<td>
				标签
			</td>
			<td>
				操作
			</td>
		</tr>
		<s:iterator value="dto.tagList" var="t">
			<tr>
				<td>${t.name }</td>
				<td><a href="javascript:delYuYueTag(${t.id},'${t.name }')">删除</a></td>
			</tr>
		</s:iterator>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>
