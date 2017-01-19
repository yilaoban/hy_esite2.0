<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div>
	<lable class="pr10"> <b>分享者</b>：<span id="i_nickname"></span></lable>
	<lable class="pr10"> <b>是否是粉丝</b>：<span id="i_subscribe"></span></lable>
	<lable class="pr10"> <b>性别</b>：<span id="i_sex"></span></lable>
	<lable> <b>区域</b>：<span id="i_area"></span></lable>
</div>
<table width="100%" cellspacing="1" cellpadding="1" border="1" class="tb_normal">
	<thead>
		<tr>
			<s:if test="atype!='f'.toString()">
				<th>
					<s:if test="atype=='s'.toString()">转发者</s:if>
					<s:elseif test="atype=='c'.toString()">点击者</s:elseif>
					<s:elseif test="atype=='g'.toString()">关注者</s:elseif>
					<s:elseif test="atype=='h'.toString()">互动者</s:elseif>
					<s:elseif test="atype=='w'.toString()">外部对象</s:elseif>
				</th>
				<th>是否是粉丝</th>
				<th>区域</th>
				<s:if test="atype=='h'.toString()">
					<th>互动类型</th>
				</s:if>
			</s:if>
			<th>页面</th>
			<th>站点</th>
			<th>时间</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="dto.ulist" var="u">
			<tr align="center">
				<s:if test="atype!='f'.toString()">
					<td>${u.user}</td>
					<td>${u.subscribe}</td>
					<td>${u.area}</td>
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
				</s:if>
				<td>
					<a href="/${oname}/user/show.action?pageid=${u.pageid}" target="_blank">${u.pagename}</a>
				</td>
				<td>${u.sitename}</td>

				<td>
					<s:date name="created" format="yyyy-MM-dd HH:mm:ss" />
				</td>
			</tr>
		</s:iterator>
	</tbody>
</table>
<%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		$("#i_nickname").html($("#n_${pname}", window.parent.document).html());
		$("#i_subscribe").html($("#g_${pname}", window.parent.document).html());
		$("#i_sex").html($("#s_${pname}", window.parent.document).html());
		$("#i_area").html($("#a_${pname}", window.parent.document).html());
	});

	
</script>