function zan(pageid,featureid,fppid,obj){
	$.post("/user/zan.action",{"featureid":featureid,"dto.fppid":fppid,"pageid":pageid},function(data){
		if(data=="Y"){
			var spanobj = $("#lab_zan_"+fppid);
			var oct = spanobj.text();
			var oct_l = oct.length;
			var oct_n = oct.substr(1,oct_l-2);
			oct_n = parseInt(oct_n)+1;
			spanobj.text("+1").fadeOut(2000,function(){spanobj.text("("+oct_n+")").show();});
		}else{
			alert("已经赞过啦～");
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
		lazyload(m);
	}
	
	/*翻页*/
	function fanye(pagely,pon,obj){
		var divid = $(obj).parent().parent().attr("id");
		t_slider(pagely,divid,pon);
	}
	
	var pagemark = 0;
	function t_slider(pagely,divid,pon){
		var getdiv = $("#"+divid+" li");
		var li_l = getdiv.length;
		li_l = Math.ceil(li_l/pagely);
		if(pon == "n"){
			pagemark = pagemark + 1;
		} else {
			pagemark = pagemark - 1;
		}
		if(pagemark >= li_l ){
			pagemark = pagemark%li_l;
		} else if (pagemark < 1){
			pagemark = (pagemark+li_l)%li_l;
		}
		getdiv.hide();
		getdiv.slice(pagely*pagemark,pagely+pagely*pagemark).fadeIn();
		lazyload(divid);
	}
	
	/*赞*/
	$(document).ready(function(){
		var zans = document.getElementsByName("zantotal");
		var pfid=new Array();
		if(zans.length>0){
			for(i=0;i<zans.length;i++){
				pfid.push(zans[i].value);
			}
		}
		if(pfid.length>0){
			$.post("/user/select_zan.action",{"pfid":pfid},function(data){
				$.each(data.zanList,function(index,value){
					$("#lab_zan_"+index).html("("+value+")");					
				});
			});
		}
	})
	/*懒加载*/
	function lazyload(m){
		$("#"+m+" img").each(function(){
			var dto = $(this).attr("data-original");
			if ( typeof dto == "undefined" ){ 
				return
			} else {
				if( $(this).parent().parent().is(":visible")){
					$(this).attr("src",$(this).attr("data-original"));
					$(this).removeAttr("data-original");
				}
			}
	  	});
	}

	/*切视频*/
	function changeVideo(url){
		$("#video").html('<embed src="'+url+'" allowFullScreen="true" quality="high" width="872" height="413" align="middle" allowScriptAccess="always" type="application/x-shockwave-flash" wmode="transparent"></embed>');
	}
	
	function remove(str){
		$("#"+str).remove();
	}
