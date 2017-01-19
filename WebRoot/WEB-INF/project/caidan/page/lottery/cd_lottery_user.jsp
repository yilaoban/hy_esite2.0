<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
<div class="toolbar pb10">
  <ul class="c_switch">
  <li <s:if test="media==0">class="selected"</s:if>><a href="lottery_user_sina.action?lid=${lid }&type=${type }">微博数据</a></li>
  <li <s:if test="media==1">class="selected"</s:if>><a href="lottery_user_wx.action?lid=${lid }&type=${type }&media=1">微信数据</a></li>
  <li <s:if test="media==2">class="selected"</s:if>><a href="lottery_user_wx.action?lid=${lid }&type=${type }&media=2">用户数据</a></li>
  <li <s:if test="media==-1">class="selected"</s:if>><a href="lottery_user_wx.action?lid=${lid }&type=${type }&media=-1">匿名数据</a></li>
  <li><a href="lottery_winner.action?lid=${lid }&type=${type }">中奖数据</a></li>
  </ul>
  <a href="/${oname}/cd-lottery/index.action?htype=${type }" class="return" ></a>
</div>
	<table width="100%" class="tb_normal" border="1" cellspacing="1"
		cellpadding="1">
		<thead>
			<tr>
				<th>用户名</th>
				<s:if test="media == 0">
					<th>微博昵称</th>
				</s:if>
				<s:elseif test="media == 1">
					<th>微信昵称</th>
				</s:elseif>
				<s:elseif test="media == 2">
					<th>微博昵称</th>
					<th>微信昵称</th>
				</s:elseif>
				<th>
					已抽奖次数
				</th>
				<th>
					剩余抽奖次数
				</th>
				<th>
					抽奖详情
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="dto.user" var="u">
				<tr align="center">
					<td align="center">
						 ${u.username }
					</td>
					<s:if test="media == 0 || media == 1">
						<td align="center">
							 ${u.nickName }
						</td>
					</s:if>
					<s:elseif test="media == 2">
						<td align="center">
							 ${u.nickName }
						</td>
						<td align="center">
							${u.wxnickname }
						</td>  
					</s:elseif>
					<td align="center">
						${u.usednum }
					</td>
					<td align="center">
						${u.totalnum-usednum }
					</td>
					<td align="center">
						<a href="javascript:lud(${u.wbuid },${lid },${media },'${oname }')">详情</a>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
