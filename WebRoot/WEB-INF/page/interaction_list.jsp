<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<div class="wrap_content">
<div class="toolbar pb10">
	<span class="breadcumb">微博PAGE >> ${dto.sitegroup.groupname }</span>
	<div class="clearfix"></div>
</div>
<s:if test="dto.features.size > 0">
<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
			<th>功能名</th>
			<th>最近更新</th>
			<th>互动总数</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="dto.features" var="f">
		<tr>
			<td>${f.name}</td>
			<td><s:date name="interactionDate" format="yyyy-MM-dd HH:mm"/></td>
			<td>${f.interactionCount}</td>
			<td><a href="javascript:void(0);" onclick="hd_details('${f.name}',${f.id},${dto.sitegroup.id})">查看详情</a>&nbsp;<s:if test='id==113'><a href="javascript:startHandler('${f.name}',${f.id},${dto.sitegroup.id})">更新</a>&nbsp;<a href="hd_export.action?featureid=${f.id }&sitegroupid=${dto.sitegroup.id }">导出</a></s:if></td>
		</tr>
		</s:iterator>
	</tbody>
</table>
</s:if>
<s:else>
	该微博PAGE还没有功能
</s:else>
<div id="feature_div">
</div>
</div>