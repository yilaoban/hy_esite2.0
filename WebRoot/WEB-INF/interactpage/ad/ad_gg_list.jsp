<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="/js/qrcode.js"></script> 
<script type="text/javascript">
function delGG(id,name){
		var layerid=layer.confirm('确定将['+name+']删除吗?',{title:"确认删除"},function(){
			$.post("/${oname}/interact/delGG.action",{inajax:1,ggid:id},function(data){
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
	<div class="toolbar pb10">
		<a href="/${oname }/interact/addGG.action" class="btn btn-primary">新增广告</a>
	</div>
	
	<table width="100%" class="tb_normal" border="1" cellspacing="1"
		cellpadding="1">
			<tr>
				<th>标题</th>
				<th>图片</th>
				<th>开始时间</th>
				<th>结束时间</th>
				<th>操作</th>
			</tr>
		<s:iterator value="dto.adGGList" var="a" status="st">
			<tr>
				<td>${a.title }</td>
				<td><img src="${a.img }" height="67" width="67"></td>
				<td>
					<s:date name="starttime" format="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<s:date name="endtime" format="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<a href="/${oname }/interact/editGG.action?ggid=${a.id}">编辑</a>
					<a href="javascript:delGG(${a.id},'${a.title }')">删除</a>
				</td>
			</tr>
		</s:iterator>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>
