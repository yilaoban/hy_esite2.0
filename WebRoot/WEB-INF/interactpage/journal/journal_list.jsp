<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<div class="wrap_content left_module">
   <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li class="selected"><a href="/${oname }/interact/journal_list.action?mid=10001">微期刊</a></li>
	  <li><a href="/${oname }/interact/index.action?mid=10001">邮件期刊</a></li>
	  </ul>
  	<a href="/${oname }/interact/save_journal_pre.action?mid=${mid }" class="button">新增微期刊</a>
  </div>
  	<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
			<th>微期刊标题</th>
			<th>创建时间</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="dto.list" var="s">
			<tr align="center" >
			     <td align="center">${s.title }</td>
			      <td align="center"><s:date name="createtime" format="yyyy-MM-dd HH:mm"/></td>
			      <td align="center"><a href="/${oname }/interact/update_journal_pre.action?id=${s.id }&mid=${mid }">编辑</a><i class="split">|</i><a href="/${oname }/interact/journal_content_list.action?jid=${s.id}">内容设定</a><i class="split">|</i><a href="/${oname }/interact/preview_journal.action?jid=${s.id}" target="_blank">预览</a><i class="split">|</i><a href="/${oname }/interact/periodicalRecord.action?jid=${s.id }&mid=${mid }">微期刊数据</a><i class="split">|</i><a href="javascript:void(0);" onclick="del_journal(${s.id })">删除</a></td>
			</tr>
		</s:iterator>
	</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>
