<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

  <div class="toolbar pb10">
  	<ul class="c_switch">
	  </ul>
	  <a href="/${oname }/interact/spread_design.action" class="button">新增口碑营销</a>
	</div>
  	<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
			<th>口碑营销标题</th>
			<th>开始时间/结束时间</th>
			<th>互动类型</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="dto.spreadList" var="s">
			<tr align="center" >
			     <td align="center">${s.title }</td>
			      <td align="center"><s:date name="starttime" format="yyyy-MM-dd HH:mm"/>/<s:date name="endtime" format="yyyy-MM-dd HH:mm"/></td>
			      <td align="center"><s:if test='#s.type == "SRE"'>直发</s:if><s:elseif test='#s.type == "FWD"'>转发</s:elseif><s:elseif test='#s.type == "FAC"'>转发并评论</s:elseif></td>
			      <td align="center"><a href="/${oname }/interact/to_update_spread_design.action?spreadid=${s.id }">编辑</a><i class="split">|</i><a href="spread_option.action?id=${s.id }&mid=${mid }">内容设定</a><i class="split">|</i><a href="/${oname }/interact/preview_spread.action?spreadid=${s.id}" target="_blank">预览</a><i class="split">|</i><a href="/${oname }/interact/spreadRecord.action?spreadid=${s.id }&mid=${mid }">互动数据</a><i class="split">|</i><a href="javascript:void(0);" onclick="delete_spread(${s.id })">删除</a></td>
			</tr>
		</s:iterator>
	</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>
