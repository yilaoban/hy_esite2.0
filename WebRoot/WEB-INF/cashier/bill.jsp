<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head lang="en">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="format-detection" content="telephone=no">
	<meta name="format-detection" content="email=no">
	<meta content="width=320, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" name="viewport" />	
     <link rel="stylesheet" type="text/css" href="/css/cashier/reset.css" />
	<link rel="stylesheet" type="text/css" href="/css/cashier/index.css" />
	<script src="/js/jquery-1.11.1.min.js" type="text/javascript"></script>
	<script type="text/javascript">
	function exit(){
		$.post("/${oname}/user/cashierexit.action",function(data){
			if(data == "Y"){
				window.location.href = "/${oname}/user/cashierlogin.action";
			}
		});
	}
	
	
	$(document).ready(function(){
		$('#con').empty();
		$.post("/${oname}/user/userBillRecord.action",
		   function(data){
				$.each( data.ulist, function(i, n){
					$("#con").append("<tr><td>NO."+n.hyuid+"</td><td>"+n.createStr+"</td><td class='jine'>"+n.score/100+"元</td><td>"+n.desc+"</td></tr>");
				});
		   });
	});
	
	
	</script>
</head>
<body>
	<div class="pcs_img_bg">
		<ul class="pcs_tab">
			<li>
				<a href="javascript:void(0)" onclick="exit()" style="color: white;display: block;">退出登录</a>
			</li>
			<li>
				<a href="/${oname}/user/scan.action" style="color: white;display: block;">进入扫描</a>
			</li>
		</ul>
		<div class="pcs_white">
		<table class="pcs_table">
			<thead>
			<tr>
				<td>会员号</td>
				<td>消费时间</td>
				<td>消费金额</td>
				<td>消费备注</td>
			</tr>
			</thead>
			<tbody id="con">
			<tr>
				<td>123</td>
				<td>312</td>
				<td class="jine">123元</td>
				<td>消费备注</td>
			</tr>
			<tr>
				<td>123</td>
				<td>312</td>
				<td class="jine">123元</td>
				<td>消费备注</td>
			</tr>
			<tr>
				<td>123</td>
				<td>312</td>
				<td class="jine">123元</td>
				<td>消费备注</td>
			</tr>
			<tr>
				<td>123</td>
				<td>312</td>
				<td class="jine">123元</td>
				<td>消费备注</td>
			</tr>
			<tr>
				<td>123</td>
				<td>312</td>
				<td class="jine">123元</td>
				<td>消费备注</td>
			</tr>
			<tr>
				<td>123</td>
				<td>312</td>
				<td class="jine">123元</td>
				<td>消费备注</td>
			</tr>
			<tr>
				<td>123</td>
				<td>312</td>
				<td class="jine">123元</td>
				<td>消费备注</td>
			</tr>
			<tr>
				<td>123</td>
				<td>312</td>
				<td class="jine">123元</td>
				<td>消费备注</td>
			</tr>
			<tr>
				<td>123</td>
				<td>312</td>
				<td class="jine">123元</td>
				<td>消费备注</td>
			</tr>
			<tr>
				<td>123</td>
				<td>312</td>
				<td class="jine">123元</td>
				<td>消费备注</td>
			</tr>
			<tr>
				<td>123</td>
				<td>312</td>
				<td class="jine">123元</td>
				<td>消费备注</td>
			</tr>
			<tr>
				<td>123</td>
				<td>312</td>
				<td class="jine">123元</td>
				<td>消费备注</td>
			</tr>
			<tr>
				<td>123</td>
				<td>312</td>
				<td class="jine">123元</td>
				<td>消费备注</td>
			</tr>
			<tr>
				<td>123</td>
				<td>312</td>
				<td class="jine">123元</td>
				<td>消费备注</td>
			</tr>
			<tr>
				<td>123</td>
				<td>312</td>
				<td class="jine">123元</td>
				<td>消费备注</td>
			</tr>
			<tr>
				<td>123</td>
				<td>312</td>
				<td class="jine">123元</td>
				<td>消费备注</td>
			</tr>
			</tbody>
		</table>
		</div>
	</div>
</body>
</html>
