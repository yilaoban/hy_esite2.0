<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<div class="iframe_body">
<form action="shareSub.action" id="form" name="form" method="post" enctype="multipart/form-data">
	<input type="hidden" value="${pageid }" id="pageid" name="pageid">
	<p>名称：<input type="text" name="templatename"></p>
	<p>缩略图：<input type="file" name="pic"></p>
	<p><input type="submit" value="提交"></p>
</form>
</div>
<s:if test='result != null && result != "" '>
	<s:if test='result == "Y"'>
		<script>
			parent.layer.load(2,2);
			setTimeout('parent.layer.msg("操作成功!",2,1)',2000);
			var i = parent.layer.getFrameIndex();
			setTimeout("window.parent.location.reload()",3000);
		</script>
	</s:if>
	<s:else>
		<script>parent.layer.msg('${result}',2,3);</script>
	</s:else>
</s:if>