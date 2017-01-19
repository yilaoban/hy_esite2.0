<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
  <div class="module_list" style="">
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
        <th>操作</th>
      </tr>
    </thead>
    <tbody>
      <s:iterator value="dto.store_list" var="l">
        <tr>
          <td>${l.title}</td>
          <td>
            <a href="javascript:;" data-toggle="popover" data-placement="left" title="内容示例" data-content="${l.example}">示例</a>
            |
            <a href="javascript:;" onclick="editTemplate('${l.id}')">使用</a>
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
<div id="templateModal" class="modal fade bs-example-modal-lg" role="dialog" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content"></div>
  </div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$('[data-toggle="popover"]').popover({
			container : "body",
			html : true
		});

		$("#industryModal,#templateModal").on("hidden.bs.modal", function() {
			$(this).removeData("bs.modal");
			$(".colpick").remove();
		});
	});

	function industry() {
		$('#industryModal').modal({
			backdrop : true,
			remote : "/${oname}/interact/template_industry.action?dto.industry=${dto.industry}"
		});
	}

	function editTemplate(id) {
		$('#templateModal').modal({
			backdrop : true,
			remote : "/${oname}/interact/template_edit.action?template.store_id=" + id
		});
	}

	
</script>