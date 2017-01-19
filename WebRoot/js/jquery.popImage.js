;(function($){
	$.fn.popImage = function(options){
		var s = $.extend({}, $.fn.popImage.defaultSettings, options || {});
		if ($("#popImage_cache").length == 0){
			$("<div id='popImage_cache'></div><div class='popImage_close'></div>").appendTo("body");
		}
		var item_num = $("#popImage_cache img").length;
		return this.each(function(index){
			var $$ = $(this),
			iw = $$.outerWidth(),
			ih = $$.outerHeight(),
			imgUrl = this[s.tagName],
			index = item_num + index,
			this_id = "slide" + index;

			$('<img src="'+imgUrl+'" class="popImage_cached '+this_id+'" title="点击关闭"/>').appendTo("#popImage_cache").hide();
			$$.click(function(e){
				var animate_image = $('#popImage_cache .'+this_id),
				w_w = $(window).width(),
				w_h = $(window).height(),
				st = $(window).scrollTop();
				$('.popImage_close').hide();
				e.preventDefault();
				position = $$.offset(),
				o_h = animate_image.height(),
				o_w = animate_image.width();
				var oa_h = position.top;
				var child =  $$.children();
				var c_h = child.height();
				var t,l = (w_w - o_w)/2;
					t = oa_h - (o_h - c_h)/2;
				if( t + o_h > w_h){
					t = w_h - o_h - 100;
				}


				animate_image.css({'left':position.left, 'top':position.top,'height':ih,'width':iw});
				$('.popImage_cached').hide();
				animate_image.show().fadeTo(0,0.9);
				if(t < 0 ){
					t=100;
				};
				animate_image.animate({'left':l,'top':t,'height':o_h,'width':o_w,'opacity':1},s.timeOut,function(){
					var position2 = animate_image.offset();
					$('.popImage_close').css({'left':position2.left+o_w-6, 'top':position2.top-15}).show();
				});
			});
			$('.popImage_close,.popImage_cached').bind('click',function(a){
				$('.popImage_close').hide();
				$('.popImage_cached').hide();
				a.preventDefault();
			});
		});
	};
	$.fn.popImage.defaultSettings = {
		"tagName":"href",
		"timeOut":"600"
	};
})(jQuery);