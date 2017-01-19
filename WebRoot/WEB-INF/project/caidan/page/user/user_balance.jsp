<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
  <table class="tb_normal" width="100%">
    <thead>
      <tr>
        <th>金币变动</th>
        <th>日期</th>
        <th>描述</th>
      </tr>
    </thead>
    <tbody>
      <s:iterator value="dto.jfList" var="l" status="st">
        <tr>
          <td>${l.score}</td>
          <td>
            <s:date name="created" format="yyyy-MM-dd HH:mm:ss" />
          </td>
          <td>${l.desc}</td>
        </tr>
      </s:iterator>
    </tbody>
  </table>
  <%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
</div>
<script>
	$(document).ready(function() {
	});

	
</script>