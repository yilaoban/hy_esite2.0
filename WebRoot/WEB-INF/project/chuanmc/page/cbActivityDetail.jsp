<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	function delCbActivityMatter(id){
		var srcString = '/${oname}/interact/delCbActivityMatter.action';
		var layerid=layer.confirm('确定删除?',{icon: 2},function(){
			$.post(srcString,{"amid":id},function(data){
				if(data == "Y"){
					layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
						window.parent.location.reload();
					}); 
				}else{
					layer.msg('删除失败！请重试……', {icon: 5, time: 2000});
				}
			});
		});
	}
	
	function findMaterial(){
		var srcString = '/${oname}/interact/findMaterial.action?cbid=${cbid}&aid=${aid}';
		var	title="设置物料";
		layer.open({
				type : 2,
				area : ['800px','600px'],
				title : [title,true],
				content: srcString,
				cancel: function(index){ 
					window.parent.location.reload();
				} 
			});
	}
	
	
	function findNewsMaterial(){
		var srcString = '/${oname}/interact/findNewsMaterial.action?cbid=${cbid}&aid=${aid}';
		var	title="新闻物料";
		layer.open({
				type : 2,
				area : ['800px','600px'],
				title : [title,true],
				content: srcString,
				cancel: function(index){ 
					window.parent.location.reload();
				} 
			});
	}
</script>

<div class="wrap_content">
	<div class="toolbar pb10">
		<a href="javascript:void(0)" onclick="findMaterial()" class="btn btn-primary">设置物料</a> 
		<a href="javascript:void(0)" onclick="findNewsMaterial()" class="btn btn-primary">新闻物料</a> 
	</div>
		<table width="100%" cellspacing="1" cellpadding="1" border="1" class="tb_normal">
			<thead>
				<tr>
					<th>
						标题
					</th>
					<th>
						图片
					</th>
					<th>
						概述
					</th>
					<th>
						操作
					</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="dto.matterList" var="f">
					<tr align="center">
						<td>
							${f.title}
						</td>
						<td>
							<img src="${f.pic }" height="78px" width="78px"> 
						</td>
						<td>
							<s:if test="#f.description.length()>5">    
							    <span style="cursor:default;text-decoration:none;" title="${f.description }"> <s:property value="#f.description.substring(0,5)" />
							     ...</span>
							</s:if>    
							<s:else>    
							  	 <s:property value="#f.description" />    
							</s:else>
						</td>
						<td>
					      	<a href="javascript:void(0)" onclick="delCbActivityMatter(${f.id})">删除</a>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>
