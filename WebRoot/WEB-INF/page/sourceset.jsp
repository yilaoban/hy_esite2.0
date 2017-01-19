<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
  <h4 class="modal-title" id="myModalLabel">资源配置</h4>
</div>
<div class="modal-body">
<form id="sourceform">
	<s:iterator value="sourceList" var="s">
		<input type="checkbox" name="sources" id="l_${s.id}" value="${s.id }" <s:if test="#s.pid == pageid">checked="checked"</s:if>><label for="l_${s.id }">${s.title }</label>
	</s:iterator>
	<input type="hidden" name="pageid" value="${pageid}">
</form>
</div>
<div class="modal-footer">
  <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
  <button type="button" class="btn btn-primary" onclick="saveSource()" data-dismiss="modal">保存</button>
</div>
<script type="text/javascript">
	function saveSource(){
		$.ajax({
            cache: true,
            type: "POST",
            url:"source_save.action",
            data:$('#sourceform').serialize(),// 你的formid
            async: false,
            error: function(request) {
                alert("Connection error");
            },
            success: function(data) {
            	$("#source-edit").load("sourceList.action?pageid=${pageid}");
            }
        });
	}
</script>