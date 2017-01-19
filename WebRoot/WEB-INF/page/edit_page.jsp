<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	$(document).ready(function(e) {
		var pcid = $('#currentCard').val();
		var strCookie=document.cookie;
		var arrCookie=strCookie.split(";");
		var user = 0;
		for(var i=0;i<arrCookie.length;i++){
		       var arr=arrCookie[i].split("=");
		       if("currentCard" == arr[0].trim()){
			      user=arr[1];
			      document.cookie = "currentCard=0";
			      break;
		       }
		}
		pcid = user;
		if(pcid >0){
			var desc = $('#'+pcid).attr("desc");
			reloadCard(pcid,desc);
		}else{
			var pagecardid = $("#firstcardid").val();
			if(pagecardid >0){
				var desc = $('#'+pagecardid).attr("desc");
				reloadCard(pagecardid,desc);
			}
		}
	});
	
	function addNoCard(){
		bootbox.alert("首页/列表/社区类只能添加一张卡片！");
	}
	
	function clickOption(select) {
		window.location.href="/${oname}/page/editpage.action?pageid=" + $(select).val();
	}
	function closeWindow(){
		window.opener = null;
  		window.close();
	}
	
	function openwin(pageid) {
		var stype=$("#pagestype").val();
		if(stype=='P'){
			 window.open ('/${oname}/user/preview/'+pageid+".html", 'newwindow', 'height=502, width=320, top=120,left=0,toolbar=1, menubar=0, scrollbars=0, resizable=0, location=0, status=0');
		}
		if(stype=='C'){
			 window.open ('/${oname}/user/preview/'+pageid+".html", 'newwindow');  
		}
	}
</script>

		<div class="navbar navbar-self navbar-fixed-top" role="navigation">
			<div class="container-fluid">
				<ul class="nav navbar-nav">
					<li><a href="/${oname }/page/editpage.action?pageid=${pageid}">
						<span class="glyphicon glyphicon-edit"></span>
						页面编辑
					</a></li>
					<li><a href="javascript:void(0);" onclick="btset()">
						<span class="glyphicon glyphicon-font"></span>
						页面设置
					</a></li>
					<li><a href="javascript:void(0);" onclick="bjset()">
						<span class="glyphicon glyphicon-music"></span>
						页面背景
					</a></li>
					<li><a href="javascript:void(0);" onclick="dhset()">
						<span class="glyphicon glyphicon-music"></span>
						导航设置
					</a>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<s:if test='dto.page.stype =="P"'>
					<li id="showQR" class="rel">
						<a href="javascript:void(0);">
						<span class="glyphicon glyphicon-phone"></span>
						手机预览
						</a>
						<div id="qrcode" style="display:none"><a href="javascript:void(0);" onclick="openwin(${pageid})"><img style="position: absolute;left: 60px;top: 55px;" src="/images/huiyee-logo.png" /></a></div></li>
			        </s:if>
			        <s:else>
				        <li id="showQR" class="rel">
							<a href="javascript:void(0);" onclick="openwin(${pageid})">
							<span class="glyphicon glyphicon-phone"></span>
							预览
							</a>
						</li>
			        </s:else>
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
			<input type="hidden" id="oname" value="${oname}">
			<input type="hidden" id="currentCard">
			<input type="hidden" id="orderlist" />
			<input type="hidden" value="${dto.stype }" id="pagestype">
			<select id="pagelist" class="form-control" onchange="clickOption(this)" style="width:200px;">
				<s:iterator value="dto.pages" var="p">
				  <option value ="${p.id}"  <s:if test='dto.pageid == #p.id'> selected="selected" </s:if> >${p.name }</option>
				</s:iterator>  
			</select>
				<ul id="list" style="width:230px;padding-top:15px;overflow-y:auto;">
				<s:iterator value="dto.pc" var="pc"  status="st">
				  <li>
					<s:if test="#st.index==0">
						<input type="hidden" id="firstcardid" value="${pc.id }">
						<input type="hidden" id="firstcardcssid" value="${pc.css }">
					</s:if>
					<div class="card-mod clearfix">
						<div id="${pc.id}" class="cardorder" type="${pc.type }" onclick="reloadCard(${pc.id},'${pc.desc }');" desc="${pc.desc }" event="${pc.eventName }">
							<span class="glyphicon glyphicon-th-large"></span><span class="glyphicon glyphicon-edit"></span><span class="cardname">${pc.cardname }</span><span class="glyphicon glyphicon-pencil" title="修改名字"></span>
						</div>
						<s:if test="dto.subtype != null && dto.subtype != ''"><a class="card-oprt" href="javascript:void(0);" title="替换卡片" onclick="updateCard(${pc.id},'${dto.subtype }',true)">替换卡片</a></s:if>
						<s:else><a class="card-oprt" href="javascript:void(0);" onclick="deleteCard(${pc.id},${pageid})" title="删除">删除</a>
							<a class="card-copy"  href="javascript:void(0)" onclick="copyCard(${pc.id})" title="复制">复制</a>
						</s:else>
					</div></li>
				</s:iterator>
				</ul>
				<s:if test='dto.isAddCard == "Y"'>
					<s:if test="dto.subtype==null "><div id="ll" class="addCard" onclick="addNoCard()"><span class="glyphicon glyphicon-plus"></span>新增卡片</div></s:if>
				</s:if>
				<s:else>
					<s:if test="dto.subtype==null "><div id="ll" class="addCard" onclick="addCard('F',true)"><span class="glyphicon glyphicon-plus"></span>新增卡片</div></s:if>
				</s:else>
		</div>
		<s:if test='dto.page.stype =="P"'>
			<div class="card-edit-phone">
				<div id="field">
					<div class="mCustomScrollbar light" data-mcs-theme="minimal-dark">
				<div id="clearselect"></div>
				<div class="forphone">
					 <div id="card-edit" class="cemin rel" <s:if test="dto.pc.size ==1 && dto.page.bgJson.pageheight>0">style="height:${dto.page.bgJson.pageheight}px;background:${dto.page.bgJson.background} repeat;"</s:if>></div>
				</div>
				<div class="phone-bg">
					<div class="phone-frame">
						<div class="phone-top"></div>
						<div class="phone-left"></div>
						<div class="phone-right"></div>
						<div class="phone-bottom"></div>
						<div class="page-set">
							<a href="javascript:void(0);" onclick="showBlk()" title="恢复隐藏"><img src="/images/rst-eye.png" /></a>
							<a href="javascript:void(0);" onclick="bgset()" title="背景设置"><img src="/images/bg_color.png" /></a>
							<a href="javascript:void(0);" onclick="showGrid()" title="显示网格"><img src="/images/igrid.png" /></a>
						</div>
					</div>
				</div>
			</div>
			</div>
			<div class="edit-right">
				<div class="element-set" style="margin-top:10px;">
					<h4>组件</h4>
					<ul id="zujian" class="clearfix"></ul>
				</div>
				<div style="margin-top:30px;">
					<h4>翻页效果</h4>
					<select id="card_event" onchange="changeEvent()" class="form-control" style="margin-top:15px;">
						<option value="move">移入</option>
						<option value="flip">翻转</option>
						<option value="newspaper">报纸</option>
						<option value="push">推拉</option>
						<option value="fold">折叠</option>
					</select>
				</div>
				<div class="edit_weizhi" style="margin-top:30px;">
						<h4>组件位置</h4>
						<div style="margin-top:15px;">
							<input type="hidden" id="hy_data">
							<div>
							<div style="float:left;margin-left:20px;">左<input type="text" id="weizhi_left" class="text-fix form-control" style="width:50px;" oninput="editStyle(this,1)" /></div> 
							<div style="margin-left:120px;">上 <input type="text" id="weizhi_top" class="text-fix form-control" style="width:50px;"  oninput="editStyle(this,2)" /></div>
							</div>
							<div style="margin-top:10px;">
							<div style="float:left;margin-left:20px;">宽<input type="text" id="weizhi_width" class="text-fix form-control" style="width:50px;"  oninput="editStyle(this,3)" /></div>
							<div style="margin-left:120px;">高<input type="text" id="weizhi_height" class="text-fix form-control" style="width:50px;"  oninput="editStyle(this,4)" /></div>
							</div>
						</div>
				</div>
			</div>
		</s:if>
		<s:else>
			<div class="card-edit-pc">
				<div class="forpc rel">
				<div class="page-set-pc">
						<a href="javascript:void(0);" onclick="showBlk()" title="恢复隐藏"><img src="/images/rst-eye.png" /></a>
					</div>
				<div id="card-edit" style="zoom:53.5%;overflow:hidden;width:1280px;height:720px;" class="rel"></div>
				</div>
			</div>
			<div class="edit-right">
				<div class="element-set" style="margin-top:10px;">
					<h4>组件</h4>
					<ul id="zujian" class="clearfix"></ul>
				</div>
				<div class="edit_weizhi" style="margin-top:30px;">
						<h4>组件位置</h4>
						<div style="margin-top:15px;">
							<input type="hidden" id="hy_data">
							<div>
							<div style="float:left;margin-left:20px;">左<input type="text" id="weizhi_left" class="text-fix form-control" style="width:50px;" oninput="editStyle(this,1)" /></div> 
							<div style="margin-left:120px;">上 <input type="text" id="weizhi_top" class="text-fix form-control" style="width:50px;"  oninput="editStyle(this,2)" /></div>
							</div>
							<div style="margin-top:10px;">
							<div style="float:left;margin-left:20px;">宽<input type="text" id="weizhi_width" class="text-fix form-control" style="width:50px;"  oninput="editStyle(this,3)" /></div>
							<div style="margin-left:120px;">高<input type="text" id="weizhi_height" class="text-fix form-control" style="width:50px;"  oninput="editStyle(this,4)" /></div>
							</div>
						</div>
				</div>
			</div>
		</s:else>
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
				
				function editStyle(arg,num) {
					var hd=$("#hy_data").val();
					if(hd != "" && hd != null){
						if(num == 1){
							$("[hydata="+hd+"]").css("left",arg.value);
						}else if(num == 2){
							$("[hydata="+hd+"]").css("top",arg.value);
						}else if(num == 3){
							$("[hydata="+hd+"]").css("width",arg.value);
						}else{
							$("[hydata="+hd+"]").css("height",arg.value);
						}
					}
				};
				
			</script>
		</div>
<!-- Modal -->
<div id="normalModal" class="modal fade bs-example-modal-lg" role="dialog" aria-hidden="true" style="z-index:99999">
  <div class="modal-dialog">
    <div class="modal-content">
    </div>
  </div>
</div>
<div id="wideModal" class="modal fade bs-example-modal-lg" role="dialog" aria-hidden="true" style="z-index:99999">
  <div class="modal-dialog" style="width:900px;">
    <div class="modal-content">
    </div>
  </div>
</div>
<div id="myModal1" class="modal fade bs-example-modal-lg" role="dialog" aria-hidden="true" style="z-index:99999">
	<div class="modal-dialog" style="width:400px;">
			<div class="modal-content">
			</div>
	</div>
</div>
<div id="sckModal" class="modal fade bs-example-modal-lg" role="dialog" aria-hidden="true" style="z-index:99999">
	<div class="modal-dialog" style="width:800px;">
			<div class="modal-content">
			</div>
	</div>
</div>
<div id="prizeModal" class="modal fade bs-example-modal-lg" role="dialog" aria-hidden="true" style="z-index:99999">
	<div class="modal-dialog" style="width:850px;">
			<div class="modal-content">
			</div>
	</div>
</div>
<div id="rightPopup">
	<div class="popup-content"></div>
</div>
<script type="text/javascript">
	var id = '<s:property value="dto.pc.size"/>';
	if(id>0){
		$("#zujian").load("/${oname}/page/zujian.action");
	}else{
		$(".edit-right").hide();
		$("#card-edit").html("<p style='font-size:18px;font-weight:bold;padding:30px;color:#5B769F;'>请点击左侧“新增卡片”按钮添加你所需要的卡片类型</p>");
	}

	
	setMinHeight();

	$(window).resize(function() {
		setMinHeight();
	});
	
	function setMinHeight(){
		var W = $(window).width()
		var H = $(window).height();
			$(".popup-body").height(H-230);
			$(".edit-right").height(H-70);
			$(".card-edit-phone").width(W-480);
		var lh = $("#list").height();
		if (lh > (H-160)){
			$("#list").css("height",H-160);
		}
	}
	
</script>
