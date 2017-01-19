<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="/js/qrcode.js"></script> 
<script type="text/javascript">
function delGG(id,name){
		var layerid=layer.confirm('确定将['+name+']删除吗?',{title:"确认删除"},function(){
			$.post("/${oname}/cd-guanggao/delTfGG.action",{inajax:1,id:id},function(data){
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

function ggUp(id,name){
	var layerid=layer.confirm('确定将广告['+name+']上移吗?',{title:"上移"},function(){
		$.post("/${oname}/cd-guanggao/ggUp.action",{inajax:1,id:id},function(data){
		if(data==0){
			layer.msg('上移失败！已经是最前了……', {icon: 5, time: 2000});
		}else{
			layer.msg('上移成功！', {icon: 6, time: 1500}, function(){
					window.location.reload();
			}); 
		}
		});
	});
}

function ggDown(id,name){
	var layerid=layer.confirm('确定将广告['+name+']下移吗?',{title:"下移"},function(){
		$.post("/${oname}/cd-guanggao/ggDown.action",{inajax:1,id:id},function(data){
		if(data==0){
			layer.msg('下移失败！已经是最后了……', {icon: 5, time: 2000});
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
	  <!-- Nav tabs -->
	  <ul class="nav nav-tabs">
	    <li role="presentation" <s:if test="type==0">class="active"</s:if>><a href="tfggList.action">首页</a></li>
	    <li role="presentation" <s:if test="type==1">class="active"</s:if>><a href="tfggList.action?type=1">试手气</a></li>
	    <li role="presentation" <s:if test="type==2">class="active"</s:if>><a href="tfggList.action?type=2">拼才华</a></li>
	  </ul>
	
	  <!-- Tab panes -->
	  <div class="tab-content">
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
					<td>
						<s:if test="#a.img != null && #a.img != ''">
							<img src="${a.img }" height="30" width="30">
						</s:if>
					</td>
					<td>
						<s:date name="starttime" format="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
						<s:date name="endtime" format="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
						<a href="javascript:ggUp(${a.id },'${a.title }')">上移</a>
						<a href="javascript:ggDown(${a.id },'${a.title }')">下移</a>
						<a href="javascript:delGG(${a.id},'${a.title }')">删除</a>
					</td>
				</tr>
			</s:iterator>
		</table>
		<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
	  </div>
	
	</div>
</div>
