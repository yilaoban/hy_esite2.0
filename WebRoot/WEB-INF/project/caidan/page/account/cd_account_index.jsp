<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
	<div class="toolbar pb10">
		<s:set name="accountType" value="type"></s:set>
		<input type="button" value="新增" class="btn btn-primary" onclick="javascript:window.location='/${oname}/cd-account/addAccount.action?type=${type }'"/>
	</div>
	<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
		<tr>
			<th>帐号</th>
			<th>姓名</th>
			<th>手机</th>
			<th>邮箱</th>
			<th>地址</th>
			<s:if test='#accountType=="PRZ"'>
				<th>已绑定微信</th>
			</s:if>
			<th>操作</th>
		</tr>
		<s:iterator value="dto.list" var="a">
		<tr>
			<td><a href="/${oname }/cd-account/editAct.action?acid=${a.id}">${a.username }</a></td>
			<td>${a.fullname }</td>
			<td>${a.phone }</td>
			<td>${a.email }</td>
			<td>${a.title }</td>			
			<s:if test='#accountType=="PRZ"'>
				<td>
					<s:if test="#a.wxUser!=null">
						${a.wxUser.nickname }
					</s:if>
					<s:else>
						&nbsp;
					</s:else>
				</td>
			</s:if>
			<td>
				<s:if test='#accountType=="PRZ"'>
					<s:if test="#a.wxUser!=null">
						<a href="javascript:removeBD('${a.id }')">移除绑定</a>
					</s:if>
					<s:else>
						<a href="javascript:showQr('${a.id }')">绑定微信</a>
					</s:else>
				</s:if>
				<a href="javascript:changePwd('${a.id }')">修改密码</a>
				<a href="javascript:removeAT('${a.id }')">删除帐号</a>
			</td>
		</tr>
		</s:iterator>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>
<div id="msg" style="display: none">
  <div id="qrcode" style="float: left"></div>
  <div id="url" style="float: left"></div>
</div>
<script type="text/javascript" src="/js/qrcode.js"></script>
<script type="text/javascript">
var qrcode = new QRCode(document.getElementById("qrcode"), {
	width : 200,
	height : 200
});
function showQr(acid) {
	var url = "${pageDomain}/${oname }/user/wxshow/41448/wxn/"+acid+".html";
	qrcode.clear();
	qrcode.makeCode(url);
	$("#url").text(url);
	layer.msg('正在生成二维码,请稍等...', {
		icon : 16,
		time : 2000
	}, function() {
		layer.alert($("#msg").html(), {
			area : [ '480px', '360px' ]
		});
	});
}

function removeBD(acid){
	layer.confirm('确定移除此帐号的微信绑定么？', {
		  btn: ['是','否'] //按钮
		}, function(){
		  $.post("/${oname}/cd-account/jiechubangding.action",{"acid":acid},function(data){
			  if(data>0){
				  layer.msg('移除成功！', {icon: 6, time: 1500}, function(){
						window.location.reload();
				  }); 
			  }else if(data==0){
				  layer.msg('系统异常,请稍后再试!', {icon: 5, time: 2000});
			  }else{
				  layer.msg('权限不足!', {icon: 5, time: 2000});
			  }
			  
		  });
		}, function(index){
			layer.close(index);
		});
}

function removeAT(acid){
	layer.confirm('确定删除此帐号么？', {
		  btn: ['是','否'] //按钮
		}, function(){
		  $.post("/${oname}/cd-account/detAt.action",{"acid":acid},function(data){
			  if(data>0){
				  layer.msg('删除成功！', {icon: 6, time: 1500}, function(){
						window.location.reload();
				  }); 
			  }else if(data==0){
				  layer.msg('系统异常,请稍后再试!', {icon: 5, time: 2000});
			  }else{
				  layer.msg('权限不足!', {icon: 5, time: 2000});
			  }
			  
		  });
		}, function(index){
			layer.close(index);
		});
}

function changePwd(acid){
	layer.prompt({
		  title: '输入新密码',
		  formType: 1 
		}, function(pass){
		  layer.prompt({title: '再次输入新密码', formType: 1}, function(pass1){
			    if(pass!=pass1){
			    	layer.msg('两次密码输入不一致,请重新输入!', {icon: 5, time: 2000});
			    }else if(pass==""){
			    	layer.msg('密码不能为空!', {icon: 5, time: 2000});
			    }else{
			    	$.post("/${oname}/cd-account/updatePwd.action",{"acid":acid,"password1":pass},function(data){
						  if(data>0){
							  layer.msg('修改成功！', {icon: 6, time: 1500}, function(){
									window.location.reload();
							  }); 
						  }else if(data==0){
							  layer.msg('系统异常,请稍后再试!', {icon: 5, time: 2000});
						  }else{
							  layer.msg('权限不足!', {icon: 5, time: 2000});
						  }
			    	});
			    }
		  });
		});
}
</script>