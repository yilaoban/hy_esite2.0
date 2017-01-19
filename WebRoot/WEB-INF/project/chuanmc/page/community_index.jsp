<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	function changeName(id,oldname,cateid){
		var entity = '${entity}';
		var url = "/" + entity + "/bbs/updateForumName.action";
		layer.prompt({
			value : oldname,
			title : '修改名称'
			},function(value){
				if(value==null||value==''){
					   return false;
				}
				$.post(url,{"forum.title":value,"forum.id":id,"cateid":cateid},function(data){
					if(data =="Y"){
						layer.msg('修改中！请稍等……', {icon: 6, time: 1500}, function(){
							window.parent.location.reload();
						}); 
					}else{
						layer.msg('版块名称不能重复……', {icon: 5, time: 2000});
					}
				});	             
	    });
	}
	
	
	function createCategory(){
		var entity = '${entity}';
		var url = "/" + entity + "/bbs/createCategory.action";
		$.post(url,function(data){
			if(data == "Y"){
				window.location.reload();
			}
		});
	}
	
	
	function createHighForum() {
		var entity = '${entity}';
		var srcString = '/' + entity + '/bbs/createHignFroum.action';
		var	title="创建高级版块";
		layer.open({
				type : 2,
				area : ['450px','235px'],
				title : [title,true],
				content: srcString
			});
	}
	
	function baseSetting(id){
		var entity = '${entity}';
		var srcString = '/' + entity + '/bbs/baseSetting.action?forumid=' + id;
		var	title="基础设定";
		layer.open({
				type : 2,
				area : ['450px','300px'],
				title : [title,true],
				content: srcString
			});
	}
	
	function permissionSetting(id){
		var entity = '${entity}';	
		var srcString = '/' + entity + '/bbs/permissionSetting.action?forumid=' + id;
		var	title="权限设置";
		layer.open({
				type : 2,
				area : ['520px','350px'],
				title : [title,true],
				content: srcString
			});
		
	}
	
	function topicManager(id){
		var entity = '${entity}';	
		var srcString = '/' + entity + '/bbs/topicManager.action';
		$.post(srcString,{"forumid":id},function(data){
			if(data == "Y"){
				layer.msg('请先完成基础配置', {icon: 5, time: 2000});
			}else{
				window.location.href =  '/' + entity + '/bbs/topicIndex.action?forumid=' + id + "&forumer=" + data;
			}
		});
	}
	
	function delforum(id){
		var srcString = '/${entity}/bbs/delforum.action';
		var layerid=layer.confirm('确定删除?',{icon: 2},function(){
			$.post(srcString,{"forumid":id},function(data){
				if(data == "Y"){
					layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
						window.parent.location.reload();
					}); 
				}else{
					layer.msg('删除失败！请重试……', {icon: 5, time: 2000});
				}
			});
		});
	}
</script>
<div class="wrap_content">
		<s:if test="dto.bbsCatergoryList != null && dto.bbsCatergoryList.size() > 0">
			<div class="toolbar pb10">
				<a href="javascript:void(0);" onclick="createHighForum()" class="btn btn-primary">创建版块</a> 
			</div>
			
			<!-- 
			<a href="/${sessionScope.account.owner.entity}/bbs/topicIndex.action" <s:if test="type==2">style="color: red"</s:if>>话题管理</a>
			 -->
				
				<!-- 
				<a class="button" href="javascript:void(0);" onclick="createNormalForum()">创建普通板块</a>
				 -->
			<div>
				<table width="100%" cellspacing="1" cellpadding="1" border="1" class="tb_normal">
					<thead>
						<tr>
							<th>
								板块名称
							</th>
							<th>
								类型
							</th>
							<th>
								创建时间
							</th>
							<th>
								话题数
							</th>
							<th>
								操作
							</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="dto.bbsForumList" var="f">
							<tr align="center">
								<td>
									<a href="javascript:void(0)" onclick="changeName(${f.id},'${f.title }',${f.cateid})">${f.title}</a>
								</td>
								<td>
									 ${f.catname }
								</td>
								<td>
									<s:date name="createtime" format="yyyy-MM-dd HH:mm:ss"/>
								</td>
								<td>
										${f.topicnum }
								</td>
								<td>
							      	<a href="javascript:void(0)" onclick="baseSetting(${f.id})">基础设定</a><i class="split">|</i>
							      	<!-- 
							      	<a href="javascript:void(0)">模板选择</a><i class="split">|<i class="split">
							      	 -->
							      	<a href="javascript:void(0)" onclick="permissionSetting(${f.id })">权限设置</a><i class="split">|</i>
							      	<a href="javascript:void(0)" onclick="topicManager(${f.id })">话题管理</a><i class="split">|</i>
							      	<a href="javascript:void(0)" onclick="delforum(${f.id })">删除</a>
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</s:if>
		<s:else>
			<input type="button" value="开通" onclick="createCategory()"/>
		</s:else>
		<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>
