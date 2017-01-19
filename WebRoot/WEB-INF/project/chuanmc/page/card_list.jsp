<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
  <div class="toolbar">
			<ul class="c_switch">
				<li id="K_li" class="pageli"><a href="javascript:void(0);" onclick="cardChange('K')">空白页</a></li>
			    <li id="F_li" class="pageli"><a href="javascript:void(0);" onclick="cardChange('F')">首页</a></li>
				<li id="S_li" class="pageli"><a href="javascript:void(0);" onclick="cardChange('S')">图文</a></li>
				<li id="C_li" class="pageli"><a href="javascript:void(0);" onclick="cardChange('C')">列表</a></li>
				<li id="D1_li" class="pageli"><a href="javascript:void(0);" onclick="cardChange('D1')">抽奖类</a></li>
				<li id="D4_li" class="pageli"><a href="javascript:void(0);" onclick="cardChange('D4')">微调研</a></li>
				<li id="D5_li" class="pageli"><a href="javascript:void(0);" onclick="cardChange('D5')">微申请</a></li>
				<li id="D6_li" class="pageli"><a href="javascript:void(0);" onclick="cardChange('D6')">投票</a></li>
				<!-- 
				<li id="D7_li" class="pageli"><a href="javascript:void(0);" onclick="cardChange('D7')">积分</a></li>
				<li id="D2_li" class="pageli"><a href="javascript:void(0);" onclick="cardChange('D2')">测评</a></li>
				<li id="Q_li" class="pageli" <s:if test='type == "S" || type =="D" || type =="K"'>style="display: none;"</s:if>><a href="javascript:void(0);" onclick="cardChange('Q')">社区</a></li>
				 -->
			</ul>
	</div>
</div>
<div class="modal-body">
	<form id="cardform" action="/page/addNewCard.action" method="post">
		<div class="clearfix">
    <div id="F_card" class="tabpage">
		<div class="slick">
        	<s:iterator value="dto.fcards" var="c" status="st">
		            	<div id="${c.id}" class="tmpl-slct" cardname="${c.name }" carddesc="${c.desc }" style="width:220px;">
		              		<img width="220px" src="${c.bimg }">
							<p>${c.name }</p>
						</div>
			</s:iterator>
		</div>
	</div>
	<div id="S_card" class="tabpage">
		<div class="slick">
        	<s:iterator value="dto.scards" var="c" status="st">
		            	<div id="${c.id}" class="tmpl-slct" cardname="${c.name }" carddesc="${c.desc }" style="width:220px;">
		              		<img width="220px" src="${c.bimg }">
							<p>${c.name }</p>
						</div>
			</s:iterator>
		</div>
	</div>
	<div id="C_card" class="tabpage">
		<div class="slick">
        	<s:iterator value="dto.ccards" var="c" status="st">
		            	<div id="${c.id}" class="tmpl-slct" cardname="${c.name }" carddesc="${c.desc }" style="width:220px;">
		              		<img width="220px" src="${c.bimg }">
							<p>${c.name }</p>
						</div>
			</s:iterator>
		</div>
	</div>
	<!-- 
	 <div id="Q_card" class="tabpage">
		<div class="slick">
        	<s:iterator value="dto.qcards" var="c" status="st">
		            	<div id="${c.id}" class="tmpl-slct" cardname="${c.name }" carddesc="${c.desc }" style="width:220px;">
		              		<img width="220px" src="${c.bimg }">
							<p>${c.name }</p>
						</div>
			</s:iterator>
		</div>
	</div>
	 -->
	<div id="K_card" class="tabpage">
		<div class="slick">
        	<s:iterator value="dto.kcards" var="c" status="st">
		            	<div id="${c.id}" class="tmpl-slct" cardname="${c.name }" carddesc="${c.desc }" style="width:220px;">
		              		<img width="220px" src="${c.bimg }">
							<p>${c.name }</p>
						</div>
			</s:iterator>
		</div>
	</div>
	<div id="D1_card" class="tabpage">
		<div class="slick">
        	<s:iterator value="dto.d1cards" var="c" status="st">
		            	<div id="${c.id}" class="tmpl-slct" cardname="${c.name }" carddesc="${c.desc }" style="width:220px;">
		              		<img width="220px" src="${c.bimg }">
							<p>${c.name }</p>
						</div>
			</s:iterator>
		</div>
	</div>
	<div id="D4_card" class="tabpage">
		<div class="slick">
        	<s:iterator value="dto.d4cards" var="c" status="st">
		            	<div id="${c.id}" class="tmpl-slct" cardname="${c.name }" carddesc="${c.desc }" style="width:220px;">
		              		<img width="220px" src="${c.bimg }">
							<p>${c.name }</p>
						</div>
			</s:iterator>
		</div>
	</div>
	<div id="D5_card" class="tabpage">
		<div class="slick">
        	<s:iterator value="dto.d5cards" var="c" status="st">
		            	<div id="${c.id}" class="tmpl-slct" cardname="${c.name }" carddesc="${c.desc }" style="width:220px;">
		              		<img width="220px" src="${c.bimg }">
							<p>${c.name }</p>
						</div>
			</s:iterator>
		</div>
	</div>
	<div id="D6_card" class="tabpage">
		<div class="slick">
        	<s:iterator value="dto.d6cards" var="c" status="st">
		            	<div id="${c.id}" class="tmpl-slct" cardname="${c.name }" carddesc="${c.desc }" style="width:220px;">
		              		<img width="220px" src="${c.bimg }">
							<p>${c.name }</p>
						</div>
			</s:iterator>
		</div>
	</div>
	<div id="D7_card" class="tabpage">
		<div class="slick">
        	<s:iterator value="dto.d7cards" var="c" status="st">
		            	<div id="${c.id}" class="tmpl-slct" cardname="${c.name }" carddesc="${c.desc }" style="width:220px;">
		              		<img width="220px" src="${c.bimg }">
							<p>${c.name }</p>
						</div>
			</s:iterator>
		</div>
	</div>
	<div id="D2_card" class="tabpage">
		<div class="slick">
        	<s:iterator value="dto.d2cards" var="c" status="st">
            	<div id="${c.id}" class="tmpl-slct" cardname="${c.name }" carddesc="${c.desc }" style="width:220px;">
              		<img width="220px" src="${c.bimg }">
					<p>${c.name }</p>
				</div>
			</s:iterator>
		</div>
	</div>
</div>
<input type="hidden" name="pageid" value="${pageid }">
<input type="hidden" name="cardid" id="cardidval" value="">
</form>

</div>
<div class="modal-footer">
  <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
  
  <button type="button" class="btn btn-primary" onclick="subform()" data-dismiss="modal">选择模板</button>
  
</div>

    <script type="text/javascript">
        $(document).ready(function() {
        	var type = '${type}';
        	if(type == "S" || type.startWith("D") || type == "K"){
        		$('#F_li').hide();
        		$('#C_li').hide();
			}
        
	        $('.slick').slick({
			  infinite: false,
			  slidesToShow: 3,
			  slidesToScroll: 3
			});
			
			$(".tabpage").hide();
			
			$("#K_card").show();
			$("#K_li").addClass("selected");
			
			$(".slick-slide").css("width",220);
			var F_card_L = $("#F_card .slick-slide").length * 254;
			$("#F_card .slick-track").css("width",F_card_L);
			var S_card_L = $("#S_card .slick-slide").length * 254;
			$("#S_card .slick-track").css("width",S_card_L);
			var C_card_L = $("#C_card .slick-slide").length * 254;
			$("#C_card .slick-track").css("width",C_card_L);
			var D1_card_L = $("#D1_card .slick-slide").length * 254;
			$("#D1_card .slick-track").css("width",D1_card_L);
			var D4_card_L = $("#D4_card .slick-slide").length * 254;
			$("#D4_card .slick-track").css("width",D4_card_L);
			var D5_card_L = $("#D5_card .slick-slide").length * 254;
			$("#D5_card .slick-track").css("width",D5_card_L);
			var D6_card_L = $("#D6_card .slick-slide").length * 254;
			$("#D6_card .slick-track").css("width",D6_card_L);
			var D7_card_L = $("#D7_card .slick-slide").length * 254;
			$("#D7_card .slick-track").css("width",D7_card_L);
			
			var Q_card_L = $("#Q_card .slick-slide").length * 254;
			$("#Q_card .slick-track").css("width",Q_card_L);
			
            var caval = $("#F_card .visible div:first").attr("id");
            $("#cardidval").val(caval);
            
        });       
        
        $(".tmpl-slct").click(function(){
	     	$(".tmpl-slct").removeClass("selected");
		    $(this).addClass("selected");
		    var caval = $(this).attr("id");
		    $("#cardidval").val(caval)
		})
		
		function cardChange(type){
		  $(".tmpl-slct").removeClass("selected");
		  $("#cardidval").val(0);
          $(".tabpage").hide();
          $("#"+type+"_card").show();
          $(".pageli").removeClass("selected");
          $("#"+type+"_li").addClass("selected");
    	}
    </script>