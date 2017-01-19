<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/WEB-INF/pagechip/iframe_header.jsp" %>
<link rel="stylesheet" href="/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="/js/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="/js/ztree/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="/js/ztree/jquery.ztree.exedit-3.5.js"></script>
<script type="text/javascript">
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
		var zNodes =${dto.json4};
		$.fn.zTree.init($("#treeDemo0"), setting, zNodes);
	});
	
</script>
<body class="iframe_body">
<form id="myform">
	<input type="hidden" name="dto.fid" value="${fid}" />
	<input type="hidden" name="dto.type" value="F">
	<ul id="treeDemo0" class="ztree" style="overflow-x:auto;margin-top:10px;"></ul>
	<br>
	<select name="dto.pageid">
      	<s:iterator value="dto.pagelist" var='p'>
      		<option value="${p.id}" <s:if test='#p.id == dto.pageid'>selected="selected"</s:if> >${p.name }</option>
      	</s:iterator>
      </select>
	<input type="button" value="确定" id="save154"/>
	<input type="button" value="取消" onclick="closeFrame()"/>
	<s:iterator value="dto.ids" var="l">	
		<input type="hidden" value="${l}" name="dto.list" id="tr_${l}">
	</s:iterator>
	
</form>
<script type="text/javascript">
$("#save154").click(function(){
	$.ajax({
        cache: true,
        type: "POST",
        url:"/${oname}/page/config_sub.action?featureid=${featureid}",
        data:$('#myform').serialize(),
        async: false,
        success: function(data) {
        	if(data == "Y"){
				layer.load(2);
				setTimeout('layer.closeAll("loading");parent.layer.msg("操作成功!",{icon: 6, time: 1500},function(){closeFrame()})',2000);
        	}else{
				parent.layer.msg("保存失败！请重试！",{icon: 6, time: 1500},3);
        	}
        }
    });	
});
</script>
</body>
