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
		url:'/${oname}/page/aptCodeUpload.action?aptid=${aptid}',
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
	
	
	$('input:radio[name="coded"]').change(function(){
		var aptid='${aptid }';
		var coded=$(this).val();
		$.post("/${oname}/interact/updateCoded.action",{"status":coded,"aptid":aptid},function(data){
		});
	});
});
</script>
<div class="wrap_content left_module">
	<div class="toolbar pb10">
		<ul class="c_switch">
			<li class="selected"><a href="javascript:void(0)">验证码信息</a></li>
		</ul>
		<form action="aptCode.action" style="float: left;" method="get">
			<input type="hidden" value="${aptid }" name="aptid">
			<input type="text" name="code" placeholder="请输入券号" value="${code }">		
			<input type="submit" value="搜索">
		</form>
		<a href="/${oname }/interact/index.action?mid=10000&omid=${sessionScope.omid}" class="return" title="返回"></a></div>
		<p>
			验证码重复使用:<input type="radio" name="coded" value="1" <s:if test="dto.apt.coded==1">checked="checked"</s:if>/>否<input type="radio" name="coded" value="2" <s:if test="dto.apt.coded==2">checked="checked"</s:if>/>是
		</p>
		<p>
		<div id="picker">上传验证码</div>
		</p>
		<span class="notice">说明：上传时，以txt文件上传，一条验证码一行</span>
		<table width="80%" class="tb_normal" border="1" cellspacing="1" cellpadding="1">
			<tr>
				<th colspan="2">总共:${dto.used+dto.least },已用:${dto.used },剩余:${dto.least }</th>
			</tr>
			<tr>
				<th>验证码</th> 
				<th>是否使用</th>
			</tr>
		<s:iterator value="dto.list" var="p">
			<tr>
				<td>${p.code }</td>
				<td>
					<s:if test='#p.status=="1"'>已用</s:if><s:else>未用</s:else>
				</td>
			</tr>
		</s:iterator>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
</div>