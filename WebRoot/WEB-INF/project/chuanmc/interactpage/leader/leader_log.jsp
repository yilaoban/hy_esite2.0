<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
</script>
<div >
	<table width="100%" cellspacing="1" cellpadding="1" border="1" class="tb_normal">
		<thead>
			<tr>
				<th>来源</th>
				<th>描述</th>
				<th>创建时间</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="dto.leaderLogList" var="p">
				<tr align="center">
					<td>
						<s:if test='#p.type =="HUD"'>互动</s:if>
						<s:if test='#p.type =="UZL"'>用户总览</s:if>
						<s:if test='#p.type =="UFX"'>用户分析</s:if>
						<s:if test='#p.type =="MCR"'>用户洞察</s:if>
						<s:if test='#p.type =="EWB"'>网站搭建</s:if>
						(<s:if test='#p.subtype =="FRM"'>表单</s:if>
						<s:if test='#p.subtype =="DYA"'>调研</s:if>
						<s:if test='#p.subtype =="LTR"'>抽奖</s:if>
						<s:if test='#p.subtype =="VOT"'>投票</s:if>
						<s:if test='#p.subtype =="FTR"'>统计</s:if>
						<s:if test='#p.subtype =="FTS"'>站点</s:if>
						<s:if test='#p.subtype =="FTR"'>统计页面</s:if>
						
						<s:if test='#p.subtype =="UFZ"'>总览</s:if>
						<s:if test='#p.subtype =="UFF"'>分析</s:if>
						<s:if test='#p.subtype =="UFV"'>访问</s:if>
						
						<s:if test='#p.subtype =="CSQ"'>趋势</s:if>
						<s:if test='#p.subtype =="CSU"'>用户</s:if>
						<s:if test='#p.subtype =="CSP"'>传播页面</s:if>
						
						<s:if test='#p.subtype =="EWS"'>微活动</s:if>
						<s:if test='#p.subtype =="EWX"'>微现场</s:if>
						<s:if test='#p.subtype =="EWW"'>微网站</s:if>
						)
					</td>
					<td>
						${p.hydesc }
					<td>
						<s:date name="createtime" format="yyyy-MM-dd HH:mm:ss" />
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
</div>
