<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="/js/qrcode.js"></script> 
<script type="text/javascript">
function delYuYueService(id,name){
		var layerid=layer.confirm('确定将['+name+']删除吗?',{title:"确认删除"},function(){
			$.post("/${oname}/interact/delYuYueService.action",{inajax:1,serviceid:id},function(data){
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

function yuYueServiceSet(serviceid,catid){
		var srcString = '/${oname}/interact/yuYueServiceSet.action?serviceid=' + serviceid + '&catid=' + catid;
		layer.open({
				type : 2,
				area : ['500px','480px'],
				title : '人员设置',
				content: srcString
			});
	}

function moveUp(id,name){
	var layerid=layer.confirm('确定将['+name+']上移吗?',{title:"上移"},function(){
		$.post("/${oname}/interact/yuyue_serviceIdx.action",{inajax:1,serviceid:id,moveUp:1},function(data){
		if(data==0){
			layer.msg('上移失败！请重试……', {icon: 5, time: 2000});
		}else{
			layer.msg('上移成功！', {icon: 6, time: 1500}, function(){
					window.location.reload();
			}); 
		}
		});
	});
}

function moveDown(id,name){
	var layerid=layer.confirm('确定将['+name+']下移吗?',{title:"下移"},function(){
		$.post("/${oname}/interact/yuyue_serviceIdx.action",{inajax:1,serviceid:id,moveUp:-1},function(data){
		if(data==0){
			layer.msg('下移失败！请重试……', {icon: 5, time: 2000});
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
	<div class="switch_tab_div pb10">
		<span><a href="/${oname}/interact/yuyueCatalog.action">项目设置</a><i class="gt">&gt;&gt;</i>${dto.catalog.name}</span>
		<p class="clearfix"></p>
		<ul class="c_switch">
		  <li <s:if test='swtype=="S"'>class="selected"</s:if>><a href="/${oname}/interact/yuyueService.action?catid=${catid }">服务项目</a></li>
		  <li <s:if test='swtype=="R"'>class="selected"</s:if>><a href="/${oname}/interact/yuYueServicer.action?catid=${catid }&swtype=R">提供者</a></li>
	      <li <s:if test='swtype=="P"'>class="selected"</s:if>><a href="/${oname}/interact/yuYueSSTime.action?catid=${catid }&swtype=P&weekday=1">预约配置</a></li>
		  <li <s:if test='swtype=="D"'>class="selected"</s:if>><a href="/${oname}/interact/yuyueCatelogRecord.action?catid=${catid}&day=1&swtype=D">当天预约</a></li>
	   	  <li <s:if test='swtype=="Y"'>class="selected"</s:if>><a href="/${oname}/interact/yuyueCatelogRecord.action?catid=${catid}&swtype=Y">预约记录</a></li>
	    </ul>
	</div>
	<div class="toolbar pb10">
		<a href="/${oname }/interact/addYuYueService.action?catid=${catid }" class="btn btn-primary">新增服务</a>
	</div>
	
	<table width="100%" class="tb_normal" border="1" cellspacing="1"
		cellpadding="1">
			<tr>
				<th>服务</th>
				<th>简介</th>
				<th>操作</th>
			</tr>
		<s:iterator value="dto.serviceList" var="p">
			<tr>
				<td>${p.name }</td>
				<td>${p.hydesc }</td>
				<td>
					<a href="/${oname }/interact/editYuYueService.action?serviceid=${p.id}&catid=${catid }">编辑</a>
					 <!-- 
					 <a href="javascript:void(0)" onclick="yuYueServiceSet(${p.id},${catid})" >配置</a>
					  -->
					<a href="javascript:delYuYueService(${p.id},'${p.name }')">删除</a>
					<a href="javascript:moveUp(${p.id },'${p.name }')">上移</a>
					<a href="javascript:moveDown(${p.id },'${p.name }')">下移</a>
				</td>
			</tr>
		</s:iterator>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>
