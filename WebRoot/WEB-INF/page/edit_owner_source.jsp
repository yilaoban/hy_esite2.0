<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<link href="/css/wxjob.css" rel="stylesheet" type="text/css">
<div class="frame_content">
	<div class="inner_container_box side_l cell_layout">
		<div class="inner_container_box_bd">
			<div class="inner_side">
				<div class="bd">
					<div class="menu_manage">
						<div class="sub_title_bar light">
							<h4>菜单管理</h4>
							<div class="opr_wrp">
								<button class="btn btn-add" onclick="addMenu(0,-1)" title="添加">&nbsp;</button>
							</div>
						</div>
						<div class="inner_menu_box with_switch" id="menuList">
							<ul class="inner_menu list1">
								<s:iterator value="dto.os.jsonArray" var="arr" status="st">
									<li>
										<dt class="inner_menu_item" id="menu_${m.id}">
											<s:if test="#arr.list">
												<s:set name="list_size" value="#arr.list.size"></s:set>
											</s:if>
											<s:else>
												<s:set name="list_size" value="0"></s:set>
											</s:else>
											<a href="javascript:void(0)" class="inner_menu_link" onclick="editMenu(${st.index},-1)"><strong>${arr.name }</strong></a>
											<span class="menu_opr">
												<button class="btn btn-inner-add" onclick="addMenu(${st.index},${list_size});" title="添加">&nbsp;</button>
												<a onclick="RemoveMenu(${st.index},-1);" >—</a>
											</span>
										</dt>
										<ul class="list2">
											<s:iterator value="#arr.list" var="s" status="st2">
												<li>
													<dd class="inner_menu_item" id="subMenu_${m.id}_${s.id}">
														<i class="icon_dot">●</i>
														<a href="javascript:void(0);" class="inner_menu_link" id="${s.id}" fid="${s.fid}" name="${s.name}" type="${s.type}" key="${s.key}" url="${s.url}" idx="${s.idx}" onclick="editMenu(${st.index},${st2.index })">
															<strong>${s.name}</strong>
														</a>
														<span class="menu_opr">
															<a onclick="RemoveMenu(${st.index},${st2.index});" >—</a>
														</span>
													</dd>
												</li>
											</s:iterator>
										</ul>
										<div class="menu_mask"></div>
									</li>
								</s:iterator>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="inner_main">
				<div class="bd" style="padding:20px;">
						<form id="form2" class="formview jNice">
							<dl>
								<dt>导航背景色:</dt>
								<dd>
									<input type="color" onchange="saveStyle()" name="map['backgroundColor']" value="${dto.os.styleJson.backgroundColor }"/>
								</dd>
							</dl>
							<dl>
								<dt>字体颜色:</dt>
								<dd>
									<input type="color" onchange="saveStyle()" name="map['fontColor']" value="${dto.os.styleJson.fontColor }"/>
								</dd>
							</dl>
							<dl>
								<dt></dt>
								<dd>
									<input type="hidden" name="id" value="${id }"/>
									<button type="button" class="btn" onclick="javascript:$('#form2').hide()">关闭</button>
								</dd>
							</dl>
						</form>
					<form id="menuForm" style="display: none" class="formview jNice">
						<dl>
							<dt>标题</dt>
							<dd>
								<input type="text" id="name" name="menu.name" class="text-medium">
							</dd>
						</dl>
						<dl>
							<dt>链接</dt>
							<dd>
								<input type="text" id="link" name="menu.link" class="text-long">
							</dd>
						</dl>
						<dl>
							<dt>图标(可选)</dt>
							<dd>
								<img style="background-color:#E7E7E7;" id="uploadArrowShow" width="100px"/><a href="javascript:void()" class="delImg">删除</a>
								<div style="width:100%;overflow:hidden;">
									<div style="float:left;margin: 10px 0 0;" id="as2" ></div>
									<div style="clear:both;" id="pickerm2">上传图片</div>
								</div>
								<input type="hidden" id="ico" name="menu.ico"/>
							</dd>
						</dl>
						<dl>
							<dt></dt>
							<dd>
								<button type="button" class="btn btn-primary" onclick="saveMenu()">保存</button>
								<button type="button" class="btn" onclick="closeMenu()">关闭</button>
								<input type="hidden" id="idx1" name="idx1">
								<input type="hidden" id="idx2" name="idx2">
								<input type="hidden" name="id" value="${dto.os.id}">
							</dd>
						</dl>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<link rel="stylesheet" type="text/css" href="/diyUpload/css/webuploader.css">
<link rel="stylesheet" type="text/css" href="/diyUpload/css/diyUpload.css">
<script type="text/javascript" src="/diyUpload/js/webuploader.js"></script>
<script type="text/javascript" src="/diyUpload/js/diyUpload.js"></script>
<script type="text/javascript">

$(function(){
	$(".delImg").click(function(){
		$(this).hide();
		$("#ico").val("");
		$("#uploadArrowShow").hide();
	});
});

var jsonObj = JSON.parse('${dto.os.jsonArray}');
function editMenu(index1,index2){
	var obj;
	if(index2 >= 0){
		//二级目录
		obj = jsonObj[index1].list[index2];
	}else{
		//一级目录
		obj = jsonObj[index1];
	}
	$("#idx1").val(index1);
	$("#idx2").val(index2);
	showform(obj);//显示编辑
}

function closeMenu(){
	$("#menuForm").hide();
	$("#form2").show();
}

function showform(obj){
	$("#form2").hide();
	$("#menuForm").show();
	if(obj){
		$("#name").val(obj.name);
		$("#link").val(obj.link);
		$("#ico").val(obj.ico);
		if(obj.ico){
			$("#uploadArrowShow").attr("src",obj.ico);
		}else{
			$("#uploadArrowShow").hide();
		}
	}
}

function saveStyle(){
	$.ajax({
        cache: true,
        type: "POST",
        url:"/${oname}/page/save_source_style.action",
        data:$('#form2').serialize(),
        async: false
    });	
	$("#form2").show();
}

function addMenu(idx1,idx2){
	$(".delImg").hide();
	$("#ico").val("");
	$("#name").val("");
	$("#link").val("");
	if(idx2 < 0){
		$("#idx1").val(jsonObj.length);
		$("#idx2").val(idx2);
	}else{
		$("#idx1").val(idx1);
		if(jsonObj[idx1].list){
			$("#idx2").val(jsonObj[idx1].list.length);
		}else{
			$("#idx2").val(0);
		}
	}
	$("#form2").hide();
	$("#menuForm").show();
}

function RemoveMenu(idx1,idx2){
	$.post("/${oname}/page/remove_daohang.action",{"idx1":idx1,"idx2":idx2,"id":'${dto.os.id}'},function(data){
		if(data>0){
    		window.location.reload();
    	}
	})
}

function saveMenu(){
	$.ajax({
        cache: true,
        type: "POST",
        url:"/${oname}/page/save_edit_owner_source.action",
        data:$('#menuForm').serialize(),
        async: false,
        success: function(data) {
        	if(data>0){
        		window.location.reload();
        	}
        }
    });	
}

$('#as2').diyUpload({
	url:'/${oname}/user/h5UploadPic.action',
	success:function( data ) {
		$("#ico").val('${imgDomain}'+data.picUrl);
		$("#uploadArrowShow").hide();
	},
	error:function( err ) {
		console.info( err );	
	},
	del:function(filename) {
		$("#ico").val("");
		$("#uploadArrowShow").hide();	
	},
	auto: true,
	pick: '#pickerm2',
	fileNumLimit:1,
	fileSizeLimit:500000 * 1024,
	fileSingleSizeLimit:50000 * 1024,
	accept:{
		title:"Images",
		extensions:"gif,jpg,jpeg,bmp,png",
		mimeTypes:"image/*"
	}
});
</script>
