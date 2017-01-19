<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
function aptclean(id){
	var layerid=layer.confirm('确定清空数据吗?',{title:"清空表单数据"},function(){
		$.post("/${oname}/interact/orderclean.action",{"aptid":id},function(data){
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
 	  <a href="/${oname }/interact/add_order_pre.action" class="btn btn-primary">新增表单</a>
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
			      <td align="center"><s:date name="starttime" format="yyyy-MM-dd"/>/<s:date name="endtime" format="yyyy-MM-dd"/></td>
			      <td align="center"><a href="/${oname }/interact/update_order_pre.action?id=${s.id }">编辑</a><i class="split">|</i><a href="javascript:void(0);" onclick="del_order(${s.id })">删除</a><i class="split">|</i><a href="/${oname }/interact/appointment_data.action?aptid=${s.id }&data_select=0">数据</a><i class="split">|</i><a href="/${oname }/interact/preview_order.action?id=${s.id }" target="_blank">预览</a><i class="split">|</i><a href="javascript:aptclean('${s.id }')">清空</a><s:if test="#s.coded>0"><i class="split">|</i><a href="/${oname }/page/aptCode.action?aptid=${s.id}">验证码</a></s:if></td>
			</tr>
		</s:iterator>
	</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>
