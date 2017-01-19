<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div class="wrap_content">
	<ul class="c_switch">
		<li class="selected">
			<a href="/${oname }/interact/jiliRecord.action?cbid=${cbid}">激励记录</a>
		</li>
	</ul>
	<br/>
	<br/>
	<br/>
	<br/>
	<form action="" method="post">
		姓名:<input type="text" name="name" value="${name }"/>
		开始时间:<input type="text" name="starttime" value="${starttime }" placeholder="请选择开始时间"  id="start" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'end\')}',dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" class="Wdate">
		结束时间:<input type="text" name="endtime" value="${endtime }" placeholder="请选择结束时间"  id="end" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'start\')}',dateFmt:'yyyy-MM-dd HH:mm:ss',onpicked:function(){endchange()},lang:'zh-cn'})" class="Wdate"><br>
		<input type="submit" value="搜索"/>
	</form>
	<table width="100%" cellspacing="1" cellpadding="1" border="1" class="tb_normal">
		<thead>
			<tr>
				<td>姓名</td>
				<td>性别</td>
				<td>昵称</td>
				<td>激励类型</td>
				<td>总数</td>
				<td>查看</td>
			</tr>
		</thead>
		<tbody>
				<s:iterator value="dto.senderImpel" var="s">
				<tr>
					<td>${s.name }</td>
					<td>${s.gender }</td>
					<td>${s.nickname }</td>
					<td>红包</td>
					<td><s:property value="fenToyuan(#s.total)"/></td>
					<td><a href="/${oname }/interact/senderDetail.action?sender=${s.sender}&cbid=${cbid}" target="_blank">详情</a></td>
				</tr>
				</s:iterator>
		</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>