<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<link rel="stylesheet" type="text/css" href="/diyUpload/css/webuploader.css">
<link rel="stylesheet" type="text/css" href="/diyUpload/css/diyUpload.css">
<script type="text/javascript" src="/diyUpload/js/webuploader.js"></script>
<script type="text/javascript" src="/diyUpload/js/diyUpload.js"></script>
<script type="text/javascript">
	function formsub(){
		var name=$("#name").val();
		if(name==""){
			layer.msg('请填写服务!', {icon: 5, time: 2000});
			return;
		}
		if(!um.hasContents()){
			layer.msg('请填写详细描述!', {icon: 5, time: 2000});
			return;
		}
		var content = um.getContent();
		$('#hyldesc').val(content);
		$("#form1").submit();
	}
</script>
<div class="wrap_content left_module">
	<form action="/${oname }/interact/saveYuYueService.action" id="form1" class="formview jNice" method="post">
	<input type="hidden" name="yuYueService.hyldesc" id="hyldesc">
	<input type="hidden" name="yuYueService.caid" value="${catid}">
	<div class="toolbar pb10">
  	<ul class="c_switch">
	   <li class="selected"><a href="">新增服务</a></li>
	   </ul>
	   <a href="javascript:window.history.back()" class="return" title="返回"></a>
	</div>
	<div>
		<dl>
		 	<dt>服务名称</dt>
			<dd>
				<input type="text" name="yuYueService.name" id="name"/>
			</dd>
		</dl>
		<dl>
		 	<dt>简介</dt>
			<dd>
				<textarea  maxlength="200" name="yuYueService.hydesc"></textarea>
			</dd>
		</dl>
		<dl>
			<dt>详细描述</dt>
			<dd>
			<script type="text/plain" id="myEditor" style="width:470px;height:200px;"></script>
			</dd>
		</dl>
		<dl class="clearfix">
			<input type="hidden" name="yuYueService.simg" id="simg">
			<dt>小图片</dt>
			<dd>
				<div id="demo">
					<div id="as1" ></div>
					<div id="picker1">选择图片</div>
				</div>
			</dd>
		</dl>
		<dl class="clearfix">
			<input type="hidden" name="yuYueService.img" id="img">
			<dt>大图片</dt>
			<dd>
				<div id="demo">
					<div id="as" ></div>
					<div id="picker">选择图片</div>
				</div>
			</dd>
		<dl>
	</div>
	<dl>
		<dt></dt>
		<dd>
			<input type="button" class="btn btn-primary" value="保存" onclick="formsub()"/>
		</dd>
	</dl>
	</form>
</div>
<script type="text/javascript">
	//实例化编辑器
    var um = UE.getEditor('myEditor');
	um.addListener('ready', function(editor) {
		$(".edui-btn-toolbar").css("white-space","normal");
	});
	
	$('#as').diyUpload({
		url:'/${oname}/user/h5UploadPic.action',
		success:function( data ) {
			console.info( data );
			var url = '${imgDomain}' + data.picUrl;
			$('#img').val(url);
			$('#picker').hide();
		},
		error:function( err ) {
			console.info( err );	
		},
		del:function(filename) {
			$('#img').val("");
			$('#picker').show();
		},
		auto: true,
		pick: '#picker',
		fileNumLimit:1,
		fileSizeLimit:500000 * 1024,
		fileSingleSizeLimit:50000 * 1024,
		accept:{
					title:"Images",
					extensions:"gif,jpg,jpeg,bmp,png",
					mimeTypes:"image/*"
				}
	});
	
	$('#as1').diyUpload({
		url:'/${oname}/user/h5UploadPic.action',
		success:function( data ) {
			console.info( data );
			var url = '${imgDomain}' + data.picUrl;
			$('#simg').val(url);
			$('#picker1').hide();
		},
		error:function( err ) {
			console.info( err );	
		},
		del:function(filename) {
			$('#simg').val("");
			$('#picker1').show();
		},
		auto: true,
		pick: '#picker1',
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