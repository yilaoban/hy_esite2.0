<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div id="tab">
  <ul>
    <li <s:if test="lightType==1">class="select"</s:if>>
      <a href="/${oname }/interact/yuyueIndex.action">项目设置</a>
    </li>
    <li <s:if test="lightType==2">class="select"</s:if>>
      <a href="/${oname }/interact/yuyueRecord.action">预约记录</a>
    </li>
    <li <s:if test="lightType==3">class="select"</s:if>>
      <a href="/${oname }/interact/yuyueSites.action?lightType=3&grouptype=Y">应用站点</a>
    </li>
    <li <s:if test="lightType==4">class="select"</s:if>>
      <a href="/${oname }/interact/yuyueAptIndex.action">表单配置</a>
    </li>
    <li <s:if test="lightType==5">class="select"</s:if>>
      <a href="/${oname}/interact/yuyueTemplate.action">模板消息</a>
    </li>
    <s:set name="app_name" value="'微预约'" ></s:set>
	<s:include value="/WEB-INF/page/my_app_hyperlink.jsp" />	
  </ul>
</div>