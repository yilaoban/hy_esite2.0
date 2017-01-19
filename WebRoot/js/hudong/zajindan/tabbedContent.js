//tab effects
var TabbedContent = {
	init: function() {	
		$('.tab_item').bind("click", function(){  
			$(".hidden").hide();
			$(".click_more").show();
			var background = jQuery(this).parent().find(".moving_bg");
			jQuery(background).stop().animate({
				left: jQuery(this).position()['left']
			}, {
				duration: 300
			});
			
			TabbedContent.slideContent(jQuery(this));			
		});
	},
	init2: function() {	
		jQuery(".tabslider_141205_zjd ul").bind("swipeleft",function(){
			 TabbedContent.slideContent2(jQuery(this));
		});
		
	},
	init3: function() {	
		jQuery(".tabslider_141205_zjd ul").bind("swiperight",function(){
			 TabbedContent.slideContent3(jQuery(this));
		});		
	},
	
	slideContent: function(obj) {
		
		var margin = jQuery(obj).parent().parent().find(".slide_content_141205_zjd").width();
		margin = margin * (jQuery(obj).prevAll().size() - 1);
		margin = margin * -1;
		
		
		jQuery(obj).parent().parent().find(".tabslider_141205_zjd").stop().animate({
			marginLeft: margin + "px"
		}, {
			duration: 300
		});
		var index=jQuery(obj).index();
		if(index == 3){
			var lid = jQuery(obj).attr("hyvalue");
			if(lid >0){
				var pageid = $("#hy_pageid").val();
				var oname = $("#oname").val();
				$.post("/"+oname+"/user/lottery_prize_winner.action",{"lid":lid,"pageid":pageid},function(data){
				jQuery(".tab_card_201412051623").remove();
				var html = "<ul class='tab_card_201412051623'>";
				 for(var i=0; i<data.length; i++){
					if(data[i].status == 3){
						html = html +  "<li>恭喜您获得"+data[i].name + ";</li>";
					}else if(data[i].status == 2){
						html = html +"<li>恭喜您获得"+data[i].name +"<br/>券号:" +data[i].code + "<br/>密码："+data[i].password +"</li>";
					}else if(data[i].status == 1){
						html = html +"<li>恭喜您获得"+data[i].jf + "积分;</li>";
					}
				 }
				 html = html + "</ul>";
				 jQuery(".tabslider_141205_zjd").append(html);
				})
			}
		}
		
	},
	slideContent2: function(obj) {
		var margin = jQuery(obj).parent().parent().width();			
		margin = margin * (jQuery(obj).prevAll().size());
		
		//求出一共有几个滑动区块		
		var len=(jQuery(obj).siblings().size()-1);
		var wid=jQuery(obj).parent().parent().width();		
		var totalwidth=len * wid;
		
			
		margin = margin * -1;
		
		if(margin>=-totalwidth){
			jQuery(obj).parent().parent().find(".tabslider_141205_zjd").stop().animate({
				marginLeft: margin + "px"
				
			}, {
				duration: 300
			});
		}
		var index=jQuery(obj).index();		
		
		
		var background =jQuery(obj).parent().parent().parent().find(".moving_bg");
		var tabs_141205_zjd=jQuery(obj).parent().parent().parent().find(".tab_item");
		
		jQuery(background).stop().animate({
			left: tabs.eq(index).position()['left']
		}, {
			duration: 300
		});
	},
	
	slideContent3: function(obj) {
		var margin = jQuery(obj).parent().parent().width();			
		margin = margin * (jQuery(obj).prevAll().size()-1);
		
		//求出一共有几个滑动区块		
		var len=(jQuery(obj).siblings().size()-1);
		var wid=jQuery(obj).parent().parent().width();		
		var totalwidth=len * wid;
		
			
		margin = -(margin-wid);
		
		
		if(margin <= 0){
			jQuery(obj).parent().parent().find(".tabslider_141205_zjd").stop().animate({
				marginLeft: margin + "px"
				
			}, {
				duration: 300
			});
		}
		var index=jQuery(obj).index();
		if(index == 1){
			index=2;
		}
	
		var background =jQuery(obj).parent().parent().parent().find(".moving_bg");
		var tabs=jQuery(obj).parent().parent().parent().find(".tab_item");
		
		
		jQuery(background).stop().animate({
			left: tabs.eq(index-2).position()['left']
		}, {
			duration: 300
		});
	}
}

jQuery(document).ready(function() {
	setTimeout('init()',100);
});

/**
 * 互动tab
 */
function init(){
	TabbedContent.init();
	TabbedContent.init2();
	TabbedContent.init3();
}


/**
 * 签到方法
 */
function showCheckinData(){
	var oname = $("#oname").val();
	$.post("/"+oname+"/user/checkin_draw.action", function(data){
	   		$(".tk_zi").remove();
			if(data.status == 1){
				$('#HBox').append("<div class='tk_zi'>签到成功!<br/>今日签到获得：<span>"+data.jf+"</span><br/>已连续签到 <span>"+data.daynum+"</span> 天</div>");
			}else{
				$('#HBox').append("<div class='tk_zi'>"+data.hydesc+"</div>");
			}
	   });
}
