<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
var um = UE.getEditor('myEditor');
$(document).ready(function() {
	$("#myModal").on("hidden.bs.modal", function() {
		$('#myfrom1')[0].reset();
		um.setContent("");
	});
	$("#start").blur(function(){
		var start=$(this).val();
		if(start!=""){
			var re = /^-?[0-9]+\.?[0-9]*$/;
			if(!re.test(start)){
				$("#scoreMsg").text("标准分必须为数字!").show();
				return;
			}
			var end=$("#end").val();
			if(end!=""&&start>end){
				$("#scoreMsg").text("得分下限必须小于等于上限!").show();
				return;
			}
		}
		$("#scoreMsg").text("").hide();
	});
	
	$("#end").blur(function(){
		var end=$(this).val();
		if(end!=""){
			var re = /^-?[0-9]+\.?[0-9]*$/;
			if(!re.test(end)){
				$("#scoreMsg").text("标准分必须为数字!").show();
				return;
			}
			var start=$("#start").val();
			if(start!=""&&start>end){
				$("#scoreMsg").text("得分下限必须小于等于上限!").show();
				return;
			}
		}
		$("#scoreMsg").text("").hide();
	})
	
	$("#subBtn").click(function(){
		$("#start").blur();
		$("#end").blur();
		if($("#scoreMsg").is(":visible")){
			$("#scoreMsg").focus();
			return;
		}
		
		var content = um.getContent();
		$('#examContent').val(content);
		var start=$("#start").val();
		var end=$("#end").val();
		if(start==""&&end==""){
			$("#scoreMsg").text("上限与下限不能同时为空!").show();
			return;
		}
		var resultid=$("#resultid").val();
		var url;
		if(resultid>0){
			url="/${oname}/interact/updateExamResult.action";
		}else{
			url="/${oname}/interact/addExamResult.action";
		}
		$.ajax({
	        cache: true,
	        type: "POST",
	        url:url,
	        data:$('#myfrom1').serialize(),// 你的formid
	        async: false,
	        error: function(request) {
	            layer.alert("Connection error",0);
	        },
	        success: function(data) {
	            if(data=="Y"){
	            	layer.msg('操作完成!', {icon: 6, time: 1000},function(){
		            	$('#myModal').modal('hide');
	    				window.location.reload();
	    			});
	            }else if(data=="N"){
	            	layer.msg('操作失败！请稍等……', {icon: 5, time: 1500});
	            }else{
	            	layer.msg(data, {icon: 5, time: 1500});
	            }
	        }
	    });
	});
});

function examRsDel(id){
	var layerid=layer.confirm('确定删除此结果吗?',{title:"删除测评结果"},function(){
		$.post("/${oname}/interact/delExamResult.action",{"resultid":id},function(data){
			if(data=="Y"){
				layer.msg('删除完成!',{icon: 6, time: 1000},function(){
					window.location.reload();
				});
			}else{
				layer.msg('删除失败!',{icon: 5, time: 1000});
			}
		});
	});
}

function examRsUpt(id){
	$.post("/${oname}/interact/findExamResult.action",{"resultid":id},function(data){
		if(data!=null){
			var content=data.content;
			um.setContent(content);
			$('#examContent').val(content);
			$("#start").val(data.start);
			$("#end").val(data.end);
			$("#resultid").val(data.id);
			$('#myModal').modal('show');
		}else{
			layer.msg('系统异常!',{icon: 5, time: 1000});
		}
	});
}

</script>
<div class="toolbar pb10">
	  <ul class="c_switch">
	  <li class="selected"><a href="">测评内容</a></li>
	  </ul>
	  <a href="javascript:void(0)" data-toggle="modal" data-target="#myModal" class="button">添加测评结果或建议 </a>
	  <a href="/${oname }/interact/index.action?mid=10015" class="return" title="返回"></a>
	</div>
	<table width="100%" cellspacing="1" cellpadding="1" border="1" class="tb_normal">
		<tr><td>得分范围(总分${dto.total }分)</td><td>测评结果</td><td>操作</td></tr>
		<s:iterator value="dto.list" var="r">
			<tr>
				<td>${r.start }~${r.end }</td>
				<td>${r.content }</td>
				<td><a href="javascript:examRsUpt('${r.id }')">编辑</a><i class="split">|</i><a href="javascript:examRsDel('${r.id }')">删除</a></td>
			</tr>
		</s:iterator>
	</table>
</div>
<div id="myModal" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog" style="width:400px;">
    <div class="modal-content">
    	<div class="modal-header">
		  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		  <h4 class="modal-title" id="myModalLabel">新增测评结果</h4>
		</div>
		<div class="modal-body">
			<form action="" method="post" id="myfrom1">
				<input type="hidden" name="examResult.examid" value="${examid }">
				<input type="hidden" name="examResult.id" id="resultid">
				<p>	
					得分范围:<input type="text" name="examResult.start" id="start" style="width: 40px"/>
					~
					<input type="text" name="examResult.end" id="end" style="width: 40px"/>
					<br/><span style="color:red;display:none" id="scoreMsg"></span>
				</p>
				<p>	
					<script type="text/plain" id="myEditor" style="width:358px;height:200px;"></script>
					<input type="hidden" name="examResult.content" id="examContent"/>
				</p>
			</form>
		</div>
		<div class="modal-footer">
		  <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		  <button type="button" class="btn btn-primary" id="subBtn">保存</button>
		</div>
    </div>
  </div>
</div>
