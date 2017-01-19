<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
  <table width="100%" class="tb_normal">
    <thead>
      <tr>
        <th>消息类型</th>
        <th>操作</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>顾客评价通知</td>
        <td>
          <a href="javascript:;" onclick="editTemplate('${wt_f.type}','${wt_f.store_id}','${wt_f.id}')">编辑</a>
        </td>
      </tr>
      <tr>
        <td>店主回复通知</td>
        <td>
          <a href="javascript:;" onclick="editTemplate('${wt_r.type}','${wt_r.store_id}','${wt_r.id}')">编辑</a>
        </td>
      </tr>
    </tbody>
  </table>
</div>
<script type="text/javascript">
	$(document).ready(function() {

	});

	function editTemplate(type, store_id, id) {
		var param = "?template.type=" + type;
		param += "&template.store_id=" + store_id;
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