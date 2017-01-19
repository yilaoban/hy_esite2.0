<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
  <div class="pb10">
    <input type="radio" name="r" value="" <s:if test="taskR.status==null">checked</s:if> />
    <label>全部</label>
    <input type="radio" name="r" value="NTP" <s:if test="taskR.status=='NTP'">checked</s:if> />
    <label>未审核</label>
    <input type="radio" name="r" value="CMP" <s:if test="taskR.status=='CMP'">checked</s:if> />
    <label>审核通过</label>
    <input type="radio" name="r" value="ERR" <s:if test="taskR.status=='ERR'">checked</s:if> />
    <label>审核未通过</label>
  </div>
  <div class="pb10">
    <label>时间</label>
    <input id="starttime" value="${taskR.starttime}" class="Wdate" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\')}',onpicked:pickStart,oncleared:pickStart})" />
    ~
    <input id="endtime" value="${taskR.endtime}" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'starttime\')}',onpicked:pickEnd,oncleared:pickEnd})" />
  </div>
  <table id="table" class="tb_normal" width="100%" border="1" cellspacing="0" cellpadding="0">
    <thead>
      <tr>
        <th>昵称</th>
        <th>手机</th>
        <th>区域</th>
        <th>IP地址</th>
        <th>时间</th>
        <th>积分</th>
        <th>状态</th>
        <th>操作</th>
      </tr>
    </thead>
    <tbody>
      <s:iterator value="dto.recordList" var="r" status="st">
        <tr>
          <td>${r.nickname}</td>
          <td>${r.phone}</td>
          <td>${r.area}</td>
          <td>${r.ip}</td>
          <td>
            <s:date name="createtime" format="yyyy-MM-dd HH:mm:ss" />
          </td>
          <td>${r.jf}</td>
          <td>
            <s:if test="status=='NTP'">未审核</s:if>
            <s:elseif test="status=='CMP'">审核通过</s:elseif>
            <s:elseif test="status=='ERR'">审核未通过</s:elseif>
          </td>
          <td>
            <a href="javascript:;" onclick="updateRecord('${r.id}','CMP')">通过</a>
            |
            <a href="javascript:;" onclick="updateRecord('${r.id}','ERR')">驳回</a>
            |
            <a href="javascript:;" onclick="delRecord('${r.id}')">删除</a>
          </td>
        </tr>
      </s:iterator>
    </tbody>
  </table>
  <%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
</div>
<script>
	var status = "${taskR.status}";
	var starttime = "${taskR.starttime}";
	var endtime = "${taskR.endtime}";

	$(document).ready(function() {
		$("input[type='radio']").click(function() {
			status = $(this).val();
			filter();
		});
	});
	function pickStart() {
		starttime = $dp.$('starttime').value;
		filter();
	}
	function pickEnd(timeStr) {
		endtime = $dp.$('endtime').value;
		;
		filter();
	}
	function filter() {
		var url = "/${oname}/cd-task/record.action?taskR.tid=${taskR.tid}";
		if (status != "") {
			url += "&taskR.status=" + status;
		}
		if (starttime != "") {
			url += "&taskR.starttime=" + starttime;
		}
		if (endtime != "") {
			url += "&taskR.endtime=" + endtime;
		}
		window.location.href = url;
	}

	function updateRecord(id, status) {
		$.ajax({
			url : "/${oname}/cd-task/record_update.action",
			data : {
				"taskR.id" : id,
				"taskR.status" : status
			},
			type : "post",
			cache : false,
			success : function(res) {
				if (res > 0) {
					layer.alert("审核已生效", function() {
						window.location.reload();
					});
				} else {
					layer.msg("审核失败", {
						icon : 5
					});
				}
			}
		});
	}

	function delRecord(id) {
		layer.confirm("确定删除此记录吗？", function() {
			$.ajax({
				url : "/${oname}/cd-task/record_del.action",
				data : {
					"taskR.id" : id,
				},
				type : "post",
				cache : false,
				success : function(res) {
					if (res > 0) {
						layer.alert("删除成功", function() {
							window.location.reload();
						});
					} else {
						layer.msg("删除失败", {
							icon : 5
						});
					}
				}
			});
		});
	}

	
</script>