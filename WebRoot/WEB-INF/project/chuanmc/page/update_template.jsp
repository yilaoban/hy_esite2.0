<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="frame_content">
<form id="page_form" action="update_template.action"  method="post" class="formview jNice" enctype="multipart/form-data">
	<input type="hidden" name="id" value="${id}">
	<dl>
		<dt>选择色调</dt>
		<dd>
			<input class="color text-medium" name="style" onchange="document.getElementById('color').style.backgroundColor = '#'+this.color" value="${my.t.bgcolor}">
		</dd>
	</dl>
	<dl>
		<dt></dt>
		<dd id="color">
			<img src="${imgDomain }${my.t.background }" height="180" width="200">
		</dd>
	</dl>
	<dl>
		<dt>图片上传</dt>
		<dd>
			<input type="file" name="pic"><input type="hidden" name="oldpic" value="${my.t.background }">
		</dd>
	</dl>
	<dl>
		<dt></dt>
		<dd>
			<input type="submit"  value="保存">	
			<input type="button" value="关闭" onclick="closeFrame()">
		</dd>
	</dl>
</form>
</div>
<s:if test='result != null && result != "" '>
	<s:if test='result == "Y"'>
		<script>
			parent.layer.load(2,2);
			setTimeout('parent.layer.msg("操作成功!",2,1)',2000);
			var i = parent.layer.getFrameIndex();
			//parent.layer.close(i); 
			setTimeout("window.parent.location.reload()",3000);
		</script>
	</s:if>
	<s:else>
		<script>parent.layer.msg('${result}',2,3);</script>
	</s:else>
</s:if>