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
</script>
<div class="wrap_content">
	<form action="/${oname}/content/findWxUser.action" method="post" id="myform">
		<input type="text" placeholder="输入昵称" name="nickname" value="${nickname }">		
		<input type="submit" value="搜索">
	</form>
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
				<td><a href="javascript:void(0)" onclick="setShopOwner(${wx.id })">设为店主</a></td>
			</tr>
			</s:iterator>
		</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
	
</div>