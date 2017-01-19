<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="frame_content">
<form id="site_form" action="add_sub.action?pageid=${pageid}" method="post">
	<p>页面名称:${mgrDto.page.name }</p>
	<p>选择功能:</p>
	<s:iterator value="mgrDto.modules" var="m">
	<p>${m.name}:
			<s:iterator value="#m.features" var = "f">
				<input type="checkbox" name="mgrDto.featureids" value="${f.id}">${f.name}
			</s:iterator>
	</p>
	</s:iterator>
	<p><input type="button" value="增加" onclick="addSub(this.form)"/> <input type="button" value="取消" onclick="closeFrame()"/></p>
</form>
</div>
<s:if test='result != null && result != "" '>
	<s:if test='result == "Y"'>
		<script>
			$('#feature_div',window.parent.document).load('feature.action',{pageid:'${pageid}'});
			//加载层-默认风格
			layer.load();
			//此处演示关闭
			setTimeout(function(){
			    layer.closeAll('loading');
			}, 2000);
			setTimeout('closeFrame()',1000);
		</script>
	</s:if>
	<s:else>
		<script>parent.layer.msg('${result}',2,3);</script>
	</s:else>
</s:if>