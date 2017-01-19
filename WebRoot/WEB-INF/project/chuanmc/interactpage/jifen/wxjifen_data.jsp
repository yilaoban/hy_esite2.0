<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content left_module">
	  <div class="toolbar pb10">
	  	<ul class="c_switch">
		  <li  class="selected"><a href="/${oname }/interact/wxjifen.action?mid=10009">微信数据</a></li>
		  </ul>
		  <a href="/${oname }/interact/wxjifen.action?mid=10009" class="return" title="返回"></a>
		</div>
		<!-- 
	<form action="">
		<input type="hidden" name="ownerwbuid" value="${ownerwbuid }"/>
		<input type="text" name="nickname" placeholder="请输入微博昵称" value="${nickname }"/>
		<input type="hidden" name="mid" value="${mid }">
		<input type="submit" value="搜索"/>
	</form>
		 -->
	共${dto.pager.totalCount }用户
	<table width="100%" class="tb_normal" border="1" cellspacing="1"
		cellpadding="1">
		<thead>
			<tr>
				<td>昵称</td>
				<td>总积分</td>
				<td>剩余积分</td>
				<td>消耗积分</td>
				<td>积分详情</td>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="dto.list" var="s">
				<tr>
					<td>
						${s.nickname }
					</td>
					<td>${s.total }</td>
					<td>${s.total-s.used }</td>
					<td>${s.used }</td>
					<td><a href="javascript:void(0);" onclick="wxjifenDetail(${ownerwbuid },'${s.wbuid }','${mid }')">详情</a></td>
				</tr>
				</s:iterator>
		</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
 </div>