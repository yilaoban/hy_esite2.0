<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="popup-header">
  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
  <h4 class="modal-title" id="myModalLabel">内容素材</h4>
</div>
<div class="popup-body">
	<ul class="nav nav-tabs" id="contenttype">
		<li role="presentation" class="active" type="T"><a href="javascript:tabContent(0)">产品目录</a></li>
		<li role="presentation" type="P"><a href="javascript:tabContent(1)">图文目录 </a></li>
		<li role="presentation" type="V" ><a href="javascript:tabContent(2)">视频目录</a></li>
		<li role="presentation" type="N"><a href="javascript:tabContent(3)">新闻目录</a></li>
		<li role="presentation" type="T"><a href="javascript:tabContent(5)">微电商目录</a></li>
		<li role="presentation" type="T"><a href="javascript:tabContent(6)">微积分目录</a></li>
	</ul>
	<div style="width: 30%;float:left">
		<ul id="treeDemo0" class="ztree" style="overflow-x:auto;margin-top:10px;"></ul>
		<ul id="treeDemo1" class="ztree" style="overflow-x:auto;margin-top:10px;display: none;"></ul>
		<ul id="treeDemo2" class="ztree" style="overflow-x:auto;margin-top:10px;display: none;"></ul>
		<ul id="treeDemo3" class="ztree" style="overflow-x:auto;margin-top:10px;display: none;"></ul>
		<ul id="treeDemo5" class="ztree" style="overflow-x:auto;margin-top:10px;display: none;"></ul>
		<ul id="treeDemo6" class="ztree" style="overflow-x:auto;margin-top:10px;display: none;"></ul>
	</div>
	<div style="width: 70%;float:left">
		<ul id="su_content">
		</ul>
	</div>
</div>
<link rel="stylesheet" href="/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="/js/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="/js/ztree/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="/js/ztree/jquery.ztree.exedit-3.5.js"></script>
<script type="text/javascript">
$("[role='presentation']").click(function(){
	$(".active").removeClass("active");
	$(this).addClass("active");
});
var setting = {
		callback: {
			onClick: checkcategory
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

	var currentpage = 1;
	var jj = true;
	function checkcategory(event, treeId, treeNode){
		var catid=treeNode.id;
		var pageid = treeNode.pageid;
		currentpage = 1;
		loadcontent(catid,pageid);
	}
	
	function loadcontent(catid,pageid){
		var type = $("#contenttype .active").attr("type");
		$.post("/${oname}/page/loadcontent.action",{"ccid":catid,"pageId":currentpage},function(data){
			$("#su_content").empty();
			var size = 0 ;
			if(type=='T'){
				size = data.product.length;
				$.each(data.product,function(index,value){
					$("#su_content").append("<li class='list-group-item-s1' onMouseOver=\"this.className='list-group-item-s2'\" onMouseOut=\"this.className='list-group-item-s1'\" re_href='/${oname}/user/show/"+pageid+"/pcn/ctt-hy-"+value.id+".html'>"+value.name+"</li>");
				});
			}else if(type=='P'){
				size = data.pictureList.length;
				$.each(data.pictureList,function(index,value){
					$("#su_content").append("<li class='list-group-item-s1' onMouseOver=\"this.className='list-group-item-s2'\" onMouseOut=\"this.className='list-group-item-s1'\" re_href='/${oname}/user/show/"+pageid+"/pcn/cpp-hy-"+value.id+".html'>"+value.title+"</li>");
				});
			}else if(type=='N'){
				size = data.newList.length;
				$.each(data.newList,function(index,value){
					$("#su_content").append("<li class='list-group-item-s1' onMouseOver=\"this.className='list-group-item-s2'\" onMouseOut=\"this.className='list-group-item-s1'\" re_href='/${oname}/user/show/"+pageid+"/pcn/n-"+value.id+".html'>"+value.title+"</li>");
				});
			}else{
				size = data.videoList.length;
				$.each(data.videoList,function(index,value){
					$("#su_content").append("<li class='list-group-item-s1' onMouseOver=\"this.className='list-group-item-s2'\" onMouseOut=\"this.className='list-group-item-s1'\" re_href='/${oname}/user/show/"+pageid+"/pcn/cvv-hy-"+value.id+".html'>"+value.title+"</li>");
				});
			}
			if(currentpage > 1 || size >=10){
				$("#su_content").append("<a class='huanyipi'>换一批</a>");
			}
			if(jj){
				if(currentpage > 1 && size < 10){
					jj = false;
				}
			}else{
				if(currentpage == 1){
					jj = true;
				}
			}
			$(".list-group-item-s1").on("click",function(){
				$("#normalModal").modal('hide');
				var link = $(this).attr("re_href");
				var inputid = '${inputid}';
				$("#"+inputid).val(link);
			});
			
			$(".huanyipi").click(function(){
				if(jj){
					currentpage++;
				}else{
					currentpage--;
				}
				loadcontent(catid,pageid);
			});
		});	
	}

	$(document).ready(function(){
		var zNodes =${dto.json};
		var zNodes1 =${dto.json1};
		var zNodes2 =${dto.json2};
		var zNodes3 =${dto.json3};
		var zNodes5 =${dto.json5};
		var zNodes6 =${dto.json6};
		$.fn.zTree.init($("#treeDemo0"), setting, zNodes);
		$.fn.zTree.init($("#treeDemo1"), setting, zNodes1);
		$.fn.zTree.init($("#treeDemo2"), setting, zNodes2);
		$.fn.zTree.init($("#treeDemo3"), setting, zNodes3);
		$.fn.zTree.init($("#treeDemo5"), setting, zNodes5);
		$.fn.zTree.init($("#treeDemo6"), setting, zNodes6);
	});
	
	
	function tabContent(id){
		$("#treeDemo0").hide();
		$("#treeDemo1").hide();
		$("#treeDemo2").hide();
		$("#treeDemo3").hide();
		$("#treeDemo5").hide();
		$("#treeDemo6").hide();
		$("#treeDemo"+id).show();
		$("#su_content").empty();
	}
</script>
<style>
	.list-group-item-s1{
		border:2px solid #e5e5e5;
	}
	#su_content li{
		width:50%;
		line-height:26px;
		cursor:pointer;
		margin-top:4px;
		padding:0 5px;
	}
	.huanyipi{
		cursor:pointer;
	}
	
	#su_content li.list-group-item-s1{ border:2px solid #e5e5e5;}
	#su_content li.list-group-item-s2{ border:2px solid #00a0e9;}
</style>
