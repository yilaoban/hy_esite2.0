<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<link href="/css/hudong/shequ/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
function bbslogin() {
		var username = $('#username').val().trim();
		var password = $('#password').val().trim();
		var oname = '${oname}';
		var url = '/' + oname + '/user/hyuser/login.action';
		$.post(url,{"username":username,"password":password},function(data){
			if(data == 0){
				$.alert("用户名或密码输入有误！","");
			}else {
				var pageid = '${sessionScope.visitUser.pageid }';
				var source = '${sessionScope.visitUser.source }';
				if(source == '' ){
					source = 'bbs';
				}
				window.location.href = '/'+ oname +'/user/show/'+pageid+ '/' + source + '.html';
			}
		});
}
</script>
    <style type="text/css">
      .ui-header {
        background: #E9E9E9;
        height: 50px;
        border-bottom: 1px solid #DDDDDD;
        line-height: 50px;
        font-weight: bold;
        text-align: center;
        font-size: 1.2em;
        color: #333;
      }
      .ui-header h1 {
    	margin: 0;
	    padding: 0;
	    border: 0;
	    font-size: 100%;
	    font: inherit;
	    vertical-align: baseline;
      }
      .ui-body {
        padding: 10px;
      }
      input[type="text"], input[type="password"]  {
        width: 280px;
	    padding: 9px;
	    border: 1px solid #DDDDDD;
	    border-radius: 6px;
      }
      input[type="button"] {
        padding: 9px;
        border: 1px solid #DDDDDD;
        background: #F6F6F6;
        border-radius: 6px;
        font-size: 10px;
        font-weight: bold;
      }
      .subbtn {
        width: 100%;
      }
      .formopt {
        margin: 1em 0;
        position: relative;
      }
      #get_code {
        position: absolute;
        background: #e3e3e3;
        text-decoration: none;
        min-width: 100px;
        color: #555;
        padding: .4em;
        top: .4em;
        right: .4em;
        border-radius: .4em;
        font-size: .8em;
        font-weight: normal;
      }
    </style>
    <%@include file="/WEB-INF/card/background.jsp"%>
    
    <div class="ui-header"> 
        <h1>用户登录</h1>  
    </div>
    <div class="ui-body">
      <form>  
          <div class="formopt">
            <input id="username" name="bbsUser.username" type="text" placeholder="用户名"> 
          </div>
          <div class="formopt">   
            <input type="password" name="bbsUser.password" id="password" placeholder="密码">
          </div>
          <div class="formopt"> 
            <input class="subbtn" onclick="bbslogin()" type="button" value="登录">
          </div> 
          <a id="qzc">去注册</a>
      </form> 
      <script type="text/javascript">
      	$(document).ready(function(){
      		$("#qzc").attr("href","/${oname}/user/bbs/register.action?forumid="+$("#hy_source").val());
      	});
      </script>
    </div> 
<%@include file="/WEB-INF/card/cardfile.jsp"%>