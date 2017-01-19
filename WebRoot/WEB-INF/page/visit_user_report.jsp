<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
<div >
	    <a class="chosen" href="/page/custom_visitreport.action?sgid=${sgid}">非匿名用户</a> /
	    <a href="/page/visti_log_unkown.action?sgid=${sgid}">匿名用户</a>
</div>

 <div>
  <s:form action="custom_visitreport" method="post" id="visitform" name="visitform">
  <input type="hidden" name="sgid" value="${sgid}">
  <input type="hidden" name="wbuid" value="${wbuid }">
 	<span>访问时间段查询：<input type="text" id="startdate" name="visitdto.startdate" 
 	onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'enddate\')}',startDate:'%y-%M-%d 00:00:01',dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate" 
 	readonly="readonly" value="${visitdto.startdate }">
 	至：<input type="text" id="enddate" name="visitdto.enddate" 
 	onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startdate\')}',startDate:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate" 
 	readonly="readonly" value="${visitdto.enddate }">
 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 	访问次数：
 	<input type="text" id="visit" name="visitdto.num" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" size="6" maxlength="8"><input type="submit" value="查询" ></span>
  </s:form>
  <form id="nickname" name="nickname" method="get"
			action="custom_visitreport_nickname.action">
		 <input type="hidden" name="sgid" value="${sgid}">
		 <input name="nickname" value="${nickname}">
		 <input type="submit" value="搜索" />
  </form>
  </div>
  <div>
    共${dto.pager.totalCount }条
  	<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
			<th>微博昵称</th>
			<th>最近一次访问时间</th>
			<th>访问次数</th>
			<th>访问IP</th>
			<th>访问区域</th>
			<th>访问终端</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="dto.reports" var="s">
			<tr align="center" >
			     <td ><a href="http://weibo.com/u/${s.wbuid}" target="_blank" >${s.name }</a></td>
			      <td >${s.lasttime }</td>
			      <td ><s:if test='#s.num>0'>
			      		 ${s.num }
			      	   </s:if>
			      	   <s:else>
			      	   </s:else></td>
			      <td >${s.ip }</td>
			      <td >${s.area }</td>
			      <td ><s:if test='#s.terminal=="A"'>PAD</s:if><s:elseif test='#s.terminal=="C"'>PC</s:elseif><s:elseif test='#s.terminal=="P"'>PHONE</s:elseif></td>
			      <td align="center"> 
			      <a href="javascript:void(0)" onclick="visitRecordDetail('${sgid}','${s.wbuid}','${s.siteid}')">查看详情</a>
			      </td>
			</tr>
		</s:iterator>
	</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>
</div>
