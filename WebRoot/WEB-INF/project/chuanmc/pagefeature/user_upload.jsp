<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<style>
	.input-file {overflow:hidden;position:relative;background:url(/images/test1/upload.png) no-repeat;width:340px;height:50px;display:inline-block;margin:0 auto;}
	.input-file input{opacity:0;filter:alpha(opacity=0);font-size:100px;position:absolute;top:0;right:0;}
</style>
<form id="page_form" method="post" action="/user/uploadSub.action" enctype="multipart/form-data">
<div>
<div id="upload" style="text-align:center;"> 
<s:if test='result != null && result != "" '>
		<s:if test='result == "Y"'>
			<p style="font-size:20px;color:#000;padding:0;">上传成功！</p>
			<p><a href="javascript:void(0);" onclick="location.href='/user/upload.action?featureid=106&fid=${fid }&pageid=${pageid }'">还要上传</a></p>
		</s:if>
		<s:else>
			<p style="font-size:20px;color:#000;padding:0;">上传失败！原因：${result}</p>
			<p><a href="javascript:void(0);" onclick="location.href='/user/upload.action?featureid=106&fid=${fid }&pageid=${pageid }'">重新上传</a></p>
		</s:else>
</s:if>
<s:else>
		<span class="input-file"><input type="file" name="img" onchange="sub(this.form)"></span>
		<p>推荐上传分辨率为500*500的JPG图片</p>
</s:else>
</div>
<input type="hidden" name="featureid" value="${featureid }">
<input type="hidden" name="fid" value="${fid }">
<input type="hidden" name="pageid" value="${pageid }">
</div>
</form>
