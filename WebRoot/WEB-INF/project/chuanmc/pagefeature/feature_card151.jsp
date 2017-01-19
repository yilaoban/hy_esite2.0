<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="/js/card.js"></script>
<script type="text/javascript" src="/js/block.js"></script>
<div class="popup-header">
  <button type="button" class="close closePopup" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
  <h4 class="modal-title" id="myModalLabel">社区管理编辑</h4>
</div>
<div class="popup-body">
	
	<form action="/page/config_sub_new.action?featureid=${featureid}" enctype="multipart/form-data" method="post">
	<input type="hidden" name="dto.fid" value="${fid}" />
	<input type="hidden" name="dto.relationid" value="${relationid}" />
	<p id="label"></p>
	<s:if test="dto.bbsForumList.size()>0">
		  <s:iterator value="dto.bbsForumList" var="forum">
	           <label class="forradio"><input type="checkbox" value="${forum.id}" name="dto.forumid" <s:if test="dto.froumidList.contains(#forum.id)"> checked="checked" </s:if> /> ${forum.title} </label><br />
	      </s:iterator>
	</s:if>
	<s:else>
		请至社区管理创建论坛类型的版块
	</s:else>
</form>
</div>
<div class="popup-footer">
  <button type="button" class="btn btn-default closePopup" data-dismiss="modal">关闭</button>
  <button type="button" class="btn btn-primary" onclick="onClikForum(${fid},${featureid},${relationid},'${type }',${pageid })" data-dismiss="modal">保存</button>
</div>
<script type="text/javascript">
$(document).ready(function() {
    $(".closePopup").click(function(){
		$("#rightPopup").animate({width:'hide'});
	});
});
</script>
