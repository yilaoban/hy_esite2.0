<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="frame_content">
<form id="site_form" <s:if test ="siteid == 0">action="/loadpage/loadPageSub.action"</s:if><s:else> action="/loadpage/siteLoadPageUpdateSub.action"</s:else> method="post" class="jNice">
	<p>名称：<input type="text" id="site_name" name="sitename" class="text-medium" value="${sitename }" /></p>
	<s:if test="siteid == 0">
	<p>应用：<select name="appid"><s:iterator value="dto.apps" var="a"><option value="${a.id}">${a.name}</option> </s:iterator> </select></p>
	</s:if>
	<p>所含版块：
		<s:iterator value="dto.modules" var = "m" status="st">
			<s:if test="moduleList.contains(#m.id)">
				<label class="forradio"><input name="moduleList" type="checkbox" value="${m.id }" checked="checked"/>${m.id }${m.name}</label>
			</s:if>
			<s:else>
				<label class="forradio"><input name="moduleList" type="checkbox" value="${m.id }"/>${m.id }${m.name}</label>
			</s:else>
		</s:iterator>
	</p>
	<s:if test ="siteid == 0">
		<input type="button" onclick="site_sub(this.form)" value="创建">
	</s:if>
	<s:else>
		<input type="hidden" name="siteid" value="${siteid}">
		<input type="button" onclick="site_sub(this.form)" value="保存">	
	</s:else>
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