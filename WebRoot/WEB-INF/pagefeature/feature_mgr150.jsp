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
			chkStyle: "radio",
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
		$("#currentid").val(catid);		
	}

	$(document).ready(function(){
		var zNodes =${dto.json};
		var zNodes1 =${dto.json1};
		var zNodes2 =${dto.json2};
		var zNodes3 =${dto.json3};
		$.fn.zTree.init($("#treeDemo0"), setting, zNodes);
		$.fn.zTree.init($("#treeDemo1"), setting, zNodes1);
		$.fn.zTree.init($("#treeDemo2"), setting, zNodes2);
		$.fn.zTree.init($("#treeDemo3"), setting, zNodes3);
	});
	
	
	function tabContent(id){
		$("#treeDemo0").hide();
		$("#treeDemo1").hide();
		$("#treeDemo2").hide();
		$("#treeDemo3").hide();
		$("#treeDemo"+id).show();
	}
</script>
<body class="iframe_body">
<form id="myform">
	<a href="javascript:tabContent(0)">产品目录</a>
	<a href="javascript:tabContent(1)">图文目录</a>
	<a href="javascript:tabContent(2)">视频目录</a>
	<a href="javascript:tabContent(3)">新闻目录</a>
	
	<ul id="treeDemo0" class="ztree" style="overflow-x:auto;margin-top:10px;"></ul>
	<ul id="treeDemo1" class="ztree" style="overflow-x:auto;margin-top:10px;display: none;"></ul>
	<ul id="treeDemo2" class="ztree" style="overflow-x:auto;margin-top:10px;display: none;"></ul>
	<ul id="treeDemo3" class="ztree" style="overflow-x:auto;margin-top:10px;display: none;"></ul>
	<input type="hidden" name="dto.fid" value="${fid}" />
	<input type="hidden" name="dto.current.id" id="currentid" value="${dto.current.id }"/>
	<div>
	使用详情页:
	<select name="dto.pageid">
      	<s:iterator value="dto.pagelist" var='p'>
      		<option value="${p.id}" <s:if test='#p.id == dto.pageid'>selected="selected"</s:if> >${p.name }</option>
      	</s:iterator>
      </select>
     <input type="button" id="save150" value="确定"/>
	<input type="button" value="取消" onclick="closeFrame()"/>
     </div>
</form>
<script type="text/javascript" src="/js/mulu/scriptbreaker-multiple-accordion-1.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".topnav").accordion({
			accordion:false,
			speed: 500,
			closedSign: '[+]',
			openedSign: '[-]'
		});
	});
</script>
<script type="text/javascript">
		$("#save150").click(function(){
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
