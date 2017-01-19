<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<div class="wrap_content left_module">
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li class="selected"><a href="">微上墙</a></li>
	  </ul>
 	  <a href="/${oname }/interact/saveCommentPre.action" class="button">新增微上墙</a>
	</div>
  	<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
			<th>标题</th>
			<th>创建时间</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="dto.list" var="s">
			<tr align="center" >
			     <td align="center">${s.name }</td>
			      <td align="center"><s:date name="createtime" format="yyyy-MM-dd"/></td>
			      <td align="center"><a href="/${oname }/interact/updateCommentPre.action?id=${s.id }">编辑</a><i class="split">|</i><a href="javascript:void(0);" onclick="del_comment(${s.id })">删除</a><s:if test='#s.needcheck=="Y"'><i class="split">|</i><a href="/${oname }/interact/findCommentReordList.action?id=${s.id }">审核</a></s:if></td>
			</tr>
		</s:iterator>
	</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>
