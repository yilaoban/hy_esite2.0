<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
<div class="toolbar pb10">
  <ul class="c_switch">
  <li><a href="lottery_user_sina.action?lid=${lid }&type=${type }">微博数据</a></li>
  <li><a href="lottery_user_wx.action?lid=${lid }&type=${type }&media=1">微信数据</a></li>
  <li><a href="lottery_user_wx.action?lid=${lid }&type=${type }&media=2">用户数据</a></li>
  <li><a href="lottery_user_wx.action?lid=${lid }&type=${type }&media=-1">匿名数据</a></li>
  <li class="selected"><a href="lottery_winner.action?lid=${lid }&type=${type }">中奖数据</a></li>
  </ul>
  <a href="/${oname}/cd-lottery/index.action?htype=${type }" class="return" ></a>
</div>
	<table width="100%" class="tb_normal" border="1" cellspacing="1"
		cellpadding="1">
		<thead>
			<tr>
				<th>
					奖品名称
				</th>
				<th>
					中奖人数
				</th>
				<th>
					操作
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="dto.prize" var="p">
				<tr align="center">
					<td align="center">
						${p.name }
					</td>
					<td align="center">
						${p.used }
					</td>
					<td align="center">
						<s:if test='#p.type == "H"'>
							<a href="javascript:lotterySend(${p.id })">查看中奖人</a><i class="split">|
						</s:if>
						<s:if test='#p.type == "M"'>
							<a href="javascript:showlotterywinner(${p.id })">查看中奖人</a><i class="split">|
						</s:if>
						<s:else>
							<a href="javascript:slw(${p.id })">查看中奖人</a><i class="split">|
						</s:else>
						<a href="export_winner.action?lpid=${p.id }&lpname='${p.name}'">导出中奖人</a>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
