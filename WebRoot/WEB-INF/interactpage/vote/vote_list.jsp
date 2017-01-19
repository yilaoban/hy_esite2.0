<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
  	<div class="toolbar pb10">
  	  <ul class="c_switch">
	  </ul>
	  <a href="/${oname }/interact/vote_design.action" class="btn btn-primary">新增投票</a>
	</div>
  	<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
			<th>标题</th>
			<th>开始时间/结束时间</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="dto.list" var="s">
			<tr align="center" >
			     <td align="center">${s.title }</td>
			      <td align="center"><s:date name="starttime" format="yyyy-MM-dd HH:mm"/>/<s:date name="endtime" format="yyyy-MM-dd HH:mm"/></td>
			      <td align="center"><a href="/${oname }/interact/to_update_vote_design.action?voteid=${s.id }">编辑</a><i class="split">|</i><a href="/${oname }/interact/vote_content.action?voteid=${s.id }&mid=${mid }">投票内容</a><i class="split">|</i><a href="/${oname }/interact/vote_prieview.action?voteid=${s.id}" target="_blank">预览</a><i class="split">|</i><a href="/${oname }/interact/vote_record_list.action?voteid=${s.id}&mid=${mid }&type=0">数据</a><i class="split">|</i><a href="javascript:void(0);" onclick="del_vote(${s.id })">删除</a><i class="split">|</i><a href="javascript:voteclean('${oname }','${s.id }')">清空</a></td>
			</tr>
		</s:iterator>
	</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>
