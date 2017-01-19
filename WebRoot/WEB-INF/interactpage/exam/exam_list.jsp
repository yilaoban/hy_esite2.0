<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
function examclean(id){
	var layerid=layer.confirm('确定清空数据吗?',{title:"清空投票数据"},function(){
		$.post("/${oname}/interact/examclean.action",{"searchid":id},function(data){
			if(data >0){
				layer.msg('清空完成!',{icon: 6, time: 1000});
			}else{
				layer.msg('清空失败!',{icon: 5, time: 1000});
			}
		});
	});
}
function del_exam(id){
	var layerid=layer.confirm('确定删除吗?', {icon: 2},function(){
		$.post("/${oname}/interact/delete_exam.action",{"id":id},function(data){
			if(data == 1){
				layer.alert("删除失败,请重试!",0);
			}else{
				layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
					window.parent.location.reload();
				}); 
			}
		});
	});
}
</script>
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  </ul>
	  <a href="/${oname }/interact/exam_design.action" class="btn btn-primary">新增测评</a>
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
		<s:iterator value="dto.examList" var="s">
			<tr align="center" >
			     <td align="center">${s.title }</td>
			      <td align="center"><s:date name="starttime" format="yyyy-MM-dd HH:mm"/>/<s:date name="endtime" format="yyyy-MM-dd HH:mm"/></td>
			      <td align="center">
			      	<a href="/${oname }/interact/to_update_exam_design.action?examid=${s.id }">编辑</a><i class="split">|</i>
			      	<a href="/${oname }/interact/exam_question_option.action?searchid=${s.id }&mid=${mid }">测评内容</a><i class="split">|</i>
			      	<a href="/${oname }/interact/examResult.action?examid=${s.id }&mid=${mid }">测评结果</a><i class="split">|</i>
			      	<a href="/${oname }/interact/preview_exam.action?rid=${s.id}" target="_blank">预览</a><i class="split">|</i>
			      	<a href="/${oname }/interact/exam_record_list.action?searchid=${s.id}&mid=${mid }&mtype=0">数据</a><i class="split">|</i>
			      	<a href="javascript:void(0);" onclick="del_exam(${s.id })">删除</a><i class="split">|</i>
			      	<a href="javascript:examclean('${s.id }')">清空</a></td>
			</tr>
		</s:iterator>
	</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>
