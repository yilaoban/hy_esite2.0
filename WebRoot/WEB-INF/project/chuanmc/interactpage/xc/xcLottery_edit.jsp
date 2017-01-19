<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	function checkSub(){
		var index = layer.msg('正在保存配置,请稍等...', {icon: 16,time:0});
		$.ajax({ 
			cache: true,
			type: "POST",
			url:"/${oname}/interact/xcLottery_update.action",
			data:$('#form1').serialize(),
			async: false, 
			success: function(data) {
				layer.close(index);
				if(data!=-10000){
					if(parseInt(data)>0){
						layer.alert("配置成功,添加特邀嘉宾"+msg+"人,具体内容请查看详情");
					}else{
						layer.alert("配置成功");
					}
				}
			} 
		});
	}
	
	
	function setCookie(name,value) 
	{ 
	    var exp = new Date(); 
	    exp.setTime(exp.getTime() + 10*60*1000); 
	    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString(); 
	} 
	
	function getCookie(name) 
	{ 
	    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	 
	    if(arr=document.cookie.match(reg))
	 
	        return unescape(arr[2]); 
	    else 
	        return null; 
	} 
	
	$(document).ready(function(){
		 	var tab=getCookie('xcTab');
			if(tab!=0){
				$('#myTab a[href="#'+tab+'"]').tab('show');
				if(tab=="tab4"){
					$("#addHdBtn").show();
				}
			}
		 	
			useUpload();
			signShow();
			
			$('input:radio[name="checkinConfig.atype"]').bind("click",function(){
				signShow();
			});
			
			$('#myTab a[href^="#tab"]').click(function (e) {
				  e.preventDefault();
				  $(this).tab('show');
			});
			
			$('#myTab a[href^="#tab"]').on('shown.bs.tab', function (e) {
				 if($(this).attr("href")=="#tab4"){
					 $("#addHdBtn").show();
				 }else{
					 $("#addHdBtn").hide();
				 }
			})
			
		});
		
	function useUpload(){
		inviteRecord();
		upbut();
		
		
	}
	function upbut(){
		var index=$("input:radio[name='inviteConfig.atype']:checked").val();
		var type=$("#upstatus").is(":visible");
		if(index=="S"){
			$("#xcUpload").attr("class","invite");
			if(type){
				$("#xcUpload").show();
			}else{
				$("#xcUpload").hide();
			}
		}else{
			$("#xcUpload").hide();
			$("#xcUpload").removeAttr("class");
		}
	}
	
	
	function inviteRecord(){
		var href1="/${oname }/interact/invite_detail.action?xcid=${xcid }&utype=1";
		var href2="/${oname }/interact/xcSendDetail.action?xcid=${xcid }&mid=${mid }";
		var index=$("input:radio[name='inviteConfig.atype']:checked").val();
		var needinvite=$("input:radio[name='xc.needinvite']:checked").val()
		if(index=="N"||needinvite=="N"){
			$("#inviteRecord").attr("href",href1);
		}else{
			$("#inviteRecord").attr("href",href2);
		}
	}
	
	
	function lotterystart(id,interactid){
		var layerid=layer.confirm('确定开始吗?',{title:"确认开始"},function(){
			$.post("/${oname}/interact/hdstart.action",{"hdid":id,"interactid":interactid},function(data){
				if(data >0){
					layer.msg('互动功能已开始!',{icon: 6, time: 1000});
				}else{
					layer.msg('互动功能开启失败!',{icon: 5, time: 1000});
				}
			});
		});
	}
	
	function xcclean(xcid){
		var layerid=layer.confirm('确定清空数据吗?<br><input type="checkbox" name="inviteclean" value="N"/>保留邀请函打开记录.',{title:"清空微现场数据"},function(){
			var inviteclean=$("input:checkbox[name='inviteclean']:checked").val();
			if(inviteclean==null){
				inviteclean="Y";
			}
			$.post("/${oname}/interact/xcclean.action",{"xcid":xcid,"inviteclean":inviteclean},function(data){
				if(data >0){
					layer.msg('清空完成!',{icon: 6, time: 1000});
				}else{
					layer.msg('清空失败!',{icon: 5, time: 1000});
				}
			});
		});
	}
	
	function xchdDel(entityid,interactid,title){
		var layerid=layer.confirm('确定将内容['+title+']删除吗?',function(){
			$.post("/${oname}/interact/rmXchd.action",{inajax:1,"hdid":entityid,"interactid":interactid,"xcid":'${xcid}'},function(data){
				if(data==1){
					layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
						window.location.reload();
					}); 
				}else{
					layer.msg('删除失败！请重试……', {icon: 5, time: 2000});
				}
			});
		});
	}
	
	
	function signShow(){
		var e=$("input:radio[name='checkinConfig.atype']:checked");
		if(e.val()=="G"){
			$(".check_G").show();			
		}else{
			$(".check_G").hide();
		}
	}
</script>
<style>
.tab-content {padding:30px;}
.tab-content dl dt {
    float: left;
    width: 120px;
    padding-right: 20px;
    text-align: right;
}
</style>
<a name="maodian"></a>
<div class="wrap_content left_module">
  <div class="switch_tab_div pb10">
	<span><a href="/${oname }/page/xcSiteGroup.action">我的现场</a><i class="gt">&gt;&gt;</i><a href="/${oname }/page/xcSite.action?xcid=${xcid}">${dh.sitegroup.groupname }</a><i class="gt">&gt;&gt;</i>基础设置</span>
	<p class="clearfix"></p>
 </div>


  <form action="xcLottery_update.action" method="post" enctype="multipart/form-data" id="form1" class="formview">
  <input type="hidden" name="mid" value="10014">
  <input type="hidden" name="xcid" value="${xcid }">
  <input type="hidden" name="xc.id" value="${xcid }">

<div>
  <ul class="nav nav-tabs" role="tablist" id="myTab">
    <li role="presentation"><a href="/${oname }/page/xcAptIndex.action?aptid=${xc.aptid}&xcid=${xcid}" onclick="setCookie('xcTab','tab1')">申请配置</a></li>
    <li role="presentation" class="active"><a href="#tab1" role="tab" aria-controls="tab1" data-toggle="tab" onclick="setCookie('xcTab','tab1')">邀请配置</a></li>
    <li role="presentation"><a href="#tab2" role="tab" aria-controls="tab2" data-toggle="tab" onclick="setCookie('xcTab','tab2')">签到配置</a></li>
    <li role="presentation"><a href="#tab3" role="tab" aria-controls="tab3" data-toggle="tab" onclick="setCookie('xcTab','tab3')">评论配置</a></li>
    <li role="presentation"><a href="#tab4" role="tab" aria-controls="tab4" data-toggle="tab" onclick="setCookie('xcTab','tab4')">抽奖配置</a></li>
     <a href="/${oname }/page/xcSite.action?xcid=${xcid }" class="return pull-right" title="返回"></a>
  </ul>

  <div class="tab-content">
    <div role="tabpanel" class="tab-pane active" id="tab1">
    			<dl class="invite">
	 	<dt>是否限定邀请人</dt>
		<dd>
			<s:if test="inviteConfig == null">
				<label><input type="radio" name="inviteConfig.atype" value="S" onchange="useUpload()"/>限定 </label>
				<label><input type="radio" name="inviteConfig.atype" value="N" checked="checked" onchange="useUpload()"/>不限定</label>
			</s:if>
			<s:else>
				<label><input type="radio" name="inviteConfig.atype" value="S"  onchange="useUpload()" <s:if test='inviteConfig.atype == "S"' >checked="checked"</s:if>/>限定 </label>
				<label><input type="radio" name="inviteConfig.atype" value="N"  onchange="useUpload()" <s:if test='inviteConfig.atype == "N"' >checked="checked"</s:if>/>不限定</label>
			</s:else>
		</dd>
	</dl>
	<dl class="invite" id="xcUpload">
	 	<dt>上传邀请人名单 </dt>
		<dd>
			<input type="file" name="userfile"/>
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
    </div>
    <div role="tabpanel" class="tab-pane" id="tab2">
    	<dl class="checkin">
	 	<dt>签到成功提示语 </dt>
		<dd>
			<input type="text" class="text-medium" value="${checkinConfig.note }" name="checkinConfig.note"/>
		</dd>
	</dl>
	<dl class="checkin">
	 	<dt>是否限定签到人</dt>
		<dd>
			<s:if test="checkinConfig == null">
				<label><input type="radio" name="checkinConfig.atype" value="Y" />限定仅邀请人可签到</label>
				<label><input type="radio" name="checkinConfig.atype" value="G" />限定关注者可签到</label>
				<label><input type="radio" name="checkinConfig.atype" value="N" checked="checked"/>不限定</label>
			</s:if>
			<s:else>
				<label><input type="radio" name="checkinConfig.atype" value="Y" <s:if test='checkinConfig.atype == "Y"' >checked="checked"</s:if>/>限定仅邀请人可签到 </label>
				<label><input type="radio" name="checkinConfig.atype" value="G" <s:if test='checkinConfig.atype == "G"' >checked="checked"</s:if>/>限定关注者可签到 </label>
				<label><input type="radio" name="checkinConfig.atype" value="N" <s:if test='checkinConfig.atype == "N"' >checked="checked"</s:if>/>不限定</label>
			</s:else>
		</dd>
	</dl>
	<dl class="check_G">
	 	<dt>邀请用户关注配置</dt>
		<dd>
			<a href="javascript:wxgzevent('${checkinConfig.gzid }')">关注设置</a>
			<!-- 点击设置弹出IFRAME完成设置后会改写id='gzid'的隐藏域将value传进来 -->
			<input type="hidden" name="checkinConfig.gzid" id="gzid"/>
		</dd>
	</dl>
	</div>
    <div role="tabpanel" class="tab-pane" id="tab3">
    <dl class="comment" style="display: none">
	 	<dt>评论内容</dt>
		<dd>
			<input type="text" class="text-medium" value="${commentCongig.note }" name="commentCongig.note"/>
		</dd>
	</dl>
	<dl class="comment">
	 	<dt>是否限定评论人</dt>
		<dd>
			<s:if test="commentCongig == null">
				<label><input type="radio" name="commentCongig.atype" value="Q" />限定仅签到者可评论 </label>
				<label><input type="radio" name="commentCongig.atype" value="N" checked="checked"/>不限定</label>
			</s:if>
			<s:else>
				<label><input type="radio" name="commentCongig.atype" value="Q" <s:if test='commentCongig.atype == "Q"' >checked="checked"</s:if>/>限定仅签到者可评论 </label>
				<label><input type="radio" name="commentCongig.atype" value="N" <s:if test='commentCongig.atype == "N"' >checked="checked"</s:if>/>不限定</label>
			</s:else>
		</dd>
	</dl>
	<dl class="comment">
	 	<dt>是否审核</dt>
		<dd>
		<s:if test="commentCongig == null">
			<label><input type="radio" name="commentCongig.checked" value="Y" />需要审核 </label>
			<label><input type="radio" name="commentCongig.checked" value="N" checked="checked"/>不需要审核</label>
	 	</s:if>
	 	<s:else>
	 		<label><input type="radio" name="commentCongig.checked" value="Y" <s:if test='commentCongig.checked == "Y"' >checked="checked"</s:if>/>需要审核</label>
			<label><input type="radio" name="commentCongig.checked" value="N" <s:if test='commentCongig.checked == "N"' >checked="checked"</s:if>/>不需要审核</label>
	 	</s:else>
		</dd>
	</dl>
    </div>
    <div role="tabpanel" class="tab-pane" id="tab4">
    	<dl class="type">
	 	<dt>启动需取出的人数</dt>
		<dd>
		<s:if test="lconfig != null">
			<input type="text" class="text-medium" value="${lconfig.num }" id="num" autocomplete="off" name="lconfig.num" onkeyup="this.value=this.value.replace(/\D/g,'')">
			<input type="hidden" name="lconfig.started" value="${lconfig.started}"/>
  			<input type="hidden" name="lconfig.startnum" value="${lconfig.startnum }"/>
		</s:if>
		<s:else>
			<input type="text" class="text-medium" value="0" id="num" autocomplete="off" name="lconfig.num" onkeyup="this.value=this.value.replace(/\D/g,'')">
		</s:else>
		</dd>
	</dl>
	<dl class="type">
	 	<dt>类型</dt>
		<dd>
	 	<s:if test="lconfig == null">
			<label><input type="radio" name="lconfig.type" value="J" checked="checked"/>参与抽奖 </label>
			<label><input type="radio" name="lconfig.type" value="C" />参与并且提高中奖率</label>
			<label><input type="radio" name="lconfig.type" value="P"/>排序抽奖</label>
	 	</s:if>
	 	<s:else>
	 		<label><input type="radio" name="lconfig.type" value="J" <s:if test='lconfig.type == "J"' >checked="checked"</s:if>/>参与抽奖 </label>
			<label><input type="radio" name="lconfig.type" value="C" <s:if test='lconfig.type == "C"' >checked="checked"</s:if>/>参与并且提高中奖率</label>
			<label><input type="radio" name="lconfig.type" value="P" <s:if test='lconfig.type == "P"' >checked="checked"</s:if>/>排序抽奖</label>
	 	</s:else>
		</dd>
	</dl>
	<dl class="type">
	 	<dt>指定抽奖</dt>
		<dd>
		<s:if test="lconfig == null">
			<label><input type="radio" name="lconfig.atype" value="Y" checked="checked"/>邀请 </label>
			<label><input type="radio" name="lconfig.atype" value="P" />评论</label>
			<label><input type="radio" name="lconfig.atype" value="Q"/>签到</label>
			<label><input type="radio" name="lconfig.atype" value="N"/>不需要</label>
		</s:if>
		<s:else>
			<label><input type="radio" name="lconfig.atype" value="Y" <s:if test='lconfig.atype == "Y"' >checked="checked"</s:if>/>邀请 </label>
			<label><input type="radio" name="lconfig.atype" value="P" <s:if test='lconfig.atype == "P"' >checked="checked"</s:if>/>评论</label>
			<label><input type="radio" name="lconfig.atype" value="Q" <s:if test='lconfig.atype == "Q"' >checked="checked"</s:if>/>签到</label>
			<label><input type="radio" name="lconfig.atype" value="N" <s:if test='lconfig.atype == "N"' >checked="checked"</s:if>/>不需要</label>
		</s:else>
		</dd>
	</dl>
	<dl class="type">
	 	<dt>中奖类型</dt>
		<dd>
		<s:if test="lconfig == null">
			<label><input type="radio" name="lconfig.unique" value="N" checked="checked"/>重复中奖 </label>
			<label><input type="radio" name="lconfig.unique" value="Y" />唯一中奖</label>
		</s:if>
		<s:else>
			<label><input type="radio" name="lconfig.unique" value="N" <s:if test='lconfig.unique == "N"' >checked="checked"</s:if>/>重复中奖 </label>
			<label><input type="radio" name="lconfig.unique" value="Y" <s:if test='lconfig.unique == "Y"' >checked="checked"</s:if>/>唯一中奖</label>
		</s:else>
		</dd>
	</dl>
	<s:iterator value="entitys" var="e">
		<dl>
		 	<dt style="font-size:18px;">${e.title }</dt>
			<dd>
				<span class="xc_switch1 xc_switch_off xc_switch_light" onclick="xchdDel('${e.entityid }','${e.featureid }','${e.title }')">删除</span>
				<s:if test='featureid==10002'>
					<!-- 投票 -->
					<a href="/${oname }/interact/to_update_vote_design.action?voteid=${e.entityid }&redirectXc=${xcid }" class="xc_info">配置</a>
					<a href="/${oname }/interact/vote_content.action?voteid=${e.entityid }&mid=10002&redirectXc=${xcid }" class="xc_info">投票内容</a>
					<a href="/${oname }/interact/vote_record_list.action?voteid=${e.entityid }&mid=10002&type=0&redirectXc=${xcid }" class="xc_info">数据</a>
					<a href="javascript:voteclean('${oname }',${e.entityid })" class="xc_info">清空</a>
				</s:if>
				<s:if test='featureid==10004'>
					<!-- 砸金蛋 -->
					<a href="/${oname }/interact/edit_lhj.action?lid=${e.entityid }&mid=${e.featureid }&redirectXc=${xcid }" class="xc_info">配置</a>
					<a href="/${oname }/interact/prize.action?lid=${e.entityid }&returnType=L&mid=${e.featureid }&redirectXc=${xcid }" class="xc_info">奖品管理</a>
					<a href="javascript:checkLottery(${e.entityid })" class="xc_info">预算</a>
					<a href="javascript:lotterystart('${e.entityid }','10003')" class="xc_info">开始</a>
					<a href="/${oname }/interact/lottery_user_sina.action?lid=${e.entityid }&type=L&mid=10004&redirectXc=${xcid }" class="xc_info">数据</a>
					<a href="javascript:lotteryclean('${oname }',${e.entityid} )" class="xc_info">清空</a>
				</s:if>
				<s:if test='featureid==10003'>
					<!-- 大转盘 -->
					<a href="/${oname }/interact/edit_zhuanpan.action?lid=${e.entityid }&mid=${e.featureid }&redirectXc=${xcid }" class="xc_info">配置</a>
					<a href="/${oname }/interact/prize.action?lid=${e.entityid }&returnType=L&mid=${e.featureid }&redirectXc=${xcid }" class="xc_info">奖品管理</a>
					<a href="javascript:checkLottery(${e.entityid })" class="xc_info">预算</a>
					<a href="javascript:lotterystart('${e.entityid }','10003')" class="xc_info">开始</a>
					<a href="/${oname }/interact/lottery_user_sina.action?lid=${e.entityid }&type=L&mid=10004&redirectXc=${xcid }" class="xc_info">数据</a>
					<a href="javascript:lotteryclean('${oname }',${e.entityid} )" class="xc_info">清空</a>
				</s:if>
			</dd>
		</dl>
	</s:iterator>
    </div>
  </div>
  
  	<dl>
	 	<dt></dt>
		<dd>
			<input type="button" class="btn btn-primary" value="保存" onclick="checkSub()">
			<input type="button" class="btn btn-primary" value="添加互动" onclick="xchd('${xcid }')" id="addHdBtn" style="display: none">
			<input type="button" class="btn btn-primary" value="清空数据" onclick="xcclean('${xcid }')">
		</dd>
	</dl>
</div>
	
 </form>
 </div>
 <div id="myModal1" class="modal fade bs-example-modal-lg" role="dialog" aria-hidden="true">
	<div class="modal-dialog" style="width:400px;">
			<div class="modal-content">
			</div>
	</div>
</div>
