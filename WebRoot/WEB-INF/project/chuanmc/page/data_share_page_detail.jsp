<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div>
	<lable class="pr10"> <b>页面</b>：<span id="i_pagename"></span></lable>
	<lable> <b>站点</b>：<span id="i_sitename"></span></lable>
</div>
<table width="100%" cellspacing="1" cellpadding="1" border="1" class="tb_normal">
	<thead>
		<tr>
			<th>
				<s:if test="atype=='s'.toString()">转发者</s:if>
				<s:elseif test="atype=='c'.toString()">点击者</s:elseif>
				<s:elseif test="atype=='g'.toString()">关注者</s:elseif>
				<s:elseif test="atype=='h'.toString()">互动者</s:elseif>
				<s:elseif test="atype=='w'.toString()">外部对象</s:elseif>
			</th>
			<th>区域</th>
			<s:if test="atype=='h'.toString()">
				<th>互动类型</th>
			</s:if>
			<th>时间</th>
			<th>分享者</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="dto.plist" var="p">
			<tr align="center">
				<td>${p.user}</td>
				<td>${p.area}</td>
				<s:if test="atype=='h'.toString()">
					<td>
						<s:if test="adesc=='t'.toString()">投票</s:if>
						<s:elseif test="adesc=='d'.toString()">调研</s:elseif>
						<s:elseif test="adesc=='f'.toString()">表单</s:elseif>
						<s:elseif test="adesc=='l'.toString()">砸金蛋</s:elseif>
						<s:elseif test="adesc=='y'.toString()">摇一摇</s:elseif>
						<s:elseif test="adesc=='z'.toString()">大转盘</s:elseif>
					</td>
				</s:if>
				<td>
					<s:date name="created" format="yyyy-MM-dd HH:mm:ss" />
				</td>
				<td>${p.shareuser}</td>
			</tr>
		</s:iterator>
	</tbody>
</table>
<%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		$("#i_pagename").html($("#p_${page}", window.parent.document).html());
		$("#i_sitename").html($("#s_${page}", window.parent.document).html());
	});

	
</script>