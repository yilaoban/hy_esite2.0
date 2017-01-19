<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
 	function checkSite(){
		if($("#title").val()==""){
			layer.alert("请输入微现场名称",0);
			return false;
		}if($("#site1").val() == 0){
			layer.alert("请选择大屏幕所在的站点",0);
			return false;
		}if($("#site2").val() == 0){
			layer.alert("请选择微现场所在的站点",0);
			return false;
		}
		$.post("/interact/xcLotterySave.action",{"xc.title":$("#title").val(),"dpmsiteid":$("#site1").val(),"xcsiteid":$("#site2").val()},function(data){
			if(data == "N"){
				layer.alert("新增微现场失败,请重试!",0);
			}else{
				layer.alert("新增微现场成功",1);
				window.parent.location.reload();
			}
		});
	}
</script>
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
  新增微现场
</div>
<div class="modal-body">
	<form action="/interact/xcLotterySave.action" method="post" enctype="multipart/form-data" id="form1">
		<div >
		<dl>
		 	<dt>微现场名称: <input class="text-medium" id="title" name="xc.title"/><span class="must">*</span></dt>
		</dl>
		<dl>
		 	<dt>绑定大屏幕：
				<select id="site1" name="dpmsiteid">
					<option value ="0">请选择大屏幕所在的站点</option>
					<s:iterator value="dto.siteList" var="f">
					  <option value ="${f.id}">${f.name}</option>
					</s:iterator>  
				</select>
				<span class="must">*</span></dt>
		</dl>
		<dl>
		 	<dt>绑定微现场：
				<select id="site2" name="xcsiteid">
					<option value ="0">请选择微现场所在的站点</option>
					<s:iterator value="dto.siteList" var="f">
					  <option value ="${f.id}">${f.name}</option>
					</s:iterator>  
				</select>
				<span class="must">*</span></dt>
		</dl>
		</div>
	</form>
</div>
<div class="modal-footer">
  <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
  <button type="button" class="btn btn-primary" onclick="checkSite()" data-dismiss="modal">确定</button>
</div>
