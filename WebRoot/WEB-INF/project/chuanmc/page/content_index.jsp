<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link rel="stylesheet" href="/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="/js/qrcode.js"></script> 
<script type="text/javascript" src="/js/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="/js/ztree/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="/js/ztree/jquery.ztree.exedit-3.5.js"></script>
<SCRIPT type="text/javascript">
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
		
		//内容移动/复制需要
		var moveSetting = {
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
			$("#targetCcid").val(catid);		
		}
		
		
		var zNodes =${dto.tree};

		function getFont(treeId, node) {
			return node.font ? node.font : {};
		}
		
		function onClick(e, treeId, treeNode){
			window.location.replace("/"+'${oname}'+"/content/content_index.action?ccid="+treeNode.id+"&typeid="+${dto.current.typeid})
		}
		
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
			var node = treeObj.getNodeByParam("id", ' ${dto.current.id}', null);
			if(node==null){
				return;
			}
			var treeLevel=node.level;
			$("#treeLevel").val(treeLevel);
			
			$("#selectall").change(function(){
				if($(this).is(':checked')){
					$("input[name='bathPid']").attr("checked",true);
				}else{
					$("input[name='bathPid']").removeAttr("checked");
				}
			});
			
			//复制内容
			$.fn.zTree.init($("#treeDemo1"), moveSetting, zNodes);
			var treeObj2 = $.fn.zTree.getZTreeObj("treeDemo1");
			var node2 = treeObj2.getNodeByParam("id", ' ${dto.current.id}', null);
			treeObj2.checkNode(node2, true, true,true);
			
			$("#tongbu").click(function(){
				 var index = layer.msg('正在同步,请稍等...', {icon: 16,time:0});
				 $.post("/${oname}/content/updateToPage.action",{"ccid":'${dto.current.id}'},function(data){
					 layer.close(index);
						if(data > 0){
							layer.msg(data+'个页面同步完成！', {icon: 6, time: 3000}); 
						}else{
							layer.msg('没有需要同步的页面！', {icon: 5, time: 3000});
						}
				 });
			});
			
			$("#cmmSub").click(function(){
				 var ids="";
				 $("input:checkbox[name='bathPid']:checked").each(function(){
					 ids+=$(this).val()+",";
				 });
				 if(ids.length==0){
					 layer.msg('没有选择需要移动的内容!', {icon: 5, time: 2000});
					 return;
				 }
				 var targetCcid=$("#targetCcid").val();
				 if(targetCcid==0){
					 layer.msg('没有选择需要移动到的目录!', {icon: 5, time: 2000});
					 return;
				 }
				 
				 var copyOrcut=$("input[name='copyOrcut']:checked").val();
				 
				 $('#cmm').modal('hide');
				 var index = layer.msg('正在处理中,请稍等...', {icon: 16,time:0});
				 $.post("/${oname}/page/contentMove.action",{"ids":ids,"targetCcid":targetCcid,"copyOrcut":copyOrcut},function(data){
						layer.close(index);
						if(data == 1){
							layer.msg('处理完成！', {icon: 6, time: 1500}, function(){
								 $("input:checkbox[name='bathPid']:checked").each(function(){
									 $(this).attr("checked",false);
								 });
								window.location.reload();
							}); 
						}else{
							layer.msg('创建失败！请重试！', {icon: 5, time: 2000});
						}
					}); 
				 })
			
			
				 $("#contentMove").click(function(){
					 var ids="";
					 $("input:checkbox[name='bathPid']:checked").each(function(){
						 ids+=$(this).val()+",";
					 });
					 if(ids.length==0){
						 layer.msg('没有选择需要移动的内容!', {icon: 5, time: 2000});
						 return;
					 }
					 $('#cmm').modal('toggle');
				 })
				 
				 $("#batchDel").click(function(){
					 var ids="";
					 $("input:checkbox[name='bathPid']:checked").each(function(){
						 ids+=$(this).val()+",";
					 });
					 if(ids.length==0){
						 layer.msg('没有选择需要删除的内容!', {icon: 5, time: 2000});
						 return;
					 }
					 var layerid=layer.confirm('确定删除选中的内容吗?', {icon: 3, title:'批量删除'},function(){
							$.post("/${oname}/page/contentBatchDel.action",{"ids":ids,"targetCcid":'${dto.current.id}'},function(data){
								if(data == 1){
									layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
										window.parent.location.reload();
									}); 
								}else{
									layer.msg('删除失败！请重试……', {icon: 5, time: 2000});
								}
							});
					});
				 })
				 
				 
		});
		
		function bbspost(id,name,type,namespace){
			$.post("/"+namespace+"/bbs/bbsTopicSub.action",{"entityId":id,"entityName":name,"entityType":type},function(data){
				if(data==1){
					layer.msg('开通成功~', {icon: 6, time: 1500},function(data){
						window.location.reload();
					}); 
				}else if(data==0){
					layer.msg('开通失败!',2,0);
				}else if(data==-1){
					layer.msg('此产品开通评论!',2,0);
				}else if(data==2){
					var srcString = "/"+namespace+"/bbs/bbsProduct.action?entityId="+id+"&entityName="+name+"&entityType="+type;
					var title="选择板块";
					layer.open({
							type : 2,
							area : ['400px','450px'],
							title : [title,true],
							content: srcString
					});
				}else if(data==-2){
					layer.confirm('点击[确定]将在[社区管理]创建相应的版块及话题!',{title:"开通社区提示"},function(){
						$.post("/"+'${oname}'+"/bbs/bbsTopicSub.action",{"entityId":id,"entityType":type,"fromTips":"Y","entityName":name},function(data){
							if(data==1){
				        		layer.msg('开通成功!',{icon: 6, time: 1000}, function(){
				        			window.top.location.reload();
				        		});
							}else if(data==-1){
								layer.msg('此产品已有开通社区!',{icon: 5, time: 1000}, function(){
				        			window.top.location.reload();
				        		});
							}else{
								layer.msg('开通社区失败!',{icon: 5, time: 1000}, function(){
				        			window.top.location.reload();
				        		});
							}			
						})
					});
				}
			});
		}
		
		function news2top(id,top){
			layer.confirm('确定置顶/取消置顶吗?', {icon: 3, title:'新闻置顶'},function(){
				$.post("/${oname}/page/contentTotop.action",{"contentid":id,"topType":top},function(data){
					if(data == 1){
						layer.msg('置顶中！请稍等……', {icon: 6, time: 1500}, function(){
							window.parent.location.reload();
						}); 
					}else{
						layer.msg('置顶失败！请重试……', {icon: 5, time: 2000});
					}
				});
			});
		}
		
		function bathpost(){
			 var bathPid="";
			 $("input:checkbox[name='bathPid']:checked").each(function(){
			 	bathPid+=$(this).val()+",";
			 });
			 var ctype='${dto.current.type}';
			 if(bathPid.length==0){
				 layer.msg('没有选择开通评论的内容!', {icon: 5, time: 2000});
				 return;
			 }
			 $.post("/${sessionScope.account.owner.entity}/bbs/bathSubTopic.action",{"bathPid":bathPid,"entityType":ctype},function(data){
			 		if(data=="Y"){
						layer.msg('开通评论中！请稍等……', {icon: 6, time: 1500}, function(){
							window.location.reload();
						}); 
					}else if(data=="N"){
						var srcString = "/"+'${oname}'+"/bbs/bbsProduct.action?bathPid="+bathPid+"&entityType="+ctype;
						layer.open({
								type : 2,
								area : ['400px','450px'],
								title : '选择板块',
								content : srcString
						});
					}else if(data=="T"){
						layer.confirm('点击[确定]将在[社区管理]创建相应的版块及话题!',{title:"开通社区提示"},function(){
							$.post("/"+'${oname}'+"/bbs/bathSubTopic.action",{"bathPid":bathPid,"entityType":ctype,"fromTips":"Y"},function(data){
								if(data=="Y"){
					        		layer.msg('开通社区成功!',{icon: 6}, function(){
					        			window.top.location.reload();
					        		});
								}else{
									layer.msg('开通社区异常!',{icon: 5}, function(){
					        			window.top.location.reload();
					        		});
								}			
							})
						});
					}else{
						layer.msg(data, {icon: 5, time: 2000});
					}
			 });
		};
		
		
		function chakan(pageid,contentid){
			layer.open({
				type : 2,
				area : ['680px','400px'],
				title : ["查看链接",true],
				content: "contentlink.action?pageid="+pageid+"&contentId="+contentid
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
<div>
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
		<form action="" style="float:left;">
			<input type="text" name="entityName" value="${entityName }"/>
			<input type="hidden" name="ccid" value="${dto.current.id }"/>
			<input type="submit" value="搜索"/>
		</form>
		<s:if test="dto.current.id>4">
			<s:if test='dto.current.type=="T"'><input type="button" value="新增" class="btn btn-primary" onclick="window.location='add_product_pre.action?ccid=${dto.current.id }'"/></s:if>
			<s:elseif test='dto.current.type=="P"'><input type="button" value="新增" class="btn btn-primary" onclick="window.location='add_picture_pre.action?ccid=${dto.current.id }'"/></s:elseif>
			<s:elseif test='dto.current.type=="V"'><input type="button" value="新增" class="btn btn-primary" onclick="window.location='add_video_pre.action?ccid=${dto.current.id }'"/></s:elseif>
			<s:elseif test='dto.current.type=="N"'><input type="button" value="新增" class="btn btn-primary" onclick="window.location='add_new_pre.action?ccid=${dto.current.id }'"/>	<input type="button" value="面包屑设置" class="btn btn-primary" onclick="breadset(${ccid})"/></s:elseif>
			<s:if test="dto.product.size>0">
						<input type="button" value="开通评论" class="btn btn-primary" onclick="bathpost()"/>
			</s:if>
			<s:if test="dto.newList.size>0">
						<input type="button" value="开通评论" class="btn btn-primary" onclick="bathpost()"/>
			</s:if>
		</s:if>
		<s:elseif test="dto.current.id==0">
			<input type="button" value="新增目录" onclick="addcategory(0,'${dto.current.type }','${dto.current.typeid }')"/>
		</s:elseif>
		<div class="btn-group">
			  <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" >批量操作<span class="caret"></span></button>
			  	<ul class="dropdown-menu">
			    	<li><a href="javascript:void(0);" id="contentMove">复制/移动</a></li>
			    	<li><a href="javascript:void(0)" id="batchDel">批量删除</a></li>
				</ul>
		</div>
	</div>
	<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
		<tr>
			<th width="5%"><input type="checkbox" id="selectall" title="全选"/></th>
			<th width="20%">标题</th>
			<s:if test='dto.current.type=="T" || dto.current.type=="P"'><th width="20%">缩略图</th></s:if>
			<th width="15%">更新时间</th>
			<th width="40%">操作</th>
		</tr>
		<s:if test='dto.current.type=="T"'>
		<!-- 产品 -->
			<s:iterator value="dto.product" var="t" status="st">
					<tr>
						<td><input type="checkbox" name="bathPid" value="${t.id }"/></td>
						<td align="left"><s:if test='#t.name.length()<13'>${t.name }</s:if><s:else><span title="${t.name }">${fn:substring(t.name, 0, 13)}...</span></s:else></td>
						<td><s:if test='#t.simgurl != null&&#t.simgurl!="" '><a class="thumb" href="${imgDomain }${t.simgurl }" target="_blank"><img src="${imgDomain }${t.simgurl }" height="40" width="40"></a></s:if><s:else><img src="/images/nopic.png" height="40" width="40"></s:else></td>
						<td><s:date name="updatetime" format="yyyy-MM-dd"/></td>
						<td>
							<input id="idx${st.count }" value="${t.idx }" type="hidden"/>
							<a href="edit_product_pre.action?contentId=${t.id }">编辑</a><i class="split">|</i>
							<s:if test="#st.count>1"><a href="javascript:void(0);" onclick="up_T('${t.id }','${t.catid }','${dto.current.type }','${st.count }')">上移</a><i class="split">|</i></s:if>
							<s:if test="#st.count < dto.product.size"><a href="javascript:void(0);" onclick="down_T('${t.id }','${t.catid }','${dto.current.type }','${st.count }')">下移</a><i class="split">|</i></s:if>
							<s:if test="dto.topicList.contains(#t.id)">
								已开通
							</s:if>
							<s:else>
								<a href="javascript:bbspost(${t.id },'${t.name }','${dto.current.type }','${sessionScope.account.owner.entity}')">开通评论</a>
							</s:else>
							<i class="split">|</i>
							<a href="/${oname }/product/tabIndex.action?contentId=${t.id}">拓展</a><i class="split">|</i>
							<a href="javascript:delProduct(${t.id },'${t.name }')">删除</a>
						</td>
					</tr>	
			</s:iterator>
		</s:if>
		<s:elseif test='dto.current.type=="P"'>
		<!-- 图片 -->
			<s:iterator value="dto.pictureList" var="t" status="st">
					<tr>
						<td><input type="checkbox" name="bathPid" value="${t.id }"/></td>
						<td align="left"><s:if test='#t.title.length()<13'>${t.title }</s:if><s:else><span title="${t.title }">${fn:substring(t.title, 0, 13)}...</span></s:else></td>
						<td><s:if test='#t.imgurl != null&&#t.imgurl!=""'><a class="thumb" href="${imgDomain }${t.imgurl }" target="_blank"><img src="${imgDomain }${t.imgurl }" height="40" width="40"></a></s:if><s:else><img src="/images/nopic.png" height="40" width="40"></s:else></td>
						<td><s:date name="updatetime" format="yyyy-MM-dd"/></td>
						<td>
							<input id="idx${st.count }" value="${t.idx }" type="hidden"/>
							<a href="edit_picture_pre.action?contentId=${t.id }">编辑</a><i class="split">|</i>
							<s:if test="#st.count>1"><a href="javascript:void(0);" onclick="up_T('${t.id }','${t.catid }','${dto.current.type }','${st.count }')">上移</a><i class="split">|</i></s:if>
							<s:if test="#st.count < dto.pictureList.size"><a href="javascript:void(0);" onclick="down_T('${t.id }','${t.catid }','${dto.current.type }','${st.count }')">下移</a><i class="split">|</i></s:if>
							<a href="javascript:deletePic(${t.id },'${t.title }')">删除</a>
						</td>
					</tr>	
			</s:iterator>
		</s:elseif>
		<s:elseif test='dto.current.type=="N"'>
		<!-- 新闻 -->
			<s:iterator value="dto.newList" var="t" status="st">
					<tr>
						<td><input type="checkbox" name="bathPid" value="${t.id }"/></td>
						<td align="left"><s:if test='#t.title.length()<13'>${t.title }</s:if><s:else><span title="${t.title }">${fn:substring(t.title, 0, 13)}...</span></s:else></td>
						<td><s:date name="updatetime" format="yyyy-MM-dd"/></td>
						<td>
							<input id="idx${st.count }" value="${t.idx }" type="hidden"  ntop="${t.top }"/>
							<a href="edit_new_pre.action?contentId=${t.id }">编辑</a><i class="split">|</i>
							<s:if test="#st.count>1"><a href="javascript:void(0);" onclick="up_T('${t.id }','${t.catid }','${dto.current.type }', '${st.count }')">上移</a><i class="split">|</i></s:if>
							<s:if test="#st.count < dto.newList.size"><a href="javascript:void(0);" onclick="down_T('${t.id }','${t.catid }','${dto.current.type }','${st.count }')">下移</a><i class="split">|</i></s:if>
							<s:if test="dto.topicList.contains(#t.id)">
								已开通
							</s:if>
							<s:else>
								<a href="javascript:bbspost(${t.id },'${t.title }','${dto.current.type }','${sessionScope.account.owner.entity}')">开通评论</a>
							</s:else>
							<i class="split">|</i>
							<s:if test="#t.top==0">
								<a href="javascript:void(0)" onclick="news2top('${t.id}',1)">置顶<a>
							</s:if>
							<s:else>
								<a href="javascript:void(0)" onclick="news2top('${t.id}',0)">取消置顶</a>
							</s:else>
							<s:if test="dto.current.pageid > 0">
								<i class="split">|</i><a onclick="chakan(${dto.current.pageid},${t.id })">查看</a>
							</s:if>
							<i class="split">|</i>
							<a href="javascript:deleteNew(${t.id },'${t.title }')">删除</span></a>
						</td>
					</tr>	
			</s:iterator>
		</s:elseif>
		<s:elseif test='dto.current.type=="V"'>
		<!-- 视频 -->
			<s:iterator value="dto.videoList" var="t" status="st">
					<tr>
						<td><input type="checkbox" name="bathPid" value="${t.id }"/></td>
						<td align="left"><s:if test='#t.title.length()<13'>${t.title }</s:if><s:else><span title="${t.title }">${fn:substring(t.title, 0, 13)}...</span></s:else></td>
						<td><s:date name="updatetime" format="yyyy-MM-dd"/></td>
						<td>
							<input id="idx${st.count }" value="${t.idx }" type="hidden"/>
							<a href="edit_video_pre.action?contentId=${t.id }">编辑</a><i class="split">|</i>
							<s:if test="#st.count>1"><a href="javascript:void(0);" onclick="up_T('${t.id }','${t.catid }','${dto.current.type }','${st.count }')">上移</a><i class="split">|</i></s:if>
							<s:if test="#st.count< dto.videoList.size"><a href="javascript:void(0);" onclick="down_T('${t.id }','${t.catid }','${dto.current.type }','${st.count }')">下移</a><i class="split">|</i></s:if>
							<a href="javascript:deleteVideo(${t.id },'${t.title }')">删除</a>
						</td>
					</tr>	
			</s:iterator>
		</s:elseif>
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
			原图片：
				<s:if test='dto.current.pic != null&&dto.current.pic!="" '><img src="${imgDomain }${dto.current.pic }" height="40" width="40"></s:if><s:else><img src="/images/nopic.png" height="40" width="40"></s:else>
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
<!-- content_move_modal -->
<div class="modal fade" id="cmm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">内容复制/移动</h4>
      </div>
      <div class="modal-body">
      	选择目录
        <ul id="treeDemo1" class="ztree" style="overflow-x:auto;margin-top:10px;"></ul>
                        操作  <input type="radio" value="0" checked="checked" name="copyOrcut"/>复制 <input type="radio" value="1" name="copyOrcut"/>移动<input type="radio" value="2" name="copyOrcut" checked="checked"/>复制(同步修改)
		<input type="hidden" id="targetCcid"/>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="cmmSub">确定</button>
      </div>
    </div>
  </div>
</div>
	<div class="clearfix"></div>
</div>