<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	function showDetail(mid) {
		var url = "/${oname}/data/index_user_detail.action?mtype=${mtype}&mid=" + mid;
		layer.open({
			type : 2,
			area : [ '1200px', '550px' ],
			title : '用户访问详情',
			content : url
		});
	}
	function submit() {
		$("#dateForm").submit();
	}
</script>
<!-- <a href="/${oname}/data/index.action" style="float: right;">返回统计</a> -->
<div class="wrap_content">
	<div class="toolbar pb10">
		<ul class="c_switch">
			<li <s:if test="mtype=='wx'">class="selected"</s:if>><a href="/${oname}/data/index_user.action?mtype=wx">微信数据</a></li>
			<li <s:if test="mtype=='wb'">class="selected"</s:if>><a	href="/${oname}/data/index_user.action?mtype=wb">微博数据</a></li>
			<li <s:if test="mtype=='dl'">class="selected"</s:if>><a	href="/${oname}/data/index_user.action?mtype=dl">移动端数据</a></li>
		</ul>
	</div>
	<div class="">
		<div class="toolbar">
			<form id="dateForm" action="/${oname}/data/index_user.action">
				<input name="mtype" type="hidden" value="${mtype}" /> <input name="starttime" id="startTime" value="${starttime}" class="Wdate"
					onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" /> - <input name="endtime" id="endTime" value="${endtime}"
					class="Wdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" /> <a href="javascript:void(0);"
					onclick="submit()">搜索</a>
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