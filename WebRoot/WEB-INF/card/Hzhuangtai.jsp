<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<input id='cardid' type='hidden' value='${pcid}' />
<!-- 合伙人状态 -->
<link href="/css/Hzhuangtai/index.css" rel="stylesheet" type="text/css" />

<%@include file="/WEB-INF/card/background.jsp"%>


<div class="ztbanner block ${blocks[0].ctname}" status="0" hyct="Y" style="${blocks[0].hyct}" hyblk="S" hydata="${blocks[0].rid}"><img src="${blocks[0].img}" /></div>

<div class="ztcontent block ${blocks[1].ctname}" hyblk="S" status="0" hyct="Y" style="${blocks[1].hyct}" hydata="${blocks[1].rid}">
	<div class="ztcontent1" id="zt1">
		<div>您的合伙人身份已经被取消</div>
		<div>您可以拨打我们的客服电话咨询</div>
		<div>400-812-6655</div>
	</div>
	<div class="ztcontent2" id="zt2">
		<img width=120 height=120 src="/images/Hzhuangtai/hhr2.png" />
		<div>您的申请被驳回.<span id="err"></span></div>
		<div>如果您需要可以再次提交申请...</div>
		<a href="${blocks[1].link1}"><input name="" type="button"  class="Hztbutton" id="" value="提交申请"/></a>
	</div>
	<div class="ztcontent3" id="zt3">
		<div>您还未提交过申请!请先提交申请...</div>
		<a href="${blocks[1].link1}"><input name="" type="button"  class="Hztbutton" id="" value="提交申请"/></a>
	</div>
	<div class="ztcontent4" id="zt4">
		<img width=120 height=120 src="/images/Hzhuangtai/hhr1.png" />
		<div>感谢您成为我们的合伙人!</div>
		<div>您可以点击开始推广!</div>
		<a href="${blocks[1].link2}"><input name="" type="button"  class="Hztbutton" id="" value="开始推广"/></a>
	</div>
	<div class="ztcontent5" id="zt5">
		<div>您已提交申请!等待处理中...</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		$.post("/${oname}/user/checkSenderStatus.action",function(data){
			if(data==null){
				$("#zt3").show();
			}else if(data.status==null){
				$("#zt5").show();
			}else if(data.status=="ERR"){
				$("#zt2").show();
				$("#err").text("原因:"+data.reason+"!");
			}else if(data.status=="FAL"){
				$("#zt1").show();
			}else if(data.status=="CMP"){
				$("#zt4").show();
			}else{
				alert(data.reason);
			}
		});
	});
	

 </script>
<%@include file="/WEB-INF/card/dzpfileTprize.jsp"%>
