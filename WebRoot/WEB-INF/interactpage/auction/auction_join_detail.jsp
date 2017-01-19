<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<div class="wrap_content">
	<table width="100%" class="tb_normal" border="1" cellspacing="1"
		cellpadding="1">
		<thead>
			<tr>
				<th>
					时间
				</th>
				<th>
					操作
				</th>
				<th>
					出价
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="dto.list" var="l">
				<tr align="center">
					<td align="center">
						<s:date name="createtime" format="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td align="center">
						竞价
					</td>
					<td align="center">
						${l.bidnum }
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
</div>
