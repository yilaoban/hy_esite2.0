<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">

	function closeWindow(){
		window.opener = null;
  		window.close();
	}
	
	function openwin(pageid) {
		var stype=$("#pagestype").val();
		if(stype=='P'){
			 //window.open ('/user/preview/'+pageid+".html", 'newwindow', 'height=502, width=320, top=120,left=0,toolbar=1, menubar=0, scrollbars=0, resizable=0, location=0, status=0');
			 window.open ('/user/preview.action?pageid=' + pageid, 'newwindow', 'height=502, width=320, top=120,left=0,toolbar=1, menubar=0, scrollbars=0, resizable=0, location=0, status=0');
		}
		if(stype=='C'){
			 window.open ('/user/preview/'+pageid+".html", 'newwindow');  
		}
	}
	
	function editCode(pageid,backdrop){
		$('#wideModal').modal({
			backdrop:backdrop,
			remote:"/page/editCode.action?pageid="+pageid
		});
	}
	
</script>

		<div class="navbar navbar-self navbar-fixed-top" role="navigation">
			<div class="container-fluid">
				<ul class="nav navbar-nav">
					<li><a href="/page/edit_kongbaipage.action?pageid=${pageid}">
						<span class="glyphicon glyphicon-edit"></span>
						页面编辑
					</a></li>
					<li><a href="javascript:void(0);" onclick="editCode(${pageid},true)">
						<span class="glyphicon glyphicon-edit"></span>
						源代码
					</a></li>
					 <li class="dropdown" role="presentation">
						<a id="drop4" class="dropdown-toggle" aria-expanded="false" aria-haspopup="true" data-toggle="dropdown" href="#">
						<span class="glyphicon glyphicon-edit"></span>
						CSS
						<span class="caret"></span>
						</a>
						
						<s:if test='!dto.css.isEmpty() && dto.css != null'>
							<ul id="menu1" class="dropdown-menu" aria-labelledby="drop4" role="menu">
								<s:iterator value="dto.css" var="cs" status="st">
									<li>
										<a href="javascript:void(0);" onclick="readFileContent(true,'${cs.value }','C')">${cs.key }</a>
									</li>
						  		</s:iterator>
							</ul>
						</s:if>
					</li>
					<li class="dropdown" >
					 <a id="dLabel" data-target="#" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					 	<span class="glyphicon glyphicon-edit"></span>
					   JS 
					    <span class="caret"></span>
					  </a>
					
					<s:if test='!dto.js.isEmpty() && dto.js != null'>
						  <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
						  	<s:iterator value="dto.js" var="js" status="st">
						  		<li>
						  			<a href="javascript:void(0);" onclick="readFileContent(true,'${js.value }','J')">${js.key }</a>
						  		</li>
						  	</s:iterator>
						  </ul>
					  </s:if>
					</li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="javascript:void(0);" onclick="openwin(${pageid})">
						<span class="glyphicon glyphicon-share"></span>
						页面预览
					</a></li>
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
			<input type="hidden" id="currentCard">
			<input type="hidden" id="orderlist" />
			<input type="hidden" value="${dto.stype }" id="pagestype">
		</div>
		<s:if test='dto.page.stype =="P"'>
			<div class="card-edit">
				<div class="forphone rel">
					<div class="page-set">
						<a href="javascript:void(0);" onclick="showBlk()" title="恢复隐藏"><img src="/images/rst-eye.png" /></a>
						<a href="javascript:void(0);" onclick="bgset()" title="背景设置"><img src="/images/bg_color.png" /></a>
					</div>
					<div class=" light" data-mcs-theme="minimal-dark">
					 <iframe src="/page/preview.action?pageid=${pageid}" width="318px" height="498px" scrolling="yes"></iframe>
					 </div>
				</div>
			</div>
		</s:if>
		<s:else>
			<div id="card-edit" class="card-edit rel">${dto.pageHtml.html }</div>
		</s:else>
		</div>
<!-- Modal -->
<div id="normalModal" class="modal fade bs-example-modal-lg" tabindex="0" role="dialog" aria-hidden="true">
  <div class="modal-dialog">
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
			var elText = domain+"/user/preview/"+pageid+".html";
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
</script>