<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<link rel="stylesheet" href="/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="/js/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="/js/ztree/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="/js/ztree/jquery.ztree.exedit-3.5.js"></script>
<SCRIPT type="text/javascript">
		<!--
		var setting = {
			view: {
				fontCss: getFont,
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
			callback: {
				onClick:onClick
			}
		};

		var zNodes =${dto.tree};

		function getFont(treeId, node) {
			return node.font ? node.font : {};
		}
		
		function onClick(e, treeId, treeNode){
			window.location.replace("/"+'${oname}'+"/content/content_form_index.action?ccid="+treeNode.id+"&typeid="+${dto.current.typeid})
		}
		
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
			var node = treeObj.getNodeByParam("id", '${dto.current.id}', null);
			var treeLevel=node.level;
			$("#treeLevel").val(treeLevel)
		});
		
		
		
		function delcfr(formid,id){
		//delcfr
			layer.confirm('确定删除吗?',function(){
				$.post("/content/delcfr.action",{"formid":formid,"recordid":id},function(data){
					if(data == "Y"){
						layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
							window.location.reload();
						}); 
					}else{
						layer.msg('删除失败！请重试……', {icon: 5, time: 2000});
					}
				});
			});
		}		
		
		function cfrdetail(formid,id){
				var srcString = '/'+'${oname}'+'/content/cfrDetail.action?formid=' + formid+'&recordid='+id;
				layer.open({
						type : 2,
						area : ['400px','450px'],
						title : '详情',
						content: srcString
				});
		}
	</SCRIPT>
		<style type="text/css">
.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
	</style>
<div class="wrap_content">
	<div class="module_list clearfix">
	<ul>
		<s:iterator value="dto.typeList" var="t">
			<li <s:if test='dto.current.typeid==id'>class="select"</s:if>>
			<s:if test='type=="F"'>
				<a href="content_form_index.action?typeid=${t.id }">${t.name }</a>
			</s:if>
			<s:else>
				<a href="content_index.action?typeid=${t.id }">${t.name }</a>
			</s:else>
			</li>
		</s:iterator>
		<li style="background:#fff;"><a href="javascript:void(0);" style="color:#7298B9;" onclick="config_contenttype()"/>更多</a></li>
	</ul>
	</div>
	<div class="tree">
		<div>
			<input type="hidden" id="treeLevel" value="0"/>
			<a href="content_index.action?typeid=${dto.current.typeid}&ccid=-1" title="返回首层"><img width="24px" src="/images/folder.png"></a>
			<a href="javascript:addcategory('${dto.current.id }','${dto.current.type}','${dto.current.typeid}')" title="增加" style="margin-left:8px;"><img width="24px" src="/images/folder-add.png"></a>
			<a href="javascript:updateCategoryName('${dto.current.id }','${dto.current.name }')" title="编辑" style="margin-left:8px;"><img width="24px" src="/images/folder-edit.png"></a>
			<a href="javascript:delCategory('${dto.current.id }','${dto.current.name }','${dto.current.typeid }')" title="删除" style="margin-left:8px;"><img width="24px" src="/images/folder-remove.png"></a>
		</div>
			<ul id="treeDemo" class="ztree" style="overflow-x:auto;margin-top:10px;"></ul>
	</div>
	<div class="tree_content">
	<div class="toolbar pb10">
		<s:if test="dto.contentform.id>0">
			<input type="button" value="新增" class="btn btn-primary" onclick="window.location='content_form_add_index.action?formid=${dto.contentform.id }'"/>
			<input type="button" value="配置" class="btn btn-primary" onclick="window.location='content_form_update_index.action?formid=${dto.contentform.id }'"/>
		</s:if>
	</div>
	<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
			<tr>
				<s:if test="dto.colum==null">
					<th>标题</th>
					<th>内容</th>
				</s:if>
				<s:else>
					<s:iterator value="dto.colum" var="c">
						<th>${c.name }</th>
					</s:iterator>
				</s:else>
				<th width="210">操作</th>
			</tr>
		<s:iterator value="dto.record" var="s">
			<tr align="center" >
				<s:iterator value="list" var="t" status="st">
					<s:if test='dto.colum!=null&&dto.colum[#st.index].stype=="B"'>
						<td><s:if test='#t != null&&#t!="" '><img src="${imgDomain }${t }" height="40" width="40"></s:if><s:else><img src="/images/nopic.png" height="40" width="40"></s:else></td>
					</s:if>
					<s:else>
						<td>${t }</td>
					</s:else>
				</s:iterator>
					<td>
						<a href="/${oname }/content/editRecord.action?formid=${dto.contentform.id}&recordid=${s.id}">编辑</a>
						<a href="javascript:cfrdetail('${dto.contentform.id }','${s.id }')">查看</a>
						<a href="javascript:delcfr('${dto.contentform.id }','${s.id }')">删除</a>
					</td>
			</tr>
		</s:iterator>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
	</div>
</div>
<link rel="stylesheet" type="text/css" href="/diyUpload/css/webuploader.css">
<link rel="stylesheet" type="text/css" href="/diyUpload/css/diyUpload.css">
<script type="text/javascript" src="/diyUpload/js/webuploader.js"></script>
<script type="text/javascript" src="/diyUpload/js/diyUpload.js"></script>
<div id="addHtml" style="display: none">
		<p>新目录名称：<input type="text" id="ccname" class="text-medium"/></p>
		<p>目录描述：<textarea id="ccdesc"></textarea></p>
		<p>目录图片：
			<input type="hidden" id="ccpic"/>
			<div id="as"></div>
			<div id="picker">选择图片</div>
		</p>
		
		<script type="text/javascript">
		
		/*
		* 服务器地址,成功返回,失败返回参数格式依照jquery.ajax习惯;
		* 其他参数同WebUploader
		*/
		
		$('#as').diyUpload({
			url:'/${oname}/user/h5UploadPic.action',
			success:function( data ) {
				$("#ccpic").val(data.picUrl);
				console.info( data );
			},
			error:function( err ) {
				console.info( err );	
			},
			del:function(filename) {
				console.info( filename );	
			},
			auto: true,
			pick: '#picker',
			//chunked:true,
			// 分片大小
			//chunkSize:512 * 1024,
			//最大上传的文件数量, 总文件大小,单个文件大小(单位字节);
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
	</div>
<div id="editHtml" style="display: none">
		<p>新目录名称：<input type="text" id="editname" value="${dto.current.name }"/>
			<input type="hidden" id="editid" value="${dto.current.id }"/>
		</p>
		<p>目录描述：<textarea id="editdesc">${dto.current.desc }</textarea></p>
		<p>
			原图片：<s:if test='dto.current.pic != null&&dto.current.pic!="" '><img src="${imgDomain }${dto.current.pic }" height="40" width="40"></s:if><s:else><img src="/images/nopic.png" height="40" width="40"></s:else>
				<input type="hidden" id="editpic" value="${dto.current.pic }"/>
		</p>
		<p>目录图片：
			<div id="as2"></div>
			<div id="picker2">选择图片</div>
		</p>
		
		<script type="text/javascript">
		
		/*
		* 服务器地址,成功返回,失败返回参数格式依照jquery.ajax习惯;
		* 其他参数同WebUploader
		*/
		
		$('#as2').diyUpload({
			url:'/${oname}/user/h5UploadPic.action',
			success:function( data ) {
				$("#editpic").val(data.picUrl);
				console.info( data );
			},
			error:function( err ) {
				console.info( err );	
			},
			del:function(filename) {
				console.info( filename );	
			},
			auto: true,
			pick: '#picker2',
			//chunked:true,
			// 分片大小
			//chunkSize:512 * 1024,
			//最大上传的文件数量, 总文件大小,单个文件大小(单位字节);
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
	</div>		