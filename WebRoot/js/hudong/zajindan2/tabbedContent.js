//tab effects
var TabbedContent = {
	init: function() {	
		$(".tab_item").mouseover(function() {
			$(".hidden").hide();
			$(".click_more").show();
			var background = $(this).parent().find(".moving_bg");
			
			$(background).stop().animate({
				left: $(this).position()['left']
			}, {
				duration: 300
			});
			
			TabbedContent.slideContent($(this));			
		});
	},
	init2: function() {	
		$(".tabslider_141205_zjd ul").bind("swipeleft",function(){
			 TabbedContent.slideContent2($(this));
		});
		
	},
	init3: function() {	
		$(".tabslider_141205_zjd ul").bind("swiperight",function(){
			 TabbedContent.slideContent3($(this));
		});		
	},
	
	slideContent: function(obj) {
		
		var margin = $(obj).parent().parent().find(".slide_content_141205_zjd").width();
		margin = margin * ($(obj).prevAll().size() - 1);
		margin = margin * -1;
		
		
		$(obj).parent().parent().find(".tabslider_141205_zjd").stop().animate({
			marginLeft: margin + "px"
		}, {
			duration: 300
		});
		var index=$(obj).index();
		if(index == 3){
			var lid = $(obj).attr("hyvalue");
			if(lid >0){
				var pageid = $("#hy_pageid").val();
				var oname = $("#oname").val();
				$.post("/"+oname+"/user/lottery_prize_winner.action",{"lid":lid,"pageid":pageid},function(data){
				$(".tab_card_201412051623").remove();
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
				 $(".tabslider_141205_zjd").append(html);
				})
			}
		}
		
	},
	slideContent2: function(obj) {
		var margin = $(obj).parent().parent().width();			
		margin = margin * ($(obj).prevAll().size());
		
		//求出一共有几个滑动区块		
		var len=($(obj).siblings().size()-1);
		var wid=$(obj).parent().parent().width();		
		var totalwidth=len * wid;
		
			
		margin = margin * -1;
		
		if(margin>=-totalwidth){
			$(obj).parent().parent().find(".tabslider_141205_zjd").stop().animate({
				marginLeft: margin + "px"
				
			}, {
				duration: 300
			});
		}
		var index=$(obj).index();		
		
		
		var background =$(obj).parent().parent().parent().find(".moving_bg");
		var tabs_141205_zjd=$(obj).parent().parent().parent().find(".tab_item");
		
		$(background).stop().animate({
			left: tabs.eq(index).position()['left']
		}, {
			duration: 300
		});
	},
	
	slideContent3: function(obj) {
		var margin = $(obj).parent().parent().width();			
		margin = margin * ($(obj).prevAll().size()-1);
		
		//求出一共有几个滑动区块		
		var len=($(obj).siblings().size()-1);
		var wid=$(obj).parent().parent().width();		
		var totalwidth=len * wid;
		
			
		margin = -(margin-wid);
		
		
		if(margin <= 0){
			$(obj).parent().parent().find(".tabslider_141205_zjd").stop().animate({
				marginLeft: margin + "px"
				
			}, {
				duration: 300
			});
		}
		var index=$(obj).index();
		if(index == 1){
			index=2;
		}
	
		var background =$(obj).parent().parent().parent().find(".moving_bg");
		var tabs=$(obj).parent().parent().parent().find(".tab_item");
		
		
		$(background).stop().animate({
			left: tabs.eq(index-2).position()['left']
		}, {
			duration: 300
		});
	}
}

$(document).ready(function() {
	TabbedContent.init();
	TabbedContent.init2();
	TabbedContent.init3();
});