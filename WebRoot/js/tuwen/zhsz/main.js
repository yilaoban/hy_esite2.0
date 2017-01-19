$(function(){
	var timer = null;
	$('#openMenu').click(function(){
		var iH = $(window).height();
		
		//alert(iH);
		if($('.menu_150313').css('display')=='none'){
			var msk = $('<div id="msk"></div>');
			
			$('body').append(msk);			
			$('.menu_150313').show().animate({'right':0},500);
		}else{
			$('.menu_150313').animate({'right':'-200px'},500);
			
			clearTimeout(timer);
			timer = setTimeout(function(){
				$('#msk').remove();
				$('.menu_150313').hide();
			},500);
		}
	});
	
	$('.menu_150313').swipeRight(function(){
		$('.menu_150313').animate({'right':'-200px'},500);
			
		clearTimeout(timer);
		timer = setTimeout(function(){
			$('#msk').remove();
			$('.menu_150313').hide();
		},500);
	});
	
});