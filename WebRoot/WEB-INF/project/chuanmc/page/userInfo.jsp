<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	function addUserBalck(id,nickname){
		var entity = '${entity}';
		var url = '/'+ entity + '/bbs/addUserBalck.action';
		var layerid=layer.confirm('确定将['+nickname+']加入黑名单吗?',function(){
					$.post(url,{"uid":id,"isbalck":"Y"},function(data){
						if(data == "Y"){
							layer.msg('正在加入黑名单！请稍等……', {icon: 6, time: 1500}, function(){
								window.location.reload();
							}); 
						}else{
							layer.msg("加入黑名单失败!请重试……", {icon: 5, time: 2000});
						}
					});
				});
	}
	
	function removeUserBalck(id,nickname){
		var entity = '${entity}';
		var url = '/'+ entity + '/bbs/addUserBalck.action';
		var layerid=layer.confirm('确定将['+nickname+']移除黑名单吗?',function(){
					$.post(url,{"uid":id,"isbalck":"N"},function(data){
						if(data == "Y"){
							layer.msg('正在移出黑名单！请稍等……', {icon: 6, time: 1500}, function(){
								window.location.reload();
							}); 
						}else{
							layer.msg("移除黑名单失败!请重试……", {icon: 5, time: 2000});
						}
					});
				});
	}
	
	
	function addUserPage() {
		var srcString = '/${oname}/bbs/addUserPage.action';
		var	title="添加会员";
		layer.open({
				type : 2,
				area : ['450px','380px'],
				title : [title,true],
				content: srcString
			});
		
	}
</script>
<div class="wrap_content">
		<div class="toolbar pb10">
		<form action="/${sessionScope.account.owner.entity}/bbs/userSearch.action" method="post" class="jNice left">
			<input type="hidden" name="type" value="${type }">
			会员昵称：<input type="text" name="nickname" value="${nickname }" class="text-medium"> 
			<!-- 
			 会员等级:
			<select name="levelid">
				<option value="0">选择会员等级</option>
				<s:iterator value="dto.bbsJfLevelList" var="l">
				  <option value ="${l.id }" <s:if test="#l.id == levelid ">selected="selected"</s:if> >${l.level_name}</option>
				</s:iterator>  
			</select>
			 -->
			 <input type="submit" value="搜索" class="btn btn-info btn-sm"> 
		</form>
		<ul class="c_switch">
		<!-- 
			<li <s:if test="type==0">class="selected"</s:if>><a href="/${sessionScope.account.owner.entity}/bbs/user_index.action" >积分规则设置</a></li>
			<li <s:if test="type==1">class="selected"</s:if>><a href="/${sessionScope.account.owner.entity}/bbs/userLevel.action?type=1">会员等级分配</a></li>
			<li <s:if test="type==2">class="selected"</s:if>><a href="/${sessionScope.account.owner.entity}/bbs/userInfo.action?type=2">会员查看</a></li>
		 -->
		</ul>
		<a class="btn btn-primary" href="javascript:void(0);" onclick="addUserPage()">添加会员</a>
		<a class="btn btn-primary" href="/${oname}/bbs/userBalck.action?type=${type }">黑名单</a>
	</div>
	<table width="100%" cellspacing="1" cellpadding="1" border="1"
		class="tb_normal">
		<thead>
			<tr>
				<th>
					会员昵称
				</th>
				<!-- 
				<th>
					会员等级
				</th>
				 -->
				<th>
					积分
				</th>
				<th>
					话题数
				</th>
				<th>
					回复数
				</th>
				<th>
					置顶主题数
				</th>
				<th>
					顶
				</th>
				<th>
					踩
				</th>
				<th>
					注册时间
				</th>
				<th>
					操作
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="dto.bbsUserList" var="u">
				<tr align="center">
					<td>
						${u.nickname }
					</td>
					<!-- 
					<td>
						${u.level_name }
					</td>
					 -->
					<td>
						${u.jf }
					</td>
					<td>
						${u.topicnum }
					</td>
					<td>
						${u.replynum }
					</td>
					<td>
						${u.toptotal }
					</td>
					<td>
						${u.uptotal }
					</td>
					<td>
						${u.downtotal }
					</td>
					<td>
						<s:date name="createtime" format="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
						<s:if test='#u.isbalck == "Y"'>黑名单
							<a href="javascript:void(0);" onclick="removeUserBalck(${u.id },'${u.nickname}')">移除黑名单</a>
						</s:if>
						<s:else><a href="javascript:void(0);" onclick="addUserBalck(${u.id },'${u.nickname}')">加入黑名单</a></s:else>
						
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>
