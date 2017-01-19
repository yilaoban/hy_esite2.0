<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
var um = UE.getEditor('myEditor${nowSecond }');
function formSub(url){
			$.ajax({
		        cache: true,
		        type: "POST",
		        url:url,
		        data:$('#form1').serialize(),// 你的formid
		        async: false,
		        error: function(request) {
		            layer.alert("Connection error",0);
		        },
		        success: function(data) {
		            if(data=="Y"){
		            	window.parent.location.reload()
		            }else{
		            	layer.alert("操作失败!",0);
		            }
		        }
		    });
		}
</script>
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
  <h4 class="modal-title" id="myModalLabel">新增测评结果</h4>
</div>
<div class="modal-body">
	<form action="" method="post" id="myfrom1">
		<input type="hidden" name="examid" value="${examid }">
		<p>	
			得分范围:<input type="text" name="start" style="width: 40px" onkeyup="this.value = this.value.replace(/\D/g,'');"/>~<input type="text" name="end" style="width: 40px" onkeyup="this.value = this.value.replace(/\D/g,'');"/>
		</p>
		<p>	
			<script type="text/plain" id="myEditor${nowSecond }" style="width:100%;height:200px;"></script>
		</p>
	</form>
</div>
<div class="modal-footer">
  <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
  <button type="button" class="btn btn-primary" onclick="formsub()">保存</button>
</div>