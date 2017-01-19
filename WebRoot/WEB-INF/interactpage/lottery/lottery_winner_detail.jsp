<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

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
				<s:if test='dto.lotteryPrize.type!="C"'>
					<th>
						姓名
					</th>
					<th>
						手机
					</th>
					<th>
						邮件
					</th>
					<th>
						地址
					</th>
				</s:if>
				<s:else>
					<th>
						券号
					</th>
					<th>
						密码
					</th>
				</s:else>
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
					<s:if test='dto.lotteryPrize.type!="C"'>
						<td align="center">
							${r.sub.username }
						</td>
						<td align="center">
							${r.sub.userphone }
						</td>
						<td align="center">
							${r.sub.useremail }
						</td>
						<td align="center">
							${r.sub.userlocation }
						</td>
					</s:if>
					<s:else>
						<td align="center">
							${r.record.lpc.code }
						</td>
						<td align="center">
							${r.record.lpc.password }
						</td>
					</s:else>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
</div>
