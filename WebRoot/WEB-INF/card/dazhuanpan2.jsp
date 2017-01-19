<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 大转盘2 -->
<script type="text/javascript" src="/js/hudong/dazhuanpan1/awardRotate.js"></script>
<link rel="stylesheet" href="/css/hudong/dazhuanpan1/demo.css" type="text/css"></link>

<%@include file="/WEB-INF/card/background.jsp"%>

<s:if test='blocks[0].display=="Y"'>
<div class="wdy_top block ${blocks[0].ctname}" status="0" hyct="Y" style="${blocks[0].hyct}" hyblk="S" hydata="${blocks[0].rid}" class_data="hy20150507111858">
	<img src="${blocks[0].img}" style="width:100%;height:100%;" hyvar="img" hydesc="640*350"/>
</div>
</s:if>
<s:if test='blocks[1].display=="Y"'>
<div class="turntable-bg block ${blocks[1].ctname}" status="0" hyct="Y" style="${blocks[1].hyct}" hydata="${blocks[1].rid}">
	<s:if test='blocks[1].obj.id > 0'> 
		<div class="pointer"><img src="/images/hudong/dazhuanpan2/dzp_simg.png" alt="pointer" width=100% height=100% /></div>
		<div class="rotate" ><img id="rotate" src="/images/hudong/dazhuanpan2/dzp_bimg.png" alt="turntable" width=100% height=100% /></div>
		<s:set name="obj" value="blocks[1].obj" />
	</s:if>
	<s:else>
		<img src="/images/hudong/dazhuanpan2/dzp_03.png" width="auto\9" height="auto" style="max-width: 100%;" hyvar="img" hydesc="320*210"/>
	</s:else>
</div>
</s:if>

<s:if test='blocks[2].display=="Y"'>
	<div class="jx_zhan_dazhuanpan1_141113_1 block  ${blocks[2].ctname}" status="0" hyct="Y" style="${blocks[2].hyct}" hydata="${blocks[2].rid}" class_data="hy20150507111306">
		<div class="jx_zhuan_zi_dazhuanpan1_141113_1">
			<section class="clear">
		
     		<div class="tabbed_content_141204_1">
			<div class="tabs_141204_1">
				<span class="d1tab_item tab_active"  rel_data="tabcontent1">活动说明</span>
				<span class="d1tab_item" rel_data="tabcontent2">奖项设置</span>
				<span class="d1tab_item zjjl" hyvalue="${blocks[1].obj.id}" rel_data="tabcontent3">中奖纪录</span>
			</div>
			
			<div class="slide_content_141204_1">						
				<div class="tabslider_141204_1">
					<div class="d1tabcontent d1tabcontent1">
						<span hyvar="desc">${blocks[2].desc1}</span>
					</div>
					<div class="d1tabcontent d1tabcontent2">
						<span hyvar="desc">${blocks[2].desc2}</span>
				  	</div>  
				  	<s:if test='blocks[1].obj.id > 0'>
					</s:if>
					<div class="d1tabcontent d1tabcontent3">		  
					<s:else>
						<div>恭喜您获得iphone6一部；</div>
						<div>恭喜您获得20积分；</div>
						<div>恭喜您获得50元优惠券<br/>
				                 券号：ssdddd<br/>
				                 密码：123343<br/>
					</div>
					</s:else>        		
					</div>
				</div>
				<br style="clear:both" />
			</div>
			</div>
			</section>
		</div>
	</div>
</s:if>
<script type="text/javascript">
	$(document).ready(function(){
		$(".d1tabcontent1").show();
		
		$(".d1tab_item").click(function(){
			$(".d1tab_item").removeClass("tab_active");
			$(this).addClass("tab_active");
			var contentId = $(this).attr("rel_data");
			$(".d1tabcontent").hide();
			$(".d1"+contentId).show();
		});
	})
</script>
<%@include file="/WEB-INF/card/dzpfileFprize.jsp"%>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
