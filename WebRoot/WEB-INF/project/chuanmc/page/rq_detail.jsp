<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<div class="frame_content">
<div class="toolbar pb10">
</div>
	<div style="width: 80%; height: 10%; float: left">
		共${dto.pager.totalCount }人帮集
	</div>
	<table width="100%" class="tb_normal" border="1" cellspacing="1"
		cellpadding="1">
		<thead>
			<tr>
				<th>IP</th><th>来源(昵称)</th><th>参与时间</th><th>帮集分数</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="dto.details" var="u">
				<tr align="center">
						<td align="center">
							${u.ip }
						</td>
						<td>
							<s:if test="utype==0">
								微博<s:if test="name!=null">(${u.name })</s:if>
							</s:if>
							<s:elseif test="utype==1">
								微信<s:if test="name!=null">(${u.name })</s:if>
							</s:elseif>
						</td>
						<td><s:date name="createtime" format="yyyy-MM-dd HH:mm:ss"/></td>
						<td>${u.addjf }</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
</div>
