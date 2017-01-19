<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<table width="100%" border="1" cellspacing="1" cellpadding="1" class="tb_normal">
	<tbody>
	   	<s:iterator value="record.maps" var="m" status="st">
			<tr align="center">
				<s:if test='#m.stype=="I"'>
					<td>${m.name}</td><td><img src="${record.values[st.index] }" height="100px"/></td>
				</s:if>
				<s:else>
					<td>${m.name}</td><td>${record.values[st.index] }</td>
				</s:else>
			</tr>
		</s:iterator>
	</tbody>
	</table>
