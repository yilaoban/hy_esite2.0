<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="toolbar pt10">
	<span class="breadcumb">${dto.sitename } >> 模块编辑</span>
	<p class="clearfix"></p>
</div>
<s:if test="dto.features.size>0">
<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
			<th>序号</th>
			<th>功能名</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="dto.features" var="f" status="st">
			<tr align="center" id="feature_${f.id}">
				<td>${f.idx }</td>
				<td>
					<a href="javascript:void(0);" onclick="changeName(${f.pfid },<s:if test="#attr.f.fname!=null">'${f.fname}'</s:if><s:else>'${f.name}-${f.pfid }'</s:else>)"><span id="pfname${f.pfid }"><s:if test="#attr.f.fname!=null">${f.fname}</s:if><s:else>${f.name}-${f.pfid }</s:else></span></a>
				</td>
				<td>
					<a href="javascript:void(0);" onclick="config(${f.id},${f.fid})">编辑</a>
				</td>
			</tr>
		</s:iterator>
	</tbody>
</table>
</s:if>
<s:else>
<p>该页面还没有功能</p>
</s:else>
