<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<link href="/css/hudong/shequ/index.css" rel="stylesheet" type="text/css">
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
    <div class="ui-header"> 
        <h1>用户注册</h1>  
    </div>
    <div class="ui-body">
      <form> 
      	  <div id="step1">
	          <div class="formopt">
	             <input type="text" name="bbsUser.username" id="username" placeholder="账户名" ">
	          </div>  
	          <div class="formopt">
	          	 <input type="text" name="checkCode" id="code" placeholder="验证码"   class="Captcha" style="width:150px;">
	         	 <img id="imageC" src="${pageContext.servletContext.contextPath }/checkCode.cc"   onclick="myreload()" title="点击更换验证码" style="height:38px;float:right" />
	          </div>  
			  <input class="subbtn" onclick="nextStep()" type="button" value="下一步">
      	  </div>
		  
		  <div style="display: none;" id="step2"> 
			<input class="subbtn" onclick="$('#step1').show();$('#step2').hide()" type="button" value="上一步">
			<div class="formopt" style="height:38px;">
	    		<div id="demo">
					<div id="as" ></div>
					<div id="picpicker">上传头像</div>
				</div>
				<input type="hidden" id="img" />
			</div>
    		<input type="hidden" id="nickname" placeholder="请输入昵称" value="${sessionScope.visitUser.wxUser.nickname}">
    		<div class="formopt">
       			<input type="password" name="bbsUser.password" id="password" placeholder="密码" onblur="checkStyle()">
       		</div>
       		<div class="formopt">
       			<input type="password" id="password1" placeholder="再次输入密码" onblur="checkPassword()">
       		</div>
           	<span id="error"></span>
	        <div class="formopt"> 
	            <input class="subbtn" onclick="bbsregister()" type="button" value="注册"> 
	        </div> 
		  </div>
      </form> 
    </div> 
	<link rel="stylesheet" type="text/css" href="/diyUpload/css/webuploader.css">
	<link rel="stylesheet" type="text/css" href="/diyUpload/css/diyUpload.css">
<script type="text/javascript">
function bbsregister() {
	var username = $('#username').val().trim();
	var code = $('#code').val().trim();
	var password = $('#password').val().trim();
	var nickname = $('#nickname').val().trim();
	var img = $('#img').val();
	if(img != ''){
		img = '${imgDomain}' + img;
	}
	if(username=="" || password=="" || code == "") {
		$.alert("请填写完整的信息！","");
		return;
	}
	if(!checkStyle()){
		return;
	}
	if(!checkPassword()){
		return;
	}
	$.post("/${oname}/user/bbs/findBBSUserByName.action",{"username":username},function(data){
		if(data > 0){
			$.alert("该账号已注册，去登录","");
		 	setInterval(function(){
				window.location.href = "/${oname}/user/bbs/toLogin.action?forumid=" + $("#hy_source").val();
			},2000);
			return;
		}else{
			$("#error").html("");
			$.post("/${oname}/user/hyuser/doRegister.action",{"username":username,"password":password,"code":code,"nickname":username,"img":img},function(data){
				if(data == "Y"){
					$.alert("注册成功！","");
					var pageid = '${sessionScope.visitUser.pageid }';
					var source = '${sessionScope.visitUser.source }';
					var oname = '${oname}';
					if(source == ''){
						source = 'bbs';
					}
					var url = "/" + oname + "/user/show/" + pageid+ "/" + source + ".html";
					window.location.href = url;
				}else if(data == "N"){
					$.alert("注册失败！","");
				}else{
					$.alert(data,"");
				}
			});
		}
	});
}

function checkPassword(){
	var password = $('#password').val().trim();
	var password1 = $('#password1').val().trim();
	if(password != password1){
		$("#error").html("密码输入不一致，请重新输入").css("color", "RED");
		return false;
	}else{
		$("#error").html("");
		return true;
	}
}

function checkStyle(){
	var re = /^[0-9_a-zA-Z]{6,20}$/;
	if(!(re.test($("#password").val()))){
		$("#error").html("长度在6~18之间，只能包含字母、数字和下划线").css("color", "RED");
		return false;
	}else{
		$("#error").html("");
		return true;
	}
}

function nextStep(){
	var username = $('#username').val().trim();
	var validCode = $('#code').val().trim();
	if( username=="") {
		$.alert("请填写账户名","");
		return;
	}
	if(validCode ==""){
		$.alert("请输入验证码！","");
		return;
	}
	$('#step2').show();$('#step1').hide();
}

function myreload()
	{
		document.getElementById("imageC").src = document.getElementById("imageC").src+"?nocache="+new Date().getTime();
	}

jQuery('#as').diyUpload({
	url:'/${oname}/user/h5UploadPic.action',
	success:function( data ) {
		console.info( data );
		$('#img').val(data.picUrl);
	},
	error:function( err ) {
		console.info( err );	
	},
	auto: true,
	pick: '#picpicker',
	//最大上传的文件数量, 总文件大小,单个文件大小(单位字节);
	fileNumLimit:1,
	fileSizeLimit:500000 * 1024,
	fileSingleSizeLimit:50000 * 1024,
	accept:{
				title:"Images",
				extensions:"gif,jpg,jpeg,bmp,png",
				mimeTypes:"image/*"
			}
});
</script>
<%@include file="/WEB-INF/card/cardfile.jsp"%>