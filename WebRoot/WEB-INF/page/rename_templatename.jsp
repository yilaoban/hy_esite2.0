<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="frame_content">
<form id="page_form" action="/template/rename_template.action"  method="post" class="formview jNice">
	<dl>
		<dt>模板名</dt>
		<dd><input type="text" id="page_name" name="name" class="text-medium" value="${dto.mytemplate.name }"/></dd>
	</dl>
	<input type="hidden" name="id" value="${id}">
	<dl>
		<dd>
		<input type="submit"  value="保存">	
		<input type="button" value="取消" onclick="closeFrame()">
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