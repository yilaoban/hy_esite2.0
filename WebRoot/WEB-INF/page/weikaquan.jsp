<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
	function setShopOwner(wxuid){
		$.post("/${oname}/content/setShopOwner.action",{"wxuid":wxuid},function(data){
			if(data > 0){
				alert("设置成功");
			}else{
				alert("设置失败");
			}
		});
	}
	
	function findWxUser(){
		var srcString = '/${oname}/content/findWxUser.action';
		
		layer.open({
				type : 2,
				area : ['600px','600px'],
				title : '设置店主',
				content: srcString,
				cancel: function(index){
					window.parent.location.reload();
				} 
			});
	}
	
	function delShopOwner(wxuid){
		var srcString = '/${oname}/content/delShopOwner.action';
		var layerid=layer.confirm('确定移除?',{icon: 2},function(){
			$.post(srcString,{"wxuid":wxuid},function(data){
				if(data > 0){
					layer.msg('移除中！请稍等……', {icon: 6, time: 1500}, function(){
						window.parent.location.reload();
					}); 
				}else{
					layer.msg('移除失败！请重试……', {icon: 5, time: 2000});
				}
			});
		});
	}
	
</script>
<div class="wrap_content">
	<a href="javascript:void(0)" onclick="findWxUser()" class="button" style="float: right">成为店主</a>
	<table width="100%" class="tb_normal" border="1" cellspacing="1" cellpadding="1">
		<thead>
			<tr>
				<th>昵称</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="dto.userList" var="wx">
			<tr>
				<td>${wx.nickname }</td>
				<td><a href="javascript:void(0)" onclick="delShopOwner(${wx.id })">移除店主</a></td>
			</tr>
			</s:iterator>
		</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
	
</div>