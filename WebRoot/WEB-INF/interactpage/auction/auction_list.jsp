<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

  <div class="toolbar pb10">
  	<ul class="c_switch">
	  </ul>
		<a href="/${oname }/interact/saveAuction.action?mid=${mid }" class="button">新增竞拍</a>
	</div>
	<table width="100%" class="tb_normal" border="1" cellspacing="1"
		cellpadding="1">
		<thead>
			<tr>
				<th>
					竞拍商品名
				</th>
				<th>
					开始时间/结束时间
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
						${s.title }
					</td>
					<td align="center">
						<s:date name="startTime" format="yyyy-MM-dd HH:mm" />
						/
						<s:date name="endTime" format="yyyy-MM-dd HH:mm" />
					</td>
					<td align="center">
						<a href="editAuction.action?auid=${s.id }&mid=${mid }">编辑</a><i class="split">|</i><!-- <a href="javascript:auction_view(1,${s.id },'10','10','source')">预览</a><i class="split">| --><a href="auctionJoinList.action?auid=${s.id }&mid=${mid }">竞拍数据</a><i class="split">|</i><a  href="javascript:audel(${s.id },'${s.title }')">删除</a>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
</div>
