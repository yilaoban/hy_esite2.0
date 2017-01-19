function nextcategory(obj){
	var front=$(obj);
	var ccid=front.val();
	var text=front.find("option:selected").text();
	$.each(front.nextAll(),function(){
				$(this).remove();
	});
	if(text!="(当前路径)"){
		$.post("/content/next_category.action",{"ccid":ccid},function(data){
			
			var optionStr="<select id='category'  name='ccid' onchange='nextcategory(this)'>";
				optionStr+="<option value='"+ccid+"'>(当前路径)</option>";
			if(data != null){
				$.each(data,function(index,value){
					optionStr+="<option value='"+value.id+"'>"+value.name+"</option>";						
				});
				optionStr+="</select>";
			}
			front.attr("id",ccid);
			front.removeAttr("name");
			front.attr("onchange","nextcategory(this)");
			front.after(optionStr);
		});	
	}else{
		front.attr("name","ccid");
	}
}
	

function delProduct(id,name){
	var layerid=layer.confirm('确定将内容['+name+']删除吗?',function(){
		$.post("del_product.action",{inajax:1,contentId:id},function(data){
			if(data==1){
				layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
					window.parent.location.reload();
				}); 
			}else{
				layer.msg('删除失败！请重试……', {icon: 5, time: 2000});
			}
		});
	});
}
	
function publicProduct(id,name){
	var layerid=layer.confirm('确定将内容['+name+']发布吗?',function(){
		$.post("public_product.action",{inajax:1,contentId:id},function(data){
			if(data==1){
				layer.msg('发布中！请稍等……', {icon: 6, time: 1500}, function(){
					window.parent.location.reload();
				}); 
			}else{
				layer.msg('发布失败！请重试……', {icon: 5, time: 2000});
			}
		});
	});
}

function offProduct(id,name){
	var layerid=layer.confirm('确定将内容['+name+']取消发布吗?',function(){
		$.post("off_product.action",{inajax:1,contentId:id},function(data){
			if(data==1){
				layer.msg('取消发布成功!',2,1);
				window.location.reload();
			}else{
				layer.msg('取消发布失败!',2,3);
			}
		});
	});
}

	
function deletePic(id,name){
	var layerid=layer.confirm('确定将图片['+name+']删除吗?',function(){
		$.post("del_picture.action",{inajax:1,contentId:id},function(data){
			if(data==1){
				layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
					window.parent.location.reload();
				}); 
			}else{
				layer.msg('删除失败！请重试……', {icon: 5, time: 2000});
			}
		});
	});
}
	
function publicPicture(id,name){
	var layerid=layer.confirm('确定将图片['+name+']发布吗?',function(){
		$.post("public_picture.action",{inajax:1,contentId:id},function(data){
			if(data==1){
				layer.msg('发布中！请稍等……', {icon: 6, time: 1500}, function(){
					window.parent.location.reload();
				}); 
			}else{
				layer.msg('发布失败！请重试……', {icon: 5, time: 2000});
			}
		});
	});
}

function offPicture(id,name){
	var layerid=layer.confirm('确定将图片['+name+']取消发布吗?',function(){
		$.post("off_picture.action",{inajax:1,contentId:id},function(data){
			if(data==1){
				layer.msg('取消发布成功!',2,1);
				window.location.reload();
			}else{
				layer.msg('取消发布失败!',2,3);
			}
		});
	});
}
	
function deleteNew(id,name){
	var layerid=layer.confirm('确定将新闻['+name+']删除吗?',function(){
		$.post("del_new.action",{inajax:1,contentId:id},function(data){
			if(data==1){
				layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
					window.parent.location.reload();
				}); 
			}else{
				layer.msg('删除失败！请重试……', {icon: 5, time: 2000});
			}
		});
	});
}
	
function publicNew(id,name){
var layerid=layer.confirm('确定将新闻['+name+']发布吗?',function(){
		$.post("public_new.action",{inajax:1,contentId:id},function(data){
			if(data==1){
				layer.msg('发布中！请稍等……', {icon: 6, time: 1500}, function(){
					window.parent.location.reload();
				}); 
			}else{
				layer.msg('发布失败！请重试……', {icon: 5, time: 2000});
			}
		});
	});
}

function offNew(id,name){
var layerid=layer.confirm('确定将新闻['+name+']取消发布吗?',function(){
		$.post("off_new.action",{inajax:1,contentId:id},function(data){
			if(data==1){
				layer.msg('取消发布成功!',2,1);
				window.location.reload();
			}else{
				layer.msg('取消发布失败!',2,3);
			}
		});
	});
}
	
function publicVideo(id,name){
var layerid=layer.confirm('确定将视频['+name+']发布吗?',function(){
		$.post("public_video.action",{inajax:1,contentId:id},function(data){
			if(data==1){
				layer.msg('发布中！请稍等……', {icon: 6, time: 1500}, function(){
					window.parent.location.reload();
				}); 
			}else{
				layer.msg('发布失败！请重试……', {icon: 5, time: 2000});
			}
		});
	});
}
	
function offVideo(id,name){
var layerid=layer.confirm('确定将视频['+name+']取消发布吗?',function(){
		$.post("off_video.action",{inajax:1,contentId:id},function(data){
			if(data==1){
				layer.msg('取消发布成功!',2,1);
				window.location.reload();
			}else{
				layer.msg('取消发布失败!',2,3);
			}
		});
	});
}

function deleteVideo(id,name){
var layerid=layer.confirm('确定将视频['+name+']删除吗?',function(){
		$.post("del_video.action",{inajax:1,contentId:id},function(data){
			if(data==1){
				layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
					window.parent.location.reload();
				}); 
			}else{
				layer.msg('删除失败！请重试……', {icon: 5, time: 2000});
			}
		});
	});
}
	
function delCategory(id,name,typeid){
		if(id<5){
			layer.msg('此目录为系统目录，不能删除!', {icon: 5, time: 2000});
			return;
		}else{
			var layerid=layer.confirm('确定将目录['+name+']删除吗?',function(){
				$.post("del_category.action",{inajax:1,ccid:id},function(data){
				if(data==0){
					layer.msg('删除失败！请重试……', {icon: 5, time: 2000});
				}else{
					layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
						if(data>0){
							window.location='content_index.action?ccid='+data;
						}else{
							window.location='content_index.action?typeid='+typeid;
						}
					}); 
				}
				});
			});
		}
}
	
function addcategory(id,type,typeid){
	var level=$("#treeLevel").val();
	if(level>=3)
	{
		layer.msg('系統最多创建三级目录', {icon: 5, time: 2000});
		return;
	}
	var htmlStr=$("#addHtml").html();
	if(id==""){
		id=0;
	}
	$("#addHtml").empty();
	layer.open({
	    title : '新增目录',
	    btn: ['确定', '取消'],
	    content : htmlStr,
	    yes : function(index){
			var ccid=id;
			var ccname=$("#ccname").val();
			var ccindex=$("#ccindex").val();
			if(ccindex==""){
				ccindex=0;
			}
			var parten = /^\s*$/ ;
			if(ccname==""||parten.test(ccname)){
				alert("没有填写目录名称！");
				return;
			}
			var ccdesc=$("#ccdesc").val();
			var ccpic=$("#ccpic").val();
			var subtype=$("#subtype").val();
			if(subtype==null){
				subtype="N";
			}
			$.post("add_category.action",{inajax:1,ccid:ccid,"ccname":ccname,"ccdesc":ccdesc,"ccpic":ccpic,"ccindex":ccindex,"cctype":type,"typeid":typeid,"subtype":subtype},function(data){
				if(data==1){
					layer.msg('新增中！请稍等……', {icon: 6, time: 1500}, function(){
						window.parent.location.reload();
					}); 
				}else if(data==2){
					layer.msg('目录重复！请重试……', {icon: 5, time: 2000});
				}else{
					layer.msg('增加失败！请重试……', {icon: 5, time: 2000});
				}
			});	 
        },
        cancel : function(index){
        	$("#addHtml").html(htmlStr);
        	layer.close(index);
        }
	});
}
function updateCategoryName(id,name){
	//为了美吉姆项目 目录加上了描述和图片
	if(id==0){
		layer.msg('没有选中需要修改的目录！', {icon: 5, time: 2000});
		return;
	}
	if(id<5){
		layer.msg('此目录为系统目录，不能修改名称！', {icon: 5, time: 2000});
		return;
	}
	var htmlStr=$("#editHtml").html();
	$("#editHtml").empty();
	layer.open({
	    title : '编辑目录',
	    content: htmlStr,
	    btn: ['确定', '取消'],
	    yes : function(index){
			var ccname=$("#editname").val();
			if(ccname==null||ccname==''){
			   layer.alert("请填写名称");
			   return false;
			}
			var id=$("#editid").val();
			var ccdesc=$("#editdesc").val();
			var ccpic=$("#editpic").val();
			$.post("updateCategoryName.action",{inajax:1,"ccid":id,"ccname":ccname,"ccdesc":ccdesc,"ccpic":ccpic},function(data){
				if(data==1){
					layer.msg('修改中！请稍等……', {icon: 6, time: 1500}, function(){
						window.parent.location.reload();
					}); 
				}else{
					layer.msg('修改失败！请重试……', {icon: 5, time: 2000});
				}
			});	 
        },
	    cancel : function(index){
        	$("#editHtml").html(htmlStr);
        	layer.close(index);
        }
	});
}
	
function changeName(pfid,oldname,pageid){
	layer.prompt({
		value : oldname,
		title : '修改名称'
		},function(value){
			$.post("/page/update_fname.action",{"fname":value,"pfid":pfid},function(data){
				if(data ==1){
					layer.msg('修改中！请稍等……', {icon: 6, time: 1500}, function(){
						$('#feature_div').load('/${oname}/page/feature.action',{pageid:pageid});
					}); 
				}else if(data ==2){
					layer.msg('请填写名称!', {icon: 2, time: 2000});
				}else{
					layer.msg('创建失败！请重试……', {icon: 5, time: 2000});
				}
			});	             
    });
}

function changeTemplateName(id,oldname){
	layer.prompt({
		value : oldname,
		title : '修改名称'
		},function(value){
			$.post("/template/rename_template.action",{"name":value,"id":id},function(data){
				if(data ==1){
					layer.msg('修改中！请稍等……', {icon: 6, time: 1500}, function(){
						window.parent.location.reload();
					}); 
				}else if(data ==2){
					layer.msg('请填写名称!', {icon: 2, time: 2000});
				}else{
					layer.msg('创建失败！请重试……', {icon: 5, time: 2000});
				}
			});	             
    });
}

function changeSiteName(siteid,oldname){
	layer.prompt({
		value : oldname,
		title : '修改名称'
		},function(value){
			$.post("/page/update_site_name.action",{"pagename":value,"siteid":siteid},function(data){
				if(data ==1){
					layer.msg('修改中！请稍等……', {icon: 6, time: 1500}, function(){
						window.parent.location.reload();
					}); 
				}else if(data ==2){
					layer.msg('请填写名称!', {icon: 2, time: 2000});
				}else{
					layer.msg('创建失败！请重试……', {icon: 5, time: 2000});
				}
			});	             
    });
}

function changeSiteName1(id,oldname){
	layer.prompt({
		value : oldname,
		title : '修改名称'
		},function(value){
			$.post("/page/updateGroupName.action",{"name":value,"id":id},function(data){
				if(data ==1){
					layer.msg('修改中！请稍等……', {icon: 6, time: 1500}, function(){
						window.parent.location.reload();
					}); 
				}else if(data ==2){
					layer.msg('请填写名称!', {icon: 2, time: 2000});
				}else{
					layer.msg('创建失败！请重试……', {icon: 5, time: 2000});
				}
			});	             
    });
}

function updatePageName(pageid,oldname){
	layer.prompt({
		value : oldname,
		title : '修改名称'
		},function(value){
			$.post("/page/updatePageName.action",{"pagename":value,"pageid":pageid},function(data){
				if(data ==1){
					layer.msg('修改中！请稍等……', {icon: 6, time: 1500}, function(){
						window.parent.location.reload();
					}); 
				}else if(data ==2){
					layer.msg('请填写名称!', {icon: 2, time: 2000});
				}else{
					layer.msg('创建失败！请重试……', {icon: 5, time: 2000});
				}
			});	             
	});
}

	
function startHandler(name,featureid,sitegroupid){
	$.post("hd_update.action",{"featureid":featureid,"sitegroupid":sitegroupid},function(data){
		layer.alert(data,-1);
	});
}
	
	
	
function updateSiteGroup(id,name){
    var vhtml='<div><p>站点名称：<input id="sgname" value="'+name+'"></input></p></div>';
	layer.open({
		title:'编辑站点', 
		dialog : {
			msg:vhtml,
			btns : 2,
			type : -1,
			btn : ['确定','取消'],
			yes : function(){
			var groupname=$("#sgname").val();
			if(groupname==null||groupname==''){
			    layer.alert("站点名称不能为空");
			    return false;
			}
			$.post("update_site_group.action",{"groupname":groupname,"groupid":id,"inajax":1},function(data){
				if(data==1){
					layer.msg('修改中！请稍等……', {icon: 6, time: 1500}, function(){
						window.parent.location.reload();
					}); 
				}else if(data==2){
				   layer.alert("站点名称不能为空");
				}else{
					layer.msg('修改失败！请重试……', {icon: 5, time: 2000});
				}
			});
			}
		}
	});
}

function danymicRecordDetailData(pageId){
    var sgid=$("#sgid").val();
    var wbuid=$("#wbuid").val();
	jQuery.getJSON("getDanymicRecordDetailData.action",{"inajax":1,"sgid":sgid,"wbuid":wbuid,"pageId":pageId},function(data){
		if(data!=null){
		 		var strHtml="<tr><th>互动时间</th><th>互动类型</th><th>互动对象</th></tr>";
		 		$.each(data,function(index,value){
		 		     if(value.action=111){
		 		        strHtml+="<tr><td align='center'>"+value.createtime+"</td><td align='center'>新浪授权</td><td align='center'>"+value.target+"</td></tr>";	 		
		 		     }else if(value.action=103){
		 		        strHtml+="<tr><td align='center'>"+value.createtime+"</td><td align='center'>产品赞</td><td align='center'>"+value.target+"</td></tr>";	 		
		 		     }else if(value.action=106){
		 		        strHtml+="<tr><td align='center'>"+value.createtime+"</td><td align='center'>上传图片</td><td align='center'>"+value.target+"</td></tr>";	 		
		 		     }else if(value.action=113){
		 		        strHtml+="<tr><td align='center'>"+value.createtime+"</td><td align='center'>新浪分享</td><td align='center'>"+value.target+"</td></tr>";	 		
		 		     }
			 	     
				});
				$("#detailtable").html(strHtml);
				$("#detailPageId").val(pageId);
				checkDanymicDetailValue();
		}
	});
}
	
function addEmail(pageid,fid,uid){
	var str="<p>名称：<input id='email'></p>";
	layer.open({
		title : '修改名称',
	    dialog : {
	        msg: str,
	        btns : 2, 
	        type : -1,
	        yes : function(){
				var email=$("#email").val();
				if(email==null||email==''){
				   layer.alert("请填写email地址");
				   return false;
				}else{
				   reg=/^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/gi;
				   if(email.search(reg) < 0) {
			          layer.alert("email地址格式有误");
			          return;
			       }
				}
				$.post("/user/emailContract.action",{"email":email,"fid":fid,"pageid":pageid,"uid":uid},function(data){
					if(data =='Y'){
						layer.msg('添加成功~', {icon: 6, time: 1500}); 
					}else if(data =='E'){
						layer.msg('请填写名称!',1,3);
					}else if(data =='F'){
						layer.msg('email地址格式有误!',1,3);
					}else if(data =='C'){
						layer.msg('该email地址已经被添加!',1,3);
					}else{
						layer.msg('添加失败!',1,3);
					}
				});	             
	        },
	    }
	});
}

function add_prize(lid){
	var srcString = 'prizeAddPre.action?lid='+lid;
	var	title="添加奖品";
	layer.open({
			type : 2,
			area : ['450px','400px'],
			title : [title,true],
			content: srcString
		});
}

function checkLottery(lid){
	var srcString = 'checkLottery.action?lid='+lid;
	var	title="提交审核";
	layer.open({
			type : 2,
			area : ['450px','490px'],
			title : [title,true],
			content: srcString
		});
}


function update_prize(id){
	var srcString = 'prizeEditPre.action?id='+id;
	var	title="修改奖品";
	layer.open({
			type : 2,
			area : ['450px','480px'],
			title : [title,true],
			content: srcString
		});
}

function update_EmailP(id){
	var srcString = 'email_EditPublish.action?id='+id;
	var	title="修改微期刊";
	layer.open({
			type : 2,
			area : ['450px','250px'],
			title : [title,true],
			content: srcString
		});
}


function ldel(lid){
var layerid=layer.confirm('确定删除此抽奖吗?',function(){
			$.post("/interact/del_zhuanpan.action",{"inajax":1,"lid":lid},function(data){
			if(data!=null){
			 		if(data ==1){
			 			layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
							window.parent.location.reload();
						}); 
					} else{
						layer.msg('删除失败！请重试……', {icon: 5, time: 2000});
					}
			}
		});
	});
}

function del_prize(id){
var layerid=layer.confirm('确定删除吗?',function(){
		$.post("delprize.action",{"id":id},function(data){
			if(data == "success"){
				layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
					window.parent.location.reload();
				}); 
			}else{
				layer.msg(data,2,1);
			}
		});
	});
}
	
	
function lottery_view(lid,wbuid,source,pageid){
	$.post("/interact/lottery_draw.action",{"lid":lid,"wbuid":wbuid,"source":source,"pageid":pageid},function(data){
		if(data.status==0){
			alert("未中");
		}else if(data.status==1){
			alert("积分");
		}else if(data.status==2){
			alert("优惠券");
		}else if(data.status==3){
			alert("实物");
		}
		//0-未中奖,1-中了积分,2-中了优惠劵,3-中了实物
	})
}

function checkin_view(wbuid,ownerwbuid,pageid,source){
	$.post("/interact/checkin_draw.action",{"wbuid":wbuid,"ownerwbuid":ownerwbuid,"pageid":pageid,"source":source},function(data){
		alert(data);
	})
}


function auction_view(wbuid,auid,addnum,pageid,source){
	$.post("/interact/auction_draw.action",{"wbuid":wbuid,"auid":auid,"addnum":addnum,"pageid":pageid,"source":source},function(data){
		alert(data)
	})
}

function audel(auid,name){
var layerid=layer.confirm('确定将['+name+']删除吗?',function(){
		$.post("auctionDel.action",{"inajax":1,"auid":auid},function(data){
			if(data==1){
				layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
					window.parent.location.reload();
				}); 
			}else{
				layer.msg('删除失败！请重试……', {icon: 5, time: 2000});
			}
		});
	});
}

function rqdel(id,name){
var layerid=layer.confirm('确定将['+name+']删除吗?',function(){
		$.post("renQiDel.action",{"inajax":1,"id":id},function(data){
			if(data==1){
				layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
					window.parent.location.reload();
				}); 
			}else{
				layer.msg('删除失败！请重试……', {icon: 5, time: 2000});
			}
		});
	});
}

function checkindel(ownerwbuid){
var layerid=layer.confirm('确定删除['+ownerwbuid+']的签到规则吗?',function(){
		$.post("checinkDel.action",{"inajax":1,"ownerwbuid":ownerwbuid},function(data){
			if(data==1){
				layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
					window.parent.location.reload();
				}); 
			}else{
				layer.msg('删除失败！请重试……', {icon: 5, time: 2000});
			}
		});
	});
}

function checkindel(nickname,ownerwbuid,utype){
var layerid=layer.confirm('确定删除['+nickname+']的签到规则吗?',function(){
		$.post("checinkDel.action",{"inajax":1,"ownerwbuid":ownerwbuid,"utype":utype},function(data){
			if(data==1){
				layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
					window.parent.location.reload();
				}); 
			}else{
				layer.msg('删除失败！请重试……', {icon: 5, time: 2000});
			}
		});
	});
}

function jifendel(id){
var layerid=layer.confirm('确定删除此积分规则吗?',function(){
		$.post("balanceRuleDel.action",{"inajax":1,"id":id},function(data){
			if(data==1){
				layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
					window.parent.location.reload();
				}); 
			}else{
				layer.msg('删除失败！请重试……', {icon: 5, time: 2000});
			}
		});
	});
}

function ljsy(){
	var h = $('input:checkbox[name="templates"]:checked');
	if(h.length==0){
		layer.alert("请选择至少一个模板!")
		return;
	}
	$.ajax({
        cache: true,
        type: "POST",
        url:"/template/ljsy.action",
        data:$('#template_form').serialize(),// 你的formid
        async: false,
        error: function(request) {
            layer.alert("Connection error",0);
        },
        success: function(data) {
            if(data==1){
            	layer.msg('使用成功！请到我的模板查看使用', {icon: 6, time: 1500}); 
            }
        }
    });
}

function getTempleteCardData(pageId){
    var categoryid=$("#categoryid").val();
    var type=$("#ctype").val();
	jQuery.getJSON("getTempleteCards.action",{"inajax":1,"categoryid":categoryid,"type":type,"pageId":pageId},function(data){
		if(data!=null){
		 		var strHtml="";
		 		$.each(data.cardlist,function(index,value){
		 		    strHtml+="<div><a href='"+value.bimg+"' rel='gallery' target='_black'  class='image_gall'><img src='"+value.bimg+"'onmousemove='makeCode(\""+value.url+"\")' height='100'/></a></div>";	 		
				});
				$("#cards").html(strHtml);
				$("#cardPageId").val(pageId);
				$("#cardTotal").val(data.total);
				checkTempleteCardValue();
		}
	});
}
function getMaterialData(pageId){
    var categoryid=$("#categoryid").val();
    var type=$("#ctype").val();
	jQuery.getJSON("getMaterialPics.action",{"inajax":1,"categoryid":categoryid,"type":type,"pageId":pageId},function(data){
		if(data!=null){
		 		var strHtml="";
		 		$.each(data.cardlist,function(index,value){
		 		    strHtml+="<div><a href='"+value.path+"' rel='gallery' target='_black' class='image_gall'><img src='"+value.path+"' height='100'/></a></div>";	 		
				});
				$("#mpics").html(strHtml);
				$("#materialPageId").val(pageId);
				$("#materialTotal").val(data.total);
				checkMaterialValue();
		}
	});
}


function delcsc(id,jid,name){
var layerid=layer.confirm('确定删除['+name+']吗?',function(){
		$.post("csContentDel.action",{"inajax":1,"id":id,"jid":jid},function(data){
			if(data==1){
				layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
					window.parent.location.reload();
				}); 
			}else{
				layer.msg('删除失败！请重试……', {icon: 5, time: 2000});
			}
		});
	});
}

function changeSdNickname(id,oldname){
	var str="<p>名称：<input id='name' value='"+oldname+"'></p>";
	layer.open({
		title : '修改昵称',
	    dialog : {
	        msg: str,
	        btns : 2, 
	        type : -1,
	        yes : function(){
				var name=$("#name").val();
				if(name==null||name==''){
				   layer.alert("请填写名称");
				   return false;
				}
				$.post("/interact/updateXcSd.action",{"nickname":name,"recordId":id},function(data){
					if(data ==1){
						layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
							window.parent.location.reload();
						}); 
					}else if(data ==2){
						layer.msg('请填写名称!',1,3);
					}else{
						layer.msg('修改失败！请重试……', {icon: 5, time: 2000});
					}
				});
	        },
	    }
	});
}

function delXcSd(id,name){
	var layerid=layer.confirm('确定将内容['+name+']删除吗?',function(){
		$.post("/interact/delXcSd.action",{inajax:1,recordId:id},function(data){
			if(data==1){
				layer.msg('删除中！请稍等……', {icon: 6, time: 1500}, function(){
					window.parent.location.reload();
				}); 
			}else{
				layer.msg('删除失败！请重试……', {icon: 5, time: 2000});
			}
		});
	});
}

//<input type="button" onclick="lbsnearby('${oname}',120.383211,31.494092,'会易科技LBS',1)" value="查找"/>
function lbsnearby(oname,x,y,tags,limit){
	$.post("/"+oname+"/user/bdmap/asub2.action",{"x":x,"y":y,"tags":tags,"limit":limit},function(data){
		alert(data);
	})
}

function checkLog(id) {
	loadLog(1, id);
	$("#logModal").modal({
		backdrop : true
	});
}

function loadLog(pageId, id) {
	$.ajax({
		url : "show_log.action",
		data : {
			"pageId" : pageId,
			"lid" : id
		},
		type : "post",
		cache : false,
		success : function(data) {
			if (data) {
				for (var i = 0; i < data.length; i++) {
					var log = data[i];
					var html = "";
					html += '<tr>';
					html += '	<td>' + log.total + '</td>';
					html += '	<td>' + log.accountname + '</td>';
					html += '	<td title="' + log.reason + '">' + log.reason + '</td>';
					html += '	<td>' + log.ip + '</td>';
					html += '	<td>' + log.createtime + '</td>';
					if (log.type == 0) {
						html += '	<td>提交申请</td>';
					} else if (log.type == 1) {
						html += '	<td>审核通过</td>';
					} else if (log.type == 2) {
						html += '	<td>审核不通过</td>';
					}
					html += '</tr>';
					$("#logTable").append(html);
				}

				var page = parseInt(pageId) + 1;
				$("#loadMore").attr("onclick", "loadLog(" + page + "," + id + ")");
				if (data.length == 10) {
					$("#loadMore").show();
				} else {
					$("#loadMore").hide();
				}
			}
		}
	});
}


function lotteryclean(oname,id){
	var layerid=layer.confirm('确定清空数据吗?',{title:"清空抽奖数据"},function(){
		$.post("/"+oname+"/interact/lotteryclean.action",{"lid":id},function(data){
			if(data >0){
				layer.msg('清空完成!',{icon: 6, time: 1000});
			}else{
				layer.msg('清空失败!',{icon: 5, time: 1000});
			}
		});
	});
}

function showAptDetail(oname,rid){
	var srcString = '/'+oname+'/interact/apt_detail.action?rid='+rid;
	var	title="查看详情";
	layer.open({
			type : 2,
			area : ['450px','400px'],
			title : [title,true],
			content: srcString
		});
}
function editAptDetail(oname,aptid,wxuid){
	var srcString = '/'+oname+'/interact/apt_detail_edit.action?aptid='+aptid+'&wxuid='+wxuid;
	var	title="查看详情";
	layer.open({
			type : 2,
			area : ['450px','400px'],
			title : [title,true],
			content: srcString
		});
}

function voteclean(oname,id){
	var layerid=layer.confirm('确定清空数据吗?',{title:"清空投票数据"},function(){
		$.post("/"+oname+"/interact/voteclean.action",{"voteid":id},function(data){
			if(data >0){
				layer.msg('清空完成!',{icon: 6, time: 1000});
			}else{
				layer.msg('清空失败!',{icon: 5, time: 1000});
			}
		});
	});
}

function wxgzevent(id){
	if(id==0&&$("#gzid")){
		id=$("#gzid").val();
	}
 	var srcString = "/${oname}/interact/gzEventIndex.action?id="+id;
	layer.open({
			type : 2,
			area : ['800px','500px'],
			title : '配置',
			content: srcString
		});
}


function showAdWay(oname,id){
	var srcString = "/"+oname+"/interact/showAdWay.action?wayid="+id;
	var	title="编辑";
	layer.open({
			type : 2,
			area : ['450px','490px'],
			title : [title,true],
			content: srcString
		});
}

function showMediaWay(oname,id,medianame){
	var srcString = "/"+oname+"/interact/showMediaWay.action?wayid="+id+"&medianame="+medianame;
	var	title="编辑";
	layer.open({
			type : 2,
			area : ['450px','490px'],
			title : [title,true],
			content: srcString
		});
}