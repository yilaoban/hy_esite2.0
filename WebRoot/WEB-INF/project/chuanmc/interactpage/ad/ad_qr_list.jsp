<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="/js/jquery.cookie.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#qwd').typeahead({
		    source: function (query, process) {
		    	var parameter = {"wd": query,"type":"QHO"};
		        $.post('/${oname}/interact/adajax_getWdList.action', parameter, function (data) {
		            process(data);
		        });
		    }
		});
		$('#wd').typeahead({
		    source: function (query, process) {
		        var parameter = {"wd": query,"type":"BBN"};
		        $.post('/${oname}/interact/adajax_getWdList.action', parameter, function (data) {
		            process(data);
		        });
		    }
		});
		$('#media').typeahead({
		    source: function (query, process) {
		        var parameter = {"media": query};
		        $.post('/${oname}/interact/adajax_getMediaList.action', parameter, function (data) {
		            process(data);
		        });
		    }
		});
		
		
		$("#saveWay").click(function(){
			var qwd=$("#qwd").val();
			var wd=$("#wd").val();
			var media=$("#media").val();
			var gid=$("input:radio[name='gid']:checked").val();
			var url=$("#wayurl").val();
			var fsurl=$("#fsurl").val();
			if(qwd==""||wd==""||media==""){
				layer.msg('参数不能为空!', {icon: 5, time: 2000});
			}else if(!gid){
				layer.msg('没有选择应用站点!', {icon: 5, time: 2000});
			}else{
				 $.post('/${oname}/interact/adajax_saveAdWay.action', {"qwd":qwd,"wd":wd,"media":media,"gid":gid,"url":url,"fsurl":fsurl}, function (data) {
					if(data==-1){
						layer.msg('媒体不能为空!', {icon: 5, time: 2000});
					}else if(data==-2){
						layer.msg('期号不能为空!', {icon: 5, time: 2000});
					}else if(data==-3){
						layer.msg('版本不能为空!', {icon: 5, time: 2000});
					}else if(data==-4){
						layer.msg('添加失败,请稍后再试!', {icon: 5, time: 2000});
					}else{
						layer.msg('添加成功！请稍等……', {icon: 6, time: 1500}, function(){
							window.parent.location.reload();
						}); 
					}
				 });
				
			}
		});
	});
	function delWay(id){
		var layerid=layer.confirm('确定删除吗?',{title:"确认删除"},function(){
			$.post("/${oname}/interact/delWay.action",{inajax:1,wayid:id},function(data){
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

function addWay(){
	var	title="新增维度";
		var index = layer.open({
				type : 1,
				area : ['550px','500px'],
				title : [title,true],
				content: $('#myModal')
			});
}

function adWaygg(oname,wayid){
	$.cookie('ggCookie', '');
	var srcString = "/"+oname+"/interact/adWayggSet.action?wayid="+wayid;
	var	title="广告投放";
	layer.open({
			type : 2,
			area : ['450px','490px'],
			title : [title,true],
			content: srcString
		});
}
</script>
<div class="wrap_content">
	<div  class="toolbar pb10">
		<form action="" class="formview jNice left">
			媒体:<input type="text" class="text-medium" name="media" value="${media }"/>	
			期号:	<input type="text" class="text-medium" name="qwd" value="${qwd }"/>
			版本:<input type="text" class="text-medium" name="wd" value="${wd }"/>
			<input type="submit" class="btn btn-info btn-sm" value="搜索"/>	
		</form>
		<a class="btn btn-primary" href="javascript:void(0);" onclick="addWay()">新增</a>
	</div>
	<table width="100%" border="1" cellspacing="1" cellpadding="1" class="tb_normal">
		<tr>
			<td>媒体</td>
			<td>期号</td>
			<td>版本</td>
			<td>操作</td>
		</tr>
		<s:iterator value="dto.list"  var="l">
		<tr>
			<td>${l.media.name }</td>			
			<td>${l.qwd.name }</td>			
			<td>${l.wd.name }</td>			
			<td>
				<a href="javascript:showAdWay('${oname }','${l.id }')">编辑</a>
				<a href="javascript:adWaygg('${oname }','${l.id }')">投放广告</a>
				<a href="javascript:showQr('${l.pageid }','${l.id }')">二维码</a>
				<a href="javascript:delWay(${l.id})">删除</a>
			</td>			
		</tr>
		</s:iterator>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>
        	<form class="formview jNice" id="myModal" style="display: none;">
        		<dl>
        			<dt>媒&nbsp;&nbsp;&nbsp;&nbsp;体:</dt>
		        	<dd><input type="text" class="text-medium" data-provide="typeahead" id="media" autocomplete="off"/></dd>
        		</dl>
        		<dl>
        			<dt>期&nbsp;&nbsp;&nbsp;&nbsp;号:</dt>
					<dd><input type="text" class="text-medium" data-provide="typeahead" id="qwd"  autocomplete="off"/></dd>
        		</dl>
				<dl>
        			<dt>版&nbsp;&nbsp;&nbsp;&nbsp;本:</dt>
					<dd><input type="text" class="text-medium" data-provide="typeahead" id="wd"  autocomplete="off"/></dd>
        		</dl>
				<dl>
        			<dt>默认url:</dt>
					<dd><input type="text" class="text-medium" id="wayurl" autocomplete="off"/></dd>
        		</dl>
				<dl>
        			<dt>粉丝url:</dt>
					<dd><input type="text" class="text-medium" id="fsurl" autocomplete="off"/><font color="red">注:粉丝url为空跳默认url</font></dd>
        		</dl>
				<dl>
	       			<dt>选择应用站点:</dt>
	       			<dd>
	       				<s:iterator value="list" var="l">
						<input type="radio" name="gid" value="${l.id }"/>${l.groupname }
					</s:iterator>
	       			</dd>
	       		</dl>
      			<dl>
        			<dt>
        			</dt>
        			<dd>
				        <button type="button" class="btn btn-primary" id="saveWay">保存</button>
				        <button type="button" class="btn btn-default" onclick="layer.closeAll('page');">取消</button>
        			</dd>
        		</dl>
        	</form>
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
function showQr(pageid,wayid) {
	var url = "${pageDomain}/${oname }/user/wxshow/" + pageid + "/wxn/"+wayid+".html";
	qrcode.clear();
	qrcode.makeCode(url);
	$("#url").text(url);
	layer.msg('正在生成二维码,请稍等...', {
		icon : 16,
		time : 2000
	}, function() {
		layer.alert($("#msg").html(), {
			area : [ '480px', '360px' ]
		});
	});
}
</script>