<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="popup-header">
  <button type="button" class="close closePopup"><span>&times;</span></button>
  <a class="btn btn-primary" id="tab_content">内容</a>
  <a class="btn btn-default" id="tab_position">位置</a>
</div>
<div class="popup-body">
<form id="form1" name="form1"method="post">
	<div class="clearfix">
	<ul id="json_list">
		<s:iterator value="dto.vjsonArray" var ="p" status="st">
		<li class="celement" id="list_${st.count}">
			<input type="hidden" id="json_${st.count }" class="hy_yuansu" name="list" value='${p}'/>
			<a id="xs_${st.count}" href="javascript:void(0)" onclick="showJson(${st.count},'${oname }')">元素${st.count}</a>
			<a id="sc_${st.count}" href="javascript:void(0)" onclick="del_json(${st.count },'${oname }')" class="closeTab">删除</a>
		</li>
		</s:iterator>
	</ul>
		<a href="javascript:void(0);" onclick="add_list('${oname}')" class="addTab">增加</a>
	</div>
</form>
<div class="list_div blockview mt20" id="newDiv">
<form id="newform">
	<s:iterator value="dto.jsonObj" var="j">
	<div class="edit_content">
		<s:if test='#j.value.type=="T" || #j.value.type=="S"'>
			<dl>
			 	<dt>${j.value.mapping}</dt>
				<dd>
					<input type="text" class="text-pop form-control" name="${j.key }" id="var_${j.key }" />${j.value.desc }
				</dd>
			</dl>
		</s:if>
		<s:if test='#j.value.type=="A"'>
			<dl>
			 	<dt>${j.value.mapping }</dt>
				<dd>
					<textarea class="kindeditor" name="${j.key }" id="var_${j.key }"></textarea>${j.value.desc }
				</dd>
			</dl>
		</s:if>
		<s:if test='#j.value.type=="I"'>
				<dl>
				 	<dt>${j.value.mapping }</dt>
				 	<dd>
				 		<div class="btn-group">
						  <button type="button" class="btn btn-default"  onClick="upd_${j.key }.click()">原图上传</button>
						  <button type="button" class="btn btn-default" id="pic_upload" name="pic" onclick="img_flash('${j.key }','${j.value.desc }')">截图上传</button>
						  <button type="button" class="btn btn-default" onclick="sckall(2,'${j.key}')">从素材库选择</button>
						</div>
						${j.value.desc }
						<input name="${j.key }" type="hidden" id="var_${j.key }">
						<input type="file" name="img" id="upd_${j.key }" onchange="ajaxFileUpload('${imgDomain }','${j.key }')" style="display:none;" >
				 	</dd>
					<dd>
						<img id="img_${j.key}" src="" style="max-width:100%;max-height:100px;"/>
					</dd>
				</dl>
		</s:if>
		<s:if test='#j.value.type=="L"'>
			<dl>
			 	<dt>${j.value.mapping }</dt>
				<dd>
					<input type="text" class="text-pop form-control hylink" name="${j.key }" id="var_${j.key }"/>
				</dd>
				<dd>
					<label><input type="checkbox" id="gl_${j.key}" onclick="usegld('${j.key}','${oname }')">链接到</label>
					<select class="text-pop form-control" id="sel_${j.key}" disabled="disabled" name="gld" onchange="gldym('${j.key}',this,'${oname}')">
						<s:iterator value='dto.pages' var="p">
						<option value="${p.id }">${p.name}</option>
						</s:iterator>
					</select>
				</dd>
			</dl>
		</s:if>
		<s:if test='#j.value.type=="V"'>
			<dl>
			 	<dt>${j.value.mapping }</dt>
				<dd>
					<input type="text" class="text-pop form-control" name="${j.key }" id="var_${j.key }"/>
				</dd>
			</dl>
		</s:if>
		</div>
		</s:iterator>
</form>
</div>
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
    <div class="popup-footer">
	<input type="hidden" id="show_index">
  <button type="button" class="btn btn-default closePopup">关闭</button>
  <button type="button" class="btn btn-primary" id="baocun">保存</button>
</div>

<script type="text/javascript">
$(document).ready(function() { 
			showJson(1,'${oname}');
         	 $("#tab_content").click(function(){
	     	$(".popup-header .btn-primary").removeClass("btn-primary").addClass("btn-default");
	     	$(this).removeClass("btn-default").addClass("btn-primary");
	     	$(".edit_content").show();
	     	$(".edit_position").hide();
	     	$("#form1").show();
	     });
	     $("#tab_position").click(function(){
	     	$(".popup-header .btn-primary").removeClass("btn-primary").addClass("btn-default");
	     	$(this).removeClass("btn-default").addClass("btn-primary");
	    	$(".edit_content").hide();
	     	$(".edit_position").show();
	     	$("#form1").hide();
	     });
     	     $(".closePopup").click(function(){
				$("#rightPopup").animate({width:'hide'});
			});
     	     
     	     $("#baocun").click(function(){
	     	   	scjson();
	     	   	var jsonuserinfo = $('#form1').serializeObject();
	     	   	var json = JSON.stringify(jsonuserinfo);
		     	   $.post("edit_source_save_c.action",{"psid":'${psid}',"json":json,"top":$("#source_top").val(),"left":$("#source_left").val(),"right":$("#source_right").val(),"bottom":$("#source_bottom").val()},function(data){
		     		  	bootbox.alert("保存成功！您可以继续操作");	
			        	var cardid = $("#cardid").val();
		        		var desc = $('#'+cardid).attr("desc");
		        		reloadCard(cardid,desc);
			        });
	     	     });
        });
        

function setwz(left,top){
	$("#source_left").val(left+"px");
	$("#source_top").val(top+"px");
}
</script>

