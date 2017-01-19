<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<div class="wrap_content left_module">
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li <s:if test="type==0">class="selected"</s:if>><a href="/${oname }/interact/spreadRecord.action?spreadid=${spreadid }&mid=10010&type=0">微博数据</a></li>
	  <li <s:if test="type==1">class="selected"</s:if>><a href="/${oname }/interact/spreadRecord.action?spreadid=${spreadid }&mid=10010&type=1">微信数据</a></li>
	  </ul>
	  <a href="/${oname }/interact/index.action?mid=10010" class="return" title="返回"></a>
	</div>
	<form action="" id="form1" method="get">
		<input type="hidden" name="type" value="${type }"/>
		<input type="hidden" name="spreadid" value="${spreadid }">
		<span>互动时间段：</span>
		<input id="begintime" type="text" value="${begintime }" name="begintime" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
		至 
		<input id="endtime" type="text" value="${endtime }" name="endtime" class="Wdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'begintime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
		<input type="submit" value="搜索" />
	</form> 
	<table class="tb_normal tb_high" width="100%"  border="1" cellspacing="1" cellpadding="1" >
		<thead>
			<tr>
				<th>IP</th>
				<th>来源(昵称)</th>
				<th>互动内容</th>
				<th>互动时间</th>
			</tr>
		</thead>
		<tbody>
		<p>
			<s:iterator value="dto.pager" var="p" >
				<span style="margin-left:0px">共${p.totalCount}个互动</span>
			</s:iterator>
		</p>
			<s:iterator value="dto.spreadRecord" var="sr" >
				<tr>
					<td id="td">
						${sr.ip }
					</td>
					<td>
					   <s:if test="type==0">微博(${sr.nickname})</s:if>
					   <s:else>
					   		微信<s:if test="nickname!=null">(${sr.nickname })</s:if>
					   </s:else>
					</td>
					<td>
						${sr.content}  
					</td>
					<td>
						<s:date name="#sr.createtime" format="yyyy-MM-dd HH:mm:ss" />
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
		<%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
	
</div>