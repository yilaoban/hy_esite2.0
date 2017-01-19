<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="frame_content">
<form id="page_form" <s:if test ="pageid == 0">action="/page/page_create_sub.action"</s:if><s:else> action="/page/page_update_sub.action"</s:else> method="post" class="formview jNice">
	<dl>
		<dt>名称</dt>
		<dd id="page_dd"><input type="text" id="page_name" name="pagename" class="text-medium"/></dd>
	</dl>
	<dl>
		<dt>页面类型</dt>
		<dd>	
			<s:if test='type=="W"'>
				<label class="forradio"><input type="radio" value="C" name="stype" disabled="disabled">PC端</label>
			 	<label class="forradio"><input type="radio" value="P" name="stype" checked="checked">移动端</label>
			</s:if>
			<s:elseif test='type=="S"'>
				<label class="forradio"><input type="radio" value="C" name="stype" checked="checked">PC端</label>
			 	<label class="forradio"><input type="radio" value="P" name="stype">移动端</label>
			</s:elseif>
			<s:elseif test='type=="P"'>
				<label class="forradio"><input type="radio" value="C" name="stype" checked="checked">PC端</label>
			 	<label class="forradio"><input type="radio" value="P" name="stype">移动端</label>
			</s:elseif>
		</dd>
	</dl>
	
	<!--  
	<dl>
		<dt>模板类型</dt>
		<dd>
	   <label class="forradio"><input type="radio" value="Y" name="istemplate" checked="checked" onclick="$('#templatetype1').show();$('#templatetype2').hide()">系统模板</label>
	   <label class="forradio"><input type="radio" value="N" name="istemplate" onclick="$('#templatetype2').show();$('#templatetype1').hide()">自定义模板</label>
	   </dd>
	</dl>
	<dl id="templatetype1">
		<dt>选择模板</dt>
	   <dd >
	   	<s:iterator value="pdto.list" var="p" status='st'>
		  <label class="forradio"><input type="radio" id="muban" name="ownertempid" <s:if test='#st.index==0'>checked="checked"</s:if> value="${p.id }">${p.name }</label>
		</s:iterator>
	   </dd>
	</dl>
	<dl style="display: none" id="templatetype2">
		<dt>JSP名称</dt>
	   	<dd><input type="text" id="jsp_name" name="jspname" class="text-medium" /></dd>
	</dl>
	-->
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