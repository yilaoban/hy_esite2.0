<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
  <h4 class="modal-title" id="myModalLabel">编辑源码</h4>
</div>
<div class="modal-body">
<form action="/page/updateCode.action" method="post" enctype="multipart/form-data" id="myCodeform">
	<input type="hidden" id="pageid" name="pageid" value="${pageid }">
	<textarea name="html">${html}</textarea>
</form>
</div>
<div class="modal-footer">
  <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
  <button type="button" class="btn btn-primary" onclick="updateCode(${pageid })" data-dismiss="modal">保存</button>
</div>
<script type="text/javascript">
	function updateCode(pageid){
		$.ajax({
               cache: true,
               type: "POST",
               url:"/page/updateCode.action",
               data:$('#myCodeform').serialize(),
               async: false,
               error: function(request) {
                   alert("Connection error");
               },
               success: function(data) {
               		if(data == 1){
						window.location.href= "page/edit_kongbaipage.action?pageid=" + pageid;
               		}
               }
           });	
	}
	
</script>
<style>
textarea {
	width:98%;
	height:400px;
	overflow-y:100% 
}
</style> 