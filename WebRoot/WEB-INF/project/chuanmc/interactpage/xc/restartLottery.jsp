<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
</div>
<div class="modal-body">
	 <form action="xcLottery_update.action" method="post" enctype="multipart/form-data" id="form" class="formview">
  <input type="hidden" name="mid" value="10014">
  <input type="hidden" name="xcid" value="${xcid}">
	<dl>
	 	<dt>启动需取出的人数</dt>
		<dd>
			<input type="text" class="text-medium" id="num" autocomplete="off" name="num" onkeyup="this.value=this.value.replace(/\D/g,'')" value="${num }">
		</dd>
	</dl>
 </form>
</div>
<div class="modal-footer">
  <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
  <button type="button" class="btn btn-primary" onclick="restartLotteryDesign()" data-dismiss="modal">确定</button>
</div>
  <script type="text/javascript">
  	function restartLotteryDesign(){
		if($("#num").val()==""){
			$("#num_").html("请输入启动需取出的人数").css("color", "RED");
			return false;
		}
		$.ajax({ 
			cache: true,
			type: "POST",
			url:"/interact/restartXcLottery.action",
			data:$('#form').serialize(),
			async: false, 
			success: function(data) { 
        		if(data == "N"){
					layer.alert("操作失败,请重试!",0);
				}else{
					layer.alert("操作成功",1);
					window.parent.location.reload();
				}
			} 
		});
	}
  </script>