<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<div class="wrap_content left_module">
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li><a href="auctionJoinList.action?auid=${auid }&mid=${mid }">全部数据</a></li>
	  <li class="selected"><a href="auctionWinner.action?auid=${auid }&mid=${mid }">中奖数据</a></li>
	  </ul>
	 <a href="/${oname }/interact/index.action?mid=10007" class="return"></a>
	</div>
	<table width="100%" class="tb_normal" border="1" cellspacing="1"
		cellpadding="1">
		<thead>
			<tr>
				<th>
					微博ID/昵称
				</th>
				<th>
					竞拍次数
				</th>
				<th>
					最后一次出价
				</th>
				<th>
					操作
				</th>
			</tr>
		</thead>
		<tbody>
				<tr align="center">
					<td align="center">
						<a href="http://www.weibo.com/u/${dto.record.wbuid }">${dto.record.wbuid }</a>/${dto.	record.nickName }
					</td>
					<td align="center">
						${dto.record.total }
					</td>
					<td align="center">
						${dto.record.bidnum }
					</td>
				</tr>
		</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
</div>
