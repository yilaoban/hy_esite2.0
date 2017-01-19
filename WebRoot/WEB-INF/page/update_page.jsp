<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="frame_content">
<form id="page_form" <s:if test ="pageid == 0">action="/page/page_create_sub.action"</s:if><s:else> action="/page/page_update_sub.action"</s:else> method="post" class="formview jNice">
	<dl>
		<dt>名称</dt>
		<dd><input type="text" id="page_name" name="pagename" class="text-medium" value="${dto.page.name }"/></dd>
	</dl>
	<dl>
		<dt>模板类型</dt>
		<dd>
	   <label class="forradio"><input type="radio" value="Y" name="istemplate" <s:if test='dto.page.istemplate=="Y"'> checked $('#templatetype2').hide()</s:if> onclick="$('#templatetype1').show();$('#templatetype2').hide()">系统模板</label>
	   <label class="forradio"><input type="radio" value="N" name="istemplate" <s:if test='dto.page.istemplate=="N"'>checked</s:if> onclick="$('#templatetype2').show();$('#templatetype1').hide()">自定义模板</label>
	   </dd>
	</dl>
	<dl id="templatetype1" <s:if test='dto.page.istemplate=="N"'>style="display: none"</s:if>>
		<dt>选择模板</dt>
	   <dd >
	   	<s:iterator value="dto.list" var="a">
		  <label class="forradio"><input type="radio" id="muban" name="ownertempid" <s:if test='dto.page.ownertempid==#a.id'>checked</s:if> value="${a.id }">${a.name }</label>
		</s:iterator>
	   </dd>
	</dl>
	<dl  id="templatetype2" <s:if test='dto.page.istemplate=="Y"'>style="display: none"</s:if>>
		<dt>JSP名称</dt>
	   	<dd><input type="text" id="jsp_name" name="jspname" class="text-medium" value="${dto.page.jspname }" /></dd>
	   </dl>
	<input type="hidden" name="pageid" value="${pageid}">
	<input type="hidden" name="siteid" value="${siteid}">
	<dl>
		<dt></dt>
		<dd>
		<s:if test ="pageid == 0">
			<input type="button" onclick="page_sub(this.form)" value="创建">	
		</s:if>
		<s:else>
			<input type="button" onclick="page_sub(this.form)" value="保存">	
		</s:else>
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