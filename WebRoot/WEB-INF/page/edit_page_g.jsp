<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">

	function closeWindow(){
		window.opener = null;
  		window.close();
	}
	
	function editCode(pageid,backdrop){
		$('#wideModal').modal({
			backdrop:backdrop,
			remote:"/${sessionScope.account.owner.entity}/page/editPageSource.action?pageid="+pageid
		});
	}
	
</script>

		<div class="navbar navbar-self navbar-fixed-top" role="navigation">
			<div class="container-fluid">
				<ul class="nav navbar-nav">
					<li><a href="javascript:void(0);" onclick="editPage(${pageid})">
						<span class="glyphicon glyphicon-edit"></span>
						页面编辑
					</a></li>
					<li><a href="javascript:void(0);" onclick="editCode(${pageid},true)">
						<span class="glyphicon glyphicon-edit"></span>
						源代码
					</a></li>
					<li><a href="javascript:void(0);" onclick="btset()">
						<span class="glyphicon glyphicon-font"></span>
						属性设置
					</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li id="showQR" class="rel">
						<a href="javascript:void(0);">
						<span class="glyphicon glyphicon-phone"></span>
						手机预览
						</a>
						<div id="qrcode" style="display:none"><img style="position: absolute;left: 60px;top: 55px;" src="/images/huiyee-logo.png" /></div></li>
			        <li><a href="javascript:void(0);" onclick="closeWindow();">
			        	<span class="glyphicon glyphicon-remove"></span>
			        	退出编辑
			        </a></li>
			      </ul>
			</div>
		</div>
		<div class="clearfix">
		<div class="card-list">
			<input type="hidden" id="pageid" value="${pageid }">
		</div>
		<s:if test='dto.page.stype =="P"'>
			<div class="card-edit">
				<div class="forphone rel">
					<div class=" light" data-mcs-theme="minimal-dark">
					 <iframe id="ifm" src="/${sessionScope.account.owner.entity }/page/preview.action?pageid=${pageid}&method=e" width="318px" height="498px" scrolling="yes"></iframe>
					 </div>
				</div>
			</div>
		</s:if>
		<s:else>
			<iframe id="ifm" src="/${sessionScope.account.owner.entity }/page/preview.action?pageid=${pageid}&method=e" width="100%" height="700px" scrolling="yes"></iframe>
		</s:else>
		</div>
<!-- Modal -->
<div id="normalModal" class="modal fade bs-example-modal-lg" tabindex="0" role="dialog" aria-hidden="true">
  <div class="modal-dialog" style="margin:0 20px 0 0;">
    <div class="modal-content">
    </div>
  </div>
</div>
<div id="wideModal" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog" style="width:900px;">
    <div class="modal-content">
    </div>
  </div>
</div>
<script type="text/javascript" src="/js/qrcode.js"></script> 
<script type="text/javascript">
	var qrcode = new QRCode(document.getElementById("qrcode"), {
			width : 150,
			height : 150
	});

	function makeCode () {
			var pageid=$("#pageid").val();
			var domain='${siteDomain}';
			var elText = domain+"/${sessionScope.account.owner.entity}/user/preview/"+pageid+".html";
			qrcode.makeCode(elText);
	}

	makeCode();
				
	$(document).ready(function(){
		$("#showQR").hover(function(){
			$("#qrcode").show()
		},function(){
		    $("#qrcode").hide()
		});
	});
	function ref(){
		$('#ifm').attr('src', $('#ifm').attr('src'));
	}
</script>