<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="toolbar pt10">
	<span class="breadcumb">导航名 >> 活动</span>
	<p class="clearfix"></p>
</div>
<s:if test="dto.activity.size>0">
<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
			<th>活动名</th>
			<th>创建时间</th>
			<th>包含板块</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="dto.activity" var="f" status="st">
			<tr align="center" id="act_${f.id }">
				<td>${f.name }</td>
				<td>
					<s:date name="createtime" format="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<s:iterator value="#f.modules" var="s">
						${s.name};
					</s:iterator>
				</td>
				<td>
			        <a href="javascript:void(0)" onclick="update_activity(${f.id})">编辑</a>
					<a href="javascript:void(0)" onclick="del_activity(${f.id},'${f.name }')">删除</a>
				</td>
			</tr>
		</s:iterator>
	</tbody>
</table>
</s:if>
<s:else>
<p>该导航下还没有活动</p>
</s:else>
