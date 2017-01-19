<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
<h2>申请管理</h2>
  <hr/>
  <div >
 	<h3>申请数据</h3>
 	<input type="hidden" value="${id }">
 	<a href="/${oname }/interact/appointment_data.action?id=${id }">全部数据</a><i class="split">|<a href="/${oname }/interact/selected_data.action?id=${id }">入选数据</a>
 	</div>
 	<input type="button" value="发送私信">
 	<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
			<th>微博ID</th>
			<th>备注</th>
			<th>私信结果</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="apdto.list" var="s">
			<tr align="center" >
			     <td align="center"><img src="#"><i class="split">|${s.nickname }<input type="button" value="已关注您"></td>
			      <td><textarea></textarea></td>
			      <td align="center"><a href="#">已点击</a></td>
			</tr>
		</s:iterator>
	</tbody>
	</table>