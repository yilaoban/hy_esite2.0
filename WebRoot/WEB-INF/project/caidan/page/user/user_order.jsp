<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
  <table class="tb_normal" width="100%">
    <thead>
      <tr>
        <th>兑换券</th>
        <th>名称</th>
        <th>兑换价格</th>
        <th>是否使用</th>
      </tr>
    </thead>
    <tbody>
      <s:iterator value="dto.ogList" var="l" status="st">
        <tr>
          <td>
            <img src="${imgDomain}${l.productsimg}" style="width: 50px; height: 50px;" />
          </td>
          <td>${l.productname}</td>
          <td>${l.price}</td>
          <td>
            <s:if test='used=="N"'>未使用</s:if>
            <s:elseif test='used=="Y"'>已使用</s:elseif>
            <s:else>未知</s:else>
          </td>
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