<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<div class="wrap_content left_module">
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li <s:if test='dto.type == "W"'>class="selected"</s:if>><a href="/${oname }/interact/index.action?mid=10009">微博积分</a></li>
	  <li <s:if test='dto.type == "X"'>class="selected"</s:if>><a href="/${oname }/interact/wxjifen.action?mid=10009">微信积分</a></li>
	  </ul>
	</div>
	<table width="100%" class="tb_normal" border="1" cellspacing="1"
		cellpadding="1">
		<thead>
			<tr>
				<td>商家微博</td>
				<td>操作</td>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="dto.map" id="column">
				<tr>
				  <td><s:property value="key.nickname" /></td>
				  <td>
				  	<!-- 
				  	<a href="addRule.action?ownerwbuid=${key.cid }&mid=${mid }">添加积分规则</a><i class="split">|</i><a href="showRule.action?ownerwbuid=${key.cid }&mid=${mid }">编辑</a><i class="split">|</i>
				  	 -->
				  	<a href="jifenData.action?ownerwbuid=${key.cid }&mid=${mid }&utype=0">积分数据</a>
				  </td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
</div>
