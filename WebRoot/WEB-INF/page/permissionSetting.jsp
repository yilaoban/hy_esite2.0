<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	function saveForum(){
		$('#ovisittype_').html("");$('#visittype_').html("");
		var visittype=$('input:radio[name="forum.visittype"]:checked').val();
		var ovisittype=$('input:radio[name="forum.ovisittype"]:checked').val();
		if(visittype > 0 || ovisittype > 0){
			$.ajax({
		        cache: true,
		        type: "POST",
		        url:'/${oname}/bbs/savePermissionSetting.action',
		        data:$('#myfrom').serialize(),// 你的formid
		        async: false,
		        error: function(request) {
		            layer.alert("Connection error",0);
		        },
		        success: function(data) {
		            if(data > 0){
		            	layer.msg('保存成功！请稍等……', {icon: 6, time: 1500}, function(){
							window.parent.location.reload();
						}); 
		            }else{
		            	layer.alert("操作失败!",0);
		            }
		        }
		    });
			
			
		}else{
			$('#visittype_').html("请至少选择微信环境或其他环境中一项").css("color", "RED");
		}
	}
	
	function showUrl(){
		var visittype=$('input:radio[name="forum.visittype"]:checked').val();
		var ovisittype=$('input:radio[name="forum.ovisittype"]:checked').val();
		if(visittype == 3 || ovisittype == 1){
			$('#url').show();
		}else{
			$('#url').hide();
		}
	}
	
</script>
	<form action="/${sessionScope.account.owner.entity}/bbs/savePermissionSetting.action" method="post" id="myfrom" class="formview jNice">
		<p>	<input type="hidden" name="forum.id" value="${forumid }" id="perSetting">
		<span id="visittype_" ></span>	
		</p>
		
		<dl>
		 	<dt>
		 		微信环境
		 	</dt>
			<dd>
				<label class="forradio"><input type="radio" name="forum.visittype" value="1"  <s:if test="dto.bbsForum.visittype == 1">checked="checked"</s:if> onclick="showUrl()">授权用户</label>	
				<label class="forradio">	<input type="radio" name="forum.visittype" value="2" onclick="showUrl()" <s:if test="dto.bbsForum.visittype == 2">checked="checked"</s:if>>粉丝</label>	
				<label class="forradio">	<input type="radio" name="forum.visittype" value="3" onclick="showUrl()" <s:if test="dto.bbsForum.visittype == 3">checked="checked"</s:if>>用户信息(账号密码)</label>
			</dd>
		</dl>
		
		<dl>
		 	<dt>
		 		其他环境
		 	</dt>
			<dd>
				<label class="forradio">	<input type="radio" name="forum.ovisittype" value="1" onclick="showUrl()" <s:if test="dto.bbsForum.ovisittype == 1">checked="checked"</s:if>>用户信息(账号密码)</label>	
				<label class="forradio">	<input type="radio" name="forum.ovisittype" value="2" onclick="showUrl()" <s:if test="dto.bbsForum.ovisittype == 2">checked="checked"</s:if>>匿名</label>		
				<span id="ovisittype_" ></span>
			</dd>
		</dl>
		<div <s:if test="dto.bbsForum.visittype == 3 || dto.bbsForum.ovisittype == 1">style="display: block"</s:if><s:else>style="display: none;"</s:else> id="url">
		<dl>
		 	<dt>
		 		登陆地址
		 	</dt>
			<dd>
				<select name="forum.loginpageid">
					<option value="0">请选择跳转页面</option>
					<s:iterator value="dto.pageList" var="p">
					  <option value ="${p.id }" <s:if test="dto.bbsForum.loginpageid == #p.id">selected="selected"</s:if>>${p.name}(${p.sitename })</option>
					</s:iterator>  
				</select>
			</dd>
		</dl>
		<dl>
		 	<dt>
		 		注册地址
		 	</dt>
			<dd>
				<select name="forum.registerpageid">
				<option value="0">请选择跳转页面</option>
				<s:iterator value="dto.pageList" var="p">
				  <option value ="${p.id }" <s:if test="dto.bbsForum.registerpageid == #p.id">selected="selected"</s:if>>${p.name}(${p.sitename })</option>
				</s:iterator>  
			</select>
			</dd>
		</dl>
		</div>
		<dl>
		 	<dt>
		 		发帖审核
		 	</dt>
			<dd>
				<label class="forradio"><input type="radio" name="forum.topicCheck" value="Y" checked="checked">开启</label>
				<label class="forradio"><input type="radio" name="forum.topicCheck" value="N" <s:if test='dto.bbsForum.topicCheck == "N"'>checked="checked"</s:if>>关闭 </label> 
			</select>
			</dd>
		</dl>
		<dl>
		 	<dt>
		 		评论审核
		 	</dt>
			<dd>
				<label class="forradio"><input type="radio" name="forum.commentCheck" value="Y" checked="checked">开启</label>
				<label class="forradio"><input type="radio" name="forum.commentCheck" value="N" <s:if test='dto.bbsForum.commentCheck == "N"'>checked="checked"</s:if>>关闭</label>
			</select>
			</dd>
		</dl>
		<dl>
		 	<dt></dt>
			<dd>
  				  <button type="button" class="btn btn-primary" onclick="saveForum()">保存</button>
				  <button type="button" class="btn btn-default" onclick="closeFrame()">关闭</button>
			</dd>
		</dl>
		
	</form>
<div >
  
</div>
