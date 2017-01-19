<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<link rel="stylesheet" type="text/css" href="/diyUpload/css/webuploader.css">
<link rel="stylesheet" type="text/css" href="/diyUpload/css/diyUpload.css">
<script type="text/javascript" src="/diyUpload/js/webuploader.js"></script>
<script type="text/javascript" src="/diyUpload/js/diyUpload.js"></script>
<script type="text/javascript">
	function formsub(){
		$.ajax({
	        cache: true,
	        type: "POST",
	        url:"/${oname}/interact/gzEventSub.action",
	        data:$('#form1').serialize(),// 你的formid
	        async: false,
	        error: function(request) {
	            layer.alert("Connection error",0);
	        },
	        success: function(data) {
	            if(data>0){
	            	alert("引导用户关注已设置完成,请完成后续设置并保存!");
	            	parent.$("#gzid").val(data);
		            closeFrame();
	            }
	        }
	    });
	}
</script>
<form action=""  enctype="multipart/form-data" method="post" class="formview jNice" id="form1">
	<input type="hidden" name="id" value="${gzEvent.id }"/>
	<input type="hidden" name="keyMsg.id" value="${gzEvent.msg.id }"/>
	<dl>
		<dt>关注引导页链接</dt>
		<dd>
			<input id="title" type="text" class="text-medium" name="furl" value="${gzEvent.link }"/>
		</dd>
	</dl>
	<dl>
		<dt>标题</dt>
		<dd>
			<input id="title" type="text" class="text-medium" name="keyMsg.title" value="${gzEvent.msg.title }"/>
		</dd>
	</dl>
	<dl>
		<dt>描述</dt>
		<dd>
			<textarea name="keyMsg.description">${gzEvent.msg.description }</textarea>
		</dd>
	</dl>
	<dl>
		<dt>url</dt>
		<dd>
			<input id="title" type="text" class="text-medium" name="keyMsg.url" value="${gzEvent.msg.url }"/>
		</dd>
	</dl>
	<dl>
		<dt>原图</dt>
		<dd>
			<s:if test='gzEvent.msg.pic_url != null&&gzEvent.msg.pic_url!="" '><img src="${gzEvent.msg.pic_url }" height="40" width="40"></s:if><s:else><img src="/images/nopic.png" height="40" width="40"></s:else>
			<input type="hidden" name="keyMsg.pic_url" value="${gzEvent.msg.pic_url }" id="pic"/>
		</dd>
	</dl>
	<dl class="clearfix">
		<dt>替换图片</dt>
		<dd>
			<div id="as"></div>
			<div id="picker">选择图片</div>
			<script type="text/javascript">
		
		/*
		* 服务器地址,成功返回,失败返回参数格式依照jquery.ajax习惯;
		* 其他参数同WebUploader
		*/
		
		$('#as').diyUpload({
			url:'/${oname}/user/h5UploadPic.action',
			success:function( data ) {
				$("#pic").val('${imgDomain }'+data.picUrl);
				console.info( data );
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
						title:"Images",
						extensions:"gif,jpg,jpeg,bmp,png",
						mimeTypes:"image/*"
					}
		});
		</script>
		</dd>
		<dl>
			<dt></dt>
			<dd>
				<input type="button" value="提交" onclick="formsub()">
				<input type="button" value="取消" onclick="closeFrame()">
			</dd>
		</dl>
	</dl>
</form>