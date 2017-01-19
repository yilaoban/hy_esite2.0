<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div class="wrap_content">
		<table class="tb_normal" width="100%" border="1" cellspacing="0"
			cellpadding="0">
			<tr>
				<th>
					<s:if test='src=="s"'>微博昵称</s:if>
					<s:else>微信昵称</s:else>
				</th>
				<th>
					访问时间
				</th>
				<th>
					访问IP
				</th>
				<th>
					访问区域
				</th>
				<th>
					访问终端
				</th>
			</tr>
			<s:iterator value="dto.list" var="l" status="s">
				<tr>
					<td align="center">
						<s:if test='#l.wbuid>0'><a href="http://weibo.com/u/${l.wbuid}" target="_blank" >${l.nickname }</a></s:if>
					</td>
					<td align="center">
						<s:date name="createtime" format="yyyy-MM-dd HH:mm:ss"></s:date>
					</td>
					<td align="center">
						${l.ip }
					</td>
					<td align="center">
						${l.area }
					</td>
					<td align="center">
						<s:if test='#l.terminal=="A"'>PAD</s:if><s:elseif test='#l.terminal=="C"'>PC</s:elseif><s:elseif test='#l.terminal=="P"'>PHONE</s:elseif>
					</td>
				</tr>
			</s:iterator>
		</table>
		<%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
</div>
