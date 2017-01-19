<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div style="margin:0 auto;width:800px;">
	<div class="prephone">
		<iframe id="iframe" name="iframe" src="${link }" frameborder="0" height="500px" width="320px" scrolling="no"></iframe>
	</div>
	<div class="precontrol">
		<a class="lastpage" href="javascript:void(0);" onclick="dang()">上一页</a>
		<a class="nextpage" href="javascript:void(0);" onclick="ding()">下一页</a>
	</div>
	<script type="text/javascript" src="/js/mobile/zepto.min.js"></script>
	<script type="text/javascript" src="/js/mobile/fx.js"></script>
	<script type="text/javascript" src="/js/mobile/touch.js"></script>
	<script type="text/javascript" src="/js/qrcode.js"></script> 
	<script type="text/javascript">
		var total_page;
		var nowrow = 1, lastrow = 0;
		const towards = { up:1, down:2};
		var isAnimating = false;
		
		function dang(){
			if (isAnimating) return;
			lastrow = nowrow;
			if (lastrow!=1) { nowrow = lastrow-1; pageMove(towards.down);}	
		};
		function ding(){
			total_page = window.frames['iframe'].$(".page").length;
			if (isAnimating) return;
			lastrow = nowrow;
			if (lastrow!=total_page) {  nowrow = lastrow+1; pageMove(towards.up);}	
		};
		
		function pageMove(tw){
			var lastPage = ".page-"+lastrow+"-"+1,
				nowPage = ".page-"+nowrow+"-"+1;
				lastPageEffect = window.frames['iframe'].$(lastPage).attr("effect");
				nowPageEffect = window.frames['iframe'].$(nowPage).attr("effect");
				
			switch(tw) {
				case towards.up:
					outClass = 'pt-page-'+lastPageEffect+'UpOut';
					inClass = 'pt-page-'+nowPageEffect+'UpIn';
					break;
				case towards.down:
					outClass = 'pt-page-'+lastPageEffect+'DownOut';
					inClass = 'pt-page-'+nowPageEffect+'DownIn';
					break; 
			}
			isAnimating = true;
			window.frames['iframe'].$(nowPage).removeClass("hide");
			
			window.frames['iframe'].$(lastPage).addClass(outClass);
			window.frames['iframe'].$(nowPage).addClass(inClass);
			
			setTimeout(function(){
				console.log(lastPage+"  "+nowPage);
				window.frames['iframe'].$(lastPage).removeClass('page-current');
				window.frames['iframe'].$(lastPage).removeClass(outClass);
				window.frames['iframe'].$(lastPage).addClass("hide");
				window.frames['iframe'].$(lastPage).find("img").addClass("hide");
				
				window.frames['iframe'].$(nowPage).addClass('page-current');
				window.frames['iframe'].$(nowPage).removeClass(inClass);
				window.frames['iframe'].$(nowPage).find("img").removeClass("hide");
				
				isAnimating = false;
			},600);
		}
	</script>
</div>