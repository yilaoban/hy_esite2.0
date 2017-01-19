<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<form id="mbform">
	<input type="hidden" name="sitesource.id" value="${sourceid }"/>
	<div>
		<dl>
		 	<dd>资源标题:<input type="text" class="text-medium" placeholder="资源标题" id="title" value="${ss.title }" name="sitesource.title" required="required"></dd>
		</dl>
		<dl>
		 	<dd>资源级别:<input type="radio" name="sitesource.type" id="type1" value="C" <s:if test='ss.type=="C"'>checked="checked"</s:if> ><label for="type1">卡片级</label>  <input type="radio" name="sitesource.type" id="type2" value="P" <s:if test='ss.type=="P"'>checked="checked"</s:if>><label for="type2">页面级</label></dd>
		</dl>
		<dl>
		 	<dd>定义json:<textarea id="json" name="sitesource.json" style="margin: 0px;width: 546px;height: 121px;" >${ss.json }</textarea>
			</dd>
		</dl>
		<dl>
		 	<dd>值json:<textarea id="vjson" name="sitesource.vjson" style="margin: 0px;width: 546px;height: 121px;">${ss.vjson }</textarea>
			</dd>
		</dl>
		<dl class="clearfix">
		 	<dd>上传文件：
			<input type="hidden" id="path" name="sitesource.path" value="${ss.path }"/>
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
	
	
	$('#sub').click(function(){
		var title=$("#title").val();
		var json=$("#json").val();
		var vjson=$("#vjson").val();
		var path=$("#path").val();		
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
	            	layer.msg('修改成功！', {icon: 6, time: 1500},function(data){
	            		top.window.location.reload();
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
</script>