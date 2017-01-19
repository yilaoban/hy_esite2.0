<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
$(document).ready(function() {
	var pageId = 1;
	var id = '${lid}';
	$.ajax({
		url : "show_log.action",
		data : {
			"pageId" : pageId,
			"lid" : id
		},
		type : "post",
		cache : false,
		success : function(data) {
			if (data) {
				for (var i = 0; i < data.length; i++) {
					var log = data[i];
					var html = "";
					html += '<tr>';
					html += '	<td>' + log.total + '</td>';
					html += '	<td>' + log.accountname + '</td>';
					html += '	<td title="' + log.reason + '">' + log.reason + '</td>';
					html += '	<td>' + log.ip + '</td>';
					html += '	<td>' + log.createtime + '</td>';
					if (log.type == 0) {
						html += '	<td>提交申请</td>';
					} else if (log.type == 1) {
						html += '	<td>审核通过</td>';
					} else if (log.type == 2) {
						html += '	<td>审核不通过</td>';
					}
					html += '</tr>';
					$("#logTable").append(html);
				}

				var page = parseInt(pageId) + 1;
				$("#loadMore").attr("onclick", "loadLog(" + page + "," + id + ")");
				if (data.length == 10) {
					$("#loadMore").show();
				} else {
					$("#loadMore").hide();
				}
			}
		}
	});

});
	

</script>

<table id="logTable" class="tb_normal" width="100%" border="1" cellspacing="0" cellpadding="0">
	<tr id="thead">
		<th>申请金额（分）</th>
		<th>申请账号</th>
		<th>申请/审核描述</th>
		<th>IP地址</th>
		<th style="width: 140px;">操作时间</th>
		<th>操作类型</th>
	</tr>
</table>
<p style="text-align: center;">
	<button id="loadMore" style="display:none">点击加载</button>
</p>



