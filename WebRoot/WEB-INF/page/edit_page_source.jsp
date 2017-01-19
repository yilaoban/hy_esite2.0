<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
  <h4 class="modal-title" id="myModalLabel">源代码</h4>
</div>
<div class="modal-body">
	<div class="frame_content">
	<form id="form" action="/page/add_sub.action?pageid=${pageid}" method="post">
		<textarea name="source" style="width:98%;height:300px;overflow-y:100%">${source }</textarea>
	</form>
	</div>
</div>
<div class="modal-footer">
	  <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	  <button type="button" class="btn btn-primary" onclick="save(${pageid })" data-dismiss="modal">保存</button>
</div>
<script type="text/javascript">
		function save(pageid){
			$.ajax({
	               cache: true,
	               type: "POST",
	               url:"/${oname}/page/savePageSource.action?pageid="+pageid,
	               data:$('#form').serialize(),
	               async: false,
	               error: function(request) {
	                   alert("Connection error");
	               },
	               success: function(data) {
	               		if(data == 1){
							alert("修改成功");
							$('#ifm').attr('src', $('#ifm').attr('src'));
	               		}
	               }
	           });	
		}
	
</script>

<s:if test='result != null && result != "" '>
	<s:if test='result == "Y"'>
		<script>
			parent.layer.load(2,2);
			setTimeout('parent.layer.msg("操作成功!",2,1)',2000);
			var i = parent.layer.getFrameIndex();
			//parent.layer.close(i); 
			setTimeout("window.parent.location.reload()",3000);
		</script>
	</s:if>
	<s:else>
		<script>parent.layer.msg('${result}',2,3);</script>
	</s:else>
</s:if>
