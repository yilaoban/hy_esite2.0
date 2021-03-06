<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li class="selected"><a href="javascript:void(0)">集人气</a></li>
	  </ul>
	   <a href="/${oname }/interact/renqiAdd.action" class="button">新增集人气</a>
	</div>
  	<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
			<th>标题</th>
			<th>开始时间/结束时间</th>
			<th>类型</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="dto.list" var="s">
			<tr align="center" >
			     <td align="center">${s.title }</td>
			      <td align="center"><s:date name="startStr" format="yyyy-MM-dd HH:mm"/>/<s:date name="endStr" format="yyyy-MM-dd HH:mm"/></td>
			      <th><s:if test="utype==0">微博</s:if><s:else>微信</s:else></th>
			      <td align="center">
			      	<a href="editIndex.action?id=${s.id }&mid=${mid }">编辑</a><i class="split">|<i class="split">
			      	<a href="renQiData.action?rqid=${s.id }&mid=${mid }">集人气数据</a><i class="split">|<i class="split">
			      	<a href="javascript:rqdel(${s.id },'${s.title }')">删除</a>
			      </td>
			</tr>
		</s:iterator>
	</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>
