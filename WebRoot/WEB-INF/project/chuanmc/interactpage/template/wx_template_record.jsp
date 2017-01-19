<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
  <table class="tb_normal" width="100%">
    <thead>
      <tr>
        <th>消息类型</th>
        <th>用户</th>
        <th>发送时间</th>
        <th>发送状态</th>
      </tr>
    </thead>
    <tbody>
      <s:iterator value="dto.job_list" var="l">
        <tr>
          <td>
            <s:if test="type=='OCT'">到店提醒通知</s:if>
            <s:elseif test="type=='OCF'">服务评价通知</s:elseif>
          </td>
          <td>${l.user.nickname}</td>
          <td>
            <s:date name="sendtime" format="yyyy-MM-dd HH:mm:ss"></s:date>
          </td>
          <td>
            <s:if test="errmsg=='ready'">未发送</s:if>
            <s:elseif test="errmsg=='ok'">发送成功</s:elseif>
            <s:elseif test="errmsg=='success'">发送成功</s:elseif>
            <s:elseif test="errcode==43004">发送失败：用户未关注</s:elseif>
            <s:else>发送失败</s:else>
          </td>
        </tr>
      </s:iterator>
    </tbody>
  </table>
  <%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
</div>
<script type="text/javascript">
	$(document).ready(function() {
	});

	
</script>