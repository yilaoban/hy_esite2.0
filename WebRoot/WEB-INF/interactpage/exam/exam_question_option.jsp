<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
function up_question_exam(id){
	$.post("/${oname}/interact/up_exam_question.action",{"id":id},function(data){
		if(data == 1){
			window.parent.location.reload();
		}else{
			layer.alert("向上移动失败,请重试!",0);
		}
	})
}
function down_question_exam(id){
	$.post("/${oname}/interact/down_exam_question.action",{"id":id},function(data){
		if(data == 1){
			window.parent.location.reload();
		}else{
			layer.alert("向下移动失败,请重试!",0);
		}
	})
}
function del_question_exam(id){
	var layerid=layer.confirm('确定删除吗?', {icon: 2},function(){
		$.post("/${oname}/interact/del_exam_question.action",{"id":id},function(data){
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
<div class="wrap_content left_module" >
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li class="selected"><a href="">测评内容</a></li>
	  </ul>
	  <a href="/${oname }/interact/save_exam_question_pre.action?searchid=${searchid }" class="button">新增测评内容</a>
	  <img src="/images/help.jpg" height="20" width="20" onclick="help_exam_content('${oname }')" title="帮助文档" style="cursor:pointer">
	  <a href="/${oname }/interact/index.action?mid=10015" class="return" title="返回"></a>
	</div>
<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
			<th>设置</th>
			<th>题目名称</th>
			<th>题目类型</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="dto.list" var="s" status="st">
			<tr align="center" >
				  <td align="center"><s:if test='#s.type=="SGL"'><a href="javascript:void(0)" onclick="create_tg(${s.id },${searchid },${s.idx })" />↓↓↓↓</a></s:if></td>
			      <td align="center">${s.title }</td>
			      <td align="center"><s:if test='#s.type=="SGL"'>单选题</s:if><s:if test='#s.type=="MUP"'>多选题</s:if><s:if test='#s.type=="ORD"'>排序题</s:if><s:if test='#s.type=="FIL"'>填空题</s:if><s:if test='#s.type=="GAD"'>打分题</s:if></td>
			      <td align="center"><a href="/${oname }/interact/find_one_exam_question.action?id=${s.id }">编辑</a><i class="split">|</i><a href="javascript:void(0);" onclick="del_question_exam(${s.id })">删除</a><s:if test="#st.count>1"><i class="split">|</i><a href="javascript:void(0);" onclick="up_question_exam(${s.id })">上移</a></s:if><s:if test="#st.count < dto.list.size"><i class="split">|</i><a href="javascript:void(0);" onclick="down_question_exam(${s.id })">下移</a></s:if>
			      </td>
			</tr>
		</s:iterator>
	</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
	

