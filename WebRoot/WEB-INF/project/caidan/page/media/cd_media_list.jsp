<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="/js/qrcode.js"></script> 
<script type="text/javascript">
function delMedia(id,name){
		var layerid=layer.confirm('确定将['+name+']删除吗?',{title:"确认删除"},function(){
			$.post("/${oname}/cd-media/delMedia.action",{inajax:1,mid:id},function(data){
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
		<a href="/${oname }/cd-media/addMedia.action" class="btn btn-primary">新增媒体</a>
	</div>
	
	<table width="100%" class="tb_normal" border="1" cellspacing="1"
		cellpadding="1">
			<tr>
				<th>媒体编号</th>
				<th>媒体名称</th>
				<th>媒体区域</th>
				<th>媒体简介</th>
				<th>合作时间</th>
				<th>操作</th>
			</tr>
		<s:iterator value="dto.adMediaList" var="a" status="st">
			<tr>
				<td>${a.idStr }</td>
				<td>${a.name }</td>
				<td>${a.area }</td>
				<td>${a.hydesc }</td>
				<td>
					<s:date name="createtime" format="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<a href="/${oname }/cd-media/editMedia.action?mid=${a.id}">编辑</a>
					<a href="/${oname}/cd-media/mediaWay.action?mid=${a.id}">详情</a>
					<a href="javascript:delMedia(${a.id},'${a.name }')">删除</a>
				</td>
			</tr>
		</s:iterator>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>
