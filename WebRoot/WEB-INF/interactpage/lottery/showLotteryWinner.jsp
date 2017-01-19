<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
$(document).ready(function() {
		$("#filter").val("${processstatus}");
		$("#filter").change(function() {
			var url = "/${oname}/interact/showLotteryWinner.action?lpid=${lpid}";
			var processstatus = $(this).val();
			if (processstatus != "") {
				window.location.href = url + "&processstatus=" + processstatus;
			} else {
				window.location.href = url;
			}
		});
	});
</script>
<div class="toolbar pb10">
		<lable>根据状态筛选：</lable>
		<select id="filter">
			<option value="">全部</option>
			<option value="N">未领取</option>
			<option value="Y">已领取</option>
		</select>
	</div>
<div class="wrap_content">
	<table width="100%" class="tb_normal" border="1" cellspacing="1"
		cellpadding="1">
		<thead>
			<tr>
				<th>
					昵称
				</th>
				<th>
					中奖时间
				</th>
				<th>
					领取时间
				</th>
				<th>
					状态
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="dto.record" var="r">
				<tr align="center">
					<td align="center">
						${r.record.nickName }
					</td>
					<td align="center">
						${r.record.createtime }
					</td>
					<td align="center">
						<s:date name="#r.record.processtime" format="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td align="center">
						<s:if test= '#r.record.processstatus == "N"'>未领取</s:if><s:elseif test='#r.record.processstatus == "Y"'>已领取</s:elseif>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
</div>
