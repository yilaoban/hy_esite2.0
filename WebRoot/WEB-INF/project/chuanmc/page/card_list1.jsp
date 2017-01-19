<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
  <div class="toolbar">
			<ul class="c_switch">
			    <li id="F_li" class="pageli"><a href="javascript:void(0);">替换卡片</a></li>
			</ul>
	</div>
</div>
<div class="modal-body">
	<form id="cardform1" action="/page/updateOldCard.action" method="post">
		<div class="clearfix">
    <div id="F_card" class="tabpage">
		<div class="slick">
        	<s:iterator value="dto.soncards" var="c" status="st">
		            	<div id="${c.id}" class="tmpl-slct" cardname="${c.name }" style="width:220px;">
		              		<img width="220px" src="${c.bimg }">
							<p>${c.name }</p>
						</div>
			</s:iterator>
		</div>
	</div>
	</div>
<input type="hidden" name="pageid" value="${pageid }">
<input type="hidden" name="cardid" id="cardidval" value="">
<input type="hidden" name="pcid" value="${pcid }">
</form>

</div>
<div class="modal-footer">
  <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
  
  <button type="button" class="btn btn-primary" onclick="subform1()" data-dismiss="modal">选择模板</button>
  
</div>

    <script type="text/javascript">
        $(document).ready(function() {
	        $('.slick').slick({
			  infinite: false,
			  slidesToShow: 3,
			  slidesToScroll: 3
			});
			
			$(".tabpage").hide();
			$("#F_card").show();
			$("#F_li").addClass("selected");
			$(".slick-slide").css("width",220);
			var F_card_L = $("#F_card .slick-slide").length * 254;
			$("#F_card .slick-track").css("width",F_card_L);
			
            var caval = $("#F_card .visible div:first").attr("id");
            $("#cardidval").val(caval);
            
        });       
        
        $(".tmpl-slct").click(function(){
	     	$(".tmpl-slct").removeClass("selected");
		    $(this).addClass("selected");
		    var caval = $(this).attr("id");
		    $("#cardidval").val(caval)
		})
		
    </script>