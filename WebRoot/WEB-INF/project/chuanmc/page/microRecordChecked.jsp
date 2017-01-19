<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	function updateMicroRecord(){
		var aa = $('#status').val().length;
		if(aa > 3){
			layer.msg('限制3个字！', {icon: 5, time: 2000});
			return false;
		}
		$.post("/${oname}/bbs/updateMicroRecord.action",{"crid":${crid},"contentRecord.status":$('#status').val(),"contentRecord.remark":$('#content').val()},function(data){
			if(data == "Y"){
				layer.msg('操作中！请稍等……', {icon: 6, time: 1500}, function(){
					layer.msg('操作成功！', {icon: 6, time: 2000});
					window.location.reload();
				}); 
			}else{
				layer.msg('操作失败！请重试……', {icon: 5, time: 2000});
			}
		});
	}
</script>
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
  <h4 class="modal-title" id="myModalLabel">审核</h4>
</div>
<div class="modal-body">
	<form action="/${oname}/bbs/updateMicroRecord.action" method="post" id="myfrom1">
		<p>
			原因：<input type="text" name="contentRecord.status" id="status" value="${contentRecord.status}"><span>
		</p>
		<p>
			备注：<textarea name="contentRecord.remark" id="content">${contentRecord.remark}</textarea>		
		</p> 
	</form>
</div>
<div class="modal-footer">
  <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
  <button type="button" class="btn btn-primary" onclick="updateMicroRecord()">保存</button>
</div>
