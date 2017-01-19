<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
  <table class="tb_normal" width="100%">
    <thead>
      <tr>
        <th>消息类型</th>
        <th>操作</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>会员充值成功</td>
        <td>
          <a href="javascript:;" onclick="editTemplate('${wt_cu.type}','${wt_cu.store_id}','${wt_cu.id}')">编辑</a>
        </td>
      </tr>
      <tr>
        <td>会员消费提醒</td>
        <td>
          <a href="javascript:;" onclick="editTemplate('${wt_xu.type}','${wt_xu.store_id}','${wt_xu.id}')">编辑</a>
        </td>
      </tr>
      <tr>
        <td>充值通知商家</td>
        <td>
          <a href="javascript:;" onclick="editTemplate('${wt_cd.type}','${wt_cd.store_id}','${wt_cd.id}')">编辑</a>
        </td>
      </tr>
      <tr>
        <td>消费通知商家</td>
        <td>
          <a href="javascript:;" onclick="editTemplate('${wt_xd.type}','${wt_xd.store_id}','${wt_xd.id}')">编辑</a>
        </td>
      </tr>
      <tr>
        <td>管理员申请</td>
        <td>
          <a href="javascript:;" onclick="editTemplate('${wt_sq.type}','${wt_sq.store_id}','${wt_sq.id}')">编辑</a>
        </td>
      </tr>
    </tbody>
  </table>
</div>
<%@include file="/WEB-INF/page/includeAppSites.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {

	});

	function editTemplate(type, store_id, id) {
		var param = "?template.type=" + type;
		param += "&template.store_id=" + store_id;
		if (id) {
			param += "&template.id=" + id;
		}
		if (store_id != 3) {
			param += "&template.showUrl=true";
		}

		layer.open({
			type : 2,
			area : [ '600px', '590px' ],
			title : "模板消息",
			content : "/${oname}/template/edit.action" + param
		});
	}

	
</script>