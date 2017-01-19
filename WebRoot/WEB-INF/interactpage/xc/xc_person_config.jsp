<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="/js/ajaxfileupload.js"></script>
<script type="text/javascript">
function inviteRecord(){
	var href1="/${oname }/interact/invite_detail.action?xcid=${xcid }&utype=1";
	var href2="/${oname }/interact/xcSendDetail.action?xcid=${xcid }&mid=${mid }";
	var index=$("input:radio[name='inviteConfig.atype']:checked").val();
	var needinvite=$("input:radio[name='xc.needinvite']:checked").val()
	if(index=="N"||needinvite=="N"){
		window.open(href1);
	}else{
		window.open(href2);
	}
}

$.fn.serializeJson=function(){  
    var serializeObj={};  
    var array=this.serializeArray();  
    $(array).each(function(){  
        if(serializeObj[this.name]){  
            if($.isArray(serializeObj[this.name])){  
                serializeObj[this.name].push(this.value);  
            }else{  
                serializeObj[this.name]=[serializeObj[this.name],this.value];  
            }  
        }else{  
            serializeObj[this.name]=this.value;   
        }  
    });  
    return serializeObj;  
};  

function formSub(){
	var d=$("#form1").serializeJson();
	$.ajaxFileUpload({
		url:'/${oname}/page/xcPersonSub.action', 
		secureuri:false,
		fileElementId:"userfile",
		dataType: 'json',
		data:d,
		success: function (data, status){
			layer.msg(data, {icon: 6, time: 1500}, function(){
				window.location.reload();
			}); 
		},
		error: function (data, status, e){
			alert(e);
		}
	})
	return false;
}
</script>
<a name="maodian"></a>
<div class="wrap_content left_module">
  <div class="switch_tab_div pb10">
	<span><a href="/${oname }/page/xcSiteGroup.action">我的现场</a><i class="gt">&gt;&gt;</i><a href="/${oname }/page/xcSite.action?xcid=${xcid}">${dh.sitegroup.groupname }</a><i class="gt">&gt;&gt;</i>参会人员</span>
	<p class="clearfix"></p>
 </div>


  <form action="xcLottery_update.action" method="post" enctype="multipart/form-data" id="form1" class="formview">
  <input type="hidden" name="mid" value="10014">
  <input type="hidden" name="xcid" value="${xcid }">
  <div class="toolbar pb10">
	  <a href="/${oname }/page/xcSite.action?xcid=${xcid }" class="return" title="返回"></a>
	</div>
	<div class="mt20">
	<dl>
	 	<dt style="font-size:18px;">邀请嘉宾</dt>
		<dd>
			<label for="xc.needinvite1"><span class="xc_switch1 xc_switch_on <s:if test='xc.needinvite == "Y"' >xc_switch_light</s:if>" onclick="javascript:$('.xc_switch1').removeClass('xc_switch_light');$(this).addClass('xc_switch_light');">开</span></label><input type="radio" id="xc.needinvite1" name="xc.needinvite" value="Y" style="display:none;" <s:if test='xc.needinvite == "Y"' >checked="checked"</s:if> /><label for="xc.needinvite2"><span class="xc_switch1 xc_switch_off <s:if test='xc.needinvite == "N"' >xc_switch_light</s:if>" onclick="javascript:$('.xc_switch1').removeClass('xc_switch_light');$(this).addClass('xc_switch_light');">关</span></label><input type="radio" id="xc.needinvite2" name="xc.needinvite" value="N" style="display:none;" <s:if test='xc.needinvite == "N"' >checked="checked"</s:if>/>
			<a id="inviteRecord" href="javascript:inviteRecord()" class="xc_info">详情</a>
		</dd>
	</dl>
	<div style="padding-left:150px;">
		<dl class="invite">
	 	<dt>是否限定邀请人</dt>
		<dd>
			<s:if test="inviteConfig == null">
				<label><input type="radio" name="inviteConfig.atype" value="S"/>限定 </label>
				<label><input type="radio" name="inviteConfig.atype" value="N" checked="checked"/>不限定</label>
			</s:if>
			<s:else>
				<label><input type="radio" name="inviteConfig.atype" value="S" <s:if test='inviteConfig.atype == "S"' >checked="checked"</s:if>/>限定 </label>
				<label><input type="radio" name="inviteConfig.atype" value="N" <s:if test='inviteConfig.atype == "N"' >checked="checked"</s:if>/>不限定</label>
			</s:else>
		</dd>
	</dl>
	<dl class="invite" id="xcUpload">
	 	<dt>上传邀请人名单 </dt>
		<dd>
			<input type="file" name="userfile" id="userfile"/>
		</dd>
		<dd>
			<font style="color: red">上传Excel文件,第一列为序号,第二列是真实名称,第三列为微信昵称</font>
		</dd>
	</dl>
	<dl class="invite" id="upstatus">
	 	<dt>邀请成功提示语 </dt>
		<dd>
			<input type="text" class="text-medium" value="${inviteConfig.note}" name="inviteConfig.note"/>
		</dd>
	</dl>
	<dl class="invite">
	 	<dt>非邀请成功提示语 </dt>
		<dd>
			<input type="text" class="text-medium" value="${inviteConfig.failNote}" name="inviteConfig.failNote"/>
		</dd>
	</dl>
	<dl>
	 	<dt></dt>
		<dd>
			<input type="button" class="btn btn-primary" value="保存" onclick="formSub()">
		</dd>
	</dl>
	</div>
 </form>
 </div>
