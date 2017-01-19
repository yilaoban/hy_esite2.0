<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
		function addom(obj,name){
			layer.prompt({
				title:'创建'+name,
			},function(value){
				var mid=$(obj).attr("id");
				$.post("addOwnerInteract.action",{"title":value,"mid":mid},function(data){
						if(data=="Y"){
							layer.msg('创建中！请稍等……', {icon: 6, time: 1500}, function(){
								window.location.reload();
							}); 
			            }else if(data=="N"){
			            	layer.msg('创建失败！请重试……', {icon: 5, time: 2000});
			            }else{
			            	layer.msg(data, {icon: 5, time: 2000});
			            }			
				});	
			});
		}
		
		function rename(obj,id){
			var name = $(obj).text();
			layer.prompt({
				title:'互动重命名', 
				value:name
			},function(value){
				$.post("config_interact_sub.action",{"title":value,"lid":id,"status":"RENAME"},function(data){
					if(data=="Y"){
						layer.msg('修改中！请稍等……', {icon: 6, time: 1500}, function(){
							window.location.reload();
						}); 
		            }else if(data=="D"){
		            	layer.msg('此互动名称已存在!', {icon: 5, time: 2000});
		            }
					else{
						layer.msg('修改失败!', {icon: 5, time: 2000});
		            }			
				});	
			});
		}
		
		function i_toggle(id,obj){
			var s=$(obj).is(':checked');
			var status;
			if(s){
				status="SHOW";
			}else{
				status="HIDE";
			}
			$.post("config_interact_sub.action",{"lid":id,"status":status},function(data){
			})
		}
		
		function delOm(obj,id){
			var id=id;
			layer.confirm('确定删除吗?',function(){
				$.post("config_interact_sub.action",{"lid":id,"status":"DEL"},function(data){
					 	if(data=="Y"){
					 		layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
								window.location.reload();
							}); 
			            }else{
			            	layer.msg('删除失败!', {icon: 5, time: 2000});
			            }			
				})
			});
		}
		
		function siftMid(mid){
			$(".interactTr").toggle();
			$(".tr_"+mid).show();
		}
</script>
<div class="frame_content">
		<div class="mb10">
		<s:iterator value="list" var="l">
				<span class="labels" id="${l.id }" onclick="addom(this,'${l.name }');$('.layui-layer-input').attr('placeholder','请输入活动名称')"/><img src="/images/${l.id }.png" style="width:16px;margin-right:5px;" />${l.name }</span>
		</s:iterator>
		
		</div>
			<table width="100%" class="tb_normal" border="1" cellspacing="1" cellpadding="1">
				<thead>
					<tr>
						<td>类型</td>
						<td>名称</td>
						<td>显示/不显示</td>
						<td>操作</td>
					</tr>
				</thead>
				<tbody>
				<s:iterator value="omlist" var="m" status="st">
					<tr  class="interactTr  tr_${m.id }">
						<td>
							<a href="javascript:siftMid(${m.id })">
							<s:if test="#m.id==10000">表单</s:if>
							<s:elseif test="#m.id==10002">投票</s:elseif>
							<s:elseif test="#m.id==10003">大转盘</s:elseif>
							<s:elseif test="#m.id==10004">砸金蛋</s:elseif>
							<s:elseif test="#m.id==10006">调研</s:elseif>
							<s:elseif test="#m.id==10011">摇一摇</s:elseif>
							<s:elseif test="#m.id==10015">评测</s:elseif>
							</a>
						</td>
						<td>
							<a href="javascript:void(0);" onclick="rename(this,'${m.omid }')">${m.title }</a>
						</td>
						<td><label class="forradio"><input type="checkbox" <s:if test='status=="CMP"'>checked="checked"</s:if> value="CMP" onchange="i_toggle('${m.omid}',this)"/>显示</label></td>
						<td>
							<a href="javascript:void(0);" onclick="delOm(this,'${m.omid }')"/>删除</a>
						</td>
					</tr>
				</s:iterator>
				</tbody>
			</table>
	</div>
