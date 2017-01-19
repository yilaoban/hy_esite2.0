<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div>站点名称：<input class='text-medium' id='sitename'/></div>
<div class="modal-header">
  <div class="toolbar">
			<ul class="c_switch">
			    <li id="W_li" class="pageli"><a href="javascript:void(0);" onclick="cardChange('W')">微现场</a></li>
				<li id="B_li" class="pageli"><a href="javascript:void(0);" onclick="cardChange('B')">微社区</a></li>
				<li id="C_li" class="pageli"><a href="javascript:void(0);" onclick="cardChange('C')">合伙人</a></li>
			</ul>
	</div>
</div>
<div class="modal-body">
	<form id="cardform" action="" method="post">
		<input type="hidden" name="copytype" id="copytype" value="W"/>
		<div class="clearfix">
    <div id="W_card" class="tabpage">
		<div class="slick">
        	<s:iterator value="dto.sitegroup" var="s">
				<s:if test='type=="W"'><input type="radio" name="sitegroupid"  value="${s.id }"/>${s.groupname}</s:if>
			</s:iterator>
		</div>
	</div>
	<!-- 微社区 -->
	<div id="B_card" class="tabpage">
		<div class="slick">
        	<s:iterator value="dto.sitegroup" var="s">
				<s:if test='type=="B"'><input type="radio" name="sitegroupid" value="${s.id }"/>${s.groupname}</s:if>
			</s:iterator>
		</div>
	</div>
	<!-- 微传播 -->
	<div id="C_card" class="tabpage">
		<div class="slick">
        	<s:iterator value="dto.sitegroup" var="s">
				<s:if test='type=="C"'><input type="radio" name="sitegroupid" value="${s.id }"/>${s.groupname}</s:if>
			</s:iterator>
		</div>
	</div>
	</div>
</form>

</div>
<div class="modal-footer">
  <button type="button" class="btn btn-default" data-dismiss="modal" onclick="closeFrame()">关闭</button>
  <button type="button" class="btn btn-primary" onclick="sgsubform()" data-dismiss="modal">选择套装</button>
  
</div>

    <script type="text/javascript">
        $(document).ready(function() {
			
			$(".tabpage").hide();
			$("#W_card").show();
			$("#W_li").addClass("selected");
			$(".slick-slide").css("width",220);
            $("#W_card").find("input[name='sitegroupid']").eq(0).attr("checked", true); 
            
            
            $(".tabpage").each(function(){
				if($(this).find("input[name='sitegroupid']").length==0){
					$(this).find(".slick").eq(0).append("暂无此类型的套装");
				}            	
            })
        });       
        
        $(".tmpl-slct").click(function(){
	     	$(".tmpl-slct").removeClass("selected");
		    $(this).addClass("selected");
		})
		
		function cardChange(type){
		  $("#copytype").val(type);
          $(".tabpage").hide();
         
          $(".pageli").removeClass("selected");
          $("#"+type+"_li").addClass("selected");
	      $("#"+type+"_card").show();
	      $("input[name='sitegroupid']").each(function(){
	    	 $(this).attr("checked", false); 
	      });
          $("#"+type+"_card").find("input[name='sitegroupid']").eq(0).attr("checked", true); 
    	}
    	
    	function sgsubform(){
    		var sgid=$('input:radio[name="sitegroupid"]:checked').val();
    		var copytype=$("#copytype").val();
    		var sitename=$("#sitename").val();
    		if(sitename==''){
    			layer.alert("请输入站点名称",0);
				return;
    		
    		}
    		if($('input:radio[name="sitegroupid"]:checked').length==0){
				layer.confirm('您没有选择套装,将创建一个空白站点?',function(){
					$.post("copySite.action",{"sitename":sitename,"copyType":copytype,"groupid":sgid},function(data){
					if(data == 1){
						layer.msg('创建中！请稍等……', {icon: 6, time: 1500}, function(){
							window.parent.location.reload();
						}); 
					}else if(data==-1){
						layer.msg('创建失败！重复的站点名称……', {icon: 5, time: 2000});
					}else{
						layer.msg('创建失败！请重试……', {icon: 5, time: 2000});
					}
					});
				});
    		}else{
    			$.post("copySite.action",{"sitename":sitename,"copyType":copytype,"groupid":sgid},function(data){
					if(data == 1){
						layer.msg('创建中！请稍等……', {icon: 6, time: 1500}, function(){
							window.parent.location.reload();
						}); 
					}else if(data==-1){
						layer.msg('创建失败！重复的站点名称……', {icon: 5, time: 2000});
					}else{
						layer.msg('创建失败！请重试……', {icon: 5, time: 2000});
					}
				});
    		}
	   }
    </script>