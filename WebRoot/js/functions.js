/**
 * 时间对象的格式化;
 */
Date.prototype.format = function(format) {
    /*
 * eg:format="YYYY-MM-dd hh:mm:ss";
 */
var o = {
    "M+" :this.getMonth() + 1, // month
    "d+" :this.getDate(), // day
    "h+" :this.getHours(), // hour
    "m+" :this.getMinutes(), // minute
    "s+" :this.getSeconds(), // second
    "q+" :Math.floor((this.getMonth() + 3) / 3), // quarter
    "S" :this.getMilliseconds()
// millisecond
    }
 
    if (/(y+)/.test(format)) {
    format = format.replace(RegExp.$1, (this.getFullYear() + "")
                .substr(4 - RegExp.$1.length));
    }
 
    for ( var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
        format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
                : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
}

function closeFrame(){
    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	parent.layer.close(index);
}

function sub(form){
	form.submit();
}

function showwbgroup(backdrop) {
	$('#wideModal').modal({
		backdrop:backdrop,
		remote:"/interact/showwbgroup.action"
	});
}

function create_activity(id){
	var srcString = 'create_activity.action?siteid=' + id;
	var title="创建活动";
	layer.open({
			type : 2,
			area : ['400px','450px'],
			title : [title,true],
			content: srcString
	});
}

function create_activity_report_data(id,activityid){
	var srcString = 'activity_report_data.action?sitegroupid=' + id + '&activityid =' +activityid;
	var	title="匿名访问详情";
	layer.open({
			type : 2,
			area : ['600px','480px'],
			title : [title,true],
			content: srcString
		});
}

function create_anonActivity_report_data(id,activityid){
	var srcString = '/page/anonActivity_report_data.action?sitegroupid='+id+'&activityid =' +activityid;
	var	title="非匿名访问详情";
	layer.open({
			type : 2,
			area : ['700px','500px'],
			title : [title,true],
			content: srcString
		});
}

function hd_details(name,featureid,sitegroupid){
	var srcString = '/page/hd_details.action?featureid='+featureid+'&sitegroupid='+sitegroupid;
	var	title=name+"-详情";
	layer.open({
			type : 2,
			area : ['800px','500px'],
			title : [title,true],
			content: srcString
		});
}

function create_sitegroup(){
	layer.prompt({
		title : '创建站点'
	},function(value){
		if(trim(value) !=""){
			$.post("copySite.action",{"sitename":value,"copyType":"N"},function(data){
				if(data > 0){
					layer.msg('创建中！请稍等……', {icon: 6, time: 1500}, function(){
						window.location="pageconfig_new.action?siteid="+data+"&stype=P"
					}); 
				}else if(data == -1){
					layer.msg('此站点已存在！请重新输入', {icon: 5, time: 2000},function(){
						create_sitegroup();
					});
				}else{
					layer.msg('创建失败！请重试……', {icon: 5, time: 2000},function(){
						window.parent.location.reload();
					});
				}
			});
		}else{
			layer.msg('站点名称不能为空！请重新输入', {icon: 6, time: 1500}, function(){
				create_sitegroup();
			}); 
		}
	});
}

function trim(str){ //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}


function create_xc(qid){
	var srcString = '/page/createIndex.action';
	var	title="创建套装站点";
	layer.open({
			type : 2,
			area : ['700px','500px'],
			title : [title,true],
			content: srcString
		});
}

function create_tg(qid,sid,idx){
	var srcString = '/interact/createnext.action?questionid='+qid+'&searchid='+sid + '&idx='+idx;
	var	title="逻辑设置";
	layer.open({
			type : 2,
			area : ['700px','300px'],
			title : [title,true],
			content: srcString
		});
}

function showSiteCountDetail(id,type){
	var srcString = '/page/siteCountDetail.action?sitegroupid='+id+'&type='+encodeURIComponent(type);
	var	title="访问明细";
	layer.open({
			type : 2,
			area : ['800px','450px'],
			title : [title,true],
			content: srcString
		});
}

function showLoadSiteCountDetail(id,type){
	var srcString = '/loadpage/loadSiteCountDetail.action?siteid='+id+'&type='+encodeURIComponent(type);
	var	title="访问明细";
	layer.open({
			type : 2,
			area : ['400px','250px'],
			title : [title,true],
			content: srcString
		});
}

function update_site(id){
 	var srcString = '/page/site_update.action?siteid='+id;
 	var title="编辑导航";
	layer.open({
			type : 2,
			area : ['400px','250px'],
			title : [title,true],
			content: srcString
		});
}

function update_load_site(id){
 	var srcString = '/loadpage/loadSiteUpdate.action?siteid='+id;
 	var title="编辑导航";
	layer.open({
			type : 2,
			area : ['400px','250px'],
			title : [title,true],
			content: srcString
		});
}


function site_sub(form){
		//var chk_value =[];  
		//$('input[name="moduleList"]:checked').each(function(){
		//  chk_value.push($(this).val());    
		//		});
		if($("#site_name").val()==""){
			$("#sn_sp").remove();
			$("#sitename").append(" <span id='sn_sp'><font color='red'>名称不能为空!</font></span>");
			return;
		}
		//if(chk_value.length==0){
		//	layer.alert("请选择版块!");
		//	return;
		//}
		form.submit();
}

function del_site(id,name){
	var layerid=layer.confirm('确定将['+name+']删除吗?',function(){
		$.post("/page/del_site.action",{"siteid":id},function(data){
			if(data == 1){
				layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
					window.parent.location.reload();
				}); 
			}else{
				layer.msg('删除失败！请重试……', {icon: 5, time: 2000});
			}
		});
	});
}
function del_site_group(id,name){
	var layerid=layer.confirm('确定将['+name+']删除吗?',function(){
		$.post("/page/del_site_group.action",{"groupid":id},function(data){
			if(data == 1){
				layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
					window.parent.location.reload();
				}); 
			}else{
				layer.msg('删除失败！请重试……', {icon: 5, time: 2000});
			}
		});
	});
}
function del_load_site(id,name){
	var layerid=layer.confirm('确定将['+name+']删除吗?',function(){
		$.post("/loadpage/del_site.action",{"siteid":id},function(data){
			if(data == 1){
				layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
					window.parent.location.reload();
				}); 
			}else{
				layer.msg('删除失败！请重试……', {icon: 5, time: 2000});
			}
		});
	});
}

function create_page(siteid){
	$.post("page_create.action",{"siteid":siteid},function(data){
		window.location.reload();
	})
}

function page_sub(form){
		if($("#page_name").val()==""){
		  	$("#dd_sp").remove();
			$("#page_dd").append(" <span id='dd_sp'><font color='red'>名称不能为空!</font></span>");
			return;
		}
		form.submit();
}

function del_page(id,name){
	var layerid=layer.confirm('该页面的数据将全部丢失!<br>确定将['+name+']删除吗?',function(){
			$.post("/page/del_page.action",{"pageid":id},function(data){
				if(data == 1){
					layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
						window.parent.location.reload();
					}); 
				}else{
					layer.msg('删除失败！请重试……', {icon: 5, time: 2000});
				}
			});
		});
}

 var tag= 0;
 function imgchange(img)
 {
       var s=img;
		$("#h"+s).slideToggle();
		$("#"+s).slideToggle();
	   if (tag==0)
	   	{
	   $("#"+s+"text").text("我用原图片");
	   tag=1;
	   }else if(tag==1){
	   $("#"+s+"text").text("我要上传");
	   tag=0;
	   }
 }

 function config(featureid,fid){
 	var srcString = 'config.action?featureid='+featureid+'&fid='+fid;
 	var title = "未知功能";
 	var width ;
 	var height ;
 	if(featureid==101){
 		width='600px';
 		height='400px';
		title="视频列表配置";
 	}else if(featureid==102){
 		width='800px';
 		height='500px';
 		title="产品两层列表配置";	
 	}else if(featureid==103){
 		width='600px';
 		height='400px';
 		title="产品列表赞配置";
 	}else if(featureid==104){
 		width='1000px';
 		height='500px';
 		title="通告配置";
 	}else if(featureid==105){
 		width='600px';
 		height='400px';
 		title="用户上传图片表单配置";
 	}else if(featureid==106){
 		width='600px';
 		height='400px';
 		title="用户上传图片配置";
 	}else if(featureid==107){
 		width='600px';
 		height='400px';
 		title="用户上传图片审核配置";
 	}else if(featureid==108){
 		width='800px';
 		height='500px';
 		title="上传图片推荐位配置";
 	}else if(featureid==110){
 		width='600px';
 		height='400px';
 		title="新闻列表配置";
 	}
 	else if(featureid==109){
 		width='600px';
 		height='400px';
 		title="图片列表配置";
 	}
 	else if(featureid==112){
 		width='600px';
 		height='400px';
 		title="用户新浪分享表单配置";
 	}
 	else if(featureid==114){
 		width='800px';
 		height='500px';
 		title="用户新浪微博审核";
 	}
 	else if(featureid==115){
 		width='600px';
 		height='400px';
 		title="用户新浪微博列表";
 	}else if(featureid==116){
 		width='800px';
 		height='500px';
 		title="审核微博分类";
 	}else if(featureid==118){
 		width='600px';
 		height='400px';
 		title="用户预约列表";
 	}else if(featureid==119){
 		width='600px';
 		height='400px';
 		title="邮件预定";
 	}
 	else if(featureid==122){
 		width='600px';
 		height='400px';
 		title="邮件期刊";
 	}
 	else if(featureid==123){
 		width='600px';
 		height='400px';
 		title="投票";
 	}
 	else if(featureid==124){
 		width='600px';
 		height='400px';
 		title="调研";
 	}
 	else if(featureid==125){
 		width='600px';
 		height='400px';
 		title="老虎机";
 	}
 	else if(featureid==126){
 		width='600px';
 		height='400px';
 		title="竞拍";
 	}
 	else if(featureid==136){
 		width='600px';
 		height='400px';
 		title="大转盘";
 	}
 	else if(featureid==138){
 		width='600px';
 		height='400px';
 		title="刮刮乐";
 	}
 	else if(featureid==134){
 		width='600px';
 		height='400px';
 		title="口碑传播";
 	}
 	else if(featureid==140){
 		width='600px';
 		height='400px';
 		title="微期刊";
 	}
 	else if(featureid==142){
 		width='1000px';
 		height='500px';
 		title="防伪码审核";
 	}else if(featureid==144){
 		width='600px';
 		height='400px';
 		title="集人气";
 	}else if(featureid==145){
 		width='600px';
 		height='400px';
 		title="散播";
 	}else if(featureid==146){
 		width='600px';
 		height='400px';
 		title="微现场";
 	}else if(featureid==147){
 		width='600px';
 		height='400px';
 		title="新闻列表(目录)";
 	}
 	else if(featureid==148){
 		width='600px';
 		height='400px';
 		title="图片列表(目录)";
 	}else if(featureid==149){
 		width='600px';
 		height='400px';
 		title="内容列表(赞踩评论)";
 	}else if(featureid==150){
 		width='600px';
 		height='400px';
 		title="内容目录列表";
 	}else if(featureid==152){
 		width='600px';
 		height='400px';
 		title="传播配置";
 	}else if(featureid==153){
 		width='600px';
 		height='400px';
 		title="评测";
 	}else if(featureid==154){
 		width='600px';
 		height='400px';
 		title="产品多目录";
 	}
 	else if(featureid==155){
 		width='600px';
 		height='400px';
 		title="图片多目录";
 	}
 	else if(featureid==156){
 		width='600px';
 		height='400px';
 		title="新闻多目录";
 	}
 	else if(featureid==157){
 		width='600px';
 		height='400px';
 		title="视频多目录";
 	}
 	else if(featureid==158){
 		width='600px';
 		height='400px';
 		title="自定义多目录";
 	}
	layer.open({
			type : 2,
			area : [ width,height],
			title : title,
			content: srcString
		});
 } 
 
 function add(pageid){
 	var srcString = 'add.action?pageid='+pageid;
	var title="增加功能";
	layer.open({
			type : 2,
			area : ['700px','500px'],
			title : [title,true],
			content: srcString
		});
 } 
 function addSub(form){
		var chk_value =[];//模块功能
		$('input[name="mgrDto.featureids"]:checked').each(function(){
		   	chk_value.push($(this).val());    
				});
		if(chk_value.length==0){
			layer.msg('请选择功能', {icon: 5, time: 2000});
			return;
		}
		form.submit();
}

 function uploadSub(form){
 		var file =$("#user_upload").val();
		if(file==''){
			parent.layer.msg('请选择图片!', {icon: 5, time: 2000});
			return;
		}
		form.submit();
}

function del_page_feature(id,name,pageid){
	var layerid=layer.confirm('确定将['+name+']删除吗?',function(){
				$.post("/page/del_page_feature.action",{"pfid":id},function(data){
					if(data == 1){
						layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
							$('#feature_div').load('/page/feature.action',{pageid:pageid});
						}); 
					}else{
						layer.msg("删除失败！请重试……", {icon: 5, time: 2000});
					}
				});
			});
}

function up_page_feature(pfid,pageid){
	$.post("/page/up_page_feature.action",{"pfid":pfid},function(data){
		if(data == 1){
			$('#feature_div').load('/page/feature.action',{pageid:pageid});
		}else{
			layer.msg("向上移动失败！请重试……", {icon: 5, time: 2000});
		}
	})
}

function down_page_feature(pfid,pageid){
	$.post("/page/down_page_feature.action",{"pfid":pfid},function(data){
		if(data == 1){
			$('#feature_div').load('/page/feature.action',{pageid:pageid});
		}else{
			layer.msg("向下移动失败！请重试……", {icon: 5, time: 2000});
		}
	})
}

function addprolist(){
	var htmlStr=$("#addProlist").html();
	$("#addProlist").empty();
	parent.layer.open({
	    area : ['auto','auto'],
	    title : '新增分类',
	    dialog : {
	        msg: htmlStr,
	        btns : 2, 
	        type : -1,
	        yes  : function(index){
	        	var place=$(".tab_content").length;
				var b=$(".newProduct").length;
				var title=parent.$("#val").val();
				var content=parent.$("#val1").val();
				var idx=parent.$("#idx").val();
				var StrHtml='<li class=""><a href="javascript:void(0);" onclick="showTab('+parseInt(place+1)+')">'+title+'</a><span style="font-size:12px;font-weight:normal;">(<a href="javascript:void(0);" onclick="delprolist(\''+title+'\',this)">删</a>/<a href="javascript:void(0)" onclick="rename(this)">改</a><input type="hidden" name="dto.prolist['+place+'].title" value="'+title+'"/><input type="hidden" name="dto.prolist['+place+'].id" value="'+parseInt(0-b-1)+'"/><input type="hidden" name="dto.prolist['+place+'].content" value="'+content+'"/><input type="hidden" name="dto.prolist['+place+'].idx" value="'+idx+'"/>)</span></li>';
				if($(".lst").children("li").size()>0){
					$(".lst").children("li:last").after(StrHtml);
				}else{
					$(".lst").before(StrHtml);
				}
				$("#add").append("<div id='tab"+parseInt(place+1)+"' class='tab_content' style='display:none;'><ul id="+place+"><p style='clear: both'></p></ul><input type='button' value='增加产品' onclick='addprouct("+parseInt(0-b-1)+",\""+title+"\",this)'/></div>");
				parent.layer.close(index);
	        }
	    },
        end : function(index){
        	$("#addProlist").html(htmlStr);
        	layer.close(index);
        }
	});
}

function addprouct(prolistid,prolistname,obj){
	var place=$(obj).prev().attr("id");
	var htmlStr=$("#product1").html();
	$("#product").empty();
	parent.layer.open({
	    area : ['auto','auto'],
	    title : '增加产品',
	    dialog : {
	        msg: htmlStr,
	        btns : 2, 
	        type : -1,
	        yes  : function(index){
				var a=parent.$('input:checkbox[name="prd"]:checked');
				$.each(a,function(index,value){
					var productid=$(this).val();
					var img=$(this).next("img").attr("src");
		        	$(obj).prev().children("p").before("<li  class='existProduct'><img src='"+img+"' height='40' width='40'/><input checked='checked' type='checkbox' name='dto.relationStr["+place+"]' value='"+prolistid+"~"+productid+"~"+0+"~-1'/><input value='0' type='text' oninput='addidx(this)' style='width: 20px'/></li>");
				});
				parent.layer.close(index);
	        }
	    },
        end : function(index){
        	$("#product1").html(htmlStr);
        	parent.layer.close(index);
        }
	});
}

function delprolist(name,obj){
	parent.layer.confirm('确定删除系列:['+name+']吗?',function(index){
		$(obj).parents("li").remove();
		$(".tab_content").hide();
		parent.layer.close(index);
	});
}

function rename(obj){
	var a=$(obj).next().val();
	var b=$(obj).next().next().next().val();
	var c=$(obj).next().next().next().next().val();
	$("#newvalue").attr('value',a);
	$("#newvalue3").attr('value',c);
	$("#newvalue1").text(b);
	var htmlStr=$("#renameProlist").html();
	$("#renameProlist").empty();
	parent.layer.open({
	    area : ['auto','auto'],
	    title : '修改系列',
	    dialog : {
	        msg: htmlStr,
	        btns : 2, 
	        type : -1,
	        yes  : function(index){
				var newvalue=parent.$("#newvalue").val();
				var content=parent.$("#newvalue1").val();
				var idx=parent.$("#newvalue3").val();
				$(obj).parent().prev("a").text(newvalue);
				$(obj).next().val(newvalue);
				$(obj).next().next().next().val(content);
				$(obj).next().next().next().next().val(idx);
				parent.layer.close(index);
	        }
	    },
        end : function(index){
        	$("#renameProlist").html(htmlStr);
        	parent.layer.close(index);
        }
	});
}

function addVideoList(form){
		var chk_value =[]; 
		var check=true;   
		$('select[name="dto.vids"] option:selected').each(function(){
		   var value=$(this).val();
		   for(var i=0;i<chk_value.length;i++){
		       if(value==chk_value[i]){
		           parent.layer.alert("你选择了重复的视频");
		           check= false;
		           break;
		       }
		   }
		   chk_value.push(value);   
	    });
	    if(check){
		   form.submit();
		}
}

function addPicList(form){
		var chk_value =[]; 
		var check=true;   
		$('select[name="dto.pids"] option:selected').each(function(){
		   var value=$(this).val();
		   for(var i=0;i<chk_value.length;i++){
		       if(value==chk_value[i]){
		           parent.layer.alert("你选择了重复的图片");
		           check= false;
		           break;
		       }
		   }
		   chk_value.push(value);   
	    });
	    if(check){
		   form.submit();
		}
}

function showuploadrecord(){
	$("#form1").submit();
}

function setHome(siteid,pageid,name){
	layer.confirm('确认将['+name+']设为首页嘛?',function(index){
		$.post("/page/set_home.action",{"pageid":pageid,"siteid":siteid},function(data){
			if(data ==1){
				window.parent.location.reload()
			}else{
				layer.alert("设置失败!");
			}
		});
	});
}

function pubPage(pageid,name){
	layer.confirm('确认将['+name+']上线吗?',function(index){
		$.post("/page/pub.action",{"pageid":pageid},function(data){
			if(data > 0){
				layer.msg('上线中！请稍等……', {icon: 6, time: 1500}, function(){
					window.parent.location.reload();
				}); 
			}else{
				layer.alert("上线失败!");
			}
		});
	});
}
function pubPageAll(siteId,name){
	layer.confirm('确认将['+name+']更新吗?',function(index){
		$.post("/page/pubBySite.action",{"siteId":siteId},function(data){
			if(data > 0){
				layer.msg('更新中！请稍等……', {icon: 6, time: 1500}, function(){
					window.parent.location.reload();
				}); 
			}else{
				layer.alert("更新失败!");
			}
		});
	});
}


function addidx(obj){
	var str=$(obj).prev().val();
	var a=new Array();
	a=str.split("~");
	a[3]=$(obj).val();
	var str2=a[0];
	for(var i=1;i<a.length;i++){
		str2=str2+"~"+a[i];
	}
	$(obj).prev().val(str2)
}


function addnewslist(fid){
	var htmlStr=$("#newsData").html();
	$("#newsData").empty();
	parent.layer.open({
	    area : ['auto','auto'],
	    title : '增加新闻列表',
	    dialog : {
	        msg: htmlStr,
	        btns : 2, 
	        type : -1,
	        yes  : function(index){
				var a=parent.$('input:checkbox[name="nnid"]:checked');
				$.each(a,function(index,value){
					var a=$(".exist").length;
					var b=$(".new").length;
					var place=parseInt(a+b);
					var newsid=$(this).val();
					var  newstitle=this.nextSibling.nodeValue;
					var appendStr="<tr class='new' id='"+place+"'>";
					appendStr+="<td>"+newstitle+"</td>";
		        	appendStr+="<td><input type='hidden' name='dto.list["+place+"].id' value='-1'/>";
					appendStr+="<input type='hidden' name='dto.list["+place+"].nlid' value='"+fid+"'/>"
					appendStr+="<input type='hidden' name='dto.list["+place+"].nid' value='"+newsid+"'>";
					appendStr+="<input type='text' name='dto.list["+place+"].idx' value='0'/></td>";
					appendStr+="<td><a href='javascript:delnews(\""+place+"\")'>删除</a></td></tr>";
		        	$("table").append(appendStr);
				});
				parent.layer.close(index);
	        }
	    },
        end : function(index){
        	$("#newsData").html(htmlStr);
        	parent.layer.close(index);
        }
	});
}


function delnews(id){
			parent.layer.confirm('确认删除嘛?',function(index){
				$("#"+id).html("");
				parent.layer.close(index);
			});	
}

function notAvailable(n){
	var html,link;
	if(n == "yb"){
		var html="<div><p>您尚未开通易博！</p><p>挖掘潜在需求，引领用户互动！</p><p>开通易博，简单快速地帮您找见客户，智能个性化地精准沟通，让推广批量自动的开展！</p><p>老客开通可享95折优惠</p><p>联系客服400-812-6655</p></div>";
		var link="http://www.huiyee.com/yeeweibo.html"
	} else if (n == "yy"){
		var html="<div><p>您尚未开通易邮！</p><p>发送，仅仅是邮件营销的开始！</p><p>开通易邮，对您的客户数据进行深度分析，挖掘客户兴趣，享受智能推荐，让营销有的放矢！</p><p>老客开通可享95折优惠</p><p>联系客服400-812-6655</p></div>";
		var link="http://www.huiyee.com/yeemail.html"
	} else if (n == "yz"){
		var html="<div><p>您尚未开通易站！</p><p>营销着陆点，为传播加油！</p><p>开通易站，无论活动迷你页或微博互动Page，便捷收集兴趣数据，让您的互动客户爆棚！</p><p>老客开通可享95折优惠</p><p>联系客服400-812-6655</p></div>";
		var link="http://www.huiyee.com/yeesite.html"
	}
	layer.open({
		area : ['600px','auto'],
		title:'未开通', 
		content:html,
		dialog : {
			msg: html,
			btns : 2,
			type : -1,
			btn : ['了解更多','取消'],
			yes : function(){
				var a = $("<a href='"+link+"' target='_blank'>Apple</a>").get(0); 
	            var e = document.createEvent('MouseEvents'); 
	            e.initEvent( 'click', true, true ); 
	            a.dispatchEvent(e); 
			}
		}
	});
}

	/*标签页切换*/
	function showTab(n){
		$(".tabs").removeClass("active");
		$(".tabs:eq("+(n-1)+")").addClass("active");
		$(".tab_content").hide();
		$("#tab"+n).show();
		pagemark = 0;
		var m = "tab"+n;
	}
	
	function changDiv(div){
		var status=$("#cs").val();
		if(status=="EDT"){
			$("#EDT").show();
			$("#CMP").hide();
			$("#DEL").hide();
		}else if(status=="CMP"){
			$("#EDT").hide();
			$("#CMP").show ();
			$("#DEL").hide();
		}else if(status=="DEL"){
			$("#EDT").hide();
			$("#CMP").hide();
			$("#DEL").show();
		}
	}
	
	function activity_sub(form){
		var chk_value =[];    
		$('input[name="moduleList"]:checked').each(function(){
		   chk_value.push($(this).val());    
				});
				
		if($("#activity_name").val()==""){
		   layer.alert("请输入活动名!");
			return;
		}
		
		if(chk_value.length==0){
			layer.alert("请选择版块!");
			return;
		}
		form.submit();
	}

	function update_activity(id){
 	var srcString = '/page/activity_update.action?activityid='+id ;
 	var title="编辑活动";
	layer.open({
			type : 2,
			area : ['400px','250px'],
			title : [title,true],
			content: srcString
		});
	}
	
	function del_activity(id,name){
 	var layerid=layer.confirm('是否确定将活动['+name+']删除吗?',function(){
			$.post("/page/activity_del.action",{"activityid":id},function(data){
				if(data == "Y"){
					layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
						$("#act_"+id).remove();
					}); 
				}else{
					layer.alert("删除失败,请重试!",0);
				}
			});
		});
	}
	
	function danymicRecordDetail(sgid,wbuid,siteid,action,start,end){
		
         layer.open({
				type : 2,
				zIndex : 50,
				area : ['720px','500px'],
				offset : ['100px' , '50%'],
				title : ['查看详情',true],
            	content : 'danymic_record_detail.action?sgid='+sgid+'&wbuid='+wbuid+'&siteid='+siteid+'&siftDto.action='+action+'&siftDto.startTime='+start+'&siftDto.endTime='+end
		});
	}
	
	function visitRecordDetail(sgid,wbuid,siteid){
         layer.open({
				type : 2,
				area : ['720px','500px'],
				title : ['查看详情',true],
            	content : 'visit_record_detail.action?sgid='+sgid+'&wbuid='+wbuid+'&siteid='+siteid
		});
	}
	
	function checkDanymicDetailValue(){
		var pageId=$("#detailPageId").val();
		var total=$("#detailTotal").val();
		if(total>(pageId*10)){
			var nextpageId=parseInt(pageId)+1;
			$("#detailnextpage").html("<a href='javascript:danymicRecordDetailData("+nextpageId+")'>下一页</a>");
		}else{
			$("#detailnextpage").html("");
		}
		if(pageId>1){
			priorpageId=parseInt(pageId)-1;
			$("#detailprepage").html("<a href='javascript:danymicRecordDetailData("+priorpageId+")'>上一页</a>");
		}else{
			$("#detailprepage").html("");
		}
	}
	
	
	function activity_details(name,activityid){
	var srcString = '/page/activity_details.action?terminalName='+name+ '&activityid=' +activityid;
	var	title= "参与人数详情";
	layer.open({
			type : 2,
			area : ['800px','500px'],
			title : [title,true],
			content: srcString
		});
     }

    function activity_success_details(name,activityid){
	var srcString = '/page/activity_success_details.action?terminalName='+name+ '&activityid=' +activityid;
	var	title= "成功参与人数详情";
	layer.open({
			type : 2,
			area : ['800px','500px'],
			title : [title,true],
			content: srcString
		});
     }

     function activity_fail_details(name,activityid){
	var srcString = '/page/activity_fail_details.action?terminalName='+name+ '&activityid=' +activityid;
	var	title= "跳出人数详情";
	layer.open({
			type : 2,
			area : ['800px','500px'],
			title : [title,true],
			content: srcString
		});
     }
     
function del_order(id){
	var layerid=layer.confirm('确定删除吗?', {icon: 2},function(){
		$.post("/interact/del_order.action",{"id":id},function(data){
			if(data == 1){
				layer.alert("删除失败,请重试!",0);
			}else{
				layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
					window.parent.location.reload();
				}); 
			}
		});
	});
}


function addRow(aptid,stype) {
    var st ='';
    if(stype=='S'){
    	st='<select><option>下拉框</option></select>';
    }else if(stype=='T'){
    	st='单行文本';
    }else if(stype=='A'){
    	st='多行文本';
    }else if(stype=='C'){
    	st='<input type="checkbox">复选';
    }else if(stype=='R'){
    	st='<input type="radio">单选';
    }
    var m ='';
    var tag = $('input[id="new_mapping"]').each(function(i){
    	m +=$(this).val()+',';
    });
    $.post("/interact/findMapping.action",{"aptid":aptid,"newMappingStr":m},function(data){
    	if(data=="OUT"){
    		layer.alert("字段用完了",0);
    	}else{
    		var idx=data.replace("f", "")
		    var html='<tr align="center" id="tr_'+data+'">';
		    html+='<td align="center">'+st+'</td>';
		    html+='<td align="center"><input type="text" class="text-medium" name="mappingmodel.name"><input type="hidden"  id="new_mapping" name="mappingmodel.mapping" value='+data+'></td>';
		    html+='<td align="center"><input type="text" class="text-medium" name="mappingmodel.defaultvalue" id="'+data+'" onclick="changeContent(\''+data+'\')"><input type="hidden" name="mappingmodel.coltype" value="S"><input type="hidden" name="mappingmodel.stype" value="'+stype+'"><input type="hidden" name="mappingmodel.show" value="Y"><input type="hidden" name="mappingmodel.tag" value="N"></td>';
		    html+='<td align="center"><input type="checkbox" onclick="change(this,\'nf'+data+'\')"><input type="hidden" name="mappingmodel.req" value="N"  id="nf'+data+'"></td>';
		    html+='<td align="center"><span><input type="text" class="text-small" size="3" name="mappingmodel.row" value="0">行</span></td>';
		    html+='<td align="center"><a href="javascript:void(0)" onclick="removeRow(\''+data+'\')">删除</a></td></tr>';
		    
    		$("#addtable").append(html);
    	}   
    })
   }


function addRowNew(aptid,stype) {
    var st ='';
    if(stype=='S'){
    	st='<select><option>下拉框</option></select>';
    }else if(stype=='T'){
    	st='单行文本';
    }else if(stype=='A'){
    	st='多行文本';
    }else if(stype=='C'){
    	st='<input type="checkbox">复选';
    }else if(stype=='R'){
    	st='<input type="radio">单选';
    }else if(stype=='I'){
    	st='图片';
    }
    
    var m ='';
    var tag = $('input[class="new_mapping"]').each(function(i){
    	m +=$(this).val()+',';
    });
    var index = layer.msg('正在分配自定义字段,请稍等...', {icon: 16,time:0});
    $.post("/interact/findMapping.action",{"aptid":aptid,"newMappingStr":m},function(data){
    	layer.close(index);
    	if(data=="OUT"){
    		layer.alert("字段用完了",0);
    	}else{
    		var idx=data.replace("f", "")
		    var html='<tr align="center" id="tr_'+data+'">';
		    html+='<td align="center">'+st+'<input type="hidden" name="subUdf['+idx+'].coltype" value="S"><input type="hidden" name="subUdf['+idx+'].stype" value="'+stype+'"><input type="hidden" name="subUdf['+idx+'].isshow" value="Y"><input type="hidden" name="mappingmodel.tag" value="N"></td>';
		    html+='<td align="center"><input type="text" class="text-medium" name="subUdf['+idx+'].name"><input type="hidden"  class="new_mapping" name="subUdf['+idx+'].mapping" value='+data+'></td>';
		    html+='<td align="center"><input type="text" class="text-medium" name="subUdf['+idx+'].defaultvalue" id="'+data+'" onclick="changeContent(\''+data+'\')"></td>';
		    html+='<td align="center"><input type="checkbox" name="subUdf['+idx+'].req" value="Y"></td>';
		    html+='<td align="center"><span><input type="text" class="text-small" size="3" name="subUdf['+idx+'].row" value="0">行</span></td>';
		    html+='<td align="center"><a href="javascript:void(0)" onclick="removeRow(\''+data+'\')">删除</a></td></tr>';
		    
    		$("#addtable").append(html);
    	}   
    })
   }
   
function addCfRow(stype){
	 var st ='';
    if(stype=='S'){
    	st='<select><option>下拉框</option></select>';
    }else if(stype=='T'){
    	st='单行文本';
    }else if(stype=='A'){
    	st='多行文本';
    }else if(stype=='C'){
    	st='<input type="checkbox">复选';
    }else if(stype=='R'){
    	st='<input type="radio">单选';
    }else if(stype=='P'){
    	st='百度位置';
    }else if(stype=='B'){
    	st='图片';
    }else if(stype=='Q'){
    	st='360全景';
    }
    var m ='';
    var tag = $('input[id="new_mapping"]').each(function(i){
    	m +=$(this).val()+',';
    });
	var size=$("#addtable").find("tr").length-1;
	if(parseInt(size)>=20){
		layer.alert("字段用完了",0);
	}else{
		var index=$("#trindex").val();
		var html='<tr align="center" id="tr_'+index+'">';
		    html+='<td align="center">'+st;
		    html+='<input type="hidden" name="newList['+index+'].mapping" value="fx"/><input type="hidden" name="newList['+index+'].coltype" value="S"/><input type="hidden" name="newList['+index+'].stype" value="'+stype+'"/>'
		    html+='</td>';
		    html+='<td align="center"><input type="text" class="text-medium" name="newList['+index+'].name"></td>';
		   if(stype=='S' || stype=='C' || stype=='R'){
		    html+='<td align="center"><input type="text" class="text-medium" name="newList['+index+'].defaultvalue" id="'+index+'" onclick="changeContent(\''+index+'\')"></td> >'
		   }else{
		   	 html+='<td align="center"><input type="text" class="text-medium" name="newList['+index+'].defaultvalue"></td>'
		   }
		    html+='<td align="center"><span>位置<input type="text" class="text-small" size="3" name="newList['+index+'].row">行</span></td>';
		    html+='<td align="center"><a href="javascript:void(0)" onclick="removeRow(\''+index+'\')">删除</a></td></tr>';
    		$("#addtable").append(html);
    		$("#trindex").val(parseInt(index)+1);
	}
}   


function removeRow(mapping) {
	$("#tr_"+mapping).remove();
}
   
function del_tbl(tblN,ckN){ 
	var ck = document.getElementsByName(ckN); 
    var tab = $(tblN); 
	var rowIndex; 
	for(var i=0;i<ck.length;i++){ 
        if(ck[i].checked){ 
			rowIndex = ck[i].parentNode.parentNode.sectionRowIndex; 
         	tab.deleteRow(rowIndex); 
			i = -1; 
		} 
	} 
}

function checkTitle(){
	if($("#title").val()==""){
		$("#title_").html("请输入申请标题");
		return false;
	}else{
		$("#title_").html("");
		return true;
	}
}
function checkNum(){
	var num1=$("#num1").val();
	var num2=$("#num2").val();
	if(parseInt(num1) > parseInt(num2)){
		layer.alert("每日限量不能大于总限制量");
		return false;
	}else{
		return true;
	}
}

function checkForm(){
	if(checkTitle()&&checkNum()){
		$("#myform").submit();
		return true;
	}
	if(!checkTitle()){
		layer.alert("请填写申请标题");
		return false;
	}
	if(!checkNum()){
		layer.alert("每日限量不能大于总限制量");
		return false;
	}
	
}


function isPhone(){
	if($("#mobile").val()==""){
		return true;
	}
}
function changeValue(arg,flag){
	if( arg.checked ==false){
		$("#"+flag).val("N");
	}else{
		$("#"+flag).val("Y");
	}
}
function change(arg,req){
	if(arg.checked==false){
		$("#"+req).val("N");
	}else{
		$("#"+req).val("Y");
	}
}


function checkRow(){
	var array=document.getElementsByName("mappingmodel.row");
	for(var i=0;i<array.length;i++){
		for(var j=i+1;j<array.length;j++){
			if(parseInt(array[i])!="" && parseInt(array[i])==parseInt(array[j])){
				layer.alert("请检查位置是否填写正确");
				return false;
			}else{
				return true;
			}
		}
	}
}

function del_periodical(id){
	var layerid=layer.confirm('确定删除吗?', {icon: 2},function(){
		$.post("/interact/del_periodical.action",{"id":id},function(data){
			if(data == 1){
				layer.alert("删除失败,请重试!",0);
			}else{
				layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
					window.parent.location.reload();
				}); 
			}
		});
	});
}
function change_publish(id){
	var layerid=layer.confirm('确定设置为首页吗?',function(){
		$.post("/interact/change_publish.action",{"id":id},function(data){
			if(data == 1){
				layer.alert("设置失败,请重试!",0);
			}else{
				layer.msg('设置中！请稍等……', {icon: 6, time: 1500}, function(){
					window.parent.location.reload();
				}); 
			}
		});
	});
}
function formcheck(){
		var objs = document.getElementsByName("dto.list");
		for (var i = 0; i < objs.length; i++) {
		var temp = "";
			if (objs[i].checked) {
			var val = objs[i].getAttribute("value");
			temp += val;
			var objn = document.getElementById("n_"+val);
			temp += "," + objn.value;
			var obji = document.getElementById("i_"+val);
			if(!(/^\d*$/.test(obji.value)) || obji.value == ""){
				alert("请填入邮件排列顺序(只能是数字)");
				return false;			
			}
			temp += "," + obji.value;
			objs[i].value = temp;
		}
	}
}
function valuation(arg,jsp_name){
	var str=$("#muban").val();
	if(arg.checked==true){
		$("#"+jsp_name).val(str);
	}
}
function choosetype(){
	 var se =document.getElementById("sel"); 
	 var option=se.getElementsByTagName("option"); 
	 var str="";
	 for(var i=0;i<option.length;i++){
	 	 if(option[i].selected){
	 	 	document.getElementById("s3").value = option[i].value; 
	 	 } 
	 }   
}
function create_site(id){
	var srcString = '/page/site_create.action?groupid='+id;
	var	title="创建导航";
	layer.open({
			type : 2,
			area : ['400px','250px'],
			title : [title,true],
			content: srcString
		});
}
function slw(id){
	var srcString = 'lottery_winner_detail.action?lpid='+id;
	var	title="中奖人详情";
	layer.open({
			type : 2,
			area : ['600px','400px'],
			title : [title,true],
			content: srcString
		});
}

function showlotterywinner(id){
	var srcString = 'showLotteryWinner.action?lpid='+id;
	var	title="中奖人详情";
	layer.open({
			type : 2,
			area : ['600px','400px'],
			title : [title,true],
			content: srcString
		});
}

function lotterySend(id){
	var srcString = 'lotterySend.action?lpid='+id;
	var	title="中奖人详情";
	layer.open({
			type : 2,
			area : ['600px','400px'],
			title : [title,true],
			content: srcString
		});
}

function RcheckForm(){
	//var files=document.getElementsByName("rqomodel.img").value;
	//$.ajax({
	          //type:"post",
	          //url:"interact/file_upload.action",
	          //data:{"rqomodel.img":files},
	          //dataType:"html",
	          //success:function(data){
	          	//if(data==1){
	          		//layer.alert("上传成功");
	          	//}else{
	          		//layer.alert("上传失败",0);
	          	//}
	          //}
	       //});
	if(!checkQtitle()){
		layer.alter("请填写完整的题目标题");
		return false;
	}else if(!checkQoption()){
		layer.alter("请填写选项");
		return false;
	}else{
		document.myform.submit();
		return true;
	}
}
function del_content(id){
	var layerid=layer.confirm('确定删除吗?', {icon: 2},function(){
		$.post("/interact/del_vote_option.action",{"id":id},function(data){
			if(data == 1){
				layer.alert("删除失败,请重试!",0);
			}else{
				layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
					window.parent.location.reload();
				}); 
			}
		});
	});
}
function del_question(id){
	var layerid=layer.confirm('确定删除吗?', {icon: 2},function(){
		$.post("/interact/del_research_question.action",{"id":id},function(data){
			if(data == 1){
				layer.alert("删除失败,请重试!",0);
			}else{
				layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
					window.parent.location.reload();
				}); 
			}
		});
	});
}
function changetype(type){

	 var se =document.getElementById("sel"); 
	 var option=se.getElementsByTagName("option"); 
	 var str="";
	 for(var i=0;i<option.length;i++){
	 	 if(option[i].selected){
	 	 	document.getElementById("s3").value = option[i].value; 
	 	 } 
	 }   
	var html='';
	if(type.value=='SGL'){
		html+='<dl><dt>选项</dt><dd><input type="text" class="text-medium" size="30" name="rqomodel.content" id="options" onblur="checkQoption()"><span id="options_"></span><a href="javascript:void(0);" onclick="addResearch()" title="增加一个选项"><img src="/images/add.png" /></a></dd>';
		html+='<dl><dt style="font-weight:normal ">选项图片</dt><dd><input type="file" name="rqomodel.img"><input type="hidden" name="rqomodel.pic"></dd></dl>';
		html+='<dl><dt>选项</dt><dd><input type="text" class="text-medium" size="30" name="rqomodel.content" id="options" onblur="checkQoption()"><span id="options_"></span></dd>';
		html+='<dl><dt style="font-weight:normal ">选项图片</dt><dd><input type="file" name="rqomodel.img"><input type="hidden" name="rqomodel.pic"></dd></dl>';
		$("#content_block").html(html);
		return;
	}
	else if(type.value=='MUP' || type.value=='ORD'){
		html+='<dl><dt>选项</dt><dd><input type="text" class="text-medium" size="30" name="rqomodel.content" id="options" onblur="checkQoption()"><span id="options_"></span><a href="javascript:void(0);" onclick="addResearch()" title="增加一个选项"><img src="/images/add.png" /></a></dd>';
		html+='<dl><dt style="font-weight:normal ">选项图片</dt><dd><input type="file" name="rqomodel.img"><input type="hidden" name="rqomodel.pic"></dd></dl>';
		html+='<dl><dt>选项</dt><dd><input type="text" class="text-medium" size="30" name="rqomodel.content" id="options" onblur="checkQoption()"><span id="options_"></span></dd>';
		html+='<dl><dt style="font-weight:normal ">选项图片</dt><dd><input type="file" name="rqomodel.img"><input type="hidden" name="rqomodel.pic"></dd></dl>';
		$("#content_block").html(html);
		return;
	}
	else if(type.value=='FIL'){
		$("#content_block").html(html);
		return;
	}
	else if(type.value=='GAD'){
		$("#content_block").html(html);
		return;
	}else{
		return;
	}

}
function addResearch(){
	var html='';
	html+='<div ><dl id="Btext"><dt>选项</dt><dd style="position:relative;"><input type="hidden" name="rqomodel.id"><input type="text" class="text-medium" name="rqomodel.content" id="options" size="30" onblur="checkQoption()"><span id="options_"></span><a href="javascript:void(0);" onclick="removeResearch(this)" style="position:absolute;right:0;top:0;"><img src="/images/close.png" /></a></dd><dl>'
	html+='<dl id="Bfile"><dt style="font-weight:normal ">选项图片</dt><dd><input type="file" name="rqomodel.img"><input type="hidden" name="rqomodel.pic"></dd></dl></div>'
	$("#content_block").append(html);
}
function removeResearch(obj){
	var tbody=obj.parentNode.parentNode;
	tbody.parentNode.removeChild(tbody);
}
function checkQtitle(){
		if($("#title").val()==""){
			$("#title_").html("请输入题目名称").css("color", "RED");
			return false;
		}else{
			$("#title_").html("");
			return true;
		}
}
function checkQoption(){
	var names=document.getElementsByName("rqomodel.content");
	var k=0;
	for(var i=0;i<names.length;i++){
		if(names[i].value==''){
			k++;
		}
	}
	if(k==0){
		return true;
	}
	return false;
}
function checkQanswer(){
	var answers=document.getElementsByName("rqomodel.content");
	var answers_=document.getElementsByName("answers_");
	for(var i=0;i<answers.length;i++){
		for(var j=0;j<answers_.length;j++){
			if(answers[i].value==""){
		      answers_[j].html("请输入内容");
		      return false;
	        }else{
		      answers_[j].html("");
		      return true;
	        }
	    }
	}
}
function ajld(wbuid,auid,nickName){
	var srcString = 'auctionJoinDetail.action?auid='+auid+"&wbuid="+wbuid;
	var	title=nickName+"-竞价详情";
	layer.open({
			type : 2,
			area : ['600px','400px'],
			title : [title,true],
			content: srcString
		});
}
function del_vote(id){
	var layerid=layer.confirm('确定删除吗?', {icon: 2},function(){
		$.post("/interact/delete_vote.action",{"id":id},function(data){
			if(data == 1){
				layer.alert("删除失败,请重试!",0);
			}else{
				layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
					window.parent.location.reload();
				}); 
			}
		});
	});
}
function del_research(id){
	var layerid=layer.confirm('确定删除吗?', {icon: 2},function(){
		$.post("/interact/delete_research.action",{"id":id},function(data){
			if(data == 1){
				layer.alert("删除失败,请重试!",0);
			}else{
				layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
					window.parent.location.reload();
				}); 
			}
		});
	});
}
function delete_spread(id){
	var layerid=layer.confirm('确定删除吗?', {icon: 2},function(){
		$.post("/interact/delete_spread.action",{"id":id},function(data){
			if(data == 1){
				layer.alert("删除失败,请重试!",0);
			}else{
				layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
					window.parent.location.reload();
				}); 
			}
		});
	});
}
function del_spread(id){
	var layerid=layer.confirm('确定删除吗?', {icon: 2},function(){
		$.post("/interact/delete_spread_option.action",{"id":id},function(data){
			if(data == 1){
				layer.alert("删除失败,请重试!",0);
			}else{
				layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
					window.parent.location.reload();
				}); 
			}
		});
	});
}
function checkWbid(){
	var s=document.getElementById("wbid").value;
	if(s==''){
		$("#wbid_").html("请填写要转发的微博ID").css("color", "RED");
		return false;
	}else{
		$("#wbid_").html("");
		return true;
	}
}

function checkin(){
	if(!checkWbid()){
		layer.alert("亲，请注意填写要转发的微博ID");
	}else{
		document.myform.submit();
	}
}


function addJournalContent(jid){
	var srcString = '/interact/add_journal_content.action?jid=' + jid;
	var	title="新增内容";
	layer.open({
			type : 2,
			area : ['700px','500px'],
			title : [title,true],
			content: srcString
		});
}

function updateJournalContent(id,jid){
	var srcString = '/interact/to_update_journal_design.action?id=' + id + "&jid=" + jid;
	var	title="编辑";
	layer.open({
			type : 2,
			area : ['700px','500px'],
			title : [title,true],
			content: srcString
		});
}

function updateJournalShareContent(id,jid){
	var srcString = '/interact/to_journal_sharecontent.action?id=' + id + "&jid=" + jid;
	var	title="分享内容设置";
	layer.open({
			type : 2,
			area : ['700px','500px'],
			title : [title,true],
			content: srcString
		});
}

function del_journal(id){
	var layerid=layer.confirm('确定删除吗?', {icon: 2},function(){
		$.post("/interact/delete_journal.action",{"id":id},function(data){
			if(data == 1){
				layer.alert("删除失败,请重试!",0);
			}else{
				layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
					window.parent.location.reload();
				}); 
			}
		});
	});
}

function del_journalcontent(id){
	var layerid=layer.confirm('确定删除吗?', {icon: 2},function(){
		$.post("/interact/delete_journal_content.action",{"id":id},function(data){
			if(data == 1){
				layer.alert("删除失败,请重试!",0);
			}else{
				layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
					window.parent.location.reload();
				}); 
			}
		});
	});
}
function up_question(id){
	$.post("/interact/up_research_question.action",{"id":id},function(data){
		if(data == 1){
			window.parent.location.reload();
		}else{
			layer.alert("向上移动失败,请重试!",0);
		}
	})
}
function down_question(id){
	$.post("/interact/down_research_question.action",{"id":id},function(data){
		if(data == 1){
			window.parent.location.reload();
		}else{
			layer.alert("向下移动失败,请重试!",0);
		}
	})
}
function checkEtitle(){
	if($("#title").val()==''){
		$("#title_").html("请输入邮件标题名称").css("color", "RED");
		return false;
	}else{
		$("#title_").html('');
		return true;
	}
}
function checkEurl(){
	if($("#url").val()==''){
		$("#url_").html("请输入url").css("color", "RED");
		return false;
	}else{
		$("#url_").html('');
		return true;
	}
}
function checkButton(){
	if(!checkEtitle()||!checkEurl()){
		layer.alert("请仔细填写表单");
	}else{
		document.myform.submit();
	}
}
function checkJourTitle(){
	if($("#title").val()==''){
		$("#title_").html("请输入微期刊标题名称").css("color", "RED");
		return false;
	}else{
		$("#title_").html('');
		return true;
	}
}
function ckButton(){
	if(!checkJourTitle()){
		layer.alert("请仔细填写微期刊标题");
	}else{
		document.myform.submit();
	}
}
function del_options(id){
		var layerid=layer.confirm('确定删除吗?', {icon: 2},function(){
			$.post("/interact/del_options.action",{"id":id},function(data){
				if(data == 1){
					layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
						window.parent.location.reload();
					}); 
				}else{
					layer.alert("删除失败,请重试!",0);
				}
			});
		});
}
function del_template(id){
	var layerid=layer.confirm('确定删除吗?', {icon: 2},function(){
		$.post("/template/del_template.action",{"id":id},function(data){
			if(data == 1){
				layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
					window.parent.location.reload();
				}); 
			}else{
				layer.alert("删除失败,请重试!",0);
			}
		});
	});
}
function edit_style(id){
	var srcString ='/template/update_template_pre.action?id='+id;
	var title="编辑";
	layer.open({
			type : 2,
			area : ['500px','400px'],
			title : [title,true],
			content: srcString
		});
}


function lud(wbuid,lid,media,oname){
	var srcString ='/'+oname+'/interact/user_detail.action?wbuid='+wbuid+"&lid="+lid+"&media="+media;
	var title="抽奖详情";
	layer.open({
			type : 2,
			area : ['650px','500px'],
			title : [title,true],
			content: srcString
		});
}


function ltt(){
	var ruletype=$('input:radio[name="ruletype"]:checked').val();
	if(ruletype=="G"){
		$('#ruletype').hide();
		$('#usertype').hide()
	}else if(ruletype=="D"){
		$('#ruletype').show();
		var usertype=$('input:radio[name="usertype"]:checked').val();
		if(usertype=="A"){
			$('#usertype').hide();
		}else if(usertype=="S"){
			$('#usertype').show();
		
		}
	} 

}

function zanDetail(id){
	var srcString = '/page/zanDetail.action?productid='+id;
	var	title="赞详细列表";
	layer.open({
			type : 2,
			area : ['600px','600px'],
			title : [title,true],
			content: srcString
		});
}
function delete_site(id){
	var layerid=layer.confirm('确定删除吗?', {icon: 2},function(){
		$.post("/page/deleteSite.action",{"id":id},function(data){
			if(data == 1){
				layer.alert("删除失败,请重试!",0);
			}else{
				layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
					$(".sg_"+id).remove();
				}); 
			}
		});
	});
}
function reloadBlock(relationid){
	var srcString = '/page/showBlock.action?relationid='+relationid;
	var	title="赞详细列表";
	layer.open({
			type : 2,
			area : ['600px','600px'],
			title : [title,true],
			content: srcString
		});
}  

function copyPage(pageid,siteid){
	var srcString = 'copy_page.action?pageid='+pageid+"&siteid="+siteid;
	var	title="复制页面";
	layer.open({
			type : 2,
			area : ['600px','400px'],
			title : [title,true],
			content: srcString
		});

}

function takeOnline(id,name){
	var layerid=layer.confirm('确定将['+name+']上线吗?',function(){
		var index = layer.msg('正在努力上线...', {icon: 16,time:0});
		$.post("pageOnline.action",{"pageid":id},function(data){
			layer.close(index);
			if(data > 0){
				layer.msg('上线成功！', {icon: 6, time: 1500}, function(){
					window.location.reload();
				}); 
			}else{
				layer.msg("上线失败,请重试!",{icon: 2, time: 2000});
			}
		});
	});
}
function takeOffline(id,name){
	var layerid=layer.confirm('确定将['+name+']下线吗?',function(){
		var index = layer.msg('正在及时下线...', {icon: 16,time:0});
		$.post("pageOffline.action",{"pageid":id},function(data){
			layer.close(index);
			if(data > 0){
				layer.msg('下线成功！', {icon: 6, time: 1500}, function(){
					window.location.reload();
				}); 
			}else{
				layer.msg("下线失败,请重试!",{icon: 2, time: 2000});
			}
		});
	});
}
function updateData(id,name){
	var layerid=layer.confirm('确定更新['+name+']的数据吗?',function(){
		var index = layer.msg('正在拼命更新...', {icon: 16,time:0});
		$.post("updateData.action",{"pageid":id},function(data){
			layer.close(index);
			if(data >= 0){
				layer.msg('更新成功！', {icon: 6, time: 1500}); 
			}else{
				layer.msg("更新失败,请重试!",{icon: 2, time: 2000});
			}
		});
	});
}

function yjgx(siteid){
	var layerid=layer.confirm('确定更新全部页面的数据吗?',function(){
		var index = layer.msg('正在拼命更新... 稍等片刻', {icon: 16,time:0});
		$.post("updateDataAll.action",{"siteid":siteid},function(data){
			layer.close(index);
			if(data > 0){
				layer.msg('更新成功！', {icon: 6, time: 1500}); 
			}else{
				layer.msg("更新失败,请重试!",{icon: 2, time: 2000});
			}
		});
	});
}

function yjsx(siteid){
	var layerid=layer.confirm('确定上线全部页面吗?',function(){
		var index = layer.msg('正在努力上线... 可能需要一会儿', {icon: 16,time:0});
		$.post("pageOnlineAll.action",{"siteid":siteid},function(data){
			layer.close(index);
			if(data > 0){
				layer.msg('更新成功！', {icon: 6, time: 1500},function(){
					window.location.reload();
				}); 
			}else{
				layer.msg("更新失败,请重试!",{icon: 2, time: 2000});
			}
		});
	});
}


function yjxx(siteid){
	var layerid=layer.confirm('确定下线全部页面吗?',function(){
		var index = layer.msg('正在下线中... 可能需要一会儿', {icon: 16,time:0});
		$.post("pageOfflineAll.action",{"siteid":siteid},function(data){
			layer.close(index);
			if(data > 0){
				layer.msg('更新成功！', {icon: 6, time: 1500},function(){
					window.location.reload();
				}); 
			}else{
				layer.msg("更新失败,请重试!",{icon: 2, time: 2000});
			}
		});
	});
}

function publish_wx(oname,siteid, authed){
	var srcString = '/'+oname+'/page/publish_to_wx.action?siteid='+siteid+"&authed="+authed;
	var title="发布到微信";
	layer.open({
			type : 2,
			area : ['800px','500px'],
			title : title,
			content: srcString
	});
}

function publish_sina(oname,siteid){
	var srcString = '/'+oname+'/page/publish_index_sina.action?siteid=' + siteid;
	var title="绑定到微博";
	layer.open({
			type : 2,
			area : ['800px','500px'],
			title : title,
			content: srcString
	});
}

function publish_qq(siteid){
	var srcString = 'publish_index_qq.action?siteid=' + siteid;
	var title="绑定到微信";
	layer.open({
			type : 2,
			area : ['800px','500px'],
			title : title,
			content: srcString
	});
}

function checkTempleteCardValue(){
		var pageId=$("#cardPageId").val();
		var total=$("#cardTotal").val();
		if(total>(pageId*2)){
			var nextpageId=parseInt(pageId)+1;
			$("#cardnextpage").html("<a href='javascript:getTempleteCardData("+nextpageId+")'>下一页</a>");
		}else{
			$("#cardnextpage").html("");
		}
		if(pageId>1){
			priorpageId=parseInt(pageId)-1;
			$("#cardprepage").html("<a href='javascript:getTempleteCardData("+priorpageId+")'>上一页</a>");
		}else{
			$("#cardprepage").html("");
		}
	}
	
function checkMaterialValue(){
		var pageId=$("#materialPageId").val();
		var total=$("#materialTotal").val();
		if(total>(pageId*2)){
			var nextpageId=parseInt(pageId)+1;
			$("#materialnextpage").html("<a href='javascript:getMaterialData("+nextpageId+")'>下一页</a>");
		}else{
			$("#materialnextpage").html("");
		}
		if(pageId>1){
			priorpageId=parseInt(pageId)-1;
			$("#materialprepage").html("<a href='javascript:getMaterialData("+priorpageId+")'>上一页</a>");
		}else{
			$("#materialprepage").html("");
		}
}

function upload_page(siteid){
	var srcString = 'upload_page.action?siteid='+siteid;
	var title="页面上传";
	layer.open({
			type : 2,
			area : ['500px','420px'],
			title : [title,true],
			content: srcString,
		});
	}

function upload_page_html(siteid){
	var srcString = 'upload_page_html.action?siteid='+siteid;
	var title="页面上传";
	layer.open({
			type : 2,
			area : ['500px','420px'],
			title : [title,true],
			content: srcString,
		});
	}

	
function upload_page_sub(form){
	if(form.tname.value ==""){
		top.layer.alert("页面还有没有名称!");
		return;
	}	
	
		if(form.file.value==""){
			top.layer.alert("还没有选择文件!");
			return;
		}
	form.submit();
}

function showWxUrl(pageid,weixinshowid){
	var srcString = 'show_wx_url.action?pageid='+pageid+"&weixinid="+weixinshowid;;
	var	title="查看微信url";
	layer.open({
			type : 2,
			area : ['500px','300px'],
			title : [title,true],
			content: srcString
		});
}
function up_T(id,ccid,cctype,indexid){
	$.post("/content/updateUpProduct.action",{"contentId":id,"ccid":ccid,"cctype":cctype,"moveUp":1},function(data){
		if(data >= 1){
			window.parent.location.reload();
		}else{
			layer.alert("向上移动失败,请重试!",0);
		}
	})
}
function down_T(id,ccid,cctype,indexid){
	$.post("/content/updateUpProduct.action",{"contentId":id,"ccid":ccid,"cctype":cctype,"moveUp":-1},function(data){
		if(data >= 1){
			window.parent.location.reload();
		}else{
			layer.alert("向下移动失败,请重试!",0);
		}
	})
}

function up_content(id,voteid,oldIdx,newIdxid,oname){
	var newIdx=$("#"+newIdxid).val();
	$.post("/"+oname + "/interact/updateContent.action",{"optionid":id,"voteid":voteid,"oldIdx":oldIdx,"newIdx":newIdx},function(data){
		if(data == 1){
			window.parent.location.reload();
		}else{
			layer.alert("向上移动失败,请重试!",0);
		}
	})
}

function down_content(id,voteid,oldIdx,newIdxid,oname){
	var newIdx=$("#"+newIdxid).val();
	$.post("/"+oname+"/interact/updateContent.action",{"optionid":id,"voteid":voteid,"oldIdx":oldIdx,"newIdx":newIdx},function(data){
		if(data == 1){
			window.parent.location.reload();
		}else{
			layer.alert("向下移动失败,请重试!",0);
		}
	})
}


function promotion(pageid){
	var srcString = 'showAddresslist.action?pageid='+pageid;
	var	title="推广";
	layer.open({
			type : 2,
			area : ['800px','500px'],
			title : [title,true],
			content: srcString
		});
}
function checkSource(){
	var pageid=$("#pageid").val();
	var sourcename=$("#source").val();
	if(sourcename==''){
		layer.alert("助记符名称不能为空!");
		return false;
	}
	if(sourcename.length<3){
		layer.alert("助记符名称不能小于3位!");
		return false;
	}
	$.post("/page/findSourceExit.action",{"pageid":pageid,"source":sourcename},function(data){
		if(data == 1){
			layer.alert("助记符名称重复，请重新输入!",0);
		}else{
			layer.alert("恭喜你可以使用!",0);
		}
	})
}
function add_promotion(pageid){
	var srcString = '/page/savePageAddress_pre.action?pageid='+pageid;
	var	title="新增推广";
	layer.open({
			type : 2,
			area : ['500px','300px'],
			title : [title,true],
			content: srcString
		});
}
function checkform(){
	var name=$("#name").val();
	if(name==''){
		layer.alert("渠道名称不能为空!");
		return false;
	}
	if($("#source").val()==''){
		layer.alert("助记符名称不能为空");
		return false;
	}
	if($("#source").val().length<3){
		layer.alert("助记符名称不能小于3位!");
		return false;
	}
	if(checkSource()==false){
		layer.alert("请先检查一下助记符");
		return false;
	}
	document.myform.submit();
}
function weibo_flow(){
	var srcString = '/page/sub_weibo_flow1.action';
	var title="如何在官微显示页面";
	layer.open({
			type : 2,
			area : ['800px','500px'],
			title : [title,true],
			content: srcString
	});
}
function help_appointment(){
	var srcString = '/interact/help_appointment.action';
	var title="申请帮助说明";
	layer.open({
			type : 2,
			area : ['800px','500px'],
			title : [title,true],
			content: srcString
	});
}
function help_auction(){
	var srcString = '/interact/help_auction.action';
	var title="竞拍帮助说明";
	layer.open({
			type : 2,
			area : ['800px','500px'],
			title : [title,true],
			content: srcString
	});
}
function help_vote(){
	var srcString = '/interact/help_vote.action';
	var title="投票帮助说明";
	layer.open({
			type : 2,
			area : ['800px','500px'],
			title : [title,true],
			content: srcString
	});
}
function help_spread(){
	var srcString = '/interact/help_spread.action';
	var title="口碑帮助说明";
	layer.open({
			type : 2,
			area : ['800px','500px'],
			title : [title,true],
			content: srcString
	});
}
function help_research(){
	var srcString = '/interact/help_research.action';
	var title="调研帮助说明";
	layer.open({
			type : 2,
			area : ['800px','500px'],
			title : [title,true],
			content: srcString
	});
}

function help_research_content(oname){
	var srcString = '/'+oname +'/interact/help_research_content.action';
	var title="调研内容帮助说明";
	layer.open({
			type : 2,
			area : ['800px','500px'],
			title : [title,true],
			content: srcString
	});
}

function help_ggl(){
	var srcString = '/interact/help_ggl.action';
	var title="刮刮乐帮助说明";
	layer.open({
			type : 2,
			area : ['800px','500px'],
			title : [title,true],
			content: srcString
	});
}
function help_zjd(){
	var srcString = '/interact/help_zjd.action';
	var title="砸金蛋帮助说明";
	layer.open({
			type : 2,
			area : ['800px','500px'],
			title : [title,true],
			content: srcString
	});
}
function help_dzp(){
	var srcString = '/interact/help_dzp.action';
	var title="大转盘帮助说明";
	layer.open({
			type : 2,
			area : ['800px','500px'],
			title : [title,true],
			content: srcString
	});
}

function help_yyy(){
	var srcString = '/interact/help_yyy.action';
	var title="摇一摇帮助说明";
	layer.open({
			type : 2,
			area : ['800px','500px'],
			title : [title,true],
			content: srcString
	});
}

function pay(id){
	var html="<div><input type='radio' name='payed' value='100' checked='checked'>100人民币<input type='radio' name='payed' value='200'>200人民币</div>";
	var index = layer.open({
		area : ['auto','auto'],
		title:'支付', 
		dialog : {
			msg:html,
			btns : 2,
			type : -1,
			btn : ['确定','取消'],
			yes : function(){
				var payed=$("input[type='radio']:checked").val();
				$.post("/page/updatePayed.action",{"id":id,"payed":payed},function(data){
				if(data == 1){
						$("#payed_"+id).html(payed);
						layer.close(index);
					}else{
						layer.alert("支付失败,请重试!",0);
					}
				});
			}
		}
	});
}
function audit(id){
	var html="<div><input type='radio' name='st' value='CMP' checked='checked'>审核成功<input type='radio' name='st' value='FLD'>审核失败</div>";
	var index = layer.open({
		area : ['auto','auto'],
		title:'审核', 
		dialog : {
			msg:html,
			btns : 2,
			type : -1,
			btn : ['确定','取消'],
			yes : function(){
				var s=$('input:radio:checked').val();
				$.post("/page/updateStatus.action",{"id":id,"status":s},function(data){
				if(data == 1){
						if(s=='CMP'){
							$("#status_"+id).html('审核成功');
						}else{
							$("#status_"+id).html('审核失败');
						}	
						layer.close(index);
					}else{
						layer.alert("审核失败,请重试!",0);
					}
				});
			}
		}
	});
}


function hdexport(featureid,pageid){
var htmlStr=$("#expotrhtml").html();
	$("#expotrhtml").empty();
	layer.open({
	    area : ['auto','auto'],
	    title : '筛选提交时间',
	    dialog : {
	        msg: htmlStr,
	        btns : 2, 
	        type : -1,
	        yes  : function(index){
	        	var location="/page/hd_export.action?featureid="+featureid+"&exportDto.pageId="+pageid;
				var start=$("#begintime").val();
				var end=$("#endtime").val();
				if(start.length>0){
					location=location+"&exportDto.startTime="+start;
				}
				if(end.length>0){
					location=location+"&exportDto.endTime="+end;
				}
				window.location.href=location;
	        	layer.close(index);
	        }
	    },
        end : function(index){
        	$("#expotrhtml").html(htmlStr);
        	layer.close(index);
        }
	});
}
	function addjf(){
		var type=$("#type").val();
		var oname=$("#oname").val();
		$("#jfdl").hide();
		$(".hbdl").hide();
		$(".mhbdl").hide();
		$(".ztdl").hide();
		if(type=="J"){
			$("#jfdl").show();
		}else if(type=="H"){
			$(".hbdl").show();
		}else if(type=="M"){
			$(".hbdl").show();
			$(".mhbdl").show();
		}else if(type=="Z"){
			$(".ztdl").show();
		}
	}
function wxjifenDetail(ownerwbuid,wbuid,mid){
	var srcString = 'wxjifenDetail.action?ownerwbuid='+ownerwbuid+"&wbuid="+wbuid+"&mid="+mid+"&utype="+1;
	var title="积分详情";
	layer.open({
			type : 2,
			area : ['800px','500px'],
			title : [title,true],
			content: srcString
	});
}
function jifenDetail(ownerwbuid,wbuid,mid){
	var srcString = 'jifenDetail.action?ownerwbuid='+ownerwbuid+"&wbuid="+wbuid+"&mid="+mid+"&utype="+0;
	var title="积分详情";
	layer.open({
			type : 2,
			area : ['800px','500px'],
			title : [title,true],
			content: srcString
	});
}
function prizeManageDoc(){
	var srcString = '/interact/prize_manage_doc.action';
	var title="奖品管理文档";
	layer.open({
			type : 2,
			area : ['800px','500px'],
			title : [title,true],
			content: srcString
	});
}
function readFileContent(backdrop,fileUrl,type){
	$('#wideModal').modal({
		backdrop:backdrop,
		remote:"/page/readStyleFromFile.action?fileUrl="+fileUrl+"&type="+type
	});
	
}
function renqc(){
	var a=$('input:radio[name="utype"]:checked');
	var utype=a.val();
	if(utype==0){
		$("#wb").show();
		$("#wx").hide();
	}else if(utype==1){
		$("#wx").show();
		$("#wb").hide();
	}
}

function rqdetail(fuid,rqid){
	var srcString ='/interact/renQiDetail.action?fuid='+fuid+"&rqid="+rqid;
	var title="集人气详情";
	layer.open({
			type : 2,
			area : ['650px','440px'],
			title : [title,true],
			content: srcString
		});
}
function share(pageid){
	var srcString = '/page/sharePre.action?pageid='+pageid;
	var	title="共享";
	layer.open({
			type : 2,
			area : ['500px','300px'],
			title : [title,true],
			content: srcString
		});
}
function choose_page(siteid){
	var srcString = '/page/chooseTemplatePage.action?siteid='+siteid;
	var	title="选择套装页面";
	layer.open({
			type : 2,
			area : ['500px','300px'],
			title : [title,true],
			content: srcString
		});
}


function csDetail(img,name,sign,content){
	$("#var_img").attr("src",img);
	$("#name").html(name);
	$("#sign").html(sign);
	$("#content").html(content);
	var htmlStr=$("#csDetail").html();
	$("#csDetail").empty();
	layer.open({
	    area : ['500px','300px'],
	    title : '传播详情',
	    type : 1,
	    btns: 1,
	    page: {
        html: htmlStr
   		},
        end : function(index){
        	$("#csDetail").html(htmlStr);
        	$("#var_img").attr("src","");
			$("#name").html("");
			$("#sign").html("");
			$("#content").html("");
        	layer.close(index);
        }
	});
}
function del_comment(id){
	var layerid=layer.confirm('确定删除吗?', {icon: 2},function(){
		$.post("/interact/delComment.action",{"id":id},function(data){
			if(data == 1){
				layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
					window.parent.location.reload();
				}); 
			}else{
				layer.alert("删除失败,请重试!",0);
			}
		});
	});
}
function check_comment(id){
	var html="<div><input type='radio' name='st' value='CMP' checked='checked'>审核通过<input type='radio' name='st' value='FLD'>审核不通过</div>";
	layer.open({
		area : ['auto','auto'],
		title:'审核', 
		dialog : {
			msg:html,
			btns : 2,
			type : -1,
			btn : ['确定','取消'],
			yes : function(){
				var s=$('input:radio:checked').val();
				$.post("/interact/updateCommentRecordStatus.action",{"id":id,"status":s},function(data){
				if(data == 1){
						window.location.reload();
					}else{
						layer.alert("审核失败,请重试!",0);
					}
				});
			}
		}
	});
}
function xcupload(id){
	var srcString = '/interact/xcupload.action?xcid='+id;
	var	title="添加特约嘉宾";
	layer.open({
			type : 2,
			area : ['500px','300px'],
			title : [title,true],
			content: srcString
		});
}
function sharegroup(id){
		$.post("/page/shareGroup.action",{"id":id},function(data){
			if(data == 1){
				window.parent.location.reload();
			}else{
				layer.alert("共享失败,请重试!",0);
			}
		});
}
function cancelshare(id){
		$.post("/page/cancelShare.action",{"id":id},function(data){
			if(data == 1){
				window.parent.location.reload();
			}else{
				layer.alert("操作失败,请重试!",0);
			}
		});
}

function xchd(xcid){
	var srcString = 'xchdIndex.action?xcid='+xcid;
	var	title="新增现场互动";
	layer.open({
			type : 2,
			area : ['700px','500px'],
			title : [title,true],
			content: srcString
		});
	
}

function del_contenttype(id,oname){
	var layerid=layer.confirm('确定删除吗?', {icon: 2},function(){
		$.post("/content/del_content_type.action",{"typeid":id},function(data){
			if(data == 0){
				layer.msg("操作失败!请重试……", {icon: 5, time: 2000});
			}else{
				layer.msg('处理中！请稍等……', {icon: 6, time: 1500}, function(){
					window.location.reload();
				});
			}
		});
	});
}
function config_contenttype(){
	var srcString = '/content/config_type.action';
	var	title="更多分类";
	layer.open({
			type : 2,
			area : ['700px','500px'],
			title : [title,true],
			content: srcString
		});
}
function interact_config_index(oname){
	var srcString = '/'+oname+'/interact/config_index.action';
	var	title="互动设置";
	layer.open({
			type : 2,
			area : ['700px','500px'],
			title : [title,true],
			content: srcString, 
			end : function(index){
					window.location="/"+oname+"/interact/index.action"
				}
			
		});
}
function dataShow(aptid,oname){
	var srcString = '/'+oname+'/interact/apt_dataShow.action?aptid='+aptid;
	var	title="配置显示项";
	layer.open({
			type : 2,
			area : ['700px','500px'],
			title : [title,true],
			content: srcString
		});
}


function dataExport(aptid,utype,oname){
	var vhtml=$("#exportHtml").html();
	$("#exportHtml").empty();
    layer.open({
	    title : '导出数据',
	    btn: ['确定', '取消'],
	    content : vhtml,
	    yes : function(index){
	    	var starttime=$("#start").val();
	    	var endtime=$("#end").val();
	    	window.location="/"+oname+"/interact/aptExport.action?aptid="+aptid+"&data_select="+utype+"&starttime="+starttime+"&endtime="+endtime;
	    	layer.close(index);
        },
        end : function(index){
        	$("#exportHtml").html(vhtml);
        	layer.close(index);
        }
	});
}

function formSub(url){
			$.ajax({
		        cache: true,
		        type: "POST",
		        url:url,
		        data:$('#form1').serialize(),// 你的formid
		        async: false,
		        error: function(request) {
		            layer.alert("Connection error",0);
		        },
		        success: function(data) {
		            if(data=="Y"){
		            	window.parent.location.reload()
		            }else{
		            	layer.alert("操作失败!",0);
		            }
		        }
		    });
		}


function editPage(pageid){
	$('#normalModal').modal({
		backdrop:'static',
		remote:"editGp.action?pageid="+pageid
	});
}

// 将form中的值转换为键值对。
// 形如：{name:'aaa',password:'tttt'}
// ps:注意将同名的放在一个数组里
function getFormJson(frm) {
	var o = {};
	var a = $("#"+frm).serializeArray();
	$.each(a, function () {
		if (o[this.name] !== undefined) {
			if (!o[this.name].push) {
				o[this.name] = [o[this.name]];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return o;
}

function upwhole(ownername,siteid){
	var layerid=layer.confirm('确定将整站同步到前台吗?',function(){
		$.post("/"+ownername+"/page/upWholeSite.action",{"siteid":siteid},function(data){
			if(data == 1){
				layer.msg('同步中！请稍等……', {icon: 6, time: 1500}, function(){
					window.parent.location.reload();
				}); 
			}else{
				layer.alert("删除失败,请重试!",0);
			}
		});
	});
}


function beifen(ownername,siteid){
	var srcString = '/'+ownername+'/page/beifen.action?siteid=' + siteid;
	var title="恢复备份";
	layer.open({
			type : 2,
			area : ['400px','450px'],
			title : [title,true],
			content: srcString
	});
}

function changeContent(id){
	var content = $('#'+id).val();
	var reg = /\,/g;
	content = content.replace(reg,'\n');
	layer.prompt({
		formType :2 ,
		title : '初始内容',
		value :content
	},function(value, index){
		var re = /\n/g; 
		value = value.replace(re,',');
		$('#'+id).val(value);
		layer.close(index);
	});
	
}

function startTimeonblur(){
		if($("#startTime").val() == null || $("#startTime").val() == ""){
			$("#startTime_").html("开始时间不能为空!").css("color", "RED");
			return false;
		}else{
			$("#startTime_").html("");
			return true;
		}
	}
	function endTimeonblur(){
		if($("#endTime").val() == null || $("#endTime").val() == ""){
			$("#endTime_").html("结束时间不能为空!").css("color", "RED");
			return false;
		}else{
			$("#endTime_").html("");
			return true;
		}
	}
	
	
	String.prototype.endWith=function(str){
		if(str==null||str==""||this.length==0||str.length>this.length)
		  return false;
		if(this.substring(this.length-str.length)==str)
		  return true;
		else
		  return false;
		return true;
		}
	
	
	String.prototype.startWith=function(str){
		if(str==null||str==""||this.length==0||str.length>this.length)
		  return false;
		if(this.substr(0,str.length)==str)
		  return true;
		else
		  return false;
		return true;
		}
		
		
	function del_apt(id,oname){
		var srcString = '/'+ oname + '/interact/del_apt.action';
		var layerid=layer.confirm('确定删除?',{icon: 2},function(){
			$.post(srcString,{"rid":id},function(data){
				if(data == "Y"){
					layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
						window.parent.location.reload();
					}); 
				}else{
					layer.msg('删除失败！请重试……', {icon: 5, time: 2000});
				}
			});
		});
	}
	
function breadset(ccid){
	layer.open({
	    type: 2,
	    title:"面包屑设置",
	    area: ['500px', '400px'],
	    fix: false, //不固定
	    maxmin: true,
	    content: 'breadset.action?ccid='+ccid
	});
}

function createMb(siteid){
	layer.open({
	    type: 2,
	    title:"创建模板",
	    area: ['500px', '510px'],
	    fix: false, //不固定
	    maxmin: true,
	    content: 'addmb.action?siteid='+siteid
	});
}

function addZjsource(siteid){
	layer.open({
	    type: 2,
	    title:"上传资源",
	    area: ['600px', '620px'],
	    fix: false, //不固定
	    maxmin: true,
	    content: 'zujianSource.action?siteid='+siteid
	});
}
function editSource(siteid){
	layer.open({
		type: 2,
		title:"编辑资源",
		area: ['600px', '620px'],
		fix: false, //不固定
		maxmin: true,
		content: 'edit_zujian_source.action?sourceid='+siteid
	});
}

function useMb(mbid,type){
	var width = '500px';
	var height = '280px';
	layer.open({
	    type: 2,
	    title:"使用模板",
	    area: [width, height],
	    fix: false, //不固定
	    maxmin: true,
	    content: 'usemb.action?mbid='+mbid+"&stype="+type
	});
}

function preMb(link,cid,stype){
	layer.open({
	    type: 2,
	    title:"预览模板",
	    area: ["1000px", "620px"],
	    fix: true, //不固定
	    maxmin: true,
	    content: "yulan.action?link="+link+"&cid="+cid+"&stype="+stype
	});
}

function loadmbk(oname,type){
	window.location="/"+oname+"/template/index.action?type="+type;
}


function moveToGroup(oname,voteid,mid,type,redirectXc){
		var srcString = "/"+oname+"/interact/moveToGroup.action?voteid="+voteid+"&mid="+mid+"&type="+type+"&redirectXc=" +redirectXc ;
		var	title="移动到组";
		layer.open({
				type : 2,
				area : ['450px','490px'],
				title : [title,true],
				content: srcString
			});
	}
	
function moveToGroup(oname,lid,type){
	var srcString = "/"+oname+"/interact/lotteryMoveToGroup.action?lid="+lid+"&type="+type+"&gz_i=1";
	window.location.href = srcString;
}


function editWxShare(shareid,siteid,oname){
	layer.open({
	    type: 2,
	    title: '微信分享设置',
	    shadeClose: true,
	    shade: 0.8,
	    area: ['60%', '92%'],
	    content: '/'+oname+'/page/editWxPageShow.action?shareid='+shareid+'&siteid='+siteid
	});
}


function deleteWxShare(shareid,siteid,title,oname){	
	var layerid=layer.confirm('确定将标题为['+title+']的设置删除吗?',function(){
	$.post("/"+oname+"/page/deleteWxPageShow.action",{"shareid":shareid,"siteid":siteid},function(data){
		if(data > 0){
			layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
				window.parent.location.reload();
			}); 
		}else{
			layer.msg('删除失败！请重试……', {icon: 5, time: 2000});
		}
	});
});}

function editWxShareWebSite(shareid,siteid,oname){
	layer.open({
	    type: 2,
	    title: '微信分享设置',
	    shadeClose: true,
	    shade: 0.8,
	    area: ['650px', '680px'],
	    content: '/'+oname+'/page/editWxPageShowWb.action?shareid='+shareid+'&siteid='+siteid
	});
}

