<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/WEB-INF/pagechip/iframe_header.jsp" %>

<body class="iframe_body">
	防伪码审核列表：<s:property value="dto.list.size"/><br/>
	<form action="/page/selected.action" id="form" name="form">
	<input type="hidden" value="${featureid }" name="featureid">
	审核状态：
			<input type="checkbox" name="edt" value="EDT" <s:if test='edt=="EDT"'>checked="checked"待审核</s:if> >待审核
			<input type="checkbox" name="cmp" value="CMP" <s:if test='cmp=="CMP"'>checked="checked"审核成功</s:if> >审核成功
			<input type="checkbox" name="fld" value="FLD" <s:if test='fld=="FLD"'>checked="checked"审核失败</s:if> >审核失败<br>
	更新时间：<input type="text" placeholder="请选择开始时间" id="start" value="${starttime }" name="starttime" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'end\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate">
	<input type="text" placeholder="请选择结束时间" id="end" value="${endtime }"  name="endtime" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'start\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate"><br>
	提交时间：<input type="text" placeholder="请选择开始时间" id="started" value="${submitstarttime }"  name="submitstarttime" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'ended\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate">
	<input type="text" placeholder="请选择结束时间" id="ended" value="${submitendtime }"  name="submitendtime" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'started\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate">
	<input type="submit" value="筛选">
	</form>
	<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1">
		<tr>
			<td>序号</td>
			<td>openid</td>
			<td>提交时间</td>
			<td>更新时间</td>
			<td>防伪码</td>
			<td>电话</td>
			<td>特约店名称</td>
			<td>审核</td>
			<td>话费</td>
			<td>操作</td>
		</tr>
		<s:iterator value="dto.list" var ="l" status = 'st'>
			<tr>
				<td>${st.count}</td>
				<td>${l.entityid}</td>
				<td><s:date name="createtime" format="yyyy-MM-dd HH:mm"/></td>
				<td><s:date name="updatetime" format="yyyy-MM-dd HH:mm"/></td>
				<td>${l.code1}<s:if test='code2 !=null && code2!=""'><br></s:if>
				${l.code2}<s:if test='code3 !=null && code3!=""'><br></s:if>
				${l.code3}<s:if test='code4 !=null && code4!=""'><br></s:if>
				${l.code4}</td>
				<td>${l.phone}</td>
				<td>${l.address}</td>
				<td><span id="status_${l.id}"><s:if test='status=="EDT"'>待审核</s:if><s:if test='status=="CMP"'>审核成功</s:if><s:if test='status=="FLD"'>审核失败</s:if></span></td>
				<td><span id="payed_${l.id}">${l.payed}</span></td>
				<td><a href="javascript:void(0);" onclick="audit(${l.id })">审核</a><i class="split">|</i><a href="javascript:void(0);" onclick="pay(${l.id })">支付</a></td>
			</tr>
		</s:iterator>
	</table>
	  <s:iterator value="dto.list" var="l">
      </s:iterator>
    <input type="button" value="刷新" onclick="window.location.reload()"/>
	<input type="button" value="关闭" onclick="closeFrame()"/>
</body>
