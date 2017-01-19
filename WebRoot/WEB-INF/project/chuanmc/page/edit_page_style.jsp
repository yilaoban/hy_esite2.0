<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
  <s:if test='type=="C"'><h4 class="modal-title" id="myModalLabel">编辑CSS</h4></s:if>
  <s:if test='type=="J"'><h4 class="modal-title" id="myModalLabel">编辑JS</h4></s:if>
</div>
<div class="modal-body">
<form action="/page/writeStyleToFile.action" method="post" id="form" enctype="multipart/form-data">
	<input type="hidden" id="pageid" name="pageid" value="${pageid }">
	<input type="hidden" id="pageid" name="fileUrl" value="${fileUrl }">
	<textarea name="writeContent">${readContent }</textarea>
</form>
</div>
<div class="modal-footer">
  	<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
  	<button type="button" class="btn btn-primary" onclick="updatePageCss(${pageid })" data-dismiss="modal">保存</button>
</div>
<script type="text/javascript">
		function updatePageCss(pageid){
		$.ajax({
               cache: true,
               type: "POST",
               url:"/page/writeStyleToFile.action",
               data:$('#form').serialize(),
               async: false,
               error: function(request) {
                   alert("Connection error");
               },
               success: function(data) {
               		if(data == 1){
						alert("修改成功");
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