<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
		<nav class="navbar navbar-grey navbar-fixed-top" role="navigation">
		  <div class="container">
				<ul class="nav navbar-nav">
					<li><a href="/page/editpage.action?pageid=${pageid}">页面编辑</a></li>
					<li><a href="/page/pageProcess.action?pageid=${pageid}">页面流程</a></li>
					<li><a target="_blank" href="/user/preview.action?pageid=${pageid}">页面预览</a></li>
					<li><a href="javascript:void(0);" onclick="showQrcode(true)">查看url地址</a></li>
				</ul>
		  </div>
		</nav>
		<div class="editor clearfix">
		<div class="card-list">
			<input type="hidden" id="pageid" value="${pageid }">
			<input type="hidden" id="orderlist" />
			<ul id="list">
				<s:iterator value="dto.pc" var="pc">
					<li id="${pc.id}" class="cardorder" title="1">${pc.cardname }
					<div class="dropdown" style="display:inline;float:right;">
					  <a class="card_edit" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">其他
					   <span class="caret"></span>
					  </a>
					  <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
					     <li><a href="javascript:void(0);" onclick="updateCardName(${pc.id})">改名</a></li>
					     <li><a href="javascript:void(0);" onclick="deleteCard(${pc.id},${pageid})">删除</a></li>
					  </ul>
					</div>
					<a class="card_edit" href="javascript:void(0);" onclick="reloadCard(${pc.id})">编辑</a>
					</li>
				</s:iterator>  
			</ul>    
		</div>
		<div id="card-edit" class="card-edit">
			<jsp:include page="/WEB-INF/card/${dto.tc.path}" flush="true"></jsp:include>
		</div>
			<script type="text/javascript" src="/js/jquery.dragsort-0.5.2.min.js"></script> 
			<script type="text/javascript">
				$("#list").dragsort({ dragEnd: saveOrder});
				
				function saveOrder() {
					var order = $(".cardorder");
					var str = "";
					for(var i= 0;i<order.length;i++){
						str += order[i].id +"," +(i+1) + ";";
					}
					$.post("/page/moveCard.action",{"cardMoveStr":str});
				};
				
				$(document).ready(function(e) {
						$(".block").hover(
						     function(){
							    $(this).addClass("rel").append("<div class='block-modal'></div>");
							  },
						     function(){
						     	$(this).removeClass("rel");
							    $(".block-modal").remove();
							  }
							 );
							$(".block").bind('click', function(){
									var id = $(this).attr("hydata");
									if(id >0){
										$('#myModal').modal({
											backdrop:'static',
											remote:"/page/showBlock.action?relationid="+id
										});
									}
							});
				});
			</script>
		</div>
<!-- Modal -->
<div id="myModal" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
    </div>
  </div>
</div>
<div id="myModal1" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-hidden="true">
			<div class="modal-dialog">
  			<div class="modal-content">
	  			<div class="modal-header">
					  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
					  <h4 class="modal-title" id="myModalLabel">图片上传</h4>
					</div>
					<div class="modal-body">
	  					<div id="upload_div">
							<OBJECT classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
							codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,40,0"
							WIDTH="650" HEIGHT="450" id="myMovieName">
							<PARAM NAME=movie VALUE="/flashupload/avatar.swf">
							<PARAM NAME=quality VALUE=high>
							<PARAM NAME=bgcolor VALUE=#FFFFFF>
							<param name="flashvars" value="imgUrl=/flashupload/default.jpg&uploadUrl=/user/shareUpload.action?feature=113-${pageid}-${result['1'].share.id}&uploadSrc=false&pCut=150|150&pData=353|353|235|235|80|80&pSize=235|235|0|0|0|0" />
							<EMBED src="/flashupload/avatar.swf" quality=high bgcolor=#FFFFFF WIDTH="650" HEIGHT="450" wmode="transparent" flashVars="imgUrl=/flashupload/default.jpg&uploadUrl=/user/shareUpload.action?feature=113-${pageid}-${result['1'].share.id}&uploadSrc=false&pCut=150|150&pData=353|353|235|235|80|80&pSize=235|235|0|0|0|0"
							NAME="myMovieName" ALIGN="" TYPE="application/x-shockwave-flash" allowScriptAccess="always"
							PLUGINSPAGE="http://www.macromedia.com/go/getflashplayer">
							</EMBED>
							</OBJECT>
						</div>
					</div>
  			</div>
			</div>
</div>
