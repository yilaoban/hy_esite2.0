<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 砸金蛋 -->
<link rel="stylesheet" href="/css/hudong/zajindan/index.css" type="text/css"></link>


<style type="text/css" class_data="hy2015050743701">
.egg{height:200px;}
.egg ul li{}
.eggList{padding-top:88px;position:relative;}
.eggList li{float:left;background:url(/images/hudong/zajindan/egg.png) no-repeat -48px -60px;width:96px;height:132px;cursor:pointer;position:relative;}
.eggList li.curr{background:url(/images/hudong/zajindan/egg.png) no-repeat -161px -54px;cursor:default;z-index:101;}
.hammer{background:url(/images/hudong/zajindan/hammer.png) no-repeat;width:74px;height:87px;position:absolute; text-indent:-9999px;left:70%;top:55px;}
.tip{width:auto;padding:0 10px;position:absolute;top:80px; background:black;color:white;z-index:1000;overflow:hidden;display:none;}
.tipImg{width:60px;}
.tipButton{margin:15px 0;background-color:#474747;}
.mask{display:none;position:fixed;left:0;top:0;width:100%;height:100%;z-index:999;background:rgba(0,0,0,0.2);}
</style>


<%@include file="/WEB-INF/card/background.jsp"%>
	<s:if test='blocks[0].display=="Y"'>
  <div class="zjd_141117_jd block ${blocks[0].ctname}" status="0" hyct="Y" style="${blocks[0].hyct}" hyblk="I" hydata="${blocks[0].rid}" class_data="hy2015050743786">
	  <s:if test='blocks[0].obj.id > 0'>
		  	<div class="mask"></div>
		  	<div class="egg" class_data="hy2015050743515">
				<ul class="eggList" class_data="hy2015050743539">
					<li></li>
					<li></li>
					<li></li>
					<p class="hammer" id="hammer"></p>
				</ul>
			</div>
			<s:set name="obj" value="blocks[0].obj" />
	  </s:if>
	  <s:else>
	  	<img src="/images/hudong/zajindan/za.png" width="100%"/>
	  </s:else>
  </div>
  </s:if>
  
  <s:if test='blocks[1].display=="Y"'>
  <div class="zjd_141117_sm block ${blocks[1].ctname}" status="0" hyct="Y" style="${blocks[1].hyct}" hyblk="S" hydata="${blocks[1].rid}" class_data="hy2015050743870">
		<section class="clear">
		
		<div class="tabbed_content_141205_zjd">
			<div class="tabs_141205_zjd">
				<span class="z1tab_item tab_active" rel_data="tabcontent1">活动说明</span>
				<span class="z1tab_item" rel_data="tabcontent2">奖项设置</span>
				<span class="z1tab_item zjjl" hyvalue="${blocks[0].obj.id}" rel_data="tabcontent3">中奖纪录</span>
			</div>
		
			<div class="slide_content_141205_zjd">						
				<div class="tabslider_141205_zjd">
					<div class="z1tabcontent z1tabcontent1">
						<span class="hdsm_141205_zjd_ld" hyvar="desc">${blocks[1].desc1}</span>
					</div>
					<div class="z1tabcontent z1tabcontent2">
						<span class="hdsm_141205_zjd_ld" hyvar="desc">${blocks[1].desc2}</span>
					</div>
					<div class="z1tabcontent z1tabcontent3" >		  
					<s:if test='blocks[0].obj.id > 0'>    		
					</s:if>
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
  </s:if>
  <script type="text/javascript">
	$(document).ready(function(){
		$(".z1tabcontent1").show();
		
		$(".z1tab_item").click(function(){
			$(".z1tab_item").removeClass("tab_active");
			$(this).addClass("tab_active");
			var contentId = $(this).attr("rel_data");
			$(".z1tabcontent").hide();
			$(".z1"+contentId).show();
		});
		
	})
</script>
<%@include file="/WEB-INF/card/zjdfile.jsp"%>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
