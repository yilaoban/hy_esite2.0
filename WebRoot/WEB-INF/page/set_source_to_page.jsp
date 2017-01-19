<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
  <h4 class="modal-title" id="myModalLabel">选择导航</h4>
</div>
<div class="modal-body" style="height:auto;overflow:hidden;">
<form id="btForm" class="blockview">
	<s:iterator value="dto.list" var ="l">
		<input type="radio" name="os" value="${l.id}" id="dh—${l.id }" <s:if test="dto.source.os.id == id"> checked="checked" </s:if> ><label for="dh—${l.id }">${l.title}</label>
	</s:iterator>
</form>
</div>
<div class="modal-footer">
	<button type="button" class="btn btn-default" id="canceldh" data-dismiss="modal">取消导航</button>
  <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
  <button type="button" class="btn btn-primary" id="dhsave" data-dismiss="modal">保存</button>
</div>
<script type="text/javascript">
$("#dhsave").click(function(){
	var id = $('input:radio[name=os]:checked').val();
	if(id > 0){
		$.post("save_source_to_page.action",{"id":id,"pageid":'${pageid}'},function(data){
			if(data > 0 ){
				bootbox.alert("保存成功!");
				var pcid = $('#currentCard').val();
				var desc = $('#'+pcid).attr("desc");
				reloadCard(pcid,desc);
			}
		})
	}
});

$("#canceldh").click(function(){
	$.post("cancel_source_to_page.action",{"pageid":'${pageid}'},function(data){
		if(data > 0 ){
			bootbox.alert("取消成功!");
			var pcid = $('#currentCard').val();
			var desc = $('#'+pcid).attr("desc");
			reloadCard(pcid,desc);
		}
	})
});
</script>