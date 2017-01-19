<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<link rel="stylesheet" type="text/css" href="/diyUpload/css/webuploader.css">
<link rel="stylesheet" type="text/css" href="/diyUpload/css/diyUpload.css">
<script type="text/javascript" src="/diyUpload/js/webuploader.js"></script>
<script type="text/javascript" src="/diyUpload/js/diyUpload.js"></script>
<script type="text/javascript">
	function saveSettingForum(){
		var nickname = $('#nickname').val().trim();
		if(nickname == ""){
			layer.msg("请填写官方昵称");
			return;
		}
		$.ajax({
	        cache: true,
	        type: "POST",
	        url:'/${oname}/bbs/saveBaseSetting.action',
	        data:$('#myfrom1').serialize(),// 你的formid
	        async: false,
	        error: function(request) {
	            layer.alert("Connection error",0);
	        },
	        success: function(data) {
	            if(data > 0){
	            	layer.msg('保存成功！请稍等……', {icon: 6, time: 1500}, function(){
						window.parent.location.reload();
					}); 
	            }else{
	            	layer.alert("操作失败!",0);
	            }
	        }
	    });
		
	}
</script>
	<form action="/${sessionScope.account.owner.entity}/bbs/saveBaseSetting.action" method="post" id="myfrom1" class="formview jNice">
		<input type="hidden" name="forum.id" value="${forumid }" id="baseSetting">	
		<input type="hidden" name="forum.img" id="img" value="${dto.bbsForum.img}">
		<dl>
		 	<dt>官方昵称</dt>
			<dd>
				<input type="text" class="text-medium" name="forum.nickname" id="nickname" placeholder="官方发帖或回复粉丝帖时显示" value="${dto.bbsForum.nickname}">
			</dd>
		</dl>
		<dl class="clearfix">
		 	<dt>头像</dt>
		 	<s:if test="dto.bbsForum.img != null">
			<dd>
				<img src="${dto.bbsForum.img}" id="img1" height="67" width="67"/>
			</dd>
			</s:if>
			<dd>
				<div id="demo" class="">
					<div id="as" ></div>
					<div id="picker"><span class="glyphicon glyphicon-picture" aria-hidden="true"></span> 选择图片上传</div>
				</div>
			</dd>
		</dl>
		<dl>
		 	<dt></dt>
			<dd>
				  <button type="button" class="btn btn-primary" onclick="saveSettingForum()">保存</button>
				  <button type="button" class="btn btn-default"  onclick="closeFrame()">关闭</button>
			</dd>
		</dl>
	</form>
<script type="text/javascript">

/*
* 服务器地址,成功返回,失败返回参数格式依照jquery.ajax习惯;
* 其他参数同WebUploader
*/

$('#as').diyUpload({
	url:'/${oname}/user/h5UploadPic.action',
	success:function( data ) {
		console.info( data );
		var url = '${imgDomain}' + data.picUrl;
		$('#img').val(url);
		$('#img1').hide();
	},
	error:function( err ) {
		console.info( err );	
	},
	del:function(filename) {
		$('#img').val("");
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