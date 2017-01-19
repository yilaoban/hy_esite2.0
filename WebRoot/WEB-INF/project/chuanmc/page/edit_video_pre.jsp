<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	 $(document).ready(function() {  
           $("#category").change();
        });
         
	function checkSub(){
		var editor = CKEDITOR.instances.editor1;
	 	$("#ckvalue").val(editor.getData());
		$("#form1").submit();
	}
</script>
<div  class="wrap_content">
<form action="edit_video_Sub.action" method="post" enctype="multipart/form-data" class="formview jNice">
<input type="hidden" value="${dto.video.id }" name="contentId"/>
<input type="hidden" value="${dto.video.catid }" name="ccid"/>
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li class="selected"><a href="">编辑视频</a></li>
	  </ul>
	  <a href="javascript:window.history.back()" class="return" title="返回"></a>
	</div>
	<span id="errrSpan" style="color:red"><strong style="color:red"><s:fielderror theme="" fieldName="error"/></strong> </span>

	<dl>
	 	<dt>分类</dt>
		<dd>
			<s:iterator value="dto.ccList" var="c" status="s">
				<s:iterator value="c" var="cc">
					<s:if test='use=="Y"'><input type="text" disabled="disabled" value="${cc.name }" class="text-long"/></s:if>
				</s:iterator>
				<s:if test="!#s.last">-</s:if>
			</s:iterator>
		</dd>
	</dl>
	<div id="contentTag">
		<script type="text/javascript">
			$("#contentTag").load('/${oname}/page/contentTag.action?ccid=${dto.video.catid}&pid=${dto.video.id}');	
		</script>
	</div>
	<dl>
	 	<dt>名称</dt>
		<dd>
			<input type="text" name="title" class="text-long" value="${dto.video.title }"/><span class="must">*</span>
			<span id="errrSpan" style="color:red"><strong style="color:red"><s:fielderror theme="" fieldName="error1"/></strong> </span>
		</dd>
	</dl>
	<dl>
	 	<dt>图片</dt>
		<dd>
			<span id="himg" style="display: none"><input type="file" name="img" id="fsimg" /></span>
			<img id="img" src="${imgDomain }${dto.video.picurl }" width="120" height="100">
			<input type="hidden" name="imgurl" value="${dto.video.picurl }">
		</dd>
		<dd>
			<a id="imgtext" href='javascript:imgchange("img")'>我要上传</a>
		</dd>
	</dl>
	<dl>
	 	<dt>视频flash地址</dt>
		<dd>
			<input type="text" name="videourl" class="text-long" value="${dto.video.videourl }"/><span class="must">*</span>
			<span id="errrSpan" style="color:red"><strong style="color:red"><s:fielderror theme="" fieldName="error2"/></strong> </span>
		</dd>
	</dl>
	<dl>
	 	<dt>提交审核</dt>
		<dd>
			<s:if test='dto.video.status=="DSH"'>
				<input type="radio" name="status" value="DSH" checked="checked">是&nbsp;
					<input type="radio" name="status" value="EDT">下次编辑&nbsp;
					<input type="radio" name="status" value="CMP">直接发布
			</s:if>
			<s:if test='dto.video.status=="EDT"'>
				<input type="radio" name="status" value="DSH" >是&nbsp;
					<input type="radio" name="status" value="EDT" checked="checked">下次编辑&nbsp;
					<input type="radio" name="status" value="CMP">直接发布
			</s:if>
			<s:if test='dto.video.status=="CMP"'>
				<input type="radio" name="status" value="DSH" >是&nbsp;
					<input type="radio" name="status" value="EDT">下次编辑&nbsp;
					<input type="radio" name="status" value="CMP" checked="checked">直接发布
			</s:if>
		</dd>
	</dl>
	<dl>
	 	<dt></dt>
		<dd>
			<input type="submit" class="btn btn-primary" value="确定"/>
		</dd>
	</dl>
<!-- 
<p>
	图片链接地址:<input type="text" class="text-long" name="linkurl" value="${dto.video.linkurl }"/>
</p>
<p>
	视频地址:<input type="text" name="videourl" class="text-long" value="${dto.video.videourl }"/>
</p>
 -->
</form>
</div>