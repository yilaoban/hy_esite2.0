<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div id="tab">
  <ul>
    <li <s:if test="lightType==1">class="select"</s:if>>
      <a href="/${oname}/cd-task/index.action">任务列表</a>
    </li>
  </ul>
</div>