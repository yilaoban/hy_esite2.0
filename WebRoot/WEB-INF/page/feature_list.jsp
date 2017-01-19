<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="toolbar pt10">
	<span class="breadcumb">页面 >> ${dto.page.name }</span>
	<p class="clearfix"></p>
</div>
<s:if test="dto.features.size>0">
<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
			<th>序号</th>
			<th>功能</th>
			<th>配置</th>
			<!-- 
			<th>操作</th>
			-->
		</tr>
	</thead>
	<tbody>
		<s:iterator value="dto.features" var="f" status="st">
			<tr align="center" id="feature_${f.id}">
				<td>${f.idx }</td>
				<td>
					<a href="javascript:void(0);" onclick="changeName(${f.pfid },<s:if test="#attr.f.fname!=null">'${f.fname}'</s:if><s:else>'${f.name}'</s:else>,${dto.page.id})"><span id="pfname${f.pfid }"><s:if test="#attr.f.fname!=null">${f.fname}</s:if><s:else>${f.name}</s:else></span></a>
				</td>
				<td>
					<a href="javascript:void(0);" onclick="config(${f.id},${f.fid})">配置</a>
					<a href="javascript:void(0);" onclick="del_page_feature(${f.pfid},'${f.name}-${f.pfid}',${dto.page.id})">删除</a> 
				</td>
				<!-- 
				<td>
					<s:if test="#st.count > 1">
						<a href="javascript:void(0);" onclick="up_page_feature(${f.pfid},${dto.page.id})">往上</a>
					</s:if> 
					<s:if test="#st.count < dto.features.size">
						<a href="javascript:void(0);" onclick="down_page_feature(${f.pfid},${dto.page.id})">往下</a>
					</s:if>
				</td>
				 -->
			</tr>
		</s:iterator>
	</tbody>
</table>
</s:if>
<s:else>
<p>该页面还没有功能</p>
</s:else>
