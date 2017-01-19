<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="dropdown right" style="margin-top:10px;margin-right:15px;">
 <a id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true" href="javascript:void(0);">
   	<span class="glyphicon glyphicon-th-large" aria-hidden="true"></span>
   	${app_name}
   	<span class="caret"></span>
 </a>
 <ul class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenu1" style="min-width:100px;">
  <s:if test="#session.account.owner.contains(1)">
	<li>
		<a href="/${oname }/bbs/index.action">微社区</a>
	</li>
</s:if>
<s:if test="#session.account.owner.contains(2)">
	<li>
		<a href="/${oname }/interact/cbIndex.action">合伙人</a>
	</li>
</s:if>
<s:if test="#session.account.owner.contains(3)">
	<li>
		<a href="/${sessionScope.account.owner.entity}/setting/jfSetting.action">用户中心</a>
	</li>
</s:if>
<s:if test="#session.account.owner.contains(4)">
	<li>
		<a href="/${oname }/content/ebProductList.action?subtype=W">微电商</a>
	</li>
</s:if>
<s:if test="#session.account.owner.contains(5)">
	<li>
		<a href="/${oname }/content/ebProductList.action?subtype=J">积分商城</a>
	</li>
</s:if>
<s:if test="#session.account.owner.contains(6)">
	<li>
		<a href="/${oname }/interact/offCheckIndex.action">线下签到</a>
	</li>
</s:if>
<s:if test="#session.account.owner.contains(7)">
	<li>
		<a href="/${oname }/interact/yuyueIndex.action">微预约</a>
	</li>
</s:if>
<s:if test="#session.account.owner.contains(8)">
	<li>
		<a href="/${oname}/interact/leaderIndex.action">潜客管理</a>
	</li>
</s:if>
<s:if test="#session.account.owner.contains(9)">
	<li>
		<a href="">用户分析</a>
	</li>
</s:if>
<s:if test="#session.account.owner.contains(10)">
	<li>
		<a href="/${oname}/interact/site_search.action">站内搜索</a>
	</li>
</s:if>
<s:if test="#session.account.owner.contains(11)">
	<li>
		<a href="/${oname}/interact/adIndex.action">微投放</a>
		</li>
	</s:if>
  </ul>
</div>