<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<table width="100%" cellspacing="1" cellpadding="1" border="1" class="tb_normal">
	<tr>
		<td>标题</td>
		<td>操作</td>
	</tr>
	<s:iterator value="map" id="entry">
		<tr>
			<td>${value.title }</td>
			<td><a href="javascript:loadValue('${key }')">编辑</a><i class="split">|</i><a href="javascript:void(0)">删除</a></td>
		</tr>
	</s:iterator>
</table>
<form id="mbform">
	<input type="hidden" name="siteid" value="${siteid }"/>
	<input type="hidden" name="sitesource.id" id="sourceid"/>
	<div style="display: none">
	<s:iterator value="map" id="entry">
		<input id="s_title_${key }" value="${value.title }"/>
		<input id="s_json_${key }" value="${value.json }"/>
		<input id="s_vjson_${key }" value="${value.vjson }"/>
		<input id="s_path_${key }" value="${value.path }"/>
	</s:iterator>
	</div>
	<div>
		<dl>
		 	<dd>资源标题:<input type="text" class="text-medium" placeholder="资源标题" id="title" name="sitesource.title" required="required"></dd>
		</dl>
		<dl>
		 	<dd>定义json:<textarea rows="" cols="" id="json" name="sitesource.json" style="margin: 0px;width: 546px;height: 121px;" required="required"></textarea>
			</dd>
		</dl>
		<dl>
		 	<dd>值json:<textarea rows="" cols="" id="vjson" name="sitesource.vjson" style="margin: 0px;width: 546px;height: 121px;" required="required"></textarea>
			</dd>
		</dl>
		<dl class="clearfix">
		 	<dd>上传文件：
			<input type="hidden" id="path" name="sitesource.path"/>
			<div id="as"></div>
			<div id="picker">选择文件</div>
			</dd>
		</dl>
		<dl>
		 	<dd>
		 		<input type="button" value="确定" id="sub"/>
		 		<input type="button" value="取消" onclick="closeFrame()"/>
			</dd>
		</dl>
	</div>
</form>
<script type="text/javascript">

$(document).ready(function(){
	
	loadValue();
	
	
	$('#sub').click(function(){
		var title=$("#title").val();
		var json=$("#json").val();
		var vjson=$("#vjson").val();
		var path=$("#path").val();
		var sourceid=$("#sourceid").val();
		if(sourceid==""){
			layer.msg('没有选择需要修改的资源!', {icon: 5, time: 2000});
			return;
		}
		if(title==""){
			layer.msg('请填写标题!', {icon: 5, time: 2000});
			return;
		}
		if(json==""){
			layer.msg('请填写定义json!', {icon: 5, time: 2000});
			return;
		}
		if(vjson==""){
			layer.msg('请填写值json!', {icon: 5, time: 2000});
			return;
		}
		if(path==""){
			layer.msg('请先上传文件!', {icon: 5, time: 2000});
			return;
		}
		$.ajax({
	        cache: true,
	        type: "POST",
	        url:"/${oname}/page/zjsSub.action",
	        data:$('#mbform').serialize(),// 你的formid
	        async: false,
	        error: function(request) {
	            layer.alert("Connection error",0);
	        },
	        success: function(data) {
	            if(data>0){
	            	layer.msg('更新成功~', {icon: 6, time: 1500},function(data){
	            		closeFrame();
					}); 
	            }else{
	            	layer.alert("操作失败!",0);
	            }
	        }
	    });
	});
	
	
	/*
	* 服务器地址,成功返回,失败返回参数格式依照jquery.ajax习惯;
	* 其他参数同WebUploader
	*/
	
	$('#as').diyUpload({
		url:'/${oname}/page/uploadZjs.action?siteid=${siteid}',
		success:function( data ) {
			$("#path").val(data.path);
			console.info( data );
		},
		error:function( err ) {
			console.info( err );	
		},
		del:function(filename) {
			console.info( filename );
			$("#path").val("");
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
					mimeTypes:"*"
				}
	});
	
});

function loadValue(sourceid){
	var title=$("#s_title_"+sourceid).val();
	var json=$("#s_json_"+sourceid).val();
	var vjson=$("#s_vjson_"+sourceid).val();
	var path=$("#s_path_"+sourceid).val();
	$("#sourceid").val(sourceid);
	$("#title").val(title);
	$("#json").val(json);
	$("#vjson").val(vjson);
	$("#path").val(path);
}

</script>