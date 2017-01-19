<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div class="wrap_content">
	<ul class="c_switch">
		<li>
			<a href="/${oname }/interact/jiliRecord.action?cbid=${cbid}">激励记录</a>
		</li>
		<li class="selected">
			<a href="/${oname }/interact/jiliConfirm.action?cbid=${cbid}">效果确认</a>
		</li>
	</ul>
	<br/>
	<br/>
	<br/>
	<form action="">
		<input type="hidden" name="sender" value="${sender }"/>
		<input type="hidden" name="cbid" value="${cbid }" />
		开始时间:<input type="text" placeholder="请选择开始时间" id="start" value="${starttime }" name="starttime" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'end\')}',dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" class="Wdate">
		结束时间:<input type="text" placeholder="请选择结束时间" id="end" value="${endtime }"  name="endtime" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'start\')}',dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" class="Wdate"><br>
		<input type="submit" value="搜索"/>
	</form>
	<table width="100%">
		<thead>
			<tr>
				<td>转发数</td>
				<td>点击数</td>
				<td>互动数</td>
				<td>关注数</td>
				<td>外部效果</td>
				<td>传播时间</td>
				<td>确认时间</td>
				<td>奖励</td>
			</tr>
		</thead>
		<tbody>
		
			<s:iterator value="dto.implList" var="l">
				<tr>
				<td>${l.zValid }</td>
				<td>${l.dValid }</td>
				<td>${l.hValid }</td>
				<td>${l.gValid }</td>
				<td>${l.wValid }</td>
				<td><s:date name="starttime" format="yyyy-MM-dd HH:mm:ss"/>至<s:date name="endtime" format="yyyy-MM-dd HH:mm:ss"/></td>
				<td><s:date name="createtime" format="yyyy-MM-dd HH:mm:ss"/></td>
				<td><s:property value="fenToyuan(#l.hongbao)"/></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>