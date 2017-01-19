<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<div class="wrap_content left_module">
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li class="selected"><a href="">审核评论列表</a></li>
	  </ul>
	  <a href="/${oname }/interact/commentAction.action" class="return" title="返回"></a>
	</div>
  	<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
			<th>评论内容</th>
			<th>图片展示</th>
			<th>状态</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="dto.rlist" var="s">
			<tr align="center" >
			     <td align="center">${s.content }</td>
			      <td align="center"><img src="${imgDomain }${s.img }" width="100" height="80"></td>
			      <td align="center"><s:if test='#s.status=="EDT"'><a href="javascript:void(0);" onclick="check_comment(${s.id })">审核</a></s:if><s:if test='#s.status=="CMP"'>审核完成</s:if><s:if test='#s.status=="FLD"'>审核失败</s:if></td>
			</tr>
		</s:iterator>
	</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>
