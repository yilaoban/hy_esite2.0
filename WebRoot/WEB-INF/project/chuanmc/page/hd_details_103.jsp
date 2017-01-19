<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="frame_content">
<s:if test="dto.zandetails.size>0">
</s:if>
	<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1">
		<thead>
		<tr>
			<th>昵称</th>
			<th>操作</th>
			<th>商品</th>
			<th>时间</th>
		</tr>
		</thead>
		<tbody>
			<s:iterator value="dto.zandetails" var="d">
			<tr>
				<td>${d.nickname }</td>
				<td>赞了</td>
				<td><a href="${d.productlinkurl }" target="_blank">${d.productname }</a></td>
				<td><s:date name="createtime" format="yyyy-MM-dd HH:mm"/></td>
			</tr>	
			</s:iterator>
		</tbody>
	</table>
<s:else>
	没有记录!
</s:else>
</div>
