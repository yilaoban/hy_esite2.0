<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<div class="wrap_content left_module">
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li class="selected"><a href="/${oname }/interact/index.action?mid=10001">邮件期刊</a></li>
	  </ul>
	</div>
  	<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
			<th>标题</th>
			<th>创建时间</th>
			<th>是否首页</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="dto.list" var="s">
			<tr align="center" >
			     <td align="center"><a href="${s.url }" target="_blank">${s.title }</a></td>
			      <td align="center"><s:date name="createtime" format="yyyy-MM-dd HH:mm"/></td>
			      <td align="center"><s:if test='publish=="N"'>否</s:if><s:if test='publish=="Y"'>是</s:if></td>
			      <td align="center"><a href="javascript:update_EmailP('${s.id}')">编辑</a><i class="split">|<i class="split"><a href="javascript:void(0);" onclick="del_periodical(${s.id })">删除</a><i class="split">|<i class="split"><a href="javascript:void(0);" onclick="change_publish('${s.id }')">设为首页</a></td>
			</tr>
		</s:iterator>
	</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>
