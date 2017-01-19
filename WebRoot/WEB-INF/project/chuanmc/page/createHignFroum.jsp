<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	function saveHignForum(){
		var cateType=$('input:radio[name="cateid"]:checked').val();
		var title = $('#title').val().trim();
		if(title == ""){
			$("#title_").html("请输入版块名称").css("color", "RED");
			return;
		}
		$.post("/${sessionScope.account.owner.entity}/bbs/saveForum.action",{"title":title,"cateType":cateType},function(data){
			if(data == "N"){
				$("#title_").html("版块名称不能重复").css("color", "RED");
			}else{
				window.parent.location.reload();
			}
		});
	}
</script>
	<form action="/${sessionScope.account.owner.entity}/bbs/saveForum.action" method="post" id="myfrom2" class="formview jNice">
		<dl>
		 	<dt>版块名称</dt>
			<dd>
				<input type="text" class="text-medium" name="title" placeholder="请填写版块名称" id="title"><span id="title_" class="must">*</span>
			</dd>
		</dl>
		<dl>
		 	<dt>板块类型</dt>
			<dd>
				<label class="forradio"><input type="radio" name="cateid" value="N" checked="checked" />新闻</label>
				<label class="forradio"><input type="radio" name="cateid" value="T"  />产品</label>
				<label class="forradio"><input type="radio" name="cateid" value="L"  />论坛</label>
			</dd>
		</dl>
		<dl>
		 	<dt></dt>
			<dd>
	 			<button type="button" class="btn btn-primary" onclick="saveHignForum()">保存</button>
				<button type="button" class="btn btn-default" onclick="closeFrame()">关闭</button>
			</dd>
		</dl>
	</form>
	  
