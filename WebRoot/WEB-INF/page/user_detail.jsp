<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<div class="frame_content">
<div class="toolbar pb10">
  <ul class="c_switch">
  <li <s:if test='type!="Y"'>class="selected"</s:if>><a href="/${oname }/interact/user_detail.action?wbuid=${wbuid }&lid=${lid }&media=${media }">全部数据</a></li>
  <li <s:if test='type=="Y"'>class="selected"</s:if>><a href="/${oname }/interact/user_detail.action?wbuid=${wbuid }&lid=${lid }&media=${media }&type=Y">中奖数据</a></li>
  </ul>
  <s:if test='type=="Z"'><a href="index.action?mid=10003" class="return" ></a></s:if><s:elseif test='type=="L"'><a href="index.action?mid=10004" class="return"></a></s:elseif><s:elseif test='type=="G"'><a href="index.action?mid=10005" class="return" ></a></s:elseif>
</div>
	<div style="width: 80%; height: 10%; float: left">
		共${dto.pager.totalCount }次
	</div>
	<table width="100%" class="tb_normal" border="1" cellspacing="1"
		cellpadding="1">
		<thead>
			<tr>
				<th>抽奖时间</th><th>奖品</th><th>终端</th><th>来源</th><th>ip</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="dto.lur" var="u">
				<tr align="center">
						<td align="center">
							${u.createtime }
						</td>
						<s:if test="status==0">
							<td align="center">
								未中奖
							</td>
						</s:if>
						<s:else>
							<td align="center">
								${u.lpName }
							</td>		
						</s:else>
						<td>
							<s:if test='terminal=="C"'>PC</s:if>
							<s:elseif test='terminal=="P"'>PHONE</s:elseif>
							<s:elseif test='terminal=="A"'>PAD</s:elseif>
						</td>
						<td>${u.source }</td>
						<td>${u.ip }</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
</div>
