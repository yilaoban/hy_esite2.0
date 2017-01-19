<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:iterator value="dto.zjList" var='z' status="st">
<li class="zu" data='${z.id}'>
	<div title="${z.desc }" class="component${st.count}" data='${z.id}' title="双击或拖拽添加组件"></div>
</li>
</s:iterator>
<script type="text/javascript">
	$(document).ready(function(){
		$(".zu").bind("dblclick",function(){
			var pcid = $("#currentCard").val();
			if(pcid ==null || pcid ==""){
				bootbox.alert("请选择一张卡片!",0);
				return;
			}
			var div = $(this);
			$.post("/${oname}/page/addZj.action",{"pcid":pcid,"bid":$(this).attr("data")},function(data){
				$("#card-edit").append(data);
				block_drag_click1();
			});
		});
		$( ".zu" ).draggable({
	      helper: "clone",
	      revert: "invalid"
	    });
	    $( "#card-edit").droppable({
	    	accept: ".zu",
	        drop: function(event,ui){
				var pcid = $("#currentCard").val();
				if(pcid ==null || pcid ==""){
					bootbox.alert("请选择一张卡片!",0);
					return;
				}
				var bid = ui.helper.attr("data");
				$.post("/${oname}/page/addZj.action",{"pcid":pcid,"bid":bid},function(data){
					var pcid = $('#currentCard').val();
					var desc = $('#'+pcid).attr("desc");
					reloadCard(pcid,desc);
				});
			}
	      })
	});
	
function block_drag_click1(){
	$("#card-edit .hyzj").attr("tabindex",0).mousedown(function(){
			$("#card-edit .block, #card-edit .hyzj").removeClass("block-editing");
			$(this).focus().addClass("block-editing");
		    $("#hy_data").val($(this).attr("hydata"));
	    if($(this).attr("status")==0){
		    $(this).attr("status","1").append("<div class='block-d-modal'></div>");
	    }
	    $("#weizhi_width").val($(this).css("width"));
		$("#weizhi_height").val($(this).css("height"));
	    $("#weizhi_left").val($(this).css("left"));
		$("#weizhi_top").val($(this).css("top"));
	}).unbind("keydown").keydown(function(event){
		keyDelZJ(1,event,$(this),$(this).attr("hydata"));
	}).keyup(function(event){
		if (event.which >= 37 && event.which <= 40) {
			click2save();
		}
	}).contextPopup({
		 items: [
				{label:'编辑', action:function() { editBlk(); }},
				{label:'占满一行', action:function() { $(".block-editing").css({"width":320,"left":0});click2save();}},
				null,
				{label:'置于顶层', action:function() { maxIndex = parseInt(maxIndex) + 1; $(".block-editing").css("z-index",maxIndex); click2save();}},
				{label:'置于底层', action:function() { minIndex = parseInt(minIndex) - 1; $(".block-editing").css("z-index",minIndex); click2save();}},
				null,
				{label:'删除', action:function() { var hydata = $(".block-editing").attr("hydata"); deleteBlk(hydata);}},
				]
		});
	$("#card-edit .hyzj").bind('dblclick', function(){
			var id = $(this).attr("hydata");
			if(id >0){
				$('#rightPopup .popup-content').load("showBlock.action?relationid="+id+"&pageid="+$("#pageid").val());
				$('#rightPopup').animate({width: 'show'});
			}
		});
	$("#card-edit .hyzj").draggable({
		containment: "#field",
		drag: function() {
			$("#weizhi_left").val($(this).css("left"));
			$("#weizhi_top").val($(this).css("top"));
	    },
	    stop:function(){
	    	click2save();
	    }
	}).resizable({
		handles: "n, e, s, w, sw, se, nw, ne",
		resize: function(){
			$("#weizhi_width").val($(this).css("width"));
			$("#weizhi_height").val($(this).css("height"));
		},
		stop:function(){
			click2save();
	    }
	});
}
</script>
