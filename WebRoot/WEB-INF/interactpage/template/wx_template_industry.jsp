<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal">
    <span aria-hidden="true">&times;</span>
    <span class="sr-only">Close</span>
  </button>
  <h4 class="modal-title">修改行业</h4>
</div>
<div class="modal-body">
  <p class="text-danger">修改行业后，你在原有行业中的模板将被删除（一个月只能修改一次）</p>
  <form class="form-inline">
    <div class="form-group" style="display: block;">
      <label for="primary_first">主营行业</label>
      <select class="form-control" id="primary_first">
        <s:iterator value="dto.primary_first_list" var="ind">
          <option value="${ind.first_class}" <s:if test="#ind.first_selected">selected</s:if>>${ind.first_class}</option>
        </s:iterator>
      </select>
      <select class="form-control" id="primary_second">
        <s:iterator value="dto.primary_second_list" var="ind">
          <option value="${ind.id}" <s:if test="#ind.second_selected">selected</s:if>>${ind.second_class}</option>
        </s:iterator>
      </select>
    </div>
    <div class="form-group">
      <label for="secondary_first">副营行业</label>
      <select class="form-control" id="secondary_first">
        <s:iterator value="dto.secondary_first_list" var="ind">
          <option value="${ind.first_class}" <s:if test="#ind.first_selected">selected</s:if>>${ind.first_class}</option>
        </s:iterator>
      </select>
      <select class="form-control" id="secondary_second">
        <s:iterator value="dto.secondary_second_list" var="ind">
          <option value="${ind.id}" <s:if test="#ind.second_selected">selected</s:if>>${ind.second_class}</option>
        </s:iterator>
      </select>
    </div>
  </form>
</div>
<div class="modal-footer">
  <button type="button" class="btn btn-primary" onclick="setIndustry()">确认</button>
  <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
</div>

<script type="text/javascript">
	$(document).ready(function() {

		$("#primary_first,#secondary_first").change(function() {
			var $this = $(this);
			$.ajax({
				url : "/${oname}/template/industry_second.action",
				data : {
					"dto.industry" : $(this).val()
				},
				type : "get",
				cache : false,
				success : function(data) {
					if (data) {
						var html = "";
						for (var i = 0; i < data.length; i++) {
							var value = data[i].id;
							var text = data[i].second_class;
							html += '<option value="' + value + '">' + text + '</option>';
						}
						$this.next().html(html);
					}
				}
			});
		});

	});

	function setIndustry() {
		var industry_id1 = $("#primary_second").val();
		var industry_id2 = $("#secondary_second").val();
		if (!industry_id1) {
			bootbox.alert("请选择主营行业");
			return;
		}
		if (!industry_id2) {
			bootbox.alert("请选择副营行业");
			return;
		}
		if (industry_id1 == industry_id2) {
			bootbox.alert("主副行业不能相同");
			return;
		}

		$.ajax({
			url : "/${oname}/template/industry_save.action",
			data : {
				"dto.industry_id1" : industry_id1,
				"dto.industry_id2" : industry_id2
			},
			type : "post",
			cache : false,
			success : function(data) {
				if (data) {
					if (data.errcode == 0) {
						bootbox.alert("修改成功", function() {
							window.location.reload();
						});
					} else if (data.errcode == 43100) {
						bootbox.alert("一个月只能修改一次");
					} else {
						bootbox.alert(data.errmsg);
					}
				} else {
					bootbox.alert("修改失败");
				}
			}
		});
	}

	
</script>