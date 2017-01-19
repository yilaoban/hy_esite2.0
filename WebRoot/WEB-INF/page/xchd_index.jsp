<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="modal-header">
  <div class="toolbar">
			<ul class="c_switch">
			    <li id="V_li" class="pageli"><a href="javascript:void(0);" onclick="cardChange('V')">投票</a></li>
			    <li id="L_li" class="pageli"><a href="javascript:void(0);" onclick="cardChange('L')">砸金蛋</a></li>
			    <li id="Z_li" class="pageli"><a href="javascript:void(0);" onclick="cardChange('Z')">大转盘</a></li>
			</ul>
	</div>
</div>
<div class="modal-body">
	<form id="cardform" action="" method="post">
		<input type="hidden" name="hdtype" id="hdtype" value="10002"/>
		<input type="hidden" name="xcid" id="xcid" value="${xcid }"/>
		<div class="clearfix">
    <div id="V_card" class="tabpage">
		<div class="slick">
        	<s:iterator value="dto.list" var="s">
        		<s:if test='#s.type=="V"'>
					<input type="radio" name="sitegroupid" value="${s.id }" onclick="typechange(10002)"/>${s.groupname}
        		</s:if>
			</s:iterator>
		</div>
	</div>
	 <div id="L_card" class="tabpage">
		<div class="slick">
        	<s:iterator value="dto.list" var="s">
        		<s:if test='#s.type=="L"'>
					<input type="radio" name="sitegroupid" value="${s.id }" onclick="typechange(10004)"/>${s.groupname}
        		</s:if>
			</s:iterator>
		</div>
	</div>
	<div id="Z_card" class="tabpage">
		<div class="slick">
        	<s:iterator value="dto.list" var="s">
        		<s:if test='#s.type=="Z"'>
					<input type="radio" name="sitegroupid" value="${s.id }" onclick="typechange(10003)"/>${s.groupname}
        		</s:if>
			</s:iterator>
		</div>
	</div>
	</div>
</form>

</div>
<div class="modal-footer">
  <button type="button" class="btn btn-default" data-dismiss="modal" onclick="closeFrame()">关闭</button>
  <button type="button" class="btn btn-primary" onclick="sgsubform()" data-dismiss="modal">选择互动</button>
  
</div>

    <script type="text/javascript">
        $(document).ready(function() {
			
			$(".tabpage").hide();
			$("#V_card").show();
			$("#V_li").addClass("selected");
			$(".slick-slide").css("width",220);
			var V_card_L = $("#V_card .slick-slide").length * 254;
			$("#V_card .slick-track").css("width",V_card_L);
			
			
            var caval = $("#V_card .visible div:first").attr("id");
            $("#cardidval").val(caval);
            
        });       
        
        $(".tmpl-slct").click(function(){
	     	$(".tmpl-slct").removeClass("selected");
		    $(this).addClass("selected");
		    var caval = $(this).attr("id");
		    $("#cardidval").val(caval)
		})
		
		function cardChange(type){
          $(".tabpage").hide();
          $("#"+type+"_card").show();
          $(".pageli").removeClass("selected");
          $("#"+type+"_li").addClass("selected");
    	}
    	
    	function typechange(hdtype){
    		$("#hdtype").val(hdtype);
    	}
    	
    	function sgsubform(){
	    		var groupid=$("input:radio[name='sitegroupid']:checked").val();
	    		if($('input:radio[name="sitegroupid"]:checked').length==0){
	    			layer.alert("请选择一个现场互动",0);
					return;
	    		}else{
	    			var s=$('#hdtype').val();
	    			var xcid=$('#xcid').val();
					$.post("/${oname}/interact/addXchd.action",{"interactid":s,"xcid":xcid,"groupid":groupid},function(data){
						if(data>0){
							layer.alert("创建成功!",0);
							window.parent.location.reload();
						}else{
							layer.alert("创建失败!",0);
						}
					});
	    		}
	    	}
    </script>