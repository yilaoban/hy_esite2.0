<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
$(document).ready(function() {
		$("#siteid").change(function(){
			$.post("/${oname}/content/ajax_get_page.action",{"siteid":$("#siteid").val()},
			   function(data){
					$("#pageid").html("");
					$("#pageid").append("<option value='-100'>选择页面</option>");
					$('#pageid').prev().children(":first").children(":first").text("选择页面");
					$.each( data, function(i, n){
						$("#pageid").append("<option value='"+n.pageid+"' id='c_"+n.pageid+"'>"+n.name+"</option>");
					});
			   }, "json");
		});
});

function saveWKQ() {
	var pageid = $('#pageid').val().trim();
	if(pageid == -100){
		alert("请选择页面！");
		return;
	}
	$.post("/${oname}/content/saveWKQ.action",{"pageid":pageid},function(data){
			if(data > 0){
				alert("设置成功！");
				window.location.reload();
			}else {
				alert("设置失败！");
			}
		});
}
</script>

<div class="wrap_content">
	
	<s:if test="page != null">
		已经设置了${page.name }为验证页面
	</s:if>
	<br/>
	<select name="siteid" id="siteid">
		<option value ="-100">选择站点</option>
		<s:iterator value="siteList" var="s">
		  <option value="${s.id}" id="p_${s.id}">${s.name}</option>
		</s:iterator>
	</select>
	<select name="pageid" id="pageid">
		<option value ="-100" >选择页面</option>
	</select>
	<input type="button" onclick="saveWKQ()" value="设置验证页面">
</div>