<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content left_module">
  	<div class="toolbar pb10">
  	  <ul class="c_switch">
	  <li class="selected"><a href="">传播内容管理</a></li>
	  </ul>
	  <a href="csContentAdd.action?id=${id }&mid=${mid }" class="button"/>添加内容</a>
	  <a href="index.action?mid=10013" class="return" title="返回"></a>
	</div>
<div style="overflow: hidden;">
	<table width="100%" class="tb_normal" border="1" cellspacing="1"
		cellpadding="1">
			<tr>
				<th>缩略图</th>
				<th>名称</th>
				<th>开始时间</th>
				<th>内容</th>
				<th>署名</th>
				<th>操作</th>
			</tr>
		<s:iterator value="jlist" var="j">
			<tr>
				<td><s:if test='#j.img != null&&#j.img!="" '><a class="thumb" href="${imgDomain }${j.img }" target="_blank"><img src="${imgDomain }${j.img }" height="40" width="40"></a></s:if><s:else><img src="/images/nopic.png" height="40" width="40"></s:else></td>
				<td>${j.name }</td>
				<td>${j.starttime }</td>
				<td>${j.content }</td>
				<td>${j.sign }</td>
				<td>
					<a href="javascript:delcsc('${id }','${j.jid }','${j.name }')">删除</a>
				</td>
			</tr>
		</s:iterator>
	</table>
	
	<%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
</div>
</div>