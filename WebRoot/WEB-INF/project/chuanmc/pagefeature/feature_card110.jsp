<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="/js/card.js"></script>
<script type="text/javascript" src="/js/block.js"></script>
<div class="popup-header">
  <button type="button" class="close closePopup" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
  <h4 class="modal-title" id="myModalLabel">内容管理模块编辑-新闻列表</h4>
</div>
<div class="popup-body">
<form  action="/page/config_sub.action?featureid=${featureid}" method="post">
<s:iterator value="dto.categoryTreeList" var="ep" status="st">
       <label class="forradio"><input type="radio" value="${ep.id}" name="dto.ccid" <s:if test="dto.ccid == #ep.id"> checked="checked" </s:if> /> ${ep.name} </label><br />
      </s:iterator>
</form>
</div>
<div class="popup-footer">
  <button type="button" class="btn btn-default closePopup" data-dismiss="modal">关闭</button>
  <button type="button" class="btn btn-primary" onclick="onclickNewList(${fid},${featureid},${relationid},'${type }',${pageid })" data-dismiss="modal">保存</button>
</div>
<script type="text/javascript">
$(document).ready(function() {
    $(".closePopup").click(function(){
		$("#rightPopup").animate({width:'hide'});
	});
});
</script>
