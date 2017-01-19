<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li class="selected"><a href="">私人订制</a></li>
	  </ul>
	</div>
  	<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
			<th>功能名</th>
			<th>页面名</th>
			<th>详情</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="dto.list" var="s">
			<tr align="center" >
			     <td align="center"><s:if test='#s.name1 ==null || #s.name1==""'>${s.name2}</s:if><s:else>${s.name1}</s:else></td>
			     <td align="center">${dto.pagename }</td>
			     <td align="center"><a href="javascript:void(0);" onclick="config(${s.featureid },'${s.fid }')">查看</a><i class="split">|</i><a href="javascript:void(0);" onclick="hdexport(${s.featureid },'${s.pageid }')">导出</a></td>
			</tr>
		</s:iterator>
	</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
<p id="expotrhtml" style="display: none">
	<span>开始时间：<input id="begintime" type="text" name="begintime" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" /></span><br>
	<span>结束时间：<input id="endtime" type="text" name="endtime" class="Wdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'begintime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" /></span>
</p>