<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<div class="wrap_content">
<div class="toolbar pb10">
	 <span><a href="/data/page_hd_type.action?pid=${pid}">互动列表</a>  <i class="gt">&gt;&gt;</i> 上传图片</span><br>
</div>
<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
			<th>微博昵称</th>
			<th>上传图片</th>
			<th>上传时间</th>
			<th>上传IP</th>
			<th>终端</th>
			<th>来源</th>
		</tr>
	</thead>
	<tbody>
	<s:iterator value="dto.hd106Model" var="m">
		<tr>
			<td>${m.nickname}</td>
			<td><img src="${imgDomain}${m.simg}" /></td>
			<td><s:date name="uploadtime" format="yyyy-MM-dd HH:mm:ss"/></td>
			<td>${m.ip }</td>
			<td>${m.terminal }</td>
			<td>${m.source }</td>
		</tr>
	</s:iterator>
	</tbody>
</table>
<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>