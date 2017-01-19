<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="/js/rbga.js"></script>
<div class="popup-header">
  <button type="button" class="close closePopup"><span>&times;</span></button>
  <a class="btn btn-primary" id="tab_content">内容</a>
  <a class="btn btn-default" id="tab_position">位置</a>
</div>
<form id="form1" name="form1">
<div class="popup-body">
	<s:iterator value="dto.jsonObj" var="j" status="st">
		<div class="edit_content">
			<%--单行 --%>
			<s:if test='#j.value.type=="T" || #j.value.type=="S"'>
				<dl>
				 	<dt>${j.value.mapping}</dt>
					<dd>
						<input type="text" class="text-pop form-control" name="${j.key }" id="var_${j.key }" value="${dto.vjsonObj[j.key] }"/>${j.value.desc }
					</dd>
				</dl>
			</s:if>
			<%--多行编辑器 --%>
			<s:if test='#j.value.type=="A"'>
				<dl>
				 	<dt>${j.value.mapping }</dt>
					<dd>
						<textarea class="kindeditor" name="${j.key }" id="var_${j.key }">${dto.vjsonObj[j.key] }</textarea>${j.value.desc }
					</dd>
				</dl>
			</s:if>
			<%--图片 --%>
			<s:if test='#j.value.type=="I"'>
				<dl>
				 	<dt>${j.value.mapping }</dt>
				 	<dd>
				 		<div class="btn-group">
						  <button type="button" class="btn btn-default"  onClick="upd_${j.key }.click()">原图上传</button>
						  <button type="button" class="btn btn-default" id="pic_upload" name="pic" onclick="img_flash('${j.key }','${j.value.desc }')">截图上传</button>
						  <button type="button" class="btn btn-default" onclick="sckall(2,'${j.key}')">从素材库选择</button>
						</div>
						<input name="${j.key }" type="hidden" id="var_${j.key }" value="${dto.vjsonObj[j.key] }" hydata="${dto.vjsonObj[j.key] }"> 
						<input type="file" name="img" id="upd_${j.key }" onchange="ajaxFileUpload('${imgDomain }','${j.key }')" style="display:none;" >
				 	</dd>
					<dd>
						<img id="img_${j.key}" src="${dto.vjsonObj[j.key] }" style="max-width:100%;max-height:100px;"/>
					</dd>
				</dl>
			</s:if>
			<%--链接 --%>
			<s:if test='#j.value.type=="L"'>
				<dl>
				 	<dt>${j.value.mapping }</dt>
					<dd>
						<input type="text" class="text-pop form-control hylink" name="${j.key }" id="var_${j.key }" value="${dto.vjsonObj[j.key] }" hydata="${dto.vjsonObj[j.key] }"/>${j.value.desc }
					</dd>
					<dd>
						<label><input type="checkbox" id="gl_${j.key}" onclick="usegld('${j.key}','${oname }')">链接到</label>
						<select class="text-pop form-control" id="sel_${j.key}" disabled="disabled" name="gld" onchange="gldym('${j.key}',this,'${oname }')">
							<s:iterator value='dto.pages' var="p">
							<option value="${p.id}">${p.name}</option>
							</s:iterator>
						</select>
					</dd>
				</dl>
			</s:if>
	</div>
		</s:iterator>
	<div class="edit_position" style="display:none">
		<div style="margin-top:15px;">
			<div style="float:left;margin-left:50px;">靠左<input type="text" id="source_left" name="left" placeholder="??px" value="${dto.left }" class="text-fix form-control" style="width:50px;" /></div> 
			<div style="margin-left:180px;">靠上 <input type="text" id="source_top" placeholder="??px" value="${dto.top }" name="top" class="text-fix form-control" style="width:50px;" /></div>
			<div style="float:left;margin-left:50px;">靠右<input type="text" id="source_right" name="right" placeholder="??px" value="${dto.right }" class="text-fix form-control" style="width:50px;" /></div> 
			<div style="margin-left:180px;">靠下<input type="text" id="source_bottom" placeholder="??px" value="${dto.bottom }" name="bottom" class="text-fix form-control" style="width:50px;" /></div>
		</div>
		<div style="margin-left:50px;">注:优先靠左、上</div>
	</div>
</div>
</form> 
   <div class="popup-footer">
  <button type="button" class="btn btn-default closePopup">关闭</button>
  <button type="button" class="btn btn-primary" id="baocun">保存</button>
</div>
<script type="text/javascript">
$(document).ready(function() {
         sedit();
	     $(".closePopup").click(function(){
			$("#rightPopup").animate({width:'hide'});
		});
		$("#baocun").click(function(){
	        $(".hylink").attr("disabled",false);
	        var jsonuserinfo = $('#form1').serializeObject();
	        var json = JSON.stringify(jsonuserinfo);
	        $.post("edit_source_save.action",{"psid":'${psid}',"json":json},function(data){
	        	$("#rightPopup").animate({width:'hide'});
	        	var cardid = $("#cardid").val();
        		var desc = $('#'+cardid).attr("desc");
        		reloadCard(cardid,desc);
	        })
		});
		
		$("#tab_content").click(function(){
	     	$(".popup-header .btn-primary").removeClass("btn-primary").addClass("btn-default");
	     	$(this).removeClass("btn-default").addClass("btn-primary");
	     	$(".edit_content").show();
	     	$(".edit_position").hide();
	     });
	     $("#tab_position").click(function(){
	     	$(".popup-header .btn-primary").removeClass("btn-primary").addClass("btn-default");
	     	$(this).removeClass("btn-default").addClass("btn-primary");
	    	$(".edit_content").hide();
	     	$(".edit_position").show();
	     });
});

function sedit(){
	var s = $(".kindeditor");
   	for(var i=0;i<s.length;i++){
   		edit($(s[i]).attr("id"));
   	};
} 

function setwz(left,top){
	$("#source_left").val(left+"px");
	$("#source_top").val(top+"px");
}
</script>
