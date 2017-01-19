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
		url:'/${oname}/content/productCodeUpload.action?pid=${pid}',
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
		<a href="/${oname }/content/ebProductList.action?subtype=${subtype}" class="return" title="返回"></a></div>
		<p>
		<div id="picker">上传优惠券</div>
		</p>
		<span class="notice">说明：上传时，以txt文件上传，一条优惠券一行</span>
		<table width="80%" class="tb_normal" border="1" cellspacing="1" cellpadding="1">
			<tr>
				<th>优惠券</th> 
				<th>是否使用</th>
			</tr>
		<s:iterator value="dto.codeList" var="p">
			<tr>
				<td>${p.code }</td>
				<td>
					<s:if test='#p.status=="Y"'>已用</s:if><s:else>未用</s:else>
				</td>
			</tr>
		</s:iterator>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
</div>