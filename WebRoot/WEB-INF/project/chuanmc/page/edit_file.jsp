<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<link rel="stylesheet" href="/js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="/js/ztree/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="/js/ztree/jquery.ztree.core-3.5.js"></script>
<SCRIPT type="text/javascript">
		<!--
		var setting = {
			callback: {
				onClick:onClick
			}
		};
		
		$.ajaxSetup ({
		cache: false //close AJAX cache
		});

		var zNodes =${json};

		$(document).ready(function(){
			if(zNodes.length==0){
				alert("没有可编辑文件");
			}else{
				$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			}
		});
		
		function onClick(e, treeId, treeNode){
			$("#div").load("showEditFile.action?path="+treeNode.path);
		}
		//-->
</SCRIPT>
<div class="wrap_content">
<div class="switch_tab_div pb10">
	<span><a href="/${oname}/page/sitegroupList.action"><s:if test='site.type=="P"'>移动站点</s:if><s:elseif test='site.type=="C"'>PC站点</s:elseif></a><i class="gt">&gt;&gt;</i> <a href="/${oname }/page/pageconfig_new.action?siteid=${site.id }&stype=${site.type }">${site.name }</a><i class="gt">&gt;&gt;</i><s:if test='isImg=="N"'>编辑文件</s:if><s:elseif test='isImg=="Y"'>替换多媒体</s:elseif><s:elseif test='isImg=="P"'>通用管理</s:elseif></span>
	<p class="clearfix"></p>
</div>
<div class="zTreeDemoBackground left">
	<ul id="treeDemo" class="ztree"></ul>
</div>
<a href="/${oname }/page/pageconfig_new.action?siteid=${siteid}" class="return">返回</a>
<div id="div" style="width: 581px; height: 372px;float: left">
</div>
</div>