<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="/js/qrcode.js"></script> 
<script type="text/javascript">
function delYuYueCatalog(id,name){
		var layerid=layer.confirm('确定将项目['+name+']删除吗?',{title:"确认删除"},function(){
			$.post("/${oname}/interact/delYuYueCatalog.action",{inajax:1,catid:id},function(data){
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

function updateYuYueCatalog(id,type){
	layer.prompt({
		value : type,
		title : '标记'
		},function(value){
			if(value.length <= 3){
				$.post("/${oname}/interact/updateYuYueCatalogType.action",{"catid":id,"type":value},function(data){
					if(data==0){
							layer.msg('操作失败！请重试……', {icon: 5, time: 2000});
						}else{
							window.location.reload();
						}
				});            
			}else {
				layer.msg('所填内容太长，只限3个字符', {icon: 5, time: 2000});
			}
    });
}


function catalogUp(id,name){
	var layerid=layer.confirm('确定将项目['+name+']上移吗?',{title:"上移"},function(){
		$.post("/${oname}/interact/yuyue_catalogUp.action",{inajax:1,catid:id},function(data){
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

function catalogDown(id,name){
	var layerid=layer.confirm('确定将项目['+name+']下移吗?',{title:"下移"},function(){
		$.post("/${oname}/interact/yuyue_catalogDown.action",{inajax:1,catid:id},function(data){
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

function showTips(){
	$('.layui-layer-content').append('<div style="color: red;">默认：NOR，隐藏：HID，也可自定义其它标记</div>');
}

</script>
<div class="wrap_content">
	<div class="toolbar pb10" >
		<a href="/${oname }/interact/addYuYueCatalog.action" class="btn btn-primary">新增项目</a>
	</div>
	
	<table width="100%" class="tb_normal" border="1" cellspacing="1"
		cellpadding="1">
			<tr>
				<th>项目</th>
				<th>操作</th>
			</tr>
		<s:iterator value="dto.catalogList" var="p" status="st">
			<tr>
				<td>${p.name }</td>
				<td>
					<a href="javascript:msg('${p.fwpageid }','${p.id }','二维码');">二维码地址</a>
					<a href="/${oname }/interact/editYuYueCatalog.action?catid=${p.id}">编辑</a>
					<a href="/${oname }/interact/yuyueService.action?catid=${p.id}">配置</a>
					<a href="javascript:updateYuYueCatalog(${p.id},'${p.type }');showTips();">标记</a>
					<!-- 
						<s:if test='#p.type == "NOR"'>
							<a href="javascript:updateYuYueCatalog(${p.id},'HID')">隐藏</a>
						</s:if>
						<s:else>
							<a href="javascript:updateYuYueCatalog(${p.id},'NOR')">显示</a>
						</s:else>
					 -->
					<a href="javascript:catalogUp(${p.id },'${p.name }')">上移</a>
					<a href="javascript:catalogDown(${p.id },'${p.name }')">下移</a>
					<a href="javascript:delYuYueCatalog(${p.id},'${p.name }')">删除</a>
				</td>
			</tr>
		</s:iterator>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>
<div id="msg" style="display: none">
  <div id="qrcode" style="float: left"></div>
  <div id="url" style="float: left"></div>
</div>
<script type="text/javascript" src="/js/qrcode.js"></script>

<script type="text/javascript">
var qrcode = new QRCode(document.getElementById("qrcode"), {
		width : 200,
		height : 200
	});
	
	function msg(pid, sourceid, title) {
		if (pid == 0) {
			layer.msg('请先配置应用站点！', {
				icon : 5,
				time : 2000
			});
			return;
		}
		var url = "${pageDomain}/${oname }/user/wxshow/" + pid + "/wxn/" + sourceid + ".html";
		qrcode.clear();
		qrcode.makeCode(url);
		$("#url").text(url);
		layer.msg('正在生成二维码,请稍等...', {
			icon : 16,
			time : 2000
		}, function() {
			layer.alert($("#msg").html(), {
				title : title,
				area : [ '480px', '360px' ]
			});
		});
	}
</script>