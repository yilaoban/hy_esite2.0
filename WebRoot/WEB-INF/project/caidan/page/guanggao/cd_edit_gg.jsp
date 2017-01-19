<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<link rel="stylesheet" type="text/css" href="/diyUpload/css/webuploader.css">
<link rel="stylesheet" type="text/css" href="/diyUpload/css/diyUpload.css">
<script type="text/javascript" src="/diyUpload/js/webuploader.js"></script>
<script type="text/javascript" src="/diyUpload/js/diyUpload.js"></script>
<script type="text/javascript">
	function formsub(){
		var title=$("#title").val();
		if(title==""){
			layer.msg('请填写标题!', {icon: 5, time: 2000});
			return;
		}
		if(!um.hasContents()){
			layer.msg('请填写内容!', {icon: 5, time: 2000});
			return;
		}
		var content = um.getContent();
		$('#content').val(content);
		$("#form1").submit();
	}
</script>
<div class="wrap_content left_module">
	<form action="/${oname }/cd-guanggao/updateGG.action" id="form1" class="formview jNice" method="post">
	<input type="hidden" name="adgg.content" id="content">
	<input type="hidden" name="adgg.id" value="${ggid }">
	<div class="toolbar pb10">
	  	<ul class="c_switch">
			<li class="selected"><a href="#">编辑广告</a></li>
		</ul>
		<a href="javascript:window.history.back()" class="return" title="返回"></a>
	</div>
	<div>
		<dl>
		 	<dt>标题</dt>
			<dd>
				<input type="text" name="adgg.title" id="title" value="${adgg.title }"/>
			</dd>
		</dl>
		
		<dl>
		 	<dt>简介</dt>
			<dd>
				<textarea  maxlength="200" name="adgg.hydesc">${adgg.hydesc }</textarea>
			</dd>
		</dl>
		<dl>
			<dt>内容</dt>
			<dd>
			<script type="text/plain" id="myEditor" style="width:470px;height:200px;">${adgg.content}</script>
			</dd>
		</dl>
		
		<dl>
			<dt>链接地址</dt>
			<dd>
				<input type="text" name="adgg.url" id="url" value="${adgg.url}">
			</dd>
		</dl>
		<dl>
	 	<dt>开始时间</dt>
			<dd>
				<input id="startTime" type="text" value="<s:date name="adgg.starttime" format="yyyy-MM-dd HH:mm:ss"/>" name="adgg.startTime" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly"  />
				<span id="startTime_"></span>
			</dd>
		</dl>
		<dl>
		 	<dt>结束时间</dt>
			<dd>
				<input id="endTime" type="text" value="<s:date name="adgg.endtime" format="yyyy-MM-dd HH:mm:ss"/>" name="adgg.endTime" class="Wdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly"  />
				<span id="endTime_"></span>
			</dd>
		</dl>
	<dl class="clearfix">
		<input type="hidden" name="adgg.img" id="img" value="${adgg.img }">
		<dt>图片</dt>
		<dd <s:if test="adgg.img == null || adgg.img == ''">style="display: none" </s:if>>
				<img src="${adgg.img}" id="img1" height="100"/>
			</dd>
		<dd>
			<div id="demo">
				<div id="as" ></div>
				<div id="picker">选择图片</div>
			</div>
		</dd>
		<dd>
			建议尺寸 640*310
		</dd>
	</dl>
	<dl>
		<dt></dt>
		<dd>
			<input type="button" value="保存" onclick="formsub()" class="btn btn-primary"/>
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
</script>