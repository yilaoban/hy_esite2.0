<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	
function delPjWdById(id,name){
	var srcString = '/${oname}/servicerpj/delPjWdById.action';
	var layerid=layer.confirm('确定将['+name+']删除?',{icon: 2},function(){
		$.post(srcString,{"pjwdid":id},function(data){
			if(data > 0){
				layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
					window.parent.location.reload();
				}); 
			}else{
				layer.msg('删除失败！请重试……', {icon: 5, time: 2000});
			}
		});
	});
}

function editpjwd(id,oldname){
	var url = "/${oname}/servicerpj/updatePjWdById.action";
	layer.prompt({
		value : oldname,
		title : '编辑'
		},function(value){
			if(value==null||value==''){
				   return false;
			}
			$.post(url,{"name":value,"pjwdid":id},function(data){
				if(data > 0){
					layer.msg('修改中！请稍等……', {icon: 6, time: 1500}, function(){
						window.parent.location.reload();
					}); 
				}else{
					layer.msg('操作失败……', {icon: 5, time: 2000});
				}
			});	             
    });
}

function addpjwd(){
	layer.prompt({
		title : '新增维度'
		},function(value){
			if(value==null||value==''){
				   return false;
			}
			$.post("/${oname}/servicerpj/savepjwd.action",{"name":value},function(data){
				if(data > 0){
					layer.msg('请稍等……', {icon: 6, time: 1500}, function(){
						window.parent.location.reload();
					}); 
				}else{
					layer.msg('操作失败……', {icon: 5, time: 2000});
				}
			});	             
    });
	
}

</script>

<div class="wrap_content">
	<div class="toolbar pb10">
		<a href="javascript:void(0);" onclick="addpjwd()" class="btn btn-primary">新增维度</a> 
	</div>

	<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
		<thead>
			<tr>
				<th>名称</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="dto.pjwdList" var="wd">
			<tr>
				<td align="center">
					${wd.name}
				</td>
				<td align="center">
					<a href="javascript:void(0);" onclick="editpjwd(${wd.id},'${wd.name}')">编辑</a>
					<a href="javascript:void(0);" onclick="delPjWdById(${wd.id},'${wd.name}')">删除</a>
				</td>
			</tr>
			</s:iterator>
		</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>