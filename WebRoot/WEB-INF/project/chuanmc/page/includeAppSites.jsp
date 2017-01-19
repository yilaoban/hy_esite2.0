<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
function showAppSite(){
	/*	var	title="请输入新站点的名称";
		var index = layer.open({
				type : 1,
				title : [title,true],
				content: $('#myModal')
			});*/
		
		layer.prompt({
			title : '创建站点'
			},function(sitename){
				if(sitename==null||sitename==''){
					layer.msg('请输入站点名称！', {icon: 5, time: 2000});
					   return false;
				}
				var a=$("input:radio[name='groupid']:checked");
				var groupid=$(a).attr("gid");
				var copyType=$(a).attr("gtype");
				if(typeof(groupid)=="undefined"||groupid==0){
					layer.msg('没有选择需创建的套装！', {icon: 5, time: 2000});
					return;
				}
				if(typeof(copyType)=="undefined"||copyType==""){
					layer.msg('套装类型不正确！', {icon: 5, time: 2000});
					return;
				}
				var url = "/${oname}/page/copySite.action";
				var index = layer.msg('正在创建,请稍等...', {icon: 16,time:0});
				$.post(url,{"sitename":sitename,"copyType":copyType,"groupid":groupid},function(data){
					layer.close(index);
					if(data == 1){
						layer.msg('创建中！请稍等……', {icon: 6, time: 1500}, function(){
							window.location.reload();
						}); 
					}else if(data==-1){
						layer.msg('创建失败！重复的站点名称……', {icon: 5, time: 2000});
					}else{
						layer.msg('创建失败！请重试……', {icon: 5, time: 2000});
					}
				});
	    });
	}
</script>
<!-- Modal -->
<div class="wrap_content" id="myModal" style="display: none;">
	<s:iterator value="dto.taozhuang" var="t" status="st1">
		     <img width="150px"  alt="" <s:if test='#t.wps.pic != ""'>src="${t.wps.pic }"</s:if><s:else>src="/images/nopic.png"</s:else>  class="template-pic"/>
		     <input <s:if test="#st1.first">checked="checked"</s:if> type="radio" name="groupid" gid="${t.id }" gtype="${t.type }"/>
		      	${t.groupname }
	</s:iterator>
	<p class="clearfix">
	请输入新站点的名称:<input type="text" id="sitename"/>
	</p>
	 <button type="button" class="btn btn-primary" onclick="copyCheckSiteGroup()">保存</button>
</div>