$(function(){
	var $category=$(".proList > .plCon");
	$category.hide();
	var $toggleBtn=$(".plTitle > em");
	$toggleBtn.click(function(){
		if($(this).parent(".plTitle").next().is(":visible")){
			$(this).parent(".plTitle").next().hide();
			$(this).prev(".plTitle > a").css("background","");
			$(this).css("background","url(http://img.huiyee.com/site/1140/427/images/icon3.gif) no-repeat left center").text("展开");
		}else{
			$(".plCon").hide();
			$(this).parent(".plTitle").next().show();
			$(this).prev(".plTitle > a").css("background","");
			$(this).css("background","url(http://img.huiyee.com/site/1140/427/images/icon12.gif) no-repeat left center").text("收起");
		}
		return false;
	});
})
