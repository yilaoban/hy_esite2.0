<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<link href="/css/login/index.css" rel="stylesheet" type="text/css" />

<!-- 登录 -->
<%@include file="/WEB-INF/card/background.jsp"%>


<div class="box_dl block ${blocks[0].ctname}" status="0" hyct="Y" style="${blocks[0].hyct}" hyblk="S" hydata="${blocks[0].rid}">
	
	 <s:if test="#session.visitUser.hyUser.username != null">
             <div class="box_dl">
				<span>欢迎您，${sessionScope.visitUser.hyUser.username } 先生/女士</span>
				<input name="登出" type="button" onclick="nnexit()"  class="button_dl" id="登出" value="登出"/>
				<input name="返回首页" type="button"  class="button_dl" id="返回首页" value="返回首页"/>
			</div>
  	</s:if>
    <s:else>
	  <ul>
	    <li>
	      <input  id="username" type="text" class="input_dl" placeholder="请输入用户名" />
	    </li>
	    <li>
	      <input  id="password" type="password" class="input_dl" placeholder="请输入登录密码" />
	    </li> 
	  </ul>
	  <input type="button" onclick="nnlogin()"  class="button_dl" id="登录" value="登录"/>
  	</s:else>
</div>
<script type="text/javascript">
function nnlogin() {
  var username = $('#username').val().trim();
  var password = $('#password').val().trim();
  var url = '/${oname}/user/hyuser/login.action';
  $.post(url,{"username":username,"password":password},function(data){
   if(data == 0){
	   $.alert("用户名或密码输入有误！","");
   }else {
    window.location.href="${blocks[0].link}";
   }
  });
}

function nnexit(){
	$.post("/${oname}/user/bbsexit.action",function(data){
		if(data == "Y"){
			window.location.reload();
		}else {
			alert('退出失败');
		}
	});
}
</script>

<%@include file="/WEB-INF/card/cardfile.jsp"%>
