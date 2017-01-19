<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="frame_content">
<form id="activity_form" action="/page/create_activitySub.action"  method="post" class="jNice">
	<p>活动名：<input type="text" id="activity_name" name="activityname" class="text-medium" /></p>
	<p>选择版块：
		<s:iterator value="dto.modules" var = "m" status="st">
			<label class="forradio"><input name="moduleList" type="checkbox" value="${m.id }" />${m.id }${m.name}</label>
		</s:iterator>
	</p>
	<input type="hidden" name="siteid" value="${siteid }">
	<input type="button" onclick="activity_sub(this.form)" value="保存">
	<input type="button" value="取消" onclick="closeFrame()">
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