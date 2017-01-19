<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
        <td>预约确认消息</td>
        <td>
          <a href="javascript:;" onclick="editTemplate('${wt_u.type}','${wt_u.store_id}','${wt_u.id}')">编辑</a>
        </td>
      </tr>
      <tr>
        <td>预约通知消息</td>
        <td>
          <a href="javascript:;" onclick="editTemplate('${wt_d.type}','${wt_d.store_id}','${wt_d.id}')">编辑</a>
        </td>
      </tr>
    </tbody>
  </table>
</div>
<script type="text/javascript">
	function editTemplate(type, store_id, id) {
		var param = "?template.type=" + type;
		param += "&template.store_id=" + store_id;
		if (id) {
			param += "&template.id=" + id;
		}
		var title = "模板消息";
		if (type == "YYU") {
			title += "(发给用户)";
		} else if (type == "YYD") {
			title += "(发给店主)";
		}
		layer.open({
			type : 2,
			area : [ '600px', '440px' ],
			title : title,
			content : "/${oname}/template/edit.action" + param
		});
	}

	
</script>