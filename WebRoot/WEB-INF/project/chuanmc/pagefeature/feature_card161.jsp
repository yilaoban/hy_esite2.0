<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="/js/card.js"></script>
<script type="text/javascript" src="/js/block.js"></script>
<div class="popup-header">
  <button type="button" class="close closePopup" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
  <h4 class="modal-title" id="myModalLabel">抽奖-九宫格</h4>
</div>
<div class="popup-body">
<form id="myform" class="formview jNice">
	<dl>
	 	<dt>标题</dt>
		<dd>
			<input id="title" onblur="checkTitle1()" type="text" class="text-medium"  name="dto.lottery.name" value="${dto.lottery.name }">
			<span class="must">*</span>
			<span id="title_"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>开始时间</dt>
		<dd>
			<input onblur="checkTitle2()" id="startTime" value="<s:date name="dto.lottery.starttime" format="yyyy-MM-dd HH:mm:ss"/>" type="text" name="dto.lottery.starttime" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
			<span class="must">*</span>
			<span id="startTime_"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>结束时间</dt>
		<dd>
			<input onblur="checkTitle3()" id="endTime" value="<s:date name="dto.lottery.endtime" format="yyyy-MM-dd HH:mm:ss"/>" type="text" name="dto.lottery.endtime" class="Wdate"	onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
			<span class="must">*</span>
			<span id="endTime_"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>抽奖机会</dt>
		<dd>
			抽奖总次数<input type="text" class="text-small" name="dto.lottery.usertotal" value="${dto.lottery.usertotal }"/>次
		</dd>
		<dd>
			每日抽奖次数<input type="text" class="text-small" name="dto.lottery.userdaynum" value="${dto.lottery.userdaynum }"/>次
		</dd>
	</dl>
	<dl>
	 	<dt>中奖率</dt>
		<dd>
			万分之<input type="text" class="text-medium" name="dto.lottery.zjl" value="${dto.lottery.zjl }" onkeyup="this.value = this.value.replace(/\D/g,'');"/>
		</dd>
	</dl>
	<dl>
	 	<dt></dt>
		<dd>
			<button style="width:200px" class="btn btn-default" onclick="prize_set('${dto.lottery.id}');return false;">奖品设置</button>
		</dd>
	</dl>
	<input type="hidden" name="dto.type" value="T">
	<input type="hidden" name="dto.lottery.id" value="${dto.lottery.id}" />
	<input type="hidden" name="dto.fid" value="${fid}" />
	<input type="hidden" name="dto.relationid" value="${relationid}" />
</form>
</div>
<div class="popup-footer">
  <button type="button" class="btn btn-default closePopup"  data-dismiss="modal">关闭</button>
  <button type="button" class="btn btn-primary" id="save161" data-dismiss="modal">保存</button>
</div>
<script type="text/javascript">
$(document).ready(function() {
    $(".closePopup").click(function(){
		$("#rightPopup").animate({width:'hide'});
	});
    
    $("#prizeModal").on("hidden.bs.modal", function() {
	    $(this).removeData("bs.modal");
	});
	$("#prizeModal").on("hidden.bs.modal", function() {
		 $(".colpick").remove();
	})
});

function prize_set(lid){
	$('#prizeModal').modal({
		backdrop: false,
		remote:"/${oname}/interact/edit_nine_prize.action?lid="+lid
	});
}

$("#save161").click(function(){
	if($("#title").val()==""){
		$("#title_").html("请输入标题").css("color", "RED");
		window.location.hash="maodian";
	}else if(document.getElementById("startTime").value==""){
		$("#startTime_").html("请输入开始时间").css("color", "RED");
		window.location.hash="maodian";
	}else if(document.getElementById("endTime").value==""){
		$("#endTime_").html("请输入结束时间").css("color", "RED");
		window.location.hash="maodian";
	}else{
		$.ajax({
	        cache: true,
	        type: "POST",
	        url:"/${oname}/page/config_sub_new.action?featureid=${featureid}",
	        data:$('#myform').serialize(),
	        async: false,
	        success: function(data) {
	        	if(data == "Y"){
	        		$("#rightPopup").animate({width:'hide'});
	        		var cardid = $("#cardid").val();
	        		var desc = $('#'+cardid).attr("desc");
	        		reloadCard(cardid,desc);
	        	}else{
	        		bootbox.alert("保存失败！请重试…");
	        	}
	        }
	    });	
	}
});
</script>