<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
   $(document).ready(function(e) {
		$("#appdiv").hide();
	});
	function nextStep(){
	    var pageid=$("#pageid").val();
	    if(pageid<0){
	       layer.alert('请选择一个页面!');
	       return;
	    }
	    $("#pagediv").hide();
	    $("#appdiv").show();
		$("#tip").html("");
		$("#tip").show();
		var pageid=$("#pageid").val();
	$.ajax({  
           type: "POST",  
           url: "/page/findSinaTokenIsExit.action",  
           dataType: "json",  
           data:  "pageid="+pageid,  
           success: function (jsonStr) {
               if(jsonStr[0].count > 0){
               	  $("#tip").html("该页面已经绑定了微博账号，直接查看"+"<a href='jsonStr[0].address'>"+jsonStr[0].address+"</a>");
               }
           }  
    }); 
	}
	function lastStep(){
	    $("#pagediv").show();
	    $("#appdiv").hide();
		$("#sub").hide();
		$("#tip").html("");
		$("#tip").hide();
	}
	function publishSub(){
	    var appid=$("#appid").val();
	    if(appid<0){
	       layer.alert('请选择一个应用!');
	       return;
	    }
	    $("#pbform").submit();
	}
</script>

<div>
	<form action="/user/publishLink.action" id="pbform"
		enctype="multipart/form-data" method="post" class="jNice">
		<div id="pagediv">
		<s:if test='pbdto.plist.size>0'>
			<dl class="bindview">
				<dt>页面</dt>
				<dd>
				<select name="pageid" id="pageid">
					<s:iterator value="pbdto.plist" var="p">
						<option value="${p.id}">
							${p.name}
						</option>
					</s:iterator>
				</select>
				</dd>
			</dl>
			<dl class="bindview">
				<dt></dt>
				<dd>
				<input type="button" value="下一步" onclick="nextStep()">
				</dd>
			</dl>
		</s:if>
		<s:else><p>暂时没有能够绑定到微博的页面(上线之后才能绑定)</p></s:else>
		</div>
		<div id="appdiv">
			<dl class="bindview">
				<dt>应用</dt>
				<dd>
				<select id="appid" name="appid">
					<option value="-1">
						请选择应用
					</option>
					<s:iterator value="pbdto.applist" var="a">
						<option value="${a.id}">
							${a.name}
						</option>
					</s:iterator>
				</select>
			</dd>
			</dl>
			<dl class="bindview">
				<dt></dt>
				<dd>
				<input type="button" value="上一步" onclick="lastStep()">
				<input type="button" value="确定" onclick="publishSub()">
				</dd>
			</dl>
			<span id="tip"></span>
		</div>
		<div class="instruction">
		<h4>
			绑定到微博后如何在官微显示页面
		</h4>
		<dl>
			<dt>
				1、添加应用
			</dt>
			<dd>
				在新浪微博登陆官微，点击“管理中心”——>>“应用中心”——>>“应用广场”，在右上角的搜索框中输入应用名。若在第2步中选择的应用为“活动会”，则在应用广场搜索“活动会”应用。点击“查看详情”，然后点击“立即使用”。
			</dd>
			<dd>
				<img src="/images/sub_weibo_img1.png">
			</dd>
			<dt>
				2、重命名应用
			</dt>
			<dd>
				完成上一步后点击“管理中心”——>>“应用中心”——>>“管理”，刚添加的应用“活动会”在这里展示。点击“重命名”修改应用名称，该名称将显示在导航栏上。
			</dd>
			<dd>
				<img src="/images/sub_weibo_img2.png">
			</dd>
			<dt>
				3、官微显示应用
			</dt>
			<dd>
				勾选“粉丝可见”，微博page就能显示在您的官微上啦，通知您的粉丝们赶快来参加活动吧！
			</dd>
		</dl>
		</div>
	</form>
</div>
