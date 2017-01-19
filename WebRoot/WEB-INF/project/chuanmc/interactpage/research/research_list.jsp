<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
function researchclean(id){
	var layerid=layer.confirm('确定清空数据吗?',{title:"清空投票数据"},function(){
		$.post("/${oname}/interact/researchclean.action",{"searchid":id},function(data){
			if(data >0){
				layer.msg('清空完成!',{icon: 6, time: 1000});
			}else{
				layer.msg('清空失败!',{icon: 5, time: 1000});
			}
		});
	});
}
</script>
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  </ul>
	  <a href="/${oname }/interact/research_design.action" class="btn btn-primary">新增调研</a>
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
		<s:iterator value="dto.researchList" var="s">
			<tr align="center" >
			     <td align="center">${s.title }</td>
			      <td align="center"><s:date name="starttime" format="yyyy-MM-dd HH:mm"/>/<s:date name="endtime" format="yyyy-MM-dd HH:mm"/></td>
			      <td align="center"><a href="/${oname }/interact/to_update_research_design.action?researchid=${s.id }">编辑</a><i class="split">|</i><a href="/${oname }/interact/research_question_option.action?searchid=${s.id }&mid=${mid }">调研内容</a><i class="split">|</i><a href="/${oname }/interact/preview_research.action?rid=${s.id}" target="_blank">预览</a><i class="split">|</i><a href="/${oname }/interact/research_record_list.action?searchid=${s.id}&mid=${mid }&mtype=0">数据</a><i class="split">|</i><a href="javascript:void(0);" onclick="del_research(${s.id })">删除</a><i class="split">|</i><a href="javascript:researchclean('${s.id }')">清空</a></td>
			</tr>
		</s:iterator>
	</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>
