<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="/js/card.js"></script>
<script type="text/javascript" src="/js/block.js"></script>
<div class="popup-header">
  <button type="button" class="close closePopup" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
  <h4 class="modal-title" id="myModalLabel">互动模块编辑-刮刮乐</h4>
  <!-- 
  <button type="button" class="btn btn-primary" onclick="newGGL()">新增</button>
   -->
</div>
<div class="popup-body">
<form enctype="multipart/form-data" method="post">
	请选择需要抽奖的选项：(请至微互动模块处添加刮刮乐)<br/>
	<p id="label"></p>
	  <s:iterator value="dto.lotteryList" var="apt">
           <label class="forradio"><input type="radio" value="${apt.id}" name="dto.lotteryid" <s:if test="dto.lotteryid == #apt.id"> checked="checked" </s:if> /> ${apt.name} </label><a href="/${oname }/interact/edit_ggl.action?lid=${apt.id }&mid=10005&omid=${apt.omid }">编辑</a><br />
      </s:iterator>
</form>
</div>
<div class="popup-footer">
  <button type="button" class="btn btn-default closePopup" data-dismiss="modal">关闭</button>
  <button type="button" class="btn btn-primary" onclick="onClikLottery(${fid},${featureid},${relationid})" data-dismiss="modal">保存</button>
</div>
<script type="text/javascript">
$(document).ready(function() {
	if(${dto.lotteryid} == 0){
		$('input:radio[name="dto.lotteryid"]:first').attr('checked', 'checked');
	}
    $(".closePopup").click(function(){
		$("#rightPopup").animate({width:'hide'});
	});
});
</script>