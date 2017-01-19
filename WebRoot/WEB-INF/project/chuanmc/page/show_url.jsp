<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/WEB-INF/pagechip/iframe_header.jsp" %>

<body class="iframe_body">
	防伪码审核列表：<br/>
	<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1">
		<tr>
			<td>序号</td>
			<td>openid</td>
			<td>提交时间</td>
			<td>防伪码1</td>
			<td>防伪码2</td>
			<td>防伪码3</td>
			<td>防伪码4</td>
			<td>电话</td>
			<td>特约店名称</td>
			<td>审核</td>
			<td>话费</td>
			<td>操作</td>
		</tr>
		<s:iterator value="dto.list" var ="l" status = 'st'>
			<tr>
				<td>${st.count}</td>
				<td>${l.openid}</td>
				<td><s:date name="createtime" format="yyyy-MM-dd HH:mm"/></td>
				<td><s:if test='code1 ==null || code1==""'>-</s:if><s:else>${l.code1}</s:else></td>
				<td><s:if test='code2 ==null || code2==""'>-</s:if><s:else>${l.code2}</s:else></td>
				<td><s:if test='code3 ==null || code3==""'>-</s:if><s:else>${l.code3}</s:else></td>
				<td><s:if test='code4 ==null || code4==""'>-</s:if><s:else>${l.code4}</s:else></td>
				<td>${l.phone}</td>
				<td>${l.address}</td>
				<td><s:if test='status=="EDT"'>待审核</s:if><s:if test='status=="CMP"'>审核成功</s:if><s:if test='status=="FLD"'>审核失败</s:if></td>
				<td>${l.payed}</td>
				<td><s:if test='status =="EDT"'><a href="javasrcipt:void(0)" onclick="audit(${l.id })">审核</a><i class="split">|</i></s:if><s:if test='payed < 200'><a href="javasrcipt:void(0)" onclick="pay(${l.id })">支付</a></s:if></td>
			</tr>
		</s:iterator>
	</table>
	  <s:iterator value="dto.list" var="l">
      </s:iterator>
	<input type="button" value="关闭" onclick="closeFrame()"/>
</body>
