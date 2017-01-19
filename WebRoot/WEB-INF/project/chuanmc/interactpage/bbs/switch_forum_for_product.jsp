<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	function bbspost2(){
			$.ajax({
		        cache: true,
		        type: "POST",
		        url:"/${sessionScope.account.owner.entity}/bbs/bbsTopicSub2.action",
		        data:$('#form1').serialize(),// 你的formid
		        async: false,
		        error: function(request) {
		            layer.alert("Connection error",0);
		        },
		        success: function(data) {
		            if(data==1){
		            	layer.msg('开通成功!',{icon: 6, time: 1000}, function(){
		        			window.top.location.reload();
		        		});
					}else if(data==0){
						layer.msg('开通社区失败!',{icon: 5, time: 1000}, function(){
		        			window.top.location.reload();
		        		});
					}else if(data==-1){
						layer.msg('此产品已有开通社区!',{icon: 5, time: 1000}, function(){
		        			window.top.location.reload();
		        		});
					}
		        }
		    });
}
</script>
<div class="wrap_content">
	<form action="" id="form1">
		<input type="hidden" name="entityId" value="${entityId }"/>
		<input type="hidden" name="entityName" value="${entityName }"/>
		<input type="hidden" name="entityType" value="${entityType }"/>
		<p>当前有:<s:property value="list.size"/>个符合条件的板块.</p>
		<p>
			选择板块:
			<select name="forumid">
				<s:iterator value="list" var="l">
					<option value="${l.id }">${l.title }</option>
				</s:iterator>
			</select>
		</p>
		<p>
			<input type="button" value="确定" onclick="bbspost2()"/>
			<input type="button" value="取消" onclick="closeFrame()"/>
		</p>
	</form>
</div>