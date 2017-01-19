<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<div class="toolbar pb10">
  <ul class="c_switch">
  </ul>
  <a href="/${oname }/interact/save_yyy.action?mid=${mid }" class="btn btn-primary">新增摇一摇</a>
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
						<a href="edit_yyy.action?lid=${s.id }&mid=${mid }">编辑</a><i class="split">|</i><!-- <a href="javascript:lottery_view(${s.id },10000,'source',7)">预览</a><i class="split">|</i> -->
						<a href="prize.action?lid=${s.id }&returnType=${s.type }&mid=${mid }">奖品</a><i class="split">|</i>
						<a href="lottery_user_sina.action?lid=${s.id }&type=${s.type }&mid=${mid }">数据</a><i class="split">|</i>
						<a href="javascript:checkLottery(${s.id })">预算</a><i class="split">|</i>
						<a href="javascript:void(0);" onclick="checkLog(${s.id})">记录</a><i class="split">|</i>
						<a  href="javascript:ldel(${s.id })">删除</a><i class="split">|</i>
						<a  href="javascript:lotteryclean('${oname }','${s.id }')">清空</a>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
</div>
<script type="text/javascript">
function checkLog(id){
	var srcString = '/${oname}/interact/checkLog.action?lid=' + id;
	var title = '审核记录';
	layer.open({
		type : 2,
		area : ['600px','500px'],
		title : [title,true],
		content: srcString
	});
}
	
</script>
<!-- 
<div id="logModal" class="modal fade bs-example-modal-lg" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content" style="width:800px;">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span>
					<span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">审核记录</h4>
			</div>
			<div class="modal-body">
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
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function() {
		var head = $("#thead").html();
		$("#logModal").on("hidden.bs.modal", function() {
			$("#logTable").html(head);
			$("#logPage").val(1);
		});
	});
</script>
 -->