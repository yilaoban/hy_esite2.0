<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="frame_content">
 <div class="toolbar pb10">
  <ul class="c_switch">
	<li <s:if test='type=="A"||type==null'>class="selected"</s:if>><a href="wxjifenDetail.action?ownerwbuid=${ownerwbuid }&wbuid=${wbuid }&type=A&mid=${mid }&utype=1">积分明细</a></li>
	<li <s:if test='type=="P"'>class="selected"</s:if>><a href="wxjifenDetail.action?ownerwbuid=${ownerwbuid }&wbuid=${wbuid }&type=P&mid=${mid }&utype=1">积分收入</a></li>
	<li <s:if test='type=="M"'>class="selected"</s:if>><a href="wxjifenDetail.action?ownerwbuid=${ownerwbuid }&wbuid=${wbuid }&type=M&mid=${mid }&utype=1">积分支出</a></li>
  </ul>
 </div>
	<table width="100%" class="tb_normal" border="1" cellspacing="1"
		cellpadding="1">
		<thead>
			<tr>
				<td>wbuid/昵称</td>
				<td>积分变化</td>
				<td>日期</td>
				<td>备注</td>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="dto.record" var="s">
				<tr>
					<td>
						${s.wbuid }/${s.nickname }
					</td>
					<td>${s.actionnum }</td>
					<td><s:date name="createtime" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td>${s.hydesc }</td>
				</tr>
				</s:iterator>
		</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
 </div>