<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div class="wrap_content">
	<table width="100%" class="tb_normal" border="1" cellspacing="1" cellpadding="1">
		<thead>
			<tr>
				<th>名称</th>
				<th>图片</th>
				<th>总数</th>
				<th>已使用</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="dto.productList" var="p">
			<tr>
				<td>${p.name }</td>
				<td><img src="${imgDomain }${p.simgurl}" height="60"/></td>
				<td>${p.total }</td>
				<td>${p.used }</td>
				<td><a href="/${oname}/content/showPayOrder.action?productid=${p.id }&tool=2">查看详情</a></td>
			</tr>
			</s:iterator>
		</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
	
</div>