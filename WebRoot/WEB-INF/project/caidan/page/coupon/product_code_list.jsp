<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<link rel="stylesheet" type="text/css" href="/diyUpload/css/webuploader.css">
<link rel="stylesheet" type="text/css" href="/diyUpload/css/diyUpload.css">
<script type="text/javascript" src="/diyUpload/js/webuploader.js"></script>
<script type="text/javascript" src="/diyUpload/js/diyUpload.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	/*
	* 服务器地址,成功返回,失败返回参数格式依照jquery.ajax习惯;
	* 其他参数同WebUploader
	*/
	
	$('#as').diyUpload({
		url:'/${oname}/cd-coupon/addCodePre.action?pid=${pid}',
		success:function( data ) {
			layer.msg('操作完成!', {icon: 6, time: 2000},function(){
				window.location.reload();
			});
		},
		error:function( err ) {
			console.info( err );	
		},
		del:function(filename) {
			console.info( filename );
		},
		auto: true,
		pick: '#picker',
		//chunked:true,
		// 分片大小
		//chunkSize:512 * 1024,
		//最大上传的文件数量, 总文件大小,单个文件大小(单位字节);
		fileNumLimit:1,
		fileSizeLimit:500000 * 1024,
		fileSingleSizeLimit:50000 * 1024,
		accept:{
					extensions: 'txt',
					mimeTypes:"text/plain"
				}
	});
	
});
function delcode(id){
	layer.confirm('确定删除吗?',function(){
		$.post("/${oname}/cd-coupon/delCouponCode.action",{"id":id},function(data){
			if(data == 1){
				layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
					window.parent.location.reload();
				}); 
			}else{
				layer.msg('删除失败！请重试……', {icon: 5, time: 2000});
			}
		});
	});
}
function clearcode(){
	layer.confirm('确定清空券码吗,已有人兑换的无法清除.',function(){
		$.post("/${oname}/cd-coupon/clearCouponCode.action",{"productid":'${pid}'},function(data){
			if(data>0){
				layer.msg('清除中！请稍等……', {icon: 6, time: 1500}, function(){
					window.location.reload();
				}); 
			}else{
				layer.msg('清除失败！请重试……', {icon: 5, time: 2000});
			}
		});
	});	
}
</script>
<div class="wrap_content left_module">
	<div class="toolbar pb10">
		<ul class="c_switch">
			<li class="selected"><a href="javascript:void(0)">优惠券信息</a></li>
		</ul>
		<form action="" style="float: left;" method="get">
			<input type="hidden" value="${pid }" name="pid">
			<input type="hidden" value="${subtype }" name="subtype">
			<input type="text" name="code" placeholder="请输入券号" value="${code }">		
			<input type="submit" value="搜索">
		</form>
		<a href="/${oname }/cd-coupon/index.action" class="return" title="返回"></a></div>
		<p>
		<a href="addCodePre.action?pid=${pid }">上传券号</a><i class="split">|</i>
		<a href="javascript:addcode();">填写券号</a><i class="split">|</i>
		<a href="javascript:clearcode();">清空券码</a>
		<span class="notice">说明：上传时，以txt文件上传，一条优惠券一行</span>
		</p>
		<table width="80%" class="tb_normal" border="1" cellspacing="1" cellpadding="1">
			<tr>
				<th>优惠券</th> 
				<th>密码</th>
				<th>使用数</th>
				<th>操作</th>
			</tr>
		<s:iterator value="dto.codeList" var="p">
			<tr>
				<td>${p.code }</td>
				<td>${p.password }</td>
				<td>${p.used }</td>
				<td><s:if test="#p.used==0"><a href="javascript:delcode(${p.id })">删除</a></s:if>
					<s:else>已使用</s:else>
				</td>
			</tr>
		</s:iterator>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
</div>
<form class="formview jNice" id="myModal" style="display: none;">
	<dl>
		<dt>券号:</dt>
    	<dd><input type="text" class="text-medium" data-provide="typeahead" id="code" autocomplete="off"/></dd>
	</dl>
	<dl>
    	<dt>密码:</dt>
		<dd><input type="text" class="text-medium" data-provide="typeahead" id="password"  autocomplete="off"/></dd>
	</dl>
	<dl>
    	<dt>数量:</dt>
		<dd><input type="number" min="0" class="text-medium" data-provide="typeahead" id="total"  autocomplete="off"/></dd>
	</dl>
	<dl>
		<dd>
		    <button type="button" class="btn btn-primary" id="saveCode">保存</button>
		    <button type="button" class="btn btn-default" onclick="layer.closeAll('page');">取消</button>
		</dd>
	</dl>
</form>
<script type="text/javascript">
	function addcode(){
		var	title="填写券号";
			var index = layer.open({
					type : 1,
					area : ['450px','350px'],
					title : [title,true],
					content: $('#myModal')
				});
	}
	
	$("#saveCode").click(function(){
		var code=$("#code").val();
		var password=$("#password").val();
		var total=$("#total").val();
		var pid =${pid };
		if(code==""){
			layer.msg('券号不能为空!', {icon: 5, time: 2000});
		}else{
			 $.post('/${oname}/cd-coupon/saveCode.action', {"code":code,"password":password,"total":total,"pid":pid}, function (data) {
				if(data==1){
					layer.msg('添加成功！请稍等……', {icon: 6, time: 1500}, function(){
						window.parent.location.reload();
					}); 
				}
			 });
			
		}
	});
</script>