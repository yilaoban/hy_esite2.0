<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	function loadServicer(){
		var loadhtml=$("#loadServicer").html();
		$("#loadServicer").empty();
		layer.confirm(loadhtml, { title:'导入人员',end:function(){	$("#loadServicer").html(loadhtml);}}, function(index){
			var caid=$("#catid").val();
			layer.close(index);
			var index2 = layer.msg('正在导入,请稍等...', {icon: 16,time:0});
			$.post("/${oname}/servicerpj/loadServicer.action",{"caid":caid},function(data){
				layer.close(index2);
				if(data>0){
					layer.msg('导入成功！', {icon: 6, time: 1500}, function(){
						window.location.reload();
					}); 
				}
			});
		}); 
	}
	
	function delYuYueServicer(id,name){
		var layerid=layer.confirm('确定将['+name+']删除吗?',{title:"确认删除"},function(){
			$.post("/${oname}/servicerpj/delServicer.action",{inajax:1,serid:id},function(data){
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
	
	function servicerMove(id,name,upOrdowm){
		var str;
		if(upOrdowm==1){
			str="上移";
		}else{
			str="下移";
		}
		layer.confirm('确定将['+name+']'+str+'吗?',{title:str},function(index){
			$.post("/${oname}/servicerpj/servicerMove.action",{inajax:1,serid:id,moveUp:upOrdowm},function(data){
			if(data>0){
				if(upOrdowm==1){
					$("#tr"+id).prev().insertAfter($("#tr"+id));
				}else{
					$("#tr"+id).insertAfter($("#tr"+id).next());				
				}
				layer.close(index);
			}else{
				layer.msg(str+'失败！请重试……', {icon: 5, time: 2000});
			}
			});
		});
	}
	
	function setServicerTop(id,name){
		layer.confirm('确定将['+name+']置顶吗?',{title:"置顶"},function(){
			$.post("/${oname}/servicerpj/servicerTop.action",{inajax:1,serid:id,top:1},function(data){
			if(data==0){
				layer.msg('置顶失败！请重试……', {icon: 5, time: 2000});
			}else{
				layer.msg('置顶成功！', {icon: 6, time: 1500}, function(){
						window.location.reload();
				}); 
			}
			});
		});
	}
	
	function cancleServicerTop(id,name){
		layer.confirm('确定取消['+name+']置顶吗?',{title:"取消置顶"},function(){
			$.post("/${oname}/servicerpj/servicerTop.action",{inajax:1,serid:id,top:0},function(data){
			if(data==0){
				layer.msg('取消失败！请重试……', {icon: 5, time: 2000});
			}else{
				layer.msg('取消成功！', {icon: 6, time: 1500}, function(){
						window.location.reload();
				}); 
			}
			});
		});
	}
	
	
</script>
<div class="wrap_content left_module">
	<div class="toolbar pb10">
	  <a href="/${oname }/servicerpj/addServicerPj.action" class="btn btn-primary">新增人员</a>
	  <a href="javascript:loadServicer()" class="btn btn-primary">导入人员</a>
	</div>

  	<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
			<th>姓名</th>
			<th>标签</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="dto.list" var="p">
		<tr id="tr${p.id }">
			<td>${p.name }</td>
			<td>
				<s:iterator value="#p.tagList" var="t">
						${t.name }
				</s:iterator>
			</td>
			<td>
					<a href="/${oname }/servicerpj/editServicer.action?serid=${p.id}">编辑</a>
				
					<a href="javascript:delYuYueServicer(${p.id},'${p.name }')">删除</a>
					<a href="javascript:servicerMove(${p.id },'${p.name }',1)">上移</a>
					<a href="javascript:servicerMove(${p.id },'${p.name }',-1)">下移</a>
					<s:if test="#p.top==1">
						<a href="javascript:cancleServicerTop(${p.id },'${p.name }')">取消置顶</a>
					</s:if>
					<s:else>
						<a href="javascript:setServicerTop(${p.id },'${p.name }')">置顶</a>
					</s:else>
			</td>
		</tr>
		</s:iterator>
	</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>
<div id="loadServicer" style="display: none">
	请选择微预约项目配置中的提供者导入
	<br>
	选择项目:
	<select id="catid">
		<s:iterator value="dto.catalogs" var="c">
		<option value="${c.id }">${c.name }</option>
		</s:iterator>
	</select>
</div>