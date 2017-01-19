<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<link rel="stylesheet" type="text/css" href="/diyUpload/css/webuploader.css">
<link rel="stylesheet" type="text/css" href="/diyUpload/css/diyUpload.css">
<script type="text/javascript" src="/diyUpload/js/webuploader.js"></script>
<script type="text/javascript" src="/diyUpload/js/diyUpload.js"></script>
<script src="/js/bootstrap/js/bootstrap-typeahead.js"></script>
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
  <h4 class="modal-title" id="myModalLabel">页面设置</h4>
</div>
<div class="modal-body">
<form id="btForm" action="/page/saveBt.action?pageid=${pageid}" class="blockview">
	<dl <s:if test='page.type=="G"'>style="display: none"</s:if>>
	 	<dt>标题</dt>
		<dd>
			<input type="text" id="title" name="title" class="text-fix form-control" value="${ptp.title }">
		</dd>
	</dl>
	<dl <s:if test='page.type=="G"'>style="display: none"</s:if>>
	 	<dt>关键字</dt>
		<dd>
			<input type="text" id="keywords" name="keywords"  class="text-fix form-control" value="${ptp.keywords }">
		</dd>
	</dl>
	<dl <s:if test='page.type=="G"'>style="display: none"</s:if>>
	 	<dt>描述</dt>
		<dd>
			<input type="text" id="description" name="description" class="text-fix form-control" value="${ptp.description }">
		</dd>
	</dl>
	<div id="contentTag">
		<script type="text/javascript">
			$("#contentTag").load('/${oname}/page/pageTag.action?pageid=${pageid}');	
		</script>
	</div>
	<dl style="display: none">
	 	<dt>背景</dt>
	 	<dd>
	 		<div class="btn-group">
			  <button type="button" class="btn btn-default" onclick="switchBG('C',0,0)">单色</button>
			  <button type="button" class="btn btn-default" onclick="switchBG('P',0,0)">图片</button>
			<button type="button" class="btn btn-default" onclick="switchBG('M',1,'bg')">从素材库选择</button>
			</div>
		</dd>
		<dd id="bgClrSlt" class="bgAll" <s:if test='flag=="P"'>style="display:none;"</s:if>>
			<input type="radio" name="background" id="yanse" <s:if test='flag!="P"'>checked="checked"</s:if> style="display:none">
			<input type="text" name="colorpicker" id="picker" <s:if test='flag=="C"'>value="${ptp.background }" style="border:1px solid ${ptp.background };border-right:20px solid ${ptp.background };"</s:if> />
		</dd>
		<dd id="bgImgSlt" class="bgAll" <s:if test='flag!="P"'>style="display:none;"</s:if>>
			<input type="radio" name="background" id="tupian" <s:if test='flag=="P"'>checked="checked"</s:if> style="display:none">
			<input type="file" name="img" id="upd_bg" onchange="ajaxFileUpload('${imgDomain}','bg')" style="display:none">
 			<img id="img_bg" height="100" <s:if test='flag=="C"'>src="/images/nopic.png"</s:if><s:else> src="${ptp.background }"</s:else> width="100"/>
 			 <input type="hidden" name="bg" id="var_bg" value="${ptp.background }">
		</dd>
	</dl>
	<dl style="display: none">
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
		url:'/hy/user/musicUpload.action',
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

		$('#picker').colpick({
		layout:'hex',
		submit:0,
		onChange:function(hsb,hex,rgb,el,bySetColor) {
			$(el).css('border-color','#'+hex);
			if(!bySetColor) $(el).val('#'+hex);
		}
		}).keyup(function(){
			$(this).colpickSetColor(this.value);
		});
		
	function delmusic(){
		$("#showmname").html("");
		$("#uploadMusic").val("");
	}
</script>