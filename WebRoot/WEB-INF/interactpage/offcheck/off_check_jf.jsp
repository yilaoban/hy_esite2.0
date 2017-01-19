<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
  <div class="toolbar pb10">
    <ul class="c_switch">
      <li class="selected">
        <a href="javascript:;" rel="template">模板消息</a>
      </li>
    </ul>
    <a href="javascript:window.history.back()" class="return" title="返回"></a>
  </div>

  <div id="template" class="tabpanel">
    <table width="100%" class="tb_normal">
      <thead>
        <tr>
          <th>消息类型</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>积分变动通知</td>
          <td>
            <a href="javascript:;" onclick="editTemplate('${wt_f.type}','${wt_f.store_id}','${wt_f.id}')">编辑</a>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$(".c_switch li a").click(function() {
			$(".c_switch li").removeClass("selected");
			$(this).parent().addClass("selected");
			var rel = $(this).attr("rel");
			$(".tabpanel").hide();
			$("#" + rel).show();
		});
	});

	function editTemplate(type, store_id, id) {
		var param = "?template.type=" + type;
		param += "&template.entityid=${sourceid}";
		param += "&template.store_id=" + store_id;
		param += "&template.showUrl=true";
		if (id) {
			param += "&template.id=" + id;
		}

		layer.open({
			type : 2,
			area : [ '600px', '490px' ],
			title : "模板消息",
			content : "/${oname}/template/edit.action" + param
		});
	}

	
</script>