<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div id="tab">
  <ul>
  	 <li <s:if test="lightType==1">class="select"</s:if>><a href="/${oname }/cd-report/index.action">渠道下线</a></li>
  	 <li <s:if test="lightType==2">class="select"</s:if>><a href="/${oname }/cd-report/wayMoneyIndex.action">渠道业绩</a></li>
     <li <s:if test="lightType==3">class="select"</s:if>><a href="/${oname }/cd-report/jfIndex.action">金币明细</a></li>
  </ul>
</div>
