<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div class="wrap_content left_module">
  	<div class="toolbar pb10">
  	  <ul class="c_switch">
	  <li class="selected"><a href="">微期刊数据</a></li>
	  </ul>
	  <a href="javascript:window.history.back()" class="return" ></a>
	</div>
	<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
		<tr>
			<th>标题内容</th>
			<th>分享人数</th>
			<th>最后一次分享时间</th>
			<th>操作</th>
		</tr>
		<s:iterator value="dto.journalContent" var="j">
			<tr>
				<td>${j.title }</td>
				<td>${j.count }</td>
				<td>${j.lastsharetime }</td>
				<td><a href="interact/findJournalSR.action?contentid=${j.id }&mid=${mid} ">查看详情</a></td>
			</tr>
		</s:iterator>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>