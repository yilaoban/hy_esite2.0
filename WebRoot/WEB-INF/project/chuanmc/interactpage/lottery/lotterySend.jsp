<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<link type="text/css" rel="stylesheet" href="/css/global.css">
<link type="text/css" rel="stylesheet" href="/js/bootstrap/css/bootstrap.css">
<script type="text/javascript" src="/js/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/bootbox.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#filter").val("${errcode}");
		$("#filter").change(function() {
			var url = "/${oname}/interact/lotterySend.action?lpid=${lpid}";
			var errcode = $(this).val();
			if (errcode != "") {
				window.location.href = url + "&errcode=" + errcode;
			} else {
				window.location.href = url;
			}
		});

		$("#allCheck").click(function() {
			$(".ckb").prop("checked", $(this).prop("checked"));
		});
	});
	function resend(id) {
		var data = "";
		if (id == -1) {
			if ($(".ckb:checked").size() == 0) {
				bootbox.alert("请勾选重发用户");
				return;
			}
			var i = 1;
			$(".ckb:checked").each(function() {
				var rid = $(this).val();
				if (i > 1) {
					data += "&";
				}
				data += "rids=" + rid;
				i++;
			});
		} else {
			data = "rids=" + id;
		}

		$.ajax({
			url : "/${oname}/interact/resend_coupon.action",
			data : data,
			type : "post",
			cache : false,
			success : function(data) {
				if (data > 0) {
					bootbox.alert("操作成功", function() {
						window.location.reload();
					});
				}
			}
		});
	}
</script>
	<div class="toolbar pb10">
		<lable>根据失败原因筛选：</lable>
		<select id="filter">
			<option value="">全部</option>
			<option value="-10000">时间限制</option>
			<option value="-10002">频率限制</option>
			<option value="-10003">余额不足</option>
		</select>
		<button class="btn btn-primary" onclick="resend(-1)">批量重发</button>
	</div>
	<table class="tb_normal" width="100%" border="1" cellspacing="0" cellpadding="0">
		<tr>
			<th style="width:20px;">
				<input id="allCheck" type="checkbox" />
			</th>
			<th style="width: 140px;">昵称</th>
			<th style="width: 140px;">发送时间</th>
			<th style="width: 75px;">状态</th>
			<th>描述</th>
			<th style="width: 60px;">操作</th>
		</tr>
		<s:iterator value="dto.sends" var="s">
			<tr>
				<td>
					<s:if test="#s.sent=='E'.toString()">
						<s:if test="#s.errcode==-10000 || #s.errcode==-10002 || #s.errcode==-10003">
							<input type="checkbox" class="ckb" value="${s.id}" />
						</s:if>
					</s:if>
				</td>
				<td>${s.nickname}</td>
				<td>
					<s:date name="#s.senttime" format="yyyy-MM-dd HH:mm:ss" />
				</td>
				<td>
					<s:if test="#s.sent=='Y'.toString()">发送成功</s:if>
					<s:elseif test="#s.sent=='N'.toString()">未发送</s:elseif>
					<s:elseif test="#s.sent=='E'.toString()">发送失败</s:elseif>
					<s:elseif test="#s.sent=='R'.toString()">正在重发</s:elseif>
				</td>
				<td>${s.errmsg}</td>
				<td>
					<s:if test="#s.sent=='E'.toString()">
						<s:if test="#s.errcode==-10000 || #s.errcode==-10002 || #s.errcode==-10003">
							<a id="res_${s.id}" href="javascript:void(0);" onclick="resend(${s.id})"> 重发 
						</s:if>
					</s:if>
				</td>
			</tr>
		</s:iterator>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
