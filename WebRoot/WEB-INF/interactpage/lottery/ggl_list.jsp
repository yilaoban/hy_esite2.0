<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<div class="toolbar pb10">
  <ul class="c_switch">
  </ul>
  <a href="/${oname }/interact/save_ggl.action?mid=${mid }" class="button">新增刮刮乐</a>
</div>
	<table width="100%" class="tb_normal" border="1" cellspacing="1"
		cellpadding="1">
		<thead>
			<tr>
				<th>
					标题
				</th>
				<th>
					开始时间/结束时间
				</th>
				<th>
					中奖率(‱)
				</th>
				<th>
					操作
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="dto.list" var="s">
				<tr align="center">
					<td align="center">
						${s.name }
					</td>
					<td align="center">
						<s:date name="starttime" format="yyyy-MM-dd HH:mm" />
						/
						<s:date name="endtime" format="yyyy-MM-dd HH:mm" />
					</td>
					<td align="center">
						${s.zjl }
					</td>
					<td align="center">
						<a href="edit_ggl.action?lid=${s.id }&mid=${mid }">编辑</a><i class="split">|</i><!-- <a href="javascript:lottery_view(${s.id },10000,'source',7)">预览</a><i class="split">|</i> --><a
									href="prize.action?lid=${s.id }&returnType=${s.type }&mid=${mid }">奖品管理</a><i class="split">|</i><a href="lottery_user_sina.action?lid=${s.id }&type=${s.type }&mid=${mid }">数据</a><i class="split">|</i><a  href="javascript:ldel(${s.id })">删除</a>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
</div>
