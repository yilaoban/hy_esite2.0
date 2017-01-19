<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	function confirmUserLevel(){
		var levelid = $('#levelid').val();
		if(levelid > 0){
			$.post("/${oname}/setting/confirmUserLevel.action",{"hyuid":'${hyuid }',"levelid":levelid},function(data){
				if(data > 0){
					window.parent.location.reload();
				}
			});
		}else{
			$('#levelid_').html("请选择会员等级").css("color", "RED");;
		}
	}
</script>


	<form action="/${oname}/setting/confirmUserLevel.action" method="post" id="myfrom2" class="formview jNice">
		<dl>
		 	<dt>会员等级</dt>
			<dd>
				<select name="levelid" id="levelid">
					<s:iterator value="levelList" var="l">
					  <option value ="${l.id}">${l.name}</option>
					</s:iterator>  
				</select>
			</dd>
			<dt></dt>
			<dd>
				<span id="levelid_"></span>
			</dd>
		</dl>
		<dl>
		 	<dt></dt>
			<dd>
	 			<button type="button" class="btn btn-primary" onclick="confirmUserLevel()">保存</button>
				<button type="button" class="btn btn-default" onclick="closeFrame()">关闭</button>
			</dd>
		</dl>
	</form>
	  
