<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<div class="wrap_content left_module">
	<div class="toolbar pb10">
		<ul class="c_switch">
			<li class="selected"><a href="/${oname }/cd-lottery/findPrizeCodePre.action?id=${id}&lid=${lid }&returnType=${returnType}">券号信息</a></li>
		</ul>
		<form action="/${oname }/cd-lottery/findPrizeCode.action" style="float: left" method="post">
			<input type="hidden" value="${id }" name="id">
			<input type="hidden" value="${lid }" name="lid">
			<input type="hidden" value="${returnType }" name="returnType">
			<input type="text" name="code" placeholder="请输入券号">		
			<input type="submit" value="搜索">		
		</form>
		<a href="/${oname }/cd-lottery/prize.action?lid=${lid }&returnType=${returnType }" class="return" title="返回"></a></div>
	<div id="prizecontent" style="overflow: hidden;">
		<table width="80%" class="tb_normal" border="1" cellspacing="1" cellpadding="1">
			<tr>
				<th>券号</th> 
				<th>密码</th>
				<th>是否使用</th>
			</tr>
		<s:iterator value="dto.prizeCodeList" var="p">
			<tr>
				<td>${p.code }</td>
				<td>${p.password }</td>
				<td>
					<s:if test='#p.used=="Y"'>已用</s:if><s:else>未用</s:else>
				</td>
			</tr>
		</s:iterator>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
	</div>
</div>