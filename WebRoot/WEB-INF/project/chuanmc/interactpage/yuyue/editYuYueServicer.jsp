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
			layer.msg('请填写姓名!', {icon: 5, time: 2000});
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
	
	function addinput(){
		 var html ='<input type="text" name="yuYueServicer.tagname"/>';
		 $("#tag").before(html);
	}
</script>
<div class="wrap_content left_module">
	<form action="/${oname }/interact/updateYuYueServicer.action" id="form1" class="formview jNice" method="post">
	<input type="hidden" name="yuYueServicer.hyldesc" id="hyldesc" value="">
	<input type="hidden" name="yuYueServicer.caid" value="${catid}">
	<input type="hidden" name="serviceid" value="${serviceid}">
	<input type="hidden" name="yuYueServicer.id" value="${serid}">
	<input type="hidden" name="swtype" value="${swtype}">
	<div class="toolbar pb10">
  	<ul class="c_switch">
	   <li class="selected"><a href="">编辑人员</a></li>
	   </ul>
	   <a href="javascript:window.history.back()" class="return" title="返回"></a>
	</div>
	<dl>
	 	<dt>姓名</dt>
		<dd>
			<input type="text" name="yuYueServicer.name" id="name" value="${yuYueServicer.name}"/>
		</dd>
	</dl>
	<dl>
	 	<dt>标签</dt>
		<dd>
			<s:if test="yuYueServicer.tagList.size() >0">
				<s:iterator value="yuYueServicer.tagList" var="t">
					<input type="text" name="yuYueServicer.tagname" value="${t.name}"/>
				</s:iterator>
			</s:if>
			<s:else>
				<input type="text" name="yuYueServicer.tagname"/>
			</s:else>
			<a onclick="addinput()" id="tag"><b>+</b>更多</a>
		</dd>
	</dl>
	
	<dl>
	 	<dt>简介</dt>
		<dd>
			<textarea  maxlength="200" name="yuYueServicer.hydesc">${yuYueServicer.hydesc}</textarea>
		</dd>
	</dl>
	<dl>
		<dt>详细描述</dt>
		<dd>
		<script type="text/plain" id="myEditor" style="width:470px;height:200px;">${yuYueServicer.hyldesc}</script>
		</dd>
	</dl>
	<dl class="clearfix">
		<input type="hidden" name="yuYueServicer.img" id="img" value="${yuYueServicer.img }">
		<dt>大图片</dt>
		<p <s:if test="yuYueServicer.img == null">style="display: none" </s:if>>
				<img src="${yuYueServicer.img}" id="img1" height="67" width="67"/>
		</p>
		<dd>
			<div id="demo">
				<div id="as" ></div>
				<div id="picker">选择图片</div>
			</div>
		</dd>
	</dl>
	<dl>
		<input type="hidden" name="yuYueServicer.simg" id="simg" value="${yuYueServicer.simg }">
		<dt>小图片</dt>
		<p <s:if test="yuYueServicer.simg == null">style="display: none" </s:if>>
				<img src="${yuYueServicer.simg}" id="img2" height="67" width="67"/>
		</p>
		<dd>
			<div id="demo2">
				<div id="as2" ></div>
				<div id="picker1">选择图片</div>
			</div>
		</dd>
	</dl>
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
			$('#img1').hide();
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
	
	
	$('#as2').diyUpload({
		url:'/${oname}/user/h5UploadPic.action',
		success:function( data ) {
			console.info( data );
			var url = '${imgDomain}' + data.picUrl;
			$('#simg').val(url);
			$('#img2').hide();
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