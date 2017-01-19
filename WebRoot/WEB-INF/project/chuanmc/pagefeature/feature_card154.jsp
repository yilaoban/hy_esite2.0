<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="/js/card.js"></script>
<script type="text/javascript" src="/js/block.js"></script>
<link rel="stylesheet" href="/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="/js/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="/js/ztree/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="/js/ztree/jquery.ztree.exedit-3.5.js"></script>
<div class="popup-header">
  <button type="button" class="close closePopup" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
  <h4 class="modal-title" id="myModalLabel">内容素材-产品目录</h4>
</div>
<div class="popup-body">
<form id="myform">
	<ul id="treeDemo0" class="ztree" style="overflow-x:auto;margin-top:10px;"></ul>
	<br>
	<s:iterator value="dto.ids" var="l">
		<input type="hidden" value="${l}" name="dto.list" id="tr_${l}">
	</s:iterator>
	<input type="hidden"   name="dto.type" value="T">
	<input type="hidden" name="dto.fid" value="${fid}" />
	<input type="hidden" name="dto.relationid" value="${relationid}" />
</form>
</div>
<div class="popup-footer">
  <button type="button" class="btn btn-default closePopup"  data-dismiss="modal">关闭</button>
  <button type="button" class="btn btn-primary" id="save154" data-dismiss="modal">保存</button>
</div>
<script type="text/javascript">
$(document).ready(function() {
    $(".closePopup").click(function(){
		$("#rightPopup").animate({width:'hide'});
	});
});
var setting = {
		check: {
			enable: true,
			chkStyle: "checkbox",
			radioType: "all"
		},
		callback: {
			onCheck: checkcategory
		},
		data: {
			keep:{
				leaf: true,
				parent: false
			},
			simpleData: {
				enable: true
			}
		},
	};

	function checkcategory(event, treeId, treeNode){
		var catid=treeNode.id;
		if(treeNode.checked == true){
			$("#myform").append("<input id='tr_"+catid+"' name='dto.list' value='"+catid+"' type='hidden' />");
		}else{
			$("#tr_"+catid).remove();
		}
	}

	$(document).ready(function(){
		var zNodes =${dto.json};
		$.fn.zTree.init($("#treeDemo0"), setting, zNodes);
	});
	
	$("#save154").click(function(){
	$.ajax({
        cache: true,
        type: "POST",
        url:"/${oname}/page/config_sub_new.action?featureid=${featureid}",
        data:$('#myform').serialize(),
        async: false,
        success: function(data) {
        	if(data == "Y"){
        		$("#rightPopup").animate({width:'hide'});
        		var cardid = $("#cardid").val();
        		var desc = $('#'+cardid).attr("desc");
        		reloadCard(cardid,desc);
        	}else{
        		bootbox.alert("保存失败！请重试…");
        	}
        }
    });	
});
</script>