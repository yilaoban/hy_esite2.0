<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
	function createRule(){
		var action = $("#action").val();
		if(action == 0){
			alert("请选择动作名称");
			return;
		}else{
			var entity = '${entity}';
			var url = '/' + entity + '/bbs/saveRule.action';
			var jifen = $('#jifen').val();
			$.post(url,{"action":action,"jifen":jifen},function(data){
					if(data == "N"){
						alert("该积分规则已存在!");
					}else{
						alert("添加积分规则成功!");
						window.location.reload();
					}
				});
		}
	}
	
</script>
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
  <h4 class="modal-title" id="myModalLabel">添加积分规则</h4>
</div>
<div class="modal-body"> 
	<form action="/${sessionScope.account.owner.entity}/bbs/saveRule.action" method="post" id="myform">
		<p>
			动作名称： 
			<select name="action" id="action">
				<option value ="0">请选择动作名称</option>
				<option value ="1">发表主题</option>
				<option value ="2">发表回复</option>
				<option value ="3">顶</option>
				<option value ="4">踩</option>
				<option value ="5">主题被置顶</option>
			</select>
		</p>
		<p>
			增加积分：<input type="text" name="jifen" id="jifen" onkeyup="this.value = this.value.replace(/\D/g,'');"/>分
		</p>
	</form>
</div>
<div class="modal-footer">
  <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
  <button type="button" class="btn btn-primary" onclick="createRule()" data-dismiss="modal">保存</button>
</div>