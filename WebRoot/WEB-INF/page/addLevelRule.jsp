<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
	function createLevel(){
		var level_name = $("#level_name").val();
		var require_jf = $('#require_jf').val();
		if(level_name == ""){
			alert("请填写等级名称");
			return;
		}
		if(require_jf == ""){
			alert("请输入该等级所需积分");
			return;
		}
		var entity = '${entity}';
		var url = '/' + entity + '/bbs/saveLevel.action';
		$.post(url,{"level_name":level_name,"require_jf":require_jf},function(data){
				if(data == "N"){
					alert("该等级需要更高积分!");
				}else{
					window.location.reload();
				}
			});
	}
	
</script>
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
  <h4 class="modal-title" id="myModalLabel">添加积分规则</h4>
</div>
<div class="modal-body"> 
	<form action="/${sessionScope.account.owner.entity}/bbs/saveRule.action" method="post" id="myform">
		<p>
			等级名称：<input type="text" name="level_name" id="level_name"/>
		</p>
		<p>
			所需积分：<input type="text" name="require_jf" id="require_jf" onkeyup="this.value = this.value.replace(/\D/g,'');"/>分
		</p>
	</form>
</div>
<div class="modal-footer">
  <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
  <button type="button" class="btn btn-primary" onclick="createLevel()" data-dismiss="modal">保存</button>
</div>