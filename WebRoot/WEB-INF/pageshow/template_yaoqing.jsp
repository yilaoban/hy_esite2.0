<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
		<title>${page.bgJson.title}</title>
		<meta name="description" content="${page.bgJson.description}" />  
		<meta name="keywords" content="${page.bgJson.keywords}" />
		<meta id="myViewport" name="viewport" content="width=320, initial-scale=1, maximum-scale=1, user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">  
		<meta name="apple-mobile-web-app-status-bar-style" content="black">  
		<meta content="telephone=no,email=no" name="format-detection">
		<link href="/css/modelGenaral.css" rel="stylesheet" type="text/css" />
		<link href="/css/modelGlobal.css" rel="stylesheet" type="text/css" />
		<link href="/css/animation.css" rel="stylesheet" type="text/css"/>
		<link href="/css/animation-c.css" rel="stylesheet" type="text/css"/>
		<link href="/js/swiper/swiper.min.css" rel="stylesheet" type="text/css"/>
		<link href="/js/easyDialog/easydialog.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="/diyUpload/css/webuploader.css">
		<link rel="stylesheet" type="text/css" href="/diyUpload/css/diyUpload.css">
		<script type="text/javascript" src="/js/mobile/zepto.min.js"></script>
		<script type="text/javascript" src="/js/mobile/fx.js"></script>
		<script type="text/javascript" src="/js/mobile/touch.js"></script>
		<%@include file="/WEB-INF/pageshow/wx_fx.jsp"%>
		<script type="text/javascript" src="/diyUpload/js/webuploader.js"></script>
		<script type="text/javascript" src="/diyUpload/js/diyUpload.js"></script>
		<script type="text/javascript" src="/js/Sortable.min.js"></script>
		<script type="text/javascript" src="/js/pano2vr_player.js"></script>
		<script type="text/javascript" src="/js/easyDialog/easydialog.min.js"></script>
		<script type="text/javascript" src="/js/mustache.js"></script>
		<link rel="stylesheet" href="/js/bootstrap/css/bootstrap.css" />
		<script type="text/javascript" src="/js/bootstrap/js/bootstrap.min.js"></script> 
		<script src="/js/cb/jquery.hDialog.min.js"></script> 
		<script type="text/javascript" src="/js/hudong/zajindan/tabbedContent.js"></script>
		<script src="/js/jquery.swipe.js" type="text/javascript" charset="utf-8"></script>
		<link href="/js/jquery-weui/css/weui.css" rel="stylesheet"/>
		<link href="/js/jquery-weui/css/jquery-weui.css" rel="stylesheet"/>
		<script src="/js/jquery-weui/js/jquery-weui.js" type="text/javascript"></script>
	</head>
	<body style="<s:if test='dto.pc.size > 1'>overflow:hidden;</s:if>background:${page.bgJson.background} repeat;">
	<div id="audiocontainer"></div>
	<s:if test='dto.pc.size > 1'>
	<%--  <div id="loading" class="loading"><img src="images/load.gif"/>加载中...</div> --%>
		<s:set name="pageid" value="pageid"></s:set>
			<s:iterator value="dto.pc" var="c" status="st">
			 <div class="page page-${st.count}-1 <s:if test="#st.index == 0">page-current</s:if><s:else>hide</s:else>" effect="${c.eventName }">
 			 	<s:if test="!#st.last"><span class="zoomzoom" style="background:url(<s:if test='page.bgJson.uploadArrow'>/images/arrowBtn.png</s:if><s:else>${page.bgJson.uploadArrow}</s:else>);position:absolute;left:50%;margin-left:-17px;bottom:15px;height: 22px; width: 35px;z-index:99;" ></span></s:if>
				<div id="a_${c.id}" class="wrap">
						<script type="text/javascript">
							var id = '${c.id}';
							if(id > 0){
								if('${oname}' !=''){
									$("#a_"+id).load('/${oname}/user/showCard.action?pcid='+id+"&pageid="+'${pageid}'+'&jspstyle='+'${page.jspstyle}'+"&css="+'${c.css}');
								}else{
									$("#a_"+id).load('/user/showCard.action?pcid='+id+"&pageid="+'${pageid}'+'&jspstyle='+'${page.jspstyle}'+"&css="+'${c.css}');
								}
							}
						</script>
				</div>
			</div>
			</s:iterator>
		</s:if>
		<s:else>
		<s:iterator value="dto.pc" var="c" status="st">
			<div id="a_${c.id}" class="single_page" <s:if test="page.bgJson.pageheight > 0">style="height:${page.bgJson.pageheight}px"</s:if> >
			<script type="text/javascript">
				var id = '${c.id}';
				if(id > 0){
					if('${oname}' !=''){
						$("#a_"+id).load('/${oname}/user/showCard.action?pcid='+id+"&pageid="+'${pageid}'+'&jspstyle='+'${page.jspstyle}'+"&css="+'${c.css}');
					}else{
						$("#a_"+id).load('/user/showCard.action?pcid='+id+"&pageid="+'${pageid}'+'&jspstyle='+'${page.jspstyle}'+"&css="+'${c.css}');
					}
				}
			</script>
			</div>
		</s:iterator>
		</s:else>
		<div id="hiddeninput">
		<input id="hy_pageid" type="hidden"  value="${pageid}">
		<input id="hy_visitUser" type="hidden" value="${sessionScope.visitUser }">
		<input id="hy_cd" type="hidden" value="${cd}">
		<input id="hy_userAgent" type="hidden" value="${userAgent}">
		<input id="hy_cid" type="hidden" value="${cid}">
		<input id="hy_entity" type="hidden" value="${page.entityid}">
		<input id="oname" type="hidden" value="${oname }">
		<input id="hy_source" type="hidden" value="${forumid }">
		</div>
		<script type="text/javascript">
			(function(){
				var w_width = $(window).width();
				var w_height = $(window).height();
				var viewportScale = w_width/320;
				var w_height = w_height/viewportScale;
				$("#myViewport").attr("content","width=320, initial-scale="+viewportScale+", maximum-scale="+viewportScale+", user-scalable=no");
				
		<s:if test='dto.pc.size > 1'>	
				var total_page = $(".page").length ;
				var now = { row:1, col:1 }, last = { row:0, col:0};
				const towards = { up:1, right:2, down:3, left:4};
				var isAnimating = false;
				
				s=window.innerHeight/500;
				ss=250*(1-s);
				
				$('.wrap').css('-webkit-transform','scale('+s+','+s+') translate(0px,-'+ss+'px)');
				
				document.addEventListener('touchmove',function(event){
					event.preventDefault(); },false);
				
				Zepto(document).swipeUp(function(){
					if (isAnimating) return;
					last.row = now.row;
					last.col = now.col;
					if (last.row != total_page ) { now.row = last.row+1; now.col = 1; pageMove(towards.up);}	
				})
				
				Zepto(document).swipeDown(function(){
					if (isAnimating) return;
					last.row = now.row;
					last.col = now.col;
					if (last.row!=1) { now.row = last.row-1; now.col = 1; pageMove(towards.down);}	
				})
				
				function pageMove(tw){
					var lastPage = ".page-"+last.row+"-"+last.col,
						nowPage = ".page-"+now.row+"-"+now.col;
						lastPageEffect = $(lastPage).attr("effect");
						nowPageEffect = $(nowPage).attr("effect");
						
					switch(tw) {
						case towards.up:
							outClass = 'pt-page-'+lastPageEffect+'UpOut';
							inClass = 'pt-page-'+nowPageEffect+'UpIn';
							break;
						case towards.down:
							outClass = 'pt-page-'+lastPageEffect+'DownOut';
							inClass = 'pt-page-'+nowPageEffect+'DownIn';
							break; 
						/* case towards.up:
							outClass = 'pt-page-rotateFoldTop';
							inClass = 'pt-page-rotateUnfoldTop';
							break;
						case towards.down:
							outClass = 'pt-page-rotateFoldBottom';
							inClass = 'pt-page-rotateUnfoldTop';
							break; */
						/*case towards.left:
							outClass = 'pt-page-moveToLeft';
							inClass = 'pt-page-moveFromRight';
							break;
						case towards.right:
							outClass = 'pt-page-moveToRight';
							inClass = 'pt-page-moveFromLeft';
							break;*/
					}
					isAnimating = true;
					$(nowPage).removeClass("hide");
					
					$(lastPage).addClass(outClass);
					$(nowPage).addClass(inClass);
					
					setTimeout(function(){
						$(lastPage).removeClass('page-current');
						$(lastPage).removeClass(outClass);
						$(lastPage).addClass("hide");
						$(lastPage).find("img").addClass("hide");
						
						$(nowPage).addClass('page-current');
						$(nowPage).removeClass(inClass);
						$(nowPage).find("img").removeClass("hide");
						
						isAnimating = false;
					},600);
				}
				
			</s:if>
				})();
			
			<s:if test='page.bgJson.uploadMusic!=null && page.bgJson.uploadMusic !=""'>
			var gSound = '${imgDomain}${page.bgJson.uploadMusic}';
			var pop_up_note_mode = true;
	   		var note_id = 1;

	        function $$(name) {
	            return document.getElementById(name);
	        }

	         function playbksound() {
	            var audiocontainer = $$('audiocontainer');
	            if (audiocontainer != undefined) {
	                audiocontainer.innerHTML = '<audio id="bgsound" loop="loop" autoplay="autoplay"> <source src="' + gSound + '" /> </audio>';
	            }
	           
	            var audio = $$('bgsound');
	            audio.play();
	            sound_div = document.createElement("div");
	            sound_div.setAttribute("ID", "cardsound");
	            sound_div.style.cssText = "position:fixed;right:20px;top:20px;z-index:50000;visibility:visible;";
	            sound_div.onclick = switchsound;
	            if (document.body.offsetWidth > 400) {
	                bg_htm = "<img id='sound_image' src='/images/music_note_big.png'>";
	                box_htm = "<div id='note_box' style='height:100px;width:44px;position:absolute;left:-20px;top:-80px'></div>";
	            }
	            else {
	                bg_htm = "<img id='sound_image' width='30px' src='/images/music_note_big.png'>";
	                box_htm = "<div id='note_box' style='height:100px;width:44px;position:absolute;left:-5px;top:-80px'></div>";
	            }
	            sound_div.innerHTML = bg_htm + box_htm;
	            document.body.appendChild(sound_div);
	            setTimeout("popup_note()", 100);
	        } 

	        function switchsound() {
	            au = $$('bgsound');
	            ai = $$('sound_image');
	            if (au.paused) {
	                au.play();
	                ai.src = "/images/music_note_big.png";
	                pop_up_note_mode = true;
	                popup_note();
	            }
	            else {
	                pop_up_note_mode = false;
	                au.pause();
	                ai.src = "/images/music_note_big.png";
	            }
	        }

				function popup_note() {
	            box = $$("note_box");

	            note = document.createElement("span");
	            note.style.cssText = "visibility:visible;position:absolute;background-image:url('/images/music_note_small.png');width:15px;height:25px";
	            note.style.left = Math.random() * 20 + 20;
	            note.style.top = "75px";
	            this_node = "music_note_" + note_id;
	            note.setAttribute("ID", this_node);
	            note_id += 1;
	            scale = Math.random() * 0.4 + 0.4;
	            note.style.webkitTransform = "rotate(" + Math.floor(360 * Math.random()) + "deg) scale(" + scale + "," + scale + ")";
	            note.style.webkitTransition = "top 2s ease-in, opacity 2s ease-in, left 2s ease-in";
	            note.addEventListener("webkitTransitionEnd", on_pop_note_end);
	            box.appendChild(note);

	            setTimeout("document.getElementById('" + this_node + "').style.left = '0px';", 100);
	            setTimeout("document.getElementById('" + this_node + "').style.top = '0px';", 100);
	            setTimeout("document.getElementById('" + this_node + "').style.opacity = '0';", 100);

	            if (pop_up_note_mode) {
	                setTimeout("popup_note()", 600);
	            }
	        }      

	       function on_pop_note_end(event) {
	            note = event.target;

	            if (note.parentNode == $$("note_box")) {
	                $$("note_box").removeChild(note);
	             }
	        }

	        playbksound();
	        </s:if>
	        
	        $(document).ready(function(){
				setTimeout('teyue()',2000);
			})
			
			function teyue(){
				if($(".qrcj").length == 0 ){
					var xcid= '${page.entityid}';
					var pageid=${pageid};
						$.post("/${oname}/user/checkTeyue.action",{"hypage":pageid,"xcid":xcid},function(data){
					});
				}
			}
		</script>
		${html}
	<%@include file="/WEB-INF/pageshow/page_visit_time.jsp" %>
	</body>
</html>
