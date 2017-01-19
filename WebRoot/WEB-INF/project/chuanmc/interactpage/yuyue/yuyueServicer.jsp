<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="/js/qrcode.js"></script> 
<script type="text/javascript">
function delYuYueServicer(id,name){
		var layerid=layer.confirm('确定将['+name+']删除吗?',{title:"确认删除"},function(){
			$.post("/${oname}/interact/delYuYueServicer.action",{inajax:1,serid:id,serviceid:${serviceid}},function(data){
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

	function addYuYueTag(){
		var srcString = '/${oname}/interact/addYuYueTag.action';
		layer.open({
				type : 2,
				area : ['600px','600px'],
				title : '添加标签',
				content: srcString
			});
	}
	
	
	function yuYueServicerTag(serid){
		var srcString = '/${oname}/interact/yuYueServicerTag.action?serid='+ serid;
		layer.open({
				type : 2,
				area : ['600px','600px'],
				title : '设置标签',
				content: srcString,
				cancel: function(index){
					window.parent.location.reload();
				} 
			});
	}
	
	function yuYueServiceSet(serid,catid){
		var srcString = '/${oname}/interact/yuYueServicerSet.action?serid=' + serid + '&catid=' + catid;
		layer.open({
				type : 2,
				area : ['600px','600px'],
				title : ' 服务项目设置',
				content: srcString
			});
	}
	
	function moveUp(id,name){
		var layerid=layer.confirm('确定将['+name+']上移吗?',{title:"上移"},function(){
			$.post("/${oname}/interact/yuyue_servicerIdx.action",{inajax:1,serid:id,moveUp:1},function(data){
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
			$.post("/${oname}/interact/yuyue_servicerIdx.action",{inajax:1,serid:id,moveUp:-1},function(data){
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

	function setServicerTop(id,name){
		var layerid=layer.confirm('确定将['+name+']置顶吗?',{title:"置顶"},function(){
			$.post("/${oname}/interact/setServicerTop.action",{inajax:1,serid:id,top:1},function(data){
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
		var layerid=layer.confirm('确定取消['+name+']置顶吗?',{title:"取消置顶"},function(){
			$.post("/${oname}/interact/setServicerTop.action",{inajax:1,serid:id,top:0},function(data){
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
		<!-- 
		<a href="javascript:void(0)" onclick="addYuYueTag()" class="btn btn-primary" style="float: right">新增标签</a>
		 -->
		<a href="/${oname }/interact/addYuYueServicer.action?catid=${catid}&swtype=${swtype }" class="btn btn-primary">新增人员</a>
	</div>
	
	<table width="100%" class="tb_normal" border="1" cellspacing="1"
		cellpadding="1">
			<tr>
				<th>姓名</th>
				<th>简介</th>
				<th>标签</th>
				<th>操作</th>
			</tr>
		<s:iterator value="dto.servicerList" var="p">
			<tr>
				<td>${p.name }</td>
				<td>${p.hydesc }</td>
				<th>
					<s:iterator value="#p.tagList" var="t">
						${t.name }
					</s:iterator>
				</th>
				<td>
					<a href="/${oname }/interact/editYuYueServicer.action?serid=${p.id}&swtype=${swtype }&catid=${catid }">编辑</a>
					<!-- 
					<a href="javascript:void(0)" onclick="yuYueServicerTag(${p.id})" >标签配置</a>
					<a href="/${oname}/interact/yuYueSSTime.action?ssid=${p.ssid }">时间配置</a>
					 <a href="javascript:void(0)" onclick="yuYueServiceSet(${p.id},${catid})" >配置</a>
					 -->
					<a href="javascript:delYuYueServicer(${p.id},'${p.name }')">删除</a>
					<a href="javascript:moveUp(${p.id },'${p.name }')">上移</a>
					<a href="javascript:moveDown(${p.id },'${p.name }')">下移</a>
					<s:if test="#p.top==1">
						<a href="javascript:cancleServicerTop(${p.id },'${p.name }')">取消置顶</a>
					</s:if>
					<s:else>
						<a href="javascript:setServicerTop(${p.id },'${p.name }')">置顶</a>
					</s:else>
				</td>
			</tr>
		</s:iterator>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>
