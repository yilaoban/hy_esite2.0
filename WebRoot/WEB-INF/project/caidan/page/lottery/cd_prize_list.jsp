<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content left_module">
  	<div class="toolbar pb10">
  	  <ul class="c_switch">
	  <li class="selected"><a href="">奖品管理</a></li>
	  </ul>
	  <a href="javascript:void(0);" onclick="add_prize('${lid}')" class="button"/>添加奖品</a>
	  <img src="/images/help.jpg" height="20" width="20" onclick="prizeManageDoc()" title="奖品管理文档" style="cursor:pointer;margin-bottom:8px;margin-left:10px">
      <a href="/${oname}/cd-lottery/index.action?htype=${returnType}" class="return" title="返回"></a>
	</div>
<div id="prizecontent" style="overflow: hidden;">
	<table width="100%" class="tb_normal" border="1" cellspacing="1"
		cellpadding="1">
			<tr>
				<th>奖品名称</th>
				<th>总数</th>
				<th>已用</th>
				<th>剩余</th>
				<th>奖品类型</th>
				<th>是否为安慰奖</th> 
				<th>操作</th>
			</tr>
		<s:iterator value="dto.prizelist" var="p">
			<tr>
				<td>${p.name }</td>
				<td>${p.total }</td>
				<td>${p.used }</td>
				<td>${p.total-p.used }</td>
				<td>
					<s:if test='type=="J"'>积分</s:if>
					<s:elseif test='type=="S"'>实物</s:elseif>
					<s:elseif test='type=="C"'>优惠券</s:elseif>
					<s:elseif test='type=="M"'>口令红包</s:elseif>
					<s:elseif test='type=="H"'>微信红包</s:elseif>
					<s:elseif test='type=="Z"'>自提商品</s:elseif>
				</td>
				<td>
					<s:if test='hydefault=="Y"'>是</s:if>
					<s:else>否</s:else>
				</td>
				<td>
					<a href="javascript:update_prize('${p.id}')">编辑</a><i class="split">|</i><a href="javascript:del_prize(${p.id })">删除</a>
					<s:if test='type=="C"'>
						<i class="split">|</i><a href="addPrizeCodePre.action?id=${p.id }&lid=${lid }&returnType=${returnType }">上传券号</a>
						<i class="split">|</i><a href="findPrizeCodePre.action?id=${p.id }&lid=${lid }&returnType=${returnType }">查看券号</a>
					</s:if>
				</td>
			</tr>
		</s:iterator>
	</table>
	
	<%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
</div>
</div>
<script type="text/javascript">
function add_prize(lid){
	var srcString = '/${oname}/cd-lottery/prizeAddPre.action?lid='+lid;
	var	title="添加奖品";
	layer.open({
			type : 2,
			area : ['450px','400px'],
			title : [title,true],
			content: srcString
		});
}
</script>