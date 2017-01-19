<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<link rel="stylesheet" type="text/css" href="/diyUpload/css/webuploader.css">
<link rel="stylesheet" type="text/css" href="/diyUpload/css/diyUpload.css">
<script type="text/javascript" src="/diyUpload/js/webuploader.js"></script>
<script type="text/javascript" src="/diyUpload/js/diyUpload.js"></script>
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
  <h4 class="modal-title" id="myModalLabel">背景设置</h4>
</div>
<div class="modal-body" style="height:auto;overflow:hidden;">
<form id="btForm" action="/page/saveBt.action?pageid=${pageid}" class="blockview">
<input type="hidden" id="title" name="title"  value="${ptp.title }">
<input type="hidden" id="keywords" name="keywords" value="${ptp.keywords }">
<input type="hidden" id="description" name="description" value="${ptp.description }">
	<dl>
	 	<dt>背景</dt>
	 	<dd>
	 		<div class="btn-group">
			  <button type="button" class="btn btn-default" onclick="switchBG('C',0,0)">单色</button>
			  <button type="button" class="btn btn-default" onclick="switchBG('P',0,0)">图片</button>
			<!-- <button type="button" class="btn btn-default" onclick="switchBG('M',1,'bg')">从素材库选择</button> -->
			<button type="button" class="btn btn-default" onclick="switchBG('W',0,0)">无</button>
			</div>
		</dd>
		<dd id="bgClrSlt" class="bgAll" <s:if test='flag=="P"'>style="display:none;"</s:if>>
			<input type="radio" name="background" id="yanse" <s:if test='flag!="P"'>checked="checked"</s:if> style="display:none">
			<input type="text" name="colorpicker" id="picker" placeholder="选取颜色" <s:if test='flag=="C"'>value="${ptp.background }" style="background-color:${ptp.background };border:1px solid ${ptp.background };border-right:20px solid ${ptp.background };"</s:if> />
		</dd>
		<dd id="bgImgSlt" class="bgAll" <s:if test='flag!="P"'>style="display:none;"</s:if>>
			<input type="radio" name="background" id="tupian" <s:if test='flag=="P"'>checked="checked"</s:if> style="display:none">
			<input type="file" name="img" id="upd_bg" onchange="ajaxFileUpload('${imgDomain}','bg')" style="display:none">
 			<img id="img_bg" height="100" <s:if test='flag=="C"'>src="/images/nopic.png"</s:if><s:else> src="${ptp.background }"</s:else> width="100"/>
 			 <input type="hidden" name="bg" id="var_bg" value="${ptp.background }">
		</dd>
		<dd class="bgAll" style="display:none;">
			<input type="radio" name="background" id="wubj" <s:if test='dto.flag=="P"'>checked="checked"</s:if> style="display:none">
		</dd>
	</dl>
	<dl>
		<dt>背景音乐</dt>
		<dd>
			<div style="width:100%;" id="demo">
			<div style="float:left;margin: 10px 0 0;" id="as" ></div>
			<div style="float:left;margin:6px 20px 0 0;" id="showmname"><s:if test='ptp.uploadMusic != null && ptp.uploadMusic != ""'><img src="/images/zujian/qq.png" title="${ptp.musicname }" /><a style="margin-left:15px;" onclick="delmusic()">删除</a></s:if></div>
			<div style="float:left;" id="pickerm">选择音乐</div>
			</div>
			<input type="hidden" id="uploadMusic" name="uploadMusic" value="${ptp.uploadMusic }" />
			<input type="hidden" id="musicname" name="musicname" />
		</dd>
	</dl>
	<dl>
		<dt>滑动箭头</dt>
		<dd >
			<img style="background-color:#E7E7E7;" id="uploadArrowShow" <s:if test='ptp.uploadArrow != null && ptp.uploadArrow != ""'>src="${ptp.uploadArrow}"</s:if> width="100px"/>
			<div style="width:100%;height:auto;overflow:hidden;float:left;">
				<div style="float:left;margin: 10px 0 0;" id="as2" ></div>
				<div style="clear:both;" id="pickerm2">上传图片</div>
			</div>
			<div><input id="noimg" <s:if test='ptp.uploadArrow == ""'>checked="checked"</s:if> type="checkbox" />无图片</div>
			<input type="hidden" id="uploadArrow" name="uploadArrow" value="${ptp.uploadArrow }" />
		</dd>
		<dd>
			<a style="border:1px solid #00a0e9;background-color:#00a0e9;color:#fff;cursor:pointer;" onclick="showimg()"><b>+</b>更多</a>
			<div id="showimg">
				<ul>
					<li><img src="/images/arrowBtn.png" style="background-color:#E7E7E7;" /></li>
					<li><img src="/images/common/up1.png" style="background-color:#E7E7E7;" /></li>
					<li><img src="/images/common/up2.png" style="background-color:#E7E7E7;" /></li>
					<li><img src="/images/common/up3.png" style="background-color:#E7E7E7;" /></li>
					<li><img src="/images/common/up4.png" style="background-color:#E7E7E7;" /></li>
					<li><img src="/images/common/up5.png" style="background-color:#E7E7E7;" /></li>
					<li><img src="/images/common/up6.png" style="background-color:#E7E7E7;" /></li>
					<li><img src="/images/common/up7.png" style="background-color:#E7E7E7;" /></li>
				</ul>
				<script type="text/javascript">	
					$("#showimg ul li").on("click",function(){
						var src = $(this).find("img").attr("src");
						$("#uploadArrow").val(src);
						$("#uploadArrowShow").attr('src',src); 
					});
					
					$('#noimg').click(function(){  
					    if($(this).attr('checked')){  
					        $("#uploadArrowShow").hide();
					    }else{
					    	$("#uploadArrowShow").show();
					    }
					});
				</script>
			</div>
		</dd>
	</dl>
</form>
</div>
<div class="modal-footer">
  <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
  <button type="button" class="btn btn-primary" onclick="saveBt(${pageid})" data-dismiss="modal">保存</button>
</div>
<link rel="stylesheet" href="/js/colpick/colpick.css" type="text/css" />
<script type="text/javascript" src="/js/colpick/colpick.js"></script>
<script type="text/javascript">

	$('#as').diyUpload({
		url:'/${oname}/user/musicUpload.action',
		success:function( data ) {
			console.info( data );
			$("#uploadMusic").val(data.picUrl);
			$("#musicname").val(data.picName);
			$("#showmname").hide();
		},
		error:function( err ) {
			console.info( err );	
		},
		del:function(filename) {
			alert(filename);	
		},
		auto: true,
		pick: '#pickerm',
		//chunked:true,
		// 分片大小
		//chunkSize:512 * 1024,
		//最大上传的文件数量, 总文件大小,单个文件大小(单位字节);
		fileNumLimit:1,
		fileSizeLimit:500000 * 1024,
		fileSingleSizeLimit:50000 * 1024,
		accept:{
					title:"Images",
					extensions:"mp3",
					mimeTypes:"*"
				}
	});
	
	$('#as2').diyUpload({
		url:'/${oname}/user/h5UploadPic.action',
		success:function( data ) {
			$('input[name="uploadArrow"]').val('${imgDomain}'+data.picUrl);
			$("#uploadArrowShow").hide();
		},
		error:function( err ) {
			console.info( err );	
		},
		del:function(filename) {
			$('input[name="uploadArrow"]').val('${ptp.uploadArrow}');
			$("#uploadArrowShow").show();	
		},
		auto: true,
		pick: '#pickerm2',
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

		$('#picker').colpick({
		layout:'hex',
		submit:0,
		onChange:function(hsb,hex,rgb,el,bySetColor) {
			$(el).css('border-color','#'+hex);
			$(el).css('background-color','#'+hex);
			if(!bySetColor) $(el).val('#'+hex);
		}
		}).keyup(function(){
			$(this).colpickSetColor(this.value);
		});
		
	function delmusic(){
		$("#showmname").html("");
		$("#uploadMusic").val("");
	}
	
	function showimg(){
	if($('#showimg').is(':hidden')){
		$("#showimg").show();
	}else{
		$("#showimg").hide();
	}
} 
</script>