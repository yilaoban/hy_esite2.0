<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
  <div class="module_list" style="display: none;">
    <span>若您在微信公众平台修改了模板消息，请</span>
    <a href="javascript:;" style="color: #459ae9;" onclick="syncTemplate()">同步模板 </a>
  </div>
  <div class="module_list">
    <span> 所在行业</span>
    <b>
      <s:if test="dto.industry==null">未获取到行业信息</s:if>
      <s:else>${dto.industry}</s:else>
    </b>
    <a href="javascript:;" style="color: #459ae9;" onclick="industry()">修改行业 </a>
  </div>
  <table id="table" class="tb_normal" width="100%" border="1" cellspacing="0" cellpadding="0">
    <thead>
      <tr>
        <th>标题</th>
        <th>使用场景</th>
        <th>操作</th>
      </tr>
    </thead>
    <tbody>
      <s:iterator value="dto.list" var="l">
        <tr>
          <td>${l.store.title}</td>
          <td>${l.remark}</td>
          <td>
            <a href="javascript:;" onclick="editTemplate('${l.id}','${l.store_id}')">编辑</a>
            |
            <a href="javascript:;" onclick="delTemplate('${l.id}','${template_id}')">删除</a>
          </td>
        </tr>
      </s:iterator>
    </tbody>
  </table>
  <%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
</div>
<div id="industryModal" class="modal fade bs-example-modal-lg" role="dialog" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content"></div>
  </div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$("#industryModal").on("hidden.bs.modal", function() {
			$(this).removeData("bs.modal");
			$(".colpick").remove();
		});
	});

	function syncTemplate() {
		$.ajax({
			url : "/${oname}/template/sync.action",
			type : "get",
			cache : false,
			success : function(data) {
				if (data) {
					if (data.errcode == 0) {
						bootbox.alert("同步成功", function() {
							window.location.reload();
						});
					} else {
						bootbox.alert(data.errmsg);
					}
				} else {
					bootbox.alert("同步失败");
				}
			}
		});
	}

	function industry() {
		$('#industryModal').modal({
			backdrop : true,
			remote : "/${oname}/template/industry.action?dto.industry=${dto.industry}"
		});
	}

	function editTemplate(id, store_id) {
		var param = "?template.id=" + id;
		param += "&template.store_id=" + store_id;
		param += "&template.showRemark=true";
		param += "&template.showUrl=true";
		param += "&template.showSend=true";
		layer.open({
			type : 2,
			area : [ '600px', '590px' ],
			title : "模板消息",
			content : "/${oname}/template/edit.action" + param
		});
	}

	function delTemplate(id, template_id) {
		bootbox.confirm("确定删除此模板吗？", function(res) {
			if (res) {
				$.ajax({
					url : "/${oname}/template/del.action",
					data : {
						"template.id" : id,
						"template.template_id" : template_id
					},
					type : "post",
					cache : false,
					success : function(data) {
						if (data) {
							if (data.errcode == 0) {
								bootbox.alert("删除成功", function() {
									window.location.reload();
								});
							} else {
								bootbox.alert(data.errmsg);
							}
						} else {
							bootbox.alert("删除失败");
						}
					}
				});
			}
		});
	}

	
</script>