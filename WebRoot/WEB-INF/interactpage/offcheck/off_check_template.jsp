<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="wrap_content">
  <div class="toolbar pb10">
    <a href="javascript:window.history.back()" class="return" title="返回"></a>
  </div>
  <table id="table" class="tb_normal" width="100%" border="1" cellspacing="0" cellpadding="0">
    <thead>
      <tr>
        <th>标题</th>
        <th>发送设置</th>
        <th>操作</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>服务评价通知</td>
        <td>
          <s:if test="wt_f.sendtime!=null">指定时间：${wt_f.sendtime}</s:if>
          <s:elseif test="wt_f.delay==null || wt_f.delay==0">立即发送</s:elseif>
          <s:elseif test="wt_f.delay%1440==0"><fmt:formatNumber type="number" value="${wt_f.delay/1440}" maxFractionDigits="0"/>天后发送</s:elseif>
          <s:elseif test="wt_f.delay%60==0"><fmt:formatNumber type="number" value="${wt_f.delay/60}" maxFractionDigits="0"/>小时后发送</s:elseif>
          <s:else>${wt_f.delay}分钟后发送</s:else>
        </td>
        <td>
          <a href="javascript:;" onclick="editTemplate('${wt_f.type}','${wt_f.store_id}','${wt_f.id}')">编辑</a>
        </td>
      </tr>
      <tr>
        <td>到店提醒通知</td>
        <td>
          <s:if test="wt_t.sendtime!=null">指定时间：${wt_t.sendtime}</s:if>
          <s:elseif test="wt_t.delay==null || wt_t.delay==0">立即发送</s:elseif>
          <s:elseif test="wt_t.delay%1440==0"><fmt:formatNumber type="number" value="${wt_t.delay/1440}" maxFractionDigits="0"/>天后发送</s:elseif>
          <s:elseif test="wt_t.delay%60==0"><fmt:formatNumber type="number" value="${wt_t.delay/60}" maxFractionDigits="0"/>小时后发送</s:elseif>
          <s:else>${wt_t.delay}分钟后发送</s:else>
        </td>
        <td>
          <a href="javascript:;" onclick="editTemplate('${wt_t.type}','${wt_t.store_id}','${wt_t.id}')">编辑</a>
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
		param += "&template.entityid=${sourceid}";
		param += "&template.store_id=" + store_id;
		if (type == 'OCT') {
			param += "&template.showUrl=true";
		}
		param += "&template.showSend=true";
		if (id) {
			param += "&template.id=" + id;
		}

		layer.open({
			type : 2,
			area : [ '600px', '590px' ],
			title : "模板消息",
			content : "/${oname}/template/edit.action" + param
		});
	}

	
</script>