<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="frame_content">
	<form action="/yibosales/bbs/saveForum.action" method="post" id="myfrom2">
		<p>
			模板名称：
		    <input type="text" placeholder="请填写版块名称" id="title">
		    <input type="button" value="保存" class="btn btn-primary" id="savedh">
		</p>
		<div class="template-list clearfix" align="center">
			<s:iterator value="dto.vmList" var="vm">
				<div class="iphone-frame" id="${vm.id }">
			   		<div class="rel">
			      		<img class="template-pic" src="${vm.img }" title="${vm.title}" style="width:270px;height:186px;"/>
			      	</div>
			    </div>
			</s:iterator>        
		</div>
		
	</form>
</div>
<script type="text/javascript">
$(function(){
	$(".iphone-frame").click(function(){
		$(".highlight").removeClass("highlight");
		$(this).addClass("highlight");
	});
	$(".iphone-frame:first").click();
	$("#savedh").click(function(){
		var title = $("#title").val();
		if(title=="") 
			return;
		if($(".highlight").length != 1) 
			return;
		var vmid = $(".highlight").attr("id");
		$.post("/${oname}/page/save_owner_source.action",{"title":title,"vmid":vmid},function(data){
			if(data == 1){
				parent.window.location.reload();
			}
		})
	});
});
</script>
