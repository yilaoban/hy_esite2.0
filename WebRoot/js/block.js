String.prototype.endWith=function(s){
  if(s==null||s==""||this.length==0||s.length>this.length)
     return false;
  if(this.substring(this.length-s.length)==s)
     return true;
  else
     return false;
  return true;
 }

String.prototype.startWith=function(s){
 if(s==null||s==""||this.length==0||s.length>this.length)
  return false;
 if(this.substr(0,s.length)==s)
    return true;
 else
    return false;
 return true;
}


$.fn.serializeObject = function()    
{    
   var o = {};    
   var a = this.serializeArray();    
   $.each(a, function() {    
       if (o[this.name]) {    
           if (!o[this.name].push) {    
               o[this.name] = [o[this.name]];    
           }    
           o[this.name].push(this.value || '');    
       } else {    
           o[this.name] = this.value || '';    
       }    
   });    
   return o;    
};  
  
function onClik(cardid){  
		 var relationid=$("#relationid").val();
        $(".hylink").attr("disabled",false);
       	var ct_width=$("[hydata='"+relationid+"']").css("width");
       	var ct_height=$("[hydata='"+relationid+"']").css("height");
       	var ct_top=$("[hydata='"+relationid+"']").css("top");
       	var ct_left=$("[hydata='"+relationid+"']").css("left");
       	var ct_index=$("[hydata='"+relationid+"']").css("z-index");
        if($("#ct_name").length > 0 ){
        	var ct_delay=$("#ct_delay").val();
        	var ct_duration=$("#ct_duration").val();
        	var str ="position:absolute;width:"+ct_width+";height:"+ct_height+";left:"+ct_left+";top:"+ct_top+";animation-delay:"+ct_delay+"s;animation-duration:"+ct_duration+"s;-webkit-animation-delay:"+ct_delay+"s;-webkit-animation-duration:"+ct_duration+"s;z-index:"+ct_index;
        	$("#ct_value").val(str);
        }else{
        	var str ="position:absolute;width:"+ct_width+";height:"+ct_height+";left:"+ct_left+";top:"+ct_top+";z-index:"+ct_index;
        	$("#ct_value").val(str);
        }
        var jsonuserinfo = $('#form1').serializeObject();
        if($("[name='redirect']").length>0){
        	var type = $('input:radio[name="redirect"]:checked').val();
        	var urlPre = $('#urlType1').val();
        	var urlShow = $("#ruletype1").val();
        	var toPageid = $('#toPageid').val();
        	if(type == "Z"){
        		if(urlShow == null || urlShow ==""){
        			alert("请填写指定跳转地址");
        			return;
        		}
        		url = urlPre + $("#ruletype1").val();
        	}else if(type == "R"){
        		urlPre = $('#urlType2').val();
        		urlShow = $("#ruletype2").val();
        		if(urlShow == null || urlShow ==""){
        			alert("请填写跳转地址");
        			return;
        		}
        		url = urlPre + $("#ruletype2").val();
        	}else if(type == "L"){
        		if(toPageid == 0){
        			alert("请选择链接页面");
        			return;	
        		}else{
        			url = '/' + oname + '/user/show/' + toPageid + '/pcn/kv.html';
        		}
        	}
        	var obj = {"redirect":{"type":type,"urlPre":urlPre,"urlShow":urlShow,"toPageid":toPageid,"url":url}};
        	 jsonuserinfo.obj=obj;
        }
        
        var json = JSON.stringify(jsonuserinfo);
        $.post("blockSub.action",{"relationid":relationid,"json":json},function(data){
        	if(data == 1){
        		$("#rightPopup").animate({width:'hide'});
        		var desc = $('#'+cardid).attr("desc");
        		reloadCard(cardid,desc);
        	}else{
        		bootbox.alert("保存失败！请重试…");
        	}
        });
}

function onClik2(cardid){
	var relationid=$("#relationid").val();
	var style = $("[hydata='"+relationid+"']").attr("style");
	$("#ct_value").val(style);
	scjson();
	var jsonuserinfo = $('#form1').serializeObject();
	var json2 = JSON.stringify(jsonuserinfo);
    var relationid=$("#relationid").val();
    $.post("blockSubC.action",{"relationid":relationid,"json":json2},function(data){
    	if(data == 1){
    		bootbox.alert("保存成功！您可以继续操作");
    		var desc = $('#'+cardid).attr("desc");
    		reloadCard(cardid,desc);
    	}else{
    		bootbox.alert("保存失败！请重试…");
    	}
   });
}

function onClikSave(cardid){
	if(document.getElementById("yanse").checked){
		var picker=$("#picker").val();
		$.post("savePageCardStyle.action",{"cardid":cardid,"bg":picker},function(data){
        	if(data == 1){
        		var desc = $('#'+cardid).attr("desc");
        		reloadCard(cardid,desc);
        	}else{
        		bootbox.alert("保存失败！请重试…");
        	}
        });
	}
	if(document.getElementById("tupian").checked){
		var imgurl=$("#var_bg").val();
		$.post("savePageCardStyle.action",{"cardid":cardid,"bg":imgurl},function(data){
        	if(data == 1){
        		var desc = $('#'+cardid).attr("desc");
        		reloadCard(cardid,desc);
        	}else{
        		bootbox.alert("保存失败！请重试…");
        	}
        });
	}
	if(document.getElementById("wubj").checked){
		$.post("savePageCardStyle.action",{"cardid":cardid,"bg":" "},function(data){
        	if(data == 1){
        		var desc = $('#'+cardid).attr("desc");
        		reloadCard(cardid,desc);
        	}else{
        		bootbox.alert("保存失败！请重试…");
        	}
        });
	}
}

function onClikSaveCss(cardid){
	var cardcss=$("#cardcss").val();
	$.post("savePageCardCss.action",{"cardid":cardid,"css":cardcss},function(data){
       	if(data == 1){
       		var desc = $('#'+cardid).attr("desc");
       		reloadCard(cardid,desc,cardcss);
       	}else{
       		bootbox.alert("保存失败！请重试…");
       	}
       });
}

function onClik1(fid,featureid,relationid,utype,pageid,oname){
	var obj = $('input:radio[name="dto.aptid"]:checked');
	var aptid = obj.val();
	var name = obj.attr("namevalue");
	document.getElementById("name").value = name; 
	var cardid = $("#cardid").val();
	var type = $('input:radio[name="dto.redirect.type"]:checked').val();
	var url = null;
	var urlPre = null;
	var urlShow = null;
	var toPageid = 0;
	var color = $("#var_cfont_color").val();
	var size = $("#var_sefont_size").val();
	var bottom = $("#var_bottom_size").val();
	
	var btcontent = $("#var_bt_content").val();
	var btcolor = $("#var_bt_font_color").val();
	var btsize = $("#var_bt_font_size").val();
	var btbg = $("#var_bt_bg").val();
	var bttm = $("#var_bt_tm").val();  //按钮透明度
	if(type == "Z"){
		urlPre = $('#urlType1').val();
		urlShow = $("#ruletype1").val();
		if(urlShow == null || urlShow ==""){
			alert("请填写指定跳转地址");
			return false;
		}
		url = urlPre + $("#ruletype1").val();
	}else if(type == "R"){
		urlPre = $('#urlType2').val();
		urlShow = $("#ruletype2").val();
		if(urlShow == null || urlShow ==""){
			alert("请填写跳转地址");
			return false;
		}
		url = urlPre + $("#ruletype2").val();
	}else if(type == "L"){
		toPageid = $('#toPageid').val();
		if(toPageid == 0){
			alert("请选择链接页面");
			return false;	
		}else{
			url = '/' + oname + '/user/show/' + toPageid + '.html';
		}
	}
	$.post("config_sub_new.action",{"dto.relationid":relationid,"dto.fid":fid,"featureid":featureid,"dto.aptid":aptid,"dto.name":name,"dto.redirect.type":type,"dto.redirect.url":url,"dto.redirect.urlShow":urlShow,"dto.redirect.urlPre":urlPre,"dto.type":utype,"dto.pageid":pageid,"dto.redirect.toPageid":toPageid,"dto.style118.size":size,"dto.style118.color":color,"dto.style118.bottom":bottom,"dto.style118.btcontent":btcontent,"dto.style118.btcolor":btcolor,"dto.style118.btsize":btsize,"dto.style118.btbg":btbg,"dto.style118.bttm":bttm},function(data){
        	if(data == "Y"){
        		$("#rightPopup").animate({width:'hide'});
        		var desc = $('#'+cardid).attr("desc");
        		reloadCard(cardid,desc);
        	}else{
        		bootbox.alert("保存失败！请重试…");
        	}
        });
//	form.submit();
}

function onClikResearch(fid,featureid,relationid,utype,pageid,oname){
	var obj = $('input:radio[name="dto.researchid"]:checked');
	var researchid = obj.val();
	var cardid = $("#cardid").val();
	var type = $('input:radio[name="dto.redirect.type"]:checked').val();
	var url = null;
	var urlPre = null;
	var urlShow = null;
	var toPageid = 0;
	if(type == "Z"){
		urlPre = $('#urlType1').val();
		urlShow = $("#ruletype1").val();
		if(urlShow == null || urlShow ==""){
			alert("请填写指定跳转地址");
			return false;
		}
		url = urlPre + $("#ruletype1").val();
	}else if(type == "R"){
		urlPre = $('#urlType2').val();
		urlShow = $("#ruletype2").val();
		if(urlShow == null || urlShow ==""){
			alert("请填写跳转地址");
			return false;
		}
		url = urlPre + $("#ruletype2").val();
	}else if(type == "L"){
		toPageid = $('#toPageid').val();
		if(toPageid == 0){
			alert("请选择链接页面");
			return false;	
		}else{
			url = '/' + oname + '/user/show/' + toPageid + '.html';
		}
	}
	$.post("config_sub_new.action",{"dto.relationid":relationid,"dto.fid":fid,"featureid":featureid,"dto.researchid":researchid,"dto.redirect.type":type,"dto.redirect.url":url,"dto.redirect.urlShow":urlShow,"dto.redirect.urlPre":urlPre,"dto.type":utype,"dto.pageid":pageid,"dto.redirect.toPageid":toPageid},function(data){
        	if(data == "Y"){
        		$("#rightPopup").animate({width:'hide'});
        		var desc = $('#'+cardid).attr("desc");
        		reloadCard(cardid,desc);
        	}else{
        		bootbox.alert("保存失败！请重试…");
        	}
        });
//	form.submit();
}


function onClikExam(fid,featureid,relationid,utype,pageid,oname){
	var obj = $('input:radio[name="dto.examid"]:checked');
	var examid = obj.val();
	var cardid = $("#cardid").val();
	var type = $('input:radio[name="dto.redirect.type"]:checked').val();
	var url = null;
	var urlPre = null;
	var urlShow = null;
	var toPageid = 0;
	if(type == "Z"){
		urlPre = $('#urlType1').val();
		urlShow = $("#ruletype1").val();
		if(urlShow == null || urlShow ==""){
			alert("请填写指定跳转地址");
			return false;
		}
		url = urlPre + $("#ruletype1").val();
	}else if(type == "R"){
		urlPre = $('#urlType2').val();
		urlShow = $("#ruletype2").val();
		if(urlShow == null || urlShow ==""){
			alert("请填写跳转地址");
			return false;
		}
		url = urlPre + $("#ruletype2").val();
	}else if(type == "L"){
		toPageid = $('#toPageid').val();
		if(toPageid == 0){
			alert("请选择链接页面");
			return false;	
		}else{
			url = '/' + oname + '/user/show/' + toPageid + '.html';
		}
	}
	$.post("config_sub_new.action",{"dto.relationid":relationid,"dto.fid":fid,"featureid":featureid,"dto.examid":examid,"dto.redirect.type":type,"dto.redirect.url":url,"dto.redirect.urlShow":urlShow,"dto.redirect.urlPre":urlPre,"dto.type":utype,"dto.pageid":pageid,"dto.redirect.toPageid":toPageid},function(data){
        	if(data == "Y"){
        		$("#rightPopup").animate({width:'hide'});
        		var desc = $('#'+cardid).attr("desc");
        		reloadCard(cardid,desc);
        	}else{
        		bootbox.alert("保存失败！请重试…");
        	}
        });
//	form.submit();
}

function onclickNewList(fid,featureid,relationid,type,pageid){
	var obj = $('input:radio[name="dto.ccid"]:checked');
	var ccid = obj.val();
	var cardid = $("#cardid").val();
	$.post("config_sub_new.action",{"dto.relationid":relationid,"dto.fid":fid,"featureid":featureid,"dto.ccid":ccid,"dto.type":type,"dto.pageid":pageid},function(data){
        	if(data == "Y"){
        		$("#rightPopup").animate({width:'hide'});
        		var desc = $('#'+cardid).attr("desc");
        		reloadCard(cardid,desc);
        	}else{
        		bootbox.alert("保存失败！请重试…");
        	}
        });
}

function onClikVote(fid,featureid,relationid,utype,pageid,oname){
	var obj = $('input:radio[name="dto.voteid"]:checked');
	var voteid = obj.val();
	if(voteid > 0){
		var count = obj.attr("count");
		var start = $('#start').val();
		var end = $('#end').val();
		var cardid = $("#cardid").val();
		var type = $('input:radio[name="dto.redirect.type"]:checked').val();
		var url = null;
		var urlPre = null;
		var urlShow = null;
		var toPageid = 0;
		if(type == "Z"){
			urlPre = $('#urlType1').val();
			urlShow = $("#ruletype1").val();
			if(urlShow == null || urlShow ==""){
				alert("请填写指定跳转地址");
				return false;
			}
			url = urlPre + $("#ruletype1").val();
		}else if(type == "R"){
			urlPre = $('#urlType2').val();
			urlShow = $("#ruletype2").val();
			if(urlShow == null || urlShow ==""){
				alert("请填写跳转地址");
				return false;
			}
			url = urlPre + $("#ruletype2").val();
		}else if(type == "L"){
			toPageid = $('#toPageid').val();
			if(toPageid == 0){
				alert("请选择链接页面");
				return false;	
			}else{
				url = '/' + oname + '/user/show/' + toPageid + '.html';
			}
		}
		if(start >= 0 && end >= 0){
			$.post("config_sub_new.action",{"dto.relationid":relationid,"dto.fid":fid,"featureid":featureid,"dto.voteid":voteid,"dto.redirect.type":type,"dto.redirect.url":url,"dto.redirect.urlShow":urlShow,"dto.redirect.urlPre":urlPre,"dto.type":utype,"dto.pageid":pageid,"dto.start":start,"dto.end":end,"dto.redirect.toPageid":toPageid},function(data){
		        	if(data == "Y"){
		        		$("#rightPopup").animate({width:'hide'});
		        		var desc = $('#'+cardid).attr("desc");
		        		reloadCard(cardid,desc);
		        	}else{
		        		bootbox.alert("保存失败！请重试…");
		        	}
		        });
		}else{
			alert("请填写从第几题到第几题");
		}
	}
}

function onClikAuction(fid,featureid,relationid,type,pageid){
	var obj = $('input:radio[name="dto.auctionid"]:checked');
	var auctionid = obj.val();
	var cardid = $("#cardid").val();
	$.post("config_sub_new.action",{"dto.relationid":relationid,"dto.fid":fid,"featureid":featureid,"dto.auctionid":auctionid,"dto.type":type,"dto.pageid":pageid},function(data){
        	if(data == "Y"){
        		$(".modal-backdrop").remove();
        		var desc = $('#'+cardid).attr("desc");
        		reloadCard(cardid,desc);
        	}else{
        		bootbox.alert("保存失败！请重试…");
        	}
        });
}

function onClikLottery(fid,featureid,relationid,utype,pageid,oname) {
	var obj = $('input:radio[name="dto.lotteryid"]:checked');
	var lotteryid = obj.val();
	var cardid = $("#cardid").val();
	var type = $('input:radio[name="dto.redirect.type"]:checked').val();
		var url = null;
		var urlPre = null;
		var furl = null;
		var urlShow = null;
		var furlShow = null;
		var toPageid = 0;
		if(type == "Z"){
			urlPre = $('#urlType1').val();
			urlShow = $("#ruletype1").val();
			furlShow = $("#ruletype2").val();
			if((urlShow == null || urlShow =="") && (furlShow == null || furlShow =="")){
				alert("请填写指定跳转地址");
				return false;
			}
			url = urlPre + urlShow;
			furl = urlPre + furlShow;
		}else if(type == "R"){
			urlPre = $('#urlType2').val();
			urlShow = $("#ruletype2").val();
			if(urlShow == null || urlShow ==""){
				alert("请填写跳转地址");
				return false;
			}
			url = urlPre + $("#ruletype2").val();
		}else if(type == "L"){
			toPageid = $('#toPageid').val();
			if(toPageid == 0){
				alert("请选择链接页面");
				return false;	
			}else{
				url = '/' + oname + '/user/show/' + toPageid + '.html';
			}
		}
	
	$.post("config_sub_new.action",{"dto.relationid":relationid,"dto.fid":fid,"featureid":featureid,"dto.lotteryid":lotteryid,"dto.type":utype,"dto.pageid":pageid,"dto.redirect.type":type,"dto.redirect.url":url,"dto.redirect.furl":furl,"dto.redirect.urlShow":urlShow,"dto.redirect.furlShow":furlShow,"dto.redirect.urlPre":urlPre,"dto.redirect.toPageid":toPageid},function(data){
	       	if(data == "Y"){
	       		$("#rightPopup").animate({width:'hide'});
	       		var desc = $('#'+cardid).attr("desc");
        		reloadCard(cardid,desc);
	       	}else{
	       		bootbox.alert("保存失败！请重试…");
	       	}
       });
}

function onClikSpread(fid,featureid,relationid,type,pageid){
	var obj = $('input:radio[name="dto.spreadid"]:checked');
	var spreadid = obj.val();
	var cardid = $("#cardid").val();
	$.post("config_sub_new.action",{"dto.relationid":relationid,"dto.fid":fid,"featureid":featureid,"dto.spreadid":spreadid,"dto.type":type,"dto.pageid":pageid},function(data){
	       	if(data == "Y"){
	       		$("#rightPopup").animate({width:'hide'});
	       		var desc = $('#'+cardid).attr("desc");
        		reloadCard(cardid,desc);
	       	}else{
	       		bootbox.alert("保存失败！请重试…");
	       	}
       });
}

function onClikForum(fid,featureid,relationid,type,pageid){
	var s = $('input:checkbox[name="dto.forumid"]:checked');
	if(s.length == 0){
		alert("请选择一项");
		return;
	}
	var ids="[";
	for(i= 0;i<s.length;i++){
		if(s[i].checked){
			if(i != s.length - 1){
				ids+=s[i].value+",";
			}else{
				ids+=s[i].value;
			}
		}
	}
	ids += "]";
	var cardid = $("#cardid").val();
	$.post("config_sub_new.action",{"dto.relationid":relationid,"dto.fid":fid,"featureid":featureid,"dto.forumid":ids,"dto.type":type,"dto.pageid":pageid},function(data){
	       	if(data == "Y"){
	       		$("#rightPopup").animate({width:'hide'});
	       		var desc = $('#'+cardid).attr("desc");
        		reloadCard(cardid,desc);
	       	}else{
	       		bootbox.alert("保存失败！请重试…");
	       	}
       });	
}


function edit(id) {
	var editor = KindEditor.create('#'+id, {
      	width : '350px',
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
			'insertunorderedlist', '|', 'emoticons', 'image', 'link','lineheight'],
		afterBlur: function(){this.sync();},
		afterChange: function(){this.sync();},
		afterCreate:function(){this.sync();}
	});
};

function ajaxFileUpload(imgDomain,id){
		var val = $("#upd_"+id).val();
		var idx = val.lastIndexOf(".");
		var result =val.substring(idx,val.length);
		if(result != ".jpg" && result != ".JPG" && result != ".jpeg" && result != ".JPEG" && result != ".bmp" && result != ".BMP" && result != ".png" && result != ".PNG" && result != ".gif" && result != ".GIF"){
			bootbox.alert("文件格式不支持！（仅支持jpg/jpeg/bmp/png等格式）");
			return;
		}
		$.ajaxFileUpload({
				url:'picUpload.action', 
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

var count = 100;

function del_json(index,oname){
	var s = $("#form1 .hy_yuansu").length;
	if(s > 1){
		$("#list_"+index).remove();
		var y = $("#form1 .hy_yuansu");
		var i = y[0].id.substring(5);
		showJson(i,oname);
	}
}

function add_list(oname){
	clearForm('newform');
	var e = $(".celement").length;
	$(".celement").removeClass("on");
	$("#json_list").append("<li class='celement on' id='list_"+(e+1)+"'><input type='hidden' id='json_"+(e+1)+"' class='hy_yuansu' name='list' value='{}'/><a id='xs_"+(e+1)+"' href='javascript:void(0)' onclick='showJson("+(e+1)+",\""+oname+"\")'>元素"+(e+1)+"</a><a id='sc_"+(e+1)+"' href='javascript:void(0)' onclick='del_json("+(e+1)+",\""+oname+"\")' class='closeTab'>删除</a></li>")
	$("#show_index").val((e+1));
	var s = $("#newDiv .kindeditor");
   	for(var i=0;i<s.length;i++){
   		edit(s[i].id);
   	}
}

function showJson(index,oname){
	clearForm('newform');
	$("#newform .hylink").attr("disabled",false);
	var json = $("#json_"+index).val();
	json = json.replace(/&apos;/g,"'");
	$(".celement").removeClass("on");
	$("#list_"+index).addClass("on");
	var myObject = eval('(' + json + ')');
	
	for(var p in myObject){
   		$("#var_"+p).val(myObject[p]).attr("hydata",myObject[p]);
   		$("#var_se"+p).val(myObject[p]);
   		if($("#img_"+p).length>0){
	   		$("#img_"+p).attr("src",myObject[p]);
   		}
   		if($("#var_bc"+p).length>0){
	   		var bgcol="RGB("+myObject[p]+")";
	   		$("#var_bc"+p).val(myObject[p]).text(myObject[p]).attr("style","margin-top:5px;width:80px;border:1px solid "+bgcol.colorHex()+";border-right:20px solid "+bgcol.colorHex()+";");
   		}
   		if($("#var_c"+p).length>0){
   			$("#var_c"+p).val(myObject[p]).text(myObject[p]).attr("style","margin-top:5px;width:80px;border:1px solid "+myObject[p]+";border-right:20px solid "+myObject[p]+";");
   		}
   		if($("#var_b1").length>0){
	   		$("#var_b1").attr("style","margin-top:5px;width:80px;border:1px solid "+$("#var_b1").val()+";border-right:20px solid "+$("#var_b1").val()+";");
   		}
   		if($("#var_bds"+p).length>0){
   			$("#bds"+p).attr("style","width:"+myObject[p]*7+"px;");
   			$("#btn0"+p).attr("style","left:"+myObject[p]*7+"px;");
   			$("#var_bds"+p).val(myObject[p]).text(myObject[p]);
   		}
   		if($("#var_bdc"+p).length>0){
   			$("#bdc"+p).attr("style","width:"+myObject[p]*140+"px;");
   			$("#btn0"+p).attr("style","left:"+myObject[p]*140+"px;");
   			$("#var_bdc"+p).val(myObject[p]).text(myObject[p]);
   		}
   		if(myObject[p].startWith("/"+oname+"/user/show/")>0){
   			var reg = /\/show\/[1-9][0-9]*/g;  
       		var numList = myObject[p].match(reg);
       		var pageid = numList[0].replace("/show/","");
			var s = document.getElementById("sel_"+p);
			var ops = s.options;
			for(var i=0;i<ops.length; i++){
				var tempValue = ops[i].value;
				if(tempValue == pageid) {
					$("#gl_"+p).attr("checked","checked");
					$("#sel_"+p).attr("disabled",false);
					$("#var_"+p).attr("disabled",true).val("/"+oname+"/user/show/"+pageid+".html");
					ops[i].selected = true;
					break;
				}
			}
   		}else {
   			$("#gl_"+p).attr("checked",false);
   			$("#sel_"+p).attr("disabled",true);
   		}
	}
	$("#show_index").val(index);
	var s = $("#newDiv .kindeditor");
   	for(var i=0;i<s.length;i++){
   		edit(s[i].id);
   	}
}

function scjson(){
	$("#newform .hylink").attr("disabled",false);
	var index = $("#show_index").val();
	var jsonuserinfo = $('#newform').serializeObject();
    var json = JSON.stringify(jsonuserinfo);
    if($("#json_"+index).length > 0){
	    $("#json_"+index).attr('value',json);
	}else{
	    $("#json_list").append("<input type='text' id='json_"+index+"' name='list' value='"+json+"'/>");
	}
	$("#newform .hylink").attr("disabled",true);
	return json;
}

function delDiv(id){
	$("#"+id).remove();
}
function delDiv2(id){
	var id1 = $("#"+id).closest('div').attr("id");
	$("#"+id1).remove();
}


function clearForm(id){
	KindEditor.remove(".kindeditor");
	$(".kindeditor").val("").html("").attr("hydata","");
	$("#newDiv img").each(function (i) {
        $(this).removeAttr("src");
    });
    var formObj = document.getElementById(id);
	if(formObj == undefined){
		return;
	}
	for(var i=0; i<formObj.elements.length; i++){
		if(formObj.elements[i].type == "text"){
			formObj.elements[i].value = "";
		}
		else if(formObj.elements[i].type == "hidden"){
			formObj.elements[i].value = "";
		}
		else if(formObj.elements[i].type == "radio"){
			formObj.elements[i].checked = false;
		}
		else if(formObj.elements[i].type == "checkbox"){
			formObj.elements[i].checked = false;
		} 
		else if(formObj.elements[i].type == "select-one"){
			formObj.elements[i].options[0].selected = true;
		}
		else if(formObj.elements[i].type == "textarea"){
			formObj.elements[i].value = "";
		}
		else if(formObj.elements[i].type == "select-multiple"){
			for(var j = 0; j < formObj.elements[i].options.length; j++){
			formObj.elements[i].options[j].selected = false;
			}
		}
		else if(formObj.elements[i].type == "file"){
			//formObj.elements[i].select();
			//document.selection.clear();
			// for IE, Opera, Safari, Chrome
			var file = formObj.elements[i];
			if(file.outerHTML){
			file.outerHTML = file.outerHTML;
			}else{
			file.value = ""; // FF(鍖呮嫭3.5)
			}
		}
	}
} 

function usegld(id,oname){
	var isChecked = $('#gl_'+id).is(":checked"); 
	if(isChecked){
		$("#sel_"+id).attr("disabled",false);
		$("#var_"+id).attr("disabled",true).val("/"+oname+"/user/show/"+$("#sel_"+id).val()+"/pcn/kv.html");
	}else{
		$("#sel_"+id).attr("disabled",true);
		$("#var_"+id).attr("disabled",false).val($("#var_"+id).attr("hydata"));
	}
}

function gldym(id,select,oname){
	var pageid = $(select).val();
	$("#var_"+id).val("/"+oname+"/user/show/"+pageid+"/pcn/kv.html");
}

function img_flash(id,desc){
	 //var img=new Image();
	 //img.src=$("#img_"+id).attr("src");
	 //var width=img.width,height=img.height;
	 var s = desc.split("*");
	 var width=s[0];
	 var height=s[1];
  	$('#myModal1').modal({
		backdrop: false,
		remote:"img_flash.action?width="+width+"&height="+height+"&key="+id
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
     
function removePic(){
 	$(".imgarea").remove();
 }
 
function showMoreSck(fid,sid,pageId){
	$.post("ajax_sck.action",{"fcategoryid":fid,"scategoryid":sid,"pageId":pageId},function(data){
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
	$.post("ajax_sck.action",{"fcategoryid":fid,"scategoryid":sid,"pageId":pageId},function(data){
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
		remote:"sck.action?fcategoryid="+fid+"&key="+key
	});
}

function ctfunction(i,that){
	$(".btn-group-aimation .btn").removeClass("btn-primary");
	$(that).addClass("btn-primary");
	var ctname="";
	if(i==1 || i == 2){
		$(".fx-hide").show();
	}else{
		$(".fx-hide").hide();
	}
	if(i == 0){
		ctname = "fadeIn";
	}else if(i == 1){
		var sel = $("#fx_name").val();
		if(sel == 0){
			ctname="fadeInLeft";
		}else if(sel == 1){
			ctname="fadeInDown";
		}else if(sel ==2){
			ctname="fadeInRight";
		}else{
			ctname="fadeInUp";
		}
	}else if(i == 2){
		var sel = $("#fx_name").val();
		if(sel == 0){
			ctname="bounceInLeft";
		}else if(sel == 1){
			ctname="bounceInDown";
		}else if(sel ==2){
			ctname="bounceInRight";
		}else{
			ctname="bounceInUp";
		}
	}else if(i == 3){
		ctname="bounceIn";
	}else if(i == 4){
		ctname="zoomIn";
	}else if(i == 5){
		ctname="rubberBand";
	}else{
		ctname="";
	}
	$("#cttype").val(i);
	$("#ct_name").val(ctname);
	showCT();
}

function getCTname(type,fx){
	var ctname;
	if(type == 0 && type != ""){
		ctname = "fadeIn";
	}else if(type == 1){
		if(fx == 0){
			ctname="fadeInLeft";
		}else if(fx == 1){
			ctname="fadeInDown";
		}else if(fx ==2){
			ctname="fadeInRight";
		}else{
			ctname="fadeInUp";
		}
	}else if(type == 2){
		if(fx == 0){
			ctname="bounceInLeft";
		}else if(fx == 1){
			ctname="bounceInDown";
		}else if(fx ==2){
			ctname="bounceInRight";
		}else{
			ctname="bounceInUp";
		}
	}else if(type == 3){
		ctname="bounceIn";
	}else if(type == 4){
		ctname="zoomIn";
	}else if(type == 5){
		ctname="rubberBand";
	}else{
		ctname="";
	}
	return ctname;
}

function fxname(arg){
	var type=$("#cttype").val();
	var sel = arg.value;
	if(type==1){
		if(sel == 0){
			ctname="fadeInLeft";
		}else if(sel == 1){
			ctname="fadeInDown";
		}else if(sel ==2){
			ctname="fadeInRight";
		}else{
			ctname="fadeInUp";
		}
	}
	if(type==2){
		if(sel == 0){
			ctname="bounceInLeft";
		}else if(sel == 1){
			ctname="bounceInDown";
		}else if(sel ==2){
			ctname="bounceInRight";
		}else{
			ctname="bounceInUp";
		}
	}
	$("#ctfx").val(sel);
	$("#ct_name").val(ctname);
	showCT();
}

function showCT(){
	var ct_name=$("#ct_name").val();
   	var ct_delay=$("#ct_delay").val();
   	var ct_duration=$("#ct_duration").val();
   	var style = "animation-delay:"+ct_delay+"s;animation-duration:"+ct_duration+"s;";
   	$("#div_pet").removeClass().attr("style",style).addClass(ct_name);
}


function chct(id,oname){
	$('#normalModal').modal({
		backdrop:'static',
		remote:"/"+oname+"/page/choosecontent.action?inputid="+id
	});
}