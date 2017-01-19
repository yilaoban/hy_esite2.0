<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="/js/rbga.js"></script>
<script type="text/javascript" src="/js/ajaxfileupload.js"></script>
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
  <h4 class="modal-title" id="myModalLabel">页面编辑</h4>
</div>
<div class="modal-body">
	<form id="form1" name="form1" action="/page/blockSub.action" method="post" class="blockview clearfix">
	<s:if test='dto.param.size > 0'>
		<s:iterator value="dto.param" var="j" status="st">
			<s:if test='#j.value.type=="T" || #j.value.type=="S"'>
				<dl>
				 	<dt>${j.value.mapping}</dt>
					<dd>
						<input type="text" class="text-fix form-control" name="${j.key }" id="var_${j.key }" value="${j.value.value }"/>${j.value.desc }
					</dd>
				</dl>
			</s:if>
			<s:elseif test='#j.value.type=="A"'>
				<dl>
				 	<dt>${j.value.mapping }</dt>
					<dd>
						<textarea class="kindeditor" name="${j.key }" id="var_${j.key }"></textarea>${j.value.desc }
					</dd>
				</dl>
			</s:elseif>
			<s:elseif test='#j.value.type=="C"'>
				<dl>
				 	<dt>${j.value.mapping }</dt>
					<dd>
						<input type="radio" name="${j.key }" style="margin-top: 10px" value="N">否
						<input type="radio" name="${j.key }" style="margin-top: 10px" value="Y">是
					</dd>
				</dl>
			</s:elseif>
			<s:elseif test='#j.value.type=="I"'>
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
					<dd style="background:#DBDBDB">
						<img id="img_${j.key}" height="100" src=""/>
					</dd>
				</dl>
			</s:elseif>
			<s:elseif test='#j.value.type=="L"'>
				<dl>
				 	<dt>${j.value.mapping }</dt>
					<dd>
						<input type="text" class="text-fix form-control hylink" name="${j.key }" id="var_${j.key }" />${j.value.desc }
					</dd>
					<dd>
						<label><input type="checkbox" id="gl_${j.key}" onchange="usegld('${j.key}','${oname }')">链接到</label>
						<select class="text-fix form-control" id="sel_${j.key}" disabled="disabled" name="gld" onchange="gld('${j.key}',this)">
							<s:iterator value='dto.pages' var="p">
							<option value="${p.id}">${p.name}</option>
							</s:iterator>
						</select>
					</dd>
				</dl>
			</s:elseif>
			<s:elseif test='#j.value.type=="V"'>
				<dl>
				 	<dt>${j.value.mapping }</dt>
					<dd>
						<input type="text" class="text-fix form-control" name="${j.key }" id="var_${j.key }" />${j.value.desc }
					</dd>
				</dl>
			</s:elseif>
			<!-- bgcolor -->
			<s:elseif test='#j.value.type=="Z"'>
				<dl>
				 	<dt>${j.value.mapping }</dt>
					<dd id="bgClrSlt" class="bgAll" <s:if test='dto.flag=="P"'>style="display:none;"</s:if>>
						<input type="text" class="zpicker" name="${j.key }" id="var_bc${j.key }" />
					</dd>
				</dl>
			</s:elseif>
			<!-- font-color -->
			<s:elseif test='#j.value.type=="F"'>
				<dl>
				 	<dt>${j.value.mapping }</dt>
					<dd id="ftCl" class="bgAll" <s:if test='dto.flag=="P"'>style="display:none;"</s:if>>
						<input type="text" class="ftCl" name="${j.key }" id="var_c${j.key }" />
					</dd>
				</dl>
			</s:elseif>
			<!-- bordercolor -->
			<s:elseif test='#j.value.type=="B"'>   
				<dl>
				 	<dt>${j.value.mapping }</dt>
					<dd id="btCl" class="bgAll" <s:if test='dto.flag=="P"'>style="display:none;"</s:if>>
						<input type="text" class="btCl" name="${j.key }" id="var_c${j.key }" />
					</dd>
				</dl>
			</s:elseif>
			<s:elseif test='#j.value.type=="X"'>
				<dl>
				 	<dt>${j.value.mapping }</dt>
					<dd id="bgClrSlt" class="bgAll">
						<div class="User_ratings User_grade" id="div_fraction0">
							<div class="ratings_bars">
								<input type="text" class="title0 bars_10" style="border: 1px solid #bfbebe;width:35px;font-size: 14px;" name="${j.key }" id="var_${j.key }" value="${j.value.tm }" />
								<span class="bars_10">0</span>
								<div class="scale" id="bar0">
									<div></div>
									<span id="btn0"></span>
								</div>
								<span class="bars_10">1</span>
								<span class="bars_10" style="margin-left:10px;font-size: 12px;color:red;">注:0为最透明</span>
							</div>
						</div>
					</dd>
				</dl>
				<script type="text/javascript">
					new scale('btn0', 'bar0', 'var_${j.key }');
				</script>
			</s:elseif>
		</s:iterator>
	</s:if>
	<s:else>
		此页面不需编辑
	</s:else>
	</form> 
    </div>
    <div class="modal-footer">
  <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
  <s:if test='dto.param.size > 0'><button type="button" class="btn btn-primary" onclick="onClik(${pageid})"  data-dismiss="modal">保存</button></s:if>
</div>

<link rel="stylesheet" href="/js/colpick/colpick.css" type="text/css" />
<script type="text/javascript" src="/js/colpick/colpick.js"></script>
<script type="text/javascript">
$(document).ready(function() {
         sdata(sedit);//数据
	     showCT();//动画
	     
	     $("#normalModal").on("hidden.bs.modal", function() {
		    $(this).removeData("bs.modal");
		});
		$("#normalModal").on("hidden.bs.modal", function() {
			 $(".colpick").remove();
		})
});
        
function sdata(callback) {    
	<s:iterator value="dto.value" var="j">
   		var id = '${j.key}';
   		var v = '${j.value}';
   		$("#var_"+id).val(v).text(v).attr("hydata",v);
   		 $("input[type=radio][name='"+id+"'][value='"+v+"']").attr("checked",true);  
	   	if($("#var_bc"+id).length>0){
	   		var bgcol="RGB("+v+")";
	   		$("#var_bc"+id).val(v).text(v).attr("style","margin-top:5px;width:80px;border:1px solid "+bgcol.colorHex()+";border-right:20px solid "+bgcol.colorHex()+";");
   		}
   		if($("#var_c"+id).length>0){
   			$("#var_c"+id).val(v).text(v).attr("style","margin-top:5px;width:80px;border:1px solid "+v+";border-right:20px solid "+v+";");
   		}
   		if($("#img_"+id).length>0){
	   		$("#img_"+id).attr("src",v);
   		}
   		if(v.startWith("/${sessionScope.account.owner.entity}/user/show/") > 0){
   			var pageid = v.substring(v.lastIndexOf("/")+1, v.lastIndexOf("."));
   			$("#gl_"+id).attr("checked","checked");
   			$("#sel_"+id).attr("disabled",false);
			$("#var_"+id).attr("disabled",true).val("/${sessionScope.account.owner.entity}/user/show/"+pageid+".html");

			var s = document.getElementById("sel_"+id);
			var ops = s.options;
			for(var i=0;i<ops.length; i++){
				var tempValue = ops[i].value;
				if(tempValue == pageid) {
					ops[i].selected = true;
					break;
				}
			}
   		}else {
   			$("#gl_"+id).attr("checked",false);
   			$("#sel_"+id).attr("disabled",true);
   		}
   	</s:iterator>
    callback(); 
} 
function sedit(){
	var s = $(".kindeditor");
   	for(var i=0;i<s.length;i++){
   		edit($(s[i]).attr("id"));
   	};
} 

$('.zpicker').colpick({
	layout:'hex',
	submit:0,
	onChange:function(hsb,hex,rgb,el,bySetColor) {
		$(el).css('border-color','#'+hex);
		var sb=('#'+hex).colorRgb();
		if(!bySetColor) $(el).val(sb.substring(sb.indexOf('(')+1,sb.indexOf(')')));
	}
}).keyup(function(){
		$(this).colpickSetColor(this.value);
	});

$('.ftCl').colpick({
	layout:'hex',
	submit:0,
	onChange:function(hsb,hex,rgb,el,bySetColor) {
		$(el).css('border-color','#'+hex);
		if(!bySetColor) $(el).val('#'+hex);
	}
}).keyup(function(){
		$(this).colpickSetColor(this.value);
	});
$('.btCl').colpick({
	layout:'hex',
	submit:0,
	onChange:function(hsb,hex,rgb,el,bySetColor) {
		$(el).css('border-color','#'+hex);
		if(!bySetColor) $(el).val('#'+hex);
	}
}).keyup(function(){
		$(this).colpickSetColor(this.value);
	});
	
function showCT(){
	var ct_name=$("#ct_name").val();
   	var ct_delay=$("#ct_delay").val();
   	var ct_duration=$("#ct_duration").val();
   	if(ct_name==""){
   		ct_name=getCTname($("#cttype").val(),$("#ctfx").val());
   	}
   	var style = "animation-delay:"+ct_delay+"s;animation-duration:"+ct_duration+"s;";
   	$("#div_pet").removeClass().attr("style",style).addClass(ct_name);
}

function usegld(id){
	var isChecked = $('#gl_'+id).is(":checked"); 
	if(isChecked){
		$("#sel_"+id).attr("disabled",false);
		$("#var_"+id).attr("disabled",true).val("/${oname}/user/show/"+$("#sel_"+id).val()+".html");
	}else{
		$("#sel_"+id).attr("disabled",true);
		$("#var_"+id).attr("disabled",false).val($("#var_"+id).attr("hydata"));
	}
}

function gld(id,select){
	var pageid = $(select).val();
	$("#var_"+id).val("/${oname}/user/show/"+pageid+".html");
}

function img_flash(id,desc){
	 var s = desc.split("*");
	 var width=s[0];
	 var height=s[1];
  	$('#myModal1').modal({
		backdrop: false,
		remote:"/page/img_flash.action?width="+width+"&height="+height+"&key="+id
	});
}
function uploadevent(status,picUrl,callbackdata){
	$('#myModal1').modal('hide');
	   status+="";
	switch(status){
	    case '1':
		   var id = $("#img_key").val();
		   $("#img_"+id).attr("src",picUrl);
		   $("#var_"+id).val(picUrl);
			break;
	    case '-1':
	    	break;
	    default:
	  } 
}

function onClik(pageid){  
       $(".hylink").attr("disabled",false);
       var jsonuserinfo = $('#form1').serializeObject();
       var json = JSON.stringify(jsonuserinfo);
        $.post("/page/editGpSub.action",{"pageid":pageid,"json":json},function(data){
        	if(data == 1){
        		$(".modal-backdrop").remove();
        	}else{
        		bootbox.alert("保存失败！请重试…");
        	}
        });
        ref();//刷新iframe
}

function edit(id) {
	var editor = KindEditor.create('#'+id, {
      	width : '450px',
      	height : '100px',
      	newlineTag : "br",
      	uploadJson : '/js/kindeditor/jsp/upload_json.jsp',
		fileManagerJson : '/js/kindeditor/jsp/file_manager_json.jsp',
		resizeType : 1,
		allowFileManager : true,
		allowPreviewEmoticons : true,
		allowImageUpload : true,
		items : [
		'source','fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
			'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
			'insertunorderedlist', '|', 'emoticons', 'image', 'link'],
		afterBlur: function(){this.sync();},
		afterChange: function(){this.sync();},
		afterCreate:function(){this.sync();}
	});
};

function ajaxFileUpload(imgDomain,id){
		var val = $("#upd_"+id).val();
		var idx = val.lastIndexOf(".");
		var result =val.substring(idx,val.length);
		if(result != ".jpg" && result != ".JPG" && result != ".jpeg" && result != ".JPEG" && result != ".bmp" && result != ".BMP" && result != ".png" && result != ".PNG"){
			bootbox.alert("文件格式不支持！（仅支持jpg/jpeg/bmp/png等格式）");
			return;
		}
		$.ajaxFileUpload({
				url:'/page/picUpload.action', 
				secureuri:false,
				fileElementId:"upd_"+id,
				dataType: 'json',
				success: function (data, status){
					if(status == "success"){
						$("#img_"+id).attr("src",imgDomain+data);
						$("#var_"+id).attr("value",imgDomain+data);
					}
				},
				error: function (data, status, e){
					bootbox.alert(e);
				}
			})
		return false;
	}
function sckall(fid,key){
	$('#sckModal').modal({
		backdrop: false,
		remote:"/page/sck.action?fcategoryid="+fid+"&key="+key
	});
}

function showMoreSck(fid,sid,pageId){
	$.post("/page/ajax_sck.action",{"fcategoryid":fid,"scategoryid":sid,"pageId":pageId},function(data){
		if(data.pager.totalPage <= pageId){
			$("#a_more").hide();
		}else{
			$("#a_more").show();
			$("#a_more").attr("onclick",'showMoreSck('+fid+','+sid+','+(pageId+1)+')');
		}
		$.each(data.materiallist,function(index,value){
			if(fid == 1){
				$("#sck_div").append("<div class='mtrl-item-c'><img src='"+value.path+"' width='238' onclick='checkPic(this)'/></div>");
			}
			else{
				$("#sck_div").append("<div class='mtrl-item-c'><img src='"+value.path+"' onclick='checkPic(this)'/></div>");
			}
		});
		reSizeModal();
	});
}

function reSizeModal(){
	 var mbHeight = $("#sckModal .modal-body").height();
       if (mbHeight > 420){
	       $("#sckModal .modal-body").mCustomScrollbar({
				setHeight:420,
				theme:"minimal-dark"
			});
		}
}

function changeSck(fid,sid,pageId){
	$("#sck_div").html('');
	$(".selected").removeClass('selected');
	$("#li_"+sid).addClass("selected");
	$.post("/page/ajax_sck.action",{"fcategoryid":fid,"scategoryid":sid,"pageId":pageId},function(data){
		if(data.pager.totalPage <= pageId){
			$("#a_more").hide();
		}else{
			$("#a_more").show();
			$("#a_more").attr("onclick",'showMoreSck('+fid+','+sid+','+(pageId+1)+')');
		}
		$.each(data.materiallist,function(index,value){
			if(fid == 1){
				$("#sck_div").append("<div class='mtrl-item-c'><img src='"+value.path+"' width='238' onclick='checkPic(this)'/></div>");
			}
			else{
				$("#sck_div").append("<div class='mtrl-item-c'><img src='"+value.path+"' onclick='checkPic(this)'/></div>");
			}
		});
		reSizeModal();
	});
}

function checkPic(obj){
	$(".sck-checked").removeClass("sck-checked");
	$(obj).addClass("sck-checked");
}

function selectSck(){
	var s = $(".sck-checked");
	if(s.length >= 1){
		var id = $("#hy_key").val();
		$("#img_"+id).attr("src",s[0].src);
		$("#var_"+id).val(s[0].src);
	}
}

function sckall(fid,key){
	$('#sckModal').modal({
		backdrop: false,
		remote:"/page/sck.action?fcategoryid="+fid+"&key="+key
	});
}
</script>

