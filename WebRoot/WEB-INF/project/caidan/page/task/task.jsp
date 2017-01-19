<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
  <div class="toolbar pb10">
    <a href="/${oname}/cd-task/edit.action?task.type=0" class="btn btn-primary">新增下载任务</a>
    <a href="/${oname}/cd-task/edit.action?task.type=1" class="btn btn-success">新增答题任务</a>
  </div>
  <table id="table" class="tb_normal" width="100%" border="1" cellspacing="0" cellpadding="0">
    <thead>
      <tr>
        <th>标题</th>
        <th>类型</th>
        <th>开始时间</th>
        <th>结束时间</th>
        <th>操作</th>
      </tr>
    </thead>
    <tbody>
      <s:iterator value="dto.list" var="l" status="st">
        <tr>
          <td>${l.title}</td>
          <td>
            <s:if test="type==0">下载</s:if>
            <s:elseif test="type==1">答题</s:elseif>
          </td>
          <td>${l.starttime}</td>
          <td>${l.endtime}</td>
          <td>
            <a href="/${oname}/cd-task/record.action?taskR.tid=${l.id}">查看</a>
            |
            <a href="/${oname}/cd-task/edit.action?task.id=${l.id}&task.type=${l.type}">编辑</a>
            |
            <a href="javascript:;" onclick="sort('${l.id}','${l.idx}',0)">上移</a>
            |
            <a href="javascript:;" onclick="sort('${l.id}','${l.idx}',1)">下移</a>
            |
            <a href="javascript:;" onclick="delTask('${l.id}')">删除</a>
          </td>
        </tr>
      </s:iterator>
    </tbody>
  </table>
  <%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
</div>
<script>
	$(document).ready(function() {
	});

	function sort(id, idx, type) {
		$.ajax({
			url : "/${oname}/cd-task/sort.action",
			data : {
				"task.id" : id,
				"task.idx" : idx,
				"task.type" : type
			},
			type : "post",
			cache : false,
			success : function(res) {
				if (res > 0) {
					layer.alert("移动成功", function() {
						window.location.reload();
					});
				} else {
					layer.msg("不能再移啦", {
						icon : 5
					});
				}
			}
		});
	}

	function delTask(id) {
		layer.confirm("确定删除此任务吗？", function() {
			$.ajax({
				url : "/${oname}/cd-task/del.action",
				data : {
					"task.id" : id,
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