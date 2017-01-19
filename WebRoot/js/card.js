$(function() {
$("#list").sortable({ 
	helper: "clone",
	axis: "y",
	update: function saveOrder() {
	var order = $(".cardorder");
	var str = "";
	for(var i= 0;i<order.length;i++){
		str += order[i].id +"," +(i+1) + ";";
	}
	$.post("moveCard.action",{"cardMoveStr":str});
}
});
});


var minIndex=100;
var maxIndex=100;
function reloadCard(pcid,desc){
	$("#rightPopup").animate({width:'hide'});
	if(pcid != 0){
		$(".cardorder").attr("class","cardorder");
		$("#"+pcid).addClass("editing");
		$("#currentCard").val(pcid);
		$("#card_event").val($("#"+pcid).attr("event"));
		$('#card-edit').load('/'+$("#oname").val()+'/user/showCard.action',{'pcid':pcid,'pageid':$("#pageid").val(),'method':'E'},function(){
//			if(desc == "Y"){
				block_drag_click();
//			}else {
//				block_2_click();
//			}
				setMinHeight();
			$("#clearselect").click(function(){
				$("#card-edit .block,#card-edit .hyzj").removeClass("block-editing");
			    $("#weizhi_width").val("");
				$("#weizhi_height").val("");
			    $("#weizhi_left").val("");
				$("#weizhi_top").val("");
			});
			minIndex=100;
			maxIndex=100;
			$("#card-edit .block, #card-edit .hyzj").each(function(){
				var thisindex = $(this).css("z-index");
				if (thisindex == "auto"){
					$(this).css("z-index",100);
					thisindex = 100;
				}
				if ( thisindex < minIndex ){
					minIndex = thisindex;
				} else if ( thisindex > maxIndex ){
					maxIndex = thisindex;
				}
			});
			
			var lis = $("#list li").length;
			if(lis == 1){
				$(".phone-bottom").html('<a id="pageHeight" href="javascript:void(0);"><span class="glyphicon glyphicon-arrow-down"></span> 增加高度</a>');
				$( "#pageHeight" ).click(function(){
					var ffH = $("#card-edit").height();
					if(ffH <= 500){
						bootbox.confirm("当需要使用到长页面时，使用此功能可每次增加一个页面的高度，可随需要继续增加便于排版，最终页面高度将会自动计算。但是卡片背景功能将失效，如需使用背景请使用页面背景。是否继续使用此功能？", function(result) {
							if(result == true){
								var getHeight = $("#card-edit").height();
								$("#card-edit").height(getHeight+500);
								$.post("pageheight.action",{"pageid":$("#pageid").val(),"pageheight":getHeight+500});
								$.post("savePageCardStyle.action",{"cardid":pcid,"bg":" "});
								$("#card-edit > div:first").remove();
							} 
						}); 
					}  else {
						var getHeight = $("#card-edit").height();
						$("#card-edit").height(getHeight+500);
						$.post("pageheight.action",{"pageid":$("#pageid").val(),"pageheight":getHeight+500});
					}
				})
				/*var getHeight = $(".forphone").height();
				$("#card-edit").append("<div id='pageHeight' class='ctrlLine' title='调整页面高度'><span id='heightText'>500px</span></div>");
				$("#pageHeight").css("top",getHeight-22);
				$("#heightText").text(getHeight+"px");
				$( "#pageHeight" ).draggable({ 
					axis: "y",
					drag: function() {
						var reHeight =  parseInt($("#pageHeight").css("top"));
						$(".forphone").height(reHeight+22);
						$("#heightText").text((reHeight+22)+"px");
						if(reHeight > 480){
							$("#mCSB_1_container").css("top",480-reHeight);
						}
				    },
				    stop: function(){
				    	var reHeight =  parseInt($("#pageHeight").css("top"));
				    	if(reHeight < 478){
				    		$("#pageHeight").css("top",478);
				    		$(".forphone").height(500);
							$("#heightText").text("500px");
							$("#mCSB_1_container").css("top",0);
							reHeight = 500;
						} else {
							$.post("pageheight.action",{"pageid":$("#pageid").val(),"pageheight":reHeight+22});
						}
						
				    }
			    });*/
			}
		});
	}
}


function deleteCard(pcid,pageid){
	bootbox.confirm("确认删除？", function(result) {
		if(result == true){
		$.post("deleteCard.action",{"pcid":pcid,"pageid":pageid},function(data){
			if(data==1){
				$("#"+pcid).parent().remove();
				window.location.href="editpage.action?pageid=" + pageid;
			}else{
				bootbox.alert("删除失败！请重试…");
			}
		});
		}
	}); 
}

function addCard(type,backdrop){
	var lis = $("#list li").length;
	var ffH = $("#card-edit").height();
	var pageid= $("#pageid").val();
	var order = $(".cardorder");
	var str = "";
	for(var i= 0;i<order.length;i++){
		var aa = order[i].getAttribute("type");
		if(aa == "S" || aa.startWith("D") || aa == "K"){
			type = aa;
			break;
		}
	}
	if(lis == 1 && ffH > 500){
		bootbox.confirm("您已将第一个卡片设置为长页面，如果继续增加卡片，会将所有卡片取消高度并设置为滑动式，是否继续添加？", function(result) {
			if(result == true){
				$('#wideModal').modal({
					backdrop:backdrop,
					remote:"addCard.action?pageid="+pageid+"&type=" + type
				});
			} 
		}); 
	}  else {
		$('#wideModal').modal({
			backdrop:backdrop,
			remote:"addCard.action?pageid="+pageid+"&type=" + type
		});
	}
}

function updateCard(pcid,subtype,backdrop){
	var pageid= $("#pageid").val();
	$('#wideModal').modal({
		backdrop:backdrop,
		remote:"updateCard.action?pageid="+pageid+"&subtype=" + subtype+"&pcid=" + pcid
	});
}

$(document).ready(function(){
	$("#normalModal,#wideModal,#myModal1,#sckModal,#chctModal").on("hidden.bs.modal", function() {
	    $(this).removeData("bs.modal");
	});
	$("#normalModal,#wideModal,#myModal1,#sckModal,#chctModal").on("hidden.bs.modal", function() {
		 $(".colpick").remove();
	})
})

//function block_2_click(){
//	$("#card-edit .block").hover(
//     function(){
//	    $(this).addClass("rel").append("<div class='block-modal'></div>");
//	    $(this).find(".ui-resizable-handle").css("opacity","1");
//	  },
//     function(){
//     	$(this).removeClass("rel");
//	    $(".block-modal").remove();
//	    $(this).find(".ui-resizable-handle").css("opacity","0");
//	  }
//	 );
//	$("#card-edit .hyzj").hover(
//		     function(){
//			    $(this).addClass("rel").append("<div class='block-modal'></div>");
//			    $(this).find(".ui-resizable-handle").css("opacity","1");
//			  },
//		     function(){
//		     	$(this).removeClass("rel");
//			    $(".block-modal").remove();
//			    $(this).find(".ui-resizable-handle").css("opacity","0");
//			  }
//			 );
//	$("#card-edit .block,#card-edit .hyzj").bind('dblclick', function(){
//			var id = $(this).attr("hydata");
//			if(id >0){
//				$('#rightPopup .popup-content').load("showBlock.action?relationid="+id+"&pageid="+$("#pageid").val());
//				$('#rightPopup').animate({width: 'show'});
//				setRightPopMinHeight();
//			}
//		});
//}
function block_drag_click(){
	$("#card-edit .block").attr("tabindex",0).mousedown(function(){
			$("#card-edit .block, #card-edit .hyzj").removeClass("block-editing");
			$(this).focus().addClass("block-editing");
		    $("#hy_data").val($(this).attr("hydata"));
	    if($(this).attr("status")==0){
		    $(this).attr("status","1").append("<div class='block-d-modal'></div>");
	    }
	    $("#weizhi_width").val($(this).css("width"));
		$("#weizhi_height").val($(this).css("height"));
	    $("#weizhi_left").val($(this).css("left"));
		$("#weizhi_top").val($(this).css("top"));
	}).keydown(function(event){
		keyDelZJ(0,event,$(this),$(this).attr("hydata"));
	}).keyup(function(event){
		if (event.which >= 37 && event.which <= 40) {
			click2save();
		}
	}).contextPopup({
		 items: [
		    {label:'编辑', action:function() { editBlk(); }},
			{label:'占满一行', action:function() { $(".block-editing").css({"width":320,"left":0,"padding":0});click2save();}},
			null,
	        {label:'置于顶层', action:function() { maxIndex = parseInt(maxIndex) + 1; $(".block-editing").css("z-index",maxIndex); click2save();}},
			{label:'置于底层', action:function() { minIndex = parseInt(minIndex) - 1; $(".block-editing").css("z-index",minIndex); click2save();}},
	        null,
			{label:'隐藏', action:function() { var hydata = $(".block-editing").attr("hydata"); hiddenBlk(hydata); }},
			]
	});
	$("#card-edit .hyzj").attr("tabindex",0).mousedown(function(){
			$("#card-edit .block, #card-edit .hyzj").removeClass("block-editing");
			$(this).focus().addClass("block-editing");
		    $("#hy_data").val($(this).attr("hydata"));
	    if($(this).attr("status")==0){
		    $(this).attr("status","1").append("<div class='block-d-modal'></div>");
	    }
	    $("#weizhi_width").val($(this).css("width"));
		$("#weizhi_height").val($(this).css("height"));
	    $("#weizhi_left").val($(this).css("left"));
		$("#weizhi_top").val($(this).css("top"));
	}).keydown(function(event){
		keyDelZJ(1,event,$(this),$(this).attr("hydata"));
	}).keyup(function(event){
		if (event.which >= 37 && event.which <= 40) {
			click2save();
		}
	}).contextPopup({
		 items: [
	         	{label:'编辑', action:function() { editBlk(); }},
		        {label:'占满一行', action:function() { $(".block-editing").css({"width":320,"left":0});click2save();}},
		        null,
				{label:'置于顶层', action:function() { maxIndex = parseInt(maxIndex) + 1; $(".block-editing").css("z-index",maxIndex); click2save();}},
				{label:'置于底层', action:function() { minIndex = parseInt(minIndex) - 1; $(".block-editing").css("z-index",minIndex); click2save();}},
				null,
				{label:'删除', action:function() { var hydata = $(".block-editing").attr("hydata"); deleteBlk(hydata);}},
				]
		});
	$("#card-edit .block,#card-edit .hyzj").bind('dblclick', function(){
		var id = $(this).attr("hydata");
		if(id >0){
			$('#rightPopup .popup-content').load("showBlock.action?relationid="+id+"&pageid="+$("#pageid").val());
			$('#rightPopup').animate({width: 'show'});
		}
	}).draggable({
		containment: "#mCSB_1_container",
		drag: function() {
			$("#weizhi_left").val($(this).css("left"));
			$("#weizhi_top").val($(this).css("top"));
	    },
	    stop:function(){
	    	click2save();
	    }
	}).resizable({
		handles: "n, e, s, w, sw, se, nw, ne",
		resize: function(){
			$("#weizhi_width").val($(this).css("width"));
			$("#weizhi_height").val($(this).css("height"));
		},
		stop:function(){
			click2save();
	    }
	});
}

function editBlk(){
	var id = $(".block-editing").attr("hydata");
	if(id >0){
		$('#rightPopup .popup-content').load("showBlock.action?relationid="+id+"&pageid="+$("#pageid").val());
		$('#rightPopup').animate({width: 'show'});
	}
}
function keyDelZJ(danger,event,obj,data){
	var ct_top = obj.position().top;
	var ct_left = obj.position().left;
	if(event.which == 46){
		if (danger == 1){
			deleteBlk2(data);
		}
	}else if (event.which == 37) {
		obj.css("left",ct_left-1);
	}else if (event.which == 38) {
		obj.css("top",ct_top-1);
	}else if (event.which == 39) {
		obj.css("left",ct_left+1);
	}else if (event.which == 40) {
		obj.css("top",ct_top+1);
	}
	$("#weizhi_left").val(obj.css("left"));
	$("#weizhi_top").val(obj.css("top"));
}

function deleteBlk(relationid){
	if(relationid > 0){
		bootbox.confirm("确认删除吗？", function(result) {
		if(result == true){
			$.post("delblk.action",{"relationid":relationid},function(data){
				if(data == 1){
					bootbox.alert("删除失败,请重试!",0);
				}else{
					var pcid = $('#currentCard').val();
					var desc = $('#'+pcid).attr("desc");
					reloadCard(pcid,desc);
				}
			});
		}
		});
	} 
}

function deleteBlk2(relationid){
	if(relationid > 0){
		$.post("delblk.action",{"relationid":relationid},function(data){
			if(data == 1){
				bootbox.alert("删除失败,请重试!",0);
			}else{
				var pcid = $('#currentCard').val();
				var desc = $('#'+pcid).attr("desc");
				reloadCard(pcid,desc);
			}
		});
	} 
}

function hiddenBlk(relationid){
	if(relationid > 0){
		bootbox.confirm("确认隐藏？", function(result) {
		if(result == true){
			$.post("hiddenblk.action",{"relationid":relationid},function(data){
				if(data == 1){
					bootbox.alert("隐藏失败,请重试!",0);
				}else{
					var pcid = $('#currentCard').val();
					var desc = $('#'+pcid).attr("desc");
					reloadCard(pcid,desc);
				}
			});
		}
		});
	} 
}

function toPageProcess(backdrop){
	var pageid= $("#pageid").val();
	$('#wideModal').modal({
		backdrop:backdrop,
		remote:"pageProcess.action?pageid="+pageid
	});
}
function editCard(pcid,pageid){
		window.location.href="editpage.action?pageid=" + pageid+"&pcid="+pcid;
}

$(function editname() {
	$(".glyphicon-pencil").on('click', function(){
		 var inid = $(this).parent().attr("id");
        var inobj = $("#"+inid+" .cardname")
        var inval = inobj.text();
        
        $("#"+inid+" .glyphicon-pencil").addClass("hidden");
        inobj.html("<input class='insideEdit' type='text' maxlength='8' id='edit"+inid+"' value='"+inval+"'>");
        
        $("#edit"+inid).val("").focus().val(inval).live("blur",function() {
            var editval = $(this).val();
            if( editval == ""){
             	editval = inval;
             	$(this).parent().html(inval);
             	$("#"+inid+" a").show();
            }else {
	            $(this).parent().html(editval);
	            $("#"+inid+" a").show();
	           	$.post("updateCardName.action",{"inajax":1,"name":editval,"pcid":inid},function(data){});
			}
			$("#"+inid+" .glyphicon-pencil").removeClass("hidden");
        })
	})
})

function bgset(){
	var id = $("#cardid").val();
	var lis = $("#list li").length;
	var ffH = $("#card-edit").height();
	if(id>0){
		if(lis == 1 && ffH > 500){
			bootbox.alert("此卡片已设为长页面，请使用页面背景为该卡片设置背景。");
		}  else {
			$('#normalModal').modal({
				backdrop:'static',
				remote:"bgset.action?pcid="+id
			});
		}
	}
}

function dhset(){
	var pageid = $("#pageid").val();
	if(id>0){
		$('#normalModal').modal({
			backdrop:'static',
			remote:"set_source_to_page.action?pageid="+pageid
		});
	}
}

function savePageCardCss(){
	var id = $("#cardid").val();
	if(id>0){
		$('#normalModal').modal({
			backdrop:'static',
			remote:"cssset.action?pcid="+id
		});
	}
}

function btset(){
	$('#normalModal').modal({
		backdrop:'static',
		remote:"btset.action?pageid="+$("#pageid").val()
	});
}

function bjset(){
	$('#normalModal').modal({
		backdrop:'static',
		remote:"bjset.action?pageid="+$("#pageid").val()
	});
}

function saveBt(){
	if(document.getElementById("yanse").checked){
		var uploadArrow;
		if($("#noimg").is(":checked")){
			uploadArrow="";
		}else{
			uploadArrow = $("#uploadArrow").val();
		}
    	$.post("btsetSub.action",
    			{"uploadMusic":$("#uploadMusic").val(),
    			 "musicname":$("#musicname").val(),
    			 "pageid":$("#pageid").val(),
    			 "title":$("#title").val(),
    			 "keywords":$("#keywords").val(),
    			 "description":$("#description").val(),
    			 "bg":$("#picker").val(),
    			 "uploadArrow":uploadArrow,
    			 "tagJson.tag1":$("input:text[name='tagJson.tag1']").eq(0).val(),
    			 "tagJson.tag2":$("input:text[name='tagJson.tag2']").eq(0).val(),
    			 "tagJson.tag3":$("input:text[name='tagJson.tag3']").eq(0).val(),
    			 "tagJson.tag4":$("input:text[name='tagJson.tag4']").eq(0).val(),
    			 "tagJson.tag5":$("input:text[name='tagJson.tag5']").eq(0).val()
    			 },
    	function(data){
    		if(data==1){
    			bootbox.alert("保存成功!");
    			window.location.reload();
    		}else{
    			bootbox.alert("保存失败！请重试…");
    		}
    	});
    }
    if(document.getElementById("tupian").checked){
    	$.post("btsetSub.action",{"uploadMusic":$("#uploadMusic").val(),"musicname":$("#musicname").val(),"pageid":$("#pageid").val(),"title":$("#title").val(),"keywords":$("#keywords").val(),"description":$("#description").val(),"bg":$("#var_bg").val(),"uploadArrow":$("#uploadArrow").val()},function(data){
    		if(data==1){
    			bootbox.alert("保存成功!");
    		}else{
    			bootbox.alert("保存失败！请重试…");
    		}
    	});
    }
    if(document.getElementById("wubj").checked){
    	$.post("btsetSub.action",{"uploadMusic":$("#uploadMusic").val(),"musicname":$("#musicname").val(),"pageid":$("#pageid").val(),"title":$("#title").val(),"keywords":$("#keywords").val(),"description":$("#description").val(),"bg":" ","uploadArrow":$("#uploadArrow").val()},function(data){
    		if(data==1){
    			bootbox.alert("保存成功!");
    		}else{
    			bootbox.alert("保存失败！请重试…");
    		}
    	});
    }
   
}

	function switchBG(type,fid,key){
		$(".bgAll").hide();
		if (type=="C"){
			$("#bgClrSlt").show();
			$("#tupian").removeAttr("checked");
			$("#wubj").removeAttr("checked");
			$("#yanse").attr("checked","checked");
		} else if(type=="P") {
			$("#bgImgSlt").show();
			$("#yanse").removeAttr("checked");
			$("#wubj").removeAttr("checked");
			$("#tupian").attr("checked","checked");
			upd_bg.click();
		} else if(type=="M") {
			$("#bgImgSlt").show();
			$("#yanse").removeAttr("checked");
			$("#wubj").removeAttr("checked");
			$("#tupian").attr("checked","checked");
			sckall(fid,key);
		}else{
			$("#yanse").removeAttr("checked");
			$("#tupian").removeAttr("checked");
			$("#wubj").attr("checked","checked");
		}
	}
	
function subform(){
	var pageid = $('#pageid').val();
	var cardid = $('#cardidval').val();
	if(cardid > 0){
		var cardname = $('#'+cardid).attr("cardname");
		var desc = $('#'+cardid).attr("carddesc");
		$.ajax({ 
				cache: true,
				type: "POST",
				url:"addNewCard.action", 
				data:$('#cardform').serialize(),
				async: false, 
				error: function(request) { alert("连接服务器失败!"); }, 
				success: function(data) { 
	        		document.cookie = "currentCard=" +data;
	        		window.location.reload();
				} 
			});
	}
}

function subform1(){
	var pageid = $('#pageid').val();
	var cardid = $('#cardidval').val();
	var cardname = $('#'+cardid).attr("cardname");
	var desc = $('#'+cardid).attr("carddesc");
	$.ajax({ 
			cache: true,
			type: "POST",
			url:"updateOldCard.action", 
			data:$('#cardform1').serialize(),
			async: false, 
			error: function(request) { alert("连接服务器失败!"); }, 
			success: function(data) { 
        		window.location.reload();
			} 
		});
}

function showBlk(){
	var id = $("#cardid").val();
	if(id > 0){
		bootbox.confirm("确认恢复？", function(result) {
		if(result == true){
			$.post("showblk.action",{"pcid":id},function(data){
				if(data == 1){
					bootbox.alert("恢复失败,请重试!",0);
				}else{
					var pcid = $('#currentCard').val();
					var desc = $('#'+pcid).attr("desc");
					reloadCard(pcid,desc);
				}
			});
		}
		}); 
	}
}

function showGrid(){
	var hasgrid = $("#clearselect").hasClass("grid");
	if(hasgrid){
		$("#clearselect").removeClass("grid");
	} else {
		$("#clearselect").addClass("grid");
	}
}

function savePosition(){
	var id = $("#cardid").val();
	var blocks = $("#card-edit .block,#card-edit .hyzj");
	var str = '';
	for(i=0;i<blocks.length;i++){
		str += $(blocks[i]).attr("hydata")+"~hy~"+$(blocks[i]).attr("style")+"~yh~";
	}
	$.post("saveposition.action",{"ps":str},function(data){
		bootbox.alert("保存成功!",0);
	});
	
}


function newAppointment(oname){
	var title='新建申请';
	$.post("/interact/addAppointment.action",{"titlename":title},function(data){
		if(data >0){
			$("#label").html("<input type='radio' value="+data+" name='dto.aptid'>新建申请&nbsp;&nbsp;&nbsp;<a href='/"+oname+"/interact/update_order_pre.action?id="+data+"'>编辑</a>");
		}
	});
}
function newYYY(){
	var title='新建摇一摇';
	var type='Y';
	$.post("/interact/addLottery.action",{"titlename":title,"type":type},function(data){
		if(data >0){
			$("#label").html("<input type='radio' value="+data+" name='dto.aptid'>新建摇一摇&nbsp;&nbsp;&nbsp;<a href='/interact/edit_yyy.action?lid="+data+"&mid=10011'>编辑</a>");
		}
	});
}
function newZJD(){
	var title='新建砸金蛋';
	var type='L';
	$.post("/interact/addLottery.action",{"titlename":title,"type":type},function(data){
		if(data >0){
			$("#label").html("<input type='radio' value="+data+" name='dto.aptid'>新建砸金蛋&nbsp;&nbsp;&nbsp;<a href='/interact/edit_lhj.action?lid="+data+"&mid=10004'>编辑</a>");
		}
	});
}
function newGGL(){
	var title='新增刮刮乐';
	var type='G';
	$.post("/interact/addLottery.action",{"titlename":title,"type":type},function(data){
		if(data >0){
			$("#label").html("<input type='radio' value="+data+" name='dto.aptid'>新建刮刮乐&nbsp;&nbsp;&nbsp;<a href='/interact/edit_ggl.action?lid="+data+"&mid=10005'>编辑</a>");
		}
	});
}
function newDZP(){
	var title='新增大转盘';
	var type='Z';
	$.post("/interact/addLottery.action",{"titlename":title,"type":type},function(data){
		if(data >0){
			$("#label").html("<input type='radio' value="+data+" name='dto.aptid'>新建大转盘&nbsp;&nbsp;&nbsp;<a href='/interact/edit_zhuanpan.action?lid="+data+"&mid=10003'>编辑</a>");
		}
	});
}
function newResearch(){
	var title='新建调研';
	$.post("/interact/addResearch.action",{"titlename":title},function(data){
		if(data >0){
			$("#label").html("<input type='radio' value="+data+" name='dto.aptid'>新建调研&nbsp;&nbsp;&nbsp;<a href='/interact/to_update_research_design.action?researchid="+data+"'>编辑</a>");
		}
	});
}
function newVote(){
	var title='新建投票';
	$.post("/interact/addVote.action",{"titlename":title},function(data){
		if(data >0){
			$("#label").html("<input type='radio' value="+data+" name='dto.aptid'>新建投票&nbsp;&nbsp;&nbsp;<a href='/interact/to_update_vote_design.action?voteid="+data+"'>编辑</a>");
		}
	});
}
function newSpread(){
	var title='新建口碑';
	$.post("/interact/addSpread.action",{"titlename":title},function(data){
		if(data >0){
			$("#label").html("<input type='radio' value="+data+" name='dto.aptid'>新建口碑&nbsp;&nbsp;&nbsp;<a href='/interact/to_update_spread_design.action?spreadid="+data+"'>编辑</a>");
		}
	});
}
function newAuction(){
	var title='新建竞拍';
	$.post("/interact/addAuction.action",{"titlename":title},function(data){
		if(data >0){
			$("#label").html("<input type='radio' value="+data+" name='dto.aptid'>新建竞拍&nbsp;&nbsp;&nbsp;<a href='/interact/editAuction.action?auid="+data+"&mid=10007'>编辑</a>");
		}
	});
}

function changeEvent(){
	var id = $("#currentCard").val();
	var eventName = $("#card_event").val();
	if(id > 0){
		$.post("changeEvent.action",{"pcid":id,"eventName":eventName},function(data){
			if(data == 1){
				$("#"+id).attr("event",eventName);
			}
		});
	}
}

function copyCard(pcid){
	bootbox.confirm("确定要复制该卡片吗？", function(result) {
		if(result == true){
			$.post("copycard.action",{"pcid":pcid},function(data){
				if(data > 0){
					window.location.reload();
				}else{
					bootbox.alert("复制失败！请重试…");
				}
			});
		}
	}); 
}

function sourceset(pageid){
	$('#normalModal').modal({
		backdrop:'static',
		remote:"sourceset.action?pageid="+pageid
	});
}

function click2save(){
	var hydata = $(".block-editing").attr("hydata");
	var ct_delay=$(".block-editing").css("animation-delay");
	var ct_duration=$(".block-editing").css("animation-duration");
	var ct_width=$(".block-editing").css("width");
	var ct_height=$(".block-editing").css("height");
	var ct_top=$(".block-editing").css("top");
	var ct_left=$(".block-editing").css("left");
	var ct_index=$(".block-editing").css("z-index");
	var style= "position:absolute;width:"+ct_width+";height:"+ct_height+";left:"+ct_left+";top:"+ct_top+";z-index:"+ct_index+";animation-delay:"+ct_delay+";animation-duration:"+ct_duration+";-webkit-animation-delay:"+ct_delay+";-webkit-animation-duration:"+ct_duration+";";
	$.post("saveposition.action",{"ps":hydata+"~hy~"+style });
}
