<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div id="tab">
  <ul>
    <li><a href="/${oname }/content/ebProductList.action?subtype=J">商品管理</a></li>
    <li><a href="/${oname }/page/marketingOrderList.action?subtype=J">订单管理</a></li>
    <li class="select"><a href="/${oname }/page/wsc_list.action?subtype=J&grouptype=F">应用站点</a></li>
    <li><a href="/${oname}/pay/template.action?subtype=J">模板消息</a></li>
   	<s:set name="app_name" value="'积分商城'" ></s:set>
    <s:include value="/WEB-INF/page/my_app_hyperlink.jsp" />
  </ul>
</div>