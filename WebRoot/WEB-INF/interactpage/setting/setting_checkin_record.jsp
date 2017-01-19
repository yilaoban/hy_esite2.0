<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
	<ul class="c_switch">
	    <li>
	       <a href="/${oname}/setting/jfSetting.action">积分规则</a>
	    </li>
	    <li>
	      <a href="/${oname}/setting/jfUser.action">用户积分</a>
	    </li>
	    <li class="selected">
	      <a href="/${oname}/setting/jfCheckinRecord.action" >签到记录</a>
	    </li>
	 </ul>
    <p class="clearfix"></p>   

	<table width="100%" class="tb_normal" border="1" cellspacing="1" cellpadding="1">
		<thead>
			<tr>
				<th>昵称</th>
				<th>积分</th>
				<th>签到天数</th>
				<th>签到时间</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="dto.checkinRecordList" var="r">
			<tr>
				<td>${r.nickname }</td>
				<td>${r.addbalance }</td>
				<td>${r.daynum }</td>
				<td><s:date name="#r.createtime" format="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
			</s:iterator>
		</tbody>
	</table>
		<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>

</div>