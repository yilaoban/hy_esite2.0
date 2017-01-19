<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
  <h4 class="modal-title" id="myModalLabel">背景设置</h4>
</div>
<div class="modal-body">
<form id="styleform" name="styleform" class="formview">
	<dl>
	 	<dt>背景</dt>
	 	<dd>
	 		<div class="btn-group">
			  <button type="button" class="btn btn-default" onclick="switchBG('C',0,0)">单色</button>
			  <button type="button" class="btn btn-default" onclick="switchBG('P',0,0)">图片</button>
			<button type="button" class="btn btn-default" onclick="switchBG('M',1,'bg')">从素材库选择</button>
			<button type="button" class="btn btn-default" onclick="switchBG('W',0,0)">无</button>
			</div>
		</dd>
		<dd id="bgClrSlt" class="bgAll" <s:if test='dto.flag=="P"'>style="display:none;"</s:if>>
			<input type="radio" name="background" id="yanse" <s:if test='dto.flag!="P"'>checked="checked"</s:if> style="display:none">
			<input type="text" name="colorpicker" id="picker" placeholder="选取颜色" <s:if test='dto.flag=="C"'>value="${dto.bg }"  style="background-color:${dto.bg };border:1px solid ${dto.bg };border-right:20px solid ${dto.bg };"</s:if> />
		</dd>
		<dd id="bgImgSlt" class="bgAll" <s:if test='dto.flag!="P"'>style="display:none;"</s:if>>
			<input type="radio" name="background" id="tupian" <s:if test='dto.flag=="P"'>checked="checked"</s:if> style="display:none">
			<input type="file" name="img" id="upd_bg" onchange="ajaxFileUpload('${imgDomain}','bg')" style="display:none">
 			 <img id="img_bg" height="100" <s:if test='dto.flag=="C"'>src="/images/nopic.png"</s:if><s:else>src="${dto.bg }"</s:else> width="100"/>
 			 <input type="hidden" name="bg" id="var_bg" value="${dto.bg }">
		</dd>
		<dd class="bgAll" style="display:none;">
			<input type="radio" name="background" id="wubj" <s:if test='dto.flag=="P"'>checked="checked"</s:if> style="display:none">
		</dd>
	</dl>
</form>
</div>
<div class="modal-footer">
  <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
  <button type="button" class="btn btn-primary" onclick="onClikSave(${pcid})" data-dismiss="modal">保存</button>
</div>
<link rel="stylesheet" href="/js/colpick/colpick.css" type="text/css" />
<script type="text/javascript" src="/js/colpick/colpick.js"></script>
<script type="text/javascript">
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
</script>