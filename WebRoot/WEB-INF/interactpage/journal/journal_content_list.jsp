<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<div class="wrap_content left_module">
  	<div class="toolbar pb10">
  	  <ul class="c_switch">
	  <li class="selected"><a href="">微期刊内容设定</a></li>
	  </ul>
	  <a class="button" onclick="addJournalContent(${jid})" href="javascript:void(0);">添加内容</a>
	  <a href="javascript:window.history.back()" class="return" title="返回"></a>
	</div>
  	<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
			<th>标题</th>
			<th>缩略图</th>
			<th>创建时间</th>
			<th>更新时间</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="dto.journalContentList" var="s">
			<tr align="center" >
			    <td align="center">${s.title }</td>
			     <td align="center"><img src="${imgDomain }${s.simg }" width="80" height="50"></td>
			    <td align="center"><s:date name="createtime" format="yyyy-MM-dd HH:mm"/></td>
			    <td align="center"><s:date name="updatetime" format="yyyy-MM-dd HH:mm"/></td>
			    <td align="center"><a onclick="updateJournalContent(${s.id},${s.jid})" href="javascript:void(0);">编辑</a><i class="split">|</i><a onclick="updateJournalShareContent(${s.id},${s.jid})" href="javascript:void(0);">分享内容设定</a><i class="split">|</i><a href="javascript:void(0);" onclick="del_journalcontent(${s.id })">删除</a></td>
			</tr>
		</s:iterator>
	</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>
