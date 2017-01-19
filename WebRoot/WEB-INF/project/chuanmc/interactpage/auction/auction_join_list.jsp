<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<div class="wrap_content left_module">
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li <s:if test="type==0">class="selected"</s:if>><a href="auctionJoinList.action?auid=${auid }&mid=${mid }&type=0">微博数据</a></li>
	  <li <s:if test="type==1">class="selected"</s:if>><a href="auctionJoinList.action?auid=${auid }&mid=${mid }&type=1">微信数据</a></li>
	  </ul>
	 <a href="/${oname }/interact/index.action?mid=10007" class="return"></a>
	</div>
	<div style="width: 80%; height: 10%; float: left">
		共${dto.pager.totalCount }用户
	</div>
	<div style="width: 80%; height: 10%; float: left; margin-bottom: 20px">
	</div>
	<table width="100%" class="tb_normal" border="1" cellspacing="1"
		cellpadding="1">
		<thead>
			<tr>
				<th>
					IP
				</th>
				<th>
					来源
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
			<s:iterator value="dto.list" var="l">
				<tr align="center">
					<td align="center">
						${l.ip }
					</td>
					<td align="center">
						<s:if test="type==0">
							微博<s:if test="nickName!=null">(${l.nickName })</s:if>
						</s:if>
						<s:elseif test="type==1">
							微信<s:if test="nickName!=null">(${l.nickName })</s:if>
						</s:elseif>
					</td>
					<td align="center">
						${l.total }
					</td>
					<td align="center">
						${l.bidnum }
					</td>
					<td align="center">
						<a href="javascript:ajld(${l.wbuid },${auid },'${l.nickName }')">详情</a>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
</div>
