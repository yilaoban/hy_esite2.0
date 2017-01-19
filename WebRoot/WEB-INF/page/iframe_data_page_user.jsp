<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script src="/js/highcharts/highcharts.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var pagename = $("#pagename", parent.document).val();
		$("#page_name").html(pagename);
	});
	function submit() {
		$("#dateForm").submit();
	}
	function showDetail(mid) {
		var url = "/${oname}/data/index_user_detail.action?page=${pageid}&mtype=${mtype}&mid=" + mid;
		parent.layer.open({
			type : 2,
			area : [ '1200px', '550px' ],
			title : '用户访问详情',
			content : url
		});
	}
</script>

<div class="wrap_content">
	<p>用户访问详情</p>
	<p>
		页面名： <span id="page_name"></span>
	</p>
	<div class="switch_tab_div">
		<a href="/${oname}/data/iframe_user_page.action?mtype=wx&pageid=${pageid}" <s:if test="mtype=='wx'">class="switch_tab_b"</s:if> <s:else>class="switch_tab_a"</s:else>>微信数据</a> <a
			href="/${oname}/data/iframe_user_page.action?mtype=wb&pageid=${pageid}" <s:if test="mtype=='wb'">class="switch_tab_b"</s:if> <s:else>class="switch_tab_a"</s:else>>微博数据</a> <a
			href="/${oname}/data/iframe_user_page.action?mtype=dl&pageid=${pageid}" <s:if test="mtype=='dl'">class="switch_tab_b"</s:if> <s:else>class="switch_tab_a"</s:else>>移动端数据</a>
	</div>
	<div class="tab_content">
		<div class="toolbar">
			<form id="dateForm" action="/${oname}/data/iframe_user_page.action">
				<input name="mtype" type="hidden" value="${mtype}" /> <input name="pageid" type="hidden" value="${pageid}"> <input name="starttime" id="startTime" value="${starttime}"
					class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />- <input name="endtime" id="endTime"
					value="${endtime}" class="Wdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" /> <a
					href="javascript:void(0);" onclick="submit()">搜索</a>
			</form>
		</div>

		<table width="100%" cellspacing="1" cellpadding="1" border="1" class="tb_normal">
			<thead>
				<tr>
					<th>昵称</th>
					<th>性别</th>
					<th>最近访问时间</th>
					<th>访问设备</th>
					<th>IP地址</th>
					<th>地区</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="dto.ulist" var="u">
					<tr align="center">
						<td><a href="javascript:void(0);" onclick="showDetail('${u.mid}')">${u.nickname}</a></td>
						<td>${u.sex}</td>
						<td><s:date name="visittime" format="yyyy-MM-dd HH:mm:ss" /></td>
						<td>${u.agent}</td>
						<td>${u.ip}</td>
						<td>${u.area}</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
	</div>
</div>