<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content left_module">
<form id ="template_form">
	<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
		<thead>
			<tr>
				<td>模板名</td>
				<td>PC端缩略图</td>
				<td>PHONE端缩略图</td>
				<td>行业</td>
				<td>模板类型</td>
				<td>操作</td>
			</tr>
		</thead>
		<s:iterator value="dto.myTemplateList" var="t">
		<tr>
			<input type="hidden" name="id" value="${t.id }">
			<td>${t.name}</td>
			<td>
				<a href="${t.cimg }" rel="gallery"  class="image_gall"><img src="${t.cimg }" height="100"/></a>
			</td>
			<td>
				<a href="${t.pimg }" rel="gallery"  class="image_gall"><img src="${t.pimg }" height="100"/></a>
			</td>
			<td>${t.categoryName }</td>
			<td><s:if test='#t.type=="W"'>微官网</s:if><s:if test='#t.type=="P"'>微产品</s:if><s:if test='#t.type=="E"'>微活动</s:if></td>
			<td><a href="javascript:void(0);" onclick="changeTemplateName(${t.id },'${t.name }')">重命名</a> <a href="javascript:void(0);" onclick="edit_style(${t.id })">编辑</a> <a href="javascript:void(0);" onclick="del_template(${t.id })">删除</a></td>
		</tr>
		</s:iterator>
	</table>
</form>
</div>
<script type="text/javascript" src="/js/jquery.popImage.js"></script>
<script>
	$(function(){
		$("a.image_gall").popImage();
	});
</script>
