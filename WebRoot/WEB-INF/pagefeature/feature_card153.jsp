<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="/js/card.js"></script>
<script type="text/javascript" src="/js/block.js"></script>

<div class="popup-header">
  <button type="button" class="close closePopup" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
  <h4 class="modal-title" id="myModalLabel">互动模块编辑-测评</h4>
  <!-- 
  <button type="button" class="btn btn-primary" onclick="newResearch()">新增</button>
   -->
</div>
<div class="popup-body">
<form enctype="multipart/form-data" method="post">
	<p>请选择需要测评的选项：(请至微互动模块处添加测评)</p>
	  <s:iterator value="dto.examList" var="apt">
           <label class="block_label"><input type="radio" value="${apt.id}" name="dto.examid" <s:if test="dto.examid == #apt.id"> checked="checked" </s:if> /> ${apt.title} <a href="/${oname }/interact/to_update_exam_design.action?examid=${apt.id }&omid=${apt.omid }">编辑</a></label>
      </s:iterator>
</form>
</div>
<div class="popup-footer">
  <button type="button" class="btn btn-default closePopup" data-dismiss="modal">关闭</button>
  <button type="button" class="btn btn-primary" onclick="onClikExam(${fid},${featureid},${relationid},'${type }',${pageid },'${oname}')" data-dismiss="modal">保存</button>
</div>
<script type="text/javascript">
$(document).ready(function() {
	if(${dto.examid} == 0){
		$('input:radio[name="dto.examid"]:first').attr('checked', 'checked');
	}
    $(".closePopup").click(function(){
		$("#rightPopup").animate({width:'hide'});
	});
});
</script>