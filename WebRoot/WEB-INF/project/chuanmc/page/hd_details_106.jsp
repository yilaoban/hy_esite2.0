<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="frame_content">
<s:if test="dto.uploaddetails.size>0">
</s:if>
	<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1">
		<thead>
		<tr>
			<th>昵称</th>
			<th>操作</th>
			<th>图片</th>
			<th>时间</th>
		</tr>
		</thead>
		<tbody>
			<s:iterator value="dto.uploaddetails" var="d">
			<tr>
				<td>${d.nickname }</td>
				<td>上传图片</td>
				<td><img src="${imgDomain}${d.simg}" width="40" height="40"/></td>
				<td><s:date name="createtime" format="yyyy-MM-dd HH:mm"/></td>
			</tr>	
			</s:iterator>
		</tbody>
	</table>
<s:else>
	没有记录!
</s:else>
</div>