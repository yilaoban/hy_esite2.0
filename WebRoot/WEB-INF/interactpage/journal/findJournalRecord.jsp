<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div class="wrap_content left_module">
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li class="selected"><a href="">查看详情</a></li>
	  </ul>
	  <a href="javascript:window.history.back()" class="return" title="返回"></a>
	</div>
	<div>
		<form action="searchJnickname.action?contentid=${contentid}&jid=${jid}" id="form1" method="post">
			<input type="text" class="text-medium" placeholder="输入微博名称" id="nickname" value="${nickname }" name="nickname">
			<input type="submit" value="搜索">
		</form>
	</div>
	<div style="padding:10px 0;">
		<form action="searchtimeorts.action?contentid=${contentid}&jid=${jid}" id="form2" method="post" class="jNice">
			<p>
			<span>分享时间：</span>
			<input id="begintime" type="text" name="begintime" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
			至 
			<input id="endtime" type="text" name="endtime" class="Wdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'begintime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
			</p>
			<p>
			<span>终端：</span>
			
			<select name="terminal">
				<option value="不限" selected="selected">不限</option>
				<s:iterator value="dto.bindsource" var="b">
					<option value="${b.terminal}">${b.terminal}</option>
				</s:iterator>
			</select>
			
			<span>来源：</span>
			<select name="source">
				<option value="不限" selected="selected">不限</option>
				<s:iterator value="dto.bindsource" var="b">
					<option value="${b.source}">${b.source}</option>
				</s:iterator>
			</select>
			<input type="submit" value="查询">
			</p>
		</form>
	</div>

	

	
		<table width="100%" class="tb_normal" border="1" cellspacing="1"
		cellpadding="1">
			<tr>
				<th>微博昵称</th>
				<th>分享时间</th>
				<th>IP</th> 
				<th>终端</th>
				<th>来源</th>
			</tr>
			<s:iterator value="dto.journalShareRecord" var="j">
				<tr>
					<td>${j.nickname}</td>
					<td>${j.createtime}</td>
					<td>${j.ip}</td>
					<td>${j.terminal}</td>
					<td>${j.source}</td>
				</tr>
			</s:iterator>
		</table>
		 <%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>