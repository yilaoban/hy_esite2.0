<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
 	function checkbigScreenSite(){
		if($("#site1").val() == 0){
			layer.alert("请选择大屏幕所在的站点",0);
			return false;
		}
		$.post("/interact/updateBigScreenSite.action",{"dpmsiteid":$("#site1").val(),"xcSiteid":$('#xcSiteid').val()},function(data){
			if(data == "N"){
				layer.alert("修改大屏幕站点失败,请重试!",0);
			}else{
				layer.alert("修改大屏幕站点成功",1);
				window.parent.location.reload();
			}
		});
	}
</script>
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
  绑定大屏幕
</div>
<div class="modal-body">
	<form action="/interact/updateBigScreenSite.action" method="post" enctype="multipart/form-data" id="form1">
		<input type="hidden" id="xcSiteid" value="${xcSiteid }"/>
		<dl>
		 	<dt>绑定大屏幕：
				<select id="site1" name="dpmsiteid">
					<option value ="0" >请选择大屏幕所在的站点</option>
					<s:iterator value="dto.siteList" var="f">
					  <option value ="${f.id}" <s:if test='#f.id == siteid'>selected="selected"</s:if>>${f.name}</option>
					</s:iterator>  
				</select>
				<span class="must">*</span></dt>
		</dl>
	</form>
</div>
<div class="modal-footer">
  <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
  <button type="button" class="btn btn-primary" onclick="checkbigScreenSite()" data-dismiss="modal">确定</button>
</div>
