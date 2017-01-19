<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
function delcat(id,name){
	var layerid=layer.confirm('确定将['+name+']删除吗?',function(){
		$.post("/${oname}/cd-news/del.action",{inajax:1,contentid:id},function(data){
			if(data==1){
				layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
					window.parent.location.reload();
				}); 
			}else{
				layer.msg('删除失败！请重试……', {icon: 5, time: 2000});
			}
		});
	});
}


function delProduct(id,name){
	var layerid=layer.confirm('确定将内容['+name+']删除吗?',function(){
		$.post("/${oname}/content/del_product.action",{inajax:1,contentId:id},function(data){
			if(data==1){
				layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
					window.parent.location.reload();
				}); 
			}else{
				layer.msg('删除失败！请重试……', {icon: 5, time: 2000});
			}
		});
	});
}
</script>
<div class="wrap_content">
	<div class="toolbar pb10">
		<input type="button" value="新增" class="btn btn-primary" onclick="javascript:window.location='/${oname}/cd-coupon/add.action'"/>
	</div>
	<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
		<tr>
			<th width="25%">名称</th>
			<th width="10%">更新时间</th>
			<th width="40%">操作</th>
		</tr>
		<s:iterator value="dto.list" var="l" status="st">
			<s:set name="t" value="#l.product"></s:set>
			<tr>
				<td>${t.name }</td>
				<td><s:date name="#t.updatetime" format="yyyy-MM-dd"/></td>
				<td>
					<input id="idx${st.count }" value="${t.idx }" type="hidden"/>
					<a href="/${oname }/cd-coupon/edit.action?pid=${t.id}">编辑</a><i class="split">|</i>
					<a href="javascript:up_T('${t.id }','${t.catid }','T','${st.count }')">上移</a><i class="split">|</i>
					<a href="javascript:down_T('${t.id }','${t.catid }','T','${st.count }')">下移</a><i class="split">|</i>
					<a href="javascript:delProduct(${t.id },'${t.name }')">删除</a><i class="split">|</i>
					<s:if test='#t.subtype == "C"'><a href="/${oname }/cd-coupon/productCodeIndex.action?pid=${t.id }">优惠券</a></s:if>
				</td>
			</tr>
		</s:iterator>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>